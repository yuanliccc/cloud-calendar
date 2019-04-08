package group.cc.occ.webscoket;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by wangyuming on 2019/3/19.
 */
public class ServerDecoder implements Decoder.Text<Object> {
    private static Log log = LogFactory.getLog(ServerDecoder.class);

    @Override
    public Object decode(String s) throws DecodeException {
        try {
            return JSON.toJSON(s);
        }catch (Exception e){

        }

        return null;
    }

    @Override
    public boolean willDecode(String s) {
        return false;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
