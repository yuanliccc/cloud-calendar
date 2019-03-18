package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeMapper extends Mapper<Notice> {
    @Select("SELECT * FROM NOTICE WHERE ${key} like #{value} AND USERID = #{userId}")
    public List<Notice> listByKey(@Param("key")String key, @Param("value")String value, @Param("userId")Integer userId);

    @Delete("DELETE FROM NOTICE WHERE ID in(${noticesId})")
    public void deleteBatch(@Param("noticesId")String noticesId);
}