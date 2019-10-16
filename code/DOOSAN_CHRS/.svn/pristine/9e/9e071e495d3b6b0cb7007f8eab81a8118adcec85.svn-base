/*
 * @(#)DelSuspendComCmd.java 1.0 2007-1-5 下午02:01:32
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
import com.ait.hrm.bean.Suspension;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author aqing (eqing@ait.net.cn)
 * @Date 2007-1-5 下午02:01:32
 * @version 1.0
 * 
 */
public class DelSuspendComCmd extends HrmAbstractCommand {

    /* (non-Javadoc)
     * @see com.ait.hrm.servlet.HrmAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
//	删除休职信息
	String msg="保存成功!";
	
	
	HttpSession session = request.getSession(true);
	AdminBean admin = (AdminBean) session.getAttribute("admin");
	MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
	msg = messageSource.getMessage("alert.mutual.save_successfully");
	try {
	    String[] check;
	    check = request.getParameterValues("selectedTag");
	    List<Suspension> lSuspend = new ArrayList<Suspension>();
	    if (null != check) {
		for (int i = 0, j = check.length; i < j; i++) {
		    Suspension bean = new Suspension();
		    ObjectBindUtil.setFormBean(request, bean, "_" + check[i]);
		    lSuspend.add(bean);
		}
	    }
	    HrmServices services = HrmServices.getInstance();
	    services.delSuspension(lSuspend);
	    
	} catch (Exception e) {
	    Logger.getLogger(getClass()).error(e.toString());
	    msg="保存失败";
	    throw new GlRuntimeException("删除休职信息失败！", e);
	}
	//提示
	request.setAttribute("alert", msg);
	//跳转的新页
	request.setAttribute("url", "/hrmControlServlet?operation=searchSuspend&menu_code="+request.getParameter("menu_code"));
	return "/inc/alertMessage.jsp";
    }

}

