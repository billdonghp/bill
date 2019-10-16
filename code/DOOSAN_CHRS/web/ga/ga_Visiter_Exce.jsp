<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="affirmorList" class="java.util.ArrayList"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参观者情况报表</title>
</head>
<%
	response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=visiterReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<body>

<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">
	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="13"><b><font size="+2">参观者情况报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap">
				编号</td>
		      <td nowrap="nowrap">
				申请人</td>
			  <td nowrap="nowrap">
				访问日期</td>
			  <td nowrap="nowrap">
				到达时间</td>
			  <td nowrap="nowrap">
				离开时间</td>
		      <td nowrap="nowrap">
				访问单位</td>
			  <td nowrap="nowrap" >
				访问地点</td>
			  <td nowrap="nowrap" >
				访问目的</td>
			  <td nowrap="nowrap">
				来访人数</td>
			  <td nowrap="nowrap">
				满意度</td>				
			  <td nowrap="nowrap">
				决裁者</td>				
		    </tr>			
		 <c:forEach items="${checkList}" var="test" varStatus="i">
		      <tr align="center">
		       <td nowrap="nowrap" align="center">${test.GA_VISITER_APPLY_ID}</td>
		      <td nowrap="nowrap" align="center">${test.PLAY_APPLYCHINESENAME}</td>
		      <td nowrap="nowrap"  align="center">${test.VISITER_DATE}</td>
		      <td nowrap="nowrap"  align="center">${test.VISITER_COME_TIME}</td>
		      <td nowrap="nowrap" align="center">${test.VISITER_END_TIME}</td>
		      <td nowrap="nowrap"  align="center">${test.VISITER_COMPANY}</td>
		      <td nowrap="nowrap"  align="center">${test.VISIT_PLACE}</td>
		      <td nowrap="nowrap"  align="center">${test.VISITER_OBJECTIVE}</td>
		      <td nowrap="nowrap" align="center">${test.VISIT_COUNT}</td>
		      <td nowrap="nowrap"  align="center">${test.MANY}&nbsp;</td>
		      
		      <td nowrap="nowrap" align="center"><c:forEach
						items="${test.allVisiterApproval}" var="view" varStatus="j">
				      	${view.AFFIRM_LEVEL}${view.CHINESENAME}
				      	<c:if test="${view.AFFIRM_FLAG==0}">
							<font >未通过</font>
						</c:if>
						<c:if test="${view.AFFIRM_FLAG==1}">
							<font >通过</font>
						</c:if>
						<c:if test="${view.AFFIRM_FLAG==2}">
							<font >已否决</font>
						</c:if>
						<br>
					</c:forEach>
					</td>
		      
		      
			 </tr>
		    </c:forEach>
		 </table>
		</td>
	</tr>
</table>
</body>
</html>