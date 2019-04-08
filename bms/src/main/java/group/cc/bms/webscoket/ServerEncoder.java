package group.cc.bms.webscoket;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by wangyuming on 2019/3/19.
 */
public class ServerEncoder implements Encoder.Text<Object>{
    private static Log log = LogFactory.getLog(ServerEncoder.class);

    @Override
    public String encode(Object o) throws EncodeException {
        try {
            return JSON.toJSONString(o);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
