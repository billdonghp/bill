<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<% 
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String familyNO = request.getParameter("familyNO")!=null? request.getParameter("familyNO"):"";
   String flag = request.getParameter("flag")!=null? request.getParameter("flag"):"";
   AffirmDAO affirmdao = new AffirmDAO();

   affirmdao.FamilyAffirm(familyNO,admin.getAdminID(),flag);
   affirmdao.hrFamilyUpdate(familyNO,flag);
   response.sendRedirect("/ess/ess0501.jsp?menu_code="+menu_code);
%>
