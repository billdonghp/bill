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
 *   @copyright       Copyright: AIT
 *   @author         (zhangji@ait.net.cn) 
 *   @create-time   2013-1-11   上午11:27:58   
 *   @revision      3.5
 ***********************************************************************/

public class RegularlyArModifySentMail {
	
	private static final Logger logger = Logger.getLogger(RegularlyArModifySentMail.class);
	
	private  String cpnyAllNameForMail="";
	//private  String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
//	private String url = "http://portal.doosan.com" ;
	private String url = "http://10.40.128.28:8083/" ;
	
	public void regularlyArModifySentMail() throws Exception{	
		EssCommonDAO essCommonDAO = new EssCommonDAO();
		SimpleMap parameterObject = new SimpleMap();
		
		
		List regularlySentArModifyEmpidInfoList=essCommonDAO.regularlySentArModifyEmpidInfoList(parameterObject);
		System.out.println("****************Ar Modify Mail Running*****************Count:"+regularlySentArModifyEmpidInfoList.size());
		
		for(int i=0;i<regularlySentArModifyEmpidInfoList.size();i++){
			SimpleMap sm = (SimpleMap)regularlySentArModifyEmpidInfoList.get(i);
			try {
				String emailAddress=sm.getString("AFFIMOREMAIL");
				//String emailAddress="ait@doosan.com";
				
				List regularlySentArModifyInfoList=essCommonDAO.regularlySentArModifyInfoList(sm);
				
				if(regularlySentArModifyInfoList.size()>0){
				this.sendEssOtMail(regularlySentArModifyInfoList,emailAddress);
			    }
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(regularlySentArModifyEmpidInfoList.size()>0){
			essCommonDAO.insertRegularlySentArModifyInfoRecords(parameterObject);//插入发送记录
		}
		
		
	
	}
	public void sendEssOtMail(List list,String emailAddress)throws Exception{
		cpnyAllNameForMail="斗山工程机械（中国）有限公司";		
		SimpleMap inputData = new SimpleMap();
		String emailTitle="未决裁考勤修改情况提醒";	
		StringBuffer content = new StringBuffer();
		
		//content.append("您好:").append("<br>").append("以下是未决裁考勤修改信息：.").append("<br><br><br>");
		for(int i=0;i<list.size();i++){
			SimpleMap sm = (SimpleMap)list.get(i);
			try {
				content.append("部门:").append(sm.getString("DEPTNAME")).append("<br>")
					   .append("姓名:").append(sm.getString("CHINESENAME")).append("<br>")
			           .append("考勤日期:").append(sm.getString("AR_DATE_STR1")).append("<br>")
			           .append("----------------------------------")
			           .append("<br>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		content.append("<br>");
		content.append("系统决裁页面位置:ESS系统 > 考勤决裁 > 考勤修改决裁");
		content.append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆</a>")
		   .append("<br><br>"+cpnyAllNameForMail) ;
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);
		
		logger.info("定时发送邮件: " + emailTitle ) ;

		new Mail().sendMail(inputData) ;	
	}

}
