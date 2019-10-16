package com.ait.ga.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.ExpressApplyAndAffirmServices;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.DateUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.FormUtil;
import com.ait.web.Command;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-19
 * 
 */
public class ConferenceRoomCommand implements Command {
	
	private ExpressApplyAndAffirmServices eaaServices = null;
	
	private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	private Mail mail = new Mail() ;
	
	private EssSysparam essSysparam = null;
	
	AdminBean admin = new AdminBean();

	public ConferenceRoomCommand() {
		eaaServices = new ExpressApplyAndAffirmServices();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
		String content = request.getParameter("content");
		String returnPage = null;
		if(!content.equals("") && content.equals("conferenceRoomApplyPage")){
			returnPage= this.conferenceRoomApplyPage(request, admin);
		}else if(!content.equals("") && content.equals("andconferenceRoomApply")){
			returnPage= this.addconferenceRoomApply(request, admin);
		}else if(!content.equals("") && content.equals("andconferenceRoomAffirm")){
			returnPage= this.getconferenceRoomAffirm(request, admin);
		}else if(!content.equals("") && content.equals("ongingAffirm")){
			returnPage= this.confirmConferenceRoomAffirm(request, admin);
		}else if(!content.equals("") && content.equals("conferenceRoomAffirmInfo")){
			returnPage= this.getConfirmConferenceRoomInfo(request, admin);
		}else if(!content.equals("") && content.equals("deleteApply")){
			returnPage= this.deleteConfirmConferenceRoomApply(request, admin);
		}else{
			return "error.jsp";
		}
		return returnPage;
	}
	
	public String conferenceRoomApplyPage(HttpServletRequest request,AdminBean admin){

		String visiterNo = request.getParameter("visiterNo");
		String temp = request.getParameter("temp");
		String companyId = request.getParameter("companyId") != null ? request.getParameter("companyId") : admin.getCpnyId();
		request.setAttribute("visiterNo", visiterNo);
		request.setAttribute("temp", temp);
	try{
		
		SimpleMap parameterObject = null;
		
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("cpny_id", companyId);
		
		List roomname = eaaServices.roomnameList(parameterObject);
		
		parameterObject.set("applerId", admin.getAdminID());
		List meetroomList = eaaServices.meetroomList(parameterObject);
		request.setAttribute("roomname", roomname);
		request.setAttribute("meetroomList", meetroomList);
		request.setAttribute("companyId", companyId);
		request.setAttribute("cpnyId", admin.getCpnyId());
		String declaration = "";
		if("63000000".equals(admin.getCpnyId())){
			declaration="(请提前一天申请会议室，会议临时取消时，请及时删除，以便其他部门使用)";
		}
		request.setAttribute("declaration", declaration);
	
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("addconferenceRoomApply happens Exception. ", e);
	}

       return "/ga_conferenceRoom_apply.jsp";
	
  }
	
