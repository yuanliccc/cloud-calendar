package group.cc.df.service.impl;

import group.cc.df.dao.DfClonedDynamicFormMapper;
import group.cc.df.model.DfClonedDynamicForm;
import group.cc.df.service.DfClonedDynamicFormService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author gxd
 * @date 2019/04/12
 */
@Service
@Transactional
public class DfClonedDynamicFormServiceImpl extends AbstractService<DfClonedDynamicForm> implements DfClonedDynamicFormService {
    @Resource
    private DfClonedDynamicFormMapper dfClonedDynamicFormMapper;

}
