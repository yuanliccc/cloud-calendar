package group.cc.occ.model;

import javax.persistence.*;
import java.io.Serializable;

public class Role implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "roleKey")
    private String rolekey;

    @Column(name = "orgId")
    private Integer orgid;

    @Column(name = "roleLevel")
    private Integer rolelevel;

    public Role(){}

    public Role(String name, String rolekey, Integer orgid, Integer rolelevel){
        this.setName(name);
        this.setOrgid(orgid);
        this.setRolekey(rolekey);
        this.setRolelevel(rolelevel);
    }

    public Integer getRolelevel() {
        return rolelevel;
    }

    public void setRolelevel(Integer rolelevel) {
        this.rolelevel = rolelevel;
    }

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