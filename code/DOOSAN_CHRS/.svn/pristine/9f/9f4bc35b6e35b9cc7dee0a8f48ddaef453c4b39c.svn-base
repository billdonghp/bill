package com.ait.ev.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ait.ar.bean.ArDetailBack;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.ess.base.ErrorMessage;
import com.ait.ess.bean.EssAffirmParam;
import com.ait.ess.bean.EssAffirmor;
import com.ait.ess.bean.EssLeaveBean;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.ess.business.EssServices;
import com.ait.ess.dao.EssArDAO;
import com.ait.ev.bean.EvaluateAffirmParam;
import com.ait.ev.bean.EvaluateInfo;
import com.ait.ev.business.EvaluateApplyServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.FormUtil;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (zhangji@ait.net.cn)
 * @Date 2011-10-17
 * 
 */
public class EvaluateQueryCommand extends ArAbstractCommand {
	
	private EvaluateApplyServices evServices =null;
	
	private Mail mail ;
	
	private EssSysparam essSysparam = null;
	
	private String cpnyId;
	
	private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	public EvaluateQueryCommand(){
		evServices = new EvaluateApplyServices();
		
		mail = new Mail() ;
	}

	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		String adminid=admin.getAdminID();
		cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		String returnPage=null;
		if(!content.equals("") && content.equals("evaluateQueryDetail")){
			this.getEvaluateQueryDetailView(request, admin);
			returnPage="/ev_query_detail_view.jsp";
		}
		else if(!content.equals("") && content.equals("evaluateQueryDetailExcel")){
			this.getEvaluateQueryDetailExcel(request, admin);
			returnPage="/ev_query_detail_excel.jsp";
		}else if(!content.equals("") && content.equals("evaluateQueryResult")){
			this.getEvaluateQueryResultView(request, admin);
			returnPage="/ev_query_result_view.jsp";
		}else if(!content.equals("") && content.equals("evaluateQueryResultExcel")){
			this.getEvaluateQueryResultExcel(request, admin);
			returnPage="/ev_query_result_excel.jsp";
		}else if(!content.equals("") && content.equals("evaluateQueryResultExcelSum")){
			this.getEvaluateQueryResultExcelSum(request, admin);
			returnPage="/ev_query_result_excel_sum.jsp";
		}
		
