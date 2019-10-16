package com.ait.ga.cmd.access;
 
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

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.bean.PresentBean;
import com.ait.ga.business.VisiterApplicationsServices;
import com.ait.ga.cmd.visit.GaAffirm;
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

public class AccessApplications implements Command {
	private VisiterApplicationsServices visiterApplicationsServices;

	private SimpleMap parameterObject;

	private String content = null;
	
	private EssSysparam essSysparam = null;
	private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	private Mail mail = new Mail() ;
	
	private GaAffirm gaAffirm = new GaAffirm();

	public AccessApplications() {
		visiterApplicationsServices = new VisiterApplicationsServices();
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
			if (content.equals("accessApplicationsView")) {
				returnPage = this.ApplicationsView(request);
			} else if(content.equals("accessApplicationsSave")){
				returnPage = this.accessApplicationsSave(request);
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
		
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		
		request.setAttribute("DeptID",DeptID);
		request.setAttribute("DeptNAME",DeptNAME);
		String applyId=FormUtil.getApplyDocumentidNew("ga_access_apply_id", "ga_access_apply", "FW");
		//this.checkPhoto(request, applyId);
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
			
			request.setAttribute("applyId", applyId);			
			List apiLanguage = visiterApplicationsServices.apiLanguage(parameterObject);
			
			List listMM=DateUtil.getTimePerMMList();
			
			List listHH=DateUtil.getTimePerHourList();
			
			request.setAttribute("listMM",listMM);
			request.setAttribute("listHH",listHH);
			
			String tiShi = "";
			
			if("63000000".equals(cpnyId)){
				tiShi="1.来访人员需要由接待人全程陪同<br>2.厂区内未经申请严禁拍照<br>3.物品搬出时，必须开具搬出证<br>* 以上如有违反，将按照公司相关规定处理";
			}
			
			request.setAttribute("tiShi", tiShi);
			request.setAttribute("cpnyId", cpnyId);
			
			//得到所有参观者国别
			//List allVisiterCountry = visiterApplicationsServices.getAllVisiterCountry(parameterObject);
			//request.setAttribute("allVisiterCountry", allVisiterCountry);
			
			request.setAttribute("apiLanguage", apiLanguage);
			
			SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
			String sysDate = formatdate.format(Calendar.getInstance().getTime());
			request.setAttribute("sysDate", sysDate);
			
			List visiterType = visiterApplicationsServices.visiterType(parameterObject);
			
			request.setAttribute("visiterType", visiterType);
			
			List dept = visiterApplicationsServices.getDept(parameterObject);
			request.setAttribute("dept", dept);
			
//			所有设备
			//List allDevice = visiterApplicationsServices.allDevice(parameterObject);
			//request.setAttribute("allDevice", allDevice);
			
			List allVisiterDistiniction = visiterApplicationsServices.allVisiterDistiniction(parameterObject);
			request.setAttribute("allVisiterDistiniction", allVisiterDistiniction);
			parameterObject.put("cpny_id", ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId());
			//宣传片
			//List file = visiterApplicationsServices.file(parameterObject);
			//request.setAttribute("visit_pro", file);
			
			//参观项目
			//List visit_item = visiterApplicationsServices.visit_item(parameterObject);
			//request.setAttribute("visit_pian", visit_item);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ApplicationsView error ", e);
		}
		//this.checkPhoto(request, applyId);
		return "/ga_access_apply.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
	
	
	private String accessApplicationsSave(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		String cpnyId = request.getParameter("cpnyId");
		String adminName = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getChineseName();
		
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = format1.format(Calendar.getInstance().getTime());
		
		int flag = NumberUtil.parseInt(request.getParameter("flag"));
		//GaAffirmList gaAffirmList = new GaAffirmList();
		//List affirmor=gaAffirm.getAffirmor(adminId,"Visiter_Apply",essSysparam.isContainsOwner());	
		SimpleMap map = null;
		SimpleMap map1 = null;
		String msg = "";
		String prompt = "";
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
				String memo="";
				///String applyId = request.getParameter("applyId"); //编号
				String applyId=FormUtil.getApplyDocumentidNew("ga_access_apply_id", "ga_access_apply", "FW");
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.put("visiterComeTime", parameterObject.getString("hour")+":"+parameterObject.getString("min"));
				parameterObject.put("visiterEndTime", parameterObject.getString("Outhour")+":"+parameterObject.getString("Outmin"));
				parameterObject.put("applyId", applyId);
				
				parameterObject.put("accessApplyId", applyId);
				String accessApplyId = (String)visiterApplicationsServices.getAccessApplyId(parameterObject);
				
				if("".equals(accessApplyId) || accessApplyId == null){
                    
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
						 //presentBean.setGa_vister_apply_id(applyId);
						 presentBean.setPerson_id(adminId);
						 gigtList.add(presentBean);
						 	
							parameterObject.set("supervisor", adminId);
							
					}
					parameterObject.set("cpny_id", cpny_id);
					parameterObject.set("cpnyId", cpnyId);
					visiterApplicationsServices.accessApplySave(parameterObject,gigtList);	
					prompt = "为安全起见，请在预约时间"+parameterObject.getString("visiterComeTime")+"到正门迎接访客，并在会面结束后，将访客欢送至正门。\\n如不遵守，将可能导致安全Issue。";
					msg = "申请成功";	
				}else{
					msg = "对不起,申请编号重复,请重新申请";
				}
			}
				
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String date = format.format(Calendar.getInstance().getTime());
			String subDate = date.substring(2, 8);
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			String applyId=FormUtil.getApplyDocumentid("ga_access_apply_id", "ga_access_apply", "FW");
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
//			List allDevice = visiterApplicationsServices.allDevice(parameterObject);
//			request.setAttribute("allDevice", allDevice);
			
			List allVisiterDistiniction = visiterApplicationsServices.allVisiterDistiniction(parameterObject);
			request.setAttribute("allVisiterDistiniction", allVisiterDistiniction);
			
			request.setAttribute("errorMsg", msg);
			request.setAttribute("prompt", prompt);
		} catch (Exception e) {
			request.setAttribute("errorMsg", "申请失败");
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ApplicationsView error ", e);
		}
		return "/ga_access_apply.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
}