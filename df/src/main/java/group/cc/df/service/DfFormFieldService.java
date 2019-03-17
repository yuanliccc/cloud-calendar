package group.cc.df.service;
import group.cc.df.dto.DfFieldComponentDTO;
import group.cc.df.model.DfFormField;
import group.cc.core.Service;

import java.util.List;


/**
 * @author gxd
 * @date 2019/01/10
 */
public interface DfFormFieldService extends Service<DfFormField> {
    List<DfFieldComponentDTO> findDynamicFormFieldsByFormId(Integer formId);
}
