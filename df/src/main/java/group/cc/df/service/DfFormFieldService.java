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

    /**
     * 根据表单Id查询表单域信息
     * @param formId
     * @return
     */
    List<DfFieldComponentDTO> findDynamicFormFieldsByFormId(Integer formId);

    /**
     * 根据收集表的Id查询收集表的表单域信息
     * @param formId
     * @return
     */
    List<DfFormField> findCollectFormFieldByCollectFormId(Integer formId);
}
