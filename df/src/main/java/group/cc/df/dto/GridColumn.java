package group.cc.df.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by 高旭东 on 2019/1/11.
 */
public class GridColumn {
    private int span;

    private List<Map<String, Object>> list;

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
}
