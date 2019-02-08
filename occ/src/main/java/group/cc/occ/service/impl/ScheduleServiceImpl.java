package group.cc.occ.service.impl;

import group.cc.occ.dao.ScheduleMapper;
import group.cc.occ.model.Schedule;
import group.cc.occ.service.ScheduleService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/01/02
 */
@Service
@Transactional
public class ScheduleServiceImpl extends AbstractService<Schedule> implements ScheduleService {
    @Resource
    private ScheduleMapper scheduleMapper;

}
