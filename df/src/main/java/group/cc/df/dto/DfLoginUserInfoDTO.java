package group.cc.df.dto;

/**
 * @author gxd
 * @date 2019/02/16
 */
public class DfLoginUserInfoDTO {
    /**
     * 用户用于登录的字符串
     * 也可以邮箱和手机号
     */
    private String identityCode;

    /**
     * 用户用以登录的密码
     */
    private String verifyPassword;

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
