package com.safe.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class SqlFactory {
	/**
	 * sql �Z��ӹ� where 
	* @Title: makeSql 
	* @param sql
	* @param whereSql
	* @return
	* @return: String 
	* @author mgg
	* @date 2020��5��25��
	 */
	public static String makeSql(String sql,String whereSql){
		String alias=getAlias(sql);
		if(alias!=null&&StringUtils.isNotBlank(alias)){
			whereSql=alias+". "+whereSql.trim();
		}
		String SQL=sql.toUpperCase();
		int i=SQL.indexOf("WHERE");
		if(i>0){
			sql=SQL.replace("WHERE", "WHERE "+whereSql+"AND ");
		}else{
			if(SQL.indexOf("GROUP")>0){
				sql=SQL.replace("GROUP", "WHERE "+whereSql+"   GROUP");
			}else if(sql.toUpperCase().indexOf("ORDER")>0){
				sql=SQL.replace("ORDER", "WHERE "+whereSql+"  ORDER");
			}else if(sql.toUpperCase().indexOf("LIMIT")>0){
				sql=SQL.replace("LIMIT", "WHERE "+whereSql+"   LIMIT");
			}else{
				sql+=" WHERE "+whereSql+" ";
			}
		}
		return sql;
	}
	/**
	 * ��ȡ����sql �ı����
	* @Title: getAlias 
	* @param sql
	* @return
	* @return: String 
	* @author mgg
	* @date 2020��5��25��
	 */
	public static String getAlias(String sql){
		int m=sql.toUpperCase().indexOf("FROM");
		int n=sql.toUpperCase().indexOf("JOIN");
		if(n<=0){
			n=sql.toUpperCase().indexOf("WHERE");
			if(n<=0){
				n=sql.toUpperCase().indexOf("GROUP");
				if(n<=0){
					n=sql.toUpperCase().indexOf("ORDER");
					if(n<=0){
						n=sql.toUpperCase().indexOf("LIMIT");
					}
				}
			}
		}
		String s2;
		if(n>=0&&n>m){
			 s2=sql.substring(m, n);
		}else{
			 s2=sql.substring(m);
		}
		  Pattern pattern = Pattern.compile("\\b(\\w+)\\s+\\b");
	      Matcher matcher = pattern.matcher(s2);//ԭ�ĳ��ԡ��װٽ̡̳�����ҵת������ϵ���߻����Ȩ������ҵ�뱣��ԭ�����ӣ�https://www.yiibai.com/javaexamples/regular_group.html
	      String alias="";
	      if (matcher.find(1)) {
	          System.out.println("found: " + matcher.group(1) );
	          alias=matcher.group(1) ;
	      }
		return alias;
	}
}
