package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.model.DfClonedDynamicForm;

public interface DfClonedDynamicFormMapper extends Mapper<DfClonedDynamicForm> {

    /**
     * 保存克隆信息
     * @param clonedDynamicForm
     * @return
     */
    int saveClonedDynamicForm(DfClonedDynamicForm clonedDynamicForm);
}