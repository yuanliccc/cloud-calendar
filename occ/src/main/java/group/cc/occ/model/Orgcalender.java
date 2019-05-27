package group.cc.occ.model;

import java.util.Date;
import javax.persistence.*;

public class Orgcalender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    @Column(name = "specificTime")
    private Date specifictime;

    @Column(name = "isRepeat")
    private String isrepeat;

    @Column(name = "submitUserId")
    private Integer submituserid;

    @Column(name = "submitTime")
    private Date submittime;

    @Column(name = "subordinateCanSeen")
    private String subordinatecanseen;

    @Column(name = "orgId")
    private Integer orgid;

    @Column(name = "type")
    private String type;

    @Column(name = "startTime")
    private Date starttime;

    @Column(name = "endTime")
    private Date endtime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return specificTime
     */
    public Date getSpecifictime() {
        return specifictime;
    }

    /**
     * @param specifictime
     */
    public void setSpecifictime(Date specifictime) {
        this.specifictime = specifictime;
    }

    /**
     * @return isRepeat
     */
    public String getIsrepeat() {
        return isrepeat;
    }

    /**
     * @param isrepeat
     */
    public void setIsrepeat(String isrepeat) {
        this.isrepeat = isrepeat;
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
     * @return subordinateCanSeen
     */
    public String getSubordinatecanseen() {
        return subordinatecanseen;
    }

    /**
     * @param subordinatecanseen
     */
    public void setSubordinatecanseen(String subordinatecanseen) {
        this.subordinatecanseen = subordinatecanseen;
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
}