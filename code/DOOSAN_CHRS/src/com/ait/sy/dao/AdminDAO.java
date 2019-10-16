package com.ait.sy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ait.core.db.jdbc.JdbcTemplate;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

import org.apache.log4j.Logger;

/**
 * <p>Title: Admin DAO </p>
 *
 * <p>Description:doing the administrator log in and list its info</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company:AIT </p>
 * @author not attributable
 * @version 1.0
 */
public class AdminDAO {

	public AdminDAO() {
	}

	public AdminBean login(String username, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement("select * from sy_admin_v where USERNAME=? and PASSWORD=?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next())
				return createAdminBean(rs);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug("登陆失败",ex);
			throw new GlRuntimeException("登陆失败",ex);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return null;
	}

	
	public AdminBean searchEmp(String username,String cpnyId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcTemplate.getTemplate().getConnection();
			//pstmt = con.prepareStatement("select * from sy_admin_v where adminid=? and cpny_id=? ");
			pstmt = con.prepareStatement("select * from sy_admin_v where adminid in (select y.empid from hr_employee y where y.ko_person_id in (select e.ko_person_id from hr_employee e where e.ad_user_id = lower(?) or e.empid = lower(?) or e.ko_person_id = lower(?)  ) and y.cpny_id = ? and (y.status_code is null or y.status_code != 'EmpStatus3')) and cpny_id=?  order by adminid desc");
			pstmt.setString(1, username);
			pstmt.setString(2, username);
			pstmt.setString(3, username);
			pstmt.setString(4, cpnyId);
			pstmt.setString(5, cpnyId);
			rs = pstmt.executeQuery();
			if (rs.next())
				return createAdminBean(rs);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug("登陆失败",ex);
			throw new GlRuntimeException("登陆失败",ex);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return null;
	}
	
	public void updateUserIP(String userIP,String personId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement("update SY_ADMIN set LOGIN_IP = ? ,LOGIN_TIME =sysdate where ADMINID = ? ");
			pstmt.setString(1, userIP);
			pstmt.setString(2, personId);
		    pstmt.executeQuery();
			
		    pstmt = con.prepareStatement("insert into  sy_admin_list values  ( ? ,  ?,sysdate) ");
			pstmt.setString(1, personId );
			pstmt.setString(2, userIP);
		    pstmt.executeQuery();
		    
		    
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug("更新登陆时间和IP失败",ex);
			throw new GlRuntimeException("更新登陆时间和IP失败",ex);
		} finally {
			SqlUtil.close( pstmt, con);
		}
		
	}
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private AdminBean createAdminBean(ResultSet rs) {
		AdminBean admin = new AdminBean();
		try {
			admin.setAdminNo(rs.getInt("ADMINNO") + "") ;
			admin.setAdminID(StringUtil.checkNull(rs.getString("PERSON_ID")));
			//添加  登录的Ad验证
			admin.setAd_User_Id(StringUtil.checkNull(rs.getString("AD_USER_ID")));
			
			admin.setAdminLevel((rs.getInt("ADMINLEVEL")));
			admin.setUsername(StringUtil.checkNull(rs.getString("USERNAME")));
			admin.setPassword(StringUtil.checkNull(rs.getString("PASSWORD")));
			admin.setChineseName(StringUtil.checkNull(rs.getString("CHINESENAME")));
			admin.setPinyin(StringUtil.checkNull(rs.getString("CHINESE_PINYIN")));
			admin.setEnglishName(StringUtil.checkNull(rs.getString("ENGLISHNAME")));
			admin.setKoreanname(StringUtil.checkNull(rs.getString("KOREANNAME")));
			admin.setScreenGrantNo(StringUtil.checkNull(rs.getString("SCREEN_GRANT_NO")));
			admin.setCreateDate(StringUtil.checkNull(rs.getString("CREATE_DATE")));
			admin.setCreatedBy(StringUtil.checkNull(rs.getString("CREATED_BY")));
			admin.setUpdateDate(StringUtil.checkNull(rs.getString("UPDATE_DATE")));
			admin.setUpdatedBy(StringUtil.checkNull(rs.getString("UPDATED_BY")));
			admin.setOrderNo((rs.getInt("ORDERNO")));
			admin.setActivity((rs.getInt("ACTIVITY")));
			admin.setDeptID(StringUtil.checkNull(rs.getString("DEPTID")));
			admin.setHrTag(rs.getString("HR_TAG"));
			admin.setCompanyName(StringUtil.checkNull(rs.getString("COMPANY_NAME")));
			admin.setDepartment(StringUtil.checkNull(rs.getString("deptname")));
			admin.setEnglishdept(StringUtil.checkNull(rs.getString("DEPT_EN_NAME")));
			admin.setKordept(StringUtil.checkNull(rs.getString("DEPT_KOR_NAME")));
			admin.setCpnyId(StringUtil.checkNull(rs.getString("CPNY_ID")));
			admin.setCpnyName(StringUtil.checkNull(rs.getString("CPNY_NAME")));
			admin.setPersonId(StringUtil.checkNull(rs.getString("PERSON_ID")));
			admin.setCodeType(getLoginCodeType(StringUtil.checkNull(rs.getString("SCREEN_GRANT_NO"))));
			admin.setEmpDiffCode(StringUtil.checkNull(rs.getString("EMP_DIFF_CODE")));
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).debug("登陆失败 createAdminBean",ex);
			throw new GlRuntimeException("登陆失败 createAdminBean",ex);
		}
		return admin;
	}
	
	private List getLoginCodeType(String SCREEN_GRANT_NO){
		List result = new ArrayList();
		String grantStr[]=SCREEN_GRANT_NO.split(",");
		String whereStr="";
		for(int i=0;i<grantStr.length;i++){
			whereStr +="'"+grantStr[i]+"',";
			
		}
		whereStr=whereStr.substring(0,whereStr.length()-1);		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(" select distinct t.screen_code from sy_screen_grant t where t.screen_grant_no in ("+whereStr+")  and t.code_type is not null ");			
			rs = pstmt.executeQuery();
			while (rs.next()){
				SimpleMap simpleMap = new SimpleMap();
				simpleMap.set(rs.getString("SCREEN_CODE"), getCodeType(whereStr,rs.getString("SCREEN_CODE")));				
				result.add(simpleMap);				
			}				
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug("取出登陆者的getLoginCodeType类型时出错",ex);
			throw new GlRuntimeException("取出登陆者的getLoginCodeType类型时出错",ex);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return result;		
	}
	
	private List getCodeType(String whereStr,String menuCodeStr){
		List result = new ArrayList();		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcTemplate.getTemplate().getConnection();
			pstmt = con.prepareStatement(" select distinct t.code_type from sy_screen_grant t where t.screen_grant_no in ("+whereStr+") and t.screen_code=?  and t.code_type is not null ");
			pstmt.setString(1, menuCodeStr);		
			rs = pstmt.executeQuery();
			while (rs.next()){
				result.add(rs.getString("CODE_TYPE"));				
			}				
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug("取出登陆者的getCodeType类型时出错",ex);
			throw new GlRuntimeException("取出登陆者的getCodeType类型时出错",ex);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return result;		
	}
	
	
}
