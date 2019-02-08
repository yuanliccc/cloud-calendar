package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccScheduleUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface PccScheduleUserMapper extends Mapper<PccScheduleUser> {

    @Update("UPDATE " +
            "pcc_schedule_user AS psu " +
            "SET " +
            "psu.is_complete = '是', " +
            "psu.complete_date=#{date} " +
            "WHERE psu.pcc_schedule_id=#{pccScheduleId} " +
            "AND " +
            "psu.pcc_user_id=#{pccUserId} ")
    void complete(@Param("pccUserId") Integer pccUserId, @Param("pccScheduleId") Integer pccScheduleId, @Param("date") Date date);
}