package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.apache.ibatis.annotations.Mapper
public interface PccUserMapper extends Mapper<PccUser> {

    @Select("select * from pcc_user as pu WHERE pu.id in (select puf.friend_pcc_user_id from pcc_user_friend AS puf WHERE puf" +
            ".pcc_user_id=#{pccUserId})")
    List<PccUser> friends(@Param("pccUserId") Integer pccUserId);
}