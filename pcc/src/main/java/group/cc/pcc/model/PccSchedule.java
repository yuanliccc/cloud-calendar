package group.cc.pcc.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pcc_schedule")
public class PccSchedule {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 提醒时间
     */
    @Column(name = "remind_time")
    private Date remindTime;

    /**
     * 所属用户（主键）
     */
    @Column(name = "pcc_user_id")
    private Integer pccUserId;

    /**
     * 任务内容
     */
    private String content;

    /**
     * 截止时间
     */
    private Date deadline;

    @Column(name = "is_delete")
    private String isDelete;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取提醒时间
     *
     * @return remind_time - 提醒时间
     */
    public Date getRemindTime() {
        return remindTime;
    }

    /**
     * 设置提醒时间
     *
     * @param remindTime 提醒时间
     */
    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }

    /**
     * 获取所属用户（主键）
     *
     * @return pcc_user_id - 所属用户（主键）
     */
    public Integer getPccUserId() {
        return pccUserId;
    }

    /**
     * 设置所属用户（主键）
     *
     * @param pccUserId 所属用户（主键）
     */
    public void setPccUserId(Integer pccUserId) {
        this.pccUserId = pccUserId;
    }

    /**
     * 获取任务内容
     *
     * @return content - 任务内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置任务内容
     *
     * @param content 任务内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取截止时间
     *
     * @return deadline - 截止时间
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * 设置截止时间
     *
     * @param deadline 截止时间
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * @return is_delete
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

}