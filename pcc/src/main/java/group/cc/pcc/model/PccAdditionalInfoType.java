package group.cc.pcc.model;

import javax.persistence.*;

@Table(name = "pcc_additional_info_type")
public class PccAdditionalInfoType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "table_name")
    private String tableName;

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
     * @return table_name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
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