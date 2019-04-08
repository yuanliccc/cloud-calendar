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
}