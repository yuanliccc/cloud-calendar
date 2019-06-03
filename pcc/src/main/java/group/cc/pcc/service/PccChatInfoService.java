package group.cc.pcc.service;
import group.cc.pcc.model.PccChatInfo;
import group.cc.core.Service;

import java.util.List;


/**
 * @author yuanli
 * @date 2019/05/30
 */
public interface PccChatInfoService extends Service<PccChatInfo> {

    List<PccChatInfo> friendChatInfo(Integer sendUserId, Integer receiveUserId);

    Integer count(Integer receiveUserId);

    Integer countSendUserChatInfo(Integer sendUserId, Integer receiveUserId);

    void receiveChatInfo(Integer sendUserId, Integer receiveUserId);

}
