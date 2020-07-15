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
 * 创建 createOne方法自动放入创建时间和创建人
 * @author Administrator
 *
 */
@Component
@Aspect
public class SysCreateAspectJ {
	 /**
     * 指定切点
     * 匹配 com.safe.core.controller包及其子包下的所有类的所有方法
     */
    @Pointcut("execution(public * com.safe.core.controller.*Controller.createOne(..))")
    public void insertAop(){
    	System.out.println("进入了");
    }
 
    @Before("insertAop()")
    public void beforeInsertAop(JoinPoint joinPoint){
        System.out.println("调用方法之前。。。。");
        System.out.println("我是前置通知，方法执行前调用...");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        System.out.println(obj);
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        String methodName = signature.getName();
        //AOP代理类的名字
        String className = signature.getDeclaringTypeName();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();
        // 接收到请求，记录请求内容
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
        System.out.println("AOP方式：" + content);
        //设置请求开始时间
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
		        	 Field[] fields = superClass.getDeclaredFields();//获取该类的所有成员变量（私有的）
		    		 try {
			        	 for(Field field:fields){
			        		 if(field.getName().equals("createtime")){
			        			 boolean b=field.isAccessible();
			        			 field.setAccessible(true);//暴力反射，解除私有限定
								 field.set(args, new Date());
			        			 field.setAccessible(b);
			        		 }else if(field.getName().equals("creatorId")){
			        			 boolean b=field.isAccessible();
			        			 field.setAccessible(true);//暴力反射，解除私有限定
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
        System.out.println("调用方法结束之后。。。。");
    }
 
    //抛出异常时才调用
    @AfterThrowing("insertAop()")
    public void afterThrowing()
    {
        System.out.println("校验token出现异常了......");
    }
 
    //dao aop
    @Pointcut("execution(public * com.safe.core.controller.dao.*.*(..))")
    public void dao(){
    }
 
    @Before("dao()")
    public void beforeDao(){
        System.out.println("调用dao方法之前。。。。");
    }
 
    @AfterReturning("dao()")
    public void afterDao(){
        System.out.println("调用dao方法结束之后。。。。");
    }
 
    //抛出异常时才调用
    @AfterThrowing("dao()")
    public void afterDaoThrowing()
    {
        System.out.println("校验dao出现异常了......");
    }
 
}
