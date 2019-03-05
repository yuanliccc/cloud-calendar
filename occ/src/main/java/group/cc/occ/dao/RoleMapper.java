package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    @Select("SELECT * FROM ROLE WHERE #{key} = #{value}")
    public Role queryBy(@Param("key")String key, @Param("value")String value);

    @Select("SELECT * FROM ROLE WHERE ${key} like #{value}")
    public List<Role> listByKey(@Param("key")String key, @Param("value")String value);

    @Select("SELECT R.* FROM ROLE R LEFT JOIN USER_ROLE UR ON UR.ROLEID = R.ID WHERE UR.ID = ${userId} AND R.ORGID = ${orgId}")
    public Role findRoleByUserIdAndOrgId(@Param("userId") Integer userId, @Param("orgId") Integer orgId);

    @Delete("DELETE FROM ROLE WHERE ID IN(${roles})")
    public void deleteBatch(@Param("roles") String roles);

    @Delete("DELETE FROM USER_ROLE WHERE ROLEID IN(${roles})")
    public void deleteUserRole(@Param("roles") String roles);

    @Delete("DELETE FROM ROLE_PERMISSION WHERE ROLEID IN(${roles})")
    public void deleteRolePermission(@Param("roles") String roles);
}