package group.cc.occ.service;
import group.cc.occ.model.Role;
import group.cc.core.Service;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
public interface RoleService extends Service<Role> {
    public Role queryBy(String key, String value);
}
