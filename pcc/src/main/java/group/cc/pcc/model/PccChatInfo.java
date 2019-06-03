package group.cc.pcc.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pcc_chat_info")
public class PccChatInfo {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 发送人id
     */
    @Column(name = "send_user_id")
    private Integer sendUserId;

    /**
     * 接收人id
     */
    @Column(name = "receive_user_id")
    private Integer receiveUserId;

    /**
     * 信息内容
     */
    private String content;

    /**
     * 发送时间
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * 是否接受
     */
    @Column(name = "is_received")
    private String isReceived;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取发送人id
     *
     * @return send_user_id - 发送人id
     */
    public Integer getSendUserId() {
        return sendUserId;
    }

    /**
     * 设置发送人id
     *
     * @param sendUserId 发送人id
     */
    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    /**
     * 获取接收人id
     *
     * @return receive_user_id - 接收人id
     */
    public Integer getReceiveUserId() {
        return receiveUserId;
    }

    /**
     * 设置接收人id
     *
     * @param receiveUserId 接收人id
     */
    public void setReceiveUserId(Integer receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    /**
     * 获取信息内容
     *
     * @return content - 信息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置信息内容
     *
     * @param content 信息内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取发送时间
     *
     * @return send_time - 发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置发送时间
     *
     * @param sendTime 发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取是否接受
     *
     * @return is_received - 是否接受
     */
    public String getIsReceived() {
        return isReceived;
    }

    /**
     * 设置是否接受
     *
     * @param isReceived 是否接受
     */
    public void setIsReceived(String isReceived) {
        this.isReceived = isReceived;
    }
}