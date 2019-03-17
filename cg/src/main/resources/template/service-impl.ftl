package ${serviceImplPackage};

import ${basePackage}.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import java.util.List;
import group.cc.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author ${author}
 * @date ${date}
 */
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Override
    public List<${modelNameUpperCamel}> listByKey(String key, String value){
        value = "%" + value + "%";
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Mapper.listByKey(key, value);
        return list;
    }

    @Override
    public void deleteBatch(List<${modelNameUpperCamel}> ${modelNameLowerCamel}s) {
        StringBuffer ${modelNameLowerCamel}Sb = new StringBuffer();

        for (${modelNameUpperCamel} e: ${modelNameLowerCamel}s){
            ${modelNameLowerCamel}Sb.append(e.getId() + ",");
        }
        ${modelNameLowerCamel}Sb.deleteCharAt(${modelNameLowerCamel}Sb.length() - 1);

        ${modelNameLowerCamel}Mapper.deleteBatch(${modelNameLowerCamel}Sb.toString());
    }
}
