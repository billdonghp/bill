package com.ait.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.core.db.jdbc.JdbcTemplate;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.bean.Department;
import com.ait.hrm.entity.AgeReport;
import com.ait.hrm.entity.DimissionReport;
import com.ait.hrm.entity.EmpContractReport;
import com.ait.hrm.entity.EmpContrastBean;
import com.ait.hrm.entity.EmpContrastReport;
import com.ait.hrm.entity.EmpDistribution;
import com.ait.hrm.entity.EmpStatusReport;
import com.ait.hrm.entity.EndContractReport;
import com.ait.hrm.entity.JoinTypeReport;
import com.ait.hrm.entity.MarriageReport;
import com.ait.hrm.entity.NationReport;
import com.ait.hrm.entity.PostGroupReport;
import com.ait.hrm.entity.PostReport;
import com.ait.hrm.entity.PreviewUpgradeReports;
import com.ait.hrm.entity.SexReport;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class ReportDAO {
	
	private PaServices paServices ;
	private ArServices arServices ;
	
	public ReportDAO() { 
		paServices = PaServices.getInstance() ;
		arServices = new ArServices() ;
	}
	//获取所有的职级
	public List getPostGradeAll() {
		List postGrade = new ArrayList() ;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select t.post_grade_title from hr_post_grade t order by t.post_group_id,t.post_grade_id" ;
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				postGrade.add(rs.getString("post_grade_title")) ;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {            
			SqlUtil.close(rs, pstmt, conn);
		}
		return postGrade ;
	}
	
	//获取一个部门的信息
	public HashMap<String, String> getPost(String post_code,String deptid) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =  " Select distinct hd.POST_CODE,hd.POST,hd.deptcount,hp.allcount,TRUNC(hd.deptcount*100/hp.allcount,1)||'%' As bb "+
					  " From "+
					  " (Select Sum(deptcount) As deptcount,hh.POST,hh.POST_CODE "+
					  " From  (Select hr.POST,hr.POST_CODE,hr.department,hr.deptid,Count(empid) As deptcount "+
					  " From hr_employee_v hr "+
					  " Where hr.ACTIVITY = 1 and hr.post_code In (Select code_id From sy_code Where parent_code='PostCode') "+ 
					  " And hr.deptid In  "+
					  " (Select deptid From  hr_department Where " +
					  " date_ended > sysdate or date_ended = '' or date_ended is null and dept_level>=1 " +
					  " And hr.status_code<>'Status03' "+
					  " Start With deptid=? Connect By Prior deptid=parent_dept_id) "+ 
					  " Group By hr.POST,hr.department,hr.POST_CODE,hr.deptid) hh "+
					  " Where hh.POST_code=?  "+
					  " Group By hh.POST,hh.POST_CODE "+
					  " )  hd , "+
					  " (Select hr.POST,Count(empid) As allcount "+
					  " From hr_employee_v hr "+
					  " Where hr.ACTIVITY = 1 and hr.post_code In (Select code_id From sy_code Where parent_code='PostCode') " +
					  " And hr.status_code<>'Status01' "+ 
					  " Group By hr.POST)  hp "+
					  " Where hd.POST = hp.POST(+) ";
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptid);
			pstmt.setString(2, post_code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				map.put("post", rs.getString("post_code")!=null?rs.getString("post_code"):"");
				map.put("deptcount", rs.getString("deptcount")!=null?rs.getString("deptcount"):"");
				map.put("allcount", rs.getString("allcount")!=null?rs.getString("allcount"):"");
				map.put("bb", rs.getString("bb")!=null?rs.getString("bb"):"");
			}
			
			sql ="Select Count(*) As sumdeptemp" + 
					"  From hr_employee_v hr " + 
					"  Where hr.deptid In  " + 
					" (Select deptid From  hr_department Where " +
					" date_ended > sysdate or date_ended = '' or date_ended is null and " +
					" dept_level>=1" +
					" Start With deptid=? Connect By Prior deptid=parent_dept_id)" +
					" And hr.status_code<>'Status03' and hr.ACTIVITY = 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				map.put("hj", rs.getString(1));
			}

		} catch (Exception ex) {
			
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return map;
		
	}
	
	//获得部门人数
	public int getdepts(String deptid) {
		
		int count = 0;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Select Count(*) From hr_employee_v Where deptid In (Select deptid From  hr_department Where " +
					 "date_ended > sysdate or date_ended = '' or date_ended is null and " +
					 "dept_level>=1 "+					 
					 "Start With deptid=? Connect By Prior deptid=parent_dept_id) " +
					 "And status_code<>'Status03' and ACTIVITY = 1";
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		
		return count;
		
	}
	
	
	//获得所有人数
	public int getdepts() {
		
		int count = 0;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Select Count(*) From hr_employee_v where status_code<>'Status03' and ACTIVITY=1";
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1)-1;
			}

		} catch (Exception ex) {
			
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		
		return count;
		
	}
	
	//获取岗位人数
	public int getDate(String post_code) {
		
		int count = 0;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Select hr.POST,Count(empid) As allcount "+
					 " From hr_employee_v hr " + 
					 " Where hr.post_code In (Select code_id From sy_code Where parent_code='PostCode') " +
					 " And hr.post_code=? And status_code<>'Status03' and hr.ACTIVITY=1 "+
				     " Group By hr.POST ";
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post_code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("allcount");
			}

		} catch (Exception ex) {
			
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		
		return count;
		
	}
	 
//	获得所有下属部门
	public List getDept_pay_insurance(String date,String deptid) {
		List list = new ArrayList();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT HD.DEPTID, HD.DEPTNAME, HD.DEPT_EN_NAME, HD.DEPT_LEVEL,HD.DEPT_KOR_NAME FROM HR_DEPARTMENT HD "
				  +	" WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= ADD_MONTHS(TO_DATE(?,'YYYYMM'),1) -1  AND "
				  + " TO_DATE(?,'YYYYMM') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) AND HD.PARENT_DEPT_ID = ? ORDER BY HD.DEPTID " ;
		
		Department dept = null ;
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,date);   
			pstmt.setString(2,date);   
			pstmt.setString(3,deptid);    
			rs = pstmt.executeQuery();
			while(rs.next()){
				dept = new Department() ; 
				dept.setDeptID(rs.getString("deptid")) ;
				dept.setDeptName(rs.getString("deptname")) ;
				dept.setDeptEnName(rs.getString("dept_en_name")) ;
				dept.setKorDept(rs.getString("dept_kor_name")) ;
				dept.setDeptLevel(rs.getInt("DEPT_LEVEL")) ;
				list.add(dept) ;
			}  
			
		} catch (Exception ex) {
			ex.printStackTrace() ;
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		
		return list;
	}
	
//	获得所有下属部门
	public List getDept_peopleware(String date,String deptid,int dept_level) {
		List list = new ArrayList();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT HD.DEPTID, HD.DEPTNAME, HD.DEPT_EN_NAME, HD.DEPT_LEVEL,HD.DEPT_KOR_NAME FROM HR_DEPARTMENT HD "
			  +	" WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= ADD_MONTHS(TO_DATE(?,'YYYYMM'),1) -1  AND "
			  + " TO_DATE(?,'YYYYMM') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) AND HD.PARENT_DEPT_ID = ? " ;
		
		if (dept_level == 3)
		{
			sql = "SELECT HD.DEPTID, HD.DEPTNAME, HD.DEPT_EN_NAME, HD.DEPT_LEVEL,HD.DEPT_KOR_NAME FROM HR_DEPARTMENT HD WHERE HD.DEPTID = ? UNION ALL " 
				  + sql ;
		}
		
		Department dept = null ;
		try{
			int i = 1 ;
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			if (dept_level == 3)
			{
				pstmt.setString(i++,deptid); 
			}
			pstmt.setString(i++,date);   
			pstmt.setString(i++,date);   
			pstmt.setString(i++,deptid);    
			rs = pstmt.executeQuery();
			while(rs.next()){
				dept = new Department() ; 
				dept.setDeptID(rs.getString("deptid")) ;
				dept.setDeptName(rs.getString("deptname")) ;
				dept.setDeptEnName(rs.getString("dept_en_name")) ;
				dept.setKorDept(rs.getString("dept_kor_name")) ;
				dept.setDeptLevel(rs.getInt("DEPT_LEVEL")) ;
				list.add(dept) ;
			}  
			
		} catch (Exception ex) {
			ex.printStackTrace() ;
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		
		return list;
	}
	
//	获得所有下属部门
	public List getDept_PayDaily_head(String date,String deptid) {
		List list = new ArrayList();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT HD.DEPTID, HD.DEPTNAME, HD.DEPT_EN_NAME, HD.DEPT_LEVEL,HD.DEPT_KOR_NAME FROM HR_DEPARTMENT HD "
				  +	" WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= TO_DATE(?,'YYYY-MM-DD')  AND "
				  + " TO_DATE(?,'YYYY-MM-DD') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) AND HD.PARENT_DEPT_ID = ? ORDER BY HD.DEPTID " ;
		
		Department dept = null ;
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,date);   
			pstmt.setString(2,date);   
			pstmt.setString(3,deptid);    
			rs = pstmt.executeQuery();
			while(rs.next()){
				dept = new Department() ; 
				dept.setDeptID(rs.getString("deptid")) ;
				dept.setDeptName(rs.getString("deptname")) ;
				dept.setDeptEnName(rs.getString("dept_en_name")) ;
				dept.setKorDept(rs.getString("dept_kor_name")) ;
				dept.setDeptLevel(rs.getInt("DEPT_LEVEL")) ;
				list.add(dept) ;
			}  
			
		} catch (Exception ex) {
			ex.printStackTrace() ;
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		
		return list;
	}
	
//	获得所有下属部门
	public List getDept_PayDaily(String date,String deptid,int dept_level) {
		List list = new ArrayList();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT HD.DEPTID, HD.DEPTNAME, HD.DEPT_EN_NAME, HD.DEPT_LEVEL,HD.DEPT_KOR_NAME FROM HR_DEPARTMENT HD "
			  +	" WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= TO_DATE(?,'YYYY-MM-DD')  AND "
			  + " TO_DATE(?,'YYYY-MM-DD') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) AND HD.PARENT_DEPT_ID = ? " ;
		
		if (dept_level == 3)
		{
			sql = "SELECT HD.DEPTID, HD.DEPTNAME, HD.DEPT_EN_NAME, HD.DEPT_LEVEL,HD.DEPT_KOR_NAME FROM HR_DEPARTMENT HD WHERE HD.DEPTID = ? UNION ALL " 
				  + sql ;
		}
		
		Department dept = null ;
		try{
			int i = 1 ;
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			if (dept_level == 3)
			{
				pstmt.setString(i++,deptid); 
			}
			pstmt.setString(i++,date);   
			pstmt.setString(i++,date);   
			pstmt.setString(i++,deptid);    
			rs = pstmt.executeQuery();
			while(rs.next()){
				dept = new Department() ; 
				dept.setDeptID(rs.getString("deptid")) ;
				dept.setDeptName(rs.getString("deptname")) ;
				dept.setDeptEnName(rs.getString("dept_en_name")) ;
				dept.setKorDept(rs.getString("dept_kor_name")) ;
				dept.setDeptLevel(rs.getInt("DEPT_LEVEL")) ;
				list.add(dept) ;
			}  
			
		} catch (Exception ex) {
			ex.printStackTrace() ;
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		
		return list;
	}
	
	public int getDeptRowspan_paDaily(String date,String deptid,int dept_level)
	{
		int rowspan = 0 ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "" ;
		
		if (dept_level == 3)
		{
			sql = " SELECT COUNT(HD.DEPTID) + 1 FROM HR_DEPARTMENT HD "
				+ " WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= TO_DATE(?,'YYYY-MM-DD') " 
                + " AND TO_DATE(?,'YYYY-MM-DD') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) "
                + " AND HD.DEPT_LEVEL < 5 "
                + " START WITH DEPTID = ? CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID " ;
		}
		else
		{
			sql = " SELECT " 
				+ " (SELECT COUNT(HD.DEPTID) FROM HR_DEPARTMENT HD "
				+ " WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= TO_DATE(?,'YYYY-MM-DD') " 
                + " AND TO_DATE(?,'YYYY-MM-DD') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) "
                + " AND HD.DEPT_LEVEL < 5 "
                + " START WITH DEPTID = ? CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID) " 
                + " + "
                + " (SELECT COUNT(HD.DEPTID) FROM HR_DEPARTMENT HD "
                + " WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= TO_DATE(?,'YYYY-MM-DD') " 
                + " AND TO_DATE(?,'YYYY-MM-DD') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) "
                + " AND HD.DEPT_LEVEL < 5 AND HD.PARENT_DEPT_ID = ?) " 
                + " FROM DUAL  ";
		}
		
		
		               
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,date) ;
			pstmt.setString(2,date) ;
			pstmt.setString(3,deptid) ;
			
			if(dept_level != 3)
			{
				pstmt.setString(4,date) ;
				pstmt.setString(5,date) ;
				pstmt.setString(6,deptid) ;
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				rowspan = rs.getInt(1) ;
			}  
			
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return rowspan ;
	}
	
