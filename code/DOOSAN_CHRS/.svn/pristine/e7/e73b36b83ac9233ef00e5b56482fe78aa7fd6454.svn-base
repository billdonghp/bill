/*
 * @(#)ExcelBatchDAO.java 1.0 2007-1-29 下午08:53:21
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.excel.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.sqlmap.util.SimpleMap;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-1-29 下午08:53:21
 * @version 1.0
 * 
 */
public class ExcelBatchDAO {

	private ThreadLocal commitMode = new ThreadLocal();

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger
			.getLogger(SQLMapConfigManager.class);

	public ExcelBatchDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	/**
	 * save commit mode to local thread variable
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	private void saveCommitMode(Connection conn) throws SQLException {

		commitMode.set(new Boolean(conn.getAutoCommit()));
	}

	/**
	 * restore commit mode from local thread variable
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	private void restoreCommitMode(Connection conn) throws SQLException {

		conn.setAutoCommit(((Boolean) commitMode.get()).booleanValue());
	}

	/**
	 * set transation mode is not automatic
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	private void beginTx(Connection conn) throws SQLException {

		if (conn != null) {
			saveCommitMode(conn);
			conn.setAutoCommit(false);
		}
	}

	/**
	 * rollback transation
	 * 
	 * @param conn
	 */
	private void cancelTx(Connection conn) {

		try {
			if (conn != null)
				conn.rollback();
			restoreCommitMode(conn);
		} catch (SQLException e) {

			logger.error("SQLExcpetion while canceling transaction : " + e);
		}
	}

	/**
	 * commit transation
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	protected void endTx(Connection conn) throws SQLException {

		if (conn != null) {
			conn.commit();
			restoreCommitMode(conn);
		}
	}

	/**
	 * create sql of insert database
	 * 
	 * @param sheet
	 * @param paras
	 * @return String
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public String createInsertSql(Sheet sheet, SimpleMap paras)
			throws ArrayIndexOutOfBoundsException {

		// 取得第一行的单元格数组
		Cell[] firstRow = sheet.getRow(0);

		// 取得Excel列对应的表字段
		SimpleMap mappingColumn = (SimpleMap) paras.get("mappingColumn");
		Object[] columns = mappingColumn.keySet().toArray();

		// 取得附加列的集合
		SimpleMap appendColumn = (SimpleMap) paras.get("appendColumn");

		final int size = firstRow.length;

		if (columns != null && columns.length != size) {

			logger.error("Excel File Column Map Excepiton. Excel column: "
					+ size + "Map table column: " + columns.length);
			throw new GlRuntimeException("Excel File Column Map Excepiton.");
		}

		String tableName = "";
		// 取得要导入的表名称
		if (paras.getString("tableName") != null) {

			tableName = paras.getString("tableName");
		} else {
			// 如果没有表名称参数,取sheet名称为要导入的表名称
			tableName = sheet.getName();
		}

		String sql = " INSERT INTO " + tableName + " (";

		// 循环当前sheet的所有列名称,拼接SQL
		for (int i = 0; i < size - 1; i++) {

			// 如果有对应的表自段，取表字段拼接SQL
			if (columns != null && columns.length != 0
					&& size == columns.length) {

				sql += columns[i] + ", ";
			} else {

				sql += firstRow[i].getContents() + ", ";
			}
		}

		// 循环附加的列名集合,拼接SQL
		if (appendColumn != null) {

			Iterator names = appendColumn.keySet().iterator();
			while (names.hasNext()) {
				String name = (String) names.next();
				if (name == null) {
					continue;
				}
				sql += name + ",";
				String value = (String) paras.get(name);
			}
		}

		// 取sheet最后一个列名,拼接SQL
		if (columns != null && columns.length != 0 && size == columns.length) {

			sql += columns[size - 1] + ") ";
		} else {

			sql += firstRow[size - 1].getContents() + ") ";
		}

		// 拼接从Excel文件取得值的SQL
		sql += " VALUES ( ";
		for (int i = 0; i < firstRow.length - 1; i++) {
			sql += "?, ";
		}

		// 循环附加的列值集合，拼接SQL
		if (appendColumn != null) {

			Iterator it = appendColumn.keySet().iterator();
			while (it.hasNext()) {
				String itStr = (String) it.next();
				if (itStr == null) {
					continue;
				}
				sql += (String) paras.get(itStr) + ",";
			}
		}

		sql += "? ) ";

		logger.debug(sheet.getName() + " sql: " + sql);
		return sql;
	}

	/**
	 * insert sheet by transation
	 * 
	 * @param sheet
	 * @param paras
	 * @param on_error_rollback
	 * @return SheetBatchResult
	 */
	public SheetBatchResult insertSheetTx(Sheet sheet, SimpleMap paras,
			boolean on_error_rollback) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		long start = System.currentTimeMillis();

