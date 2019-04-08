package group.cc.occ.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class Organization implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "orgKey")
    private String orgkey;

    @Column(name = "parentOrgId")
    private Integer parentorgid;

    @Column(name = "rootOrgId")
    private Integer rootorgid;

    public Integer getRootorgid() {
        return rootorgid;
    }

    public void setRootorgid(Integer rootorgid) {
        this.rootorgid = rootorgid;
    }

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
     * @return orgKey
     */
    public String getOrgkey() {
        return orgkey;
    }

    /**
     * @param orgkey
     */
    public void setOrgkey(String orgkey) {
        this.orgkey = orgkey;
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