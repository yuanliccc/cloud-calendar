package group.cc.occ.service.impl;

import group.cc.occ.dao.ScheduleMapper;
import group.cc.occ.model.Event;
import group.cc.occ.model.Schedule;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.model.dto.ScheduleDto;
import group.cc.occ.service.EventService;
import group.cc.occ.service.ScheduleService;

import java.util.ArrayList;
import java.util.Date;
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

    @Resource
    private EventService eventService;

    @Override
    public List<Schedule> listByKey(String key, String value, LoginUserDto login){
        value = "%" + value + "%";
        List<Schedule> list = scheduleMapper.listByKey(key, value, login.getOrganization().getId());

        return list;
    }

    @Override
    public List<Schedule> findAllByLoginOrgId(LoginUserDto login) {
        List<Schedule> list = scheduleMapper.listByKey("id","", login.getOrganization().getId());
        return list;
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

    @Override
    public void revoke(Integer scheduleId) {
        this.scheduleMapper.revoke(scheduleId);
    }

    @Override
    public List<ScheduleDto> findAllScheduleThisMonth(LoginUserDto login, Date dayTime) {
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        String orgParentOrRootIds = login.getOrganization().getParentorgid() + "," + login.getOrganization().getRootorgid();
        List<Schedule> list = this.scheduleMapper.findAllScheduleThisMonth(login.getOrganization().getId(), orgParentOrRootIds, dayTime);

        for (Schedule s: list){
            ScheduleDto scheduleDto = new ScheduleDto();
            List<Event> events = this.eventService.getTheEventByScheduleId(s.getId());

            for (Event e: events){
                if(e.getEndtime().compareTo(s.getEndtime()) > 0){
                    s.setEndtime(e.getEndtime());
                }
            }

            scheduleDto.setSchedule(s);
            scheduleDto.setEvents(events);
            scheduleDtos.add(scheduleDto);
        }

        return scheduleDtos;
    }

    @Override
    public List<ScheduleDto> findAllScheduleToday(LoginUserDto login) {
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        String orgParentOrRootIds = login.getOrganization().getParentorgid() + "," + login.getOrganization().getRootorgid();

        List<Schedule> list = this.scheduleMapper.findAllScheduleToday(login.getOrganization().getId(), orgParentOrRootIds);

        for (Schedule s: list){
            ScheduleDto scheduleDto = new ScheduleDto();
            List<Event> events = this.eventService.getTheEventByScheduleId(s.getId());

            for (Event e: events){
                if(e.getEndtime().compareTo(s.getEndtime()) > 0){
                    s.setEndtime(e.getEndtime());
                }
            }

            scheduleDto.setSchedule(s);
            scheduleDto.setEvents(events);
            scheduleDtos.add(scheduleDto);
        }
        return scheduleDtos;
    }
}
