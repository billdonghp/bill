/*
 * @(#)RetrieveDataForApplyWishCmd.java 1.0 2009-7-23 下午03:01:12
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.washhouse;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-23 下午03:01:12
 * @version 1.0
 * 
 */
public class RetrieveDataForApplyWashCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ga.servlet.GaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.putToolbarInfo(request);
		GaServices services = new GaServices();
		GaAffirm gaAffirm = new GaAffirm();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		Object result = null;
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
		
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			// get affirmor
			List affirmor=gaAffirm.getAffirmor(admin.getAdminID(), "WashApply", essSysparam.isContainsOwner());
			
			List clothingList = services.retrieveClothingCode(parameterObject);

			request.setAttribute("affirmor", affirmor);
			request.setAttribute("clothingList", clothingList);
			request.setAttribute("personId", admin.getAdminID());
			request.setAttribute("empId", admin.getUsername());
			request.setAttribute("name", admin.getChineseName());
			request.setAttribute("dept", admin.getDepartment());
			String declaration = "";
			if("63000000".equals(admin.getCpnyId())){
				declaration="(送洗衣服前请在系统中进行申请)";
			}
			request.setAttribute("declaration", declaration);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve data for apply wash Exception. ", e);
		}

		return "/ga_wash_apply.jsp?menu_code=" + parameterObject.getString("menu_code");
	}

}

