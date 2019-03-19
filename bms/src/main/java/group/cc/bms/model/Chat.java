package group.cc.bms.model;

import java.util.Date;
import javax.persistence.*;

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
}