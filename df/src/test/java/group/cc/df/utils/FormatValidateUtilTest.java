package group.cc.df.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 高旭东 on 2019/2/16.
 */
public class FormatValidateUtilTest {
    private static final String email_1 = "877381442@qq.com";

    private static final String email_2 = "@163.com";

    private static final String email_3 = "8adsfasdf@1231";

    private static final String email_4 = "87ad";

    private static final String email_5 = "41123";

    private static final String phone_1 = "15202598062";

    private static final String phone_2 = "1377453256";

    private static final String phone_3 = "18323098888";

    private static final String phone_4 = "16000";

    private static final String phone_5 = "ass15740";
    @Test
    public void testValidateEmail() throws Exception {
        List<String> testList = new ArrayList<>(5);

        testList.add(email_1);
        testList.add(email_2);
        testList.add(email_3);
        testList.add(email_4);
        testList.add(email_5);

        for (String str: testList) {
            System.out.println(str + " >>>> " + FormatValidateUtil.validateEmail(str));
        }
    }

    @Test
    public void testValidatePhone() throws Exception {
        List<String> testList = new ArrayList<>(5);

        testList.add(phone_1);
        testList.add(phone_2);
        testList.add(phone_3);
        testList.add(phone_4);
        testList.add(phone_5);

        for (String str: testList) {
            System.out.println(str + " >>>> " + FormatValidateUtil.validatePhone(str));
        }
    }

}