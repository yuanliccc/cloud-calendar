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
}