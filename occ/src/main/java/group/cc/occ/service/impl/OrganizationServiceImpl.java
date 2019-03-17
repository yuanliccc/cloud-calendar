package group.cc.occ.service.impl;

import group.cc.occ.dao.OrganizationMapper;
import group.cc.occ.model.Organization;
import group.cc.occ.model.Role;
import group.cc.occ.model.User;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.OrganizationService;
import group.cc.core.AbstractService;
import group.cc.occ.service.RoleService;
import group.cc.occ.service.UserService;
import group.cc.occ.util.SQLUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
@Service
@Transactional
public class OrganizationServiceImpl extends AbstractService<Organization> implements OrganizationService {
    @Resource
    private OrganizationMapper organizationMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private UserService userService;

    @Override
    public List<Organization> listByKey(String key, String value, LoginUserDto loginUserDto) {
        value = "%" + value + "%";
        List<Organization> list = organizationMapper.listByKey(key, value, loginUserDto.getOrganization().getId());
        return list;
    }

    @Override
    public List<Organization> findAllByLoginOrg(LoginUserDto loginUserDto) {
        List<Organization> list = organizationMapper.listByKey("NAME", "%%", loginUserDto.getOrganization().getId());
        return list;
    }

    @Override
    public void addOrg(Organization organization) {
        Organization rootOrg = null;
        //判断是否为根模块或者父模块
        if(organization.getParentorgid() != null && organization.getRootorgid() == null){
            rootOrg = this.findById(organization.getParentorgid());

            //若为根模块
            if(rootOrg.getRootorgid() == null && rootOrg.getParentorgid() == null)
                organization.setRootorgid(rootOrg.getId());
            else if(rootOrg.getRootorgid() != null) //若为父模块
                organization.setRootorgid(rootOrg.getRootorgid());
        }
        this.save(organization);
        organization = this.findBy("orgkey", organization.getOrgkey());

        //创建管理员和成员角色
        Role manager = new Role(organization.getName() + "管理员", organization.getOrgkey() + "Manager", organization.getId(), 5);
        Role member = new Role(organization.getName() + "成员", organization.getOrgkey() + "Member", organization.getId(), 1);
        roleService.save(manager);
        roleService.save(member);


        //创建管理员用户
        User user = new User();
        user.setName(organization.getName() + "管理员");
        user.setAccount(organization.getOrgkey() + SQLUtil.generateNumber());
        user.setPassword("123456");
        boolean success = false;

        while(!success){
            try {
                userService.register(user);
                success = true;
                user = userService.findBy("account", user.getAccount());
            } catch (Exception e) {
                e.printStackTrace();
                user.setAccount(organization.getOrgkey() + SQLUtil.generateNumber());
            }
        }

        //设置管理员角色
        userService.saveRole(user.getId(), manager.getId());
    }

    @Override
    public void deleteOrg(Integer orgId) {
        Organization org = this.findById(orgId);
        List<Role> roles = roleService.findAllByLoginOrg(orgId);

        //先删除角色
        roleService.deleteBatch(roles);
        this.deleteById(orgId);
    }

    @Override
    public void updateOrg(Organization organization) {
        Organization rootOrg = null;
        //判断是否为根模块或者父模块
        if(organization.getParentorgid() != null && organization.getRootorgid() == null){
            rootOrg = this.findById(organization.getParentorgid());

            //若为根模块
            if(rootOrg.getRootorgid() == null && rootOrg.getParentorgid() == null)
                organization.setRootorgid(rootOrg.getId());
            else if(rootOrg.getRootorgid() != null) //若为父模块
                organization.setRootorgid(rootOrg.getRootorgid());

            //设置为根模块
        }else if(organization.getParentorgid() == null && organization.getRootorgid() != null){
            organization.setRootorgid(null);
        }

        organizationMapper.updateOrg(organization);
    }

    @Override
    public List<Organization> getAllLoginUserOrg(LoginUserDto loginUserDto) {
        List<Organization> list = organizationMapper.getAllLoginUserOrg(loginUserDto.getUser().getId());
        return list;
    }
}
