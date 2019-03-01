package group.cc.occ.service.impl;

import group.cc.occ.dao.RoleMapper;
import group.cc.occ.model.Role;
import group.cc.occ.service.RoleService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role queryBy(String key, String value) {
        return roleMapper.queryBy(key, value);
    }
}
