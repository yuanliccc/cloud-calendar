package group.cc.occ.config;

import group.cc.occ.util.InitUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by wangyuming on 2019/3/22.
 */
@WebListener
public class UrlListenner implements ServletContextListener {
    private Log log = LogFactory.getLog(UrlListenner.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            List<String> URL = new ArrayList<>();
            Properties properties = PropertiesLoaderUtils.loadAllProperties("allowUrl.properties");
            for (Object key : properties.keySet()) {
                String keyStr = key.toString();
                try {
                    URL.add(properties.getProperty(keyStr));
                }catch (Exception e) {
                    log.error(e.getMessage());
                }
            }

            log.info("allow URL " + URL);
            InitUtil.setUrl(URL);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
