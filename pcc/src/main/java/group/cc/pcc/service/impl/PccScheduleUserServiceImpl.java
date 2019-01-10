package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccScheduleUserMapper;
import group.cc.pcc.model.PccScheduleUser;
import group.cc.pcc.service.PccScheduleUserService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author yuanli
 * @date 2019/01/10
 */
@Service
@Transactional
public class PccScheduleUserServiceImpl extends AbstractService<PccScheduleUser> implements PccScheduleUserService {
    @Resource
    private PccScheduleUserMapper pccScheduleUserMapper;

}
