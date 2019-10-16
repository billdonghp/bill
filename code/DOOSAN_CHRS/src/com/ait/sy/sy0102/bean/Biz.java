package com.ait.sy.sy0102.bean;

/**
 * <p>
 * Title: AIT INTRANET
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: AIT
 * </p>
 * @author AIT
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.Func;
import com.ait.utils.NotExistException;
import com.ait.utils.PageControl;

public class Biz {

    private static final Logger log = Logger.getLogger(Biz.class);

    Vector Vlist = new Vector(); // 产生Vector的对象Vlist

    Connection Conn = null; // 产生连接初始化的对象Conn

    int i = 0; // 初始化循环Vector的变量值

    HttpSession m_session = null;

    Func func = new Func();

    public Biz() {   

    }

    String LIST_SQL_0 = "SELECT DEPTNO,                "
	    + "    DEPTID,			        "
	    + "    DEPTNAME,				"
	    + "    CPNY_ID,	  "
	    + "    to_char(DATE_CREATED,'YYYY-MM-DD'),			        "
	    + "    PARENT_DEPT_ID,			"
	    + "    DEPT_LEVEL,		 "
	    + "    to_char(CREATE_DATE,'YYYY-MM-DD'),	  "
	    + "    (select CHINESENAME from HR_EMPLOYEE e where e.EMPID=c.CREATED_BY), "
	    + "    UPDATE_DATE,				"
	    + "    UPDATED_BY,	  "
	    + "    ACTIVITY,			        "
	    + "    ORDERNO,				"
	    + "    to_char(DATE_ENDED,'YYYY-MM-DD'),				"
	    + "    (select DEPTNAME from HR_DEPARTMENT p where p.DEPTID=c.PARENT_DEPT_ID),"
	    + "    DEPT_EN_NAME,				"
	    + "	   (select CHINESE_PINYIN from HR_EMPLOYEE e where e.EMPID=c.CREATED_BY) AS CHINESE_PINYIN, "
	    + "	   (select ENGLISHNAME from HR_EMPLOYEE e where e.EMPID=c.CREATED_BY) AS ENGLISHNAME, "
	    + "	   (select KOREANNAME from HR_EMPLOYEE e where e.EMPID=c.CREATED_BY) AS KOREANNAME "
	    + "    from HR_DEPARTMENT c 	           ";  

    String WHERE_SQL = " where ACTIVITY<>0 ";

    String TREE_SQL = " CONNECT BY PRIOR DEPTID=PARENT_DEPT_ID  START WITH PARENT_DEPT_ID in (SELECT CPNY_ID FROM HR_COMPANY) ";

    String UPDATE_SQL = "UPDATE HR_DEPARTMENT SET "
	    + " DEPT_LEVEL = (SELECT HDP.DEPT_LEVEL FROM HR_DEPARTMENT HDP WHERE HDP.DEPTID = ?) + 1,"
	    + " DEPTNAME = ?, UPDATE_DATE = SYSDATE, UPDATED_BY = ?, PARENT_DEPT_ID = ?"
	    + " WHERE DEPTID = ?";  

    // 删除记录的sql语句，"?"代表参数变量
    String DELETE_SQL = "DELETE HR_DEPARTMENT WHERE deptid = ?";  

    String DETAIL_SQL = " WHERE DEPTID = ?";

    String INSERT_SQL = "INSERT INTO HR_DEPARTMENT ( "+
	      "	DEPTNO,DEPTID,DEPTNAME,DEPT_EN_NAME,PARENT_DEPT_ID,"+
	      "	CREATE_DATE,CREATED_BY,ACTIVITY, DATE_CREATED,"+
	      "	DEPT_LEVEL,CPNY_ID,ORDERNO)  "+
	     "  VALUES (HR_DEPT_SEQ.NEXTVAL,?,?,?,?,"+
	      "	sysDate,?,1,TO_DATE(?, 'YYYY-MM-DD'),"+
	      "	(SELECT DEPT_LEVEL+1 FROM HR_DEPARTMENT WHERE DEPTID=? ),"+
	     "	(SELECT CPNY_ID FROM HR_DEPARTMENT WHERE DEPTID=? ), "+
	      "	(SELECT NVL(MAX(ORDERNO)+1,1) FROM HR_DEPARTMENT))";   
    

    String ORDER_SQL = " START WITH PARENT_DEPT_ID IN "
	    + " (SELECT CPNY_ID FROM HR_COMPANY) "
	    + " CONNECT BY PRIOR deptid = parent_dept_id "
	    + " ORDER SIBLINGS BY orderno ";

    String LIST_MAN = " SELECT EMPID ,CHINESENAME,CHINESE_PINYIN,DEPTID FROM HR_EMPLOYEE WHERE DEPTID IN "
	    + "  (SELECT DEPTID FROM HR_DEPARTMENT CONNECT BY PRIOR DEPTID=PARENT_DEPT_ID START WITH DEPTID=? ) ";

    public Connection Conn() throws Exception {
	Connection Conn = null; // 建立参数声明
	try {
	    Conn = ConnBean.getConn(); // 得到连接
	} catch (Exception e) {
	    log.error(e.toString());
	}   
	return Conn;
    }

    // 选出所有记录列表的sql语句
    @SuppressWarnings("unchecked")
    public Vector List_M(PageControl pc, String key,String language) throws Exception {
	String SEARCH_WHERE = "";
	Connection conn = Conn();
	PreparedStatement pstmt = null; // 建立参数声明
	ResultSet rs = null; // 建立数据集
	if (key != null) {
	  
		 if(language.equals("zh"))
		 {
			 SEARCH_WHERE = " where deptid like '%"  
					+ key
					+ "%' or deptname like '%"
					+ key
					+ "%' ";   
		    LIST_SQL_0 += SEARCH_WHERE;   
		 }else
		 {                                                
			 SEARCH_WHERE = " where deptid like '%"                                     
					+ key
					+ "%' or DEPT_EN_NAME like '%"             
					+ key
					+ "%'";  
			 LIST_SQL_0 = "SELECT DEPTNO,                "
				    + "    DEPTID,			        "     
				    + "    DEPT_EN_NAME,				"   
				    + "    CPNY_ID,	  "
				    + "    to_char(DATE_CREATED,'YYYY-MM-DD'),			        "
				    + "    PARENT_DEPT_ID,			"
				    + "    DEPT_LEVEL,		 "
				    + "    to_char(CREATE_DATE,'YYYY-MM-DD'),	  "
				    + "    (select CHINESENAME from HR_EMPLOYEE e where e.EMPID=c.CREATED_BY), "
				    + "    UPDATE_DATE,				"
				    + "    UPDATED_BY,	  "
				    + "    ACTIVITY,			        "
				    + "    ORDERNO,				"
				    + "    to_char(DATE_ENDED,'YYYY-MM-DD'),				"
				    + "    (select DEPT_EN_NAME from HR_DEPARTMENT p where p.DEPTID=c.PARENT_DEPT_ID), "
				    + "	   (select CHINESE_PINYIN from HR_EMPLOYEE e where e.EMPID=c.CREATED_BY) AS CHINESE_PINYIN, "
				    + "	   (select ENGLISHNAME from HR_EMPLOYEE e where e.EMPID=c.CREATED_BY) AS ENGLISHNAME, "
				    + "	   (select KOREANNAME from HR_EMPLOYEE e where e.EMPID=c.CREATED_BY) AS KOREANNAME "
				    + "    from HR_DEPARTMENT c 	        ";
			 
			 LIST_SQL_0 += SEARCH_WHERE;   	                 
		 }                     
	}     
	try {
	    pc.seti();
	    Logger.getLogger(getClass()).debug(LIST_SQL_0 + ORDER_SQL);
	    pstmt = conn.prepareStatement(LIST_SQL_0 + ORDER_SQL);
	    rs = pstmt.executeQuery();
	    for (int i = 0; i < pc.getI(); i++) {
		rs.next();
	    }           
	    pc.setii();

	    while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
		Ent Ent = new Ent();
		Ent.setDeptNo(rs.getInt(1));
		Ent.setDeptID(rs.getString(2));
		Ent.setDeptName(rs.getString(3));
		Ent.setCompanyID(rs.getString(4));
		Ent.setCreatedDate(rs.getString(5));
		Ent.setParentDeptNo(rs.getString(6));
		Ent.setDeptLevel(rs.getInt(7));
		Ent.setCreateDate(rs.getString(8));
		Ent.setCreatorID(rs.getString(9));
		Ent.setModifyDate(rs.getString(10));
		Ent.setModifierID(rs.getString(11));
		Ent.setActivity(rs.getInt(12));
		Ent.setOrderNo(rs.getInt(13));
		Ent.setEndEddate(rs.getString(14));
		Ent.setParentDeptName(rs.getString(15));
		Ent.setChiesePinYin(rs.getString("CHINESE_PINYIN")) ;
		Ent.setEn_name(rs.getString("ENGLISHNAME")) ;
		Ent.setKor_name(rs.getString("KOREANNAME")) ;
		Vlist.addElement(Ent);
		pc.setiii();
	    }
	} catch (SQLException e) {
		e.printStackTrace() ;
	    log.error(e.toString());
	}       

	finally {
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

    // 选出所有记录列表的sql语句
    @SuppressWarnings("unchecked")
    public Vector List_M(PageControl pc) throws Exception {
	// String SEARCH_WHERE = "";
	String sql = LIST_SQL_0 + ORDER_SQL;
	log.debug(sql);
	Connection conn = Conn();

	PreparedStatement pstmt = null; // 建立参数声明
	ResultSet rs = null; // 建立数据集

	try {
	    pc.seti();

	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();
	    for (int i = 0; i < pc.getI(); i++) {
		rs.next();
	    }
	    pc.setii();

	    while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
		Ent Ent = new Ent();
		Ent.setDeptNo(rs.getInt(1));
		Ent.setDeptID(rs.getString(2));
		Ent.setDeptName(rs.getString(3));
		Ent.setCompanyID(rs.getString(4));
		Ent.setCreatedDate(rs.getString(5));
		Ent.setParentDeptNo(rs.getString(6));
		Ent.setDeptLevel(rs.getInt(7));
		Ent.setCreateDate(rs.getString(8));
		Ent.setCreatorID(rs.getString(9));
		Ent.setModifyDate(rs.getString(10));
		Ent.setModifierID(rs.getString(11));  
		Ent.setActivity(rs.getInt(12));
		Ent.setOrderNo(rs.getInt(13));
		Ent.setEndEddate(rs.getString(14));
//		Ent.setDeptcode(rs.getString(15));
		Ent.setParentDeptName(rs.getString(16));
		Vlist.addElement(Ent);
		pc.setiii();

	    }
	} catch (SQLException e) {
	    log.error(e.toString());
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

    @SuppressWarnings("unchecked")
    public Vector List_Emp(String DeptID, PageControl pc) throws Exception {
	// String SEARCH_WHERE = "";
	Connection conn = Conn();

	PreparedStatement pstmt = null; // 建立参数声明
	ResultSet rs = null; // 建立数据集

	try {
	    pc.seti();
	    
	    pstmt = conn.prepareStatement(LIST_MAN);
	    pstmt.setString(1, DeptID);
	    rs = pstmt.executeQuery();
	    for (int i = 0; i < pc.getI(); i++) {
		rs.next();
	    }
	    pc.setii();

	    while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
		Ent Ent = new Ent();
		Ent.setEmpID(rs.getString(1));
		Ent.setChineseName(rs.getString(2));
		Ent.setChiesePinYin(rs.getString(3));
		Ent.setDeptID(rs.getString(4));

		Vlist.addElement(Ent);
		pc.setiii();

	    }
	} catch (SQLException e) {
	    log.error(e.toString());
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

    @SuppressWarnings("unchecked")
    public Vector List_Waggie(int loginNo) throws Exception {

	Connection conn = Conn();

	String LIST_WAGGIE = "SELECT DEPTNO,  "
		+ " DEPTID,DEPTNAME,CPNY_ID,DATE_CREATED,PARENT_DEPT_ID,DEPT_LEVEL,"
		+ " CREATE_DATE,CREATED_BY,UPDATE_DATE,UPDATED_BY, "
		+ " ACTIVITY,ORDERNO, DATE_ENDED, checked "
		+ " from (SELECT HR_DEPARTMENT_SY_TREE_V.*,CASE WHEN DEPTID IN ( "
		+ " select ADMIN_DEPTID from SY_ADMIN_DEPTID where ADMIN_NO = ? "
		+ "  ) THEN 'checked' ELSE '' END AS CHECKED FROM HR_DEPARTMENT_SY_TREE_V) ";

	PreparedStatement pstmt = null; // 建立参数声明
	ResultSet rs = null; // 建立数据集

	try {

	    pstmt = conn.prepareStatement(LIST_WAGGIE + TREE_SQL);
	    pstmt.setInt(1, loginNo);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		Ent Ent = new Ent();
		Ent.setDeptNo(rs.getInt(1));
		Ent.setDeptID(rs.getString(2));
		Ent.setDeptName(rs.getString(3));
		Ent.setCompanyID(rs.getString(4));
		Ent.setCreatedDate(rs.getString(5));
		Ent.setParentDeptNo(rs.getString(6));
		Ent.setDeptLevel(rs.getInt(7));
		Ent.setCreateDate(rs.getString(8));
		Ent.setCreatorID(rs.getString(9));
		Ent.setModifyDate(rs.getString(10));
		Ent.setModifierID(rs.getString(11));
		Ent.setActivity(rs.getInt(12));
		Ent.setOrderNo(rs.getInt(13));
		Ent.setEndEddate(rs.getString(14));
		Ent.setChecked(rs.getString(15));

		Vlist.addElement(Ent);

	    }
	} catch (SQLException e) {
	    log.error(e.toString());
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

    @SuppressWarnings("unchecked")
    public Vector List() throws Exception {

	Connection conn = Conn();

	PreparedStatement pstmt = null; // 建立参数声明
	ResultSet rs = null; // 建立数据集

	try {

	    pstmt = conn.prepareStatement(LIST_SQL_0);

	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		Ent Ent = new Ent();
		Ent.setDeptNo(rs.getInt(1));
		Ent.setDeptID(rs.getString(2));
		Ent.setDeptName(rs.getString(3));
		Ent.setCompanyID(rs.getString(4));
		Ent.setCreatedDate(rs.getString(5));
		Ent.setParentDeptNo(rs.getString(6));
		Ent.setDeptLevel(rs.getInt(7));
		Ent.setCreateDate(rs.getString(8));
		Ent.setCreatorID(rs.getString(9));
		Ent.setModifyDate(rs.getString(10));
		Ent.setModifierID(rs.getString(11));
		Ent.setActivity(rs.getInt(12));
		Ent.setOrderNo(rs.getInt(13));
		Ent.setEndEddate(rs.getString(14));
//		Ent.setDeptcode(rs.getString(15));
		Ent.setParentDeptName(rs.getString(15));
		Vlist.addElement(Ent);
	    }
	} catch (SQLException e) {
	    log.error(e.toString());
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

    public void Insert(Ent Ent) throws Exception {

	Connection conn = Conn();
	PreparedStatement pstmt = null;
	try {
	    pstmt = conn.prepareStatement(INSERT_SQL);
	    int i = 0;
	    pstmt.setString(++i, Ent.getDeptcode());
	    pstmt.setString(++i, Ent.getDeptName());
	    pstmt.setString(++i, Ent.getDeptEnName()); 
	    pstmt.setString(++i, Ent.getParentDeptNo());
	    pstmt.setString(++i, Ent.getCreatorID());
	    pstmt.setString(++i, Ent.getCreatedDate());
	    pstmt.setString(++i, Ent.getParentDeptNo());
	    pstmt.setString(++i, Ent.getParentDeptNo());  

	    if (pstmt.executeUpdate() == 0) {                 
		throw new NotExistException("No data exists");
	    }
	} catch (SQLException e) {
	    log.error(e.toString());
	} finally {
	    if (pstmt != null) {
		pstmt.close();
	    }
	    if (conn != null) {
		conn.close();
	    }
	}
    }

    // 返回搜索结果

    @SuppressWarnings("unchecked")
    public Vector Detail(String No) throws Exception {
    	log.debug("No1:"+No);
	Connection conn = Conn();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Vlist.clear();
	try {
	    pstmt = conn.prepareStatement(LIST_SQL_0 + DETAIL_SQL); // 执行详细信息语句
	    if (log.isDebugEnabled()) {
		log.debug(LIST_SQL_0 + DETAIL_SQL);
	    }
	    pstmt.setString(1, No); // 为详细信息的?即详细信息的条件赋值
	    rs = pstmt.executeQuery(); // 得到信息数据集
	    while (rs.next()) { // 依次得到值
		Ent Ent = new Ent(); // 产生实体对象
		// 按照上面搜索信息依次附值，以下方法如下
		Ent.setDeptNo(rs.getInt(1));
		Ent.setDeptID(rs.getString(2));
		Ent.setDeptName(rs.getString(3));
		Ent.setCompanyID(rs.getString(4));
		Ent.setCreatedDate(rs.getString(5));
		Ent.setParentDeptNo(rs.getString(6));
		Ent.setDeptLevel(rs.getInt(7));
		Ent.setCreateDate(rs.getString(8));
		Ent.setCreatorID(rs.getString(9));
		Ent.setModifyDate(rs.getString(10));
		Ent.setModifierID(rs.getString(11));
		Ent.setActivity(rs.getInt(12));
		Ent.setOrderNo(rs.getInt(13));
		Ent.setEndEddate(rs.getString(14));    
		Ent.setParentDeptName(rs.getString(15));
		Ent.setDeptEnName(rs.getString(16));
		Vlist.addElement(Ent); // 形成一条记录，插入记录列表
	    }
	} catch (SQLException e) {
	    log.error(e.toString());
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

    // 删除信息方法
    public void Delete(String No) throws Exception {
	Connection conn = Conn();
	PreparedStatement pstmt = null; // 建立参数声明
	try {
	    pstmt = conn.prepareStatement(DELETE_SQL);
	    pstmt.setString(1, No);
	    if (pstmt.executeUpdate() == 0) { // 调用上面形成的DELETE语句，为?即参数变量赋值
		throw new NotExistException("No data exists");
	    }
	} catch (SQLException e) {    
	    log.error(e.toString());
	} finally {
	    if (pstmt != null) {
		pstmt.close();
	    } // 关闭参数声明
	    if (conn != null) {
		conn.close();
	    } // 关闭参数声明
	}

    }

    public String Update(Ent Ent) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String  message ="It is invalid to put superior level beneath the subordinate level!";
	try {
	    conn = Conn();
	    int j = 0;
	    String sqlcheck=" select ((select dept_level from hr_department where deptid=?)-"+
        " (select dept_level from hr_department where deptid=?) ) "+
        " from hr_department where deptid=?";
	    pstmt=conn.prepareStatement(sqlcheck);  
	    pstmt.setString(++j, Ent.getDeptID());   
	    pstmt.setString(++j, Ent.getParentDeptNo());  
	    pstmt.setString(++j, Ent.getDeptID());
	    ResultSet rs= pstmt.executeQuery();                                  
	    String num="";
	    if(rs.next())                                    
	    {
	    num =rs.getString(1);           
	    }  
	    if(!num.contains("-")&&!num.equals("0"))//判断是否为负数
	   {
	    conn.setAutoCommit(false);  
	    String sql = " UPDATE HR_DEPARTMENT SET "
		    + " DEPT_LEVEL = (SELECT HDP.DEPT_LEVEL + 1 FROM HR_DEPARTMENT HDP WHERE HDP.DEPTID = ?),"
		    + " DEPTNAME = ?,DEPT_EN_NAME=?, UPDATE_DATE = SYSDATE, UPDATED_BY = ?, PARENT_DEPT_ID = ?," 
		    + " DATE_ENDED = TO_DATE(?,'YYYY-MM-DD')  "
		    + " WHERE DEPTID = ?";              
	    Logger.getLogger(getClass()).debug(sql);      
	    //更新相关部门等级　子部门等级=子部门旧等级+(部门新等级-部门旧等级)                
	    StringBuffer sql2 = new StringBuffer();  
	    sql2.append(" UPDATE HR_DEPARTMENT　");  
	    sql2.append(" SET ");                                                
	    sql2.append(" DEPT_LEVEL=DEPT_LEVEL+ ");
	    sql2.append(" ((SELECT DEPT_LEVEL + 1 FROM HR_DEPARTMENT WHERE DEPTID = ? ) ");
	    sql2.append(" 	-(SELECT DEPT_LEVEL FROM HR_DEPARTMENT WHERE DEPTID=? ))");
	    sql2.append("　WHERE DEPTID IN ( 	");
	    sql2.append("　	SELECT   deptid　");
	    sql2.append("　	FROM hr_department　");
	    sql2.append("　	WHERE deptid <> ?　");                                             
	    sql2.append("　	START WITH deptid = ? ");
	    sql2.append("　	CONNECT BY PRIOR deptid = parent_dept_id )");
	    Logger.getLogger(getClass()).debug(sql2.toString());

	    pstmt = conn.prepareStatement(sql2.toString()); // 执行更新数据方法
	    int i = 0;
	    pstmt.setString(++i, Ent.getParentDeptNo());  
	    pstmt.setString(++i, Ent.getDeptID());
	    pstmt.setString(++i, Ent.getDeptID());
	    pstmt.setString(++i, Ent.getDeptID());
	    pstmt.executeUpdate();

	    pstmt = null;
	    pstmt = conn.prepareStatement(sql); // 执行更新数据方法
	    i = 0;
	    
	    pstmt.setString(++i, Ent.getParentDeptNo());
	    pstmt.setString(++i, Ent.getDeptName());
	    pstmt.setString(++i, Ent.getDeptEnName());
	    pstmt.setString(++i, Ent.getModifierID());
	    pstmt.setString(++i, Ent.getParentDeptNo());
	    pstmt.setString(++i, Ent.getEndEddate());
	    pstmt.setString(++i, Ent.getDeptID());
	    if (pstmt.executeUpdate() == 0) {
		Logger.getLogger(getClass()).debug(" Update No data exists ");
		
	    }
	    message="";
	    conn.commit();           
	  }
                 
	 } catch (Exception e) {        
	    Logger.getLogger(getClass()).debug(e.toString());
	    e.printStackTrace();
	    try {
		conn.rollback();
	    } catch (SQLException sqle) {
		Logger.getLogger(getClass()).debug(sqle.toString());
		sqle.printStackTrace();
	    }
	   } finally {
	    SqlUtil.close(pstmt, conn);
	}
	return message;      
    }

    //  选出所有记录列表的sql语句
    @SuppressWarnings("unchecked")
    public Vector ListByParentNo(String parentNo) throws Exception {
	String SEARCH_WHERE = "";
	String ORDER = "";
	Connection conn = Conn();
	PreparedStatement pstmt = null; // 建立参数声明
	ResultSet rs = null; // 建立数据集

	SEARCH_WHERE = " WHERE c.parent_dept_id =? ";
	ORDER = " ORDER BY c.ORDERNO ";
	try {
	    Logger.getLogger(getClass()).debug(
		    LIST_SQL_0 + SEARCH_WHERE + ORDER);
	    pstmt = conn.prepareStatement(LIST_SQL_0 + SEARCH_WHERE + ORDER);
	    pstmt.setString(1, parentNo);
	    rs = pstmt.executeQuery();

	    while (rs.next()) {
		Ent Ent = new Ent();
		Ent.setDeptNo(rs.getInt(1));
		Ent.setDeptID(rs.getString(2));
		Ent.setDeptName(rs.getString(3));
		Ent.setCompanyID(rs.getString(4));
		Ent.setCreatedDate(rs.getString(5));
		Ent.setParentDeptNo(rs.getString(6));
		Ent.setDeptLevel(rs.getInt(7));
		Ent.setCreateDate(rs.getString(8));
		Ent.setCreatorID(rs.getString(9));
		Ent.setModifyDate(rs.getString(10));
		Ent.setModifierID(rs.getString(11));
		Ent.setActivity(rs.getInt(12));
		Ent.setOrderNo(rs.getInt(13));
		Ent.setEndEddate(rs.getString(14));  
		Ent.setParentDeptName(rs.getString(15));              
		Ent.setDeptEnName(rs.getString(16));
		Vlist.addElement(Ent);
	    }
	} catch (SQLException e) {
	    log.error(e.toString());
	}

	finally {
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

    /**
     * 更新部门序号
     * @param lEnt
     */
    public void updateOrder(List lEnt) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	try {
	    conn = Conn();
	    conn.setAutoCommit(false);

	    StringBuffer sql = new StringBuffer();
	    sql.append(" UPDATE HR_DEPARTMENT　");
	    sql.append(" SET ");
	    sql.append(" ORDERNO=? ");
	    sql.append(" WHERE DEPTID= ? ");
	    Logger.getLogger(getClass()).debug(sql.toString());
	    if (lEnt != null) {
		pstmt = conn.prepareStatement(sql.toString()); // 执行更新数据方法

		int i = 0;
		for (int m = 0, n = lEnt.size(); m < n; m++) {
		    Ent ent = (Ent) lEnt.get(m);
		    i = 0;
		    pstmt.setInt(++i, ent.getOrderNo());
		    pstmt.setString(++i, ent.getDeptID());
		    pstmt.addBatch();
		}
		pstmt.executeBatch();
		conn.commit();
	    }   
	} catch (Exception e) {
	    Logger.getLogger(getClass()).error(e.toString());
	    e.printStackTrace();
	    try {
		conn.rollback();
	    } catch (SQLException sqle) {
		Logger.getLogger(getClass()).error(sqle.toString());
		sqle.printStackTrace();
	    }
	} finally {
	    SqlUtil.close(pstmt, conn);
	}
    }

}