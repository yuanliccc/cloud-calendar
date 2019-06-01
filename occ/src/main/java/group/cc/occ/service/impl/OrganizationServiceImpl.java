package group.cc.occ.service.impl;

import group.cc.occ.dao.OrganizationMapper;
import group.cc.occ.model.*;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.*;
import group.cc.core.AbstractService;
import group.cc.occ.util.SQLUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


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

    @Resource
    private OrgApplyService orgApplyService;

    @Resource
    private SystemConfigService systemConfigService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private NoticeListService noticeListService;

    /*
    * 机构展示（包括该机构下的所有子机构）
    * */
    @Override
    public List<Organization> listByKey(String key, String value, LoginUserDto loginUserDto) {
        value = "%" + value + "%";
        List<Organization> organizations = new ArrayList<>();
        List<Organization> list = this.organizationMapper.findOrgById(key, value,loginUserDto.getOrganization().getId());
        Stack<Organization> stack = new Stack<>();
        stack.addAll(list);
        organizations.addAll(list);

        while(!stack.isEmpty()){
            list = this.organizationMapper.findChildOrg(key, value, stack.pop().getId());

            stack.addAll(list);
            organizations.addAll(list);
        }

        return organizations;
    }

    /**
     * 添加机构
     * */
    @Override
    public void addOrg(Organization organization, LoginUserDto login) {
        Organization rootOrg = null;
        //判断是否为根机构或者父机构
        if(organization.getParentorgid() != null && organization.getRootorgid() == null){
            rootOrg = this.findById(organization.getParentorgid());

            //若为根机构
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

        manager = this.roleService.findBy("rolekey", manager.getRolekey());
        member = this.roleService.findBy("rolekey", member.getRolekey());

        this.assignOrgRole("机构管理权限", manager.getId());
        this.assignOrgRole("机构成员权限", member.getId());


        //创建管理员用户
        User user = new User();
        user.setName(organization.getName() + "管理员");
        user.setAccount(SQLUtil.generateNumber());
        user.setPassword("e10adc3949ba59abbe56e057f20f883e");
        boolean success = false;

        while(!success){
            try {
                userService.register(user);
                success = true;
                user = userService.findBy("account", user.getAccount());
            } catch (Exception e) {
                e.printStackTrace();
                user.setAccount(SQLUtil.generateNumber());
            }
        }

        //设置管理员角色
        userService.saveRole(user.getId(), manager.getId());
        NoticeList noticeList = new NoticeList();
        noticeList.setSubmituserid(login.getUser().getId());
        noticeList.setOrgid(login.getOrganization().getId());
        noticeList.setTitle(organization.getName() + "的机构管理员信息");
        noticeList.setContent("账号：" + user.getAccount() + "\n密码：123456");
        noticeList.setType("个人通知");
        noticeList.setSubordinatecanseen("否");
        this.noticeListService.saveNoticeList(noticeList, login);

    }

    /**
     * 机构入驻申请同意后新增机构
     * */
    @Override
    public void orgApply(Integer applyId, Integer orgManagerId) {
        OrgApply orgApply = this.orgApplyService.findById(applyId);

        Organization organization = new Organization();
        organization.setName(orgApply.getName());
        organization.setOrgkey(orgApply.getOrgkey());
        this.save(organization);

        organization = this.findBy("orgkey", organization.getOrgkey());

        //创建管理员和成员角色
        Role manager = new Role(organization.getName() + "管理员", organization.getOrgkey() + "Manager", organization.getId(), 5);
        Role member = new Role(organization.getName() + "成员", organization.getOrgkey() + "Member", organization.getId(), 1);
        roleService.save(manager);
        roleService.save(member);

        manager = this.roleService.findBy("rolekey", manager.getRolekey());
        member = this.roleService.findBy("rolekey", member.getRolekey());

        this.assignOrgRole("机构管理权限", manager.getId());
        this.assignOrgRole("机构成员权限", member.getId());

        //设置管理员角色
        userService.saveRole(orgManagerId, manager.getId());
    }

    /**
     * 分配权限
     * */
    public void assignOrgRole(String roleName, Integer roleId){
        SystemConfig systemConfig = this.systemConfigService.getValueByKey(roleName);

        String[] perIds = systemConfig.getValue().split(",");

        this.permissionService.assignPer(perIds, roleId);
    }
    /**
     * 删除机构
     * */
    @Override
    public void deleteOrg(Integer orgId) {
        Organization org = this.findById(orgId);
        List<Role> roles = roleService.findAllByLoginOrg(orgId);

        //先删除角色
        roleService.deleteBatch(roles);
        this.deleteById(orgId);
    }

    /**
     * 机构数据更新
     * */
    @Override
    public void updateOrg(Organization organization) {
        Organization rootOrg = null;
        //判断是否为根机构或者父机构
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

    /**
     * 获取当前登录用户加入的所有机构
     * */
    @Override
    public List<Organization> getAllLoginUserOrg(LoginUserDto loginUserDto) {
        List<Organization> list = organizationMapper.getAllLoginUserOrg(loginUserDto.getUser().getId());
        return list;
    }

    /**
     * 获取当前机构的所有子机构（非顶层机构）
     * */
    public List<Organization> getAllChildOrg(Integer orgId){
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setOrganization(new Organization());
        loginUserDto.getOrganization().setId(orgId);

        return this.listByKey("Name", "%%", loginUserDto);
    }

    /**
     * 是否有子机构
     * */
    @Override
    public Boolean hasChildOrg(Integer orgId) {
        List<Organization> list = this.organizationMapper.findChildOrg("NAME", "%%", orgId);
        Boolean sel = (list == null || list.size() == 0) ? false : true;
        return sel;
    }
}
