package com.ait.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class GetReportGroup {
    private static ServiceLocator services;

    public GetReportGroup() {
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException ex) {
        }
    }

    Vector Vlist = new Vector(); // 产生Vector的对象Vlist

    Connection Conn = null; // 产生连接初始化的对象Conn

    int i = 0; // 初始化循环Vector的变量值

    // 返回连接
    public Connection Conn() throws Exception {
        Connection Conn = null;// 建立参数声明
        try {
            Conn = services.getConnection(); // 得到连接
        } catch (Exception e) {
            System.out.print("Conn Error:" + e);
        }
        return Conn;
    }

    // 返回全部记录，用于没有分页和搜索的情况
    public Vector DataSelect(String SELECT_SQL) throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)

        Connection conn = Conn(); // 建立连接
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        
        try {

            pstmt = conn.prepareStatement(SELECT_SQL);
            rs = pstmt.executeQuery(); // 得到信息数据集

            while (rs.next()) { 
                Vlist.add(StringUtil.checkNull(rs.getString("SUBDEPT")));
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

    public String getDeptid (String deptname) throws Exception
    {
    	Connection conn = Conn(); // 建立连接
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        String deptid = "";
        String sql = "";
        sql = "SELECT DEPTID FROM ar_subdept_v WHERE SUBDEPT = '"+deptname+"'" ;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); // 得到信息数据集
            while(rs.next()){
            	deptid = rs.getString("DEPTID");
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
      return deptid;
    }

    
    public Vector data;

    // Get
    public Vector getData() {
        return data;
    }

    // Set
    public void setData(Vector data) {
        this.data = data;
    }

    // entity

    public String editNull(String s) {
        if (s == null) {
            return "";
        } else {
            return s;
        }
    }

}
