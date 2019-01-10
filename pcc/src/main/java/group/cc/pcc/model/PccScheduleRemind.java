package group.cc.pcc.model;

import javax.persistence.*;

@Table(name = "pcc_schedule_remind")
public class PccScheduleRemind {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务id
     */
    @Column(name = "pcc_schedule_id")
    private Integer pccScheduleId;

    /**
     * 提醒服务id
     */
    @Column(name = "pcc_remind_service_id")
    private Integer pccRemindServiceId;

    /**
     * 是否执行
     */
    @Column(name = "is_execute")
    private String isExecute;

    /**
     * 提醒服务执行结果
     */
    private String result;

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
     * 获取任务id
     *
     * @return pcc_schedule_id - 任务id
     */
    public Integer getPccScheduleId() {
        return pccScheduleId;
    }

    /**
     * 设置任务id
     *
     * @param pccScheduleId 任务id
     */
    public void setPccScheduleId(Integer pccScheduleId) {
        this.pccScheduleId = pccScheduleId;
    }

    /**
     * 获取提醒服务id
     *
     * @return pcc_remind_service_id - 提醒服务id
     */
    public Integer getPccRemindServiceId() {
        return pccRemindServiceId;
    }

    /**
     * 设置提醒服务id
     *
     * @param pccRemindServiceId 提醒服务id
     */
    public void setPccRemindServiceId(Integer pccRemindServiceId) {
        this.pccRemindServiceId = pccRemindServiceId;
    }

    /**
     * 获取是否执行
     *
     * @return is_execute - 是否执行
     */
    public String getIsExecute() {
        return isExecute;
    }

    /**
     * 设置是否执行
     *
     * @param isExecute 是否执行
     */
    public void setIsExecute(String isExecute) {
        this.isExecute = isExecute;
    }

    /**
     * 获取提醒服务执行结果
     *
     * @return result - 提醒服务执行结果
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置提醒服务执行结果
     *
     * @param result 提醒服务执行结果
     */
    public void setResult(String result) {
        this.result = result;
    }
}