		// 取得插入数据库的SQL语句
		String sql = createInsertSql(sheet, paras);

		int success = 0;

		SheetBatchResult result = new SheetBatchResult(sheet);
		result.setBatchQuery(sql);

		try {
			conn = ConnectionManager.getConnection();
			beginTx(conn);
			pstmt = conn.prepareStatement(sql);

			// 循环工作表的每行数据,插入数据库
			for (int i = 1; i < sheet.getRows(); i++) {
				try {
					// 如果第一列第i行为空,跳出当前循环
					if (sheet.getCell(0, i).getContents().length() == 0) {
						continue;
					}
					// 为SQL指派参数
					assignParameters(pstmt, sheet.getRow(i),
							sheet.getRow(0).length);
					pstmt.executeUpdate();
					success++;
				} catch (SQLException se) {

					result.addErrorMessage(i + " " + (i + 1) + ") : "
							+ se.getMessage());
					logger.error(sheet.getName() + " (" + (i + 1) + ""
							+ se.getMessage() + ":RollBack.");

					if (on_error_rollback) {
						cancelTx(conn);
						success = 0;
						throw new SQLException(se.getMessage());
					}
				}
			}
			endTx(conn);
		} catch (ArrayIndexOutOfBoundsException e) {

			logger.error("insert sheet error.");
			throw new GlRuntimeException(" insert sheet error.", e);
		} catch (Exception e) {

			logger.error("insert sheet error.");
			throw new GlRuntimeException("insert sheet error.", e);
		} finally {
			close(pstmt);
			ConnectionManager.closeConnection(conn);
		}

		long end = System.currentTimeMillis();
		result.setElapsedTime(end - start);
		result.setSuccessCount(success);

