package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccScheduleTextMapper;
import group.cc.pcc.model.PccScheduleText;
import group.cc.pcc.service.PccScheduleTextService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author yuanli
 * @date 2019/01/15
 */
@Service
@Transactional
public class PccScheduleTextServiceImpl extends AbstractService<PccScheduleText> implements PccScheduleTextService {
    @Resource
    private PccScheduleTextMapper pccScheduleTextMapper;

}
