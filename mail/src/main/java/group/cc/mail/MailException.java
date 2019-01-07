package group.cc.mail;

/**
 * mail 模块不可检查异常
 * @author YuanLi
 */
public class MailException extends Exception {

    public MailException() {
        super();
    }

    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable e) {
        super(message, e);
    }

    public MailException(Throwable e) {
        super(e);
    }

}
