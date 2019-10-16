<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				<td background="images/lietianbiao4.jpg" align="LEFT"
					valign="MIDDLE">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<c:forEach items="${toolbarInfo.menuentList}" var="oneResult">
							<c:choose>
								<c:when test="${oneResult.menuCode == toolbarInfo.menu_code}">
									<td class="letianfont3"><a 
										href="<c:out value='${oneResult.menuUrl}'/>">
										<!-- <c:out value="${oneResult.menuIntro}" />-->	
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
				<td width="8" height="26"><img src="images/lietianbiao5.jpg"
					width="8" height="26"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="2"></td>
	</tr>
</table>
