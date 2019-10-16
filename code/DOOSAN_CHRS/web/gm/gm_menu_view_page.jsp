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
	"attachment; filename=MenuPrint.xls");
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
				<td align="center" colspan="5" rowspan="2"><b><font size="+2">食堂菜谱(${Date})</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>	

		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" >
		    
		 <tr align="center" bgcolor="#F5F5F5">
		    <td align="center" colspan="5"> <b><font size="+1">早    餐</font></b></td>
		 </tr>
		  <tr align="center" bgcolor="#F5F5F5">
		      <td  width="30%">菜品代码</td>
		      <td  width="30%">菜品名称</td>
		      <td  width="30%">原料构成</td>
		      <td  width="30%">制作方法</td>
		      <td  width="30%">菜品制作担当</td>   
		  </tr>
		<c:forEach items="${gmMenuViewList}" var="oneResult" varStatus="i">
		<c:if test="${oneResult.MENU_EATTYPE == 'RepastType05'}">
		    <tr align="center">
			      <td  width="30%">${oneResult.VEGET_ID}&nbsp;</td>
			      <td  width="30%">${oneResult.VEGET_NAME}&nbsp;</td>
			      <td  width="30%">${oneResult.MATERIAL}&nbsp;</td>
			      <td  width="30%">${oneResult.METHOD}&nbsp;</td>
		      	  <td  width="30%">${oneResult.MAKER}&nbsp;</td>
		    </tr>
	     </c:if>

		 </c:forEach>
		 
		  <tr align="center" bgcolor="#F5F5F5">
		    <td align="center" colspan="5"> <b><font size="+1">午    餐</font></b></td>
		 </tr>
		<c:forEach items="${gmMenuViewList}" var="oneResult" varStatus="i">
		<c:if test="${oneResult.MENU_EATTYPE == 'RepastType15'}">
		    <tr align="center">
			      <td width="30%" >${oneResult.VEGET_ID}&nbsp;</td>
			      <td width="30%" >${oneResult.VEGET_NAME}&nbsp;</td>
			      <td width="30%" >${oneResult.MATERIAL}&nbsp;</td>
			      <td width="30%" >${oneResult.METHOD}&nbsp;</td>
		      	  <td width="30%" >${oneResult.MAKER}&nbsp;</td>
		    </tr>
	     </c:if>

		 </c:forEach>
		 
		 
		  <tr align="center" bgcolor="#F5F5F5">
		    <td align="center" colspan="5"> <b><font size="+1">晚    餐</font></b></td>
		 </tr>
		<c:forEach items="${gmMenuViewList}" var="oneResult" varStatus="i">
		<c:if test="${oneResult.MENU_EATTYPE == 'RepastType20'}">
		     <tr align="center">
			      <td width="30%" >${oneResult.VEGET_ID}&nbsp;</td>
			      <td width="30%" >${oneResult.VEGET_NAME}&nbsp;</td>
			      <td width="30%" >${oneResult.MATERIAL}&nbsp;</td>
			      <td width="30%" >${oneResult.METHOD}&nbsp;</td>
		      	  <td width="30%" >${oneResult.MAKER}&nbsp;</td>
		    </tr>
	     </c:if>

		 </c:forEach>
		 
		 
		  <tr align="center" bgcolor="#F5F5F5">
		    <td align="center" colspan="5"> <b><font size="+1">夜    餐</font></b></td>
		 </tr>
		<c:forEach items="${gmMenuViewList}" var="oneResult" varStatus="i">
		<c:if test="${oneResult.MENU_EATTYPE == 'RepastType30'}">
		     <tr align="center">
			      <td width="30%" >${oneResult.VEGET_ID}&nbsp;</td>
			      <td width="30%" >${oneResult.VEGET_NAME}&nbsp;</td>
			      <td width="30%" >${oneResult.MATERIAL}&nbsp;</td>
			      <td width="30%" >${oneResult.METHOD}&nbsp;</td>
		      	  <td width="30%" >${oneResult.MAKER}&nbsp;</td>
		    </tr>
	     </c:if>

		 </c:forEach>
		  

		
		</td>
		
</tr>
	
</table>
</body>
</html>