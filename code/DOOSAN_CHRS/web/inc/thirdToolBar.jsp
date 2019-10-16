<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<%@ page import="com.ait.util.PageControl,com.ait.utils.Func"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ait.utils.*"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<%
	if (session.getAttribute("admin")==null){     
	     response.sendRedirect("/error.jsp");
	}
    admin = (AdminBean)session.getAttribute("admin");
	menu_code = request.getParameter("menu_code");
	rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
    MenuEnt menuent = new MenuEnt();
	List menu_vector = menubiz.thirdmenulist(menu_code,rodeLevel);
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="8">&nbsp;</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="4" height="26"><img src="/images/lietianbiao3.jpg"
					width="4" height="26"></td>
				<td background="/images/lietianbiao4.jpg" align="LEFT"
					valign="MIDDLE">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<%
								for (int i = 0; i < menu_vector.size(); i++) {
								menuent = (MenuEnt) menu_vector.get(i);
						%>
						<%
						if (menu_code.equals(menuent.getMenuCode())) {
						%>
						<td class="letianfont3" ><a href="<%=menuent.getMenuUrl()%>">
           <ait:content enContent='<%=menuent.getMenuEnIntro()%>' zhContent='<%=menuent.getMenuIntro()%>' koContent='<%=menuent.getMenuKorIntro() %>'/></a></td>
						<td><span class="STYLE1">|</span></td>
						<%
						} else {
						%>            
						<td class="letianfont4" ><a href="<%=menuent.getMenuUrl()%>">
  <ait:content enContent='<%=menuent.getMenuEnIntro()%>' zhContent='<%=menuent.getMenuIntro()%>' koContent='<%=menuent.getMenuKorIntro() %>'/></a></td>
						<td><span class="STYLE1">|</span></td>
						<%
							}
							}
						%>
					</tr>
				</table>
				</td>
				<td width="8" height="26"><img src="/images/lietianbiao5.jpg"
					width="8" height="26"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="2"></td>
	</tr>
</table>