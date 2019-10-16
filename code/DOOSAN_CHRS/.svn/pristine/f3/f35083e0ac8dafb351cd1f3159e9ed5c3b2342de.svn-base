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
 *   @create-time   2013-5-15   上午11:27:58   
 *   @revision      3.5
 ***********************************************************************/

public class RegularlyArAbnormalSentMail {
	
	private static final Logger logger = Logger.getLogger(RegularlyArAbnormalSentMail.class);
	
	private  String cpnyAllNameForMail="";
	//private  String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	
	public void regularlyArAbnormalSentMail() throws Exception{	
		EssCommonDAO essCommonDAO = new EssCommonDAO();
		SimpleMap parameterObject = new SimpleMap();
		
		
		List regularlySentArAbnormalEmpidInfoList=essCommonDAO.regularlySentArAbnormalEmpidInfoList(parameterObject);
		System.out.println("****************Ar Abnormal Mail Running*****************Count:"+regularlySentArAbnormalEmpidInfoList.size());
		
		for(int i=0;i<regularlySentArAbnormalEmpidInfoList.size();i++){
			SimpleMap sm = (SimpleMap)regularlySentArAbnormalEmpidInfoList.get(i);
			try {
				String emailAddress=sm.getString("AFFIMOREMAIL");
				//String emailAddress="ait@doosan.com";
				
				List regularlySentArAbnormalInfoList=essCommonDAO.regularlySentArAbnormalInfoList(sm);
				
				if(regularlySentArAbnormalInfoList.size()>0){
				this.sendEssOtMail(regularlySentArAbnormalInfoList,emailAddress);
			    }
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(regularlySentArAbnormalEmpidInfoList.size()>0){
			essCommonDAO.insertRegularlySentArAbnormalInfoRecords(parameterObject);//插入发送记录
		}
		
		
	
	}
	public void sendEssOtMail(List list,String emailAddress)throws Exception{
		cpnyAllNameForMail="斗山工程机械（中国）有限公司";		
		SimpleMap inputData = new SimpleMap();
		String emailTitle="异常勤态信息提醒";	
		StringBuffer content = new StringBuffer();
		
		content.append("提示:").append("以下异常勤态信息仅供参照，无需进行任何操作！").append("<br><br>");
		for(int i=0;i<list.size();i++){
			SimpleMap sm = (SimpleMap)list.get(i);
			try {
				content.append("部门:").append(sm.getString("DEPTNAME")).append("<br>")
					   .append("姓名:").append(sm.getString("CHINESENAME")).append("<br>")
			           .append("日期:").append(sm.getString("AR_DATE_STR")).append("<br>")
			           .append("项目:").append(sm.getString("ITEM_NAME")).append("<br>")
			           .append("长度:").append(sm.getString("QUANTITY")).append(sm.getString("UNIT")).append("<br>")
			           .append("----------------------------------")
			           .append("<br>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		content.append("<br>");
		content.append("<br><br>"+cpnyAllNameForMail) ;
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);
		
		logger.info("定时发送邮件: " + emailTitle ) ;

		new Mail().sendMail(inputData) ;	
	}

}
