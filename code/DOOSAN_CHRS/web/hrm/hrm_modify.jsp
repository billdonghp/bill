<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<script language="javascript" src="../js/prototype/prototype.js" /></script>
<title>LSFC&gt;人事信息&gt;个人信息</title>
<script language="javascript" src="../js/hr_modify_basic.js"></script>
<SCRIPT type="text/javascript">

var bornCode='${personalInfo.bornPlaceCode}';
            
function Save(){
    var school = '<ait:message  messageID="alert.emp.staff_info.basic_info.school_name_must_be_entered" module="hrm"/>';
	var lan = '<ait:message  messageID="alert.emp.staff_info.basic_info.language_type_must_be_entered" module="hrm"/>';	
	var exam = '<ait:message  messageID="alert.emp.staff_info.basic_info.exam_name_must_be_entered" module="hrm"/>';	
	var lanLev = '<ait:message  messageID="alert.emp.staff_info.basic_info.language_level_must_be_entered" module="hrm"/>';	
	var itName = '<ait:message  messageID="alert.emp.staff_info.basic_info.it_exam_must_be_entered" module="hrm"/>';	
	var itLev = '<ait:message  messageID="alert.emp.staff_info.basic_info.it_level_must_be_entered" module="hrm"/>';
	var array = new Array(school,lan,exam,lanLev,itName,itLev);
	if(CheckForm(array)) {
		document.form1.action = "hrmControlServlet?menu_code=hr0101&operation=updateBaseInfo&empID=<c:out value='${empID}'/>";
		document.form1.fireSubmit();
	}           
}
function updateBPC(obj)
{
    var url = '/codeServlet';
    var pars = 'pc='+$F(obj);
   	var myAjax = new Ajax.Request(
                url,
                {method: 'get', parameters: pars, onComplete: showBornPlace}
                );
}
function showBornPlace(originalRequest)
{
    //put returned XML to the select
    
    var sel=$("bornPlaceCode");
    sel.options.length=0;
    
    var xml = originalRequest.responseXML;
    var root=xml.documentElement;
    var responseNodes=root.getElementsByTagName("response");
    if(responseNodes.length>0){
    	var responseNode=responseNodes[0];
    	var itemNodes=responseNode.getElementsByTagName("item");
    	for(var i=0,j=itemNodes.length;i<j;i++){
    		var nameNodes=itemNodes[i].getElementsByTagName("name");
    		var valueNodes=itemNodes[i].getElementsByTagName("value");
    		if(nameNodes.length>0&&valueNodes.length>0){
	    		var option=new Option(nameNodes[0].firstChild.nodeValue,valueNodes[0].firstChild.nodeValue);
	    		
	    		if(bornCode==valueNodes[0].firstChild.nodeValue){
	    			option.selected=true;
	    		}
	    		
	    		sel.add(option);
    		}
    	}
    }
}
</SCRIPT>
</head>
<body>
<form name="form1" method="post">

