package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccChatInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PccChatInfoMapper extends Mapper<PccChatInfo> {

    @Select("select * from pcc_chat_info " +
            "where " +
            "(send_user_id=#{sendUseId} and receive_user_id=#{receiveUserId}) " +
            "or" +
            "(send_user_id=#{receiveUserId} and receive_user_id=#{sendUseId})")
    List<PccChatInfo> friendChatInfo(@Param("sendUseId") Integer sendUserId
            ,@Param("receiveUserId") Integer receiveUserId);

    @Select("select count(id) from pcc_chat_info " +
            "where " +
            "receive_user_id=#{receiveUserId} and " +
            "is_received='否'")
    Integer count(@Param("receiveUserId")Integer receiveUserId);

    @Select("select count(id) from pcc_chat_info " +
            "where " +
            "send_user_id=#{sendUseId} and " +
            "receive_user_id=#{receiveUserId} and " +
            "is_received='否'")
    Integer countSendUserChatInfo(@Param("sendUserId") Integer sendUserId
            , @Param("receiveUserId") Integer receiveUserId);

    @Update("update pcc_chat_info " +
            "set is_received='是' " +
            "where send_user_id=#{sendUseId} and " +
            "receive_user_id=#{receiveUserId}")
    void receiveChatInfo(@Param("sendUserId") Integer sendUserId
            , @Param("receiveUserId") Integer receiveUserId);
}