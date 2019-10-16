<%@ page language="java"
	contentType="application/vnd.ms-excel; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<%
	response.setHeader("Content-disposition",
			"attachment;filename=BUS_ARRANGE_REPORT.xls");
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
							<td align="center" colspan="12">
								<b><font size="+2">班车安排报表</font>
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
								代请人
							</td>
							<td width="3%" align="center">
								区域
							</td>
							<td width="3%" align="center">
								日期
							</td>
							<td width="3%" align="center">
								时间
							</td>
							<td width="3%" align="center">
								运营班车
							</td>
							<td width="3%" align="center">
								座位数
							</td>
							<td width="3%" align="center">
								乘坐人数
							</td>
							<td width="3%" align="center">
								乘坐率
							</td>
							<td width="3%" align="center">
								到达日期
							</td>
							<td width="3%" align="center">
								到达时间
							</td>
							<td width="3%" align="center">
								到达情况
							</td>
							<td width="3%" align="center">
								备注
							</td>
						</tr>




						<c:forEach items="${busArrangeList}" var="list" varStatus="i">
							<tr align="center">
								<td align="center" height="30">
									${list.APPLYER_CHINESENAME }
								</td>
								<td align="center">
									${list.BUS_AREA}
								</td>
								<td align="center">
									${list.APPLY_STARTDATE}&nbsp;
								</td>
								<td align="center">
									${list.APPLY_STARTTIME}&nbsp;
								</td>
								<td align="center">
									${list.BUS}&nbsp;
								</td>
								<td align="center">
									${list.SEATNUM}&nbsp;
								</td>
								<td align="center">
									${list.RIDENUM}&nbsp;
								</td>
								<td align="center">
									<fmt:formatNumber value="${list.RIDEPERCENT }" type="percent" />
								</td>
								<td align="center">
									${list.ARRIVE_DATE}&nbsp;
								</td>
								<td align="center">
								    ${list.ARRIVE_TIME }&nbsp;
								</td>
								<td align="center">
							   			 <c:if test="${list.ARRIVE_TIME_FLAG==1}">
							   			   <font >异常</font>
							   			   
							   			  </c:if>
							   			  <c:if test="${list.ARRIVE_TIME_FLAG==0}">
							   			  <font >正常</font>
							   			  </c:if>
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
