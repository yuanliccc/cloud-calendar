package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@org.apache.ibatis.annotations.Mapper
public interface PccUserMapper extends Mapper<PccUser> {

    @Select("select " +
            "* " +
            "from pcc_user as pu " +
            "WHERE " +
            "pu.id in " +
            "(" +
            "select " +
            "puf.friend_pcc_user_id " +
            "from " +
            "pcc_user_friend AS puf " +
            "WHERE " +
            "puf.pcc_user_id=#{pccUserId}" +
            ")")
    List<PccUser> friends(@Param("pccUserId") Integer pccUserId);

    @Select("SELECT " +
            "puf.friend_pcc_user_id AS friendId, " +
            "puf.remark AS friendRemark, " +
            "pu.name AS friendName, " +
            "pu.sex AS friendSex, " +
            "pu.email AS friendEmail, " +
            "pu.phone AS friendPhone " +
            "FROM " +
            "pcc_user_friend AS puf " +
            "LEFT JOIN " +
            "pcc_user AS pu " +
            "ON " +
            "pu.id = puf.friend_pcc_user_id " +
            "WHERE " +
            "puf.pcc_user_id = 1")
    List<Map<String,Object>> friendsDetail(Integer pccUserId);
}