package com.ait.util;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.core.db.jdbc.JdbcTemplate;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sy.bean.SysCodeBean;
import com.ait.core.exception.LGRuntimeException;
import com.ait.util.SqlUtil;
import com.ait.util.SysCodeParser;
import com.ait.util.ServiceLocator;  
import com.ait.utils.ConnBean;

public class SysCodeParser {

	public static Vector getCodeByOrder(String parentCode) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select CODE_ID,CODE_NAME " + "from sy_code "
				+ "where PARENT_CODE='" + parentCode + "' "
				+ "order by orderno";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString("code_id"));
				map.put("name", rs.getString("code_name"));
				v.add(map);
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取"+parentCode+"代码列表错误",e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return v;
	}

	public static Vector getCode(String parentCode) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CODE_ID, CODE_NAME,CODE_KOR_NAME,CODE_EN_NAME "
			+ "FROM SY_CODE "
			+ "WHERE ACTIVITY = 1 AND PARENT_CODE = ? "
			+ "ORDER BY ORDERNO, CODE_NO";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Logger.getLogger(SysCodeParser.class).debug("PARENT_CODE: " + parentCode);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, parentCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString("CODE_ID"));
				map.put("name", rs.getString("CODE_NAME"));
				map.put("enName", rs.getString("CODE_EN_NAME"));
				map.put("korName", rs.getString("CODE_KOR_NAME"));				
				v.add(map);
			}
			Logger.getLogger(SysCodeParser.class).debug("[" + parentCode + "] count : " + v.size());
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取"+parentCode+"代码列表错误",e);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return v;
	}
	
	
	public static Vector getCodeByLaguage(String parentCode,String language) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CODE_ID, CODE_NAME "
			+ "FROM SY_CODE "
			+ "WHERE ACTIVITY = 1 AND PARENT_CODE = ? "
			+ "ORDER BY ORDERNO, CODE_NO";
		if (!language.equals("zh"))
		sql = "SELECT CODE_ID, CODE_EN_NAME"
			+ " FROM SY_CODE "
			+ "WHERE ACTIVITY = 1 AND PARENT_CODE = ? "
			+ "ORDER BY ORDERNO, CODE_NO";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Logger.getLogger(SysCodeParser.class).debug("PARENT_CODE: " + parentCode);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, parentCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {                     
				HashMap map = new HashMap();
				map.put("code", rs.getString(1));
				map.put("name", rs.getString(2));
				v.add(map);
			}
			Logger.getLogger(SysCodeParser.class).debug("[" + parentCode + "] count : " + v.size());
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取"+parentCode+"代码列表错误",e);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return v;
	}

	public static Vector getCode(String parentCode, int activity) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CODE_ID, CODE_NAME,Code_En_Name "
			+ "FROM SY_CODE "
			+ "WHERE PARENT_CODE = ? "
			+ "AND ACTIVITY = ? "
			+ "ORDER BY ORDERNO, CODE_NO";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, parentCode);
			pstmt.setInt(2, activity);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString("CODE_ID"));   
				map.put("name", rs.getString("CODE_NAME"));
				map.put("enname", rs.getString("CODE_EN_NAME"));
				v.add(map);
			}
			Logger.getLogger(SysCodeParser.class).debug("[" + parentCode + "] count : " + v.size());
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取"+parentCode+"的sy_code错误",e);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return v;
	}

	public static Vector getCodeInstead(String parentCode, int activity) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CODE_ID, CODE_NAME,Code_En_Name "
			+ "FROM SY_CODE "
			+ "WHERE PARENT_CODE = ? "
			+ "AND ACTIVITY = ? "
			+ "AND CODE_ID in ('ArModifyApply','LeaveApply','OtApply') "
			+ "ORDER BY ORDERNO, CODE_NO";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, parentCode);
			pstmt.setInt(2, activity);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString("CODE_ID"));   
				map.put("name", rs.getString("CODE_NAME"));
				map.put("enname", rs.getString("CODE_EN_NAME"));
				v.add(map);
			}
			Logger.getLogger(SysCodeParser.class).debug("[" + parentCode + "] count : " + v.size());
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取"+parentCode+"的sy_code错误",e);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return v;
	}

	public static Vector getCodeInsteadAppoint(String parentCode, int activity) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CODE_ID, CODE_NAME "
			+ "FROM SY_CODE "
			+ "WHERE PARENT_CODE = ? "
			+ "AND ACTIVITY = ? "
			+ "AND CODE_ID = 'OtApply'"
			+ "ORDER BY ORDERNO, CODE_NO";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, parentCode);
			pstmt.setInt(2, activity);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString("CODE_ID"));   
				map.put("name", rs.getString("CODE_NAME"));
				v.add(map);
			}
			Logger.getLogger(SysCodeParser.class).debug("[" + parentCode + "] count : " + v.size());
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取"+parentCode+"的sy_code错误",e);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return v;
	}
	
	public static Vector getTaxCode() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select TAX_TYPE_NO,TAX_TYPE from PA_INCOME_TAX_TYPE ";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString("TAX_TYPE_NO"));
				map.put("name", rs.getString("TAX_TYPE"));
				v.add(map);
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取PA_INCOME_TAX_TYPE列表错误",e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return v;
	}

	public static Vector getQualificationType() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select Q_ALLW_TYP_NO,Q_ALLW_TYPE from PA_Q_ALLW_TYPE";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString("Q_ALLW_TYP_NO"));
				map.put("name", rs.getString("Q_ALLW_TYPE"));
				v.add(map);
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取PA_Q_ALLW_TYPE列表错误",e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return v;
	}

	public static Vector getDutyAllowNo() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select PA_D_ALLW_NO,DESCRIPTION "
				+ "from PA_DUTY_ALLOWANCE_V";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString(1));
				map.put("name", rs.getString(2));
				v.add(map);
			}

		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取PA_DUTY_ALLOWANCE_V列表错误",e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return v;
	}

	public static Vector getQualAllowNo() { // for emp_qual_bonus
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select " + "PA_Q_ALLW_NO, "
				+ "ALLOWANCE_TYPE || ' ' || Q_ALLW_AMOUNT "
				+ "from PA_QUAL_ALLOWANCE_V";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString(1));
				map.put("name", rs.getString(2));
				v.add(map);
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取PA_QUAL_ALLOWANCE_V列表错误",e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return v;
	}

	public static Vector getProportionNo() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select " + "SALARY_PRO_NO, "
				+ "BASE_PART * 100 || '/' || ABILITY_PART * 100 "
				+ "from PA_SALARY_PROPORTION_V ";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString(1));
				map.put("name", rs.getString(2));
				v.add(map);
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取PA_SALARY_PROPORTION_V列表错误",e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return v;
	}

	public static Vector getCode(String table, String code, String name) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select " + code + "," + name + " from " + table;
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString(1));
				map.put("name", rs.getString(2));
				v.add(map);
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("方法getCode错误",e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return v;
	}

	public static Vector getCode(String table, String code, String name,
			int activity) throws DataAccessException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select " + code + "," + name + " from " + table
				+ " where activity='" + activity + "'";
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString(1));
				map.put("name", rs.getString(2));
				v.add(map);
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("方法getCode错误",e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return v;
	}
	
	public static Vector getCode(String table, String code, String name,String item) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "" ;
		if(item.equals("all"))
			sql = "select " + code + "," + name + " from " + table;
		else
			sql = "select " + code + "," + name + " from " + table + " where 1=1 " + getWhereSQL(item,code);
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector v = new Vector();
		try {
			con = ServiceLocator.getInstance().getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {       
				HashMap map = new HashMap();
				map.put("code", rs.getString(1));
				map.put("name", URLEncoder.encode(rs.getString(2),"UTF-8"));
				v.add(map);
			}                     
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new LGRuntimeException("方法getCode错误",e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}              
		return v;
	}
	
	
	private static String getWhereSQL(String item,String code){
		String whereSQL = " ";
		String[] temp = item.split(",");
		for (int i = 0; i < temp.length; i++) {
			if(whereSQL.equals(" "))
				whereSQL += " and (" ;
			else
				whereSQL += " or " ;
			whereSQL += code+"='"+temp[i]+"'" ;                 
		}
		return whereSQL+") ";
	}

	
	public static List getSysCode(String parentCode) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CODE_ID, CODE_NAME,CODE_EN_NAME,CODE_KOR_NAME "
			+ "FROM SY_CODE "
			+ "WHERE PARENT_CODE = ? AND ACTIVITY = 1 "
			+ "ORDER BY CODE_NO,ORDERNO";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		List list = new ArrayList();
		try {
			con = ConnBean.getConn() ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, parentCode);    
			rs = pstmt.executeQuery();
			SysCodeBean codeBean = null ;
			while (rs.next()) {
				codeBean = new SysCodeBean();
				codeBean.setCodeId(rs.getString("CODE_ID"));
				codeBean.setCodeName(rs.getString("CODE_NAME"));  
				codeBean.setEnCodeName(rs.getString("CODE_EN_NAME"));
				codeBean.setKorCodeName(rs.getString("CODE_KOR_NAME"));
				list.add(codeBean);
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取"+parentCode+"的sy_code异常",e);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;
	}
	
	public static String  getEmpNomalDiff(String cpnyId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String empdiff="";
		String sql =  " select DISTINCT hr.emp_diff_code EMPDIFFCODE, sy.code_name NAME "
		             +" from hr_employee hr, sy_code sy "
		             +" where hr.emp_diff_code = sy.code_id(+)  "
		             +"  and  HR.CPNY_ID = ?  "
		             +"   and rownum=1  ";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		List list = new ArrayList();
		try {
			con = ConnBean.getConn() ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cpnyId);    
			rs = pstmt.executeQuery();
			SysCodeBean codeBean = null ;
			while (rs.next()) {				
				empdiff=rs.getString("EMPDIFFCODE");				
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new GlRuntimeException("获取默认员工区分发生错误");
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return empdiff;
	}
	
	public static Vector getSysCodeV(String parentCode) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CODE_ID, CODE_NAME "
			+ "FROM SY_CODE "
			+ "WHERE PARENT_CODE = ? "
			+ "ORDER BY ORDERNO, CODE_NO";
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector list = new Vector();
		try {
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, parentCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString(1));
				map.put("name", rs.getString(2));
				list.add(map);
			}
			Logger.getLogger(SysCodeParser.class).debug("[" + parentCode + "] count : " + list.size());
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());

		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;
	}
	
	public static Vector getSysCodeV(String parentCode,String item,String language) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "" ;   
		if(item.trim().equals("all"))
		 sql = "SELECT CODE_ID, CODE_NAME,CODE_KOR_NAME,CODE_EN_NAME "
			+ "FROM SY_CODE "
			+ "WHERE PARENT_CODE = ? "
			+ "ORDER BY ORDERNO, CODE_NO";
		else
			sql = "SELECT CODE_ID, CODE_NAME ,CODE_KOR_NAME,CODE_EN_NAME "
				+ "FROM SY_CODE "
				+ "WHERE PARENT_CODE = ? "+ getWhereSQL(item, "code_id")
				+ "ORDER BY ORDERNO, CODE_NO " ;
		Logger.getLogger(SysCodeParser.class).debug(sql);
		Vector list = new Vector();
		try {
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, parentCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap map = new HashMap();
				map.put("code", rs.getString(1));
				if(language.equals("zh")){
					map.put("name", rs.getString(2));
				}else if(language.equals("kor")){
					map.put("name", rs.getString(3));
				}else{
					map.put("name", rs.getString(4));
				}
				list.add(map);
			}
			Logger.getLogger(SysCodeParser.class).debug("[" + parentCode + "] count : " + list.size());
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());

		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;
	}
	public static List getPostGradeCode(){
		List list = new ArrayList() ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT post_grade_id, "+
		"  post_grade_name, "+
		"  post_grade_en_name, "+
		"  post_grade_kor_name "+
		"		FROM hr_post_grade   "+
				" order by post_grade_id ";
		Logger.getLogger(SysCodeParser.class).debug(sql);  
		try {
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SysCodeBean codeBean = new SysCodeBean();
				codeBean.setCodeId(rs.getString("post_grade_id"));
				codeBean.setCodeName(rs.getString("post_grade_name"));
				codeBean.setEnCodeName(rs.getString("post_grade_en_name"));
				codeBean.setKorCodeName(rs.getString("post_grade_kor_name"));
				list.add(codeBean);  
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new LGRuntimeException("获取postGradeCode异常",e);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list ;
	}
	
	
	public static List getPostGroupCode(){
		List list = new ArrayList() ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =  "SELECT post_group_id, "+
		"  post_group_name, "+
		"  post_group_en_name, "+
		"  post_group_kor_name "+
		"		FROM hr_post_group  "+
				" order by post_group_id ";
		Logger.getLogger(SysCodeParser.class).debug(sql);  
		try {
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SysCodeBean codeBean = new SysCodeBean();
				codeBean.setCodeId(rs.getString("post_group_id"));
				codeBean.setCodeName(rs.getString("post_group_name")); 
				codeBean.setEnCodeName(rs.getString("post_group_en_name"));				
				codeBean.setKorCodeName(rs.getString("post_group_kor_name"));
				list.add(codeBean);  
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new LGRuntimeException("获取getPostGroupCode异常",e);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list ;
	}
	
	
	public static List getPostCode(){
		List list = new ArrayList() ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =  "SELECT post_id, "+
		"  post_name, "+
		"  post_en_name, "+
		"  post_kor_name "+
		"		FROM hr_post  "+
				" order by post_id ";
		Logger.getLogger(SysCodeParser.class).debug(sql);  
		try {
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SysCodeBean codeBean = new SysCodeBean();
				codeBean.setCodeId(rs.getString("post_id"));
				codeBean.setCodeName(rs.getString("post_name"));  
				codeBean.setEnCodeName(rs.getString("post_en_name"));
				codeBean.setKorCodeName(rs.getString("post_kor_name"));
				list.add(codeBean);  
			}
		} catch (Exception e) {
			Logger.getLogger(SysCodeParser.class).error(e.toString());
			throw new LGRuntimeException("获取hr_post异常",e);        
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list ;
	}
	
	public static Vector getCodeByID(String evs_period_id,String ev_type_id){
	    Connection con=null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    System.out.println(evs_period_id + "33333333333333333333333333");
	    System.out.println(ev_type_id + "33333333333333333333333333");
	    String sql ="SELECT EV.EV_PROCESS_ID, " +
	    		" SY.CODE_NAME, EV.EV_PROCESS_ORDER, " +
	    		" EV.EVS_MASTER_TYPE FROM EVS_TYPE_PROCESS EV, " +
	    		" SY_CODE SY" +
	    		" WHERE EV.EV_PROCESS_ID = SY.CODE_ID(+) " +
	    		" AND EV.EV_PERIOD_ID = '" + evs_period_id + "'  AND EV.EV_TYPE_ID = '" + ev_type_id + "' " +
	    		" ORDER BY EV_PROCESS_ORDER";
	    Logger.getLogger(SysCodeParser.class).debug(sql);
	    Vector v = new Vector();
	    try{
	      con = ServiceLocator.getInstance().getConnection();
	      stmt=con.prepareStatement(sql);
	      rs=stmt.executeQuery();
	      while(rs.next()){
	        HashMap map = new HashMap();
	        map.put("code", rs.getString("EV_PROCESS_ID"));
	        map.put("name",rs.getString("CODE_NAME"));
	        map.put("processorder",rs.getString("EV_PROCESS_ORDER"));
	        map.put("masterType",rs.getString("EVS_MASTER_TYPE")); 
	        v.add(map);
	      }
	    }catch(Exception e){
	      Logger.getLogger(SysCodeParser.class).error(e.toString());
	      e.printStackTrace() ;
	    }finally{
	      SqlUtil.close(rs,stmt,con);
	    }
	    return v;
	  }

}
