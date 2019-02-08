package group.cc.occ.service.impl;

import group.cc.occ.dao.MemorandumMapper;
import group.cc.occ.model.Memorandum;
import group.cc.occ.service.MemorandumService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/01/02
 */
@Service
@Transactional
public class MemorandumServiceImpl extends AbstractService<Memorandum> implements MemorandumService {
    @Resource
    private MemorandumMapper memorandumMapper;

}
