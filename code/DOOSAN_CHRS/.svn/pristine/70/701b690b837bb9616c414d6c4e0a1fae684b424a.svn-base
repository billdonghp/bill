<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>

<html>
<head>

<title>菜品意见</title>
</head>

<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=VegetableOpinionReport.xls");
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
				<td align="center" colspan="16"><b><font size="+2">菜品意见报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>	

		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      
		      
		     <td class="info_title_01" nowrap>填写日期</td>
		      <td class="info_title_01" nowrap>就餐日期</td>
		      <td class="info_title_01" nowrap>餐次</td>
		      <td class="info_title_01" nowrap>菜品名称</td>
		      <td class="info_title_01" nowrap>原料构成</td>
		      <td class="info_title_01" nowrap>制作方法</td>
		      <td class="info_title_01" nowrap>开发担当</td>
		      <td class="info_title_01" nowrap>卫生</td>
		      <td class="info_title_01" nowrap>新鲜度</td>
		      <td class="info_title_01" nowrap>口味</td>
		      <td class="info_title_01" nowrap>制作方法</td>
		      <td class="info_title_01" nowrap>营养</td>
		      <td class="info_title_01" nowrap>其他</td>
		      <td class="info_title_01" nowrap>综合评分</td>
		       <td class="info_title_01" nowrap>填写人</td>
		       <td class="info_title_01" nowrap>所属部门</td>
		      
		    </tr>
		<c:forEach items="${resultList}" var="oneResult" varStatus="i">
		    <tr align="center">
			      <td align="center" >${oneResult.FILL_DATE}&nbsp;</td>
			      <td align="center" >${oneResult.REPAST_DATE}&nbsp;</td>
			      <td align="center" >${oneResult.MENU_EATTYPE_NAME}&nbsp;</td>
			      <td align="center" >${oneResult.VEGET_NAME}&nbsp;</td>
			      <td align="center" >${oneResult.MATERIAL}&nbsp;</td>
			      <td align="center" >${oneResult.METHOD}&nbsp;</td>
			      <td align="center" >${oneResult.MAKER}&nbsp;</td>
			       <td align="center" >${oneResult.SANITATION}&nbsp;</td>
			      <td align="center" >${oneResult.GREENNESS}&nbsp;</td>
			      <td align="center" >${oneResult.TASTE}&nbsp;</td>
			      <td align="center" >${oneResult.METHODNAME}&nbsp;</td>
			      <td align="center" >${oneResult.ALIMENTATION}&nbsp;</td>
			      <td align="center" >${oneResult.OTHER}&nbsp;</td>
			       <td align="center" >${oneResult.GRADE}&nbsp;</td>
			      <td align="center" >${oneResult.CHINESENAME}&nbsp;</td>
			      <td align="center" >${oneResult.DEPTNAME}&nbsp;</td>
		      	
		    </tr>
	

		    </c:forEach>
		  

		
		</td>
		
</tr>
	
</table>
</body>
</html>