package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

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
            "puf.pcc_user_id = #{pccUserId}")
    List<Map<String,Object>> friendsDetail(@Param("pccUserId") Integer pccUserId);

    @Select("SELECT " +
            "pu.*, pf.url AS url, " +
            "pf.url_type AS urlType " +
            "FROM " +
            "pcc_user AS pu " +
            "LEFT JOIN " +
            "pcc_file AS pf " +
            "ON " +
            "pu.pcc_file_id = pf.id " +
            "WHERE pu.id=#{id}")
    Map<String,Object> detail(Integer id);

    @Update("Update " +
            "pcc_user AS pu " +
            "set " +
            "pu.pcc_file_id=#{pccFileId} " +
            "where " +
            "pu.id=#{pccUserId}")
    void updateImg(@Param("pccUserId") Integer pccUserId, @Param("pccFileId") Integer pccFileId);

    @Update("Update " +
            "pcc_user AS pu " +
            "set " +
            "pu.password=#{password} " +
            "where " +
            "pu.id=#{pccUserId}")
    void updatePassword(Integer pccUserId, String password);

    @Select("select * from pcc_user where email=#{email}")
    PccUser findByEmail(@Param("email") String email);
}