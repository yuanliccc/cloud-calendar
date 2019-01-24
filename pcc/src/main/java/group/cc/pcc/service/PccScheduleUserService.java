package group.cc.pcc.service;
import group.cc.pcc.model.PccScheduleUser;
import group.cc.core.Service;


/**
 * @author yuanli
 * @date 2019/01/10
 */
public interface PccScheduleUserService extends Service<PccScheduleUser> {

    void complete(Integer pccUserId, Integer pccScheduleId);

}
