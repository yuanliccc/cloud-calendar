package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.model.DfDynamicForm;

public interface DfDynamicFormMapper extends Mapper<DfDynamicForm> {
    public int saveDynamicForm(DfDynamicForm dfDynamicForm);
}