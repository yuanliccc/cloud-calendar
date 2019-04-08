package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

    @Select("SELECT P.* FROM PERMISSION P LEFT JOIN ROLE_PERMISSION RP ON RP.PERID = P.ID " +
            " WHERE RP.ROLEID = #{roleId}")
    public List<Permission> getAllPermissionByRoleId(@Param("roleId") Integer roleId);

    @Select("SELECT * FROM PERMISSION WHERE ID NOT IN(SELECT PERID FROM ROLE_PERMISSION " +
            " WHERE ROLEID = #{roleId})")
    public List<Permission> getOtherPermission(@Param("roleId") Integer roleId);

    @Insert("INSERT INTO ROLE_PERMISSION(ROLEID, PERID) VALUES(#{roleId}, #{perId})")
    public void addRolePermission(@Param("roleId")Integer roleId, @Param("perId")Integer perId);

    @Select("SELECT * FROM PERMISSION WHERE ${key} like #{value}")
    public List<Permission> listByKey(@Param("key")String key, @Param("value")String value);

    @Delete("DELETE FROM PERMISSION WHERE ID in(${perIds})")
    public void deleteBatch(@Param("perIds")String perIds);
}