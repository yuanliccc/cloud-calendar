package group.cc.occ.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Administrator on 2019/2/28.
 */
public class InitUtil {
    private static List<String> url = new ArrayList<>();
    private static String filePath = "occ//src//main//resources//allowUrl.properties";

    static{
        reLoad();
    }

    public static  void reLoad(){
        try{
            File f = new File(filePath);
            InputStream stream = new BufferedInputStream(new FileInputStream(new File(filePath)));
            Properties prop = new Properties();
            prop.load(stream);
            Enumeration en = prop.propertyNames();

            while (en.hasMoreElements()){
                String key = (String) en.nextElement();
                String value = prop.getProperty(key);
                url.add(value);
            }
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List getUrl(){
        return url;
    }
}
