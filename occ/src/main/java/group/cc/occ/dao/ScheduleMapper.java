package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Schedule;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface ScheduleMapper extends Mapper<Schedule> {

    @Select("SELECT * FROM SCHEDULE WHERE ORGID = #{orgId} OR(ORGID IN(SELECT ROOTORGID AS ID FROM ORGANIZATION WHERE ID = #{orgId} " +
            " UNION SELECT PARENTORGID AS ID FROM ORGANIZATION WHERE ID = #{orgId}) AND SUBORDINATECANSEEN = '是') AND ${key} like #{value}")
    public List<Schedule> listByKey(@Param("key") String key, @Param("value") String value, @Param("orgId") Integer orgId);

    @Delete("DELETE FROM SCHEDULE WHERE ID IN (${ids})")
    public void deleteBatch(@Param("ids") String schedules);

    @Update("UPDATE SCHEDULE SET STATE = '已撤销' WHERE ID = #{scheduleId}")
    public void revoke(@Param("scheduleId") Integer scheduleId);

    @Select("SELECT * FROM SCHEDULE WHERE STATE = '执行' AND (ORGID = #{orgId} OR (ORGID IN (${parentOrgIds}) AND " +
            " SUBORDINATECANSEEN = '是' )) AND ((DATE_FORMAT(endTime, '%Y-%m-%d') >= DATE_FORMAT(#{dailyDay}, '%Y-%m-01') AND " +
            " DATE_FORMAT(startTime,'%Y-%m-%d') <= DATE_FORMAT(#{dailyDay},'%Y-%m-01'))OR (DATE_FORMAT(startTime, '%Y-%m-%d') " +
            " >= DATE_FORMAT(#{dailyDay}, '%Y-%m-01') AND DATE_FORMAT(startTime, '%Y-%m-%d') <= DATE_FORMAT(#{dailyDay}, '%Y-%m-31')))")
    public List<Schedule> findAllScheduleThisMonth(@Param("orgId")Integer orgId, @Param("parentOrgIds")String parentOrgIds, @Param("dailyDay")Date dayTime);

    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\tSCHEDULE\n" +
            "WHERE\n" +
            "\tSTATE = '执行'\n" +
            "AND (\n" +
            "\tORGID = #{orgId}\n" +
            "\tOR (\n" +
            "\t\tORGID IN (${parentOrgIds})\n" +
            "\t\tAND SUBORDINATECANSEEN = '是'\n" +
            "\t)\n" +
            ")\n" +
            "AND now() >= startTime\n" +
            "AND now() <= endTime")
    public List<Schedule> findAllScheduleToday(@Param("orgId")Integer orgId, @Param("parentOrgIds")String parentOrgIds);
}