package group.cc.occ.service;
import group.cc.occ.model.Notice;
import java.util.List;
import group.cc.core.Service;
import group.cc.occ.model.dto.LoginUserDto;


/**
 * @author wangyuming
 * @date 2019/03/17
 */
public interface NoticeService extends Service<Notice> {
    public List<Notice> listByKey(String key, String value, LoginUserDto loginUserDto);
    public void deleteBatch(List<Notice> notices);
}
