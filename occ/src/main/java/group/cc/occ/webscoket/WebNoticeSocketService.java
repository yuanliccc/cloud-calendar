package group.cc.occ.webscoket;

import com.alibaba.fastjson.JSONObject;
import group.cc.occ.model.Chat;
import group.cc.occ.model.Message;
import group.cc.occ.service.ChatService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by wangyuming on 2019/3/19.
 */
@ServerEndpoint(value="/notice/{userid}", encoders = {ServerEncoder.class}, decoders = {ServerDecoder.class})
@Component
public class WebNoticeSocketService {

    private ChatService chatService;

    private static Log log = LogFactory.getLog(WebNoticeSocketService.class);

    private static CopyOnWriteArraySet<WebNoticeSocketService> webNoticeSocketServices = new CopyOnWriteArraySet<>();

    private Session session;

    private Integer userId;

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebNoticeSocketService.applicationContext = applicationContext;
    }

    @OnOpen
    public void onOpen(Session session,@PathParam("userid") Integer userid){
        this.session = session;
        this.userId = userid;
        log.info(this.session.getId());
        webNoticeSocketServices.add(this);
    }

    @OnClose
    public void onClose(){
        webNoticeSocketServices.remove(this);
        log.info(userId + " 连接关闭！");
    }

    @OnError
    public void onError(Session session, Throwable error){
        log.error("发生错误");
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, Session session){
        Chat c = null;
        try {
            c = JSONObject.parseObject(message, Chat.class);
            chatService = applicationContext.getBean(ChatService.class);
            chatService.save(c);
            sengObject(new Message(1, c), c.getReceiveuserid());
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public static void sendMessage(String message, Integer userId){
        for(WebNoticeSocketService n: webNoticeSocketServices){
            if(userId == n.userId){
                try {
                    n.sendObject(new Message(0, message));
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    public static void sengObject(Object obj, Integer userId){
        for(WebNoticeSocketService n: webNoticeSocketServices){
            if(userId == n.userId){
                try {
                    n.sendObject(obj);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    /*public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }*/

    public void sendObject(Object obj) throws IOException, EncodeException{
        this.session.getBasicRemote().sendObject(obj);
    }
}
