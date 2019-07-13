/*
 * Copyright (c) 2019 YuanLi. All rights reserved.
 */

package group.cc.pcc.schedule;

import com.alibaba.fastjson.JSONObject;
import group.cc.pcc.model.PccUser;
import group.cc.pcc.service.PccTaskService;
import group.cc.rabbitmq.mail.MailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuanli
 * 日程到期提醒
 * 日程到期前10s
 */
@Component
public class TaskDeadMailScheduler {

    private static final Logger logger = LoggerFactory.getLogger(TaskDeadMailScheduler.class);

    @Autowired
    private PccTaskService pccTaskService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Scheduled(fixedRate = 10000)
    public void sendMail() {
        logger.info("开始日程到期检测");

        List<Map<String, Object>> willDeadTasks = pccTaskService.willDeadList();

        if(willDeadTasks.size() == 0) {
            return;
        }

        willDeadTasks.parallelStream().forEach((taskMap) -> {
            sendToMQ(taskMap);
            updateIsRemind(taskMap);
        });
    }

    private void updateIsRemind(Map<String, Object> taskMap) {
        Integer id = (Integer) taskMap.get("id");

        pccTaskService.isRemind(id);
    }

    private void sendToMQ(Map<String, Object> taskMap) {
        // 构建邮件信息对象
        MailMessage mailMessage = new MailMessage();

        PccUser pccUser = new PccUser();
        pccUser.setId((Integer) taskMap.get("pcc_user_id"));
        pccUser.setName((String)taskMap.get("user_name"));
        pccUser.setEmail((String)taskMap.get("email"));

        String[] receiversJSON = new String[1];
        receiversJSON[0] = JSONObject.toJSONString(pccUser);

        mailMessage.setToUsers(receiversJSON);
        mailMessage.setSubject("个人日历-日程到期提醒");
        mailMessage.setType("dead-task");

        Map<String, Object> content = new HashMap<>(2);
        content.put("content", taskMap.get("content"));
        content.put("endTime", taskMap.get("time"));

        mailMessage.setContent(JSONObject.toJSONString(content));

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        Message message = MessageBuilder
                .withBody(JSONObject.toJSONString(mailMessage).getBytes())
                .andProperties(messageProperties)
                .build();

        rabbitTemplate.convertAndSend("calendar.mail.exchange"
                , "calendar.mail.routing.key"
                , message);
    }

}
