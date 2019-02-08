package group.cc.occ.model;

import javax.persistence.*;

public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String key;

    @Column(name = "parentOrgId")
    private Integer parentorgid;

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
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return parentOrgId
     */
    public Integer getParentorgid() {
        return parentorgid;
    }

    /**
     * @param parentorgid
     */
    public void setParentorgid(Integer parentorgid) {
        this.parentorgid = parentorgid;
    }
}