package com.safe.core.utils;

import javax.servlet.http.HttpSession;

import com.safe.core.filter.SessionListener;

public class SessionUtils {
	/**
	 * session����Ĺ�����
	* @Title: sessionHandlerByCacheMap 
	* @param session
	* @return: void 
	* @author mgg
	* @date 2020��5��6��
	 */
	public static void sessionHandlerByCacheMap(HttpSession session){
		/*1��һ�������ֻ�ܴ���һ��session����
		Ҳ����˵���û��Ḳ��session��
		2��ͬһ�û������������¼����������session����ʱ����Ҫ�ж��û��Ƿ��ѵ�¼��
		����session�滻��session��
		*/
		if(session.getAttribute("userInfo")!=null){
			BaseUserInfo baseUserInfo=(BaseUserInfo)session.getAttribute("userInfo");
			//�ظ���¼����session
			if(SessionListener.sessionContext.getSessionMap().get(baseUserInfo.getName())!=null){
				HttpSession userSession=(HttpSession)SessionListener.sessionContext.getSessionMap().get(baseUserInfo.getName());
				
				//��������û��󣬸���map,�滻map sessionid
				SessionListener.sessionContext.getSessionMap().remove(session.getId());	
				SessionListener.sessionContext.getSessionMap().put(baseUserInfo.getName(),session);
				System.out.println(session.getId());
				SessionListener.sessionContext.getSessionMap().remove(userSession.getId());	
				//ע�������û� ����Ϣȡ������Ϣ
			    userSession.invalidate();
			    
			}else{
				//�״ε�¼�洢
				SessionListener.sessionContext.getSessionMap().remove(session.getId());
				SessionListener.sessionContext.getSessionMap().put(baseUserInfo.getName(),session);
			}
		}
		/*if(SessionListener.sessionContext.getSessionMap().get(userid)!=null){
			HttpSession userSession=(HttpSession)SessionListener.sessionContext.getSessionMap().get(userid);
			//ע�������û�
			    if (session.isNew()) {
			    	userSession.invalidate();
			       //session��Ч�����������ҳ����ת�����ص���¼ҳ��
			    }
						
			SessionListener.sessionContext.getSessionMap().remove(userid);
			//��������û��󣬸���map,�滻map sessionid
			SessionListener.sessionContext.getSessionMap().remove(session.getId());	
			SessionListener.sessionContext.getSessionMap().put(userid,session);	
		}else{
			// ���ݵ�ǰsessionid ȡsession���� ����map key=�û��� value=session���� ɾ��map
           	SessionListener.sessionContext.getSessionMap().get(session.getId());
			SessionListener.sessionContext.getSessionMap().put(userid,SessionListener.sessionContext.getSessionMap().get(session.getId()));
			SessionListener.sessionContext.getSessionMap().remove(session.getId());
		}*/
	}
}
