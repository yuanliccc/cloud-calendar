package group.cc.occ.service.impl;

import group.cc.occ.dao.UserWorkArrangeMapper;
import group.cc.occ.model.UserWorkArrange;
import group.cc.occ.model.WorkArrange;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.UserWorkArrangeService;

import java.util.Calendar;
import java.util.List;
import group.cc.core.AbstractService;
import group.cc.occ.service.WorkArrangeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @ddate 2019/04/27
 */
@Service
@Transactional
public class UserWorkArrangeServiceImpl extends AbstractService<UserWorkArrange> implements UserWorkArrangeService {
    @Resource
    private UserWorkArrangeMapper userWorkArrangeMapper;

    @Resource
    private WorkArrangeService workArrangeService;

    @Override
    public List<UserWorkArrange> listByKey(String key, String value){
        value = "%" + value + "%";
        List<UserWorkArrange> list = userWorkArrangeMapper.listByKey(key, value);
        return list;
    }

    @Override
    public void deleteBatch(List<UserWorkArrange> userWorkArranges) {
        StringBuffer userWorkArrangeSb = new StringBuffer();

        for (UserWorkArrange e: userWorkArranges){
            userWorkArrangeSb.append(e.getId() + ",");
        }
        userWorkArrangeSb.deleteCharAt(userWorkArrangeSb.length() - 1);

        userWorkArrangeMapper.deleteBatch(userWorkArrangeSb.toString());
    }

    @Override
    public void finish(Integer workId, LoginUserDto loginUserDto) {
        UserWorkArrange userWorkArrange = this.userWorkArrangeMapper.findByUserIdAndWorkId(workId, loginUserDto.getUser().getId());
        Calendar calendar = Calendar.getInstance();


        StringBuffer sb = new StringBuffer();
        if(userWorkArrange.getCompleteyear() == null){
            sb.append(calendar.get(Calendar.YEAR));
        }else
            sb.append(userWorkArrange.getCompleteyear() + "," + calendar.get(Calendar.YEAR));
        this.userWorkArrangeMapper.finishAndContinue(workId, loginUserDto.getUser().getId(), sb.toString());


        WorkArrange workArrange = this.workArrangeService.findById(workId);
        if(this.userWorkArrangeMapper.getSubNum(workId) == 0 && "否".equals(workArrange.getIsrepeat())){
            workArrange.setState("已完成");
            this.workArrangeService.update(workArrange);
        }


    }
}
