<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=ExpressManagerReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>

<body>
<form name="ApplyForm" method="post" action="">
<input name="listSize" value="${fn:length(arrangementList)}" type="hidden">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="10"><b><font size="+2" style="FONT-FAMILY:宋体;font-size:14pt;">就餐确认</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<tr>
	  <td nowrap>职号</td>
	  <td nowrap>姓名</td>
	  <td nowrap>课组</td>
	  <td nowrap>部门</td>
	  <td nowrap>就餐日期</td>
	  <td nowrap>餐别</td>
	  <td nowrap>餐次</td>
	  <td nowrap>班车</td>
	  <td nowrap>申请时间</td>
	  <td nowrap>状态</td>
	  <td nowrap>总务状态</td>
	</tr>
	<c:forEach items="${list}" var="list" varStatus="i">
	<tr>
	  <td nowrap>${list.EMPID }</td>
	  <td nowrap>${list.CHINESENAME }</td>
	  <td nowrap>${list.DEPTNAME }</td>
	  <td nowrap>${list.DEPTNAME1 }</td>
	  <td nowrap>
		${list.OTDATE}&nbsp;</td>
	  <td nowrap>
	  ${list.CODE_NAME }&nbsp;</td>
	  <td nowrap>
		${list.eatType}
		&nbsp;

	</td>	
	  <td nowrap>
		${list.LINE_NAME }&nbsp;
	  </td>
	  <td nowrap>
		${list.APPLYDATE}&nbsp;
	  </td>
	  <td nowrap>&nbsp;
		<c:if test="${list.CONFIRM == 0}">未确认</font></c:if> 
		<c:if test="${list.CONFIRM == 1}">已确认</font></c:if>
	  </td>
	  <td nowrap>&nbsp;
		<c:if test="${list.FALG == 0}">总务未确认</font></c:if> 
		<c:if test="${list.FALG == 1}">总务已确认</font></c:if>
	  </td>
	</tr>
	</c:forEach>
	 
	</table>
		            
</form>
</body>
</html>
