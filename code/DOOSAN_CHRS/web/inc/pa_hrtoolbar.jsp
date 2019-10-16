<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.utils.*" errorPage="" %>
<%@page import="com.ait.sy.bean.*"%>
<%
String menu_code ="";
String rodeLevel ="";
menu_code = request.getParameter("menu_code");
admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
ToolMenu toolmenu = new ToolMenu();
MenuBiz menubiz = new MenuBiz();
toolmenu =menubiz.toolMenuList(menu_code,rodeLevel);
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
          <td width="168" height="45" align="center"> <!--<input name="searchcontents" type="text" id="searchcontents">--></td>
          <td width="56" align="center"><!--<img src="/images/btn/search1.gif" width="56" height="18">--></td>
          <td width="3">
          <td width="527" align="right" valign="middle" style="padding:3 0 3 0 ">
	<!-- <a href="javascript:Save ();"><img src="/images/btn/save1.gif" width="67" height="24" border="0" align="absmiddle"> 
          </a> <img src="/images/btn/return1.gif" width="67" height="24" align="absmiddle"> 
            <img src="/images/btn/homepage1.gif" width="67" height="24" align="absmiddle"> 
            <img src="/images/btn/exit1.gif" width="67" height="24" align="absmiddle">--></td>
        </tr>
      </table></td>
  </tr>
  <tr> 
    <td height="2"></td>
  </tr>
</table>
