package group.cc.occ.service.impl;

import group.cc.occ.dao.NoticeListMapper;
import group.cc.occ.model.NoticeList;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.NoticeListService;

import java.util.Date;
import java.util.List;
import group.cc.core.AbstractService;
import group.cc.occ.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/05/29
 */
@Service
@Transactional
public class NoticeListServiceImpl extends AbstractService<NoticeList> implements NoticeListService {
    @Resource
    private NoticeListMapper noticeListMapper;

    @Resource
    private NoticeService noticeService;

    @Override
    public List<NoticeList> listByKey(String key, String value){
        value = "%" + value + "%";
        List<NoticeList> list = noticeListMapper.listByKey(key, value);
        return list;
    }

    @Override
    public void deleteBatch(List<NoticeList> noticeLists) {
        StringBuffer noticeListSb = new StringBuffer();

        for (NoticeList e: noticeLists){
            noticeListSb.append(e.getId() + ",");
        }
        noticeListSb.deleteCharAt(noticeListSb.length() - 1);

        noticeListMapper.deleteBatch(noticeListSb.toString());
    }

    @Override
    public void saveNoticeList(NoticeList noticeList, LoginUserDto login) {
        noticeList.setOrgid(login.getOrganization().getId());
        noticeList.setSubmittime( new Date());

        if("个人通知".equals(noticeList.getType())){
            Integer userId = noticeList.getSubmituserid();

            noticeList.setSubmituserid(login.getUser().getId());
            this.save(noticeList);

            Integer noticeListId = this.noticeListMapper.selectLastInsertId();
            noticeList.setId(noticeListId);
            this.noticeService.userNotice(noticeList, userId);
        }else if("机构通知".equals(noticeList.getType())){
            noticeList.setSubmituserid(login.getUser().getId());

            this.save(noticeList);

            Integer noticeListId = this.noticeListMapper.selectLastInsertId();
            noticeList.setId(noticeListId);
            this.noticeService.organizationNotice(noticeList);
        }
        else if("系统通知".equals(noticeList.getType())){
            noticeList.setSubmituserid(login.getUser().getId());

            this.save(noticeList);

            Integer noticeListId = this.noticeListMapper.selectLastInsertId();
            noticeList.setId(noticeListId);

            this.noticeService.systemNotice(noticeList);
        }

    }
}
