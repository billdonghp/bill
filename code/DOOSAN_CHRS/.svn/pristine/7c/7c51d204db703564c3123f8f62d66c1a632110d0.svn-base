/*
 * @(#)SQLMapConfigManager.java 1.0 2006-12-3
 *
 */
package com.ait.sqlmap.util;

import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.ait.core.config.ConfigurationException;
import com.ait.core.exception.GlRuntimeException;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-5 下午03:46:51
 * @version 1.0
 * 
 */
public class SQLMapConfigManager {

	private static final Logger logger = Logger
			.getLogger(SQLMapConfigManager.class);

	private static Hashtable sqlMapClients = new Hashtable();

	private UserConfiguration userConfig;

	private static final String defaultSysFile = "/system.properties";

	private static SQLMapConfigManager sqlMapManager;

	/**
	 * 
	 * 
	 */
	public SQLMapConfigManager() {

		init(null);
	}

	public SQLMapConfigManager(String sysFile) {

		init(sysFile);
	}

	public void init(String sysFile) {

		if (sysFile == null || sysFile.equals("")) {

			userConfig = UserConfiguration.getInstance(defaultSysFile);
		} else {

			userConfig = UserConfiguration.getInstance(sysFile);
		}

	}

	/**
	 * 
	 * @return SQLMapConfigManager
	 */
	public static SQLMapConfigManager getInstance() {
		if (sqlMapManager != null)
			return sqlMapManager;
		else
			return new SQLMapConfigManager();
	}

	/**
	 * 
	 * @param clientName
	 * @return
	 */
	public SqlMapClient getSqlMapClient(String clientName) {
		SqlMapClient client = (SqlMapClient) sqlMapClients.get(clientName);
		if (client == null) {
			try {
				client = createSqlMapClient(clientName);

			} catch (ConfigurationException ex) {

				logger.error("Create SqlMap Client fail. ");
				throw new GlRuntimeException("Create SqlMap Client fail. ", ex);
			} catch (IOException ex) {

				logger.error("Create SqlMap Client fail. ");
				throw new GlRuntimeException("Create SqlMap Client fail. ", ex);
			}
		}
		return client;
	}

	/**
	 * 
	 * @param clientName
	 * @return SqlMapClient
	 * @throws IOException
	 */
	private SqlMapClient createSqlMapClient(String clientName)
			throws IOException, ConfigurationException {
		String sqlmapConfig = userConfig.getString("sqlmap.conf." + clientName);
		SqlMapClient sqlMap = null;

		Reader reader = Resources.getResourceAsReader(sqlmapConfig);
		sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		if (sqlMap != null) {
			sqlMapClients.put(clientName, sqlMap);
		}
		return sqlMap;
	}
}
