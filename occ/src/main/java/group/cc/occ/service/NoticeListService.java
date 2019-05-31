package group.cc.occ.service;
import group.cc.occ.model.NoticeList;
import java.util.List;
import group.cc.core.Service;
import group.cc.occ.model.dto.LoginUserDto;


/**
 * @author wangyuming
 * @date 2019/05/29
 */
public interface NoticeListService extends Service<NoticeList> {
    public List<NoticeList> listByKey(String key, String value);
    public void deleteBatch(List<NoticeList> noticeLists);
    public void saveNoticeList(NoticeList noticeList, LoginUserDto login);
    public void notice(Integer submitUserId, String title, String content, String type, String subordinatecanseen, LoginUserDto login);
}
