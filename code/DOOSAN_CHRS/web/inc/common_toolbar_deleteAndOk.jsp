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
    <td>
	  <table width="99%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="left" valign="middle" style="padding:3 0 3 0 " nowrap="nowrap">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 "> 
           <c:if test="${toolbarInfo.insertMenu == 1}">
          <ait:image src="/images/btn/confirm.gif"  border="0" align="absmiddle"
          	onclick="javascript:Confirm();" style="cursor:hand" title="确认" enTitle="confirm" />
		  <ait:image src="/images/btn/Delete.gif"  border="0" align="absmiddle"
          	onclick="javascript:Delete();" style="cursor:hand" title="删除" enTitle="delete" />
          	 </c:if> 
          </td>    
        </tr>  
      </table>
      </td>
  </tr>
</table>
