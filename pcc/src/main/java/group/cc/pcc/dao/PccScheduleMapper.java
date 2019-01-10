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
            "ps.id AS id, " +
            "ps.content AS content, " +
            "ps.create_time AS creatTime, " +
            "ps.remind_time AS remindTime, " +
            "pu1.NAME AS createUserName, " +
            "pu1.id AS createUserId, " +
            "ps.deadline AS deadline, " +
            "psu.id AS psuId, " +
            "psu.pcc_user_id AS receiverId, " +
            "pu2.name AS receiverName, " +
            "psu.is_complete as isComplete, " +
            "psu.complete_date AS completeDate " +
            "FROM " +
            "pcc_schedule AS ps " +
            "LEFT JOIN pcc_schedule_user AS psu ON ps.id = psu.pcc_schedule_id " +
            "LEFT JOIN pcc_user AS pu1 ON pu1.id = ps.pcc_user_id " +
            "LEFT JOIN pcc_user AS pu2 ON pu2.id = psu.pcc_user_id " +
            "WHERE " +
            "ps.pcc_user_id = #{pccUserId} " +
            "OR psu.pcc_user_id = #{pccUserId} " +
            "ORDER BY " +
            "ps.create_time DESC")
    List<Map<String,Object>> relationList(@Param("pccUserId") Integer pccUserId);
}