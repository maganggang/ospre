package com.safe.core.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.safe.core.base.bean.SessionContext;

public class SessionListener implements HttpSessionListener {
	 public  static SessionContext sessionContext=SessionContext.getInstance();
	 
	    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	    	System.out.println("´´½¨");
	    	sessionContext.AddSession(httpSessionEvent.getSession());
	    }

	    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
	    	System.out.println("Ïú»Ù");
	        sessionContext.DelSession(httpSessionEvent.getSession());
	    }
	

}
