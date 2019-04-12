package group.cc.rabbitmq.mail;

import group.cc.core.json.JSONUtil;
import group.cc.rabbitmq.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件消息抽象
 * @author yuanli
 */
public class MailMessage extends Message {

    /**
     * json
     */
    private String fromUser;

    /**
     * json
     */
    private String toUser;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 邮件内容解析器，类名即可（消费端将直接获取 spring 容器内相应名称的 bean 调用 parse 方法进行处理，此过程不做任何判断）
     */
    private String contentParser;

    /**
     * List<json>
     */
    private List<String> attachments;

    public MailMessage(String content, List<String> attachments) {
        this.content = content;
        this.attachments = attachments;
    }

    public MailMessage(String content) {
        this.content = content;
    }

    public MailMessage(List<String> attachments) {
        this.attachments = attachments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getContentParser() {
        return contentParser;
    }

    public void setContentParser(String contentParser) {
        this.contentParser = contentParser;
    }

    /**
     * 从 {@link #fromUser} JSON 字符串中获取指定 key 的 value
     * @param key 指定 key
     * @return value
     */
    public Object fromUserJSON(Object key) {
        return JSONUtil.get(fromUser, key);
    }

    /**
     * 从 {@link #toUser} JSON 字符串中获取指定 key 的 value
     * @param key 指定 key
     * @return value
     */
    public Object toUserJSON(Object key) {
        return JSONUtil.get(toUser, key);
    }

    /**
     * 从 {@link #attachments} JSON 字符串列表中获取指定 key 的 value 列表
     * @param key 指定 key
     * @return value 列表
     */
    public List<Object> attachmentsJSON(Object key) {
        List<Object> list = new ArrayList<>(attachments.size());

        attachments.forEach((attachment) -> {
            list.add(JSONUtil.get(attachment, key));
        });

        return list;
    }
}