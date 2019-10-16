<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="affirmorList" class="java.util.ArrayList"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>签证情况报表</title>
</head>
<%
	response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=visaInfo.xls"); 
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
				<td align="center" colspan="13"><b><font size="+2">签证情况报表</font></b></td>
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
				出差日期（From）</td>
			<td nowrap="nowrap">
				出差日期（To）</td>
		      <td nowrap="nowrap">
				出差国家</td>
			  <td nowrap="nowrap" >
				签证类型</td>
			  <td nowrap="nowrap" >
				紧急度</td>			  
		      <td nowrap="nowrap">
				出差内容</td>	
			  <td nowrap="nowrap">
				备注</td>
			 <td nowrap="nowrap">
				综合满意度</td>
				 <td nowrap="nowrap">
				服务态度满意度</td>	
				 <td nowrap="nowrap">
				纳期遵守度</td>					
			  <td nowrap="nowrap">
				决裁者</td>				
		    </tr>			
		 <c:forEach items="${checkList}" var="test" varStatus="i">
		      <tr align="center">
		      <td nowrap="nowrap" align="center">${test.CHINESENAME}</td>
		      <td nowrap="nowrap"  align="center">${test.APPLYDEPTNAME}</td>
		      <td nowrap="nowrap" align="center">${test.USEFDATE}</td>
		      <td nowrap="nowrap" align="center">${test.USETDATE}</td>
		      <td nowrap="nowrap"  align="center">${test.SELECTDEPTID}</td>
		      <td nowrap="nowrap"  align="center">${test.USEVISATYPE}</td>
		      <td nowrap="nowrap" align="center">${test.ISLEND}&nbsp;</td>
		      <td nowrap="nowrap" align="center">${test.USEINFORMATION}</td>
		      <td nowrap="nowrap"  align="center">${test.NOTE}&nbsp;</td>
		      <td nowrap="nowrap"  align="center">${test.MANYZH}</td>
		      <td nowrap="nowrap"  align="center">${test.MANYFW}</td>
		      <td nowrap="nowrap"  align="center">${test.MANYNQ}</td>
		      
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