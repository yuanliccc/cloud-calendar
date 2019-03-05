package group.cc.occ.service;
import group.cc.occ.model.Permission;
import group.cc.core.Service;

import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
public interface PermissionService extends Service<Permission> {
    public void deleteByModuleId(Integer moduleId);

    public void deleteByModules(String moduleIds);

    public List<String> findPerForRoleId(Integer roleId);

    public void deleteByRoleId(Integer roleId);
}
