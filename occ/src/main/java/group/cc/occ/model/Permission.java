package group.cc.occ.model;

import javax.persistence.*;

public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String identify;

    private String explain;

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
     * @return identify
     */
    public String getIdentify() {
        return identify;
    }

    /**
     * @param identify
     */
    public void setIdentify(String identify) {
        this.identify = identify;
    }

    /**
     * @return explain
     */
    public String getExplain() {
        return explain;
    }

    /**
     * @param explain
     */
    public void setExplain(String explain) {
        this.explain = explain;
    }
}