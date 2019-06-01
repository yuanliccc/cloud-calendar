package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccTask;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface PccTaskMapper extends Mapper<PccTask> {

    @Select("select * from pcc_task " +
            "where " +
            "date_format(time, '%Y-%m-%d')=#{day} " +
            "and is_delete='否'")
    List<PccTask> listDay(@Param("day") String day);

    @Select("select " +
            "pt.*, " +
            "pu.id as pcc_user_id, " +
            "pu.name as user_name, " +
            "pu.email as email " +
            "from pcc_task as pt " +
            "left join pcc_user as pu " +
            "on pt.pcc_user_id=pu.id " +
            "where " +
            "pt.time <= from_unixtime(unix_timestamp(now()) + 20) and " +
            "pt.is_delete = '否' and " +
            "pt.is_remind = '否'")
    List<Map<String, Object>> willDeadList();

    @Select("select * from pcc_task " +
            "where " +
            "date_format(time, '%Y-%m-%d')=#{day} " +
            "and is_delete='否' and " +
            "pcc_user_id=#{pccUserId}")
    List<PccTask> listDayUser(@Param("day")String day, @Param("pccUserId") Integer pccUserId);

    @Update("update pcc_task set is_delete='是' where id=#{id}")
    void deleteImitate(@Param("id") Integer id);

    @Select("select " +
            "count(id) as count, " +
            "date_format(time, '%Y-%m-%d') as day " +
            "from pcc_task  " +
            "where date_format(time, '%Y-%m-%d') " +
            "between #{startDay} and #{endDay} and " +
            "is_delete='否' and " +
            "pcc_user_id=#{pccUserId} " +
            "group by date_format(time, '%Y-%m-%d') " +
            "order by date_format(time, '%Y-%m-%d')")
    List<Map<String, Object>> counts(@Param("pccUserId") Integer pccUserId,
                                     @Param("startDay") String startDay,
                                     @Param("endDay") String endDay);
}