package group.cc.occ.service;
import group.cc.occ.model.OrgInvite;
import java.util.List;
import group.cc.core.Service;


/**
 * @author wangyuming
 * @date 2019/05/30
 */
public interface OrgInviteService extends Service<OrgInvite> {
    public List<OrgInvite> listByKey(String key, String value, Integer userId);
    public void deleteBatch(List<OrgInvite> orgInvites);

    public void addInvite(OrgInvite orgInvite) throws  Exception;

    public void approveInvite(OrgInvite orgInvite);
}
