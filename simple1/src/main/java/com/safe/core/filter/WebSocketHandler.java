package com.safe.core.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.safe.core.utils.BaseUserInfo;

/**
 * 与websocket客户端来进行交互的接口
 * @author dell
 *
 */
public class WebSocketHandler extends TextWebSocketHandler{
	 //在线用户列表
    private static final Map<String, WebSocketSession> users;
    //用户标识
    private static final String CLIENT_ID = "USER";

    static {
        users = new HashMap<String, WebSocketSession>();
    }
   
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
    //接收到客户端消息时调用
        System.out.println("text message: " + session.getId() + "-" + message.getPayload());
        String text = "get message "+session.getId();
        session.sendMessage(new TextMessage(text.getBytes()));
        // ...
        System.out.println(message.getPayload());

        WebSocketMessage message1 = new TextMessage("server:"+message);
        try {
            session.sendMessage(message1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        // 与客户端完成连接后调用
        System.out.println("afterConnectionEstablished");
        System.out.println("getId:" + session.getId());
        System.out.println("getLocalAddress:" + session.getLocalAddress().toString());
        System.out.println("getTextMessageSizeLimit:" + session.getTextMessageSizeLimit());
        System.out.println("getUri:" + session.getUri().toString());
        System.out.println("getPrincipal:" + session.getPrincipal());
        session.sendMessage(new TextMessage("hello".getBytes()));
        //
        String username= getClientId(session);
        if (username != null) {
            users.put(username, session);
            session.sendMessage(new TextMessage("成功建立socket连接"));
        }
    }
 
    /**
     * 获取用户标识
     * @param session
     * @return
     */
    private String getClientId(WebSocketSession session) {
        try {
        	BaseUserInfo userInfo =(BaseUserInfo) session.getAttributes().get(CLIENT_ID);
            String clientId = userInfo.getName();
            return clientId;
        } catch (Exception e) {
            return null;
        }
    }

	@Override
    public void handleTransportError(WebSocketSession session,
                                     Throwable exception) throws Exception {
// 消息传输出错时调用
		if (session.isOpen()) {
            session.close();
        }
        System.out.println("连接出错");
        users.remove(getClientId(session));
        System.out.println("handleTransportError");
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus closeStatus) throws Exception {
// 一个客户端连接断开时关闭
        users.remove(getClientId(session));
    }
 
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    /**
     * 发送信息给指定用户
     * @param clientId
     * @param message
     * @return
     */
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if (users.get(clientId) == null) {
            return false;
        }
        WebSocketSession session = users.get(clientId);
        if (!session.isOpen()) {
            return false;
        }
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * 广播信息
     * @param message
     * @return
     */
    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<String> clientIds = users.keySet();
        WebSocketSession session = null;
        for (String clientId : clientIds) {
            try {
                session = users.get(clientId);
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                allSendSuccess = false;
            }
        }

        return  allSendSuccess;
    }
}
