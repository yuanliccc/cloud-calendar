package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccScheduleFileMapper;
import group.cc.pcc.model.PccScheduleFile;
import group.cc.pcc.service.PccScheduleFileService;
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
public class PccScheduleFileServiceImpl extends AbstractService<PccScheduleFile> implements PccScheduleFileService {
    @Resource
    private PccScheduleFileMapper pccScheduleFileMapper;

}
