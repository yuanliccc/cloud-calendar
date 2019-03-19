package group.cc.bms.service.impl;

import group.cc.bms.service.NoticeScoketService;
import group.cc.bms.webscoket.WebNoticeSocketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangyuming on 2019/3/19.
 */
@Service
@Transactional
public class NoticeScoketServiceImpl implements NoticeScoketService {

    @Override
    public void OccNotice(Integer userId, String message) {
        WebNoticeSocketService.sendMessage(message, userId);
    }
}
