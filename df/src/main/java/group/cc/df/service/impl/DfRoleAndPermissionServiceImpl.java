package group.cc.df.service.impl;

import group.cc.df.dao.DfRoleAndPermissionMapper;
import group.cc.df.model.DfRoleAndPermission;
import group.cc.df.service.DfRoleAndPermissionService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author gxd
 * @date 2019/03/25
 */
@Service
@Transactional
public class DfRoleAndPermissionServiceImpl extends AbstractService<DfRoleAndPermission> implements DfRoleAndPermissionService {
    @Resource
    private DfRoleAndPermissionMapper dfRoleAndPermissionMapper;

}
