package com.ait.gm.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.gm.business.ExpressMangerServices;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;
import com.ibm.icu.text.SimpleDateFormat;


/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-14
 * 
 */
public class ExpressMangerCommand implements Command {
	private ExpressMangerServices emServices;
	
	private Mail mail ;
	
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://portal.doosan.com" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;

	public ExpressMangerCommand() {
		emServices = new ExpressMangerServices();
		mail = new Mail() ;
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		String adminid=admin.getAdminID();
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		String returnPage=null;
		if(!content.equals("") && content.equals("expressConfirm")){
			returnPage=this.expressConfirmInfo(request,admin);			
		}else if(!content.equals("") && content.equals("oingConfirm")){
			returnPage=this.oingConfirm(request,admin);		
		}else if(!content.equals("") && content.equals("expressSend")){
			returnPage=this.expressSend(request,admin);
		}else if(!content.equals("") && content.equals("ingSend")){		
			returnPage=this.ingSend(request,admin);
		}else if(!content.equals("") && content.equals("expressView")){
			returnPage=this.expressView(request,admin);
		}else if(!content.equals("") && content.equals("expressViewExcel")){
				returnPage=this.expressViewExcel(request,admin);
		}else if(!content.equals("") && content.equals("confirmCosts")){
			returnPage=this.confirmCosts(request,admin);
		
		}else if(!content.equals("") && content.equals("expressExcel")){			
			SimpleMap  parameterObject = new SimpleMap();
			parameterObject.setString("screenGrantNo", admin.getScreenGrantNo());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			List reportList = emServices.gaReportList(parameterObject);
			//礼品方案的下拉列表
			List recordList = (new GaServices().getSchemeName(parameterObject));
			request.setAttribute("recordList", recordList);
			request.setAttribute("reportList", reportList);
			returnPage="/gm_express_excel.jsp";
		}else if(!content.equals("") && content.equals("ImpexpressExcel")){			
		 String flag=request.getParameter("flag");
		 if(flag.equals("EMSDetailsExcel")){//EMS月结算明细表
			 this.EMSDetailsExcel(request, admin);	        	 
			 returnPage="/reports/gm_report/EMSDetailsExcel.jsp?startdate="+request.getParameter("excelStartDate2")+"&enddate="+request.getParameter("excelEndDate2"); 
		 }
		 if(flag.equals("EMSTotalExcel")){//EMS月结算报表
			 this.EMSTotalExcelList(request, admin);        	 
			 returnPage="/reports/gm_report/EMSTotalExcel.jsp?startdate="+request.getParameter("excelStartDate2")+"&enddate="+request.getParameter("excelEndDate2"); 
		 }
         if(flag.equals("EMSStatisticsExcel")){//EMS发送情况统计表  
        	 this.EMSStatisticsExcel(request, admin);
        	 returnPage="/reports/gm_report/EMSStatisticsExcel.jsp?startdate="+request.getParameter("excelStartDate2")+"&enddate="+request.getParameter("excelEndDate2"); 
		 }       
		}else if(!content.equals("") && content.equals("expressInstall")){
			returnPage = this.expressInstall(request, admin);
		}else if(!content.equals("") && content.equals("expressInstallSave")){
			this.expressInstallSave(request, admin);
			returnPage = this.expressInstall(request, admin);
		}else if(!content.equals("") && content.equals("expressInstallUpdate")){
			this.expressInstallUpdate(request, admin);
			returnPage = this.expressInstall(request, admin);
		}else if(!content.equals("") && content.equals("ViewexpressInstallUpdate")){
			returnPage = this.ViewexpressInstallUpdate(request, admin);
		}
		else if(!content.equals("") && content.equals("expressInstallDel")){
				returnPage=this.expressInstallDel(request,admin);
		}
		else{
			return "error.jsp";
		}
		return returnPage;
	}
   public String expressConfirmInfo(HttpServletRequest request,AdminBean admin){	
	   SimpleMap parameterObject = null;
	   try{
			
			parameterObject = ObjectBindUtil.getData(request);
			//parameterObject.set("applerId", admin.getAdminID());
			int pageSize =10;
			int pageGroupSize =10;
			int currentPage = 0;
		
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
			String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):"";
			String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):"";
			String qryType = request.getParameter("qryType") !=null?request.getParameter("qryType"):"0";
			String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			String key = request.getParameter("key")!=null?request.getParameter("key"):"";
			parameterObject.put("startDate", startDate);
			parameterObject.put("endDate", endDate);
			parameterObject.put("qryType", qryType);
			parameterObject.put("deptid", deptid);
			parameterObject.put("cpnyId", cpnyId);
			parameterObject.put("key", key);
//			
			parameterObject.put("adminid", admin.getAdminID());
			
