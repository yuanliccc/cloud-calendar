package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccScheduleAdditionalTypeMapper;
import group.cc.pcc.model.PccScheduleAdditionalType;
import group.cc.pcc.service.PccScheduleAdditionalTypeService;
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
public class PccScheduleAdditionalTypeServiceImpl extends AbstractService<PccScheduleAdditionalType> implements PccScheduleAdditionalTypeService {
    @Resource
    private PccScheduleAdditionalTypeMapper pccScheduleAdditionalTypeMapper;

}
