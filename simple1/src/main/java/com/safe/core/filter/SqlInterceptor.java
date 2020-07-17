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
 * ��֯�ṹ�Ĳ�ѯ ������ע��
 * 
 * @author Administrator
 * 		��������������������������������
		��Ȩ����������ΪCSDN����������ͨ����ԭ�����£���ѭCC 4.0 BY-SA��ȨЭ�飬ת���븽��ԭ�ĳ������Ӽ���������
		ԭ�����ӣ�https://blog.csdn.net/tanqian351/java/article/details/84700890
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
		
		 // ����һ
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        //�����ص�RoutingStatementHandler�������и�StatementHandler���͵�delegate��������ʵ������BaseStatementHandler��Ȼ��͵�BaseStatementHandler�ĳ�Ա����mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //idΪִ�е�mapper������ȫ·��������com.uv.dao.UserMapper.insertUser
        String id = mappedStatement.getId();
        logger.info(id);
        //sql������� select��delete��insert��update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
        BoundSql boundSql = statementHandler.getBoundSql();
        System.out.println(sqlCommandType);
        //��ȡ��ԭʼsql���
        String sql = boundSql.getSql();
        String mSql = sql;
        //TODO �޸�λ��
 
        //ע���߼��ж�  ���ע���˲�����
        Class<?> classType = Class.forName(mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf(".")));
        String mName = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1, mappedStatement.getId().length());
        for (Method method : classType.getDeclaredMethods()) {
        	
            if (method.isAnnotationPresent(InterceptAnnotation.class) && mName.equals(method.getName())) {
                InterceptAnnotation interceptorAnnotation = method.getAnnotation(InterceptAnnotation.class);
                if (interceptorAnnotation.flag()) {
                   // mSql = sql + " limit 2";
                    System.out.println(mSql);
                 // ��ȡrequest��Ϣ���õ���ǰ��¼�û���Ϣ
            		RequestAttributes req = RequestContextHolder.getRequestAttributes();
            		if (req == null) {
            				return invocation.proceed();
            			}
            		//����request
            		HttpServletRequest request =((ServletRequestAttributes) req).getRequest();
            		//���request������û�/Ա����ϢΪ�գ���ֱ�����쳣
            		if(request.getSession().getAttribute("userInfo")== null ){
            			throw new RuntimeException("�˲�ѯΪȨ�����ݣ�������ӵ��Ա�����û����Ե��˺ŵ�¼�ſ��Բ�ѯ��");
            		}
            			BaseUserInfo userInfo =(BaseUserInfo) request.getSession().getAttribute("userInfo");
            			//���Ҹ�λ��ĸ�λid-����֯id �и�λid��������֯id�޸�λid-��creatorid 
            			//�˺�����Ա�Ŀ�����Ϊ�ǹ���Ա
            			String sql2=null;
            			if(userInfo.getPostId()==null){
            				if(userInfo.getRoleIds().contains(1)){
            					sql2=" 1=1 ";
            				}else{
            					sql2="   creator_id="+userInfo.getId();
            				}
            			}else{
            				//��ʼ��bean
            				this.loadService();
            				Post post=postService.selectByPrimaryKey(userInfo.getPostId());
            				 sql2="   org_id="+post.getOrgId();
            			}
            			mSql=SqlFactory.makeSql(sql, sql2);
            			System.out.println(mSql);
                }else{
                	System.out.println("��֪���������ҳ");
                }
            }
        }
 
        //ͨ�������޸�sql���
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
     * ����ע���bean
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
