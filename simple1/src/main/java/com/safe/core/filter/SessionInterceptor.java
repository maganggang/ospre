package com.safe.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	   private final static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@Override
    public void postHandle(HttpServletRequest request, 
        HttpServletResponse response, Object handler, 
        ModelAndView modelAndView) throws Exception { 
		System.out.println("w进入 postHandle 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());
    } 
      
    @Override
    public void afterCompletion(HttpServletRequest request, 
        HttpServletResponse response, Object handler, Exception ex) 
        throws Exception { 
    	System.out.println("w进入 afterCompletion 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());
    } 
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("w进入 preHandle 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());

			String url = request.getRequestURI();

			//登陆的时候，会把用户信息存在session中，直接获取，
			//要是获得用户信息为空，就进入拦截器重定向的位置
			//可在项目中添加业务逻辑 拥有对应角色的url才能进行对应的操作
			System.out.println(url);
			//添加一些不进入拦截器的例外（登陆，注册，以及注册页面需要的一些数据请求等）
			if (request.getSession()==null&&null == request.getSession().getAttribute( "userInfo")  && url.indexOf("assets") == -1&&url.indexOf("/account/login")==-1&&url.indexOf("/account/getGifCode")==-1){
			//response.sendRedirect(request.getContextPath()+"/user/login.jsp");
			//request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response); 
			java.io.PrintWriter out = response.getWriter(); 
			String CONTENT_TYPE = "text/html; charset=GBK";
			response.setContentType(CONTENT_TYPE);
			String a = request.getContextPath()+"/user/login";
			out.println("<html>"); 
			out.println("<script>"); 
			out.println("window.open ('"
			+ a
			+ "','_top')"); 
			out.println("</script>"); 
			out.println("</html>"); 

			response.sendRedirect(request.getContextPath() + "/assets/login.jsp"); 
			return false;
			} else {
				System.out.println(request);
				//想在这里补充insert参数
			return true;
			}
}
}
