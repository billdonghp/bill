package com.ait.core.db.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import com.ait.core.config.Configuration;
import com.ait.core.db.jdbc.ConnectionAdapter;
import com.ait.core.exception.GlRuntimeException;

/**
 * Connection-per-request
 * @author lizaijun
 *
 */
public class JdbcTemplate {

	private static JdbcTemplate jdbcTmpl;
	private static ThreadLocal localThread;
	private HashMap dsPool;
	private static final String providerClass = "/configuration/em2/datasource-provider";
	private IDataSourceProvider provider;
	private Object dsKey = new Object();
	
	private JdbcTemplate() {
		super();
		dsPool = new HashMap();
		localThread = new ThreadLocal();
		try{
		   String clazz = Configuration.getInstance().getString(providerClass,"");
		   provider = (IDataSourceProvider)Thread.currentThread().getContextClassLoader().loadClass(clazz).newInstance();
		}
		catch(Exception ex){
            ex.printStackTrace();
			throw new GlRuntimeException("必须正确设置datasource-provider",ex);
		}
	}
	
	/**
	 * single instance
	 * @return JdbcTemplate
	 */
	public static JdbcTemplate getTemplate(){
		if (jdbcTmpl == null)
			jdbcTmpl = new JdbcTemplate();
	    return jdbcTmpl;
	}
	
	/**
	 * get Connection and one-per-request
	 * @param create true:当前线程如果没有连接则创建
	 * @return Connection
	 */
	public Connection getConnection(boolean create){
		DataSource ds;
		synchronized(this){
			ds = (DataSource)dsPool.get(dsKey);
			if (ds == null){
				ds = provider.getDataSouce();
				dsPool.put(dsKey,ds);
			}	
		}
		ConnectionAdapter cnnAdpt = (ConnectionAdapter)localThread.get();
		if ((cnnAdpt == null) && create){
			try{
				cnnAdpt = new ConnectionAdapter(ds.getConnection());
				cnnAdpt.realSetAutoCommit(false);
				localThread.set(cnnAdpt);
			}
			catch(Exception ex){
				throw new GlRuntimeException("获取Connection异常",ex);
			}
		}
		return cnnAdpt;	
	}
	
	public Connection getConnection(){
		return getConnection(true);
	}
	
	public static boolean isUsed(){
		return (jdbcTmpl != null);
	}
	
	public void close() throws SQLException {
		ConnectionAdapter cnnAdpt = (ConnectionAdapter)localThread.get();
		if (cnnAdpt != null){
			cnnAdpt.realClose();
			localThread.set(null);
		}
	}	
}


