package group.cc.df.service.impl;

import group.cc.df.dao.DfFormAttachementMapper;
import group.cc.df.model.DfFormAttachement;
import group.cc.df.service.DfFormAttachementService;
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
public class DfFormAttachementServiceImpl extends AbstractService<DfFormAttachement> implements DfFormAttachementService {
    @Resource
    private DfFormAttachementMapper dfFormAttachementMapper;

}
