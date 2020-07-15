package com.safe.core.aop.aspect;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.safe.core.utils.BaseUserInfo;
/**
 * ���� createOne�����Զ����봴��ʱ��ʹ�����
 * @author Administrator
 *
 */
@Component
@Aspect
public class SysCreateAspectJ {
	 /**
     * ָ���е�
     * ƥ�� com.safe.core.controller�������Ӱ��µ�����������з���
     */
    @Pointcut("execution(public * com.safe.core.controller.*Controller.createOne(..))")
    public void insertAop(){
    	System.out.println("������");
    }
 
    @Before("insertAop()")
    public void beforeInsertAop(JoinPoint joinPoint){
        System.out.println("���÷���֮ǰ��������");
        System.out.println("����ǰ��֪ͨ������ִ��ǰ����...");
        //��ȡĿ�귽���Ĳ�����Ϣ
        Object[] obj = joinPoint.getArgs();
        System.out.println(obj);
        Signature signature = joinPoint.getSignature();
        //���������һ������
        String methodName = signature.getName();
        //AOP�����������
        String className = signature.getDeclaringTypeName();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();
        // ���յ����󣬼�¼��������
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        StringBuffer content = new StringBuffer();
        content.append("url=" + req.getRequestURI());
        content.append(" ,paramName=").append(Arrays.toString(strings));
        content.append(" ,paramValue=").append(Arrays.toString(joinPoint.getArgs()));
        content.append(", clientIp=").append(req.getRemoteAddr());
        content.append(" ,HTTP_METHOD=").append(req.getMethod());
        content.append(" ,methodName=").append(methodName);
        content.append(" ,methodClassName=").append(className);
        System.out.println("AOP��ʽ��" + content);
        //��������ʼʱ��
        req.setAttribute("STARTTIME2", System.currentTimeMillis());
        String urlString=req.getRequestURI();
        if(urlString.endsWith("/insert")&&methodName.equals("createOne")){
        	HttpSession session = req.getSession();
			if(session.getAttribute("userInfo")!=null){
				BaseUserInfo userInfo =(BaseUserInfo) session.getAttribute("userInfo");
				Integer id=userInfo.getId();
				  System.out.println("aop:Insert" + content);
		        	 Object args= joinPoint.getArgs()[0];
		        	 Class<?> superClass=args.getClass().getSuperclass();
		        	 Field[] fields = superClass.getDeclaredFields();//��ȡ��������г�Ա������˽�еģ�
		    		 try {
			        	 for(Field field:fields){
			        		 if(field.getName().equals("createtime")){
			        			 boolean b=field.isAccessible();
			        			 field.setAccessible(true);//�������䣬���˽���޶�
								 field.set(args, new Date());
			        			 field.setAccessible(b);
			        		 }else if(field.getName().equals("creatorId")){
			        			 boolean b=field.isAccessible();
			        			 field.setAccessible(true);//�������䣬���˽���޶�
			        			 field.set(args, id);
			        			 field.setAccessible(b);
			        		 }
			        	 }
		        	 } catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
			}
        }
    }
 
    @AfterReturning("insertAop()")
    public void afterInsertAop(){
        System.out.println("���÷�������֮�󡣡�����");
    }
 
    //�׳��쳣ʱ�ŵ���
    @AfterThrowing("insertAop()")
    public void afterThrowing()
    {
        System.out.println("У��token�����쳣��......");
    }
 
    //dao aop
    @Pointcut("execution(public * com.safe.core.controller.dao.*.*(..))")
    public void dao(){
    }
 
    @Before("dao()")
    public void beforeDao(){
        System.out.println("����dao����֮ǰ��������");
    }
 
    @AfterReturning("dao()")
    public void afterDao(){
        System.out.println("����dao��������֮�󡣡�����");
    }
 
    //�׳��쳣ʱ�ŵ���
    @AfterThrowing("dao()")
    public void afterDaoThrowing()
    {
        System.out.println("У��dao�����쳣��......");
    }
 
}
