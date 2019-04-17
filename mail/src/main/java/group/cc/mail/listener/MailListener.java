package group.cc.mail.listener;

import com.alibaba.fastjson.JSONObject;
import group.cc.mail.parse.parser.SimpleContentParser;
import group.cc.rabbitmq.mail.MailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 监听邮件
 * @author YuanLi
 */
@Component("mailListener")
public class MailListener {

    @Autowired
    private MailSender defaultMailSender;

    private Logger logger = LoggerFactory.getLogger(MailListener.class);

    @RabbitListener(queues = "calendar.mail.queue", containerFactory = "singleListenerContainer")
    public void consumeMail(@Payload byte[] message) throws Exception {
        String str = new String(message, "utf-8");
        MailMessage mailMessage = JSONObject.toJavaObject(JSONObject.parseObject(str)
                , MailMessage.class);
        logger.info("收到消息 => " + str);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailMessage.fromUserJSON("email").toString());
        simpleMailMessage.setSubject("任务创建");
        new SimpleContentParser().parse(simpleMailMessage, mailMessage);
        defaultMailSender.send(simpleMailMessage);
    }

    

}
