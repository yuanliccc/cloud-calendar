package group.cc.mail.parse.parser;

import group.cc.mail.MailException;
import group.cc.mail.validate.MailValidator;
import group.cc.mail.validate.Validator;
import group.cc.rabbitmq.mail.MailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("mailToUserValidator")
public class MailToUserValidator implements Validator<MailMessage, String[]> {

    private MailValidator mailValidator = new MailValidator();

    private static final Logger log = LoggerFactory.getLogger(MailToUserValidator.class);

    @Override
    public String[] validate(MailMessage mailMessage) throws MailException {

        String[] toUsers = mailMessage.getToUsers();

        if(toUsers == null) {
            throw new MailException("未配置邮件接收人");
        }
        String[] toUsersMail = mailMessage.toUsersJSON("email");

        if(toUsersMail == null) {
            throw new MailException("接收人邮箱地址为空");
        }
        else {
            Arrays.asList(toUsersMail).forEach((mail) -> {
                if(!mailValidator.validate(mail)) {
                    log.error("邮箱 => " + mail + " 不满足正则匹配");
                }
            });
        }

        return toUsersMail;
    }

}
