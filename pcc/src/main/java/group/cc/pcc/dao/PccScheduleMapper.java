package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccSchedule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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
    List<Map<String,Object>> dayCount(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("pccUserId") Integer pccUserId);

    @Select("SELECT " +
            "* " +
            "FROM " +
            "pcc_schedule_view AS psv " +
            "WHERE " +
            "psv.createUserId = #{pccUserId} " +
            "OR  " +
            "FIND_IN_SET(#{pccUserId}, psv.receiverIds)")
    List<Map<String,Object>> relationList(@Param("pccUserId") Integer pccUserId);

    @Select("SELECT " +
            "* " +
            "FROM " +
            "pcc_schedule_view AS psv " +
            "WHERE " +
            "psv.createUserId = #{pccUserId}")
    List<Map<String, Object>> createList(@Param("pccUserId") Integer pccUserId);

    @Select("SELECT " +
            "* " +
            "FROM " +
            "pcc_schedule_view AS psv " +
            "WHERE " +
            "substring_index( " +
            "REVERSE( " +
            "substring_index( " +
            "psv.isCompletes, " +
            "',', " +
            "FIND_IN_SET(#{pccUserId}, psv.receiverIds) " +
            ") " +
            "), " +
            "',', " +
            "1 " +
            ") = '否'")
    List<Map<String, Object>> untreatedList(@Param("pccUserId") Integer pccUserId);

    @Select("SELECT " +
            "* " +
            "FROM " +
            "pcc_schedule_view AS psv " +
            "WHERE " +
            "substring_index( " +
            "REVERSE( " +
            "substring_index( " +
            "psv.isCompletes, " +
            "',', " +
            "FIND_IN_SET(#{pccUserId}, psv.receiverIds) " +
            ") " +
            "), " +
            "',', " +
            "1 " +
            ") = '是'")
    List<Map<String, Object>> treatedList(@Param("pccUserId") Integer pccUserId);
}