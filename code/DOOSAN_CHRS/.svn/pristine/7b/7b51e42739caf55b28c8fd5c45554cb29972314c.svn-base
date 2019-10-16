<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.utils.*"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<SCRIPT LANGUAGE="JavaScript" src="/evs/js/evtable.js"></SCRIPT>
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
<script language="JavaScript" type="" src="">
    
    function Add()
    {	
		location.href='/evs/<%=menu_code%>_a.jsp?menu_code=<%=menu_code%>&ID='+ID;
    }
</script>
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
