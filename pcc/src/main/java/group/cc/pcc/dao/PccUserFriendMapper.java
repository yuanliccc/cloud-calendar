package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccUserFriend;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PccUserFriendMapper extends Mapper<PccUserFriend> {

    @Update("Update pcc_user_friend set remark=#{remark} " +
            "where " +
            "pcc_user_id=#{pccUserId} and " +
            "friend_pcc_user_id=#{friendPccUserId} ")
    void remark(@Param("pccUserId") Integer pccUserId,
                @Param("friendPccUserId") Integer friendPccUserId,
                @Param("remark") String remark);

    @Delete("delete from pcc_user_friend where " +
            "(pcc_user_id=#{pccUserId} and friend_pcc_user_id=#{friendPccUserId}) or " +
            "(pcc_user_id=#{friendPccUserId} and friend_pcc_user_id=#{pccUserId})")
    void deleteByIdes(@Param("pccUserId")Integer pccUserId,@Param("friendPccUserId") Integer friendPccUserId);

    @Select("select puf.* from pcc_user_friend as puf " +
            "left join pcc_user as pu on pu.id=puf.friend_pcc_user_id " +
            "where " +
            "pu.email = #{email} and " +
            "puf.pcc_user_id=#{pccUserId}")
    List<PccUserFriend> findByFriendEmail(@Param("pccUserId") Integer pccUserId, @Param("email") String email);


    @Select("select puf.* from pcc_user_friend as puf " +
            "where " +
            "puf.friend_pcc_user_id = #{friendPccUserId} and " +
            "puf.pcc_user_id=#{pccUserId}")
    List<PccUserFriend> findByFriendId(@Param("pccUserId") Integer pccUserId, @Param("friendPccUserId") Integer friendPccUserId);
}