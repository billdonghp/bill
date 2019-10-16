package com.ait.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnBean {

	public static synchronized Connection getConn() {
		DataSource ds = null;
		Connection conn = null;
		try {
			Logger.getLogger(ConnBean.class).debug("get db connection from jndi resource......");
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/EM2");
			conn = ds.getConnection();
		} catch (NamingException namex) {
			Logger.getLogger(ConnBean.class).error(namex.toString());
		} catch (SQLException sqlex) {
			Logger.getLogger(ConnBean.class).error(sqlex.toString());
		}
		return conn;
	}
	
	public static synchronized Connection getConnIfEhr() {
		Connection conn = null;
    	try { 
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			//和EHR系统进行同步
    	    conn = DriverManager.getConnection("jdbc:oracle:thin:@10.40.128.30:1521:human","if_ehr","if_ehr") ;
		} 
    	catch (SQLException e) { 
    		return null; 
    		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return conn;
	}

}
