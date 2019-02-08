package group.cc.occ.service.impl;

import group.cc.occ.dao.LastloginorgMapper;
import group.cc.occ.model.Lastloginorg;
import group.cc.occ.service.LastloginorgService;
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
public class LastloginorgServiceImpl extends AbstractService<Lastloginorg> implements LastloginorgService {
    @Resource
    private LastloginorgMapper lastloginorgMapper;

}
