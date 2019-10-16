package com.ait.ga.cmd.visit;
 
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.api.resultApi.DooPushAPI;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.AccommodationBean;
import com.ait.ga.bean.EatryBean;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.bean.PresentBean;
import com.ait.ga.business.ExpressApplyAndAffirmServices;
import com.ait.ga.business.VisiterApplicationsServices;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.FormUtil;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;

public class VisiterApplications implements Command {
	private VisiterApplicationsServices visiterApplicationsServices;
	
	private ExpressApplyAndAffirmServices eaaServices = null;

	private SimpleMap parameterObject;

	private String content = null;
	
	private EssSysparam essSysparam = null;
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://portal.doosan.com" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	private Mail mail = new Mail() ;
	
	private GaAffirm gaAffirm = new GaAffirm();

	public VisiterApplications() {
		visiterApplicationsServices = new VisiterApplicationsServices();
		eaaServices = new ExpressApplyAndAffirmServices();
	}

	public String execute(HttpServletRequest request,// 捕获调用方法抛出的异常减少调用方法异常处理代码
			HttpServletResponse response) throws ServletException, IOException {
		String returnPage = null;
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId());
		content = request.getParameter("content");// 从request中得到想要查看的内容
		if (content != null) {
			if (content.equals("visiterApplicationsView")) {
				returnPage = this.ApplicationsView(request);
			} else if(content.equals("visiterApplicationsSave")){
				returnPage = this.visiterApplicationsSave(request);
			} else if(content.equals("visiterApproval")){
				returnPage = this.visiterApproval(request);
			} else if(content.equals("visiterApprovalOKorNO")){
				returnPage = this.visiterApprovalOKorNO(request);
			} else if(content.equals("visiterApprovalInformation")){
				returnPage = this.visiterApprovalInformation(request);
			} else if(content.equals("deleteVisiterApproval")){
				returnPage = this.deleteVisiterApproval(request);
			} else if(content.equals("viewDetail")){
				returnPage = this.visiterViewDetail(request);				
			}else if(content.equals("uploadVisiterDocuments")){
				this.uploadVisiterDocuments(request);	
				returnPage="/ga_visiter_upload.jsp?Directive=CloseWindow";
			}else if(content.equals("viewPhoto")){
				returnPage=this.viewPhoto(request);				
			}else if(content.equals("deletePhoto")){
				returnPage=this.deletePhoto(request);
			}
		} else {
			Logger.getLogger(getClass()).error("get content parameter fail!");
			returnPage = "/error.jsp";
		}
		Logger.getLogger(getClass()).debug("return Page : " + returnPage);
		return returnPage;
	}

	public void putToolbarInfo(HttpServletRequest request)
			throws GlRuntimeException {

		try {
			SimpleMap map = new SimpleMap();

			// get paramter from request object
			List toolMenuList = null;
			List menuentList = null;
			ToolMenu toolmenu = new ToolMenu();
			MenuBiz menubiz = new MenuBiz();
			String menu_code = request.getParameter("menu_code");
			String operation = request.getParameter("operation");
			AdminBean admin = (AdminBean) (request.getSession()
					.getAttribute("admin"));
			String rodeLevel = admin.getScreenGrantNo() != null ? admin
					.getScreenGrantNo() : "";

			// get process popedom list
			toolMenuList = menubiz.toolMenuList(menu_code, rodeLevel);
			menuentList = menubiz.thirdmenulist(menu_code, rodeLevel);

			int selectMenu = 0;
			int insertMenu = 0;
			int updateMenu = 0;
			int deleteMenu = 0;
			for (int i = 0; i < toolMenuList.size(); i++) {

				toolmenu = (ToolMenu) toolMenuList.get(i);

				if (toolmenu.getSelect() == 1) {

					selectMenu = 1;
				}
				if (toolmenu.getInsertr() == 1) {

					insertMenu = 1;
				}
				if (toolmenu.getUpdate() == 1) {

					updateMenu = 1;
				}
				if (toolmenu.getDelect() == 1) {

					deleteMenu = 1;
				}
			}
			// save insert,update,delete popedom
			map.setInt("selectMenu", selectMenu);
			map.setInt("insertMenu", insertMenu);
			map.setInt("updateMenu", updateMenu);
			map.setInt("deleteMenu", deleteMenu);
			// save MenuEnt object list
			map.set("menuentList", menuentList);
			// save menu code
			map.setString("menu_code", menu_code);
			map.setString("operation", operation);

			request.setAttribute("toolbarInfo", map);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"ArAbstractCommand put toolbar information to request Exception.",
					e);
		}
	}


	private String ApplicationsView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		
		String adminNo = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getAdminNo();
		
		String DeptID = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getDeptID();
		
		String DeptNAME = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getDepartment();
		
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		
		String visiterEatryInfo = "";
		String visiterAccommodationInfo = "";
		if(cpnyId.equals("78000000")){
		}else if(cpnyId.equals("63000000")){
		}else if(cpnyId.equals("61000000")){
			visiterEatryInfo = "VIP餐厅最多容纳14人";
			visiterAccommodationInfo = "请使用与身份证/护照同一姓名";
		}else if(cpnyId.equals("59000000")){			
		}else if(cpnyId.equals("60000000")){	
		}else{
			visiterEatryInfo="";
		}
		request.setAttribute("visiterAccommodationInfo",visiterAccommodationInfo);
		request.setAttribute("visiterEatryInfo",visiterEatryInfo);
		
		request.setAttribute("DeptID",DeptID);
		request.setAttribute("DeptNAME",DeptNAME);
		String applyId=FormUtil.getApplyDocumentid("ga_visiter_apply_id", "ga_visiter_apply", "CG");
		this.checkPhoto(request, applyId);
		SimpleMap map = null;
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String date = format.format(Calendar.getInstance().getTime());
			String subDate = date.substring(2, 8);
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applyId", adminId);
			parameterObject.set("cpny_id", cpnyId);
			
			request.setAttribute("applyId", applyId);			
			List apiLanguage = visiterApplicationsServices.apiLanguage(parameterObject);
			
			List listMM=DateUtil.getTimePerMMList();
			
			List listHH=DateUtil.getTimePerHourList();
			
			request.setAttribute("listMM",listMM);
			request.setAttribute("listHH",listHH);
			
			List roomname = eaaServices.roomnameList(parameterObject);
			request.setAttribute("roomname", roomname);
			
			//得到所有参观者国别
			List allVisiterCountry = visiterApplicationsServices.getAllVisiterCountry(parameterObject);
			request.setAttribute("allVisiterCountry", allVisiterCountry);
			
			request.setAttribute("apiLanguage", apiLanguage);
			
			SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
			String sysDate = formatdate.format(Calendar.getInstance().getTime());
			request.setAttribute("sysDate", sysDate);
			
			List visiterType = visiterApplicationsServices.visiterType(parameterObject);
			
			request.setAttribute("visiterType", visiterType);
			
			List dept = visiterApplicationsServices.getDept(parameterObject);
			request.setAttribute("dept", dept);
			
