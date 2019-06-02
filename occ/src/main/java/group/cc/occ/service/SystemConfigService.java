package group.cc.occ.service;
import group.cc.occ.model.SystemConfig;
import java.util.List;
import group.cc.core.Service;


/**
 * @author wangyuming
 * @date 2019/04/27
 */
public interface SystemConfigService extends Service<SystemConfig> {
    public List<SystemConfig> listByKey(String key, String value);
    public void deleteBatch(List<SystemConfig> systemConfigs);
    public SystemConfig getValueByKey(String key);
}
