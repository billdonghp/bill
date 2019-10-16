<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;健康信息</title>
</head>
<script language="javascript" src="../js/hr_add_health.js"></script>
<SCRIPT type="text/javascript">

function Save(){
	//体检日期为必输项
	//体检日期不可以重复
	var dateMessage = '<ait:message  messageID="alert.emp.staff_info.health_info.physical_exam_date_must_be_entered" module="hrm"/>';
	var redateMessage = '<ait:message  messageID="alert.emp.staff_info.health_info.physical_exam_date_cannot_be_repeated" module="hrm"/>';
	if(CheckForm(dateMessage,redateMessage)) {
	
		document.form1.action="hrmControlServlet?menu_code=hr0108&operation=insertHealth&empID=<c:out value='${empID}'/>";
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
		
		<!-- display basic info --> <%@ include file="../hrm/hrm_add_basic.jsp"%> 
		
		<!-- display content -->
		<table width="100%" border="0" cellpadding="5">
			<tr>
				<td tdwidth="50%" class="title1"><!-- 健康 -->
					<ait:message  messageID="display.emp.staff_info.health_info.health_status" module="hrm"/>
				</td>
			</tr>
			<tr height="135">
				<td>

				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td class="info_title_01" height="30" nowrap><!-- 体检日期 -->
							<ait:message  messageID="display.mutual.physical_exam_date"/>
						</td>
						<td class="info_title_01" nowrap><!-- 身高（cm） -->
							<ait:message  messageID="display.emp.staff_info.health_info.height" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 体重（kg） -->
							<ait:message  messageID="display.emp.staff_info.health_info.weight" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 血型 -->
							<ait:message  messageID="display.emp.staff_info.health_info.blood_type" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 左眼视力 -->
							<ait:message  messageID="display.emp.staff_info.health_info.eyesight_L" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 右眼视力 -->
							<ait:message  messageID="display.emp.staff_info.health_info.eyesight_R" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 色视情况 -->
							<ait:message  messageID="display.emp.staff_info.health_info.dichromatism" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 血压（Max） -->
							<ait:message  messageID="display.emp.staff_info.health_info.blood_pressure_max" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 血压（Min） -->
							<ait:message  messageID="display.emp.staff_info.health_info.blood_pressure_min" module="hrm"/>
						</td>
					</tr>
					<c:forEach items="${healthInfoList}" var="oneResult" varStatus="i">
						<tr>
							<td class="info_content_01" height="30" nowrap>${oneResult.physicalDate}</td>
							<td class="info_content_01" nowrap>${oneResult.height}</td>
							<td class="info_content_01" nowrap>${oneResult.weight}</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enBloodType}" zhContent="${oneResult.bloodType}" koContent="${oneResult.korBloodType}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.leftEyesight}</td>
							<td class="info_content_01" nowrap>${oneResult.rightEyesight}</td>
							<td class="info_content_01" nowrap>${oneResult.colorBlind}</td>
							<td class="info_content_01" nowrap>${oneResult.bloodPressureMax}</td>
							<td class="info_content_01" nowrap>${oneResult.bloodPressureMix}</td>
							<input type="hidden" name="physicalDate_exist${i.count}" id="physicalDate_exist${i.count}" value="${oneResult.physicalDate}">
						</tr>
					</c:forEach>　　　
					<c:forEach var="i" begin="1" end="3" step="1">
						<tr>
							<td class="info_content_01" height="30" nowrap><input
								type="text" size="10" class="content" name="physicalDate_${i}"
								id="physicalDate_${i}" onClick="setday(this);" date
								mask="9999-99-99" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="height_${i}" id="height_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="weight_${i}" id="weight_${i}" /></td>
							<td class="info_content_01" nowrap><ait:codeClass
								name="bloodTypeCode_${i}" codeClass="BloodTypeCode" all="ALL" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="leftEyesight_${i}" id="leftEyesight_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="rightEyesight_${i}"
								id="rightEyesight_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="colorBlind_${i}" id="colorBlind_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="bloodPressureMax_${i}"
								id="bloodPressureMax_${i}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="bloodPressureMix_${i}"
								id="bloodPressureMix_${i}" /></td>
							<input type="hidden" name="createdBy_${i}" value="${adminId}">
							<input type="hidden" name="empID_${i}" value="${empID}" />
						</tr>
					</c:forEach>
				</table>
				</td>
			</tr>
			<tr height="135">
				<td>

				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
