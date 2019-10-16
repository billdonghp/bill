package com.ait.utils;

/**
 * <p>Title: AIT  HOMEPAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT </p>
 * @author AIT
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class MenuBiz {

	Vector Vlist = new Vector(); // 产生Vector的对象Vlist

	Connection Conn = null; // 产生连接初始化的对象Conn

	int i = 0; // 初始化循环Vector的变量值

	/**
	 * 
	 * 
	 * @param screengrantno
	 * @return
	 */

	public Vector getMenuTree(String menu_code, int depth, String screengrantno) {

		Connection conn = ConnBean.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Vector vMenuData = new Vector();
		String sql = "";
		sql = "select * from sy_menu WHERE exists " + "(SELECT screen_code FROM SY_SCREEN_GRANT " + "WHERE sy_screen_grant.screen_grant_no in (" + screengrantno + ")" + " AND sy_screen_grant.screen_code=sy_menu.menu_code) and depth='" + depth + "'" + " start with menu_code='"
				+ menu_code + "'" + " connect by prior menu_code=menu_parent_code" + " order by depth,menu_parent_code";
		try {
			pst = conn.prepareStatement(sql);

			rs = pst.executeQuery();
			while (rs != null && rs.next()) {

				MenuEnt ent = new MenuEnt();
				ent.setMenuIntro(rs.getString("MENU_INTRO"));
				ent.setMenuParentCode(rs.getString("MENU_PARENT_CODE"));
				ent.setDepth(rs.getInt("DEPTH"));
				ent.setMenuCode(rs.getString("MENU_CODE"));
				ent.setMenuUrl(rs.getString("MENU_URL"));
				vMenuData.addElement(ent);
			}
		} catch (SQLException e) {

			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, pst, conn);

		}
		return vMenuData;
	}

	/**
	 * 根据权限组号取得一级屏幕号
	 * @param screengrantno
	 * @return
	 */
	public Vector getTopMenu(String screengrantno) {
		Vector v = new Vector();
		screengrantno = screengrantno.replaceAll(",", "','");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM sy_menu ");
		sql.append("	WHERE exists ");
		sql.append("	(");
		sql.append("		SELECT screen_code FROM SY_SCREEN_GRANT ");
		sql.append("			WHERE sy_screen_grant.screen_grant_no in ('" + screengrantno + "')");
		sql.append("			AND sy_screen_grant.screen_code=sy_menu.menu_code ");
		sql.append("	)");
		sql.append("	AND sy_menu.depth=0 AND ACTIVITY = 1");
		sql.append("	ORDER BY  sy_menu.ORDERNO ");

		Logger.getLogger(getClass()).debug("get top sql: " + sql.toString());

		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		try {

			conn = ConnBean.getConn();

			state = conn.createStatement();
			rs = state.executeQuery(sql.toString());
			while (rs != null && rs.next()) {
				MenuEnt ent = new MenuEnt();
				ent.setMenuIntro(StringUtil.checkNull(rs.getString("MENU_INTRO")));
				ent.setMenuEnIntro(StringUtil.checkNull(rs.getString("MENU_EN_INTRO")));
				ent.setMenuKorIntro(StringUtil.checkNull(rs.getString("MENU_KOR_INTRO")));
				ent.setMenuParentCode(rs.getString("MENU_PARENT_CODE"));
				ent.setDepth(rs.getInt("DEPTH"));
				ent.setMenuCode(rs.getString("MENU_CODE"));
				ent.setMenuUrl(rs.getString("MENU_URL"));
				v.addElement(ent);
			}
		} catch (SQLException e) {

			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, state, conn);
		}
		return v;
	}

	public ArrayList toolMenuList(String menucode, String rodelevel) throws Exception {
		ArrayList toolMenu = new ArrayList();
		String[] temp = rodelevel.split(",");
		String toolmenu_sql = "SELECT " + "SELECTR,INSERTR,UPDATER,DELETER " + "FROM SY_SCREEN_GRANT WHERE SCREEN_CODE=? and SCREEN_GRANT_NO=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(toolmenu_sql);
			pstmt.setString(1, menucode);

			for (int i = 0; i < temp.length; i++) {
				pstmt.setString(2, temp[i].toString());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					toolMenu.add(this.createtoolmenu(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Detail Error:" + e);
			return null;
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return toolMenu;
	}

	private ToolMenu createtoolmenu(ResultSet rs) {
		ToolMenu toolmenu = new ToolMenu();
		try {
			toolmenu.setSelect((rs.getInt("SELECTR") != 0) ? rs.getInt("SELECTR") : 0);
			toolmenu.setInsertr((rs.getInt("INSERTR") != 0) ? rs.getInt("INSERTR") : 0);
			toolmenu.setUpdate((rs.getInt("UPDATER") != 0) ? rs.getInt("UPDATER") : 0);
			toolmenu.setDelect((rs.getInt("DELETER") != 0) ? rs.getInt("DELETER") : 0);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return toolmenu;
	}

	public ArrayList thirdmenulist(String menuCode, String screengrantno) {
		ArrayList thirdMenu = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		/*
		 * 改正错误:不同的权限组具有同一个menu的权限会导致查询出多条,并且在页面上显示多个菜单
		 * 李再军
		 * 2006-9-12
		 */
		String sql = "SELECT distinct SY_MENU.MENU_INTRO,SY_MENU.MENU_EN_INTRO,SY_MENU.MENU_KOR_INTRO,SY_MENU.MENU_PARENT_CODE, SY_MENU.DEPTH, " + "SY_MENU.MENU_CODE, SY_MENU.MENU_URL, " + "MAX(NVL(SY_SCREEN_GRANT.INSERTR, 0)) INSERTR, " + "MAX(NVL(SY_SCREEN_GRANT.UPDATER, 0)) UPDATER, "
				+ "MAX(NVL(SY_SCREEN_GRANT.DELETER, 0)) DELETER,SY_MENU.ORDERNO " + "FROM SY_MENU, SY_SCREEN_GRANT " + "WHERE SY_MENU.MENU_CODE = SY_SCREEN_GRANT.SCREEN_CODE(+) " + " AND SY_MENU.ACTIVITY = "+"'1'"+" AND SY_SCREEN_GRANT.SCREEN_GRANT_NO IN ('" + screengrantno.replaceAll(",", "','") + "') "
				+ "AND SY_MENU.MENU_PARENT_CODE = (SELECT MENU_PARENT_CODE FROM SY_MENU WHERE MENU_CODE = ?) " + "GROUP BY SY_MENU.MENU_INTRO,SY_MENU.MENU_EN_INTRO,SY_MENU.MENU_KOR_INTRO, SY_MENU.MENU_PARENT_CODE, SY_MENU.DEPTH, " + "SY_MENU.MENU_CODE, SY_MENU.MENU_URL, " + "SY_SCREEN_GRANT.SCREEN_CODE, SY_MENU.ORDERNO "
				+ "ORDER BY SY_MENU.ORDERNO, SY_MENU.MENU_CODE";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menuCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MenuEnt menuEnt = new MenuEnt();
				menuEnt.setMenuIntro(StringUtil.checkNull(rs.getString("MENU_INTRO")));
				menuEnt.setMenuEnIntro(StringUtil.checkNull(rs.getString("MENU_EN_INTRO")));
				menuEnt.setMenuKorIntro(StringUtil.checkNull(rs.getString("MENU_KOR_INTRO")));				
				menuEnt.setMenuParentCode(rs.getString("MENU_PARENT_CODE"));
				menuEnt.setDepth(rs.getInt("DEPTH"));
				menuEnt.setMenuCode(rs.getString("MENU_CODE"));
				menuEnt.setMenuUrl(rs.getString("MENU_URL"));
				menuEnt.setInsertr(rs.getInt("INSERTR"));
				menuEnt.setUpdater(rs.getInt("UPDATER"));
				menuEnt.setDeleter(rs.getInt("DELETER"));

				//if (thirdMenu.indexOf(menuEnt) == -1) {
				thirdMenu.add(menuEnt);
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return thirdMenu;
	}

	// 产生信息列表方法
	public Vector MenuList(String Role_ID) throws Exception {

		String RightMenu_SQL = "SELECT  " + " b.menu_intro," + " b.menu_parent_code, " + " b.depth,      " + " b.menu_code, " + " b.menu_url " + " FROM sy_menu b " + "	WHERE b.menu_code " + "			IN (SELECT a.screen_code FROM sy_screen_grant a WHERE a.screen_grant_no IN ("
				+ Role_ID + ",0) )" + " START WITH b.depth='0' CONNECT BY PRIOR  b.menu_code = b.menu_parent_code " + " ORDER SIBLINGS BY b.orderno,b.menu_code ASC ";

		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(RightMenu_SQL); // 执行详细信息语句
			rs = pstmt.executeQuery(); // 得到信息数据集
			while (rs.next()) { // 依次得到值
				MenuEnt Ent = new MenuEnt(); // 产生实体对象
				Ent.setMenuIntro(rs.getString(1));
				Ent.setMenuParentCode(rs.getString(2));
				Ent.setDepth(rs.getInt(3));
				Ent.setMenuCode(rs.getString(4));
				Ent.setMenuUrl(rs.getString(5));
				Vlist.addElement(Ent); // 形成一条记录，插入记录列表
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug("获取屏幕列表错误", e);
			throw new GlRuntimeException("获取屏幕列表错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return Vlist; // 返回记录列表
	}

	public ArrayList thirdmenulist2(String menuparent, String screengrantno) {
		ArrayList thirdMenu = null;
		screengrantno = screengrantno.replaceAll(",", "','");
		
		String sql = "	SELECT A.MENU_INTRO, A.MENU_PARENT_CODE, A.DEPTH, A.MENU_CODE,NVL(E.MENU_URL,D.MENU_URL), "
					+ " A.MENU_EN_INTRO, A.MENU_KOR_INTRO,E.MENU_URL,D.MENU_URL "
					+ " FROM SY_MENU A, "
					+ " (SELECT ROW_NUMBER() OVER(PARTITION BY B.MENU_PARENT_CODE ORDER BY MENU_CODE) ROW_NUM, "
		   		    + " B.MENU_INTRO, B.MENU_CODE, B.MENU_URL, B.MENU_PARENT_CODE "
		   		    + " FROM SY_MENU B "
		   		    + " WHERE B.MENU_CODE IN (SELECT DISTINCT C.SCREEN_CODE FROM SY_SCREEN_GRANT C WHERE C.SCREEN_GRANT_NO IN ('" + screengrantno + "')) "
		   		    + " AND B.DEPTH = 2) D, "
		   		    + " (SELECT B.MENU_CODE,B.MENU_URL, B.MENU_PARENT_CODE "
		   		    + " FROM SY_MENU B "
		   		    + " WHERE B.MENU_CODE IN (SELECT DISTINCT C.SCREEN_CODE FROM SY_SCREEN_GRANT C WHERE C.SCREEN_GRANT_NO IN ('" + screengrantno + "')) "
		   		    + " AND B.DEPTH = 2 ) E  "
		   		    + " WHERE A.MENU_PARENT_CODE = '" + menuparent + "' "
		   		    + " AND A.MENU_CODE = D.MENU_PARENT_CODE "
		   		    + " AND D.ROW_NUM = 1 "
		   		    + " AND A.MENU_URL = E.MENU_URL(+) " 
		   		    + " AND A.ACTIVITY = 1 "
		   		    + " ORDER BY A.ORDERNO,A.MENU_CODE " ;
		
		
//		String sql = "select b.MENU_INTRO, b.MENU_PARENT_CODE, b.DEPTH, b.MENU_CODE, " 
//				+ "(SELECT SM.MENU_URL FROM SY_MENU SM  "
//				+ " WHERE SM.MENU_PARENT_CODE = B.MENU_CODE AND SM.MENU_CODE IN (select a.SCREEN_CODE "
//                + " from SY_SCREEN_GRANT a where a.SCREEN_GRANT_NO in ('" + screengrantno + "') ) AND ROWNUM = 1) AS MENU_URL, " 
//				+ "b.MENU_EN_INTRO, b.MENU_KOR_INTRO from SY_MENU b where b.MENU_PARENT_CODE ='" + menuparent + "' and b.MENU_CODE in(select a.SCREEN_CODE from SY_SCREEN_GRANT a "
//				+ "where a.SCREEN_GRANT_NO in('" + screengrantno + "')) START WITH b.depth='1' CONNECT BY PRIOR b.MENU_CODE = b.MENU_PARENT_CODE ORDER SIBLINGS BY b.orderno,b.menu_code ASC ";

		Logger.getLogger(getClass()).debug("get second menu sql: " + sql.toString());
		
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			thirdMenu = new ArrayList();
			while (rs != null && rs.next()) {
				thirdMenu.add(this.createthirdmenu(rs));
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, state, conn);
		}
		return thirdMenu;
	}

	private MenuEnt createthirdmenu(ResultSet rs) {
		MenuEnt thirdmenu = new MenuEnt();
		try {
			thirdmenu.setMenuIntro(StringUtil.checkNull(rs.getString(1)));
			thirdmenu.setMenuParentCode(rs.getString(2));
			thirdmenu.setDepth(rs.getInt(3));
			thirdmenu.setMenuCode(rs.getString(4));
			thirdmenu.setMenuUrl(rs.getString(5));
			thirdmenu.setMenuEnIntro(StringUtil.checkNull(rs.getString(6)));
			thirdmenu.setMenuKorIntro(StringUtil.checkNull(rs.getString(7)));
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return thirdmenu;
	}
}
