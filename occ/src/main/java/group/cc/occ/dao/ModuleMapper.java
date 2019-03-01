package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.Module;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ModuleMapper extends Mapper<Module> {
    @Select("SELECT * FROM MODULE WHERE ID IN(SELECT P.MODULEID FROM  ROLE R LEFT JOIN ROLE_PERMISSION RP ON R.ID = RP.ROLEID " +
            "LEFT JOIN PERMISSION P ON P.ID = RP.PERID WHERE R.ID = #{roleId})" )
    public List<Module> getModulesByRoleId(@Param("roleId") Integer roleId);

    @Select("SELECT * FROM MODULE WHERE ISSYSTEM = '是'")
    public List<Module> getSystemModules();
}