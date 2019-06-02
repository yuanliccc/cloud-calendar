package group.cc.pcc.service;
import group.cc.pcc.model.PccNotice;
import group.cc.core.Service;

import java.util.List;


/**
 * @author yuanli
 * @date 2019/06/01
 */
public interface PccNoticeService extends Service<PccNotice> {

    List<PccNotice> notNoticeList(Integer pccUserId);

    List<PccNotice> lisUser(Integer pccUserId);

    List<PccNotice> listApply(Integer pccUserId);

    boolean isApply(Integer pccUserId, String email);
}
