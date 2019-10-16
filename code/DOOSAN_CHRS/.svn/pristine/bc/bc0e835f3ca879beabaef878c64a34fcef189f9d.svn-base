<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.utils.*" errorPage="" %>
<%@page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<%
String menu_code ="";
String rodeLevel ="";
menu_code = request.getParameter("menu_code");

ArrayList tool_menu=null;
admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
ToolMenu toolmenu = new ToolMenu();
MenuBiz menubiz = new MenuBiz();
tool_menu=menubiz.toolMenuList(menu_code,rodeLevel);
String imgname = menu_code.substring(0,2);
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
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="15" valign="middle">
	  <table width="99%" height="15" border="0" cellpadding="0" cellspacing="0">
        <tr>
        <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td width="7">
          <td width="168" align="center"> </td>
          <td width="56" align="center"></td>
          <td width="3">
          <td width="527" align="right" valign="middle" style="padding:3 0 3 0 "></td>
        </tr>
    </table></td>
  </tr>
  <tr> 
    <td height="1"></td>
  </tr>
</table>
