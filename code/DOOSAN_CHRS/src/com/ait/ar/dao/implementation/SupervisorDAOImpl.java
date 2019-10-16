package com.ait.ar.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ar.bean.Supervisor;
import com.ait.ar.dao.SupervisorDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.web.ApplicationContext;

public class SupervisorDAOImpl implements SupervisorDAO {
	private static ServiceLocator services;

	
	public SupervisorDAOImpl() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException ex) {
			Logger.getLogger(SupervisorDAOImpl.class).error(ex.toString());
		}
	}

	public List getAllSupervisor(String cpny_id) throws DataAccessException {
		List list = new ArrayList();
		String sql = "SELECT AR_SUPERVISOR.SUPERVISOR_ID, "
						 +"  HR_EMPLOYEE.EMPID, "
					     +"  HR_EMPLOYEE.CHINESENAME, "
					     +"  HR_EMPLOYEE.CHINESE_PINYIN, "
					     +"  HR_EMPLOYEE.DEPTNAME, "
					     +"  HR_EMPLOYEE.FOURTHDEPTNAME, "
					     +"  to_char(AR_SUPERVISOR.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE "
					 +" FROM AR_SUPERVISOR, HR_EMP_V HR_EMPLOYEE "
					+" WHERE AR_SUPERVISOR.SUPERVISOR_ID = HR_EMPLOYEE.PERSON_ID AND HR_EMPLOYEE.ACTIVITY = 1 and HR_EMPLOYEE.CPNY_ID='"+ cpny_id
				 +"' ORDER BY HR_EMPLOYEE.FOURTHDEPTID,HR_EMPLOYEE.DEPTID ";
		Logger.getLogger(getClass()).debug(sql);
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			con = services.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(this.createSupervisor1(rs));
			}
		} catch (ServiceLocatorException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return list;
	}
	
	public List getAllSupervisorByEmpId(String empId, String cpny_id) throws DataAccessException {
		List list = new ArrayList();
		String sql = "SELECT AR_SUPERVISOR.SUPERVISOR_ID, "
						 +"  HR_EMPLOYEE.EMPID, "
					     +"  HR_EMPLOYEE.CHINESENAME, "
					     +"  HR_EMPLOYEE.CHINESE_PINYIN, "
					     +"  HR_EMPLOYEE.DEPTNAME, "
					     +"  HR_EMPLOYEE.FOURTHDEPTNAME, "
					     +"  to_char(AR_SUPERVISOR.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE "
					 +" FROM AR_SUPERVISOR, HR_EMP_V HR_EMPLOYEE "
					+" WHERE AR_SUPERVISOR.SUPERVISOR_ID = HR_EMPLOYEE.PERSON_ID AND HR_EMPLOYEE.ACTIVITY = 1 "
					+" AND EXISTS (SELECT * FROM AR_SUPERVISOR_INFO AR WHERE AR.AR_SUPERVISOR_ID = '" + empId + "'" 
					+" AND AR.SUPERVISED_DEPTID = HR_EMPLOYEE.DEPTID  OR HR_EMPLOYEE.PERSON_ID = '"+ empId +"') AND HR_EMPLOYEE.Cpny_Id='"+ cpny_id
				 +"' ORDER BY HR_EMPLOYEE.FOURTHDEPTID,HR_EMPLOYEE.DEPTID ";
		Logger.getLogger(getClass()).debug(sql);
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			con = services.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(this.createSupervisor1(rs));
			}
		} catch (ServiceLocatorException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return list;
	}

	/**
	 * deleteSupervisor
	 * 
	 * @param supervisorID
	 *            String
	 */
	public void deleteSupervisor(String supervisorID)
			throws DataAccessException {
		String deleteSupervisor = "delete AR_SUPERVISOR where SUPERVISOR_ID = '"
				+ supervisorID + "'";
		String deleteSupervisorInfo = "delete AR_SUPERVISOR_INFO where AR_SUPERVISOR_ID='"
				+ supervisorID + "'";
		Logger.getLogger(getClass()).debug(deleteSupervisorInfo);
		Logger.getLogger(getClass()).debug(deleteSupervisor);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(deleteSupervisorInfo);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(deleteSupervisor);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}

	/**
	 * addSupervisor
	 * 
	 * @param supervisor
	 *            Supervisor
	 */
	public void addSupervisor(Supervisor supervisor) throws DataAccessException {
		CallableStatement cs = null;
		String sql = "{ call ar_add_supervisor(?,?,?) }";
		Logger.getLogger(getClass()).debug(sql);
		Logger.getLogger(getClass()).debug(
				"parameter1:" + supervisor.getSupervisorId());
		Logger.getLogger(getClass()).debug(
				"parameter2:" + supervisor.getCreatedBy());
		Logger.getLogger(getClass()).debug(
				"parameter3:" + supervisor.getItemName());
		Connection con = null;
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setString(1, supervisor.getSupervisorId());
			cs.setString(2, supervisor.getCreatedBy());
			cs.setString(3, supervisor.getItemName());
			cs.execute();
		} catch (ServiceLocatorException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}
	}

	public Supervisor getSupervisorById(String supervisorId)
			throws DataAccessException {
		Supervisor supervisor = null;
		String sql = "SELECT AR_SUPERVISOR.SUPERVISOR_ID, "
				   + "  	 HR_EMPLOYEE.EMPID, "
				   + "       HR_EMPLOYEE.CHINESENAME, "
				   + "	     HR_EMPLOYEE.CHINESE_PINYIN, "
				   + "	     HR_EMPLOYEE.DEPTNAME, "
				   + "	     HR_EMPLOYEE.FOURTHDEPTNAME, "
				   + "	     to_char(AR_SUPERVISOR.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE  "
				   + "	FROM AR_SUPERVISOR, HR_EMP_V HR_EMPLOYEE  "
				   + " WHERE AR_SUPERVISOR.SUPERVISOR_ID = HR_EMPLOYEE.person_id  " 
				   + "   AND HR_EMPLOYEE.ACTIVITY = 1 "
				   + "   AND AR_SUPERVISOR.SUPERVISOR_ID = '" + supervisorId + "'" ;
		Logger.getLogger(getClass()).debug(sql);
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				supervisor = createSupervisor1(rs);
			}
		} catch (ServiceLocatorException e) {
			Logger.getLogger(getClass()).error(e.toString());
			supervisor = null;
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
			supervisor = null;
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		Logger.getLogger(getClass()).debug("get supervisor " + supervisor);
		return supervisor;
	}

	private Supervisor createSupervisor(ResultSet rs) {
		Supervisor supervisor = null;
		try {
			supervisor = new Supervisor();
			supervisor.setDepartment(StringUtil.checkNull(rs.getString("deptname")));
			supervisor.setFourthDepartment(StringUtil.checkNull(rs.getString("FOURTHDEPTNAME")));
			supervisor.setEmpId(rs.getString("EMPID"));
			supervisor.setSupervisorId(rs.getString("SUPERVISOR_ID"));
			supervisor.setChineseName(rs.getString("chinesename"));
			supervisor.setPinyin(rs.getString("CHINESE_PINYIN"));
			supervisor.setCreateDate(rs.getString("CREATE_DATE"));
			supervisor.setSupervised_deptname(rs.getString("supervised_deptname"));
		} catch (SQLException ex) {
			//ex.printStackTrace() ;
			Logger.getLogger(getClass()).error(ex.toString());
			supervisor = null;
		}
		return supervisor;
	}
	
	private Supervisor createSupervisor1(ResultSet rs) {
		Supervisor supervisor = null;
		try {
			supervisor = new Supervisor();
			supervisor.setDepartment(StringUtil.checkNull(rs.getString("deptname")));
			supervisor.setFourthDepartment(StringUtil.checkNull(rs.getString("FOURTHDEPTNAME")));
			supervisor.setSupervisorId(rs.getString("SUPERVISOR_ID"));
			supervisor.setEmpId(StringUtil.checkNull(rs.getString("EMPID")));
			supervisor.setChineseName(rs.getString("chinesename"));
			supervisor.setPinyin(rs.getString("CHINESE_PINYIN"));
			supervisor.setCreateDate(rs.getString("CREATE_DATE"));			
		} catch (SQLException ex) {
			//ex.printStackTrace() ;
			Logger.getLogger(getClass()).error(ex.toString());
			supervisor = null;
		}
		return supervisor;
	}

	
	public int getAllSupervisorCount(Object parameterObject)
			throws DataAccessException {
		AdminBean admin=ApplicationContext.getTheadLocal();
		
		SimpleMap smp = new SimpleMap();
		smp = (SimpleMap) parameterObject;
		String deptid = StringUtil.checkNull(smp.get("deptID"));
		String key = StringUtil.checkNull(smp.get("key"));
		String personId=StringUtil.checkNull(smp.get("personId"));
		int count = 0;
		String sql = " select count(*) from(select t.*,rownum cunt from (SELECT   ar_supervisor.supervisor_id, "
				+ " hr.chinesename chineseName, "
				+ " dep.DEPTNAME department, "
				+ " dep.FOURTHDEPTNAME fourthDepartment, "
				+ " to_char(ar_supervisor.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE,dep.deptname supervised_deptname "
				+ " FROM ar_supervisor, hr_employee hr, hr_emp_v dep"
				+ " WHERE hr.person_id = ar_supervisor.supervisor_id AND dep.person_id = ar_supervisor.supervisor_id  AND hr.CPNY_ID='"+admin.getCpnyId()+"'";
		if (!deptid.equals("") && deptid != null) {
			sql += "AND ar_supervisor.supervisor_id in (select info.ar_supervisor_id from ar_supervisor_info info where info.supervised_deptid ='" + deptid + "')";
		}
		if(personId!=null && !personId.equals("")){
				sql +=" AND ar_supervisor.supervisor_id = '" + personId + "'" ;
		}
		if (!key.equals("") && key != null) {
			sql += "AND ( hr.EMPID  like '%" + key + "%' or hr.CHINESENAME like '%"
					+ key + "%') ";
		}
		sql += " ORDER BY  dep.FOURTHDEPTNAME, dep.DEPTNAME) t )";
		Logger.getLogger(getClass()).debug(sql);
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			con = services.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (ServiceLocatorException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return count;
	}

	
	public List getAllSupervisorList(Object parameterObject, int currentPage,
			int pageSize) throws DataAccessException {
		
		AdminBean admin=ApplicationContext.getTheadLocal();
		List list = new ArrayList();
		SimpleMap  smp= new SimpleMap();
		smp=(SimpleMap)parameterObject;
		String deptid=StringUtil.checkNull(smp.get("deptID"));
		String personId=StringUtil.checkNull(smp.get("personId"));
		String key=StringUtil.checkNull(smp.get("key"));
		String sql = " select * from(select t.*,rownum cunt from (SELECT  hr.empid,ar_supervisor.supervisor_id, "+
		             " hr.chinesename chineseName, "+					 
					 " dep.DEPTNAME deptname, hr.chinese_pinyin CHINESE_PINYIN, "+
					 " dep.FOURTHDEPTNAME FOURTHDEPTNAME, "+
					 " to_char(ar_supervisor.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE,dep.deptname supervised_deptname  "+
					 " FROM ar_supervisor, hr_employee hr, hr_emp_v dep "+
					 " WHERE hr.person_id = ar_supervisor.supervisor_id AND dep.person_id = ar_supervisor.supervisor_id   AND hr.CPNY_ID='"+admin.getCpnyId()+"' ";
					 if(!deptid.equals("") && deptid!=null){
						 sql += "AND ar_supervisor.supervisor_id in (select info.ar_supervisor_id from ar_supervisor_info info where info.supervised_deptid ='" + deptid + "')";
					 }
					 if(personId!=null && !personId.equals("")){
							sql +=" AND ar_supervisor.supervisor_id = '" + personId + "'" ;
						 }
		             if(!key.equals("") && key!=null){
		            	sql +=" AND (hr.EMPID like '%"+key+"%' or hr.CHINESENAME like '%"+key+"%') " ;
		             }					
					 sql +=" ORDER BY  dep.FOURTHDEPTNAME, dep.DEPTNAME) t "+
					 " where ROWNUM <="+(currentPage+1)*pageSize+" ) temp "+
					 " where temp.cunt>"+currentPage*pageSize;
		Logger.getLogger(getClass()).debug(sql);
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			con = services.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(this.createSupervisor(rs));
			}
		} catch (ServiceLocatorException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return list;		
	}
}
