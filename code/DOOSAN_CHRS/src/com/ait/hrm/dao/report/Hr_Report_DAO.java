package com.ait.hrm.dao.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class Hr_Report_DAO {
	
	private static ServiceLocator services;
	
	public Hr_Report_DAO() {
		try {
		      services = ServiceLocator.getInstance();
		    }
		    catch (ServiceLocatorException ex) {
		    }
	}
	
	public int getHr_Counnt(String rowvalue,String colvalue,String rowtitle,String coltitle,String deptid) {
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String deptsql = "";
		
		if(!deptid.equals(""))
			deptsql = " and deptid like '"+deptid+"%' ";
		String sql = "select count(*) from hr_full_info where " + rowvalue +
					 "='"+ rowtitle +"' and "+colvalue+ "='"+coltitle+"' " +
					 deptsql;
		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
			
		} catch (ServiceLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}finally {
			
			SqlUtil.close(rs, pstmt, conn);
		}
		
		return count;
	}
	
	public int getHr_Cot(String colvalue,String coltitle,String deptid) {
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String deptsql = "";
		
		if(!deptid.equals(""))
			deptsql = " and deptid like '"+deptid+"%' ";
		String sql = "select count(*) from hr_full_info where "+
					 colvalue+ "='"+coltitle+"' " +deptsql;
		
		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
			
		} catch (ServiceLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}finally {
			
			SqlUtil.close(rs, pstmt, conn);
		}
		
		return count;
	}
	
	public int getHr_Cot(String colvalue,String coltitle,String deptid,String rowvalue,String item) {
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String deptsql = "";
		String sql = "" ;
		String whereSql = "" ;
		if(!deptid.equals(""))
			deptsql = " and deptid like '"+deptid+"%' ";
		if(item.equals("all"))  
			sql = "select count(*) from hr_full_info where "+
					 colvalue+ "='"+coltitle+"' " +deptsql;
		else{
			String temp[] = item.split(",");
			for (int i = 0; i < temp.length; i++) {
				if(whereSql.equals(""))
					whereSql += " and (" ;
				else
					whereSql += " or " ;
				whereSql += rowvalue + "='"+temp[i]+"' ";
			}
			sql = "select count(*) from hr_full_info where "+
			 colvalue+ "='"+coltitle+"' " + whereSql +") "
			 +deptsql;
		}

		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
			
		} catch (ServiceLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}finally {
			
			SqlUtil.close(rs, pstmt, conn);
		}
		
		return count;
	}
}
