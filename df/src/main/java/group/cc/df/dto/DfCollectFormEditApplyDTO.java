package group.cc.df.dto;

import group.cc.df.model.DfCollectFormEditApply;
import group.cc.df.model.DfDynamicForm;
import group.cc.df.model.DfUser;

/**
 * Created by 高旭东 on 2019/5/16.
 */
public class DfCollectFormEditApplyDTO {

    private DfCollectFormEditApply applyInfo;

    private DfUser applyUser;

    private DfDynamicForm dfDynamicForm;

    private DfUser holder;

    public DfCollectFormEditApply getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(DfCollectFormEditApply applyInfo) {
        this.applyInfo = applyInfo;
    }

    public DfUser getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(DfUser applyUser) {
        this.applyUser = applyUser;
    }

    public DfDynamicForm getDfDynamicForm() {
        return dfDynamicForm;
    }

    public void setDfDynamicForm(DfDynamicForm dfDynamicForm) {
        this.dfDynamicForm = dfDynamicForm;
    }

    public DfUser getHolder() {
        return holder;
    }

    public void setHolder(DfUser holder) {
        this.holder = holder;
    }
}
