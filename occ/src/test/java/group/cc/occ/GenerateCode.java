package group.cc.occ;

import group.cc.cg.CodeGenerator;
import group.cc.cg.VueGenerator;
import group.cc.occ.model.Role;
import group.cc.occ.util.InitUtil;
import io.swagger.models.auth.In;
import org.springframework.data.redis.core.RedisTemplate;

public class GenerateCode {

    public static void main(String[] args) {
        //CodeGenerator.generate();
        //VueGenerator.generate();
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
