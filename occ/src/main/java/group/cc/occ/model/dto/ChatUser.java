package group.cc.occ.model.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import group.cc.occ.model.User;

import java.io.Serializable;

/**
 * Created by wangyuming on 2019/3/20.
 */
public class ChatUser implements Serializable, Comparable<ChatUser> {
    private Integer id;

    private String name;

    private String account;

    private String password;

    private String phone;

    private String email;

    private String sex;

    private Boolean display;

    private Integer unreadnum;

    public ChatUser(){}

    public ChatUser(User user){
        this.setId(user.getId());
        this.setAccount(user.getAccount());
        this.setEmail(user.getEmail());
        this.setName(user.getName());
        this.setPhone(user.getPhone());
        this.setSex(user.getSex());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public Integer getUnreadnum() {
        return unreadnum;
    }

    public void setUnreadnum(Integer unreadnum) {
        this.unreadnum = unreadnum;
    }

    @Override
    public int compareTo(ChatUser o) {
        if(this.unreadnum == null && o.getUnreadnum() == null)
            return 0;
        else if(this.unreadnum == null){
            return -1;
        }else if(o.getUnreadnum() == null){
            return 1;
        }

        return this.unreadnum - o.getUnreadnum() >= 0 ? (this.unreadnum - o.getUnreadnum() == 0 ? 0 : 1) : -1;
    }
}
