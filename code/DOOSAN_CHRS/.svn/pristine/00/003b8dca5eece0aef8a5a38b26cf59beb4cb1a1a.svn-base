/*
 * @(#)NavigationMapper.java 1.0 2006-12-18 上午10:56:06
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sqlmap.util;

import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-18 上午11:00:50
 * @version 1.0fdjfdl
 * 
 */
public class NavigationMapper {

	private static final Logger logger = Logger.getLogger(NavigationMapper.class);

	private UserConfiguration userConfig;

	private ReadXMLUtil read;

	private String navigationFilePath;

	private static final String defaultSysFile = "/system.properties";

	private static final String defaultNavigationConfig = "navigation.hr.path";

	public NavigationMapper() {
		init(null, null, null);
	}

	public NavigationMapper(String webPath) {
		init(webPath, null, null);
	}

	public NavigationMapper(String webPath, String sysFilePath) {
		init(webPath, sysFilePath, null);
	}

	public NavigationMapper(String webPath, String sysFilePath, String navigationConfig) {
		init(webPath, sysFilePath, navigationConfig);
	}

	// load navigation config file
	private void init(String webPath, String sysFilePath, String navigationConfig) {
		String segmentPath;
		try {
			// load system properties file
			if (sysFilePath == null || sysFilePath.equals("")) {
				userConfig = UserConfiguration.getInstance(defaultSysFile);
			} else {
				userConfig = UserConfiguration.getInstance(sysFilePath);
			}
			// get navigation path
			if (navigationConfig == null || navigationConfig.equals("")) {
				segmentPath = userConfig.getString(defaultNavigationConfig);
			} else {
				segmentPath = userConfig.getString(navigationConfig);
			}
			// construct navigation file path
			if (webPath == null || webPath.equals("")) {
				navigationFilePath = segmentPath;
			} else {
				navigationFilePath = this.getFullPath(webPath, segmentPath);
			}
			// construct ReadXMLUtil object
			   
			read = ReadXMLUtil.getInstance(navigationFilePath);
		} catch (Exception e) {
			logger.error("load navigation path fail.");
			throw new GlRuntimeException("load navigation path fail.", e);
		}
	}

	/**
	 * get map object by map paramter
	 * 
	 * @param mapParam
	 * @return object
	 */
	public Object getMapObjectByMapParam(String mapParam) {
		Object result;
		String commandName = null;
		try {    
			commandName = read.getElement("/navigation-mapper/operation[@name=\"" + mapParam + "\"]/command");
			result = Class.forName(commandName).newInstance();      
		} catch (ClassNotFoundException e) {
			logger.error("couldn't found class [[" + commandName + "]].");
			throw new GlRuntimeException("couldn't found class [[" + commandName + "]].", e);
		} catch (InstantiationException e) {
			logger.error("couldn't instantiate a object of type [[" + commandName + "]].");
			throw new GlRuntimeException("couldn't instantiate a object of type [[" + commandName + "]].", e);
		} catch (Exception e) {
			logger.error("get map object by param [[" + mapParam + "]] fail.");
			throw new GlRuntimeException("get map object by param [[" + mapParam + "]] fail.", e);
		}
		return result;
	}

	/**
	 * get map value by map paramter
	 * 
	 * @param mapParam
	 * @return String
	 */
	public String getMapValueByMapParam(String mapParam) {
		String result;
		try {
			result = read.getElement("/navigation-mapper/operation[@name=\"" + mapParam + "\"]/return-url");
		} catch (Exception e) {
			logger.error("get map value by param [[" + mapParam + "]] fail.");
			throw new GlRuntimeException("get map value by param [[" + mapParam + "]] fail.", e);
		}
		return result;
	}

	/**
	 * get map object by full xml path
	 * 
	 * @param XMLPath
	 * @return Object
	 */
	public Object getMapObjectByXMLPath(String XMLPath) {
		Object result;
		String commandName = null;
		try {
			commandName = read.getElement(XMLPath);
			result = Class.forName(commandName).newInstance();
		} catch (ClassNotFoundException e) {
			logger.error("couldn't found class [[" + commandName + "]].");
			throw new GlRuntimeException("couldn't found class [[" + commandName + "]].", e);
		} catch (InstantiationException e) {
			logger.error("couldn't instantiate a object of type [[" + commandName + "]].");
			throw new GlRuntimeException("couldn't instantiate a object of type [[" + commandName + "]].", e);
		} catch (Exception e) {
			logger.error("get map object by XML path [[" + XMLPath + "]] fail.");
			throw new GlRuntimeException("get map object by XML path [[" + XMLPath + "]] fail.", e);
		}
		return result;
	}

	/**
	 * get map value by full xml path
	 * 
	 * @param XMLPath
	 * @return String
	 */
	public String getMapValueByXMLPath(String XMLPath) {
		String result;
		try {
			result = read.getElement(XMLPath);
		} catch (Exception e) {
			logger.error("get map value by XML path [[" + XMLPath + "]] fail.");
			throw new GlRuntimeException("get map value by XML path [[" + XMLPath + "]] fail.", e);
		}
		return result;
	}

	/**
	 * get full path
	 * 
	 * @param webPath
	 * @param segmentPath
	 * @return String
	 */
	private String getFullPath(String webPath, String segmentPath) {
		if (!webPath.endsWith("/")) {
			if (!segmentPath.startsWith("/")) {
				return webPath + "/" + segmentPath;
			} else {
				return webPath + segmentPath;
			}
		} else {
			if (!segmentPath.startsWith("/")) {
				return webPath + segmentPath;
			} else {
				return (webPath + segmentPath).replaceAll("//", "/");
			}
		}
	}
}
