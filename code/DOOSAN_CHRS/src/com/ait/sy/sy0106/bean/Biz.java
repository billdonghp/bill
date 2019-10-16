package com.ait.sy.sy0106.bean;

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

    String LIST_SQL_0 = "SELECT grantEmpNo,                "
            + "    cnpyID,			        " + "    empID,				" + "    decTypeID,	  "
            + "    decLevelID,			        "
            + "    to_char(createDate,'YYYYMMDD'),	  "
            + "    creatorId,			        "
            + "    to_char(modifyDate,'YYYYMMDD'),				" + "    modifierId,	  "
            + "    activity,			        " + "    orderNo,				"
            + "    decFlag from sy_menu 	        ";

    String UPDATE_SQL = "UPDATE sy_menu SET" + " cnpyID = ?," + " empID = ?,"
            + " decTypeID =?," + " decLevelID = ?" + " modifyDate = ?"
            + " modifierId = ?" + " activity = ?" + " orderNo = ?"
            + " decFlag = ?" + " WHERE grantEmpNo = ?";

    // 删除记录的sql语句，"?"代表参数变量
    String DELETE_SQL = "DELETE sy_menu WHERE grantEmpNo = ?";

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
    public Vector List(PageControl pc) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        try {
            pc.seti();
            pstmt = conn.prepareStatement(LIST_SQL_0); // 执行select与排序语句
            rs = pstmt.executeQuery();
            for (int i = 0; i < pc.getI(); i++) {
                rs.next();
            }
            pc.setii();
            while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) { // 循环数据列表信息
                Ent Ent = new Ent(); // 产生实体对象
                Ent.setGrantEmpNo(rs.getInt(1));
                Ent.setCnpyID(rs.getString(2));
                Ent.setEmpID(rs.getString(3));
                Ent.setDecTypeID(rs.getString(4));
                Ent.setDecLevelID(rs.getString(5));
                Ent.setCreateDate(rs.getString(6));
                Ent.setCreatorID(rs.getString(7));
                Ent.setModifyDate(rs.getString(8));
                Ent.setModifierID(rs.getString(9));
                Ent.setActivity(rs.getInt(10));
                Ent.setOrderNo(rs.getInt(11));
                Ent.setDecFlag(rs.getInt(12));
                Vlist.addElement(Ent); // 形成一条记录，插入记录列表
                pc.setiii();
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }

    public void Insert(Ent Ent) throws Exception {

        Connection conn = Conn();
        PreparedStatement pstmt = null;

        String INSERT_SQL = " INSERT INTO sy_menu (  	"
                + "			grantEmpNo,				"
                + "			cnpyID,			"
                + "			empID,			"
                + "			decTypeID,		"
                + "                  decLevelID,              "
                + "                  createDate,              "
                + "                  creatorID,               "
                + "                  activity,                "
                + "                  orderNo,                 "
                + "			decFlag)			"
                + "		VALUES(grantEmpNo_SEQ.NEXTVAL,?,?,?,?,sysDate,?,1,orderNo_SEQ.NEXTVAL,?)";
        try {
            pstmt = conn.prepareStatement(INSERT_SQL);
            pstmt.setString(1, Ent.getCnpyID());
            pstmt.setString(2, Ent.getEmpID());
            pstmt.setString(3, Ent.getDecTypeID());
            pstmt.setString(4, Ent.getDecLevelID());
            pstmt.setString(5, Ent.getCreatorID());
            pstmt.setInt(6, Ent.getDecFlag());
            if (pstmt.executeUpdate() == 0) {
                throw new NotExistException("No data exists");
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug("fail  " + e.toString());
        } finally {
            SqlUtil.close(pstmt, conn);
        }
    }

    // 返回搜索结果

    // 删除信息方法
    public void Delete(int No) throws Exception {
        Connection conn = Conn();
        PreparedStatement pstmt = null; // 建立参数声明
        try {
            pstmt = conn.prepareStatement(DELETE_SQL);
            pstmt.setInt(1, No);
            if (pstmt.executeUpdate() == 0) // 调用上面形成的DELETE语句，为?即参数变量赋值
                throw new NotExistException("No data exists");
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
            SqlUtil.close(pstmt, conn);
        }

    }

    public void Update(Ent Ent) throws Exception {

        Connection conn = Conn();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(UPDATE_SQL); // 执行更新数据方法
            Logger.getLogger(getClass()).debug(UPDATE_SQL);
            pstmt.setString(1, Ent.getCnpyID());
            pstmt.setString(2, Ent.getEmpID());
            pstmt.setString(3, Ent.getDecTypeID());
            pstmt.setString(4, Ent.getDecLevelID());
            pstmt.setString(5, Ent.getModifyDate());
            pstmt.setString(6, Ent.getModifierID());
            pstmt.setInt(7, Ent.getActivity());
            pstmt.setInt(8, Ent.getOrderNo());
            pstmt.setInt(9, Ent.getDecFlag());
            pstmt.setInt(10, Ent.getGrantEmpNo());
            if (pstmt.executeUpdate() == 0) {
                throw new NotExistException("Update No data exists");
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug("Update Error:" + e.toString());
        } finally {
            SqlUtil.close(pstmt, conn);
        }

    }

    // 执行插入数据方法

}
