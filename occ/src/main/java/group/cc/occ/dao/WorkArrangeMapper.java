package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.WorkArrange;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WorkArrangeMapper extends Mapper<WorkArrange> {
    @Select("SELECT\n" +
            "\tWA.*\n" +
            "FROM\n" +
            "\tWORK_ARRANGE WA\n" +
            "LEFT JOIN USER_WORK_ARRANGE UWA ON UWA.WORKARRANGEID = WA.ID\n" +
            "WHERE\n" +
            "\tWA.ORGID = #{orgId}\n" +
            "AND UWA.USERID = #{userId} \n" +
            "AND ${key} LIKE #{value}")
    public List<WorkArrange> listByKey(@Param("key")String key, @Param("value")String value, @Param("orgId")Integer orgId, @Param("userId")Integer userId);

    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\tWORK_ARRANGE\n" +
            "WHERE\n" +
            "\tORGID = #{orgId}\n" +
            "AND ${key} LIKE #{value}")
    public List<WorkArrange> listByKeyForManager(@Param("key")String key, @Param("value")String value, @Param("orgId")Integer orgId);


    @Delete("DELETE FROM WORK_ARRANGE WHERE ID in(${ids})")
    public void deleteBatch(@Param("ids")String ids);

    @Insert("INSERT INTO USER_WORK_ARRANGE (USERID, WORKARRANGEID, ISREPEAT) VALUES(#{userId}, #{workId}, #{isRepeat})")
    public void saveUserForWork(@Param("workId")Integer workId, @Param("userId")Integer userId, @Param("isRepeat")String isRepeat);

    @Delete("DELETE FROM USER_WORK_ARRANGE WHERE WORKARRANGEID = #{workId}")
    public void deleteWorkUser(@Param("workId") Integer workId);

}