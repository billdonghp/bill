package com.ait.web;

import java.util.Observable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ait.core.config.Configuration;
import com.ait.core.db.jdbc.IDataSourceProvider;
import com.ait.core.exception.GlRuntimeException;

public class TomcatDataSourceProvider implements IDataSourceProvider{

	private final String url = "/configuration/em2/jndi-datasource<default>/url";
	
	public TomcatDataSourceProvider() {
		super();
	}
	
	public DataSource getDataSouce() {
		try{
			String dsName = Configuration.getInstance().getString(url,"");
			Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:/comp/env");
            return (DataSource) env.lookup(dsName);
		}
		catch(Exception ex){
			throw new GlRuntimeException("获取数据源失败,请正确设置em2.xml/jndi-datasource/url",ex);
		}
	}
	
	public void update(Observable o, Object arg) {
		
		
	}

}
