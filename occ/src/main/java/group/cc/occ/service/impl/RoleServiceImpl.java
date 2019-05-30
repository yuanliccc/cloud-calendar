package group.cc.occ.service.impl;

import group.cc.occ.dao.RoleMapper;
import group.cc.occ.model.Role;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.model.dto.PermissionDto;
import group.cc.occ.service.PermissionService;
import group.cc.occ.service.RoleService;
import group.cc.core.AbstractService;
import group.cc.occ.service.UserService;
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
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionService permissionService;

    @Override
    public Role queryBy(String key, String value) {
        return roleMapper.queryBy(key, value);
    }

    @Override
    public List<Role> listByKey(String key, String value, LoginUserDto login) {
        value = "%" + value + "%";
        List<Role> list = this.roleMapper.listByKey(key, value, login.getOrganization().getId());
        return list;
    }

    /***
     * 查找该用户在特定部门的角色
     * */
    @Override
    public Role findByUserIdAndOrgId(Integer userId, Integer orgId) {
        return roleMapper.findRoleByUserIdAndOrgId(userId, orgId);
    }

    @Override
    public void deleteRole(Integer roleId) {
        this.permissionService.deleteByRoleId(roleId);
        roleMapper.deleteUserRole(roleId + " ");
        this.deleteById(roleId);
    }

    @Override
    public void deleteBatch(List<Role> roles) {
        if(roles == null || roles.size() == 0) return ;

        StringBuffer roleSb = new StringBuffer();

        for (Role r: roles){
            roleSb.append(r.getId() + ",");
        }

        roleSb.deleteCharAt(roleSb.length() - 1);

        //先删除角色和权限点的中间表
        roleMapper.deleteRolePermission(roleSb.toString());
        //在删除角色和用户的中间表
        roleMapper.deleteUserRole(roleSb.toString());
        roleMapper.deleteBatch(roleSb.toString());
    }

    /***
     * 为角色分配权限
     * */
    @Override
    public void assignPer(PermissionDto permissionDto) {
        roleMapper.deleteRolePermission(permissionDto.getRoleId() + "");

        this.permissionService.assignPer(permissionDto);
    }

    /**
     * 查找当前登录机构的所有角色
     * */
    @Override
    public List<Role> findAllByLoginOrg(LoginUserDto login) {
        List<Role> list = roleMapper.findAllByLoginOrg(login.getOrganization().getId());
        return list;
    }

    /**
     * 查找当前登录机构的所有角色
     * */
    @Override
    public List<Role> findAllByLoginOrg(Integer orgId) {
        List<Role> list = roleMapper.findAllByLoginOrg(orgId);
        return list;
    }

    /***
     * 为用户分配角色
     * */
    @Override
    public void distributeRole(Integer userId, Integer roleId, LoginUserDto login) {
        Role r = this.roleMapper.getRoleByUserId(userId, login.getOrganization().getId());

        Integer id = roleMapper.getUserRoleId(userId, login.getOrganization().getId());
        if(id != null){
            roleMapper.deleteUserRoleById(id);
        }
        roleMapper.addUserRole(userId, roleId);
    }

    /**
     * 获取在该部门内该用户的角色
     * */
    @Override
    public Role getRoleByUserId(Integer userId, Integer orgId) {
        Role role = roleMapper.getRoleByUserId(userId, orgId);
        return role;
    }

    /**
     * 查找该机构普通成员角色
     * */
    @Override
    public Role findOrgMemberRole(Integer orgId) {
        Role role = roleMapper.findOrgMemberRole(orgId);
        return role;
    }

    /**
     * 移除成员出本机构
     * */
    @Override
    public void deleteUserRoleByUserIdAndRoleId(Integer userId, Integer roleId) {
        this.roleMapper.deleteUserRoleByUserIdAndRoleId(userId, roleId);
    }
}
