package group.cc.occ.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "work_arrange")
public class WorkArrange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "orgId")
    private Integer orgid;

    @Column(name = "submitUserId")
    private Integer submituserid;

    @Column(name = "submitTime")
    private String submittime;

    @Column(name = "isRepeat")
    private String isrepeat;

    @Column(name = "startTime")
    private Date starttime;

    @Column(name = "endTime")
    private Date endtime;

    private String title;

    private String content;

    private String state;

    private List<UserWorkArrange> userWorkArrangeList;

    public List<UserWorkArrange> getUserWorkArrangeList() {
        return userWorkArrangeList;
    }

    public void setUserWorkArrangeList(List<UserWorkArrange> userWorkArrangeList) {
        this.userWorkArrangeList = userWorkArrangeList;
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
    public String getSubmittime() {
        return submittime;
    }

    /**
     * @param submittime
     */
    public void setSubmittime(String submittime) {
        this.submittime = submittime;
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
     * @return startTime
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * @param starttime
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * @return endTime
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * @param endtime
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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
}