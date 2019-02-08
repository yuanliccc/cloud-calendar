package group.cc.occ.service.impl;

import group.cc.occ.dao.EventMapper;
import group.cc.occ.model.Event;
import group.cc.occ.service.EventService;
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
public class EventServiceImpl extends AbstractService<Event> implements EventService {
    @Resource
    private EventMapper eventMapper;

}
