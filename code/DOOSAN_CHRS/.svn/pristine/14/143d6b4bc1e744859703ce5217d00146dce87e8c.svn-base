<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="com.ait.utils.*" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap"%>

<%
	String menu_code = "";
	String rodeLevel = "";
	SimpleMap map = new SimpleMap();
	ArrayList tool_menu = null;
	if (session.getAttribute("admin") == null) {
		response.sendRedirect("/error.jsp");
	}
	menu_code = request.getParameter("menu_code");
	admin = (AdminBean) session.getAttribute("admin");
	rodeLevel = admin.getScreenGrantNo() != null ? admin.getScreenGrantNo() : "";
	ToolMenu toolmenu = new ToolMenu();
	MenuBiz menubiz = new MenuBiz();
	tool_menu = menubiz.toolMenuList(menu_code, rodeLevel);

	request.setAttribute("toolbarInfo", map);

%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
	  <table width="99%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="left" valign="middle" style="padding:3 0 3 0 " nowrap="nowrap">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">&nbsp;
          </td>    
        </tr>  
      </table>
      </td>
  </tr>
</table>