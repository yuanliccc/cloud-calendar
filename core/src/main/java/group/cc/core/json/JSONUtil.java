package group.cc.core.json;

import com.alibaba.fastjson.JSONObject;

public class JSONUtil {

    public static Object get(String jsonStr, Object key) {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        return jsonObject.get(key);
    }

}
