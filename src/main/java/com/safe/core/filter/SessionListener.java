package com.safe.core.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.safe.core.utils.SessionContext;

public class SessionListener implements HttpSessionListener {
	 public  static SessionContext sessionContext=SessionContext.getInstance();
	 
	    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	    	System.out.println("����");
	    	sessionContext.AddSession(httpSessionEvent.getSession());
	    }

	    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
	    	System.out.println("����");
	        sessionContext.DelSession(httpSessionEvent.getSession());
	    }
	

}
