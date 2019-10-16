<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;健康信息</title>
</head>
<script language="javascript" src="../js/hr_modify_health.js"></script>
<SCRIPT type="text/javascript">

function Save(){

var dateMessage = '<ait:message  messageID="alert.emp.staff_info.health_info.physical_exam_date_must_be_entered" module="hrm"/>';
var redateMessage = '<ait:message  messageID="alert.emp.staff_info.health_info.physical_exam_date_cannot_be_repeated" module="hrm"/>';
  var size='$size}';
	if(CheckForm(dateMessage,redateMessage,size)) {
  
		document.form1.action="hrmControlServlet?menu_code=hr0108&operation=updateHealth&empID=<c:out value='${empID}'/>";
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
							<td class="info_content_01" size="10" height="30" nowrap><input
								type="text" size="10" class="content"
								name="physicalDate_${i.count}" id="physicalDate_${i.count}"
								value="${oneResult.physicalDate }" onClick="setday(this);"
								requie date mask="9999-99-99" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="height_${i.count}" id="height_${i.count}"
								value="${oneResult.height }" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="weight_${i.count}" id="weight_${i.count}"
								value="${oneResult.weight }" /></td>
							<td class="info_content_01" nowrap><ait:codeClass
								name="bloodTypeCode_${i.count}" codeClass="BloodTypeCode"
								all="ALL" selected="${oneResult.bloodTypeCode }" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="leftEyesight_${i.count}"
								id="leftEyesight_${i.count}" value="${oneResult.leftEyesight }" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="rightEyesight_${i.count}"
								id="rightEyesight_${i.count}"
								value="${oneResult.rightEyesight }" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="colorBlind_${i.count}"
								id="colorBlind_${i.count}" value="${oneResult.colorBlind }" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="bloodPressureMax_${i.count}"
								id="bloodPressureMax_${i.count}"
								value="${oneResult.bloodPressureMax }" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="bloodPressureMix_${i.count}"
								id="bloodPressureMix_${i.count}"
								value="${oneResult.bloodPressureMix }" /></td>
							<input type="hidden" name="updatedBy_${i.count}"
								value="${adminId}">
							<input type="hidden" name="empID_${i.count}" value="${empID}" />
							<input type="hidden" name="healthNo_${i.count}"
								value="${oneResult.healthNo}">
						</tr>
					</c:forEach>
					<c:if test="${healthInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-healthInfoCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
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
			<tr height="135">
				<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
					    <td class="info_title_01" height="30" nowrap width="10%"><!-- 心 -->
							<ait:message  messageID="display.emp.staff_info.health_info.heart" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap width="10%"><!-- 肝 -->
							<ait:message  messageID="display.emp.staff_info.health_info.liver" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap width="10%"><!-- 脾 -->
							<ait:message  messageID="display.emp.staff_info.health_info.spleen" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap width="10%"><!-- 肺 -->
							<ait:message  messageID="display.emp.staff_info.health_info.lung" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap width="10%"><!-- 皮肤 -->
							<ait:message  messageID="display.emp.staff_info.health_info.skin" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap width="10%"><!-- 胸透 -->
							<ait:message  messageID="display.emp.staff_info.health_info.chest_x_rayed" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap width="10%"><!-- 大便培养 -->
							<ait:message  messageID="display.emp.staff_info.health_info.dejection_symptom" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap width="10%"><!-- 肝功能 -->
							<ait:message  messageID="display.emp.staff_info.health_info.liver_function" module="hrm"/>
						</td>     
						<td class="info_title_01" nowrap width="30%"><!-- 医生意见 -->
							<ait:message  messageID="display.emp.staff_info.health_info.doctor_conclusion" module="hrm"/>
						</td>
<!--						<td class="info_title_01" height="30" nowrap>体检日期</td>-->
<!--						<td class="info_title_01" nowrap>血糖</td>-->
<!--						<td class="info_title_01" nowrap>OT</td>-->
<!--						<td class="info_title_01" nowrap>PT</td>-->
<!--						<td class="info_title_01" nowrap>HBsAg</td>-->
<!--						<td class="info_title_01" nowrap>抗-HBs</td>-->
<!--						<td class="info_title_01" nowrap>HBeAg</td>-->
<!--						<td class="info_title_01" nowrap>抗-HBe</td>-->
<!--						<td class="info_title_01" nowrap>抗-HBc</td>-->
					</tr>
					<c:forEach items="${healthInfoList}" var="oneResult" varStatus="i">
						<tr>      
						
<!--							  <td class="info_content_01" height="30" nowrap>${oneResult.physicalDate}</td>-->
							  <td class="info_content_01" nowrap><ait:codeClass
								name="bloodSugar_${i.count}" codeClass="CheckResult"  selected="${oneResult.bloodSugarCode }"/></td>
								<td class="info_content_01" nowrap><ait:codeClass
								name="healthOT_${i.count}" codeClass="CheckResult"  selected="${oneResult.healthOtCode }"/></td>
								<td class="info_content_01" nowrap><ait:codeClass
								name="healthPT_${i.count}" codeClass="CheckResult"  selected="${oneResult.healthPtCode }"/></td>
								<td class="info_content_01" nowrap><ait:codeClass
								name="healthHBSAG_${i.count}" codeClass="CheckResult"  selected="${oneResult.healthHbsagCode }"/></td>
								<td class="info_content_01" nowrap><ait:codeClass
								name="healthHBS_${i.count}" codeClass="CheckResult"  selected="${oneResult.healthHbsCode }"/></td>
								<td class="info_content_01" nowrap><ait:codeClass
								name="healthHBEAG_${i.count}" codeClass="CheckResult"  selected="${oneResult.healthHbeagCode }"/></td>
								<td class="info_content_01" nowrap><ait:codeClass
								name="healthHBE_${i.count}" codeClass="CheckResult"  selected="${oneResult.healthHbeCode }"/></td>
								<td class="info_content_01" nowrap><ait:codeClass
								name="healthHBC_${i.count}" codeClass="CheckResult"  selected="${oneResult.healthHbgCode }"/></td>
                            	 <td class="info_content_01" height="30" nowrap><input
								type="text" size="10" class="content" name="content_${i.count}"
								id="content_${i.count}" /></td>                 
                            	
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="bloodSugar_${i.count}"-->
<!--								id="bloodSugar_${i.count}" value="${oneResult.bloodSugar }" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthOT_${i.count}"-->
<!--								id="healthOT_${i.count}" value="${oneResult.healthOT }" /></td>-->  
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthPT_${i.count}"-->
<!--								id="healthPT_${i.count}" value="${oneResult.healthPT }" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthHBSAG_${i.count}"-->
<!--								id="healthHBSAG_${i.count}" value="${oneResult.healthHBSAG }" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthHBS_${i.count}"-->
<!--								id="healthHBS_${i.count}" value="${oneResult.healthHBS }" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthHBEAG_${i.count}"-->
<!--								id="healthHBEAG_${i.count}" value="${oneResult.healthHBEAG }" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthHBE_${i.count}"-->
<!--								id="healthHBE_${i.count}" value="${oneResult.healthHBE }" /></td>-->
<!--							<td class="info_content_01" nowrap><input type="text"-->
<!--								class="content" name="healthHBC_${i.count}"-->
<!--								id="healthHBC_${i.count}" value="${oneResult.healthHBC }" /></td>-->
						</tr>   
					</c:forEach>
					<c:if test="${healthInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-healthInfoCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
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

<input type="hidden" name="healthInfoCnt" id="healthInfoCnt"
	value="${healthInfoCnt }">
</form>
</body>
<ait:xjos />
</html>
