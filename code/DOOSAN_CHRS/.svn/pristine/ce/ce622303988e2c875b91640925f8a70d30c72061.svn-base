<%@ page language="java"
	contentType="application/vnd.ms-excel; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<%
	response.setHeader("Content-disposition",
			"attachment;filename=WASH_APPLY_INFO.xls");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="application/vnd.ms-excel; charset=UTF-8">
	</head>
	<body>


		<table width="100%" style="border-collapse: collapse" border="1"
			height="60" cellpadding="5">
			<tr align="center" height="30">
				<td align="center">
					<table width="100%" align="center">
						<tr align="center">
							<td align="center" colspan="9">
								<b><font size="+2"> 洗衣情况报表</font>
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
						<tr>
							<td width="3%" align="center">
								职号
							</td>
							<td width="3%" align="center">
								姓名
							</td>
							<td width="3%" align="center">
								部门
							</td>
							<td width="3%" align="center">
								名称
							</td>
							<td width="3%" align="center">
								数量
							</td>
							<td width="3%" align="center">
								单价
							</td>
							<td width="3%" align="center">
								价格
							</td>
							<td width="3%" align="center">
								洗衣人员明细
							</td>
							<td width="3%" align="center">
								备注
							</td>
							
						</tr>




						<c:forEach items="${applyExcelList}" var="list" varStatus="i">
							<tr align="center">
								<td align="center" height="30">
									${list.EMPID }
								</td>
								<td align="center">
									${list.CHINESENAME}
								</td>
								<td align="center">
									${list.DEPT_NAME}&nbsp;
								</td>
								<td align="center">
									${list.WASH_NAME}&nbsp;
								</td>
								<td align="center">
									${list.QUENTITY}${list.UNIT_NAME}&nbsp;
								</td>
								<td align="center">
									${list.UNIT_PRICE}&nbsp;
								</td>
								<td align="center">
									${list.AMOUNT}&nbsp;
								</td>
								
								<td align="center">
									${list.REMARK}&nbsp;
								</td>
								
								
								<td align="center">
									${list.REMARK1}&nbsp;
								</td>
								
								




							</tr>
						</c:forEach>
						

					</table>
					</td>

				</tr>
		</table>
	</body>
</html>
