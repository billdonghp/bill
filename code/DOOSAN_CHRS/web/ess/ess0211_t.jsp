<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<% 
   MatchApply matchapply = new MatchApply();
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String empID = request.getParameter("empID")!=null? request.getParameter("empID"):"";
   String matchApplyPerson = request.getParameter("matchApplyPerson")!=null? request.getParameter("matchApplyPerson"):"";
   String matchApplyTypeCode = request.getParameter("matchApplyTypeCode")!=null? request.getParameter("matchApplyTypeCode"):"";
  // String leaveHours = request.getParameter("leaveHours")!=null? request.getParameter("leaveHours"):"";
   
   String matchStartDate = request.getParameter("matchStartDate")!=null? request.getParameter("matchStartDate"):"";   
   String Starttime = request.getParameter("start_time")!=null? request.getParameter("start_time"):"";
   String Endtime = request.getParameter("end_time")!=null? request.getParameter("end_time"):"";   
   String matchEndDate = request.getParameter("matchEndDate")!=null? request.getParameter("matchEndDate"):"";   
   matchStartDate = matchStartDate + Starttime;
   matchEndDate = matchEndDate + Endtime;
   
  // String work_content = request.getParameter("work_content")!=null? StringUtil.toCN(request.getParameter("work_content")):"";
   String[] affirmlist = request.getParameterValues("affirmlist");
   EssDAO essdao  = new EssDAO();
   String MatchApplySeq = essdao.getEssMatchApplySeq();
   matchapply.setMatchApplyNo(MatchApplySeq);
   matchapply.setMatchPerson(matchApplyPerson);
   matchapply.setMatchTypeCode(matchApplyTypeCode);
  // leaveapply.setLeaveDays(leaveHours);
   matchapply.setMatchApplyStartDate(matchStartDate);
   matchapply.setMatchApplyEndDate(matchEndDate);
  // leaveapply.setLeaveContent(work_content);
   matchapply.setCreatedBy(admin.getAdminID());
   
   
   essdao.addEssMatchApply(matchapply,empID);
   if(affirmlist!=null){
      essdao.addEssApplyAffirmor(MatchApplySeq,affirmlist,"MatchApply");
   }
   response.sendRedirect("/ess/ess0207.jsp?menu_code="+menu_code);
%>
