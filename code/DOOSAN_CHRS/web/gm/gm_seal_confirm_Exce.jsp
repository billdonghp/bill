<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公章确认报表</title>
</head>
</SCRIPT>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=SealReport.xls");
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
				<td align="center" colspan="11"><b><font size="+2">公章确认报表</font></b></td>
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
				姓名</td>
			  <td nowrap="nowrap">
				部门</td>
		      <td nowrap="nowrap">
				日期</td>
			  <td nowrap="nowrap" >
				起案号</td>
			  <td nowrap="nowrap">
				使用章</td>
		      <td nowrap="nowrap">
				盖章文件的接收单位</td>
			  <td nowrap="nowrap">
				使用内容</td>	
		      <td nowrap="nowrap">
				份数 </td>
			  <td nowrap="nowrap">
				归还日期 </td>					 	    
			  <td nowrap="nowrap" align="center">
				备注</td>
		    </tr>			
		 <c:forEach items="${applyList}" var="varTest" varStatus="i">
		      <tr align="center">
		      <td nowrap="nowrap" align="center">${varTest.DOCUMENTNO}</td>
		      <td nowrap="nowrap"  align="center">${varTest.CHINESENAME}</td>
		      <td nowrap="nowrap" align="center">${varTest.APPLYDEPTNAME}</td>
		      <td nowrap="nowrap"  align="center">${varTest.USEDATE}</td>
		      <td nowrap="nowrap"  align="center">${varTest.DRAFT_NO}</td>
		      <td nowrap="nowrap" align="center">${varTest.CODE_NAME}</td>
		      <td nowrap="nowrap" align="center">${varTest.DEPTNAME}</td>
		      <td nowrap="nowrap"  align="center">${varTest.USEINFORMATION}</td>
		      <td nowrap="nowrap"  align="center">${varTest.USESHARES}</td>
		      <td nowrap="nowrap" align="center">${varTest.RETURNDATE} </td>
		      <td nowrap="nowrap" align="center">${varTest.NOTE} </td>
		    </c:forEach>
		 </table>
		 <tr align="center"> 
			 </tr>
		</td>
	</tr>
</table>
</body>
</html>