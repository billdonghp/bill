<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.utils.*" errorPage="" %>
<%@page import="com.ait.sy.bean.*,com.ait.util.PageControl,java.util.Vector"%>
<%@page import="java.util.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%
String menu_code ="";
String rodeLevel ="";


ArrayList tool_menu=null;
if (session.getAttribute("admin")==null){
	     response.sendRedirect("/error.jsp");
	    }
menu_code = request.getParameter("menu_code");
admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
ToolMenu toolmenu = new ToolMenu();
MenuBiz menubiz = new MenuBiz();
tool_menu=menubiz.toolMenuList(menu_code,rodeLevel);
String imgname = menu_code.substring(0,2);
MenuEnt menuent = new MenuEnt();
ArrayList menu_vector =menubiz.thirdmenulist(menu_code,rodeLevel);
String x = request.getParameter("strPage");
String y= request.getParameter("bigpage");
PageControl pc=new PageControl(10,10);
int bigpage=pc.getTmpBig(y);
int strPage=pc.getTmpSmall(x,bigpage);

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
<table width="99%" height="45" border="0" cellpadding="0" cellspacing="0">
        <tr>
        <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">
          <a href="javascript:Save();" >
          <img src="/images/btn/save1.gif" width="67" height="24" border="0" align="absmiddle"></a> 
          </td>
        </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="kuang_zuo"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="18"><img src="/images/top_zuo.jpg" width="18" height="28"></td>
          <td background="/images/top_zuo_d2.jpg"><table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <%
										for (int i = 0; i < menu_vector.size(); i++) {
										menuent = (MenuEnt) menu_vector.get(i);
								%>
                <%
								if (menu_code.equals(menuent.getMenuCode())) {
								%>
                <td class="td_shen"><a href="<%=menuent.getMenuUrl()%>" class="shen"><%=menuent.getMenuIntro()%></a></td>
                <%
								} else {
								%>
                <td class="td_qian"><a href="<%=menuent.getMenuUrl()%>" class="qian"><%=menuent.getMenuIntro()%></a></td>
                <%
									}
									}
								%>
              </tr>
            </table></td>
          <td width="14" align="right"><img src="/images/top_you.jpg" width="14" height="28"></td>
        </tr>
      </table></td>
  </tr>
</table>