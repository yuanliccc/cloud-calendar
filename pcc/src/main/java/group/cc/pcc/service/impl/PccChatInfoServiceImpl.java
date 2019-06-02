package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccChatInfoMapper;
import group.cc.pcc.model.PccChatInfo;
import group.cc.pcc.service.PccChatInfoService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author yuanli
 * @date 2019/05/30
 */
@Service
@Transactional
public class PccChatInfoServiceImpl extends AbstractService<PccChatInfo> implements PccChatInfoService {

    @Resource
    private PccChatInfoMapper pccChatInfoMapper;

    @Override
    public List<PccChatInfo> friendChatInfo(Integer sendUserId, Integer receiveUserId) {
        return pccChatInfoMapper.friendChatInfo(sendUserId, receiveUserId);
    }

    @Override
    public Integer count(Integer receiveUserId) {
        return pccChatInfoMapper.count(receiveUserId);
    }

    @Override
    public Integer countSendUserChatInfo(Integer sendUserId
            , Integer receiveUserId) {
        return pccChatInfoMapper.countSendUserChatInfo(sendUserId, receiveUserId);
    }

    @Override
    public void receiveChatInfo(Integer sendUserId
            , Integer receiveUserId) {
        pccChatInfoMapper.receiveChatInfo(sendUserId, receiveUserId);
    }
}
