package group.cc.mail.configurer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * rabbit 配置
 * @author YuanLi
 */
@Configuration
@PropertySource({"classpath:common-env.properties"})
public class MailRabbitConfigurer {

    @Value("${cc.mail.rabbit.queue.name}")
    private String mailQueueName;

    @Value("${cc.mail.rabbit.exchange.name}")
    private String mailExchangeName;

    @Value("${cc.mail.rabbit.routing.key}")
    private String mailRoutingKey;

    @Bean
    public Queue mailQueue() {

        return new Queue(mailQueueName, true);
    }

    @Bean
    public DirectExchange mailDirectExchange() {

        return new DirectExchange(mailExchangeName, true, false);
    }

    @Bean
    public Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailDirectExchange()).with(mailRoutingKey);
    }
}
