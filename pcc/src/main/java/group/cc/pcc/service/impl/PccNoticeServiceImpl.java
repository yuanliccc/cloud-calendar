/*
 * Copyright (c) 2019 YuanLi. All rights reserved.
 */

package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccNoticeMapper;
import group.cc.pcc.model.PccNotice;
import group.cc.pcc.service.PccNoticeService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author yuanli
 * @date 2019/06/01
 */
@Service
@Transactional
public class PccNoticeServiceImpl extends AbstractService<PccNotice> implements PccNoticeService {
    @Resource
    private PccNoticeMapper pccNoticeMapper;

    @Override
    public List<PccNotice> notNoticeList(Integer pccUserId) {
        return pccNoticeMapper.notNoticeList(pccUserId);
    }

    @Override
    public List<PccNotice> lisUser(Integer pccUserId) {
        return pccNoticeMapper.lisUser(pccUserId);
    }

    @Override
    public List<PccNotice> listApply(Integer pccUserId) {
        return pccNoticeMapper.listApply(pccUserId);
    }

    @Override
    public boolean isApply(Integer pccUserId, String email) {

        List<PccNotice> pccNotices = pccNoticeMapper.applyListEmail(pccUserId, email);
        return pccNotices.size() == 0 ? false : true;
    }
}
