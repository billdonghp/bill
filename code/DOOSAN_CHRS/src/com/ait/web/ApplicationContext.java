/*
 * @(#)ApplicationContext.java 1.0 2009-6-2 上午10:33:43
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-6-2 上午10:33:43
 * @version 1.0
 * 
 */
public class ApplicationContext implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final ThreadLocal<AdminBean> theadLocal = new ThreadLocal<AdminBean>();

	private static final Log logger = LogFactory.getLog(ApplicationContext.class);

	public static AdminBean getTheadLocal() {
		return theadLocal.get();
	}

	public static void setAdminBean(HttpServletRequest request, HttpServletResponse response) {

		try {
			AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
			theadLocal.set(admin);
			
		} catch (Exception e) {
			
			logger.error("Set local thread Exception: " + e);
			throw new GlRuntimeException("Set local thread Exception.",e);
		}
	}
}
