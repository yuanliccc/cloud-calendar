package group.cc.mail.parse;

import com.yl.common.parse.Parser;
import org.springframework.stereotype.Component;

/**
 * 邮箱字符串解析器（解析器不会对邮箱正确性进行验证）
 * 如： abc@163.com 返回：["abc","@163"]
 * @author YuanLi
 */
@Component("mailParser")
public class MailParser implements Parser<String, String[]> {

    @Override
    public String[] parse(String s) {
        if(s == null || s.trim().equals("")) {
            return null;
        }
        String[] result = new String[2];

        // 获取邮箱账号
        result[0] = s.substring(0, s.indexOf('@') + 1);
        // 获取邮箱服务商标识
        result[1] = s.substring(s.indexOf('@'), s.indexOf('.'));

        return result;
    }
}
