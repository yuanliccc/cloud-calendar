package group.cc.occ.service.impl;

import group.cc.occ.dao.ModuleMapper;
import group.cc.occ.model.Module;
import group.cc.occ.model.Permission;
import group.cc.occ.model.dto.ModuleDto;
import group.cc.occ.service.ModuleService;
import group.cc.core.AbstractService;
import group.cc.occ.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author wangyuming
 * @date 2019/03/01
 */
@Service
@Transactional
public class ModuleServiceImpl extends AbstractService<Module> implements ModuleService {
    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private PermissionService permissionService;

    @Override
    public List<ModuleDto> getModules(Integer roleId) {
        List<Module> roleModules = moduleMapper.getModulesByRoleId(roleId);
        List<Module> systemModules = moduleMapper.getSystemModules();
        Map<Integer, List<Module>> map = new HashMap<>();

        Iterator<Module> iterator = roleModules.iterator();

        while(iterator.hasNext()){
            Module m = iterator.next();
            List<Module> t = map.get(m.getParent());

            if(t == null)
                t = new ArrayList<>();

            t.add(m);
            map.put(m.getParent(), t);
        }

        List<ModuleDto> dtos = new ArrayList<>();

        iterator = systemModules.iterator();

        while(iterator.hasNext()){
            Module m = iterator.next();
            ModuleDto dto = new ModuleDto();

            if(map.get(m.getId()) != null){
                dto.setModule(m);
                dto.setChildren(map.get(m.getId()));
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Override
    public void saveModule(Module module) throws Exception{
        Module t = this.findBy("modulekey", module.getModulekey());
        if(t != null)
            throw new Exception("modulekey已存在！");
        this.save(module);

        if("是".equals(module.getIssystem())) return;

        module = this.findBy("modulekey", module.getModulekey());

        Permission left = new Permission(0, module.getName() + "左侧展示",module.getModulekey() +
                "_left",module.getName() + "模块权限", module.getId());
        Permission add = new Permission(0, module.getName() + "新增",module.getModulekey() +
                "_add",module.getName() + "模块权限", module.getId());
        Permission display = new Permission(0, module.getName() + "查看",module.getModulekey() +
                "_display",module.getName() + "模块权限", module.getId());
        Permission delete = new Permission(0, module.getName() + "删除",module.getModulekey() +
                "_delete",module.getName() + "模块权限", module.getId());
        Permission edit = new Permission(0, module.getName() + "编辑",module.getModulekey() +
                "_edit",module.getName() + "模块权限", module.getId());
        permissionService.save(left);
        permissionService.save(add);
        permissionService.save(display);
        permissionService.save(delete);
        permissionService.save(edit);
    }

    @Override
    public void deleteBatch(List<Module> modules) {
        StringBuffer perSb = new StringBuffer();
        StringBuffer moduleSb = new StringBuffer();

        for (Module m: modules){
            if("否".equals(m.getIssystem())){
                perSb.append(m.getId() + ",");
            }
            moduleSb.append(m.getId() + ",");
        }

        if(perSb.length() > 0){
            perSb.deleteCharAt(perSb.length() - 1);
        }

        moduleSb.deleteCharAt(moduleSb.length() - 1);

        permissionService.deleteByModules(perSb.toString());
        moduleMapper.deleteBatch(moduleSb.toString());
    }

    public void deleteByModuleId(Integer moduleId) {
        Module module = this.findById(moduleId);
        if("否".equals(module.getIssystem())){
            permissionService.deleteByModuleId(moduleId);
        }
        this.deleteById(moduleId);
    }

    @Override
    public List<Module> getAllParent() {
        List<Module> systemModules = moduleMapper.getSystemModules();
        return systemModules;
    }

    @Override
    public List<Module> listByKey(String key, String value) {
        value = "%" + value + "%";
        List<Module> list = this.moduleMapper.listByKey(key, value);
        return list;
    }

    @Override
    public List<Module> findAllExceptSystemModule() {
        List<Module> list = this.moduleMapper.listByKey("ISSYSTEM", "否");
        return list;
    }
}
