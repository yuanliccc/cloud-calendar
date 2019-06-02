/*
 * Copyright (c) 2019 YuanLi. All rights reserved.
 */

package group.cc.pcc.schedule;

import com.alibaba.fastjson.JSONObject;
import group.cc.pcc.service.PccScheduleService;
import group.cc.pcc.service.PccScheduleUserService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuanli
 * 任务到期提醒
 * 任务结束前五分钟提醒
 */
@Component
public class TaskDeadMailRemindScheduler {

    private static final Logger logger = LoggerFactory.getLogger(TaskDeadMailRemindScheduler.class);

    @Autowired
    private PccScheduleService pccScheduleService;

    @Autowired
    private PccScheduleUserService pccScheduleUserService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 每两分钟进行任务检测
     */
    @Scheduled(fixedRate = 120000)
    public void remindTaskDead() {
        logger.info("任务到期检测开始");
        List<Map<String, Object>> willDeadTasks = pccScheduleService.willDeadSchedule(300l);
        logger.info("到期任务列表: " + willDeadTasks.toString());

        if(willDeadTasks.size() == 0) {
            return;
        }

        // 分组
        Map<Integer, List<Map<String, Object>>> groups = willDeadTasks
                .parallelStream()
                .collect(Collectors.groupingBy(item -> (Integer)item.get("id")));

        groups.forEach((key, value) -> {
            sendToMQ(parse(value));
            updateIsRemind(value);
        });
    }

    private Map<String, Object> parse(List<Map<String, Object>> items) {
        Map<String, Object> res = new HashMap<>();

        if(items.size() >= 1) {
            res.put("id", items.get(0).get("id"));
            res.put("content", items.get(0).get("content"));
            res.put("deadline", items.get(0).get("deadline"));
        }
        else {
            return res;
        }
        List<Map<String, Object>> receivers = new ArrayList<>();
        items.forEach(item -> {
            Map<String, Object> receiver = new HashMap<>();

            receiver.put("name", item.get("name"));
            receiver.put("email", item.get("email"));
            receiver.put("psu_id", item.get("psu_id"));

            receivers.add(receiver);
        });
        res.put("receivers", receivers);

        return res;
    }

    private void updateIsRemind(List<Map<String, Object>> items) {
        items.forEach(item -> {
            pccScheduleUserService.remind((Integer)item.get("psu_id"));
        });
    }

    private void sendToMQ(Map<String, Object> item) {
        // 构建邮件信息对象
        MailMessage mailMessage = new MailMessage();

        List<Map<String, Object>> receivers = (List<Map<String, Object>>) item.get("receivers");

        String[] receiversJSON = new String[receivers.size()];

        for(int i = 0; i < receivers.size(); i++) {
            receiversJSON[i] = JSONObject.toJSONString(receivers.get(i));
        }

        mailMessage.setToUsers(receiversJSON);
        mailMessage.setSubject("个人日历-任务预到期提醒");
        mailMessage.setType("will-dead-schedule");
        mailMessage.setContent(JSONObject.toJSONString(item.get("content")));

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
