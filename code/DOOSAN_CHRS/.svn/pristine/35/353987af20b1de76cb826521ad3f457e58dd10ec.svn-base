<%@ page contentType="text/html; charset=UTF-8" import =" com.ait.sqlmap.util.SimpleMap, com.ait.gm.dao.GMDAO"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.util.*"%>
<jsp:useBean id="allEateryType" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="allCardType" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="result" class="java.util.ArrayList" scope="request" />
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
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center" style="font-family: 宋体;">
			<tr align="center">
				<td align="center" colspan="23"><b><font size="15pt"> <%=request.getParameter("area")%>月 &nbsp;就餐消费月别结算表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px; font-family: 宋体;">
			<tr>
				<td align="center">日期</td>
				<c:forEach items="${allEateryType}" var="all" varStatus="i">
					<td colspan="5" align="center">${all.GM_TYPE}</td>
				</c:forEach>
				<td colspan="2" align="center">加餐</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<c:forEach items="${allEateryType}" var="all" varStatus="i">				  
					<td align="center">卡机</td>
					<td align="center">签字</td>
					<td align="center">合计</td>					
					<td align="center">通报</td>
					<td align="center">结账</td>
				</c:forEach>				
					<td align="center">加餐</td>
					<td align="center">VIP食堂</td>
			</tr>
			
			<%
				SimpleMap sm = null;
				SimpleMap sm1 = null;
				SimpleMap sm2 = null;
				String day = "";
				String hjc = "";
				String vipst = "";			
				for(int i=0 ; i<result.size() ; i++){
					sm = (SimpleMap) result.get(i);
					day = sm.getString("DAY");
					hjc = sm.getString("加餐");
					vipst = sm.getString("VIP食堂");					
			%>
			<tr>
					<td align="center"><%=day %></td>
			<%
				for(int j=0 ; j<allEateryType.size() ; j++){
					sm1 = (SimpleMap) allEateryType.get(j);
					String sk = sm.getString(sm1.getString("GM_TYPE")+"刷卡");
					String qz = sm.getString(sm1.getString("GM_TYPE")+"签字");
					String hj = sm.getString(sm1.getString("GM_TYPE")+"合计");
					String tb = sm.getString(sm1.getString("GM_TYPE")+"通报");
					String jz = sm.getString(sm1.getString("GM_TYPE")+"结账");
			%>
					<td align="center"><%=sk %></td>
					<td align="center"><%=qz %></td>
					<td align="center"><%=hj %></td>
					<td align="center"><%=tb %></td>					
					<td align="center"><%=jz %></td>
			<%
				}
			%>			<td align="center"><%=hjc %></td>
						<td align="center"><%=vipst %></td>
			</tr>
			<%
					}
			%>
		</table>
		</td>
	</tr>
</table>
</body>
</html>