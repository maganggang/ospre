package com.safe.core.filter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.safe.core.base.bean.ApplicationContextHelper;
import com.safe.core.beans.Post;
import com.safe.core.service.PostService;
import com.safe.core.utils.BaseUserInfo;
import com.safe.core.utils.SqlFactory;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import config.InterceptAnnotation;

/**
 * 组织结构的查询 和配置注解
 * 
 * @author Administrator
 * 		————————————————
		版权声明：本文为CSDN博主「谷神通」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
		原文链接：https://blog.csdn.net/tanqian351/java/article/details/84700890
 *
 */
@Intercepts({


    @Signature(
            type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class
    })


})
public class SqlInterceptor implements Interceptor {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(SqlInterceptor.class);
	private Properties properties;
	private ApplicationContext beanFactory;
    private PostService postService;
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
	/*	String methodName = invocation.getMethod().getName();
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		String sqlId = mappedStatement.getId();
		String namespace = sqlId.substring(0, sqlId.indexOf('.'));
		Executor exe = (Executor) invocation.getTarget();
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		logger.debug("sqlCommandType:" + sqlCommandType);*/
		
		 // 方法一
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
        String id = mappedStatement.getId();
        logger.info(id);
        //sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
        BoundSql boundSql = statementHandler.getBoundSql();
        System.out.println(sqlCommandType);
        //获取到原始sql语句
        String sql = boundSql.getSql();
        String mSql = sql;
        //TODO 修改位置
 
        //注解逻辑判断  添加注解了才拦截
        Class<?> classType = Class.forName(mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf(".")));
        String mName = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1, mappedStatement.getId().length());
        for (Method method : classType.getDeclaredMethods()) {
        	
            if (method.isAnnotationPresent(InterceptAnnotation.class) && mName.equals(method.getName())) {
                InterceptAnnotation interceptorAnnotation = method.getAnnotation(InterceptAnnotation.class);
                if (interceptorAnnotation.flag()) {
                   // mSql = sql + " limit 2";
                    System.out.println(mSql);
                 // 获取request信息，得到当前登录用户信息
            		RequestAttributes req = RequestContextHolder.getRequestAttributes();
            		if (req == null) {
            				return invocation.proceed();
            			}
            		//处理request
            		HttpServletRequest request =((ServletRequestAttributes) req).getRequest();
            		//如果request里面的用户/员工信息为空，则直接抛异常
            		if(request.getSession().getAttribute("userInfo")== null ){
            			throw new RuntimeException("此查询为权限数据，必须是拥有员工和用户属性的账号登录才可以查询！");
            		}
            			BaseUserInfo userInfo =(BaseUserInfo) request.getSession().getAttribute("userInfo");
            			//查找岗位里的岗位id-》组织id 有岗位id——》组织id无岗位id-》creatorid 
            			//账号无人员的可以认为是管理员
            			String sql2=null;
            			if(userInfo.getPostId()==null){
            				if(userInfo.getRoleIds().contains(1)){
            					sql2=" 1=1 ";
            				}else{
            					sql2="   creator_id="+userInfo.getId();
            				}
            			}else{
            				//初始化bean
            				this.loadService();
            				Post post=postService.selectByPrimaryKey(userInfo.getPostId());
            				 sql2="   org_id="+post.getOrgId();
            			}
            			mSql=SqlFactory.makeSql(sql, sql2);
            			System.out.println(mSql);
                }else{
                	System.out.println("不知道不进入分页");
                }
            }
        }
 
        //通过反射修改sql语句
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, mSql);
        return invocation.proceed();

	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }

	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties ;
	}
	/**
     * 加载注入的bean
     */
    private void loadService() {
        if (null == beanFactory) {
            beanFactory = ApplicationContextHelper.getApplicationContext();
            if(null == beanFactory){
            	return;
            }
        }
        if (postService == null) {
        	postService = beanFactory.getBean(PostService.class);
        }
       
        
    }

}
