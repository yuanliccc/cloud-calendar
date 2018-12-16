package group.cc.mail;

import com.yl.common.parse.Parser;
import com.yl.common.validate.Validator;

/**
 * 邮箱简单抽象实体
 * @author YuanLi
 */
public class Mail {

    private String prefix;

    private String serverName;

    private Mail(String prefix, String serverName) {
        this.prefix = prefix;
        this.serverName = serverName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * mail 建造器，唯一生成 Mail 对象的方法（提供邮箱的验证及 Mail 对象的构造）
     * @author YuanLi
     */
    public static class MailBuilder {

        private String mail;

        private Validator<String> mailValidator;

        private Parser<String, String[]> parser;

        public MailBuilder(String mail,
                           Validator<String> mailValidator,
                           Parser<String, String[]> mailParser) {
            this.mail = mail;
            this.mailValidator = mailValidator;
            this.parser = mailParser;
        }

        public Mail build() throws MailException {
            // 验证邮箱正确性
            if(!mailValidator.validate(mail)) {
                throw new MailException("Default mail test wrong, please check it!");
            }
            String[] mailParseResult = parser.parse(mail);

            return new Mail(mailParseResult[0], mailParseResult[1]);
        }
    }
}
