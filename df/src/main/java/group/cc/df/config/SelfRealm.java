package group.cc.df.config;

import group.cc.df.dao.DfUserMapper;
import group.cc.df.model.DfUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 高旭东 on 2019/3/25.
 */
public class SelfRealm extends AuthorizingRealm {

    @Autowired
    private DfUserMapper dfUserMapper;

    // 认证用户
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken  userpasswordToken = (UsernamePasswordToken) authenticationToken;//这边是界面的登陆数据，将数据封装成token
        // 获取当前用户的用户名
        String userName = userpasswordToken.getUsername();

        // 从数据库中根据用户名查询用户
        DfUser user = null;
        List<DfUser> userList = dfUserMapper.getUserByUserName(userName);
        if (userList != null && !userList.isEmpty()) {
            user = userList.get(0);
        }

        if (user == null) {
            throw new UnknownAccountException("没有在本系统中找到对应的用户信息");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
        return info;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 从凭证中获取用户
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        // 根据用户查询用户对象
        /*DfUser user = dfUserMapper.getUserByUserName();*/
        // todo 查询用户拥有的角色

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // todo 将角色添加到info中


        return info;
    }
}
