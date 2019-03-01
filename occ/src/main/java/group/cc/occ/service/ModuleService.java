package group.cc.occ.service;
import group.cc.occ.model.Module;
import group.cc.core.Service;
import group.cc.occ.model.dto.ModuleDto;

import java.util.List;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
public interface ModuleService extends Service<Module> {
    public List<ModuleDto> getModules(Integer roleId);
}
