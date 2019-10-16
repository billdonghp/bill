/**
 * @author qinxd
 * @date 2006-10-8
 */
package com.ait.web;

import com.ait.jdbc.core.SqlParameter;

/**
 * TODO: new class describing
 * @version 1.0
 */
public class SqlAndTitle {
	
	private String sql;
	
	private SqlParameter[] params;
	
	private String title;
	
	private String filename;

	public SqlAndTitle(){
		
	}

	public SqlAndTitle(String sql, String title, String filename){
		this.sql = sql;
		this.title = title;
		this.filename = filename;
	}

	public SqlAndTitle(String sql, SqlParameter[] params, String title, String filename){
		this.sql = sql;
		this.params = params;
		this.title = title;
		this.filename = filename;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public SqlParameter[] getParams() {
		return params;
	}

	public void setParams(SqlParameter[] params) {
		this.params = params;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
