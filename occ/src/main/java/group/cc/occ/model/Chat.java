package group.cc.occ.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sendUserId")
    private Integer senduserid;

    @Column(name = "receiveUserId")
    private Integer receiveuserid;

    @Column(name = "sendTime")
    private Date sendtime;

    private String content;

    private String type;

    @Column(name = "hadSeen")
    private String hadseen;

    @Column(name = "orgid")
    private Integer orgid;

    public Integer getOrgid() {
        return orgid;
    }
    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public String getHadseen() {
        return hadseen;
    }

    public void setHadseen(String hadseen) {
        this.hadseen = hadseen;
    }

    /**
     * @return id
     *

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
     * @return sendUserId
     */
    public Integer getSenduserid() {
        return senduserid;
    }

    /**
     * @param senduserid
     */
    public void setSenduserid(Integer senduserid) {
        this.senduserid = senduserid;
    }

    /**
     * @return receiveUserId
     */
    public Integer getReceiveuserid() {
        return receiveuserid;
    }

    /**
     * @param receiveuserid
     */
    public void setReceiveuserid(Integer receiveuserid) {
        this.receiveuserid = receiveuserid;
    }

    /**
     * @return sendTime
     */
    public Date getSendtime() {
        return sendtime;
    }

    /**
     * @param sendtime
     */
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
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

    @Override
    public String toString(){
        return this.id + " | "  +this.sendtime + "";
    }
}