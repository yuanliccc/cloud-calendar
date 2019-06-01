package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.UserWorkArrange;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserWorkArrangeMapper extends Mapper<UserWorkArrange> {
    @Select("SELECT * FROM USER_WORK_ARRANGE WHERE ${key} like #{value}")
    public List<UserWorkArrange> listByKey(@Param("key")String key, @Param("value")String value);

    @Delete("DELETE FROM USER_WORK_ARRANGE WHERE ID in(${ids})")
    public void deleteBatch(@Param("ids")String ids);

    @Select("SELECT USERID FROM USER_WORK_ARRANGE WHERE WORKARRANGEID = #{workId}")
    public List<Integer> getWorkUser(@Param("workId") Integer workId);

    @Select("SELECT * FROM USER_WORK_ARRANGE WHERE WORKARRANGEID like #{workId}")
    public List<UserWorkArrange> findByWorkId(@Param("workId") Integer workId);

    @Update("UPDATE USER_WORK_ARRANGE SET ISCOMPLETE = '是' WHERE USERID = #{userId} AND WORKARRANGEID = #{workId} ")
    public void finish(@Param("workId")Integer workId, @Param("userId")Integer userId);

    @Update("UPDATE USER_WORK_ARRANGE SET ISCOMPLETE = '是', COMPLETEYEAR = #{completeYear} WHERE USERID = #{userId} AND WORKARRANGEID = #{workId} ")
    public void finishAndContinue(@Param("workId")Integer workId, @Param("userId")Integer userId, @Param("completeYear")String year);

    @Select("SELECT * FROM USER_WORK_ARRANGE WHERE USERID = #{userId} AND WORKARRANGEID = #{workId} ")
    public UserWorkArrange findByUserIdAndWorkId(@Param("workId")Integer workId, @Param("userId")Integer userId);

    @Select("SELECT\n" +
            "\t(\n" +
            "\t\t(\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\tCOUNT(*)\n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tuser_work_arrange\n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\tworkarrangeid = #{workId}\n" +
            "\t\t\tAND isComplete = '是'\n" +
            "\t\t) - (\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\tCOUNT(*)\n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tuser_work_arrange\n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\tworkarrangeid = #{workId}\n" +
            "\t\t)\n" +
            "\t)")
    public Integer getSubNum(@Param("workId")Integer workId);

    @Select("SELECT ID FROM USER_WORK_ARRANGE WHERE WORKARRANGEID = #{workId}")
    public List<Integer> getWorkUserIdByWorkId(@Param("workId") Integer workId);

    @Update("UPDATE USER_WORK_ARRANGE SET ISREPEAT = #{isRepeat} WHERE ID = #{id}")
    public void updateState(@Param("id")Integer id, @Param("isRepeat")String isRepeat);

}