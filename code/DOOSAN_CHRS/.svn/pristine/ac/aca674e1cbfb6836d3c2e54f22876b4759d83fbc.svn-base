<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>

<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;社会关系</title>
</head>
<script language="javascript" src="../js/hr_add_family.js"></script>
<SCRIPT type="text/javascript">

function Save(){
	
	var array = new Array('<ait:message  messageID="alert.emp.staff_info.relations.relations_must_be_entered" module="hrm"/>',
	'<ait:message  messageID="alert.emp.staff_info.relations.name_must_be_entered" module="hrm"/>',
	'<ait:message  messageID="alert.emp.staff_info.relations.army_must_be_entered" module="hrm"/>',
	'<ait:message  messageID="alert.emp.staff_info.relations.military_rank_must_be_entered" module="hrm"/>',
	'<ait:message  messageID="alert.emp.staff_info.relations.military_region_must_be_entered" module="hrm"/>',
	'<ait:message  messageID="alert.mutual.start_date_must_b_entered"/>',	
	'<ait:message  messageID="alert.mutual.end_date_must_be_entered"/>',
	'<ait:message  messageID="alert.emp.staff_info.relations.name_cannot_be_repeated." module="hrm" />'
	
	);  
	if(CheckForm(array)) {  
	
		document.form1.action="hrmControlServlet?menu_code=hr0107&operation=insertRelation&empID=<c:out value='${empID}'/>";
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
						
						<td width="27%" class="info_content_01" nowrap>${personalInfo.idcardNo}</td>
						
						<td class="info_title_01" nowrap><!--出生日期--><ait:message  messageID="display.mutual.birth_date"/></td>
                        
                        <td class="info_content_00" width="20%"><c:out value='${personalInfo.dob}'/></td>
                        
						<td width="13%" class="info_title_01" nowrap><!-- 婚姻状态 -->
							<ait:message  messageID="display.mutual.martial_status"/>
						</td>
						<td width="13%" class="info_content_01" nowrap>
								<ait:content enContent="${personalInfo.enMaritalStatus}" zhContent="${personalInfo.maritalStatus}" koContent="${personalInfo.korMaritalStatus}"/>
							</td>
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
						<td width="64%" class="info_content_01" nowrap>${personalInfo.officePhone}</td>
					</tr>
					<tr>
						<td class="info_title_01" height="30" nowrap><!-- 住址电话 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.home" module="hrm"/>
						</td>
						<td class="info_content_01" nowrap>${personalInfo.homePhone}</td>
					</tr>
					<tr>
						<td class="info_title_01" height="30" nowrap><!-- 手机 -->
							<ait:message  messageID="display.emp.staff_info.social_relations.mobile_phone" module="hrm"/>
						</td>
						<td class="info_content_01" nowrap>${personalInfo.cellPhone}</td>
					</tr>
					<tr>
						<td class="info_title_01" height="30" nowrap><!-- Fax -->
							<ait:message  messageID="display.mutual.fax"/>
						</td>
						<td class="info_content_01" nowrap>${personalInfo.fax}</td>
					</tr>
					<tr>
						<td colspan="2" class="info_title_01" height="30" nowrap><!-- e-Mail -->
							<ait:message  messageID="display.mutual.e_mail"/>
						</td>
						<td class="info_content_01" nowrap>${personalInfo.email}</td>
					</tr>
					<tr>
						<td colspan="2" class="info_title_01" height="30" nowrap><!-- 档案关系 -->
							<ait:message  messageID="display.mutual.personnel_file"/>
						</td>
						<td class="info_content_01" nowrap>
								<ait:content enContent="${personalInfo.enFileRelation}" zhContent="${personalInfo.fileRelation}" koContent="${personalInfo.korFileRelation}"/>
							</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
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
							<td class="info_content_01" height="30" nowrap><!-- <c:out
								value='${oneResult.relationalType}' /> -->
								<ait:content enContent="${oneResult.enRelationalType}" zhContent="${oneResult.relationalType}" koContent="${oneResult.korRelationalType}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.famName}</td>
							<td class="info_content_01" nowrap>${oneResult.famBornDate}</td>
							<td class="info_content_01" nowrap><!-- <c:out
								value='${oneResult.famOccupation}' /> -->
								<ait:content enContent="${oneResult.enFamOccupation}" zhContent="${oneResult.famOccupation}" koContent="${oneResult.korFamOccupation}"/>
							</td>
							<td class="info_content_01" nowrap>${oneResult.famIDCard}</td>
							<td class="info_content_01" nowrap><!-- <c:out
								value='${oneResult.famDegree}' /> -->
								<ait:content enContent="${oneResult.enFamDegree}" zhContent="${oneResult.famDegree}" koContent="${oneResult.korFamDegree}"/>
							</td>
							<input type="hidden" name="famName_exist${i.count}"
								id="famName_exist${i.count}" value="${oneResult.famName}">
						</tr>
					</c:forEach>
					<c:forEach var="i" begin="1" end="3" step="1">
						<tr>
							<td class="info_content_01" height="30"><ait:codeClass
								name="relationalTypeCode_${i}" codeClass="RelationalTypeCode"
								all="ALL" /></td>
							<td class="info_content_01"><input type="text"
								class="content" name="famName_${i}" id="famName_${i}" /></td>
							<td class="info_content_01"><input type="text" size="10"
								class="content" name="famBornDate_${i}" id="famBornDate_${i}"
								onClick="setday(this);" date mask="9999-99-99" /></td>
							<td class="info_content_01"><ait:codeClass
								name="famOccupationCode_${i}" codeClass="OccupCode" all="ALL" /></td>
							<td class="info_content_01"><input type="text"
								class="content" name="famIDCard_${i}" id="famIDCard_${i}" /></td>
							<td class="info_content_01" nowrap><ait:codeClass
								name="famDegreeCode_${i}" codeClass="DegreeCode" all="ALL" /></td>
							<input type="hidden" name="creatorID_${i}" value="${adminId}">
							<input type="hidden" name="empID_${i}" value="${empID}" />
						</tr>
					</c:forEach>
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
						<td height="30" class="info_content_01" nowrap>${personalInfo.homeAddress}</td>
						<td class="info_content_01" nowrap>${personalInfo.postalCode}</td>
						<td class="info_content_01" nowrap>${personalInfo.regPlace}</td>
						<td class="info_content_01" nowrap><!-- ${personalInfo.regType} -->
								<ait:content enContent="${personalInfo.enRegType}" zhContent="${personalInfo.regType}" koContent="${personalInfo.korRegType}"/>
							</td>
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
					<c:choose>
						<c:when test="${militaryServiceInfo.empID != null}">
							<tr>
								<td height="30" class="info_content_01" nowrap><!--${militaryServiceInfo.militaryType} -->
							<ait:content enContent="${militaryServiceInfo.enMilitaryType}" zhContent="${militaryServiceInfo.militaryType}" koContent="${militaryServiceInfo.korMilitaryType}"/>
						</td>
								<td class="info_content_01" nowrap><!--${militaryServiceInfo.militaryLevel} -->
							<ait:content enContent="${militaryServiceInfo.enMilitaryLevel}" zhContent="${militaryServiceInfo.militaryLevel}" koContent="${militaryServiceInfo.korMilitaryLevel}"/>
						</td>
								<td class="info_content_01" nowrap><!--${militaryServiceInfo.militaryArea} -->
							<ait:content enContent="${militaryServiceInfo.enMilitaryArea}" zhContent="${militaryServiceInfo.militaryArea}" koContent="${militaryServiceInfo.korMilitaryArea}"/>
						</td>
								<td class="info_content_01" nowrap>${militaryServiceInfo.startTime}</td>
								<td class="info_content_01" nowrap>${militaryServiceInfo.endTime}</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td height="30" class="info_content_01" nowrap><ait:codeClass
									name="militaryTypeCode" codeClass="MilitaryTypeCode" all="ALL" /></td>
								<td class="info_content_01" nowrap><ait:codeClass
									name="militaryLevelCode" codeClass="MilitaryLevelCode"
									all="ALL" /></td>
								<td class="info_content_01" nowrap><ait:codeClass
									name="militaryAreaCode" codeClass="MilitaryAreaCode" all="ALL" /></td>
								<td class="info_content_01" nowrap><input type="text"
									size="10" name="startTime" id="startTime"
									onClick="setday(this);" date mask="9999-99-99" /></td>
								<td class="info_content_01" nowrap><input type="text"
									size="10" name="endTime" id="endTime" onClick="setday(this);"
									date mask="9999-99-99" /></td>
								<input type="hidden" name="creatorID"
									value="<c:out value='${adminId}'/>">
								<input type="hidden" name="empID"
									value="<c:out value='${empID}'/>" />
							</tr>
						</c:otherwise>
					</c:choose>
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
						<td width="20%" height="30" class="info_title_01" nowrap><!-- 宗教 -->
							<ait:message  messageID="display.mutual.religion"/>
						</td>
						<td width="40%" class="info_title_01" nowrap><!-- 爱好/兴趣 -->
							<ait:message  messageID="display.mutual.hobby"/>
						</td>
						<td width="40%" class="info_title_01" nowrap><!-- 特长 -->
							<ait:message  messageID="display.mutual.skill"/>
						</td>
					</tr>
					<tr>
						<td height="30" class="info_content_01" nowrap><!-- ${personalInfo.religion} -->
								<ait:content enContent="${personalInfo.enReligion}" zhContent="${personalInfo.religion}" koContent="${personalInfo.korReligion}"/>
							</td>
						<td class="info_content_01" nowrap>${personalInfo.hobby}</td>
						<td class="info_content_01" nowrap>${personalInfo.speciality}</td>
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


<input type="hidden" name="familySign" id="familySign"> <input
	type="hidden" name="militarySign" id="militarySign"></form>
</body>
<ait:xjos />
</html>
