package com.springboot.websocket.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: Arthas
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
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全set，用来存放每个客户端对应的WebSocket对象
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketServerSet = new CopyOnWriteArraySet<>();

    private Session session;

    private String sid = "";

    /**
     * 建立连接
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        webSocketServerSet.add(this);
        //新增一个在线用户
        addOnlineCount();
        log.info("有用户【{}】开始监听 ,当前在线人数为：{}", sid, getOnlineCount());
        this.sid = sid;
        try {
            sendMessage("连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketServerSet.remove(this);
        subOnlineCount();
        log.info("用户【{}】连接关闭！当前在线人数为:{}", this.sid, getOnlineCount());

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自用户【{}】的信息:{}", sid, message);
        //想当前的用户群发消息
        log.info("向在线的所有用户发送消息：{}", message);
        for (WebSocketServer item : webSocketServerSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
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
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        log.info("推送消息到用户【{}】，推送内容:{}", sid, message);
        for (WebSocketServer item : webSocketServerSet) {
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

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 在线数加1
     */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    /**
     * 在线数减一
     */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
