package com.ait.pa.cmd.ccplan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ait.pa.Paformular;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class CCPlan {
	 Connection connIn =null;
	public synchronized Connection getConn() {
    	Connection conn = null;
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
			String url =
		        "jdbc:oracle:thin:@10.224.3.221:1541:DSHR";
			conn = DriverManager.getConnection (url,"DICCHRIF","dshrif");
		} 
    	catch (SQLException e) { 
    		return null; 
    		} catch (InstantiationException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} 
		return conn;
    }
	
	public ResultSet synchronizePersonPlan(String cpnyId,String planYear){		
		
		connIn = this.getConn(); //建立连接
	    PreparedStatement pstmt = null; //建立参数声明
	    ResultSet rs = null; //建立数据集
	    String sql="select * from PY_PERSON_PLAN t where t.COM_ORG_ID='"+cpnyId+"' and t.plan_yy='"+planYear+"'";
	    try {
	      pstmt = connIn.prepareStatement(sql); 
	      Logger.getLogger(getClass()).debug(sql);		 
	      rs = pstmt.executeQuery();	     
	    }
	    catch (SQLException e) {

	      Logger.getLogger(getClass()).error(e);
	    }	    
		return rs;
	}
	
	public  int productsTempPersonPlanTable(String cpnyId,String planYear){
		int flag=0;		
		PreparedStatement pstmt = null;
		PreparedStatement pstmtDelete = null;
		Connection conn = ConnBean.getConn();
		ResultSet rs =this.synchronizePersonPlan(cpnyId,planYear);
		try {
			pstmtDelete = conn.prepareStatement("delete  from T_PY_PERSON_PLAN t where t.COM_ORG_ID='"+cpnyId+"'  and t.plan_yy='"+planYear+"'");
			pstmtDelete.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String sql = "insert into  T_PY_PERSON_PLAN(PERSONPLAN_ID,COM_ORG_ID,PLAN_YY,PLAN_BASE_YMD,BG_GU,BG_NM,ORG_ID,ORG_FULL_NM,GROUP_TYPE,POS_TYPE, "+
                     "NOW_INWON,BEFORE_YEAR_INWON,AFTER_YEAR_INWON,INWON_MONTH_01,INWON_MONTH_02,INWON_MONTH_03,INWON_MONTH_04,INWON_MONTH_05,INWON_MONTH_06, "+
                     "INWON_MONTH_07,INWON_MONTH_08,INWON_MONTH_09,INWON_MONTH_10,INWON_MONTH_11,INWON_MONTH_12,INWON_MONTH_TOT,MOD_USER_ID,MOD_DATE,MOD_LOC_ID) " +
                     " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";    

        try {
        	pstmt = conn.prepareStatement(sql);
        	while(rs.next()){
        	 pstmt.setInt(1,rs.getInt("PERSONPLAN_ID"));
        	 pstmt.setString(2,rs.getString("COM_ORG_ID"));
        	 pstmt.setString(3,rs.getString("PLAN_YY"));
        	 pstmt.setString(4,rs.getString("PLAN_BASE_YMD"));
        	 pstmt.setString(5,rs.getString("BG_GU"));
        	 pstmt.setString(6,rs.getString("BG_NM"));
        	 pstmt.setString(7,rs.getString("ORG_ID"));
        	 pstmt.setString(8,rs.getString("ORG_FULL_NM"));
        	 pstmt.setString(9,rs.getString("GROUP_TYPE"));
        	 pstmt.setString(10,rs.getString("POS_TYPE"));
        	 pstmt.setInt(11,rs.getInt("NOW_INWON"));
        	 pstmt.setInt(12,rs.getInt("BEFORE_YEAR_INWON"));
        	 pstmt.setInt(13,rs.getInt("AFTER_YEAR_INWON"));
        	 pstmt.setInt(14,rs.getInt("INWON_MONTH_01"));
        	 pstmt.setInt(15,rs.getInt("INWON_MONTH_02"));
        	 pstmt.setInt(16,rs.getInt("INWON_MONTH_03"));
        	 pstmt.setInt(17,rs.getInt("INWON_MONTH_04"));
        	 pstmt.setInt(18,rs.getInt("INWON_MONTH_05"));
        	 pstmt.setInt(19,rs.getInt("INWON_MONTH_06"));
        	 pstmt.setInt(20,rs.getInt("INWON_MONTH_07"));
        	 pstmt.setInt(21,rs.getInt("INWON_MONTH_08"));
        	 pstmt.setInt(22,rs.getInt("INWON_MONTH_09"));
        	 pstmt.setInt(23,rs.getInt("INWON_MONTH_10"));
        	 pstmt.setInt(24,rs.getInt("INWON_MONTH_11"));
        	 pstmt.setInt(25,rs.getInt("INWON_MONTH_12"));
        	 pstmt.setInt(26,rs.getInt("INWON_MONTH_TOT"));
        	 pstmt.setString(27,rs.getString("MOD_USER_ID"));
        	 pstmt.setDate(28,rs.getDate("MOD_DATE"));
        	 pstmt.setString(29,rs.getString("MOD_LOC_ID"));
        	 
        		pstmt.executeUpdate();
        		flag=1;
        	}
           
       
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs,pstmt, conn);
            SqlUtil.close(connIn);
        }
		return flag;
		
	}

}
