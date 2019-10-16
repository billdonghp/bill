<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.utils.*" errorPage="" %>
<%@page import="com.ait.sy.bean.*"%>
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

%>
<table width="100%" height="10" border="0" cellpadding="0" cellspacing="0">
<tr>
<td height="10"></td>
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