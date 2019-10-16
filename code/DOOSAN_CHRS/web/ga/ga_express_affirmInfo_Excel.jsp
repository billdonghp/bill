<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap,java.util.*,com.ait.ga.cmd.visit.*,com.ait.ga.servlet.ExpressApplyAndAffirmCommand,com.ait.ga.bean.GaAffirmList" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"></jsp:useBean>
<jsp:useBean id="expressAffirmList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="eaac" class="com.ait.ga.servlet.ExpressApplyAndAffirmCommand" scope="request"></jsp:useBean>
<jsp:useBean id="wpListAffirm" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="wpAffirm" class="com.ait.ga.bean.GaAffirmList"></jsp:useBean>
<jsp:useBean id="dataMapList" class="com.ait.sqlmap.util.SimpleMap" scope="request"></jsp:useBean>
<jsp:useBean id="applyList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="affirmorList" class="java.util.ArrayList"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>快件决裁情况报表</title>
</head>

<%
	response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=expreeInfo.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>

<body>
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				快件情况
				</td>
			</tr>     
		</table>
		
	<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap" class="info_title_01">
				发件人职号</td>
		      <td nowrap="nowrap" class="info_title_01">
				发件人</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请人部门</td>
		      <td nowrap="nowrap" class="info_title_01">
				申请人职号</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请人</td>	
			 <td nowrap="nowrap" class="info_title_01">
				申请日期</td>
		      <td nowrap="nowrap" class="info_title_01">
				快件类型</td>	
			  <td nowrap="nowrap" class="info_title_01">
				快递单号</td>
			 <td nowrap="nowrap" class="info_title_01">
				发件城市</td>	           
		      <td nowrap="nowrap" class="info_title_01">
				寄达城市</td>	
			  <td nowrap="nowrap" class="info_title_01">
				收件单位</td>	
			  <td nowrap="nowrap" class="info_title_01">
				快件内容</td>	
				<td nowrap="nowrap" class="info_title_01">
				发件原因</td>		
			  <td nowrap="nowrap" class="info_title_01">
				收件人</td>
			<td nowrap="nowrap" class="info_title_01">
				发送情况</td>					
		      
		    </tr>
		  <c:forEach items="${expressAffirmList}" var="list" varStatus="i">
		    <tr align="center">
		     <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${list.EMPID2 }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${list.SENDPERSONNAME }</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">&nbsp;${list.DEPTNAME }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${list.EMPID1 }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${list.APPLYEMPNAME }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${list.APPLYDATE }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${list.CODE_NAME }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${list.POSTNUMBER }</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">&nbsp;${list.CITYSENDTO }</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">&nbsp;${list.CITYARRIVE }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${list.POSTADDRESS }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${list.POSTDESCRIPTION }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${list.CAUSE}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${list.SENDTOPERSON }</td>  
		      
		     <td nowrap="nowrap" align="center" class="info_content_01">
		     <c:if test="${list.ISSEND == '1'}"><font color="#00CC00">已发送</font></c:if>
		     <c:if test="${list.ISSEND == '0'}"><font color="#990099">未发送</font></c:if></td>	        
			 </tr>	
		 </c:forEach>
		 </table>
		 		 		
</html>
