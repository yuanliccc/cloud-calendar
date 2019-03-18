package group.cc.occ.service.impl;

import group.cc.occ.dao.NoticeMapper;
import group.cc.occ.model.Notice;
import group.cc.occ.model.User;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.NoticeService;

import java.util.Date;
import java.util.List;
import group.cc.core.AbstractService;
import group.cc.occ.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/03/17
 */
@Service
@Transactional
public class NoticeServiceImpl extends AbstractService<Notice> implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    @Resource
    private UserService userService;

    @Override
    public List<Notice> listByKey(String key, String value, LoginUserDto loginUserDto){
        value = "%" + value + "%";
        List<Notice> list = noticeMapper.listByKey(key, value, loginUserDto.getUser().getId());
        return list;
    }

    @Override
    public void deleteBatch(List<Notice> notices) {
        StringBuffer noticeSb = new StringBuffer();

        for (Notice e: notices){
            noticeSb.append(e.getId() + ",");
        }
        noticeSb.deleteCharAt(noticeSb.length() - 1);

        noticeMapper.deleteBatch(noticeSb.toString());
    }

    @Override
    public void addNotice(Notice notice, LoginUserDto loginUserDto) {
        if(notice.getId() != null && notice.getId() == -1){
            List<User> users = userService.getUserByLoginOrgId(loginUserDto);

            for (User u: users){
                Notice t = new Notice();
                t.setStarttime(new Date());
                t.setContent(notice.getContent());
                t.setState("已通知");
                t.setTitle(notice.getTitle());
                t.setType(notice.getType());
                t.setUserid(u.getId());
                this.save(t);
            }
        }else {
            notice.setStarttime(new Date());
            notice.setState("已通知");
            this.save(notice);
        }
    }
}
