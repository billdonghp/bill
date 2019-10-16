<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.sql.*,com.ait.utils.*" errorPage=""%>
<%@page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<%
String menu_code="";
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
    function ModifySave()
    {

            document.LGFORM.action='/evs/<%=menu_code%>_t.jsp';
            document.LGFORM.submit();
    }
    </script>
<form name="LGFORM" method="POST" action="" >
<input type="hidden" name="flag" value="update">
<input type="hidden" name="menu_code" value="<%=menu_code%>" >
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" valign="middle">
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="right" valign="middle"
					style="padding:3 0 3 0 ">
					<img name="defaultbtn" id="defaultbtn" src="/images/ev/default.gif" border="0" onClick="javascript:defaultGrade();" style="cursor:hand;visibility:"  alt="相对化" />
					<img name="submitbtn" id="submitbtn" src="/images/ev/submit.gif" border="0" onClick="javascript:submitDefaultGrade();" style="cursor:hand;visibility:"  alt="同意/提交" /></td>
			</tr>
		</table>
		</td>
	</tr>

</table>