<!--						<td class="info_title_01" height="30" nowrap>血糖</td>-->
<!--						<td class="info_title_01" nowrap>OT</td>-->
<!--						<td class="info_title_01" nowrap>PT</td>-->
<!--						<td class="info_title_01" nowrap>HBsAg</td>-->
<!--						<td class="info_title_01" nowrap>抗-HBs</td>-->
<!--						<td class="info_title_01" nowrap>HBeAg</td>-->
<!--						<td class="info_title_01" nowrap>抗-HBe</td>-->
<!--						<td class="info_title_01" nowrap>抗-HBc</td>-->
						<td class="info_title_01" height="30" nowrap><!-- 心 -->
							<ait:message  messageID="display.emp.staff_info.health_info.heart" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 肝 -->
							<ait:message  messageID="display.emp.staff_info.health_info.liver" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 脾 -->
							<ait:message  messageID="display.emp.staff_info.health_info.spleen" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 肺 -->
							<ait:message  messageID="display.emp.staff_info.health_info.lung" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 皮肤 -->
							<ait:message  messageID="display.emp.staff_info.health_info.skin" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 胸透 -->
							<ait:message  messageID="display.emp.staff_info.health_info.chest_x_rayed" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 大便培养 -->
							<ait:message  messageID="display.emp.staff_info.health_info.dejection_symptom" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 肝功能 -->
							<ait:message  messageID="display.emp.staff_info.health_info.liver_function" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 医生意见 -->
							<ait:message  messageID="display.emp.staff_info.health_info.doctor_conclusion" module="hrm"/>
						</td>
					</tr>
					<c:forEach items="${healthInfoList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap>
								<ait:content enContent="${oneResult.enBloodSugar}" zhContent="${oneResult.bloodSugar}" koContent="${oneResult.korBloodSugar}"/>
							</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enHealthOT}" zhContent="${oneResult.healthOT}" koContent="${oneResult.korHealthOT}"/>
							</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enHealthPT}" zhContent="${oneResult.healthPT}" koContent="${oneResult.korHealthPT}"/>
							</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enHealthHBSAG}" zhContent="${oneResult.healthHBSAG}" koContent="${oneResult.korHealthHBSAG}"/>
							</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enHealthHBS}" zhContent="${oneResult.healthHBS}" koContent="${oneResult.korHealthHBS}"/>
							</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enHealthHBEAG}" zhContent="${oneResult.healthHBEAG}" koContent="${oneResult.korHealthHBEAG}"/>
							</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enHealthHBE}" zhContent="${oneResult.healthHBE}" koContent="${oneResult.korHealthHBE}"/>
							</td>
							<td class="info_content_01" nowrap>
								<ait:content enContent="${oneResult.enHealthHBC}" zhContent="${oneResult.healthHBC}" koContent="${oneResult.korHealthHBC}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.content}</td>
						</tr>
					</c:forEach>
					<c:forEach var="i" begin="1" end="3" step="1">
						<tr>  
<!--							<td class="info_content_01" height="30" nowrap><input-->
<!--								type="text" class="content" name="bloodSugar_${i}"-->
<!--								id="bloodSugar_${i}" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthOT_${i}" id="healthOT_${i}" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthPT_${i}" id="healthPT_${i}" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthHBSAG_${i}" id="healthHBSAG_${i}" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthHBS_${i}" id="healthHBS_${i}" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthHBEAG_${i}" id="healthHBEAG_${i}" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthHBE_${i}" id="healthHBE_${i}" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthHBC_${i}" id="healthHBC_${i}" /></td>-->
                            <td class="info_content_01" nowrap><ait:codeClass
								name="bloodSugar_${i}" codeClass="CheckResult"  /></td>
							<td class="info_content_01" nowrap><ait:codeClass
								name="healthOT_${i}" codeClass="CheckResult"  /></td>
							<td class="info_content_01" nowrap><ait:codeClass
								name="healthPT_${i}" codeClass="CheckResult"  /></td>
							<td class="info_content_01" nowrap><ait:codeClass
								name="healthHBSAG_${i}" codeClass="CheckResult"  /></td>  
							<td class="info_content_01" nowrap><ait:codeClass
								name="healthHBS_${i}" codeClass="CheckResult"  /></td>
							<td class="info_content_01" nowrap><ait:codeClass
								name="healthHBEAG_${i}" codeClass="CheckResult"  /></td>
							<td class="info_content_01" nowrap><ait:codeClass
								name="healthHBE_${i}" codeClass="CheckResult"  /></td>
							<td class="info_content_01" nowrap><ait:codeClass  
								name="healthHBC_${i}" codeClass="CheckResult" /></td>
						     <td class="info_content_01" height="30" nowrap><input
								type="text" size="10" class="content" name="content_${i}"
								id="content_${i}" /></td>
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

<input type="hidden" name="healthSign" id="healthSign">
</body>
<ait:xjos />
</html>
