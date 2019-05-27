package group.cc.mail.configurer;

import com.yl.common.parse.Parser;
import com.yl.common.validate.Validator;
import group.cc.mail.Mail;
import group.cc.mail.MailException;
import group.cc.mail.config.MailHostsHolder;
import group.cc.mail.parse.parser.MailToUserValidator;
import group.cc.mail.validate.MailProtocolValidator;
import group.cc.mail.validate.MailValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
@PropertySource({"classpath:application.properties"})
public class MailConfigurer {

    @Value("${cc.mail.default.mail}")
    private String defaultMailSender;

    @Value("${cc.mail.default.mail.password}")
    private String defaultMailSenderPassword;

    @Value("${cc.mail.default.mail.protocol}")
    private String defaultMailProtocol;

    @Autowired
    private MailValidator mailValidator;

    @Autowired
    private MailProtocolValidator mailProtocolValidator;

    @Autowired
    private Parser<String, String[]> mailParser;

    @Bean(value = "defaultMailSender")
    public JavaMailSender mailSender() throws MailException {
        // 构造 mail 对象
        Mail mail = new Mail.MailBuilder(defaultMailSender, mailValidator, mailParser).build();

        // 验证邮箱协议正确性
        if(!mailProtocolValidator.validate(defaultMailProtocol)) {
            throw new MailException("Default mail protocol test wrong, please check it!");
        }

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setDefaultEncoding("utf-8");
        mailSender.setHost(MailHostsHolder.mailHosts.get(mail.getServerName()).get(defaultMailProtocol));
        mailSender.setUsername(defaultMailSender);
        mailSender.setPassword(defaultMailSenderPassword);

        return mailSender;
    }

}
