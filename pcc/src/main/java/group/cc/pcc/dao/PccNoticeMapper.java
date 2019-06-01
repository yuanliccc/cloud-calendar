package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccNotice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PccNoticeMapper extends Mapper<PccNotice> {

    @Select("select * from pcc_notice where pcc_user_id=#{pccUserId} and " +
            "is_remind = '否'")
    List<PccNotice> notNoticeList(@Param("pccUserId") Integer pccUserId);

    @Select("select * from pcc_notice where pcc_user_id=#{pccUserId}")
    List<PccNotice> lisUser(@Param("pccUserId") Integer pccUserId);


    @Select("select pn.* " +
            "from pcc_notice as pn " +
            "left join pcc_user_friend as puf " +
            "on " +
            "(" +
            "puf.pcc_user_id = pn.pcc_user_id and " +
            "pn.content -> '$.id' = puf.friend_pcc_user_id " +
            ") " +
            "or " +
            "(" +
            "puf.pcc_user_id = pn.content -> '$.id' and " +
            "pn.pcc_user_id = puf.friend_pcc_user_id " +
            ") " +
            "where " +
            "puf.id is null and " +
            "pn.type='friend-apply' and " +
            "pn.pcc_user_id=#{pccUserId}")
    List<PccNotice> listApply(@Param("pccUserId") Integer pccUserId);

    @Select("select pn.* from pcc_notice as pn " +
            "left join pcc_user as pu1 " +
            "on pu1.id=pn.content -> '$.id' " +
            "left join pcc_user as pu2 " +
            "on pu2.id=pn.pcc_user_id " +
            "where " +
            "pu2.email=#{email} and " +
            "pu1.id=#{pccUserId}")
    List<PccNotice> applyListEmail(@Param("pccUserId")Integer pccUserId, @Param("email") String email);
}