package group.cc.occ.config;

/**
 * Created by Administrator on 2019/3/3.
 */
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ Author     ：scw
 * @ Date       ：Created in 下午 2:48 2018/6/17 0017
 * @ Description：用于配置分页插件的使用
 * @ Modified By：
 * @Version: $version$
 */
@Configuration
public class PgeHeplerConfig {
    //将分页插件注入到容器中
    @Bean
    public PageHelper pageHelper() {
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        new SqlSessionFactoryBean().setPlugins(new Interceptor[]{pageHelper});
        return pageHelper;
    }

}