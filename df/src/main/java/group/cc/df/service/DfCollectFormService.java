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
}
