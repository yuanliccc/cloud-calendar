package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Organization;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrganizationMapper extends Mapper<Organization> {

    @Select("SELECT * FROM ORGANIZATION WHERE (ID = #{orgId} OR ROOTORGID = #{orgId} OR PARENTORGID = #{orgId}) " +
            " AND ${key} like #{value}")
    public List<Organization> listByKey(@Param("key") String key, @Param("value")String value, @Param("orgId")Integer orgId);

    @Update("UPDATE ORGANIZATION SET NAME = #{org.name}, ORGKEY = #{org.orgkey}, PARENTORGID = #{org.parentorgid}, " +
            " ROOTORGID = #{org.rootorgid} WHERE ID = #{org.id}")
    public void updateOrg(@Param("org") Organization org);

    @Select("SELECT O.* FROM ORGANIZATION O LEFT JOIN ROLE R ON R.ORGID = O.ID LEFT JOIN USER_ROLE UR ON UR.ROLEID = R.ID WHERE " +
            " UR.USERID = #{userId}")
    public List<Organization> getAllLoginUserOrg(@Param("userId")Integer userId);

    @Select("SELECT * FROM ORGANIZATION WHERE ID = #{orgId} AND ${key} like #{value}")
    public List<Organization> findOrgById(@Param("key") String key, @Param("value")String value, @Param("orgId")Integer orgId);

    @Select("SELECT * FROM ORGANIZATION WHERE PARENTORGID = #{orgId} AND ${key} like #{value}")
    public List<Organization> findChildOrg(@Param("key") String key, @Param("value")String value, @Param("orgId")Integer orgId);
}