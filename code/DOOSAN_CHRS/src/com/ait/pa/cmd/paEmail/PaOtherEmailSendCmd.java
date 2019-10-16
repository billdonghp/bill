package com.ait.pa.cmd.paEmail;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class PaOtherEmailSendCmd extends ArAbstractCommand {
	
	private static final Logger logger = Logger.getLogger(PaEmailSendCmd.class);
	
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	private PaServices paServices = null ;
	
	//"http://dghr.corp.doosan.com/dic_login.jsp" ; //"http://172.16.225.240:9080/dic_login.jsp" 
	
	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.putToolbarInfo(request);
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		paServices = PaServices.getInstance() ;
		
		MessageSource messageSource ;
		SimpleMap parameterObject = new SimpleMap() ;
		SimpleMap querParam = new SimpleMap() ;
		List paDataList = new ArrayList() ;
		String url = "" ;
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpny_id", admin.getCpnyId());
			querParam.setString("cpny_id", admin.getCpnyId());
			
			if(admin.getCpnyId().equals("61000000")){
				parameterObject.setString("errorMail", "lin1.zhu@doosan.com");
				querParam.setString("errorMail", "lin1.zhu@doosan.com");
			}else if(admin.getCpnyId().equals("63000000")){
				parameterObject.setString("errorMail", "jiuling.zhang@doosan.com");
				querParam.setString("errorMail", "jiuling.zhang@doosan.com");
			}else if(admin.getCpnyId().equals("78000000")){
				parameterObject.setString("errorMail", "jindan.xu@doosan.com");
				querParam.setString("errorMail", "jindan.xu@doosan.com");
			}else if(admin.getCpnyId().equals("60000000")){
				parameterObject.setString("errorMail", "xiaomei.liu@doosan.com");
				querParam.setString("errorMail", "xiaomei.liu@doosan.com");
			}else if(admin.getCpnyId().equals("59000000")){
				parameterObject.setString("errorMail", "lili.qian@doosan.com");
				querParam.setString("errorMail", "lili.qian@doosan.com");
			}else{
				parameterObject.setString("errorMail", "ait@doosan.com");
				querParam.setString("errorMail", "ait@doosan.com");
			}	
			
//			if (parameterObject.getString("tableName") != null && parameterObject.getString("tableName").equals("PA_HISTORY"))
//			{
//				parameterObject.setString("bonusTypeCode", "BonusType") ;
//				parameterObject.setInt("bonusNo", 0) ;
//			}
//			else if (parameterObject.getString("tableName") != null && parameterObject.getString("tableName").equals("PA_REPLENISHMENT_HISTORY"))
//			{
//				parameterObject.setString("bonusTypeCode", "BonusType00") ;
//				parameterObject.setInt("bonusNo", 0) ;
//			}
			if (parameterObject.getString("tableName")!= null)
			{	
				// 其他数据发邮件
				parameterObject.set("mailName", parameterObject.getString("tableName")) ;
				
					paDataList = paServices.retrievePaOtherSendMail(parameterObject) ;
			}
			

			sendPaDataMail(paDataList, parameterObject,admin) ;
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("pa send OtherEmail Exception. ", e);
		}

		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", "邮件发送完成!!!");
		request.setAttribute("url","/paControlServlet?operation=viewOtherEmailcmd&menu_code=" + parameterObject.getString("menu_code") 
									+ "&paMonth=" + parameterObject.getString("paMonth") + "&key=" + parameterObject.getString("key") 
									+ "&year=" + parameterObject.getString("year") + "&month=" + parameterObject.getString("month")
									+ "&tableName=" + parameterObject.getString("tableName") + "&statTypeCode=" + parameterObject.getString("statTypeCode") 
									+ "&activity=" + parameterObject.getString("activity") + "&deptId=" + parameterObject.getString("deptId") 
		);

		return "/inc/alertMessage.jsp";
	}
	
	/**
	 * send pa data of mail
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	private void sendPaDataMail(List paDataList, SimpleMap parameterObject,AdminBean admin) throws Exception {
		
		paServices = PaServices.getInstance() ;
		SimpleMap empWagesData = new SimpleMap() ;
		SimpleMap inputData = new SimpleMap() ;	
		
		int paDataListSize = paDataList.size() ;
		// set email title
		inputData.setString("EMAIL_TITLE", parameterObject.getString("year") + "年" + parameterObject.getString("month") + "月" + parameterObject.getString("mailName")) ;
		
		for (int i = 0 ; i < paDataListSize ; ++i)
		{
			empWagesData = (SimpleMap)paDataList.get(i) ;
			String content="";
			String cpnyId=admin.getCpnyId();
			if(cpnyId.equals("61000000")){
				content=this.getMailContent_DISC(empWagesData, parameterObject) ;
				inputData.setString("FROM_EMAIL_ADDR", "lin1.zhu@doosan.com") ;
//			}else if(cpnyId.equals("63000000")){
//				content=this.getMailContent_DISD(empWagesData, parameterObject) ;
//				inputData.setString("FROM_EMAIL_ADDR", "jiuling.zhang@doosan.com") ;
//			}else if(cpnyId.equals("78000000")){
//				content=this.getMailContent_DICC(empWagesData, parameterObject) ;
//				inputData.setString("FROM_EMAIL_ADDR", "jindan.xu@doosan.com") ;
//			}else if(cpnyId.equals("60000000")){
//				content=this.getMailContent_DIY(empWagesData, parameterObject) ;
//				inputData.setString("FROM_EMAIL_ADDR", "xiaomei.liu@doosan.com") ;
//			}else if(cpnyId.equals("59000000")){
//				content=this.getMailContent_DICI(empWagesData, parameterObject) ;
//				inputData.setString("FROM_EMAIL_ADDR", "lili.qian@doosan.com") ;
			}else{
				continue ;
			}	
			// set email content
			inputData.setString("EMAIL_CONTNT", content) ;
			
			inputData.setString("RCVR_EMAIL_ADDR", empWagesData.getString("EMAIL")) ;
			if(!empWagesData.getString("EMAIL").equals("") && empWagesData.getString("EMAIL")!=null){
				try
				{
					new Mail().paSendMail(inputData) ;
					
					paServices.updatePaOtherEmail(empWagesData) ;
				}
				catch(Exception e)
				{
					e.printStackTrace() ;
				}
			}
			
		}
	
	}
	
	public final static String weekNames[] = { "星期日 ", "星期一 ", "星期二 ", "星期三 ", "星期四 ", "星期五 ", "星期六 " };
	
	private String getMailContent_DISC(SimpleMap empWages,SimpleMap parameterObject) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日 "); 

		Calendar calendar = Calendar.getInstance();
		String paDate = sdf.format(calendar.getTime()) ;
		String week = weekNames[calendar.get(Calendar.DAY_OF_WEEK) - 1] ;
		String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) ;
		
		StringBuffer content = new StringBuffer(25000);
		
		content.append("<html>")
	       	   .append("<title>工资发放</title>")
		       .append("<body bgcolor=lavender lang=ZH-CN link=blue vlink=purple><div class=Section1><p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-USstyle='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p>")
		       .append("<p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-US style='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p><div>") 
		       .append("<div class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><hr size=2 width='100%' align=center tabindex=-1></span></font></div>")
		       .append("<p class=MsoNormal><b>")
		       .append("<font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold'>发件人:</span></font>주림 D B C N <br><b>")
		       .append("<font size=2><span style='font-size:10.0pt;font-weight:bold'>发送时间</span></font>: ").append(paDate).append(week).append(time).append("<br><b>")
		       //.append("<font size=2><span style='font-weight:bold'>收件人</span></font>:장나 사원 인프라코어 D I C C<br><b>")
		       .append("<font size=2><span style='font-size:10.0pt;font-weight:bold'>主题</span></font>: ").append(parameterObject.getString("year")).append("年").append(parameterObject.getString("month")).append("月 ").append(empWages.getString("GIVENAME")).append("</p></div>") 
		       .append("<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
		       .append("<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'>")
		       .append("<tr><td colspan=2 style='padding:.75pt .75pt .75pt .75pt'>")
		       .append("<p align=center style='text-align:center'><strong><b><font size=5 face=宋体><span lang=EN-US style='font-size:18.0pt;font-family:宋体'>").append(parameterObject.getString("year")).append("年").append(parameterObject.getString("month")).append("月 ").append(empWages.getString("GIVENAME")).append("支付明细表</span></font></b></strong>")
		       .append("</p></td></tr>")
		       .append("<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>&nbsp;</td></tr>")
		       .append("<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>")
		       .append("<p class=MsoNormal><strong><b><font size=2 face=宋体><span style='font-size:10.0pt;font-family:宋体'>薪水乃个人隐私，请您尊重他人的隐私，如有违反，公司给予警告处罚</span></font></b></strong>")
		       .append("</p></td></tr>") 
		       .append("<tr height=57 style='height:42.75pt'><td colspan=2 height=57 style='padding:.75pt .75pt .75pt .75pt;height:42.75pt'><div align=center>")
		       .append("<table class=MsoNormalTable border=1 cellpadding=0 width=805 style='width:603.75pt' id=Table1 height=47><tr>")
    		   .append("<td colspan=3 style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>部门</span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>职号</span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>姓名</span></font></p></td>")
    		   .append("</tr><tr><td colspan=3 style='padding:.75pt .75pt .75pt .75pt'>")
    		   .append("<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>").append(empWages.getString("DEPARTMENT")).append("</span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>").append(empWages.getString("EMPID")).append("<o:p></o:p></span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>").append(empWages.getString("CHINESENAME")).append("</span></font></p></td>")
		       .append("</tr><tr>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>支给名称</span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>支给金额</span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>个人福利合计</span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>个税</span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>实发金额</span></font></p></td>")
			   .append("</tr><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
    		   .append("<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>").append(empWages.getString("GIVENAME")).append("</span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'>")
    		   .append("<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>").append(empWages.getString("PAY_AMOUNT")).append("</span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'>")
    		   .append("<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>").append(empWages.getString("PERWELFARESUM")).append("</span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'>")
    		   .append("<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>").append(empWages.getString("INCOMETAX")).append("</span></font></p></td>")
    		   .append("<td style='padding:.75pt .75pt .75pt .75pt'>")
    		   .append("<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>").append(empWages.getString("NETAMOUNT")).append("</span></font></p></td>")
    		   .append("</tr><tr height=27 style='height:20.25pt'>")
			  .append("<td colspan=2 height=27 >")
			  .append("<p align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
			  .append("<img width=100 height=27 id='_x0000_i1025' src='http://10.40.128.28:8083/images/login_logo.gif'")			  														  
			  .append(" alt='border=0'><strong><b><font face=宋体><span style='font-family:宋体'><!-- </img> -->&nbsp;</span></font></b></strong></span>")
			  .append("<strong><b><font face=宋体>斗山山猫机械（中国）有限公司</font></b></strong></font></p></td>")
			  .append("<td colspan=3 height=27 valign=bottom >")
			  .append("<p align=center style='text-align:center'><strong><b><font size=3 face=宋体>")
			  .append("<span style='font-size:12.0pt;font-family:宋体'>感谢您对公司所做的贡献</span></font></b></strong></p>")
			  .append("</td></tr>")
			  .append("<tr height=27 style='height:20.25pt'>")
			  .append("<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
			  //.append("<b><font size=2 face=宋体>备注：本次春节福利包括公司福利600元，公司其他福利400元。</font></b>")
			  //.append("<b><font size=2 face=宋体>备注：本次春节福利包括公司福利600元，公司其他福利400元。</font></b>")
			 	 .append("<b><font size=2 face=宋体>备注：如有疑问，请只和HR联系~</font></b>")
			  .append("</td></tr></table></div></body></html>") ;	
			
			return content.toString() ;
	}

}


