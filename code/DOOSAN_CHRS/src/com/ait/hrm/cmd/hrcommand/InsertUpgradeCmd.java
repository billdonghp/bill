/*
 * @(#)InsertUpgradeCmd.java 1.0 2006-12-19 下午05:18:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.hrcommand;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.bean.InternalExperience;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author hue (sunhui@ait.net.cn)
 * @Date 2006-12-19 下午05:18:53
 * @version 1.0
 * 
 */
public class InsertUpgradeCmd extends HrmAbstractCommand {

    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	//添加调动信息
	String msg="保存成功!";
	
	HttpSession session = request.getSession(true);
	AdminBean admin = (AdminBean) session.getAttribute("admin");
	MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
	msg = messageSource.getMessage("alert.mutual.save_successfully");
	try {
	    String[] check;
	    check = request.getParameterValues("selectedTag");
	    List<InternalExperience> lExpInside = new ArrayList<InternalExperience>();
	    if (null != check) {
		for (int i = 0, j = check.length; i < j; i++) {
		    InternalExperience exp = new InternalExperience();
		    ObjectBindUtil.setFormBean(request, exp, "_" + check[i]);
		    lExpInside.add(exp);
		}
	 }   
	    HrmServices services = HrmServices.getInstance();
	    services.insertExpInsideAndSaveOldEmpProperty(lExpInside);  
	    
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(Calendar.getInstance().getTime());
	    SimpleMap expMap = new SimpleMap();
		expMap.put("START_DATE_LESS_THAN", date);
		expMap.put("ACTIVITY", "0");                      
		services.batchExeExpInside(expMap);  
		Logger.getLogger(getClass()).debug("-------------调动发令完成------------");
		
		
	} catch (Exception e) {
	    Logger.getLogger(getClass()).error(e.toString());
	    msg="保存失败";
	    throw new GlRuntimeException("添加调动信息失败！", e);
	}
	//提示
	request.setAttribute("alert", msg);
	//跳转的新页
	request.setAttribute("url", "/hrmControlServlet?operation=viewUpgrade&menu_code="+request.getParameter("menu_code"));
	return "/inc/alertMessage.jsp";
	//return "/hrm/hrm_dl_upgrade.jsp";
    }

}
