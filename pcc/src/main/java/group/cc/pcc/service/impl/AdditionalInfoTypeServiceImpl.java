package group.cc.pcc.service.impl;

import group.cc.pcc.dao.AdditionalInfoTypeMapper;
import group.cc.pcc.model.AdditionalInfoType;
import group.cc.pcc.service.AdditionalInfoTypeService;
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
public class AdditionalInfoTypeServiceImpl extends AbstractService<AdditionalInfoType> implements AdditionalInfoTypeService {
    @Resource
    private AdditionalInfoTypeMapper additionalInfoTypeMapper;

}
