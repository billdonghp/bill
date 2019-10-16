package com.ait.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;

public class ActivityReport {
	private static ServiceLocator services;
	private final static Logger logger = Logger.getLogger(ActivityReport.class);

	public ActivityReport() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException ex) {
		}
	}
	
	private int[] sqlTypes;

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
		sqlTypes = new int[0];
		logger.debug(SELECT_SQL);
		ActivityReport report = null;
		Connection conn = Conn(); // 建立连接
		PreparedStatement pstmt = null; // 建立参数声明
		ResultSet rs = null; // 建立数据集
		ResultSetMetaData rsmd = null;
		Vector data_v = null;
		try {
			report = new ActivityReport();
			pstmt = conn.prepareStatement(SELECT_SQL);
			rs = pstmt.executeQuery(); // 得到信息数据集
			rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			sqlTypes = new int[columnCount];
			for (int i = 0; i < columnCount; i++){
				sqlTypes[i] = rsmd.getColumnType(i + 1);
			}
			data_v = new Vector();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				data_v.add(rsmd.getColumnName(i));
			}
			report.setData(data_v);
			Vlist.addElement(report);
			while (rs.next()) { // 依次得到值
				report = new ActivityReport(); // 产生实体对象
				data_v = new Vector();
				// 按照上面搜索信息依次附值，以下方法如;
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					if(rsmd.getColumnTypeName(i).equals("VARCHAR2")|| rsmd.getColumnTypeName(i).equals("VARCHAR"))
					data_v.add(this.editNull(rs.getString(i))+"&nbsp;");
					else
					data_v.add(this.editNull(rs.getString(i)));
				}
				report.setData(data_v);
				Vlist.addElement(report); // 形成一条记录，插入记录列表
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

	public int[] getSqlTypes() {
		return sqlTypes;
	}

	public void setSqlTypes(int[] sqlTypes) {
		this.sqlTypes = sqlTypes;
	}

}
