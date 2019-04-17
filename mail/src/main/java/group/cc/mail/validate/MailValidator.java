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
public class MailValidator implements Validator<String> {

    private String mailPattern = "^[\\w-]+@[\\w-]+([.][\\w-]+)+$";

    @Override
    public boolean validate(String s) {

        if(s == null || s.trim().equals("")) {
            return false;
        }

        return s.matches(mailPattern);
    }
}
