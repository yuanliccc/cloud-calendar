package group.cc.occ.service.impl;

import group.cc.occ.dao.OrganizationMapper;
import group.cc.occ.model.Organization;
import group.cc.occ.service.OrganizationService;
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
public class OrganizationServiceImpl extends AbstractService<Organization> implements OrganizationService {
    @Resource
    private OrganizationMapper organizationMapper;

}
