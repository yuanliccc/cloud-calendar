package group.cc.occ.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "org_apply")
public class OrgApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "orgKey")
    private String orgkey;

    private String state;

    @Column(name = "submitTime")
    private Date submittime;

    @Column(name = "approvalUserId")
    private Integer approvaluserid;

    @Column(name = "approvalTime")
    private Date approvaltime;

    @Column(name = "submitUserId")
    private Integer submituserid;

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
     * @return approvalUserId
     */
    public Integer getApprovaluserid() {
        return approvaluserid;
    }

    /**
     * @param approvaluserid
     */
    public void setApprovaluserid(Integer approvaluserid) {
        this.approvaluserid = approvaluserid;
    }

    /**
     * @return approvalTime
     */
    public Date getApprovaltime() {
        return approvaltime;
    }

    /**
     * @param approvaltime
     */
    public void setApprovaltime(Date approvaltime) {
        this.approvaltime = approvaltime;
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
}