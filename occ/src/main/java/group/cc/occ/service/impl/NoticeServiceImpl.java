package group.cc.occ.service.impl;

import group.cc.occ.dao.NoticeMapper;
import group.cc.occ.model.Notice;
import group.cc.occ.service.NoticeService;
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
public class NoticeServiceImpl extends AbstractService<Notice> implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

}
