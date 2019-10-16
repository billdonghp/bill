<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="com.ait.utils.*" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.i18n.MessageSource,com.ait.sy.bean.AdminBean"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%
	/*HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	MessageSource messageSource1 = new MessageSource("ar",admin1.getLocale(), "UTF-8");
	String start = messageSource1.getMessage("alert.att.maintenance.calculation.start");*/
	String menu_code = "";
	String rodeLevel = "";
	SimpleMap map = new SimpleMap();
	ArrayList tool_menu = null;
	if (session.getAttribute("admin") == null) {
		response.sendRedirect("/error.jsp");
	}
	menu_code = request.getParameter("menu_code");
	admin = (AdminBean) session.getAttribute("admin");
	MessageSource messageSource1 = new MessageSource("ar",admin.getLocale(), "UTF-8");
	String start = messageSource1.getMessage("alert.att.maintenance.calculation.start");
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
<script language="javascript" type="text/javascript">
function startCalc() {
	//'开始计算?'
	if(confirm('<ait:message  messageID="alert.att.maintenance.calculation.start" module="ar"/>')){
		beforeSubmit();
		document.form1.fireSubmit();
		afterSubmit();
	}
}
</script>
<table width="100%" border="0" cellpadding="0" cellspacing="0">

  <tr>
    <td>
	  <table width="99%" border="0" cellpadding="0" cellspacing="0">
        <tr>
        <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">
          <!-- '开始计算?'-->
          	<ait:image src="/images/btn/Cal_Total.gif"  border="0" align="absmiddle"
          	onclick="startCalc();" style="cursor:hand" title="添加" enTitle="add" />
          </td>
        </tr>
      </table>
      </td>
  </tr>
</table>
