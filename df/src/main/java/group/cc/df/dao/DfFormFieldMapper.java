package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.dto.DfFieldComponentDTO;
import group.cc.df.model.DfFormField;

import java.util.List;

public interface DfFormFieldMapper extends Mapper<DfFormField> {
    public int saveFormField(DfFormField dfFormField);

    List<DfFormField> findDynamicFormFieldsByFormId(Integer formId);
}