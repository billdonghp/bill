<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="affirmorList" class="java.util.ArrayList"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公章情况报表</title>
</head>
<%
	response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=sealInfo.xls");
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
				<td align="center" colspan="13"><b><font size="+2">公章情况报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap">
				姓名</td>
			  <td nowrap="nowrap">
				部门</td>
			  <td nowrap="nowrap">
				日期</td>
		      <td nowrap="nowrap">
				起案号</td>
			  <td nowrap="nowrap" >
				使用章</td>
			  <td nowrap="nowrap" >
				盖章文件的接收单位</td>
			  <td nowrap="nowrap">
				外借</td>
		      <td nowrap="nowrap">
				使用内容</td>
			  <td nowrap="nowrap">
				份数</td>				
			  <td nowrap="nowrap">
				归还日期</td>				
			  <td nowrap="nowrap">
				备注</td>				
			  <td nowrap="nowrap">
				决裁者</td>				
		    </tr>			
		 <c:forEach items="${checkList}" var="test" varStatus="i">
		      <tr align="center">
		      <td nowrap="nowrap" align="center">${test.CHINESENAME}</td>
		      <td nowrap="nowrap"  align="center">${test.APPLYDEPTNAME}</td>
		      <td nowrap="nowrap" align="center">${test.USEDATE}</td>
		      <td nowrap="nowrap"  align="center">${test.DRAFT_NO}</td>
		      <td nowrap="nowrap"  align="center">${test.USESEALTYPE}</td>
		      <td nowrap="nowrap"  align="center">${test.SELECTDEPTID}</td>
		      <td nowrap="nowrap" align="center">${test.ISLEND}&nbsp;</td>
		      <td nowrap="nowrap" align="center">${test.USEINFORMATION}</td>
		      <td nowrap="nowrap"  align="center">${test.USESHARES}&nbsp;</td>
		      <td nowrap="nowrap"  align="center">${test.RETURNDATE}&nbsp;</td>
		      <td nowrap="nowrap"  align="center">${test.NOTE}&nbsp;</td>
		      
		      <td nowrap="nowrap" align="center"><c:forEach
						items="${test.affirmorList}" var="view" varStatus="j">
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