package group.cc.occ.service.impl;

import group.cc.occ.dao.OrgApplyMapper;
import group.cc.occ.model.OrgApply;
import group.cc.occ.service.OrgApplyService;
import java.util.List;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/05/30
 */
@Service
@Transactional
public class OrgApplyServiceImpl extends AbstractService<OrgApply> implements OrgApplyService {
    @Resource
    private OrgApplyMapper orgApplyMapper;

    @Override
    public List<OrgApply> listByKey(String key, String value, Integer orgId){
        value = "%" + value + "%";
        List<OrgApply> list = orgApplyMapper.listByKey(key, value, orgId);
        return list;
    }

    @Override
    public void deleteBatch(List<OrgApply> orgApplys) {
        StringBuffer orgApplySb = new StringBuffer();

        for (OrgApply e: orgApplys){
            orgApplySb.append(e.getId() + ",");
        }
        orgApplySb.deleteCharAt(orgApplySb.length() - 1);

        orgApplyMapper.deleteBatch(orgApplySb.toString());
    }
}
