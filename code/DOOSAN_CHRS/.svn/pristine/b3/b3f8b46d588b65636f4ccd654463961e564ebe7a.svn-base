package com.ait.ar.arBalance;
/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DiccConnBean {

public static Connection getConn() {
    	Connection conn = null;
    	try {
			//Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
			//String url = "jdbc:oracle:thin:@172.16.221.2:1521:human";
			//conn = DriverManager.getConnection (url,"human","human");
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
    	    conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.221.2:1521:dwyc","human","human") ;
		} 
    	catch (SQLException e) { 
    		return null; 
    		} catch (InstantiationException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		} 
		return conn;
    }
}
