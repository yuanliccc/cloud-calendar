package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Module;
import group.cc.occ.model.dto.ModuleDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ModuleMapper extends Mapper<Module> {
    @Select("SELECT * FROM MODULE WHERE ID IN(SELECT P.MODULEID FROM PERMISSION P LEFT JOIN ROLE_PERMISSION RP ON RP.PERID = P.ID " +
            " WHERE P.IDENTIFY LIKE '%_left' AND RP.ROLEID = #{roleId})" )
    public List<Module> getModulesByRoleId(@Param("roleId") Integer roleId);

    @Select("SELECT * FROM MODULE WHERE ISSYSTEM = '是'")
    public List<Module> getSystemModules();

    @Select("SELECT * FROM MODULE WHERE ${key} like #{value}")
    public List<Module> listByKey(@Param("key")String key, @Param("value")String value);

    @Delete("DELETE FROM MODULE WHERE ID in(${moduleIds})")
    public void deleteBatch(@Param("moduleIds")String moduleIds);
}