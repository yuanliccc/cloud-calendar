package group.cc.df.service;
import com.github.pagehelper.PageInfo;
import group.cc.df.dto.DfCollectFormEditApplyDTO;
import group.cc.df.model.DfCollectFormEditApply;
import group.cc.core.Service;

import java.util.Map;


/**
 * @author gxd
 * @date 2019/05/14
 */
public interface DfCollectFormEditApplyService extends Service<DfCollectFormEditApply> {

    void submitCollectFormEditApply(DfCollectFormEditApply applyInfo);

    PageInfo<DfCollectFormEditApplyDTO> findCollectFormEditApply(Map<String, Object> conditionMap);
}
