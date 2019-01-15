package group.cc.pcc.service;
import group.cc.pcc.model.PccSchedule;
import group.cc.core.Service;
import group.cc.pcc.model.dto.PccScheduleDto;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author yuanli
 * @date 2018/12/23
 */
public interface PccScheduleService extends Service<PccSchedule> {

    List<Map<String,Object>> dayCount(Date startDate, Date endDate, Integer pccUserId);

    void add(PccScheduleDto pccScheduleDto);

    List<Map<String,Object>> relationList(Integer pccUserId);

    List<Map<String,Object>> createList(Integer pccUserId);

    List<Map<String,Object>> untreated(Integer pccUserId);

    List<Map<String,Object>> treated(Integer pccUserId);
}
