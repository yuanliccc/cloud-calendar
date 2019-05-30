package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.OrgApply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrgApplyMapper extends Mapper<OrgApply> {
    @Select("SELECT * FROM ORG_APPLY WHERE ${key} like #{value}")
    public List<OrgApply> listByKey(@Param("key")String key, @Param("value")String value, @Param("orgId")Integer orgId);

    @Delete("DELETE FROM ORG_APPLY WHERE ID in(${ids})")
    public void deleteBatch(@Param("ids")String ids);
}