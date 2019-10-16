<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;个人信息</title>
<script language="javascript" src="../js/hr_add_basic.js"></script>
<SCRIPT type="text/javascript">
function Save(){
	var school = '<ait:message  messageID="alert.emp.staff_info.basic_info.school_name_must_be_entered" module="hrm"/>';
	var lan = '<ait:message  messageID="alert.emp.staff_info.basic_info.language_type_must_be_entered" module="hrm"/>';	
	var exam = '<ait:message  messageID="alert.emp.staff_info.basic_info.exam_name_must_be_entered" module="hrm"/>';	
	var lanLev = '<ait:message  messageID="alert.emp.staff_info.basic_info.language_level_must_be_entered" module="hrm"/>';	
	var itName = '<ait:message  messageID="alert.emp.staff_info.basic_info.it_exam_must_be_entered" module="hrm"/>';	
	var itLev = '<ait:message  messageID="alert.emp.staff_info.basic_info.it_level_must_be_entered" module="hrm"/>';
	var array = new Array(school,lan,exam,lanLev,itName,itLev);
	if(CheckForm(array)) {	
		document.form1.action = "hrmControlServlet?menu_code=hr0101&operation=insertBaseInfo&empID=<c:out value='${empID}'/>";
		document.form1.fireSubmit();
	}
 }

