package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccScheduleMapper;
import group.cc.pcc.model.PccSchedule;
import group.cc.pcc.service.PccScheduleService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author yuanli
 * @date 2018/12/23
 */
@Service
@Transactional
public class PccScheduleServiceImpl extends AbstractService<PccSchedule> implements PccScheduleService {
    @Resource
    private PccScheduleMapper pccScheduleMapper;

    @Override
    public List<Map<String, Object>> dayCount(Date startDate, Date endDate, Integer pccUserId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");


        return pccScheduleMapper.dayCount(simpleDateFormat.format(startDate), simpleDateFormat.format(endDate), pccUserId);
    }
}
