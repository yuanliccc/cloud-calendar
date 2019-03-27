package group.cc.df.utils;

import group.cc.df.model.DfUser;
import group.cc.df.service.DfUserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 密码校验器
 * @author gaoxudogn
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {

    @Autowired
    private DfUserService dfUserService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());

        List<DfUser> userList = this.dfUserService.getUserByUserName(userName);
        if (userList == null || userList.isEmpty()) {
            return false;
        }

        DfUser user = userList.get(0);
        String realPassword = user.getPassword();

        return this.equals(password, realPassword);
    }
}
