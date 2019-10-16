<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.utils.*" errorPage="" %>
<%@page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<%
String menu_code = "sy0101";
String rodeLevel = "1";

ArrayList tool_menu=null;
//menu_code = request.getParameter("menu_code");
AdminBean admin = new AdminBean();
admin = (AdminBean)session.getAttribute("admin");
//rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
ToolMenu toolmenu = new ToolMenu();
MenuBiz menubiz = new MenuBiz();
tool_menu=menubiz.toolMenuList(menu_code,rodeLevel);
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
<table width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr> 
    <td height="30" valign="middle">
	  <table width="761" height="45" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="7">
		     <form name="search" >
             </form>
             <form name="search" >
             </form>
          <td width="168" height="45" align="center"> <input name="searchcontents" type="text" id="searchcontents"></td>
          <td width="56" align="center"><img src="/images/btn/search1.gif" width="56" height="18"></td>
          <td width="3"> 
          <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td width="527" align="right" valign="middle" style="padding:3 0 3 0 ">
		   <a href="javascript:Save ();"><img src="/images/btn/save1.gif" width="67" height="24" border="0" align="absmiddle"></a> <img src="/images/btn/return1.gif" width="67" height="24" align="absmiddle"> 
            <img src="/images/btn/homepage1.gif" width="67" height="24" align="absmiddle"> 
            <img src="/images/btn/exit1.gif" width="67" height="24" align="absmiddle"></td>
        </tr>
      </table></td>
  </tr>
  <tr> 
    <td height="2"></td>
  </tr>
</table>