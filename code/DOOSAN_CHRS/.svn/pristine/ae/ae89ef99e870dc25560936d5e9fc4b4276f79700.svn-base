<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.dao.*" errorPage="" %>
<% 
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String matchApplyNo = request.getParameter("matchApplyNo")!=null? request.getParameter("matchApplyNo"):"";
   String applyType = request.getParameter("applyType")!=null? request.getParameter("applyType"):"";
   String adminid = request.getParameter("adminid")!=null? request.getParameter("adminid"):"";
   String flag = request.getParameter("flag")!=null? request.getParameter("flag"):"";
   AffirmDAO affirmdao = new AffirmDAO();
   affirmdao.MatchApplyAffirm(matchApplyNo,flag,adminid);
   response.sendRedirect("/ess/ess0511.jsp?menu_code="+menu_code);
%>