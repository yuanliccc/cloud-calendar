package group.cc.pcc.service.impl;

import group.cc.pcc.dao.PccTaskMapper;
import group.cc.pcc.model.PccTask;
import group.cc.pcc.service.PccTaskService;
import group.cc.core.AbstractService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author yuanli
 * @date 2019/05/31
 */
@Service
@Transactional
public class PccTaskServiceImpl extends AbstractService<PccTask> implements PccTaskService {
    @Resource
    private PccTaskMapper pccTaskMapper;

    @Override
    public List<PccTask> listDay(String day) {
        return pccTaskMapper.listDay(day);
    }

    @Override
    public List<Map<String, Object>> willDeadList() {
        return pccTaskMapper.willDeadList();
    }

    @Override
    public List<PccTask> listDayUser(String day, Integer pccUserId) {
        return pccTaskMapper.listDayUser(day, pccUserId);
    }

    @Override
    public void deleteImitate(Integer id) {
        pccTaskMapper.deleteImitate(id);
    }

    @Override
    public Map<String, Long> counts(Integer pccUserId, String startDay, String endDay) {
        List<Map<String , Object>> counts =  pccTaskMapper.counts(pccUserId, startDay, endDay);

        Map<String, Long> res = new HashMap<>(counts.size());

        counts.parallelStream().forEach(count -> {
            res.put((String)count.get("day"), (Long) count.get("count"));
        });

        return res;
    }
}
