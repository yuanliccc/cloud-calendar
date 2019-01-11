package group.cc.df.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by 高旭东 on 2019/1/11.
 */
public class ComponentList {
    private String type;

    private String name;

    private List<Map<String, Object>> options;

    private List<GridColumn> columns;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, Object>> getOptions() {
        return options;
    }

    public void setOptions(List<Map<String, Object>> options) {
        this.options = options;
    }

    public List<GridColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<GridColumn> columns) {
        this.columns = columns;
    }
}