	/*增加会议室申请信息,同时添加决裁信息*/
	public String addconferenceRoomApply(HttpServletRequest request,AdminBean admin){
		boolean temp=false;
		String documentno="";
		documentno = FormUtil.getApplyDocumentid("CONFERENCENO", "PU_CONFERENCEROOM_APPLY", "HY");			
	try{
		
		SimpleMap parameterObject = null;
		
		String cycleType = StringUtil.checkNull(request.getParameter("CYCLE_TYPE"));
		String cycleTypeSize = StringUtil.checkNull(request.getParameter("CYCLE_TYPE_SIZE"));
		
		String[] equipArray = request.getParameterValues("UP");
		String equips = "";
		if(equipArray != null){
			for (int i = 0 ; i < equipArray.length ; ++i)
			{
				equips = equips + equipArray[i] + "　";
			}
		}
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("applerId", admin.getAdminID());
		parameterObject.set("deptId", admin.getDeptID());		
		parameterObject.set("equips",equips);
		parameterObject.set("documentno",documentno);
		parameterObject.set("cpny_id", admin.getCpnyId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date  Formatstartdate = new Date();
		Date  Formatenddate = new Date();
		try {
			Formatstartdate=sdf.parse(request.getParameter("bookdate"));
			Formatenddate=sdf.parse(request.getParameter("enddate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double vra=DateUtil.DateDiff(Formatstartdate,Formatenddate,"DAY");
		
		
		if("".equals(cycleType) || "".equals(cycleTypeSize)){
			
			for(int i=0;i<(vra+1);i++){
				Calendar today = Calendar.getInstance();
				today.setTime(Formatstartdate);
				today.add(today.DATE, i);	
				parameterObject.set("Formatdate",sdf.format(today.getTime()));
				temp=eaaServices.addconferenceRoomApply(parameterObject);
			}
			
		}else{
			
			for(int i=0;i<(vra+1);i++){
				
				Calendar today = Calendar.getInstance();
				today.setTime(Formatstartdate);
				today.add(today.DATE, i);
				
				String dayOfWeek = String.valueOf(today.get(Calendar.DAY_OF_WEEK)-1);
				String dayOfMonth = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
				//System.out.println(sdf.format(today.getTime())+"测试："+today.get(Calendar.DAY_OF_MONTH)+"#####################"+today.get(Calendar.DAY_OF_WEEK));
				
				
				if(("CycleType01".equals(cycleType)&&cycleTypeSize.equals(dayOfWeek)) 
						|| ("CycleType02".equals(cycleType)&&cycleTypeSize.equals(dayOfMonth)) ){
					
					documentno = FormUtil.getApplyDocumentid("CONFERENCENO", "PU_CONFERENCEROOM_APPLY", "HY");
					parameterObject.set("Formatdate",sdf.format(today.getTime()));
					parameterObject.set("documentno",documentno);
					temp=eaaServices.addconferenceRoomApply(parameterObject);

				    		
				}

			}
			
		}
	
			
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String  date1 = format1.format(Calendar.getInstance().getTime());
		
		List roomname = eaaServices.roomnameList(parameterObject);
		request.setAttribute("roomname", roomname);
		
		String visiterTemp = request.getParameter("visitertemp");
		String visiterNo = request.getParameter("visiterno");
		 request.setAttribute("temp", visiterTemp);
		 request.setAttribute("visiterNo", visiterNo);
		 List meetroomList = eaaServices.meetroomList(parameterObject);
		 request.setAttribute("meetroomList", meetroomList);	
		
		 if(temp){
	    	   request.setAttribute("errorMsg", "已申请成功！注意：如申请连续的时间，系统会自动将申请的时间更改为：列：今天13:00~15:00,明天13:00~15:00，不会包括其他时间");
	    		   parameterObject.set("id", request.getParameter("conferenceRoom"));
	    		   String roomname1 = eaaServices.roomnameList1(parameterObject);
	    		   String roomConfirmorid = eaaServices.roomConfirmorid(parameterObject);
	    	   sendRoomMail(admin.getAdminID(),admin.getChineseName(),date1,request.getParameter("bookdate"),request.getParameter("starttime"),request.getParameter("endtime"),roomname1,request.getParameter("enddate"),roomConfirmorid,admin.getCpnyId(),request.getParameter("Purposeofuse"));
	    	   }else{
	    	   request.setAttribute("errorMsg", "申请失败！");
	       }
			return "/ga_conferenceRoom_apply.jsp";
	} catch (Exception e) {
		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("addconferenceRoomApply happens Exception. ", e);
	}
      
	
  }
	/*得到序列号*/
	public int getSequence(String seqName) {
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
  
	/*根据登陆者得到决裁的信息*/
	public String getconferenceRoomAffirm(HttpServletRequest request,AdminBean admin){
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
		int resultCount = eaaServices.getconferenceRoomAffirmListNumber(parameterObject);
		List ExpressAffirmList=eaaServices.getconferenceRoomAffirmList(parameterObject,currentPage,pageSize);
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
	    request.setAttribute("expressAffirmList", ExpressAffirmList);
	    if(ExpressAffirmList==null || ExpressAffirmList.size()==0){
	    	   request.setAttribute("errorMsg", "没有您相关的决裁信息！");	
	    }
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("getconferenceRoomAffirm happens Exception. ", e);
	}     
		return "/ga_conferenceRoom_affirm.jsp";
	
  }
	
	/*执行决裁*/
	public String confirmConferenceRoomAffirm(HttpServletRequest request,AdminBean admin){
	
	try{
		
		SimpleMap parameterObject = null;		
		parameterObject = ObjectBindUtil.getData(request);
		eaaServices.confirmConferenceRoomAffirm(parameterObject);
	
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("confirmConferenceRoomAffirm happens Exception. ", e);
	}
	   this.getconferenceRoomAffirm(request, admin);
		return "/ga_conferenceRoom_affirm.jsp";	
  }
	/*根据登陆者得到决裁情况的信息*/
	public String getConfirmConferenceRoomInfo(HttpServletRequest request,AdminBean admin){
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
		int resultCount = eaaServices.getConfirmConferenceRoomInfoListNumber(parameterObject);
        List ExpressAffirmList=eaaServices.getConfirmConferenceRoomInfoList(parameterObject,currentPage,pageSize);//得到决裁情况信息
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
	    request.setAttribute("expressAffirmList", ExpressAffirmList);
	    if(ExpressAffirmList==null || ExpressAffirmList.size()==0){
	    	   request.setAttribute("errorMsg", "没有您相关的决裁信息！");	
	    }
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("getConfirmConferenceRoomInfo happens Exception. ", e);
	}     
		return "/ga_conferenceRoom_affirmInfo.jsp";
	
   }
	
	/*执行删除*/
	public String deleteConfirmConferenceRoomApply(HttpServletRequest request,AdminBean admin){
	
	try{
		
		SimpleMap parameterObject = null;		
		parameterObject = ObjectBindUtil.getData(request);
		eaaServices.deleteConfirmConferenceRoomApply(parameterObject);
	
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("deleteConfirmConferenceRoomApply happens Exception. ", e);
	}
	   this.getConfirmConferenceRoomInfo(request, admin);
		return "/ga_conferenceRoom_affirmInfo.jsp";
	
  }
	/*得到所有正在使用中的会议室情况*/
	public List getAllUsingConfirmConferenceRoom(HttpServletRequest request){
		List list=null;
	
	try{
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		SimpleMap parameterObject =  new SimpleMap();	
		parameterObject.set("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId());

		list=eaaServices.getAllUsingConfirmConferenceRoom(parameterObject);
	
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("getAllUsingConfirmConferenceRoom happens Exception. ", e);
	}

	return list;
	
  }
	
	private void sendRoomMail(String adminid,String applyer,String applydate,String bookdate,String starttime,String endtime,String roomname,String enddate,String roomConfirmorid,String cpny_id,String purposeofuse) throws Exception {

		SimpleMap parameterObject = new SimpleMap();
		
			SimpleMap inputData = new SimpleMap();
			
			parameterObject.set("applerId",adminid);
			parameterObject.set("cpny_id",cpny_id);
//			1	22	78000000
//			2	222	63000000
//			3	322	60000000
//			4	122	59000000
//			5	422	61000000
			if("78000000".equals(cpny_id)){
				parameterObject.set("eaa", "22");
			}
			if("63000000".equals(cpny_id)){
				parameterObject.set("eaa", "222");
				//根据pu_room 管理担当发送邮件
				String emplistarrry[]=roomConfirmorid.split(",");
				String selectEmpidLStr="";
				for(int s=0;s<emplistarrry.length;s++){
					selectEmpidLStr +="'"+emplistarrry[s]+"',";
				}
				parameterObject.set("selectEmpidLStr", selectEmpidLStr.substring(0,selectEmpidLStr.length()-1));

			}
			if("60000000".equals(cpny_id)){
				parameterObject.set("eaa", "322");
				//根据pu_room 管理担当发送邮件
				String emplistarrry[]=roomConfirmorid.split(",");
				String selectEmpidLStr="";
				for(int s=0;s<emplistarrry.length;s++){
					selectEmpidLStr +="'"+emplistarrry[s]+"',";
				}
				parameterObject.set("selectEmpidLStr", selectEmpidLStr.substring(0,selectEmpidLStr.length()-1));

			}
			if("59000000".equals(cpny_id)){
				parameterObject.set("eaa", "122");
			}
			if("61000000".equals(cpny_id)){
				parameterObject.set("eaa", "422");
			}
			
			List result = null;
			
			if("63000000".equals(cpny_id)||"60000000".equals(cpny_id)){
				 result=eaaServices.selectConfirmoridStr(parameterObject);
			}else{
				 result = eaaServices.getconfirm(parameterObject);
			}
			
			

			inputData.setString("申请人", applyer);
			inputData.setString("主题", "会议室申请");
			inputData.setString("申请时间", applydate);
			inputData.setString("预订日期", bookdate);
			inputData.setString("开始时间", starttime);
			inputData.setString("结束时间", endtime);
			inputData.setString("申请会议室", roomname);	
			inputData.setString("会议主题", purposeofuse);
            SimpleMap smap= new SimpleMap();
			for(int i=0;i<result.size();i++){
				smap=(SimpleMap)result.get(i);
				if(!smap.get("EMAIL").toString().equals("") && smap.get("EMAIL")!=null){
//				mail.sendMail(inputData) ;
				mail.gaSendMail(inputData, cpny_id, smap.get("EMAIL").toString(), "会议室申请 请您确认");
				}
			}
			//essServices.insertOtAffirmMail(inputData);
	}
	
	
}
