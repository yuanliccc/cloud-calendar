package group.cc.occ.service.impl;

import group.cc.occ.dao.ScheduleMapper;
import group.cc.occ.model.Schedule;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.ScheduleService;
import java.util.List;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/03/29
 */
@Service
@Transactional
public class ScheduleServiceImpl extends AbstractService<Schedule> implements ScheduleService {
    @Resource
    private ScheduleMapper scheduleMapper;

    @Override
    public List<Schedule> listByKey(String key, String value, LoginUserDto login){
        value = "%" + value + "%";
        List<Schedule> list = scheduleMapper.listByKey(key, value, login.getOrganization().getId());
        return list;
    }

    @Override
    public List<Schedule> findAllByLoginOrgId(LoginUserDto login) {
        return null;
    }

    @Override
    public void deleteBatch(List<Schedule> schedules) {
        StringBuffer scheduleSb = new StringBuffer();

        for (Schedule e: schedules){
            scheduleSb.append(e.getId() + ",");
        }
        scheduleSb.deleteCharAt(scheduleSb.length() - 1);

        scheduleMapper.deleteBatch(scheduleSb.toString());
    }
}
