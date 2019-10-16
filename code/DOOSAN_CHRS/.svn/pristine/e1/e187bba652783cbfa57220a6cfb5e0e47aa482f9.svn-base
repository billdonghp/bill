<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.utils.*"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap"%>
<%@page import="java.util.*"%>
<SCRIPT LANGUAGE="JavaScript" src="/evs/js/evtable0132.js"></SCRIPT>
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

	int insertMenu = 0;
	int updateMenu = 0;
	int deleteMenu = 0;
	for (int i = 0; i < tool_menu.size(); i++) {
		toolmenu = (ToolMenu) tool_menu.get(i);
		if (toolmenu.getInsertr() == 1) {
			insertMenu = 1;

		}
		if (toolmenu.getUpdate() == 1) {
			updateMenu = 1;

		}
		if (toolmenu.getDelect() == 1) {
			deleteMenu = 1;

		}
	}
	
	map.setInt("insertMenu", insertMenu);
	map.setInt("updateMenu", updateMenu);
	map.setInt("deleteMenu", deleteMenu); 
	request.setAttribute("toolbarInfo", map);
%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" valign="middle">
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			<tr>
			 <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	   <ait:history />
              </td>
				<td align="right" valign="middle" style="padding:3 0 3 0 ">
					<%if(insertMenu==1){%>
					<img src="/images/btn/Add.gif" 
				          border="0" align="absmiddle" onclick="javascript:Add();" style="cursor:hand;">
					<%}%>
					<%if(updateMenu==1){%>
					<img src="/images/btn/Modify.gif"
					 border="0" align="absmiddle" onclick="javascript:Modify();" style="cursor:hand;">
					<%}%><%if(deleteMenu==1){%>
					<img src="/images/btn/Delete.gif"
					  border="0" align="absmiddle" onclick="javascript:Delete();" style="cursor:hand;">
					<%}%>
					</td>
			</tr>
		</table>
		</td>
	</tr>

</table>
