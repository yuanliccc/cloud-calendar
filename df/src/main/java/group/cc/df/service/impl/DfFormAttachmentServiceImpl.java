package group.cc.df.service.impl;

import group.cc.df.dao.DfFormAttachmentMapper;
import group.cc.df.model.DfFormAttachment;
import group.cc.df.service.DfFormAttachmentService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author gxd
 * @date 2019/01/10
 */
@Service
@Transactional
public class DfFormAttachmentServiceImpl extends AbstractService<DfFormAttachment> implements DfFormAttachmentService {
    @Resource
    private DfFormAttachmentMapper dfFormAttachmentMapper;

}
