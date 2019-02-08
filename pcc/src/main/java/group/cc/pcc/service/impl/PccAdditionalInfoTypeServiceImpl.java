package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccAdditionalInfoTypeMapper;
import group.cc.pcc.model.PccAdditionalInfoType;
import group.cc.pcc.service.PccAdditionalInfoTypeService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author yuanli
 * @date 2019/01/15
 */
@Service
@Transactional
public class PccAdditionalInfoTypeServiceImpl extends AbstractService<PccAdditionalInfoType> implements PccAdditionalInfoTypeService {
    @Resource
    private PccAdditionalInfoTypeMapper pccAdditionalInfoTypeMapper;

    @Override
    public List<Map<String, Object>> list(Integer pccScheduleId) {
        return pccAdditionalInfoTypeMapper.list(pccScheduleId);
    }
}