<table width="100%" border="0" cellspacing="0" cellpadding="0" >
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
		<%@ include file="../hrm/hrm_modify_basic.jsp"%> 
		
		<!-- display content -->
		<table width="100%" border="0" cellpadding="0">
			<tr height="20">
				<td width="49%" class="title1"><!-- 毕业学校 -->
					<ait:message  messageID="display.emp.staff_info.basic_info.graduated_from" module="hrm"/>
				</td>
				<td width="2%" rowspan="10"></td>
				<td width="49%"></td>
			</tr>
			<tr height="40">
				<td colspan="3">
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
					<c:forEach items="${educationInfoList}" var="oneResult"
						varStatus="i">
						<tr>
							<td class="info_content_01" height="30"><input type="text"
								class="content" name="institutionName_@${i.count}"
								id="institutionName_@${i.count}"
								value="<c:out value='${oneResult.institutionName}'/>" /></td>
							<td class="info_content_01"><ait:codeClass
								name="subjectCode_@${i.count}" codeClass="SubjectCode"
								selected="${oneResult.subjectCode}" all="all" /></td>
							<td class="info_content_01"><input type="text"
								class="content" name="startDate_@${i.count}"
								id="startDate_@${i.count}" onClick="setday(this);"
								value="<c:out value='${oneResult.startDate}'/>" date
								mask="9999-99-99" /></td>
							<td class="info_content_01"><input type="text"
								class="content" name="endDate_@${i.count}"
								id="endDate_@${i.count}" onClick="setday(this);"
								value="<c:out value='${oneResult.endDate}'/>" date
								mask="9999-99-99" /></td>
							<td class="info_content_01"><ait:codeClass
								name="degreeCode_@${i.count}" codeClass="DegreeCode"
								selected="${oneResult.degreeCode}" all="all" /></td>
							<td class="info_content_01" height="30"><input type="text"
								class="content" name="schoolAddress_@${i.count}"
								id="schoolAddress_@${i.count}"
								value="<c:out value='${oneResult.schoolAddress}'/>" /></td>	
							<input type="hidden" name="educationNo_@${i.count}"
								id="educationNo_@${i.count}" value="${oneResult.educationNo}" />
							<input type="hidden" name="modifierID_@${i.count}"
								id="modifierID_@${i.count}" value="<c:out value='${adminId}'/>" />
						</tr>   
					</c:forEach>
					<c:if test="${educationInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-educationInfoCnt}" step="1">
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
				</td>
				<td valign="top"></td>
				</tr>
				<tr height="130">
				<td colspan="3">
				<br>
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td class="info_title_01" nowrap="nowrap"><!-- 最高学历 -->
							<ait:message  messageID="display.mutual.diploma"/>						
						</td>
						<td height="30" class="info_content_00"><ait:codeClass
							name="beforeDegreeCode" codeClass="DegreeCode"
							selected="${personalInfo.beforeDegreeCode}" all="all" /></td>
						<td class="info_title_01" nowrap="nowrap"><!-- 性别 -->
							<ait:message  messageID="display.mutual.sex"/>
						</td>
						<td class="info_content_00"><ait:codeClass
							name="sexCode" codeClass="SexCode"
							selected="${personalInfo.sexCode}" />
						</td>
						<td class="info_title_01" nowrap="nowrap"><!-- 国籍 -->
							<ait:message  messageID="display.mutual.nationality"/>	
						</td>						
						<td height="30" class="info_content_00"><ait:codeClass
							name="natioNalityCode" codeClass="NationalityCode"
							selected="${personalInfo.natioNalityCode}" />
						</td>
					</tr>
					<tr>
					 	<td class="info_title_01" style="float:left" nowrap="nowrap"><!-- 员工类别 -->
					 		<ait:message messageID="display.emp.staff_info.basic_info.staff_type"  module="hrm"/>
					 	</td>
					    <td height="30" align="left" class="info_content_00">
							<ait:codeClass codeClass="EmpDivision" name="joinTypeCode" selected="${basic.joinTypeCode}" />
						</td> 
					    <td class="info_title_01" nowrap="nowrap"><!-- 民族 -->
							<ait:message  messageID="display.mutual.race"/>	
						</td>
						<td class="info_content_00"><ait:codeClass
							name="nationCode" codeClass="NationCode"
							selected="${personalInfo.nationCode}" all="ALL" />
						</td>
						<td height="30" class="info_title_01" nowrap="nowrap"><!-- 政治面貌 -->
							<ait:message  messageID="display.mutual.political_status"/>	
						</td>
						<td class="info_content_00"><ait:codeClass
							name="polityCode" codeClass="PolityCode"
							selected="${personalInfo.polityCode}" all="ALL" />
						</td>
					</tr>
					<tr>
						<td class="info_title_01" nowrap="nowrap"><!-- 保险缴费类型 -->
							<ait:message  messageID="display.emp.staff_info.basic_info.insurance" module="hrm"/>							   
					   	</td>
						<td class="info_content_00"><ait:codeClass  
					       codeClass="BaoxianType" name="work_area" selected="${personalInfo.work_area}" all="all" />
						</td>
						<td class="info_title_01" nowrap="nowrap"><!-- 籍贯 -->
						<ait:message  messageID="display.mutual.native_place"/>	
						</td>
						<td height="30" class="info_content_00">
						<ait:codeClass
							name="bornPlaceCode" codeClass="BornPlaceCode"
							selected="${personalInfo.bornPlaceCode}"   all="ALL" />      
							<%-- 
