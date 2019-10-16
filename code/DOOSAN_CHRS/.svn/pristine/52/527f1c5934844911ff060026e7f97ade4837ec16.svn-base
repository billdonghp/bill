package com.ait.sy.sy0104.bean;

/**
 * <p>Title: AIT  INTRANET</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT</p>
 * @author AIT
 * @version 1.0
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.NotExistException;
import com.ait.utils.PageControl;
import com.ait.web.ApplicationContext;

public class Biz {

    Vector Vlist = new Vector(); // 产生Vector的对象Vlist

    Connection Conn = null; // 产生连接初始化的对象Conn

    int i = 0; // 初始化循环Vector的变量值

    HttpSession m_session = null;
    AdminBean admin= ApplicationContext.getTheadLocal();

    public Biz() {

    }

    // (select deptname from HR_DEPARTMENT where deptid=he2.deptid) as deptname
    // String LIST_SQL_0 = "SELECT ADMINNO, "
    // + " ADMINID, "
    // + " USERNAME, "
    // + " PASSWORD, "
    // + " LOGIN_DEPTID, "
    // + " SCREEN_GRANT_NO, "
    // + " ACTIVITY, "
    // + " SCREEN_GRANT_NO, "
    // + " (select chinesename from hr_employee where empid=adminid) as
    // chinesename, "
    // + " (select Screen_grant_name from SY_LOGIN_SCREEN where
    // screen_grant_no=s.SCREEN_GRANT_NO) as SCREEN_GRANT_Name, "
    // + " (select department from hr_employee_v where empid =s.adminid) as
    // deptname"
    // + " from SY_ADMIN s where ADMINNO<>0 ";

    // 查询语句重写 BY Pennix 20060402
    String LIST_SQL_0 = "SELECT distinct SY_ADMIN.ADMINNO, SY_ADMIN.ADMINID, SY_ADMIN.USERNAME, SY_ADMIN.PASSWORD"
            + ", SY_ADMIN.LOGIN_DEPTID, SY_ADMIN.SCREEN_GRANT_NO, SY_ADMIN.ACTIVITY"
            + ", HR_EMPLOYEE_V.CHINESENAME " 
            + ",sys_get_screen_name(SY_ADMIN.ADMINID) SCREEN_GRANT_NAME "
            + ", HR_EMPLOYEE_V.DEPARTMENT DEPTNAME"
            + ", duty"
            + ", HR_EMPLOYEE_V.chinese_pinyin"
            + ", HR_EMPLOYEE_V.DEPT_EN_NAME"  
            + ",sys_get_screen_en_name(SY_ADMIN.ADMINID) SCREEN_GRANT_EN_NAME," 
            + "  HR_EMPLOYEE_V.cpny_id, HR_EMPLOYEE_V.CPNYNAME "
            + " FROM SY_ADMIN, HR_EMPLOYEE_V "
            + " WHERE SY_ADMIN.ADMINID = HR_EMPLOYEE_V.person_id  AND  (HR_EMPLOYEE_V.date_left BETWEEN TO_DATE(TO_CHAR(add_months(SYSDATE, -1), 'YYYY-MM-DD'), 'YYYY-MM-DD') "+    
                  "  AND TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD') OR HR_EMPLOYEE_V.DATE_LEFT IS NULL)" +
                  "  AND  HR_EMPLOYEE_V.cpny_id='"+admin.getCpnyId()+"' ";

    String TREE_SQL = "WHERE MENU_CODE <> '00000' START WITH MENU_CODE=0000 CONNECT BY PRIOR MENU_CODE=MENU_PARENT_CODE";

    String UPDATE_SQL = "UPDATE SY_ADMIN SET" + " SCREEN_GRANT_NO = ?,"
            + " LOGIN_DEPTID = ?," + " UPDATED_BY = ?,UPDATE_DATE=sysDate" + " where ADMINNO = ? ";

    String DETAIL_SQL = " AND ADMINNO = ? AND  HR_EMPLOYEE_V.CPNY_ID=?";

    String DELETE_SQL = "DELETE SY_ADMIN WHERE ADMINNO = ?";

    String INSERT_SQL = " INSERT INTO SY_ADMIN (  	" 
	    + "			ADMINNO,			"
            + "			ADMINID,			" 
            + "			USERNAME,			" 
            + "		        PASSWORD,			"
            + "		        SCREEN_GRANT_NO,			" 
            + "		        LOGIN_DEPTID,			"
            + "                       CREATE_DATE,                     "
            + "                       CREATED_BY,                      "
            + "                       ACTIVITY)                        "
            + "		VALUES(SY_ADMIN_NO.NEXTVAL,?,?,?,?,?,sysDate,?,1)";

    // String ORDER_SQL = " ORDER BY ADMINNO desc";
    String ORDER_SQL = "ORDER BY SY_ADMIN.ADMINID desc";

    // /////////////////////////////////////////////////////////////////////////////////////////
    public Connection Conn() throws Exception {
        Connection Conn = null; // 建立参数声明
        try {
            Conn = ConnBean.getConn(); // 得到连接
        } catch (Exception e) {
            System.out.print("Conn Error:" + e);
        }
        return Conn;
    }

     
    public Vector Detail(String no,String cpnyid) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        try {
            pstmt = conn.prepareStatement(LIST_SQL_0 + DETAIL_SQL); // 执行select与排序语句
            Logger.getLogger(getClass()).debug(LIST_SQL_0 + DETAIL_SQL);
            pstmt.setInt(1, Integer.parseInt(no));
            pstmt.setString(2,cpnyid);
            rs = pstmt.executeQuery();
            while (rs.next()) { // 循环数据列表信息
                Ent Ent = new Ent();
                Ent.setLoginNo(rs.getInt(1));
                Ent.setEmpID(rs.getString(2));
                Ent.setUserID(rs.getString(3));
                Ent.setPassWord(rs.getString(4));
                Ent.setLoginDeptID(rs.getString(5));
                Ent.setScreenGrantno(rs.getString(6));
                Ent.setChineseName(rs.getString(7));
                Ent.setGrantName(rs.getString(8));
                Ent.setCpnyId(rs.getString("CPNY_ID"));
                Ent.setCpnyname(rs.getString("CPNYNAME"));
                Vlist.addElement(Ent); // 形成一条记录，插入记录列表
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }

    // 选出所有记录列表的sql语句
    public Vector List(PageControl pc) throws Exception {
        Connection conn = Conn();

        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集

        try {
            pc.seti();

            pstmt = conn.prepareStatement(LIST_SQL_0 + ORDER_SQL);

            rs = pstmt.executeQuery();
            for (int i = 0; i < pc.getI(); i++) {
                rs.next();
            }
            pc.setii();

            while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
                Ent Ent = new Ent();

                Ent.setLoginNo(rs.getInt(1));
                Ent.setEmpID(rs.getString(2));
                Ent.setUserID(rs.getString(3));
                Ent.setPassWord(rs.getString(4));

                Ent.setLoginDeptID(rs.getString(5));
                Ent.setScreenGrantno(rs.getString(6));
                Ent.setChineseName(rs.getString(7));
                Ent.setGrantName(rs.getString(8));

                Vlist.addElement(Ent);
                pc.setiii();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }

    public Vector ListSQL(PageControl pc, String SQL) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        try {
            pc.seti();
            pstmt = conn.prepareStatement(LIST_SQL_0 + SQL + ORDER_SQL);
            Logger.getLogger(getClass()).debug(LIST_SQL_0 + SQL + ORDER_SQL);
            rs = pstmt.executeQuery();
            for (int i = 0; i < pc.getI(); i++) {
                rs.next();
            }
            pc.setii();

            while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
                Ent ent = new Ent();
                ent.setLoginNo(rs.getInt(1));
                ent.setEmpID(rs.getString(2));
                ent.setUserID(rs.getString(3));
                ent.setPassWord(rs.getString(4));
                ent.setLoginDeptID(rs.getString(5));
                ent.setScreenGrantno(rs.getString(6));
                ent.setChineseName(rs.getString("CHINESENAME"));
                ent.setGrantName(rs.getString("SCREEN_GRANT_Name"));
                ent.setDepartMent(rs.getString("DEPTNAME"));
                ent.setGrantNo(rs.getString("SCREEN_GRANT_NO"));
		        ent.setDuty(rs.getString("duty"));
		        ent.setChinisenamePingYing(rs.getString("chinese_pinyin"));
		        ent.setGrantEnName(rs.getString("SCREEN_GRANT_EN_NAME"));        
		        ent.setDepartMentEnName(rs.getString("DEPT_EN_NAME"));  
		        ent.setCpnyId(rs.getString("CPNY_ID"));
		        ent.setCpnyname(rs.getString("CPNYNAME"));
                Vlist.addElement(ent);
                pc.setiii();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }

    //获取担当信息
    public Vector ListSQLUndertake(String affirmTypeId, String empid,String cpnyIdGz) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集

        String affirmTypeSql = "";
        String empidSql = "";
        String cpnyIdGzSql = "";
        
        if(!affirmTypeId.equals("")){
            affirmTypeSql=" AND AFFIRM_TYPE_ID='"+affirmTypeId+"'";
        }
        if(!empid.equals("")){
        	empidSql=" AND PERSON_ID='"+empid+"'";
        }
        if(!cpnyIdGz.equals("")){
        	cpnyIdGzSql=" AND COMFIRM_CPNYID='"+cpnyIdGz+"'";
        }
        
        try {
        	
        	String sql = " SELECT S.PERSON_ID, S.COMFIRM_CPNYID, S.AFFIRM_TYPE_ID,to_char(S.CREATE_DATE,'yyyy-mm-dd') CREATE_DATE,H.CHINESENAME,H.EMPID,"
        		+" C.CODE_NAME,A.ADMINNO FROM SY_COMFIRM_CPNYID S, HR_EMPLOYEE H, SY_CODE C,SY_ADMIN A "
        		+" WHERE S.PERSON_ID = H.PERSON_ID AND C.CODE_ID = S.AFFIRM_TYPE_ID AND S.PERSON_ID = A.ADMINID "
        		+affirmTypeSql
        		+empidSql
        		+cpnyIdGzSql;
        		//+" AND S.COMFIRM_CPNYID = ? AND S.PERSON_ID = ? AND S.AFFIRM_TYPE_ID = ? ";
        	Logger.getLogger(getClass()).debug(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Ent ent = new Ent();
		        ent.setPerson_id(rs.getString("PERSON_ID"));  
		        ent.setComfirm_cpnyid(rs.getString("COMFIRM_CPNYID"));
		        ent.setAffirm_type_id(rs.getString("AFFIRM_TYPE_ID"));
		        ent.setCreateDate(rs.getString("CREATE_DATE"));
		        ent.setChineseName(rs.getString("CHINESENAME"));
		        ent.setEmpID(rs.getString("EMPID"));
		        ent.setCode_name(rs.getString("CODE_NAME"));
		        ent.setLoginNo(rs.getInt("ADMINNO"));
                Vlist.addElement(ent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }
    
    public Vector ListSQLUndertakeOne(String cnpyId) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        try {
        	
        	String sql = " SELECT S.PERSON_ID, S.COMFIRM_CPNYID, S.AFFIRM_TYPE_ID,to_char(S.CREATE_DATE,'yyyy-mm-dd') CREATE_DATE,H.CHINESENAME,H.EMPID,"
        		+" C.CODE_NAME,A.ADMINNO FROM SY_COMFIRM_CPNYID S, HR_EMPLOYEE H, SY_CODE C,SY_ADMIN A "
        		+" WHERE S.PERSON_ID = H.PERSON_ID AND C.CODE_ID = S.AFFIRM_TYPE_ID AND S.PERSON_ID = A.ADMINID";
        		//+" AND S.COMFIRM_CPNYID = ? ";
        	Logger.getLogger(getClass()).debug(sql);
            pstmt = conn.prepareStatement(sql);
           // pstmt.setString(1, cnpyId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Ent ent = new Ent();
		        ent.setPerson_id(rs.getString("PERSON_ID"));  
		        ent.setComfirm_cpnyid(rs.getString("COMFIRM_CPNYID"));
		        ent.setAffirm_type_id(rs.getString("AFFIRM_TYPE_ID"));
		        ent.setCreateDate(rs.getString("CREATE_DATE"));
		        ent.setChineseName(rs.getString("CHINESENAME"));
		        ent.setEmpID(rs.getString("EMPID"));
		        ent.setCode_name(rs.getString("CODE_NAME"));
		        ent.setLoginNo(rs.getInt("ADMINNO"));
                Vlist.addElement(ent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }
    
    
    public void AddSQLUndertake(String personId,String cpnyId,String affirmTypeId) throws Exception {
    	Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        try {
        	
        	String sql = " insert into SY_COMFIRM_CPNYID (PERSON_ID, COMFIRM_CPNYID, AFFIRM_TYPE_ID, CREATE_DATE) "
        		+" values (?, ?, ?,sysdate) ";
        	Logger.getLogger(getClass()).debug(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, personId);
            pstmt.setString(2, cpnyId);
            pstmt.setString(3, affirmTypeId);
            pstmt.executeQuery();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
    }
    
    public String getAdminNo(String adminid) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String adminno = "";
        try {
            String SQL = "SELECT ADMINNO FROM SY_ADMIN WHERE ADMINID='"
                    + adminid + "'";
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                adminno = rs.getString("ADMINNO");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("cant execute family query", ex);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return adminno;
    }

    public void addGrantDeptid(String adminno, String[] dp) throws Exception {

        Connection conn = Conn();
        PreparedStatement pstmt = null;
        String SQL = "INSERT INTO SY_ADMIN_DEPTID (" + "    ADMIN_NO,"
                + "     ADMIN_DEPTID) " + "    VALUES(?,?)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, adminno);
            for (int i = 0; i < dp.length; i++) {
                pstmt.setString(2, dp[i]);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void deleteAdminDept(String no) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null;
        String SQL = "delete from SY_ADMIN_DEPTID where ADMIN_NO=? ";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, no);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }
    
    public void deleteAdminTypeDept(String no,String affTypeId) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null;
        String SQL = "delete from SY_COMFIRM_CPNYID s where s.person_id = "
        	+"(select adminid from SY_ADMIN where adminno = ?) and s.affirm_type_id = ? ";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, no);
            pstmt.setString(2, affTypeId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    //添加担当确认
    public void addGrantTypeDeptid(String adminno, String[] dp,String affirmtypeId) throws Exception {

        Connection conn = Conn();
        PreparedStatement pstmt = null;
        String SQL = " INSERT INTO SY_COMFIRM_CPNYID (PERSON_ID,AFFIRM_TYPE_ID,COMFIRM_CPNYID,CREATE_DATE)"
        	+" VALUES(?,?,?,sysdate)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, adminno);
            pstmt.setString(2, affirmtypeId);
            for (int i = 0; i < dp.length; i++) {
                pstmt.setString(3, dp[i]);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public void Insert(Ent ent) throws Exception {

        Connection conn = Conn();
        PreparedStatement pstmt = null;

        try {

            // pstmt = conn.prepareStatement(INSERT_SQL);
            pstmt = conn.prepareStatement(INSERT_SQL);
            pstmt.setString(1, ent.getEmpID());
            pstmt.setString(2, ent.getUserID());
            pstmt.setString(3, ent.getPassWord());
            pstmt.setString(4, ent.getScreenGrantno());
            pstmt.setString(5, ent.getLoginDeptid());
            pstmt.setString(6, ent.getCreatorID());

            if (pstmt.executeUpdate() == 0) {
                throw new NotExistException("No data exists");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void Delete(int No) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        try {
            pstmt = conn.prepareStatement(DELETE_SQL);
            pstmt.setInt(1, No);
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

    public void Update(Ent ent) throws Exception {

        Connection conn = Conn();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(UPDATE_SQL); // 执行更新数据方法
            pstmt.setString(1, ent.getScreenGrantno());
            pstmt.setString(2, ent.getLoginDeptid());
            pstmt.setString(3, ent.getModifierID());
            pstmt.setInt(4, ent.getLoginNo());

            if (pstmt.executeUpdate() == 0) {
                throw new NotExistException("Update No data exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    //
    public boolean checkName(String name) throws Exception {
        int flag = 0;
        Connection conn = Conn();
        ResultSet rs = null;
        String Oracle_Sql = "select count(*) as sySum from SY_ADMIN where userName='"
                + name + "'";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(Oracle_Sql);
            while (rs.next()) {
                flag = rs.getInt("sySum");
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        }
        if (flag > 0)
            return true;
        else
            return false;
    }

    public boolean checkName(String name, String empid) throws Exception {
        int flag = 0;
        Connection conn = Conn();
        ResultSet rs = null;
        String Oracle_Sql = "select count(*) as sySum from SY_ADMIN where userName='"
                + name + "' and adminid<>'" + empid + "'";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(Oracle_Sql);
            while (rs.next()) {
                flag = rs.getInt("sySum");
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        }
        if (flag > 0)
            return true;
        else
            return false;
    }

    //
    public boolean checkEmp(String empID) throws Exception {
        int flag = 0;
        Connection conn = Conn();
        ResultSet rs = null;
        String Oracle_Sql = "select count(*) as sySum from hr_employee where empID='"
                + empID + "'";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(Oracle_Sql);
            while (rs.next()) {
                flag = rs.getInt("sySum");
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        }
        if (flag > 0)
            return true;
        else
            return false;

    }

    //
    public boolean checkEmp_Admin(String empID) throws Exception {
        int flag = 0;
        Connection conn = Conn();
        ResultSet rs = null;
        String Oracle_Sql = "select count(*) as sySum from sy_admin where adminID='"
                + empID + "'";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(Oracle_Sql);
            while (rs.next()) {
                flag = rs.getInt("sySum");
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        }
        if (flag > 0)
            return true;
        else
            return false;

    }

    //
    public ArrayList getQuanxian(String no) throws Exception {
        ArrayList depts = new ArrayList();
        Connection conn = Conn();
        ResultSet rs = null;
        String Oracle_Sql = "select admin_deptid  from SY_ADMIN_DEPTID where admin_no='"
                + no + "'";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(Oracle_Sql);
            while (rs.next()) {
                depts.add(rs.getString("admin_deptid"));
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        }
        return depts;
    }
    
    //
    public ArrayList getTypeQuanxian(String adminNo,String affirmTypeId) throws Exception {
        ArrayList depts = new ArrayList();
        Connection conn = Conn();
        ResultSet rs = null;
        String affirmTypeSql = "";
        if(!affirmTypeId.equals("")){
            affirmTypeSql=" AND S.AFFIRM_TYPE_ID = '"+affirmTypeId+"'";
        }
        
        String Oracle_Sql = "select S.PERSON_ID, S.COMFIRM_CPNYID, S.AFFIRM_TYPE_ID,"
			+" to_char(S.CREATE_DATE,'yyyy-mm-dd') CREATE_DATE from SY_COMFIRM_CPNYID s"
			+" where s.person_id =  '"+adminNo+"'"
			+affirmTypeSql;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(Oracle_Sql);
            while (rs.next()) {
                depts.add(rs.getString("COMFIRM_CPNYID"));
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
        }
        return depts;
    }

}
