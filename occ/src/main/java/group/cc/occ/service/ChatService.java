package group.cc.occ.service;
import group.cc.core.Service;
import group.cc.occ.model.Chat;

import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/19
 */
public interface ChatService extends Service<Chat> {
    public List<Chat> findBySql(String Sql);
}
