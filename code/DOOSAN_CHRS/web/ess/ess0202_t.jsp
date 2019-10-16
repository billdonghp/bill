<%@ page contentType="text/html; charset=UTF-8" language="java" import="org.apache.log4j.Logger,com.ait.ess.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<% 
   LeaveApply leaveapply = new LeaveApply();
   String menu_code = StringUtil.checkNull(request.getParameter("menu_code"));
   String empID = StringUtil.checkNull(request.getParameter("empID"));
   String leaveApplyPerson = StringUtil.checkNull(request.getParameter("leaveApplyPerson"));
   String leaveApplyTypeCode = StringUtil.checkNull(request.getParameter("leaveApplyTypeCode"));
   String leaveHours = StringUtil.checkNull(request.getParameter("leaveHours"), "0");

   String leaveStartDate = StringUtil.checkNull(request.getParameter("leaveStartDate"));
   String Starttime = StringUtil.checkNull(request.getParameter("start_time"));
   String Endtime = StringUtil.checkNull(request.getParameter("end_time"));   
   String leaveEndDate = StringUtil.checkNull(request.getParameter("leaveEndDate"));   
   leaveStartDate = leaveStartDate + " " + Starttime;
   leaveEndDate = leaveEndDate + " " + Endtime;
   String work_content = StringUtil.toCN(request.getParameter("work_content"));
   String[] affirmlist = request.getParameterValues("affirmlist");
   EssDAO essdao  = new EssDAO();
   String LeaveApplySeq = essdao.getEssLeaveApplySeq();
   leaveapply.setLeaveApplyNo(LeaveApplySeq);
   leaveapply.setLeavePerson(leaveApplyPerson);
   leaveapply.setLeaveTypeCode(leaveApplyTypeCode);
   leaveapply.setLeaveDays(leaveHours);
   leaveapply.setLeaveApplyStartDate(leaveStartDate);
   
   leaveapply.setLeaveApplyEndDate(leaveEndDate);
   leaveapply.setLeaveContent(work_content);
   leaveapply.setCreatedBy(admin.getAdminID());
   int result = essdao.addEssLeaveApply(leaveapply,empID);
   Logger.getLogger(getClass()).debug("Apply Result : " + result);
   if(affirmlist!=null){
      essdao.addEssApplyAffirmor(LeaveApplySeq,affirmlist,"LeaveApply");
   }
   response.sendRedirect("/ess/ess0202.jsp?menu_code="+menu_code+"&result=" + result);
%>
