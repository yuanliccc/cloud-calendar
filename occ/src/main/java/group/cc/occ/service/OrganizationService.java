package group.cc.occ.service;
import com.sun.org.apache.xpath.internal.operations.Bool;
import group.cc.occ.model.Organization;
import group.cc.core.Service;
import group.cc.occ.model.dto.LoginUserDto;

import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
public interface OrganizationService extends Service<Organization> {
    public List<Organization> listByKey(String key, String value, LoginUserDto loginUserDto);

    public void addOrg(Organization organization);

    public void deleteOrg(Integer orgId);

    public void updateOrg(Organization organization);

    public List<Organization> getAllLoginUserOrg(LoginUserDto loginUserDto);

    public List<Organization> getAllChildOrg(Integer orgId);

    public Boolean hasChildOrg(Integer orgId);

    public void orgApply(Integer applyId, Integer orgManagerId);
}
