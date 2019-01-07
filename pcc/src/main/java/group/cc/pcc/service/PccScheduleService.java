package group.cc.pcc.service;
import group.cc.pcc.model.PccSchedule;
import group.cc.core.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author yuanli
 * @date 2018/12/23
 */
public interface PccScheduleService extends Service<PccSchedule> {

    List<Map<String,Object>> dayCount(Date startDate, Date endDate, Integer pccUserId);
}
