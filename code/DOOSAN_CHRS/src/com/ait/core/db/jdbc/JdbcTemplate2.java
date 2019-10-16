package com.ait.core.db.jdbc;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.ait.core.config.Configuration;
import com.ait.core.exception.GlRuntimeException;

/**
 * Connection-per-request
 * @author lizaijun
 *
 */
public class JdbcTemplate2 {

	private static JdbcTemplate2 jdbcTmpl;
	private static DataSource ds;
	private static final String providerClass = "/configuration/em2/datasource-provider";
	
	private JdbcTemplate2() {
		super();
		try{
		   String clazz = Configuration.getInstance().getString(providerClass,"");
		   IDataSourceProvider provider = (IDataSourceProvider)Thread.currentThread().getContextClassLoader().loadClass(clazz).newInstance();
		   ds = provider.getDataSouce();
		}
		catch(Exception ex){
			throw new GlRuntimeException("获取DataSouce失败,检测是否正确设置datasource-provider",ex);
		}
	}
	
	public static JdbcTemplate2 getTemplate(){
		if (jdbcTmpl == null)
			jdbcTmpl = new JdbcTemplate2();
	    return jdbcTmpl;
	}
	
	public ConnectionAdapter getConnection(){
		ConnectionAdapter cnn;
		try{
			cnn = new ConnectionAdapter(ds.getConnection());
			cnn.realSetAutoCommit(false);
		}
		catch(SQLException sex){
			throw new GlRuntimeException("获取Connection异常",sex);
		}
		return cnn;	
	}
	
	
}


