/*
 * @(#)CodeUtil.java 1.0 2006-12-11 下午03:56:25
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sqlmap.util;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.commons.dao.CommonDAO;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-11 下午03:56:25
 * @version 1.0
 * 
 */
public class CodeUtilNotDsDy {

	private static CodeUtilNotDsDy instance = new CodeUtilNotDsDy();

	protected SimpleMap codes = new SimpleMap("common codes");

	private CodeUtilNotDsDy() {
		initCode();
	}

	/**
	 * <p>
	 * 读取Code信息，读入内存对象
	 * <p>
	 * 读取数据库中的数据,取出数据库中所有的数据，将数据放入
	 * <p>
	 * 全局的codes HashMap中，放入方法为
	 * <p>
	 * "PARENT_CODE"为键
	 * <p>
	 * SimpleMap(classData)为值
	 * <p>
	 * 放入classData的方法为
	 * <p>
	 * "CODE_NAME"为键，SimpleMap(codeMap)的值为值
	 * <p>
	 * 
	 *            数据库code纪录列表
	 * 
	 */
	public void initCode() {
	
		SimpleMap smp= new SimpleMap();
		CommonDAO commonDAO = new CommonDAO();
		List codesList = null;
		try {
			// get code list 
			codesList = (List) commonDAO.retrieveCodeListNotDsDy(smp);
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(e.toString());
		}
		if (codesList == null)
			return;
		Iterator codesIterator = codesList.iterator();

		String codeClass = "";
		SimpleMap classData = null;
		while (codesIterator.hasNext()) {
			SimpleMap codeMap = (SimpleMap) codesIterator.next();
			
			// create new classData map if parent code change
			if (!codeMap.get("PARENT_CODE").equals(codeClass)) {
				
				//add key:PARENT_CODE value:classData to codes map
				if (!codeClass.equals("")) {
					codes.put(codeClass, classData);
				}
				// get parent code name
				codeClass = codeMap.get("PARENT_CODE").toString();
				// create new classData map by parent code name
				classData = new SimpleMap(codeClass);
			}
			// add key:CODE_ID value:codeMap to classData map
			classData.put(codeMap.get("CODE_ID").toString(), codeMap);

		}
		// save clssData map to codes map
		codes.put(codeClass, classData);
	}

	public static CodeUtilNotDsDy getInstance() {
		return instance;
	}
	
	public static CodeUtilNotDsDy getInstance(String codeClass) {
		return instance;
	}

	/**
	 * 取得code的数据 根据属性codeClass和code来取得相应的数据值
	 * 
	 * @param codeClass
	 * @param code
	 * @param req
	 * @return 指定codeClass和code确定的数据
	 */
	public String getCodeName(String codeClass, String code,
			HttpServletRequest req) {

		SimpleMap classData = (SimpleMap) codes.get(codeClass);

		try {
				return ((SimpleMap) classData.get(code)).getString("CODE_NAME");
				
		} catch (NullPointerException ex) {
			Logger.getLogger(this.getClass()).error("Can not find code name : " + code);
			return code;
		}
	}

	/**
	 * get code map by code class
	 * @param codeClass
	 * @return SimpleMap
	 */
	public SimpleMap getCodeClass(String codeClass) {
		
		SimpleMap classData = (SimpleMap) codes.get(codeClass);
		return classData;
	}

	/**
	 * refresh codes, it must be called after CUD of codes
	 */
	public void refreshCodes() {
		synchronized (this) {
			if (codes != null) {
				codes.clear();
				initCode();
			}
		}
	}
}
