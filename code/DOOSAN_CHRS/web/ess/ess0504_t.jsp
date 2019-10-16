<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<% 
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String militaryNO = request.getParameter("militaryNO")!=null? request.getParameter("militaryNO"):"";
   String flag = request.getParameter("flag")!=null? request.getParameter("flag"):"";
   AffirmDAO affirmdao = new AffirmDAO();
   affirmdao.MilitaryServicesAffirm(militaryNO,admin.getAdminID(),flag);
   affirmdao.hrMilitaryServiceUpdate(militaryNO,flag);
   response.sendRedirect("/ess/ess0504.jsp?menu_code="+menu_code);
%>