	       //取得数据行数
			int resultCount = emServices.expressConfirmInfoListNumber(parameterObject);
			List expressConfirmInfoList=emServices.expressConfirmInfoList(parameterObject,currentPage,pageSize);
			
			request.setAttribute("deptID",parameterObject.getString("deptID"));
			request.setAttribute("postNumber",parameterObject.getString("postNumber"));
			request.setAttribute("sendPerson",parameterObject.getString("sendPerson"));
			request.setAttribute("expressConfirmInfoList",expressConfirmInfoList);
			
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptid", deptid);
			request.setAttribute("cpnyId",cpnyId);
			request.setAttribute("key", key);
			
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			if(expressConfirmInfoList==null ||expressConfirmInfoList.size()==0){
			 request.setAttribute("errorMsg", "暂无相关信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("get GM expressConfirmInfo Exception. ", e);
		}		 
	 return "/gm_express_confirm.jsp?menu_code="+ parameterObject.getString("menu_code");
//	 gm0401
   }
   public String oingConfirm(HttpServletRequest request,AdminBean admin){
	   boolean temp = false;
	   try{
			String[] applyno = request.getParameterValues("applynoAr") ;
		   
			SimpleMap parameterObject = null;	
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			
			for(int i= 0 ; i < applyno.length ; ++i)
			{
				parameterObject.set("applynoAr", applyno[i]);
				parameterObject.set("affirmorIdea", request.getParameter("affirmorIdea_"+applyno[i]));
				temp = emServices.oingConfirm(parameterObject);
				
				///sendExpressConfirmMail(expressConfirmInfo) ;
			}
			

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" oingConfirm  happends Exception. ", e);
		}
	  return this.expressConfirmInfo(request, admin);
	   
   }
   // 快件确认发邮件
	private void sendExpressConfirmMail(SimpleMap expressConfirmInfo) {

		SimpleMap parameterObject = new SimpleMap();

		SimpleMap inputData = new SimpleMap();
		// List result = seServices.getapplyemail(parameterObject);

		try
		{
			StringBuffer content = new StringBuffer();

			content.append(" 申请人：").append(expressConfirmInfo.getString("APPLYEMPNAME"))
				   .append("<br><br>").append(" 主题：").append("快件信息已确认");

			content.append("<br><br>").append(" 寄送城市：").append(expressConfirmInfo.getString("CITYSENDTO"));
			content.append("<br><br>").append(" 收件单位：").append(expressConfirmInfo.getString("POSTADDRESS"));

			content.append("<br><br>").append(" 邮件内容：").append(expressConfirmInfo.getString("POSTDESCRIPTION"));

			content.append("<br><br>").append("<a href=" + url + " target=\"_blank\">快件信息已确认,点击此处进行查看</a>")
			       .append("<br><br>斗山工程机械(中国)有限公司");

			// set email title
			inputData.setString("EMAIL_TITLE", expressConfirmInfo.getString("SENDPERSONNAME") + "," + "快件信息已确认");

			// set email content
			inputData.setString("EMAIL_CONTNT", content.toString());

			// for(int i=0;i<result.size();i++){
			if(!StringUtil.checkNull(expressConfirmInfo.getString("APPLYEMPEMAIL")).equals("") && StringUtil.checkNull(expressConfirmInfo.getString("APPLYEMPEMAIL"))!=null){
			inputData.setString("RCVR_EMAIL_ADDR", expressConfirmInfo.getString("APPLYEMPEMAIL").toString());
			mail.sendMail(inputData);
			}
			// }
			// essServices.insertOtAffirmMail(inputData);
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace() ;
		}
		
	}
	
   
   
   public String expressSend(HttpServletRequest request,AdminBean admin){	
	   SimpleMap parameterObject = null;
	   try{
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());

			int pageSize =10;
			int pageGroupSize =10;
			int currentPage = 0;
		
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
			String deptID = request.getParameter("deptID")!=null?request.getParameter("deptID"):"";
			String postNumber = request.getParameter("postNumber")!=null?request.getParameter("postNumber"):"";
			String sendPerson = request.getParameter("sendPerson")!=null?request.getParameter("sendPerson"):"";
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			parameterObject.put("deptID", deptID);
			parameterObject.put("cpnyId", cpnyId);
			parameterObject.put("postNumber", postNumber);
			parameterObject.put("sendPerson", sendPerson);
			
	       //取得数据行数
			int resultCount = emServices.expressSendListNumber(parameterObject);
			List expressConfirmInfoList=emServices.expressSendList(parameterObject,currentPage,pageSize);
			
			request.setAttribute("expressConfirmInfoList",expressConfirmInfoList);
			request.setAttribute("postNumber",parameterObject.getString("postNumber"));
			request.setAttribute("sendPerson",parameterObject.getString("sendPerson"));
			request.setAttribute("expressConfirmInfoList",expressConfirmInfoList);
			
			request.setAttribute("deptID", deptID);
			request.setAttribute("cpnyId", request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") :  admin.getCpnyId());
			request.setAttribute("postNumber", postNumber);
			request.setAttribute("sendPerson", sendPerson);
			
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			if(expressConfirmInfoList==null||expressConfirmInfoList.size()==0){
				request.setAttribute("errorMsg", "暂无相关信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("expressSend happens Exception. ", e);
		}		 
	 return "/gm_express_send.jsp?menu_code="+ parameterObject.getString("menu_code");
	   
   }
   
   public String ingSend(HttpServletRequest request,AdminBean admin){
	   boolean temp = false;
	   try{
			
			SimpleMap parameterObject = null;
			String[]applnoAry=request.getParameterValues("selectApplyno");			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			temp=emServices.ingSend(parameterObject,applnoAry);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" ingSend  happends Exception. ", e);
		}
	 if(!temp){
		request.setAttribute("errorMsg", "操作失败！");	
		}
	 this.expressSend(request, admin);
	 return "/gm_express_send.jsp";
	   
   }
   public String expressView(HttpServletRequest request,AdminBean admin){	  
	   try{
			
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());	
			int pageSize =10;
			int pageGroupSize =10;
			int currentPage = 0;
		
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
	       //取得数据行数
			int resultCount = emServices.expressViewListNumber(parameterObject);
			List expressConfirmInfoList=emServices.expressViewList(parameterObject,currentPage,pageSize);
			request.setAttribute("sendPerson", request.getParameter("sendPerson"));
			request.setAttribute("postNumber", request.getParameter("postNumber"));
			request.setAttribute("EndDate", request.getParameter("EndDate"));
			request.setAttribute("StartDate", request.getParameter("StartDate"));
			request.setAttribute("citysendto", request.getParameter("citysendto"));
			request.setAttribute("deptID", request.getParameter("deptID"));
			request.setAttribute("expressConfirmInfoList",expressConfirmInfoList);
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			if(expressConfirmInfoList==null ||expressConfirmInfoList.size()==0){
			 request.setAttribute("errorMsg", "暂无相关信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("get GM expressConfirmInfo Exception. ", e);
		}		 
			return "/gm_express_view.jsp";
	   
   }
   
   public String expressViewExcel(HttpServletRequest request,AdminBean admin){	  
	   try{
			
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());	
			int pageSize =100000;
			int pageGroupSize =10;
			int currentPage = 0;
		
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
	       //取得数据行数
//			int resultCount = emServices.expressViewListNumber(parameterObject);
			List expressConfirmInfoList=emServices.expressViewList(parameterObject,currentPage,pageSize);
			
			int sumCost = emServices.costNumber(parameterObject);
			request.setAttribute("sumCost", sumCost);
			
			request.setAttribute("sendPerson", request.getParameter("sendPerson"));
			request.setAttribute("postNumber", request.getParameter("postNumber"));
			request.setAttribute("EndDate", request.getParameter("EndDate"));
			request.setAttribute("StartDate", request.getParameter("StartDate"));
			request.setAttribute("citysendto", request.getParameter("citysendto"));
			request.setAttribute("deptID", request.getParameter("deptID"));
			request.setAttribute("expressConfirmInfoList",expressConfirmInfoList);
//			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			if(expressConfirmInfoList==null ||expressConfirmInfoList.size()==0){
			 request.setAttribute("errorMsg", "暂无相关信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("get GM expressConfirmInfo Exception. ", e);
		}		 
			return "/reports/gm_report/gm_express_report.jsp";
	   
   }
   
   public String confirmCosts(HttpServletRequest request,AdminBean admin){
	   boolean temp = false;
	   try{
			
			SimpleMap parameterObject = null;		
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			temp=emServices.confirmCosts(parameterObject);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" confirmCosts  happends Exception. ", e);
		}
	 if(!temp){
		request.setAttribute("errorMsg", "操作失败！");	
		}
	 this.expressView(request, admin);
	 return "/gm_express_view.jsp";
	   
   }
   
   public void EMSDetailsExcel(HttpServletRequest request,AdminBean admin){	  
	   try{
			
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("cpny_id", admin.getCpnyId());
			parameterObject.set("applerId", admin.getAdminID());			
			List EMSDetailsExcelList=emServices.EMSDetailsExcel(parameterObject);			
			request.setAttribute("EMSDetailsExcelList", EMSDetailsExcelList);			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("get GM EMSDetailsExcel Exception. ", e);
		}		 
			
	   
   }
   public void EMSTotalExcelList(HttpServletRequest request,AdminBean admin){	  
	   try{
			
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("excelStartDate2", request.getParameter("excelStartDate_2"));
			parameterObject.set("excelEndDate2", request.getParameter("excelEndDate_2"));
//			CPNY_ID
			parameterObject.set("CPNY_ID", admin.getCpnyId());
			List EMSTotalExcelList=emServices.EMSTotalExcelList(parameterObject);			
			request.setAttribute("EMSTotalExcelList", EMSTotalExcelList);			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("get GM EMSDetailsExcel Exception. ", e);
		}		 
			
	   
   }
   public void EMSStatisticsExcel(HttpServletRequest request,AdminBean admin){	  
	   try{
			
			SimpleMap parameterObject = null;
			Date d= new Date();			
			SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");		
			String sysDate=(request.getParameter("excelStartDate2").substring(0,4))+"-01-01";
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("sysyear", sysDate);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("CPNY_ID", admin.getCpnyId());
			List EMSStatisticsExcel=emServices.EMSStatisticsExcel(parameterObject);			
			request.setAttribute("EMSStatisticsExcel", EMSStatisticsExcel);			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("get GM EMSStatisticsExcel Exception. ", e);
		}		 
			
   }
   
   public void expressInstallSave(HttpServletRequest request,AdminBean admin){	  
	   try{
			
			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());	
			parameterObject.set("cpny_id", admin.getCpnyId());
			emServices.expressInstallSave(parameterObject);			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("expressInstallSave Exception. ", e);
		}		 
	   
   }
   
   public void expressInstallUpdate(HttpServletRequest request,AdminBean admin){	  
	   try{
			
			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());			
			emServices.expressInstallUpdate(parameterObject);			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("expressInstallSave Exception. ", e);
		}		 
	   
   }
   
   public String  expressInstall(HttpServletRequest request,AdminBean admin){	  
	   try{
			
			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("cpny_id", admin.getCpnyId());		
			List list = emServices.getExpressInstall(parameterObject);
			request.setAttribute("list", list);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("expressInstallSave Exception. ", e);
		}		 
	   return "/gm_expressInstall_Setup.jsp";
   }
   
   public String  ViewexpressInstallUpdate(HttpServletRequest request,AdminBean admin){	  
	   try{
			
			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("id", parameterObject.getString("id"));		
			List list = emServices.ViewexpressInstallUpdate(parameterObject);
			SimpleMap simpleMap = new SimpleMap();
			simpleMap = (SimpleMap)list.get(0);
			request.setAttribute("id", simpleMap.getString("ID"));
			request.setAttribute("ORDERNUM", simpleMap.getString("ORDERNUM"));
			request.setAttribute("GIVE_CITY", simpleMap.getString("GIVE_CITY"));
			request.setAttribute("ARRIVE_CITY", simpleMap.getString("ARRIVE_CITY"));
			request.setAttribute("EXPENSES_TYPE", simpleMap.getString("EXPENSES_TYPE"));
			request.setAttribute("EMAIL_EXPENSES", simpleMap.getString("EMAIL_EXPENSES"));
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ViewexpressInstallUpdate Exception. ", e);
		}		 
	   return "/gm_UpdataExpressInstall_Setup.jsp";
   }
   
   public String  expressInstallDel(HttpServletRequest request,AdminBean admin){	  
	   try{
			
			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("id", parameterObject.getString("id"));		
		    emServices.expressInstallDel(parameterObject);
		    
		   
			parameterObject.set("cpny_id", admin.getCpnyId());		
			List list = emServices.getExpressInstall(parameterObject);
			request.setAttribute("list", list);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("expressInstallDel Exception. ", e);
		}		 
		return "/gm_expressInstall_Setup.jsp";
   }
   
   
}
