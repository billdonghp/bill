<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.utils.*" errorPage="" %>
<%@page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<%
ArrayList tool_menu=null;   
menu_code = request.getParameter("menu_code");     
admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
ToolMenu toolmenu = new ToolMenu();
MenuBiz menubiz = new MenuBiz();                
tool_menu=menubiz.toolMenuList(menu_code,rodeLevel);  
String imgname = menu_code.substring(0,2);
%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" valign="middle">
	  <table width="99%" height="45" border="0" cellpadding="0" cellspacing="0">
        <tr>
        <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">
		        <a href="javascript:Save();"><img src="/images/btn/save1.gif" width="67" height="24" border="0" align="absmiddle">
                 <a href="javascript:history.back();" ><img src="/images/btn/return1.gif" width="67" height="24" border="0" align="absmiddle"></a>
          </a></td>
        </tr>  
      </table></td>         
  </tr>
  <tr>
    <td height="2"></td>           
  </tr>
</table>

