package ${servicePackage};
import ${basePackage}.model.${modelNameUpperCamel};
import java.util.List;
import group.cc.core.Service;


/**
 * @author ${author}
 * @date ${date}
 */
public interface ${modelNameUpperCamel}Service extends Service<${modelNameUpperCamel}> {
    public List<${modelNameUpperCamel}> listByKey(String key, String value);
    public void deleteBatch(List<${modelNameUpperCamel}> ${modelNameLowerCamel}s);
}
