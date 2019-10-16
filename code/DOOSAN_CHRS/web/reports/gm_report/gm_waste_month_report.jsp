<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<jsp:useBean id="aa" class="java.lang.String" scope="request" />
<%@ page import="com.ait.util.*"%>
<jsp:useBean id="allInformation" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="total" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=wasteMonth.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="50%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="50%" align="center">
			<tr align="center">
				<td align="center" colspan="7"><b><font size="+2">${StartTime}日&nbsp;至&nbsp;${EndTime}日&nbsp;&nbsp;月别结算统计报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
			   <td align="center" nowrap>
			       废品名称
			    </td>
			    <td align="center" nowrap>
			       客户单位
			    </td>
			    <td align="center" nowrap>
			       单位
			    </td>
			    <td align="center" nowrap>
			       单价
			    </td>
			    <td align="center" nowrap>
			       数量
			    </td>
			    <td align="center" nowrap>
			       销售金额（元）
			    </td>
			    <td align="center" nowrap>
			       登记日期
			    </td>
			  </tr>
			  <c:forEach items="${allInformation}" var="all" varStatus="i">
				   <tr align="center">
				 	<td align="center">
				    	${all.WASTE_TYPE}&nbsp;
				    </td>
				    <td align="center">
				    	${all.COMPANY_CUSTOMERS1 }&nbsp;
				    </td>
				    <td align="center">
				    	${all.WASTE_UNITS}&nbsp;
				    </td>
				    <td align="center">
				    	${all.WASTE_UNITPRICE}&nbsp;
				    </td>
				    <td align="center">
				      	${all.WASTE_NUMBER}&nbsp;
				    </td>
				    <td align="center">
				      	${all.WASTE_TOTAL}&nbsp;
				    </td>
				    <td align="center">
				      	 ${all.CREATE_DATE }&nbsp;
				    </td>
		  </tr>
			   </c:forEach>
			  <tr>
			  	<td align="center" style="width: 5px">
			  		合计
			  	</td>
			  	<td align="center" style="width: 5px">
			  		&nbsp;
			  	</td>
			  	<td align="center" style="width: 5px">
			  		&nbsp;
			  	</td>
			  	<td align="center" style="width: 5px">
			  		&nbsp;
			  	</td>
			  	<td align="center" style="width: 5px">
			  		&nbsp;
			  	</td>
			  	<td align="center" style="width: 5px">
			  		${total}
			  	</td>
			  	<td align="center" style="width: 5px">
			  		&nbsp;
			  	</td>
			  </tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>