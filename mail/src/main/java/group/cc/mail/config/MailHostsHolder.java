package group.cc.mail.config;

import com.yl.common.util.PropertiesReaderUtil;

import java.util.*;

/**
 * 邮箱列表 - 协议、服务器地址（这些信息由文件：mail-server.properties 提供）
 * @author YuanLi
 * @date 2018/12/16
 */
public class MailHostsHolder {

    public static final String configPath = "mail-server.properties";

    public static Map<String, Map<String, String>> mailHosts;

    static {
        // 读取 mail-server.properties 文件
        Properties properties = PropertiesReaderUtil.getProperties(configPath);
        // 获取 keys
        Enumeration enumeration = properties.propertyNames();

        mailHosts = new HashMap<>();

        // 遍历获取值
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();

            // 根据 comma 切割
            String[] hosts = properties.getProperty(key).split(",");

            Map<String, String> hostsMap = new HashMap<>();

            // 遍历切割后数组
            Arrays.asList(hosts).forEach((str) -> {
                // 根据 ： 切割
                String[] protocolAndHost = str.split(":");

                if(protocolAndHost.length == 2) {
                    hostsMap.put(protocolAndHost[0], protocolAndHost[1]);
                }
            });
            mailHosts.put(key, hostsMap);
        }
    }

}
