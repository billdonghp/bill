/*
 * @(#)ExportAttRecordReportCmd.java 1.0 2007-10-7 下午02:02:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.kpa.cmd.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.kpa.business.PaDsHrServices;
import com.ait.kpa.business.PaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ibatis.sqlmap.client.SqlMapClient;

public class PaOpenProgressCmd extends ArAbstractCommand {
	
	private static final Logger logger = Logger.getLogger(PaOpenProgressCmd.class);
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://portal.doosan.com" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	//"http://dghr.corp.doosan.com/dic_login.jsp" ; //"http://172.16.225.240:9080/dic_login.jsp" 
	
	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.putToolbarInfo(request);
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		PaServices paServices = PaServices.getInstance() ;
		
		List emailErrorList = new ArrayList() ;
		
		MessageSource messageSource ;
		SimpleMap parameterObject = new SimpleMap() ;
		List paDataList = new ArrayList() ;
		String url = "" ;
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			//测试用，使用完，要删除
			//parameterObject.set("TABLE_NAME", "T_PA_RESULT") ; //PA_HISTORY
			
			if (parameterObject.get("payOpenType") != null && !parameterObject.getString("payOpenType").equals("payMail"))
			{
				// 开始循环,交换数据
				//insertDsHrDatas(parameterObject,admin) ;
			}
			else if (parameterObject.get("payOpenType") != null && !parameterObject.getString("payOpenType").equals("payData"))
			{
				if (parameterObject.getString("TABLE_NAME").equals("KPA_BONUS_HISTORY"))
				{
					// 奖金数据发邮件
					url = "/kpa_bonus_result.jsp?menu_code=" ;
					parameterObject.set("mailName", "장려금") ; //奖金
					paDataList = paServices.retrievePaBonusInfoForSendMail(parameterObject) ;
					
					emailErrorList = sendPaBonusDataMail(paDataList, parameterObject) ;
				}
				else if (parameterObject.getString("TABLE_NAME").equals("KPA_HISTORY"))
				{					
					// 工资数据发邮件
					url = "/kpa0205.jsp?menu_code=" ;
					parameterObject.set("mailName", "급여") ; //工资
					paDataList = paServices.retrievePaInfoForSendMail(parameterObject) ;
					
					emailErrorList = sendPaDataMail(paDataList, parameterObject) ;
				}
				else if (parameterObject.getString("TABLE_NAME").equals("KPA_DIFF_HISTORY"))
				{					
					// 工资数据发邮件
					url = "/kpa_diff_calculate.jsp?menu_code=" ;
					parameterObject.set("mailName", "급여보차(或급여차액 보충지급)") ; //工资补差
					paDataList = paServices.retrievePaDiffInfoForSendMail(parameterObject) ;
					
					emailErrorList = sendPaDiffDataMail(paDataList, parameterObject) ;
				}
				
				
				if (emailErrorList.size() == 0)
				{
					emailErrorList.add("邮件发送完成!!!") ;
				}
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("kpa openProgress Exception. ", e);
		}

		request.setAttribute("emailErrorList", emailErrorList) ;

		return url + parameterObject.getString("menu_code") + "&pamonth=" + parameterObject.getString("PA_MONTH") + "&year" + parameterObject.getString("year") + "&month" + parameterObject.getString("month") + "&payOpenType=" + parameterObject.getString("payOpenType") ;
	}
	
	/**
	 * send pa data of mail
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	private ArrayList sendPaDataMail(List paDataList, SimpleMap parameterObject) throws Exception {
		
		SimpleMap empWagesData = new SimpleMap() ;
		SimpleMap inputData = new SimpleMap() ;
		
		ArrayList errorList = new ArrayList() ;
		
		int paDataListSize = paDataList.size() ;
		// set email title
		inputData.setString("EMAIL_TITLE", parameterObject.getString("year") + "연도" + parameterObject.getString("month") + "월 급여") ;
		
		for (int i = 0 ; i < paDataListSize ; ++i)
		{
			empWagesData = (SimpleMap)paDataList.get(i) ;
			
			String content = this.getPaMailContent(empWagesData, parameterObject) ;

			// set email content
			inputData.setString("EMAIL_CONTNT", content) ;

			inputData.setString("RCVR_EMAIL_ADDR", empWagesData.getString("EMAIL")) ;
			//inputData.setString("RCVR_EMAIL_ADDR", "zhangsongfeng@ait.net.cn") ;
			
			try
			{
				new Mail().paSendMail(inputData) ;
			}
			catch(Exception e)
			{
				errorList.add(empWagesData.getString("CHINESENAME") + " 工资邮件发送失败!!!") ;
			}
			
		}
		
		return errorList ;
	}
	
	/**
	 * send pa bonus data of mail
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	private ArrayList sendPaBonusDataMail(List paDataList, SimpleMap parameterObject) throws Exception {
		
		SimpleMap empWagesData = new SimpleMap() ;
		SimpleMap inputData = new SimpleMap() ;
		
		ArrayList errorList = new ArrayList() ;
		
		int paDataListSize = paDataList.size() ;
		// set email title
		inputData.setString("EMAIL_TITLE", parameterObject.getString("year") + "연도" + parameterObject.getString("month") + " 장려금") ;
		
		for (int i = 0 ; i < paDataListSize ; ++i)
		{
			empWagesData = (SimpleMap)paDataList.get(i) ;
			
			String content = this.getPaBonusMailContent(empWagesData, parameterObject) ;

			// set email content
			inputData.setString("EMAIL_CONTNT", content) ;

			inputData.setString("RCVR_EMAIL_ADDR", empWagesData.getString("EMAIL")) ;
			
			try
			{
				new Mail().paSendMail(inputData) ;
			}
			catch(Exception e)
			{
				errorList.add(empWagesData.getString("CHINESENAME") + " 奖金邮件发送失败!!!") ;
			}
			
		}
		
		return errorList ;
	}
	
	/**
	 * send pa data of mail
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	private ArrayList sendPaDiffDataMail(List paDataList, SimpleMap parameterObject) throws Exception {
		
		SimpleMap empWagesData = new SimpleMap() ;
		SimpleMap inputData = new SimpleMap() ;
		
		ArrayList errorList = new ArrayList() ;
		
		int paDataListSize = paDataList.size() ;
		// set email title
		inputData.setString("EMAIL_TITLE", parameterObject.getString("year") + "연도" + parameterObject.getString("month") + " 급여보차(或급여차액 보충지급)") ;
		
		for (int i = 0 ; i < paDataListSize ; ++i)
		{
			empWagesData = (SimpleMap)paDataList.get(i) ;
			
			String content = this.getPaDiffMailContent(empWagesData, parameterObject) ;

			// set email content
			inputData.setString("EMAIL_CONTNT", content) ;

			inputData.setString("RCVR_EMAIL_ADDR", empWagesData.getString("EMAIL")) ;
			//inputData.setString("RCVR_EMAIL_ADDR", "zhangsongfeng@ait.net.cn") ;
			
			try
			{
				new Mail().paSendMail(inputData) ;
			}
			catch(Exception e)
			{
				errorList.add(empWagesData.getString("CHINESENAME") + " 工资补差邮件发送失败!!!") ;
			}
			
		}
		
		return errorList ;
	}
	
	public final static String weekNames[] = { "星期日 ", "星期一 ", "星期二 ", "星期三 ", "星期四 ", "星期五 ", "星期六 " };
	
	private String getPaMailContent(SimpleMap empWages,SimpleMap parameterObject) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); 

		Calendar calendar = Calendar.getInstance();
		String paDate = sdf.format(calendar.getTime()) ;
		String week = weekNames[calendar.get(Calendar.DAY_OF_WEEK) - 1] ;
		String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) ;
		
		StringBuffer content = new StringBuffer(25000);
		
		content.append("<html ><body >")
		       .append("<font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold'>발송인:</span></font><br><b>")//未知
		       .append("<font size=2><span style='font-size:10.0pt;font-weight:bold'>발송시간</span></font>: ").append(paDate).append(time).append("<br><b>")
		       //.append("<font size=2><span style='font-weight:bold'>收件人</span></font>:장나 사원 인프라코어 D I C C<br><b>")
		       .append("<font size=2><span style='font-size:10.0pt;font-weight:bold'>제목</span></font>: ").append(parameterObject.getString("year")).append("연도").append(parameterObject.getString("month")).append("월 ").append(parameterObject.getString("mailName")).append("<span lang=EN-US><o:p></o:p></span></p></div>") 
		       .append("<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
		       
		       .append("<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'>")
		       .append("<tr><td colspan=2 style='padding:.75pt .75pt .75pt .75pt'>")
		       .append("<p align=center style='text-align:center'><strong><b><font size=5 face=宋体><span lang=EN-US style='font-size:18.0pt;font-family:宋体'>").append(parameterObject.getString("year")).append("연도").append(parameterObject.getString("month")).append("월 ").append("급여지급 명세서</span></font></b></strong>")
		       .append("<span lang=EN-US><o:p></o:p></span></p></td></tr>")
		       .append("<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>&nbsp;</td></tr>")
		       .append("<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>")
		       .append("<p class=MsoNormal><strong><b><font size=2 face=宋体><span style='font-size:10.0pt;font-family:宋体'>급여는 사적인 비밀로서,타인의 프라이버시를 존중해야 하며 위반시 이에 상응한  경고처분을 부가한다.</span></font></b></strong>")
		       .append("<span lang=EN-US><o:p></o:p></span></p></td></tr>") 
		       .append("<tr height=57 style='height:42.75pt'><td colspan=2 height=57 style='padding:.75pt .75pt .75pt .75pt;height:42.75pt'><div >")
		       .append("<table class=MsoNormalTable border=1 cellpadding=0 width=805 style='width:603.75pt' id=Table1 height=47><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
    		   .append("<p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>직급<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>사  번<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>성  명<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
    		   .append("</tr><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
    		   .append("<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>").append(empWages.getString("POST_KOR")).append("&nbsp;</span><span lang=EN-US><o:p></o:p></span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>").append(empWages.getString("EMPID")).append("<o:p></o:p></span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>").append(empWages.getString("CHINESENAME")).append("<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
		       .append("</tr></table></div></br>")
		       
			   .append("<table class=MsoNormalTable border=1 cellpadding=0 bgcolor=lavender style='background:lavender'><tr height=30 style='height:22.5pt'>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>").append(parameterObject.getString("year")).append(parameterObject.getString("month")).append("(USD)월</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td colspan='2' width=200 height=30 ")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>기타지급 1</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td colspan='2' width=200 height=30 ")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>기타지급 2</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td rowspan='2' width=200 height=30 ")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>소 계(A)(USD)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td rowspan='2' width=200 height=30 ")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>법인대여금(B)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td colspan='2' width=200 height=30 ")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>총계(A+B)(USD)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td colspan='5' width=500 height=30 ")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>개인대여금(C)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td rowspan='2' width=200 height=30 ")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>개인지급계(A-C)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("</tr>")
			   .append("<tr height=30 style='height:22.5pt'>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>(￦)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>(USD)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>(￦)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>(USD)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>(￦)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>(USD)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>건강보험</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>국민연금</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>기타</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>계(￦)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>계(USD)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("</tr>")
			   .append("<tr height=30 style='height:22.5pt'>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("FIXED_WAGES_MONTH")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("OTHER_PAYMENTS1")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("OTHER_PAY1")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("OTHER_PAYMENTS2")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("OTHER_PAY2")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("COUNTS")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("INSURANCE_COMPANY")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("INSURANCE_COM")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("COUNT_SUM")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("HEALTH_INSURANCE_EMP")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("NATIONAL_INSURANCE_EMP")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("OTHER_INSURANCES_EMP")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("INSURANCE_SUM_EMP1")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("INSURANCE_SUM_EMP2")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("INCOME_SUM_EMP")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("</tr></table></br>")
			   
			   .append("<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'><tr height=27 style='height:20.25pt'>")
			   .append("<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
			   .append("<p align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
			   .append("<img width=43 height=27 id='_x0000_i1025' src='http://WPADM.corp.doosan.com/imageserver/DoosanInfracore/private/img/top/logo.gif'")
			   .append(" alt='border=0'><strong><b><font face=宋体><span style='font-family:宋体'><!-- </img> -->&nbsp;</span></font></b></strong></span>")
			   .append("<strong><b><font face=宋体>두산공정기계(중국)유한회사</font></b></strong><span lang=EN-US><o:p></o:p></span></font></p></td>")
			   .append("<td height=27 valign=bottom style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
			   .append("<p align=center style='text-align:center'><strong><b><font size=3 face=宋体>")
			   .append("<span style='font-size:12.0pt;font-family:宋体'>회사에 대한 귀하의 공헌에 감사드립니다</span></font></b></strong><span lang=EN-US><o:p></o:p></span></p>")
			   .append("</td></tr></table>")
			   .append("<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
			   .append("</body></html>") ;		
			
			return content.toString() ;
	}
	
	private String getPaBonusMailContent(SimpleMap empWages,SimpleMap parameterObject) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); 

		Calendar calendar = Calendar.getInstance();
		String paDate = sdf.format(calendar.getTime()) ;
		String week = weekNames[calendar.get(Calendar.DAY_OF_WEEK) - 1] ;
		String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) ;
		
		StringBuffer content = new StringBuffer(25000);
		
		content.append("<html ><body >")
		       .append("<font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold'>발송인:</span></font><br><b>")//未知
		       .append("<font size=2><span style='font-size:10.0pt;font-weight:bold'>발송시간</span></font>: ").append(paDate).append(time).append("<br><b>")
		       //.append("<font size=2><span style='font-weight:bold'>收件人</span></font>:장나 사원 인프라코어 D I C C<br><b>")
		       .append("<font size=2><span style='font-size:10.0pt;font-weight:bold'>제목</span></font>: ").append(parameterObject.getString("year")).append("연도").append(parameterObject.getString("month")).append("월 ").append(parameterObject.getString("mailName")).append("<span lang=EN-US><o:p></o:p></span></p></div>") 
		       .append("<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
		       
		       .append("<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'>")
		       .append("<tr><td colspan=2 style='padding:.75pt .75pt .75pt .75pt'>")
		       .append("<p align=center style='text-align:center'><strong><b><font size=5 face=宋体><span lang=EN-US style='font-size:18.0pt;font-family:宋体'>").append(parameterObject.getString("year")).append("연도").append(parameterObject.getString("month")).append("월 ").append(" 장려금</span></font></b></strong>")
		       .append("<span lang=EN-US><o:p></o:p></span></p></td></tr>")
		       .append("<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>&nbsp;</td></tr>")
		       .append("<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>")
		       .append("<p class=MsoNormal><strong><b><font size=2 face=宋体><span style='font-size:10.0pt;font-family:宋体'>급여는 사적인 비밀로서,타인의 프라이버시를 존중해야 하며 위반시 이에 상응한  경고처분을 부가한다.</span></font></b></strong>")
		       .append("<span lang=EN-US><o:p></o:p></span></p></td></tr>") 
		       .append("<tr height=57 style='height:42.75pt'><td colspan=2 height=57 style='padding:.75pt .75pt .75pt .75pt;height:42.75pt'><div >")
		       .append("<table class=MsoNormalTable border=1 cellpadding=0 width=805 style='width:603.75pt' id=Table1 height=47>")
		       .append("<tr><td rowspan='2' style='padding:.75pt .75pt .75pt .75pt'>")
    		   .append("<p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>직급<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
    		   .append("<td rowspan='2' style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>사  번<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
    		   .append("<td rowspan='2' style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>성  명<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>").append(parameterObject.getString("year")).append("성과급(A)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td colspan='3' width=300 height=30 ")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>세금(￦)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td colspan='2' width=200 height=30 ")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>성과급 지급(E=A-D)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("</tr>")
			   .append("<tr height=30 style='height:22.5pt'>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>(￦)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>총담세액(B)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>연봉세금(C)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>성과급세금(D=B-C)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>(￦)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>($)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("</tr>")
			   .append("<tr height=30 style='height:22.5pt'>")
			   .append("<td width=100 style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>").append(empWages.getString("POST_KOR")).append("&nbsp;</span><span lang=EN-US><o:p></o:p></span></font></p></td>")
    		   .append("<td width=100 style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>").append(empWages.getString("EMPID")).append("<o:p></o:p></span></font></p></td>")
    		   .append("<td width=100 style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>").append(empWages.getString("CHINESENAME")).append("<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("ACHIEVEMENT")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("TAX_AMOUNT_TOTAL")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("MONTH_TAX")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("RESULT_TAX")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("RESULT_TAX_SENDS1")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("RESULT_TAX_SENDS2")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("</tr></table></br>")
			   
			   .append("<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'><tr height=27 style='height:20.25pt'>")
			   .append("<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
			   .append("<p align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
			   .append("<img width=43 height=27 id='_x0000_i1025' src='http://WPADM.corp.doosan.com/imageserver/DoosanInfracore/private/img/top/logo.gif'")
			   .append(" alt='border=0'><strong><b><font face=宋体><span style='font-family:宋体'><!-- </img> -->&nbsp;</span></font></b></strong></span>")
			   .append("<strong><b><font face=宋体>두산공정기계(중국)유한회사</font></b></strong><span lang=EN-US><o:p></o:p></span></font></p></td>")
			   .append("<td height=27 valign=bottom style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
			   .append("<p align=center style='text-align:center'><strong><b><font size=3 face=宋体>")
			   .append("<span style='font-size:12.0pt;font-family:宋体'>회사에 대한 귀하의 공헌에 감사드립니다</span></font></b></strong><span lang=EN-US><o:p></o:p></span></p>")
			   .append("</td></tr></table>")
			   .append("<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
			   .append("</body></html>") ;		
			
			return content.toString() ;
	}
	
	private String getPaDiffMailContent(SimpleMap empWages,SimpleMap parameterObject) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); 

		Calendar calendar = Calendar.getInstance();
		String paDate = sdf.format(calendar.getTime()) ;
		String week = weekNames[calendar.get(Calendar.DAY_OF_WEEK) - 1] ;
		String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) ;
		
		StringBuffer content = new StringBuffer(25000);
		
		content.append("<html ><body >")
		       .append("<font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold'>발송인:</span></font><br><b>")//未知
		       .append("<font size=2><span style='font-size:10.0pt;font-weight:bold'>발송시간</span></font>: ").append(paDate).append(time).append("<br><b>")
		       //.append("<font size=2><span style='font-weight:bold'>收件人</span></font>:장나 사원 인프라코어 D I C C<br><b>")
		       .append("<font size=2><span style='font-size:10.0pt;font-weight:bold'>제목</span></font>: ").append(parameterObject.getString("year")).append("연도").append(parameterObject.getString("month")).append("월 ").append(parameterObject.getString("mailName")).append("<span lang=EN-US><o:p></o:p></span></p></div>") 
		       .append("<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
		       
		       .append("<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'>")
		       .append("<tr><td colspan=2 style='padding:.75pt .75pt .75pt .75pt'>")
		       .append("<p align=center style='text-align:center'><strong><b><font size=5 face=宋体><span lang=EN-US style='font-size:18.0pt;font-family:宋体'>").append(parameterObject.getString("year")).append("연도").append(parameterObject.getString("month")).append("월 ").append(" 급여보차(或급여차액 보충지급)</span></font></b></strong>")
		       .append("<span lang=EN-US><o:p></o:p></span></p></td></tr>")
		       .append("<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>&nbsp;</td></tr>")
		       .append("<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>")
		       .append("<p class=MsoNormal><strong><b><font size=2 face=宋体><span style='font-size:10.0pt;font-family:宋体'>급여는 사적인 비밀로서,타인의 프라이버시를 존중해야 하며 위반시 이에 상응한  경고처분을 부가한다.</span></font></b></strong>")
		       .append("<span lang=EN-US><o:p></o:p></span></p></td></tr>") 
		       .append("<tr height=57 style='height:42.75pt'><td colspan=2 height=57 style='padding:.75pt .75pt .75pt .75pt;height:42.75pt'><div >")
		       .append("<table class=MsoNormalTable border=1 cellpadding=0 width=805 style='width:603.75pt' id=Table1 height=47>")
		       .append("<tr><td rowspan='2' style='padding:.75pt .75pt .75pt .75pt'>")
    		   .append("<p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>직급<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
    		   .append("<td rowspan='2' style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>사  번<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
    		   .append("<td rowspan='2' style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>성  명<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
			   .append("<td colspan='2' width=200 height=30 >")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>연봉</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td colspan='2' width=200 height=30 >")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>세금</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td colspan='3' width=300 height=30 >")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>소급정산 (").append(empWages.getString("PA_BETWEEN_MONTH")).append("월)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("</tr>")
			   .append("<tr><td  style='padding:.75pt .75pt .75pt .75pt'>")
    		   .append("<p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>변경 전<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>변경 후<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>변경 전<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>변경 후</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>연봉(천원 절상)</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>세금미납</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>소급액</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("</tr>")
			   .append("<tr height=30 style='height:22.5pt'>")
			   .append("<td width=100 style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>").append(empWages.getString("POST_KOR")).append("&nbsp;</span><span lang=EN-US><o:p></o:p></span></font></p></td>")
    		   .append("<td width=100 style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>").append(empWages.getString("EMPID")).append("<o:p></o:p></span></font></p></td>")
    		   .append("<td width=100 style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>").append(empWages.getString("CHINESENAME")).append("<span lang=EN-US><o:p></o:p></span></span></font></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("YEARLY_SALARY_BEFORE")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("YEARLY_SALARY_AFTER")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("TAX_AMOUNT_ALL_BEFORE")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("TAX_AMOUNT_ALL_AFTER")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("WAGES_FIXEDLY_MONTH")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("UNPAID_TAX_AMOUNT")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
			   .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("ALL_DIFFER")).append("&nbsp;</span></font><span lang=EN-US><o:p></o:p></span></p></td>")
			   .append("</tr></table></br>")
			   
			   .append("<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'><tr height=27 style='height:20.25pt'>")
			   .append("<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
			   .append("<p align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
			   .append("<img width=43 height=27 id='_x0000_i1025' src='http://WPADM.corp.doosan.com/imageserver/DoosanInfracore/private/img/top/logo.gif'")
			   .append(" alt='border=0'><strong><b><font face=宋体><span style='font-family:宋体'><!-- </img> -->&nbsp;</span></font></b></strong></span>")
			   .append("<strong><b><font face=宋体>두산공정기계(중국)유한회사</font></b></strong><span lang=EN-US><o:p></o:p></span></font></p></td>")
			   .append("<td height=27 valign=bottom style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
			   .append("<p align=center style='text-align:center'><strong><b><font size=3 face=宋体>")
			   .append("<span style='font-size:12.0pt;font-family:宋体'>회사에 대한 귀하의 공헌에 감사드립니다</span></font></b></strong><span lang=EN-US><o:p></o:p></span></p>")
			   .append("</td></tr></table>")
			   .append("<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
			   .append("</body></html>") ;		
			
			return content.toString() ;
	}
}

