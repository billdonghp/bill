package com.ait.core.db.jdbc;

import java.util.Observer;

import javax.sql.DataSource;

public interface IDataSourceProvider extends Observer{
	public DataSource getDataSouce();
}
