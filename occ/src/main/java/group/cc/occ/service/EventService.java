package group.cc.occ.service;
import group.cc.occ.model.Event;
import java.util.List;
import group.cc.core.Service;
import group.cc.occ.model.dto.LoginUserDto;


/**
 * @author wangyuming
 * @date 2019/04/26
 */
public interface EventService extends Service<Event> {
    public List<Event> listByKey(String key, String value, LoginUserDto loginUserDto);
    public void deleteBatch(List<Event> events);
}
