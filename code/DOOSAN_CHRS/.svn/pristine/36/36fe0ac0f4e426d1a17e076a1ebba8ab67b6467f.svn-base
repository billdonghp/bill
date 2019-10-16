<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="affirmorList" class="java.util.ArrayList"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>派车情况报表</title>
</head>
<%
	response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=派车情况.xls");
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
				<td align="center" colspan="13"><b><font size="+2">派车情况报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		     <td nowrap="nowrap" class="info_title_01">
				申请人</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请部门</td>
			<td nowrap="nowrap" class="info_title_01">
				申请日期</td>
			<td nowrap="nowrap" class="info_title_01">
				乘用人</td>
			<td nowrap="nowrap" class="info_title_01">
				乘车人数</td>	
		    <td nowrap="nowrap" class="info_title_01">
				接送信息</td>
		    <td nowrap="nowrap" class="info_title_01">
				车辆名称</td>
		    <td nowrap="nowrap" class="info_title_01">
				司机</td>
		    <td nowrap="nowrap" class="info_title_01">
				联系方式</td>				
		    </tr>			
		 <c:forEach items="${voitureListList}" var="list" varStatus="i">
		      <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >
    			${list.CHINESENAME }&nbsp;</td>
   			<td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.APPLYER_DEPTNAME }&nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.CREATE_DATE }&nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.LARDER } &nbsp;</td>
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.APPLY_USERSCOUNT } &nbsp;</td>
   			 
   			 <td nowrap="nowrap" align="center" class="info_content_01" >
   			 <c:forEach items="${list.voitureDetailList}" var="detail" varStatus="i">
   			 区分:<c:if test="${detail.DISTINCTION==1}"><span id='jie' >接</span></c:if>
   			 <c:if test="${detail.DISTINCTION==2}"><span id='song'>送</span></c:if>
   			 &nbsp;&nbsp;使用时间:${detail.APPLY_DATE }&nbsp;${detail.APPLY_STARTTIME }
   			 &nbsp;&nbsp;业务内容:${detail.CONTENT }&nbsp;&nbsp;乘车路线:${detail.DRIVE_WAY }
   			 <c:if test="${detail.NOTE!=NULL}">&nbsp;&nbsp;备注:${detail.NOTE }</c:if>
   			 <br />
   			 </c:forEach>
   			  &nbsp;</td>
   			   <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.VOITURE_BRAND } &nbsp;</td>
   			   <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.carStr } &nbsp;</td>
   			   <td nowrap="nowrap" align="center" class="info_content_01" >
   			 ${list.DRIVER_PHONE } &nbsp;</td>
		      
		      
			 </tr>
		    </c:forEach>
		 </table>
		</td>
	</tr>
</table>
</body>
</html>