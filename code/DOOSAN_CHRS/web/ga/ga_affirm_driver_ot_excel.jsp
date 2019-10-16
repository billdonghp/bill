<%@ page language="java"
	contentType="application/vnd.ms-excel; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<%
	response.setHeader("Content-disposition",
			"attachment;filename=DRIVER_OT_REPORT.xls");
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
							<td align="center" colspan="11">
								<b><font size="+2">司机加班报表</font>
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
								代请人</td>
							<td width="3%" align="center">
								司机</td>
							<td width="3%" align="center">
								工作类型</td>
							 <td width="3%" align="center">
								加班类型</td>
							<td width="3%" align="center">
								开始日期</td>
						   <td width="3%" align="center">
								开始时间</td>
							 <td width="3%" align="center">
								结束日期</td>
						   <td width="3%" align="center">
								结束时间</td>
						    <td width="3%" align="center">
								加班原因</td>
						   <td width="3%" align="center">
								加班长度</td>
							 <td width="3%" align="center">
								备注</td>
			
							  
						</tr>




						<c:forEach items="${driverOtList}" var="list" varStatus="i">
							<tr align="center">
								<td align="center" height="30">
									${list.APPLYER_CHINESENAME }
								</td>
								<td align="center">
									${list.DRIVER_CHINESENAME}
								</td>
								<td align="center">
									${list.DRIVER_OT_TYPE_NAME}&nbsp;
								</td>
								<td align="center">
									${list.OT_TYPE_NAME}&nbsp;
								</td>
								<td align="center">
									${list.APPLY_STARTDATE}&nbsp;
								</td>
								<td align="center">
									${list.APPLY_STARTTIME}&nbsp;
								</td>
								<td align="center">
									${list.APPLY_ENDDATE}&nbsp;
								</td>
								
								<td align="center">
									${list.APPLY_ENDTIME}&nbsp;
								</td>
								<td align="center">
									${list.REASON}&nbsp;
								</td>
								<td align="center">
									${list.OT_LENGTH}&nbsp;
								</td>
								<td align="center">
									${list.REMARK}&nbsp;
								</td>
								




							</tr>
						</c:forEach>
						

					</table>
					</td>

				</tr>
		</table>
	</body>
</html>
