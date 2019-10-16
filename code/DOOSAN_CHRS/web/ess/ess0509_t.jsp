<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<% 
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String TolerenceNo = request.getParameter("TolerenceNo")!=null? request.getParameter("TolerenceNo"):"";
   String applyType = request.getParameter("applyType")!=null? request.getParameter("applyType"):"";
   String adminid = request.getParameter("adminid")!=null? request.getParameter("adminid"):"";
   String flag = request.getParameter("flag")!=null? request.getParameter("flag"):"";
   AffirmDAO affirmdao = new AffirmDAO();
   affirmdao.TolerenceApplyAffirm(TolerenceNo,flag);
   affirmdao.updateAffirmorFlag(TolerenceNo,adminid,applyType);
   response.sendRedirect("/ess/ess0508.jsp?menu_code="+menu_code);
%>
