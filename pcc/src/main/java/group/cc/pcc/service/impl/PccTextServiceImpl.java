package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccTextMapper;
import group.cc.pcc.model.PccText;
import group.cc.pcc.service.PccTextService;
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
public class PccTextServiceImpl extends AbstractService<PccText> implements PccTextService {
    @Resource
    private PccTextMapper pccTextMapper;

}
