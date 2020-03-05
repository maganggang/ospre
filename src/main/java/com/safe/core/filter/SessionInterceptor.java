package com.safe.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class SessionInterceptor extends HandlerInterceptorAdapter{
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
			String url = request.getRequestURI();

			//��½��ʱ�򣬻���û���Ϣ����session�У�ֱ�ӻ�ȡ��
			//Ҫ�ǻ���û���ϢΪ�գ��ͽ����������ض����λ��
			//������Ŀ�����ҵ���߼� ӵ�ж�Ӧ��ɫ��url���ܽ��ж�Ӧ�Ĳ���
			System.out.println(url);
			//���һЩ�����������������⣨��½��ע�ᣬ�Լ�ע��ҳ����Ҫ��һЩ��������ȣ�
			if (null == request.getSession().getAttribute( "accountId")  && url.indexOf("assets") == -1&&url.indexOf("/account/login")==-1&&url.indexOf("/account/getGifCode")==-1){
			//response.sendRedirect(request.getContextPath()+"/user/login.jsp");
			//request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response); 
			/*java.io.PrintWriter out = response.getWriter(); 
			String CONTENT_TYPE = "text/html; charset=GBK";
			response.setContentType(CONTENT_TYPE);
			String a = request.getContextPath()+"/user/login";
			out.println("<html>"); 
			out.println("<script>"); 
			out.println("window.open ('"
			+ a
			+ "','_top')"); 
			out.println("</script>"); 
			out.println("</html>"); */

			response.sendRedirect(request.getContextPath() + "/assets/login.jsp"); 
			return false;
			} else {
				System.out.println(request);
			return true;
			}
}
}
