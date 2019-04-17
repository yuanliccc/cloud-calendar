/*
 * Copyright (c) 2019 YuanLi. All rights reserved.
 */

package group.cc.mail.parse.parser;

import group.cc.mail.MailException;
import group.cc.mail.parse.ContentParser;
import group.cc.rabbitmq.mail.MailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 含附件内容的解析器
 * @author yuanli
 */
@Component("attachmentContentParser")
public class AttachmentContentParser implements ContentParser {

    @Autowired
    private MailToUserValidator mailToUserValidator;

    public void parse(MimeMessageHelper helper, MailMessage mailMessage) throws Exception {
        // 验证接收人邮箱正确性：是否存在，存在是否正确
        String[] toUsersMail = mailToUserValidator.validate(mailMessage);

        helper.setTo(toUsersMail);
        helper.setSubject(mailMessage.getSubject());
        helper.setText(mailMessage.getContent());

        List<Exception> exceptions
                = new ArrayList<>(mailMessage.getAttachments().size() / 2);
        mailMessage.attachmentsJSON("url").forEach((url) -> {
            String path = (String) url;

            FileSystemResource file = new FileSystemResource(path);
            try {
                helper.addAttachment("附件", file);
            } catch (MessagingException e) {
                e.printStackTrace();
                exceptions.add(e);
            }
        });

        if(exceptions.size() != 0) {
            throw new MailException("获取附件过程中出现异常");
        }
    }
}
