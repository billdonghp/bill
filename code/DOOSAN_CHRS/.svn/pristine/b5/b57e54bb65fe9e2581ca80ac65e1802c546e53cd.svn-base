<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.util.PageControl,com.ait.utils.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ait.sy.bean.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<%

	String menu_code = "";
	String rodeLevel = "";
	ArrayList tool_menu = null;
	if (session.getAttribute("admin") == null) {
		response.sendRedirect("/error.jsp");
	}
	menu_code = request.getParameter("menu_code");
	request.setAttribute("menuCode",menu_code);
	admin = (AdminBean) session.getAttribute("admin");
	rodeLevel = admin.getScreenGrantNo() != null ? admin.getScreenGrantNo() : "";
	ToolMenu toolmenu = new ToolMenu();
	MenuBiz menubiz = new MenuBiz();
	tool_menu = menubiz.toolMenuList(menu_code, rodeLevel);
	
	MenuEnt menuent = new MenuEnt();
	ArrayList menu_vector = menubiz.thirdmenulist(menu_code, rodeLevel);
	request.setAttribute("menuList",menu_vector);
	String search = request.getParameter("search");
	if (search == null) {
		search = "";
	}
	String x = request.getParameter("strPage");
	String y = request.getParameter("bigpage");
	PageControl pc = new PageControl(10, 10);
	int bigpage = pc.getTmpBig(y);
	int strPage = pc.getTmpSmall(x, bigpage);
	pc.setintPage(strPage, bigpage); 
	
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
						<c:forEach items="${menuList}" var="oneResult">
							<c:choose> 
								<c:when test="${oneResult.menuCode == menuCode}">
									<td class="letianfont3"><a 
										href="<c:out value='${oneResult.menuUrl}&empID=${basic.empID}'/>">
										<!--<c:out value="${oneResult.menuIntro}" />-->	
										<ait:content enContent='${oneResult.menuEnIntro}' zhContent='${oneResult.menuIntro}' koContent='${oneResult.menuKorIntro}'/>
										</a></td>
									<td><span class="STYLE1">|</span></td>
								</c:when>
								<c:otherwise>
									<td class="letianfont4"><a 
										href="<c:out value='${oneResult.menuUrl}&empID=${basic.empID}'/>">
										<!--<c:out value="${oneResult.menuIntro}" />-->
										<ait:content enContent='${oneResult.menuEnIntro}' zhContent='${oneResult.menuIntro}' koContent='${oneResult.menuKorIntro}'/>
										</a></td>
									<td><span class="STYLE1">|</span></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					
					
					
						<%
							//	for (int i = 0; i < menu_vector.size(); i++) {
							//	menuent = (MenuEnt) menu_vector.get(i);
						%>
						<%
						//if (menu_code.equals(menuent.getMenuCode())) {
						%>
						<!-- <td class="letianfont3"><a href="<%=menuent.getMenuUrl()%>"><%=menuent.getMenuIntro()%></a></td>
						<td><span class="STYLE1">|</span></td> -->
						<%
						//} else {
						%>
						<!--<td class="letianfont4"><a href="<%=menuent.getMenuUrl()%>"><%=menuent.getMenuIntro()%></a></td>
						<td><span class="STYLE1">|</span></td>-->
						<%
						//	}
						//	}
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
