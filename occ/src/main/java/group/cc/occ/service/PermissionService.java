package group.cc.occ.service;
import group.cc.occ.model.Permission;
import group.cc.core.Service;
import group.cc.occ.model.dto.PermissionDto;

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

    public List<Permission> getAllPermissionByRoleId(Integer roleId);

    public List<Permission> getOtherPermission(Integer roleId);

    public void assignPer(PermissionDto permissionDto);

    public List<Permission> listByKey(String key, String value);

    public void deleteBatch(List<Permission> pers);
}
