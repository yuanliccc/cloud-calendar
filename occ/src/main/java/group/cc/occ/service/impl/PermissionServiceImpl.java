package group.cc.occ.service.impl;

import group.cc.occ.dao.PermissionMapper;
import group.cc.occ.model.Permission;
import group.cc.occ.model.dto.PermissionDto;
import group.cc.occ.service.PermissionService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
@Service
@Transactional
public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 通过模块id批量删除权限
     * */
    @Override
    public void deleteByModules(String moduleIds) {
        this.permissionMapper.deleteByModules(moduleIds);
    }

    /**
     * 通过角色Id查找权限
     * */
    @Override
    public List<String> findPerForRoleId(Integer roleId) {
        List<String> per = new ArrayList<>();
        List<Permission>list =  permissionMapper.findPerForRoleId(roleId);
        for (Permission p: list)
            per.add(p.getIdentify());
        return per;
    }

    /**
     * 通过角色id删除权限
     * */
    @Override
    public void deleteByRoleId(Integer roleId) {
        this.permissionMapper.deleteByRoleId(roleId);
    }

    /**
     * 通过模块id删除权限
     * */
    @Override
    public void deleteByModuleId(Integer moduleId) {
        this.permissionMapper.deleteByModuleId(moduleId);
    }

    /**
     * 通过角色Id查找权限
     * */
    @Override
    public List<Permission> getAllPermissionByRoleId(Integer roleId) {
        return this.permissionMapper.getAllPermissionByRoleId(roleId);
    }

    /**
     * 通过角色Id查找该角色没有的权限
     * */
    @Override
    public List<Permission> getOtherPermission(Integer roleId) {
        return this.permissionMapper.getOtherPermission(roleId);
    }

    @Override
    public List<Permission> listByKey(String key, String value) {
        value = "%" + value + "%";
        List<Permission> list = permissionMapper.listByKey(key, value);
        return list;
    }

    /**
     * 分配权限
     * */
    @Override
    public void assignPer(PermissionDto permissionDto) {
        for (Integer id: permissionDto.getPermissions()){
            this.permissionMapper.addRolePermission(permissionDto.getRoleId(), id);
        }
    }

    /**
     * 分配权限
     * */
    @Override
    public void assignPer(String[] pers, Integer roleId) {
       for (String perId: pers){
           this.permissionMapper.addRolePermission(roleId, Integer.parseInt(perId));
       }
    }

    @Override
    public void deleteBatch(List<Permission> pers) {
        StringBuffer perSb = new StringBuffer();

        for (Permission p: pers){
                perSb.append(p.getId() + ",");
        }

        perSb.deleteCharAt(perSb.length() - 1);

        permissionMapper.deleteBatch(perSb.toString());
    }
}
