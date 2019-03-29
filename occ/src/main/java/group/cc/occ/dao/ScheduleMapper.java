package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Schedule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScheduleMapper extends Mapper<Schedule> {

    @Select("SELECT * FROM SCHEDULE WHERE ORGID = #{orgId} OR(ORGID IN(SELECT ROOTORGID AS ID FROM ORGANIZATION WHERE ID = #{orgId} " +
            " UNION SELECT PARENTORGID AS ID FROM ORGANIZATION WHERE ID = #{orgId}) AND SUBORDINATECANSEEN = '是') AND ${key} like #{value}")
    public List<Schedule> listByKey(@Param("key") String key, @Param("value") String value, @Param("orgId") Integer orgId);

    @Delete("DELETE FROM SCHEDULE WHERE ID IN (${ids})")
    public void deleteBatch(@Param("ids") String schedules);
}