package group.cc.mail.parse.parser;

import group.cc.mail.MailException;
import group.cc.mail.parse.ContentParser;
import group.cc.rabbitmq.mail.MailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 简单邮件内容解析器
 * @author yuanli
 */
@Component("simpleContentParser")
public class SimpleContentParser implements ContentParser {

    private MailToUserValidator mailToUserValidator = new MailToUserValidator();

    public void parse(SimpleMailMessage message, MailMessage mailMessage) throws MailException {
        // 验证接收人邮箱正确性：是否存在，存在是否正确
        String[] toUsersMail = mailToUserValidator.validate(mailMessage);

        message.setTo(toUsersMail);
        message.setSubject(mailMessage.getSubject());
        message.setText(mailMessage.getContent());
    }


}
