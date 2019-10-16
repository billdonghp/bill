<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>人事信息&gt;健康信息</title>
</head>
<body>
<form name="form1" method="post">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">
		
		<!-- display toolbar -->
		<%@ include file="./inc/esstoolbar_null.jsp"%></td>
		
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info --> 
		<%@ include file="../ess/ess_view_basic.jsp"%>
		
		<!-- display 3 level menu -->
		<%@ include file="./inc/ess_view_toolbar.jsp"%>
		
		<br>
		<!-- display content -->
		<table width="100%" border="0" cellpadding="5">
			<tr>
				<td tdwidth="50%" class="title1"><!-- 健康 -->
					<ait:message  messageID="display.ess.staff_info.health_info.health_status" module="ess"/>
				</td>
			</tr>
			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td class="info_title_01" height="30" nowrap><!-- 体检日期 -->
							<ait:message  messageID="display.mutual.physical_exam_date"/>
						</td>
						<td class="info_title_01" nowrap><!-- 身高（cm） -->
							<ait:message  messageID="display.ess.staff_info.health_info.height" module="ess"/>
						</td>
						<td class="info_title_01" nowrap><!-- 体重（kg） -->
							<ait:message  messageID="display.ess.staff_info.health_info.weight" module="ess"/>
						</td>
						<td class="info_title_01" nowrap="nowrap"><!-- 血型 -->
							<ait:message  messageID="display.ess.staff_info.health_info.blood_type" module="ess"/>
						</td>
						<td class="info_title_01" nowrap><!-- 左眼视力 -->
							<ait:message  messageID="display.ess.staff_info.health_info.eyesight_L" module="ess"/>
						</td>
						<td class="info_title_01" nowrap><!-- 右眼视力 -->
							<ait:message  messageID="display.ess.staff_info.health_info.eyesight_R" module="ess"/>
						</td>
						<td class="info_title_01" nowrap><!-- 色视情况 -->
							<ait:message  messageID="display.ess.staff_info.health_info.dichromatism" module="ess"/>
						</td>
						<td class="info_title_01" nowrap><!-- 血压（Max） -->
							<ait:message  messageID="display.ess.staff_info.health_info.blood_pressure_max" module="ess"/>
						</td>
						<td class="info_title_01" nowrap><!-- 血压（Min） -->
							<ait:message  messageID="display.ess.staff_info.health_info.blood_pressure_min" module="ess"/>
						</td>
					</tr>
					<c:forEach items="${healthInfoList}" var="oneResult">
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
						</tr>
					</c:forEach>
					<c:if test="${healthInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-healthInfoCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
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

			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
                         <td class="info_title_01" height="30" nowrap><!-- 心 -->
							<ait:message  messageID="display.ess.staff_info.health_info.heart" module="ess"/>
						</td>
						<td class="info_title_01" nowrap><!-- 肝 -->
							<ait:message  messageID="display.ess.staff_info.health_info.liver" module="ess"/>
						</td>
						<td class="info_title_01" nowrap><!-- 脾 -->
							<ait:message  messageID="display.ess.staff_info.health_info.spleen" module="ess"/>
						</td>
						<td class="info_title_01" nowrap><!-- 肺 -->
							<ait:message  messageID="display.ess.staff_info.health_info.lung" module="ess"/>
						</td>             
						<td class="info_title_01" nowrap><!-- 皮肤 -->
							<ait:message  messageID="display.ess.staff_info.health_info.skin" module="ess"/>
						</td>
						<td class="info_title_01" nowrap><!-- 胸透 -->
							<ait:message  messageID="display.ess.staff_info.health_info.chest_x_rayed" module="ess"/>
						</td>  
						<td class="info_title_01"><!-- 大便培养 -->           
							<ait:message  messageID="display.ess.staff_info.health_info.dejection_symptom" module="ess"/>
						</td>
						<td class="info_title_01" nowrap><!-- 肝功能 -->
							<ait:message  messageID="display.ess.staff_info.health_info.liver_function" module="ess"/>
						</td>     
						<td class="info_title_01" nowrap><!-- 医生意见 -->
							<ait:message  messageID="display.ess.staff_info.health_info.doctor_conclusion" module="ess"/>
						</td>
					</tr>
					<c:forEach items="${healthInfoList}" var="oneResult">
						<tr>
							<td class="info_content_01" nowrap>
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
							<td class="info_content_01"  >${oneResult.content} </td>
						</tr>                 
					</c:forEach>
					<c:if test="${healthInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-healthInfoCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30" nowrap></td>
								<td class="info_content_01" nowrap></td>
								<td class="info_content_01" nowrap></td>
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