<!--							<ait:codeClass-->
<!--							name="bornPlaceCode"-->
<!--							codeClass="${personalInfo.parentBornPlaceCode}"       -->
<!--							selected="${personalInfo.bornPlaceCode}" />-->
 --%> 
						</td>
						<input type="hidden" name="empID" value="<c:out value='${empID}'/>" />
						<input type="hidden" name="updatedBy" value="<c:out value='${adminId}'/>" />
						<td class="info_title_01" nowrap="nowrap"><!-- 转正日期 -->
							<ait:message  messageID="display.mutual.off_probation_date"/>	
						</td>
						<td class="info_content_00"><input
							name="formal_date"
							value="<c:out value='${personalInfo.formal_date}'/>"
							onClick="setday(this);" date mask="9999-99-99" />
						</td>
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
				<td rowspan="2" valign="top">
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
					<c:forEach items="${lanuageInfoList}" var="oneResult" varStatus="i">
						<tr>
							<td class="info_content_01" height="30"><ait:codeClass
								name="languageTypeCode_%${i.count}" codeClass="LanguageTypeCode"
								selected="${oneResult.languageTypeCode}" /></td>
							<td class="info_content_01"><ait:codeClass
								name="examNameCode_%${i.count}" codeClass="LanguageExamCode"
								selected="${oneResult.examNameCode}" /></td>
							<td class="info_content_01"><ait:codeClass
								name="languageLevelCode_%${i.count}"
								codeClass="LanguageLevelCode"
								selected="${oneResult.languageLevelCode}" /></td>
							<td class="info_content_01"><input type="text"
								class="content" name="mark_%${i.count}" id="mark_%${i.count}"
								value="<c:out value='${oneResult.mark}'/>" /></td>
							<td class="info_content_01"><input type="text" size="10"
								class="content" name="obtainedDate_%${i.count}"
								id="obtainedDate_%${i.count}"
								value="<c:out value='${oneResult.obtainedDate}'/>"
								onClick="setday(this);" date mask="9999-99-99" /></td>
							<input type="hidden" name="languageNo_%${i.count}"
								id="languageNo_%${i.count}"
								value="<c:out value='${oneResult.languageNo}'/>" />
							<input type="hidden" name="modifierID_%${i.count}"
								id="modifierID_%${i.count}" value="<c:out value='${adminId}'/>" />
						</tr>
					</c:forEach>
					<c:if test="${lanuageInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-lanuageInfoCnt}" step="1">
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
				<td>
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
							<td class="info_content_02" nowrap height="30"><!-- ${oneResult.researchType} -->
								<ait:content enContent="${oneResult.enResearchType}" zhContent="${oneResult.researchType}" koContent="${oneResult.korResearchType}"/>
							  </td>
							<td class="info_content_02" nowrap>${oneResult.period}</td>
						</tr>
					</c:forEach>
					<c:forEach items="${study2List}" var="oneResult">
						<tr>
							<td class="info_content_02" nowrap height="30"><!-- ${oneResult.studyType} -->
								<ait:content enContent="${oneResult.enStudyType}" zhContent="${oneResult.studyType}" koContent="${oneResult.korStudyType}"/>
							  </td>
							<td class="info_content_02" nowrap>${oneResult.period}</td>
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
				<td rowspan="2" valign="top">
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
					<c:forEach items="${ITLevelInfoList}" var="oneResult" varStatus="i">
						<tr>
							<td class="info_content_01" height="30"><ait:codeClass
								name="itExamNameCode_*${i.count}" codeClass="ITExamCode"
								selected="${oneResult.itExamNameCode}" /></td>
							<td class="info_content_01"><ait:codeClass
								name="itLevelCode_*${i.count}" codeClass="ITLevelCode"
								selected="${oneResult.itLevelCode}" /></td>
							<td class="info_content_01"><input type="text"
								class="content" name="itMark_*${i.count}"
								id="itMark_*${i.count}"
								value="<c:out value='${oneResult.itMark}'/>" /></td>
							<td class="info_content_01"><input type="text" size="10"
								class="content" name="obtainedDate_*${i.count}"
								id="obtainedDate_*${i.count}"
								value="<c:out value='${oneResult.obtainedDate}'/>"
								onClick="setday(this);" date mask="9999-99-99" /></td>
							<input type="hidden" name="it_NO_*${i.count}"
								id="it_NO_*${i.count}"
								value="<c:out value='${oneResult.it_NO}'/>" />
							<input type="hidden" name="modifierID_*${i.count}"
								id="modifierID_*${i.count}" value="<c:out value='${adminId}'/>" />
						</tr>
					</c:forEach>
					<c:if test="${ITLevelInfoCnt<3}">
						<c:forEach var="i" begin="1" end="${3-ITLevelInfoCnt}" step="1">
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
				<td>
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
							<td class="info_content_01" height="30"><c:out
								value='${contractInfoCnt - (i.count -1)}' /></td>
							<td class="info_content_01"><c:out
								value='${oneResult.contractStartDate}' /></td>
							<td class="info_content_01"><c:out
								value='${oneResult.contractEndDate}' /></td>
							<td class="info_content_01"><c:out
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


<input type="hidden" name="lanuageInfoCnt" id="lanuageInfoCnt"
	value="${lanuageInfoCnt }"> <input type="hidden"
	name="educationInfoCnt" id="educationInfoCnt"
	value="${educationInfoCnt }"> <input type="hidden"
	name="ITLevelInfoCnt" id="ITLevelInfoCnt" value="${ITLevelInfoCnt }">
</form>
</body>
<ait:xjos />
</html>
