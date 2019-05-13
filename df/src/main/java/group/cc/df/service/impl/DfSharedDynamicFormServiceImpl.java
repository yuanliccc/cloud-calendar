package group.cc.df.service.impl;

import group.cc.df.dao.*;
import group.cc.df.dto.DfSharedDynamicFormDTO;
import group.cc.df.model.*;
import group.cc.df.service.DfSharedDynamicFormService;
import group.cc.core.AbstractService;
import group.cc.df.utils.DynamicFormPublishState;
import group.cc.df.utils.ShareFormStateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


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

    @Resource
    private DfFormFieldMapper dfFormFieldMapper;

    @Resource
    private DfFormItemMapper dfFormItemMapper;

    @Resource
    private DfClonedDynamicFormMapper dfClonedDynamicFormMapper;

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

    @Override
    public synchronized void cloneDynamicForm(Integer sharedId, Integer formId) {
        DfUser user = (DfUser) SecurityUtils.getSubject().getSession().getAttribute("user");

        // 首先查询分享的表单记录,更新其克隆数量
        DfSharedDynamicForm sharedDynamicForm = this.dfSharedDynamicFormMapper.selectByPrimaryKey(sharedId);
        Integer clonedCount = sharedDynamicForm.getClonedCount();
        clonedCount += 1;
        sharedDynamicForm.setClonedCount(clonedCount);
        this.dfSharedDynamicFormMapper.updateSharedDynamicForm(sharedDynamicForm);

        // 克隆动态表单
        Integer newFormId = this.cloneDynamicForm(formId);

        // 生成一条克隆记录
        DfClonedDynamicForm clonedDynamicForm = new DfClonedDynamicForm();
        clonedDynamicForm.setCloneTime(new Date());
        clonedDynamicForm.setEmployeeId(user.getId());
        clonedDynamicForm.setFormId(formId);
        clonedDynamicForm.setNewFormId(newFormId);

        this.dfClonedDynamicFormMapper.saveClonedDynamicForm(clonedDynamicForm);
    }

    /**
     * 创建一份新的表单及其表单域,表单条目
     * @param formId
     */
    private Integer cloneDynamicForm(Integer formId) {
        DfUser user = (DfUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        // 获取对应的动态表单信息
        DfDynamicForm dynamicForm = this.dfDynamicFormMapper.selectByPrimaryKey(formId);
        dynamicForm.setId(null);
        dynamicForm.setEmployeeId(user.getId());
        dynamicForm.setCreateTime(new Date());
        dynamicForm.setPublishState(DynamicFormPublishState.NO_PUBLISH);
        // 保存一条新的表单记录
        this.dfDynamicFormMapper.saveDynamicForm(dynamicForm);

        // 获取表单对应的表单域
        List<DfFormField> dfFormFieldList = this.dfFormFieldMapper.findDynamicFormFieldsByFormId(formId);
        // 保存已经存储的表单域的Id
        List<Integer> clonedFieldIdList = new ArrayList<>();
        for (DfFormField formField: dfFormFieldList) {
            if (formField.getParentId() != null) {
                continue;
            }
            Integer oldFormFieldId = formField.getId();
            // 如果已存储的列表中包含子该元素,则跳过
            if (clonedFieldIdList.contains(oldFormFieldId)) {
                continue;
            }
            clonedFieldIdList.add(oldFormFieldId);
            formField.setId(null);
            formField.setFormId(dynamicForm.getId());
            // 保存新的表单域信息
            this.dfFormFieldMapper.saveFormField(formField);
            // 根据原来的表单域Id尝试查询其子元素
            List<DfFormField> subFormFieldList = this.dfFormFieldMapper.findDynamicFormFieldListByParentId(oldFormFieldId);
            // 当存在子元素不为空时
            if (subFormFieldList != null && !subFormFieldList.isEmpty()) {
                for (DfFormField sub: subFormFieldList) {
                    Integer oldSubId = sub.getId();
                    if (clonedFieldIdList.contains(oldSubId)) {
                        continue;
                    }
                    clonedFieldIdList.add(oldSubId);
                    sub.setId(null);
                    sub.setFormId(dynamicForm.getId());
                    // 设置新的parent_id为新保存的表单域
                    sub.setParentId(formField.getId());
                    // 保存新的表单条目
                    this.dfFormFieldMapper.saveFormField(sub);
                    cloneFormItem(oldSubId, sub.getId());
                }
            }

            cloneFormItem(oldFormFieldId, formField.getId());
        }

        return dynamicForm.getId();
    }

    /**
     * 克隆表单条目信息
     * @param oldFieldId
     * @param newFieldId
     */
    private void cloneFormItem(Integer oldFieldId, Integer newFieldId) {
        List<DfFormItem> formItemList = this.dfFormItemMapper.findDfFormItemsByFieldId(oldFieldId);

        for (DfFormItem formItem: formItemList) {
            formItem.setId(null);
            formItem.setFormFieldId(newFieldId);
            this.dfFormItemMapper.saveFormItem(formItem);
        }
    }

    @Override
    public void cancelShareDynamicForm(Integer formId) {
        // 首先获取到要取消的表单的分享记录
        List<DfSharedDynamicForm> sharedFormList = this.dfSharedDynamicFormMapper.findSharedDynamicFormByFormId(formId);
        if (sharedFormList == null || sharedFormList.isEmpty()) {
            return;
        }

        DfSharedDynamicForm sharedForm = sharedFormList.get(0);
        // 设置状态为已关闭
        sharedForm.setState(ShareFormStateUtil.CLOSED);
        this.dfSharedDynamicFormMapper.updateSharedDynamicForm(sharedForm);
    }
}
