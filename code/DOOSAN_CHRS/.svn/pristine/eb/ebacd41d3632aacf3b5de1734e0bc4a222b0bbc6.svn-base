<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<% 
OTApply otApply = new OTApply();
String menu_code = StringUtil.checkNull(request.getParameter("menu_code"));
String empID = StringUtil.checkNull(request.getParameter("empID"));
String[] empIDs = request.getParameterValues("empids");
if (empIDs == null) {
	empIDs = new String[1];
	empIDs[0] = empID;
}
String OTdate = StringUtil.checkNull(request.getParameter("OTdate"));
String start_time = StringUtil.checkNull(request.getParameter("start_time"));
String end_time = StringUtil.checkNull(request.getParameter("end_time"));
String anotherDay = StringUtil.checkNull(request.getParameter("anotherDay"));
String deduct_time = StringUtil.checkNull(request.getParameter("deduct_time"));
String work_content = StringUtil.toCN(request.getParameter("work_content"));
//String[] affirmlist = request.getParameterValues("affirmlist");
EssDAO essdao  = new EssDAO();
otApply.setMenuCode(menu_code);
otApply.setOtApplyDate(OTdate);
otApply.setOtStartTime(start_time);
otApply.setOtNextDays(NumberUtil.parseInt(anotherDay));
otApply.setOtEndTime(end_time);
otApply.setOtDeductTime(deduct_time);
otApply.setOtWorkContent(work_content);
otApply.setCreatedBy(admin.getAdminID());
//不再使用页面传入的决裁者列表,自动从库中读取每个加班人的决裁者
response.sendRedirect("/ess/" + menu_code + ".jsp?menu_code="+menu_code+"&result="+essdao.addEssOTApply(otApply, empIDs));
%>