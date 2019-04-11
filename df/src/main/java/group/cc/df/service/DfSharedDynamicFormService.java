package group.cc.df.service;
import group.cc.df.model.DfSharedDynamicForm;
import group.cc.core.Service;

import java.util.Map;


/**
 * @author gxd
 * @date 2019/04/09
 */
public interface DfSharedDynamicFormService extends Service<DfSharedDynamicForm> {

    /**
     * 查询符合条件的正常分享状态下的模板表单信息
     * @param conditionMap
     * @return
     */
    Map<String, Object> findNormalSharedDynamicFormByCondition(Map<String, Object> conditionMap);
}
