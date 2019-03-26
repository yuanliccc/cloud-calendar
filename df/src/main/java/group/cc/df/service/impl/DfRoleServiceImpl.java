package group.cc.df.service.impl;

import group.cc.df.dao.DfRoleMapper;
import group.cc.df.model.DfRole;
import group.cc.df.service.DfRoleService;
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
public class DfRoleServiceImpl extends AbstractService<DfRole> implements DfRoleService {
    @Resource
    private DfRoleMapper dfRoleMapper;

}
