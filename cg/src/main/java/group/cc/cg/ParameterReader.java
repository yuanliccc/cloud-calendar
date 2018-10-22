package group.cc.cg;

import com.yl.common.util.PropertiesReaderUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ParameterReader {

    /**
     * 如果是项目的子模块（子项目），请填写此名称，代码生成过程中，将确定生成的代码应该在哪个模块下。
     * 如果项目不存在子模块（子项目），请忽略。
     */
    public static final String MODULE_NAME_KEY = "module_name";

    /**
     *
     */
    public static final String BASE_PACKAGE_KEY = "base_package";

    public static final String MODEL_PACKAGE_KEY = "model_package";

    public static final String CONTROLLER_PACKAGE_KEY = "controller_package";

    public static final String SERVICE_PACKAGE_KEY = "service_package";

    public static final String SERVICE_IMPL_KEY = "service_impl_package";

    public static final String MAPPER_PACKAGE_KEY = "model_package";

    public static final String MAPPER_IMPLEMENTS_INTERFACE_KEY = "mapper_implements_interface";

    public static final String JDBC_URL_KEY = "jdbc_url";

    public static final String JDBC_USERNAME_KEY = "jdbc_username";

    public static final String JDBC_PASSWORD_KEY = "jdbc_password";

    public static final String JDBC_DRIVER_CLASS_KEY = "jdbc_driver_class";

    public static Map<String, String> read(String filename) {
        Properties properties = PropertiesReaderUtil.getProperties(filename);

        Map<String, String> map = new HashMap<>();

        return map;
    }

    private static String ifNullThrowException(String value, String key) {

        if(value == null || value.trim().equals("")) {
            throw new RuntimeException("The key : " + key + " has no value or config");
        }

        return value.trim();
    }

}
