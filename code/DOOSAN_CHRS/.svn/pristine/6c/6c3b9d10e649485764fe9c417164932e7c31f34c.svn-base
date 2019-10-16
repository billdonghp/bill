<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="affirmorList" class="java.util.ArrayList"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维修情况报表</title>
</head>
<%
	response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=facilityReport.xls");
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
				<td align="center" colspan="13"><b><font size="+2">维修情况报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap">
				申请人</td>
			  <td nowrap="nowrap">
				申请部门</td>
			  <td nowrap="nowrap">
				分类</td>
		      <td nowrap="nowrap">
				位置</td>
			  <td nowrap="nowrap" >
				故障时间</td>
			  <td nowrap="nowrap" >
				故障详情</td>
			  <td nowrap="nowrap">
				要求维修内容</td>
		      <td nowrap="nowrap">
				整改结果及情况</td>
			  <td nowrap="nowrap">
				满意度</td>				
			  <td nowrap="nowrap">
				决裁者</td>				
		    </tr>			
		 <c:forEach items="${checkList}" var="test" varStatus="i">
		      <tr align="center">
		      <td nowrap="nowrap" align="center">${test.CHINESENAME}</td>
		      <td nowrap="nowrap"  align="center">${test.DEPTNAME}</td>
		      <td nowrap="nowrap" align="center">${test.CLASSIFICATION}</td>
		      <td nowrap="nowrap"  align="center">${test.PLACE}</td>
		      <td nowrap="nowrap"  align="center">${test.COMPLETIONDATE}</td>
		      <td nowrap="nowrap"  align="center">${test.DETAILSA}</td>
		      <td nowrap="nowrap" align="center">${test.CRFNOTE}&nbsp;</td>
		      <td nowrap="nowrap" align="center">${test.ZHENGAIJIEGU}</td>
		      <td nowrap="nowrap"  align="center">${test.MANY}&nbsp;</td>
		      
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
					<font color="#FF0000">维修担当  : ${test.WEIDAN}</font>
					</td>
		      
		      
			 </tr>
		    </c:forEach>
		 </table>
		</td>
	</tr>
</table>
</body>
</html>