package group.cc.occ.dao;

import group.cc.core.Mapper;
import group.cc.occ.model.SystemConfig;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SystemConfigMapper extends Mapper<SystemConfig> {
    @Select("SELECT * FROM SYSTEM_CONFIG WHERE ${key} like #{value}")
    public List<SystemConfig> listByKey(@Param("key")String key, @Param("value")String value);

    @Delete("DELETE FROM SYSTEM_CONFIG WHERE ID in(${moduleIds})")
    public void deleteBatch(@Param("moduleIds")String moduleIds);

    @Select("SELECT * FROM SYSTEM_CONFIG WHERE SYSTEMKEY like #{key}")
    public SystemConfig getValueByKey(@Param("key")String key);
}