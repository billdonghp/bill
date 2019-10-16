<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.ait.utils.*"%>
<%@ page import="com.ait.sy.bean.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%
	String menu_code ="";
	String rodeLevel ="";
	MenuBiz menubiz = new MenuBiz();

    admin = (AdminBean)session.getAttribute("admin");
	menu_code = request.getParameter("menu_code");
	rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
    MenuEnt menuent = new MenuEnt();
	ArrayList menu_vector =menubiz.thirdmenulist(menu_code,rodeLevel);
%>
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