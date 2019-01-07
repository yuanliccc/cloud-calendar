package group.cc.df.service.impl;

import group.cc.df.dao.DfFormFieldMapper;
import group.cc.df.model.DfFormField;
import group.cc.df.service.DfFormFieldService;
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
public class DfFormFieldServiceImpl extends AbstractService<DfFormField> implements DfFormFieldService {
    @Resource
    private DfFormFieldMapper dfFormFieldMapper;

}
