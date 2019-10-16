package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArDate;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

public class ArDateBean {
	
	AdminBean  admin=ApplicationContext.getTheadLocal();
	String cpnyId=admin.getCpnyId();
	// 取得所有考勤时间
	public ArrayList getArDate() throws Exception {
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		String sql = " SELECT STAT_MODE_NO, STAT_TYPE_CODE,SY.CODE_NAME AS STAT_TYPE,AR.DESCRIBE,VALID_DATE_FROM, VALID_DATE_TO, START_DATE, END_DATE,AR.CPNY_ID,CPNY.CODE_NAME CPNYNAME " 
			       + " FROM AR_STATISTIC_DATE AR, SY_CODE SY ,SY_CODE CPNY  WHERE AR.STAT_TYPE_CODE = SY.CODE_ID AND AR.CPNY_ID=CPNY.CODE_ID(+) AND AR.CPNY_ID='"+cpnyId+"'  ORDER BY AR.STAT_TYPE_CODE, VALID_DATE_FROM ";
		Logger.getLogger(getClass()).debug(sql);
		ArrayList list = null;
		try{
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			list = new ArrayList();
			while (rs.next()) {
				ArDate ar = new ArDate();
				ar.setStatNo(rs.getInt("STAT_MODE_NO"));
				ar.setCode(StringUtil.checkNull(rs.getString("STAT_TYPE_CODE")));
				ar.setCodeName(StringUtil.checkNull(rs.getString("STAT_TYPE"))) ;
				ar.setDescribe(StringUtil.checkNull(rs.getString("DESCRIBE"))) ;
				ar.setFromDate(StringUtil.checkNull(this.getTime(rs.getString("VALID_DATE_FROM"))));
				ar.setToDate(StringUtil.checkNull(this.getTime(rs.getString("VALID_DATE_TO"))));
				ar.setStarDate(rs.getInt("START_DATE"));
				ar.setEndDate(rs.getInt("END_DATE"));
				ar.setCpnyId(StringUtil.checkNull(rs.getString("CPNY_ID")));
				ar.setCpnyName(StringUtil.checkNull(rs.getString("CPNYNAME")));
				list.add(ar);
			}
		}catch(SQLException sqlex){
			Logger.getLogger(getClass()).error(sqlex.toString());
		}finally{
			SqlUtil.close(rs, state, conn);
		}
		return list;
	}

	public String getTime(String time) {
		time = time.substring(0, time.indexOf(" "));
		return time;
	}
	