		else{
			return "error.jsp";
		}
//		
		return returnPage;
	}
	
	

	/*查询当月评价明细信息*/
	 public String getEvaluateQueryDetailView(HttpServletRequest request,AdminBean admin){	  
		
		    cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
			String adminId = admin.getAdminID();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			//List deptList = new ArrayList();

			try {
				
				String key = StringUtil.checkNull(request.getParameter("key"));
				


				parameterObject.put("key", key);
				
				
				parameterObject.set("cpnyId", admin.getCpnyId());
				parameterObject.set("ADMIN_ID", admin.getAdminID());
				parameterObject.set("PERSON_ID", admin.getAdminID());
				
				
				String empid=evServices.retrieveEvAffirmAdmin(parameterObject);
				
				if(!"".equals(empid)){
					parameterObject.set("EV_ADMIN_ID", "");
				}else{
					parameterObject.set("EV_ADMIN_ID", admin.getAdminID());
				}
				
				
				List positionList=evServices.getEvaluateQueryPositionList(parameterObject);
				//List deptList=evServices.getEvaluateApplyDeptList(parameterObject);
				
				
				///List positionList=evServices.getEvaluateApplyPositionList(parameterObject);
				
				String yearMonth = request.getParameter("year") + request.getParameter("month");
				
				parameterObject.set("dept", request.getParameter("DeptId"));
				
				//parameterObject.set("dept", request.getParameter("selectDept"));
				parameterObject.set("position", request.getParameter("selectPosition"));
				parameterObject.set("yearMonth", yearMonth);
				parameterObject.set("level", request.getParameter("level"));
								
				List itemList = evServices.getEvaluateItem(parameterObject.getString("yearMonth"),parameterObject.getString("dept"),parameterObject.getString("position"),parameterObject.getString("ADMIN_ID"),1);
				/* paging logic */
				UserConfiguration config = UserConfiguration
						.getInstance("/system.properties");
				int pageSize = config.getInt("page.style6.pagesize");
				int pageGroupSize = config.getInt("page.style6.pagegroupsize");
				int currentPage = 0;
				// if has currentpage set value into currentPage
				if (request.getParameter("currentPage") != null
						&& !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request
							.getParameter("currentPage"));

				// 取得数据行数
				parameterObject.set("supervisor", adminId);
				
				int resultCount = evServices.getEvaluateQueryDetailListCnt(parameterObject);
				// 如果"当前页"大于最大页数,取最后一页
				if (currentPage > (resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize)
						&& resultCount != 0) {
					currentPage = resultCount % pageSize == 0 ? resultCount
							/ pageSize - 1 : resultCount / pageSize;
				}

				
				List evaluateList=evServices.getEvaluateQueryDetailList(parameterObject,currentPage,pageSize);
				
				
				

			
//				request.setAttribute("deptID", deptID);
				//request.setAttribute("deptList", deptList);
				request.setAttribute("key", key);
				request.setAttribute("positionList",positionList);
				//request.setAttribute("deptList",deptList);
				request.setAttribute("DeptId", request.getParameter("DeptId"));
				//request.setAttribute("selectDept", request.getParameter("selectDept"));
				request.setAttribute("selectPosition", request.getParameter("selectPosition"));
				request.setAttribute("year", request.getParameter("year"));
				request.setAttribute("month", request.getParameter("month"));
				request.setAttribute("level", request.getParameter("level"));
				//request.setAttribute("itemName",itemName);
				request.setAttribute("itemList",itemList);
				
				
				request.setAttribute("evaluateList", evaluateList);

				// paging parameter
				request.setAttribute("resultCount", resultCount);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageGroupsize", pageGroupSize);
			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).error(e.toString());
			}
			
			
		 return "/ev_query_detail_view.jsp?menu_code="
			+ request.getParameter("menu_code");
		   
	   }
	 
	 
	 /*查询当月评价明细信息*/
	 public String getEvaluateQueryDetailExcel(HttpServletRequest request,AdminBean admin){	  
	
		    cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
			String adminId = admin.getAdminID();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			//List deptList = new ArrayList();

			try {
				
				String key = StringUtil.checkNull(request.getParameter("key"));
				


				parameterObject.put("key", key);
				
				parameterObject.set("ADMIN_ID", admin.getAdminID());
				parameterObject.set("cpnyId", admin.getCpnyId());
				
				
				//List positionList=evServices.getEvaluatePositionSetList(parameterObject);
				///List deptList=evServices.getEvaluateApplyDeptList(parameterObject);
				///List positionList=evServices.getEvaluateApplyPositionList(parameterObject);
				
				String yearMonth = request.getParameter("year") + request.getParameter("month");
				
				parameterObject.set("dept", request.getParameter("DeptId"));
				//parameterObject.set("dept", request.getParameter("selectDept"));
				parameterObject.set("position", request.getParameter("selectPosition"));
				parameterObject.set("yearMonth", yearMonth);
				parameterObject.set("level", request.getParameter("level"));
								
				List itemList = evServices.getEvaluateItem(parameterObject.getString("yearMonth"),parameterObject.getString("dept"),parameterObject.getString("position"),parameterObject.getString("ADMIN_ID"),1);
				

				// 取得数据行数
				parameterObject.set("supervisor", adminId);
				parameterObject.set("PERSON_ID", admin.getAdminID());
				String empid=evServices.retrieveEvAffirmAdmin(parameterObject);
				
				if(!"".equals(empid)){
					parameterObject.set("EV_ADMIN_ID", "");
				}else{
					parameterObject.set("EV_ADMIN_ID", admin.getAdminID());
				}
				
				List evaluateList=evServices.getEvaluateQueryDetailExcelList(parameterObject);
				
				
				

			
//				request.setAttribute("deptID", deptID);
//				request.setAttribute("deptList", deptList);
				request.setAttribute("itemList", itemList);
				request.setAttribute("key", key);
				//request.setAttribute("positionList",positionList);
				//request.setAttribute("deptList",deptList);
				request.setAttribute("DeptId", request.getParameter("DeptId"));
				request.setAttribute("selectPosition", request.getParameter("selectPosition"));
				request.setAttribute("year", request.getParameter("year"));
				request.setAttribute("month", request.getParameter("month"));
				request.setAttribute("level", request.getParameter("level"));
				//request.setAttribute("itemName",itemName);
				
				
				
				request.setAttribute("evaluateList", evaluateList);

				// paging parameter
//				request.setAttribute("resultCount", resultCount);
//				request.setAttribute("currentPage", currentPage);
//				request.setAttribute("pageSize", pageSize);
//				request.setAttribute("pageGroupsize", pageGroupSize);
			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).error(e.toString());
			}
			
			
		 return "/ev_query_detail_excel.jsp?menu_code="
			+ request.getParameter("menu_code");
		   
	   }
	 
	 /*查询跨月评价明细信息*/
	 public String getEvaluateQueryResultView(HttpServletRequest request,AdminBean admin){	  
		
		    cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
			String adminId = admin.getAdminID();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			//List deptList = new ArrayList();
			
			int qryType = Integer.parseInt(StringUtil.checkNull(request
					.getParameter("qryType"), "0"));

			try {
				
				String key = StringUtil.checkNull(request.getParameter("key"));
				
				if(request.getParameter("year")==null || request.getParameter("month")==null ||
						request.getParameter("year1")==null || request.getParameter("month1")==null){
					
					// 取日期参数
					GregorianCalendar currentDay = new GregorianCalendar();
					int m = currentDay.get(Calendar.MONTH) + 1;
					int y = currentDay.get(Calendar.YEAR);

					String month = ("0" + String.valueOf(m)).substring(("0" + String
							.valueOf(m)).length() - 2, ("0" + String.valueOf(m)).length());
					String year = String.valueOf(y);

					parameterObject.set("yearMonth", year + month);
					parameterObject.set("yearMonth1", year + month);
					request.setAttribute("year", year);
					request.setAttribute("month", month);
					request.setAttribute("year1", year);
					request.setAttribute("month1", month);
					
				}else{
					String yearMonth = request.getParameter("year") + request.getParameter("month");
					String yearMonth1 = request.getParameter("year1") + request.getParameter("month1");
					parameterObject.set("yearMonth", yearMonth);
					parameterObject.set("yearMonth1", yearMonth1);
					request.setAttribute("year", request.getParameter("year"));
					request.setAttribute("month", request.getParameter("month"));
					request.setAttribute("year1", request.getParameter("year1"));
					request.setAttribute("month1", request.getParameter("month1"));
				}

				parameterObject.set("key", key);
				parameterObject.put("qryType", qryType);
				parameterObject.set("ADMIN_ID", admin.getAdminID());
				parameterObject.set("PERSON_ID", admin.getAdminID());
				parameterObject.set("cpnyId", admin.getCpnyId());
				
                String empid=evServices.retrieveEvAffirmAdmin(parameterObject);
				
				if(!"".equals(empid)){
					parameterObject.set("EV_ADMIN_ID", "");
				}else{
					parameterObject.set("EV_ADMIN_ID", admin.getAdminID());
				}
				
				List positionList=evServices.getEvaluateQueryPositionList(parameterObject);
				//List deptList=evServices.getEvaluateApplyDeptList(parameterObject);
				///List positionList=evServices.getEvaluateApplyPositionList(parameterObject);
				
				String yearMonth = request.getParameter("year") + request.getParameter("month");
				
				parameterObject.set("dept", request.getParameter("DeptId"));
				//parameterObject.set("dept", request.getParameter("selectDept"));
				parameterObject.set("position", request.getParameter("selectPosition"));
				//parameterObject.set("yearMonth", yearMonth);
				//parameterObject.set("level", request.getParameter("level"));
				
				//String itemName = evServices.getEvaluateApplyItem(parameterObject);
				

				/* paging logic */
				UserConfiguration config = UserConfiguration
						.getInstance("/system.properties");
				int pageSize = config.getInt("page.style6.pagesize");
				int pageGroupSize = config.getInt("page.style6.pagegroupsize");
				int currentPage = 0;
				// if has currentpage set value into currentPage
				if (request.getParameter("currentPage") != null
						&& !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request
							.getParameter("currentPage"));

				// 取得数据行数
				parameterObject.set("supervisor", adminId);
				
				
				
				
				List evaluateList = new ArrayList();
				int resultCount =0;
				
				if(qryType==4){
					
					resultCount = evServices.getEvaluateQueryNoApplyListCnt(parameterObject);
				
					evaluateList=evServices.getEvaluateQueryNoApplyList(parameterObject,currentPage,pageSize);
					
					for (int i = 0; i < evaluateList.size(); i++) {
						
						EvaluateInfo evi = (EvaluateInfo) evaluateList.get(i);
						
						SimpleMap parameterObject1 = new SimpleMap();
						parameterObject1.setString("DEPTID", evi.getDEPTID()) ;
						parameterObject1.setString("POSTCODE", evi.getPOST_CODE());

						evi.setAffirmorList((ArrayList) evServices.getEvaluateAffirmorAllList(parameterObject1));
							
					}
					
					
				}else{
					resultCount = evServices.getEvaluateQueryResultListCnt(parameterObject);
					
					evaluateList=evServices.getEvaluateQueryResultList(parameterObject,currentPage,pageSize);
					
					EvaluateInfo evaluateInfo = new EvaluateInfo();
					for (int i = 0; i < evaluateList.size(); i++) {
						
						EvaluateInfo evi = (EvaluateInfo) evaluateList.get(i);

						evi.setAffirmorList((ArrayList) evServices.getAffirmorList(evi.getNo()));
							
					}
					
				}
				
				// 如果"当前页"大于最大页数,取最后一页
				if (currentPage > (resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize)
						&& resultCount != 0) {
					currentPage = resultCount % pageSize == 0 ? resultCount
							/ pageSize - 1 : resultCount / pageSize;
				}
				
				
				MessageSource messageSource = new MessageSource("ess", admin
						.getLocale(), "UTF-8");
				String message1 = messageSource
						.getMessage("display.ess.approval.pending");
				String message2 = messageSource
						.getMessage("display.ess.approval.approved");
				String message3 = messageSource
						.getMessage("display.ess.approval.rejected");
				Map statusMap = new HashMap();
				Map colorMap = new HashMap();
				if (evaluateList.size() > 0) {
					statusMap.put("0", message1);
					statusMap.put("1", message2);
					statusMap.put("2", message3);

					colorMap.put("0", "#990099");
					colorMap.put("1", "#00CC00");
					colorMap.put("2", "#CC0000");
				}
				
				request.setAttribute("statusMap", statusMap);
				request.setAttribute("colorMap", colorMap);
				
				request.setAttribute("qryType", qryType);
				//request.setAttribute("deptList", deptList);
				request.setAttribute("key", key);
				request.setAttribute("positionList",positionList);

				request.setAttribute("DeptId", request.getParameter("DeptId"));
				//request.setAttribute("selectDept", request.getParameter("selectDept"));
				request.setAttribute("selectPosition", request.getParameter("selectPosition"));
				//request.setAttribute("year", request.getParameter("year"));
				//request.setAttribute("month", request.getParameter("month"));
				request.setAttribute("evaluateList", evaluateList);

				// paging parameter
				request.setAttribute("resultCount", resultCount);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageGroupsize", pageGroupSize);
			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).error(e.toString());
			}
			
			
		 return "/ev_query_result_view.jsp?menu_code="
			+ request.getParameter("menu_code");
		   
	   }
	 
	 /*查询跨月评价明细信息*/
	 public String getEvaluateQueryResultExcel(HttpServletRequest request,AdminBean admin){	  
	
		    cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
			String adminId = admin.getAdminID();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			//List deptList = new ArrayList();

			try {
				
				int qryType = Integer.parseInt(StringUtil.checkNull(request
						.getParameter("qryType"), "0"));
				String key = StringUtil.checkNull(request.getParameter("key"));
				
				parameterObject.put("key", key);
				
				parameterObject.set("ADMIN_ID", admin.getAdminID());
				parameterObject.set("cpnyId", admin.getCpnyId());
				
				//List positionList=evServices.getEvaluatePositionSetList(parameterObject);
				///List deptList=evServices.getEvaluateApplyDeptList(parameterObject);
				///List positionList=evServices.getEvaluateApplyPositionList(parameterObject);
				
			
				String yearMonth = request.getParameter("year") + request.getParameter("month");
				String yearMonth1 = request.getParameter("year1") + request.getParameter("month1");
				parameterObject.set("yearMonth", yearMonth);
				parameterObject.set("yearMonth1", yearMonth1);
				
				parameterObject.set("dept", request.getParameter("DeptId"));
				parameterObject.set("position", request.getParameter("selectPosition"));
				
				parameterObject.put("qryType", request.getParameter("qryType"));
			//	parameterObject.set("level", request.getParameter("level"));
				
				//String itemName = evServices.getEvaluateApplyItem(parameterObject);


				// 取得数据行数
				parameterObject.set("supervisor", adminId);
				parameterObject.set("PERSON_ID", admin.getAdminID());
				String empid=evServices.retrieveEvAffirmAdmin(parameterObject);
				
				if(!"".equals(empid)){
					parameterObject.set("EV_ADMIN_ID", "");
				}else{
					parameterObject.set("EV_ADMIN_ID", admin.getAdminID());
				}

				
				
				List evaluateList = new ArrayList();
				int resultCount =0;
				
				if(qryType==4){
					
				
					evaluateList=evServices.getEvaluateQueryNoApplyExcelList(parameterObject);
					
					for (int i = 0; i < evaluateList.size(); i++) {
						
						EvaluateInfo evi = (EvaluateInfo) evaluateList.get(i);
						
						SimpleMap parameterObject1 = new SimpleMap();
						parameterObject1.setString("DEPTID", evi.getDEPTID()) ;
						parameterObject1.setString("POSTCODE", evi.getPOST_CODE());

						evi.setAffirmorList((ArrayList) evServices.getEvaluateAffirmorAllList(parameterObject1));
							
					}
					
					
				}else{
					
					evaluateList=evServices.getEvaluateQueryResultExcelList(parameterObject);
					
					EvaluateInfo evaluateInfo = new EvaluateInfo();
					for (int i = 0; i < evaluateList.size(); i++) {
						
						EvaluateInfo evi = (EvaluateInfo) evaluateList.get(i);

						evi.setAffirmorList((ArrayList) evServices.getAffirmorList(evi.getNo()));
							
					}
					
				}
				
				
				
//				List evaluateList=evServices.getEvaluateQueryResultExcelList(parameterObject);
//				
//				
//				EvaluateInfo evaluateInfo = new EvaluateInfo();
//				for (int i = 0; i < evaluateList.size(); i++) {
//					
//					EvaluateInfo evi = (EvaluateInfo) evaluateList.get(i);
//
//					evi.setAffirmorList((ArrayList) evServices.getAffirmorList(evi.getNo()));
//						
//				}
				MessageSource messageSource = new MessageSource("ess", admin
						.getLocale(), "UTF-8");
				String message1 = messageSource
						.getMessage("display.ess.approval.pending");
				String message2 = messageSource
						.getMessage("display.ess.approval.approved");
				String message3 = messageSource
						.getMessage("display.ess.approval.rejected");
				Map statusMap = new HashMap();
				Map colorMap = new HashMap();
				if (evaluateList.size() > 0) {
					statusMap.put("0", message1);
					statusMap.put("1", message2);
					statusMap.put("2", message3);

					colorMap.put("0", "#990099");
					colorMap.put("1", "#00CC00");
					colorMap.put("2", "#CC0000");
				}
				request.setAttribute("statusMap", statusMap);
				request.setAttribute("colorMap", colorMap);

//				request.setAttribute("deptID", deptID);
//				request.setAttribute("deptList", deptList);
				request.setAttribute("key", key);
				//request.setAttribute("positionList",positionList);
				//request.setAttribute("deptList",deptList);
				request.setAttribute("DeptId", request.getParameter("DeptId"));
				request.setAttribute("selectPosition", request.getParameter("selectPosition"));
				request.setAttribute("year", request.getParameter("year"));
				request.setAttribute("month", request.getParameter("month"));
			//	request.setAttribute("level", request.getParameter("level"));
			//	request.setAttribute("itemName",itemName);
				
				
				
				request.setAttribute("evaluateList", evaluateList);

			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).error(e.toString());
			}
			
			
		 return "/ev_query_result_excel.jsp?menu_code="
			+ request.getParameter("menu_code");
		   
	   }
	 
	 /*年份差评汇总报表*/
	 public String getEvaluateQueryResultExcelSum(HttpServletRequest request,AdminBean admin){	  
	
		    cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
			String adminId = admin.getAdminID();
			SimpleMap parameterObject = new SimpleMap();
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());

			try {
				
				String key = StringUtil.checkNull(request.getParameter("key"));
				parameterObject.set("ADMIN_ID", admin.getAdminID());
				parameterObject.set("cpnyId", admin.getCpnyId());

				String year = request.getParameter("yearSum");
				parameterObject.set("year", year);
				parameterObject.set("PERSON_ID", admin.getAdminID());
		 
				// 取得数据行数
				parameterObject.set("supervisor", adminId);
				
				String empid=evServices.retrieveEvAffirmAdmin(parameterObject);
				
				if(!"".equals(empid)){
					parameterObject.set("EV_ADMIN_ID", "");
				}else{
					parameterObject.set("EV_ADMIN_ID", admin.getAdminID());
				}

				List evaluateList=evServices.getEvaluateQueryResultExcelSumList(parameterObject);


				request.setAttribute("yearSum", request.getParameter("yearSum"));

				request.setAttribute("evaluateList", evaluateList);

			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).error(e.toString());
			}
			
			
		 return "/ev_query_result_excel_sum.jsp?menu_code="
			+ request.getParameter("menu_code");
		   
	   }
	
	
}
