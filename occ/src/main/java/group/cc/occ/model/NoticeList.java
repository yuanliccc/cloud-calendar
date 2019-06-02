package group.cc.occ.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "notice_list")
public class NoticeList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private String title;

    private String type;

    @Column(name = "orgId")
    private Integer orgid;

    @Column(name = "submitUserId")
    private Integer submituserid;

    @Column(name = "submitTime")
    private Date submittime;

    @Column(name = "subordinateCanSeen")
    private String subordinatecanseen;

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
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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
}