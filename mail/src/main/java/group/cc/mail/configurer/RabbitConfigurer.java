package group.cc.mail.configurer;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:common-env.properties"})
public class RabbitConfigurer {

    @Value("${cc.mail.rabbit.queue.name}")
    private String mailQueueName;

    @Bean
    public Queue mailQueue() {

        return new Queue(mailQueueName, true);
    }

}
