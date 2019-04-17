package group.cc.df.dto;

import group.cc.df.model.DfDynamicForm;
import group.cc.df.model.DfSharedDynamicForm;
import group.cc.df.model.DfUser;

/**
 * Created by 高旭东 on 2019/4/7.
 */
public class DfDynamicFormDTO {

    /**
     * 动态表单信息
     */
    private DfDynamicForm dfDynamicForm;

    /**
     * 创建人信息
     */
    private DfUser holder;

    /**
     * 分享表单信息
     */
    private DfSharedDynamicForm sharedForm;

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

    public DfSharedDynamicForm getSharedForm() {
        return sharedForm;
    }

    public void setSharedForm(DfSharedDynamicForm sharedForm) {
        this.sharedForm = sharedForm;
    }
}
