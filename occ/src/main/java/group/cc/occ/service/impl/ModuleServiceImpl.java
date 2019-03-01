package group.cc.occ.service.impl;

import group.cc.occ.dao.ModuleMapper;
import group.cc.occ.model.Module;
import group.cc.occ.model.dto.ModuleDto;
import group.cc.occ.service.ModuleService;
import group.cc.core.AbstractService;
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
}
