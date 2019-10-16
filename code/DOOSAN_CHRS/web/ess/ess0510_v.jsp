<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ait.hrm.business.HrmServices"%>
<%@ page import="com.ait.ess.dao.EssDAO"%>
<%@ page import="com.ait.hrm.bean.PersonalInfo"%>
<%@ page import="com.ait.hrm.bean.BasicInfo"%>
<%@ page import="com.ait.util.BeanUtil"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>基本信息申请</title>
</head>
<body>

<SCRIPT type="text/javascript">
<!--hidden
function backFront(){
	location.href="/ess/${param.menu_code}.jsp?menu_code=${param.menu_code}";
}
 //-->
</SCRIPT>
<%
	EssDAO essDao = new EssDAO();
	String empID = request.getParameter("empid");
	PersonalInfo hrPersonalInfo = (PersonalInfo) HrmServices.getInstance().retrievePersonalInfo(empID);
	PersonalInfo essPersonalInfo = (PersonalInfo) essDao.getEssPersonalInfo(empID);
	BeanUtil util = new BeanUtil();
	essPersonalInfo = (PersonalInfo) util.compareObject(essPersonalInfo, hrPersonalInfo);
	request.setAttribute("personalInfo", essPersonalInfo);
	BasicInfo basic = (BasicInfo) HrmServices.getInstance().retrieveBasicInfo(empID);
	request.setAttribute("basic", basic);
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		
		<%@ include file="../inc/common_toolbar_return.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <%@ include
			file="../ess/ess_view_basic.jsp"%> <!-- display 3 level menu -->

		<!-- display content --> <br>

		<div align="left"><span class="title1"><!-- 个人信息 --> </span></div>
		<table width="100%" 　border="0" cellpadding="0" cellspacing="1"
			class="dr_d">
			<tr>
				<td height="30" class="info_title_01" width="12%"><!-- 性别 --> <ait:message
					messageID="display.mutual.sex" /></td>
				<td class="info_content_00"><ait:content
					enContent="${personalInfo.enlishSex}"
					koContent="${personalInfo.korSex}" zhContent="${personalInfo.sex}"></ait:content></td>
				<td height="30" class="info_title_01" width="12%"><!--  民族--> <ait:message
					messageID="display.mutual.race" module="ess" /></td>
				<td class="info_content_00"><ait:content
					enContent="${personalInfo.enNation}"
					koContent="${personalInfo.korNation}"
					zhContent="${personalInfo.nation}"></ait:content></td>
				<td height="30" class="info_title_01" width="12%"><!--最高学历  -->
				<ait:message messageID="display.mutual.diploma" module="ess" /></td>
				<td class="info_content_00"><ait:content
					enContent="${personalInfo.enBeforeDegree}"
					koContent="${personalInfo.korBeforeDegree}"
					zhContent="${personalInfo.beforeDegree}"></ait:content></td>
			</tr>
			<tr>
				<td class="info_title_01"><!--  国籍 --> <ait:message
					messageID="display.mutual.nationality" module="ess" /></td>
				<td class="info_content_00"><ait:content
					enContent="${personalInfo.enNationNality}"
					koContent="${personalInfo.korNationNality}"
					zhContent=" ${personalInfo.natioNality}"></ait:content></td>
				<td height="30" class="info_title_01"><!-- 籍贯 --> <ait:message
					messageID="display.mutual.native_place" module="ess" /></td>
				<td class="info_content_00"><ait:content
					enContent="${personalInfo.enBornPlace}"
					koContent="${personalInfo.korBornPlace}"
					zhContent=" ${personalInfo.bornPlace}"></ait:content></td>
				<td class="info_title_01"><!--  婚姻状态--> <ait:message
					messageID="display.mutual.martial_status" module="ess" /></td>
				<td class="info_content_00">
				<ait:content
					enContent="${personalInfo.enMaritalStatus}"
					koContent="${personalInfo.korMaritalStatus}"
					zhContent="${personalInfo.maritalStatus}"></ait:content>
				</td>
			</tr>
			<tr>
				<td class="info_title_01" height="30"><!--家庭电话  --> <ait:message
					messageID="display.sys.info_request.request.home_tel" module="ess" />
				</td>
				<td class="info_content_00">${personalInfo.homePhone}</td>
				<td class="info_title_01"><!--移动电话  --> <ait:message
					messageID="display.sys.info_request.request.mobile_phone"
					module="ess" /></td>
				<td class="info_content_00">${personalInfo.cellPhone}</td>
				<td height="30" class="info_title_01"><!-- 办公室电话 --> <ait:message
					messageID="display.sys.info_request.request.office_tel"
					module="ess" /></td>
				<td class="info_content_00">${personalInfo.officePhone}</td>
			</tr>
			<tr>
				<td align="center" class="info_title_01"><!--  Email--> <ait:message
					messageID="display.mutual.e_mail" module="ess" /></td>
				<td class="info_content_00">${personalInfo.email}</td>
				<td class="info_title_01"><!--FAX  --> <ait:message
					messageID="display.mutual.fax" module="ess" /></td>
				<td class="info_content_00">${personalInfo.fax}</td>
				<td align="center" class="info_title_01"><!-- 档案关系 --> <ait:message
					messageID="display.mutual.personnel_file" module="ess" /></td>
				<td class="info_content_00">
				<ait:content
					enContent="${personalInfo.enFileRelation}"
					koContent="${personalInfo.korFileRelation}"
					zhContent="${personalInfo.fileRelation}"></ait:content>
				</td>
			</tr>
			<tr>
				<td class="info_title_01"><!-- 现住址 --> <ait:message
					messageID="display.mutual.current_address" module="ess" /></td>
				<td class="info_content_00">${personalInfo.homeAddress}</td>

				<td class="info_title_01"><!--  身份证号码 --> <ait:message
					messageID="display.mutual.id_no_2" module="ess" /></td>
				<td class="info_content_00">${personalInfo.idcardNo}</td>
				<td class="info_title_01"><!-- 邮编 --> <ait:message
					messageID="display.mutual.native_place" module="ess" /></td>
				<td class="info_content_00">${personalInfo.postalCode}</td>
			</tr>
			<tr>
				<td class="info_title_01"><!--户口所在地  --> <ait:message
					messageID="display.sys.info_request.request.seat_of_residence"
					module="ess" /></td>
				<td class="info_content_00">${personalInfo.regPlace}</td>
				<td class="info_title_01"><!--户口性质  --> <ait:message
					messageID="display.mutual.hukou" module="ess" /></td>
				<td class="info_content_00">
				<ait:content
					enContent="${personalInfo.enRegType}"
					koContent="${personalInfo.korRegType}"
					zhContent="${personalInfo.regType}"></ait:content>
				
				</td>
				<td class="info_title_01"><!-- 政治面貌 --> <ait:message
					messageID="display.mutual.political_status" module="ess" /></td>
				<td class="info_content_00">
				<ait:content
					enContent="${personalInfo.enPolity}"
					koContent="${personalInfo.korPolity}"
					zhContent="${personalInfo.polity}"></ait:content>
				</td>
			</tr>
			<tr>
				<td class="info_title_01" height="30"><!--宗教  --> <ait:message
					messageID="display.mutual.religion" module="ess" /></td>
				<td class="info_content_00">
				<ait:content
					enContent="${personalInfo.enReligion}"
					koContent="${personalInfo.korReligion}"
					zhContent="${personalInfo.religion}"></ait:content>
				</td>
				<td class="info_title_01"><!-- 爱好/兴趣 --> <ait:message
					messageID="display.mutual.hobby" module="ess" /></td>
				<td class="info_content_00">${personalInfo.hobby}</td>
				<td class="info_title_01"><!-- 特长 --> <ait:message
					messageID="display.mutual.skill" module="ess" /></td>
				<td class="info_content_00">${personalInfo.speciality}</td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</body>
</html>
