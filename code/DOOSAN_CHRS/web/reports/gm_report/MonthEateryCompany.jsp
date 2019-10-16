<%@ page contentType="text/html; charset=UTF-8" import =" com.ait.sqlmap.util.SimpleMap, com.ait.gm.dao.GMDAO"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.util.*"%>
<jsp:useBean id="company" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=MonthEateryPeopleReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center" style="font-family: 宋体;">
			<tr align="center">
				<td align="center" colspan="10"><b><font size="15pt"> 公司<%=request.getParameter("area")%>月就餐人数统计;</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<%for(int i=0;i<company.size();i++){ 
dataMap=(SimpleMap)company.get(i);
%>
<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px; font-family: 宋体;">

	<tr>
	<td colspan="2"  align="center">区分</td>
	<td colspan="2" align="center">就餐人数</td>
	<td colspan="2" align="center">标准（元/人）</td>
	<td colspan="2" align="center">金额（元）</td>
	<td colspan="2" align="center">备注</td>
	</tr>
	<tr>
	<td colspan="2" align="center">早餐</td>
	<td colspan="2" align="center"><%=dataMap.get("早餐就餐人数")%></td>
	<td colspan="2" align="center"><%=dataMap.get("早餐单价")%></td>
	<td colspan="2" align="center"><%=dataMap.get("早餐金额")%></td>
	<td colspan="2" align="center">&nbsp;</td>
	</tr>
	<tr>
	<td colspan="2" align="center">午餐</td>
	<td colspan="2" align="center"><%=dataMap.get("午餐就餐人数")%></td>
	<td colspan="2" align="center"><%=dataMap.get("午餐单价")%></td>
	<td colspan="2" align="center" ><%=dataMap.get("午餐金额")%></td>
	<td colspan="2" align="center">&nbsp;</td>
	</tr>
	<tr>
	<td colspan="2" align="center">晚餐</td>
	<td colspan="2" align="center"><%=dataMap.get("晚餐就餐人数")%></td>
	<td colspan="2" align="center"><%=dataMap.get("晚餐单价")%></td>
	<td colspan="2" align="center"><%=dataMap.get("晚餐金额")%></td>
	<td colspan="2" align="center">&nbsp;</td>
	</tr>
	<tr>
	<td colspan="2" align="center">夜餐</td>
	<td colspan="2" align="center"><%=dataMap.get("夜餐就餐人数")%></td>
	<td colspan="2" align="center"><%=dataMap.get("夜餐单价")%></td>
	<td colspan="2" align="center"><%=dataMap.get("夜餐金额")%></td>
	<td colspan="2" align="center">&nbsp;</td>
	</tr>
	<tr>
	<td colspan="2" align="center">加餐</td>
	<td colspan="2" align="center"><%=dataMap.get("加餐就餐人数")%></td>
	<td colspan="2" align="center"><%=dataMap.get("加餐单价")%></td>
	<td colspan="2" align="center"><%=dataMap.get("加餐金额")%></td>
	<td colspan="2" align="center">&nbsp;</td>
	</tr>
	<tr>
	<td colspan="2" align="center">叉车加餐</td>
	<td colspan="2" align="center">&nbsp;</td>
	<td colspan="2" align="center">&nbsp;</td>
	<td colspan="2" align="center">&nbsp;</td>
	<td colspan="2" align="center">&nbsp;</td>
	</tr>
	<tr>
	<td colspan="2" align="center">叉车（三宇）</td>
	<td colspan="2" align="center">&nbsp;</td>
	<td colspan="2" align="center">&nbsp;</td>
	<td colspan="2" align="center">&nbsp;</td>
	<td colspan="2" align="center">&nbsp;</td>
	</tr>
	<tr>
	<td colspan="2" align="center">韩餐（主食、水果）</td>
	<td colspan="2" align="center">&nbsp;</td>
	<td colspan="2" align="center">&nbsp;</td>
	<td colspan="2" align="center">&nbsp;</td>
	<td colspan="2" align="center">&nbsp;</td>
	</tr>
	<tr>
	<td colspan="2" align="center">合计</td>
	<td colspan="2" align="center"><%=dataMap.get("就餐人数合计")%></td>
	<td colspan="2" align="center">&nbsp;</td>
	<td colspan="2" align="center"><%=dataMap.get("就餐金额合计")%></td>
	<td colspan="2" align="center">&nbsp;</td>
	</tr>
</table>

 
<%} %>
</body>
</html>