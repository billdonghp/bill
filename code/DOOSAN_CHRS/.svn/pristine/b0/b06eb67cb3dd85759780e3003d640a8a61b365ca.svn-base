<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.ait.utils.*"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.sy.bean.*"%>     
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<%
	if (session.getAttribute("admin")==null){   
	     response.sendRedirect("/error.jsp");
	    }
    admin = (AdminBean)session.getAttribute("admin");
	menu_code = request.getParameter("menu_code");
	rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
    MenuEnt menuent = new MenuEnt();
	ArrayList menu_vector =menubiz.thirdmenulist(menu_code,rodeLevel);
%>

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="33" bgcolor="#F5F5F5"><table height="30"  border="0" cellpadding="0" cellspacing="0">
     <tr><% 
	    for(int i=0; i<menu_vector.size(); i++){
		  menuent = (MenuEnt)menu_vector.get(i);
      %><%if (menu_code.equals(menuent.getMenuCode())){%><td width="14%" class="info_bg_on"><a href="<%=menuent.getMenuUrl()%>" class="on"><%=menuent.getMenuIntro()%></a><%}else{%><td width="14%" class="info_bg_off"><a href="<%=menuent.getMenuUrl()%>" class="off"><%=menuent.getMenuIntro()%></a><%}%></td>
	  <%}%></tr>
    </table></td>
  </tr>
</table>


