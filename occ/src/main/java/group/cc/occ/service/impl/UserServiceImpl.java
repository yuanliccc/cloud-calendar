package group.cc.occ.service.impl;

import group.cc.occ.dao.UserMapper;
import group.cc.occ.model.*;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.*;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private LastloginorgService lastloginorgService;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Override
    public LoginUserDto login(String account, String password) throws Exception{
        User user = this.findBy("account", account);
        if(user == null)
            throw new Exception("账号不存在！");
        else {
            if(!password.equals(user.getPassword()))
                throw new Exception("密码错误！");
        }
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUser(user);

        updateLastLoginInfo(loginUserDto);
        List<String> permissions = permissionService.findPerForRoleId(loginUserDto.getRole().getId());
        loginUserDto.setPermissions(permissions);

        return loginUserDto;
    }

    @Override
    public LoginUserDto register(User user) throws Exception{
        if(user.getAccount() == null || user.getPassword() == null)
            throw new Exception("账号密码不能为空！");
        List list = userMapper.getUserByAccount(user.getAccount());

        if(list != null && list.size() != 0)
            throw new Exception("账号已存在！");

        this.save(user);
        User t = this.findBy("account", user.getAccount());

        initUser(t.getId());
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUser(t);

        updateLastLoginInfo(loginUserDto);

        List<String> permissions = permissionService.findPerForRoleId(loginUserDto.getRole().getId());
        loginUserDto.setPermissions(permissions);

        return loginUserDto;
    }

    private void initUser(Integer userId){
        Role role = roleService.findBy("rolekey","defaultMember");
        userMapper.saveRole(userId, role.getId());
    }

    private void updateLastLoginInfo(LoginUserDto userDto){
        Lastloginorg last = lastloginorgService.findBy("userid", userDto.getUser().getId());
        Role role = null;
        Organization org = null;

        if(last == null){
            defaultLogin(role, org, last, userDto);
            return;
        }else {
            role = roleService.findByUserIdAndOrgId(last.getUserid(), last.getOrgid());

            //如果该角色因为特殊原因消失（删除或者换部门）
            if(role == null){
                defaultLogin(role, org, last, userDto);
                return;
            }else {
                org = organizationService.findById(last.getOrgid());
                last.setLogintime(new Date());
                lastloginorgService.update(last);
            }
        }

        userDto.setOrganization(org);
        userDto.setRole(role);


    }

    private void defaultLogin(Role role, Organization org, Lastloginorg last, LoginUserDto userDto){
        role = roleService.findBy("rolekey","defaultMember");
        org = organizationService.findById(role.getOrgid());

        last = new Lastloginorg();
        last.setLogintime(new Date());
        last.setUserid(userDto.getUser().getId());
        last.setOrgid(org.getId());
        lastloginorgService.save(last);
        userDto.setOrganization(org);
        userDto.setRole(role);
    }
}
