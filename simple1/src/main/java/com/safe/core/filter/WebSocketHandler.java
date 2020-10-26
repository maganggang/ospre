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
 * ��websocket�ͻ��������н����Ľӿ�
 * @author dell
 *
 */
public class WebSocketHandler extends TextWebSocketHandler{
	 //�����û��б�
    private static final Map<String, WebSocketSession> users;
    //�û���ʶ
    private static final String CLIENT_ID = "USER";

    static {
        users = new HashMap<String, WebSocketSession>();
    }
   
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
    //���յ��ͻ�����Ϣʱ����
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
        // ��ͻ���������Ӻ����
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
            session.sendMessage(new TextMessage("�ɹ�����socket����"));
        }
    }
 
    /**
     * ��ȡ�û���ʶ
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
// ��Ϣ�������ʱ����
		if (session.isOpen()) {
            session.close();
        }
        System.out.println("���ӳ���");
        users.remove(getClientId(session));
        System.out.println("handleTransportError");
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus closeStatus) throws Exception {
// һ���ͻ������ӶϿ�ʱ�ر�
        users.remove(getClientId(session));
    }
 
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    /**
     * ������Ϣ��ָ���û�
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
     * �㲥��Ϣ
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