//	获得日工资统计的开头
	public SimpleMap getData_paDaily_head(String date,String deptid)
	{
		SimpleMap parameterObject = new SimpleMap();
		
		parameterObject.setString("date", date) ;
		parameterObject.setString("deptid", deptid) ;
		try {
			parameterObject = (SimpleMap)paServices.retrievePaDaily_head(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return parameterObject ;
	}
	
	//获得日工资统计的中间
	public SimpleMap getData_paDaily(String date,String deptid,int dept_level)
	{
		SimpleMap parameterObject = new SimpleMap();
		
		parameterObject.setString("date", date) ;
		parameterObject.setString("deptid", deptid) ;
		try {
			if (dept_level == 3)
			{
				parameterObject = (SimpleMap)paServices.retrievePaDaily_head(parameterObject) ;
			}else
			{
				parameterObject = (SimpleMap)paServices.retrievePaDaily(parameterObject) ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return parameterObject ;
	}
	
	
	
//	获得部门别工资保险情况 数据
	public List getData_pay_insurance(List deptList,String date) {
		
		List list = new ArrayList() ;
		
		String sql = " SELECT COUNT(EMPID) AS EMP_COUNT,NVL(SUM(SALARY_PAYABLE),0) AS SALARY_PAYABLE, NVL(SUM(CPY_INS_TOTAL),0) AS CPY_INS_TOTAL, "
				   + " NVL(TRUNC(SUM(CPY_INS_TOTAL)/DECODE(SUM(SALARY_PAYABLE),0,1,SUM(SALARY_PAYABLE))*100,1),0) AS PROPORTION,NVL(SUM(SALARY_PAYABLE +  CPY_INS_TOTAL),0) AS TOTAL, "
				   + " NVL(ROUND(SUM(SALARY_PAYABLE + CPY_INS_TOTAL)/COUNT(EMPID),2),0) AS AVERAGE_WAGE FROM PA_HISTORY WHERE PA_MONTH = '" + date + "'" ;
		String total_sql = "" ;
		String sqlContent = "" ;
		
		for(int i = 0 ; i < deptList.size() ; i ++)
		{
			Department dept = (Department)deptList.get(i) ;
			
			sqlContent += sql + " AND DEPTID IN (SELECT deptid FROM hr_department START WITH deptid = '" + dept.getDeptID() + "' CONNECT BY PRIOR deptid = parent_dept_id)" ;   
			sqlContent += " UNION ALL " ;
			total_sql +=  " DEPTID IN (SELECT deptid FROM hr_department START WITH deptid = '" + dept.getDeptID() + "' CONNECT BY PRIOR deptid = parent_dept_id)" ;
			if(i < deptList.size() - 1  )
			{
				total_sql += " OR " ;
			}
			
		}
		sqlContent += sql + " AND ( " +  total_sql + ")" ;
		
		SimpleMap parameterObject = new SimpleMap() ;
		parameterObject.setString("sqlContent", sqlContent) ;
		try {
			list = paServices.retrievePayInsurance(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		
		return list;
	}
	
//	获得部门别工资保险情况 数据 合计
	public List getData_pay_insurance_end(List deptList,String sDate,String eDate) {
		PaServices paServices = PaServices.getInstance() ;
		List list = new ArrayList() ;
		
		String sqlContent = " SELECT COUNT(EMPID) AS EMP_COUNT,NVL(SUM(SALARY_PAYABLE),0) AS SALARY_PAYABLE, NVL(SUM(CPY_INS_TOTAL),0) AS CPY_INS_TOTAL, "
				   + " NVL(TRUNC(SUM(CPY_INS_TOTAL)/SUM(SALARY_PAYABLE)*100,2),0) AS PROPORTION,NVL(SUM(SALARY_PAYABLE +  CPY_INS_TOTAL),0) AS TOTAL, "
				   + " NVL(ROUND(SUM(SALARY_PAYABLE + CPY_INS_TOTAL)/COUNT(EMPID),2),0) AS AVERAGE_WAGE FROM PA_HISTORY WHERE PA_MONTH BETWEEN '" + sDate + "' and '" + eDate + "'" ;
		String total_sql = "" ;
		
		for(int i = 0 ; i < deptList.size() ; i ++)
		{
			Department dept = (Department)deptList.get(i) ;
			
			total_sql +=  " DEPTID IN (SELECT deptid FROM hr_department START WITH deptid = '" + dept.getDeptID() + "' CONNECT BY PRIOR deptid = parent_dept_id)" ;
			if(i < deptList.size() - 1  )
			{
				total_sql += " OR " ;
			}
			
		}
		sqlContent += " AND ( " +  total_sql + ")" ;
		
		Logger.getLogger(getClass()).debug("SQL: "+sqlContent);
		
		SimpleMap parameterObject = new SimpleMap() ;
		parameterObject.setString("sqlContent", sqlContent) ;
		try {
			list = paServices.retrievePayInsurance(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		
		return list;
	}
	
	//获得所有部门
	public ArrayList getDept() {
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Select deptid,deptname From  hr_department Where " +
				"date_ended > sysdate or date_ended = '' or date_ended is null and " +
				"dept_level=1 ";
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("code", rs.getString("deptid"));
				map.put("name", rs.getString("deptname"));	
				list.add(map);
			}

		} catch (Exception ex) {
			
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	//获得部门名字
	public String getDeptName(String deptid) {
		
		String deptname="";
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Select deptname From  hr_department Where " +
				" date_ended > sysdate or date_ended = '' or date_ended is null and " +
				" deptid=? ";
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				deptname = rs.getString(1);
			}

		} catch (Exception ex) {
			
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		
		return deptname;
	}
	
	public List getPostInfoByDeptIdCN(String deptId) {
		List prlist = new ArrayList() ;
		PostReport pr = null ;
		ResultSet rs = null;
		Connection conn = null;
		String deptName = "" ;
		PreparedStatement pstmt = null;
		List deptIdTemp = new ArrayList() ;
		List deptIdHigh = new ArrayList() ;
		boolean flagTemp = false ;
		String sql = "select t.deptid from hr_report_post_CN_v t where t.deptid like ?" ;
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptId + ".__") ;
			rs = pstmt.executeQuery();
			while(rs.next()){
				deptIdHigh.add(rs.getString(1)) ;
				flagTemp = true ;
			}
			if(flagTemp){
				for (int i = 0; i < deptIdHigh.size(); i++) {
					deptIdTemp = new ArrayList() ;
					pr = new PostReport() ;
					sql = "select t.deptid,t.deptname from hr_report_post_CN_v t where t.deptid like '"+deptIdHigh.get(i)+"%'" ;
					Logger.getLogger(getClass()).debug("SQL: "+sql);
					SqlUtil.close(rs, pstmt);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					boolean flag = true ;
					while(rs.next()) {
						if(flag)
							pr.setDeptName(rs.getString(2)) ;
						flag = false ;
						deptIdTemp.add(rs.getString(1)) ;
					}
					for (int j = 0; j < deptIdTemp.size(); j++) {
						sql = "select * from hr_report_post_CN_v t where t.deptid = ?" ;
						Logger.getLogger(getClass()).debug("SQL: "+sql);
						SqlUtil.close(rs, pstmt);
						pstmt = conn.prepareStatement(sql) ;
						pstmt.setString(1,deptIdTemp.get(j)+"") ;
						rs = pstmt.executeQuery() ;
						if(rs.next())
							pr = this.createPostReport(rs,pr) ;
					}
					prlist.add(pr) ;
				}
				this.setSubTotal(prlist) ;
			}
			else{
				pr = new PostReport() ;
				sql = "select * from hr_report_post_CN_v t where t.deptid = ?" ;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, deptId) ;
				rs = pstmt.executeQuery();
				if(rs.next())
					pr = this.createPostReport(rs, pr) ;
				prlist.add(pr) ;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return prlist ;
	}
	
	public List getPostInfoByDeptIdKR(String deptId) {
		List prlist = new ArrayList() ;
		PostReport pr = null ;
		ResultSet rs = null;
		Connection conn = null;
		String deptName = "" ;
		PreparedStatement pstmt = null;
		List deptIdTemp = new ArrayList() ;
		List deptIdHigh = new ArrayList() ;
		boolean flagTemp = false ;
		String sql = "select t.deptid from hr_report_post_KR_v t where t.deptid like ?" ;
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptId + ".__") ;
			rs = pstmt.executeQuery();
			while(rs.next()){
				deptIdHigh.add(rs.getString(1)) ;
				flagTemp = true ;
			}
			if(flagTemp){
				for (int i = 0; i < deptIdHigh.size(); i++) {
					deptIdTemp = new ArrayList() ;
					pr = new PostReport() ;
					sql = "select t.deptid,t.deptname from hr_report_post_KR_v t where t.deptid like '"+deptIdHigh.get(i)+"%'" ;
					Logger.getLogger(getClass()).debug("SQL: "+sql);
					SqlUtil.close(rs, pstmt);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					boolean flag = true ;
					while(rs.next()) {
						if(flag)
							pr.setDeptName(rs.getString(2)) ;
						flag = false ;
						deptIdTemp.add(rs.getString(1)) ;
					}
					for (int j = 0; j < deptIdTemp.size(); j++) {
						sql = "select * from hr_report_post_KR_v t where t.deptid = ?" ;
						Logger.getLogger(getClass()).debug("SQL: "+sql);
						SqlUtil.close(rs, pstmt);
						pstmt = conn.prepareStatement(sql) ;
						pstmt.setString(1,deptIdTemp.get(j)+"") ;
						rs = pstmt.executeQuery() ;
						if(rs.next())
							pr = this.createPostReportKR(rs,pr) ;
					}
					prlist.add(pr) ;
				}
				this.setSubTotal(prlist) ;
			}
			else{
				pr = new PostReport() ;
				sql = "select * from hr_report_post_KR_v t where t.deptid = ?" ;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, deptId) ;
				rs = pstmt.executeQuery();
				if(rs.next())
					pr = this.createPostReportKR(rs, pr) ;
				prlist.add(pr) ;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return prlist ;
	}
	
	public List getPostInfoByDeptIdSpecialCN(String deptId) {
		List prlist = new ArrayList() ;
		PostReport pr = null ;
		ResultSet rs = null;
		Connection conn = null;
		String deptName = "" ;
		PreparedStatement pstmt = null;
		List deptIdTemp = new ArrayList() ;
		List deptIdHigh = new ArrayList() ;
		boolean flagTemp = false ;
		String sql = "select t.deptid from hr_report_post_CN_v t where t.deptid like ?" ;
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptId + ".__") ;
			rs = pstmt.executeQuery();
			while(rs.next()){
				deptIdHigh.add(rs.getString(1)) ;
				flagTemp = true ;
			}
			if(flagTemp){
				for (int i = 0; i < deptIdHigh.size(); i++) {
					deptIdTemp = new ArrayList() ;
					pr = new PostReport() ;
					sql = "select t.deptid,t.deptname from hr_report_post_CN_v t where t.deptid like '"+deptIdHigh.get(i)+"%'" ;
					Logger.getLogger(getClass()).debug("SQL: "+sql);
					SqlUtil.close(rs, pstmt);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					boolean flag = true ;
					while(rs.next()) {
						if(flag)
							pr.setDeptName(rs.getString(2)) ;
						flag = false ;
						deptIdTemp.add(rs.getString(1)) ;
					}
					for (int j = 0; j < deptIdTemp.size(); j++) {
						sql = "select * from hr_report_post_CN_v t where t.deptid = ?" ;
						Logger.getLogger(getClass()).debug("SQL: "+sql);
						SqlUtil.close(rs, pstmt);
						pstmt = conn.prepareStatement(sql) ;
						pstmt.setString(1,deptIdTemp.get(j)+"") ;
						rs = pstmt.executeQuery() ;
						if(rs.next())
							pr = this.createPostReportSpecial(rs,pr,deptIdTemp.get(j)+"") ;
					}
					prlist.add(pr) ;
				}
				this.setSubTotal(prlist) ;
			}
			else{
				pr = new PostReport() ;
				sql = "select * from hr_report_post_CN_v t where t.deptid = ?" ;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, deptId) ;
				rs = pstmt.executeQuery();
				if(rs.next())
					pr = this.createPostReportSpecial(rs, pr,deptId) ;
				prlist.add(pr) ;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return prlist ;
	}
	
	public List getPostInfoByDeptIdSpecialKR(String deptId) {
		List prlist = new ArrayList() ;
		PostReport pr = null ;
		ResultSet rs = null;
		Connection conn = null;
		String deptName = "" ;
		PreparedStatement pstmt = null;
		List deptIdTemp = new ArrayList() ;
		List deptIdHigh = new ArrayList() ;
		boolean flagTemp = false ;
		String sql = "select t.deptid from hr_report_post_KR_v t where t.deptid like ?" ;
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptId + ".__") ;
			rs = pstmt.executeQuery();
			while(rs.next()){
				deptIdHigh.add(rs.getString(1)) ;
				flagTemp = true ;
			}
			if(flagTemp){
				for (int i = 0; i < deptIdHigh.size(); i++) {
					deptIdTemp = new ArrayList() ;
					pr = new PostReport() ;
					sql = "select t.deptid,t.deptname from hr_report_post_KR_v t where t.deptid like '"+deptIdHigh.get(i)+"%'" ;
					Logger.getLogger(getClass()).debug("SQL: "+sql);
					SqlUtil.close(rs, pstmt);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					boolean flag = true ;
					while(rs.next()) {
						if(flag)
							pr.setDeptName(rs.getString(2)) ;
						flag = false ;
						deptIdTemp.add(rs.getString(1)) ;
					}
					for (int j = 0; j < deptIdTemp.size(); j++) {
						sql = "select * from hr_report_post_KR_v t where t.deptid = ?" ;
						Logger.getLogger(getClass()).debug("SQL: "+sql);
						SqlUtil.close(rs, pstmt);
						pstmt = conn.prepareStatement(sql) ;
						pstmt.setString(1,deptIdTemp.get(j)+"") ;
						rs = pstmt.executeQuery() ;
						if(rs.next())
							pr = this.createPostReportSpecialKR(rs,pr,deptIdTemp.get(j)+"") ;
					}
					prlist.add(pr) ;
				}
				this.setSubTotal(prlist) ;
			}
			else{
				pr = new PostReport() ;
				sql = "select * from hr_report_post_KR_v t where t.deptid = ?" ;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, deptId) ;
				rs = pstmt.executeQuery();
				if(rs.next())
					pr = this.createPostReportSpecialKR(rs, pr,deptId) ;
				prlist.add(pr) ;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return prlist ;
	}
	
	public int getNumByPost(String post_grade,String deptid,String nationality){
		int num = 0;
		String TSQL = "" ;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		if(post_grade.equals("cizhang"))
			TSQL = " and hr.post_grade_code = 'PG16' and hr.post_code not in ('PostCode173','PostCode3','PostCode171','PostCode38'," +
				"'PostCode41','PostCode73','PostCode79','PostCode91',"+
				"'PostCode135','PostCode107','PostCode168','PostCode145','PostCode20') " ;
 		if(post_grade.equals("buzhangY"))
 			TSQL = " and hr.postcode in ('PostCode173','PostCode3','PostCode171','PostCode38'," +
 					"'PostCode41','PostCode73','PostCode79','PostCode91',"+
 					"'PostCode135','PostCode107','PostCode168','PostCode145','PostCode20') " ;
 		else if(post_grade.equals("buzhangN"))
 			TSQL += " and hr.post_grade_code = 'PG1' AND hr.post_code not in ('PostCode173','PostCode3','PostCode171','PostCode38'," +
 					"'PostCode41','PostCode73','PostCode79','PostCode91',"+
 					"'PostCode135','PostCode107','PostCode168','PostCode145','PostCode20','PostCode78','PostCode2','PostCode36') " ;
 		if(post_grade.equals("managerY"))
 			TSQL = " and hr.post_grade_code = 'PG2' AND hr.post_code in ('PostCode21','PostCode24','PostCode80','PostCode100'," +
			"'PostCode92','PostCode144','PostCode136','PostCode108','PostCode53',"+
			"'PostCode194','PostCode119','PostCode160','PostCode146','PostCode196','PostCode9') " ;
 		else if(post_grade.equals("managerN"))
			TSQL += " and hr.post_grade_code = 'PG2' AND hr.post_code not in ('PostCode21','PostCode24','PostCode80','PostCode100'," +
			"'PostCode92','PostCode144','PostCode136','PostCode108','PostCode53',"+
			"'PostCode194','PostCode119','PostCode160','PostCode146','PostCode196','PostCode9') " ;
 		if(post_grade.equals("zhurenY"))
 			TSQL = " and hr.post_grade_code = 'PG6' AND hr.post_code in ('PostCode83','PostCode93','PostCode97'," +
 					"'PostCode101','PostCode139','PostCode128','PostCode113','PostCode122')" ;
 		else if(post_grade.equals("zhurenN"))
			TSQL += " and hr.post_grade_code = 'PG6' AND hr.post_code not in ('PostCode83','PostCode93','PostCode97'," +
 					"'PostCode101','PostCode139','PostCode128','PostCode113','PostCode122')" ;
 		String sql = "select count(hr.empid) as count from hr_employee hr,hr_personal_info hp where hr.empid = hp.empid " + TSQL + " and deptid = '" + deptid +"' " +
 				"and hr.status_code <> 'Status03' and hp.nationality_code "+(nationality.equals("")?"<> 'N11'":"= 'N11'");
 		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt("count") ;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return num ;
	}
	
	private PostReport createPostReportSpecial(ResultSet rs,PostReport pr,String deptid) {
		try {
			int numY = this.getNumByPost("managerY", deptid,"") ;
			int numN = this.getNumByPost("managerN", deptid,"") ;
			pr.setSubTotal(pr.getSubTotal()+numY) ;
			pr.setSubTotal(pr.getSubTotal()+numN) ;
			pr.setManager(numY+pr.getManager()) ;
			pr.setManager2(numN+pr.getManager2()) ;
			pr.setZhuGuan(rs.getInt(4)+pr.getZhuGuan()) ;
			pr.setZhuanYuan(rs.getInt(5)+pr.getZhuanYuan()) ;
			pr.setZhuLi(rs.getInt(6)+pr.getZhuLi()) ;
			numY = this.getNumByPost("zhurenY", deptid,"") ;
			numN = this.getNumByPost("zhurenN", deptid,"") ;
			pr.setSubTotal(pr.getSubTotal()+numY) ;
			pr.setSubTotal(pr.getSubTotal()+numN) ;
			pr.setZhuRen(numY+pr.getZhuRen()) ;
			pr.setZhuren2(numN+pr.getZhuren2()) ;
			pr.setMonitor(rs.getInt(8)+pr.getMonitor()) ;
			pr.setWorkMan(rs.getInt(9)+pr.getWorkMan()) ;
			pr.setEngineer(rs.getInt(10)+pr.getEngineer()) ;
			pr.setAssistantEngineer(rs.getInt(11)+pr.getAssistantEngineer()) ;
			pr.setHighEngineer(rs.getInt(12)+pr.getHighEngineer()) ;
			pr.setTeShuZhi(rs.getInt(13)+pr.getTeShuZhi());
			for (int i = 4; i < 14; i++) {
				if(i == 7)
					continue ;
				pr.setSubTotal(rs.getInt(i)+pr.getSubTotal()) ;
			}
 		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return pr ;
	}
	
	private PostReport createPostReportSpecialKR(ResultSet rs,PostReport pr,String deptid) {
		try {
			int numY = this.getNumByPost("buzhangY", deptid,"韩国") ;
			int numN = this.getNumByPost("buzhangN", deptid,"韩国") ;
			pr.setSubTotal(pr.getSubTotal()+numY) ;
			pr.setSubTotal(pr.getSubTotal()+numN) ;
			pr.setCeo(rs.getInt(3)+pr.getCeo()) ;
			pr.setFuZong(rs.getInt(4)+pr.getFuZong()) ;
			pr.setBuZhang(numY+pr.getBuZhang());
			pr.setBuzhang2(numN+pr.getBuzhang2());
			//pr.setZongJian(pr.getZongJian()+rs.getInt(6));
			pr.setChangWu(rs.getInt(7)+pr.getChangWu()) ;
			numY = this.getNumByPost("cizhang", deptid, "韩国");
			pr.setSubTotal(pr.getSubTotal()+numY) ;
			pr.setCiZhang(numY+pr.getCiZhang());
			pr.setXiZhang(rs.getInt(9)+pr.getXiZhang());
			pr.setWeiYuan(rs.getInt(10)+pr.getWeiYuan());
			pr.setDaiLi(rs.getInt(11)+pr.getDaiLi()) ;
			pr.setKeZhang(rs.getInt(12)+pr.getKeZhang()) ;
			pr.setJianDu(rs.getInt(13)+pr.getJianDu());
			pr.setManagerKR(rs.getInt(14)+pr.getManagerKR()) ;
			pr.setZhuanYuanKR(rs.getInt(15)+pr.getZhuanYuanKR()) ;
			pr.setZhuRenKR(rs.getInt(16)+pr.getZhuRenKR());
			for (int j = 3; j < 17; j++) {
				if(j ==5||j==8||j==6)
					continue ;
				pr.setSubTotal(rs.getInt(j)+pr.getSubTotal()) ;
			}
 		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return pr ;
	}
	
	private PostReport createPostReport(ResultSet rs,PostReport pr) {
		try {
			pr.setManager(rs.getInt(3)+pr.getManager()) ;
			pr.setZhuGuan(rs.getInt(4)+pr.getZhuGuan()) ;
			pr.setZhuanYuan(rs.getInt(5)+pr.getZhuanYuan()) ;
			pr.setZhuLi(rs.getInt(6)+pr.getZhuLi()) ;
			pr.setZhuRen(rs.getInt(7)+pr.getZhuRen()) ;
			pr.setMonitor(rs.getInt(8)+pr.getMonitor()) ;
			pr.setWorkMan(rs.getInt(9)+pr.getWorkMan()) ;
			pr.setEngineer(rs.getInt(10)+pr.getEngineer()) ;
			pr.setAssistantEngineer(rs.getInt(11)+pr.getAssistantEngineer()) ;
			pr.setHighEngineer(rs.getInt(12)+pr.getHighEngineer()) ;
			pr.setTeShuZhi(rs.getInt(13)+pr.getTeShuZhi());
			for (int j = 3; j < 14; j++) {
				pr.setSubTotal(rs.getInt(j)+pr.getSubTotal()) ;
			}
 		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return pr ;
	}
	
	private PostReport createPostReportKR(ResultSet rs,PostReport pr) {
		try {
			pr.setCeo(rs.getInt(3)+pr.getCeo()) ;
			pr.setFuZong(rs.getInt(4)+pr.getFuZong()) ;
			pr.setBuZhang(rs.getInt(5)+pr.getBuZhang());
			pr.setZongJian(pr.getZongJian()+rs.getInt(6));
			pr.setChangWu(rs.getInt(7)+pr.getChangWu()) ;
			pr.setCiZhang(rs.getInt(8)+pr.getCiZhang());
			pr.setXiZhang(rs.getInt(9)+pr.getXiZhang());
			pr.setWeiYuan(rs.getInt(10)+pr.getWeiYuan());
			pr.setDaiLi(rs.getInt(11)+pr.getDaiLi()) ;
			pr.setKeZhang(rs.getInt(12)+pr.getKeZhang()) ;
			pr.setJianDu(rs.getInt(13)+pr.getJianDu());
			pr.setManagerKR(rs.getInt(14)+pr.getManagerKR()) ;
			pr.setZhuanYuanKR(rs.getInt(15)+pr.getZhuanYuanKR()) ;
			pr.setZhuRenKR(rs.getInt(16)+pr.getZhuRenKR());
			for (int j = 3; j < 17; j++) {
				pr.setSubTotal(rs.getInt(j)+pr.getSubTotal()) ;
			}
 		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return pr ;
	}
	
	private void setSubTotal(List prList) {
		PostReport pr = new PostReport() ;
		for (int i = 0; i < prList.size(); i++) {
			PostReport prTemp = (PostReport)prList.get(i) ;
			pr.setCeo(prTemp.getCeo()+pr.getCeo()) ;
			pr.setFuZong(prTemp.getFuZong()+pr.getFuZong()) ;
			pr.setBuZhang(prTemp.getBuZhang()+pr.getBuZhang());
			pr.setBuzhang2(prTemp.getBuzhang2()+pr.getBuzhang2());
			pr.setCiZhang(prTemp.getCiZhang()+pr.getCiZhang());
			pr.setWeiYuan(prTemp.getWeiYuan()+pr.getWeiYuan());
			pr.setXiZhang(prTemp.getXiZhang()+pr.getXiZhang());
			pr.setZongJian(prTemp.getZongJian()+pr.getZongJian()) ;
			pr.setChangWu(prTemp.getChangWu()+pr.getChangWu()) ;
			pr.setZhuanWu(prTemp.getZhuanWu()+pr.getZhuanWu()) ;
			pr.setDaiLi(prTemp.getDaiLi()+pr.getDaiLi()) ;
			pr.setKeZhang(prTemp.getKeZhang()+pr.getKeZhang()) ;
			pr.setJianDu(prTemp.getJianDu()+pr.getJianDu());
			pr.setManagerKR(prTemp.getManagerKR()+pr.getManagerKR()) ;
			pr.setManager(prTemp.getManager()+pr.getManager()) ;
			pr.setManager2(prTemp.getManager2()+pr.getManager2()) ;
			pr.setZhuGuan(prTemp.getZhuGuan()+pr.getZhuGuan()) ;
			pr.setZhuanYuan(prTemp.getZhuanYuan()+pr.getZhuanYuan()) ;
			pr.setZhuanYuanKR(prTemp.getZhuanYuanKR()+pr.getZhuanYuanKR()) ;
			pr.setZhuLi(prTemp.getZhuLi()+pr.getZhuLi()) ;
			pr.setZhuRenKR(prTemp.getZhuRenKR()+pr.getZhuRenKR()) ;
			pr.setZhuRen(prTemp.getZhuRen()+pr.getZhuRen()) ;
			pr.setZhuren2(prTemp.getZhuren2()+pr.getZhuren2()) ;
			pr.setMonitor(prTemp.getMonitor()+pr.getMonitor()) ;
			pr.setWorkMan(prTemp.getWorkMan()+pr.getWorkMan()) ;
			pr.setEngineer(prTemp.getEngineer()+pr.getEngineer()) ;
			pr.setAssistantEngineer(prTemp.getAssistantEngineer()+pr.getAssistantEngineer()) ;
			pr.setHighEngineer(prTemp.getHighEngineer()+pr.getHighEngineer()) ;
			pr.setTeShuZhi(prTemp.getTeShuZhi()+pr.getTeShuZhi());
			pr.setSubTotal(prTemp.getSubTotal()+pr.getSubTotal()) ;
		}
		prList.add(pr) ;
	}
	
	public PostReport getPostInfo(String deptid){
		List postList = new ArrayList();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		PostReport pr = new PostReport();
		String sql ="select sum(CEO) as CEO,sum(副总) as 副总,sum(部长) as 部长,sum(经理) as 经理,sum(主管) as 主管,sum(专员) as 专员,sum(文员) as 文员,sum(高级工程师)"
					+" as 高级工程师, sum(工程师) as 工程师,sum(工程师助理) as 工程师助理,sum(主任) as 主任,sum(班长) as 班长,sum(技术工人) as 技术工人,sum(特殊职)"
					+" as 特殊职,sum(subTotal) as subTotals from (" 
					+"select deptid, sum(CEO) as CEO,sum(副总) as 副总,sum(部长) as 部长,sum(经理) as 经理,sum(主管) as 主管,sum(专员) as 专员,sum(文员) as 文员,"
					+"sum(高级工程师) as 高级工程师, sum(工程师) as 工程师,sum(工程师助理) as 工程师助理,sum(主任) as 主任,"
					+"sum(班长) as 班长,sum(技术工人) as 技术工人,sum(特殊职) as 特殊职,(CEO+副总+部长+经理 +主管+专员+文员+高级工程师+工程师+工程师助理+主任+班长+技术工人+特殊职)"
					+" as subTotal from hr_report_post_stat_v  where 1=1 ";
		if(!deptid.equals("")||deptid!=null){
			sql += "and deptid like '"+deptid+"%'";
		}
		sql +=" group by deptid,CEO,副总,部长,经理,主管,专员,文员,高级工程师,工程师,工程师助理,主任,班长,技术工人,特殊职)";
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try {
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
//				pr.setCeo(StringUtil.checkNull(rs.getString("CEO")));
//				pr.setFuzong(StringUtil.checkNull(rs.getString("副总")));
//				pr.setBuzhang(StringUtil.checkNull(rs.getString("部长")));
//				pr.setManager(StringUtil.checkNull(rs.getString("经理")));
//				pr.setZhuguan(StringUtil.checkNull(rs.getString("主管")));
//				pr.setZhuanyuan(StringUtil.checkNull(rs.getString("专员")));
//				pr.setWenyuan(StringUtil.checkNull(rs.getString("文员")));
//				pr.setGaogong(StringUtil.checkNull(rs.getString("高级工程师")));
//				pr.setEngineer(StringUtil.checkNull(rs.getString("工程师")));
//				pr.setZhugong(StringUtil.checkNull(rs.getString("工程师助理")));
//				pr.setZhuren(StringUtil.checkNull(rs.getString("主任")));
//				pr.setMonitor(StringUtil.checkNull(rs.getString("班长")));
//				pr.setWorkman(StringUtil.checkNull(rs.getString("技术工人")));
//				pr.setTeshuzhi(StringUtil.checkNull(rs.getString("特殊职")));
//				pr.setSubTotal(StringUtil.checkNull(rs.getString("subTotals")));
				
			}

		} catch (Exception ex) {
			
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	
		
		return pr;
	}
	public AgeReport getAgeInfo(String deptid){
		AgeReport ar = new AgeReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sum(A)\"0-20\",sum(B)\"21-25\",sum(C)\"26-30\",sum(D)\"31-35\",sum(E)\"36-40\",sum(F)\"41-\",sum(count)as count" 
					 +" from (select t.deptid, sum(A)as A,sum(B)AS B,sum(C)AS C,sum(D) AS D,sum(E) AS E,sum(F) AS F,(A+B+C+D+E+F)as count"
					 +" from hr_report_age_count_v t where 1=1"; 
		if(!deptid.equals("")||deptid!=null){
				sql += "and deptid like '"+deptid+"%'";
		}
		sql += "group by t.deptid,A,B,C,D,E,F)v";
		String sql1 = "select round((select  sum(age) as age from age_amount where deptid like'"+deptid+"%')/(select sum(amount) "
					  +" from age_count_v where deptid like '"+deptid+"%'),0) as age_ave from dual";
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		Logger.getLogger(getClass()).debug("AVG-SQL: "+sql1);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ar.setA(StringUtil.checkNull(rs.getString("0-20")));
				ar.setB(StringUtil.checkNull(rs.getString("21-25")));
				ar.setC(StringUtil.checkNull(rs.getString("26-30")));
				ar.setD(StringUtil.checkNull(rs.getString("31-35")));
				ar.setE(StringUtil.checkNull(rs.getString("36-40")));
				ar.setF(StringUtil.checkNull(rs.getString("41-")));
				ar.setCount(StringUtil.checkNull(rs.getString("count")));
			}
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ar.setAvg(StringUtil.checkNull(rs.getString("age_ave")));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		return ar;
	}
	public NationReport getNationInfo(String deptid){
		NationReport nr = new NationReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select a,b,c,count,round(a/count*100,2)as d,round(b/count*100,2)as e,round(c/count*100,2)as f from "
					+" (select sum(A)as a,sum(B) as b,sum(C)as c,sum(A+B+C) as count from (select * from hr_nation_report_table_v) s where 1=1";
					
		if(!deptid.equals("")||deptid!=null){
			sql += "and deptid like '"+deptid+"%'";
		}
		sql += ")";
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				nr.setA(StringUtil.checkNull(rs.getString("a")));
				nr.setB(StringUtil.checkNull(rs.getString("b")));
				nr.setC(StringUtil.checkNull(rs.getString("c")));
				nr.setCount(StringUtil.checkNull(rs.getString("count")));
				nr.setD(StringUtil.checkNull(rs.getString("d")));
				nr.setE(StringUtil.checkNull(rs.getString("e")));
				nr.setF(StringUtil.checkNull(rs.getString("f")));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return nr;
		
	}
	
	public EndContractReport getEndContractInfo(String deptid){
		EndContractReport ecr = new EndContractReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from hr_report_endcontract_count_v t where t.deptid like '%"+deptid+"%' order by t.deptid" ;
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ecr = this.createEndContractReport(rs, ecr);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		return ecr;
	}
	
	private EndContractReport createEndContractReport(ResultSet rs,EndContractReport ecr){
		try {
			ecr.setDeptId(rs.getString("deptid"));
			ecr.setDeptName(rs.getString("deptname"));
			ecr.setFst_season_now(rs.getInt("first_season_now")+ecr.getFst_season_now());
			ecr.setScd_season_now(rs.getInt("second_season_now")+ecr.getScd_season_now());
			ecr.setThd_season_now(rs.getInt("third_season_now")+ecr.getThd_season_now());
			ecr.setFth_season_now(rs.getInt("fourth_season_now")+ecr.getFth_season_now());
			ecr.setFst_season_next(rs.getInt("first_season_next")+ecr.getFst_season_next());
			ecr.setScd_season_next(rs.getInt("second_season_next")+ecr.getScd_season_next());
			ecr.setThd_season_next(rs.getInt("third_season_next")+ecr.getThd_season_next());
			ecr.setFth_season_next(rs.getInt("fourth_season_next")+ecr.getFth_season_next());
			ecr.setAfter_next_year(rs.getInt("afer_next_year")+ecr.getAfter_next_year());
			ecr.setTotal(ecr.getTotal()+rs.getInt("first_season_now")+rs.getInt("second_season_now")+rs.getInt("third_season_now")
					+rs.getInt("fourth_season_now")+rs.getInt("first_season_next")+rs.getInt("second_season_next")+rs.getInt("third_season_next")
					+rs.getInt("fourth_season_next")+rs.getInt("afer_next_year"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ecr ;
	}
	
	public List getPreviewUpgradeList(String post_grade,int temp){
		List list = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from hr_upgrade_reports t where t.post_grade = ? and t.upgradewordage > ? " ;
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		Logger.getLogger(getClass()).debug("post_grade: "+post_grade);
		Logger.getLogger(getClass()).debug("temp: "+temp);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, post_grade);
			pstmt.setInt(2, temp);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(this.createPreviewUpgradeReports(rs));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		return list;
	}
	
	private PreviewUpgradeReports createPreviewUpgradeReports(ResultSet rs){
		PreviewUpgradeReports pur = new PreviewUpgradeReports();
		try {
			pur.setEmpId(rs.getString("empid"));
			pur.setEmpName(rs.getString("chinesename"));
			pur.setDeptName(rs.getString("department"));
			pur.setPost_grade(rs.getString("post_grade"));
			pur.setJoinDate(rs.getString("joindate").substring(0,10));
			pur.setWorkAge(rs.getFloat("upgradewordage"));
			pur.setEvsGrade1(rs.getString("nowEvsGrade"));
			pur.setEvsGrade2(rs.getString("beforeEvsGrade"));
			pur.setEvsGrade3(rs.getString("beforeEvsGrade2"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
		}
		return pur ;
	}
	
	public SexReport getSexInfo(String deptid,String emptype){
		String view="hr_report_sex_count_v";
		if(emptype.equals("EmpDivision2"))
			view="hr_report_sex_count_v1";
		SexReport sr = new SexReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " ";
		if(deptid.contains("_")){  
		   sql=" select "
		    +" SUM(MAN)   MAN, "
		    +" SUM(WOMAN) WOMAN,"
		    +" SUM(AMOUNT) AMOUNT,"
		    +" ROUND(SUM(MAN)/ decode(SUM(AMOUNT),0,1,SUM(AMOUNT))*100,2) MANPERCENT, "
		    +" ROUND(SUM(WOMAN)/decode(SUM(AMOUNT),0,1,SUM(AMOUNT))*100,2) WOMANPERCENT "
		    +" from "+view+"  where deptid='Z000000' ";
		}else{
			
			sql= "select "  
		    +" SUM(MAN)   MAN, "
		    +" SUM(WOMAN) WOMAN,"
		    +" SUM(AMOUNT) AMOUNT,"
		    +" ROUND(SUM(MAN)/ decode(SUM(AMOUNT),0,1,SUM(AMOUNT))*100,2) MANPERCENT, "
		    +" ROUND(SUM(WOMAN)/decode(SUM(AMOUNT),0,1,SUM(AMOUNT))*100,2) WOMANPERCENT "
		    +" from "+view
		    +" START WITH DEPTID=? CONNECT BY PRIOR DEPTID=PARENT_DEPT_ID ";
		}
			
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);            
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			if(!deptid.contains("_"))
			{
			pstmt.setString(1,deptid);
			}
			rs = pstmt.executeQuery();    
			if(rs.next()){
				sr.setMan(StringUtil.checkNull(rs.getString("MAN")));
				sr.setWoman(StringUtil.checkNull(rs.getString("WOMAN")));
				sr.setCount(StringUtil.checkNull(rs.getString("AMOUNT")));
				sr.setA(StringUtil.checkNull(rs.getString("MANPERCENT")));
				sr.setB(StringUtil.checkNull(rs.getString("WOMANPERCENT")));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		return sr;
	}
	
	public EmpStatusReport getEmpStatusInfo(String deptid,String emptype){
		String view="HR_EMP_STATUS_COUNT_V";
		if(emptype.equals("EmpDivision2"))
			view="HR_EMP_STATUS_COUNT_V1";
		EmpStatusReport sr = new EmpStatusReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " ";
		if(deptid.contains("_")){  
		   sql= " select "+
		    " SUM(E1)    E1,  "+
		    " SUM(E2)    E2,  "+
            " SUM(E4)    E4,  "+
            " SUM(E5)    E5,  "+
            " SUM(E6)    E6,  "+
		    " SUM(AMOUNT) AMOUNT, "+
		    " ROUND(SUM(E1+E2+E6)/ decode(SUM(AMOUNT),0,1,SUM(AMOUNT))*100,2) ONPERCENT, "+ 
		   "  ROUND(SUM(E5+E4)/decode(SUM(AMOUNT),0,1,SUM(AMOUNT))*100,2) OFFPERCENT "+
		   "  from "+view+"  where deptid='Z000000' ";
		}else{
			           
			sql=" select "+
		    " SUM(E1)    E1,  "+
		    " SUM(E2)    E2,  "+
            " SUM(E4)    E4,  "+
            " SUM(E5)    E5,  "+
            " SUM(E6)    E6,  "+  
		    " SUM(AMOUNT) AMOUNT, "+
		    " ROUND(SUM(E1+E2+E6)/ decode(SUM(AMOUNT),0,1,SUM(AMOUNT))*100,2) ONPERCENT, "+ 
		   "  ROUND(SUM(E5+E4)/decode(SUM(AMOUNT),0,1,SUM(AMOUNT))*100,2) OFFPERCENT "+
		   "  from "+view+" START WITH DEPTID=? CONNECT BY PRIOR DEPTID=PARENT_DEPT_ID ";
		}
			                            
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);            
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			if(!deptid.contains("_"))
			{
			pstmt.setString(1,deptid);
			}
			rs = pstmt.executeQuery();    
			if(rs.next()){
				sr.setE1(StringUtil.checkNull(rs.getString("E1")));
				sr.setE2(StringUtil.checkNull(rs.getString("E2")));
				sr.setE4(StringUtil.checkNull(rs.getString("E4")));
				sr.setE5(StringUtil.checkNull(rs.getString("E5")));
				sr.setE6(StringUtil.checkNull(rs.getString("E6")));
				sr.setCount(StringUtil.checkNull(rs.getString("AMOUNT")));
				sr.setA(StringUtil.checkNull(rs.getString("ONPERCENT")));
				sr.setB(StringUtil.checkNull(rs.getString("OFFPERCENT")));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		return sr;
	}
	
	
	public Vector getEmpDistributionInfo(String deptid,String monthstr[]){
		Vector data=new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql[] = new String[monthstr.length];             
		if(deptid.contains("_")){
			for(int i=0;i<monthstr.length;i++)
		   sql[i]=" SELECT DECODE(SUM(M_NUM),NULL,0,SUM(M_NUM))M_NUM, "+
			 " DECODE(SUM(F_NUM),NULL,0,SUM(F_NUM))F_NUM, "+
				" DECODE(SUM(AMOUNT),NULL,0,SUM(AMOUNT)) AMOUNT FROM ("+
				" select DEPTID,PARENT_DEPT_ID,"+
				"       SUM(M_NUM) M_NUM,"+
				"       SUM(F_NUM) F_NUM,"+
				"       SUM(M_NUM+F_NUM) AMOUNT"+
				"  from hr_department_history WHERE MONTH_STR='"+monthstr[i]+"' "+
				"   GROUP BY DEPTID,PARENT_DEPT_ID)T "+
				"  where  T.DEPTID='Z000000' ";
		}else{
			for(int i=0;i<monthstr.length;i++)
			 sql[i]= " SELECT DECODE(SUM(M_NUM),NULL,0,SUM(M_NUM))M_NUM, "+
			 " DECODE(SUM(F_NUM),NULL,0,SUM(F_NUM))F_NUM, "+
			" DECODE(SUM(AMOUNT),NULL,0,SUM(AMOUNT)) AMOUNT FROM ("+
			" select DEPTID,PARENT_DEPT_ID,"+
			"       SUM(M_NUM) M_NUM,"+
			"       SUM(F_NUM) F_NUM,"+
			"       SUM(M_NUM+F_NUM) AMOUNT"+
			"  from hr_department_history WHERE MONTH_STR='"+monthstr[i]+"' "+
			"   GROUP BY DEPTID,PARENT_DEPT_ID)T "+
			"  START WITH T.DEPTID='"+deptid+"' CONNECT BY  PRIOR T.DEPTID=T.PARENT_DEPT_ID";
		}
		Logger.getLogger(getClass()).debug("SQL: "+sql);            
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			for(int j=0;j<sql.length;j++)
			{
			pstmt = con.prepareStatement(sql[j]);  
			rs = pstmt.executeQuery(); 
			EmpDistribution sr = new EmpDistribution();
			if(rs.next()){
				sr.setMalecount(StringUtil.checkNull(rs.getString("M_NUM")));
				sr.setFemalecount(StringUtil.checkNull(rs.getString("F_NUM")));  
				sr.setCount(StringUtil.checkNull(rs.getString("AMOUNT")));
			}
			data.add(sr);  
			rs.close();
			pstmt.close();         
		 }
		 
			EmpDistribution srfirst=(EmpDistribution)data.firstElement();
			EmpDistribution srlast=(EmpDistribution)data.lastElement();	
			int count= new Integer(srlast.getCount()).intValue()-new Integer(srfirst.getCount()).intValue();
			int mcount=new Integer(srlast.getMalecount()).intValue()-new Integer(srfirst.getMalecount()).intValue();
			int fcount=new Integer(srlast.getFemalecount()).intValue()-new Integer(srfirst.getFemalecount()).intValue();
			EmpDistribution periodchange=new EmpDistribution();
			periodchange.setCount(count+"");
			periodchange.setMalecount(mcount+"");
			periodchange.setFemalecount(fcount+"");
			data.add(periodchange);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		return data;
	}
	
	public Vector  getMonths(String beginmonth,String endmonth){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector vector=new Vector();
		
		String sql="select distinct to_char(to_date(iyear|| imonth, 'yyyymm'),'yyyymm') "+
                 " from ar_calender t where to_date(iyear  || imonth, 'yyyymm') "+
                      " between to_date(?, 'yyyymm') and to_date(?, 'yyyymm') "+
                        "  order by to_char(to_date(iyear   || imonth, 'yyyymm'),'yyyymm') ";
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,beginmonth);
			pstmt.setString(2,endmonth);
			rs=pstmt.executeQuery();
		   while(rs.next())
		   {
			  vector.add(rs.getString(1));
		   }
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return vector;
	}
	
	public PostGroupReport getEmpPostGroupInfo(String deptid,String emptype){
		String view ="HR_POST_GROUP_COUNT_V";
		if(emptype.equals("EmpDivision2"))
			view ="HR_POST_GROUP_COUNT_V1";
		PostGroupReport sr = new PostGroupReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " ";             
		if(deptid.contains("_")){  
		   sql=  "select "+
	       "  SUM(PG1)    PG1,   "+
	       "  SUM(PG2)    PG2,   "+
           "  SUM(PG4)    PG3,  "+
           "  SUM(PG4)    PG4,  "+
           "  SUM(PG5)    PG5,   "+
	       "  SUM(AMOUNT) AMOUNT "+
	   " from "+view+"  where deptid='Z000000' ";
		}else{
			
			 sql=  "select "+
		       "  SUM(PG1)    PG1,   "+
		       "  SUM(PG2)    PG2,   "+
	           "  SUM(PG4)    PG3,  "+
	           "  SUM(PG4)    PG4,  "+
	           "  SUM(PG5)    PG5,   "+
		       "  SUM(AMOUNT) AMOUNT "+  
		   " from  "+view+"   START WITH DEPTID=? CONNECT BY PRIOR DEPTID=PARENT_DEPT_ID ";
		}
			
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);            
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			if(!deptid.contains("_"))
			{
			pstmt.setString(1,deptid);
			}
			rs = pstmt.executeQuery();    
			if(rs.next()){
				sr.setPG1(StringUtil.checkNull(rs.getString("PG1")));
				sr.setPG2(StringUtil.checkNull(rs.getString("PG2")));
				sr.setPG3(StringUtil.checkNull(rs.getString("PG3")));
				sr.setPG4(StringUtil.checkNull(rs.getString("PG4")));
				sr.setPG5(StringUtil.checkNull(rs.getString("PG5")));  
				sr.setCount(StringUtil.checkNull(rs.getString("AMOUNT")));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		return sr;
	}
	
	
	public EmpContractReport getEmpContract(String deptid,String emptype){
		String view="HR_CONTRACT_STAT_VIEW";
		if(emptype.equals("EmpDivision2"))  
			view="HR_CONTRACT_STAT_VIEW1";
		EmpContractReport sr = new EmpContractReport();
		Connection con = null;
		PreparedStatement pstmt = null;     
		ResultSet rs = null;
		String sql = " ";             
		if(deptid.contains("_")){  
		   sql=  " select SUM(M_NUM)M_NUM,SUM(F_NUM)F_NUM,SUM(AMOUNTALL)AMOUNTALL , "+
               " SUM(MCOUNT)MCOUNT,SUM(FCOUNT)FCOUNT ,SUM(MOUNT)MOUNT,SUM(DIFF) DIFF,  "+
               " SUM(ONEYEAR)ONEYEAR,SUM(TWOYEARS)TWOYEARS,SUM(THREEYEARS)THREEYEARS, "+
               "  SUM(FOURYEARS)FOURYEARS,SUM(FIVEYEARS)FIVEYEARS,SUM(OTHER)OTHER "+
               " from  "+view+" WHERE DEPTID='Z000000' ";
		}else{                
			
			 sql= " select SUM(M_NUM)M_NUM,SUM(F_NUM)F_NUM,SUM(AMOUNTALL)AMOUNTALL , "+
             " SUM(MCOUNT)MCOUNT,SUM(FCOUNT)FCOUNT ,SUM(MOUNT)MOUNT,SUM(DIFF) DIFF,  "+
             " SUM(ONEYEAR)ONEYEAR,SUM(TWOYEARS)TWOYEARS,SUM(THREEYEARS)THREEYEARS, "+
             "  SUM(FOURYEARS)FOURYEARS,SUM(FIVEYEARS)FIVEYEARS,SUM(OTHER)OTHER "+
             " from "+view+" START WITH DEPTID='"+deptid+"' CONNECT BY PRIOR DEPTID=PARENT_DEPT_ID";
		}
		Logger.getLogger(getClass()).debug("SQL: "+sql);            
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();    
			if(rs.next()){
				sr.setM_num(StringUtil.checkNull(rs.getString("M_NUM")));
				sr.setF_num(StringUtil.checkNull(rs.getString("F_NUM")));
				sr.setAllempmount(StringUtil.checkNull(rs.getString("AMOUNTALL")));
				sr.setHavecontractmale(StringUtil.checkNull(rs.getString("MCOUNT")));
				sr.setHavecontractfemale(StringUtil.checkNull(rs.getString("FCOUNT")));  
				sr.setHavecontractmount(StringUtil.checkNull(rs.getString("MOUNT")));
				sr.setDiff(StringUtil.checkNull(rs.getString("DIFF")));
				sr.setOneyear(StringUtil.checkNull(rs.getString("ONEYEAR")));
				sr.setTwoyears(StringUtil.checkNull(rs.getString("TWOYEARS")));
				sr.setThreeyears(StringUtil.checkNull(rs.getString("THREEYEARS")));
				sr.setFouryears(StringUtil.checkNull(rs.getString("FOURYEARS")));
				sr.setFiveyears(StringUtil.checkNull(rs.getString("FIVEYEARS")));
				sr.setOther(StringUtil.checkNull(rs.getString("OTHER")));
			
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{          
			SqlUtil.close(rs, pstmt, con);
		}           
		return sr;
	}
	
	public MarriageReport getMarriageInfo(String deptid){
		MarriageReport mr = new MarriageReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select v.Y,v.W,v.count,round(Y/count*100,2) as a,round(W/count*100,2)as b from(select sum(Y)as Y,sum(W) as W,sum(Y+W) as count "
					+" from hr_report_marriage_count_v t where 1=1 ";
		
		if(!deptid.equals("")||deptid!=null){
			sql += "and deptid like '"+deptid+"%'";
		}
		sql += ")v";
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();  
			if(rs.next()){
				mr.setY(StringUtil.checkNull(rs.getString("Y")));
				mr.setW(StringUtil.checkNull(rs.getString("W")));
				mr.setCount(StringUtil.checkNull(rs.getString("count")));
				mr.setA(StringUtil.checkNull(rs.getString("A")));
				mr.setB(StringUtil.checkNull(rs.getString("B")));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		return mr;
		
	}
	public JoinTypeReport getJoinTypeInfo(String deptid){
		JoinTypeReport jtr = new JoinTypeReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select v.经历,v.经验员工 ,v.实习,v.新员工,v.无经验员工,v.合计,round((v.经验员工/v.合计*100),2) as 经验比例,"
					+"round((v.无经验员工/v.合计*100),2) as 无经验比例 from (select sum(t.经历) 经历,sum(t.经历) "
					+"as 经验员工,sum(t.实习) 实习,sum(t.新员工) 新员工,sum(t.实习+t.新员工) as 无经验员工,(sum(t.经历)+sum(t.实习+t.新员工)) as 合计"
					+" from hr_report_jiontype_count_v t where 1 = 1 ";
		
		if(!deptid.equals("")||deptid!=null){
			sql += "and deptid like '"+deptid+"%'";
		}
		sql += ")v";
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				//jtr.setDispatch(StringUtil.checkNull(rs.getString("派遣")));
				jtr.setExperience(StringUtil.checkNull(rs.getString("经历")));
				//jtr.setChuCha(StringUtil.checkNull(rs.getString("出差")));
				jtr.setCount_A(StringUtil.checkNull(rs.getString("经验员工")));
				jtr.setPraxis(StringUtil.checkNull(rs.getString("实习")));
				jtr.setNewEmployee(StringUtil.checkNull(rs.getString("新员工")));
				jtr.setCount_B(StringUtil.checkNull(rs.getString("无经验员工")));
				jtr.setCount(StringUtil.checkNull(rs.getString("合计")));
				jtr.setA(StringUtil.checkNull(rs.getString("经验比例")));
				jtr.setB(StringUtil.checkNull(rs.getString("无经验比例")));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		return jtr;
	}
	public EmpContrastReport getEmpContrastA(String deptid,String date){
		EmpContrastReport ecr = new EmpContrastReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select  " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and ((hre.date_started < to_date('"+date+"','yyyy-mm') and hre.status_code <> 'Status03') or (hre.date_left >= to_date('"+date+"','yyyy-mm') "
		+"and hre.date_started < to_date('"+date+"','yyyy-mm')))  " + 
		") s " + 
		"where s.join_type_code = 'JoinType02' or s.join_type_code = 'JoinType03' " + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"'),0) as 上月末人数, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and (hre.date_started >= to_date('"+date+"','yyyy-mm') and hre.date_started < add_months(to_date('"+date+"','yyyy-mm'),1)) " + 
		") s " + 
		"where s.join_type_code = 'JoinType02' or s.join_type_code = 'JoinType03' " + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"' " + 
		"),0)as 本月录用, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and (hre.date_left >= to_date('"+date+"','yyyy-mm') and hre.date_left < add_months(to_date('"+date+"','yyyy-mm'),1)) " + 
		") s " + 
		"where s.join_type_code = 'JoinType02' or s.join_type_code = 'JoinType03' " + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"'),0) as 本月离职, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and  ((hre.date_started < add_months(to_date('"+date+"','yyyy-mm'),1) and hre.status_code <> 'Status03') or ( hre.date_left >=  " + 
		" " + 
		"add_months(to_date('"+date+"','yyyy-mm'),1) and hre.date_started < add_months(to_date('"+date+"','yyyy-mm'),1) )) " + 
		") s " + 
		"where s.join_type_code = 'JoinType02' or s.join_type_code = 'JoinType03' " + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"'),0) as 本月末人数 " + 
		"from dual";
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ecr.setPrevMonthCount(StringUtil.checkNull(rs.getString("上月末人数")));
				ecr.setThisMonthIn(StringUtil.checkNull(rs.getString("本月录用")));
				ecr.setThisMonthOut(StringUtil.checkNull(rs.getString("本月离职")));
				ecr.setThisMonthCount(StringUtil.checkNull(rs.getString("本月末人数")));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return ecr;
	}
	
	public int getDeptRowspan(String deptid)
	{
		int rowspan = 0 ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select ( "+
					"	 select count(t.deptid) from hr_department t where t.dept_level < 5 "+
					"	 and t.parent_dept_id in "+
					"	  (select t.deptid  from hr_department t where t.parent_dept_id = ?) "+
					"	    and (CREATE_DATE IS NULL OR CREATE_DATE<=SYSDATE) "+
					"	    and(DATE_ENDED IS NULL OR DATE_ENDED>=SYSDATE)) + "+
					"     (select count(t.deptid) from hr_department t where t.parent_dept_id = ? "+
					"       and (CREATE_DATE IS NULL OR CREATE_DATE<=SYSDATE) "+
					"       and(DATE_ENDED IS NULL OR DATE_ENDED>=SYSDATE) "+
					"	      ) + 1 from dual ";
		
		
		               
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,deptid);
			pstmt.setString(2,deptid);              
			rs = pstmt.executeQuery();
			if(rs.next()){
				rowspan = rs.getInt(1) ;
			}  
			
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return rowspan ;
	}
	
	
	public List getDeptIdListByParent_dept_id(String deptid)
	{
		List list = new ArrayList() ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select hd.deptid,hd.deptname,hd.dept_en_name,hd.dept_kor_name,hd.dept_level "+
		  " from hr_department hd "+
         " where DEPTID =hd.deptid and parent_dept_id = ? "+
         " AND (hd.CREATE_DATE IS NULL OR hd.CREATE_DATE<=SYSDATE) "+
         " AND(hd.DATE_ENDED IS NULL OR hd.DATE_ENDED>=SYSDATE)  order by DEPTID ";
		
		
		Department dept = null ;
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,deptid);      
			rs = pstmt.executeQuery();
			while(rs.next()){
				dept = new Department() ; 
				dept.setDeptID(rs.getString("deptid")) ;
				dept.setDeptName(rs.getString("deptname")) ;
				dept.setDeptEnName(rs.getString("dept_en_name")) ;
				dept.setKorDept(rs.getString("dept_kor_name")) ;
				list.add(dept) ;
			}  
			
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return list ;
	}
	
	public int getDeptRowspan_peopleware(String date,String deptid,int dept_level)
	{
		int rowspan = 0 ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "" ;
		
		if (dept_level == 3)
		{
			sql = " SELECT COUNT(HD.DEPTID) + 1 FROM HR_DEPARTMENT HD "
				+ " WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= ADD_MONTHS(TO_DATE(?,'YYYYMM'),1) -1 " 
                + " AND TO_DATE(?,'YYYYMM') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) "
                + " AND HD.DEPT_LEVEL < 5 "
                + " START WITH DEPTID = ? CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID " ;
		}
		else
		{
			sql = " SELECT " 
				+ " (SELECT COUNT(HD.DEPTID) FROM HR_DEPARTMENT HD "
				+ " WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= ADD_MONTHS(TO_DATE(?,'YYYYMM'),1) -1 " 
                + " AND TO_DATE(?,'YYYYMM') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) "
                + " AND HD.DEPT_LEVEL < 5 "
                + " START WITH DEPTID = ? CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID) " 
                + " + "
                + " (SELECT COUNT(HD.DEPTID) FROM HR_DEPARTMENT HD "
                + " WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= ADD_MONTHS(TO_DATE(?,'YYYYMM'),1) -1 " 
                + " AND TO_DATE(?,'YYYYMM') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) "
                + " AND HD.DEPT_LEVEL < 5 AND HD.PARENT_DEPT_ID = ?) " 
                + " FROM DUAL  ";
		}
		
		
		               
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,date) ;
			pstmt.setString(2,date) ;
			pstmt.setString(3,deptid) ;
			
			if(dept_level != 3)
			{
				pstmt.setString(4,date) ;
				pstmt.setString(5,date) ;
				pstmt.setString(6,deptid) ;
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				rowspan = rs.getInt(1) ;
			}  
			
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return rowspan ;
	}
	
	public EmpContrastBean getEmpContrast(String deptid){
		EmpContrastBean ecr = new EmpContrastBean();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		if(deptid.contains("_"))
		 sql = "SELECT SUM(LASTMONTH) LASTMONTH,SUM(INNUM) INNUM,SUM(OUTNUM) OUTNUM , "+
       " SUM(THSIMONTH) THSIMONTH ,SUM(DIFF) DIFF  "+
       " FROM RE_NOWCONTRAST_COUNT_VIEW WHERE DEPTID='Z000000'";
		else
			 sql = "SELECT SUM(LASTMONTH) LASTMONTH,SUM(INNUM) INNUM,SUM(OUTNUM) OUTNUM , "+
		       " SUM(THSIMONTH) THSIMONTH ,SUM(DIFF) DIFF  "+
		       " FROM RE_NOWCONTRAST_COUNT_VIEW start with DEPTID=? connect by  prior deptid=parent_dept_id ";
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			if(!deptid.contains("_"))
			{  
			pstmt.setString(1,deptid);
			}
			rs = pstmt.executeQuery();
			if(rs.next()){
				ecr.setPrevMonthCount(StringUtil.checkNull(rs.getString("LASTMONTH")));
				ecr.setThisMonthIn(StringUtil.checkNull(rs.getString("INNUM")));
				ecr.setThisMonthOut(StringUtil.checkNull(rs.getString("OUTNUM")));
				ecr.setThisMonthCount(StringUtil.checkNull(rs.getString("THSIMONTH")));
				ecr.setDiff(StringUtil.checkNull(rs.getString("DIFF")));  
			}  
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return ecr;
	}
	
	public EmpContrastReport getEmpContrastB(String deptid,String date){
		EmpContrastReport ecr = new EmpContrastReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select  " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.accreditation_date,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and ((hre.accreditation_date < to_date('"+date+"','yyyy-mm') and hre.status_code <> 'Status03') or (hre.date_left >= to_date('"+date+"','yyyy-mm') "
		+"and hre.accreditation_date < to_date('"+date+"','yyyy-mm')))  " + 
		") s " + 
		"where s.join_type_code = 'JoinType02' or s.join_type_code = 'JoinType03' or s.join_type_code = 'JoinType06'" + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"%'),0) as 上月末人数, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.accreditation_date,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and (hre.accreditation_date >= to_date('"+date+"','yyyy-mm') and hre.accreditation_date < add_months(to_date('"+date+"','yyyy-mm'),1)) " + 
		") s " + 
		"where s.join_type_code = 'JoinType02' or s.join_type_code = 'JoinType03' or s.join_type_code = 'JoinType06'" + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"%' " + 
		"),0)as 本月录用, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.accreditation_date,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and (hre.date_left >= to_date('"+date+"','yyyy-mm') and hre.date_left < add_months(to_date('"+date+"','yyyy-mm'),1)) " + 
		") s " + 
		"where s.join_type_code = 'JoinType02' or s.join_type_code = 'JoinType03' or s.join_type_code = 'JoinType06'" + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"%'),0) as 本月离职, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.accreditation_date,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and  ((hre.accreditation_date < add_months(to_date('"+date+"','yyyy-mm'),1) and hre.status_code <> 'Status03') or ( hre.date_left >=  " + 
		" " + 
		"add_months(to_date('"+date+"','yyyy-mm'),1) and hre.accreditation_date < add_months(to_date('"+date+"','yyyy-mm'),1) )) " + 
		") s " + 
		"where s.join_type_code = 'JoinType02' or s.join_type_code = 'JoinType03' or s.join_type_code = 'JoinType06'" + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"%'),0) as 本月末人数 " + 
		"from dual";
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ecr.setPrevMonthCount(StringUtil.checkNull(rs.getString("上月末人数")));
				ecr.setThisMonthIn(StringUtil.checkNull(rs.getString("本月录用")));
				ecr.setThisMonthOut(StringUtil.checkNull(rs.getString("本月离职")));
				ecr.setThisMonthCount(StringUtil.checkNull(rs.getString("本月末人数")));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return ecr;
	}
	public EmpContrastReport getEmpContrastC(String deptid,String date){
		EmpContrastReport ecr = new EmpContrastReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select  " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and ((hre.date_started < to_date('"+date+"','yyyy-mm') and hre.status_code <> 'Status03') or (hre.date_left >= to_date('"+date+"','yyyy-mm') "
		+"and hre.date_started < to_date('"+date+"','yyyy-mm')))  " + 
		") s " + 
		"where s.join_type_code <> 'JoinType02' and s.join_type_code <> 'JoinType03' " + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"%'),0) as 上月末人数, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and (hre.date_started >= to_date('"+date+"','yyyy-mm') and hre.date_started < add_months(to_date('"+date+"','yyyy-mm'),1)) " + 
		") s " + 
		"where s.join_type_code <> 'JoinType02' and s.join_type_code <> 'JoinType03' " + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"%' " + 
		"),0)as 本月录用, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and (hre.date_left >= to_date('"+date+"','yyyy-mm') and hre.date_left < add_months(to_date('"+date+"','yyyy-mm'),1)) " + 
		") s " + 
		"where s.join_type_code <> 'JoinType02' and s.join_type_code <> 'JoinType03' " + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"%'),0) as 本月离职, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,s.join_type_code,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and  ((hre.date_started < add_months(to_date('"+date+"','yyyy-mm'),1) and hre.status_code <> 'Status03') or ( hre.date_left >=  " + 
		" " + 
		"add_months(to_date('"+date+"','yyyy-mm'),1) and hre.date_started < add_months(to_date('"+date+"','yyyy-mm'),1) )) " + 
		") s " + 
		"where s.join_type_code <> 'JoinType02' and s.join_type_code <> 'JoinType03' " + 
		"group by s.deptid,s.deptname,s.join_type_code " + 
		") m " + 
		"where deptid like '"+deptid+"%'),0) as 本月末人数 " + 
		"from dual";
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ecr.setPrevMonthCount(StringUtil.checkNull(rs.getString("上月末人数")));
				ecr.setThisMonthIn(StringUtil.checkNull(rs.getString("本月录用")));
				ecr.setThisMonthOut(StringUtil.checkNull(rs.getString("本月离职")));
				ecr.setThisMonthCount(
						(NumberUtil.parseInt(ecr.getPrevMonthCount())+
								NumberUtil.parseInt(ecr.getThisMonthIn())-
								NumberUtil.parseInt(ecr.getThisMonthOut()))+"");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return ecr;
	}
	public EmpContrastReport getEmpContrastD(String deptid,String date){
		EmpContrastReport ecr = new EmpContrastReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select  " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and (((hre.date_started < to_date('"+date+"','yyyy-mm') or hre.accreditation_date < to_date('"+date+"','yyyy-mm')) and hre.status_code <> 'Status03') or (hre.date_left >= to_date('"+date+"','yyyy-mm') "
		+"and (hre.date_started < to_date('"+date+"','yyyy-mm') or hre.accreditation_date < to_date('"+date+"','yyyy-mm'))))  " + 
		") s " + 
		"group by s.deptid,s.deptname " + 
		") m " + 
		"where deptid like '"+deptid+"%'),0) as 上月末人数, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and ((hre.date_started >= to_date('"+date+"','yyyy-mm') or hre.accreditation_date >= to_date('"+date+"','yyyy-mm')) and (hre.date_started < add_months(to_date('"+date+"','yyyy-mm'),1)or hre.accreditation_date < add_months(to_date('"+date+"','yyyy-mm'),1))) " + 
		") s " + 
		"group by s.deptid,s.deptname " + 
		") m " + 
		"where deptid like '"+deptid+"%' " + 
		"),0)as 本月录用, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and ((hre.date_left >= to_date('"+date+"','yyyy-mm') ) and hre.date_left < add_months(to_date('"+date+"','yyyy-mm'),1)) " + 
		") s " + 
		"group by s.deptid,s.deptname " + 
		") m " + 
		"where deptid like '"+deptid+"%'),0) as 本月离职, " + 
		"nvl((select sum(count)as count " + 
		"from " + 
		"( " + 
		"select s.deptid,s.deptname,count(empid) as count " + 
		"from  " + 
		"( " + 
		"select v.deptid,v.deptname,hre.empid,hre.chinesename,hre.join_type_code,to_char(hre.date_started,'yyyymm'),to_char(hre.date_left,'yyyymm') " + 
		"from hr_employee hre, " + 
		"( " + 
		"select hrd.deptid,hrd.deptname,hrd.parent_dept_id " + 
		"from hr_department hrd start with hrd.deptid = '0001.01' connect by prior hrd.deptid = hrd.parent_dept_id  " + 
		") v " + 
		"where v.deptid = hre.deptid and  ((hre.date_started < add_months(to_date('"+date+"','yyyy-mm'),1) or hre.accreditation_date < add_months(to_date('"+date+"','yyyy-mm'),1))  and hre.status_code <> 'Status03') or ( hre.date_left >=  " + 
		" " + 
		"add_months(to_date('"+date+"','yyyy-mm'),1) and hre.date_started < add_months(to_date('"+date+"','yyyy-mm'),1) ) " + 
		") s " + 
		"group by s.deptid,s.deptname " + 
		") m " + 
		"where deptid like '"+deptid+"%'),0) as 本月末人数 " + 
		"from dual";
		
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ecr.setPrevMonthCount(StringUtil.checkNull(rs.getString("上月末人数")));
				ecr.setThisMonthIn(StringUtil.checkNull(rs.getString("本月录用")));
				ecr.setThisMonthOut(StringUtil.checkNull(rs.getString("本月离职")));
				ecr.setThisMonthCount(StringUtil.checkNull(rs.getString("本月末人数")));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return ecr;
	}
	public DimissionReport getDimissionInfo(String deptid,String dateBegin,String dateEnd){
		DimissionReport dr = new DimissionReport();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		if(deptid.contains("_"))
			 sql= " SELECT SUM(BEGINMONTH) BEGINMONTH,SUM(INAMOUNT)INAMOUNT,SUM(LEFTAMOUNT) LEFTAMOUNT,SUM(ENDMONTH) ENDMONTH FROM(  "+     
				  "      SELECT Q.DEPTID,Q.PARENT_DEPT_ID,BEGINMONTH,INAMOUNT,LEFTAMOUNT,ENDMONTH FROM    "+   
				  "        (SELECT A.DEPTID,  A.PARENT_DEPT_ID, COUNT(EMPID) BEGINMONTH "+     
					"    	     FROM HR_DEPARTMENT A,( SELECT EMPID,DEPTID FROM HR_EMPLOYEE B1 "+   
					"    	 		 WHERE (B1.DATE_STARTED <= to_date('"+dateBegin+"','YYYY-MM-DD')  AND B1.STATUS_CODE<>'EmpStatus3' AND B1.DATE_LEFT IS NULL) OR  "+   
				     "         B1.DATE_LEFT  between to_date('"+dateBegin+"','YYYY-MM-DD') "+   
					 "   	      AND to_date('"+dateEnd+"','YYYY-MM-DD')) B "+   
					 "   	     WHERE (DATE_CREATED IS NULL OR DATE_CREATED <= SYSDATE)  "+   
					 "          AND (DATE_ENDED IS NULL OR DATE_ENDED >= SYSDATE)  "+   
					 "   	   	 AND A.DEPTID = B.DEPTID(+)  "+   
					 "   	   	 GROUP BY A.DEPTID,A.DEPTNAME, A.DEPT_LEVEL,A.PARENT_DEPT_ID)Q,  "+   
				     "     (SELECT A.DEPTID,  A.PARENT_DEPT_ID, COUNT(EMPID) INAMOUNT   "+   
					 "   	        FROM HR_DEPARTMENT A,( SELECT EMPID,DEPTID FROM HR_EMPLOYEE B1  "+   
					 "   	 		  WHERE DATE_STARTED between to_date('"+dateBegin+"','YYYY-MM-DD')   "+   
					 "            AND to_date('"+dateEnd+"','YYYY-MM-DD')) B  "+   
					  "  	     WHERE (DATE_CREATED IS NULL OR DATE_CREATED <= SYSDATE)  "+   
					  "         AND (DATE_ENDED IS NULL OR DATE_ENDED >= SYSDATE)  "+   
					 "   	   	   AND A.DEPTID = B.DEPTID(+)  "+   
					    	   "	 GROUP BY A.DEPTID,A.DEPTNAME, A.DEPT_LEVEL,A.PARENT_DEPT_ID )P, "+   
				     "  ( SELECT A.DEPTID,  A.PARENT_DEPT_ID, COUNT(EMPID) LEFTAMOUNT "+   
					  "  	         FROM HR_DEPARTMENT A,( SELECT EMPID,DEPTID FROM HR_EMPLOYEE B1  "+   
					    	"   		   WHERE DATE_LEFT between to_date('"+dateBegin+"','YYYY-MM-DD')  "+   
					 "   	          AND to_date('"+dateEnd+"','YYYY-MM-DD')) B  "+   
					 "   	        WHERE (DATE_CREATED IS NULL OR DATE_CREATED <= SYSDATE)  "+   
					 "   	        AND (DATE_ENDED IS NULL OR DATE_ENDED >= SYSDATE)  "+   
					"    	      	  AND A.DEPTID = B.DEPTID(+) "+   
					 "   	     	 GROUP BY A.DEPTID,A.DEPTNAME, A.DEPT_LEVEL,A.PARENT_DEPT_ID) S,     "+       
				   "   (SELECT A.DEPTID,  A.PARENT_DEPT_ID, COUNT(EMPID) ENDMONTH  "+   
					"    	     FROM HR_DEPARTMENT A,( SELECT EMPID,DEPTID FROM HR_EMPLOYEE B1 "+   
					"    	 		 WHERE B1.STATUS_CODE<>'EmpStatus3' AND B1.DATE_LEFT IS NULL) B "+   
					 "   	     WHERE (DATE_CREATED IS NULL OR DATE_CREATED <= SYSDATE) "+   
					 "          AND (DATE_ENDED IS NULL OR DATE_ENDED >= SYSDATE) "+   
					 "   	   	 AND A.DEPTID = B.DEPTID(+) "+   
					"    	   	 GROUP BY A.DEPTID,A.DEPTNAME, A.DEPT_LEVEL,A.PARENT_DEPT_ID)H "+   
				     "        WHERE Q.DEPTID=P.DEPTID AND P.DEPTID=S.DEPTID AND S.DEPTID=H.DEPTID)TT "+   
				     "      WHERE TT.DEPTID='Z000000'    ";
		else               
			sql= " SELECT SUM(BEGINMONTH) BEGINMONTH,SUM(INAMOUNT)INAMOUNT,SUM(LEFTAMOUNT) LEFTAMOUNT,SUM(ENDMONTH) ENDMONTH FROM(  "+     
			  "      SELECT Q.DEPTID,Q.PARENT_DEPT_ID,BEGINMONTH,INAMOUNT,LEFTAMOUNT,ENDMONTH FROM    "+   
			  "        (SELECT A.DEPTID,  A.PARENT_DEPT_ID, COUNT(EMPID) BEGINMONTH "+   
				"    	     FROM HR_DEPARTMENT A,( SELECT EMPID,DEPTID FROM HR_EMPLOYEE B1 "+   
				"    	 		 WHERE (B1.DATE_STARTED <= to_date('"+dateBegin+"','YYYY-MM-DD')  AND B1.STATUS_CODE<>'EmpStatus3' AND B1.DATE_LEFT IS NULL) OR  "+   
			     "         B1.DATE_LEFT  between to_date('"+dateBegin+"','YYYY-MM-DD') "+   
				 "   	      AND to_date('"+dateEnd+"','YYYY-MM-DD')) B "+   
				 "   	     WHERE (DATE_CREATED IS NULL OR DATE_CREATED <= SYSDATE)  "+   
				 "          AND (DATE_ENDED IS NULL OR DATE_ENDED >= SYSDATE)  "+   
				 "   	   	 AND A.DEPTID = B.DEPTID(+)  "+   
				 "   	   	 GROUP BY A.DEPTID,A.DEPTNAME, A.DEPT_LEVEL,A.PARENT_DEPT_ID)Q,  "+   
			     "     (SELECT A.DEPTID,  A.PARENT_DEPT_ID, COUNT(EMPID) INAMOUNT   "+   
				 "   	        FROM HR_DEPARTMENT A,( SELECT EMPID,DEPTID FROM HR_EMPLOYEE B1  "+   
				 "   	 		  WHERE DATE_STARTED between to_date('"+dateBegin+"','YYYY-MM-DD')   "+   
				 "            AND to_date('"+dateEnd+"','YYYY-MM-DD')) B  "+   
				  "  	     WHERE (DATE_CREATED IS NULL OR DATE_CREATED <= SYSDATE)  "+   
				  "         AND (DATE_ENDED IS NULL OR DATE_ENDED >= SYSDATE)  "+   
				 "   	   	   AND A.DEPTID = B.DEPTID(+)  "+   
				    	   "	 GROUP BY A.DEPTID,A.DEPTNAME, A.DEPT_LEVEL,A.PARENT_DEPT_ID )P, "+   
			     "  ( SELECT A.DEPTID,  A.PARENT_DEPT_ID, COUNT(EMPID) LEFTAMOUNT "+   
				  "  	         FROM HR_DEPARTMENT A,( SELECT EMPID,DEPTID FROM HR_EMPLOYEE B1  "+   
				    	"   		   WHERE DATE_LEFT between to_date('"+dateBegin+"','YYYY-MM-DD')  "+   
				 "   	          AND to_date('"+dateEnd+"','YYYY-MM-DD')) B  "+   
				 "   	        WHERE (DATE_CREATED IS NULL OR DATE_CREATED <= SYSDATE)  "+   
				 "   	        AND (DATE_ENDED IS NULL OR DATE_ENDED >= SYSDATE)  "+   
				"    	      	  AND A.DEPTID = B.DEPTID(+) "+   
				 "   	     	 GROUP BY A.DEPTID,A.DEPTNAME, A.DEPT_LEVEL,A.PARENT_DEPT_ID) S,     "+       
			   "   (SELECT A.DEPTID,  A.PARENT_DEPT_ID, COUNT(EMPID) ENDMONTH  "+   
				"    	     FROM HR_DEPARTMENT A,( SELECT EMPID,DEPTID FROM HR_EMPLOYEE B1 "+   
				"    	 		 WHERE B1.STATUS_CODE<>'EmpStatus3' AND B1.DATE_LEFT IS NULL) B "+   
				 "   	     WHERE (DATE_CREATED IS NULL OR DATE_CREATED <= SYSDATE) "+   
				 "          AND (DATE_ENDED IS NULL OR DATE_ENDED >= SYSDATE) "+   
				 "   	   	 AND A.DEPTID = B.DEPTID(+) "+   
				"    	   	 GROUP BY A.DEPTID,A.DEPTNAME, A.DEPT_LEVEL,A.PARENT_DEPT_ID)H "+   
			     "        WHERE Q.DEPTID=P.DEPTID AND P.DEPTID=S.DEPTID AND S.DEPTID=H.DEPTID)TT "+   
			     "      START WITH TT.DEPTID='"+deptid+"' CONNECT BY PRIOR DEPTID=PARENT_DEPT_ID     ";
	                    
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);  
			rs = pstmt.executeQuery();                 
			if(rs.next()){
				dr.setA(StringUtil.checkNull(rs.getString("BEGINMONTH")));
				dr.setB(StringUtil.checkNull(rs.getString("INAMOUNT")));
				dr.setC(StringUtil.checkNull(rs.getString("LEFTAMOUNT")));
				dr.setD(""+(NumberUtil.parseInt(dr.getA())+NumberUtil.parseInt(dr.getB())-NumberUtil.parseInt(dr.getC())));
				if(NumberUtil.parseDouble(dr.getD())+NumberUtil.parseDouble(dr.getA())!=0)
					dr.setDimission(NumberUtil.parseInt(dr.getC())/
							((NumberUtil.parseDouble(dr.getD())+NumberUtil.parseDouble(dr.getA()))/2)*100+"");
				else
					dr.setDimission("0.00");
					  
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		return dr; 
	}
	
	/**
	 * retrieve ar detail Statistics sqlcontent
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public String getDetailStatisticsSql(Object parameterObject,String function) {

		String sql = "" ;
		try {
			List itemList = (List)parameterObject ;
			for (int i = 0 ; i < itemList.size() ; i ++ )
			{
				SimpleMap map = (SimpleMap)itemList.get(i) ;
				if (function.equals("sum"))
					sql += ",SUM(CASE AR_ITEM_NO WHEN " + map.getString("ITEM_NO") + " THEN QUANTITY ELSE 0 END) AS \"" + map.getString("ITEM_ID") + "\"";
				else if (function.equals("count"))
					sql += ",COUNT(DISTINCT CASE WHEN AR_ITEM_NO=" + map.getString("ITEM_NO") + " AND QUANTITY > 0 THEN AR.EMPID END) AS \"" + map.getString("ITEM_ID") + "\"";
			}
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace() ;
		}
		return sql;
	}
	

	public List getDeptByParent_dept_id(String parent_dept_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList() ;
	    String sql = "select deptid from hr_department START WITH deptid = '" + parent_dept_id + "' CONNECT BY PRIOR deptid = parent_dept_id" ;
		Logger.getLogger(getClass()).debug("SQL: "+sql);
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);  
			rs = pstmt.executeQuery();                 
			while(rs.next()){
				list.add(rs.getString("deptid")) ;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		return list ; 
	}
	
	//获得日考勤明细统计的中间
	public SimpleMap getData_arDailyDetailStatistics(String date,String deptid,int dept_level)
	{
		SimpleMap parameterObject = new SimpleMap();
		
		parameterObject.setString("dateStarted", date) ;
		parameterObject.setString("deptid", deptid) ;
		try {
			if (dept_level == 3)
			{
				parameterObject = (SimpleMap)arServices.retrieveArDailyDetailStatisticsData_head(parameterObject) ;
			}else
			{
				parameterObject = (SimpleMap)arServices.retrieveArDailyDetailStatisticsData(parameterObject) ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return parameterObject ;
	}
	
//	获得所有下属科室部门
	public List getDept_section_head(String date,String deptid) {
		List list = new ArrayList();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT HD.DEPTID, HD.DEPTNAME, HD.DEPT_EN_NAME, HD.DEPT_LEVEL,HD.DEPT_KOR_NAME FROM HR_DEPARTMENT HD "
				  +	" WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= TO_DATE(?,'YYYY-MM-DD') "
				  + "   AND TO_DATE(?,'YYYY-MM-DD') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) " 
				  +	"   AND HD.DEPT_LEVEL = 3 "
				  + "   START WITH DEPTID = ? CONNECT BY PRIOR DEPTID = HD.PARENT_DEPT_ID " ;
		
		Department dept = null ;
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,date);   
			pstmt.setString(2,date);   
			pstmt.setString(3,deptid);    
			rs = pstmt.executeQuery();
			while(rs.next()){
				dept = new Department() ; 
				dept.setDeptID(rs.getString("deptid")) ;
				dept.setDeptName(rs.getString("deptname")) ;
				dept.setDeptEnName(rs.getString("dept_en_name")) ;
				dept.setKorDept(rs.getString("dept_kor_name")) ;
				dept.setDeptLevel(rs.getInt("DEPT_LEVEL")) ;
				list.add(dept) ;
			}  
			
		} catch (Exception ex) {
			ex.printStackTrace() ;
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		
		return list;
	}
	
//	获得所有下属科室部门
	public List getDept_section(String date,String deptid,int dept_level) {
		List list = new ArrayList();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT HD.DEPTID, HD.DEPTNAME, HD.DEPT_EN_NAME, HD.DEPT_LEVEL,HD.DEPT_KOR_NAME FROM HR_DEPARTMENT HD "
			  +	" WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= TO_DATE(?,'YYYY-MM-DD')  AND "
			  + " TO_DATE(?,'YYYY-MM-DD') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) AND HD.PARENT_DEPT_ID = ? " ;
		
		if (dept_level == 3)
		{
			sql = "SELECT HD.DEPTID, HD.DEPTNAME, HD.DEPT_EN_NAME, HD.DEPT_LEVEL,HD.DEPT_KOR_NAME FROM HR_DEPARTMENT HD WHERE HD.DEPTID = ? " ;
		}
		
		if (dept_level == 4)
		{
			sql = "SELECT HD.DEPTID, HD.DEPTNAME, HD.DEPT_EN_NAME, HD.DEPT_LEVEL,HD.DEPT_KOR_NAME FROM HR_DEPARTMENT HD WHERE HD.DEPTID = ? UNION ALL " 
				  + sql ;
		}
		
		Department dept = null ;
		try{
			int i = 1 ;
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			if (dept_level == 3)
			{
				pstmt.setString(i++,deptid); 
			}
			
			if (dept_level == 4)
			{
				pstmt.setString(i++,deptid); 
				pstmt.setString(i++,date);   
				pstmt.setString(i++,date);   
				pstmt.setString(i++,deptid);
			}
			    
			rs = pstmt.executeQuery();
			while(rs.next()){
				dept = new Department() ; 
				dept.setDeptID(rs.getString("deptid")) ;
				dept.setDeptName(rs.getString("deptname")) ;
				dept.setDeptEnName(rs.getString("dept_en_name")) ;
				dept.setKorDept(rs.getString("dept_kor_name")) ;
				dept.setDeptLevel(rs.getInt("DEPT_LEVEL")) ;
				list.add(dept) ;
			}  
			
		} catch (Exception ex) {
			ex.printStackTrace() ;
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		
		return list;
	}
	
	public int getDeptRowspan_paDailyStatisticsSection(String date,String deptid,int dept_level, int grade)
	{
		int rowspan = 0 ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "" ;
		
		if(grade == 1)
		{
			sql = "SELECT (SELECT COUNT(HD.DEPTID) FROM HR_DEPARTMENT HD "
				+ " WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= TO_DATE(?,'YYYY-MM-DD') " 
                + " AND TO_DATE(?,'YYYY-MM-DD') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) "
                + " START WITH DEPTID = ? CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID) " 
                + " + " 
			    + " (SELECT COUNT(HD.DEPTID) FROM HR_DEPARTMENT HD "
				+ " WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= TO_DATE(?,'YYYY-MM-DD') " 
                + " AND TO_DATE(?,'YYYY-MM-DD') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) "
                + " AND PARENT_DEPT_ID = ? ) + 2 " 
                + " FROM DUAL" ;
		}
		else if ((dept_level == 3 && grade != 2) || (dept_level != 3 && grade == 2))
		{
			sql = " SELECT COUNT(HD.DEPTID) + 1 FROM HR_DEPARTMENT HD "
				+ " WHERE NVL(HD.DATE_CREATED,TO_DATE('2007-01-01','YYYY-MM-DD')) <= TO_DATE(?,'YYYY-MM-DD') " 
                + " AND TO_DATE(?,'YYYY-MM-DD') <= NVL(HD.DATE_ENDED,TO_DATE('9999-01-01','YYYY-MM-DD')) "
                + " START WITH DEPTID = ? CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID " ; 
		}
		else
		{
			return 2 ;
		}
		
		
		               
		try{
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			if(grade == 1)
			{
				pstmt.setString(1,date) ;
				pstmt.setString(2,date) ;
				pstmt.setString(3,deptid) ;
				pstmt.setString(4,date) ;
				pstmt.setString(5,date) ;
				pstmt.setString(6,deptid) ;
			}
			
			if ((dept_level == 3 && grade != 2) || (dept_level != 3 && grade == 2))
			{
				pstmt.setString(1,date) ;
				pstmt.setString(2,date) ;
				pstmt.setString(3,deptid) ;
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				rowspan = rs.getInt(1) ;
			}  
			
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}finally{
			SqlUtil.close(rs, pstmt, con);
		}
		
		return rowspan ;
	}
	
//	获得日工资明细统计的中间
	public SimpleMap getData_paDailyDetailStatisticsSection(String date,String deptid,int dept_level,String type)
	{
		SimpleMap parameterObject = new SimpleMap();
		
		parameterObject.setString("date", date) ;
		parameterObject.setString("deptid", deptid) ;
		
		try {
			if (type.equals("single"))
			{
				parameterObject = (SimpleMap)paServices.retrievePaDaily_head(parameterObject) ;
			}
			else if(type.equals("subtotal"))
			{	
				if(dept_level == 3)
				{
					parameterObject = (SimpleMap)paServices.retrievePaDaily_head(parameterObject) ;
				}else
				{
					parameterObject = (SimpleMap)paServices.retrievePaDaily(parameterObject) ;
				}
			}
			else if(type.equals("total"))
			{
				parameterObject = (SimpleMap)paServices.retrievePaDaily(parameterObject) ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		
		return parameterObject ;
	}
	
//	获得日工资明细统计的中间
	public SimpleMap getData_arDailyDetailStatisticsSection(String date,String deptid,int dept_level,String type)
	{
		SimpleMap parameterObject = new SimpleMap();
		
		parameterObject.setString("dateStarted", date) ;
		parameterObject.setString("deptid", deptid) ;
		
		try {
			if (type.equals("single"))
			{
				parameterObject = (SimpleMap)arServices.retrieveArDailyDetailStatisticsData_head(parameterObject) ;
			}
			else if(type.equals("subtotal"))
			{	
				if(dept_level == 3)
				{
					parameterObject = (SimpleMap)arServices.retrieveArDailyDetailStatisticsData_head(parameterObject) ;
				}else
				{
					parameterObject = (SimpleMap)arServices.retrieveArDailyDetailStatisticsData(parameterObject) ;
				}
			}
			else if(type.equals("total"))
			{
				parameterObject = (SimpleMap)arServices.retrieveArDailyDetailStatisticsData(parameterObject) ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
		
		return parameterObject ;
	}
	
	// 计算部级保险汇总
	public List getData_insurnaceData_simple(String paMonth,String calType,String insType,List deptList)
	{
		List dataList = new ArrayList() ;
		SimpleMap deptMap = new SimpleMap();
		
		String sqlContent = "" ;
		String sql_per = " SUM(PER_RETIRE_INS) AS PER_RETIRE_INS, SUM(PER_MEDICARE_INS) AS PER_MEDICARE_INS, "
					   + " SUM(PER_UNEMPLOY_INS) AS PER_UNEMPLOY_INS, SUM(PER_INS_TOTAL) AS PER_INS_TOTAL  " 
					   + " FROM PA_HISTORY " ;
	    String sql_com = " SUM(CPY_RETIRE_INS) AS CPY_RETIRE_INS, SUM(CPY_MEDICARE_INS) AS CPY_MEDICARE_INS,"
	    		       + " SUM(CPY_UNEMPLOY_INS) AS CPY_UNEMPLOY_INS,SUM(CPY_WORK_INJURY_INS) AS CPY_WORK_INJURY_INS,"
	    		       + " SUM(CPY_CHILDBIRTH_INS) AS CPY_CHILDBIRTH_INS, SUM(CPY_INS_TOTAL) AS CPY_INS_TOTAL"
	    		       + " FROM PA_HISTORY " ;
	    String sql_sum = " SELECT SUM(PER_RETIRE_INS + CPY_RETIRE_INS) AS RETIRE_INS, SUM(PER_MEDICARE_INS + CPY_MEDICARE_INS) AS MEDICARE_INS, "
	    			   + " SUM(PER_UNEMPLOY_INS + CPY_UNEMPLOY_INS) AS UNEMPLOY_INS, SUM(CPY_WORK_INJURY_INS) AS WORK_INJURY_INS, "
	    			   + " SUM(CPY_CHILDBIRTH_INS) AS CHILDBIRTH_INS, SUM(PER_INS_TOTAL + CPY_INS_TOTAL) AS INS_TOTAL "
	    			   + " FROM PA_HISTORY WHERE PA_MONTH = '" + paMonth + "' AND WORK_AREA = '" + insType + "' "  ;
					
		if (calType != null && calType.equals("all"))
		{
			sqlContent = sql_sum ;
		}
		else
		{
			for (int i = 0 ; i < deptList.size() ; i ++)
			{
				deptMap = (SimpleMap)deptList.get(i) ;
				
				sqlContent = sqlContent + " SELECT '" + deptMap.getString("DEPTNAME") +  "' AS DEPTNAME, " ; 
				
				if(calType != null && calType.equals("per"))
				{
					sqlContent += sql_per ;
				}
				else if (calType != null && calType.equals("com"))
				{
					sqlContent += sql_com ;
				}
				
				sqlContent += " WHERE DEPTID IN ( SELECT DEPTID FROM HR_DEPARTMENT START WITH DEPTID = '" + deptMap.getString("DEPTID") + "' "
				   			+ " CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID) " 
				   			+ " AND WORK_AREA = '" + insType + "' "
				   			+ " AND PA_MONTH = '" + paMonth + "' " 
						    + " UNION ALL " ;
			}
			
			if(calType != null && calType.equals("per"))
			{
				sqlContent += "SELECT '小计' AS DEPTNAME, " + sql_per  + " WHERE PA_MONTH = '" + paMonth + "' AND WORK_AREA = '" + insType + "' " ;
			}
			else if (calType != null && calType.equals("com"))
			{
				sqlContent += "SELECT '小计' AS DEPTNAME, " + sql_com  + " WHERE PA_MONTH = '" + paMonth + "' AND WORK_AREA = '" + insType + "' " ;
			}
			
		}
		
		SimpleMap parameterObject = new SimpleMap() ;
		parameterObject.setString("sqlContent", sqlContent) ;
		
		if(calType != null && calType.equals("per"))
		{
			dataList = paServices.retrieveInsuranceData_simple_per(parameterObject) ;
		}
		else if (calType != null && calType.equals("com"))
		{
			dataList = paServices.retrieveInsuranceData_simple_com(parameterObject) ;
		}
		else if (calType != null && calType.equals("all"))
		{
			dataList = paServices.retrieveInsuranceData_simple(parameterObject) ;
		}
		
		
		
		
		return dataList ;
	}

}
