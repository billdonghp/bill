package com.ait.sy.sy0105.bean;

/**
 * <p>Title: AIT  INTRANET</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT</p>
 * @author AIT
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.NotExistException;
import com.ait.utils.PageControl;

public class Biz {

    Vector Vlist = new Vector(); // 产生Vector的对象Vlist

    Connection Conn = null; // 产生连接初始化的对象Conn

    int i = 0; // 初始化循环Vector的变量值

    HttpSession m_session = null;

    public Biz() {

    }

    String INSERT_SQL_NAME = " INSERT INTO SY_LOGIN_SCREEN (  	"
            + "			SCREEN_GRANT_ID,				" 
            + "			SCREEN_GRANT_NO,				"
            + "			SCREEN_GRANT_NAME,              " 
            + "			SCREEN_GRANT_EN_NAME,	        "
            + "          CPNY_ID,	              "
            + "          CREATE_DATE,	              "
            + "          CREATED_BY	    )          "
            + "		VALUES(SY_screenGrantName_Seq.NEXTVAL,?,?,?,?,sysDate,?)";     

    String UPDATE_SQL_NAME = " UPDATE  SY_LOGIN_SCREEN SET	"
            + "			SCREEN_GRANT_NO = ?,				"
            + "			SCREEN_GRANT_NAME = ?,			                       "
            + "			UPDATE_DATE = sysDate,			                       "
            + "			UPDATED_BY = ?			                       "
            + "		        WHERE SCREEN_GRANT_ID=?";

    String LIST_SQL_02 = "SELECT SY_LOGIN_SCREEN.SCREEN_GRANT_NO,               "
            + "    SY_LOGIN_SCREEN.SCREEN_GRANT_NAME,				"
            + "    SY_LOGIN_SCREEN.SCREEN_GRANT_ID,                      "
            + "    to_char(SY_LOGIN_SCREEN.CREATE_DATE, 'YYYYMMDD') createdate,	        "
            + "    SY_LOGIN_SCREEN.SCREEN_GRANT_EN_NAME,       "
            + "    sy_code.code_name cpnyname  "
            + "    from SY_LOGIN_SCREEN ,sy_code	        ";

    String LIST_SQL_0 = "SELECT SCREEN_GRANT_NO,                "
            + "    SCREEN_CODE,				" + "    SELECTR,	                       "
            + "    INSERTR,	                       "
            + "    UPDATER,                          "
            + "    DELETER,                          "
            + "    to_char(CREATE_DATE,'YYYYMMDD'),  "
            + "    CREATED_BY		                "
            + "    from SY_SCREEN_GRANT 	        ";

    String WHERE_SQL = "where SY_LOGIN_SCREEN.Cpny_Id=sy_code.code_id(+) and SCREEN_GRANT_NO=?";

    String UPDATE_SQL = "UPDATE SY_SCREEN_GRANT SET" + " SCREEN_GRANT_NO = ?,"
            + " SCREEN_CODE = ?," + " SELECTR = ?," + " INSERTR = ?"
            + " UPDATER = ?" + " DELETER = ?" + " UPDATE_DATE = ?,"
            + " UPDATED_BY = ?,"
            // + " orderNo = ?,"
            + " WHERE SCREEN_GRANT_NO = ?";

    String INSERT_SQL = " INSERT INTO SY_SCREEN_GRANT (  	"
            + "SCREEN_GRANT_NO," + " SCREEN_CODE," + "SELECTR," + "INSERTR,"
            + "UPDATER," + "DELETER," + "CREATE_DATE,"+ "CREATED_BY, code_type)"
            + "VALUES(?,?,?,?,?,?,sysDate,?,?)";

    // 删除记录的sql语句，"?"代表参数变量
    String DELETE_SQL = "DELETE SY_LOGIN_SCREEN WHERE SCREEN_GRANT_NO = ?";

    public Connection Conn() throws Exception {
        Connection Conn = null; // 建立参数声明
        try {
            Conn = ConnBean.getConn(); // 得到连接
        } catch (Exception e) {
            System.out.print("Conn Error:" + e);
        }
        return Conn;
    }

    // 选出所有记录列表的sql语句
    public Vector List(PageControl pc, String cpnyId) throws Exception {
        Connection conn = Conn();

        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集

        String ORDER_SQL = "  ORDER BY  to_number(SCREEN_GRANT_NO) ";

        try {
            pc.seti();

            if(cpnyId==null || cpnyId.equals("")){
            	pstmt = conn.prepareStatement(LIST_SQL_02 + " WHERE  SY_LOGIN_SCREEN.Cpny_Id=sy_code.code_id(+) and  SCREEN_GRANT_NO<>'101' " + ORDER_SQL); // 执行select与排序语句
            }else{
            	pstmt = conn.prepareStatement(LIST_SQL_02 + " WHERE  SY_LOGIN_SCREEN.Cpny_Id=sy_code.code_id(+) and SY_LOGIN_SCREEN.Cpny_Id='"+ cpnyId +"' and  SCREEN_GRANT_NO<>'101' " + ORDER_SQL); // 执行select与排序语句
            }

            rs = pstmt.executeQuery();
            for (int i = 0; i < pc.getI(); i++) {
                rs.next();
            }
            pc.setii();

            while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) { // 循环数据列表信息
                Ent Ent = new Ent(); // 产生实体对象
                Ent.setScreenGrantNo(rs.getString(1));
                Ent.setScreenGrantName(rs.getString(2));
                Ent.setCreateDate(rs.getString(4));
                Ent.setScreenGrant_Seq(rs.getInt(3));             
                Ent.setScreenGrantEnName(rs.getString(5));  
                Ent.setCpnyname(rs.getString("cpnyname"));
                Vlist.addElement(Ent); // 形成一条记录，插入记录列表
                pc.setiii();
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null) {
                rs.close();
            } // 关闭数据集
            if (pstmt != null) {
                pstmt.close();
            } // 关闭参数声明
            if (conn != null) {
                conn.close();
            } // 关闭连接
        }
        return Vlist; // 返回记录列表
    }

    public Vector Screen_List(PageControl pc, String no) throws Exception {
        Connection conn = Conn();

        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集

        String ORDER_SQL = " ORDER BY SCREEN_GRANT_NO";

        try {
            pc.seti();

            pstmt = conn.prepareStatement(LIST_SQL_0 + WHERE_SQL + ORDER_SQL); // 执行select与排序语句
            pstmt.setInt(1, Integer.parseInt(no));
            rs = pstmt.executeQuery();
            for (int i = 0; i < pc.getI(); i++) {
                rs.next();
            }
            pc.setii();

            while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) { // 循环数据列表信息
                Ent Ent = new Ent(); // 产生实体对象
                Ent.setScreenGrantNo(rs.getString(1));
                Ent.setScreenCode(rs.getString(2));
                Ent.setSelectr(rs.getString(3));
                Ent.setInsertr(rs.getString(4));
                Ent.setDeleter(rs.getString(4));

                Vlist.addElement(Ent); // 形成一条记录，插入记录列表
                pc.setiii();

            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null) {
                rs.close();
            } // 关闭数据集
            if (pstmt != null) {
                pstmt.close();
            } // 关闭参数声明
            if (conn != null) {
                conn.close();
            } // 关闭连接
        }
        return Vlist; // 返回记录列表
    }

    public Vector Detail(String no) throws Exception {
        Connection conn = Conn();

        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集

        try {

            pstmt = conn.prepareStatement(LIST_SQL_02 + WHERE_SQL); // 执行select与排序语句
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();

            while (rs.next()) { // 循环数据列表信息
                Ent Ent = new Ent(); // 产生实体对象
                Ent.setScreenGrantNo(rs.getString(1));
                Ent.setScreenGrantName(rs.getString(2));
                Ent.setScreenGrant_Seq(rs.getInt(3));
                Ent.setCreatorID(rs.getString(4));
                Ent.setScreenGrantEnName(rs.getString(5));
                Ent.setCpnyname(rs.getString(6));
                Vlist.addElement(Ent); // 形成一条记录，插入记录列表

            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null) {
                rs.close();
            } // 关闭数据集
            if (pstmt != null) {
                pstmt.close();
            } // 关闭参数声明
            if (conn != null) {
                conn.close();
            } // 关闭连接
        }
        return Vlist; // 返回记录列表
    }

    public Vector menulist() throws Exception {
        Vector vector = new Vector();
        Connection conn = Conn();
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {
            pstmt1 = conn.prepareStatement("select MENU_CODE from SY_MENU");
            rs = pstmt1.executeQuery();
            while (rs.next()) {
                vector.addElement(rs.getString("MENU_CODE"));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	SqlUtil.close(rs, pstmt1, conn);
        }
        return vector;
    }

    public void delectname(String screenGrantNo_O) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;

        try {
            pstmt = conn
                    .prepareStatement("delete SY_SCREEN_GRANT where SCREEN_GRANT_NO =?");
            pstmt.setString(1, screenGrantNo_O);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("fail  " + e + INSERT_SQL);
        } finally {
            if (rs != null) {
                rs.close();
            } // 关闭数据集
            if (pstmt1 != null) {
                pstmt1.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public void updatename(Ent ent) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(UPDATE_SQL_NAME); // 执行更新数据方法
            Logger.getLogger(getClass()).debug(UPDATE_SQL_NAME);
            pstmt.setString(1, ent.getScreenGrantNo());
            pstmt.setString(2, ent.getScreenGrantName());
            pstmt.setString(3, ent.getCreatorID());
            pstmt.setInt(4, ent.getScreenGrant_Seq());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Update Error:" + ent);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public void Insertname(Ent ent) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {

            pstmt = conn.prepareStatement(INSERT_SQL_NAME);
            pstmt.setString(1, ent.getScreenGrantNo());
            pstmt.setString(2, ent.getScreenGrantName());
            pstmt.setString(3, ent.getScreenGrantEnName());
            pstmt.setString(4, ent.getCpnyID());
            pstmt.setString(5, ent.getCreatorID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("fail  " + e + INSERT_SQL_NAME);
        } finally {
            if (rs != null) {
                rs.close();
            } // 关闭数据集
            if (pstmt1 != null) {
                pstmt1.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public void Insert(Ent Ent, Vector menu_vector) throws Exception {

        Connection conn = Conn();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null; // 建立屏幕数据集

        String ScreenCode;
        String InsertRt;
        String UpdateRt;
        String DeleteRt;
        String ForeRt;
        StringTokenizer IR;
        StringTokenizer UR;
        StringTokenizer DR;
        StringTokenizer FR;

        try {
            for (int i = 0; i < menu_vector.size(); i++) {
                ScreenCode = (String) menu_vector.elementAt(i);

                InsertRt = "0"; // 默认插入权限无
                IR = new StringTokenizer(Ent.getInsertr(), ","); // 形成数组
                while (IR.hasMoreTokens()) { // InsertR 存在
                    if (IR.nextToken().equals(ScreenCode)) { // 某InsertR为该ScreenCode
                        InsertRt = "1"; // 则有插入权限
                    }
                }

                UpdateRt = "0"; // 默认修改权限无
                UR = new StringTokenizer(Ent.getUpdater(), ","); // 形成数组
                while (UR.hasMoreTokens()) { // UpdateR 存在
                    if (UR.nextToken().equals(ScreenCode)) { // 某UpdateR为该ScreenCode
                        UpdateRt = "1"; // 则有修改权限
                    }
                }

                DeleteRt = "0"; // 默认修改权限无
                DR = new StringTokenizer(Ent.getDeleter(), ","); // 形成数组
                while (DR.hasMoreTokens()) { // DeleteR 存在
                    if (DR.nextToken().equals(ScreenCode)) { // 某DeleteR为该ScreenCode
                        DeleteRt = "1"; // 则有修改权限
                    }
                }

                ForeRt = "0"; // 默认前台查看权限无
                FR = new StringTokenizer(Ent.getSelectr(), ","); // 形成数组
                while (FR.hasMoreTokens()) { // ForeR 存在
                    if (FR.nextToken().equals(ScreenCode)) { // 某ForeR为该ScreenCode
                        ForeRt = "1"; // 则有前台查看权限
                    }
                }

                if (InsertRt.equals("1") || UpdateRt.equals("1")
                        || DeleteRt.equals("1") || ForeRt.equals("1")) { // 该屏幕至少有一种操作被选择，则插入该屏幕

                    pstmt = conn.prepareStatement(INSERT_SQL);
                    pstmt.setString(1, Ent.getScreenGrantNo());

                    pstmt.setString(2, ScreenCode);
                    pstmt.setString(3, ForeRt);
                    pstmt.setString(4, InsertRt);
                    pstmt.setString(5, UpdateRt);
                    pstmt.setString(6, DeleteRt);
                    pstmt.setString(7, Ent.getCreatorID());
                    pstmt.setString(8, Ent.getCode());
                    if (pstmt.executeUpdate() == 0) {
                        throw new NotExistException("No data exists");
                    }
                }

            }

        } catch (SQLException e) {
            System.out.print("fail  " + e + INSERT_SQL);
        } finally {
            if (rs != null) {
                rs.close();
            } // 关闭数据集
            if (pstmt1 != null) {
                pstmt1.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // 返回搜索结果

    // 删除信息方法
    public void Delete(String No) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        try {
            pstmt = conn.prepareStatement(DELETE_SQL);
            pstmt.setString(1, No);
            if (pstmt.executeUpdate() == 0) // 调用上面形成的DELETE语句，为?即参数变量赋值
                throw new NotExistException("No data exists");
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            } // 关闭参数声明
            if (conn != null) {
                conn.close();
            } // 关闭参数声明
        }

    }

    public void Updata(Ent Ent, Vector menu_vector) throws Exception {

        Connection conn = Conn();
        PreparedStatement pstmt = null;
        // PreparedStatement pstmt1 = null;
        String ScreenCode;
        String InsertRt;
        String UpdateRt;
        String DeleteRt;
        String ForeRt;
        StringTokenizer IR;
        StringTokenizer UR;
        StringTokenizer DR;
        StringTokenizer FR;
        try {
            for (int i = 0; i < menu_vector.size(); i++) {
                ScreenCode = (String) menu_vector.elementAt(i);
                InsertRt = "0"; // 默认插入权限无
                IR = new StringTokenizer(Ent.getInsertr(), ","); // 形成数组
                while (IR.hasMoreTokens()) { // InsertR 存在
                    if (IR.nextToken().equals(ScreenCode)) { // 某InsertR为该ScreenCode
                        InsertRt = "1"; // 则有插入权限
                    }
                }
                UpdateRt = "0"; // 默认修改权限无
                UR = new StringTokenizer(Ent.getUpdater(), ","); // 形成数组
                while (UR.hasMoreTokens()) { // UpdateR 存在
                    if (UR.nextToken().equals(ScreenCode)) { // 某UpdateR为该ScreenCode
                        UpdateRt = "1"; // 则有修改权限
                    }
                }
                DeleteRt = "0"; // 默认修改权限无
                DR = new StringTokenizer(Ent.getDeleter(), ","); // 形成数组
                while (DR.hasMoreTokens()) { // DeleteR 存在
                    if (DR.nextToken().equals(ScreenCode)) { // 某DeleteR为该ScreenCode
                        DeleteRt = "1"; // 则有修改权限
                    }
                }
                ForeRt = "0"; // 默认前台查看权限无
                FR = new StringTokenizer(Ent.getSelectr(), ","); // 形成数组
                while (FR.hasMoreTokens()) { // ForeR 存在
                    if (FR.nextToken().equals(ScreenCode)) { // 某ForeR为该ScreenCode
                        ForeRt = "1"; // 则有前台查看权限
                    }
                }
                if (InsertRt.equals("1") || UpdateRt.equals("1")
                        || DeleteRt.equals("1") || ForeRt.equals("1")) { // 该屏幕至少有一种操作被选择，则插入该屏幕
                    pstmt = conn.prepareStatement(INSERT_SQL);
                    pstmt.setString(1, Ent.getScreenGrantNo());
                    pstmt.setString(2, ScreenCode);
                    pstmt.setString(3, ForeRt);
                    pstmt.setString(4, InsertRt);
                    pstmt.setString(5, UpdateRt);
                    pstmt.setString(6, DeleteRt);
                    pstmt.setString(7, Ent.getCreatorID());
                    pstmt.setString(8, Ent.getCode());
                    if (pstmt.executeUpdate() == 0) {
                        throw new NotExistException("No3 data exists");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(pstmt, conn);
        }

    }

    // 执行插入数据方法
    public boolean checkNo(int screenGrantNo) throws Exception {
        int flag = 0;
        Connection conn = Conn();
        ResultSet rs = null;
        Statement stmt = null;
        String Oracle_Sql = "select count(*) as sySum from Sy_login_Screen where screen_grant_no="
                + screenGrantNo;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Oracle_Sql);
            while (rs.next()) {
                flag = rs.getInt("sySum");
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null) 
            	stmt.close();
            if (conn != null)
                conn.close();
        }
        if (flag > 0)
            return true;
        else
            return false;

    }

    public int Updata(String screenGrantNo_O, List entList) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement psdel = null;
        try {
            conn = Conn();
            conn.setAutoCommit(false);
            String sql = "DELETE FROM SY_SCREEN_GRANT WHERE SCREEN_GRANT_NO = ?";
            psdel = conn.prepareStatement(sql);
            psdel.setString(1, screenGrantNo_O);
            psdel.executeUpdate();
            sql = "INSERT INTO SY_SCREEN_GRANT (SCREEN_CODE, SCREEN_GRANT_NO, SELECTR, INSERTR, UPDATER, DELETER, UPDATE_DATE, UPDATED_BY, code_type)"
                    + " VALUES (?, ?, ?, ?, ?, ?, sysdate, ?, ?)";
            com.ait.sy.sy0101.bean.Ent screenEnt = new com.ait.sy.sy0101.bean.Ent();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < entList.size(); i++) {
                screenEnt = (com.ait.sy.sy0101.bean.Ent) entList.get(i);
                if (screenEnt.getSelectr() == 1 || screenEnt.getInsertr() == 1
                        || screenEnt.getUpdater() == 1
                        || screenEnt.getDeleter() == 1) {
                    ps.setString(1, screenEnt.getMenuCode());
                    Logger.getLogger(getClass()).debug(screenEnt.getMenuCode());
                    ps.setString(2, screenGrantNo_O);
                    ps.setInt(3, screenEnt.getSelectr());
                    ps.setInt(4, screenEnt.getInsertr());
                    ps.setInt(5, screenEnt.getUpdater());
                    ps.setInt(6, screenEnt.getDeleter());
                    ps.setString(7, screenEnt.getCreatorID());
                    ps.setString(8, screenEnt.getCode());
                    if (ps.executeUpdate() != 1){
                        conn.rollback();
                        return 0;
                    }
                }
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            if (psdel != null) {
                psdel.close();
            } // 关闭参数声明
            if (ps != null) {
                ps.close();
            } // 关闭参数声明
            if (conn != null) {
                conn.close();
            } // 关闭参数声明
        }

        return 1;
    }
}
