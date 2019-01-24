package group.cc.pcc.service;
import group.cc.pcc.model.PccAdditionalInfoType;
import group.cc.core.Service;

import java.util.List;
import java.util.Map;


/**
 * @author yuanli
 * @date 2019/01/15
 */
public interface PccAdditionalInfoTypeService extends Service<PccAdditionalInfoType> {

    List<Map<String,Object>> list(Integer pccScheduleId);
}
