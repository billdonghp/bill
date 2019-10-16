/*
 * @(#)ConnectionManager.java 1.0 2007-1-29 下午09:58:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.excel.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.web.TomcatDataSourceProvider;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-1-29 下午09:58:14
 * @version 1.0
 * 
 */
public class ConnectionManager {

	private static final Logger logger = Logger
			.getLogger(SQLMapConfigManager.class);

	/**
	 * get connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		Connection conn = new TomcatDataSourceProvider().getDataSouce()
				.getConnection();
		return conn;
	}

	/**
	 * close connection
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {

			logger.error(e.toString());
		}
	}
}
