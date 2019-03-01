package group.cc.occ.model;

import java.util.Date;
import javax.persistence.*;

public class Lastloginorg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId")
    private Integer userid;

    @Column(name = "orgId")
    private Integer orgid;

    @Column(name = "loginTime")
    private Date logintime;

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
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return orgId
     */
    public Integer getOrgid() {
        return orgid;
    }

    /**
     * @param orgid
     */
    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    /**
     * @return loginTime
     */
    public Date getLogintime() {
        return logintime;
    }

    /**
     * @param logintime
     */
    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }
}