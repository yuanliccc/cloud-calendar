/*
 * Copyright (c) 2019 YuanLi. All rights reserved.
 */

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

    @Select("SELECT " +
            "psft.*, pu.name AS username, " +
            "pu.email AS email, " +
            "pu.phone AS phone, " +
            "pu.sex AS sex, " +
            "psu.complete_date AS completeDate " +
            "FROM " +
            "pcc_schedule_file_text_view AS psft " +
            "LEFT JOIN " +
            "pcc_user AS pu " +
            "ON " +
            "pu.id = psft.userId " +
            "LEFT JOIN " +
            "pcc_schedule_user AS psu " +
            "ON " +
            "psu.pcc_user_id=psft.userId AND psu.pcc_schedule_id=psft.scheduleId " +
            "WHERE " +
            "psft.scheduleId = #{pccScheduleId}")
    List<Map<String,Object>> additionalInfoList(@Param("pccScheduleId") Integer pccScheduleId);

    @Select("SELECT " +
            "( " +
            "SELECT " +
            "count(*) " +
            "FROM " +
            "pcc_schedule_view AS psv " +
            "WHERE " +
            "psv.createUserId = #{pccUserId} " +
            "OR FIND_IN_SET(#{pccUserId}, psv.receiverIds) " +
            ") AS relationCount, " +
            "( " +
            "SELECT " +
            "COUNT(*) " +
            "FROM " +
            "pcc_schedule_view AS psv " +
            "WHERE " +
            "psv.createUserId = #{pccUserId} " +
            ") AS createCount, " +
            "( " +
            "SELECT " +
            "COUNT(*) " +
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
            ") = '是' " +
            ") AS treatedCount, " +
            "( " +
            "SELECT " +
            "COUNT(*) " +
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
            ") = '否' " +
            ") AS unTreatedCount, " +
            "( " +
            "SELECT " +
            "COUNT(*) " +
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
            ") = '是' " +
            "AND REVERSE( " +
            "substring_index( " +
            "REVERSE( " +
            "substring_index( " +
            "psv.completeDates, " +
            "',', " +
            "FIND_IN_SET(#{pccUserId}, psv.receiverIds) " +
            ") " +
            "), " +
            "',', " +
            "1 " +
            ") " +
            ") > psv.deadline " +
            ") AS treatedOutTimeCount, " +
            "( " +
            "SELECT " +
            "COUNT(*) " +
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
            ") = '否' " +
            "AND psv.deadline < NOW() " +
            ") AS unTreatedOutTimeCount")
    Map<String,Object> historyCount(@Param("pccUserId") Integer pccUserId);

    @Select("SELECT " +
            "COUNT(psu.pcc_user_id) AS count, " +
            "pu.name AS name " +
            "FROM " +
            "pcc_schedule_user AS psu " +
            "LEFT JOIN pcc_schedule AS ps ON ps.id = psu.pcc_schedule_id " +
            "LEFT JOIN pcc_user AS pu ON psu.pcc_user_id = pu.id " +
            "WHERE " +
            "ps.pcc_user_id = #{pccUserId} " +
            "GROUP BY " +
            "psu.pcc_user_id")
    List<Map<String,Object>> historyAssignCount(@Param("pccUserId") Integer pccUserId);

    @Select("SELECT " +
            "COUNT(ps.pcc_user_id) AS count, " +
            "pu.name AS name " +
            "FROM " +
            "pcc_schedule_user AS psu " +
            "LEFT JOIN pcc_schedule AS ps ON ps.id = psu.pcc_schedule_id " +
            "LEFT JOIN pcc_user AS pu ON ps.pcc_user_id = pu.id " +
            "WHERE " +
            "psu.pcc_user_id = #{pccUserId} " +
            "GROUP BY " +
            "ps.pcc_user_id")
    List<Map<String,Object>> historyAssignedCount(@Param("pccUserId") Integer pccUserId);

    @Select("( " +
            "SELECT " +
            "count(*) AS count, " +
            "DATE_FORMAT(s.create_time, '%Y-%m-%d') AS time " +
            "FROM " +
            "pcc_schedule AS s " +
            "WHERE " +
            "s.pcc_user_id = #{pccUserId} " +
            "AND DATE_FORMAT(s.create_time, '%Y%m%d') >= #{startDate} " +
            " " +
            "AND DATE_FORMAT(s.create_time, '%Y%m%d') <= #{endDate} " +
            "GROUP BY " +
            "DATE_FORMAT(s.create_time, '%Y-%m-%d') " +
            ") " +
            "UNION ALL " +
            "(SELECT 0 AS count, '' AS time) " +
            "UNION ALL " +
            "( " +
            "SELECT " +
            "count(*) AS count, " +
            "DATE_FORMAT(psv.createTime, '%Y-%m-%d') AS time " +
            "FROM " +
            "pcc_schedule_view AS psv " +
            "WHERE " +
            "FIND_IN_SET(#{pccUserId}, psv.receiverIds) " +
            "AND DATE_FORMAT(psv.createTime, '%Y%m%d') >= #{startDate} " +
            "AND DATE_FORMAT(psv.createTime, '%Y%m%d') <= #{endDate} " +
            "GROUP BY " +
            "DATE_FORMAT(psv.createTime, '%Y-%m-%d') " +
            ") " +
            "UNION ALL " +
            "(SELECT 0 AS count, '' AS time) " +
            "UNION ALL " +
            "( " +
            "SELECT " +
            "count(*) AS count, " +
            "DATE_FORMAT( " +
            "REVERSE( " +
            "substring_index( " +
            "REVERSE( " +
            "substring_index( " +
            "psv.completeDates, " +
            "',', " +
            "FIND_IN_SET(#{pccUserId}, psv.receiverIds) " +
            ") " +
            "), " +
            "',', " +
            "1 " +
            ") " +
            "), " +
            "'%Y-%m-%d' " +
            ") AS time " +
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
            ") = '是' " +
            "AND DATE_FORMAT( " +
            "REVERSE( " +
            "substring_index( " +
            "REVERSE( " +
            "substring_index( " +
            "psv.completeDates, " +
            "',', " +
            "FIND_IN_SET(#{pccUserId}, psv.receiverIds) " +
            ") " +
            "), " +
            "',', " +
            "1 " +
            ") " +
            "), " +
            "'%Y%m%d' " +
            ") >= #{startDate} " +
            "AND DATE_FORMAT( " +
            "REVERSE( " +
            "substring_index( " +
            "REVERSE( " +
            "substring_index( " +
            "psv.completeDates, " +
            "',', " +
            "FIND_IN_SET(#{pccUserId}, psv.receiverIds) " +
            ") " +
            "), " +
            "',', " +
            "1 " +
            ") " +
            "), " +
            "'%Y%m%d' " +
            ") <= #{endDate} " +
            "GROUP BY " +
            "DATE_FORMAT( " +
            "REVERSE( " +
            "substring_index( " +
            "REVERSE( " +
            "substring_index( " +
            "psv.completeDates, " +
            "',', " +
            "FIND_IN_SET(#{pccUserId}, psv.receiverIds) " +
            ") " +
            "), " +
            "',', " +
            "1 " +
            ") " +
            "), " +
            "'%Y-%m-%d' " +
            ") " +
            ")")
    List<Map<String, Object>> counts(@Param("pccUserId") Integer pccUserId,
                                     @Param("startDate") String startDate,
                                     @Param("endDate") String endDate);


    @Select("select " +
            "ps.*," +
            "pu.name," +
            "pu.email," +
            "psu.id as psu_id " +
            "from pcc_schedule as ps " +
            "left join pcc_schedule_user as psu on psu.pcc_schedule_id=ps.id " +
            "left join pcc_user as pu on pu.id=psu.pcc_user_id " +
            "where " +
            "(psu.is_dead_remind = '否' or psu.is_dead_remind is null) and " +
            "psu.is_complete='否' and " +
            "ps.deadline <= from_unixtime(unix_timestamp(now()) + #{between_mils} + 28800)")
    List<Map<String, Object>> willDeadSchedule(@Param("between_mils") Long mils);

}