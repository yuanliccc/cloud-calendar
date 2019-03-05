package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends Mapper<Permission> {
    @Delete("DELETE FROM PERMISSION WHERE MODULEID = #{moduleId}")
    public void deleteByModuleId(@Param("moduleId")Integer moduleId);

    @Delete("DELETE FROM PERMISSION WHERE MODULEID in(${moduleIds})")
    public void deleteByModules(@Param("moduleIds")String moduleIds);

    @Select("SELECT P.* FROM PERMISSION P WHERE P.ID IN(SELECT PERID AS ID FROM" +
            " ROLE_PERMISSION WHERE ROLEID = ${roleId})")
    public List<Permission> findPerForRoleId(@Param("roleId")Integer roleId);

    @Delete("DELETE FROM ROLE_PERMISSION WHERE ROLEID = ${roleId}")
    public void deleteByRoleId(@Param("roleId") Integer roleId);
}