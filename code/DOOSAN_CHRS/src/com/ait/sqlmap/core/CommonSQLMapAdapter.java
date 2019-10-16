/*
 * @(#)CommonSQLMapController.java 1.0 2006-12-5
 *
 */
package com.ait.sqlmap.core;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-5 下午03:46:51
 * @version 1.0
 * 
 */
public class CommonSQLMapAdapter {    

	/** SqlMapClient */
	private SqlMapClient sqlmapClient;

	private static final Logger logger = Logger
			.getLogger(CommonSQLMapAdapter.class);

	private final String defaultClientName = "em2";
	
	public CommonSQLMapAdapter() {
		this.initialize(defaultClientName);
	}

	public CommonSQLMapAdapter(String clientName) {

		this.initialize(clientName);
	}

	/**
	 * initialize sqlmap client
	 * 
	 * @param clientName
	 */
	private void initialize(String clientName) {

		try {
			sqlmapClient = SQLMapConfigManager.getInstance().getSqlMapClient(
					clientName);
		} catch (GlRuntimeException e) {

			logger.error("CommonSQLMapAdapter initialize fail. ");
			throw new GlRuntimeException(
					"CommonSQLMapAdapter initialize fail. ", e);

		}
	}

	/**
	 * 
	 * retrieves one row of query result.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return Object
	 * @throws SQLException
	 */
	public Object executeQuery(String statementName, Object parameterObject)
			throws SQLException {

		Object result = (Object) sqlmapClient.queryForObject(statementName,
				parameterObject);
		return result;
	}

	/**
	 * retrieves one row of query result by not parameter.
	 * 
	 * @param statementName
	 * @return Object
	 * @throws SQLException
	 */
	public Object executeQuery(String statementName) throws SQLException {

		Object result = this.executeQuery(statementName, null);

		return result;
	}

	/**
	 * retrieves multiple rows off query result. not for paging.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return List
	 * @throws SQLException
	 */
	public List executeQueryForMulti(String statementName,
			Object parameterObject) throws SQLException {

		List list = sqlmapClient.queryForList(statementName, parameterObject);

		return list;
	}

	/**
	 * retrieves multiple rows off query result by not parameter. not for
	 * paging.
	 * 
	 * @param statementName
	 * @return List
	 * @throws SQLException
	 */
	public List executeQueryForMulti(String statementName) throws SQLException {

		List list = this.executeQueryForMulti(statementName, null);

		return list;
	}

	/**
	 * retrieves multiple rows off query result. for paging.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return List
	 * @throws SQLException
	 */
	public List executeQueryForMulti(String statementName,
			Object parameterObject, int currentPage, int pageSize)
			throws SQLException {

		List list = sqlmapClient.queryForList(statementName, parameterObject,
				currentPage * pageSize, pageSize);

		return list;
	}

	/**
	 * retrieves multiple rows off query result by not parameter. for paging.
	 * 
	 * @param statementName
	 * @param currentPage
	 * @param pageSize
	 * @return List
	 * @throws SQLException
	 */
	public List executeQueryForMulti(String statementName, int currentPage,
			int pageSize) throws SQLException {

		List list = this.executeQueryForMulti(statementName, null, currentPage,
				pageSize);

		return list;
	}

	/**
	 * update process
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return int
	 * @throws SQLException
	 */
	public int update(String statementName, Object parameterObject)
			throws SQLException {

		return sqlmapClient.update(statementName, parameterObject);

	}

	/**
	 * update process by not parameter
	 * 
	 * @param statementName
	 * @return int
	 * @throws SQLException
	 */
	public int update(String statementName) throws SQLException {

		return this.update(statementName, null);

	}

