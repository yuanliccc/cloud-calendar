package group.cc.mail.config;

import com.yl.common.util.PropertiesReaderUtil;

import java.util.*;

/**
 * 邮箱列表 - 协议、服务器地址
 * @author YuanLi
 */
public class MailHostsHolder {

    public static final String configPath = "mail-server.properties";

    public static Map<String, Map<String, String>> mailHosts;

    static {
        Properties properties = PropertiesReaderUtil.getProperties(configPath);
        Enumeration enumeration = properties.propertyNames();

        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();

            String[] hosts = properties.getProperty(key).split(",");

            Map<String, String> hostsMap = new HashMap<>();

            Arrays.asList(hosts).forEach((str) -> {
                String[] protocolAndHost = str.split(":");

                if(protocolAndHost.length == 2) {
                    hostsMap.put(protocolAndHost[0], protocolAndHost[1]);
                }
            });
            mailHosts.put(key, hostsMap);
        }
    }

}
