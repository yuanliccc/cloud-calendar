package group.cc.df.service.impl;

import group.cc.df.dao.DfFormItemMapper;
import group.cc.df.model.DfFormItem;
import group.cc.df.service.DfFormItemService;
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
public class DfFormItemServiceImpl extends AbstractService<DfFormItem> implements DfFormItemService {
    @Resource
    private DfFormItemMapper dfFormItemMapper;

}
