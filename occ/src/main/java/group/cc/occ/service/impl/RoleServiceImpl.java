package group.cc.occ.service.impl;

import group.cc.occ.dao.RoleMapper;
import group.cc.occ.model.Role;
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
    public List<Role> listByKey(String key, String value) {
        value = "%" + value + "%";
        List<Role> list = this.roleMapper.listByKey(key, value);
        return list;
    }

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
        StringBuffer roleSb = new StringBuffer();

        for (Role r: roles){
            roleSb.append(r.getId() + ",");
        }

        roleSb.deleteCharAt(roleSb.length() - 1);

        roleMapper.deleteRolePermission(roleSb.toString());
        roleMapper.deleteUserRole(roleSb.toString());
        roleMapper.deleteBatch(roleSb.toString());
    }
}
