package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper extends Mapper<Role> {
    @Select("SELECT * FROM ROLE WHERE #{key} = #{value}")
    public Role queryBy(@Param("key")String key, @Param("value")String value);
}