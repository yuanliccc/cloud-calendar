package group.cc.pcc.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pcc_schedule_user")
public class PccScheduleUser {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务主键
     */
    @Column(name = "pcc_schedule_id")
    private Integer pccScheduleId;

    /**
     * 用户主键
     */
    @Column(name = "pcc_user_id")
    private Integer pccUserId;

    /**
     * 是否完成
     */
    @Column(name = "is_complete")
    private String isComplete;

    /**
     * 完成时间
     */
    @Column(name = "complete_date")
    private Date completeDate;


    /**
     * 是否提醒
     */
    @Column(name = "is_remind")
    private String isRemind;

    /**
     * 获取主键
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
     * 获取任务主键
     *
     * @return pcc_schedule_id - 任务主键
     */
    public Integer getPccScheduleId() {
        return pccScheduleId;
    }

    /**
     * 设置任务主键
     *
     * @param pccScheduleId 任务主键
     */
    public void setPccScheduleId(Integer pccScheduleId) {
        this.pccScheduleId = pccScheduleId;
    }

    /**
     * 获取用户主键
     *
     * @return pcc_user_id - 用户主键
     */
    public Integer getPccUserId() {
        return pccUserId;
    }

    /**
     * 设置用户主键
     *
     * @param pccUserId 用户主键
     */
    public void setPccUserId(Integer pccUserId) {
        this.pccUserId = pccUserId;
    }

    /**
     * 获取是否完成
     *
     * @return is_complete - 是否完成
     */
    public String getIsComplete() {
        return isComplete;
    }

    /**
     * 设置是否完成
     *
     * @param isComplete 是否完成
     */
    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * 获取完成时间
     *
     * @return complete_date - 完成时间
     */
    public Date getCompleteDate() {
        return completeDate;
    }

    /**
     * 设置完成时间
     *
     * @param completeDate 完成时间
     */
    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }
}