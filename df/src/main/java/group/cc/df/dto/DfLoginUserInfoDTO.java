package group.cc.df.dto;

/**
 * @author gxd
 * @date 2019/02/16
 */
public class DfLoginUserInfoDTO {
    /**
     * 用户用于登录的字符串(用户名)
     */
    private String userName;

    /**
     * 用户用以登录的密码
     */
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DfLoginUserInfoDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
