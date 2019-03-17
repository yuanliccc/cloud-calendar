package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.model.DfFormItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DfFormItemMapper extends Mapper<DfFormItem> {
    List<DfFormItem> findDfFormItemsByFieldId(Integer fieldId);

    int updateFormItem(DfFormItem dfFormItem);

    List<DfFormItem> findUselessFormItems(@Param("formItemIdList") List<Integer> formItemIdList, @Param("formFieldId") Integer formFieldId);
}