package group.cc.mail.validate;

import com.yl.common.validate.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 邮箱协议验证器
 * @author YuanLi
 */
@Component("mailProtocolValidator")
@PropertySource({"classpath:application.properties"})
public class MailProtocolValidator implements Validator<String> {

    @Value("${cc.mail.mail.protocol.pattern}")
    private String mailProtocolPattern;

    @Override
    public boolean validate(String s) {

        if(s == null || s.trim().equals("")) {
            return false;
        }

        return s.matches(mailProtocolPattern);
    }
}
