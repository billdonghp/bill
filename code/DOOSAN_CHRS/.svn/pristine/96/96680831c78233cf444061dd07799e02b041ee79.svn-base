package com.ait.ev.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.evs.dao.EvsCommonDAO;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.SimpleMap;
/***********************************************************************
 *   
 *   ****所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。   
 *   @copyright       Copyright: AIT
 *   @author         (shihuili@ait.net.cn) 
 *   @create-time   2015-1-14   上午08:16:58  
 *   @revision      1.0
 ***********************************************************************/
public class RegularlyEvMail {

	private static final Logger logger = Logger.getLogger(RegularlyEvMail.class);
	
	private  String cpnyAllNameForMail="";
	//private  String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
//	private String url = "http://portal.doosan.com" ;
	private String url = "http://10.40.128.28:8083/" ;
	
	public void RegularlyEvMail() throws Exception{	
		EvsCommonDAO evsCommonDAO = new EvsCommonDAO();
		SimpleMap parameterObject = new SimpleMap();
		
		List regularlySentEvMailInfoList=evsCommonDAO.regularlySentEvMailInfoList();
		System.out.println("****************EV Mail Running*****************Count:"+regularlySentEvMailInfoList.size());
		
		for(int i=0;i<regularlySentEvMailInfoList.size();i++){
			SimpleMap sm = (SimpleMap)regularlySentEvMailInfoList.get(i);
			try {
				String emailAddress=sm.getString("AFFIMOREMAIL");
				//String emailAddress="ait@doosan.com";
				
				if(emailAddress!=null){
				this.sendEvMail(emailAddress);
				evsCommonDAO.insertRegularlySentEvMail(sm);//插入发送记录
			    }
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	
	}
	public void sendEvMail(String emailAddress)throws Exception{
		cpnyAllNameForMail="斗山工程机械（中国）有限公司";		
		SimpleMap inputData = new SimpleMap();
		String emailTitle="月别工人评价提醒邮件";	
		StringBuffer content = new StringBuffer();

		content.append("尊敬的领导，您好！").append("<br>")
			   .append("截止今天，您仍未完成贵部门技术职员工本月的系统评价决裁，").append("<br>")
	           .append("21日24:00后数据将自动更新，逾期后系统将做未评价处理，").append("<br>")
	           .append("请点击链接，务必在更新前完成评价！").append("<br>")
	           .append("如有疑问，请联系相关法人HR担当。").append("<br>")
	           .append("谢谢~").append("<br>")
	           .append("----------------------------------")
		       .append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆</a>").append("<br><br>")
		   .append("本邮件为系统自动发送，请勿回复！").append("<br><br>"+cpnyAllNameForMail) ;
		
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);
		
		logger.info("定时发送邮件: " + emailTitle ) ;

		new Mail().sendMail(inputData) ;	
	}

}

