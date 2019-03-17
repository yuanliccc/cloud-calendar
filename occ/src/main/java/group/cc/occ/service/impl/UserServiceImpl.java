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


    /**
     * 登录
     * */
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

    /***
     * 注册
     * */
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

    public void saveRole(Integer userId, Integer roleId){
        userMapper.saveRole(userId, roleId);
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
    /**
     * 默认登录
     * */
    private void defaultLogin(Role role, Organization org, Lastloginorg last, LoginUserDto userDto){
        role = roleService.findBy("rolekey","defaultMember");
        org = organizationService.findById(role.getOrgid());
        boolean save = false;

        if(last == null) {
            last = new Lastloginorg();
            save = true;
        }

        last.setLogintime(new Date());
        last.setUserid(userDto.getUser().getId());
        last.setOrgid(org.getId());
        if(save)
            lastloginorgService.save(last);
        else
            lastloginorgService.update(last);
        userDto.setOrganization(org);
        userDto.setRole(role);
    }

    @Override
    public List<User> listByKey(String key, String value, LoginUserDto login) {
        value = "%" + value + "%";
        List<User> list = this.userMapper.listByKey(key, value, login.getOrganization().getId());
        return list;
    }


    /***
     * 通过账号或者姓名查找用户
     * */
    @Override
    public List<User> findUserByIdOrName(String value) {
        value = "%" + value + "%";
        List<User> list =  this.userMapper.findUserByIdOrName(value);
        return list;
    }

    /***
     * 获取当前机构所有用户
     * */
    @Override
    public List<User> findAllByLoginOrg(LoginUserDto login) {
        return userMapper.listByKey("NAME", "%%", login.getOrganization().getId());
    }

    /***
     * 切换部门
     * */
    @Override
    public LoginUserDto switchOrg(Integer orgId, LoginUserDto login) throws Exception{
        Organization o = this.organizationService.findById(orgId);


        if(o == null){
            throw new Exception("没有找到该部门，请刷新重试！");
        }else {
            login.setOrganization(o);
            Role r = roleService.findByUserIdAndOrgId(login.getUser().getId(), o.getId());

            if(r == null)
                throw new Exception("没有找到该部门的该角色，请刷新重试！");

            login.setRole(r);
            List<String> p = permissionService.findPerForRoleId(r.getId());
            login.setPermissions(p);

            Lastloginorg last = lastloginorgService.findBy("userid", login.getUser().getId());
            last.setOrgid(o.getId());
            last.setLogintime(new Date());
            lastloginorgService.update(last);
        }
        return login;
    }
}
