package group.cc.mail.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 默认邮件发送方 bean 配置
 * @author YuanLi
 */
@Configuration
@PropertySource({"classpath:application-dev.properties"})
public class MailConfigurer {

    @Value("${cc.mail.default.mail}")
    private String defaultMailSender;

    @Value("${cc.mail.default.mail.password}")
    private String defaultMailSenderPassword;

    @Value("${cc.mail.default.mail.protocol}")
    private String defaultMailProtocol;

    @Bean(value = "defaultMailSender")
    public MailSender mailSender() {
        JavaMailSender mailSender = new JavaMailSenderImpl();

        return mailSender;
    }

}
