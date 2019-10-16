<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.entity.*,com.ait.utils.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<% 
OTApply otApply = new OTApply();
String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
String empID = request.getParameter("empID")!=null? request.getParameter("empID"):"";
String OTdate = request.getParameter("OTdate")!=null? request.getParameter("OTdate"):"";
String start_time = request.getParameter("start_time")!=null? request.getParameter("start_time"):"";
String end_time = request.getParameter("end_time")!=null? request.getParameter("end_time"):"";
String anotherDay = request.getParameter("anotherDay")!=null? request.getParameter("anotherDay"):"";
String deduct_time = request.getParameter("deduct_time")!=null? request.getParameter("deduct_time"):"";
String otTypeCode =  StringUtil.checkNull(request.getParameter("otTypeCode"));

//	String otTypeCode = StringUtil.checkNull(request.getParameter("otTypeCode"));
//	根据日历自动判断加班类型
String work_content = request.getParameter("work_content")!=null? StringUtil.toCN(request.getParameter("work_content")):"";
String[] affirmlist = request.getParameterValues("affirmlist");
EssDAO essdao  = new EssDAO();
String OtApplySeq = essdao.getEssOTApplySeq();
otApply.setOtApplySEQ(OtApplySeq);
otApply.setOtApplyDate(OTdate);
otApply.setOtStartTime(start_time);
otApply.setOtNextDays(NumberUtil.parseInt(anotherDay));
otApply.setOtEndTime(end_time);
otApply.setOtDeductTime(deduct_time);
otApply.setOtWorkContent(work_content);
otApply.setOtTypeCode(otTypeCode);
//	otApply.setOtTypeCode(otTypeCode);
//	根据日历自动判断加班类型
otApply.setCreatedBy(admin.getAdminID());

int result = essdao.addEssOTApply(otApply,empID);
// 0 申请不成功; 1 申请成功; 2 获取加班类型失败,申请不成功
if(affirmlist!=null || result == 1)
	essdao.addEssApplyAffirmor(OtApplySeq,affirmlist,"OtApply");
	SendMail sendMail = new SendMail();
	String affirmor = sendMail.getAffirmor(admin.getAdminID(),"OtApply",1);
	sendMail.setStaff(admin.getChineseName()+"("+admin.getAdminID()+")");
	String mail = sendMail.getMailAddress(affirmor);
	
	sendMail.setType("OTAPPLY");
	sendMail.send(affirmor);
	
response.sendRedirect("/ess/ess0209.jsp?menu_code="+menu_code+"&result="+result);%>