package group.cc.df.service.impl;

import group.cc.df.dao.DfModulesMapper;
import group.cc.df.model.DfModules;
import group.cc.df.service.DfModulesService;
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
public class DfModulesServiceImpl extends AbstractService<DfModules> implements DfModulesService {
    @Resource
    private DfModulesMapper dfModulesMapper;

}
