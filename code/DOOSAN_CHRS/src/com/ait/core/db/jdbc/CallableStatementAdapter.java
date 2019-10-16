package com.ait.core.db.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ait.core.util.LStopWatch;

/**
 * stored procedure statement
 * @author lizaijun
 *
 */
class CallableStatementAdapter implements CallableStatement {
	
	private CallableStatement cstmt;
	private String sql = null;
	private ArrayList params = new ArrayList();
	
    public Reader getCharacterStream(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getCharacterStream(arg0);
	}

	public Reader getCharacterStream(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getCharacterStream(arg0);
	}

	public Reader getNCharacterStream(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getNCharacterStream(arg0);
	}

	public Reader getNCharacterStream(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getNCharacterStream(arg0);
	}

	public NClob getNClob(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getNClob(arg0);
	}

	public NClob getNClob(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getNClob(arg0);
	}

	public String getNString(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getNString(arg0);
	}

	public String getNString(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getNString(arg0);
	}

	public RowId getRowId(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getRowId(arg0);
	}

	public RowId getRowId(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getRowId(arg0);
	}

	public SQLXML getSQLXML(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getSQLXML(arg0);
	}

	public SQLXML getSQLXML(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.getSQLXML(arg0);
	}

	public void setAsciiStream(String arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setAsciiStream(arg0, arg1, arg2) ;
	}

	public void setAsciiStream(String arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setAsciiStream(arg0, arg1) ;
	}

	public void setBinaryStream(String arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setBinaryStream(arg0, arg1, arg2) ;
	}

	public void setBinaryStream(String arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setBinaryStream(arg0, arg1) ;
	}

	public void setBlob(String arg0, Blob arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setBlob(arg0, arg1) ;
	}

	public void setBlob(String arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setBlob(arg0, arg1, arg2) ;
	}

	public void setBlob(String arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setBlob(arg0, arg1) ;
	}

	public void setCharacterStream(String arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setCharacterStream(arg0, arg1, arg2) ;
	}

	public void setCharacterStream(String arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setCharacterStream(arg0, arg1) ;
	}

	public void setClob(String arg0, Clob arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setClob(arg0, arg1) ;
	}

	public void setClob(String arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setClob(arg0, arg1, arg2) ;
	}

	public void setClob(String arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setClob(arg0, arg1) ;
	}

	public void setNCharacterStream(String arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNCharacterStream(arg0, arg1, arg2) ;
	}

	public void setNCharacterStream(String arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNCharacterStream(arg0, arg1) ;
	}

	public void setNClob(String arg0, NClob arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNClob(arg0, arg1) ;
	}

	public void setNClob(String arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNClob(arg0, arg1, arg2) ;
	}

	public void setNClob(String arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNClob(arg0, arg1) ;
	}

	public void setNString(String arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNString(arg0, arg1) ;
	}

	public void setRowId(String arg0, RowId arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setRowId(arg0, arg1) ;
	}

	public void setSQLXML(String arg0, SQLXML arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setSQLXML(arg0, arg1) ;
	}

	public void setAsciiStream(int arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setAsciiStream(arg0, arg1, arg2) ;
	}

	public void setAsciiStream(int arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setAsciiStream(arg0, arg1) ;
	}

	public void setBinaryStream(int arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setBinaryStream(arg0, arg1, arg2) ;
	}

	public void setBinaryStream(int arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setBinaryStream(arg0, arg1) ;
	}

	public void setBlob(int arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setBlob(arg0, arg1, arg2) ;
	}

	public void setBlob(int arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setBlob(arg0, arg1) ;
	}