	//
    public ArrayList getArStatisticDeptIdList(int statModeNo) throws Exception {
        ArrayList depts = new ArrayList();
        Connection conn = null ;
        ResultSet rs = null;
        String Oracle_Sql = "select STAT_DEPTID from AR_STATISTIC_DEPTID where STAT_MODE_NO=" + statModeNo ;
        try {
        	conn =ConnBean.getConn();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(Oracle_Sql);
            while (rs.next()) {
                depts.add(rs.getString("STAT_DEPTID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        }
        return depts;
    }
	
	// 验证增加记录
	public int validateAddDate(ArDate arDate){
		int result = 0;
		Connection conn = null;
		Statement state = null;

		StringBuffer sql = new StringBuffer(500) ;
		
		sql.append("SELECT * FROM AR_STATISTIC_DATE AR WHERE AR.STAT_TYPE_CODE = '" + arDate.getCode() + "'   AND  AR.CPNY_ID='"+arDate.getCpnyId()+"'") ;
	
		Logger.getLogger(getClass()).debug(sql.toString());
		try{
			conn = ConnBean.getConn();
			state = conn.createStatement();
			result = state.executeUpdate(sql.toString());	
		}catch(SQLException sqlex){
			Logger.getLogger(getClass()).error(sqlex.toString());
		}finally{
			com.ait.util.SqlUtil.close(state, conn);
		}
		return result;
	}

	// 增加记录
	public int addDate(ArDate arDate){
		int r = -1;
		Connection conn = null;
		PreparedStatement state = null;
		//List<String> deptList = arDate.getDeptList() ;
		//int size = deptList.size() ;
		
		String sql = " insert into AR_STATISTIC_DATE(STAT_MODE_NO,STAT_TYPE_CODE,VALID_DATE_FROM,VALID_DATE_TO,START_DATE,END_DATE,DESCRIBE,CPNY_ID)" 
			       + " values(AR_STATISTIC_DATE_SEQ.NEXTVAL,?,TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),?,?,?,?)" ; 
		
		
		Logger.getLogger(getClass()).debug(sql) ;
		try{
			conn = ConnBean.getConn() ;
			conn.setAutoCommit(false) ;

			state = conn.prepareStatement(sql) ;
			state.setString(1, arDate.getCode()) ;
			state.setString(2, arDate.getFromDate()) ;
			state.setString(3, arDate.getToDate()) ;
			state.setInt(4, arDate.getStarDate()) ;
			state.setInt(5, arDate.getEndDate()) ;
			state.setString(6, arDate.getDescribe()) ;
			state.setString(7, arDate.getCpnyId()) ;
			state.executeUpdate() ;
			
			conn.commit() ;
		}catch(SQLException sqlex){
			try{
				conn.rollback() ;
			}catch(Exception e){}
			sqlex.printStackTrace() ;
			Logger.getLogger(getClass()).error(sqlex.toString());
		}finally{
			com.ait.util.SqlUtil.close(state, conn);
		}
		return r;
	}

	// 根据编号 获得记录
	public ArDate getArDate(int statNo){
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		String sql = " SELECT STAT_MODE_NO, STAT_TYPE_CODE,DESCRIBE, VALID_DATE_FROM, VALID_DATE_TO, START_DATE, END_DATE , AR_STATISTIC_DATE.CPNY_ID, CPNY.CODE_NAME CPNYNAME " 
			       + " FROM AR_STATISTIC_DATE,SY_CODE CPNY  WHERE STAT_MODE_NO= " + statNo+  " AND AR_STATISTIC_DATE.CPNY_ID=CPNY.CODE_ID(+) ";
		Logger.getLogger(getClass()).debug(sql);
		ArDate ar = null;
		try{
			ar = new ArDate();
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				ar.setStatNo(rs.getInt("STAT_MODE_NO"));
				ar.setCode(returnNull(rs.getString("STAT_TYPE_CODE")));
				ar.setDescribe(returnNull(rs.getString("DESCRIBE"))) ;
				ar.setFromDate(returnNull(this.getTime(rs.getString("VALID_DATE_FROM"))));
				ar.setToDate(returnNull(this.getTime(rs.getString("VALID_DATE_TO"))));
				ar.setStarDate(rs.getInt("START_DATE"));
				ar.setEndDate(rs.getInt("END_DATE"));
				ar.setCpnyId(rs.getString("CPNY_ID"));
				ar.setCpnyName(rs.getString("CPNYNAME"));
			}
		}catch(SQLException sqlex){
			Logger.getLogger(getClass()).error(sqlex.toString());
		}finally{
			SqlUtil.close(rs, state, conn);
		}
		return ar;
	}
	
	// 验证修改记录
	public int validateUpdateDate(ArDate arDate){
		int result = 0;
		Connection conn = null;
		Statement state = null;
		
		StringBuffer sql = new StringBuffer(500) ;
		
		sql.append("SELECT * FROM AR_STATISTIC_DATE AR WHERE AR.STAT_TYPE_CODE = '" + arDate.getCode() + "' AND STAT_MODE_NO <> " + arDate.getStatNo()+" AND AR.CPNY_ID='"+arDate.getCpnyId()+"'") ;
	
		Logger.getLogger(getClass()).debug(sql.toString());
		try{
			conn = ConnBean.getConn();
			state = conn.createStatement();
			result = state.executeUpdate(sql.toString());	
		}catch(SQLException sqlex){
			Logger.getLogger(getClass()).error(sqlex.toString());
		}finally{
			com.ait.util.SqlUtil.close(state, conn);
		}
		return result;
	}

	// 修改
	public int editArDate(ArDate arDate) throws Exception {
		int r = -1;
		Connection conn = null;
		PreparedStatement state = null;
		
		String sql2 = "DELETE AR_STATISTIC_DATE WHERE STAT_MODE_NO = ?" ; 
		
		String sql3 = " insert into AR_STATISTIC_DATE(STAT_MODE_NO,STAT_TYPE_CODE,VALID_DATE_FROM,VALID_DATE_TO,START_DATE,END_DATE,DESCRIBE,CPNY_ID)" 
			       + " values(AR_STATISTIC_DATE_SEQ.NEXTVAL,?,TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),?,?,?,?)" ; 
		
		Logger.getLogger(getClass()).debug(sql2) ;
		Logger.getLogger(getClass()).debug(sql3) ;
		try{
			conn = ConnBean.getConn() ;
			conn.setAutoCommit(false) ;
			
			state = conn.prepareStatement(sql2) ;
			state.setInt(1, arDate.getStatNo()) ;
			state.executeUpdate() ;
			
			state = conn.prepareStatement(sql3) ;
			state.setString(1, arDate.getCode()) ;
			state.setString(2, arDate.getFromDate()) ;
			state.setString(3, arDate.getToDate()) ;
			state.setInt(4, arDate.getStarDate()) ;
			state.setInt(5, arDate.getEndDate()) ;
			state.setString(6, arDate.getDescribe()) ;
			state.setString(7, arDate.getCpnyId()) ;
			state.executeUpdate() ;
			
			conn.commit() ;
		}catch(SQLException sqlex){
			try{
				conn.rollback() ;
			}catch(Exception e){}
			sqlex.printStackTrace() ;
			Logger.getLogger(getClass()).error(sqlex.toString());
		}finally{
			com.ait.util.SqlUtil.close(state, conn);
		}
		return r;
	}

	// 删除
	public int delArDate(int statNo){
		int affRow = 0;
		Connection conn = null;
		Statement state = null;
		String sql = "DELETE FROM AR_STATISTIC_DATE WHERE STAT_MODE_NO=" + statNo;
		Logger.getLogger(getClass()).debug(sql);
		try{
			conn = ConnBean.getConn();
			conn.setAutoCommit(false) ;
			
			state = conn.createStatement();
			affRow = state.executeUpdate(sql);
			
			conn.commit() ;
		}catch(SQLException sqlex){
			try{
				conn.rollback() ;
			}catch(Exception e){}
			Logger.getLogger(getClass()).error(sqlex.toString());
		}finally{
			SqlUtil.close(state, conn);
		}
		return affRow;
	}

	public String returnNull(String str) {
		if (str == null || str.trim().length() == 0)
			return "&nbsp;";
		return str;
	}

}
