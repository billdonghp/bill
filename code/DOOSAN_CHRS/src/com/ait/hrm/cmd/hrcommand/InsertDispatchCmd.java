/*
 * @(#)InsertDispatchCmd.java 1.0 2006-12-19 下午04:59:30
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.hrcommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.bean.Dispatch;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.business.SyCodeServices;
import com.ait.util.StringUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author hue (sunhui@ait.net.cn)
 * @Date 2006-12-19 下午04:59:30
 * @version 1.0
 * 
 */

public class InsertDispatchCmd extends HrmAbstractCommand {

    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	//派遣信息
	String msg = "保存成功!";
	
	
	HttpSession session = request.getSession(true);
	AdminBean admin = (AdminBean) session.getAttribute("admin");
	MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
	msg = messageSource.getMessage("alert.mutual.save_successfully");
	try {
	    String transCode = StringUtil.checkNull(request.getParameter("transCode"));
	    String[] check;
	    check = request.getParameterValues("selectedTag");
	    List<Dispatch> lDispatch = new ArrayList<Dispatch>();
	    if (null != check) {
		for (int i = 0, j = check.length; i < j; i++) {
		    Dispatch bean = new Dispatch();
		    ObjectBindUtil.setFormBean(request, bean, "_" + check[i]);
		    lDispatch.add(bean);
		}
	    }
	    HrmServices services = HrmServices.getInstance();

	    // 期满本职
	    if (transCode.equals(SyCodeServices.getExpireRehabCode())) {
		services.updateDispatch(lDispatch);
	   // 派遣
	    } else {
		services.insertDispatch(lDispatch);
	    }

	} catch (Exception e) {
	    Logger.getLogger(getClass()).error(e.toString());
	    msg = "保存失败";
	    throw new GlRuntimeException("派遣失败！", e);
	}
	//提示
	request.setAttribute("alert", msg);
	//跳转的新页
	request.setAttribute("url",
		"/hrmControlServlet?operation=viewDispatch&menu_code="
			+ request.getParameter("menu_code"));
	return "/inc/alertMessage.jsp";
	//return "/hrm_dl_suspend.jsp";
    }

}
