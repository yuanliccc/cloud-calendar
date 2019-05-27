package group.cc.occ.service.impl;

import group.cc.occ.dao.OrgcalenderMapper;
import group.cc.occ.model.Orgcalender;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.OrgcalenderService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/03/28
 */
@Service
@Transactional
public class OrgcalenderServiceImpl extends AbstractService<Orgcalender> implements OrgcalenderService {
    @Resource
    private OrgcalenderMapper orgcalenderMapper;

    @Override
    public List<Orgcalender> listByKey(String key, String value, LoginUserDto login){
        value = "%" + value + "%";
        List<Orgcalender> list = orgcalenderMapper.listByKey(key, value, login.getOrganization().getId());
        return list;
    }

    @Override
    public void deleteBatch(List<Orgcalender> orgcalenders) {
        StringBuffer orgcalenderSb = new StringBuffer();

        for (Orgcalender e: orgcalenders){
            orgcalenderSb.append(e.getId() + ",");
        }
        orgcalenderSb.deleteCharAt(orgcalenderSb.length() - 1);

        orgcalenderMapper.deleteBatch(orgcalenderSb.toString());
    }

    @Override
    public List<Orgcalender> findAllByLoginOrgId(LoginUserDto login) {
        List<Orgcalender> list = orgcalenderMapper.listByKey("title", "%%", login.getOrganization().getId());
        return list;
    }

    @Override
    public List<Orgcalender> findAllOrgCalenderThisMonth(LoginUserDto login, Date dayTime) {
        String orgIds = login.getOrganization().getRootorgid() + "," + login.getOrganization().getParentorgid();
        List<Orgcalender> list = this.orgcalenderMapper.findAllOrgCalenderThisMonth(login.getOrganization().getId(), orgIds, dayTime);
        return list;
    }

    @Override
    public List<Orgcalender> findAllOrgCalenderToday(LoginUserDto login) {
        String orgIds = login.getOrganization().getRootorgid() + "," + login.getOrganization().getParentorgid();
        List<Orgcalender> list = this.orgcalenderMapper.findAllOrgCalenderToday(login.getOrganization().getId(), orgIds);
        return list;
    }
}
