package group.cc.pcc.service.impl;

import com.alibaba.fastjson.JSONObject;
import group.cc.pcc.dao.PccUserFriendMapper;
import group.cc.pcc.model.PccNotice;
import group.cc.pcc.model.PccUser;
import group.cc.pcc.model.PccUserFriend;
import group.cc.pcc.server.WebSocketServer;
import group.cc.pcc.service.PccNoticeService;
import group.cc.pcc.service.PccUserFriendService;
import group.cc.core.AbstractService;
import group.cc.pcc.service.PccUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author yuanli
 * @date 2019/01/10
 */
@Service
@Transactional
public class PccUserFriendServiceImpl extends AbstractService<PccUserFriend> implements PccUserFriendService {

    @Resource
    private PccUserFriendMapper pccUserFriendMapper;

    @Resource
    private PccUserService pccUserService;

    @Resource
    private PccNoticeService pccNoticeService;

    @Resource
    private WebSocketServer webSocketServer;

    @Override
    public void remark(Integer pccUserId, Integer friendPccUserId, String remark) {
        pccUserFriendMapper.remark(pccUserId, friendPccUserId, remark);
    }

    @Override
    public void deleteByIdes(Integer pccUserId, Integer friendPccUserId) {
        pccUserFriendMapper.deleteByIdes(pccUserId, friendPccUserId);
    }

    @Override
    public void saveFriend(Integer pccUserId, Integer friendPccUserId) {
        PccUserFriend p1 = new PccUserFriend();
        PccUserFriend p2 = new PccUserFriend();
        p1.setPccUserId(pccUserId);
        p1.setFriendPccUserId(friendPccUserId);
        p2.setPccUserId(friendPccUserId);
        p2.setFriendPccUserId(pccUserId);
        pccUserFriendMapper.insert(p1);
        pccUserFriendMapper.insert(p2);
    }

    @Override
    public void friendApply(Integer pccUserId, Integer friendPccUserId) {
        PccUser pccUser = pccUserService.findById(pccUserId);
        PccUser friendPccUser = pccUserService.findById(friendPccUserId);

        PccNotice pccNotice = new PccNotice();
        pccNotice.setTime(new Date());
        pccNotice.setType("friend-apply");
        pccNotice.setApi("/pcc/user/friend/save/friend");
        pccNotice.setContent(JSONObject.toJSONString(pccUser));
        pccNotice.setPccUserId(friendPccUser.getId());

        pccNoticeService.save(pccNotice);

        webSocketServer.sendNotice(pccNotice);
    }

    @Override
    public void friendApplyEmail(Integer pccUserId, String email) {
        PccUser pccUser = pccUserService.findById(pccUserId);
        PccUser friendPccUser = pccUserService.findByEmail(email);

        PccNotice pccNotice = new PccNotice();
        pccNotice.setTime(new Date());
        pccNotice.setType("friend-apply");
        pccNotice.setApi("/pcc/user/friend/save/friend");
        pccNotice.setContent(JSONObject.toJSONString(pccUser));
        pccNotice.setPccUserId(friendPccUser.getId());

        pccNoticeService.save(pccNotice);

        webSocketServer.sendNotice(pccNotice);
    }

    @Override
    public boolean isFriend(Integer pccUserId, String email) {
        List<PccUserFriend> pccUserFriends = pccUserFriendMapper.findByFriendEmail(pccUserId, email);

        return pccUserFriends.size() == 0 ? false : true;
    }


    @Override
    public boolean isFriend(Integer pccUserId, Integer friendPccUserId) {
        List<PccUserFriend> pccUserFriends = pccUserFriendMapper.findByFriendId(pccUserId, friendPccUserId);

        return pccUserFriends.size() == 0 ? false : true;
    }

}
