package group.cc.pcc.model;

import javax.persistence.*;

@Table(name = "pcc_remind_service")
public class PccRemindService {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提醒服务名称
     */
    private String name;

    /**
     * 服务是否可用
     */
    @Column(name = "is_usable")
    private Boolean isUsable;

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
     * 获取提醒服务名称
     *
     * @return name - 提醒服务名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置提醒服务名称
     *
     * @param name 提醒服务名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取服务是否可用
     *
     * @return is_usable - 服务是否可用
     */
    public Boolean getIsUsable() {
        return isUsable;
    }

    /**
     * 设置服务是否可用
     *
     * @param isUsable 服务是否可用
     */
    public void setIsUsable(Boolean isUsable) {
        this.isUsable = isUsable;
    }
}