package group.cc.df.service.impl;

import group.cc.df.dao.DfDynamicFormMapper;
import group.cc.df.model.DfDynamicForm;
import group.cc.df.service.DfDynamicFormService;
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
public class DfDynamicFormServiceImpl extends AbstractService<DfDynamicForm> implements DfDynamicFormService {
    @Resource
    private DfDynamicFormMapper dfDynamicFormMapper;

}
