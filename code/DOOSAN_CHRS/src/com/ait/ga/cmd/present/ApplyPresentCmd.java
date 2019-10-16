/*
 * @(#)ApplyPresentCmd.java 1.0 2009-7-15 上午08:56:57
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
import com.ait.ess.bean.EssAffirmor;
import com.ait.ga.bean.GaAffirmList;
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


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-15 上午08:56:57
 * @version 1.0
 * 
 */
public class ApplyPresentCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ga.servlet.GaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GaServices services = new GaServices();
		GaAffirm gaAffirm = new GaAffirm();
		GaAffirmList gaAffirmList = new GaAffirmList();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		List detailList = new ArrayList();
		SimpleMap result = null;
		List presentnameList = new ArrayList();
		String presentname = null;
		Mail mailUtil = null;
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());

		try {

			// bind apply parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			
			// bind apply detail parameter
			Double amount = 0.0;
			int unitPriceflag = 0;
			String PRESENT_TYPE = null;
			int size = parameterObject.getInt("maxRowNum");
			SimpleMap map;
			int num = 0;
			String presentId = null;
			for (int i=0; i<=size; i++) {
				
				map = ObjectBindUtil.getData(request, "_"+i);
				if (map.keySet().size() > 0) {
					map.setString("adminId", admin.getAdminID());
					detailList.add(map);
					amount = amount + Double.parseDouble(request.getParameter("amountInput_"+i));
					PRESENT_TYPE=request.getParameter("PRESENT_TYPE_"+i);//礼品类型（公司礼品、婚礼）
					num += Integer.valueOf( request.getParameter("QUENTITY_"+i));
					presentId = parameterObject.getString("PRESENT_ID1_"+i);
					if(presentId==null || presentId.equals("")){
						presentId = parameterObject.getString("PRESENT_ID2_"+i);
						if(presentId== null || presentId.equals("")){
							presentId = parameterObject.getString("PRESENT_ID3_"+i);
						}
					}
					System.out.println();
					parameterObject.put("presentId", presentId);
//					presentnameList= services.getPresentName(parameterObject);
					result =(SimpleMap) ((List)services.getPresentName(parameterObject)).get(0);
					presentname = result.getString("CODE_NAME");
//					if(Double.parseDouble(request.getParameter("priceTmp_"+i))>100){ //DICC 单价大于100  
//						unitPriceflag = 1;
//					}
				}
			}
			
			// get affirmor
//			List affirmor=gaAffirm.getAffirmor(admin.getAdminID(), "PresentApply", essSysparam.isContainsOwner());
			List affirmor = new ArrayList();
			String[] affirmorId = request.getParameterValues("affirmorId");
			String[] affirmorDuty = request.getParameterValues("affirmorDuty");
			String personId=admin.getPersonId();
			int jNo = 1;
			for(int i=0;i<affirmorId.length;i++){
				
				if (amount<=500){
					if (!affirmorDuty[i].equals("C_12005_43")){
						GaAffirmList vb = new GaAffirmList();
						int affirmFlag=0;
						if(personId.equals(affirmorId[i])){
							affirmFlag=1;
						}
						vb.setAffirmFlag(affirmFlag);
						vb.setAffirmLevel(jNo++);
						vb.setAffirmorId(affirmorId[i]);
						affirmor.add(vb);
					}
				}else{
					GaAffirmList vb = new GaAffirmList();
					int affirmFlag=0;
					if(personId.equals(affirmorId[i])){
						affirmFlag=1;
					}
					vb.setAffirmFlag(affirmFlag);
					vb.setAffirmLevel(i+1);
					vb.setAffirmorId(affirmorId[i]);
					affirmor.add(vb);
				}
			}
			
		
//			int j = 0;
//			int k = 0;
//			int g = 0;
//			if(PRESENT_TYPE.equals("PresentMarried") && admin.getCpnyId().equals("78000000")){
//				for(int i = 0 ; i < affirmor.size() ; ++i)//DICC 如果是结婚礼品申请将决裁者刘孟才4482567删除保留尹青芝1463896,删除杜玲艳1463784
//				{ 
//					gaAffirmList= (GaAffirmList)affirmor.get(i) ;
//
//					if(gaAffirmList.getAffirmorId().equals("4482567")){
//						affirmor.remove(i);
//						k++;
//						i--;
//					}
//					if(k>0){
//						gaAffirmList.setAffirmLevel(gaAffirmList.getAffirmLevel()-1);//决裁级别递减
//					}
//					
//					if(gaAffirmList.getAffirmorId().equals("1463784")){
//						affirmor.remove(i);
//						g++;
//						i--;
//					}
//					if(g>0){
//						gaAffirmList.setAffirmLevel(gaAffirmList.getAffirmLevel()-1);//决裁级别递减
//					}
//				}
//			}else 
//				if(PRESENT_TYPE.equals("PresentCompany") && admin.getCpnyId().equals("78000000")){
//					for(int i = 0 ; i < affirmor.size() ; ++i)//DICC 如果是公司礼品申请将决裁者尹青芝1463896删除保留刘孟才4482567
//					{ 
//						gaAffirmList= (GaAffirmList)affirmor.get(i) ;
//
//						if(gaAffirmList.getAffirmorId().equals("1463896")){
//							affirmor.remove(i);
//							g++;
//							i--;
//						}
//						if(g>0){
//							gaAffirmList.setAffirmLevel(gaAffirmList.getAffirmLevel()-1);//决裁级别递减
//						}
//					}
//				}
//			
//			if((amount<=5000 && unitPriceflag ==0) && admin.getCpnyId().equals("78000000")){
//				for(int i = 0 ; i < affirmor.size() ; ++i)//DICC 总金额小于5000  排除5303402朴赞赫  (单价小于100 此功能140107已取消，只参考总价)
//				{ 
//					gaAffirmList= (GaAffirmList)affirmor.get(i) ;
//
//					if(gaAffirmList.getAffirmorId().equals("5303402")){
//						affirmor.remove(i);
//						j++;
//						i--;
//					}
//					if(j>0){
//						gaAffirmList.setAffirmLevel(gaAffirmList.getAffirmLevel()-1);//决裁级别递减
//					}
//				}
//			}
//			
//			//新添加  DISD礼品决裁线
//			if((amount<=5000 && unitPriceflag ==0) && admin.getCpnyId().equals("63000000")){
//			for(int i = 0 ; i < affirmor.size() ; ++i)//DISD 总金额小于5000  排除5303402李贤秀  (单价小于100 此功能140107已取消，只参考总价)
//			{ 
//				gaAffirmList= (GaAffirmList)affirmor.get(i) ;
//
//				if(gaAffirmList.getAffirmorId().equals("5150540")){
//					affirmor.remove(i);
//					j++;
//					i--;
//				}
//				if(j>0){
//					gaAffirmList.setAffirmLevel(gaAffirmList.getAffirmLevel()-1);//决裁级别递减
//				}
//			}
//		}
			
			
			// apply present
			parameterObject.set("affirm", affirmor);
			parameterObject.set("detail", detailList);
			result = (SimpleMap)services.applyPresent(parameterObject);
			
			MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
			String msg = messageSource.getMessage("alert.mutual.apply_successfully");
			
			request.setAttribute("alert", msg);
			request.setAttribute("url", "/gaControlServlet?operation=retrieveDataForApplyPresent&menu_code=" + parameterObject.getString("menu_code"));
			
			if (essSysparam.isGaSendMail() && PRESENT_TYPE.equals("PresentCompany")) {//公司礼品申请完邮件通知王舒婷
				
				// get first affimor mail
				parameterObject.setString("applyNO", result.getString("APPLY_NO"));
				parameterObject.setInt("applyLevel", 1);
				SimpleMap affirmObj = (SimpleMap)((List)services.getAffirmMail(parameterObject)).get(0);
				
				// send mail
				mailUtil = new Mail();
				SimpleMap inputData = new SimpleMap();
				inputData.setString("主题", "礼品申请");
				inputData.setString("申请人", affirmObj.getString("APPLY_NAME"));
				inputData.setString("申请时间", affirmObj.getString("APPLY_DATE"));
				inputData.setString("申请内容",  affirmObj.getString("REASON"));
				inputData.setString("申请名称", presentname);
				inputData.setString("申请数量", String.valueOf(num));
//				mailUtil.gaSendMail(inputData, admin.getCpnyId(), "shuting.wang@doosan.com", "礼品刚刚申请");//礼品申请完邮件通知王舒婷shuting.wang@doosan.com
				mailUtil.gaSendMail(inputData, admin.getCpnyId(), affirmObj.getString("EMAIL"), "礼品申请");
			}	
			if (essSysparam.isGaSendMail() && PRESENT_TYPE.equals("PresentMarried")) {//新婚礼品申请完邮件通知尹青芝
				
				// get first affimor mail
				parameterObject.setString("applyNO", result.getString("APPLY_NO"));
				parameterObject.setInt("applyLevel", 1);
				SimpleMap affirmObj = (SimpleMap)((List)services.getAffirmMail(parameterObject)).get(0);
				
				// send mail
				mailUtil = new Mail();
				SimpleMap inputData = new SimpleMap();
				inputData.setString("主题", "新婚礼品申请");
				inputData.setString("申请人", affirmObj.getString("APPLY_NAME"));
				inputData.setString("申请时间", affirmObj.getString("APPLY_DATE"));
				inputData.setString("申请内容",  affirmObj.getString("REASON"));
				inputData.setString("申请名称",presentname);
				inputData.setString("申请数量", amount.toString());
				mailUtil.gaSendMail(inputData, admin.getCpnyId(), "qingzhi.yin@doosan.com", "新婚礼品刚刚申请");//尹青芝qingzhi.yin@doosan.com
				mailUtil.gaSendMail(inputData, admin.getCpnyId(), affirmObj.getString("EMAIL"), "新婚礼品申请");
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Apply present Exception. ", e);
		}

		return "/inc/alertMessage.jsp";
	}

}

