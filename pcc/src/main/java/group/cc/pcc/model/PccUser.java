package group.cc.pcc.model;

import javax.persistence.*;

@Table(name = "pcc_user")
public class PccUser {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名字，昵称
     */
    private String name;

    /**
     * 性别，男或女
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phone;

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
     * 获取名字，昵称
     *
     * @return name - 名字，昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字，昵称
     *
     * @param name 名字，昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别，男或女
     *
     * @return sex - 性别，男或女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别，男或女
     *
     * @param sex 性别，男或女
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取电话号码
     *
     * @return phone - 电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话号码
     *
     * @param phone 电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}