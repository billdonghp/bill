package com.ait.ess.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ess.business.EssServices;
import com.ait.ess.dao.common.EssCommonDAO;
import com.ait.ess.servlet.EssAddCommand;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.task.SparateDataProcessor;
import com.ait.web.ApplicationContext;

/***********************************************************************
 *   
 *   ****所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。   
 *   @copyright     Copyright: AIT
 *   @author        skulls(yangfan9336@gmail.com) 
 *   @create-time   2009-11-30   下午02:17:54   
 *   @revision      1.0
 ***********************************************************************/

public class RegularlySentMail {
	
	private static final Logger logger = Logger.getLogger(RegularlySentMail.class);
	
	private  String cpnyAllNameForMail="";
	//private  String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
//	private String url = "http://portal.doosan.com" ;
	private String url = "http://10.40.128.28:8083/" ;
	
	public void regularlySentMail(){	
		EssCommonDAO essCommonDAO = new EssCommonDAO();
		SimpleMap parameterObject = new SimpleMap();
		List regularlySentMailInfoList=essCommonDAO.regularlySentMailInfoList(parameterObject);
		
		for(int i=0;i<regularlySentMailInfoList.size();i++){
			SimpleMap sm = (SimpleMap)regularlySentMailInfoList.get(i);
			try {
				this.sendLeaveMail(sm);
				essCommonDAO.insertRegularlySentMailInfo(sm);
				essCommonDAO.updateRegularlySentMailCounts(sm);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void sendLeaveMail(SimpleMap sm)throws Exception{
		cpnyAllNameForMail="斗山工程机械（山东）有限公司";		
		SimpleMap inputData = new SimpleMap();
		String emailTitle="休假申请，再次提醒";	
		StringBuffer content = new StringBuffer();
		String emailAddress=sm.getString("AFFIMOREMAIL");		
			content.append("申请人:").append(sm.getString("APPLYORNAME"))
			       .append("<br><br>").append("主题:").append(sm.getString("APPLY_TYPE")).append("<br><br>")
			       .append("申请时间:").append(sm.getString("APPLY_DATE"));
			content.append("<br><br>").append("开始时间:").append(sm.getString("FROM_DATE"))
				   .append("<br><br>").append("结束时间:").append(sm.getString("TO_DATE"));
			content.append("<br><br>").append("休假原因:").append(sm.getString("LEAVE_REASON"));
			content.append("<br><br>");
		content.append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆系统决裁</a>")
		   .append("<br><br>"+cpnyAllNameForMail) ;
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);
		
		logger.info("定时发送邮件: " + emailTitle + " " + sm.getString("APPLYORNAME")) ;

		new Mail().sendMail(inputData) ;	
	}

}
