package group.cc.df.service.impl;

import group.cc.df.dao.DfFormFieldMapper;
import group.cc.df.dao.DfFormItemMapper;
import group.cc.df.dto.DfFieldComponentDTO;
import group.cc.df.model.DfFormField;
import group.cc.df.model.DfFormItem;
import group.cc.df.service.DfFormFieldService;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author gxd
 * @date 2019/01/10
 */
@Service
@Transactional
public class DfFormFieldServiceImpl extends AbstractService<DfFormField> implements DfFormFieldService {
    @Resource
    private DfFormFieldMapper dfFormFieldMapper;

    @Resource
    private DfFormItemMapper dfFormItemMapper;

    @Override
    public List<DfFieldComponentDTO> findDynamicFormFieldsByFormId(Integer formId) {
        // 根据表单Id查询出表单中的所有表单域
        List<DfFormField> formFields = dfFormFieldMapper.findDynamicFormFieldsByFormId(formId);

        // 根据每个表单域的类型,查询子项目,附件等信息
        List<DfFieldComponentDTO> list = handleFormFields(formFields, "formField");

        return list;
    }

    private List<DfFieldComponentDTO> handleFormFields(List<DfFormField> formFields, String type) {
        if (formFields == null) {
            return null;
        }

        List<DfFieldComponentDTO> list = new ArrayList<>();

        for (DfFormField field: formFields) {
            DfFieldComponentDTO componentDTO = new DfFieldComponentDTO();

            componentDTO.setDfFormField(field);

            // 查询条目
            if (field.getType().equals("checkbox") || field.getType().equals("radio")
                  || field.getType().equals("select")) {
                List<DfFormItem> dfFormItems = new ArrayList<>();
                if ("formField".equals(type)) {
                    // 查询条目信息
                    dfFormItems = dfFormItemMapper.findDfFormItemsByFieldId(field.getId());
                } else if ("collectFormId".equals(type)) {
                    dfFormItems = dfFormItemMapper.findCollectFormItemsByCollectFormFieldId(field.getId());
                }

                // 对条目进行排序
                Collections.sort(dfFormItems);

                componentDTO.setDfFormItems(dfFormItems);

            }

            // 如果是文件类型再查询文件信息
            if (field.getType().equals("file")) {
            }

            list.add(componentDTO);
        }
        return list;
    }

    @Override
    public List<DfFieldComponentDTO> findCollectFormFieldByCollectFormId(Integer formId) {
        List<DfFormField> formFields = this.dfFormFieldMapper.findCollectFormFieldByCollectFormId(formId);
        // 根据每个表单域的类型,查询子项目,附件等信息
        List<DfFieldComponentDTO> list = handleFormFields(formFields, "collectFormId");
        return list;
    }
}
