package group.cc.occ.service.impl;


import group.cc.occ.dao.NoticeMapper;
import group.cc.occ.model.Chat;
import group.cc.occ.model.Notice;
import group.cc.occ.model.User;
import group.cc.occ.model.dto.ChatUser;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.ChatService;
import group.cc.occ.service.NoticeService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import group.cc.core.AbstractService;
import group.cc.occ.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private Log log = LogFactory.getLog(NoticeServiceImpl.class);

    @Resource
    private NoticeMapper noticeMapper;

    @Resource
    private ChatService chatService;

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

    /***
     * 新增未读通知
     * */
    @Override
    public void addNotice(Notice notice, LoginUserDto loginUserDto) {
        if(notice.getId() != null && notice.getId() == -1){
            List<User> users = userService.getUserByLoginOrgId(loginUserDto);

            for (User u: users){
                Notice t = new Notice();
                t.setStarttime(new Date());
                t.setContent(notice.getContent());
                t.setState("未读");
                t.setTitle(notice.getTitle());
                t.setType(notice.getType());
                t.setUserid(u.getId());
                this.save(t);
            }
        }else {
            notice.setStarttime(new Date());
            notice.setState("未读");
            this.save(notice);
        }
    }

    /**
     * 标记已读
     * */
    @Override
    public void seeBatch(List<Notice> notices) {
        StringBuffer noticeSb = new StringBuffer();

        for (Notice e: notices){
            noticeSb.append(e.getId() + ",");
        }
        noticeSb.deleteCharAt(noticeSb.length() - 1);

        noticeMapper.seeBatch(noticeSb.toString());
    }

    /**
     * 获取所有未读通知
     * */
    @Override
    public List<Notice> getAllUnreadNotice(LoginUserDto loginUserDto){
        List<Notice> list = noticeMapper.getAllUnreadNotice(loginUserDto.getUser().getId());
        return list;
    }


    /**
     * 获取和单个用户聊天的私信记录
     * */
    @Override
    public List<Chat> getChatUserMessage(Integer chatUserId, LoginUserDto loginUserDto) {
        Integer messageNum = this.noticeMapper.getUnreadMessageByUserId(loginUserDto.getUser().getId(), chatUserId, loginUserDto.getOrganization().getId());
        String receiveSql = "SELECT * FROM CHAT WHERE SENDUSERID = " + chatUserId + " AND RECEIVEUSERID = " + loginUserDto.getUser().getId() +
                " AND ORGID = " + loginUserDto.getOrganization().getId() + " ORDER BY SENDTIME DESC LIMIT 0," + (messageNum > 10 ? messageNum : 10);

        String sendSql = "SELECT * FROM CHAT WHERE SENDUSERID = " + loginUserDto.getUser().getId() + " AND RECEIVEUSERID = " + chatUserId +
                " AND ORGID = " + loginUserDto.getOrganization().getId() + " ORDER BY SENDTIME DESC LIMIT 0,10";;

        List<Chat> receive = chatService.findBySql(receiveSql);
        List<Chat> send = chatService.findBySql(sendSql);

        receive.addAll(send);

        receive =  receive.stream().sorted((Chat a, Chat b) ->{
            return a.getSendtime().compareTo(b.getSendtime());
        }).collect(Collectors.toList());

        return receive;
    }

    /**
     * 获取总的未读私信数量
     * */
    @Override
    public Integer getUnreadMessage(LoginUserDto loginUserDto) {
        return noticeMapper.getAllUnreadMessage(loginUserDto.getUser().getId(), loginUserDto.getOrganization().getId());
    }


    /***
     * 拉取可聊天用户列表
     * */
    @Override
    public List<ChatUser> getChatUser(LoginUserDto loginUserDto) {
        List<User> users = this.userService.getUserByLoginOrgId(loginUserDto);
        List<ChatUser> chatUsers = new ArrayList<>();

        for (User u: users){
            if(u.getId().equals(loginUserDto.getUser().getId())) continue;

            u.setPassword(null);
            ChatUser chatUser = new ChatUser(u);
            Integer messageNum = this.noticeMapper.getUnreadMessageByUserId(loginUserDto.getUser().getId(), u.getId(), loginUserDto.getOrganization().getId());
            chatUser.setUnreadnum(messageNum);
            chatUser.setDisplay(true);
            chatUsers.add(chatUser);
        }

        chatUsers = chatUsers.stream().sorted((ChatUser a,ChatUser b) ->{
            return b.getUnreadnum().compareTo(a.getUnreadnum());
        }).collect(Collectors.toList());

        return chatUsers;
    }

    //消息已读
    @Override
    public void seeAllMessage(Integer sendUserId, LoginUserDto loginUserDto) {
        this.noticeMapper.seeAllChat(sendUserId, loginUserDto.getUser().getId(), loginUserDto.getOrganization().getId());
    }
}
