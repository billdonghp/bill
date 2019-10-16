<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>Translate information</title>
</head>
<SCRIPT type="text/javascript">

</SCRIPT>
<body>
<form name="form1" method="post">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">
		
		<!-- display toolbar -->
		<%@ include file="./inc/hrtoolbar_null.jsp"%>
		
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info --> 
		<%@ include file="../hrm/hrm_view_basic.jsp"%> 
		
		<!-- display 3 level menu -->
		<%@ include file="./inc/hrm_view_toolbar.jsp"%>
		
		<br>
		<!-- display content -->
		<table width="100%" border="0" cellpadding="0">
			<tr>
				<td class="title1"><!-- 休职 -->
					<ait:message  messageID="display.emp.staff_info.unpaid_leave" module="hrm"/>
				</td>
			</tr>
			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:125;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td width="18%" height="30" nowrap class="info_title_01"><!-- 期间 -->
							<ait:message  messageID="display.mutual.period"/>
						</td>
						<!-- <td width="23%" nowrap class="info_title_01">休职类型</td> -->
						<td width="60%" nowrap class="info_title_01"><!-- 休职事由 -->
							<ait:message  messageID="display.emp.staff_info.unpaid_leave.reason_of_unpaid_leave" module="hrm"/>
						</td>
						<td width="28%" nowrap class="info_title_01"><!-- 人事令号 -->
						<ait:message  messageID="display.mutual.regulation_no"/></td>
						</tr>
					<c:forEach items="${suspendList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap>${oneResult.startDate}
							~ ${oneResult.endDate}</td>
							<!-- <td class="info_content_01" nowrap>${oneResult.suspendType}</td>-->
							<td class="info_content_01" nowrap>${oneResult.suspendReason}</td>
							<td class="info_content_01" nowrap>${oneResult.transNo}</td>
						</tr>
					</c:forEach>
					<c:if test="${suspendListCnt<3}">
						<c:forEach var="i" begin="1" end="${3-suspendListCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>

							</tr>
						</c:forEach>
					</c:if>
				</table>
				</div>
				</td>
			</tr>
			<tr height="1">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td class="title1"><!-- 派遣 -->
							<ait:message  messageID="display.emp.staff_info.assignment" module="hrm"/>
						</td>
			</tr>
			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:125;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td width="18%" height="30" nowrap class="info_title_01"><!-- 期间 -->
						<ait:message  messageID="display.mutual.period"/></td>
						<!-- <td width="23%" nowrap class="info_title_01">机关/部门</td> -->
						<td width="60%" nowrap class="info_title_01"><!-- 派遣内容 -->
							<ait:message  messageID="display.emp.staff_info.assignment.assignment_contents" module="hrm"/>
						</td>
						<td width="28%" nowrap class="info_title_01"><!-- 人事令号 -->
						<ait:message  messageID="display.mutual.regulation_no"/></td>
					</tr>
					<c:forEach items="${dispatchList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap>${oneResult.startDate}
							~ ${oneResult.endDate}</td>
							<!-- <td class="info_content_01" nowrap>${oneResult.disDept}</td> -->
							<td class="info_content_01" nowrap>${oneResult.contents}</td>
							<td class="info_content_01" nowrap>${oneResult.transNo}</td>
						</tr>
					</c:forEach>
					<c:if test="${dispatchListCnt<3}">
						<c:forEach var="i" begin="1" end="${3-dispatchListCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>

							</tr>
						</c:forEach>
					</c:if>
				</table>
				</div>
				</td>
			</tr>
			<tr height="1">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td class="title1"><!-- 兼职 -->
							<ait:message  messageID="display.emp.staff_info.concurrent_job" module="hrm"/>
						</td>
			</tr>
			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:125;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td width="18%" height="30" nowrap class="info_title_01"><!-- 期间 -->
						<ait:message  messageID="display.mutual.period"/></td>
						<!-- <td width="60%" nowrap class="info_title_01">兼职事由</td> -->
						<td width="60%" nowrap class="info_title_01"><!-- 兼职内容 -->
							<ait:message  messageID="display.emp.staff_info.contents_of_concurrent_job" module="hrm"/>
						</td>
						<td width="28%" nowrap class="info_title_01"><!-- 人事令号 -->
						<ait:message  messageID="display.mutual.regulation_no"/></td>
					</tr>
					<c:forEach items="${pluralityList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap>${oneResult.startDate}
							~ ${oneResult.endDate}</td>
							<!-- <td class="info_content_01" nowrap>${oneResult.reason}</td> -->
							<td class="info_content_01" nowrap>${oneResult.contents}</td>
							<td class="info_content_01" nowrap>${oneResult.transNo}</td>
						</tr>
					</c:forEach>
					<c:if test="${pluralityListCnt<3}">
						<c:forEach var="i" begin="1" end="${3-pluralityListCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>

							</tr>
						</c:forEach>
					</c:if>
				</table>
				</div>
				</td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="30">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>

</form>
</body>
</html>
