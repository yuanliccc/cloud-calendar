package group.cc.occ.service.impl;

import group.cc.occ.dao.UserWorkArrangeMapper;
import group.cc.occ.dao.WorkArrangeMapper;
import group.cc.occ.model.UserWorkArrange;
import group.cc.occ.model.WorkArrange;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.model.dto.WorkArrangeDto;
import group.cc.occ.service.WorkArrangeService;
import java.util.List;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/04/27
 */
@Service
@Transactional
public class WorkArrangeServiceImpl extends AbstractService<WorkArrange> implements WorkArrangeService {
    @Resource
    private WorkArrangeMapper workArrangeMapper;

    @Resource
    private UserWorkArrangeMapper userWorkArrangeMapper;

    @Override
    public List<WorkArrange> listByKey(String key, String value, LoginUserDto login){
        List<WorkArrange> list = null;
        value = "%" + value + "%";
        if(login.getPermissions() != null && login.getPermissions().contains("workArrange_manager"))
            list = workArrangeMapper.listByKeyForManager(key, value, login.getOrganization().getId());
        else
            list = workArrangeMapper.listByKey(key, value, login.getOrganization().getId(), login.getUser().getId());

        for (int i = 0; i < list.size(); i++){
            List<UserWorkArrange> userWorkArranges = this.userWorkArrangeMapper.findByWorkId(list.get(i).getId());
            list.get(i).setUserWorkArrangeList(userWorkArranges);
        }

        return list;
    }

    @Override
    public void deleteBatch(List<WorkArrange> workArranges) {
        StringBuffer workArrangeSb = new StringBuffer();

        for (WorkArrange e: workArranges){
            workArrangeSb.append(e.getId() + ",");
            deleteUserWork(e.getId());
        }


        workArrangeSb.deleteCharAt(workArrangeSb.length() - 1);

        workArrangeMapper.deleteBatch(workArrangeSb.toString());
    }

    public void deleteUserWork(Integer workArrangeId){
        List<Integer> list = this.userWorkArrangeMapper.getWorkUserIdByWorkId(workArrangeId);
        StringBuffer userWorkSb = new StringBuffer();

        for (Integer i: list){
            userWorkSb.append(i + ",");
        }

        userWorkSb.deleteCharAt(userWorkSb.length() - 1);

        this.userWorkArrangeMapper.deleteBatch(userWorkSb.toString());
    }

    @Override
    public void saveUserForWork(WorkArrangeDto workArrangeDto) {
        WorkArrange workArrange = this.findById(workArrangeDto.getWorkarrangeid());
        this.workArrangeMapper.deleteWorkUser(workArrange.getId());
        for (Integer userId: workArrangeDto.getUserlist()){
            this.workArrangeMapper.saveUserForWork(workArrange.getId(), userId, workArrange.getIsrepeat());
        }
    }

    @Override
    public List<Integer> getWorkUser(Integer workId) {
        List<Integer> userIds =  this.userWorkArrangeMapper.getWorkUser(workId);

        return userIds;
    }

    @Override
    public void updateUserWork(Integer workArrangeId, String isRepeat) {
        List<Integer> list = this.userWorkArrangeMapper.getWorkUserIdByWorkId(workArrangeId);
        for (Integer i: list){
            this.userWorkArrangeMapper.updateState(i, isRepeat);
        }
    }
}
