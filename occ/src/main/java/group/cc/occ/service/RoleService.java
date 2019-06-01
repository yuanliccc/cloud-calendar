package group.cc.occ.service;
import group.cc.occ.model.Role;
import group.cc.core.Service;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.model.dto.PermissionDto;

import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
public interface RoleService extends Service<Role> {
    public Role queryBy(String key, String value);

    public List<Role> listByKey(String key, String value, LoginUserDto login);

    public Role findByUserIdAndOrgId(Integer userId, Integer orgId);

    public void deleteRole(Integer roleId);

    public void deleteBatch(List<Role> roles);

    public void assignPer(PermissionDto permissionDto);

    public List<Role> findAllByLoginOrg(LoginUserDto login);

    public void distributeRole(Integer userId, Integer roleId, LoginUserDto login);

    public Role getRoleByUserId(Integer userId, Integer orgId);

    public List<Role> findAllByLoginOrg(Integer orgId);

    public Role findOrgMemberRole(Integer orgId);

    public void deleteUserRoleByUserIdAndRoleId(Integer userId, Integer roleId);
}
