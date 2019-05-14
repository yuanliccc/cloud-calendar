package group.cc.df.service.impl;

import group.cc.df.dao.DfCollectFormEditApplyMapper;
import group.cc.df.model.DfCollectFormEditApply;
import group.cc.df.service.DfCollectFormEditApplyService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author gxd
 * @date 2019/05/14
 */
@Service
@Transactional
public class DfCollectFormEditApplyServiceImpl extends AbstractService<DfCollectFormEditApply> implements DfCollectFormEditApplyService {
    @Resource
    private DfCollectFormEditApplyMapper dfCollectFormEditApplyMapper;

}
