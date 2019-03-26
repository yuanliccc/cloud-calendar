package group.cc.df.service.impl;

import group.cc.df.dao.DfPermissionMapper;
import group.cc.df.model.DfPermission;
import group.cc.df.service.DfPermissionService;
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
public class DfPermissionServiceImpl extends AbstractService<DfPermission> implements DfPermissionService {
    @Resource
    private DfPermissionMapper dfPermissionMapper;

}
