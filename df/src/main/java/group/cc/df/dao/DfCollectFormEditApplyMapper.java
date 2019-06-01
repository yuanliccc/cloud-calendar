package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.dto.DfCollectFormEditApplyDTO;
import group.cc.df.model.DfCollectFormEditApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DfCollectFormEditApplyMapper extends Mapper<DfCollectFormEditApply> {

    void saveCollectFormEditApply(DfCollectFormEditApply applyInfo);

    DfCollectFormEditApply findCollectFormEditApplyByEmployeeIdAndCollectFormId(@Param("employeeId") Integer employeeId,
                                                                                @Param("collectFormId") Integer collectFormId);

    void updateCollectFormEditApply(DfCollectFormEditApply applyInfo);

    List<DfCollectFormEditApplyDTO> findCollectFormEditApply(Map<String, Object> conditionMap);

    List<DfCollectFormEditApplyDTO> findFormLikeFormNameAndHolderId(@Param("formName") String formName,
                                                                    @Param("holderId") Integer holderId);

    /**
     * 删除收集表单申请信息
     * @param collectFormId
     */
    void deleteCollectFormEditApplyByCollectFormId(Integer collectFormId);
}