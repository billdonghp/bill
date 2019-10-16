/*
 * @(#)SqlMapUser.java 1.0 2006-12-3
 *
 */
package com.ait.sqlmap.template;

import java.sql.SQLException;
import java.util.List;

import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.sqlmap.util.SimpleMap;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-5 下午03:46:51
 * @version 1.0
 * 
 */
public class SqlMapUser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// 定义Statement Name
		String statementName = "sqlmap.template.testSqlMap";
		String statementName2 = "sqlmap.template.RetrieveArItemList";
		String statementName3 = "sqlmap.template.RetrieveArItemName";
		
		// 定义Parameter Object 
		Person  parameterObject = new Person();
		parameterObject.setId(128);
		parameterObject.setName("aqing");
		parameterObject.setAge(25);
		parameterObject.setSex("");
		
		SimpleMap parameterMap = new SimpleMap(); 
		parameterMap.setString("ITEM_NAME", "");
		parameterMap.setInt("ITEM_NO", 17);
		
		int parameterInt = 17;
		String resultString;
		
		//定义Result Object
		Person result;
		
		SimpleMap resultMap;
		
		// 创建SqlMap Client
		SqlMapClient sqlMapClient = SQLMapConfigManager.getInstance().getSqlMapClient(
                "template");
		
		// 执行select
		try {
			
			 // test 1
			 result = (Person) sqlMapClient.queryForObject(statementName,
	                parameterObject);
			 System.out.println(result.getId());
			 System.out.println(result.getName());
			 System.out.println(result.getSex());
			 
			 // test 2
			 List list = (List) sqlMapClient.queryForList(statementName2,
		                parameterMap);
			 for(int i = 0; i < list.size(); i ++) {
				 
				 resultMap = (SimpleMap)list.get(i);
				 System.out.println(resultMap.getString("ITEM_NO"));
				 System.out.println(resultMap.getString("ITEM_NAME"));
				 System.out.println(resultMap.getString("DESCRIPTION"));
			 }
			 
			 //	test 3
			 resultString = (String) sqlMapClient.queryForObject(statementName3,
		                parameterInt);
			 System.out.println(resultString);
			 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}

