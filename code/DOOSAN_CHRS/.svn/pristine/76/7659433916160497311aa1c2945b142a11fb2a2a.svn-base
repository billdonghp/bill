package com.ait.mail;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;

public class Mail {
	private static final Logger logger = Logger.getLogger(Mail.class);
	
	private UserConfiguration config ;
	
	private String user ;
	
	private String address ;
	
	private String password ;
	
	private String cpnyAllNameForMail ;	

//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	private String url = "http://10.40.128.28:8083/" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	
	//private String url="http://doodream.corp.doosan.com/portal/server.pt" ;
	
	public Mail() {   
		
		config = UserConfiguration.getInstance("/system.properties");		
	}
	
	public static void main(String[] args) {
		SimpleMap inputData = new SimpleMap() ;
		
		inputData.setString("RCVR_EMAIL_ADDR", "ait@doosan.com" ) ;
		
		inputData.setString("EMAIL_TITLE", "加班申请") ;
		
		inputData.setString("EMAIL_CONTNT", "申请人：张松峰<br><br> 主题：公休日加班<br><br> 申请时间：2007-11-30 <br><br> 开始时间：08:30<br><br> 结束时间：19:00<br><br><a href=\"http://172.16.225.240:9080/dic_login.jsp\" target=\"_blank\">点击此处进行决裁</a><br><br>斗山工程机械(中国)有限公司");
		
		try{
			//new Mail().sendMail(inputData);
		}
		catch(Exception e){
			e.printStackTrace() ;
		}
		
	}
	
