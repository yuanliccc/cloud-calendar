package group.cc.mail;

import com.yl.common.parse.Parser;

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

    public static class MailParser implements Parser<String, Mail> {

        @Override
        public Mail parse(String mail) {
            return null;
        }
    }
}
