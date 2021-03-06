package group.cc.occ.service;
import group.cc.occ.model.Schedule;

import java.util.Date;
import java.util.List;
import group.cc.core.Service;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.model.dto.ScheduleDto;


/**
 * @author wangyuming
 * @date 2019/03/29
 */
public interface ScheduleService extends Service<Schedule> {
    public List<Schedule> findAllByLoginOrgId(LoginUserDto login);
    public List<Schedule> listByKey(String key, String value, LoginUserDto login);
    public void deleteBatch(List<Schedule> schedules);
    public void revoke(Integer scheduleId);
    public List<ScheduleDto> findAllScheduleThisMonth(LoginUserDto login, Date dayTime);
    public List<ScheduleDto> findAllScheduleToday(LoginUserDto login);
}
