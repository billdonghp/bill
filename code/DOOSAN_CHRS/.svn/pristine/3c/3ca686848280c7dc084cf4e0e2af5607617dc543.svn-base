/*
 * @(#)CommonUtil.java 1.0 2009-6-25 上午11:26:07
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.util;

import com.ait.commons.dao.CommonDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-6-25 上午11:26:07
 * @version 1.0
 * 
 */
public class CommonUtil {

	private static CommonDAO commonDAO = new CommonDAO();
	
	public static String getPersonId(SimpleMap map) {
		
		String personId;
		
		try {
			personId = ((SimpleMap)commonDAO.retrievePersonId(map)).getString("PERSONID");
		} catch (Exception e) {
			
			throw new GlRuntimeException("Get person id Exception.", e);
		}
		return personId;
	}
}

