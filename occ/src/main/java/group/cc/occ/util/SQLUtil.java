package group.cc.occ.util;

import java.util.Date;

/**
 * Created by Administrator on 2019/2/26.
 */
public class SQLUtil {
    public static String generateNumber() {
        Date date = new Date();
        return date.getTime() + "";
    }
}