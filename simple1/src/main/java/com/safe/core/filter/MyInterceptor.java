package com.safe.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * ���ǵڶ���������
 * @author Administrator
 *
 */
public class MyInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	//��token���� ʲôȨ�޵���ʲô���ķ���
        logger.info("���� preHandle ����..." + request.getRequestURL().toString() + "," + request.getRequestURI());
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("���� postHandle ����..." + request.getRequestURL().toString() + "," + request.getRequestURI());
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("���� afterCompletion ����..." + request.getRequestURL().toString() + "," + request.getRequestURI());
    }
}
