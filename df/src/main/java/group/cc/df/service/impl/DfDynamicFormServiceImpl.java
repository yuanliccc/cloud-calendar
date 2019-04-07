package group.cc.df.service.impl;

import group.cc.df.dao.DfDynamicFormMapper;
import group.cc.df.dao.DfFormFieldMapper;
import group.cc.df.dao.DfFormItemMapper;
import group.cc.df.model.DfDynamicForm;
import group.cc.df.model.DfFormField;
import group.cc.df.model.DfFormItem;
import group.cc.df.service.DfDynamicFormService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author gxd
 * @date 2019/01/10
 */
@Service
@Transactional
public class DfDynamicFormServiceImpl extends AbstractService<DfDynamicForm> implements DfDynamicFormService {
    @Resource
    private DfDynamicFormMapper dfDynamicFormMapper;

    @Resource
    private DfFormFieldMapper dfFormFieldMapper;

    @Resource
    private DfFormItemMapper dfFormItemMapper;

    @Override
    public void saveDynamicForm(Map<String, Object> dfMap) {
        DfDynamicForm df = handleFormConfig(dfMap);

        df.setCreateTime(new Date());
        df.setEmployeeId(0);

        // 在这里存储动态表单
        dfDynamicFormMapper.saveDynamicForm(df);
        int dfFormId = df.getId();
        System.out.println("动态表单存储后: " + dfFormId);

        List<Map<String, Object>> fieldList = (List<Map<String, Object>>) dfMap.get("list");

        this.saveFormField(fieldList , 0, dfFormId, -1);
    }

