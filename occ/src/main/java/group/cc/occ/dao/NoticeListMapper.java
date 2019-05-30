package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.NoticeList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeListMapper extends Mapper<NoticeList> {
    @Select("SELECT * FROM NOTICE_LIST WHERE ${key} like #{value} ORDER BY STARTTIME DESC")
    public List<NoticeList> listByKey(@Param("key")String key, @Param("value")String value);

    @Delete("DELETE FROM NOTICE_LIST WHERE ID in(${noticesId})")
    public void deleteBatch(@Param("noticesId")String noticesId);

    @Select("select last_insert_id()")
    public Integer selectLastInsertId();
}