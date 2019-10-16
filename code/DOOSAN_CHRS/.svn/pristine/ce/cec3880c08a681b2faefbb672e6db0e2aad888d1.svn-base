/**
 * @date 2006-6-14
 */
package com.ait.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 执行sql，回调接口(主要用于查询)
 * @version 
 */
public interface QueryCallback {
	void doSetParams(PreparedStatement pstmt) throws SQLException;
	Object doBulidResultSet(ResultSet rs) throws SQLException;

}
