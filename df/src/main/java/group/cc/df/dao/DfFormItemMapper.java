package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.model.DfFormItem;

import java.util.List;

public interface DfFormItemMapper extends Mapper<DfFormItem> {
    List<DfFormItem> findDfFormItemsByFieldId(Integer fieldId);
}