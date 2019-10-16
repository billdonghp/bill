/*
 * @(#)ConfirmPresentCmd.java 1.0 2009-7-17 下午06:16:21
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.present;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
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
 * @Date 2009-7-17 下午06:16:21
 * @version 1.0
 * 
 */
public class ConfirmPresentCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ga.servlet.GaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		Mail mailUtil = null;
		EssSysparam essSysparam = null;
		
		try {

			// bind affirm parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("confirmor", admin.getAdminID());
			
			List confirmList = new ArrayList();
			List<SimpleMap> applyList = new ArrayList();
			List detailList = new ArrayList();
			
			if (parameterObject.getString("op").equals("batch")) {
				
				// bind batch affirm parameter
				String rowNo[] = request.getParameterValues("rowNo");
				SimpleMap map;
				
				for(int i=0; i<rowNo.length; i++){
					
					map = ObjectBindUtil.getData(request, "_"+rowNo[i]);
					map.setString("SEQ_NO", map.getString("applyNo"));
					map.setString("confirmor", admin.getAdminID());
					map.setString("CONFIRM_OPITION", map.getString("opinion"));
					map.setString("CONFIRM_FLAG", parameterObject.getString("CONFIRM_FLAG"));
					map.setString("adminId", admin.getAdminID());
					applyList.add(map);
					
					Iterator e = map.keySet().iterator();
					while (e.hasNext()) {
						String key = (String) e.next();

						if (key.toString().indexOf("detailNo_&") > -1) {
							
							String no = key.toString().substring(10);
							SimpleMap map1 = new SimpleMap();
							map1.setString("detailNo", map.getString("detailNo_&"+no));
							map1.setString("QUENTITY", map.getString("QUENTITY_&"+no));
							map1.setString("adminId", admin.getAdminID());
							map1.setString("cpnyId", admin.getCpnyId());
							map1.setString("CONFIRM_FLAG", parameterObject.getString("CONFIRM_FLAG"));
							detailList.add(map1);
						}
					}
				}
				
				confirmList.add(applyList);
				confirmList.add(detailList);
				
			} else {
				
				applyList.add(parameterObject);
				
				SimpleMap map = ObjectBindUtil.getData(request, "_" + parameterObject.getString("indexNo"));
				Iterator e = map.keySet().iterator();
				while (e.hasNext()) {
					String key = (String) e.next();
			
					if (key.indexOf("detailNo_&") > -1) {
						
						String no = key.substring(10);
						SimpleMap map1 = new SimpleMap();
						map1.setString("detailNo", map.getString("detailNo_&"+no));
						map1.setString("QUENTITY", map.getString("QUENTITY_&"+no));
						map1.setString("adminId", admin.getAdminID());
						map1.setString("cpnyId", admin.getCpnyId());
						map1.setString("CONFIRM_FLAG", parameterObject.getString("CONFIRM_FLAG"));
						detailList.add(map1);
					}
				}
				
				confirmList.add(applyList);
				confirmList.add(detailList);
			}
			
			// confirm present
			services.confirmPresent(confirmList, parameterObject.getInt("CONFIRM_FLAG")==1?true:false);

			MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
			String msg = messageSource.getMessage("alert.mutual.confirm_successfully");
			
			request.setAttribute("alert", msg);
			request.setAttribute("url", "/gaControlServlet?operation=retrievePresentConfirmList&flag=ConfirmType001&menu_code=" + parameterObject.getString("menu_code"));
			// 发送邮件
			essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
			if (essSysparam.isGaSendMail()) {
				
				for(SimpleMap map : applyList) {
					
					String mailTitle = "礼品申请已通过";
					parameterObject.setString("applyNO", map.getString("SEQ_NO"));
					// 发送邮件给申请者
					parameterObject.setInt("applyLevel", 0);

					if(parameterObject.getInt("CONFIRM_FLAG") == 2)
						mailTitle = "礼品申请被否决";
					
					List affirmMailList = (List)services.getAffirmMail(parameterObject);
					if (affirmMailList != null && affirmMailList.size() > 0 ) {
					
						SimpleMap affirmObj = (SimpleMap)affirmMailList.get(0);
					
						// send mail
						mailUtil = new Mail();
						SimpleMap inputData = new SimpleMap();
						inputData.setString("主题", "礼品申请");
						inputData.setString("申请人", affirmObj.getString("APPLY_NAME"));
						inputData.setString("申请时间", affirmObj.getString("APPLY_DATE"));
						inputData.setString("申请内容", affirmObj.getString("PRESENT_OBJECT") + "  " + StringUtil.checkNull(affirmObj.getString("REASON"),""));
						mailUtil.gaSendMail(inputData, admin.getCpnyId(), affirmObj.getString("EMAIL"), mailTitle);
					}
				}
			}
				
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace();
			throw new GlRuntimeException("Confirm present Exception. ", e);
		}

		return "/inc/alertMessage.jsp";
	}

}

