package group.cc.occ.service;
import group.cc.occ.model.Role;
import group.cc.core.Service;

import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
public interface RoleService extends Service<Role> {
    public Role queryBy(String key, String value);

    public List<Role> listByKey(String key, String value);

    public Role findByUserIdAndOrgId(Integer userId, Integer orgId);

    public void deleteRole(Integer roleId);

    public void deleteBatch(List<Role> roles);
}
