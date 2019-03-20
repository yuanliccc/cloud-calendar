package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.model.DfDynamicForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DfDynamicFormMapper extends Mapper<DfDynamicForm> {
    int saveDynamicForm(DfDynamicForm dfDynamicForm);

    int updateDynamicForm(DfDynamicForm dfDynamicForm);

    List<DfDynamicForm> findDynamicFormByLimit(@Param("pageSize") Integer pageSize, @Param("offset") Integer offset);

    int findDynamicFormCount();
}