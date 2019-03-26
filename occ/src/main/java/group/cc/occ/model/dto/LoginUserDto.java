package group.cc.occ.model.dto;

import group.cc.occ.model.Organization;
import group.cc.occ.model.Permission;
import group.cc.occ.model.Role;
import group.cc.occ.model.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/2/28.
 */
public class LoginUserDto implements Serializable{
    private User user;
    private Organization organization;
    private Role role;
    private List<String> permissions;

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString(){
        return "userId: " + this.getUser().getId()  + "  orgId: " + this.getOrganization().getId();
    }
}
