<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<% 
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String matchApplyNo = request.getParameter("matchApplyNo")!=null? request.getParameter("matchApplyNo"):"";
   String applyType = request.getParameter("applyType")!=null? request.getParameter("applyType"):"";
   String adminid = request.getParameter("adminid")!=null? request.getParameter("adminid"):"";
   String flag = request.getParameter("flag")!=null? request.getParameter("flag"):"";
   
   AffirmDAO affirmdao = new AffirmDAO();
   //affirmdao.LeaveApplyAffirm(leaveApplyNo,flag);
   affirmdao.updateAffirmorFlag(matchApplyNo,adminid,applyType,flag);
   int strPage=Integer.parseInt(request.getParameter("strPage"));
   
   response.sendRedirect("/ess/ess0803.jsp?menu_code="+menu_code+"&strPage="+strPage);
%>