</SCRIPT>

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
			<tr height="20">
				<td width="49%" class="title1"><!-- 毕业学校 -->
					<ait:message  messageID="display.emp.staff_info.basic_info.graduated_from" module="hrm"/>
				</td>
				<td width="2%" rowspan="10"></td>
				<td width="49%"></td>
			</tr>
			<tr height="60">
				<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td class="info_title_01" height="30" nowrap><!-- 学校名 -->
						<ait:message  messageID="display.emp.staff_info.basic_info.school" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 专业 -->
						<ait:message  messageID="display.mutual.major"/>
						</td>
						<td class="info_title_01" nowrap><!-- 入学日期 -->
						<ait:message  messageID="display.emp.staff_info.basic_info.graduated_from.start_time" module="hrm"/>
						</td>
						<td class="info_title_01" nowrap><!-- 毕业日期 -->
						<ait:message  messageID="display.emp.staff_info.basic_info.graduated_from.end_time" module="hrm"/>						
						</td>
						<td class="info_title_01" nowrap><!-- 学历 -->
						<ait:message  messageID="display.emp.staff_info.basic_info.diploma" module="hrm"/>						
						</td>
					    <td class="info_title_01" nowrap><!-- 所在地 -->
						<ait:message  messageID="display.emp.staff_info.basic_info.city" module="hrm"/>						
						</td>
					</tr>
					<c:forEach items="${educationInfoList}" var="oneResult">
						<tr>
							<td class="info_content_01" nowrap height="30"><c:out
								value='${oneResult.institutionName}' /></td>
							<td class="info_content_01" nowrap>
								<!--<c:out value='${oneResult.subject}' />-->
								<ait:content enContent="${oneResult.englishSubject}" zhContent="${oneResult.subject}" koContent="${oneResult.korSubject}"/>
							</td>
							<td class="info_content_01" nowrap><c:out
								value='${oneResult.startDate}' /></td>
							<td class="info_content_01" nowrap><c:out
								value='${oneResult.endDate}' /></td>
							<td class="info_content_01" nowrap>
								<!--<c:out value='${oneResult.degree}' />-->
								<ait:content enContent="${oneResult.englishDegree}" zhContent="${oneResult.degree}" koContent="${oneResult.korDegree}"/>
							</td>
							<td class="info_content_01" nowrap><c:out
								value='${oneResult.schoolAddress}' /></td>
						</tr>
					</c:forEach>
					<c:forEach var="i" begin="1" end="3" step="1">
						<tr>
							<td class="info_content_01" height="30"><input type="text"
								class="content" name="institutionName_<c:out value='${i}'/>"
								id="institutionName_<c:out value='${i}'/>" /></td>
							<td class="info_content_01"><ait:codeClass
								name="subjectCode_${i}" codeClass="SubjectCode" all="ALL" /></td>
							<td class="info_content_01"><input type="text" size="10"
								class="content" name="startDate_<c:out value='${i}'/>"
								id="startDate_<c:out value='${i}'/>" onClick="setday(this);"
								date mask="9999-99-99" /></td>
							<td class="info_content_01"><input type="text" size="10"
								class="content" name="endDate_<c:out value='${i}'/>"
								id="endDate_<c:out value='${i}'/>" onClick="setday(this);" date
								mask="9999-99-99" /></td>
							<td class="info_content_01"><ait:codeClass
								name="degreeCode_${i}" codeClass="DegreeCode" all="ALL" /></td>
							<td class="info_content_01" height="30"><input type="text"
								class="content" name="schoolAddress_<c:out value='${i}'/>"
								id="schoolAddress_<c:out value='${i}'/>" /></td>
						</tr>
					</c:forEach>
				</table>
				</td>
				<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td class="info_title_01" width="20%"><!-- 最高学历 -->
						<ait:message  messageID="display.mutual.diploma"/>						
						</td>
						<td height="30" class="info_content_01" width="30%">
							<!--<c:out value='${personalInfo.beforeDegree}' />-->
							<ait:content enContent="${personalInfo.enBeforeDegree}" zhContent="${personalInfo.beforeDegree}" koContent="${personalInfo.korBeforeDegree}"/>
						</td>
					   <td class="info_title_01" width="20%"><!-- 保险缴费类型 -->
						<ait:message  messageID="display.emp.staff_info.basic_info.insurance" module="hrm"/>					
						</td>                              
				       <td height="30" class="info_content_01" width="30%"><!--<c:out
							value='${personalInfo.work_area_name}' />-->
							<ait:content enContent="${personalInfo.enWorkAreaName}" zhContent="${personalInfo.work_area_name}" koContent="${personalInfo.korWorkAreaName}"/>
						</td>
					</tr>             
					<tr>    
						<td class="info_title_01" 　width="20%"><!-- 国籍 -->
						<ait:message  messageID="display.mutual.nationality"/>	
						</td>
						<td height="30" class="info_content_01" width="30%"><!--<c:out
							value='${personalInfo.natioNality}' />-->
							<ait:content enContent="${personalInfo.enNationNality}" zhContent="${personalInfo.natioNality}" koContent="${personalInfo.korNationNality}"/>
						</td>
						<td class="info_title_01" width="20%"><!-- 性别 -->
						<ait:message  messageID="display.mutual.sex"/>
						</td>
						<td class="info_content_01" width="30%"><!--<c:out
							value='${personalInfo.sex}' />-->
							<ait:content enContent="${personalInfo.enlishSex}" zhContent="${personalInfo.sex}" koContent="${personalInfo.korSex}"/>
						</td>
					</tr>
					<tr>
						<td class="info_title_01" width="20%"><!-- 籍贯 -->
						<ait:message  messageID="display.mutual.native_place"/>	
						</td>
						<td height="30" class="info_content_01" width="30%">
						<c:if test="${personalInfo.parentBornPlace==personalInfo.bornPlace}">
						<!--<c:out
							value='${personalInfo.parentBornPlace}' />-->
							<ait:content enContent="${personalInfo.enParentBornPlace}" zhContent="${personalInfo.parentBornPlace}" koContent="${personalInfo.korParentBornPlace}"/>
						            
						</c:if>
						<c:if test="${personalInfo.parentBornPlace!=personalInfo.bornPlace}">
						<!--<c:out
							value='${personalInfo.parentBornPlace}' />
							<c:out value='${personalInfo.bornPlace}' /> -->
							<%---- 
							<ait:content enContent="${personalInfo.enParentBornPlace}" zhContent="${personalInfo.parentBornPlace}" koContent="${personalInfo.korParentBornPlace}"/>
							--%><ait:content enContent="${personalInfo.enBornPlace}" zhContent="${personalInfo.bornPlace}" koContent="${personalInfo.korBornPlace}"/>
							</c:if></td>
						<td class="info_title_01" width="20%"><!-- 民族 -->
						<ait:message  messageID="display.mutual.race"/>	
						</td>
						<td class="info_content_01" width="30%"><!--<c:out
		  					value='${personalInfo.nation}' />-->
							<ait:content enContent="${personalInfo.enNation}" zhContent="${personalInfo.nation}" koContent="${personalInfo.korNation}"/>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" class="info_title_01"><!-- 政治面貌 -->
						<ait:message  messageID="display.mutual.political_status"/>	
						</td>
						<td width="30%" class="info_content_01"><!--<c:out
							value='${personalInfo.polity}' />-->
							<ait:content enContent="${personalInfo.enPolity}" zhContent="${personalInfo.polity}" koContent="${personalInfo.korPolity}"/>
						  </td>                                 
						<td class="info_title_01" width="25%"><!-- 转正日期 -->
						<ait:message  messageID="display.mutual.off_probation_date"/>	
						</td>
						<td width="25%" height="30" class="info_content_01"><c:out
							value='${personalInfo.formal_date}' /></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr height="1">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr height="20">
				<td class="title1"><!--外国语-->
					<ait:message  messageID="display.emp.staff_info.basic_info.foreign_language" module="hrm"/>	
				</td>
				<td class="title1"><!-- 研修/留学 -->
					<ait:message  messageID="display.emp.staff_info.basic_info.training.training_study_abroad" module="hrm"/>	
				</td>
			</tr>
			<tr height="60">
				<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td class="info_title_01" height="30"><!-- 语言类型 -->
							<ait:message  messageID="display.emp.staff_info.basic_info.foreign_language.language" module="hrm"/>	
						</td>
						<td class="info_title_01"><!-- 考试名 -->
							<ait:message  messageID="display.emp.staff_info.basic_info.foreign_language.exam" module="hrm"/>	
						</td>
						<td class="info_title_01"><!-- 等级 -->
							<ait:message  messageID="display.mutual.level"/>	
						</td>
						<td class="info_title_01"><!-- 分数 -->
							<ait:message  messageID="display.mutual.mark"/></td>
						<td class="info_title_01"><!-- 取得日期 -->
							<ait:message  messageID="display.emp.staff_info.basic_info.foreign_language.date_obtained" module="hrm"/>	
						</td>
					</tr>
					<c:forEach items="${lanuageInfoList}" var="oneResult">
						<tr>
							<td class="info_content_01" height="30" nowrap><!--<c:out
								value='${oneResult.languageType}' />-->
								<ait:content enContent="${oneResult.enLanguageType}" zhContent="${oneResult.languageType}" koContent="${oneResult.korLanguageType}"/>
							  </td>
							<td class="info_content_01" nowrap><!--<c:out
								value='${oneResult.examName}' />-->
								<ait:content enContent="${oneResult.enExamName}" zhContent="${oneResult.examName}" koContent="${oneResult.korExamName}"/>
							  </td>
							<td class="info_content_01" nowrap><!--<c:out
								value='${oneResult.languageLevel}' />-->
								<ait:content enContent="${oneResult.enLanguageLevel}" zhContent="${oneResult.languageLevel}" koContent="${oneResult.korLanguageLevel}"/>
							  </td>
							<td class="info_content_01" nowrap><c:out
								value='${oneResult.mark}' /></td>
							<td class="info_content_01" nowrap><c:out
								value='${oneResult.obtainedDate}' /></td>
						</tr>
					</c:forEach>
					<c:forEach var="i" begin="1" end="3" step="1">
						<tr>
							<td class="info_content_01" height="30"><ait:codeClass
								name="languageTypeCode_${i}" codeClass="LanguageTypeCode"
								all="ALL" /></td>
							<td class="info_content_01"><ait:codeClass
								name="examNameCode_${i}" codeClass="LanguageExamCode" all="ALL" /></td>
							<td class="info_content_01"><ait:codeClass
								name="languageLevelCode_${i}" codeClass="LanguageLevelCode"
								all="ALL" /></td>
							<td class="info_content_01"><input type="text"
								class="content" name="mark_<c:out value='${i}'/>"
								id="mark_<c:out value='${i}'/>" /></td>
							<td class="info_content_01"><input type="text" size="10"
								class="content" name="obtainedDate_<c:out value='${i}'/>"
								id="obtainedDate_<c:out value='${i}'/>" onClick="setday(this);"
								date mask="9999-99-99" /></td>
						</tr>
					</c:forEach>
				</table>
				</td>
				<td rowspan="2" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td class="info_title_01" width="25%" height="30"><!-- 名称 -->
							<ait:message  messageID="display.emp.staff_info.basic_info.training.title" module="hrm"/>	
						</td>
						<td class="info_title_01" width="25%"><!-- 义务服期 -->
							<ait:message  messageID="display.mutual.voluntary_work"/>	
						</td>
					</tr>
					<c:forEach items="${studyList}" var="oneResult">
						<tr>
							<td class="info_content_01" nowrap height="30"><!-- ${oneResult.researchType} -->
								<ait:content enContent="${oneResult.enResearchType}" zhContent="${oneResult.researchType}" koContent="${oneResult.korResearchType}"/>
							  </td>
							<td class="info_content_01" nowrap>${oneResult.period}</td>
						</tr>
					</c:forEach>
					<c:forEach items="${study2List}" var="oneResult">
						<tr>
							<td class="info_content_01" nowrap height="30"><!-- ${oneResult.studyType} -->
								<ait:content enContent="${oneResult.enStudyType}" zhContent="${oneResult.studyType}" koContent="${oneResult.korStudyType}"/>
							  </td>
							<td class="info_content_01" nowrap>${oneResult.period}</td>
						</tr>
					</c:forEach>
					<c:if test="${contractInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-contractInfoCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30"></td>
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
			<tr height="20">
				<td class="title1"><!-- IT能力 -->
					<ait:message  messageID="display.emp.staff_info.basic_info.IT_ability" module="hrm"/>	
				</td>
				<td class="title1"><!-- 合同信息 -->
					<ait:message  messageID="display.emp.staff_info.basic_info.contract_info" module="hrm"/>	
				</td>
			</tr>
			<tr height="60">
				<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td class="info_title_01" height="30"><!-- IT考试名 -->
							<ait:message  messageID="display.emp.staff_info.basic_info.IT_exam" module="hrm"/>	
						</td>
						<td class="info_title_01"><!-- 等级 -->
							<ait:message  messageID="display.mutual.level"/>	
						</td>
						<td class="info_title_01"><!-- 分数 -->
							<ait:message  messageID="display.mutual.mark"/>	
						</td>
						<td class="info_title_01"><!-- 取得日期 -->
							<ait:message  messageID="display.emp.staff_info.basic_info.IT_ability.date_obtained" module="hrm"/>	
						</td>
					</tr>
					<c:forEach items="${ITLevelInfoList}" var="oneResult">
						<tr>
							<td class="info_content_01" nowrap height="30"><!--<c:out
								value='${oneResult.itExamName}' />-->
								<ait:content enContent="${oneResult.enItExamName}" zhContent="${oneResult.itExamName}" koContent="${oneResult.korItExamName}"/>
							  </td>
							<td class="info_content_01" nowrap><!--<c:out
								value='${oneResult.itLevel}' />-->
								<ait:content enContent="${oneResult.enItLevel}" zhContent="${oneResult.itLevel}" koContent="${oneResult.korItLevel}"/>
							 </td>
							<td class="info_content_01" nowrap><c:out
								value='${oneResult.itMark}' /></td>
							<td class="info_content_01" nowrap><c:out
								value='${oneResult.obtainedDate}' /></td>
						</tr>
					</c:forEach>
					<c:forEach var="i" begin="1" end="3" step="1">
						<tr>
							<td class="info_content_01" height="30"><ait:codeClass
								name="itExamNameCode_@${i}" codeClass="ITExamCode" all="ALL" /></td>
							<td class="info_content_01"><ait:codeClass
								name="itLevelCode_@${i}" codeClass="ITLevelCode" all="ALL" /></td>
							<td class="info_content_01"><input type="text"
								class="content" name="itMark_@<c:out value='${i}'/>"
								id="itMark_@<c:out value='${i}'/>" /></td>
							<td class="info_content_01"><input type="text" size="10"
								class="content" name="obtainedDate_@<c:out value='${i}'/>"
								id="obtainedDate_@<c:out value='${i}'/>" onClick="setday(this);"
								date mask="9999-99-99" /></td>
							<input type="hidden" name="empID_@<c:out value='${i}'/>"
								value="<c:out value='${empID}'/>" />
							<input type="hidden" name="creatorID_@<c:out value='${i}'/>"
								value="<c:out value='${adminId}'/>">
						</tr>
					</c:forEach>
				</table>
				</td>
				<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td class="info_title_01" height="30"><!-- 次数 -->
							<ait:message  messageID="display.emp.staff_info.basic_info.time" module="hrm"/>
						</td>
						<td class="info_title_01"><!-- 开始日 -->
							<ait:message  messageID="display.emp.staff_info.basic_info.contract_info.start_date" module="hrm"/>
						</td>
						<td class="info_title_01"><!-- 结束日 -->
							<ait:message  messageID="display.emp.staff_info.basic_info.contract_info.end_date" module="hrm"/>
						</td>
						<td class="info_title_01"><!-- 期间 -->
							<ait:message  messageID="display.mutual.period"/>
						</td>
					</tr>
					<c:forEach items="${contractInfoList}" var="oneResult"
						varStatus="i">
						<tr>
							<td class="info_content_01" nowrap height="30"><c:out
								value='${contractInfoCnt - (i.count -1)}' /></td>
							<td class="info_content_01" nowrap><c:out
								value='${oneResult.contractStartDate}' /></td>
							<td class="info_content_01" nowrap><c:out
								value='${oneResult.contractEndDate}' /></td>
							<td class="info_content_01" nowrap><c:out
								value='${oneResult.contractPeriod}' /><!-- 年 -->
								<ait:message messageID="display.mutual.year"/>
								</td>
						</tr>
					</c:forEach>
					<c:if test="${contractInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-contractInfoCnt}" step="1">
							<tr>
								<td class="info_content_01" height="30"></td>
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

<c:forEach var="i" begin="1" end="3" step="1">
	<input type="hidden" name="creatorID_<c:out value='${i}'/>"
		value="<c:out value='${adminId}'/>">
	<input type="hidden" name="empID_<c:out value='${i}'/>"
		value="<c:out value='${empID}'/>" />
</c:forEach> <input type="hidden" name="educationSign" id="educationSign"> <input
	type="hidden" name="languageSign" id="languageSign"> <input
	type="hidden" name="ITLeveSign" id="ITLeveSign"></form>
</body>
<ait:xjos />
</html>
