package group.cc.df.service;
import group.cc.df.model.DfCollectForm;
import group.cc.core.Service;

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
}
