package group.cc.occ.service.impl;

import group.cc.occ.dao.EventMapper;
import group.cc.occ.model.Event;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.EventService;
import java.util.List;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/04/26
 */
@Service
@Transactional
public class EventServiceImpl extends AbstractService<Event> implements EventService {
    @Resource
    private EventMapper eventMapper;

    @Override
    public List<Event> listByKey(String key, String value, LoginUserDto loginUserDto){
        value = "%" + value + "%";
        List<Event> list = eventMapper.listByKey(key, value, loginUserDto.getOrganization().getId());
        return list;
    }

    @Override
    public void deleteBatch(List<Event> events) {
        StringBuffer eventSb = new StringBuffer();

        for (Event e: events){
            eventSb.append(e.getId() + ",");
        }
        eventSb.deleteCharAt(eventSb.length() - 1);

        eventMapper.deleteBatch(eventSb.toString());
    }

    @Override
    public List<Event> getTheEventByScheduleId(Integer id) {
        List<Event> list = this.eventMapper.getTheEventByScheduleId(id);
        return list;
    }
}
