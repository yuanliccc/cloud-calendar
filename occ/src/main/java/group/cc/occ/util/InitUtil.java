package group.cc.occ.util;

import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2019/2/28.
 */


public class InitUtil {
    private static List<String> URL = new ArrayList<>();

    public static void setUrl(List<String> list){
        URL = list;
    }


    public static List getUrl(){
        return URL;
    }
}
