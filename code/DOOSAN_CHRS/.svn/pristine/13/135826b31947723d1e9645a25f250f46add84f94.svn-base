<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;其他信息</title>
</head>
<SCRIPT type="text/javascript">

function Add(){
	
	document.form1.action="hrmControlServlet?menu_code=hr0109&operation=retrieveDataForInsertOtherInfo&empID=<c:out value='${empID}'/>";
	document.form1.submit();
 }
 
function Update(){

	document.form1.action="hrmControlServlet?menu_code=hr0109&operation=retrieveDataForUpdateOtherInfo&empID=<c:out value='${empID}'/>";
	document.form1.submit();
}

function Delete(){
	
	document.form1.action="hrmControlServlet?menu_code=hr0109&operation=retrieveDataForDeleteOtherInfo&empID=<c:out value='${empID}'/>";
	document.form1.submit();
}

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
		<%@ include file="./inc/hrtoolbar.jsp"%></td>
		
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
				<td width="21%" class="title1"><!-- 资格证书 -->
							<ait:message  messageID="display.emp.staff_info.other_info.certificate" module="hrm"/>	
						</td>
			</tr>
			<tr height="135">
				<td colspan="2">
				<div style="overflow:auto; width:100%; height:125;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td height="30" class="info_title_01" nowrap><!-- 资格证名称 -->
							<ait:message  messageID="display.emp.staff_info.other_info.certificate.certificate_title" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 证件号 -->
							<ait:message  messageID="display.emp.staff_info.other_info.certificate.certificate_no" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 证件级别 -->
							<ait:message  messageID="display.emp.staff_info.other_info.certificate.certificate_grade" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 发证处 -->
							<ait:message  messageID="display.emp.staff_info.other_info.certificate.certificate_authority" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 取证日期 -->
							<ait:message  messageID="display.emp.staff_info.other_info.date_of_obtaining" module="hrm"/>	
						</td>
					</tr>
					<c:forEach items="${qualificationInfoList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap>
								<ait:content enContent="${oneResult.enQualificationName}" zhContent="${oneResult.qualificationName}" koContent="${oneResult.korQualificationName}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.qualificationCardNo}</td>
							<td class="info_content_01" nowrap>${oneResult.qualificationGrade}</td>
							<td class="info_content_01" nowrap>${oneResult.qualificationLocation}</td>
							<td class="info_content_01" nowrap>${oneResult.obtainedDate}</td>
						</tr>
					</c:forEach>
					<c:if test="${qualificationInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-qualificationInfoCnt}"
							step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
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
				<td class="title1"><!-- 入社前经历 -->
							<ait:message  messageID="display.emp.staff_info.other_info.previous_experience" module="hrm"/>	
						</td>
			</tr>
			<tr height="135">
				<td colspan="2">
				<div style="overflow:auto; width:100%; height:125;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td height="30" class="info_title_01" nowrap><!-- 工作单位 -->
							<ait:message  messageID="display.emp.staff_info.other_info.company" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 开始时间 -->
							<ait:message  messageID="display.mutual.start_date_2"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 结束时间 -->
							<ait:message  messageID="display.mutual.end_date_2"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 工作部门 -->
							<ait:message  messageID="display.mutual.department"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 职位 -->
							<ait:message  messageID="display.mutual.position"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 离职原因 -->
							<ait:message  messageID="display.mutual.dimission_reason"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 工作业务内容 -->
							<ait:message  messageID="display.emp.staff_info.other_info.work_contents" module="hrm"/>	
						</td>
					</tr>
					<c:forEach items="${experienceInfoList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap>${oneResult.prevCpnyName}</td>
							<td class="info_content_01" nowrap>${oneResult.startDate}</td>
							<td class="info_content_01" nowrap>${oneResult.endDate}</td>
							<td class="info_content_01" nowrap>${oneResult.prevDeptName}</td>
							<td class="info_content_01" nowrap>${oneResult.prevPosition}</td>
						    <td class="info_content_01" nowrap>${oneResult.leaveReason}</td> 
							<td class="info_content_01" nowrap>${oneResult.workContent}</td>
						 
						</tr>
					</c:forEach>
					<c:if test="${experienceInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-experienceInfoCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
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
</body>
</html>
