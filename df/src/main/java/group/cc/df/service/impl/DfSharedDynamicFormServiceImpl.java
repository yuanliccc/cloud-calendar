package group.cc.df.service.impl;

import group.cc.df.dao.DfSharedDynamicFormMapper;
import group.cc.df.model.DfSharedDynamicForm;
import group.cc.df.service.DfSharedDynamicFormService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author gxd
 * @date 2019/04/09
 */
@Service
@Transactional
public class DfSharedDynamicFormServiceImpl extends AbstractService<DfSharedDynamicForm> implements DfSharedDynamicFormService {
    @Resource
    private DfSharedDynamicFormMapper dfSharedDynamicFormMapper;

}
