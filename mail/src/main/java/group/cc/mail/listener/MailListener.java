package group.cc.mail.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 监听邮件
 * @author YuanLi
 */
@Component
public class MailListener {

    private Logger logger = LoggerFactory.getLogger(MailListener.class);

    @RabbitListener(queues = "calendar.mail.queue", containerFactory = "singleListenerContainer")
    public void consumeMail(@Payload byte[] message) {
        logger.info("处理邮件" + message);
        // 暂时不处理
    }

}
