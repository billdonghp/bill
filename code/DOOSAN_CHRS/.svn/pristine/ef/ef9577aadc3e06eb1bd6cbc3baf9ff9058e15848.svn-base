<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.utils.*" errorPage="" %>
<%@ page import="com.ait.util.PageControl,com.ait.utils.Func"%>
<%@page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<%
String menu_code ="";
String rodeLevel ="";
ArrayList tool_menu=null;
if (session.getAttribute("admin")==null){
	     response.sendRedirect("/error.jsp");
	    }
menu_code = request.getParameter("menu_code");
admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
ToolMenu toolmenu = new ToolMenu();
MenuBiz menubiz = new MenuBiz();
tool_menu=menubiz.toolMenuList(menu_code,rodeLevel);
String imgname = menu_code.substring(0,2);
Func func = new Func();
String search = request.getParameter("search");
if (search == null) {
	search = "";
}
String x = request.getParameter("strPage");
String y= request.getParameter("bigpage");
PageControl pc=new PageControl(10,10);
int bigpage=pc.getTmpBig(y);
int strPage=pc.getTmpSmall(x,bigpage);
pc.setintPage(strPage,bigpage);

int insertMenu=0;
int updateMenu=0;
int deleteMenu=0;
for(int i=0;i<tool_menu.size();i++){
	toolmenu=(ToolMenu)tool_menu.get(i);
	if(toolmenu.getInsertr()==1){
		insertMenu=1;
	
	}
	if(toolmenu.getUpdate()==1){
		updateMenu=1;
	
	}
	if(toolmenu.getDelect()==1){
		deleteMenu=1;
	
	}
}
%>
<table width="100%" height="10" border="0" cellpadding="0" cellspacing="0">
<tr>
<td height="10"></td>
</tr>
</table>
