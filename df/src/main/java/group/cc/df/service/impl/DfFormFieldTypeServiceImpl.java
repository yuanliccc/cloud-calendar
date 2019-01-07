package group.cc.df.service.impl;

import group.cc.df.dao.DfFormFieldTypeMapper;
import group.cc.df.model.DfFormFieldType;
import group.cc.df.service.DfFormFieldTypeService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author gxd
 * @date 2018/12/07
 */
@Service
@Transactional
public class DfFormFieldTypeServiceImpl extends AbstractService<DfFormFieldType> implements DfFormFieldTypeService {
    @Resource
    private DfFormFieldTypeMapper dfFormFieldTypeMapper;

}
