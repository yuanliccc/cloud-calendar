package group.cc.occ.model.dto;

import group.cc.occ.model.Organization;
import group.cc.occ.model.Role;
import group.cc.occ.model.User;

/**
 * Created by Administrator on 2019/2/28.
 */
public class LoginUserDto {
    private User user;
    private Organization organization;
    private Role role;

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
}
