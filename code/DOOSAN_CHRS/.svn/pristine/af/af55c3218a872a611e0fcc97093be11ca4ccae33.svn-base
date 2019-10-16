<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>

<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;社会关系</title>
</head>
<script language="javascript" src="../js/hr_modify_family.js"></script>
<SCRIPT type="text/javascript">

function Save(){var relation = '<ait:message  messageID="alert.emp.staff_info.relations.relations_must_be_entered" module="hrm"/>';	
	var name = '<ait:message  messageID="alert.emp.staff_info.relations.name_must_be_entered" module="hrm"/>';	
	var idCard = '<ait:message  messageID="alert.emp.staff_info.relations.id_card_number_must_be_entered" module="hrm"/>';	
	var marriage = '<ait:message  messageID="alert.emp.staff_info.relations.marriage_status_must_be_entered" module="hrm"/>';	
	var person = '<ait:message  messageID="alert.emp.staff_info.relations.personnel_file_must_be_entered" module="hrm"/>';	
	var address = '<ait:message  messageID="alert.emp.staff_info.relations.current_address_must_be_entered" module="hrm"/>';
	var seat = '<ait:message  messageID="alert.emp.staff_info.relations.seat_of_residence_must_be_entered" module="hrm"/>';
	var seatStaus = '<ait:message  messageID="alert.emp.staff_info.relations.hu_kou_status_must_be_entered" module="hrm"/>';
	var Mservice = '<ait:message  messageID="alert.emp.staff_info.relations.military_service_must_be_entered" module="hrm"/>';	
	var army = '<ait:message  messageID="alert.emp.staff_info.relations.army_must_be_entered" module="hrm"/>';	
	var military = '<ait:message  messageID="alert.emp.staff_info.relations.military_rank_must_be_entered" module="hrm"/>';	
	var MRegion = '<ait:message  messageID="alert.emp.staff_info.relations.military_region_must_be_entered" module="hrm"/>';
	var start = '<ait:message  messageID="alert.mutual.start_date_must_b_entered"/>';
	var end = '<ait:message  messageID="alert.mutual.end_date_must_be_entered"/>';
	var reName = '<ait:message  messageID="alert.emp.staff_info.relations.name_cannot_be_repeated." module="hrm"/>';
	var array = new Array(relation,name,idCard,marriage,person,address,seat,seatStaus,Mservice,army,military,MRegion,start,end,reName);
	if(CheckForm(array)) {  

		document.form1.action="hrmControlServlet?menu_code=hr0107&operation=updateRelation&empID=<c:out value='${empID}'/>";
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
				<td width="63%" class="title1"><!-- 家人关系 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.family" module="hrm"/>
						</td>
				<td width="37%" class="title1"><!-- 电话/e-Mail -->
							<ait:message  messageID="display.emp.staff_info.social_relations.contact" module="hrm"/>
						</td>
			</tr>
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td width="17%" height="30" class="info_title_01" nowrap><!-- 身份证 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.id_card" module="hrm"/>
						</td>
						<td width="37%" class="info_content_01" nowrap><input
							type="text" class="content" name="idcardNo" id="idcardNo"
							value="${personalInfo.idcardNo}" /></td>
						<td width="13%" class="info_title_01" nowrap><!-- 婚姻状态 -->
							<ait:message  messageID="display.mutual.martial_status"/>
						</td>
						<td width="33%" class="info_content_01" nowrap><ait:codeClass
							name="maritalStatusCode" codeClass="MaritalStatusCode"
							selected="${personalInfo.maritalStatusCode}" /></td>
					</tr>

					<tr>
						<td width="13%" class="info_title_01" nowrap><!-- 出生日期 -->
							<ait:message  messageID="display.mutual.birth_date"/>
						</td>
						<td width="33%" class="info_content_01" nowrap colspan="3"><input
							type="text" size="10" class="content" name="dob" id="dob"
							value="${personalInfo.dob}" onClick="setday(this);" date
							mask="9999-99-99" /></td>
					</tr>
				</table>
				</td>
				<td rowspan="2" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td width="16%" rowspan="4" class="info_title_01" nowrap><!-- 电<br>话 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.tel" module="hrm"/>
						</td>
						<td width="20%" class="info_title_01" height="30" nowrap><!-- 办公室 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.office" module="hrm"/>
						</td>
						<td width="64%" class="info_content_01" nowrap><input
							type="text" class="content" name="officePhone" id="officePhone"
							value="${personalInfo.officePhone}" /></td>
					</tr>
					<tr>
						<td class="info_title_01" height="30" nowrap><!-- 住址电话 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.home" module="hrm"/>
						</td>
						<td class="info_content_01" nowrap><input type="text"
							class="content" name="homePhone" id="homePhone"
							value="${personalInfo.homePhone}" /></td>
					</tr>
					<tr>
						<td class="info_title_01" height="30" nowrap><!-- 手机 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.mobile_phone" module="hrm"/>
						</td>
						<td class="info_content_01" nowrap><input type="text"
							class="content" name="cellPhone" id="cellPhone"
							value="${personalInfo.cellPhone}" /></td>
					</tr>
					<tr>
						<td class="info_title_01" height="30" nowrap><!-- Fax -->
							<ait:message  messageID="display.mutual.fax"/>
						</td>
						<td class="info_content_01" nowrap><input type="text"
							class="content" name="fax" id="fax" value="${personalInfo.fax}" /></td>
					</tr>
					<tr>
						<td colspan="2" class="info_title_01" height="30" nowrap><!-- e-Mail -->
							<ait:message  messageID="display.mutual.e_mail"/>
						</td>
						<td class="info_content_01" nowrap><input type="text"
							class="content" name="email" id="email"
							value="${personalInfo.email}" /></td>
					</tr>
					<tr>
						<td colspan="2" class="info_title_01" height="30" nowrap><!-- 档案关系 -->
							<ait:message  messageID="display.mutual.personnel_file"/>
						</td>
						<td class="info_content_01" nowrap><ait:codeClass
							name="fileRelationCode" codeClass="FileRelation"
							selected="${personalInfo.fileRelationCode}" /></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
					<tr style="position: relative; top: expression(this.offsetParent.scrollTop);">   
						<td class="info_title_01" height="30" nowrap><!-- 关系 -->
							<ait:message  messageID="display.mutual.relation"/>
						</td>
						<td class="info_title_01" nowrap><!-- 姓名 -->
							<ait:message  messageID="display.mutual.name"/>
						</td>     
						<td class="info_title_01" nowrap><!-- 出生日期 -->
							<ait:message  messageID="display.mutual.birth_date"/>
						</td>
						<td class="info_title_01" nowrap><!-- 职业 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.vocation" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 工作单位 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.company" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 最终学历 -->
							<ait:message  messageID="display.mutual.diploma_2"/>
						</td>
					</tr>
					<c:forEach items="${familyInfoList}" var="oneResult" varStatus="i">
						<tr>
							<td class="info_content_01" nowrap><ait:codeClass
								name="relationalTypeCode_${i.count}"
								codeClass="RelationalTypeCode"
								selected="${oneResult.relationalTypeCode}" /></td>
							<td class="info_content_01" nowrap><input type="text"
								class="content" name="famName_${i.count}"
								id="famName_${i.count}" value="${oneResult.famName}" /></td>
							<td class="info_content_01" nowrap><input type="text" size="10"
								class="content" name="famBornDate_${i.count}"
								id="famBornDate_${i.count}" value="${oneResult.famBornDate}"
								onClick="setday(this);" date mask="9999-99-99" /></td>
							<td class="info_content_01" nowrap><ait:codeClass
								name="famOccupationCode_${i.count}" codeClass="OccupCode"
								selected="${oneResult.famOccupationCode}" /></td>
							<td class="info_content_01" nowrap><input type="text"  
								class="content" name="famIDCard_${i.count}"
								id="famIDCard_${i.count}" value="${oneResult.famIDCard}" /></td>
							<td class="info_content_01" nowrap><ait:codeClass
								name="famDegreeCode_${i.count}" codeClass="DegreeCode"
								selected="${oneResult.famDegreeCode}" /></td>
							<input type="hidden" name="familyNo_${i.count}" value="${oneResult.familyNo}">
							<input type="hidden" name="modifierID_${i.count}" value="${adminId}">
							<input type="hidden" name="empID_${i.count}" value="${empID}"/>  
						</tr>
					</c:forEach>
					<c:if test="${familyInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-familyInfoCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				</div>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="title1"><!-- 住址 -->
					<ait:message  messageID="display.emp.staff_info.social_relations.address" module="hrm"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td height="30" class="info_title_01" nowrap><!-- 现住址 -->
							<ait:message  messageID="display.mutual.current_address"/>
						</td>
						<td class="info_title_01" nowrap><!-- 邮编 -->
							<ait:message  messageID="display.mutual.postal_code"/>
						</td>
						<td class="info_title_01" nowrap><!-- 身份证地址 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.address_on_idcard" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 户口性质 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.hukou" module="hrm"/>
						</td>
					</tr>
					<tr>
						<td height="30" class="info_content_01" nowrap><input
							type="text" class="content" name="homeAddress" id="homeAddress"
							value="${personalInfo.homeAddress}" /></td>
						<td class="info_content_01" nowrap><input type="text"
							class="content" name="postalCode" id="postalCode"
							value="${personalInfo.postalCode}" /></td>
						<td class="info_content_01" nowrap><input type="text"
							class="content" name="regPlace" id="regPlace"
							value="${personalInfo.regPlace}" /></td>
						<td class="info_content_01" nowrap><ait:codeClass
							name="regTypeCode" codeClass="RegTypeCode"
							selected="${personalInfo.regTypeCode}" /></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr height="1">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td class="title1"><!-- 兵役 -->
					<ait:message  messageID="display.emp.staff_info.social_relations.military_service" module="hrm"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td height="30" class="info_title_01" width="20%" nowrap><!-- 军别 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.army" module="hrm"/>
						</td>
						<td class="info_title_01" width="20%" nowrap><!-- 军级 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.military_rank" module="hrm"/>
						</td>
						<td class="info_title_01" width="20%" nowrap><!-- 军区 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.military_region" module="hrm"/>
						</td>
						<td class="info_title_01" width="20%" nowrap><!-- 开始日期 -->
							<ait:message  messageID="display.mutual.start_date"/>
						</td>
						<td class="info_title_01" width="20%" nowrap><!-- 结束日期 -->
							<ait:message  messageID="display.mutual.end_date"/>
						</td>
					</tr>
					<tr>
						<td height="30" class="info_content_01" nowrap><ait:codeClass
							name="militaryTypeCode" codeClass="MilitaryTypeCode"
							selected="${militaryServiceInfo.militaryTypeCode}" all="All" /></td>
						<td class="info_content_01" nowrap><ait:codeClass
							name="militaryLevelCode" codeClass="MilitaryLevelCode"
							selected="${militaryServiceInfo.militaryLevelCode}" all="All" /></td>
						<td class="info_content_01" nowrap><ait:codeClass
							name="militaryAreaCode" codeClass="MilitaryAreaCode"
							selected="${militaryServiceInfo.militaryAreaCode}" all="All" /></td>
						<td class="info_content_01" nowrap><input type="text"
							size="10" name="startTime" id="startTime" onClick="setday(this);"
							value="<c:out value='${militaryServiceInfo.startTime}'/>" date
							mask="9999-99-99" /></td>
						<td class="info_content_01" nowrap><input type="text"
							size="10" name="endTime" id="endTime" onClick="setday(this);"
							value="<c:out value='${militaryServiceInfo.endTime}'/>" date
							mask="9999-99-99" /></td>
						<input type="hidden" name="militaryNo" id="militaryNo"
							value="<c:out value='${militaryServiceInfo.militaryNo}'/>" />
						<input type="hidden" name="modifierID" id="modifierID"
							value="<c:out value='${adminId}'/>" />
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="title1"><!-- 其他信息 -->
					<ait:message  messageID="display.emp.staff_info.social_relations.other_information" module="hrm"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td height="30" class="info_title_01" nowrap><!-- 宗教 -->
							<ait:message  messageID="display.mutual.religion"/>
						</td>
						<td class="info_title_01" nowrap><!-- 爱好/兴趣 -->
							<ait:message  messageID="display.mutual.hobby"/>
						</td>
						<td class="info_title_01" nowrap><!-- 特长 -->
							<ait:message  messageID="display.mutual.skill"/>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" class="info_content_01" nowrap><ait:codeClass
							name="religionCode" codeClass="ReligionCode"
							selected="${personalInfo.religionCode}" /></td>
						<td width="40%" class="info_content_01" nowrap><input
							type="text" class="content" name="hobby" id="hobby"
							value="${personalInfo.hobby}" /></td>
						<td width="40%" class="info_content_01" nowrap><input
							type="text" class="content" name="speciality" id="speciality"
							value="${personalInfo.speciality}" /></td>
					</tr>
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


<input type="hidden" name="updatedBy" id="updatedBy" value="${adminId }">
<input type="hidden" name="familyInfoCnt" id="familyInfoCnt"
	value="${familyInfoCnt }"> <input type="hidden"
	name="militarySign" id="militarySign"></form>
</body>
<ait:xjos />
</html>
