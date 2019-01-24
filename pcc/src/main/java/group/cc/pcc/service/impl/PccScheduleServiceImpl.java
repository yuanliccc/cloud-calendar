package group.cc.pcc.service.impl;

import com.yl.common.util.StringUtil;
import group.cc.pcc.dao.*;
import group.cc.pcc.model.PccSchedule;
import group.cc.pcc.model.PccText;
import group.cc.pcc.model.builder.*;
import group.cc.pcc.model.dto.PccScheduleComplete;
import group.cc.pcc.model.dto.PccScheduleDto;
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
    private PccScheduleRemindMapper pccScheduleRemindMapper;

    @Resource
    private PccTextMapper pccTextMapper;

    @Resource
    private PccScheduleTextMapper pccScheduleTextMapper;

    @Resource
    private PccScheduleFileMapper pccScheduleFileMapper;

    @Resource
    private PccScheduleUserMapper pccScheduleUserMapper;

    @Resource
    private PccScheduleMapper pccScheduleMapper;

    @Resource
    private PccScheduleAdditionalTypeMapper pccScheduleAdditionalTypeMapper;

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

        // 批量插入中间表 pcc_schedule_additional_type
        pccScheduleAdditionalTypeMapper.insertList(PccScheduleAdditionalTypeBuilder
                .build(pccScheduleDto.getAdditionalInfoTypes(), insertPccScheduleId));

    }

    @Override
    public List<Map<String, Object>> relationList(Integer pccUserId) {
        return pccScheduleMapper.relationList(pccUserId);
    }

    @Override
    public List<Map<String, Object>> createList(Integer pccUserId) {
        return pccScheduleMapper.createList(pccUserId);
    }

    @Override
    public List<Map<String, Object>> untreated(Integer pccUserId) {
        return pccScheduleMapper.untreatedList(pccUserId);
    }

    @Override
    public List<Map<String, Object>> treated(Integer pccUserId) {
        return pccScheduleMapper.treatedList(pccUserId);
    }

    @Override
    @Transactional
    public void complete(PccScheduleComplete pccScheduleComplete) {
        Integer[] pccFileIds = pccScheduleComplete.getFileIds();

        if (pccFileIds != null && pccFileIds.length != 0) {
            // 批量存储 pccScheduleFile
            pccScheduleFileMapper.insertList(PccScheduleFileBuilder
                    .build(pccScheduleComplete.getFileIds(),
                            pccScheduleComplete.getPccScheduleId(),
                            pccScheduleComplete.getPccUserId()));
        }

        String text = pccScheduleComplete.getText();

        if(text != null && !text.equals(StringUtil.EMPTY)) {
            // 存储 pccText
            PccText pccText = PccTextBuilder.build(pccScheduleComplete.getText());
            pccTextMapper.insert(pccText);
            // 存储 pccScheduleText
            pccScheduleTextMapper.insert(PccScheduleTextBuilder.build(pccScheduleComplete.getPccScheduleId(),
                    pccScheduleComplete.getPccUserId(), pccText.getId()));
        }
        // 更新 pcc_schedule_user 表为完成任务
        pccScheduleUserMapper.complete(pccScheduleComplete.getPccUserId(),
                pccScheduleComplete.getPccScheduleId(), new Date());
    }

    @Override
    public List<Map<String, Object>> additionalInfoList(Integer pccScheduleId) {
        return pccScheduleMapper.additionalInfoList(pccScheduleId);
    }
}