	/**
	 * update process for multi in same transaction
	 * 
	 * @param statementName
	 * @param parameterList
	 * @throws SQLException
	 */
	public void updateForMulti(String statementName, List parameterList)
			throws SQLException {

		try {

			if (parameterList == null) {

				logger.error("List parameter object is null. ");
				throw new GlRuntimeException(
						"List parameter object not exist Exception.");
			}

			this.startTransaction();
			this.startBatch();
			
			Object parameterObject;
			for (int i = 0; i < parameterList.size(); i++) {

				parameterObject = parameterList.get(i);
				sqlmapClient.update(statementName, parameterObject);
			}

			this.executeBatch();
			this.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException("update multi-row Exception. ", e);
		} finally {

			try {
				this.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}

	}

	/**
	 * update process for multi. if isAutoTrans is true, process in transatction
	 * 
	 * @param statementName
	 * @param parameterList
	 * @param isAutoTrans
	 * @throws SQLException
	 */
	public void updateForMulti(String statementName, List parameterList,
			boolean isAutoTrans) throws SQLException {

		if (isAutoTrans) {

			this.updateForMulti(statementName, parameterList);
		} else {

			if (parameterList == null) {

				logger.error("List parameter object is null. ");
				throw new GlRuntimeException(
						"List parameter object not exist Exception.");
			}

			Object parameterObject;

			this.startBatch();
			for (int i = 0; i < parameterList.size(); i++) {

				parameterObject = parameterList.get(i);
				sqlmapClient.update(statementName, parameterObject);
			}
			this.executeBatch();
		}

	}

	/**
	 * insert process
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return Object
	 * @throws SQLException
	 */
	public Object insert(String statementName, Object parameterObject)
			throws SQLException {

		return sqlmapClient.insert(statementName, parameterObject);
	}

	/**
	 * insert process by not parameter
	 * 
	 * @param statementName
	 * @return Object
	 * @throws SQLException
	 */
	public Object insert(String statementName) throws SQLException {

		return this.insert(statementName, null);
	}

	/**
	 * insert process for multi in same transaction
	 * 
	 * @param statementName
	 * @param parameterList
	 * @throws SQLException
	 */
	public void insertForMulti(String statementName, List parameterList)
			throws SQLException {

		try {

			if (parameterList == null) {

				logger.error("List parameter object is null. ");
				throw new GlRuntimeException(
						"List parameter object not exist Exception.");
			}

			this.startTransaction();
			this.startBatch();

			Object parameterObject;
			for (int i = 0; i < parameterList.size(); i++) {

				parameterObject = parameterList.get(i);
				sqlmapClient.insert(statementName, parameterObject);
			}

			this.executeBatch();
			this.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException("insert multi-row Exception. ", e);
		} finally {

			try {
				this.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}

	/**
	 * insert process for multi. if isAutoTrans is true, process in transatction
	 * 
	 * @param statementName
	 * @param parameterList
	 * @param isAutoTrans
	 * @throws SQLException
	 */
	public void insertForMulti(String statementName, List parameterList,
			boolean isAutoTrans) throws SQLException {

		if (isAutoTrans) {

			this.insertForMulti(statementName, parameterList);
		} else {

			if (parameterList == null) {

				logger.error("List parameter object is null. ");
				throw new GlRuntimeException(
						"List parameter object not exist Exception.");
			}

			Object parameterObject;

			this.startBatch();
			for (int i = 0; i < parameterList.size(); i++) {

				parameterObject = parameterList.get(i);
				sqlmapClient.insert(statementName, parameterObject);
			}
			this.executeBatch();
		}
	}

	/**
	 * delete process
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return int
	 * @throws SQLException
	 */
	public int delete(String statementName, Object parameterObject)
			throws SQLException {

		return sqlmapClient.delete(statementName, parameterObject);
	}

	/**
	 * delete process by not parameter
	 * 
	 * @param statementName
	 * @return int
	 * @throws SQLException
	 */
	public int delete(String statementName) throws SQLException {

		return this.delete(statementName, null);
	}

	/**
	 * delete proces for multi in same transaction
	 * 
	 * @param statementName
	 * @param parameterList
	 * @throws SQLException
	 */
	public void deleteForMulti(String statementName, List parameterList)
			throws SQLException {

		try {

			if (parameterList == null) {

				logger.error("List parameter object is null. ");
				throw new GlRuntimeException(
						"List parameter object not exist Exception.");
			}

			this.startTransaction();
			this.startBatch();

			Object parameterObject;
			for (int i = 0; i < parameterList.size(); i++) {

				parameterObject = parameterList.get(i);
				sqlmapClient.delete(statementName, parameterObject);
			}

			this.executeBatch();
			this.commitTransation();

		} catch (Exception e) {

			logger.error("Transation rollback. " + e.toString());
			throw new GlRuntimeException("delete multi-row Exception. ", e);
		} finally {

			try {
				this.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}

	/**
	 * delete process for multi. if isAutoTrans is true, process in transatction
	 * 
	 * @param statementName
	 * @param parameterList
	 * @param isAutoTrans
	 * @throws SQLException
	 */
	public void deleteForMulti(String statementName, List parameterList,
			boolean isAutoTrans) throws SQLException {

		if (isAutoTrans) {

			this.deleteForMulti(statementName, parameterList);
		} else {

			if (parameterList == null) {

				logger.error("List parameter object is null. ");
				throw new GlRuntimeException(
						"List parameter object not exist Exception.");
			}

			Object parameterObject;

			this.startBatch();
			for (int i = 0; i < parameterList.size(); i++) {

				parameterObject = parameterList.get(i);
				sqlmapClient.delete(statementName, parameterObject);
			}
			this.executeBatch();
		}
	}

	/**
	 * start current transation
	 * 
	 * @throws SQLException
	 */
	public void startTransaction() throws SQLException {

		sqlmapClient.startTransaction();
	}

	/**
	 * commit current transation
	 * 
	 * @throws SQLException
	 */
	public void commitTransation() throws SQLException {

		sqlmapClient.commitTransaction();
	}

	/**
	 * end current transation
	 * 
	 * @throws SQLException
	 */
	public void endTransation() throws SQLException {

		sqlmapClient.endTransaction();
	}
	
	/**
	 * start batch process
	 * 
	 * @throws SQLException
	 */
	public void startBatch() throws SQLException {
		
		sqlmapClient.startBatch();
	}
	
	/**
	 * execute Batch
	 * 
	 * @throws SQLException
	 */
	public void executeBatch() throws SQLException {
		
		sqlmapClient.executeBatch();
	}

}
