<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.utils.*" errorPage="" %>
<%@page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<%
String menu_code ="et0301";
String rodeLevel ="";

ArrayList tool_menu=null;
admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
ToolMenu toolmenu = new ToolMenu();
MenuBiz menubiz = new MenuBiz();
tool_menu=menubiz.toolMenuList(menu_code,rodeLevel);
String imgname = menu_code.substring(0,2);

%>
<table width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" valign="middle">
	  <table width="761" height="45" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="7">
             <form name="search" >
             </form>
          <td width="168" height="45" align="center"></td>
          <td width="56" align="center"></td>
          <td width="3">
          <td width="527" align="right" valign="middle" style="padding:3 0 3 0 "></td>
        </tr>
      </table></td>
  </tr>
  <tr> 
    <td height="2"></td>
  </tr>
</table>