package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccScheduleMapper;
import group.cc.pcc.model.PccSchedule;
import group.cc.pcc.service.PccScheduleService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author yuanli
 * @date 2018/12/23
 */
@Service
@Transactional
public class PccScheduleServiceImpl extends AbstractService<PccSchedule> implements PccScheduleService {
    @Resource
    private PccScheduleMapper pccScheduleMapper;

}
