package group.cc.mail.validate;

import com.yl.common.validate.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * mail 验证器
 * @author YuanLi
 */
@Component("mailValidator")
@PropertySource({"classpath:application.properties"})
public class MailValidator implements Validator<String> {

    @Value("${cc.mail.mail.pattern}")
    private String mailPattern;

    @Override
    public boolean validate(String s) {

        if(s == null || s.trim().equals("")) {
            return false;
        }

        return s.matches(mailPattern);
    }
}
