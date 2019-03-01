package group.cc.occ.model;

import javax.persistence.*;

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "roleKey")
    private String rolekey;

    @Column(name = "orgId")
    private Integer orgid;

    /**
     * @return orgid
     */
    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
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
     * @return roleKey
     */
    public String getRolekey() {
        return rolekey;
    }

    /**
     * @param rolekey
     */
    public void setRolekey(String rolekey) {
        this.rolekey = rolekey;
    }
}