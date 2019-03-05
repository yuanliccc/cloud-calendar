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

    public List<Module> listByKey(String key, String value);

    public List<Module> getAllParent();

    public void saveModule(Module module)throws Exception;

    public void deleteByModuleId(Integer moduleId);

    public void deleteBatch(List<Module> modules);
}
