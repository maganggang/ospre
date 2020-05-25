package com.safe.core.utils;

import javax.servlet.http.HttpSession;

import com.safe.core.filter.SessionListener;

public class SessionUtils {
	/**
	 * session缓存的工具类
	* @Title: sessionHandlerByCacheMap 
	* @param session
	* @return: void 
	* @author mgg
	* @date 2020年5月6日
	 */
	public static void sessionHandlerByCacheMap(HttpSession session){
		/*1、一个浏览器只能创建一个session对象，
		也就是说多用户会覆盖session。
		2、同一用户不用浏览器登录，会产生多个session，这时候需要判断用户是否已登录，
		将新session替换就session。
		*/
		if(session.getAttribute("userInfo")!=null){
			BaseUserInfo baseUserInfo=(BaseUserInfo)session.getAttribute("userInfo");
			//重复登录更换session
			if(SessionListener.sessionContext.getSessionMap().get(baseUserInfo.getName())!=null){
				HttpSession userSession=(HttpSession)SessionListener.sessionContext.getSessionMap().get(baseUserInfo.getName());
				
				//清除在线用户后，更新map,替换map sessionid
				SessionListener.sessionContext.getSessionMap().remove(session.getId());	
				SessionListener.sessionContext.getSessionMap().put(baseUserInfo.getName(),session);
				System.out.println(session.getId());
				SessionListener.sessionContext.getSessionMap().remove(userSession.getId());	
				//注销在线用户 新信息取缔老信息
			    userSession.invalidate();
			    
			}else{
				//首次登录存储
				SessionListener.sessionContext.getSessionMap().remove(session.getId());
				SessionListener.sessionContext.getSessionMap().put(baseUserInfo.getName(),session);
			}
		}
		/*if(SessionListener.sessionContext.getSessionMap().get(userid)!=null){
			HttpSession userSession=(HttpSession)SessionListener.sessionContext.getSessionMap().get(userid);
			//注销在线用户
			    if (session.isNew()) {
			    	userSession.invalidate();
			       //session无效，在这里进行页面跳转，返回到登录页面
			    }
						
			SessionListener.sessionContext.getSessionMap().remove(userid);
			//清除在线用户后，更新map,替换map sessionid
			SessionListener.sessionContext.getSessionMap().remove(session.getId());	
			SessionListener.sessionContext.getSessionMap().put(userid,session);	
		}else{
			// 根据当前sessionid 取session对象。 更新map key=用户名 value=session对象 删除map
           	SessionListener.sessionContext.getSessionMap().get(session.getId());
			SessionListener.sessionContext.getSessionMap().put(userid,SessionListener.sessionContext.getSessionMap().get(session.getId()));
			SessionListener.sessionContext.getSessionMap().remove(session.getId());
		}*/
	}
}
