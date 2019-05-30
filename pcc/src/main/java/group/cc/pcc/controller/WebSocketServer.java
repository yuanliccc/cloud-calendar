/*
 * Copyright (c) 2019 YuanLi. All rights reserved.
 */

package group.cc.pcc.controller;

import group.cc.pcc.SpringUtils;
import group.cc.pcc.model.PccUser;
import group.cc.pcc.service.PccUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{pccUserId}")
@Component
public class WebSocketServer {

    @Autowired
    private PccUserService pccUserService = (PccUserService) SpringUtils.getBean("pccUserServiceImpl");

    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static ConcurrentHashMap<Integer, Session> sessions = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private Integer pccUserId;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("pccUserId") Integer pccUserId) {
        this.session = session;
        this.pccUserId = pccUserId;
        sessions.put(pccUserId, session);
        logger.info("客户端接入 => "+ pccUserId);

        List<PccUser> friends = pccUserService.friends(pccUserId);

        friends.forEach(friend -> {
            Session friendSession = sessions.get(friend.getId());

            if (friendSession != null) {
                try{
                    friendSession.getBasicRemote().sendText("您的好友上线了！");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        sessions.remove(this.pccUserId);
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到消息 => " + message);
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送文本信息
     */
    public void sendMessage(String message, Integer pccUserId) throws IOException {
        sessions.get(pccUserId).getBasicRemote().sendText(message);
    }

}
