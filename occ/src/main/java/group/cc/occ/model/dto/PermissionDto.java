package group.cc.occ.model.dto;

import group.cc.occ.model.Permission;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/3/12.
 */
public class PermissionDto implements Serializable{
    private Integer roleId;
    private List<Integer> permissions;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Integer> permissions) {
        this.permissions = permissions;
    }
}
