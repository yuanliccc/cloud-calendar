package group.cc.df.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 高旭东 on 2019/3/25.
 */
@Configuration
public class ShiroConfiguration {

    @Value("${shiro.loginUrl}")
    private String loginUrl;

    @Value("${shiro.successUrl}")
    private String successUrl;

    @Value("${shiro.unauthorizedUrl}")
    private String unauthorizedUrl;

    @Bean
    public SelfRealm realm() {
        return new SelfRealm();
    }

    /**
     * 在使用shiro-spring-boot 1.4时,返回类型是SecurityManage会报错,
     * 直接引用shiro-spring则不报错
     * @param realm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(SelfRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);

        return securityManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会寻找web工程根目录下的/login.jsp页面
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        // 成功后的回调目录
        shiroFilterFactoryBean.setSuccessUrl(successUrl);
        // 未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);

        // 拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/api/df/logout", "logout");
        // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 :这是一个坑呢，一不小心代码就不好使了;
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }
}
