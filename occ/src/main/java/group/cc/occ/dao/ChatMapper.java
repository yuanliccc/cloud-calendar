package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Chat;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChatMapper extends Mapper<Chat> {
    @Select("${sql}")
    public List<Chat> findBySql(@Param("sql") String Sql);
}