package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.model.DfDynamicForm;

public interface DfDynamicFormMapper extends Mapper<DfDynamicForm> {
    int saveDynamicForm(DfDynamicForm dfDynamicForm);

    int updateDynamicForm(DfDynamicForm dfDynamicForm);
}