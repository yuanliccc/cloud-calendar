package group.cc.df.service.impl;

import group.cc.df.dao.DfUserAndRoleMapper;
import group.cc.df.model.DfUserAndRole;
import group.cc.df.service.DfUserAndRoleService;
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
public class DfUserAndRoleServiceImpl extends AbstractService<DfUserAndRole> implements DfUserAndRoleService {
    @Resource
    private DfUserAndRoleMapper dfUserAndRoleMapper;

}
