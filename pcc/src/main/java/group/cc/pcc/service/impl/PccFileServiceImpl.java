package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccFileMapper;
import group.cc.pcc.model.PccFile;
import group.cc.pcc.service.PccFileService;
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
public class PccFileServiceImpl extends AbstractService<PccFile> implements PccFileService {
    @Resource
    private PccFileMapper pccFileMapper;

}
