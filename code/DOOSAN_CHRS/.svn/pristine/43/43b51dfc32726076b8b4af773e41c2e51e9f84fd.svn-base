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
public class RegularlyEvMydMail {

	private static final Logger logger = Logger.getLogger(RegularlyEvMydMail.class);
	
	private  String cpnyAllNameForMail="";
	//private  String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
//	private String url = "http://portal.doosan.com" ;
	private String url = "http://10.40.128.28:8083/" ;
	
	public void RegularlyEvMydMail() throws Exception{	
		EvsCommonDAO evsCommonDAO = new EvsCommonDAO();
		SimpleMap parameterObject = new SimpleMap();
		
		List regularlySentEvMailInfoList=evsCommonDAO.regularlySentEvMydMailInfoList();
		System.out.println("****************EV Mail Running*****************Count:"+regularlySentEvMailInfoList.size());
		String emailAddress ="ying.sun@doosan.com";
		//if(regularlySentEvMailInfoList.size()>0){
			for(int i=0;i<regularlySentEvMailInfoList.size();i++){
				SimpleMap sm = (SimpleMap)regularlySentEvMailInfoList.get(i);
				try {
					 emailAddress=sm.getString("AFFIMOREMAIL");
					//String emailAddress="ait@doosan.com";
					//emailAddress ="ying.sun@doosan.com";
					if(emailAddress!=null){
					this.sendEvMail(emailAddress);
					//evsCommonDAO.insertRegularlySentEvMail(sm);//插入发送记录
				    }
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//}
			emailAddress ="ying.sun@doosan.com";
	  this.sendEvMail(emailAddress);
		
	
	}
	public void sendEvMail(String emailAddress)throws Exception{
		cpnyAllNameForMail="斗山工程机械（中国）有限公司";		
		SimpleMap inputData = new SimpleMap();
		String emailTitle="参观者申请满意度录入提醒邮件";	
		StringBuffer content = new StringBuffer();

		content.append("您好").append("<br>")
			   .append("首先感谢您对管理部业务的大力支持！").append("<br>")
	           .append("现进行管理部接待业务服务满意度调查，希望您给予客观公正的评价，非常感谢！").append("<br>")
	           .append("请评价请登录E-MGR系统，点击行政管理->决裁情况->参观者情况，请按下图步骤操作，最后一定要点保存哦，谢谢！").append("<br>")
	           .append("<br><br>")
	           .append("<img src='http://10.40.128.28:8083/images/imagecanguanzhe.png' alt='操作步骤'>")
				.append("<br><br>")
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

