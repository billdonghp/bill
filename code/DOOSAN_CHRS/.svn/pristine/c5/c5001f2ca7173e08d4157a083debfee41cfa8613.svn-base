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
    <td height="30" >
	  <table width="99%" height="45" border="0" cellpadding="0" cellspacing="0">
        <tr>
        <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">
		  <%if(insertMenu==1){%>
		  <a href="javascript:Add();" > 
			<ait:image src="/images/btn/add1.gif" width="67" height="24" border="0" align="absmiddle"/>
		  	<!-- <img src="/images/btn/add1.gif" width="67" height="24" border="0" align="absmiddle">  -->
          </a><%}%><%if(updateMenu==1){%><a href="javascript:Update();" >
			<ait:image src="/images/btn/modify1.gif" width="67" height="24" border="0" align="absmiddle"/>
          	<!-- <img src="/images/btn/modify1.gif" width="67" height="24" border="0" align="absmiddle">  -->
          </a> <%}%><%if(deleteMenu==1){%><a href="javascript:Delete();" >
			<ait:image src="/images/btn/delete1.gif" width="67" height="24" border="0" align="absmiddle"/>
          	<!-- <img src="/images/btn/delete1.gif" width="67" height="24" border="0" align="absmiddle">  -->
          </a><%}%></td>
        </tr>
      </table>
      </td>
  </tr>
</table>
