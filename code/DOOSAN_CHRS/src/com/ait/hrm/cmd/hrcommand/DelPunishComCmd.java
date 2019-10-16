/*
 * @(#)DelPunishComCmd.java 1.0 2007-1-5 下午01:59:42
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
import com.ait.hrm.bean.PunishMent;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sy.bean.AdminBean;

public class DelPunishComCmd extends HrmAbstractCommand {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
//	删除惩戒信息
	String msg="保存成功!";
	
	
	HttpSession session = request.getSession(true);
	AdminBean admin = (AdminBean) session.getAttribute("admin");
	MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
	msg = messageSource.getMessage("alert.mutual.save_successfully");
	try {
	    String[] check;
	    check = request.getParameterValues("selectedTag");
	    List<PunishMent> lPunishMent = new ArrayList<PunishMent>();
	    if (null != check) {
		for (int i = 0, j = check.length; i < j; i++) {
		    PunishMent bean = new PunishMent();
		    ObjectBindUtil.setFormBean(request, bean, "_" + check[i]);
		    lPunishMent.add(bean);
		}
	    }
	    HrmServices services = HrmServices.getInstance();
	    services.delPunishMent(lPunishMent);
	    
	} catch (Exception e) {
	    Logger.getLogger(getClass()).error(e.toString());
	    msg="保存失败";
	    throw new GlRuntimeException("删除惩戒信息失败！", e);
	}
	//提示
	request.setAttribute("alert", msg);
	//跳转的新页
	request.setAttribute("url", "/hrmControlServlet?operation=searchPunishMent&menu_code="+request.getParameter("menu_code"));
	return "/inc/alertMessage.jsp";
    }

}

