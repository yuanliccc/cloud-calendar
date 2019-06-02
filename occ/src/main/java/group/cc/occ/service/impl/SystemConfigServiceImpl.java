package group.cc.occ.service.impl;

import group.cc.occ.dao.SystemConfigMapper;
import group.cc.occ.model.SystemConfig;
import group.cc.occ.service.SystemConfigService;
import java.util.List;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author wangyuming
 * @date 2019/04/27
 */
@Service
@Transactional
public class SystemConfigServiceImpl extends AbstractService<SystemConfig> implements SystemConfigService {
    @Resource
    private SystemConfigMapper systemConfigMapper;

    @Override
    public List<SystemConfig> listByKey(String key, String value){
        value = "%" + value + "%";
        List<SystemConfig> list = systemConfigMapper.listByKey(key, value);
        return list;
    }

    @Override
    public void deleteBatch(List<SystemConfig> systemConfigs) {
        StringBuffer systemConfigSb = new StringBuffer();

        for (SystemConfig e: systemConfigs){
            systemConfigSb.append(e.getId() + ",");
        }
        systemConfigSb.deleteCharAt(systemConfigSb.length() - 1);

        systemConfigMapper.deleteBatch(systemConfigSb.toString());
    }

    @Override
    public SystemConfig getValueByKey(String key) {
        return this.systemConfigMapper.getValueByKey(key);
    }
}
