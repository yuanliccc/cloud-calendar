package group.cc.pcc.service;
import group.cc.pcc.model.PccUserFriend;
import group.cc.core.Service;


/**
 * @author yuanli
 * @date 2019/01/10
 */
public interface PccUserFriendService extends Service<PccUserFriend> {

    void remark(Integer pccUserId, Integer friendPccUserId, String remark);

    void deleteByIdes(Integer pccUserId, Integer friendPccUserId);

    void saveFriend(Integer pccUserId, Integer friendPccUserId);

    void friendApply(Integer pccUserId, Integer friendPccUserId);

    void friendApplyEmail(Integer pccUserId, String email);

    boolean isFriend(Integer pccUserId, String email);

    boolean isFriend(Integer pccUserId, Integer friendPccUserId);
}
