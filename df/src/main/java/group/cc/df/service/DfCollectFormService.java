package group.cc.df.service;
import group.cc.df.dto.DfCollectFormDTO;
import group.cc.df.model.DfCollectForm;
import group.cc.core.Service;

import java.util.List;
import java.util.Map;


/**
 * @author gxd
 * @date 2019/04/16
 */
public interface DfCollectFormService extends Service<DfCollectForm> {

    /**
     * 保存收集表单信息
     * @param collectFormMap
     */
    void saveCollectForm(Map<String, Object> collectFormMap);

    /**
     * 查询当前用户填写的对应表单Id的收集表单信息
     * @param formId
     * @return
     */
    DfCollectForm findSelfCollectFormByFormId(Integer formId);

    /**
     * 根据条件查询符合条件的收集表单信息
     * @param conditionMap
     * @return
     */
    Map<String, Object> findCollectFormByCondition(Map<String, Object> conditionMap);

    /**
     * 查询符合条件的当前用户所填写的表单信息
     * @param conditionMap
     * @return
     */
    Map<String, Object> findSelfSubmitFormByCondition(Map<String, Object> conditionMap);

    /**
     * 根据表单名称模糊查询当前用户提交的表单信息
     * @param formName
     * @return
     */
    List<DfCollectFormDTO> findFormLikeName(String formName);

    /**
     * 更新收集表单信息
     * @param collectFormMap
     */
    void updateCollectForm(Map<String, Object> collectFormMap);
}
