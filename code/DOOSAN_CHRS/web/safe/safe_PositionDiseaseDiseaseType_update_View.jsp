<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="POSITION_INFORMATION" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="DISEASE_INFORMATION" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="DISEASE_TYPE_INFORMATION" class="java.util.ArrayList" scope="request" />

<jsp:useBean id="positionId" class="java.lang.String" scope="request" />
<jsp:useBean id="position" class="java.lang.String" scope="request" />

<jsp:useBean id="diseaseId" class="java.lang.String" scope="request" />
<jsp:useBean id="diseaseName" class="java.lang.String" scope="request" />

<jsp:useBean id="diseaseTypeId" class="java.lang.String" scope="request" />
<jsp:useBean id="diseaseTypeName" class="java.lang.String" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>岗位与职业危害修改</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Save() {
	
	
	document.form1.action="/safeControlServlet?operation=jobHealth&content=PositionDiseaseDiseaseTypeUpdate&menu_code=${param.menu_code}";
	document.form1.submit();
}


</SCRIPT>
<body>

<form name="form1" method="post">
<input type="hidden" name="temp" value="0">
<input type="hidden" name="relations_id" value="${relations_id }">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			
			<%@ include file="../inc/common_toolbar_a.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->
		<br>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">岗位与职业危害修改</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			
			

			<tr>
				<td width="10%" class="info_title_01"> 岗位</td>
				<td width="10%" class="info_title_01"> 职业危害</td>
				<td width="10%" class="info_title_01"> 职业病类型</td>
			</tr>
			<tr>
				<td align="center" style="color: #666666;">
					<select name="POSITION">
						<c:forEach items="${POSITION_INFORMATION}" var="allPosition" varStatus="i">
							<c:choose>
								<c:when test="${allPosition.CODE_ID==positionId}">
									<option value="${positionId}" selected>${position}</option>
								</c:when>
								<c:otherwise>
									<option value="${allPosition.CODE_ID}">
										${allPosition.CODE_NAME}
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td align="center" style="color: #666666;">
					<select name="DISEASE">
						<c:forEach items="${DISEASE_INFORMATION}" var="allDISEASE" varStatus="j">
							<c:choose>
								<c:when test="${allDISEASE.CODE_ID == diseaseId}">
									<option value="${diseaseId}" selected>${diseaseName}</option>
								</c:when>
								<c:otherwise>
									<option value="${allDISEASE.CODE_ID }">
										${allDISEASE.CODE_NAME}&nbsp;
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td align="center" style="color: #666666;">
					<select name="DISEASETYPE">
						<c:forEach items="${DISEASE_TYPE_INFORMATION}" var="allDISEASETYPE" varStatus="k">
							<c:choose>
								<c:when test="${allDISEASETYPE.CODE_ID == diseaseTypeId}">
									<option value="${diseaseTypeId}" selected>${diseaseTypeName}</option>
								</c:when>
								<c:otherwise>
									<option value="${allDISEASETYPE.CODE_ID }">
										${allDISEASETYPE.CODE_NAME}&nbsp;
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<c:forEach var="i" begin="1" end="${10}"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30">&nbsp;</td>
						<td align="center" style="color: #666666;">&nbsp;</td>
						<td align="center" style="color: #666666;">&nbsp;</td>
					</tr>
				</c:forEach>
		</table>
		</form>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</body>
</html>