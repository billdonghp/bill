<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;其他信息</title>
</head>
<script language="javascript" src="../js/hr_add_otherinfo.js"></script>
<SCRIPT type="text/javascript">

function Save(){
	var name = '<ait:message  messageID="alert.emp.staff_info.other_info.certificate_must_be_entered" module="hrm"/>';
	var dateM = '<ait:message  messageID="alert.mutual.start_date_must_b_entered"/>';
	var reName = '<ait:message  messageID="alert.emp.staff_info.other_info.certificate_cannot_be_repeated" module="hrm"/>'
	var reDateM = '<ait:message  messageID="alert.emp.staff_info.training.start_date_cannot_be_repeated" module="hrm"/>'
	var array = new Array(name,dateM,reName,reDateM);
	if(CheckForm(array)) {

		document.form1.action="hrmControlServlet?menu_code=hr0109&operation=insertOtherInfo&empID=<c:out value='${empID}'/>";
		document.form1.fireSubmit();
	}

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
							<td class="info_content_01" height="30" nowrap>
								<ait:content enContent="${oneResult.enQualificationName}" zhContent="${oneResult.qualificationName}" koContent="${oneResult.korQualificationName}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.qualificationCardNo}</td>
							<td class="info_content_01" nowrap>${oneResult.qualificationGrade}</td>
							<td class="info_content_01" nowrap>${oneResult.qualificationLocation}</td>
							<td class="info_content_01" nowrap>${oneResult.obtainedDate}</td>
							<input type="hidden" name="qualificationNameCode_exist${i.count}"
								id="qualificationNameCode_exist${i.count}"
								value="${oneResult.qualificationNameCode}">
						</tr>
					</c:forEach>
					<c:forEach var="i" begin="1" end="3" step="1">
						<tr>
							<td class="info_content_01" height="30" nowrap><ait:codeClass
								name="qualificationNameCode_${i}" codeClass="QualNameCode"
								all="ALL" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="qualificationCardNo_${i}"
								id="qualificationCardNo_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="qualificationGrade_${i}"
								id="qualificationGrade_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="qualificationLocation_${i}"
								id="qualificationLocation_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								size="10" class="content" name="obtainedDate_${i}"
								id="obtainedDate_${i}" onClick="setday(this);" date
								mask="9999-99-99" /></td>
						</tr>
					</c:forEach>
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
							<td class="info_content_01" height="30" nowrap>${oneResult.prevCpnyName}</td>
							<td class="info_content_01" nowrap>${oneResult.startDate}</td>
							<td class="info_content_01" nowrap>${oneResult.endDate}</td>
							<td class="info_content_01" nowrap>${oneResult.prevDeptName}</td>
							<td class="info_content_01" nowrap>${oneResult.prevPosition}</td>
							<td class="info_content_01" nowrap>${oneResult.leaveReason}</td>
							<td class="info_content_01" nowrap>${oneResult.workContent}</td>
							<input type="hidden" name="startDate_exist${i.count}"
								id="startDate_exist${i.count}" value="${oneResult.startDate}">
						</tr>
					</c:forEach>
					<c:forEach var="i" begin="1" end="3" step="1">
						<tr>
							<td class="info_content_01" height="30" nowrap><input
								type="text" class="content" name="prevCpnyName_${i}"
								id="prevCpnyName_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								size="10" class="content" name="startDate_${i}"
								id="startDate_${i}" onClick="setday(this);" date
								mask="9999-99-99" /></td>
							<td class="info_content_01" nowrap><input type="text"
								size="10" class="content" name="endDate_${i}" id="endDate_${i}"
								onClick="setday(this);" date mask="9999-99-99" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="prevDeptName_${i}" id="prevDeptName_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="prevPosition_${i}" id="prevPosition_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="leaveReason_${i}" id="leaveReason_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="workContent_${i}" id="workContent_${i}" /></td>	   
							<input type="hidden" name="createdBy_${i}" value="${adminId}">
							<input type="hidden" name="empID_${i}" value="${empID}" />
						</tr>
					</c:forEach>
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

<input type="hidden" name="experienceSign" id="experienceSign">
<input type="hidden" name="qualificationSign" id="qualificationSign">
</body>
<ait:xjos />
</html>