		return result;
	}

	/**
	 * close PreparedStatement
	 * 
	 * @param pstmt
	 */
	public void close(PreparedStatement pstmt) {

		try {
			pstmt.close();
		} catch (SQLException e) {
			logger.error("close(PreparedStatement pstmt) error.");
			throw new GlRuntimeException(
					" close(PreparedStatement pstmt) error.", e);
		}
	}

	/**
	 * assign parameter
	 * 
	 * @param pstmt
	 * @param row
	 * @param columns
	 * @throws SQLException
	 */
	public void assignParameters(PreparedStatement pstmt, Cell[] row,
			int columns) throws SQLException {
		pstmt.clearParameters();

		for (int i = 0; i < columns; i++) {

			try {
				// set null value if cell's type is empty
				if (row[i].getType() == CellType.EMPTY)
					pstmt.setString(i + 1, null);
				else {
					final String para = row[i].getContents();
					pstmt.setString(i + 1, para);
				}
			} catch (Exception e) {

				pstmt.setString(i + 1, null);
			}

		}
	}

	/** *******************************************************基于IBATIS*********************************************** */

	/**
	 * insert sheet data by IBATIS
	 * 
	 * @param sheet
	 * @param paras
	 */
	public String insertSheet(Sheet sheet, SimpleMap parameterObject, int flag)
			throws GlRuntimeException {
		
		String message = "数据导入成功";
		
		// 验证是否存在导入数据
		if (sheet.getRows() < 2) {
			logger.debug("Import file not exist data, return.");
			return "导入文件不存在数据.";
		}
		
		// 创建导入临时表
		try {
			this.createImportTempTable(parameterObject);
		} catch (Exception e) {
			logger.error("Create temp table exception. " + e.toString());
			return "其他用户正在导入，请稍候再试.";
			
		}
		
		try {
			
			// 单一的INSERT SQL方式
			if (flag == ExcelBatchProcessor.SINGLE) {
				
				// 循环工作表的每行数据
				for (int i = 1; i < sheet.getRows(); i++) {
					
					
					// 验证当前行是否符合规范
					if (!validateCell(sheet.getRow(i), sheet.getRow(0).length))
						continue;
					
					// 根据当前行Excel内容创建SQL
					this.setSQLParam(parameterObject, sheet.getRow(i));
					// 导入数据到临时表
					this.insertImportTempTable(parameterObject);
				}
				
			} else {
				// 循环工作表的每行数据
				for (int i = 1; i < sheet.getRows(); i++) {
					
					
					// 验证当前行是否符合规范
					if (!validateCell(sheet.getRow(i), sheet.getRow(0).length))
						continue;
					
					
					// 根据当前行Excel内容和SELECT SQL参数，来创建SQL
					this.setSQLContent(parameterObject, sheet.getRow(i));
					// 导入数据到临时表
					this.insertImportTempTableBySelectResult(parameterObject);
					
				}
			}
			
			commonSQLMapAdapter.startTransaction();
			//commonSQLMapAdapter.startBatch() ;
			//commonSQLMapAdapter.executeBatch() ;
			
			// 删除最终导入表的已经存在的数据
			this.deleteRepeatData(parameterObject);
			// 导入数据到最终表
			this.insertFinalImportTable(parameterObject);
			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException("Import Excel data Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();

				// 删除导入临时表
				this.dropImportTempTable(parameterObject);

			} catch (SQLException e) {
				logger.error("Drop import temp table Exception. "
						+ e.toString());
				throw new GlRuntimeException(
						"Drop import temp table Exception. ", e);
			} catch (Exception e) {
				logger.error("End transation Exception. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}

		return message;
	}

	/**
	 * create sql of Excel content
	 * 
	 * @param paras
	 * @param row
	 */
	private void setSQLParam(SimpleMap paras, Cell[] row) {

		// 拼接从Excel文件取得值的SQL
		String sql = "";

		// 取得Excel列对应的表字段
		SimpleMap mappingColumn = (SimpleMap) paras.get("mappingColumn");
		Object[] fieldTypes = mappingColumn.values().toArray();
		int rowLength = fieldTypes.length ;
		
		
		for (int i = 0; i < rowLength; i++) {

			try {
				
				int fieldType = Integer.parseInt(fieldTypes[i].toString());
				
				if (row[i] != null){

					if (row[i].getType() == null || row[i].getType() == CellType.EMPTY)
						sql += null;
					else {
						
						switch (fieldType) {
						case 0:
							sql += "'" + row[i].getContents() + "'";
							break;
						case 1:
							sql += "TO_DATE('" + row[i].getContents() + "','YYYY-MM-DD HH24:MI')";
							break;
						default:
							sql += row[i].getContents();
							break;
						}
					}
				}
				else
				{
					sql += null;
				}
			} catch (Exception e) {

				sql += null;
			}

			sql += ",";
		}

		if (paras.getString("sql_content_append") == null
				|| paras.getString("sql_content_append").equals(""))
			sql = sql.substring(0, sql.length() - 1);

		// 添加 "sql_content_file" 参数
		paras.setString("sql_content_file", sql);
		logger.debug("Create file content sql : " + sql);

	}
	
	/**
	 * create sql of Excel content by select SQL
	 * 
	 * @param paras
	 * @param row
	 */
	private void setSQLContent(SimpleMap paras, Cell[] row) {
		
		// 取得Execl一行的长度
		int cellSize = row.length ;
		
		// 取得参数中的SQL内容
		String sql = paras.getString("sqlContent");

		// 取得Excel列对应的表字段
		SimpleMap mappingColumn = (SimpleMap) paras.get("mappingColumn");
		Object[] fieldTypes = mappingColumn.values().toArray();
		Object[] columnNames = mappingColumn.keySet().toArray();
		
		int columnSize = columnNames.length ;
		logger.debug("Cell.length : " + cellSize + " --- columnNames.length : " + columnSize ) ;

		for (int i = 0; i < columnSize ; i++) {

			try {
				
				// 取得Excel当前列对应的数据库类型和数据库列名称
				int fieldType = Integer.parseInt(fieldTypes[i].toString());
				String columnName = columnNames[i].toString();
				
				if ( i < cellSize)
				{
					if (row[i].getType() == CellType.EMPTY) {
						sql = sql.replaceAll("#" + columnName + "#", "null");
					} else {
						
						switch (fieldType) {
						case 0:
							sql = sql.replaceAll("#" + columnName + "#", "'" + row[i].getContents() + "'");
							break;
						case 1:
							String format = "'DD-MM-YYYY HH24:MI'" ;
							if(row[i].getContents().indexOf("/") == 4 || row[i].getContents().indexOf("-") == 4)
							{
								format = "'YYYY-MM-DD HH24:MI'" ;
							}
							sql = sql.replaceAll("#" + columnName + "#", "TO_DATE('" + row[i].getContents() + "'," + format + ")");
							break;
						default:
							sql = sql.replaceAll("#" + columnName + "#", row[i].getContents());
							break;
						}
					}
				}
				else
				{
					sql = sql.replaceAll("#" + columnName + "#", "null");
				}
				
			} catch (Exception e) {

				sql = null;
				break;
			}
		}

		// 添加 "sql_content" 参数
		paras.setString("sql_content", sql);
		logger.debug("Create content sql : " + sql);

	}

	/**
	 * validate cell
	 * 
	 * @param pstmt
	 * @param row
	 * @param columns
	 * @throws SQLException
	 */
	private boolean validateCell(Cell[] row, int columns) {
		
		/*
		// 如果当前行的数据和表头不对应返回false
		if (row.length != columns)
			return false;
		
		for (int i = 0; i < columns; i++) {

			// 如果当前行有空的单元格返回false
			if (row[i].getType() == CellType.EMPTY)
				return false;

		}
		*/
		
		if (row.length < 1)
			return false ;
		
		return true;
	}

	/**
	 * create file import temp table
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void createImportTempTable(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("common.sys.createImportTempTable",
					parameterObject);

		} catch (SQLException e) {

			logger.error("create file import temp table Exception.");
			throw new GlRuntimeException(
					"create file import temp table Exception.", e);
		}

	}

	/**
	 * insert data into file import temp table
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertImportTempTable(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("common.sys.insertImportTempTable",
					parameterObject);

		} catch (SQLException e) {

			logger.error("insert data into file import temp table Exception.");
			throw new GlRuntimeException(
					"insert data into file import temp table Exception.", e);
		}

	}
	
	/**
	 * insert data into file import temp table by select result
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertImportTempTableBySelectResult(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("common.sys.insertImportTempTableBySelectResult",
					parameterObject);

		} catch (SQLException e) {

			logger.error("insert data into file import temp table by select result Exception.");
			throw new GlRuntimeException(
					"insert data into file import temp table by select result Exception.", e);
		}

	}

	/**
	 * drop file import temp table
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void dropImportTempTable(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("common.sys.dropImportTempTable",
					parameterObject);

		} catch (SQLException e) {

			logger.error("drop file import temp table Exception.");
			throw new GlRuntimeException(
					"drop file import temp table Exception.", e);
		}

	}

	/**
	 * delete repeat data in final import table
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteRepeatData(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("common.sys.deleteRepeatData",
					parameterObject);

		} catch (SQLException e) {

			logger.error("delete repeat data in final import table Exception.");
			throw new GlRuntimeException(
					"delete repeat data in final import table Exception.", e);
		}

	}

	/**
	 * insert imported file data into final import table
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertFinalImportTable(Object parameterObject)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("common.sys.insertFinalImportTable",
					parameterObject);

		} catch (SQLException e) {

			logger
					.error("insert imported file data into final import table Exception.");
			throw new GlRuntimeException(
					"insert imported file data into final import table Exception.",
					e);
		}

	}

	/**
	 * retrieve column information list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveColumnList(Object parameterObject)
			throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"common.sys.retrieveColumnList", parameterObject);

		} catch (SQLException e) {

			logger.error("retrieve colunmn information list Exception.");
			throw new GlRuntimeException(
					"retrieve colunmn information list Exception.", e);
		}

		return list;
	}
}
