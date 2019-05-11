package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.model.DfDynamicForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DfDynamicFormMapper extends Mapper<DfDynamicForm> {
    int saveDynamicForm(DfDynamicForm dfDynamicForm);

    int updateDynamicForm(DfDynamicForm dfDynamicForm);

    List<DfDynamicForm> findDynamicFormByLimit(@Param("pageSize") Integer pageSize, @Param("offset") Integer offset);

    int findDynamicFormCount();

    /**
     * 根据条件查询符合条件的数据
     * @param conditonMap
     * @return
     */
    List<DfDynamicForm> findDynamicFormByCondition(Map<String, Object> conditonMap);

    /**
     * 获取符合条件的数据数量
     * @param conditonMap
     * @return
     */
    int findDynamicFormCountByCondition(Map<String, Object> conditonMap);

}