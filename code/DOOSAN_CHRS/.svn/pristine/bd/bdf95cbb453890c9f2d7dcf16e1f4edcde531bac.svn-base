/*
 * @(#)RetrieveDataForApplyPresentCmd.java 1.0 2009-7-14 下午07:00:15
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.present;

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
 * @Date 2009-7-14 下午07:00:15
 * @version 1.0
 * 
 */
public class RetrieveDataForApplyPresentCmd extends GaAbstractCommand {

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
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			// get affirmor
			List affirmor=gaAffirm.getAffirmor2(admin.getAdminID(),cpnyId, "PresentApply", essSysparam.isContainsOwner());

			request.setAttribute("affirmList", affirmor);
			request.setAttribute("personId", admin.getAdminID());
			request.setAttribute("empId", admin.getUsername());
			request.setAttribute("name", admin.getChineseName());
			request.setAttribute("dept", admin.getDepartment());
			request.setAttribute("cpnyId", cpnyId);
			
			String declaration = "";
			if("63000000".equals(admin.getCpnyId())){
				declaration="(对外礼品请在此页面中申请，领导决裁后进行礼品发放，如有紧急情况，请发送邮件并抄送管理部长及决裁者)";
			}
			request.setAttribute("declaration", declaration);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve data for apply present Exception. ", e);
		}

		return "/ga_apply_present.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}
