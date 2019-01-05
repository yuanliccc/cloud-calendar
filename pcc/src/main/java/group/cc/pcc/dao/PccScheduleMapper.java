package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.dao.provider.PccScheduleMapperProvider;
import group.cc.pcc.model.PccSchedule;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface PccScheduleMapper extends Mapper<PccSchedule> {

    List<Map<String,Object>> dayCount(String startDate, String endDate, Integer pccUserId);
}