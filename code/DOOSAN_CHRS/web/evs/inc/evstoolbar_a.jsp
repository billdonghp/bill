<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="com.ait.utils.*" errorPage=""%>
<%@page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<SCRIPT LANGUAGE="JavaScript" src="../evs/js/evtable.js"></SCRIPT>
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

    function AddSave()
    {
          document.LGFORM.action='/evs/<%=menu_code%>_t.jsp';
          document.LGFORM.submit();
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
			<ait:image src="/images/btn/Save.gif" border="0" align="absmiddle" onclick="javascript:AddSave();" style="cursor:hand" title="保存"
				enTitle="save" />
			<ait:image src="/images/btn/Back.gif" border="0" align="absmiddle" onclick="javascript:history.back();" style="cursor:hand" title="返回"
				enTitle="RETURN" />
		</td>
			</tr>
		</table>
		</td>
	</tr>

</table>