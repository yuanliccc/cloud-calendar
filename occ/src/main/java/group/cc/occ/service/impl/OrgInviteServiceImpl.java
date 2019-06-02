package group.cc.occ.service.impl;

import group.cc.occ.dao.OrgInviteMapper;
import group.cc.occ.model.OrgInvite;
import group.cc.occ.model.Organization;
import group.cc.occ.model.Role;
import group.cc.occ.service.OrgInviteService;
import java.util.List;
import group.cc.core.AbstractService;
import group.cc.occ.service.OrganizationService;
import group.cc.occ.service.RoleService;
import group.cc.occ.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/05/30
 */
@Service
@Transactional
public class OrgInviteServiceImpl extends AbstractService<OrgInvite> implements OrgInviteService {
    @Resource
    private OrgInviteMapper orgInviteMapper;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private RoleService roleService;

    @Resource
    private UserService userService;

    @Override
    public List<OrgInvite> listByKey(String key, String value, Integer userId){
        value = "%" + value + "%";
        List<OrgInvite> list = orgInviteMapper.listByKey(key, value, userId);
        return list;
    }

    @Override
    public void deleteBatch(List<OrgInvite> orgInvites) {
        StringBuffer orgInviteSb = new StringBuffer();

        for (OrgInvite e: orgInvites){
            orgInviteSb.append(e.getId() + ",");
        }
        orgInviteSb.deleteCharAt(orgInviteSb.length() - 1);

        orgInviteMapper.deleteBatch(orgInviteSb.toString());
    }

    /**
     * 新增机构邀请
     * */
    @Override
    public void addInvite(OrgInvite orgInvite)throws  Exception {
        List<OrgInvite> orgInvites = this.orgInviteMapper.isInviteExist(orgInvite.getApprovalorgid(), orgInvite.getUserid());

        if(orgInvites == null || orgInvites.size() == 0){
            this.save(orgInvite);
        }else {
            throw new Exception("你的机构已经邀请了该用户，该用户尚未反馈！");
        }
    }

    /**
     * 用户决定是否加入机构
     * */
    @Override
    public void approveInvite(OrgInvite orgInvite){

        if("同意".equals(orgInvite.getState())) {
            Role role = this.roleService.findOrgMemberRole(orgInvite.getApprovalorgid());
            this.userService.saveRole(orgInvite.getUserid(), role.getId());
        }

        this.update(orgInvite);
    }
}
