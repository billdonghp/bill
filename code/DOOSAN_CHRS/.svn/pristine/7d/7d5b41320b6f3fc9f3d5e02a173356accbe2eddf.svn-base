<%@ page language="java"
	contentType="application/vnd.ms-excel; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<%
	response.setHeader("Content-disposition",
			"attachment;filename=EATERY_EXCEPTION_REPORT.xls");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="application/vnd.ms-excel; charset=UTF-8">
	</head>
	<body>



<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">	
<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="6"><b><font size="+2">就餐异常查看报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>	

		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      
		      
		     <td class="info_title_01" nowrap>部门</td>
		      <td class="info_title_01" nowrap>姓名</td>
		      <td class="info_title_01" nowrap>职号</td>
		      <td class="info_title_01" nowrap>餐别</td>
		      <td class="info_title_01" nowrap>就餐时间</td>
		      <td class="info_title_01" nowrap>考勤时间</td>
		      
		    </tr>
		<c:forEach items="${resultList}" var="oneResult" varStatus="i">
		    <tr align="center">
			      <td align="center" >${oneResult.DEPTNAME}&nbsp;</td>
			      <td align="center" >${oneResult.CHINESENAME}&nbsp;</td>
			      <td align="center" >${oneResult.EMPID}&nbsp;</td>
			      <td align="center" >${oneResult.EATERYTYPE}&nbsp;</td>
			      <td align="center" >${oneResult.EATERYTIME}&nbsp;</td>
			      <td align="center" >${oneResult.MACTIME}&nbsp;</td>
		    </tr>
	

		    </c:forEach>
		  

		
		</td>
		
</tr>
	
</table>
</body>
</html>