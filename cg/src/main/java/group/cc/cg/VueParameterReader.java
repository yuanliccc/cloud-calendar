package group.cc.cg;

import com.yl.common.util.PropertiesReaderUtil;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2019/3/5.
 */
public class VueParameterReader extends  ParameterReader{
    public static final String VUE_CHINESE_NAME = "vue_chinese_name";

    public static final String VUE_PATH = "vue_path";

    public static final String VUE_MODULE_NAME = "vue_module_name";

    public static final String VUE_MODULE_FILED  = "vue_module_filed";

    public static final String VUE_MODULE_FILED_DIS = "vue_module_filed_dis";

    public static final String VUE_MODULE_FILED_DIS_CHINESE = "vue_module_filed_dis_chinese";

    private static String classpath = PropertiesReaderUtil.class.getResource("/").getPath();

    public static Map<String, String> read(String filename){
        // 读取 properties 文件
        Properties properties = null;
        try {
            properties = getProperties(classpath + "/" +filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        map.put(MODEL_PACKAGE_KEY, addPackage(map.get(BASE_PACKAGE_KEY),
                ifNullReturnEmpty((String) properties.get(MODEL_PACKAGE_KEY))));

        // 添加 model_package_path
        map.put(MODEL_PACKAGE_PATH_KEY, add(map.get(BASE_PACKAGE_PATH_KEY),
                packageConvertPath(ifNullReturnEmpty((String) properties.get(MODEL_PACKAGE_KEY)))));

        // 添加 service package
        map.put(SERVICE_PACKAGE_KEY, addPackage(map.get(BASE_PACKAGE_KEY),
                ifNullReturnEmpty((String) properties.get(SERVICE_PACKAGE_KEY))));

        // 添加 service_package_path
        map.put(SERVICE_PACKAGE_PATH_KEY, add(map.get(BASE_PACKAGE_PATH_KEY),
                packageConvertPath(ifNullReturnEmpty((String) properties.get(SERVICE_PACKAGE_KEY)))));

        // 添加 service impl package
        map.put(SERVICE_IMPL_KEY, addPackage(map.get(BASE_PACKAGE_KEY),
                ifNullReturnEmpty((String) properties.get(SERVICE_IMPL_KEY))));

        // 添加 service impl package_path
        map.put(SERVICE_IMPL_PATH_KEY, add(map.get(BASE_PACKAGE_PATH_KEY),
                packageConvertPath(ifNullReturnEmpty((String) properties.get(SERVICE_IMPL_KEY)))));

        // 添加 mapper package
        map.put(MAPPER_PACKAGE_KEY, addPackage(map.get(BASE_PACKAGE_KEY),
                ifNullReturnEmpty((String) properties.get(MAPPER_PACKAGE_KEY))));

        // 添加 mapper package_path
        map.put(MAPPER_PACKAGE_PATH_KEY, add(map.get(BASE_PACKAGE_PATH_KEY),
                packageConvertPath(ifNullReturnEmpty((String) properties.get(MAPPER_PACKAGE_KEY)))));

        // 添加 controller package
        map.put(CONTROLLER_PACKAGE_KEY, addPackage(map.get(BASE_PACKAGE_KEY),
                ifNullReturnEmpty((String) properties.get(CONTROLLER_PACKAGE_KEY))));

        // 添加 mapper package_path
        map.put(CONTROLLER_PACKAGE_PATH_KEY, add(map.get(BASE_PACKAGE_PATH_KEY),
                packageConvertPath(ifNullReturnEmpty((String) properties.get(CONTROLLER_PACKAGE_KEY)))));

        map.put(TABLES_KEY, ifNullThrowException((String)properties.get(TABLES_KEY), TABLES_KEY));

        map.put(AUTHOR_KEY, ifNullReturnEmpty((String)properties.get(AUTHOR_KEY)));

        //前台页面
        map.put(VUE_CHINESE_NAME, ifNullReturnEmpty((String)properties.get(VUE_CHINESE_NAME)));

        map.put(VUE_PATH, ifNullReturnEmpty((String)properties.get(VUE_PATH)));

        map.put(VUE_MODULE_NAME, ifNullReturnEmpty((String)properties.get(VUE_MODULE_NAME)));

        map.put(VUE_MODULE_FILED, ifNullReturnEmpty((String)properties.get(VUE_MODULE_FILED)));

        map.put(VUE_MODULE_FILED_DIS, ifNullReturnEmpty((String)properties.get(VUE_MODULE_FILED_DIS)));

        map.put(VUE_MODULE_FILED_DIS_CHINESE, ifNullReturnEmpty((String)properties.get(VUE_MODULE_FILED_DIS_CHINESE)));

        return map;
    }

    private static Properties getProperties(String filename) throws  Exception{
        Properties pps = new Properties();
        pps.load(new InputStreamReader(new FileInputStream(filename), "utf-8"));
        Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
        /*while(enum1.hasMoreElements()) {
            String strKey = (String) enum1.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }*/
        return pps;
    }

    /**
     * package 如 com.alibaba 转换为 com/alibaba
     * @param packageName 如 com.alibaba
     * @return
     */
    private static String packageConvertPath(String packageName) {

        return String.format("/%s/", packageName.contains(".")
                ? packageName.replaceAll("\\.", "/")
                : packageName);
    }

    /**
     * package 追加，如 com.alibaba, fastjson 追加为：com.alibaba.fastjson
     * @param pack 如：com.alibaba
     * @param add 如：fastjson 或者 .fastjson
     * @return
     */
    private static String addPackage(String pack, String add) {
        if(add == null || pack == null) {
            return "";
        }
        add = add.trim();

        if(add.equals("")) {
            return pack;
        }
        else if(add.startsWith(".")){
            return pack + add;
        }
        else {
            return pack + "." + add;
        }
    }


    /**
     * 文件路径追加，如 com/alibaba，fastjson 追加为 com/alibaba/fastjson
     * @param path 如：com/alibaba
     * @param add 如：fastjson 或者 /fastjson
     * @return
     */
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

        if (value == null || value.trim().equals("")) {
            throw new RuntimeException("The key : " + key + " has no value or config");
        }

        return value.trim();
    }
}
