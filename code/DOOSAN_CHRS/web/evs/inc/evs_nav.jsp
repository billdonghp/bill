<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.util.PageControl,java.util.*,com.ait.sy.bean.*"%>
<%
	request.setAttribute("menuCode",menu_code);
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
										href="<c:out value='${oneResult.menuUrl}'/>">
										<!--<c:out value="${oneResult.menuIntro}" />-->	
										<ait:content enContent='${oneResult.menuEnIntro}' zhContent='${oneResult.menuIntro}' koContent='${oneResult.menuKorIntro}'/>
										</a></td>
									<td><span class="STYLE1">|</span></td>
								</c:when>
								<c:otherwise>
									<td class="letianfont4"><a 
										href="<c:out value='${oneResult.menuUrl}'/>">
										<!--<c:out value="${oneResult.menuIntro}" />-->
										<ait:content enContent='${oneResult.menuEnIntro}' zhContent='${oneResult.menuIntro}' koContent='${oneResult.menuKorIntro}'/>
										</a></td>
									<td><span class="STYLE1">|</span></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</table>
				</td>
				<td width="6" height="26"><img src="/images/lietianbiao5.jpg"
					width="6" height="26"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="2"></td>
	</tr>
</table>





