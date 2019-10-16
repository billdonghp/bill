<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<% 
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String languageNo = request.getParameter("languageNo")!=null? request.getParameter("languageNo"):"";
   String flag = request.getParameter("flag")!=null? request.getParameter("flag"):"";
   AffirmDAO affirmdao = new AffirmDAO();
   affirmdao.ForeignLanguageAffirm(languageNo,admin.getAdminID(),flag);
   affirmdao.hrLanguageUpdate(languageNo,flag);
   response.sendRedirect("/ess/ess0503.jsp?menu_code="+menu_code);
%>
