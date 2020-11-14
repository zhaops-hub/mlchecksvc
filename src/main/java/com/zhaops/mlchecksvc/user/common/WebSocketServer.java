package com.zhaops.mlchecksvc.user.common;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zhaops.mlchecksvc.MlchecksvcApplication;
import com.zhaops.mlchecksvc.user.dto.ResultModel;
import com.zhaops.mlchecksvc.user.dto.UserDto;
import com.zhaops.mlchecksvc.user.service.UserService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author SoYuan
 */
@ServerEndpoint("/websocket/{token}")
@Component
public class WebSocketServer {
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收sid
     */
    private String sid = "";

    private UserService userService;

    private static ConfigurableApplicationContext applicationContext;

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }


    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        this.userService = applicationContext.getBean(UserService.class);
        this.session = session;
        Boolean isOpen = true;
        String errMsg = "";
        /**加入set中 */
        try {
            this.sid = this.decodeToken(token);
        } catch (RuntimeException ex) {
            isOpen = false;
            errMsg = ex.getMessage();
        }

        webSocketSet.add(this);
        addOnlineCount();           //在线数加1


        ResultModel<String> result = new ResultModel<>();
        result.setCode(isOpen ? 0 : -1);
        result.setData(isOpen ? "连接成功" : errMsg);
        sendMessage(JSON.toJSONString(result));
        if (!isOpen) {
            this.session.close();
        }
    }


    @OnClose
    public void onClose() {
        /**  从set中删除 */
        webSocketSet.remove(this);
        /**在线数减1 */
        subOnlineCount();
    }


    @OnMessage
    public void onMessage(String message, Session session) {
        /**群发消息 */
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     *
     * @param message
     * @param sid
     * @throws IOException
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }


    /**
     * 解析token
     *
     * @param token
     * @return
     */
    private String decodeToken(String token) {
        String userId = "";
        try {
            userId = JWT.decode(token).getAudience().get(0);

            UserDto user = userService.load(Long.parseLong(userId));

            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }

            /** 验证 token */
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();

            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("401");
            }

        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }

        return userId;
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}
