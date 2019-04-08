package group.cc.bms.service.impl;

import group.cc.bms.dao.ChatMapper;
import group.cc.bms.model.Chat;
import group.cc.bms.service.ChatService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/19
 */
@Service
@Transactional
public class ChatServiceImpl extends AbstractService<Chat> implements ChatService {
    @Resource
    private ChatMapper chatMapper;

    @Override
    public List<Chat> findBySql(String Sql) {
        List<Chat> chats = chatMapper.findBySql(Sql);
        return chats;
    }
}
