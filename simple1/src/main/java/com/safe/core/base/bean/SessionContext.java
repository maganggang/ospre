package com.safe.core.base.bean;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.safe.core.utils.BaseUserInfo;

/**
 * 先存储sessionid 后来用id替换，这样好替换 
 * 使用单例模式
 * @author Administrator
 *
 */
public class SessionContext {
	private static SessionContext instance;
    private HashMap<String,HttpSession> sessionMap;
 
    private SessionContext() {
    	sessionMap = new HashMap<String,HttpSession>();
    }

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }

    public synchronized void AddSession(HttpSession session) {
        if (session != null) {
        	sessionMap.put(session.getId(), session);
        	System.out.println("人数"+sessionMap.size());
        	 if(session.getAttribute("userInfo")!=null){
             	BaseUserInfo baseUserInfo=(BaseUserInfo)session.getAttribute("userInfo");
             	sessionMap.put(baseUserInfo.getName(),session);
             	sessionMap.remove(session.getId());
             }
        }
    }

    public synchronized void DelSession(HttpSession session) {
        if (session != null) {
        	sessionMap.remove(session.getId());
            if(session.getAttribute("userInfo")!=null){
            	BaseUserInfo baseUserInfo=(BaseUserInfo)session.getAttribute("userInfo");
            	sessionMap.remove(baseUserInfo.getName());
            }
        }
    }

    public synchronized HttpSession getSession(String session_id) {
        if (session_id == null) return null;
        return (HttpSession) sessionMap.get(session_id);
    }

	public HashMap<String,HttpSession> getSessionMap() {
		return sessionMap;
	}

	public void setMymap(HashMap<String,HttpSession> sessionMap) {
		this.sessionMap = sessionMap;
	}
}
