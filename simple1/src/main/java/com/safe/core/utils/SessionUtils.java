package com.safe.core.utils;

import javax.servlet.http.HttpSession;

import com.safe.core.filter.SessionListener;

public class SessionUtils {
	public static void sessionHandlerByCacheMap(HttpSession session){
		String userid=session.getAttribute("username").toString();
		if(SessionListener.sessionContext.getSessionMap().get(userid)!=null){
			HttpSession userSession=(HttpSession)SessionListener.sessionContext.getSessionMap().get(userid);
			//ע�������û�
			userSession.invalidate();			
			SessionListener.sessionContext.getSessionMap().remove(userid);
			//��������û��󣬸���map,�滻map sessionid
			SessionListener.sessionContext.getSessionMap().remove(session.getId());	
			SessionListener.sessionContext.getSessionMap().put(userid,session);	
		}
		else
		{
			// ���ݵ�ǰsessionid ȡsession���� ����map key=�û��� value=session���� ɾ��map
           	SessionListener.sessionContext.getSessionMap().get(session.getId());
			SessionListener.sessionContext.getSessionMap().put(userid,SessionListener.sessionContext.getSessionMap().get(session.getId()));
			SessionListener.sessionContext.getSessionMap().remove(session.getId());
		}
	}
}
