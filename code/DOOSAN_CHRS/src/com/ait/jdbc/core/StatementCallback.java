/**
 * @date 2006-6-14
 */
package com.ait.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 执行sql，回调接口(主要用于更新)
 * @version 
 */
public interface StatementCallback {
	void doSetParams(PreparedStatement pstmt) throws SQLException;

}
