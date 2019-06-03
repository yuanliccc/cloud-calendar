package group.cc.pcc.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pcc_task")
public class PccTask {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 所属用户
     */
    @Column(name = "pcc_user_id")
    private Integer pccUserId;

    /**
     * 日程内容
     */
    private String content;

    /**
     * 时间
     */
    private Date time;

    @Column(name = "is_remind")
    private String isRemind;


    @Column(name="is_delete")
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
     * 获取所属用户
     *
     * @return pcc_user_id - 所属用户
     */
    public Integer getPccUserId() {
        return pccUserId;
    }

    public String getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(String isRemind) {
        this.isRemind = isRemind;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 设置所属用户
     *
     * @param pccUserId 所属用户
     */
    public void setPccUserId(Integer pccUserId) {
        this.pccUserId = pccUserId;
    }

    /**
     * 获取日程内容
     *
     * @return content - 日程内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置日程内容
     *
     * @param content 日程内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取时间
     *
     * @return time - 时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置时间
     *
     * @param time 时间
     */
    public void setTime(Date time) {
        this.time = time;
    }
}