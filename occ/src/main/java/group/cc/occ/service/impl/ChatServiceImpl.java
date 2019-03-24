package group.cc.occ.service.impl;


import group.cc.core.AbstractService;
import group.cc.occ.dao.ChatMapper;
import group.cc.occ.model.Chat;
import group.cc.occ.service.ChatService;
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
