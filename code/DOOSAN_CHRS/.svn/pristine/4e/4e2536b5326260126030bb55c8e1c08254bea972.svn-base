<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="com.ait.utils.*" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
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
         <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">
          <!-- 
          	<ait:image src="/images/btn/print.gif" align="absmiddle"
          	onclick="javascript:PrintReport();" style="cursor:hand" /> -->
			<ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle"
          	onclick="javascript:ImportExcel();" style="cursor:hand" />
          	<%if (menu_code.substring(0,3).equalsIgnoreCase("kpa")) {%>
			<img
				<c:if test="${admin.languagePreference == 'zh'}">
					src="/images/bount_Korea.jpg" onclick="changeLang('ko')"
				</c:if>
				<c:if test="${admin.languagePreference != 'zh'}">
					src="/images/bount_Chinese.jpg" onclick="changeLang('zh')"
				</c:if>				
				style="cursor:hand"/>
			<%} %>
          </td>
        </tr>
      </table>
      </td>
  </tr>
</table>
<script type="text/JavaScript">
<!--
var httpRequest;

function changeLang(language) {

	if (window.ActiveXObject) { // IE
		httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		httpRequest = new XMLHttpRequest();
		httpRequest.overrideMimeType('text/xml');
	}
	   
	httpRequest.onreadystatechange = function(){
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				top.topFrame.location.reload();
				top.mainFrame.location.reload();
			} else {
				alert('An error occurred!');
			}
		}
	};
	
	httpRequest.open('POST', '/controlServlet?operation=changeLang', false);
	httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

	if (language == "zh") {
		httpRequest.send('lang=zh');
	} else if (language == "en") {
		httpRequest.send('lang=en');
	} else {
		httpRequest.send('lang=kor');
	}
}
//-->
</script>