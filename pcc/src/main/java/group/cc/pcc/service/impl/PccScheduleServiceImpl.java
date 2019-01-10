package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccScheduleMapper;
import group.cc.pcc.dao.PccScheduleRemindMapper;
import group.cc.pcc.dao.PccScheduleUserMapper;
import group.cc.pcc.model.PccSchedule;
import group.cc.pcc.model.PccScheduleRemind;
import group.cc.pcc.model.builder.PccScheduleRemindBuilder;
import group.cc.pcc.model.builder.PccScheduleUserBuilder;
import group.cc.pcc.model.dto.PccScheduleDto;
import group.cc.pcc.service.PccScheduleService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

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
    private PccScheduleRemindMapper pccScheduleRemindMapper;

    @Resource
    private PccScheduleUserMapper pccScheduleUserMapper;

    @Resource
    private PccScheduleMapper pccScheduleMapper;

    @Override
    public List<Map<String, Object>> dayCount(Date startDate, Date endDate, Integer pccUserId) {

        /*Example example = new Example(PccSchedule.class);
        Example.Criteria criteria = example.createCriteria();*/

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        return pccScheduleMapper.dayCount(simpleDateFormat.format(startDate), simpleDateFormat.format(endDate), pccUserId);
    }

    @Override
    @Transactional
    public void add(PccScheduleDto pccScheduleDto) {

        // 创建时间
        pccScheduleDto.getPccSchedule().setCreateTime(new Date());

        // 插入 pccSchedule
        pccScheduleMapper.insert(pccScheduleDto.getPccSchedule());

        Integer insertPccScheduleId = pccScheduleDto.getPccSchedule().getId();

        // 批量插入中间表 pcc_schedule_remind
        pccScheduleRemindMapper.insertList(PccScheduleRemindBuilder
                .build(pccScheduleDto.getRemindServices(), insertPccScheduleId));

        // 批量插入中间表 pcc_schedule_user
        pccScheduleUserMapper.insertList(PccScheduleUserBuilder
                .build(pccScheduleDto.getScheduleReceivers(), insertPccScheduleId));
    }

    @Override
    public List<Map<String,Object>> relationList(Integer pccUserId) {
        return pccScheduleMapper.relationList(pccUserId);
    }
}
