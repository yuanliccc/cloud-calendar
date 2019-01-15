package group.cc.pcc.model;

import javax.persistence.*;

@Table(name = "additional_info_type")
public class AdditionalInfoType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String table;

    private String filters;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return table
     */
    public String getTable() {
        return table;
    }

    /**
     * @param table
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * @return filters
     */
    public String getFilters() {
        return filters;
    }

    /**
     * @param filters
     */
    public void setFilters(String filters) {
        this.filters = filters;
    }
}