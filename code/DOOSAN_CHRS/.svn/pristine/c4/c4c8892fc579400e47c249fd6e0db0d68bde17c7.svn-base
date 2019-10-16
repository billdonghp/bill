<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>

<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>人事信息&gt;社会关系</title>
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
				<td width="63%" class="title1"><!-- 家人关系 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.family" module="ess"/>
						</td>
				<td width="37%" class="title1"><!-- 电话/e-Mail -->
							<ait:message  messageID="display.ess.staff_info.social_relations.contact" module="ess"/>
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
						<td width="16%" rowspan="4" class="info_title_01" nowrap="nowrap"><!-- 电<br>话 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.tel" module="ess"/>
						</td>
						<td width="20%" class="info_title_01" height="30" nowrap="nowrap"><!-- 办公室 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.office" module="ess"/>
						</td>
						<td width="64%" class="info_content_01" nowrap="nowrap">${personalInfo.officePhone}</td>
					</tr>
					<tr>
						<td class="info_title_01" height="30" nowrap="nowrap"><!-- 住址电话 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.home" module="ess"/>
						</td>
						<td class="info_content_01" nowrap="nowrap">${personalInfo.homePhone}</td>
					</tr>
					<tr>
						<td class="info_title_01" height="30" nowrap="nowrap"><!-- 手机 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.mobile_phone" module="ess"/>
						</td>
						<td class="info_content_01" nowrap="nowrap">${personalInfo.cellPhone}</td>
					</tr>
					<tr>
						<td class="info_title_01" height="30" nowrap="nowrap"><!-- Fax -->
							<ait:message  messageID="display.mutual.fax"/>
						</td>
						<td class="info_content_01" nowrap="nowrap">${personalInfo.fax}</td>
					</tr>
					<tr>
						<td colspan="2" class="info_title_01" height="30" nowrap="nowrap"><!-- e-Mail -->
							<ait:message  messageID="display.mutual.e_mail"/>
						</td>
						<td class="info_content_01" nowrap="nowrap">${personalInfo.email}</td>
					</tr>
					<tr>
						<td colspan="2" class="info_title_01" height="30" nowrap="nowrap"><!-- 档案关系 -->
							<ait:message  messageID="display.mutual.personnel_file"/>
						</td>
						<td class="info_content_01" nowrap="nowrap">
								<ait:content enContent="${personalInfo.enFileRelation}" zhContent="${personalInfo.fileRelation}" koContent="${personalInfo.korFileRelation}"/>
							</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr height="95">
				<td>
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td class="info_title_01" height="30" nowrap="nowrap"><!-- 关系 -->
							<ait:message  messageID="display.mutual.relation"/>
						</td>
						<td class="info_title_01" nowrap="nowrap"><!-- 姓名 -->
							<ait:message  messageID="display.mutual.name"/>
						</td>
						<td class="info_title_01" nowrap="nowrap"><!-- 出生日期 -->
							<ait:message  messageID="display.mutual.birth_date"/>
						</td>
						<td class="info_title_01" nowrap="nowrap"><!-- 职业 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.vocation" module="ess"/>
						</td>
						<td class="info_title_01" nowrap="nowrap"><!-- 工作单位 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.company" module="ess"/>
						</td>
						<td class="info_title_01" nowrap="nowrap"><!-- 最终学历 -->
							<ait:message  messageID="display.mutual.diploma_2"/>
						</td>
					</tr>
					<c:forEach items="${familyInfoList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap="nowrap"><!-- <c:out
								value='${oneResult.relationalType}' /> -->
								<ait:content enContent="${oneResult.enRelationalType}" zhContent="${oneResult.relationalType}" koContent="${oneResult.korRelationalType}"/>
							</td>
							<td class="info_content_01" nowrap="nowrap"> <c:out
								value='${oneResult.famName}' /> 
								<!--<ait:content enContent="${oneResult.famPinYin}" zhContent="${oneResult.famName}"/>-->
							</td>
							<td class="info_content_01" nowrap="nowrap"><c:out
								value='${oneResult.famBornDate}' /></td>
							<td class="info_content_01" nowrap="nowrap"><!-- <c:out
								value='${oneResult.famOccupation}' /> -->
								<ait:content enContent="${oneResult.enFamOccupation}" zhContent="${oneResult.famOccupation}" koContent="${oneResult.korFamOccupation}"/>
							</td>
							<td class="info_content_01" nowrap="nowrap"><c:out
								value='${oneResult.famIDCard}' /></td>
							<td class="info_content_01" nowrap="nowrap"><!-- <c:out
								value='${oneResult.famDegree}' /> -->
								<ait:content enContent="${oneResult.enFamDegree}" zhContent="${oneResult.famDegree}" koContent="${oneResult.korFamDegree}"/>
							</td>
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
			<tr height="1">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td class="title1" nowrap="nowrap"="nowrap="nowrap""><!-- 住址 -->
					<ait:message  messageID="display.ess.staff_info.social_relations.address" module="ess"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td height="30" class="info_title_01" nowrap="nowrap"="nowrap="nowrap""><!-- 现住址 -->
							<ait:message  messageID="display.mutual.current_address"/>
						</td>
						<td class="info_title_01"   nowrap="nowrap"="nowrap="nowrap""><!-- 邮编 -->
							<ait:message  messageID="display.mutual.postal_code"/>
						</td>
						<td class="info_title_01" nowrap="nowrap"="nowrap="nowrap""><!-- 身份证地址 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.address_on_idcard" module="ess"/>
						</td>
						<td class="info_title_01" nowrap="nowrap"><!-- 户口性质 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.hukou" module="ess"/>
						</td>
					</tr>
					<tr>
						<td height="30" class="info_content_01" nowrap="nowrap">${personalInfo.homeAddress}</td>
						<td class="info_content_01" nowrap="nowrap">${personalInfo.postalCode}</td>
						<td class="info_content_01" nowrap="nowrap">${personalInfo.regPlace}</td>
						<td class="info_content_01" nowrap="nowrap"><!-- ${personalInfo.regType} -->
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
					<ait:message  messageID="display.ess.staff_info.social_relations.military_service" module="ess"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td height="30" class="info_title_01" width="20%" nowrap="nowrap"><!-- 军别 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.army" module="ess"/>
						</td>
						<td class="info_title_01" width="20%" nowrap="nowrap"><!-- 军级 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.military_rank" module="ess"/>
						</td>
						<td class="info_title_01" width="20%" nowrap="nowrap"><!-- 军区 -->
							<ait:message  messageID="display.ess.staff_info.social_relations.military_region" module="ess"/>
						</td>
						<td class="info_title_01" width="20%" nowrap="nowrap"><!-- 开始日期 -->
							<ait:message  messageID="display.mutual.start_date"/>
						</td>
						<td class="info_title_01" width="20%" nowrap="nowrap"><!-- 结束日期 -->
							<ait:message  messageID="display.mutual.end_date"/>
						</td>
					</tr>
					<tr>
						<td height="30" class="info_content_01" nowrap="nowrap"><!--${militaryServiceInfo.militaryType} -->
							<ait:content enContent="${militaryServiceInfo.enMilitaryType}" zhContent="${militaryServiceInfo.militaryType}" koContent="${militaryServiceInfo.korMilitaryType}"/>
						</td>
						<td class="info_content_01" nowrap="nowrap"><!--${militaryServiceInfo.militaryLevel} -->
							<ait:content enContent="${militaryServiceInfo.enMilitaryLevel}" zhContent="${militaryServiceInfo.militaryLevel}" koContent="${militaryServiceInfo.korMilitaryLevel}"/>
						</td>
						<td class="info_content_01" nowrap="nowrap"><!--${militaryServiceInfo.militaryArea} -->
							<ait:content enContent="${militaryServiceInfo.enMilitaryArea}" zhContent="${militaryServiceInfo.militaryArea}" koContent="${militaryServiceInfo.korMilitaryArea}"/>
						</td>
						<td class="info_content_01" nowrap="nowrap">${militaryServiceInfo.startTime}</td>
						<td class="info_content_01" nowrap="nowrap">${militaryServiceInfo.endTime}</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr height="1">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td class="title1"><!-- 其他信息 -->
					<ait:message  messageID="display.ess.staff_info.social_relations.other_information" module="ess"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td width="20%" height="30" class="info_title_01" nowrap="nowrap"><!-- 宗教 -->
							<ait:message  messageID="display.mutual.religion"/>
						</td>
						<td width="40%" class="info_title_01" nowrap="nowrap"><!-- 爱好/兴趣 -->
							<ait:message  messageID="display.mutual.hobby"/>
						</td>
						<td width="40%" class="info_title_01" nowrap="nowrap"><!-- 特长 -->
							<ait:message  messageID="display.mutual.skill"/>
						</td>
					</tr>
					<tr>
						<td height="30" class="info_content_01" nowrap="nowrap"><!-- ${personalInfo.religion} -->
								<ait:content enContent="${personalInfo.enReligion}" zhContent="${personalInfo.religion}" koContent="${personalInfo.korReligion}"/>
							</td>
						<td class="info_content_01" nowrap="nowrap">${personalInfo.hobby}</td>
						<td class="info_content_01" nowrap="nowrap">${personalInfo.speciality}</td>
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
</form>
</body>
</html>