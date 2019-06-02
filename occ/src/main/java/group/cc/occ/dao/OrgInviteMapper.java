package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.OrgInvite;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrgInviteMapper extends Mapper<OrgInvite> {
    @Select("SELECT * FROM ORG_INVITE WHERE ${key} like #{value} AND USERID = #{userId}")
    public List<OrgInvite> listByKey(@Param("key")String key, @Param("value")String value, @Param("userId")Integer userId);

    @Delete("DELETE FROM ORG_INVITE WHERE ID in(${ids})")
    public void deleteBatch(@Param("ids")String ids);

    @Select("SELECT * FROM ORG_INVITE WHERE APPROVALORGID = #{orgId} AND USERID = #{userId}")
    public List<OrgInvite> isInviteExist(@Param("orgId")Integer orgId, @Param("userId")Integer userId);
}