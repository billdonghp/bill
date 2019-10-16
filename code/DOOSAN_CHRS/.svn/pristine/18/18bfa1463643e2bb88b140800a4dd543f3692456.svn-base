<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.dao.*" errorPage="" %>
<% 
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String leaveApplyNo = request.getParameter("leaveApplyNo")!=null? request.getParameter("leaveApplyNo"):"";
   String applyType = request.getParameter("applyType")!=null? request.getParameter("applyType"):"";
   String adminid = request.getParameter("adminid")!=null? request.getParameter("adminid"):"";
   String flag = request.getParameter("flag")!=null? request.getParameter("flag"):"";
   AffirmDAO affirmdao = new AffirmDAO();
   affirmdao.LeaveApplyAffirm(leaveApplyNo,flag,adminid);
   response.sendRedirect("/ess/ess0507.jsp?menu_code="+menu_code);
%>
