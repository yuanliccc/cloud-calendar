package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.dto.DfFieldComponentDTO;
import group.cc.df.model.DfFormField;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DfFormFieldMapper extends Mapper<DfFormField> {
    int saveFormField(DfFormField dfFormField);

    List<DfFormField> findDynamicFormFieldsByFormId(Integer formId);

    int updateFormField(DfFormField dfFormField);

    List<DfFormField> findUselessFormFields(@Param("fieldIdList") List<Integer> fieldIdList, @Param("formId") Integer formId);

    int deleteFormFieldById(Integer formFieldId);

    /**
     * 根据parentId查询表单列表
     * @param parentId
     * @return
     */
    List<DfFormField> findDynamicFormFieldListByParentId(Integer parentId);

    /**
     * 保存收集信息表的表单域信息
     * @param dfFormField
     * @return
     */
    int saveCollectFormField(DfFormField dfFormField);

    /**
     * 根据收集表的表单Id查询对应的表单域信息
     * @param formId
     * @return
     */
    List<DfFormField> findCollectFormFieldByCollectFormId(Integer formId);

    /**
     * 根据收集表单Id删除收集表单条目信息
     * @param collectFormId
     * @return
     */
    int deleteCollectFormFieldByCollectFormId(Integer collectFormId);
}