<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.dao.*" errorPage="" %>
<% 
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String otapplyNo = request.getParameter("otapplyNo")!=null? request.getParameter("otapplyNo"):"";
   String applyType = request.getParameter("applyType")!=null? request.getParameter("applyType"):"";
   String OTdate = request.getParameter("OTdate")!=null? request.getParameter("OTdate"):"";
   String adminid = request.getParameter("adminid")!=null? request.getParameter("adminid"):"";
   String flag = request.getParameter("flag")!=null? request.getParameter("flag"):"";
   String otLength = request.getParameter("otlength");
   AffirmDAO affirmdao = new AffirmDAO();
   affirmdao.OTApplyAffirm(otapplyNo,flag,adminid,otLength);
   response.sendRedirect("/ess/ess0506.jsp?menu_code="+menu_code+"page="+page);
%>
