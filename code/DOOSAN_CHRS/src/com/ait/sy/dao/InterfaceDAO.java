package com.ait.sy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.core.db.jdbc.JdbcTemplate;
import com.ait.core.exception.GlRuntimeException;
import com.ait.util.SqlUtil;

/**
 * 操作IF_MAPPING表
 * @author Pennix
 *
 */
public class InterfaceDAO {

	private static final String INTERFACE_USERNAME = "IF_EHR";

	private static final Logger logger = Logger.getLogger(InterfaceDAO.class);

	private static final String GET_EM_TABLES = "SELECT TABLE_NAME FROM USER_TABLES ORDER BY TABLE_NAME";

	private static final String GET_EM_COLUMNS = "SELECT USER_TAB_COLS.COLUMN_NAME, USER_COL_COMMENTS.COMMENTS, IF_MAPPING.FORMULA FROM USER_TAB_COLS, USER_COL_COMMENTS, IF_MAPPING WHERE USER_TAB_COLS.COLUMN_NAME = USER_COL_COMMENTS.COLUMN_NAME AND USER_TAB_COLS.COLUMN_NAME = IF_MAPPING.EM_COLUMN(+) AND USER_TAB_COLS.TABLE_NAME = ? AND USER_COL_COMMENTS.TABLE_NAME = ? AND IF_MAPPING.EM_TABLE(+) = ? AND IF_MAPPING.IF_TABLE(+) = ? ORDER BY USER_TAB_COLS.COLUMN_ID";

	private static final String GET_EM_ORDER = "SELECT ORDER_NO FROM IF_MAPPING WHERE ROWNUM = 1 AND EM_TABLE = ?";

	private static final String GET_IF_TABLES = "SELECT TABLE_NAME FROM SYS.ALL_TABLES WHERE OWNER = ? UNION SELECT VIEW_NAME TABLE_NAME FROM SYS.ALL_VIEWS WHERE OWNER = ?";

	private static final String GET_IF_COLUMNS = "SELECT COLUMN_NAME, COMMENTS FROM SYS.ALL_COL_COMMENTS WHERE OWNER = ? AND TABLE_NAME = ?";

	private static final String DELETE_MAPPING = "DELETE IF_MAPPING WHERE EM_TABLE = ?";

	private static final String INSERT_MAPPING = "INSERT INTO IF_MAPPING (EM_TABLE, EM_COLUMN, IF_TABLE, FORMULA, ORDER_NO) VALUES (?, ?, ?, ?, ?)";

	/**
	 * 取指定接口用户的所有表名
	 * @param ifUser 如果为空,取默认值INTERFACE_USERNAME = "IF_EHR"
	 * @return
	 */
	public List<String> getIfTables(String ifUser) {
		List<String> tables = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(GET_IF_TABLES);
			pstmt.setString(1, StringUtils.defaultIfEmpty(ifUser, INTERFACE_USERNAME));
			pstmt.setString(2, StringUtils.defaultIfEmpty(ifUser, INTERFACE_USERNAME));
			rs = pstmt.executeQuery();
			while (rs.next())
				tables.add(rs.getString("TABLE_NAME"));
		} catch (Exception e) {
			logger.error("接口发生错误, 请等待", e);
			throw new GlRuntimeException("接口发生错误, 请等待", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return tables;
	}

	/**
	 * 取指定接口表的所有列
	 * @param tableName
	 * @return
	 */
	public List<String[]> getIfColumns(String ifUser, String tableName) {
		List<String[]> list = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(GET_IF_COLUMNS);
			pstmt.setString(1, StringUtils.defaultIfEmpty(ifUser, INTERFACE_USERNAME));
			pstmt.setString(2, tableName);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new String[] { rs.getString("COLUMN_NAME"), rs.getString("COMMENTS") });
		} catch (Exception e) {
			logger.error("接口发生错误, 请等待", e);
			throw new GlRuntimeException("接口发生错误, 请等待", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取得当前用户的所有表名
	 * @return
	 */
	public List<String> getEmTables() {
		List<String> tables = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(GET_EM_TABLES);
			while (rs.next())
				tables.add(rs.getString("TABLE_NAME"));
		} catch (Exception e) {
			logger.error("接口发生错误, 请等待", e);
			throw new GlRuntimeException("接口发生错误, 请等待", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return tables;
	}

	/**
	 * 取得指定人事表的更新顺序号
	 * @param emTable
	 * @return
	 */
	public int getEmOrder(String emTable) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(GET_EM_ORDER);
			pstmt.setString(1, emTable);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getInt("ORDER_NO");
		} catch (Exception e) {
			logger.error("接口发生错误, 请等待", e);
			throw new GlRuntimeException("接口发生错误, 请等待", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}

	/**
	 * 取得指定表的所有列
	 * @param tableName
	 * @return
	 */
	public List<String[]> getEmColumns(String emTable, String ifTable) {
		List<String[]> list = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(GET_EM_COLUMNS);
			pstmt.setString(1, emTable);
			pstmt.setString(2, emTable);
			pstmt.setString(3, emTable);
			pstmt.setString(4, ifTable);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new String[] { rs.getString("COLUMN_NAME"), rs.getString("COMMENTS"), StringUtils.replace(rs.getString("FORMULA"), "\"", "&quot;") });
		} catch (Exception e) {
			logger.error("接口发生错误, 请等待", e);
			throw new GlRuntimeException("接口发生错误, 请等待", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 保存接口表映射
	 * @param ifTableName
	 * @param mappings
	 * @return
	 */
	public int saveMapping(String emTable, int orderNo, String ifTable, Set<String[]> set) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			pstmt = conn.prepareStatement(DELETE_MAPPING);
			pstmt.setString(1, emTable);
			pstmt.executeUpdate();
			SqlUtil.close(pstmt);
			pstmt = conn.prepareStatement(INSERT_MAPPING);
			pstmt.setString(1, emTable);
			pstmt.setString(3, ifTable);
			pstmt.setInt(5, orderNo);
			for (String[] string : set) {
				pstmt.setString(2, string[0]);
				pstmt.setString(4, string[1]);
				result += pstmt.executeUpdate();
			}
		} catch (Exception e) {
			logger.error("接口发生错误, 请等待", e);
			throw new GlRuntimeException("接口发生错误, 请等待", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}
}