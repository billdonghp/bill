<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;其他信息</title>
</head>
<script language="javascript" src="../js/hr_modify_otherinfo.js"></script>
<SCRIPT type="text/javascript">

function Save(){
	var name = '<ait:message  messageID="alert.emp.staff_info.other_info.certificate_must_be_entered" module="hrm"/>';
	var dateM = '<ait:message  messageID="alert.mutual.start_date_must_b_entered"/>';
	var reName = '<ait:message  messageID="alert.emp.staff_info.other_info.certificate_cannot_be_repeated" module="hrm"/>'
	var reDateM = '<ait:message  messageID="alert.emp.staff_info.training.start_date_cannot_be_repeated" module="hrm"/>'
	var array = new Array(name,dateM,reName,reDateM);
	if(CheckForm(array)) {

		document.form1.action="hrmControlServlet?menu_code=hr0109&operation=updateOtherInfo&empID=<c:out value='${empID}'/>";
		document.form1.fireSubmit();
	}

 }
 
</SCRIPT>
<body>
<form name="form1" method="post">



<div id='layername'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
<iframe name="emp_list" width="370" height="200" frameborder="0"
	marginwidth="0" marginheight="0" hspace="0" vspace="0"></iframe></div>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">
		
		<!-- display toolbar -->
		<%@ include file="./inc/hrtoolbar_a.jsp"%></td>
		
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info --> 
		<%@ include file="../hrm/hrm_add_basic.jsp"%> 
		
		<!-- display content -->
		<table width="100%" border="0" cellpadding="0">
			<tr>
				<td width="21%" class="title1"><!-- 资格证书 -->
							<ait:message  messageID="display.emp.staff_info.other_info.certificate" module="hrm"/>	
						</td>
			</tr>
			<tr height="135">
				<td colspan="2">

				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
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
					<c:forEach items="${qualificationInfoList}" var="oneResult"
						varStatus="i">
						<tr>
							<td class="info_content_01" height="30" nowrap><ait:codeClass
								name="qualificationNameCode_${i.count}" codeClass="QualNameCode"
								all="ALL" selected="${oneResult.qualificationNameCode}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="qualificationCardNo_${i.count}"
								id="qualificationCardNo_${i.count}"
								value="${oneResult.qualificationCardNo}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="qualificationGrade_${i.count}"
								id="qualificationGrade_${i.count}"
								value="${oneResult.qualificationGrade}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="qualificationLocation_${i.count}"
								id="qualificationLocation_${i.count}"
								value="${oneResult.qualificationLocation}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								size="10" class="content" name="obtainedDate_${i.count}"
								id="obtainedDate_${i.count}" value="${oneResult.obtainedDate}"
								onClick="setday(this);" date mask="9999-99-99" /></td>
							<input type="hidden" name="updatedBy_${i.count}"
								value="${adminId}">
							<input type="hidden" name="empID_${i.count}" value="${empID}" />
							<input type="hidden" name="qualificationNo_${i.count}"
								value="${oneResult.qualificationNo}">
						</tr>
					</c:forEach>
					<c:if test="${qualificationInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-qualificationInfoCnt}"
							step="1">
							<tr>
								<td class="info_content_01" height="30"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
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

				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
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
					<c:forEach items="${experienceInfoList}" var="oneResult"
						varStatus="i">
						<tr>
							<td class="info_content_01" height="30" nowrap><input
								type="text" class="content" name="prevCpnyName_@${i.count}"
								id="prevCpnyName_@${i.count}" value="${oneResult.prevCpnyName}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								size="10" class="content" name="startDate_@${i.count}"
								id="startDate_@${i.count}" onClick="setday(this);"
								value="${oneResult.startDate}" date mask="9999-99-99" /></td>
							<td class="info_content_01" nowrap><input type="text"
								size="10" class="content" name="endDate_@${i.count}"
								id="endDate_@${i.count}" onClick="setday(this);"
								value="${oneResult.endDate}" date mask="9999-99-99" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="prevDeptName_@${i.count}"
								id="prevDeptName_@${i.count}" value="${oneResult.prevDeptName}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="prevPosition_@${i.count}"
								id="prevPosition_@${i.count}" value="${oneResult.prevPosition}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="leaveReason_@${i.count}"
								id="leaveReason_@${i.count}" value="${oneResult.leaveReason}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="workContent_@${i.count}"
								id="workContent_@${i.count}" value="${oneResult.workContent}" /></td>
							<input type="hidden" name="updatedBy_@${i.count}"
								value="${adminId}">
							<input type="hidden" name="empID_@${i.count}" value="${empID}" />
							<input type="hidden" name="workExperNo_@${i.count}"
								value="${oneResult.workExperNo}">
						</tr>
					</c:forEach>
					<c:if test="${experienceInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-experienceInfoCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
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


<input type="hidden" name="experienceInfoCnt" id="experienceInfoCnt"
	value="${experienceInfoCnt }"> <input type="hidden"
	name="qualificationInfoCnt" id="qualificationInfoCnt"
	value="${qualificationInfoCnt }">
</body>
<ait:xjos />
</html>