    private void saveFormField(List<Map<String, Object>> fieldList, Integer displayIndex, int dfFormId, int parentId) {
        for (Map<String, Object> fieldMap: fieldList) {
            DfFormField dfField = handleGeneralField(fieldMap);

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
                Map<String, Object> optionsMap = (Map<String, Object>) fieldMap.get("options");

                if (fieldType.equals("checkbox")) {
                    List<String> checkboxList = (List<String>) optionsMap.get("defaultValue");

                    for (String str: checkboxList) {
                        defaultValue += str + ",";
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
                dfField.setFormId(dfFormId);

                // 存储表单域
                dfFormFieldMapper.saveFormField(dfField);
               int formFieldId = dfField.getId();
               System.out.println(dfField.getLabel() + "表单域存储后: " + formFieldId);

                if (fieldType.equals("checkbox") || fieldType.equals("radio") || fieldType.equals("select")) {
                    List<DfFormItem> itemList = handleFieldItem(optionsMap);

                    // 存储表单域条目
                    for (DfFormItem item: itemList) {
                        item.setFormFieldId(formFieldId);
                        dfFormItemMapper.insert(item);
                    }
                }
            } else {
                dfField.setFormId(dfFormId);
                // 保存表单域信息
                dfFormFieldMapper.saveFormField(dfField);
                int formFieldId = dfField.getId();

                List<Map<String, Object>> columnList = (List<Map<String, Object>>) fieldMap.get("columns");
                handleColumns(columnList, dfFormId, formFieldId, "save");
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
     * 处理动态表单的配置
     * @param dfMap
     * @return
     */
    private DfDynamicForm handleFormConfig(Map<String, Object> dfMap) {
        Map<String, Object> configMap = null;
        DfDynamicForm df = null;

        if (dfMap != null) {
            configMap = (Map<String, Object>) dfMap.get("config");
        }

        if (configMap != null) {
            String name = (String) configMap.get("name");
            String method = (String) configMap.get("method");

            df = new DfDynamicForm();

            df.setName(name);
            df.setMethod(method);

            if (configMap.get("id") != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Integer formId = (Integer) configMap.get("id");

                df.setId(formId);
                try {
                    df.setCreateTime(sdf.parse((String) configMap.get("createTime")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                df.setEmployeeId((Integer) configMap.get("employeeId"));
                df.setEnctype((String) configMap.get("enctype"));
                df.setAction("action");
            }
        }

        return df;
    }

    /**
     * 处理通用的表单域信息
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
            Integer id = fieldMap.get("id") == null ? null : (Integer) fieldMap.get("id");
            Integer formId = fieldMap.get("formId") == null ? null : (Integer) fieldMap.get("formId");

            dfField = new DfFormField();

            dfField.setType(type);
            dfField.setLabel(label);
            dfField.setKey(key);
            dfField.setModel(model);
            dfField.setId(id);
            dfField.setFormId(formId);
        }

        return dfField;
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
            Integer id = itemConfigMap.get("id") == null ? null : (Integer) itemConfigMap.get("id");
            Integer formFieldId = itemConfigMap.get("formFieldId") == null ? null : (Integer) itemConfigMap.get("formFieldId");

            DfFormItem dfFormItem = new DfFormItem();

            dfFormItem.setLabel(value);
            dfFormItem.setValue(value);
            dfFormItem.setId(id);
            dfFormItem.setFormFieldId(formFieldId);
            dfFormItem.setItemIndex(String.valueOf(i));

            itemList.add(dfFormItem);
        }
        return itemList;
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
                if ("update".equals(sign)) {
                    // 进行更新操作
                    this.updateFormField(list, count++, dfFormId, parentId);
                } else {
                    // 进行新增操作
                    this.saveFormField(list, count++, dfFormId, parentId);
                }

            }
        }
    }

    @Override
    public void updateDynamicForm(Map<String, Object> dfMap) {
        // 首先更新表单信息
        DfDynamicForm df = handleFormConfig(dfMap);
        dfDynamicFormMapper.updateDynamicForm(df);

        List<Integer> fieldIdList = new ArrayList<>();
        fieldIdList = this.getFieldIdList(fieldIdList, (List<Map<String, Object>>) dfMap.get("list"));
        List<Integer> dbFieldIdList = this.getFieldIdListByDB(df.getId());
        // 从数据库中查询出来的Id列表中删除前台中传递过来的Id列表,剩下的就是前台删除的数据Id
        dbFieldIdList.removeAll(fieldIdList);
        // 删除已经不存在的表单域及其条目
        this.deleteFormFieldListAndItem(dbFieldIdList);

        // 然后更新表单信息
        List<Map<String, Object>> formFieldList = (List<Map<String, Object>>) dfMap.get("list");
        this.updateFormField(formFieldList, 0, df.getId(), null);
    }

    private void updateFormField(List<Map<String, Object>> formFieldList,Integer displayIndex, Integer formId, Integer parentId) {
        for (Map<String, Object> fieldMap: formFieldList) {
            // 处理每个Map
            DfFormField dfField = handleGeneralField(fieldMap);

            if (dfField == null) {
                break;
            }

            dfField.setDisplayIndex(displayIndex++);

            if (parentId != null) {
                dfField.setParentId(parentId);
            }

            if (formId != null) {
                dfField.setFormId(formId);
            }

            String fieldType = dfField.getType();
            String defaultValue = "";

            dfField.setValue(defaultValue);

            if (!fieldType.equals("grid")) {
                Map<String, Object> optionsMap = (Map<String, Object>) fieldMap.get("options");

                if (fieldType.equals("checkbox")) {
                    List<String> checkboxList = (List<String>) optionsMap.get("defaultValue");

                    for (String str: checkboxList) {
                        defaultValue += str + ",";
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

                if (dfField.getId() == null) {
                    // 如果是新增的表单域则进行保存操作
                    dfFormFieldMapper.saveFormField(dfField);
                } else {
                    dfFormFieldMapper.updateFormField(dfField);
                }

                int formFieldId = dfField.getId();

                if (fieldType.equals("checkbox") || fieldType.equals("radio") || fieldType.equals("select")) {
                    List<DfFormItem> itemList = handleFieldItem(optionsMap);

                    // 存储表单域条目
                    for (DfFormItem item: itemList) {
                        // 如果Id不为空则进行更新操作
                        if (item.getId() != null) {
                            dfFormItemMapper.updateFormItem(item);
                        } else {
                            // 否则则进行新增操作
                            item.setFormFieldId(formFieldId);
                            dfFormItemMapper.insert(item);
                        }


                    }
                }
            } else {
                if (dfField.getId() == null) {
                    // 如果是新增的表单域则进行保存操作
                    dfFormFieldMapper.saveFormField(dfField);
                } else {
                    dfFormFieldMapper.updateFormField(dfField);
                }

                int formFieldId = dfField.getId();

                List<Map<String, Object>> columnList = (List<Map<String, Object>>) fieldMap.get("columns");
                handleColumns(columnList, formId, formFieldId, "update");
            }
        }
    }

    private void deleteFormFieldListAndItem(List<Integer> formFieldIdList) {
        for (Integer formFieldId: formFieldIdList) {
            // 首先删除表单域信息
            this.dfFormFieldMapper.deleteFormFieldById(formFieldId);
            // 然后再删除表单条目信息
            this.dfFormItemMapper.deleteFormItemByFieldId(formFieldId);
        }
    }

    /**
     * 获取从前台传递过来的表单域的Id
     * @param fieldList
     * @return
     */
    private List<Integer> getFieldIdList(List<Integer> fieldIdList, List<Map<String, Object>> fieldList) {
        for (Map<String, Object> fieldMap: fieldList) {
            Integer id = fieldMap.get("id") == null ? null : (Integer) fieldMap.get("id");

            if (id != null) {
                fieldIdList.add(id);
            }

            if ("grid".equals(fieldMap.get("type"))) {
                List<Map<String, Object>> columns = (List<Map<String, Object>>) fieldMap.get("columns");
                for (Map<String, Object> column : columns) {
                    this.getFieldIdList(fieldIdList, (List<Map<String, Object>>) column.get("list"));
                }
            }
        }

        return fieldIdList;
    }

    /**
     * 获取数据库中某个表单中表单域的Id列表
     * @param formId
     * @return
     */
    private List<Integer> getFieldIdListByDB(Integer formId) {
        List<DfFormField> dfFormFieldList = this.dfFormFieldMapper.findDynamicFormFieldsByFormId(formId);
        List<Integer> dbFieldIdList = new ArrayList<>();

        for (DfFormField formField: dfFormFieldList) {
            dbFieldIdList.add(formField.getId());
        }

        return dbFieldIdList;
    }


    @Override
    public List<DfDynamicForm> findDynamicFormByLimit(Integer pageSize, Integer pageNum) {
        Integer offset = (pageNum - 1) * pageSize;
        List<DfDynamicForm> dfDynamicFormList = this.dfDynamicFormMapper.findDynamicFormByLimit(pageSize, offset);
        return dfDynamicFormList;
    }

    @Override
    public int findDynamicFormCount() {
        return this.dfDynamicFormMapper.findDynamicFormCount();
    }
}
