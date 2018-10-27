package group.cc.cg;

import com.yl.common.util.PropertiesReaderUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 代码生成参数读取类，读取文件：code-generator.properties。
 * <ul>代码生成参数说明
 * <li>
 *     1. 代码生成与传统的文件写操作无任何差别。
 * </li>
 * <li>
 *     2. 参数设置将确定代码生成的路径。
 * </li>
 * <li>
 *     3. 处理阶段将通过 System.getProperty("user.dir") 获取项目工作目录。
 *     希望您通过 main 方法调用代码生成以免获取的工作目录错误。
 * </li>
 * </ul>
 * @author YuanLi
 */
public class ParameterReader {

    /**
     * module 名称，如果是子模块，请填写，否则不填写或忽略
     */
    public static final String MODULE_NAME_KEY = "module_name";

    /**
     * 在获取工作目录后，我们希望得到 Java 文件路径，如在 maven 项目中的 src/main/java
     */
    public static final String JAVA_PATH_KEY = "java_path";

    /**
     * 在获取工作目录后，我们希望得到 resources 文件路径，如在 maven 项目中的 src/main/resources
     */
    public static final String RESOURCES_PATH_KEY = "java_path";

    /**
     * 代码生成后的基础包，如 com.alibaba，代码将生成在此包名下的子包或该包下，具体看下面是否配置完整。
     */
    public static final String BASE_PACKAGE_KEY = "base_package";

    public static final String BASE_PACKAGE_PATH_KEY = "base_package_path";

    /**
     * 实体所在包 = 基础包 + 此处配置的包名，如配置： model，那么包名 = com.alibaba.model
     */
    public static final String MODEL_PACKAGE_KEY = "model_package";

    public static final String MODEL_PACKAGE_PATH_KEY = "model_package_path";

    /**
     * 表示控制层代码所在包。
     */
    public static final String CONTROLLER_PACKAGE_KEY = "controller_package";

    public static final String CONTROLLER_PACKAGE_PATH_KEY = "controller_package_path";

    /**
     * 表示业务层代码所在包。
     */
    public static final String SERVICE_PACKAGE_KEY = "service_package";

    public static final String SERVICE_PACKAGE_PATH_KEY = "service_package_path";

    /**
     * 表示业务层实现类代码所在包。
     */
    public static final String SERVICE_IMPL_KEY = "service_impl_package";

    public static final String SERVICE_IMPL_PATH_KEY = "service_impl_package_path";

    /**
     * 表示 Mapper 代码所在包。
     */
    public static final String MAPPER_PACKAGE_KEY = "mapper_package";

    public static final String MAPPER_PACKAGE_PATH_KEY = "mapper_package_path";

    /**
     * 表示 Mapper 需实现的接口。
     */
    public static final String MAPPER_IMPLEMENTS_INTERFACE_KEY = "mapper_implements_interface";

    public static final String JDBC_URL_KEY = "jdbc_url";

    public static final String JDBC_USERNAME_KEY = "jdbc_username";

    public static final String JDBC_PASSWORD_KEY = "jdbc_password";

    public static final String JDBC_DRIVER_CLASS_KEY = "jdbc_driver_class";

