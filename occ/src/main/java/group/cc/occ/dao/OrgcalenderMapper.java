package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Orgcalender;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrgcalenderMapper extends Mapper<Orgcalender> {
    @Select("SELECT * FROM ORGCALENDER WHERE ORGID = #{orgId} OR(ORGID IN(SELECT ROOTORGID AS ID FROM organization WHERE ID = #{orgId} " +
            " UNION SELECT PARENTORGID AS ID FROM organization WHERE ID = #{orgId}) AND SUBORDINATECANSEEN = '是') AND ${key} like #{value}")
    public List<Orgcalender> listByKey(@Param("key") String key, @Param("value") String value, @Param("orgId") Integer orgId);

    @Delete("DELETE FROM ORGCALENDER WHERE ID IN (${ids})")
    public void deleteBatch(@Param("ids")String orgCalenders);
}