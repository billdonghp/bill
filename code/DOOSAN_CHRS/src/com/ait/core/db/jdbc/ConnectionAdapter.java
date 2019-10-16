package com.ait.core.db.jdbc;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;

/**
 * Connection Adapter
 * @author lizaijun
 *
 */
public class ConnectionAdapter implements Connection {
	
	private Connection connection = null;
	
	private boolean rollbackTag = false;
	
	public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return connection.createArrayOf(arg0, arg1);
	}

	public Blob createBlob() throws SQLException {
		// TODO Auto-generated method stub
		return connection.createBlob();
	}

	public Clob createClob() throws SQLException {
		// TODO Auto-generated method stub
		return connection.createClob();
	}

	public NClob createNClob() throws SQLException {
		// TODO Auto-generated method stub
		return connection.createNClob();
	}

	public SQLXML createSQLXML() throws SQLException {
		// TODO Auto-generated method stub
		return connection.createSQLXML();
	}

	public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return connection.createStruct(arg0, arg1);
	}

	public Properties getClientInfo() throws SQLException {
		// TODO Auto-generated method stub
		return connection.getClientInfo();
	}

	public String getClientInfo(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return connection.getClientInfo(arg0);
	}

	public boolean isValid(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return connection.isValid(arg0);
	}

	public void setClientInfo(Properties arg0) throws SQLClientInfoException {
		// TODO Auto-generated method stub
		connection.setClientInfo(arg0) ;
	}

	public void setClientInfo(String arg0, String arg1) throws SQLClientInfoException {
		// TODO Auto-generated method stub
		connection.setClientInfo(arg0, arg1) ;
	}

	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return connection.isWrapperFor(arg0);
	}

	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return connection.unwrap(arg0);
	}


	public ConnectionAdapter(Connection connection) throws SQLException {
		if (connection == null)
			throw new SQLException("conneciton is null.");
		this.connection = connection;
		this.rollbackTag = false;
	}
	
	/**
	 * 创建Statement.
	 * @return Statement
	 * @throws SQLException
	 */
	public Statement createStatement() throws SQLException {
		return new StatementAdapter(connection.createStatement());
	}
	
	/**
	 * 创建PreparedStatement
	 * @param  sql巩
	 * @return PreparedStatement 按眉
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return new PreparedStatementAdapter(connection.prepareStatement(sql),sql);
	}
	
	/**
	 * stored procedure CallableStatement
	 * @param  sql
	 * @return PreparedStatement
	 * @throws SQLException
	 */
	public CallableStatement prepareCall(String sql) throws SQLException {
		return new CallableStatementAdapter(connection.prepareCall(sql), sql);
	}
	
	public String nativeSQL(String sql) throws SQLException {
		return connection.nativeSQL(sql);
	}
	
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		//connection.setAutoCommit(autoCommit);
	}
	
	public boolean getAutoCommit() throws SQLException {
		return connection.getAutoCommit();
	}
	
	public void commit() throws SQLException {
		//connection.commit();
		//this.rollbackTag = false;
	}
	
	public void rollback() throws SQLException {
		//connection.rollback();
		this.rollbackTag = true;
	}
	
	public void close() throws SQLException {
		this.rollbackTag = false;
		//connection.close();
	}
	
	public void realSetAutoCommit(boolean autoCommit) throws SQLException {
		connection.setAutoCommit(autoCommit);
	}
	
	public void realCommit() throws SQLException {
		connection.commit();
	}
	
	public void realRollback() throws SQLException {
		connection.rollback();
	}
	
	public void realClose() throws SQLException {
		connection.close();
	}

	public boolean isRollbacked(){
		return this.rollbackTag;
	}
	
	/**
	 * Retrieves whether this Connection object has been closed
	 * @return true if this Connection object is closed; false if it is still open 
	 * @throws SQLException
	 */
	public boolean isClosed() throws SQLException {
		return connection.isClosed();
	}
	
	/**
	 * Retrieves a DatabaseMetaData object that contains metadata about the database 
	 * to which this Connection object represents a connection
	 * @return a DatabaseMetaData object for this Connection object 
	 * @throws SQLException
	 */
	public DatabaseMetaData getMetaData() throws SQLException {
		return connection.getMetaData();
	}
	
	/**
	 * Puts this connection in read-only mode as a hint to the driver to enable database optimizations
	 * @throws SQLException
	 */
	public void setReadOnly(boolean readOnly) throws SQLException {
		connection.setReadOnly(readOnly);
	}
	
	/**
	 * Retrieves whether this Connection object is in read-only mode
	 * @return true if this Connection object is read-only; false otherwise 
	 * @throws SQLException
	 */
	public boolean isReadOnly() throws SQLException {
		return connection.isReadOnly();
	}
	
	/**
	 * Sets the given catalog name in order to select a subspace of 
	 * this Connection object's database in which to work
	 * @throws SQLException
	 */
	public void setCatalog(String catalog) throws SQLException {
		connection.setCatalog(catalog);
	}
	
	/**
	 * Retrieves this Connection object's current catalog name
	 * @return the current catalog name or null if there is none 
	 * @throws SQLException
	 */
	public String getCatalog() throws SQLException {
		return connection.getCatalog();
	}
	
	/**
	 * Attempts to change the transaction isolation level for this Connection object to the one given
	 * @throws SQLException
	 */
	public void setTransactionIsolation(int level) throws SQLException {
		connection.setTransactionIsolation(level);
	}
	
	/**
	 * Retrieves this Connection object's current transaction isolation level
	 * @return the current transaction isolation level
	 * @throws SQLException
	 */
	public int getTransactionIsolation() throws SQLException {
		return connection.getTransactionIsolation();
	}
	
	/**
	 * Retrieves the first warning reported by calls on this Connection object
	 * @return the first SQLWarning object or null if there are none 
	 * @throws SQLException
	 */
	public SQLWarning getWarnings() throws SQLException {
		return connection.getWarnings();
	}
	
	/**
	 * Clears all warnings reported for this Connection object
	 * @throws SQLException
	 */
	public void clearWarnings() throws SQLException {
		connection.clearWarnings();
	}
	
	/**
	 * Creates a Statement object that will generate ResultSet objects with the given type and concurrency.
	 * @return a new Statement object 
	 * @throws SQLException
	 */
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
		throws SQLException {
		Statement stmt = connection.createStatement(resultSetType, resultSetConcurrency);
		return new StatementAdapter(stmt);
	}

	/**
	 * Creates a Statement object that will generate ResultSet objects with the given type, 
	 * concurrency, and holdability
	 * @return a new Statement object
	 * @throws SQLException
	 */
	public Statement createStatement(
		int resultSetType,
		int resultSetConcurrency,
		int resultSetHoldability)
		throws SQLException {
		Statement stmt =
			connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		return new StatementAdapter(stmt);
	}
	
	/**
	 * Creates a default PreparedStatement object that has the capability to retrieve auto-generated keys.
	 * @return a new PreparedStatement object
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
		throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(sql, autoGeneratedKeys);
		return new PreparedStatementAdapter(stmt, sql);
	}
	
	/**
	 * Creates a default PreparedStatement object capable of returning the auto-generated keys 
	 * designated by the given array
	 * @return a new PreparedStatement object
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
		throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(sql, columnIndexes);
		return new PreparedStatementAdapter(stmt, sql);
	}
	
	/**
	 * Creates a default PreparedStatement object capable of returning the auto-generated keys 
	 * designated by the given array.
	 * @return a new PreparedStatement object
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
		throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(sql, columnNames);
		return new PreparedStatementAdapter(stmt, sql);
	}
	
	/**
	 * Creates a PreparedStatement object that will generate ResultSet objects with the given type 
	 * and concurrency
	 * @return a new PreparedStatement object
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(
		String sql,
		int resultSetType,
		int resultSetConcurrency)
		throws SQLException {
		PreparedStatement stmt =
			connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		return new PreparedStatementAdapter(stmt, sql);
	}
	
	/**
	 * Creates a PreparedStatement object that will generate ResultSet objects with the given type,
	 * concurrency, and holdability
	 * @return a new PreparedStatement object
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(
		String sql,
		int resultSetType,
		int resultSetConcurrency,
		int resultSetHoldability)
		throws SQLException {
		PreparedStatement stmt =
			connection.prepareStatement(
				sql,
				resultSetType,
				resultSetConcurrency,
				resultSetHoldability);
		return new PreparedStatementAdapter(stmt, sql);
	}
	
	/**
	 * Creates a CallableStatement object that will generate ResultSet objects with the given type 
	 * and concurrency
	 * @return a new CallableStatement object
	 * @throws SQLException
	 */
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
		throws SQLException {
	    CallableStatement cstmt = connection.prepareCall(sql, resultSetType, resultSetConcurrency);
	    return new CallableStatementAdapter(cstmt, sql);
	}

	/**
	 * Creates a CallableStatement object that will generate ResultSet objects with the given type 
	 * and concurrency
	 * @return a new CallableStatement object
	 * @throws SQLException
	 */
	public CallableStatement prepareCall(
		String sql,
		int resultSetType,
		int resultSetConcurrency,
		int resultSetHoldability)
		throws SQLException {
	    CallableStatement cstmt = 
	        connection.prepareCall(	sql
				                  , resultSetType
				                  , resultSetConcurrency
				                  , resultSetHoldability
				                  );
		return new CallableStatementAdapter(cstmt, sql);
	}
	
	/**
	 * Retrieves the Map object associated with this Connection object
	 * @return the java.util.Map object associated with this Connection object
	 * @throws SQLException
	 */
	public Map getTypeMap() throws SQLException {
		return connection.getTypeMap();
	}
	
	/**
	 * Installs the given TypeMap object as the type map for this Connection object
	 * @throws SQLException
	 */
	public void setTypeMap(Map map) throws SQLException {
		connection.setTypeMap(map);
	}
	/**
	 * Retrieves the current holdability of ResultSet objects created using this Connection object.
	 * @return the holdability 
	 * @throws SQLException
	 */
	public int getHoldability() throws SQLException {
		return connection.getHoldability();
	}
	/**
	 * Removes the given Savepoint object from the current transaction
	 * @throws SQLException
	 */
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		connection.releaseSavepoint(savepoint);
	}
	/**
	 * Undoes all changes made after the given Savepoint object was set
	 * @throws SQLException
	 */
	public void rollback(Savepoint savepoint) throws SQLException {
		connection.rollback(savepoint);
	}
	/**
	 * Changes the holdability of ResultSet objects created using this Connection object to the given holdability.
	 * @throws SQLException
	 */
	public void setHoldability(int holdability) throws SQLException {
		connection.setHoldability(holdability);
	}
	/**
	 * Creates an unnamed savepoint in the current transaction and returns the new Savepoint object that represents it
	 * @return the new Savepoint object 
	 * @throws SQLException
	 */
	public Savepoint setSavepoint() throws SQLException {
		return connection.setSavepoint();
	}
	/**
	 * Creates a savepoint with the given name in the current transaction and returns the new Savepoint object that represents it.
	 * @return the new Savepoint object
	 * @throws SQLException
	 */
	public Savepoint setSavepoint(String name) throws SQLException {
		return connection.setSavepoint(name);
	}
}