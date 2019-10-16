<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="com.ait.utils.*" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<SCRIPT LANGUAGE="JavaScript" src="/evs/js/ev0105table.js"></SCRIPT>
<%
String menu_code ="";
String rodeLevel ="";
ArrayList tool_menu=null;
menu_code = request.getParameter("menu_code");
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
		<td height="30" valign="middle" align="right">
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			<tr>
			  <td width="90%" align="right" valign="right" 
					style="padding:3 0 3 0 "><%if(updateMenu==1){%><a
					href="javascript:Modify();"><img src="/images/btn/modify.gif"
					width="67" height="24" border="0"> </a><%}%></td>
				<td width="10%" valign="right"
					style="padding:3 0 3 0 "><%if(updateMenu==1){%><a 
					href="javascript:Report();" ><img src="/images/btn/report.gif" 
					width="67" height="24" border="0"></a><%}%></td>
			</tr>
		</table>
		</td>
	</tr>

</table>


