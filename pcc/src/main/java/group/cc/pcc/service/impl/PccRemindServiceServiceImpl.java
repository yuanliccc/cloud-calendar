package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccRemindServiceMapper;
import group.cc.pcc.model.PccRemindService;
import group.cc.pcc.service.PccRemindServiceService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author yuanli
 * @date 2018/12/23
 */
@Service
@Transactional
public class PccRemindServiceServiceImpl extends AbstractService<PccRemindService> implements PccRemindServiceService {
    @Resource
    private PccRemindServiceMapper pccRemindServiceMapper;

}
