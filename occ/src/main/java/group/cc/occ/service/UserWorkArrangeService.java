package group.cc.occ.service;
import group.cc.occ.model.UserWorkArrange;
import java.util.List;
import group.cc.core.Service;
import group.cc.occ.model.dto.LoginUserDto;


/**
 * @author wangyuming
 * @ddate 2019/04/27
 */
public interface UserWorkArrangeService extends Service<UserWorkArrange> {
    public List<UserWorkArrange> listByKey(String key, String value);
    public void deleteBatch(List<UserWorkArrange> userWorkArranges);
    public void finish(Integer workId, LoginUserDto loginUserDto);
}
