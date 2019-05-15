package group.cc.df.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import group.cc.df.dao.DfCollectFormEditApplyMapper;
import group.cc.df.dao.DfCollectFormMapper;
import group.cc.df.dao.DfDynamicFormMapper;
import group.cc.df.dto.DfCollectFormEditApplyDTO;
import group.cc.df.model.DfCollectForm;
import group.cc.df.model.DfCollectFormEditApply;
import group.cc.df.model.DfDynamicForm;
import group.cc.df.model.DfUser;
import group.cc.df.service.DfCollectFormEditApplyService;
import group.cc.core.AbstractService;
import group.cc.df.utils.EditFormApplyStateUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;


/**
 * @author gxd
 * @date 2019/05/14
 */
@Service
@Transactional
public class DfCollectFormEditApplyServiceImpl extends AbstractService<DfCollectFormEditApply> implements DfCollectFormEditApplyService {

    @Resource
    private DfCollectFormEditApplyMapper dfCollectFormEditApplyMapper;

    @Resource
    private DfCollectFormMapper dfCollectFormMapper;

    @Resource
    private DfDynamicFormMapper dfDynamicFormMapper;

    @Override
    public void submitCollectFormEditApply(DfCollectFormEditApply applyInfo) {
        DfUser user = (DfUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        DfCollectForm collectForm = this.dfCollectFormMapper.selectByPrimaryKey(applyInfo.getCollectFormId());
        DfDynamicForm dynamicForm = this.dfDynamicFormMapper.selectByPrimaryKey(collectForm.getFormId());
        DfCollectFormEditApply preApplyInfo = this.dfCollectFormEditApplyMapper
                .findCollectFormEditApplyByEmployeeIdAndCollectFormId(user.getId(), applyInfo.getCollectFormId());
        Integer holderId = dynamicForm.getId();
        Date currentDate = new Date();
        if (preApplyInfo == null) {
            applyInfo.setEmployeeId(user.getId());
            applyInfo.setHolderId(holderId);
            applyInfo.setState(EditFormApplyStateUtil.AUDIT);
            applyInfo.setApplyDate(currentDate);

            this.dfCollectFormEditApplyMapper.saveCollectFormEditApply(applyInfo);
        } else {
            preApplyInfo.setHolderId(holderId);
            preApplyInfo.setEmployeeId(user.getId());
            preApplyInfo.setState(EditFormApplyStateUtil.AUDIT);
            preApplyInfo.setApplyDate(currentDate);
            this.dfCollectFormEditApplyMapper.updateCollectFormEditApply(preApplyInfo);
        }
    }

    @Override
    public PageInfo<DfCollectFormEditApplyDTO> findCollectFormEditApply(Map<String, Object> conditionMap) {
        Integer pageNum = (Integer) conditionMap.get("pageNum");
        Integer pageSize = (Integer) conditionMap.get("pageSize");
        PageHelper.startPage(pageNum, pageSize);
        // TODO 调用Mapper,查询申请表单
        return null;
    }
}
