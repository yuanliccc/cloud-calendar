package group.cc.occ;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateCode {

    public static void main(String[] args) {
        //CodeGenerator.generate();
        //VueGenerator.generate();
        String filePath = "D:\\occ-1.0-SNAPSHOT.war\\WEB-INF\\classes\\allowUrl.properties";
        File file = new File(filePath);
        System.out.println(file.exists());

    }

    private static  <T> T nullToDefault(Object aim, T defaultValue) {
        if(defaultValue instanceof Integer && aim != null){
            Integer t = Integer.parseInt(aim + "");
            return (T)t;
        }else if(defaultValue instanceof Integer && aim == null){
            Integer t = Integer.parseInt(defaultValue + "");
            return (T)t;
        }

        return aim == null ? defaultValue : (T)aim;
    }

}
