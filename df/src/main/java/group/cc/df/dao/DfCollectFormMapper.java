package group.cc.df.dao;

import group.cc.core.Mapper;
import group.cc.df.model.DfCollectForm;

public interface DfCollectFormMapper extends Mapper<DfCollectForm> {

    int saveCollectForm(DfCollectForm collectForm);
}