/*
 * @(#)ReadXMLUtil.java 1.0 2006-12-15 下午06:31:50
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sqlmap.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ait.core.exception.GlRuntimeException;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-15 上午11:54:50
 * @version 1.0
 * 
 */
public class ReadXMLUtil {

	private static ReadXMLUtil instance;

	private UserConfiguration userConfig;

	private String configPath = null;

	private File file;

	private SAXReader saxReader;

	private Document document;

	private static final Logger logger = Logger.getLogger(ReadXMLUtil.class);

	private ReadXMLUtil(String configPath) {

		saxReader = new SAXReader();

		// userConfig = UserConfiguration.getInstance("/system.properties");

		// configPath = userConfig.getString(mark);

		// configPath = configPath + "/" + mark;

		this.configPath = configPath;

		if (validate(configPath)) {
			try {
				document = saxReader.read(file);
			} catch (DocumentException e) {

				logger.error("Create Document object fail for " + configPath
						+ ".");
				throw new RuntimeException("Create Document object fail for "
						+ configPath + ".", e);
			}
		}
	}

	/**
	 * get ReadXMLUtil instance
	 * 
	 * @param configPath
	 * @return ReadXMLUtil
	 */
	public static ReadXMLUtil getInstance(String configPath) {

		instance = new ReadXMLUtil(configPath);
		return instance;
	}

	/**
	 * validate wether file exist
	 * 
	 * @param fileName
	 * @return boolean
	 */
	private boolean validate(String fileName) {
		file = new File(fileName);
		return file.isFile();
	}

	/**
	 * get list of value for attribute
	 * 
	 * @param nodePath
	 * @return list
	 */
	public List getAttributeList(String nodePath, String attributeName) {

		ArrayList result = new ArrayList();
		String fullPath = null;

		// get full path
		fullPath = this.getFullPath(nodePath, attributeName);

		try {
			List list = document.selectNodes(fullPath);

			// throw exception if result is null
			if (list.size() == 0) {

				logger.error("request XML path or attribute[[ " + fullPath
						+ " ]]no found.");
				throw new GlRuntimeException(
						"No found path or attribute Exception.");
			}

			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {

				Attribute attribute = (Attribute) iterator.next();

				result.add(attribute.getValue());
			}
		} catch (Exception e) {

			throw new GlRuntimeException("get attribute list exception.", e);
		}
		return result;
	}

	/**
	 * get list of value for element
	 * 
	 * @param nodePath
	 * @return List
	 */
	public List getElementList(String nodePath) {

		ArrayList result = new ArrayList();

		try {

			List list = document.selectNodes(nodePath);

			// throw exception if list is null
			if (list.size() == 0) {
   
				logger
						.error("request XML path [[ " + nodePath
								+ " ]]no found.");
				throw new GlRuntimeException("No found path Exception.");
			}

			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {

				Element element = (Element) iterator.next();

				// validate wether have child element
				if (!element.isTextOnly()) {
					break;
				}
				result.add(element.getTextTrim());
			}

			// throw exception if result is null
			if (result.size() == 0) {

				logger.error("element [[" + nodePath
						+ "]] contain child element.");
				throw new GlRuntimeException(
						"Element contain child element Exception.");
			}

		} catch (Exception e) {

			throw new GlRuntimeException("get element list exception.", e);
		}
		return result;
	}

	/**
	 * get a element by node path
	 * 
	 * @param nodePath
	 * @return String
	 */
	public String getElement(String nodePath) {
  
		List elementList;

		try {
			// get element list by node path
			elementList = this.getElementList(nodePath);

			if (elementList.size() != 1) {

				logger.error("the nodePath [[" + nodePath + "]] exist multi.");
				throw new GlRuntimeException("Exist multi result Exception.");
			}

		} catch (Exception e) {

			throw new GlRuntimeException("get element exception.", e);
		}
		return elementList.get(0).toString();
	}

	/**
	 * get a attribute value by node path
	 * 
	 * @param nodePath
	 * @param attributeName
	 * @return string
	 */
	public String getAttribute(String nodePath, String attributeName) {

		List attributeList;

		try {
			// get attribute list by node path
			attributeList = this.getAttributeList(nodePath, attributeName);

			if (attributeList.size() != 1) {

				logger.error("the nodePath [[" + nodePath + "]] exist multi.");
				throw new GlRuntimeException("Exist multi result Exception.");
			}

		} catch (Exception e) {

			throw new GlRuntimeException("get attribute exception.", e);
		}
		return attributeList.get(0).toString();
	}

	/**
	 * get full path accord with dom4j
	 * 
	 * @param nodePath
	 * @param attributeName
	 * @return String
	 */
	private String getFullPath(String nodePath, String attributeName) {

		if (!nodePath.endsWith("/")) {

			if (!attributeName.startsWith("@")) {

				return nodePath + "/@" + attributeName;
			} else {

				return nodePath + "/" + attributeName;
			}
		} else {

			if (!attributeName.startsWith("@")) {

				return nodePath + "@" + attributeName;
			} else {

				return nodePath + attributeName;
			}
		}

	}

}
