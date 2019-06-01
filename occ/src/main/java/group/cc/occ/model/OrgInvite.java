package group.cc.occ.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "org_invite")
public class OrgInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @Column(name = "approvalOrgId")
    private Integer approvalorgid;

    @Column(name = "submitUserId")
    private Integer submituserid;

    @Column(name = "checkTime")
    private Date checktime;

    @Column(name = "submitTime")
    private Date submittime;

    private String state;

    @Column(name = "userId")
    private Integer userid;

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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return approvalOrgId
     */
    public Integer getApprovalorgid() {
        return approvalorgid;
    }

    /**
     * @param approvalorgid
     */
    public void setApprovalorgid(Integer approvalorgid) {
        this.approvalorgid = approvalorgid;
    }

    /**
     * @return submitUserId
     */
    public Integer getSubmituserid() {
        return submituserid;
    }

    /**
     * @param submituserid
     */
    public void setSubmituserid(Integer submituserid) {
        this.submituserid = submituserid;
    }

    /**
     * @return checkTime
     */
    public Date getChecktime() {
        return checktime;
    }

    /**
     * @param checktime
     */
    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    /**
     * @return submitTime
     */
    public Date getSubmittime() {
        return submittime;
    }

    /**
     * @param submittime
     */
    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
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
}