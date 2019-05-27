package group.cc.mail.listener;

import com.alibaba.fastjson.JSONObject;
import freemarker.template.Template;
import group.cc.mail.parse.parser.MailToUserValidator;
import group.cc.rabbitmq.mail.MailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 监听邮件
 * @author YuanLi
 */
@Component("mailListener")
public class MailListener {

    @Autowired
    private JavaMailSender defaultMailSender;

    @Autowired
    private FreeMarkerConfigurer configurer;

    private Logger logger = LoggerFactory.getLogger(MailListener.class);

    @RabbitListener(queues = "calendar.mail.queue", containerFactory = "singleListenerContainer")
    public void consumeMail(@Payload byte[] message) throws Exception {
        String str = new String(message, "utf-8");
        MailMessage mailMessage = JSONObject.toJavaObject(JSONObject.parseObject(str)
                , MailMessage.class);
        logger.info("收到消息 => " + str);

        String type = mailMessage.getType();

        switch (type) {
            case "new-schedule" : {
                sendNewScheduleMailHtml(mailMessage);
                break;
            }
            case "complete-schedule" : {
                sendCompleteScheduleMailHtml(mailMessage);
                break;
            }
            default: {
                sendDefaultMail(mailMessage);
            }
        }
    }

    private void sendCompleteScheduleMailHtml(MailMessage mailMessage) {
        MimeMessage mimeMessage = defaultMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(mailMessage.fromUserJSON("email").toString());
            helper.setSubject(mailMessage.getSubject());

            String[] toUsersMail = new MailToUserValidator().validate(mailMessage);
            helper.setTo(toUsersMail);

            Template template = configurer.getConfiguration().getTemplate("mail-complete-schedule.vm");
            String text = FreeMarkerTemplateUtils
                    .processTemplateIntoString(template, sendNewScheduleMailMap(mailMessage));
            helper.setText(text, true);
            defaultMailSender.send(mimeMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("邮件处理错误", e);
        }
    }

    private void sendDefaultMail(MailMessage mailMessage) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailMessage.fromUserJSON("email").toString());
        simpleMailMessage.setSubject(mailMessage.getSubject());

        MailToUserValidator mailToUserValidator = new MailToUserValidator();
        try {
            // 验证接收人邮箱正确性：是否存在，存在是否正确
            String[] toUsersMail = mailToUserValidator.validate(mailMessage);

            simpleMailMessage.setTo(toUsersMail);
            simpleMailMessage.setText(mailMessage.getContent());
            defaultMailSender.send(simpleMailMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("邮件处理错误", e);
        }
    }


    private void sendNewScheduleMailHtml(MailMessage mailMessage) {
        MimeMessage mimeMessage = defaultMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(mailMessage.fromUserJSON("email").toString());
            helper.setSubject(mailMessage.getSubject());

            String[] toUsersMail = new MailToUserValidator().validate(mailMessage);
            helper.setTo(toUsersMail);

            Template template = configurer.getConfiguration().getTemplate("mail-new-schedule.vm");
            String text = FreeMarkerTemplateUtils
                    .processTemplateIntoString(template, sendNewScheduleMailMap(mailMessage));
            helper.setText(text, true);
            defaultMailSender.send(mimeMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("邮件处理错误", e);
        }
    }

    private void sendNewScheduleMail(MailMessage mailMessage) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailMessage.fromUserJSON("email").toString());
        simpleMailMessage.setSubject(mailMessage.getSubject());

        MailToUserValidator mailToUserValidator = new MailToUserValidator();
        try {
            // 验证接收人邮箱正确性：是否存在，存在是否正确
            String[] toUsersMail = mailToUserValidator.validate(mailMessage);

            simpleMailMessage.setTo(toUsersMail);
            Template template = configurer.getConfiguration().getTemplate("mail-new-schedule.vm");
            String text = FreeMarkerTemplateUtils
                    .processTemplateIntoString(template, sendNewScheduleMailMap(mailMessage));
            simpleMailMessage.setText(text);
            defaultMailSender.send(simpleMailMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("邮件处理错误", e);
        }
    }

    private Map<String, Object> sendNewScheduleMailMap(MailMessage mailMessage) {
        Map<String, Object> res = new HashMap<>();

        res.put("fromUser", mailMessage.fromUserJSON("name"));
        res.put("content", mailMessage.fromContentJSON("content"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatTime = simpleDateFormat.format(new Date((long)mailMessage.fromContentJSON("deadline")));

        res.put("endTime", formatTime);

        return res;
    }
    

}
