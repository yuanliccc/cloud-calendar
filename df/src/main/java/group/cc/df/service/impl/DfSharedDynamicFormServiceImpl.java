package group.cc.df.service.impl;

import group.cc.df.dao.DfDynamicFormMapper;
import group.cc.df.dao.DfSharedDynamicFormMapper;
import group.cc.df.dao.DfUserMapper;
import group.cc.df.dto.DfSharedDynamicFormDTO;
import group.cc.df.model.DfDynamicForm;
import group.cc.df.model.DfSharedDynamicForm;
import group.cc.df.model.DfUser;
import group.cc.df.service.DfSharedDynamicFormService;
import group.cc.core.AbstractService;
import group.cc.df.utils.ShareFormStateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author gxd
 * @date 2019/04/09
 */
@Service
@Transactional
public class DfSharedDynamicFormServiceImpl extends AbstractService<DfSharedDynamicForm> implements DfSharedDynamicFormService {
    @Resource
    private DfSharedDynamicFormMapper dfSharedDynamicFormMapper;

    @Resource
    private DfUserMapper dfUserMapper;

    @Resource
    private DfDynamicFormMapper dfDynamicFormMapper;

    @Override
    public Map<String, Object> findNormalSharedDynamicFormByCondition(Map<String, Object> conditionMap) {
        Map<String, Object> resultMap = new HashMap<>();

        Integer offset = this.getOffsetByConditionMap(conditionMap);
        conditionMap.put("offset", offset);
        conditionMap.put("state", ShareFormStateUtil.NORMAL);

        List<DfSharedDynamicForm> dfSharedDynamicFormList = this.dfSharedDynamicFormMapper.findSharedDynamicFormByCondition(conditionMap);
        List<DfSharedDynamicFormDTO> dfSharedDynamicFormDTOList = this.sharedDynamicFormListToSharedDynamicFormDTOList(dfSharedDynamicFormList);
        int total = this.dfSharedDynamicFormMapper.findSharedDynamicFormCountByCondition(conditionMap);

        resultMap.put("listInfo", dfSharedDynamicFormDTOList);
        resultMap.put("total", total);
        return resultMap;
    }

    private Integer getOffsetByConditionMap(Map<String, Object> conditionMap) {
        if (conditionMap == null || conditionMap.isEmpty()) {
            return null;
        }

        Integer pageSize = (Integer) conditionMap.get("pageSize");
        Integer pageNum = (Integer) conditionMap.get("pageNum");
        if (pageSize != null && pageNum != null) {
            Integer offset = (pageNum - 1) * pageSize;
            return offset;
        }

        return null;
    }

    private List<DfSharedDynamicFormDTO> sharedDynamicFormListToSharedDynamicFormDTOList(List<DfSharedDynamicForm> sharedDynamicFormList) {
        if (sharedDynamicFormList == null || sharedDynamicFormList.isEmpty()) {
            return null;
        }
        List<DfSharedDynamicFormDTO> dfSharedDynamicFormDTOList = new ArrayList<>(sharedDynamicFormList.size());
        for (DfSharedDynamicForm sharedDynamicForm: sharedDynamicFormList) {
            DfSharedDynamicFormDTO dto = new DfSharedDynamicFormDTO();
            dto.setSharedDynamicForm(sharedDynamicForm);
            if (sharedDynamicForm.getHolderId() != null) {
                DfUser holder = this.dfUserMapper.selectByPrimaryKey(sharedDynamicForm.getHolderId());
                dto.setHolder(holder);
            }
            if (sharedDynamicForm.getFormId() != null) {
                DfDynamicForm form = this.dfDynamicFormMapper.selectByPrimaryKey(sharedDynamicForm.getFormId());
                dto.setForm(form);
            }

            dfSharedDynamicFormDTOList.add(dto);
        }

        return dfSharedDynamicFormDTOList;
    }
}