//			所有设备
			List allDevice = visiterApplicationsServices.allDevice(parameterObject);
			request.setAttribute("allDevice", allDevice);
			
			List allVisiterDistiniction = visiterApplicationsServices.allVisiterDistiniction(parameterObject);
			request.setAttribute("allVisiterDistiniction", allVisiterDistiniction);
			parameterObject.put("cpny_id", ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId());
			//宣传片
			List file = visiterApplicationsServices.file(parameterObject);
			request.setAttribute("visit_pro", file);
			
			//参观项目
			List visit_item = visiterApplicationsServices.visit_item(parameterObject);
			request.setAttribute("visit_pian", visit_item);
			
			String declaration = "";
			if("63000000".equals(cpnyId)){
				declaration="(请提前1天申请参观者信息，告知参观者厂区内禁止拍照，在吸烟区吸烟，如有违反，将追究相关担当责任，谢谢)";
			}
			request.setAttribute("declaration", declaration);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ApplicationsView error ", e);
		}
		this.checkPhoto(request, applyId);
		return "/ga_visiter_apply.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
	private String visiterViewDetail(HttpServletRequest request) {
		this.putToolbarInfo(request);		
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.put("applyno", request.getParameter("applyno"));
			List visiterViewDetail = visiterApplicationsServices.visiterViewDetail(parameterObject);
			List visiterViewDetail1 = visiterApplicationsServices.visiterViewDetail1(parameterObject);
			List visiterViewDetail2 = visiterApplicationsServices.visiterViewDetail2(parameterObject);
			List visiterViewDetail3 = visiterApplicationsServices.visiterViewDetail3(parameterObject);
			
			request.setAttribute("visiterViewDetail", visiterViewDetail);
			request.setAttribute("visiterViewDetail1", visiterViewDetail1);
			request.setAttribute("visiterViewDetail2", visiterViewDetail2);
			request.setAttribute("visiterViewDetail3", visiterViewDetail3);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterViewDetail error ", e);
		}
		List filelist=this.getFileOrPhotoAddress();
		request.setAttribute("filelist", filelist);
		return "/ga_visiter_viewDetail.jsp";
				
	}
	
	private String visiterApplicationsSave(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		
		String adminName = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getChineseName();
		
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = format1.format(Calendar.getInstance().getTime());
		
		int flag = NumberUtil.parseInt(request.getParameter("flag"));
		int flag2 = NumberUtil.parseInt(request.getParameter("flag2"));
		int flag3 = NumberUtil.parseInt(request.getParameter("flag3"));
		
//		GaAffirmList gaAffirmList = new GaAffirmList();
//		List affirmor=gaAffirm.getAffirmor(adminId,"Visiter_Apply",essSysparam.isContainsOwner());	
		
		String[] affirmorId = request.getParameterValues("affirmorId");
		
		SimpleMap map = null;
		SimpleMap map1 = null;
		try {
			/* paging logic */
			if(flag>=0){
				String visiterName = "";
				String visiterDuty = "";
				String visiterCompany = "";
				String Distinction = "";
				String ContactTel = "";
				String GiftName = "";
				String GiftNumber = "";
				String visiterGift = "";
				String memo="";
				String applyId = request.getParameter("applyId"); //编号
				parameterObject = ObjectBindUtil.getData(request);
				/**
				 * 参观者申请信息
				 */
				parameterObject.set("supervisor", adminId);
				parameterObject.put("visitIntroDept", parameterObject.getString("visit_intro_dept"));
				parameterObject.put("visitPhone", parameterObject.getString("visit_phone"));
				parameterObject.put("visitDept", parameterObject.getString("visit_dept"));
				parameterObject.put("visitPlace", parameterObject.getString("visit_place"));
				parameterObject.put("visitCount", parameterObject.getString("visit_count"));
				parameterObject.put("visitNamePosition", parameterObject.getString("visit_name_position"));
				parameterObject.put("carNum", parameterObject.getString("car_num"));
				parameterObject.put("visitObjective", parameterObject.getString("visit_objective"));
				parameterObject.put("expectResult", parameterObject.getString("expect_result"));
				parameterObject.put("visitType", parameterObject.getString("visit_type"));
				parameterObject.put("visitorType", parameterObject.getString("visitor_type"));
				parameterObject.put("visiterComeTime", parameterObject.getString("hour")+":"+parameterObject.getString("min"));
				parameterObject.put("visiterEndTime", parameterObject.getString("Outhour")+":"+parameterObject.getString("Outmin"));
				
				//parameterObject.put("visiterEatryTime", StringUtil.checkNull(parameterObject.getString("visiterEatryHour"))+":"+StringUtil.checkNull(parameterObject.getString("visiterEatryMin")));
				parameterObject.put("visiterVoitureInTime", StringUtil.checkNull(parameterObject.getString("visiterVoitureInHour"))+":"+StringUtil.checkNull(parameterObject.getString("visiterVoitureInMin")));
				parameterObject.put("visiterVoitureOutTime", StringUtil.checkNull(parameterObject.getString("visiterVoitureOutHour"))+":"+StringUtil.checkNull(parameterObject.getString("visiterVoitureOutMin")));
				parameterObject.put("visiterConferenceTime", StringUtil.checkNull(parameterObject.getString("visiterConferenceHour"))+":"+StringUtil.checkNull(parameterObject.getString("visiterConferenceMin")));
				parameterObject.put("visiterTourismTime", StringUtil.checkNull(parameterObject.getString("visiterTourismHour"))+":"+StringUtil.checkNull(parameterObject.getString("visiterTourismMin")));

				parameterObject.put("visiter_conference_language", StringUtil.checkNull(request.getParameter("visiter_conference_language")));
				parameterObject.put("pian_language", StringUtil.checkNull(request.getParameter("pian_language")));				
				/**
				 * 管理部协助内容
				 */
				parameterObject.put("welcome", parameterObject.getString("welcome1"));
				parameterObject.put("receptionOffice", parameterObject.getString("reception_office1"));
				parameterObject.put("tea", parameterObject.getString("tea1"));
				parameterObject.put("coffee", parameterObject.getString("coffee"));
				parameterObject.put("advertisingVideo", parameterObject.getString("advertising_video1"));
				parameterObject.put("defendFacility", parameterObject.getString("defend_facility1"));
				parameterObject.put("interpretFacility", parameterObject.getString("interpret_facility1"));
				parameterObject.put("shoot", parameterObject.getString("shoot1"));
				parameterObject.put("present", parameterObject.getString("present1"));
				parameterObject.put("lunchRepast", parameterObject.getString("lunch_repast1"));
				parameterObject.put("restaurant", parameterObject.getString("restaurant1"));
				parameterObject.put("fruit", parameterObject.getString("fruit1"));
				
				/**
				 * 接待部门信息
				 */
				parameterObject.put("frontdeptID", parameterObject.getString("frontdeptID"));
				parameterObject.put("receptionLead", parameterObject.getString("reception_lead"));
				parameterObject.put("receptionActPhone", parameterObject.getString("reception_act_phone"));
				parameterObject.put("note", parameterObject.getString("note"));
				
				List<PresentBean> gigtList = new ArrayList<PresentBean>();
				for(int i=0 ; i<(flag+1) ; i++){
					PresentBean presentBean = new PresentBean();
					 visiterName = StringUtil.checkNull(request.getParameter("visiterName"+i));//姓名
					 visiterCompany = StringUtil.checkNull(request.getParameter("visiterCompany"+i));//工作单位
					 visiterDuty = StringUtil.checkNull(request.getParameter("visiterDuty"+i));//职务
					 Distinction = StringUtil.checkNull(request.getParameter("Distinction"+i));//区分
					 ContactTel = StringUtil.checkNull(request.getParameter("ContactTel"+i));//联系电话
					 GiftName = StringUtil.checkNull(request.getParameter("GiftName"+i));//礼品名称
					 GiftNumber = StringUtil.checkNull(request.getParameter("GiftNumber"+i));//礼品数量
					 memo = StringUtil.checkNull(request.getParameter("memo"+i));//备注
					 visiterGift = StringUtil.checkNull(request.getParameter("visiterGiftYesOrNo"+i));//备注
					 
					 
					 if(visiterName.equals("") || visiterName==null){
					
						 continue;
					 }
					 presentBean.setVist_name(visiterName);
					 presentBean.setCorpopation(visiterCompany);
					 presentBean.setJobs(visiterDuty);
					 presentBean.setDiffer(Distinction);
					 presentBean.setTelephone(ContactTel);
					 presentBean.setPresent(GiftName);
					 presentBean.setPresent_num(GiftNumber);
					 presentBean.setMemo(memo);
					 presentBean.setGa_vister_apply_id(applyId);
					 presentBean.setPerson_id(adminId);
					 presentBean.setPresent_apply(visiterGift);
					 gigtList.add(presentBean);
					 	
						parameterObject.set("supervisor", adminId);
						
				}
				
				//System.out.println("********************&&&"+request.getParameter("visiterEatryFlag")+request.getParameter("visiterAccommodationFlag"));
				
				List<AccommodationBean> abList = new ArrayList<AccommodationBean>();
				if("true".equals(request.getParameter("visiterAccommodationFlag"))){
					String accommodationName = "";
					String accommodationStandard = "";
					String accommodationInDate = "";
					String accommodationInTime = "";
					String accommodationOutDate = "";
					String accommodationOutTime = "";
					String accommodationMemo = "";

					for(int i=0 ; i<(flag2+1) ; i++){
						AccommodationBean accommodationBean = new AccommodationBean();
						accommodationName = StringUtil.checkNull(request.getParameter("accommodationName"+i));
						accommodationStandard = StringUtil.checkNull(request.getParameter("accommodationStandard"+i));
						accommodationInDate = StringUtil.checkNull(request.getParameter("accommodationInDate"+i));
						accommodationInTime = StringUtil.checkNull(request.getParameter("accommodationInHour"+i))+":"+StringUtil.checkNull(request.getParameter("accommodationInMin"+i));
						accommodationOutTime = StringUtil.checkNull(request.getParameter("accommodationOutHour"+i))+":"+StringUtil.checkNull(request.getParameter("accommodationOutMin"+i));
						accommodationOutDate = StringUtil.checkNull(request.getParameter("accommodationOutDate"+i));
						accommodationMemo = StringUtil.checkNull(request.getParameter("accommodationMemo"+i));

						 if(accommodationName.equals("") || accommodationName==null){
							 continue;
						 }
						 accommodationBean.setAccommodation_name(accommodationName);
						 accommodationBean.setAccommodation_standard(accommodationStandard);
						 accommodationBean.setAccommodation_in_date(accommodationInDate);
						 accommodationBean.setAccommodation_in_time(accommodationInTime);
						 accommodationBean.setAccommodation_out_date(accommodationOutDate);
						 accommodationBean.setAccommodation_out_time(accommodationOutTime);
						 accommodationBean.setAccommodation_memo(accommodationMemo);
						 
						 abList.add(accommodationBean);
						 	
						 parameterObject.set("supervisor", adminId);
							
					}
					
					
				}
				
				
				List<EatryBean> ebList = new ArrayList<EatryBean>();
				if("true".equals(request.getParameter("visiterEatryFlag"))){
					String eatryDistinction = "";
					String eatryName = "";
					String eatryDate = "";
					String eatryTime = "";
					String eatryType = "";
					int eatryNumber ;
					String eatryMemo = "";

					for(int i=0 ; i<(flag3+1) ; i++){
						EatryBean eatryBean = new EatryBean();
						eatryDistinction = StringUtil.checkNull(request.getParameter("visiterEatryDistinction"+i));
						eatryName = StringUtil.checkNull(request.getParameter("visiterEatryName"+i));
						eatryDate = StringUtil.checkNull(request.getParameter("visiterEatryDate"+i));
						eatryTime = StringUtil.checkNull(request.getParameter("visiterEatryHour"+i))+":"+StringUtil.checkNull(request.getParameter("visiterEatryMin"+i));
						eatryType = StringUtil.checkNull(request.getParameter("visiterEatryType"+i));
						eatryNumber = NumberUtil.parseInt(request.getParameter("visiterEatryNumber"+i));
						eatryMemo = StringUtil.checkNull(request.getParameter("visiterEatryMemo"+i));
						
						 if(eatryDistinction.equals("") || eatryDistinction==null){
							 continue;
						 }
						 eatryBean.setVisiter_eatry_distinction(eatryDistinction);
						 eatryBean.setVisiter_eatry_name(eatryName);
						 eatryBean.setVisiter_eatry_date(eatryDate);
						 eatryBean.setVisiter_eatry_time(eatryTime);
						 eatryBean.setVisiter_eatry_type(eatryType);
						 eatryBean.setVisiter_eatry_number(eatryNumber);
						 eatryBean.setVisiter_eatry_memo(eatryMemo);
						 

						 ebList.add(eatryBean);
						 	
						 //parameterObject.set("supervisor", adminId);
							
					}
					
					
				}
				
				
				
				parameterObject.set("cpny_id", cpny_id);
				visiterApplicationsServices.visiterApplySave(parameterObject,affirmorId,gigtList,abList,ebList);	
//				for(int s=0;s<affirmor.size();s++){
//					gaAffirmList=(GaAffirmList)affirmor.get(s);	
//					if(gaAffirmList.getAffirmLevel()==1){
//					parameterObject.set("adminId1", gaAffirmList.getAffirmorId());
					parameterObject.set("adminId1", affirmorId[0]);
					String email=visiterApplicationsServices.getapplyemail(parameterObject);
					if(!email.equals("") && email!=null && essSysparam.isGaSendMail() && essSysparam.isGaSendMail()){
						String visiterComeDate=request.getParameter("visiterTime");
						String visiterComeTime=request.getParameter("hour")+":"+request.getParameter("min");
						String visiterEndTime =request.getParameter("Outhour")+":"+request.getParameter("Outmin");
						String peopleNum=request.getParameter("visit_count");
					this.sendVisiterApplyMail(									
//							gaAffirmList.getAffirmorId(), 
							affirmorId[0],
							adminName, 
							date1, 
							visiterComeDate,
							visiterComeTime,
							visiterEndTime,
							peopleNum,
							email,
							cpny_id
							);
					this.sendVisiterApplyMail(									
//							gaAffirmList.getAffirmorId(), 
							affirmorId[0],
							adminName, 
							date1, 
							visiterComeDate,
							visiterComeTime,
							visiterEndTime,
							peopleNum,
							"qianchao.chen@doosan.com", //20190220"li.sun@doosan.com",
							cpny_id
							);
					}
					}
			
//				}	
//			}
			
			
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String date = format.format(Calendar.getInstance().getTime());
			String subDate = date.substring(2, 8);
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			String applyId=FormUtil.getApplyDocumentid("ga_visiter_apply_id", "ga_visiter_apply", "CG");
				request.setAttribute("applyId", applyId);
			List apiLanguage = visiterApplicationsServices.apiLanguage(parameterObject);
			
			request.setAttribute("apiLanguage", apiLanguage);
			
//			得到所有参观者国别
			List allVisiterCountry = visiterApplicationsServices.getAllVisiterCountry(parameterObject);
			request.setAttribute("allVisiterCountry", allVisiterCountry);
			
			SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			String sysDate = formatdate.format(Calendar.getInstance().getTime());
			request.setAttribute("sysDate", sysDate);
			
			List listMM=DateUtil.getTimePerMMList();
			
			List listHH=DateUtil.getTimePerHourList();
			
			request.setAttribute("listMM",listMM);
			request.setAttribute("listHH",listHH);
			
			List visiterType = visiterApplicationsServices.visiterType(parameterObject);
			
			request.setAttribute("visiterType", visiterType);
			

			List dept = visiterApplicationsServices.getDept(parameterObject);
			request.setAttribute("dept", dept);
			
//			所有设备
			List allDevice = visiterApplicationsServices.allDevice(parameterObject);
			request.setAttribute("allDevice", allDevice);
			
			List allVisiterDistiniction = visiterApplicationsServices.allVisiterDistiniction(parameterObject);
			request.setAttribute("allVisiterDistiniction", allVisiterDistiniction);
			
			request.setAttribute("errorMsg", "申请成功");
		} catch (Exception e) {
			request.setAttribute("errorMsg", "申请失败");
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ApplicationsView error ", e);
		}
		return "/ga_visiter_apply.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
	
	
	private void sendVisiterApplyMail(
			String adminid,
            String applyer, 
            String applyDate,
            String visiterComeDate,
			String visiterComeTime,
			String visiterEndTime,
			String peopleNum,String email,
			String cpny_id) throws Exception {
			
			SimpleMap inputData = new SimpleMap();
			
			inputData.set("申请人：", applyer);
			inputData.set("申请时间：", visiterComeDate);
			inputData.set("来访日期：", visiterComeTime);
			inputData.set("离开时间：", visiterEndTime);
			inputData.set("来访人数：", peopleNum);
			
			mail.gaSendMail(inputData,cpny_id,email,"参观者申请 等待确认") ;
			
	}
	
	private String visiterApproval(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String adminName = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getChineseName();
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		SimpleMap map = null;
		SimpleMap map1 = null;
		SimpleMap map2 = null;
		try {
			
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			
			// bind parameter
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("ADMIN_ID", adminId);
			
			request.setAttribute("adminName", adminName);
			//ADMIN_ID parameterObject.se
			int allvisiterApplyInformationCount = visiterApplicationsServices.allvisiterApplyInformationCount(parameterObject);
			request.setAttribute("allvisiterApplyInformationCount", allvisiterApplyInformationCount+"");
			
			List allvisiterApplyInformation = visiterApplicationsServices.allvisiterApplyInformation(parameterObject, currentPage, pageSize);
			
			int allvisiterApplyInformationSize = allvisiterApplyInformation.size();
			for(int i=0 ; i<allvisiterApplyInformationSize ; i++){
				map = (SimpleMap) allvisiterApplyInformation.get(i);
				String applyId = map.getString("GA_VISITER_APPLY_ID");
				parameterObject.set("applyId", applyId);
				map.set("applyId", applyId);
				List allVisiterApproval = visiterApplicationsServices.allVisiterApproval(parameterObject);
				String affirmNo = "";
				String empid = "";
				String tempapplyId = "";
				for(int j=0 ; j<allVisiterApproval.size() ; j++){
					map1 = (SimpleMap) allVisiterApproval.get(j);
					affirmNo = map1.getString("GA_AFFIRM_NO");
					empid = map1.getString("AFFIRMOR_ID");
					if(empid.equals(adminId.toString())){
						map.set("affirmNo", affirmNo);
					}
					tempapplyId = map1.getString("APPLY_NO");
				}
				request.setAttribute("empid", empid);
				request.setAttribute("tempapplyId", tempapplyId);
				map.set("allVisiterApproval", allVisiterApproval);
			}
			request.setAttribute("startDate", request.getParameter("startDate"));
			request.setAttribute("endDate", request.getParameter("endDate"));
			request.setAttribute("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId());
			request.setAttribute("qryType", request.getParameter("qryType")!=null?request.getParameter("qryType"):0);
			request.setAttribute("deptid", request.getParameter("deptid"));
			request.setAttribute("key", request.getParameter("key"));
			request.setAttribute("allvisiterApplyInformation", allvisiterApplyInformation);
			request.setAttribute("recordCount", NumberUtil
					.parseInt(allvisiterApplyInformationCount));
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterApproval error ", e);
		}
		return "/ga_visiter_Approval.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	private String visiterApprovalOKorNO(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String adminName = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getChineseName();
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		request.setAttribute("adminName", adminName);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(Calendar.getInstance().getTime());
		parameterObject = ObjectBindUtil.getData(request);
		try {
		String applyno[]=request.getParameterValues("applyno");
		
		for (int i=0; i < applyno.length; i++)//重复赋值为空，避免决裁两次，发送两次一样邮件
		{
		    String temp = applyno[i];
		    for (int j = i +1; j < applyno.length; j++)
		    {
		        if (temp.equals(applyno[j]) )
		        	applyno[j]="";
		    }
		}
		
		
		String flag=request.getParameter("flag");
		parameterObject.set("flag", flag);
		parameterObject.set("adminId", adminId);
		for(int i=0;i<applyno.length;i++){
		
			if(!"".equals(applyno[i])){  //申请编号不能为空
				
			String affirmNo=request.getParameter("affirmNo_"+applyno[i]);			
			String applerId=request.getParameter("applerId_"+applyno[i]);
			String applerName=request.getParameter("applerName_"+applyno[i]);
			String affirmorIdea = request.getParameter("affirmorIdea_"+applyno[i])!=null?request.getParameter("affirmorIdea_"+applyno[i]):"";
			String visitCount = request.getParameter("VISIT_COUNT_"+applyno[i]);
			parameterObject.set("affirmNo", affirmNo);		
			parameterObject.set("applerId",applerId);
			parameterObject.set("affirmorIdea",affirmorIdea);
			parameterObject.set("applyno", applyno[i]);
			visiterApplicationsServices.updateAffirmFlag(parameterObject);
			if(request.getParameter("flag").equals("2")){
				visiterApplicationsServices.confirmVisiterApply(parameterObject);
				parameterObject.set("adminId1", request.getParameter("applerId1_"+applyno[i]));
				String toEmail = visiterApplicationsServices.getapplyemail(parameterObject);
				if(!toEmail.equals("") && toEmail!=null && essSysparam.isGaSendMail()){				
				this.sendVIsiterAffirmMail(
						"参观者申请已被否决",
						applerName,						 
						date, 
						request.getParameter("VISITER_DATE_"+applyno[i]), 
						request.getParameter("VISITER_COME_TIME_"+applyno[i]), 
						request.getParameter("VISITER_END_TIME_"+applyno[i]),
						visitCount, 
						toEmail,
						admin.getCpnyId()
						);
				}
			}else if(request.getParameter("flag").equals("1")){			
				String MAX_AFFIRM_FLAG=request.getParameter("MAX_AFFIRM_FLAG_"+applyno[i]);
				parameterObject.set("affrimlevel", request.getParameter("AFFIRM_LEVEL_"+applyno[i]));			
				if(MAX_AFFIRM_FLAG.equals("0")){
					String toEmail = visiterApplicationsServices.getupaffrimemail(parameterObject);
					if(!toEmail.equals("") && toEmail!=null && essSysparam.isGaSendMail()){
						this.sendVIsiterAffirmMail(
								"参观者申请 请进行确认",
								applerName,						 
								date, 
								request.getParameter("VISITER_DATE_"+applyno[i]), 
								request.getParameter("VISITER_COME_TIME_"+applyno[i]), 
								request.getParameter("VISITER_END_TIME_"+applyno[i]),
								visitCount,
								toEmail,
								admin.getCpnyId()
								);
					}
					//推送
					DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_VISITER, applyno[i],Integer.parseInt(StringUtil.checkNull(parameterObject.get("affrimlevel"))) + 1);
				}else{
					visiterApplicationsServices.confirmVisiterApply(parameterObject);
					parameterObject.set("adminId1", request.getParameter("applerId1_"+applyno[i]));
					String toEmail = visiterApplicationsServices.getapplyemail(parameterObject);
					if(!toEmail.equals("") && toEmail!=null && essSysparam.isGaSendMail()){
						this.sendVIsiterAffirmMail(
								"参观者申请 已经通过决裁",
								applerName,						 
								date, 
								request.getParameter("VISITER_DATE_"+applyno[i]), 
								request.getParameter("VISITER_COME_TIME_"+applyno[i]), 
								request.getParameter("VISITER_END_TIME_"+applyno[i]),
								visitCount, 
								toEmail,
								admin.getCpnyId()
								);
						this.sendVIsiterAffirmMail(//参观者申请决裁完毕后除了发邮件给申请者还发送给褚衍桥\孔一琳\郑秀荣、刘孟才
								"参观者申请信息",
								applerName,						 
								date, 
								request.getParameter("VISITER_DATE_"+applyno[i]), 
								request.getParameter("VISITER_COME_TIME_"+applyno[i]), 
								request.getParameter("VISITER_END_TIME_"+applyno[i]),
								visitCount, 
								"qianchao.chen@doosan.com",//"li.sun@doosan.com",//yanqiao.chu@doosan.com/
								admin.getCpnyId()
								);
//						this.sendVIsiterAffirmMail(
//								"参观者申请信息",
//								applerName,						 
//								date, 
//								request.getParameter("VISITER_DATE_"+applyno[i]), 
//								request.getParameter("VISITER_COME_TIME_"+applyno[i]), 
//								request.getParameter("VISITER_END_TIME_"+applyno[i]),
//								request.getParameter("VISITER_PEOPLE_NUM_"+applyno[i]), 
//								"yilin.kong@doosan.com",//yilin.kong@doosan.com
//								admin.getCpnyId()
//								);
//						this.sendVIsiterAffirmMail(
//								"参观者申请信息",
//								applerName,						 
//								date, 
//								request.getParameter("VISITER_DATE_"+applyno[i]), 
//								request.getParameter("VISITER_COME_TIME_"+applyno[i]), 
//								request.getParameter("VISITER_END_TIME_"+applyno[i]),
//								request.getParameter("VISITER_PEOPLE_NUM_"+applyno[i]), 
//								"xiurong.zheng@doosan.com",//xiurong.zheng@doosan.com/
//								admin.getCpnyId()
//								);
//						this.sendVIsiterAffirmMail(
//								"参观者申请信息",
//								applerName,						 
//								date, 
//								request.getParameter("VISITER_DATE_"+applyno[i]), 
//								request.getParameter("VISITER_COME_TIME_"+applyno[i]), 
//								request.getParameter("VISITER_END_TIME_"+applyno[i]),
//								request.getParameter("VISITER_PEOPLE_NUM_"+applyno[i]), 
//								"mengcai.liu@doosan.com",//mengcai.liu@doosan.com
//								admin.getCpnyId()
//								);
					}
					}
				}
			}
		}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterApproval error ", e);
		}
		return this.visiterApproval(request);
	}
	private void sendVIsiterAffirmMail(
			String title,
			String applerId,
            String applyDate,
            String visiterComeDate,
			String visiterComeTime,
			String visiterEndTime,
			String visitCount,
			String email,
			String cpny_id) throws Exception {

			SimpleMap inputData = new SimpleMap();
			
			inputData.set("申请人：", applerId);
			inputData.set("来访日期：", visiterComeDate);
			inputData.set("到达时间：", visiterComeTime);
			inputData.set("离开时间：", visiterEndTime);
			inputData.set("来访人数：", visitCount);
			
			mail.gaSendMail(inputData,cpny_id,email,title) ;

	}
	
	private String visiterApprovalInformation(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpnyIdd = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : cpnyIdd;
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		request.setAttribute("adminId", adminId);
		SimpleMap map = null;
		SimpleMap map1 = null;
		try {
			
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpnyId", cpnyId);
			String qryType = (String) (parameterObject.get("qryType")!=null?parameterObject.get("qryType"):"0");
			parameterObject.set("qryType",qryType);
			
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
			if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
				b = true;
			parameterObject.set("supervisor", "");			
			
			parameterObject.set("ADMIN_ID", admin.getAdminID());
			}
			
		}
			//参观者担当可以查看所有
			for (int i = 0; i < sgNo.length; i++) {
				if ((",11,").indexOf(","+sgNo[i].trim()+",") > -1 ){
					b = true;
				parameterObject.set("supervisor", "");			
				
				parameterObject.set("ADMIN_ID", "");
				}
				
			}
			int allvisiterApplyInformationCount = visiterApplicationsServices.allvisiterAffrimInfoCount(parameterObject);
			
			List allvisiterApplyInformation = visiterApplicationsServices.allvisiterAffrimInfoList(parameterObject, currentPage, pageSize);
			
			int allvisiterApplyInformationSize = allvisiterApplyInformation.size();
			for(int i=0 ; i<allvisiterApplyInformationSize ; i++){
				map = (SimpleMap) allvisiterApplyInformation.get(i);
				String applyId = map.getString("GA_VISITER_APPLY_ID");
				request.setAttribute("applyId", applyId);
				parameterObject.set("applyId", applyId);
				List allVisiterApproval = visiterApplicationsServices.allVisiterApproval(parameterObject);
				//boolean isfalg = true;
				for(int j=0 ; j<allVisiterApproval.size() ; j++){
					map1 = (SimpleMap) allVisiterApproval.get(j);
					
					String applyNo= map1.getString("APPLY_NO");
					String AFFIRMORIDEA= map1.getString("AFFIRMORIDEA");
					map.set("applyNo", applyNo);
					map.set("AFFIRMORIDEA", AFFIRMORIDEA);
					
					String affirmFlag = map1.getString("AFFIRM_FLAG");
					//String affirmor_id = map1.getString("AFFIRMOR_ID");
					
					if(affirmFlag.equals("0")){
						map.set("AFFIRM_FLAG", "0");
						map.set("allVisiterApproval", allVisiterApproval);
//						if(isfalg){
//							map.set("isfalg", "0");
//						}
//						isfalg = false;

					}else{
//						isfalg = false;
						map.set("AFFIRM_FLAG", "1");
//						map.set("isfalg", "1");
						map.set("allVisiterApproval", allVisiterApproval);
						break;
					}
				}
				
			}
			request.setAttribute("startDate", request.getParameter("startDate"));
			request.setAttribute("endDate", request.getParameter("endDate"));
			request.setAttribute("cpnyId", cpnyId);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptid", request.getParameter("deptid"));
			request.setAttribute("key", request.getParameter("key"));
			request.setAttribute("allvisiterApplyInformation", allvisiterApplyInformation);
			request.setAttribute("adminID", admin.getAdminID());
			
			request.setAttribute("recordCount", NumberUtil
					.parseInt(allvisiterApplyInformationCount));

			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterApproval error ", e);
		}
		return "/ga_visiter_approval_information.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
	private String deleteVisiterApproval(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		
		String visiterNo = request.getParameter("ga_visiter_apply_id");
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("visiterNo", visiterNo);
			
			visiterApplicationsServices.updateApproval(parameterObject);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterApproval error ", e);
		}
		request.setAttribute("url","/gaControlServlet?operation=visiterApplications&content=visiterApprovalInformation&menu_code=" + parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
	 /*上传图片*/
	 public void uploadVisiterDocuments(HttpServletRequest request){
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		String sql="insert into FILE_OR_PHOTO_PATH(DOCUMENTNO,CHINESENAME,PATHADDRESS,filename) values (?,?,?,?)";
		DiskFileUpload fu = new DiskFileUpload();		
		String documentno=	request.getParameter("documentno");		 
			 List fileItems = null;
			 try {
				 fileItems = fu.parseRequest(request);				
				Iterator iter = null;
				if(fileItems!=null){
					iter = fileItems.iterator();
					 while (iter.hasNext()) {						
						    FileItem item = (FileItem)iter.next();
						    if (!item.isFormField() && !item.getName().equals("")){
						    	ServletContext application = request.getSession().getServletContext();
						    	String filepath = "/upload/visiter"; 						    	
						    	String path = application.getRealPath(filepath);
						    	File file = null;						    	
						    	int start = item.getName().lastIndexOf("\\"); 
						    	String name =item.getName().substring(start+1);						    	
						    	file = new File(path); 
						    	/**如果文件已经存在，则删除原有文件，上传新文件覆盖原有文件。*/ 
						    	/*if(file.exists()){ 
						    	file.delete(); 
						    	}else{ 
						    	file = new File("/upload/safe/",documentno+item.getName()); 
						    	*//**如果文件已经存在，则删除原有文件，上传新文件覆盖原有文件。*//* 						    	
						    	} */
						    	int seqid=this.getSequence("FILE_OR_PHOTO_PATH_SEQ");
						    	File objectfile= new File(path+"\\"+documentno+seqid+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
						    	if(!file.exists()){
						    		file.mkdir();
					       		}
						    	ps = conn.prepareStatement(sql);
						    	ps.setString(1,documentno);
						    	ps.setString(2,name.replaceAll("\\s*", ""));
						    	ps.setString(3,"../upload/visiter/"+documentno+seqid+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
						    	ps.setString(4,documentno+seqid+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
						    	ps.executeUpdate();
						    	
								item.write(objectfile);
								
						    }
					 }
				}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					SqlUtil.close(ps, conn);
				} 
			
		
	}
	 /*查看 图片*/
	 public String  viewPhoto(HttpServletRequest request){
		 Connection conn = ConnBean.getConn();
		 PreparedStatement ps = null;		 
		 String sql="select * from FILE_OR_PHOTO_PATH t where t.documentno=?";
		 List  result  = new ArrayList();
		 ResultSet rs=null;
		 SimpleMap sm = null;
		    try {
			ps = conn.prepareStatement(sql);			
	    	ps.setString(1,request.getParameter("documentno"));
	    	rs=ps.executeQuery();
	    	while(rs.next()){
	    		sm= new SimpleMap();
	    		sm.set("viewfilename", rs.getString("chinesename"));
	    		sm.set("filename", rs.getString("filename"));
	    		sm.set("fileaddress", rs.getString("pathaddress"));
	    		sm.set("documentno", rs.getString("documentno"));
	    		result.add(sm);
	    	}
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	      }finally {
				SqlUtil.close(rs, ps, conn);
			}
		 request.setAttribute("resultsize", result.size());
		 request.setAttribute("result", result);
		 String flag=(String) request.getParameter("flag");
		if(flag!=null && flag.equals("view")){
		return "/ga_view_photo_onlyview.jsp"; 
		}else{		 	
		 return "/ga_view_visiterphoto.jsp";
	    }
	}
	 /*删除 图片*/
	 public String  deletePhoto(HttpServletRequest request){
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		String sql = "delete  from FILE_OR_PHOTO_PATH t where t.documentno=? and t.filename=?";
		File myDir = null;
		String fillname = request.getParameter("filename");
		try {
			ServletContext application = request.getSession()
					.getServletContext();
			String filepath = "/upload/visiter";
			String path = application.getRealPath(filepath);
			myDir = new File(path + "\\" + fillname);
			myDir.delete();
			ps = conn.prepareStatement(sql);
			ps.setString(1, request.getParameter("documentno"));
			ps.setString(2, request.getParameter("filename"));
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(ps, conn);
		}
		this.viewPhoto(request);
		return "/ga_view_visiterphoto.jsp?menu_code=ga0310";
	}
	 /* 删除已经存在的文件 */
	 public void  checkPhoto(HttpServletRequest request,String documentno){
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		String sql = "delete  from FILE_OR_PHOTO_PATH t where t.documentno=? ";
		 File myDir=null;
		 File[] contents=null;
		 Iterator currentFileView=null;
		 File currentFile = null;
		ServletContext application = request.getSession().getServletContext();
	    String filepath = "/upload/visiter"; 	
	    try {
	    String path = application.getRealPath(filepath);		
		Vector vectorList=new Vector();				
		 myDir=new File(path);
		 contents =myDir.listFiles();
		for(int i=0;i<contents.length;i++){
             vectorList.add(contents[i]);             
        }
		 currentFileView=vectorList.iterator();
		 while(currentFileView.hasNext()){
             currentFile=(File)currentFileView.next();
             if(currentFile.getName().startsWith(documentno)){//如果文件名是以这个编号开始的，那么就删除它，为了防止重复
             File f = new File(path+"\\"+currentFile.getName());            
             f.delete();   
             }
             }
		    ps = conn.prepareStatement(sql);
			ps.setString(1,documentno);			
			ps.executeUpdate();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(ps, conn);
		}
       	 
	}
	 
/*得到序列号*/
	private int getSequence(String seqName) {
			int result = 0;
			Connection conn = ConnBean.getConn();
			Statement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT " + seqName + ".NEXTVAL FROM DUAL";
			Logger.getLogger(getClass()).debug(sql);
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next())
					result = rs.getInt(1);
			} catch (Exception e) {
				Logger.getLogger(getClass()).debug(e.toString());
				throw new GlRuntimeException("取得序列号失败", e);
			} finally {
				SqlUtil.close(rs, stmt, conn);
			}
			return result;
}
	public List getFileOrPhotoAddress() {
		List list =new ArrayList();
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select distinct t.documentno from FILE_OR_PHOTO_PATH t ";
		Logger.getLogger(getClass()).debug(sql);
		SimpleMap sm =null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				 sm = new SimpleMap();			    
    		     sm.setString("DOCUMENTNO", rs.getString("documentno"));
				list.add(sm);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得文件失败！", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return list;
  }
	
}