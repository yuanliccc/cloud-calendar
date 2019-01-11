package group.cc.df.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import group.cc.df.dao.DfDynamicFormMapper;
import group.cc.df.dto.GridColumn;
import group.cc.df.model.DfDynamicForm;
import group.cc.df.model.DfFormField;
import group.cc.df.model.DfFormItem;
import group.cc.df.service.DfDynamicFormService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author gxd
 * @date 2019/01/10
 */
@Service
@Transactional
public class DfDynamicFormServiceImpl extends AbstractService<DfDynamicForm> implements DfDynamicFormService {
    @Resource
    private DfDynamicFormMapper dfDynamicFormMapper;

    @Override
    public void saveDynamicForm(Map<String, Object> dfMap) {
        Map<String, Object> configMap = (Map<String, Object>) dfMap.get("config");
        System.out.println(configMap.get("labelWidth"));
        System.out.println(configMap.get("labelPosition"));
        System.out.println(configMap.get("name"));
        System.out.println(configMap.get("method"));

        List<Map<String, Object>> fieldList = (List<Map<String, Object>>) dfMap.get("list");

        for (Map<String, Object> map: fieldList) {
            String type = (String) map.get("type");
            String name = (String) map.get("name");
            String defaultValue = null;
            List<DfFormItem> itemList = null;
            List<GridColumn> columnList = null;

            if (!type.equals("grid")) {
                Map<String, Object> optionsMap = (Map<String, Object>) map.get("options");
                defaultValue = (String) optionsMap.get("defaultValue");

                if (type.equals("checkbox") || type.equals("radio") || type.equals("select")) {
                    List<Map<String, Object>> itemConfigList = (List<Map<String, Object>>) optionsMap.get("options");
                    itemList = new ArrayList<>();

                    for (int i = 0; i < itemConfigList.size(); i++) {
                        Map<String, Object> itemConfigMap = itemConfigList.get(i);
                        String value = (String) itemConfigMap.get("value");

                        DfFormItem dfFormItem = new DfFormItem();
                        dfFormItem.setLabel(value);
                        dfFormItem.setValue(value);
                        dfFormItem.setItemIndex(String.valueOf(i));

                        itemList.add(dfFormItem);
                    }
                }
            } else {
                List<Map<String, Object>> columns = (List<Map<String, Object>>) map.get("columns");
                columnList = new ArrayList<>();

                for (Map<String, Object> columnMap: columns) {
                    int span = (int) columnMap.get("span");
                    List<Map<String, Object>> list = (List<Map<String, Object>>) columnMap.get("list");

                    GridColumn gridColumn = new GridColumn();
                    gridColumn.setSpan(span);
                    gridColumn.setList(list);

                    columnList.add(gridColumn);
                }

            }

            System.out.println("type:" + type);
            System.out.println("name: " + name);
            System.out.println("defaultValue: " + defaultValue);
            if (itemList != null) {
                System.out.println("itemList: ");
                for (DfFormItem item: itemList) {
                    System.out.println(item.getValue());
                }
            }
            if (columnList != null) {
                System.out.println("columnList: ");
                for (GridColumn gridColumn: columnList) {
                    List<Map<String, Object>> columnItem = gridColumn.getList();
                    for (Map<String, Object> map1: columnItem) {
                        System.out.println(map1.get("name"));
                    }
                }
            }
            System.out.println("#############################");
        }

    }

    private DfFormField handleGeneralComponents() {
        return null;
    }

    private DfFormField handleItem() {
        return null;
    }
}
