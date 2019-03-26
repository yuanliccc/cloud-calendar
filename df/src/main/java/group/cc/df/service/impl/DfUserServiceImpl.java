package group.cc.df.service.impl;

import group.cc.df.dao.DfUserMapper;
import group.cc.df.model.DfUser;
import group.cc.df.service.DfUserService;
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
public class DfUserServiceImpl extends AbstractService<DfUser> implements DfUserService {
    @Resource
    private DfUserMapper dfUserMapper;

}
