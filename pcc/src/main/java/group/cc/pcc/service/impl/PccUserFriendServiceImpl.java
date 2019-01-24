package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccUserFriendMapper;
import group.cc.pcc.model.PccUserFriend;
import group.cc.pcc.service.PccUserFriendService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author yuanli
 * @date 2019/01/10
 */
@Service
@Transactional
public class PccUserFriendServiceImpl extends AbstractService<PccUserFriend> implements PccUserFriendService {
    @Resource
    private PccUserFriendMapper pccUserFriendMapper;

}