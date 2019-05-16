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

    /**
     * 提交申请
     * @param applyInfo
     */
    void submitCollectFormEditApply(DfCollectFormEditApply applyInfo);

    /**
     * 分页查询申请信息
     * @param conditionMap
     * @return
     */
    PageInfo<DfCollectFormEditApplyDTO> findCollectFormEditApply(Map<String, Object> conditionMap);

    /**
     * 通过申请
     * @param applyId
     */
    void adoptApply(Integer applyId);

    /**
     * 拒绝申请
     * @param applyId
     */
    void refuseApply(Integer applyId);
}
