<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>

<html>
<head>

<title>菜谱制作</title>
</head>

<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=MenuReport.xls");
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
				<td align="center" colspan="7"><b><font size="+2">菜谱制作报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>	

		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      
		      
		      <td class="info_title_01" nowrap>日期</td>
		      <td class="info_title_01" nowrap>餐次</td>
		      <td class="info_title_01" nowrap>菜品代码</td>
		      <td class="info_title_01" nowrap>菜品名称</td>
		      <td class="info_title_01" nowrap>原料构成</td>
		      <td class="info_title_01" nowrap>制作方法</td>
		      <td class="info_title_01" nowrap>菜品制作担当</td>
		      
		    </tr>
		<c:forEach items="${gmMenuViewList}" var="oneResult" varStatus="i">
		    <tr align="center">
			      <td align="center" >${oneResult.MENU_DATE}&nbsp;</td>
			      <td align="center" >${oneResult.MENU_EATTYPE_NAME}&nbsp;</td>
			      <td align="center" >${oneResult.VEGET_ID}&nbsp;</td>
			      <td align="center" >${oneResult.VEGET_NAME}&nbsp;</td>
			      <td align="center" >${oneResult.MATERIAL}&nbsp;</td>
			      <td align="center" >${oneResult.METHOD}&nbsp;</td>
		      	  <td align="center" >${oneResult.MAKER}&nbsp;</td>
		      	
		    </tr>
	

		    </c:forEach>
		  

		
		</td>
		
</tr>
	
</table>
</body>
</html>