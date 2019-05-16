package group.cc.df.service.impl;

import group.cc.core.AbstractService;
import group.cc.df.dao.*;
import group.cc.df.dto.DfCollectFormDTO;
import group.cc.df.model.*;
import group.cc.df.service.DfCollectFormService;
import group.cc.df.utils.CollectFormStateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author gxd
 * @date 2019/04/16
 */
@Service
@Transactional
public class DfCollectFormServiceImpl extends AbstractService<DfCollectForm> implements DfCollectFormService {
    @Resource
    private DfCollectFormMapper dfCollectFormMapper;

    @Resource
    private DfFormFieldMapper dfFormFieldMapper;

    @Resource
    private DfDynamicFormMapper dfDynamicFormMapper;

    @Resource
    private DfUserMapper dfUserMapper;

    @Resource
    private DfFormItemMapper dfFormItemMapper;

    @Resource
    private DfCollectFormEditApplyMapper dfCollectFormEditApplyMapper;

    @Override
    public void saveCollectForm(Map<String, Object> collectFormMap) {
        DfCollectForm collectForm = this.handleFormInfo(collectFormMap);
        if (collectForm == null) {
            return;
        }

        collectForm.setState(CollectFormStateUtil.NO_EDITOR);
        this.dfCollectFormMapper.saveCollectForm(collectForm);

        Integer collectFormId = collectForm.getId();

        List<Map<String, Object>> collectFieldList = (List<Map<String, Object>>) collectFormMap.get("list");

        this.saveCollectFormField(collectFieldList, 0, collectFormId, -1);

    }

