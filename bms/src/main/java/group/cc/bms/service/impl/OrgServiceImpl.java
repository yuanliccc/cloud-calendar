package group.cc.bms.service.impl;

import group.cc.bms.dao.OrgMapper;
import group.cc.bms.model.Org;
import group.cc.bms.service.OrgService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author yuanli
 * @date 2018/11/01
 */
@Service
@Transactional
public class OrgServiceImpl extends AbstractService<Org> implements OrgService {
    @Resource
    private OrgMapper orgMapper;

}
