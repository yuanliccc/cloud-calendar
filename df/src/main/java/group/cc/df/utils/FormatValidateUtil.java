package group.cc.df.utils;

import java.util.regex.Pattern;

/**
 * Created by 高旭东 on 2019/2/16.
 */
public class FormatValidateUtil {
    public static final String EMAIL_RULE = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

    public static final String PHONE_RULE = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

    private static Pattern pattern;

    public static boolean validateEmail(String validateStr) {
        pattern = Pattern.compile(EMAIL_RULE);
        return pattern.matcher(validateStr).matches();
    }

    public static boolean validatePhone(String validateStr) {
        pattern = Pattern.compile(PHONE_RULE);
        return pattern.matcher(validateStr).matches();
    }
}
