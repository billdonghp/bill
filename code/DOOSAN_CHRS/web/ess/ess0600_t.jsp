<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.dao.*,com.ait.util.*" errorPage="" %>
<%
   String menu_code = request.getParameter("menu_code")!=null?request.getParameter("menu_code"):"";
   String NO = request.getParameter("NO")!=null?request.getParameter("NO"):"";
   String operate = request.getParameter("operate")!=null?request.getParameter("operate"):"";
   AffirmDAO affirmdao = new AffirmDAO();
   if(operate.equals("tolerence")){
      affirmdao.deleteTolerence(NO);
      affirmdao.deleteTolerenceContent(NO);
      affirmdao.deleteTolerenceFeeContent(NO);
	  affirmdao.deleteEssAffirm(NO,"TolerenceApply");
	  response.sendRedirect("/ess/ess0609.jsp?menu_code="+menu_code);
   }
   if(operate.equals("leaveApply")){
      affirmdao.deleteLeaveApply(NO);
	  affirmdao.deleteEssAffirm(NO,"LeaveApply");
	  response.sendRedirect("/ess/ess0608.jsp?menu_code="+menu_code);
   }
     if(operate.equals("matchApply")){
      affirmdao.deleteMatchApply(NO);
	  affirmdao.deleteEssAffirm(NO,"MatchApply");
	  response.sendRedirect("/ess/ess0612.jsp?menu_code="+menu_code);
   }
   if(operate.equals("OTApply")){
      affirmdao.deleteOTApply(NO);
	  affirmdao.deleteEssAffirm(NO,"OtApply");
	  response.sendRedirect("/ess/ess0607.jsp?menu_code="+menu_code);
   }
   if(operate.equals("family")){
      affirmdao.deleteFamily(NO);
      response.sendRedirect("/ess/ess0602.jsp?menu_code="+menu_code);
   }
   if(operate.equals("qualification")){
      affirmdao.deleteQualification(NO);
	  response.sendRedirect("/ess/ess0603.jsp?menu_code="+menu_code);
   }
   if(operate.equals("foreignlanguage")){
      affirmdao.deleteForeignLanguage(NO);
	  response.sendRedirect("/ess/ess0604.jsp?menu_code="+menu_code);
   }
   if(operate.equals("militaryservice")){
      affirmdao.deletMilitaryServices(NO);
	  response.sendRedirect("/ess/ess0605.jsp?menu_code="+menu_code);
   }
   if(operate.equals("health")){
      affirmdao.deleteHealth(NO);
	  response.sendRedirect("/ess/ess0606.jsp?menu_code="+menu_code);
   }
   if(operate.equals("personalInfo")){
      affirmdao.deletePersonalInfo(NO);
	  response.sendRedirect("/ess/ess0601.jsp?menu_code="+menu_code);
   }
  



%>
