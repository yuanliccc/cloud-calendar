package group.cc.occ.service;
import group.cc.occ.model.WorkArrange;
import java.util.List;
import group.cc.core.Service;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.model.dto.WorkArrangeDto;


/**
 * @author wangyuming
 * @date 2019/04/27
 */
public interface WorkArrangeService extends Service<WorkArrange> {
    public List<WorkArrange> listByKey(String key, String value, LoginUserDto login);
    public void deleteBatch(List<WorkArrange> workArranges);
    public void saveUserForWork(WorkArrangeDto workArrangeDto);
    public List<Integer> getWorkUser(Integer workId);
    public void deleteUserWork(Integer workArrangeId);
    public void updateUserWork(Integer workArrangeId, String isRepeat);
}
