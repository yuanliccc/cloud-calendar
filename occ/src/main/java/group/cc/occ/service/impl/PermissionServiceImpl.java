package group.cc.occ.service.impl;

import group.cc.occ.dao.PermissionMapper;
import group.cc.occ.model.Permission;
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

    @Override
    public void deleteByModules(String moduleIds) {
        this.permissionMapper.deleteByModules(moduleIds);
    }

    @Override
    public List<String> findPerForRoleId(Integer roleId) {
        List<String> per = new ArrayList<>();
        List<Permission>list =  permissionMapper.findPerForRoleId(roleId);
        for (Permission p: list)
            per.add(p.getIdentify());
        return per;
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        this.permissionMapper.deleteByRoleId(roleId);
    }

    @Override
    public void deleteByModuleId(Integer moduleId) {
        this.permissionMapper.deleteByModuleId(moduleId);
    }


}
