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

public class RegularlyOtSentMail {
	
	private static final Logger logger = Logger.getLogger(RegularlyOtSentMail.class);
	
	private  String cpnyAllNameForMail="";
	//private  String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//ad登录验证
//	private String url = "http://portal.doosan.com" ;
	private String url = "http://10.40.128.28:8083/" ;
	
	public void regularlyOtSentMail() throws Exception{	
		EssCommonDAO essCommonDAO = new EssCommonDAO();
		SimpleMap parameterObject = new SimpleMap();
		
		//DICC
		List regularly780SentOtEmpidInfoList=essCommonDAO.regularly780SentOtEmpidInfoList(parameterObject);
		System.out.println("****************DICC Ot Mail Running*****************Count:"+regularly780SentOtEmpidInfoList.size());
		
		if(regularly780SentOtEmpidInfoList.size()>0){
			String cpnyId = "78000000";
			for(int i=0;i<regularly780SentOtEmpidInfoList.size();i++){
				SimpleMap sm = (SimpleMap)regularly780SentOtEmpidInfoList.get(i);
				try {
					String emailAddress=sm.getString("AFFIMOREMAIL");
					List regularlySentOtInfoList=essCommonDAO.regularly780SentOtInfoList(sm);
					
					if(regularlySentOtInfoList.size()>0){
					this.sendEssOtMail(regularlySentOtInfoList,emailAddress,cpnyId);
				    }	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//郑延敏ic9530128 yanmin.zheng@doosan.com 所有未决裁信息
			parameterObject.set("AFFIMORPERSONID", "");
			List regularlySentOtInfoList=essCommonDAO.regularly780SentOtInfoList(parameterObject);
			if(regularlySentOtInfoList.size()>0){
				String emailAddress="yanmin.zheng@doosan.com";
				//this.sendEssOtMail(regularlySentOtInfoList,emailAddress,cpnyId);  //取消 给常务发送邮件
				
				parameterObject.set("cpnyId", cpnyId);
				essCommonDAO.insertRegularlySentOtInfoRecords(parameterObject);//插入发送记录
			}
			
		}
		
		//DISC
		List regularly610SentOtEmpidInfoList=essCommonDAO.regularly610SentOtEmpidInfoList(parameterObject);
		System.out.println("****************DISC Ot Mail Running*****************Count:"+regularly610SentOtEmpidInfoList.size());
		
		if(regularly610SentOtEmpidInfoList.size()>0){
			String cpnyId = "61000000";
			for(int i=0;i<regularly610SentOtEmpidInfoList.size();i++){
				SimpleMap sm = (SimpleMap)regularly610SentOtEmpidInfoList.get(i);
				try {
					String emailAddress=sm.getString("AFFIMOREMAIL");
					List regularlySentOtInfoList=essCommonDAO.regularly610SentOtInfoList(sm);
					
					if(regularlySentOtInfoList.size()>0){
					this.sendEssOtMail(regularlySentOtInfoList,emailAddress,cpnyId);
				    }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//韩铁洙ic9500190 cheolsoo.han@doosan.com 所有未决裁信息
			parameterObject.set("AFFIMORPERSONID", "");
			List regularlySentOtInfoList=essCommonDAO.regularly610SentOtInfoList(parameterObject);
			if(regularlySentOtInfoList.size()>0){
				String emailAddress="cheolsoo.han@doosan.com";
				this.sendEssOtMail(regularlySentOtInfoList,emailAddress,cpnyId);
				
				parameterObject.set("cpnyId", cpnyId);
				essCommonDAO.insertRegularlySentOtInfoRecords(parameterObject);//插入发送记录
			}
			
		}
		
	
	}
	
	
	
	
	public void sendEssOtMail(List list,String emailAddress,String cpnyId)throws Exception{

		if(cpnyId.equals("78000000")){
			cpnyAllNameForMail="斗山工程机械(中国)有限公司";
		}else if(cpnyId.equals("61000000")){
			cpnyAllNameForMail="斗山工程机械(苏州)有限公司";	
		}else{
			cpnyAllNameForMail="斗山工程机械";
		}
		
		SimpleMap inputData = new SimpleMap();
		String emailTitle="未决裁加班情况提醒";	
		StringBuffer content = new StringBuffer();
		
		
		content.append("您好:").append("<br>").append("以下是未决裁加班申请信息，请督促决裁以免影响生产性评价.").append("<br><br><br>");
		for(int i=0;i<list.size();i++){
			SimpleMap sm = (SimpleMap)list.get(i);
			try {
				content.append("部门:").append(sm.getString("DEPTFULLNAME")).append("<br>")
					   .append("姓名:").append(sm.getString("APPLYORNAME")).append("<br>")
			           .append("类型:").append(sm.getString("APPLY_TYPE")).append("<br>")
			           .append("班次:").append(sm.getString("OTDATE")).append("<br>")
			           .append("长度:").append(sm.getString("OTLENGTH")).append("<br>")
			           ///.append("未决裁人:").append(sm.getString("AFFIMORNAME")).append("<br>")
			           .append("----------------------------------")
			           .append("<br>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

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
