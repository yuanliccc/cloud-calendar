package group.cc.pcc.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pcc_chat")
public class PccChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
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

    /**
     * @return hadSeen
     */
    public String getHadseen() {
        return hadseen;
    }

    /**
     * @param hadseen
     */
    public void setHadseen(String hadseen) {
        this.hadseen = hadseen;
    }
}