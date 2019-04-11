package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.model.DfSharedDynamicForm;

import java.util.List;
import java.util.Map;

public interface DfSharedDynamicFormMapper extends Mapper<DfSharedDynamicForm> {

    /**
     * 根据条件查询分享的模板表单
     * @param conditionMap
     * @return
     */
    List<DfSharedDynamicForm> findSharedDynamicFormByCondition(Map<String, Object> conditionMap);

    /**
     * 根据条件查询分享的模板表单的数量
     * @param conditionMap
     * @return
     */
    int findSharedDynamicFormCountByCondition(Map<String, Object> conditionMap);
}