package group.cc.occ.model.dto;

import group.cc.occ.model.Event;
import group.cc.occ.model.Schedule;

import java.util.List;

/**
 * Created by wangyuming on 2019/5/10.
 */
public class ScheduleDto {
    private Schedule schedule;
    private List<Event> events;

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
