package group.cc.occ.service;
import group.cc.occ.model.OrgApply;
import java.util.List;
import group.cc.core.Service;


/**
 * @author wangyuming
 * @date 2019/05/30
 */
public interface OrgApplyService extends Service<OrgApply> {
    public List<OrgApply> listByKey(String key, String value, Integer orgId);
    public void deleteBatch(List<OrgApply> orgApplys);
}
