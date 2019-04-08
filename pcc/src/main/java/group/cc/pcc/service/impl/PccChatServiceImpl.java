package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccChatMapper;
import group.cc.pcc.model.PccChat;
import group.cc.pcc.service.PccChatService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author yuanli
 * @date 2019/04/08
 */
@Service
@Transactional
public class PccChatServiceImpl extends AbstractService<PccChat> implements PccChatService {
    @Resource
    private PccChatMapper pccChatMapper;

}
