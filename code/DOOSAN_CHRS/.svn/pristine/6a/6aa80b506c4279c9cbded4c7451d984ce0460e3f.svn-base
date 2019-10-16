<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="visiterViewDetail" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=VisiterInfoReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">
	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="9"><b><font size="+2">来访人员信息统计报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>

		   <table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">编号&nbsp; </td>
				<td nowrap="nowrap" class="info_title_01">姓名&nbsp; </td>
				<td nowrap="nowrap" class="info_title_01">职务&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">工作单位&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">区分&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">联系电话&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">礼品名称&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">礼品数量&nbsp;</td>
				<td nowrap="nowrap" class="info_title_01">备注&nbsp;</td>
			</tr>
			<c:forEach items="${visiterViewDetail1}" var="test" varStatus="j">
			<tr>			
			    <td align="center">&nbsp;${test.GA_VISITER_APPLY_ID }</td>
				<td align="center">&nbsp;${test.VIST_NAME }</td>
				<td align="center">&nbsp;${test.JOBS }</td>
				<td align="center">&nbsp;${test.CORPORATION }</td>
					
				<td align="center">&nbsp;${test.DIFFER }</td>
				
				<td align="center">&nbsp;${test.TELEPHONE }</td>
					
				<td align="center">&nbsp;${test.PRESENT }</td>
				<td align="center">&nbsp;${test.PRESENT_NUM }</td>
				<td align="center">&nbsp;${test.MEMO }</td>
			</tr>
			</c:forEach>
	  <tr align="center"> 
			 </tr>
		 </table>
		</td>
	</tr>
</table>
</body>
</html>