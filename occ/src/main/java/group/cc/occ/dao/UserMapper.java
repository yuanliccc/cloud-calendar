package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Role;
import group.cc.occ.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {
    @Select("SELECT * FROM USER WHERE ACCOUNT = #{account}" )
    List<Map<String,Object>> getUserByAccount(@Param("account") String account);

    @Insert("INSERT INTO USER_ROLE (id, userId, roleId) VALUES(0 ,${userId}, ${roleId})" )
    public void saveRole(@Param("userId")Integer userId, @Param("roleId")Integer roleId);

    @Select("SELECT U.* FROM USER U LEFT JOIN USER_ROLE UR ON UR.USERID = U.ID LEFT JOIN ROLE R ON R.ID = UR.ROLEID WHERE " +
            " R.ORGID = #{orgId} AND U.${key} like #{value}")
    public List<User> listByKey(@Param("key")String key, @Param("value")String value, @Param("orgId")Integer orgId);

    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\tUSER\n" +
            "WHERE\n" +
            "\tID NOT IN (\n" +
            "\t\tSELECT\n" +
            "\t\t\tU.ID\n" +
            "\t\tFROM\n" +
            "\t\t\tUSER U\n" +
            "\t\tLEFT JOIN USER_ROLE UR ON UR.USERID = U.ID\n" +
            "\t\tLEFT JOIN ROLE R ON R.ID = UR.ROLEID\n" +
            "\t\tWHERE\n" +
            "\t\t\tR.ORGID = #{orgId}) AND (NAME LIKE #{value} OR ACCOUNT LIKE #{value})")
    public List<User> findUserByIdOrNameNotLoginOrg(@Param("value")String value, @Param("orgId")Integer orgId);

    @Select("SELECT U.* FROM USER U LEFT JOIN USER_ROLE UR ON UR.USERID = U.ID LEFT JOIN ROLE R ON R.ID = UR.ROLEID WHERE R.ORGID = #{orgId}")
    public List<User> getUserByLoginOrgId(@Param("orgId")Integer orgId);

}