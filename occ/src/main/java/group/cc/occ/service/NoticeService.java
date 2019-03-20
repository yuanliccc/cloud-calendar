package group.cc.occ.service;
import group.cc.bms.model.Chat;
import group.cc.occ.model.Notice;
import java.util.List;
import group.cc.core.Service;
import group.cc.occ.model.dto.ChatUser;
import group.cc.occ.model.dto.LoginUserDto;


/**
 * @author wangyuming
 * @date 2019/03/17
 */
public interface NoticeService extends Service<Notice> {
    public List<Notice> listByKey(String key, String value, LoginUserDto loginUserDto);
    public void deleteBatch(List<Notice> notices);
    public void seeBatch(List<Notice> notices);
    public void addNotice(Notice notice, LoginUserDto loginUserDto);
    public List<Notice> getAllUnreadNotice(LoginUserDto loginUserDto);

    public List<Chat> getChatUserMessage(Integer chatUserId, LoginUserDto loginUserDto);

    public Integer getUnreadMessage(LoginUserDto loginUserDto);

    public List<ChatUser> getChatUser(LoginUserDto loginUserDto);
}