    @Override
    public DfCollectForm findSelfCollectFormByFormId(Integer formId) {
        DfUser user = (DfUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        Integer userId = user.getId();
        // 因为每个表单用户只能填写一次,所以查询出来的记录要么为空,要么只有第一条
        List<DfCollectForm> collectFormList = this.dfCollectFormMapper.findCollectFormByEmployeeIdAndFormId(userId, formId);

        return collectFormList == null || collectFormList.isEmpty() ? null : collectFormList.get(0);
    }

    private void saveCollectFormField(List<Map<String, Object>> collectFieldList, Integer displayIndex,
                                      Integer collectFormId, Integer parentId) {
        for (Map<String, Object> collectFieldMap : collectFieldList) {
            DfFormField dfField = handleGeneralField(collectFieldMap);

            if (dfField == null) {
                break;
            }
            dfField.setDisplayIndex(displayIndex++);

            if (parentId != -1) {
                dfField.setParentId(parentId);
            }

            String fieldType = dfField.getType();
            String defaultValue = "";

            dfField.setValue(defaultValue);

            if (!fieldType.equals("grid")) {
                Map<String, Object> optionsMap = (Map<String, Object>) collectFieldMap.get("options");

                if (fieldType.equals("checkbox")) {
                    List<String> checkboxList = (List<String>) optionsMap.get("defaultValue");

                    for (String str : checkboxList) {
                        if (!"".equals(str)) {
                            defaultValue += str + ",";
                        }
                    }

                    if (!defaultValue.equals("")) {
                        defaultValue = defaultValue.substring(0, defaultValue.length() - 1);
                    }

                } else if (fieldType.equals("date")) {
                    String dateUTC = (String) optionsMap.get("defaultValue");
                    defaultValue = UTC2Date(dateUTC);
                } else {
                    defaultValue = (String) optionsMap.get("defaultValue");
                }

                dfField.setValue(defaultValue);
                dfField.setFormId(collectFormId);

                this.dfFormFieldMapper.saveCollectFormField(dfField);
                int formFieldId = dfField.getId();

                if (fieldType.equals("checkbox") || fieldType.equals("radio") || fieldType.equals("select")) {
                    List<DfFormItem> itemList = handleFieldItem(optionsMap);

                    // 存储表单域条目
                    for (DfFormItem item : itemList) {
                        item.setFormFieldId(formFieldId);
                        this.dfFormItemMapper.saveCollectFormItem(item);
                    }
                }
            } else {
                dfField.setFormId(collectFormId);
                // 保存表单域信息
                dfFormFieldMapper.saveCollectFormField(dfField);
                int formFieldId = dfField.getId();

                List<Map<String, Object>> columnList = (List<Map<String, Object>>) collectFieldMap.get("columns");
                handleColumns(columnList, collectFormId, formFieldId, "save");
            }
        }
    }

    /**
     * 处理栅栏布局中的子列表
     * @param columnList
     */
    private void handleColumns(List<Map<String, Object>> columnList, int dfFormId, int parentId, String sign) {
        if (columnList.size() > 0) {
            int count = 0;

            for (Map<String, Object> columnMap: columnList) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) columnMap.get("list");
                if ("save".equals(sign)) {
                    // 进行新增操作
                    this.saveCollectFormField(list, count++, dfFormId, parentId);
                } else {
                }
            }
        }
    }

    /**
     * 将UTC格式的时间字符串转为Java标准格式
     * @param dateUTC
     * @return
     */
    private String UTC2Date(String dateUTC) {
        String dateStr = "";

        if (dateUTC.equals("")) {
            return dateStr;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 如果能用sdf直接解析,则说明时间格式不是UTC
        try {
            Date date = sdf.parse(dateUTC);
            dateStr = sdf.format(date);
            return dateStr;
        } catch (ParseException e) {
            /*e.printStackTrace();*/
        }

        dateUTC = dateUTC.replace("Z", " UTC");

        try {
            Date date = format.parse(dateUTC);
            dateStr = sdf.format(date);
            return dateStr;
        } catch (ParseException e) {
            /*e.printStackTrace();*/
        }

        return dateStr;
    }


    /**
     * 处理包含条目的表单域信息(多选框,单选框,下拉框)
     * @param optionsMap
     * @return
     */
    private List<DfFormItem> handleFieldItem(Map<String, Object> optionsMap) {
        List<DfFormItem> itemList = null;
        List<Map<String, Object>> optionsList = (List<Map<String, Object>>) optionsMap.get("options");

        if (optionsList.size() > 0) {
            itemList = new ArrayList<>();
        }

        for (int i = 0; i < optionsList.size(); i++) {
            Map<String, Object> itemConfigMap = optionsList.get(i);

            String value = (String) itemConfigMap.get("value");
            Integer formFieldId = itemConfigMap.get("formFieldId") == null ? null : (Integer) itemConfigMap.get("formFieldId");

            DfFormItem dfFormItem = new DfFormItem();

            dfFormItem.setLabel(value);
            dfFormItem.setValue(value);
            dfFormItem.setFormFieldId(formFieldId);
            dfFormItem.setItemIndex(String.valueOf(i));

            itemList.add(dfFormItem);
        }
        return itemList;
    }

    /**
     * 处理通用表单域信息,因为这里进行的是信息收集,可以直接忽略Id
     * @param fieldMap
     * @return
     */
    private DfFormField handleGeneralField(Map<String, Object> fieldMap) {
        DfFormField dfField = null;

        if (fieldMap != null) {
            String type = (String) fieldMap.get("type");
            String label = (String) fieldMap.get("label");
            String key = (String) fieldMap.get("key");
            String model = (String) fieldMap.get("model");

            dfField = new DfFormField();

            dfField.setType(type);
            dfField.setLabel(label);
            dfField.setKey(key);
            dfField.setModel(model);
        }

        return dfField;
    }

    /**
     * 从Map中获取表单Id,构件一个DfCollectForm实例
     * @param collectFormMap
     * @return
     */
    private DfCollectForm handleFormInfo(Map<String, Object> collectFormMap) {
        if (collectFormMap == null) {
            return null;
        }

        Map<String, Object> configMap = (Map<String, Object>) collectFormMap.get("config");

        if (configMap != null) {
            DfCollectForm collectForm = new DfCollectForm();

            Integer formId = (Integer) configMap.get("id");
            DfUser user = (DfUser) SecurityUtils.getSubject().getSession().getAttribute("user");

            collectForm.setFormId(formId);
            collectForm.setEmployeeId(user.getId());
            collectForm.setSubmitTime(new Date());

            return collectForm;
        }

        return null;
    }

    @Override
    public Map<String, Object> findCollectFormByCondition(Map<String, Object> conditionMap) {
        Map<String, Object> resultMap = new HashMap<>();

        Integer pageSize = (Integer) conditionMap.get("pageSize");
        Integer pageNum = (Integer) conditionMap.get("pageNum");
        Integer offset = (pageNum - 1) * pageSize;
        conditionMap.put("offset", offset);

        List<DfCollectForm> collectFormList = this.dfCollectFormMapper.findCollectFormByCondition(conditionMap);
        int total = this.dfCollectFormMapper.findCollectFormCountByCondition(conditionMap);

        List<DfCollectFormDTO> collectFormDTOList = this.handleCollectFormList2CollectFormDTOList(collectFormList);

        resultMap.put("listInfo", collectFormDTOList);
        resultMap.put("total", total);
        return resultMap;
    }

    @Override
    public Map<String, Object> findSelfSubmitFormByCondition(Map<String, Object> conditionMap) {
        Map<String, Object> resultMap = new HashMap<>();

        Integer pageSize = (Integer) conditionMap.get("pageSize");
        Integer pageNum = (Integer) conditionMap.get("pageNum");
        Integer offset = (pageNum - 1) * pageSize;
        conditionMap.put("offset", offset);

        // 因为在mapper中该方法利用了动态SQL,所以可以在此进行服用
        List<DfCollectForm> selfSubmitFormList = this.dfCollectFormMapper.findCollectFormByCondition(conditionMap);
        int total = this.dfCollectFormMapper.findCollectFormCountByCondition(conditionMap);

        List<DfCollectFormDTO> collectFormDTOList = this.handleCollectFormList2CollectFormDTOList(selfSubmitFormList);

        resultMap.put("listInfo", collectFormDTOList);
        resultMap.put("total", total);
        return resultMap;
    }

    @Override
    public List<DfCollectFormDTO> findFormLikeName(String formName) {
        DfUser user = (DfUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        if (user != null) {
            List<DfCollectFormDTO> dto = this.dfCollectFormMapper.findLikeFormNameAndSubmiterId(formName, user.getId());
            return dto;
        }
        return new ArrayList<>();
    }

    /**
     * 将CollectForm列表转化为CollectFormDTO列表
     * @param collectFormList
     * @return
     */
    private List<DfCollectFormDTO> handleCollectFormList2CollectFormDTOList(List<DfCollectForm> collectFormList) {
        if (collectFormList == null || collectFormList.isEmpty()) {
            return null;
        }

        List<DfCollectFormDTO> dfCollectFormDTOList = new ArrayList<>(collectFormList.size());
        for (DfCollectForm collectForm : collectFormList) {
            Integer userId = collectForm.getEmployeeId();
            DfUser user = this.dfUserMapper.selectByPrimaryKey(userId);
            Integer formId = collectForm.getFormId();
            DfDynamicForm form = this.dfDynamicFormMapper.selectByPrimaryKey(formId);
            DfCollectFormEditApply applyInfo = this.dfCollectFormEditApplyMapper
                    .findCollectFormEditApplyByEmployeeIdAndCollectFormId(collectForm.getEmployeeId(), collectForm.getId());

            DfCollectFormDTO dto = new DfCollectFormDTO();
            dto.setCollectForm(collectForm);
            dto.setDynamicForm(form);
            dto.setSubmiter(user);
            dto.setApplyInfo(applyInfo);

            dfCollectFormDTOList.add(dto);
        }

        return dfCollectFormDTOList;
    }
}
