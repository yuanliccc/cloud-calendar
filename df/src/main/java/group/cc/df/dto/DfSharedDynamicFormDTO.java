package group.cc.df.dto;

import group.cc.df.model.DfDynamicForm;
import group.cc.df.model.DfSharedDynamicForm;
import group.cc.df.model.DfUser;

/**
 * @author gxd
 */
public class DfSharedDynamicFormDTO {
    private DfSharedDynamicForm sharedDynamicForm;

    private DfUser holder;

    private DfDynamicForm form;

    public DfSharedDynamicForm getSharedDynamicForm() {
        return sharedDynamicForm;
    }

    public void setSharedDynamicForm(DfSharedDynamicForm sharedDynamicForm) {
        this.sharedDynamicForm = sharedDynamicForm;
    }

    public DfUser getHolder() {
        return holder;
    }

    public void setHolder(DfUser holder) {
        this.holder = holder;
    }

    public DfDynamicForm getForm() {
        return form;
    }

    public void setForm(DfDynamicForm form) {
        this.form = form;
    }
}
