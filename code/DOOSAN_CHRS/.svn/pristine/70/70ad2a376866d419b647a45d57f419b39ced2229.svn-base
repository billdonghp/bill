package com.ait.util;




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
import java.sql.*;


public class SqlConnBean {
    private static Connection conn = null;
    private static Connection conn1 = null;
    private static Statement st;
    private static ResultSet rs;

    public static ResultSet getRs(String sql){
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
        }
        return rs;
    }

    public static void closeConn(){
        try {
            conn.close();
        } catch (SQLException ex) {
        }
    }	
    public static Connection getConn() {
	 	try {
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			String url =
			        "jdbc:microsoft:sqlserver://10.40.128.110:11111;DataBaseName=scm_main_xxf";
			conn1 = DriverManager.getConnection(url, "sa", "dicc123~!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return conn1;
    }

    public static void main(String[] args) {
		new SqlConnBean();
	}
}

