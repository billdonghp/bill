<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.ess.business.*,com.ait.util.*" errorPage="" %>
<% 


    String empid = request.getParameter("empid")!=null?request.getParameter("empid"):"";
    String username = request.getParameter("username")!=null?request.getParameter("username"):"";
    String password = request.getParameter("newPassword")!=null?request.getParameter("newPassword"):"";
    String oldPassword = request.getParameter("oldPassword")!=null?request.getParameter("oldPassword"):"";
	int check_update = 0;
    EssSecret esssecret = new EssSecret();
	esssecret.setEmpID(empid);
	esssecret.setUserName(username);
	esssecret.setPassWord(password);
	esssecret.setOldPassword(oldPassword);
	check_update = esssecret.updateUserName(esssecret);
   	response.sendRedirect("/ess/ess_secret.jsp?menu_code=ess0401&check_update="+check_update+"&username="+username);
  %>