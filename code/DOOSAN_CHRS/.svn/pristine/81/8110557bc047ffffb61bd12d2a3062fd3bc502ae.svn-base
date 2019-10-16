package com.ait.pu.cmd;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.ga.business.ExpressApplyAndAffirmServices;
import com.ait.mail.Mail;
import com.ait.pu.services.ConferenceRoomServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.StringUtil;
import com.ait.web.Command;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-20
 * 
 */
public class ConferenceRoomCommand implements Command {
	private ConferenceRoomServices crServices=null;
	
	private ExpressApplyAndAffirmServices eaaServices = null;
	
	private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	private Mail mail = new Mail() ;
	
	public ConferenceRoomCommand(){
		crServices= new ConferenceRoomServices();
		eaaServices= new ExpressApplyAndAffirmServices();
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
		if(!content.equals("") && content.equals("conferenceRoomConfirm")){
			returnPage=this.getConferenceRoomConfirmList(request, admin);
		}else if(!content.equals("") && content.equals("conferenceRoomConfirm1")){
			returnPage=this.getConferenceRoomConfirmList1(request, admin);
		}else if(!content.equals("") && content.equals("conferenceRoomExcel")){
			returnPage=this.getConferenceRoomExcel(request, admin);
		}else if(!content.equals("") && content.equals("oingConfirm")){
			returnPage=this.oingConfirmConferenceRoom(request, admin);
		}else if(!content.equals("") && content.equals("saveUpdate")){
			returnPage=this.saveUpdate(request, admin);
		}else if(!content.equals("") && content.equals("viewRoomInfo")){
			returnPage=this.viewRoomInfo(request, admin);
		}else if(!content.equals("") && content.equals("deleteApply")){
			returnPage=this.deleteApply(request, admin);
		}else if(!content.equals("") && content.equals("Delete")){
			returnPage=this.Delete(request, admin);
		}
		
		else{
			return "error.jsp";
		}
		return returnPage;
     	}
	/*得到已决裁的会议室信息*/
	 public String getConferenceRoomConfirmList(HttpServletRequest request,AdminBean admin){
		 SimpleMap parameterObject = null;
		 
		   try{
			   parameterObject = ObjectBindUtil.getData(request);
				SimpleMap map = null;
				SimpleMap tempMap = null;
				SimpleMap tempMap1 = null;

				String companyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
				
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());
				int pageSize =10;
				int pageGroupSize =10;
				int currentPage = 0;
//				parameterObject.set("cpny_id", admin.getCpnyId());
				parameterObject.set("cpny_id", companyId);
				
				if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				Date date=new Date();
				String shijian;
				SimpleDateFormat formater=new SimpleDateFormat();
				formater.applyPattern("yyyy-MM-dd");
				shijian=formater.format(date);
				
		       //取得数据行数
				int resultCount = 0;
				List conferenceRoomConfirmList = null;
				String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
				parameterObject.set("qryType", qryType);
				String startDate = parameterObject.getString("startDate")!=null?parameterObject.getString("startDate"):"";
				parameterObject.set("startDate", startDate);
				resultCount = crServices.getConferenceRoomConfirmListNumber(parameterObject);
				conferenceRoomConfirmList=crServices.getConferenceRoomConfirmList(parameterObject,currentPage,pageSize);
				
				for(int i=0 ; i<conferenceRoomConfirmList.size() ; i++){
					map = (SimpleMap) conferenceRoomConfirmList.get(i);
					int applyno = Integer.parseInt(map.getString("APPLYNO"));
					String roomid = map.getString("CONFERENCEROOM");
					String startime = map.getString("BOOKSTARTTIME");
					String endtime = map.getString("BOOKENDTIME");
					String starStr[] = startime.split(":");
					String endStr[] = endtime.split(":");
					map.set("hour", starStr[0]);
					map.set("min", starStr[1]);
					map.set("endhour", endStr[0]);
					map.set("endmin", endStr[1]);
					
					parameterObject.set("applyno", applyno);
					parameterObject.set("roomid", roomid);
					int equipsCount = crServices.getequipsCount(parameterObject);
					List equipsList = crServices.equipsList(parameterObject);
					List equipsApplyList = crServices.equipsApplyList(parameterObject);
					
					for(int j = 0 ; j < equipsList.size() ; ++j)
					{
						tempMap = (SimpleMap)equipsList.get(j) ;
						if(tempMap.getString("EQUIP") != null)
							tempMap.set("EQUIP", Arrays.asList(StringUtil.checkNull(tempMap.getString("EQUIP")).split("　"))) ;
						else
							tempMap.set("EQUIP", Arrays.asList("")) ;
	
					}
					
					for(int k = 0 ; k < equipsApplyList.size() ; ++k)
					{
						
						tempMap1 = (SimpleMap)equipsApplyList.get(k) ;
						if(tempMap1.getString("EQUIPS") != null)
							tempMap1.set("EQUIPS", Arrays.asList(StringUtil.checkNull(tempMap1.getString("EQUIPS")).split("　"))) ;
						else
							tempMap1.set("EQUIPS", Arrays.asList("")) ;
	
					}
					
					map.set("equipsCount", equipsCount);
					map.set("equipsList", equipsList);
					map.set("equipsApplyList", equipsApplyList);
				}
				
				List roomname = eaaServices.roomnameList(parameterObject);
				request.setAttribute("conferenceRoomConfirmList",conferenceRoomConfirmList);
				request.setAttribute("roomname", roomname);
				request.setAttribute("resultCount", resultCount);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageGroupsize", pageGroupSize);
				if(conferenceRoomConfirmList==null ||conferenceRoomConfirmList.size()==0){
				 request.setAttribute("errorMsg", "暂无相关信息！");
				}else{
					request.setAttribute("errorMsg", "");
				}
				List listMM=DateUtil.getTimePerMMList();
				
				List listHH=DateUtil.getTimePerHourList();
				
				request.setAttribute("listMM",listMM);
				request.setAttribute("listHH",listHH);
				request.setAttribute("startDate", parameterObject.getString("startDate"));
				request.setAttribute("endDate", parameterObject.getString("endDate"));
				request.setAttribute("deptid", parameterObject.getString("deptid"));
				request.setAttribute("key", parameterObject.getString("key"));
				request.setAttribute("conferenceRoom", parameterObject.getString("conferenceRoom"));
				request.setAttribute("qryType", qryType);
				request.setAttribute("cpnyId", companyId);
				
				request.setAttribute("companyId", companyId);
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get pu  getConferenceRoomConfirmList Exception. ", e);
			}
				return "/pu_conferenceRoom_confirm.jsp?menu_code="+parameterObject.getString("menu_code");
		   
	   }
	 
	 /*得到已决裁的会议室信息*/
	 public String getConferenceRoomConfirmList1(HttpServletRequest request,AdminBean admin){	
		 SimpleMap parameterObject = null;
		   try{
				
				SimpleMap map = null;
				SimpleMap tempMap = null;
				SimpleMap tempMap1 = null;
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());	
				int pageSize =10;
				int pageGroupSize =10;
				int currentPage = 0;
				parameterObject.set("cpny_id", admin.getCpnyId());
				if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
		       //取得数据行数
				int resultCount = 0;
				List conferenceRoomConfirmList = null;
					String qryType = parameterObject.getString("qryType")!=null?parameterObject.getString("qryType"):"0";
					parameterObject.set("qryType", qryType);
					UserConfiguration userConfig;
					userConfig = UserConfiguration.getInstance("/system.properties");
					String[] sgNo = admin.getScreenGrantNo().split(",");
					boolean b = false;
					for (int i = 0; i < sgNo.length; i++) {
					if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
						b = true;
					parameterObject.set("applerId", "");
					parameterObject.set("ADMIN_ID", admin.getAdminID());
					}
				}
					resultCount = crServices.getConferenceRoomConfirmListNumber1(parameterObject);
					conferenceRoomConfirmList=crServices.getConferenceRoomConfirmList1(parameterObject,currentPage,pageSize);
					request.setAttribute("startDate", parameterObject.getString("startDate"));
					request.setAttribute("endDate", parameterObject.getString("endDate"));
					
					request.setAttribute("qryType", qryType);
				
				for(int i=0 ; i<conferenceRoomConfirmList.size() ; i++){
					map = (SimpleMap) conferenceRoomConfirmList.get(i);
					int applyno = Integer.parseInt(map.getString("APPLYNO"));
					String roomid = map.getString("CONFERENCEROOM");
					String startime = map.getString("BOOKSTARTTIME");
					String endtime = map.getString("BOOKENDTIME");
					String starStr[] = startime.split(":");
					String endStr[] = endtime.split(":");
					map.set("hour", starStr[0]);
					map.set("min", starStr[1]);
					map.set("endhour", endStr[0]);
					map.set("endmin", endStr[1]);
					
					parameterObject.set("applyno", applyno);
					parameterObject.set("roomid", roomid);
					int equipsCount = crServices.getequipsCount(parameterObject);
					List equipsList = crServices.equipsList(parameterObject);
					List equipsApplyList = crServices.equipsApplyList(parameterObject);
					
					for(int j = 0 ; j < equipsList.size() ; ++j)
					{
						tempMap = (SimpleMap)equipsList.get(j) ;
						if(tempMap.getString("EQUIP") != null)
							tempMap.set("EQUIP", Arrays.asList(StringUtil.checkNull(tempMap.getString("EQUIP")).split("　"))) ;
						else
							tempMap.set("EQUIP", Arrays.asList("")) ;
	
					}
					
					for(int k = 0 ; k < equipsApplyList.size() ; ++k)
					{
						
						tempMap1 = (SimpleMap)equipsApplyList.get(k) ;
						if(tempMap1.getString("EQUIPS") != null)
							tempMap1.set("EQUIPS", Arrays.asList(StringUtil.checkNull(tempMap1.getString("EQUIPS")).split("　"))) ;
						else
							tempMap1.set("EQUIPS", Arrays.asList("")) ;
	
					}
					
					map.set("equipsCount", equipsCount);
					map.set("equipsList", equipsList);
					map.set("equipsApplyList", equipsApplyList);
				}
				
				List roomname = eaaServices.roomnameList(parameterObject);
				request.setAttribute("conferenceRoomConfirmList",conferenceRoomConfirmList);
				request.setAttribute("roomname", roomname);
				request.setAttribute("resultCount", resultCount);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageGroupsize", pageGroupSize);
				request.setAttribute("adminID", admin.getAdminID());
				if(conferenceRoomConfirmList==null ||conferenceRoomConfirmList.size()==0){
				 request.setAttribute("errorMsg", "暂无相关信息！");
				}else{
					request.setAttribute("errorMsg", "");
				}
				List listMM=DateUtil.getTimePerMMList();
				
				List listHH=DateUtil.getTimePerHourList();
				
				request.setAttribute("listMM",listMM);
				request.setAttribute("listHH",listHH);
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get pu  getConferenceRoomConfirmList Exception. ", e);
			}
			
			return "/ga_room_approval_information1.jsp?menu_code="+parameterObject.getString("menu_code");
		   
	   }
	 
	 /*导出会议室信息2014-06-17(huili)*/
	 public String getConferenceRoomExcel(HttpServletRequest request,AdminBean admin){	
		 SimpleMap parameterObject = null;
		 List list = new ArrayList();
		   try{
				
				SimpleMap map = null;
				SimpleMap tempMap = null;
				SimpleMap tempMap1 = null;
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());	
				parameterObject.set("cpny_id", admin.getCpnyId());
			
				List conferenceRoomConfirmList = null;
					String qryType = parameterObject.getString("qryType")!=null?parameterObject.getString("qryType"):"0";
					parameterObject.set("qryType", qryType);
					UserConfiguration userConfig;
					userConfig = UserConfiguration.getInstance("/system.properties");
					String[] sgNo = admin.getScreenGrantNo().split(",");
					boolean b = false;
					for (int i = 0; i < sgNo.length; i++) {
					if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
						b = true;
					parameterObject.set("applerId", "");
					parameterObject.set("ADMIN_ID", admin.getAdminID());
					}
				}
					conferenceRoomConfirmList=crServices.getConferenceRoomConfirmList1Excel(parameterObject);
					Iterator iter = conferenceRoomConfirmList.iterator();
					for (; iter.hasNext();) {
						SimpleMap basic = (SimpleMap) iter.next();
						SimpleMap simple = new SimpleMap();
						simple.setString("bookId", basic.getString("CONFERENCENO"));
						simple.setString("bookDate", basic.getString("BOOKDATE"));
						simple.setString("bookTime", basic.getString("BOOKSTARTTIME"));//basic.getString("hour")+ " : " + basic.getString("min")
						simple.setString("endbookDate", basic.getString("ENDDATE"));
						simple.setString("endbookTime", basic.getString("BOOKENDTIME"));//basic.getString("endhour")+ " : " + basic.getString("endmin")
						simple.setString("deptName", basic.getString("DEPTNAME"));
						simple.setString("chineseName", basic.getString("CHINESENAME"));
						simple.setString("peopleClass", basic.getString("PEOPLECLASS"));
						simple.setString("peopleClassup", basic.getString("PEOPLECLASS_UP"));
						simple.setString("bookNum", basic.getString("BOOKNUMBER"));
						
						simple.setString("roomName", basic.getString("ROOMNAME"));
						simple.setString("otherRequest", basic.getString("OTHERREQUEST"));
						simple.setString("purposeoFuse", basic.getString("PURPOSEOFUSE"));
						
						list.add(simple);
					}
			
					SimpleMap columns = new SimpleMap();
					columns.setString("编号", "bookId");
					columns.setString("开始日期", "bookDate");
					columns.setString("开始时间", "bookTime");
					columns.setString("结束日期", "endbookDate");
					columns.setString("结束时间", "endbookTime");
					columns.setString("申请部门", "deptName");
					columns.setString("申请人", "chineseName");
					columns.setString("与会人员", "peopleClass");
					columns.setString("与会领导", "peopleClassup");
					columns.setString("与会人数", "bookNum");

					columns.setString("会议室", "roomName");
					columns.setString("会议主题", "purposeoFuse");
					columns.setString("其他要求", "otherRequest");
					// 定义列类型
					SimpleMap columnType = new SimpleMap();
					columnType.setInt("empId", ReportConstant.CELL_TYPE_TEXT);
					columnType.setInt("createDate", ReportConstant.CELL_TYPE_TEXT);
					columnType.setInt("otDate", ReportConstant.CELL_TYPE_TEXT);
					columnType.setInt("otLength", ReportConstant.CELL_TYPE_TEXT);
			
					// 设置报表参数
					ExcelParameterBean paramBean = new ExcelParameterBean();
					paramBean.setFileName("VIEW_ROOM_DATA.xls");
					paramBean.setSheetname("ROOM_DATA");
					paramBean.setReportData(list);
					paramBean.setColumns(columns);
					paramBean.setColumnTypes(columnType);
					paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
					paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
					// 添加报表图片
					// paramBean.setImageCol(columns.size() - 4);
					// paramBean.setImageRow(recordList.size()+ 5);
					// paramBean.setImageHeight(2);
					// paramBean.setImageWidth(4);
					// paramBean.setImageFile(new File(request.getRealPath("/") +
					// "images/report_logo.png"));
					// 设置页眉
					// paramBean.setHeadContent("资产记录");
					// 设置内嵌表头
					// LSFC个人年假记录表
					paramBean.setInLineHeadContent("会议室信息");
					paramBean.setInLineHeadMergeSize(columns.size());
					// 设置EXCEL表头的显示方向
					paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
					// make attendance record report
					ReportUtil.makeReport(request, paramBean);
				} catch (Exception e) {
			
					Logger.getLogger(getClass()).error(e.toString());
					throw new GlRuntimeException(
							"export pu  getConferenceRoomConfirmList Exception. ", e);
				}
			
			return "/inc/commonReport.jsp";
		   
	   }
	 
	 /*执行确认*/
	 public String oingConfirmConferenceRoom(HttpServletRequest request,AdminBean admin){
		 SimpleMap parameterObject = null;
		   try{
				
				
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());
				String[] affirmno = request.getParameterValues("affirmno");
//				applyno
				for(int i=0;i<affirmno.length;i++){
					String affirmorIdea = request.getParameter("affirmorIdea_"+affirmno[i]);
					parameterObject.set("applyno", affirmno[i]);
					parameterObject.set("affirmorIdea", affirmorIdea);
					crServices.oingConfirmConferenceRoom(parameterObject);
				}
				/*List  toEmailList = crServices.getapplyemail(parameterObject);
				if(toEmailList.size()!=0){
					confirmRoomMail(toEmailList,request.getParameter("bookdate"+applyno),request.getParameter("bookdate"+applyno),request.getParameter("bookstarttime"+applyno),request.getParameter("bookendtime"+applyno),request.getParameter("roomname"+applyno));
				}*/

				
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("oingConfirmConferenceRoom data Exception. ", e);
			}	
			request.setAttribute("currentpage", request.getAttribute("currentpage"));
			this.getConferenceRoomConfirmList(request, admin);
		     return "/pu_conferenceRoom_confirm.jsp?menu_code="+parameterObject.getString("menu_code");
	   }
	 
	 public String saveUpdate(HttpServletRequest request,AdminBean admin){
		 boolean temp =false;
		   try{
				
				SimpleMap parameterObject = null;
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());
				
				int pageSize =10;
				int pageGroupSize =10;
				int currentPage = 0;
				String applyno = request.getParameter("applyno");
				String bookdate = request.getParameter("bookdate"+applyno);
				
				String hour = request.getParameter("hour"+applyno);
				String min = request.getParameter("min"+applyno);
				String bookstarttime = hour;
				String enddate = request.getParameter("enddate"+applyno);
				String Outhour = request.getParameter("Outhour"+applyno);
				String Outmin = request.getParameter("Outmin"+applyno);
				String bookendtime = Outhour;
				String conferenceRoom = request.getParameter("conferenceRoom"+applyno);
				String booknumber = request.getParameter("booknumber"+applyno);
				String[] equipArray = request.getParameterValues("UP"+applyno);
				String equips = "";
				
				if(equipArray != null){
					for (int i = 0 ; i < equipArray.length ; ++i)
					{
						equips = equips + equipArray[i] + "　";
					}
				}
				
				parameterObject.set("bookdate", bookdate);
				parameterObject.set("enddate", enddate);
				parameterObject.set("bookstarttime", bookstarttime);
				parameterObject.set("bookendtime", bookendtime);
				parameterObject.set("conferenceRoom", conferenceRoom);
				parameterObject.set("equips", equips);
				parameterObject.set("booknumber", booknumber);
				
				temp = crServices.saveUpdate(parameterObject);
				parameterObject.set(applyno, applyno);
				List  toEmailList = crServices.getapplyemail(parameterObject);
				if(toEmailList.size()!=0){
					UpdateRoomMail(toEmailList,request.getParameter("bookdate"+applyno),request.getParameter("bookdate"+applyno),request.getParameter("bookstarttime"+applyno),request.getParameter("bookendtime"+applyno),request.getParameter("roomname"+applyno));
				}
				if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request.getParameter("currentPage"));

				int resultCount = crServices.getConferenceRoomConfirmListNumber(parameterObject);
				List conferenceRoomConfirmList=crServices.getConferenceRoomConfirmList(parameterObject,currentPage,pageSize);
				List roomname = eaaServices.roomnameList(parameterObject);
				
				request.setAttribute("conferenceRoomConfirmList",conferenceRoomConfirmList);
				request.setAttribute("roomname", roomname);
				request.setAttribute("resultCount", resultCount);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageGroupsize", pageGroupSize);
				if(conferenceRoomConfirmList==null ||conferenceRoomConfirmList.size()==0){
				 request.setAttribute("errorMsg", "暂无相关信息！");
				}				
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("saveUpdate data Exception. ", e);
			}
			request.setAttribute("currentpage", request.getAttribute("currentpage"));
			this.getConferenceRoomConfirmList(request, admin);
		     return "/pu_conferenceRoom_confirm.jsp";		   
	   }
	 /*执行删除*/
	 public String deleteApply(HttpServletRequest request,AdminBean admin){
		 SimpleMap parameterObject = null;
		 boolean temp =false;
		   try{
				
				
				
//				parameterObject = ObjectBindUtil.getData(request);
//				parameterObject.set("applerId", admin.getAdminID());
//				parameterObject.set("deptId", admin.getDeptID());
//				
//				temp = crServices.deleteApply(parameterObject);
				
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());
				String[] affirmno = request.getParameterValues("affirmno");
				for(int i=0;i<affirmno.length;i++){
					parameterObject.set("applyno", affirmno[i]);
					parameterObject.set("applerId", admin.getAdminID());
					crServices.deleteApply(parameterObject);
				}

				
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("oingConfirmConferenceRoom data Exception. ", e);
			}
			request.setAttribute("currentpage", request.getAttribute("currentpage"));
			this.getConferenceRoomConfirmList(request, admin);
		     return "/pu_conferenceRoom_confirm.jsp?menu_code="+parameterObject.getString("menu_code");
	   }
	 
	 
	 public String viewRoomInfo(HttpServletRequest request,AdminBean admin){	  
		 
		 String flag = StringUtil.checkNull(request.getParameter("flag"),"1"); 
		 SimpleMap parameterObject = null;
		 try{
			    
				
				SimpleMap map = null;
				SimpleMap tempMap = null;
				SimpleMap tempMap1 = null;
				
				parameterObject = ObjectBindUtil.getData(request);
				
				parameterObject.set("deptId", admin.getDeptID());						
				parameterObject.set("starttime", request.getParameter("starttime"));
				parameterObject.set("endtime", request.getParameter("endtime"));
				parameterObject.set("conferenceRoom", request.getParameter("conferenceRoom"));
				parameterObject.set("dept", request.getParameter("dept"));
				parameterObject.set("cpny_id", admin.getCpnyId());
					
				List roomname = eaaServices.roomnameList(parameterObject);
				parameterObject.set("applerId", admin.getAdminID());
				int pageSize =10;
				int pageGroupSize =10;
				int currentPage = 0;
			
				if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
		       //取得数据行数
				int resultCount = crServices.getviewRoomInfoNumber(parameterObject);
				List viewRoomInfoList=crServices.getviewRoomInfoList(parameterObject,currentPage,pageSize);
				
				
				request.setAttribute("viewRoomInfoList",viewRoomInfoList);
				
				request.setAttribute("starttime", request.getParameter("starttime"));
				request.setAttribute("endtime", request.getParameter("endtime"));
				request.setAttribute("conferenceRoom", request.getParameter("conferenceRoom"));
				request.setAttribute("dept", request.getParameter("dept"));
				
				request.setAttribute("roomname", roomname);
				request.setAttribute("resultCount", resultCount);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageGroupsize", pageGroupSize);
				if(viewRoomInfoList==null ||viewRoomInfoList.size()==0){
				 request.setAttribute("errorMsg", "暂无相关信息！");
				}
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get pu  viewRoomInfo Exception. ", e);
			}
			
			if(flag.equals("1")){
				return "/pu_view_room_info.jsp";
			}
			if(flag.equals("2")){
				List viewRoomInfoList=crServices.getviewRoomInfoForReport(parameterObject);
				request.setAttribute("viewRoomInfoList",viewRoomInfoList);
				return "/reports/pu_report/pu_ConferenceRoom_report.jsp";
			}
			return "";
		   
	   }
	
		private void confirmRoomMail(List toEmailList,String applydate,String bookdate,String starttime,String endtime,String roomname) throws Exception {

			SimpleMap parameterObject = new SimpleMap();
			for(int i=0 ;i<toEmailList.size();i++){
			parameterObject=(SimpleMap)toEmailList.get(i);
			}
				SimpleMap inputData = new SimpleMap();
				
				parameterObject.set("applerId",parameterObject.get("EMPID").toString());

				StringBuffer content = new StringBuffer();
							
				content.append(parameterObject.get("CHINESENAME").toString()).append(" 所申请的会议室使用信息已经通过 ")
					   .append("<br><br>").append(" 主题：").append("会议室申请")
					   .append("<br><br>").append(" 申请时间：").append(applydate) ;
				
					content.append("<br><br>").append(" 预订日期：").append(bookdate)
					       .append("<br><br>").append(" 开始时间：").append(starttime) 
					       .append("<br><br>").append(" 结束时间：").append(endtime) ;

					content.append("<br><br>").append(" 申请会议室：").append(roomname) ;

					   
				content.append("<br><br>斗山工程机械(中国)有限公司") ;
				

				// set email title
				inputData.setString("EMAIL_TITLE", parameterObject.get("CHINESENAME").toString() + "," + " 会议室申请 已确认");

				// set email content
				inputData.setString("EMAIL_CONTNT", content.toString());
				if(!StringUtil.checkNull(parameterObject.get("EMAIL")).equals("") && StringUtil.checkNull(parameterObject.get("EMAIL"))!=null){

					inputData.setString("RCVR_EMAIL_ADDR", StringUtil.checkNull(parameterObject.get("EMAIL")));
					mail.sendMail(inputData) ;
				}
				//essServices.insertOtAffirmMail(inputData);
		}
		private void UpdateRoomMail(List toEmailList,String applydate,String bookdate,String starttime,String endtime,String roomname) throws Exception {

			SimpleMap parameterObject = new SimpleMap();
			for(int i=0 ;i<toEmailList.size();i++){
			parameterObject=(SimpleMap)toEmailList.get(i);
			}
				SimpleMap inputData = new SimpleMap();
				
				parameterObject.set("applerId",parameterObject.get("EMPID").toString());

				StringBuffer content = new StringBuffer();
							
				content.append(parameterObject.get("CHINESENAME").toString()).append(" 所申请的会议室使用信息已经修改 ")
					   .append("<br><br>").append(" 主题：").append("会议室申请")
					   .append("<br><br>").append(" 申请时间：").append(applydate) ;
				
					content.append("<br><br>").append(" 预订日期：").append(bookdate)
					       .append("<br><br>").append(" 开始时间：").append(starttime) 
					       .append("<br><br>").append(" 结束时间：").append(endtime) ;

					content.append("<br><br>").append(" 申请会议室：").append(roomname) ;

					   
				content.append("<br><br>斗山工程机械(中国)有限公司") ;
				

				// set email title
				inputData.setString("EMAIL_TITLE", parameterObject.get("CHINESENAME").toString() + "," + " 会议室申请 已修改");

				// set email content
				inputData.setString("EMAIL_CONTNT", content.toString());
				if(!StringUtil.checkNull(parameterObject.get("EMAIL")).equals("") && StringUtil.checkNull(parameterObject.get("EMAIL"))!=null){

					inputData.setString("RCVR_EMAIL_ADDR", StringUtil.checkNull(parameterObject.get("EMAIL")));
					mail.sendMail(inputData) ;
				}
				//essServices.insertOtAffirmMail(inputData);
		}
		public String Delete(HttpServletRequest request,AdminBean admin){	
			SimpleMap parameterObject = null;
			 try{
					parameterObject = ObjectBindUtil.getData(request);
					parameterObject.set("applerId", admin.getAdminID());
					parameterObject.set("deptId", admin.getDeptID());
					
					crServices.deleteApplyroom(parameterObject);

					
				} catch (Exception e) {

					Logger.getLogger(getClass()).error(e.toString());
					throw new GlRuntimeException("oingConfirmConferenceRoom data Exception. ", e);
				}
				request.setAttribute("currentpage", request.getAttribute("currentpage"));
				this.getConferenceRoomConfirmList1(request, admin);
				return "/ga_room_approval_information.jsp?menu_code="+parameterObject.getString("menu_code");
		}

}


