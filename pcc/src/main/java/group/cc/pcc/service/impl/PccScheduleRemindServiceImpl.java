package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccScheduleRemindMapper;
import group.cc.pcc.model.PccScheduleRemind;
import group.cc.pcc.service.PccScheduleRemindService;
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
public class PccScheduleRemindServiceImpl extends AbstractService<PccScheduleRemind> implements PccScheduleRemindService {
    @Resource
    private PccScheduleRemindMapper pccScheduleRemindMapper;

}
