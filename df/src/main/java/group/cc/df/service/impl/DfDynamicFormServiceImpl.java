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

        saveFormField(fieldList , null, dfFormId, -1);
    }

    private void saveFormField(List<Map<String, Object>> fieldList, Integer displayIndex, int dfFormId, int parentId) {
        for (Map<String, Object> fieldMap: fieldList) {
            DfFormField dfField = handleGeneralField(fieldMap);

            if (dfField == null) {
                break;
            }

            if (displayIndex == null) {
                dfField.setDisplayIndex(0);
            } else {
                dfField.setDisplayIndex(displayIndex);
            }

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
                handleColumns(columnList, dfFormId, formFieldId);
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

        dateUTC = dateUTC.replace("Z", " UTC");

        try {
            Date date = format.parse(dateUTC);
            dateStr = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
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

            dfField = new DfFormField();

            dfField.setType(type);
            dfField.setLabel(label);
            dfField.setKey(key);
            dfField.setModel(model);
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

            DfFormItem dfFormItem = new DfFormItem();

            dfFormItem.setLabel(value);
            dfFormItem.setValue(value);
            dfFormItem.setItemIndex(String.valueOf(i));

            itemList.add(dfFormItem);
        }
        return itemList;
    }

    /**
     * 处理栅栏布局中的子列表
     * @param columnList
     */
    private void handleColumns(List<Map<String, Object>> columnList, int dfFormId, int parentId) {
        if (columnList.size() > 0) {
            int count = 0;

            for (Map<String, Object> columnMap: columnList) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) columnMap.get("list");
                saveFormField(list, count++, dfFormId, parentId);
            }
        }
    }

    @Override
    public void updateDynamicForm(Map<String, Object> dfMap) {
        // 首先更新表单信息
        DfDynamicForm df = handleFormConfig(dfMap);
        dfDynamicFormMapper.updateDynamicForm(df);

        // 获取表单域列表
        List<Map<String, Object>> fieldList = (List<Map<String, Object>>) dfMap.get("list");

        // 列表,用于存储表单域Id
        List<Integer> fieldIdList = new ArrayList<>();
        // 更新表单域信息
        this.updateFormField(fieldList, fieldIdList);

        // 删除已经弃用的表单域信息
        List<DfFormField> uselessFormFieldList = this.dfFormFieldMapper.findUselessFormFields(fieldIdList, df.getId());
        for (DfFormField dfFormField: uselessFormFieldList) {
            this.dfFormFieldMapper.delete(dfFormField);
        }
    }

    private void updateFormField(List<Map<String, Object>> fieldList, List<Integer> fieldIdList) {
        for (Map<String, Object> fieldMap: fieldList) {
            DfFormField formField = new DfFormField();
            formField.setId((Integer) fieldMap.get("id"));
            formField.setFormId((Integer) fieldMap.get("formId"));
            formField.setLabel((String) fieldMap.get("label"));
            formField.setName(null);
            formField.setType((String) fieldMap.get("type"));
            String defaultValue = "";
            if ("checkbox".equals(formField.getType())) {
                List<String> checkboxList = (List<String>)((Map<String, Object>) fieldMap.get("options")).get("defaultValue");

                for (String str: checkboxList) {
                    defaultValue += str + ",";
                }

                if (!defaultValue.equals("")) {
                    defaultValue = defaultValue.substring(0, defaultValue.length() - 1);
                }
            } else {
                defaultValue = (String) ((Map<String, Object>) fieldMap.get("options")).get("defaultValue");
            }
            formField.setValue(defaultValue);
            formField.setParentId(fieldMap.get("parentId") == null ?
                    null : (Integer) fieldMap.get("parentId"));
            formField.setDisplayIndex((Integer) fieldMap.get("displayIndex"));
            formField.setKey((String) fieldMap.get("key"));
            formField.setModel((String) fieldMap.get("model"));

            fieldIdList.add(formField.getId());

            // 更新表单域信息
            this.dfFormFieldMapper.updateFormField(formField);

            // 处理表单条目信息
            if ("checkbox".equals(formField.getType()) || "radio".equals(formField.getType()) ||
                    "select".equals(formField.getType())) {
                List<Map<String, Object>> formItemList = (List<Map<String, Object>>) ((Map<String, Object>)
                        fieldMap.get("options")).get("options");

                List<Integer> formItemIdList = new ArrayList<>();

                this.updateFormItem(formItemList, formItemIdList);

                // 删除已经弃用的表单条目信息
                List<DfFormItem> uselessFormItemList = this.dfFormItemMapper.findUselessFormItems(formItemIdList, formField.getId());
                for (DfFormItem dfFormItem: uselessFormItemList) {
                    this.dfFormItemMapper.delete(dfFormItem);
                }

            }

            // 处理grid
            List<Map<String, Object>> columnList = (List<Map<String, Object>>) fieldMap.get("columns");
            this.updateColumns(columnList, fieldIdList);

        }
    }

    private void updateColumns(List<Map<String, Object>> columnList, List<Integer> fieldIdList) {
        if (columnList != null && columnList.size() > 0) {
            int count = 0;

            for (Map<String, Object> columnMap: columnList) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) columnMap.get("list");
                this.updateFormField(list, fieldIdList);
            }
        }
    }

    private void updateFormItem(List<Map<String, Object>> formItemList, List<Integer> formItemIdList) {
        for (Map<String, Object> formItemMap: formItemList) {
            DfFormItem formItem = new DfFormItem();
            formItem.setId((Integer) formItemMap.get("id"));
            formItem.setFormFieldId((Integer) formItemMap.get("formFieldId"));
            formItem.setValue((String) formItemMap.get("value"));
            formItem.setLabel((String) formItemMap.get("label"));
            formItem.setItemIndex((String) formItemMap.get("itemIndex"));

            formItemIdList.add(formItem.getId());

            this.dfFormItemMapper.updateFormItem(formItem);
        }
    }
}
