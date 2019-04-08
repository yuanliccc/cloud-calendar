package group.cc.bms.service;
import group.cc.bms.model.Chat;
import group.cc.core.Service;

import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/19
 */
public interface ChatService extends Service<Chat> {
    public List<Chat> findBySql(String Sql);
}
