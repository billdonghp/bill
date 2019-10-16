package com.ait.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnBean {

public static Connection getConn() {
    	Connection conn = null;
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
			String url =
		        "jdbc:oracle:thin:@10.40.128.30:1521:human";
			conn = DriverManager.getConnection (url,"chrs","dshr123@$");
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

