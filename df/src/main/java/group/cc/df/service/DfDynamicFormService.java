package group.cc.df.service;
import group.cc.df.dto.DfDynamicFormDTO;
import group.cc.df.model.DfDynamicForm;
import group.cc.core.Service;

import java.util.List;
import java.util.Map;


/**
 * @author gxd
 * @date 2019/01/10
 */
public interface DfDynamicFormService extends Service<DfDynamicForm> {
    void saveDynamicForm(Map<String, Object> dfMap);

    void updateDynamicForm(Map<String, Object> dfMap);

    /**
     *
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<DfDynamicFormDTO> findDynamicFormByLimit(Integer pageSize, Integer pageNum);

    /**
     * 获取存储数据的总量
     * @return
     */
    int findDynamicFormCount();

    /**
     * 根据动态表单的Id删除表单及其条目信息(表单域，条目)
     * @param id
     * @return
     */
    void deleteDynamicForm(Integer id);
}
