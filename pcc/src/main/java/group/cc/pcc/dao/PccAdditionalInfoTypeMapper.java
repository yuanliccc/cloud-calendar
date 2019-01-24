package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccAdditionalInfoType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PccAdditionalInfoTypeMapper extends Mapper<PccAdditionalInfoType> {

    @Select("SELECT " +
            "psat.id AS psatId, " +
            "psat.pcc_schedule_id AS pccScheduleId, " +
            "psat.additional_info_type_id AS additionalInfoTypeId, " +
            "pait.name AS name, " +
            "pait.table_name AS tableName, " +
            "pait.filters AS filters " +
            "FROM " +
            "pcc_schedule_additional_type AS psat " +
            "LEFT JOIN " +
            "pcc_additional_info_type AS pait " +
            "ON pait.id=psat.additional_info_type_id " +
            "WHERE " +
            "psat.pcc_schedule_id = #{pccScheduleId}")
    List<Map<String,Object>> list(@Param("pccScheduleId") Integer pccScheduleId);
}