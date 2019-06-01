package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Role;
import group.cc.occ.model.dto.LoginUserDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    @Select("SELECT * FROM ROLE WHERE #{key} = #{value}")
    public Role queryBy(@Param("key")String key, @Param("value")String value);

    @Select("SELECT * FROM ROLE WHERE ORGID = #{orgId} and ${key} like #{value}")
    public List<Role> listByKey(@Param("key")String key, @Param("value")String value, @Param("orgId")Integer orgId);

    @Select("SELECT R.* FROM ROLE R LEFT JOIN USER_ROLE UR ON UR.ROLEID = R.ID WHERE UR.USERID = ${userId} AND R.ORGID = ${orgId}")
    public Role findRoleByUserIdAndOrgId(@Param("userId") Integer userId, @Param("orgId") Integer orgId);

    @Delete("DELETE FROM ROLE WHERE ID IN(${roles})")
    public void deleteBatch(@Param("roles") String roles);

    @Delete("DELETE FROM USER_ROLE WHERE ROLEID IN(${roles})")
    public void deleteUserRole(@Param("roles") String roles);

    @Delete("DELETE FROM ROLE_PERMISSION WHERE ROLEID IN(${roles})")
    public void deleteRolePermission(@Param("roles") String roles);

    @Select("SELECT * FROM ROLE WHERE ORGID = #{orgId}")
    public List<Role> findAllByLoginOrg(@Param("orgId")Integer orgId);

    @Select("SELECT UR.ID FROM USER_ROLE UR LEFT JOIN ROLE R ON R.ID = UR.ROLEID WHERE UR.USERID = #{userId} AND R.ORGID = #{orgId}")
    public Integer getUserRoleId(@Param("userId") Integer userId, @Param("orgId") Integer orgId);

    @Insert("INSERT INTO USER_ROLE (USERID, ROLEID) VALUES(#{userId}, #{roleId})")
    public void addUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Delete("DELETE FROM USER_ROLE WHERE ID = #{id}")
    public void deleteUserRoleById(@Param("id")Integer id);

    @Select("SELECT R.* FROM ROLE R LEFT JOIN USER_ROLE UR ON UR.ROLEID = R.ID WHERE UR.USERID = #{userId} AND R.ORGID = #{orgId}")
    public Role getRoleByUserId(@Param("userId")Integer userId, @Param("orgId")Integer orgId);

    @Select("SELECT * FROM ROLE WHERE ORGID = #{orgId} AND ROLEKEY LIKE '%Member'")
    public Role findOrgMemberRole(Integer orgId);

    @Select("DELETE FROM USER_ROLE WHERE USERID = #{userId} AND ROLEID = #{roleid}")
    public void deleteUserRoleByUserIdAndRoleId(@Param("userId") Integer userId, @Param("roleid") Integer roleId);

}