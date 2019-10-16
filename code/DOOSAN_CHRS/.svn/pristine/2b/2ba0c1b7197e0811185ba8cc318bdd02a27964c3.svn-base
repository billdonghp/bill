package com.ait.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.sy.sy0104.bean.Ent;
import com.ait.util.SqlUtil;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: getconnection
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class Func {
	HttpSession m_session = null;
    Vector Vlist = new Vector(); // 产生Vector的对象Vlist

	/**
	 * 动态连接数据库所需函数
	 * 
	 * @param m_session
	 */
	public Func(HttpSession m_session) {
		this.m_session = m_session;
	}

	public Func() {

	}

	/**
	 * 动态连接数据库所需函数
	 * 
	 * @param m_session
	 *            func 里边如果哪个函数原来调用了Conn函数，若需要动态连接三个数据库可改为调用本函数
	 */

	private Connection Conn() throws Exception {
		Connection conn = null;
		try {
			conn = ConnBean.getConn();
		} catch (Exception e) {
			System.out.print(e);
		}
		return conn;
	}

	public String getChannel() throws Exception {
		String strNav = "";

		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String LIST_SQL_0 = "select  "
				+ " b.menuintro,"
				+ " b.menuparentcode, "
				+ " b.depth,      "
				+ " b.menuparentcode, "
				+ " b.menuintro, "
				+ " b.menuurl "
				+ " from sy_menu b,SY_LOGIN_SCREEN a, "
				+ " where b.MenuCode (+)=a.ScreenCode "
				+ " and b.MenuCode <> '0' START WITH b.menuparentcode=0 CONNECT BY PRIOR b.menuno=b.menuparentcode";
		Logger.getLogger(getClass()).debug(LIST_SQL_0);
		// String TREE_SQL = "WHERE menucode <> '0' START WITH menuparentcode=0
		// CONNECT BY PRIOR menuno=menuparentcode";
		// Logger.getLogger(getClass()).error("ssss"+LIST_SQL_0);
		try {
			pstmt = conn.prepareStatement(LIST_SQL_0);
			rs = pstmt.executeQuery();
			// Logger.getLogger(getClass()).error("NAVVVVV"+rs);
			while (rs.next()) {
				strNav = strNav
						+ "<td width='140' class=nav_active><a href='/jsp/"
						+ rs.getString(6)
						+ ".jsp' class='nav' >"
						+ (rs.getString(5))
						+ "</a></td>"
						+ "<td width='1' valign='top' class='nav_normal'><img src='' alt='' width='1' height='6' style='background-color: #FFFFFF'></td>";
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return strNav;
	}

	public void updateActive(String statsValue, String menuCode, String no)
			throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		String MENU_SQL = "SELECT keyno,                "
				+ "    tablename			        "
				+ "    from sy_menu where menuCode=?	        ";
		Logger.getLogger(getClass()).debug(MENU_SQL);
		try {
			pstmt = conn.prepareStatement(MENU_SQL);
			pstmt.setString(1, menuCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				String UPDATE_SQL_0 = "UPDATE " + rs.getString(2)
						+ " set ACTIVITY=0 ";

				String UPDATE_SQL_1 = "UPDATE " + rs.getString(2)
						+ " set ACTIVITY=1 ";

				String WHERE_SQL = "where " + rs.getString(1) + "=?";

				if (statsValue.equals("1")) {
					try {
						String executeSqlStr = UPDATE_SQL_0 + WHERE_SQL;
						Logger.getLogger(getClass()).debug(executeSqlStr);
						pstmt1 = conn.prepareStatement(executeSqlStr);
						pstmt1.setInt(1, Integer.parseInt(no));
						pstmt.executeQuery();
						if (pstmt1.executeUpdate() == 0) {
							throw new NotExistException("No1 data exists");
						}
					}

					catch (SQLException e) {
						Logger.getLogger(getClass()).error(e.toString());
					}

					// Logger.getLogger(getClass()).error("result1----"+UPDATE_SQL_0
					// + WHERE_SQL);
				}
				if (statsValue.equals("0")) {
					try {
						String executeSqlStr = UPDATE_SQL_1 + WHERE_SQL;
						Logger.getLogger(getClass()).debug(executeSqlStr);
						pstmt1 = conn.prepareStatement(executeSqlStr);
						pstmt1.setInt(1, Integer.parseInt(no));
						pstmt.executeQuery();
						if (pstmt1.executeUpdate() == 0) {
							throw new NotExistException("No1 data exists");
						}
					}

					catch (SQLException e) {
						Logger.getLogger(getClass()).error(e.toString());
					}

					// Logger.getLogger(getClass()).error("no"+no);
					// Logger.getLogger(getClass()).error("result2----"+UPDATE_SQL_1
					// + WHERE_SQL);
				}
			}

		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (pstmt1 != null)
				pstmt1.close();
			if (conn != null)
				conn.close();
		}

	}

	//
	public ArrayList getGroup(int adminNo) throws Exception {
		String str = null;
		ArrayList groups = new ArrayList();
		Connection conn = Conn();
		ResultSet rs = null;
		String Oracle_Sql = "select screen_grant_no  from SY_ADMIN where adminno="
				+ adminNo;
		// Logger.getLogger(getClass()).debug(Oracle_Sql);
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(Oracle_Sql);
			while (rs.next()) {
				str = rs.getString("screen_grant_no");
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
		String[] str1 = str.split(",");
		for (int i = 0; i < str1.length; i++) {
			groups.add(new Integer(str1[i].toString()));
		}
		return groups;

	}

	//获取裁决类型
	public Vector getAffirmTypeGroup(String adminNo) throws Exception {
		String str = null;
		ArrayList groups = new ArrayList();
		Connection conn = Conn();
		ResultSet rs = null;
		String Oracle_Sql = "select S.PERSON_ID, S.COMFIRM_CPNYID, S.AFFIRM_TYPE_ID,"
			+" to_char(S.CREATE_DATE,'yyyy-mm-dd') CREATE_DATE from SY_COMFIRM_CPNYID s"
			+" where s.person_id = "+adminNo+")";
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(Oracle_Sql);
			while (rs.next()) {
				Ent ent = new Ent();
		        ent.setPerson_id(rs.getString("PERSON_ID"));  
		        ent.setComfirm_cpnyid(rs.getString("COMFIRM_CPNYID"));
		        ent.setAffirm_type_id(rs.getString("AFFIRM_TYPE_ID"));
		        ent.setCreateDate(rs.getString("CREATE_DATE"));
		        ent.setChineseName(rs.getString("CHINESENAME"));
		        ent.setEmpID(rs.getString("EMPID"));
		        ent.setCode_name(rs.getString("CODE_NAME"));
                Vlist.addElement(ent);
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
		
		return Vlist;

	}
	
	//
	public String getGrant_Group_select(ArrayList groups,String cpnyid) throws Exception {   
		String strNav = "";
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String LIST_SQL_0 = "SELECT SCREEN_GRANT_NO,                "
				+ "    SCREEN_GRANT_NAME			        "
				+ "    from SY_LOGIN_SCREEN  where  cpny_id='"+cpnyid+"' and  SCREEN_GRANT_NO not in ('4','104','204','304','404') order by SCREEN_GRANT_NO desc	        ";

		try {
			pstmt = conn.prepareStatement(LIST_SQL_0);
			rs = pstmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				String temp = "";
				int sc_no = rs.getInt("SCREEN_GRANT_NO");
				for (int x = 0; x < groups.size(); x++) {
					if (((Integer) groups.get(x)).intValue() == sc_no) {
						temp = "checked";
						break;
					}
				}
				strNav = strNav
						+ "<TD style=\"writing-mode:tb-rl;valign:top;font-family: Arial;font-size: 12px;font-weight:bold;color: #666666;\" ><input type='checkbox' name='radio' value='"
						+ rs.getString(1) + "' " + temp + ">" + rs.getString(2)
						+ "</TD>";
				// Logger.getLogger(getClass()).error("temp-----" + temp);
				// Logger.getLogger(getClass()).error("strNav" + strNav);
			}
			i = i + 1;

		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)     
				conn.close();
		}
		return strNav;
	}
	
	public String getGrant_Group_en_select(ArrayList groups,String cpnyId) throws Exception {
		String strNav = "";
		Connection conn = Conn();
		PreparedStatement pstmt = null;                    
		ResultSet rs = null;
		String LIST_SQL_0 = "SELECT SCREEN_GRANT_NO,                "
				+ "    SCREEN_GRANT_EN_NAME			        "
				+ "    from SY_LOGIN_SCREEN where cpny_id='"+cpnyId+"' order by SCREEN_GRANT_NO desc	        ";

		try {
			pstmt = conn.prepareStatement(LIST_SQL_0);
			rs = pstmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				String temp = "";
				int sc_no = rs.getInt("SCREEN_GRANT_NO");
				for (int x = 0; x < groups.size(); x++) {
					if (((Integer) groups.get(x)).intValue() == sc_no) {
						temp = "checked";
						break;
					}
				}
				strNav = strNav
						+ "<TD><input type='checkbox' name='radio' value='"
						+ rs.getString(1) + "' " + temp + ">" + rs.getString(2)
						+ "</TD>";
				// Logger.getLogger(getClass()).error("temp-----" + temp);
				// Logger.getLogger(getClass()).error("strNav" + strNav);
			}
			i = i + 1;

		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return strNav;
	}

	public String getGrant_Group() throws Exception {
		String strNav = "";

		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String LIST_SQL_0 = "SELECT SCREEN_GRANT_NO,                "
				+ "    SCREEN_GRANT_NAME			        "
				+ "    from SY_LOGIN_SCREEN order by SCREEN_GRANT_NO desc	        ";

		// Logger.getLogger(getClass()).error("ssss"+LIST_SQL_0);
		try {
			pstmt = conn.prepareStatement(LIST_SQL_0);
			rs = pstmt.executeQuery();
			// Logger.getLogger(getClass()).error("NAVVVVV"+rs);
			int i = 0;
			while (rs.next()) {

				strNav = strNav
						+ "<TD width='100'><input type='checkbox' name='radio' value="
						+ rs.getString(1) + ">" + rs.getString(2) + "</TD>";
				i += 1;
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return strNav;
	}
	
	public String getGrant_EnGroup() throws Exception {
		String strNav = "";

		Connection conn = Conn();     
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String LIST_SQL_0 = "SELECT SCREEN_GRANT_NO,                "
				+ "    SCREEN_GRANT_EN_NAME			        "
				+ "    from SY_LOGIN_SCREEN SCREEN_GRANT_NO<>'100' order by SCREEN_GRANT_NO desc	        ";

		// Logger.getLogger(getClass()).error("ssss"+LIST_SQL_0);
		try {
			pstmt = conn.prepareStatement(LIST_SQL_0);
			rs = pstmt.executeQuery();
			// Logger.getLogger(getClass()).error("NAVVVVV"+rs);
			int i = 0;
			while (rs.next()) {

				strNav = strNav
						+ "<TD width='100'><input type='checkbox' name='radio' value="
						+ rs.getString(1) + ">" + rs.getString(2) + "</TD>";
				i += 1;
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return strNav;
	}
	public String editNull(String s) {

		if (s == null || s.equals("") || s.equals("null &gt; ")) {
			return "";
		} else {
			return s;
		}
	}

	public String editNull1(String s) {

		if (s == null || s.equals("") || s.equals(" &gt; ")) {
			return "";
		} else {
			return s;
		}
	}

	public String editNavNull(String s) {
		// Logger.getLogger(getClass()).error("editNavNull:"+s);
		if (s.equals("null &gt; ") || s.equals("null")) {
			return "";
		} else {
			return s;
		}
	}

	public String getDateStr(java.util.Date dt, String format) {

		String dateString = "";
		if (dt != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			dateString = formatter.format(dt);
		}
		return dateString;
	}

	/*
	 * public Board_Stats(String Menu_Code) throws Exception { Connection conn =
	 * Conn(); PreparedStatement pstmt = null; ResultSet rs = null; String sql =
	 * "select * from Admin where LogID =? and Password = ?";
	 * 
	 * 
	 * try {
	 * 
	 * pstmt = conn.prepareStatement(sql); pstmt.setString(1, LogID);
	 * pstmt.setString(2, Password); rs = pstmt.executeQuery(); if (rs.next()) {
	 * chk = true; } } catch (SQLException e) {
	 * Logger.getLogger(getClass()).error(e); } finally {
	 * 
	 * if (rs != null) rs.close(); if (pstmt != null) pstmt.close(); if (conn !=
	 * null) conn.close(); return chk; } }
	 */
	public boolean chkUsrDept(int usrNo, String DeptID) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select deptid from hr_department where deptid = ? and deptid in (select LOGINDEPTID from sy_login_info where loginno=?) ";
		boolean chk = false;
		// Logger.getLogger(getClass()).error("usrNo"+usrNo);
		// Logger.getLogger(getClass()).error("DeptID"+DeptID);
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, DeptID);
			pstmt.setInt(2, usrNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return chk;
	}

	public boolean chkGrantUsr(int usrNo, String screenGrantNo)
			throws Exception {
		Connection conn = Conn();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT SCREEN_GRANT_NO FROM SY_LOGIN_SCREEN WHERE SCREEN_GRANT_NO=? AND SCREEN_GRANT_NO IN (SELECT SCREEN_GRANT_NO FROM SY_ADMIN WHERE ADMINNO=?) ";
		boolean chk = false;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, usrNo);
			pstmt.setString(1, screenGrantNo);
			// Logger.getLogger(getClass()).error("usrNo--------"+usrNo);
			// Logger.getLogger(getClass()).error("screenGrantNo--------"+screenGrantNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				chk = true;
			}
			// Logger.getLogger(getClass()).error("chk--------"+chk);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return chk;
	}

	public String getNowDt() throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select to_char(sysdate,'YYYYMMDD')  from dual";
		String dt = "";

		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			dt = rs.getString(1);

		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return dt;
	}
	public String replaceAllstr(String detail, String need, String ok) { // 最新版本的字符串全替换^_^
		// detail为待处理字符串
		// need为被替换字符串
		// ok为替换成的字符串
		String temp1 = "";
		int x = 1;
		int y = 0;
		while (x >= 0) {
			x = detail.indexOf(need, y);
			if (x >= 0) {
				temp1 = temp1 + detail.substring(y, x) + ok;
				y = x + need.length();
			} else {
				temp1 = temp1 + detail.substring(y, detail.length());
			}
		}
		return temp1;
	}

	public String replaceStr(String detail, String need, String ok) { // ?x??\uACBD\uAD76\uB3E8俚\uB95C\uB214\uD64D??\uBEE3^_^
		// detail??\uB364\uB1F9\uC7BF俚\uB95C\uB214
		// need??\uAD73??\uBEE3俚\uB95C\uB214
		// ok?「?\uBEE3\uB0E5\uB3E8俚\uB95C\uB214
		String temp1 = "";
		int x = 1;
		int y = 0;
		while (x >= 0) {
			x = detail.indexOf(need, y);
			if (x >= 0) {
				temp1 = temp1 + detail.substring(y, x) + ok;
				y = x + need.length();
			} else {
				temp1 = temp1 + detail.substring(y, detail.length());
			}
		}
		return temp1;
	}

	public String chkUploadFile(String fl1, String fl2, String fl3, String fl4,
			String fl5, String fl6, String fl7, String fl8, String fl9,
			String fl10) {

		if (fl1 != "" || fl2 != "" || fl3 != "" || fl4 != "" || fl5 != ""
				|| fl6 != "" || fl7 != "" || fl8 != "" || fl9 != ""
				|| fl10 != "") {

			return "<img src='/images/Content_file.gif' width='14' height='12'>";
		} else {
			return "&nbsp;";
		}
	}

	public String isoStr(String str) {
		return str;
	}

	public String strIso(String str) {
		String new_str = "";
		try {
			new_str = new String(str.getBytes("GBK"), "ISO-8859-1");
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		}
		return new_str;
	}

	public String strIsoZp(String str) {
//		String new_str = "";
//		try {
//			new_str = new String(str.getBytes("8859_1"), "GBK");
//		} catch (Exception e) {
//			Logger.getLogger(getClass()).error(e.toString());
//		}
//		return new_str;
		return str;
	}

	public int getUpLevel(String Table, String LevelFiled, String CodeNo,
			String ParntCodeValue) throws Exception {
		Connection conn = Conn();
		Statement state = null;
		ResultSet rs = null;
		String sql = "select " + LevelFiled + " " + "from " + Table + " "
				+ "where  " + CodeNo + "='" + ParntCodeValue + "'";
		Logger.getLogger(getClass()).debug(sql);
		Logger.getLogger(getClass()).debug(sql);
		int Level = 0;
		int Levelup = 0;
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			if (rs.next()) {
				Level = rs.getInt(1);
			}
			Levelup = Level + 1;
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (state != null)
				state.close();
			if (conn != null)
				conn.close();
		}
		return Levelup;
	}

	public String getDeptIDByParentID(String parentID, int parentLevel) {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		String currentID = null;
		try {
			String parentIDHead = parentID.substring(0, 1 + parentLevel * 2);
			String sql = "select max(DEPTID) from HR_DEPARTMENT where DEPTID like '"
					+ parentIDHead + "%'";
			conn = Conn();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			if (rs.next())
				currentID = rs.getString(1);
			String start = parentIDHead;
			String middle = String
					.valueOf(Integer.parseInt(currentID.substring(
							1 + parentLevel * 2, 1 + parentLevel * 2 + 2)) + 1);
			middle = middle.length() != 2 ? "0" + middle : middle;
			String end = "";
			for (int i = 0; i < 9 - start.length() - middle.length(); i++) {
				end = end + "0";
			}
			currentID = start + middle + end;
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, statement, conn);
		}
		return currentID;
	}

	public String getDeptIDByParentID_excess(String parentID, int parentLevel) {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		String currentID = null;
		String middle = null;
		try {
			String parentIDHead = parentID.substring(0, 1 + parentLevel * 2);
			String sql = "select max(DEPTID) from HR_DEPARTMENT where DEPTID like '"
					+ parentIDHead + "%'";
			conn = Conn();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			if (rs.next())
				currentID = rs.getString(1);
			String start = parentIDHead;
			if (currentID.equals(parentIDHead)) {
				middle = "01";
			} else {
				middle = String.valueOf(Integer.parseInt(currentID.substring(
						1 + parentLevel * 2, 1 + parentLevel * 2 + 2)) + 1);
				middle = middle.length() != 2 ? "0" + middle : middle;
			}
			currentID = start + middle;
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, statement, conn);
		}
		return currentID;
	}


	public String getPost_Group_Name(String empID) throws Exception {
		Connection conn = Conn();
		Statement state = null;
		ResultSet rs = null;
		String sql = "SELECT e.PostGroupName "
				+ "FROM hr_post_group e,HR_POST_COLUMN d, "
				+ "hr_post c,hr_emp_post b " + "WHERE b.empid='" + empID + "' "
				+ "AND b.postid=c.postid(+) "
				+ "AND c.PostColumnID=d.PostColumnID "
				+ "AND d.PostGroupID=e.PostGroupID";
		Logger.getLogger(getClass()).debug(sql);
		String post_Group_Name = "";
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			if (rs.next()) {
				post_Group_Name = rs.getString(1);
			}

			// usrName = rs.getString(1);

			// Logger.getLogger(getClass()).error("mnNamemnName" + mnName);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (state != null)
				state.close();
			if (conn != null) {
				conn.close();
			}
		}
		return post_Group_Name;
	}

	public String getPost_Group_ID(String empID) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT e.POSTGROUPID "
				+ "FROM hr_post_group e,HR_POST_COLUMN d,"
				+ "hr_post c,hr_emp_post b " + "WHERE b.empid='" + empID + "' "
				+ "AND b.postid=c.postid(+) "
				+ "AND c.PostColumnID=d.PostColumnID "
				+ "AND d.PostGroupID=e.PostGroupID";

		Logger.getLogger(getClass()).debug(sql);
		String post_Group_Name = "";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				post_Group_Name = rs.getString(1);
			}

		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
		return post_Group_Name;
	}

	public String getEmpEvaType(String empID) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT f.EVA_TYPE,g.BASICNAME FROM HR_POST_GROUP e,HR_POST_COLUMN d,HR_POST c,HR_EMP_POST b ,EV_POST_RL f,SY_CODE gWHERE b.empid=? AND b.postid=c.postid(+) AND c.PostColumnID=d.PostColumnID AND d.PostGroupID=e.PostGroupID AND e.POSTGROUPID(+)=f.POSTGROUP_IDAND f.EVA_TYPE=g.BASICCODE(+)ORDER BY f.orderno ASC";
		String evaName = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				evaName = rs.getString(2);
			}

			// usrName = rs.getString(1);

			// Logger.getLogger(getClass()).error("mnNamemnName" + mnName);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(
					e + sql + String.valueOf(evaName) + "-----getEmpEvaType");
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
		return evaName;
	}

	public String getMn_Stats(String field, String memcode) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select +field+ from efs_cmptblinfo where  mem_code=?";
		String memName = "";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memcode);
			rs = pstmt.executeQuery();
			rs.next();
			memName = rs.getString(1);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e + sql);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
		return memName;
	}

	public String getUserName(String userID) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select chinesename from HR_employee where empID=?";
		String deptName = "";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			rs.next();
			deptName = rs.getString(1);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e + sql);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
		return deptName;
	}

	public String getDeptNameUserID(String userID) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select DEPTNAME from HR_DEPARTMENT a,HR_employee b  where  a.DEPTID(+)=b.DEPTID and b.empID=?";
		String deptName = "";
		// Logger.getLogger(getClass()).error("userID-----" + userID);
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				deptName = rs.getString(1);
			}

		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e + sql);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
		return deptName;
	}

	public String getDeptName(String deptID) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select DEPTNAME from HR_DEPARTMENT where  DEPTID=?";
		String deptName = "";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptID);
			rs = pstmt.executeQuery();
			rs.next();
			deptName = rs.getString(1);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e + sql);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
		return deptName;
	}

	public String getEmpName(String Usr_Id) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select CHINESENAME from HR_EMPLOYEE where  EMPID=? ";
		// Logger.getLogger(getClass()).error("mnNamemnName" + Usr_Id);
		String UsrName = "";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Usr_Id);
			rs = pstmt.executeQuery();
			// UsrName = rs.getString(1);
			// Logger.getLogger(getClass()).error("mnNamemnName2" + UsrName);
			if (rs.next()) {
				UsrName = rs.getString(1);

			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e + sql);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();

			if (conn != null)
				conn.close();
		}
		return UsrName;
	}

	public String getlv1(String location) throws Exception {
		String locaiton1 = "";
		try {

			if (location.equals("AAAA")) {
				locaiton1 = "jdbc/lgdagu";
			}
			if (location.equals("BBAA")) {
				locaiton1 = "jdbc/lgyx";
			}
			if (location.equals("ACAA")) {
				locaiton1 = "jdbc/lgnbm";
			}
			if (location.equals("ADAA")) {
				locaiton1 = "jdbc/lgsg";
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e);
		}
		return locaiton1;
	}

	public String getDetail_Code_Name(String Detail_Code) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select detail_name from code_detail where  detail_code=?";
		String detail_code = "";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Detail_Code);
			rs = pstmt.executeQuery();
			rs.next();
			detail_code = rs.getString(1);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e + sql);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();

			if (conn != null)
				conn.close();
		}
		return detail_code;
	}

	public String getBasic_Name(int i, String no) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select BASIC_FL_NM" + i + " from board where  no=?";
		String File_Name = "";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(no));
			rs = pstmt.executeQuery();
			rs.next();
			File_Name = rs.getString(1);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e + sql);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();

			if (conn != null)
				conn.close();
		}
		return File_Name;
	}

	public int getAction_Value(String actionValue, String screenCode,
			String screenGrantNo) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select "
				+ actionValue
				+ " from SY_SCREEN_GRANT where SCREEN_CODE=? and SCREEN_GRANT_NO=?";
		int File_Name = 0;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, screenCode);
			pstmt.setString(2, screenGrantNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				File_Name = rs.getInt(1);
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).debug(e + sql);
		} finally {
		    SqlUtil.close(rs, pstmt, conn);
		}
		return File_Name;
	}

	public String getLOGINDEPTID(int LOGINNO) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT REPLACE(LOGINDEPTID,',',''',''') FROM SY_LOGIN_INFO WHERE LOGINNO=?";
		String loginDepID = "";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, LOGINNO);
			rs = pstmt.executeQuery();
			rs.next();
			loginDepID = rs.getString(1);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e + sql);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();

			if (conn != null)
				conn.close();
		}
		return loginDepID;
	}

	public String getDepth(String Menu_Code) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select Depth from board_mn where  menu_cd=?";
		String Depth = "";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(Menu_Code));
			rs = pstmt.executeQuery();
			rs.next();
			Depth = rs.getString(1);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e + sql);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();

			if (conn != null)
				conn.close();
		}
		return Depth;
	}

	public void doHit(int menuCode, int no, String deptCode, int usrId)
			throws Exception {
		String sql = "insert into efs_hits (menu_cd,no,hit_dt,dept_cd,usr_id) values(?,?,sysdate,?,?)";

		Connection conn = Conn();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, menuCode);
			pstmt.setInt(2, no);
			pstmt.setString(3, deptCode);
			pstmt.setInt(4, usrId);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.print(e + sql);

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}

	public String getMenuName(String ScreenCode) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MenuName FROM RightMenu WHERE (MenuCode = LEFT(?, 1))";
		String MenuName = "";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ScreenCode);
			rs = pstmt.executeQuery();
			rs.next();
			MenuName = rs.getString(1);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e + sql);
		} finally {

			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
		return MenuName;
	}

	public String getScreenName(String ScreenCode) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ScreenName FROM RightScreen WHERE (ScreenCode = ?)";
		String ScreenName = "";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ScreenCode);
			rs = pstmt.executeQuery();
			rs.next();
			ScreenName = rs.getString(1);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e + sql);
		} finally {

			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
		return ScreenName;
	}

	// 未完成
	/*
	 * public String getMnuUrl(int menucode) throws Exception { Connection conn =
	 * Conn(); PreparedStatement pstmt = null; ResultSet rs = null; String sql =
	 * "select }
	 */

	public String getSubMenu(int menucode) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String submenu1 = "<table border='0' cellpadding='2' cellspacing='1' class='table_bg' > <tr align='center' class='table_bg_white'>";
		String submenu = "";
		String sql = chkSubMenu(menucode);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menucode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				submenu = submenu
						+ "<td>&nbsp;</td><td class=table_header_center><a href="
						+ rs.getString(3) + "><font color='"
						+ getSubMenuCss(menucode, rs.getInt(2)) + "'>"
						+ isoStr(rs.getString(1)) + "</font></a></td>";
			}
			submenu = submenu + "</tr></table>";
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return submenu1 + submenu.substring(15);
	}

	public String chkSubMenu(int menucode) throws Exception {
		String sql = "select b.menu_name, b.menu_code,b.url from efs_menu a, efs_menu b where a.menu_code = ? and a.mn_parent_code = b.mn_parent_code and a.child_flag<>0";
		String rtSql = "";
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menucode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				rtSql = sql;
			} else {
				rtSql = "select menu_name, menu_code,url from efs_menu  where menu_code = ?";
			}

		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return rtSql;
	}

	public String getSubMenuCss(int menucode, int menucode2) throws Exception {
		if (menucode == menucode2) {
			return "#737700";
		} else {
			return "";
		}
	}

	public String chkNull(String str) {
		if (str == null) {
			str = "";
		}
		return str;

	}

	public boolean getCin_Cd(String Cin_Cd, String No) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT cin_cd FROM board_m WHERE no = ? ";
		boolean chk = false;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, No);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (Cin_Cd.equals("title")) {
					chk = true;
				}

			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return chk;
	}

	public String getTitle(String Title, String tablename, String field, int No)
			throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT " + Title + " FROM " + tablename + " WHERE "
				+ field + " = ? ";
		String title = "";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, No);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				title = rs.getString(1);

			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return title;
	}

	/**
	 * 判断是否有下一条记录
	 * 
	 * @param tablename
	 *            表名
	 * @param field
	 *            字段名
	 * @param No
	 *            当前记录编号
	 * @return 若有下一条记录则返回true，若无则返回false
	 * @throws java.lang.Exception
	 */
	public boolean isback(String tablename, String field, String Menu_Code,
			String No) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "
				+ tablename
				+ " WHERE "
				+ field
				+ " > ? and use_flag='1' and menu_cd=? and reply_flag='0' and (comment_flag is null or comment_flag<>'1') ORDER BY "
				+ field + " DESC";
		boolean chk = false;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(No));
			pstmt.setInt(2, Integer.parseInt(Menu_Code));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return chk;
	}

	/**
	 * 返回下一条记录的NO
	 * 
	 * @param tablename
	 *            表名
	 * @param field
	 *            字段名
	 * @param No
	 *            当前记录编号
	 * @return 返回数值为下一条记录的NO
	 * @throws java.lang.Exception
	 */
	public int next(String tablename, String field, int No) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT " + field + " FROM " + tablename + " WHERE "
				+ field + " > ? and use_flag='1'  ORDER BY " + field + "  ";
		int id = 0;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, No);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);

			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return id;
	}

	/**
	 * 判断是否有上一条记录
	 * 
	 * @param tablename
	 *            表名
	 * @param field
	 *            字段名
	 * @param No
	 *            字段名
	 * @return 若有上一条记录则返回true，若无则返回false
	 * @throws java.lang.Exception
	 */
	public boolean isnext(String tablename, String field, String Menu_Code,
			String No) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "
				+ tablename
				+ " WHERE "
				+ field
				+ " < ? and use_flag='1' and reply_flag='0' and menu_cd=? and (comment_flag is null or comment_flag<>'1') ORDER BY "
				+ field + " DESC";
		boolean chk = false;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(No));
			pstmt.setInt(2, Integer.parseInt(Menu_Code));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return chk;
	}

	/**
	 * 判断是否有上一条记录
	 * 
	 * @param tablename
	 *            表名
	 * @param field
	 *            字段名
	 * @param No
	 *            字段名
	 * @return 若有上一条记录则返回true，若无则返回false
	 * @throws java.lang.Exception
	 */
	public boolean isreply(String tablename, int No) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + tablename + " WHERE REPLY_NO= ? ";
		boolean chk = false;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, No);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return chk;
	}

	public boolean mayreply(String Menu_Code) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOARD_MN WHERE REPLY_STAT=1 AND MENU_CD= ? ";
		boolean chk = false;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Menu_Code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return chk;
	}

	public boolean maycomment(String Menu_Code) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOARD_MN WHERE COMMENT_STAT=1 AND MENU_CD= ? ";
		boolean chk = false;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Menu_Code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return chk;
	}

	public int isnew(String tablename, int No) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + tablename + " WHERE REPLY_NO= ? ";
		int chk = 0;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, No);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				chk = 1;
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return chk;
	}

	/**
	 * 返回上一条记录的NO
	 * 
	 * @param tablename
	 *            表名
	 * @param field
	 *            字段名
	 * @param No
	 *            当前记录编号
	 * @return 返回数值为上一条记录的NO
	 * @throws java.lang.Exception
	 */
	public int back(String tablename, String field, int No) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT " + field + " FROM " + tablename + " WHERE "
				+ field + " < ?  and use_flag='1'  ORDER BY " + field + " desc";
		int id = 0;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, No);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				// Logger.getLogger(getClass()).error("!!!!!mb"+id);

			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return id;
	}

	public int getUpNum(String menu_cd) throws Exception {

		Connection conn = Conn();
		// Logger.getLogger(getClass()).error("!!!!!m"+menu_cd);
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		String sql = "select upload_number from board_mn where menu_cd=?";
		int num = 0;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menu_cd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);

			}
			// Logger.getLogger(getClass()).error("!!!!!"+num);
		}

		catch (SQLException e) {
			Logger.getLogger(getClass()).error(e);
		} finally {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return num;
	}
}