	/**
	 * send mail
	 * 
	 * @throws Exception
	 */
	public void sendMail(SimpleMap inputData) {
		HtmlEmail mailSender = new HtmlEmail();
		
		logger.debug("Send Job is started...");
		//List<InternetAddress> addressList = new ArrayList<InternetAddress>();
		try {
			
			address = config.getString("email.address.info").trim();
			user = config.getString("email.user.info").trim();
			password = config.getString("email.password.info").trim();
			
			//addressList.add(new InternetAddress("ait@doosan.com"));
			
			logger.debug("into email...");
			// smtp host	mail.ait.net.cn   斗山-eipex05.corp.doosan.com
			//mailSender.setHostName("mail.ait.net.cn");
			//20150706 eipex05服务器修改为krrelay
			mailSender.setHostName("krrelay.corp.doosan.com");
			mailSender.setCharset("UTF-8");
			// 登陆邮件服务器的用户名 ait , 密码 123qwe$
			//mailSender.setAuthentication("zhangsongfeng@ait.net.cn", "111111");
			mailSender.setAuthentication(user, password);
			// 接收人
			//mailSender.addTo("ait@doosan.com");
			mailSender.addTo(inputData.getString("RCVR_EMAIL_ADDR")) ;
			mailSender.setFrom(address);//ait@doosan.com
			
			// 标题
			mailSender.setSubject(inputData.getString("EMAIL_TITLE"));
			// 邮件内容
			mailSender.setMsg(inputData.getString("EMAIL_CONTNT"));
			// 发送
			
			logger.info("email: " + inputData.getString("RCVR_EMAIL_ADDR"));
			logger.info("email addressList: " + mailSender.getToAddresses());
			logger.info("emailContnt: " + inputData.getString("EMAIL_CONTNT"));
			
			//发送工资邮件   工人评价未决裁提醒邮件群发（）
			String messageID = "" ;
			messageID = mailSender.send();
			
			logger.info("messageID: " + messageID);
		} catch (Exception e) {
			logger.error(inputData.getString("RCVR_EMAIL_ADDR") + " --- " + inputData.getString("EMAIL_TITLE") + " send fail. Exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * pa send mail
	 * 
	 * @throws Exception
	 */
	public void paSendMail(SimpleMap inputData) throws Exception {
		HtmlEmail mailSender = new HtmlEmail();
		
		logger.debug("Send Job is started...");
		//List<InternetAddress> addressList = new ArrayList<InternetAddress>();
		try {
			
			address = config.getString("email.address.info").trim();
			user = config.getString("email.user.info").trim();
			password = config.getString("email.password.info").trim();
			
			// http://email.doosan.com
			//addressList.add(new InternetAddress(inputData.getString("RCVR_EMAIL_ADDR")));
			//addressList.add(new InternetAddress("xiaoyun.lu@doosan.com"));
			
			logger.debug("into email...");
			// smtp host	mail.ait.net.cn   斗山-eipex05.corp.doosan.com
			//mailSender.setHostName("mail.ait.net.cn");
			//mailSender.setHostName("eipex05.corp.doosan.com");
			mailSender.setHostName("krrelay.corp.doosan.com");
			mailSender.setCharset("UTF-8");
			// 登陆邮件服务器的用户名 ait , 密码 123qwe$
			//mailSender.setAuthentication("zhangsongfeng@ait.net.cn", "111111");
			mailSender.setAuthentication(user, password);
			// 接收人
			mailSender.addTo(inputData.getString("RCVR_EMAIL_ADDR")) ;
			mailSender.setFrom(inputData.getString("FROM_EMAIL_ADDR"));//ait@doosan.com
			// 标题
			mailSender.setSubject(inputData.getString("EMAIL_TITLE"));
			// 邮件内容
			mailSender.setMsg(inputData.getString("EMAIL_CONTNT"));
			
			logger.info("email: " + inputData.getString("RCVR_EMAIL_ADDR"));
			logger.info("email addressList: " + mailSender.getToAddresses());
			logger.info("emailContnt: " + inputData.getString("EMAIL_CONTNT"));
			
			// 发送工资邮件
			String messageID = "" ;
			messageID = mailSender.send();
			
			logger.info("messageID: " + messageID);
		} catch (Exception e) {
			logger.error(inputData.getString("RCVR_EMAIL_ADDR") + " --- " + inputData.getString("EMAIL_TITLE") + " send fail. Exception: " + e.toString());
			//e.printStackTrace();
			throw e ;
		}
	}
		
		/**
		 * ga send mail
		 * 
		 * @throws Exception
		 */
		public void gaSendMail(SimpleMap dataMap,String cpnyId,String emailAddress,String emailTitle) throws Exception {
			
			StringBuffer content = new StringBuffer();
			//List<InternetAddress> addressList = new ArrayList<InternetAddress>();
			if(cpnyId.equals("78000000")){
				cpnyAllNameForMail="斗山工程机械(中国)有限公司";
			}else if(cpnyId.equals("63000000")){
				cpnyAllNameForMail="斗山工程机械（山东）有限公司";
			}else if(cpnyId.equals("61000000")){
				cpnyAllNameForMail="斗山工程机械（苏州）有限公司";
			}else if(cpnyId.equals("59000000")){
				cpnyAllNameForMail="斗山(中国)投资有限公司";				
			}else if(cpnyId.equals("60000000")){
				cpnyAllNameForMail="斗山机床（烟台）有限公司";		
			}else{
				cpnyAllNameForMail="斗山工程机械";
			}
	    try{
	    	HtmlEmail mailSender = new HtmlEmail();
	    	
			address = config.getString("email.address.info").trim();
			user = config.getString("email.user.info").trim();
			password = config.getString("email.password.info").trim();
			
			// http://email.doosan.com
			//addressList.add(new InternetAddress(emailAddress));	
			//主机域名 之类的  邮件服务器
			//mailSender.setHostName("eipex05.corp.doosan.com");
			mailSender.setHostName("krrelay.corp.doosan.com");
			mailSender.setCharset("UTF-8");			
			mailSender.setAuthentication(user, password);
			// 接收人
			//mailSender.setTo(addressList);
			mailSender.addTo(emailAddress) ;
			mailSender.setFrom(address);//ait@doosan.com
			// 标题
			mailSender.setSubject(emailTitle);
			// 邮件内容
			Set dataSet=dataMap.keySet();			
			Iterator dataIterator =dataSet.iterator();
			while(dataIterator.hasNext()) {
				String Key=dataIterator.next().toString();
			    String DataValue=dataMap.getString(Key);
			    content.append(Key+":"+DataValue).append("<br><br>");
			} 
			content.append("<a href=" + url + " target=\"_blank\">点击此处登陆</a>") .append("<br><br>"+cpnyAllNameForMail) ;;
			mailSender.setMsg(content.toString());
			
			
			logger.info("email: " + emailAddress);
			logger.info("email addressList: " + mailSender.getToAddresses());
			logger.info("emailContnt: " + content.toString());
			
			//公章发邮件 礼品发邮件 就餐申请  支票申请 发邮件
			String messageID = "" ;
			messageID = mailSender.send();
			
			logger.info("messageID: " + messageID);
			
		} catch (Exception e) {
			logger.error(emailAddress + " --- " + emailTitle + "ga  send fail. Exception: " + e.toString());
			//e.printStackTrace();
			throw e ;
		}
		
	}
		
		
		
		/**
		 * ga send mail
		 * 
		 * @throws Exception
		 */
		public void arSendMail(SimpleMap dataMap,String cpnyId,String emailAddress,String emailTitle) throws Exception {
			
			StringBuffer content = new StringBuffer();
			//List<InternetAddress> addressList = new ArrayList<InternetAddress>();
			if(cpnyId.equals("78000000")){
				cpnyAllNameForMail="斗山工程机械(中国)有限公司";
			}else if(cpnyId.equals("63000000")){
				cpnyAllNameForMail="斗山工程机械（山东）有限公司";
			}else if(cpnyId.equals("61000000")){
				cpnyAllNameForMail="斗山工程机械（苏州）有限公司";
			}else if(cpnyId.equals("59000000")){
				cpnyAllNameForMail="斗山(中国)投资有限公司";				
			}else if(cpnyId.equals("60000000")){
				cpnyAllNameForMail="斗山机床（烟台）有限公司";		
			}else{
				cpnyAllNameForMail="斗山工程机械";
			}
	    try{
	    	HtmlEmail mailSender = new HtmlEmail();
	    	
			address = config.getString("email.address.info").trim();
			user = config.getString("email.user.info").trim();
			password = config.getString("email.password.info").trim();
			
			// http://email.doosan.com
			//addressList.add(new InternetAddress(emailAddress));	

			//mailSender.setHostName("eipex05.corp.doosan.com");
			mailSender.setHostName("krrelay.corp.doosan.com");
			mailSender.setCharset("UTF-8");			
			mailSender.setAuthentication(user, password);
			// 接收人
			//mailSender.setTo(addressList);
			mailSender.addTo(emailAddress) ;
			mailSender.setFrom(address);//ait@doosan.com
			// 标题
			mailSender.setSubject(emailTitle);
			// 邮件内容
			Set dataSet=dataMap.keySet();	
			
			
			Iterator dataIterator =dataSet.iterator();
			while(dataIterator.hasNext()) {
				String Key=dataIterator.next().toString();
			    String DataValue=dataMap.getString(Key);
			    content.append(Key+":"+DataValue).append("<br><br>");
			} 
			content.append("<a href=" + url + " target=\"_blank\">点击此处登陆</a>") .append("<br><br>"+cpnyAllNameForMail) ;;
			mailSender.setMsg(content.toString());
			
			
			logger.info("email: " + emailAddress);
			logger.info("email addressList: " + mailSender.getToAddresses());
			logger.info("emailContnt: " + content.toString());
			
			//发邮件
			String messageID = "" ;
			messageID = mailSender.send();
			
			logger.info("messageID: " + messageID);
			
		} catch (Exception e) {
			logger.error(emailAddress + " --- " + emailTitle + "ga  send fail. Exception: " + e.toString());
			//e.printStackTrace();
			throw e ;
		}
		
	}
}