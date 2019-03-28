package group.cc.occ.service;
import group.cc.occ.model.Orgcalender;
import java.util.List;
import group.cc.core.Service;
import group.cc.occ.model.dto.LoginUserDto;


/**
 * @author wangyuming
 * @date 2019/03/28
 */
public interface OrgcalenderService extends Service<Orgcalender> {
    public List<Orgcalender> findAllByLoginOrgId(LoginUserDto login);
    public List<Orgcalender> listByKey(String key, String value, LoginUserDto login);
    public void deleteBatch(List<Orgcalender> orgcalenders);
}
