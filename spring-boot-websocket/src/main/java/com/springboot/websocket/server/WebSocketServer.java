package com.springboot.websocket.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: lingjun.jlj
 * @date: 2019-03-25 20:27
 * @description:
 */
@Slf4j
@Component
@ServerEndpoint(value = "/websocket/{sid}")
public class WebSocketServer {

    /**
     * 静态变量，用来记录当前在线连接数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    /**
     * concurrent包的线程安全set，用来存放每个客户端对应的WebSocket对象
     */
    private static Map<String, Session> SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 建立连接
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        if (SESSION_MAP.containsKey(sid)) {
            log.info("用户:{} 已经在别处登录了", sid);
            sendMessage(sid, "您的账号已在其他地方登录!");
        }

        SESSION_MAP.put(sid, session);
        //新增一个在线用户
        addOnlineCount();
        log.info("有用户【{}】开始监听 ,当前在线人数为：{}", sid, getOnlineCount());
        try {
            sendMessage(sid, "连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        String sid = getUidBySession(session);
        SESSION_MAP.remove(sid);
        subOnlineCount();
        log.info("用户【{}】连接关闭！当前在线人数为:{}", sid, getOnlineCount());

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        String sid = getUidBySession(session);
        log.info("收到来自用户【{}】的信息:{}", sid, message);
        //想当前的用户群发消息
        log.info("向在线的所有用户发送消息：{}", message);
        for (Session item : SESSION_MAP.values()) {
            try {
                item.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String sid, String message) {
        try {
            Session session = SESSION_MAP.get(sid);
            log.info("向用户【{}】发送信息：{}", sid, message);
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 群发自定义消息
     */
    public void sendInfo(String message, @PathParam("sid") String sid) {
        log.info("推送消息到用户【{}】，推送内容:{}", sid, message);
        for (Session session : SESSION_MAP.values()) {
            String uid = getUidBySession(session);
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    session.getBasicRemote().sendText(message);
                } else if (uid.equals(sid)) {
                    session.getBasicRemote().sendText(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount.get();
    }

    /**
     * 在线数加1
     */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount.incrementAndGet();
    }

    /**
     * 在线数减一
     */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount.decrementAndGet();
    }

    /**
     * 根据WebSocketSession获取请求路径，进而获取uid
     *
     * @param session
     * @return
     */
    private String getUidBySession(Session session) {
        String path = session.getRequestURI().getPath();
        int index = StringUtils.lastIndexOf(path, "/");
        if (index < 0) {
            throw new RuntimeException("websocket请求路径非法");
        }
        return StringUtils.substring(path, index + 1);
    }
}
