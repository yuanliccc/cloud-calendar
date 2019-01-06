package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccSchedule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface PccScheduleMapper extends Mapper<PccSchedule> {

    @Select("select " +
            "count(*) as count, " +
            "DATE_FORMAT(s.create_time,'%Y-%m-%d') as create_time " +
            "from pcc_schedule as s " +
            "where " +
            "s.pcc_user_id=#{pccUserId} " +
            "AND " +
            "DATE_FORMAT(s.create_time,'%Y%m%d') >= #{startDate} " +
            "AND " +
            "DATE_FORMAT(s.create_time,'%Y%m%d') <= #{endDate} " +
            "GROUP BY DATE_FORMAT(s.create_time,'%Y-%m-%d')")
    List<Map<String,Object>> dayCount(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("pccUserId") Integer pccUserId);
}