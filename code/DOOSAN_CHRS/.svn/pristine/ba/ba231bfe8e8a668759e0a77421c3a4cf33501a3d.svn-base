<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.util.*"%>
<jsp:useBean id="allWasteInformationForReport"
	class="java.util.ArrayList" scope="request" />
<jsp:useBean id="wasteTypeList" class="java.util.ArrayList"
	scope="request" />
<jsp:useBean id="allCompanyCustomers" class="java.util.ArrayList"
	scope="request" />
<html>
	<head>
	<!-- 2gm_waste_report.jsp -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>

		<%
			response.setHeader("Content-Type",
					"application/vnd.ms-excel; charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=WasteSalesRecord.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=0");
		%>
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0" align="center">

			<tr align="center" height="30">
				<td align="center">
					<table width="100%" align="center">
						<tr align="center">
							<td align="center" colspan="13">
								<b><font size="+2">${StartTime}至${EndTime}&nbsp;&nbsp;废品销售信息</font>
								</b>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td>
					<table width="100%" border="1" cellspacing="0" cellpadding="0"
						bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
						<tr align="center" bgcolor="#F5F5F5">
						<tr>
							<td nowrap="nowrap" class="info_title_01">
								编号
							</td>
							<td nowrap="nowrap" class="info_title_01">
								登记日期
							</td>
							<td nowrap="nowrap" class="info_title_01">
								废品名称
							</td>
							<td nowrap="nowrap" class="info_title_01">
								单位
							</td>
							<td nowrap="nowrap" class="info_title_01">
								单价
							</td>
							<td nowrap="nowrap" class="info_title_01">
								数量
							</td>
							<td nowrap="nowrap" class="info_title_01">
								金额
							</td>
							<td nowrap="nowrap" class="info_title_01">
								客户公司名称
							</td>
							<td nowrap="nowrap" class="info_title_01">
								进账方式
							</td>
							<td nowrap="nowrap" class="info_title_01">
								搬出人员
							</td>
							<td nowrap="nowrap" class="info_title_01">
								车辆号
							</td>
							<td nowrap="nowrap" class="info_title_01">
								记录者
							</td>
							<td nowrap="nowrap" class="info_title_01">
								备注
							</td>
						</tr>
						<c:forEach items="${allWasteInformationForReport}" var="all"
							varStatus="i">
							<tr align="center">
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.WASTE_NO}
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.CREATE_DATE}
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.WASTE_TYPE}
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.WASTE_UNITS}
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.WASTE_UNITPRICE}
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.WASTE_NUMBER}
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.WASTE_TOTAL}
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.COMPANY_CUSTOMERS}
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.REVENUE_APPROACH }
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.OUT_STAFF }
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.CAR_NO }
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.CREATE_BY }
								</td>
								<td nowrap="nowrap" align="center" class="info_content_01">
									${all.REMARK}
								</td>
							</tr>
						</c:forEach>
					</table>
			</td>
			</tr>
		</table>
	</body>
</html>