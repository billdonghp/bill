/*
 * @(#)ApplyWishCmd.java 1.0 2009-7-23 下午02:59:47
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.washhouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.StringUtil;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-23 下午02:59:47
 * @version 1.0
 * 
 */
public class ApplyWashCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ga.servlet.GaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		GaAffirm gaAffirm = new GaAffirm();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		List detailList = new ArrayList();
		SimpleMap result = null;
		Mail mailUtil = null;
		EssSysparam essSysparam = null;

		try {

			// bind apply parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			
			// bind apply detail parameter
			int size = parameterObject.getInt("maxRowNum");
			SimpleMap map;
			for (int i=0; i<=size; i++) {
				
				map = ObjectBindUtil.getData(request, "_"+i);
				if (map.keySet().size() > 0) {
					
					map.setString("adminId", admin.getAdminID());
					detailList.add(map);
				}
			}
			
			// apply wash
			parameterObject.set("detail", detailList);
			result = (SimpleMap)services.applyWash(parameterObject);
			
			MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
			String msg = messageSource.getMessage("alert.mutual.apply_successfully");
			
			request.setAttribute("alert", msg);
			request.setAttribute("url", "/gaControlServlet?operation=retrieveDataForApplyWash&menu_code=" + parameterObject.getString("menu_code"));
			
			essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
			if (essSysparam.isGaSendMail()) {
				
				// get first affimor mail
				parameterObject.setString("applyNO", result.getString("APPLY_NO"));
				parameterObject.setInt("applyLevel", 1);
				List affirmMailList = (List)services.getWashAffirmMail(parameterObject);
				if (affirmMailList != null && affirmMailList.size() > 0 ) {
					
					SimpleMap affirmObj = (SimpleMap)affirmMailList.get(0);
					
					// send mail
					mailUtil = new Mail();
					SimpleMap inputData = new SimpleMap();
					inputData.setString("主题", "洗衣申请");
					inputData.setString("申请人", affirmObj.getString("APPLY_NAME"));
					inputData.setString("申请时间", affirmObj.getString("APPLY_DATE"));
					inputData.setString("申请内容", StringUtil.checkNull(affirmObj.getString("REMARK"),""));
					mailUtil.gaSendMail(inputData, admin.getCpnyId(), affirmObj.getString("EMAIL"), "洗衣申请");
				}
			
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Apply wash Exception. ", e);
		}

		return "/inc/alertMessage.jsp";
	}

}

