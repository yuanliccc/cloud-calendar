package group.cc.df.service;
import com.github.pagehelper.PageInfo;
import group.cc.df.dto.DfCollectFormEditApplyDTO;
import group.cc.df.model.DfCollectFormEditApply;
import group.cc.core.Service;

import java.util.List;
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

    /**
     * 根据表单名称模糊查询当前用户需要审批的表单信息
     * @param formName
     * @return
     */
    List<DfCollectFormEditApplyDTO> findFormLikeFormName(String formName);
}
