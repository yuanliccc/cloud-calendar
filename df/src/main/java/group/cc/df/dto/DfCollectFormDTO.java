package group.cc.df.dto;

import group.cc.df.model.DfCollectForm;
import group.cc.df.model.DfCollectFormEditApply;
import group.cc.df.model.DfDynamicForm;
import group.cc.df.model.DfUser;

/**
 * Created by 高旭东 on 2019/4/21.
 */
public class DfCollectFormDTO {

    /**
     * 收集表单信息
     */
    private DfCollectForm collectForm;

    /**
     * 表单信息
     */
    private DfDynamicForm dynamicForm;

    /**
     * 提交人信息
     */
    private DfUser submiter;

    private DfCollectFormEditApply applyInfo;

    public DfCollectForm getCollectForm() {
        return collectForm;
    }

    public void setCollectForm(DfCollectForm collectForm) {
        this.collectForm = collectForm;
    }

    public DfDynamicForm getDynamicForm() {
        return dynamicForm;
    }

    public void setDynamicForm(DfDynamicForm dynamicForm) {
        this.dynamicForm = dynamicForm;
    }

    public DfUser getSubmiter() {
        return submiter;
    }

    public void setSubmiter(DfUser submiter) {
        this.submiter = submiter;
    }

    public DfCollectFormEditApply getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(DfCollectFormEditApply applyInfo) {
        this.applyInfo = applyInfo;
    }

    @Override
    public String toString() {
        return "DfCollectFormDTO{" +
                "collectForm=" + collectForm +
                ", dynamicForm=" + dynamicForm +
                ", submiter=" + submiter +
                ", applyInfo=" + applyInfo +
                '}';
    }
}
