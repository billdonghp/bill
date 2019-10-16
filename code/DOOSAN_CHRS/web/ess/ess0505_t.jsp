<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<% 
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String healthNo = request.getParameter("healthNo")!=null? request.getParameter("healthNo"):"";
   String flag = request.getParameter("flag")!=null? request.getParameter("flag"):"";
   AffirmDAO affirmdao = new AffirmDAO();
   affirmdao.healthAffirm(healthNo,admin.getAdminID(),flag);
   affirmdao.hrHealthUpdate(healthNo,flag);
   response.sendRedirect("/ess/ess0505.jsp?menu_code="+menu_code);
%>
