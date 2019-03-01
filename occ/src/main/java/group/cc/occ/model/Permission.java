package group.cc.occ.model;

import javax.persistence.*;

public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String identify;

    @Column(name = "perExplain")
    private String perexplain;

    @Column(name = "moduleId")
    private Integer moduleid;

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
     * @return perExplain
     */
    public String getPerexplain() {
        return perexplain;
    }

    /**
     * @param perexplain
     */
    public void setPerexplain(String perexplain) {
        this.perexplain = perexplain;
    }

    /**
     * @return moduleId
     */
    public Integer getModuleid() {
        return moduleid;
    }

    /**
     * @param moduleid
     */
    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }
}