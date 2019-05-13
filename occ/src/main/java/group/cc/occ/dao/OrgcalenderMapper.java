package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Orgcalender;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface OrgcalenderMapper extends Mapper<Orgcalender> {
    @Select("SELECT * FROM ORGCALENDER WHERE ORGID = #{orgId} OR(ORGID IN(SELECT ROOTORGID AS ID FROM organization WHERE ID = #{orgId}  AND SUBORDINATECANSEEN = '是'" +
            " UNION SELECT PARENTORGID AS ID FROM organization WHERE ID = #{orgId}) AND SUBORDINATECANSEEN = '是') AND ${key} like #{value}")
    public List<Orgcalender> listByKey(@Param("key") String key, @Param("value") String value, @Param("orgId") Integer orgId);

    @Delete("DELETE FROM ORGCALENDER WHERE ID IN (${ids})")
    public void deleteBatch(@Param("ids")String orgCalenders);

    @Select("SELECT * FROM orgcalender WHERE(ORGID = #{orgId} OR (ORGID IN (#{ids}) AND SUBORDINATECANSEEN = '是')) AND " +
            " ((isRepeat = '否' AND ((startTime >= DATE_FORMAT(#{dailyday}, '%Y-%m-01') AND startTime <= DATE_FORMAT(#{dailyday}, " +
            " '%Y-%m-31')) OR (startTime <= DATE_FORMAT(#{dailyday}, '%Y-%m-01') AND endtime >= DATE_FORMAT(#{dailyday}, '%Y-%m-01')))) " +
            " OR (ISREPEAT = '是' AND ((DATE_FORMAT(startTime, '%m-%d') >= DATE_FORMAT(#{dailyday}, '%m-01') AND DATE_FORMAT(startTime, " +
            " '%m-%d') <= DATE_FORMAT(#{dailyday}, '%m-31')) OR (DATE_FORMAT(startTime, '%m-%d') <= DATE_FORMAT(#{dailyday}, '%m-01') " +
            " AND DATE_FORMAT(endtime, '%m-%d') >= DATE_FORMAT(#{dailyday}, '%m-01')))))")
    public List<Orgcalender> findAllOrgCalenderThisMonth(@Param("orgId") Integer orgId, @Param("ids")String orgIds, @Param("dailyday") Date dayTime);

    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\torgcalender\n" +
            "WHERE\n" +
            "\t(\n" +
            "\t\tORGID = #{orgId}\n" +
            "\t\tOR (\n" +
            "\t\t\tORGID IN (${ids})\n" +
            "\t\t\tAND SUBORDINATECANSEEN = '是'\n" +
            "\t\t)\n" +
            "\t)\n" +
            "AND (\n" +
            "\t(\n" +
            "\t\tisRepeat = '否'\n" +
            "\t\tAND (\n" +
            "\t\t\tnow() >= startTime\n" +
            "\t\t\tAND now() <= endTime\n" +
            "\t\t)\n" +
            "\t)\n" +
            "\tOR (\n" +
            "\t\tISREPEAT = '是'\n" +
            "\t\tAND (\n" +
            "\t\t\tDATE_FORMAT(now(), '%m-%d') >= DATE_FORMAT(startTime, '%m-%d')\n" +
            "\t\t\tAND DATE_FORMAT(now(), '%m-%d') <= DATE_FORMAT(endTime, '%m-%d')\n" +
            "\t\t)\n" +
            "\t)\n" +
            ")")
    public List<Orgcalender> findAllOrgCalenderToday(@Param("orgId") Integer orgId, @Param("ids")String orgIds);

}