    public static Map<String, String> read(String filename) {
        // 读取 properties 文件
        Properties properties = PropertiesReaderUtil.getProperties(filename);

        Map<String, String> map = new HashMap<>();

        StringBuilder base_path = new StringBuilder(System.getProperty("user.dir"));

        String module_name = properties.getProperty(MODULE_NAME_KEY);

        // 对 module_name 进行处理
        if(module_name != null && !module_name.trim().equals("")) {
            base_path.append("\\" + module_name.trim());
        }

        map.put(MAPPER_IMPLEMENTS_INTERFACE_KEY,
                ifNullReturnEmpty((String)properties.get(MAPPER_IMPLEMENTS_INTERFACE_KEY)));

        map.put(JDBC_URL_KEY,
                ifNullThrowException((String) properties.get(JDBC_URL_KEY), JDBC_URL_KEY));

        map.put(JDBC_USERNAME_KEY,
                ifNullThrowException((String) properties.get(JDBC_USERNAME_KEY), JDBC_USERNAME_KEY));

        map.put(JDBC_PASSWORD_KEY,
                ifNullThrowException((String) properties.get(JDBC_PASSWORD_KEY), JDBC_PASSWORD_KEY));

        map.put(JDBC_DRIVER_CLASS_KEY,
                ifNullThrowException((String) properties.get(JDBC_DRIVER_CLASS_KEY), JDBC_DRIVER_CLASS_KEY));

        // 添加 java path
        map.put(JAVA_PATH_KEY, add(base_path.toString(),
                ifNullReturnEmpty((String) properties.get(JAVA_PATH_KEY))));

        // 添加 resources path
        map.put(RESOURCES_PATH_KEY, add(base_path.toString(),
                ifNullReturnEmpty((String) properties.get(RESOURCES_PATH_KEY))));

        map.put(BASE_PACKAGE_KEY, ifNullReturnEmpty((String)properties.get(BASE_PACKAGE_KEY)));

        // 添加 base_package path
        map.put(BASE_PACKAGE_PATH_KEY, add(map.get(JAVA_PATH_KEY),
                packageConvertPath(ifNullReturnEmpty((String) properties.get(BASE_PACKAGE_KEY)))));

        // 添加 model package
        map.put(MODEL_PACKAGE_KEY, add(map.get(BASE_PACKAGE_KEY),
                ifNullReturnEmpty((String) properties.get(MODEL_PACKAGE_KEY))));

        // 添加 model_package_path
        map.put(MODEL_PACKAGE_PATH_KEY, add(map.get(BASE_PACKAGE_PATH_KEY),
                packageConvertPath(ifNullReturnEmpty((String) properties.get(MODEL_PACKAGE_KEY)))));

        // 添加 service package
        map.put(SERVICE_PACKAGE_KEY, add(map.get(BASE_PACKAGE_KEY),
                ifNullReturnEmpty((String) properties.get(SERVICE_PACKAGE_KEY))));

        // 添加 service_package_path
        map.put(SERVICE_PACKAGE_PATH_KEY, add(map.get(BASE_PACKAGE_PATH_KEY),
                packageConvertPath(ifNullReturnEmpty((String) properties.get(SERVICE_PACKAGE_KEY)))));

        // 添加 service impl package
        map.put(SERVICE_IMPL_KEY, add(map.get(BASE_PACKAGE_KEY),
                ifNullReturnEmpty((String) properties.get(SERVICE_IMPL_KEY))));

        // 添加 service impl package_path
        map.put(SERVICE_IMPL_PATH_KEY, add(map.get(BASE_PACKAGE_PATH_KEY),
                packageConvertPath(ifNullReturnEmpty((String) properties.get(SERVICE_IMPL_KEY)))));

        // 添加 mapper package
        map.put(MAPPER_PACKAGE_KEY, add(map.get(BASE_PACKAGE_KEY),
                ifNullReturnEmpty((String) properties.get(MAPPER_PACKAGE_KEY))));

        // 添加 mapper package_path
        map.put(MAPPER_PACKAGE_PATH_KEY, add(map.get(BASE_PACKAGE_PATH_KEY),
                packageConvertPath(ifNullReturnEmpty((String) properties.get(MAPPER_PACKAGE_KEY)))));

        // 添加 controller package
        map.put(CONTROLLER_PACKAGE_KEY, add(map.get(BASE_PACKAGE_KEY),
                ifNullReturnEmpty((String) properties.get(CONTROLLER_PACKAGE_KEY))));

        // 添加 mapper package_path
        map.put(CONTROLLER_PACKAGE_PATH_KEY, add(map.get(BASE_PACKAGE_PATH_KEY),
                packageConvertPath(ifNullReturnEmpty((String) properties.get(CONTROLLER_PACKAGE_KEY)))));

        return map;
    }

    private static String packageConvertPath(String packageName) {

        return String.format("/%s/", packageName.contains(".")
                        ? packageName.replaceAll("\\.", "/")
                        : packageName);
    }

    private static String add(String path, String add) {
        if(add == null || path == null) {
            return "";
        }
        add = add.trim();

        if(add.equals("")) {
            return path;
        }
        else if(add.startsWith("\\")){
            return path + add;
        }
        else {
            return path + "\\" + add;
        }
    }

    /**
     * 如果 null 返回空值，其余返回 value.trim()
     * @param value 值
     * @return value.trim()
     */
    private static String ifNullReturnEmpty(String value) {
        return value == null ? "" : value.trim();
    }

    /**
     * 如果为 null 或 空值，抛出异常，否则返回。
     * @param value 值
     * @param key 健
     * @return value.trim()
     */
    private static String ifNullThrowException(String value, String key) {

        if(value == null || value.trim().equals("")) {
            throw new RuntimeException("The key : " + key + " has no value or config");
        }

        return value.trim();
    }

}
