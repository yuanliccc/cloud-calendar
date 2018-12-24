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
     * 提醒服务（主键）
     */
    @Column(name = "pcc_remind_service_id")
    private Integer pccRemindServiceId;

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
     * 获取提醒服务（主键）
     *
     * @return pcc_remind_service_id - 提醒服务（主键）
     */
    public Integer getPccRemindServiceId() {
        return pccRemindServiceId;
    }

    /**
     * 设置提醒服务（主键）
     *
     * @param pccRemindServiceId 提醒服务（主键）
     */
    public void setPccRemindServiceId(Integer pccRemindServiceId) {
        this.pccRemindServiceId = pccRemindServiceId;
    }
}