	public void setCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setCharacterStream(arg0, arg1, arg2) ;
	}

	public void setCharacterStream(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setCharacterStream(arg0, arg1) ;
	}

	public void setClob(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setClob(arg0, arg1, arg2) ;
	}

	public void setClob(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setClob(arg0, arg1) ;
	}

	public void setNCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNCharacterStream(arg0, arg1, arg2) ;
	}

	public void setNCharacterStream(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNCharacterStream(arg0, arg1) ;
	}

	public void setNClob(int arg0, NClob arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNClob(arg0, arg1) ;
	}

	public void setNClob(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNClob(arg0, arg1, arg2) ;
	}

	public void setNClob(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNClob(arg0, arg1) ;
	}

	public void setNString(int arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setNString(arg0, arg1) ;
	}

	public void setRowId(int arg0, RowId arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setRowId(arg0, arg1) ;
	}

	public void setSQLXML(int arg0, SQLXML arg1) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setSQLXML(arg0, arg1) ;
	}

	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.isClosed();
	}

	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.isPoolable();
	}

	public void setPoolable(boolean arg0) throws SQLException {
		// TODO Auto-generated method stub
		cstmt.setPoolable(arg0) ;
	}

	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.isWrapperFor(arg0);
	}

	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return cstmt.unwrap(arg0);
	}
	
	private void saveParam(int idx, Object arg) {
		if (idx < 1)
			return;
		while (idx > params.size()) {
			params.add(null);
		}
		params.set(idx - 1, arg);
	}
	
	private void saveParam(String parameterName, Object arg) {	    
	    String procedureName = this.extractProcedureName();
	    boolean isMySql = false;
	    	    
		try {
		    java.sql.DatabaseMetaData dbmd = this.cstmt.getConnection().getMetaData();
		    
		    if ("MYSQL".equalsIgnoreCase(dbmd.getDatabaseProductName())) {
		        isMySql = true;
		    }
		        
		    if (isMySql && procedureName != null) {
		        java.sql.ResultSet paramTypesRs = 
		            dbmd.getProcedureColumns( this.cstmt.getConnection().getCatalog()
				                            , null
				                            , procedureName
				                            , "%"
				                            );
				    
			    int index = 0;
			        
			    while (paramTypesRs.next()) {
			        index++;
			        String paramName = paramTypesRs.getString(4);
			            
			        if (parameterName.equalsIgnoreCase(paramName)) {
			            saveParam(index, arg);
			        }
			    }	
		    }
		} catch(SQLException se) {
			se.printStackTrace();
		}
	}

	private String getSqlTypeStr(int sqlType) {
        String typeStr = null;
        
        if      (sqlType == Types.ARRAY)         typeStr = "Types.ARRAY";
        else if (sqlType == Types.BIGINT)        typeStr = "Types.BIGINT";
        else if (sqlType == Types.BINARY)        typeStr = "Types.BINARY";
        else if (sqlType == Types.BIT)           typeStr = "Types.BIT";
        else if (sqlType == Types.BLOB)          typeStr = "Types.BLOB";
        else if (sqlType == Types.BOOLEAN)       typeStr = "Types.BOOLEAN";
        else if (sqlType == Types.CHAR)          typeStr = "Types.CHAR";
        else if (sqlType == Types.CLOB)          typeStr = "Types.CLOB";
        else if (sqlType == Types.DATALINK)      typeStr = "Types.DATALINK";
        else if (sqlType == Types.DATE)          typeStr = "Types.DATE";
        else if (sqlType == Types.DECIMAL)       typeStr = "Types.DECIMAL";
        else if (sqlType == Types.DISTINCT)      typeStr = "Types.DISTINCT";
        else if (sqlType == Types.DOUBLE)        typeStr = "Types.DOUBLE";
        else if (sqlType == Types.FLOAT)         typeStr = "Types.FLOAT";
        else if (sqlType == Types.INTEGER)       typeStr = "Types.INTEGER";
        else if (sqlType == Types.JAVA_OBJECT)   typeStr = "Types.JAVA_OBJECT";
        else if (sqlType == Types.LONGVARBINARY) typeStr = "Types.LONGVARBINARY";
        else if (sqlType == Types.LONGVARCHAR)   typeStr = "Types.LONGVARCHAR";
        else if (sqlType == Types.NULL)          typeStr = "Types.NULL";
        else if (sqlType == Types.NUMERIC)       typeStr = "Types.NUMERIC";
        else if (sqlType == Types.OTHER)         typeStr = "Types.OTHER";
        else if (sqlType == Types.REAL)          typeStr = "Types.REAL";
        else if (sqlType == Types.REF)           typeStr = "Types.REF";
        else if (sqlType == Types.SMALLINT)      typeStr = "Types.SMALLINT";
        else if (sqlType == Types.STRUCT)        typeStr = "Types.STRUCT";
        else if (sqlType == Types.TIME)          typeStr = "Types.TIME";
        else if (sqlType == Types.TIMESTAMP)     typeStr = "Types.TIMESTAMP";
        else if (sqlType == Types.TINYINT)       typeStr = "Types.TINYINT";
        else if (sqlType == Types.VARBINARY)     typeStr = "Types.VARBINARY";
        else if (sqlType == Types.VARCHAR)       typeStr = "Types.VARCHAR";
        
        return typeStr;
	}
	
	/**
	 * 
	 * 解析procedure名
	 * @return 
	 */
	public String extractProcedureName() {
		int endCallIndex = this.sql.toUpperCase().indexOf("CALL ");
		int offset = 5;

		if (endCallIndex != -1) {
			StringBuffer nameBuf = new StringBuffer();

			String trimmedStatement = this.sql.substring(
					endCallIndex + offset).trim();

			int statementLength = trimmedStatement.length();

			for (int i = 0; i < statementLength; i++) {
				char c = trimmedStatement.charAt(i);

				if (Character.isWhitespace(c) || (c == '(') || (c == '?')) {
					break;
				}
				nameBuf.append(c);

			}

			return nameBuf.toString();
		}
		return null;
	}
	
    /**
     * CallableStatementAdapter constructor
     */
    public CallableStatementAdapter(CallableStatement cstmt, String sql) {
        this.cstmt = cstmt;
        this.sql   = sql;
    }

    public boolean wasNull() throws SQLException {
        return cstmt.wasNull();
    }

    public byte getByte(int parameterIndex) throws SQLException {
        return cstmt.getByte(parameterIndex);
    }

    public double getDouble(int parameterIndex) throws SQLException {
        return cstmt.getDouble(parameterIndex);
    }

    public float getFloat(int parameterIndex) throws SQLException {
        return cstmt.getFloat(parameterIndex);
    }

    public int getInt(int parameterIndex) throws SQLException {
        return cstmt.getInt(parameterIndex);
    }

    public long getLong(int parameterIndex) throws SQLException {
        return cstmt.getLong(parameterIndex);
    }
    
    public short getShort(int parameterIndex) throws SQLException {
        return cstmt.getShort(parameterIndex);
    }
    public boolean getBoolean(int parameterIndex) throws SQLException {
        return cstmt.getBoolean(parameterIndex);
    }

    public byte[] getBytes(int parameterIndex) throws SQLException {
        return cstmt.getBytes(parameterIndex);
    }

    public void registerOutParameter(int parameterIndex, int sqlType)
            throws SQLException {
        saveParam(parameterIndex, "sqlType:" + getSqlTypeStr(sqlType));
        cstmt.registerOutParameter(parameterIndex, sqlType);
    }

    public void registerOutParameter(int parameterIndex, int sqlType, int scale)
            throws SQLException {
        saveParam(parameterIndex, "sqlType:" + getSqlTypeStr(sqlType) + ", scale:" + scale);
        cstmt.registerOutParameter(parameterIndex, sqlType, scale);

    }

    public Object getObject(int parameterIndex) throws SQLException {
        return cstmt.getObject(parameterIndex);
    }

    public String getString(int parameterIndex) throws SQLException {
        return cstmt.getString(parameterIndex);
    }

    public void registerOutParameter(int paramIndex, int sqlType,
            String typeName) throws SQLException {
        saveParam(paramIndex, "sqlType:" + getSqlTypeStr(sqlType) + ", typeName:" + typeName);
        cstmt.registerOutParameter(paramIndex, sqlType, typeName);
    }

    public byte getByte(String parameterName) throws SQLException {
        return cstmt.getByte(parameterName);
    }

    public double getDouble(String parameterName) throws SQLException {
        return cstmt.getDouble(parameterName);
    }

    public float getFloat(String parameterName) throws SQLException {
        return cstmt.getFloat(parameterName);
    }

    public int getInt(String parameterName) throws SQLException {
        return cstmt.getInt(parameterName);
    }

    public long getLong(String parameterName) throws SQLException {
        return cstmt.getLong(parameterName);
    }

    public short getShort(String parameterName) throws SQLException {
        return cstmt.getShort(parameterName);
    }

    public boolean getBoolean(String parameterName) throws SQLException {
        return cstmt.getBoolean(parameterName);
    }

    public byte[] getBytes(String parameterName) throws SQLException {
        return cstmt.getBytes(parameterName);
    }

    public void setByte(String parameterName, byte x) throws SQLException {
        saveParam(parameterName, new Byte(x));
        cstmt.setByte(parameterName, x);

    }

    public void setDouble(String parameterName, double x) throws SQLException {
        saveParam(parameterName, new Double(x));
        cstmt.setDouble(parameterName, x);
    }

    public void setFloat(String parameterName, float x) throws SQLException {
        saveParam(parameterName, new Float(x));
        cstmt.setFloat(parameterName, x);
    }

    public void registerOutParameter(String parameterName, int sqlType)
            throws SQLException {
        saveParam(parameterName, "sqlType:" + getSqlTypeStr(sqlType));
        cstmt.registerOutParameter(parameterName, sqlType);
    }

    public void setInt(String parameterName, int x) throws SQLException {
        saveParam(parameterName, new Integer(x));
        cstmt.setInt(parameterName, x);
    }

    public void setNull(String parameterName, int sqlType) throws SQLException {        
        saveParam(parameterName, null);
        cstmt.setNull(parameterName, sqlType);
    }

    public void registerOutParameter(String parameterName, int sqlType,
            int scale) throws SQLException {
        saveParam(parameterName, "sqlType:" + getSqlTypeStr(sqlType));
        cstmt.registerOutParameter(parameterName, sqlType);
    }

    public void setLong(String parameterName, long x) throws SQLException {
        saveParam(parameterName, new Long(x));
        cstmt.setLong(parameterName, x);
    }

    public void setShort(String parameterName, short x) throws SQLException {
        saveParam(parameterName, new Short(x));
        cstmt.setShort(parameterName, x);
    }

    public void setBoolean(String parameterName, boolean x) throws SQLException {
        saveParam(parameterName, new Boolean(x));        
        cstmt.setBoolean(parameterName, x);
    }

    public void setBytes(String parameterName, byte[] x) throws SQLException {
        saveParam(parameterName, x);
        cstmt.setBytes(parameterName, x);
    }

    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {        
        return cstmt.getBigDecimal(parameterIndex);
    }

    public BigDecimal getBigDecimal(int parameterIndex, int scale)
            throws SQLException {
        return cstmt.getBigDecimal(parameterIndex, scale);
    }

    public URL getURL(int parameterIndex) throws SQLException {
        return cstmt.getURL(parameterIndex);
    }

    public Array getArray(int parameterIndex) throws SQLException {
        return cstmt.getArray(parameterIndex);
    }

    public Blob getBlob(int parameterIndex) throws SQLException {
        return cstmt.getBlob(parameterIndex);
    }

    public Clob getClob(int parameterIndex) throws SQLException {
        return cstmt.getClob(parameterIndex);
    }

    public Date getDate(int parameterIndex) throws SQLException {
        return cstmt.getDate(parameterIndex);
    }

    public Ref getRef(int i) throws SQLException {
        return cstmt.getRef(i);
    }

    public Time getTime(int parameterIndex) throws SQLException {
        return cstmt.getTime(parameterIndex);
    }

    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return cstmt.getTimestamp(parameterIndex);
    }

    public void setAsciiStream(String parameterName, InputStream x, int length)
            throws SQLException {
        saveParam(parameterName, "InputStream(" + length + ")");
        cstmt.setAsciiStream(parameterName, x, length);
    }

    public void setBinaryStream(String parameterName, InputStream x, int length)
            throws SQLException {
        saveParam(parameterName, "InputStream(" + length + ")");
        cstmt.setBinaryStream(parameterName, x, length);
    }

    public void setCharacterStream(String parameterName, Reader reader,
            int length) throws SQLException {
        saveParam(parameterName, "Reader(" + length + ")");
        cstmt.setCharacterStream(parameterName, reader, length);
    }

    public Object getObject(String parameterName) throws SQLException {
        return cstmt.getObject(parameterName);
    }

    public void setObject(String parameterName, Object x) throws SQLException {
        saveParam(parameterName, x);
        cstmt.setObject(parameterName, x);
    }

    public void setObject(String parameterName, Object x, int targetSqlType)
            throws SQLException {
        saveParam(parameterName, x);
        cstmt.setObject(parameterName, x, targetSqlType);
    }


    public void setObject(String parameterName, Object x, int targetSqlType,
            int scale) throws SQLException {
        saveParam(parameterName, x);
        cstmt.setObject(parameterName, x, targetSqlType, scale);
    }

    public Object getObject(int i, Map map) throws SQLException {
        return cstmt.getObject(i, map);
    }

    public String getString(String parameterName) throws SQLException {
        return cstmt.getString(parameterName);
    }

    public void registerOutParameter(String parameterName, int sqlType,
            String typeName) throws SQLException {
        saveParam(parameterName, "sqlType:" + getSqlTypeStr(sqlType) + ", typeName:" + typeName);
        cstmt.registerOutParameter(parameterName, sqlType, typeName);
    }

    public void setNull(String parameterName, int sqlType, String typeName)
            throws SQLException {
        saveParam(parameterName, null);
        cstmt.setNull(parameterName, sqlType, typeName);
    }

    public void setString(String parameterName, String x) throws SQLException {
        saveParam(parameterName, x);
        cstmt.setString(parameterName, x);
    }

    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return cstmt.getBigDecimal(parameterName);
    }

    public void setBigDecimal(String parameterName, BigDecimal x)
            throws SQLException {
        saveParam(parameterName, x);
        cstmt.setBigDecimal(parameterName, x);
    }

    public URL getURL(String parameterName) throws SQLException {
        return cstmt.getURL(parameterName);
    }

    public void setURL(String parameterName, URL val) throws SQLException {
        saveParam(parameterName, val);
        cstmt.setURL(parameterName, val);
    }

    public Array getArray(String parameterName) throws SQLException {
        return cstmt.getArray(parameterName);
    }

    public Blob getBlob(String parameterName) throws SQLException {
        return cstmt.getBlob(parameterName);
    }

    public Clob getClob(String parameterName) throws SQLException {
        return cstmt.getClob(parameterName);
    }

    public Date getDate(String parameterName) throws SQLException {
        return cstmt.getDate(parameterName);
    }

    public void setDate(String parameterName, Date x) throws SQLException {
        saveParam(parameterName, x);
        cstmt.setDate(parameterName, x);
    }

    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return cstmt.getDate(parameterIndex, cal);
    }

    public Ref getRef(String parameterName) throws SQLException {
        return cstmt.getRef(parameterName);
    }

    public Time getTime(String parameterName) throws SQLException {
        return cstmt.getTime(parameterName);
    }

    public void setTime(String parameterName, Time x) throws SQLException {
        saveParam(parameterName, x);
        cstmt.setTime(parameterName, x);
    }

    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return cstmt.getTime(parameterIndex, cal);
    }

    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return cstmt.getTimestamp(parameterName);
    }

    public void setTimestamp(String parameterName, Timestamp x)
            throws SQLException {
        saveParam(parameterName, x);
        cstmt.setTimestamp(parameterName, x);
    }

    public Timestamp getTimestamp(int parameterIndex, Calendar cal)
            throws SQLException {
        return cstmt.getTimestamp(parameterIndex, cal);
    }

    public Object getObject(String parameterName, Map map) throws SQLException {
        return cstmt.getObject(parameterName, map);
    }

    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return cstmt.getDate(parameterName, cal);
    }

    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return cstmt.getTime(parameterName, cal);
    }

    public Timestamp getTimestamp(String parameterName, Calendar cal)
            throws SQLException {
        return cstmt.getTimestamp(parameterName, cal);
    }

    public void setDate(String parameterName, Date x, Calendar cal)
            throws SQLException {
        saveParam(parameterName, x);
        cstmt.setDate(parameterName, x, cal);
    }

    public void setTime(String parameterName, Time x, Calendar cal)
            throws SQLException {
        saveParam(parameterName, x);
        cstmt.setTime(parameterName, x, cal);
    }

    public void setTimestamp(String parameterName, Timestamp x, Calendar cal)
            throws SQLException {
        saveParam(parameterName, x);
        cstmt.setTimestamp(parameterName, x, cal);
    }

    public int executeUpdate() throws SQLException {
		LStopWatch watch = new LStopWatch();
		int result = 0;
		boolean isError = false;
		try {
			result = cstmt.executeUpdate();
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
		return result;
    }

    public void addBatch() throws SQLException {
    	Logger.getLogger(getClass()).debug("执行sql:"+sql);
		cstmt.addBatch();
    }

    public void clearParameters() throws SQLException {
        params.clear();
		cstmt.clearParameters();
    }

    public boolean execute() throws SQLException {
		LStopWatch watch = new LStopWatch();
		boolean isError = false;
		try {
			return cstmt.execute();
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
    }

    public void setByte(int parameterIndex, byte x) throws SQLException {
		saveParam(parameterIndex, new Byte(x));
        cstmt.setByte(parameterIndex, x);
    }

    public void setDouble(int parameterIndex, double x) throws SQLException {
		saveParam(parameterIndex, new Double(x));
        cstmt.setDouble(parameterIndex, x);
    }

    public void setFloat(int parameterIndex, float x) throws SQLException {
		saveParam(parameterIndex, new Float(x));
        cstmt.setFloat(parameterIndex, x);
    }

    public void setInt(int parameterIndex, int x) throws SQLException {
		saveParam(parameterIndex, new Integer(x));
        cstmt.setInt(parameterIndex, x);
    }

    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        saveParam(parameterIndex, null);
        cstmt.setNull(parameterIndex, sqlType);
    }

    public void setLong(int parameterIndex, long x) throws SQLException {
		saveParam(parameterIndex, new Long(x));
        cstmt.setLong(parameterIndex, x);
    }

    public void setShort(int parameterIndex, short x) throws SQLException {
		saveParam(parameterIndex, new Short(x));
        cstmt.setShort(parameterIndex, x);
    }

    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		saveParam(parameterIndex, new Boolean(x));        
        cstmt.setBoolean(parameterIndex, x);
    }

    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		saveParam(parameterIndex, x);
        cstmt.setBytes(parameterIndex, x);
    }

    public void setAsciiStream(int parameterIndex, InputStream x, int length)
            throws SQLException {
        saveParam(parameterIndex, "InputStream(" + length + ")");
        cstmt.setAsciiStream(parameterIndex, x, length);
    }

    public void setBinaryStream(int parameterIndex, InputStream x, int length)
            throws SQLException {
        saveParam(parameterIndex, "InputStream(" + length + ")");
        cstmt.setBinaryStream(parameterIndex, x, length);
    }
    
    /**
     * @deprecated
     */
    public void setUnicodeStream(int parameterIndex, InputStream x, int length)
            throws SQLException {
		throw new SQLException("Not Support : setUnicodeStream(int parameterIndex, InputStream x, int length)");
    }

    public void setCharacterStream(int parameterIndex, Reader reader, int length)
            throws SQLException {
		saveParam(parameterIndex, "Reader(" + length + ")");
        cstmt.setCharacterStream(parameterIndex, reader, length);
    }

    public void setObject(int parameterIndex, Object x) throws SQLException {
		saveParam(parameterIndex, x);
        cstmt.setObject(parameterIndex, x);
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType)
            throws SQLException {
        saveParam(parameterIndex, x);
        cstmt.setObject(parameterIndex, x, targetSqlType);
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType,
            int scale) throws SQLException {
        saveParam(parameterIndex, x);
        cstmt.setObject(parameterIndex, x, targetSqlType, scale);
    }

    public void setNull(int paramIndex, int sqlType, String typeName)
            throws SQLException {
		saveParam(paramIndex, null);
        cstmt.setNull(paramIndex, sqlType, typeName);
    }

    public void setString(int parameterIndex, String x) throws SQLException {
		saveParam(parameterIndex, x);
        cstmt.setString(parameterIndex, x);
    }

    public void setBigDecimal(int parameterIndex, BigDecimal x)
            throws SQLException {
		saveParam(parameterIndex, x);
        cstmt.setBigDecimal(parameterIndex, x);
    }

    public void setURL(int parameterIndex, URL x) throws SQLException {
        saveParam(parameterIndex, x);
        cstmt.setURL(parameterIndex, x);
    }

    public void setArray(int i, Array x) throws SQLException {
		saveParam(i, x);
        cstmt.setArray(i, x);
    }

    public void setBlob(int i, Blob x) throws SQLException {
		saveParam(i, x);
        cstmt.setBlob(i, x);
    }

    public void setClob(int i, Clob x) throws SQLException {
		saveParam(i, x);
        cstmt.setClob(i, x);
    }

    public void setDate(int parameterIndex, Date x) throws SQLException {
        saveParam(parameterIndex, x);
        cstmt.setDate(parameterIndex, x);
    }

    public ParameterMetaData getParameterMetaData() throws SQLException {
        return cstmt.getParameterMetaData();
    }

    public void setRef(int i, Ref x) throws SQLException {
        saveParam(i, x);
        cstmt.setRef(i, x);
    }

    public ResultSet executeQuery() throws SQLException {
		LStopWatch watch = new LStopWatch();
		boolean isError = false;
		try {
			return cstmt.executeQuery(sql); 
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return cstmt.getMetaData();
    }

    public void setTime(int parameterIndex, Time x) throws SQLException {
		saveParam(parameterIndex, x);
        cstmt.setTime(parameterIndex, x);
    }

    public void setTimestamp(int parameterIndex, Timestamp x)
            throws SQLException {
        saveParam(parameterIndex, x);
        cstmt.setTimestamp(parameterIndex, x);
    }

    public void setDate(int parameterIndex, Date x, Calendar cal)
            throws SQLException {
        saveParam(parameterIndex, x);
        cstmt.setDate(parameterIndex, x, cal);
    }

    public void setTime(int parameterIndex, Time x, Calendar cal)
            throws SQLException {
        saveParam(parameterIndex, x);
        cstmt.setTime(parameterIndex, x, cal);
    }

    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
            throws SQLException {
        saveParam(parameterIndex, x);
        cstmt.setTimestamp(parameterIndex, x, cal);
    }

    public int getFetchDirection() throws SQLException {
        return cstmt.getFetchDirection();
    }

    public int getFetchSize() throws SQLException {
        return cstmt.getFetchSize();
    }

    public int getMaxFieldSize() throws SQLException {
        return cstmt.getMaxFieldSize();
    }

    public int getMaxRows() throws SQLException {
        return cstmt.getMaxRows();
    }

    public int getQueryTimeout() throws SQLException {
        return cstmt.getQueryTimeout();
    }

    public int getResultSetConcurrency() throws SQLException {
        return cstmt.getResultSetConcurrency();
    }

    public int getResultSetHoldability() throws SQLException {
        return cstmt.getResultSetHoldability();
    }

    public int getResultSetType() throws SQLException {
        return cstmt.getResultSetType();
    }

    public int getUpdateCount() throws SQLException {
        return cstmt.getUpdateCount();
    }

    public void cancel() throws SQLException {
        cstmt.cancel();
    }

    public void clearBatch() throws SQLException {
        cstmt.clearBatch();
    }

    public void clearWarnings() throws SQLException {
        cstmt.clearWarnings();
    }

    public void close() throws SQLException {
		try {
			cstmt.close();
		} catch (SQLException e) {
			throw e;
		}
    }

    public boolean getMoreResults() throws SQLException {
        return cstmt.getMoreResults();
    }

    public int[] executeBatch() throws SQLException {
		LStopWatch watch = new LStopWatch();
		boolean isError = false;
		try {
			return cstmt.executeBatch();
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
    }

    public void setFetchDirection(int direction) throws SQLException {
        cstmt.setFetchDirection(direction);
    }

    public void setFetchSize(int rows) throws SQLException {
        cstmt.setFetchSize(rows);
    }

    public void setMaxFieldSize(int max) throws SQLException {
        cstmt.setMaxFieldSize(max);
    }

    public void setMaxRows(int max) throws SQLException {
        cstmt.setMaxRows(max);
    }

    public void setQueryTimeout(int seconds) throws SQLException {
        cstmt.setQueryTimeout(seconds);
    }

    public boolean getMoreResults(int current) throws SQLException {
        return cstmt.getMoreResults(current);
    }

    public void setEscapeProcessing(boolean enable) throws SQLException {
        cstmt.setEscapeProcessing(enable);
    }

    public int executeUpdate(String sql) throws SQLException {
		LStopWatch watch = new LStopWatch();
		int result = 0;
		boolean isError = false;
		try {
			result = cstmt.executeUpdate(sql);
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
		return result;
    }

    public void addBatch(String sql) throws SQLException {
    	Logger.getLogger(getClass()).debug("执行sql:"+sql);
        cstmt.addBatch(sql);
    }

    public void setCursorName(String name) throws SQLException {
        cstmt.setCursorName(name);
    }

    public boolean execute(String sql) throws SQLException {
		LStopWatch watch = new LStopWatch();
		boolean isError = false;
		try {
			return cstmt.execute(sql);
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
    }

    public int executeUpdate(String sql, int autoGeneratedKeys)
            throws SQLException {
		LStopWatch watch = new LStopWatch();
		int result = 0;
		boolean isError = false;
		try {
			result = cstmt.executeUpdate(sql, autoGeneratedKeys);
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
		return result;
    }

    public boolean execute(String sql, int autoGeneratedKeys)
            throws SQLException {
		LStopWatch watch = new LStopWatch();
		boolean isError = false;
		try {
			return cstmt.execute(sql, autoGeneratedKeys);
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
    }

    public int executeUpdate(String sql, int[] columnIndexes)
            throws SQLException {
		LStopWatch watch = new LStopWatch();
		int result = 0;
		boolean isError = false;
		try {
			result = cstmt.executeUpdate(sql, columnIndexes);
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
		return result;
    }

    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		LStopWatch watch = new LStopWatch();
		boolean isError = false;
		try {
			return cstmt.execute(sql, columnIndexes);
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
    }

    public Connection getConnection() throws SQLException {
		return new ConnectionAdapter(this.cstmt.getConnection());
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        return cstmt.getGeneratedKeys();
    }

    public ResultSet getResultSet() throws SQLException {
        return cstmt.getResultSet();
    }

    public SQLWarning getWarnings() throws SQLException {
        return cstmt.getWarnings();
    }

    public int executeUpdate(String sql, String[] columnNames)
            throws SQLException {
		LStopWatch watch = new LStopWatch();
		int result = 0;
		boolean isError = false;
		try {
			result = cstmt.executeUpdate(sql, columnNames);
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
		return result;
    }

    public boolean execute(String sql, String[] columnNames)
            throws SQLException {
		LStopWatch watch = new LStopWatch();
		boolean isError = false;
		try {
			return cstmt.execute(sql, columnNames);
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
    }

    public ResultSet executeQuery(String sql) throws SQLException {
		LStopWatch watch = new LStopWatch();
		boolean isError = false;
		try {
			return cstmt.executeQuery(sql); 
		} catch (SQLException se) {
			isError = true;
			Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed(),se);
			throw se;
		} finally {
			if (!isError)
				Logger.getLogger(getClass()).debug("执行sql:"+sql+"\n 耗时:"+watch.getElapsed());
		}
    }

}
