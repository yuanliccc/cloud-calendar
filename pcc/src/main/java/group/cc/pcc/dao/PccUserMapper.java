package group.cc.pcc.dao;

import group.cc.core.Mapper;
import group.cc.pcc.model.PccUser;
import org.springframework.stereotype.Component;

@Component
@org.apache.ibatis.annotations.Mapper
public interface PccUserMapper extends Mapper<PccUser> {
}