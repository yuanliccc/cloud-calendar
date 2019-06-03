package group.cc.pcc.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pcc_notice")
public class PccNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提醒类型
     */
    private String type;

    /**
     * api
     */
    private String api;

    /**
     * 内容，可为json
     */
    private String content;

    /**
     * 是否提醒查看
     */
    @Column(name = "is_remind")
    private String isRemind;

    /**
     * 时间
     */
    private Date time;

    /**
     * 所属用户
     */
    @Column(name = "pcc_user_id")
    private Integer pccUserId;

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
     * 获取提醒类型
     *
     * @return type - 提醒类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置提醒类型
     *
     * @param type 提醒类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取api
     *
     * @return api - api
     */
    public String getApi() {
        return api;
    }

    /**
     * 设置api
     *
     * @param api api
     */
    public void setApi(String api) {
        this.api = api;
    }

    /**
     * 获取内容，可为json
     *
     * @return content - 内容，可为json
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容，可为json
     *
     * @param content 内容，可为json
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取是否提醒查看
     *
     * @return is_remind - 是否提醒查看
     */
    public String getIsRemind() {
        return isRemind;
    }

    /**
     * 设置是否提醒查看
     *
     * @param isRemind 是否提醒查看
     */
    public void setIsRemind(String isRemind) {
        this.isRemind = isRemind;
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

    /**
     * 获取所属用户
     *
     * @return pcc_user_id - 所属用户
     */
    public Integer getPccUserId() {
        return pccUserId;
    }

    /**
     * 设置所属用户
     *
     * @param pccUserId 所属用户
     */
    public void setPccUserId(Integer pccUserId) {
        this.pccUserId = pccUserId;
    }
}