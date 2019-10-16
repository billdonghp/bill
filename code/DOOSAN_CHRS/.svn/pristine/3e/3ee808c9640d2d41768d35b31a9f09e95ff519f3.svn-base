/*
 * @(#)AffirmPresent.java 1.0 2009-7-17 上午11:55:45
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.present;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.api.resultApi.DooPushAPI;
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
 * @Date 2009-7-17 上午11:55:45
 * @version 1.0
 * 
 */
public class AffirmPresentCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ga.servlet.GaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		List<SimpleMap> affirmList = new ArrayList();
		SimpleMap parameterObject;
		Mail mailUtil = null;
		EssSysparam essSysparam = null;
		
		try {

			// bind affirm parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("affirmOP", "Y");

			if (parameterObject.getString("op").equals("batch")) {
				
				// bind batch affirm parameter
			    String rowNo[] = request.getParameterValues("rowNo");
				SimpleMap map;
				
				for(int i=0; i<rowNo.length; i++){
					
					map = ObjectBindUtil.getData(request, "_"+rowNo[i]);
					map.setString("SEQ_NO", map.getString("applyNo"));
					map.setString("affirmNo", map.getString("affirmNo"));
					map.setString("AFFIRM_OPITION", map.getString("opinion"));
					map.setString("AFFIRM_FLAG", parameterObject.getString("AFFIRM_FLAG"));
					map.setString("adminId", admin.getAdminID());
					map.setString("affirmOP", "Y");
					affirmList.add(map);
				}
				
			} else {
				
				affirmList.add(parameterObject);
			}
			
			// affirm present
			services.affirmPresent(affirmList);

			MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
			String msg = messageSource.getMessage("alert.mutual.affirm_successfully");
			
			request.setAttribute("alert", msg);
			request.setAttribute("url", "/gaControlServlet?operation=retrievePresentAffirmList&affirmType=AffirmType001&menu_code=" + parameterObject.getString("menu_code"));
			
			// 发送邮件
			essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
			if (essSysparam.isGaSendMail()) {
			
				for(SimpleMap map : affirmList) {
				
					String mailTitle = "礼品申请";
					parameterObject.setString("applyNO", map.getString("SEQ_NO"));

					// 否决，发送邮件给申请者
					if(parameterObject.getInt("AFFIRM_FLAG") == 2) {
						parameterObject.setInt("applyLevel", 0);
						mailTitle = "礼品申请被否决";
					
					} else // 通过，发送给下级决裁者	
						parameterObject.setInt("applyLevel", Integer.parseInt(map.getString("AFFIRM_LEVEL"))+1);
					
					//申请意见
					String opinion = "";
					if(map.getString("opinion")!=null){
						opinion = map.getString("opinion");
					}

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
						inputData.setString("意见",opinion);
						mailUtil.gaSendMail(inputData, admin.getCpnyId(), affirmObj.getString("EMAIL"), mailTitle);

						//推送
						DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_PRESENT,map.getString("SEQ_NO"),map.getString("PERSON_ID"));

					}else{//礼品最后一级决裁完毕发送邮件通知XX人
						if("78000000".equals(admin.getCpnyId())){
						parameterObject.setInt("applyLevel", Integer.parseInt(map.getString("AFFIRM_LEVEL")));
                        List affirmMailLists = (List)services.getAffirmMail(parameterObject);
						SimpleMap affirmObj = (SimpleMap)affirmMailLists.get(0);
						mailUtil = new Mail();
						SimpleMap inputData = new SimpleMap();
						inputData.setString("主题", "礼品申请  决裁通过");
						inputData.setString("申请人", affirmObj.getString("APPLY_NAME"));
						inputData.setString("申请时间", affirmObj.getString("APPLY_DATE"));
						inputData.setString("申请内容", affirmObj.getString("PRESENT_OBJECT") + "  " + StringUtil.checkNull(affirmObj.getString("REASON"),""));
						mailUtil.gaSendMail(inputData, admin.getCpnyId(), "xueyan.ma@doosan.com", mailTitle);//马雪雁  2623519  xueyan.ma@doosan.com
//						mailUtil.gaSendMail(inputData, admin.getCpnyId(), "lingyan.du@doosan.com", mailTitle);//杜玲艳  ic0732695  lingyan.du@doosan.com
						}
					}
				}
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace();
			throw new GlRuntimeException("Affirm present Exception. ", e);
		}

		return "/inc/alertMessage.jsp";
	}

}

