<%@ page contentType="text/html; charset=UTF-8" import="com.ait.hr.bean.*,com.ait.hr.entity.*,java.util.*,java.util.Vector"%>
<!-- education, language, military service, qualification, personal info-->
<jsp:useBean id="experience" class="com.ait.hr.entity.Experience"/>
<jsp:useBean id="experience_vector" class="java.util.Vector" scope="request" />  <!-- contains the health info -->
<jsp:useBean id="education" class="com.ait.hr.entity.Education"/>
<jsp:useBean id="language" class="com.ait.hr.entity.ForeignLanguage"/>
<jsp:useBean id="qualification" class="com.ait.hr.entity.Qualification"/>
<jsp:useBean id="personal" class="com.ait.hr.entity.PersonalInfo" scope="request"/>
<jsp:useBean id="education_vector" class="java.util.Vector" scope="request"/>
<jsp:useBean id="language_vector" class="java.util.Vector" scope="request"/>
<jsp:useBean id="qualification_vector" class="java.util.Vector" scope="request"/>
<jsp:useBean id="militaryService" class="com.ait.hr.entity.MilitaryService"/>
<jsp:useBean id="miliService_vector" class="java.util.Vector" scope="request" />  <!-- contains the health info -->
<jsp:useBean id="basic" class="com.ait.hr.entity.BasicInfo" scope="request"/> <!-- basic information of the selected employee -->
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>  <!-- the admin entity keeped in current session -->

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Basic information</title>

<script>
<!--hidden

function showEmpInfo(empid){
          var url="http://150.150.176.253:8100/jsp/reports/empInfo.jsp?EmpID="+empid;
          var features = "status=yes,menubar=no,resizable=yes,scrollbars=yes,width=800,height=600";
		  var name = "";
          window.open(url,name,features);
}
//-->
</script>
</head>


<link href="css/default.css" rel="stylesheet" type="text/css">
<body>
<%@ include file="../ess/esstoolbar.jsp"%>
<%@ include file="/hr/hr_view_basic.jsp"%>
dsfadfafs

<%@ include file="../hr/hr_diaoling_toolbar.jsp"%>

<br>

<table width="100%">
	<tr width="100%">
		<td width="86%">
			 <span class="title1">个人信息</span> <br>
		</td>
		<td width="14%" align="right"><input name="empinfo" type="button" value="员工信息卡" onclick="showEmpInfo(<%=basic.getEmpID()%>);"></td>
	</tr>
</table>
<td class="line_gray_01" style="padding:1 1 1 1 ">
	<%@ include file="/hr/personal_info.jsp"%>
	<table align="center" width="100%" cellpadding="0" cellspacing="0">
 <tr><td width="10"></td>
         <td>
  <div align="left">&nbsp;&nbsp;
<span class="title1">教育经历</span></div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
        <tr align="center">
          <td width="21%" height="30" bgcolor="#F5F5F5">学校／机构</td>
            <td width="17%" bgcolor="#F5F5F5">专业</td>
            <td width="13%" bgcolor="#F5F5F5">入学日期</td>
            <td width="13%" bgcolor="#F5F5F5">毕业日期</td>
            <td width="22%" bgcolor="#F5F5F5">学历</td>
            <td width="14%" bgcolor="#F5F5F5">学历性质</td>
</tr>
  <% for (int i=0;i<education_vector.size();i++){
  	education = (Education)education_vector.elementAt(i);
  %>
       <tr align="center">
          <td height="30"  class="info_content_01"><%=education.getInstitutionName()%>&nbsp;</td>
            <td class="info_content_01"><%=education.getSubject()%>&nbsp;</td>
            <td class="info_content_01"><%=education.getStartDate()%>&nbsp;</td>
            <td class="info_content_01"><%=education.getEndDate()%>&nbsp;</td>
            <td class="info_content_01"><%=education.getDegree()%>&nbsp;</td>
            <td class="info_content_01"><%=education.getDegreeType()%>&nbsp;</td>
      </tr>
	<%}%>
  
</table>
  <div align="left">&nbsp;&nbsp;
<span class="title1">外语</span></div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
   <tr align="center">
    <td height="30" bgcolor="#F5F5F5">语言种类</td>
      <td bgcolor="#F5F5F5">考试种类</td>
      <td bgcolor="#F5F5F5">分数</td>
      <td bgcolor="#F5F5F5">取得日期</td>
      <td bgcolor="#F5F5F5">语言级别</td>
   </tr>
  <% for (int i=0;i<language_vector.size();i++){
  	language = (ForeignLanguage)language_vector.elementAt(i);
  %>
   <tr align="center">
	      <td height="29"  class="info_content_01"><%=language.getLanguageType()%>&nbsp;</td>
	 <td class="info_content_01"><%=language.getExamName()%>&nbsp;</td>
	 <td class="info_content_01"><%=language.getMark()%>&nbsp;</td>
	 <td class="info_content_01"><%=language.getObtainedDate()%>&nbsp;</td>
	 <td class="info_content_01"><%=language.getLanguageLevel()%>&nbsp;</td>
   </tr>
	<%}%>
  
</table>
  <div align="left">&nbsp;&nbsp;
<span class="title1">兵役</span></div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr align="center">
        <td height="30" bgcolor="#F5F5F5">兵役种类</td>
         <td bgcolor="#F5F5F5">兵役等级</td>
         <td bgcolor="#F5F5F5">兵役地区</td>
         <td bgcolor="#F5F5F5">开始日期</td>
         <td bgcolor="#F5F5F5">结束日期</td>
   </tr>
  <% for (int i=0;i<miliService_vector.size();i++){
  	militaryService = (MilitaryService)miliService_vector.elementAt(i);
  %>
  		<tr align="center">
			
          <td height="28" class="info_content_01"><%=militaryService.getMilitaryType()%>&nbsp;</td>
			<td class="info_content_01"><%=militaryService.getMilitaryLevel()%>&nbsp;</td>
			<td class="info_content_01"><%=militaryService.getMilitaryArea()%>&nbsp;</td>
			<td class="info_content_01"><%=militaryService.getStartTime()%>&nbsp;</td>
			<td class="info_content_01"><%=militaryService.getEndTime()%>&nbsp;</td>
		</tr>
	<%}%>

</table>
 <div align="left">&nbsp;&nbsp;
<span class="title1">资格证书</span></div>
 <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr align="center">
       <td height="30" bgcolor="#F5F5F5">证书名称</td>
         <td bgcolor="#F5F5F5">证书等级</td>
         <td bgcolor="#F5F5F5">分数</td>
         <td bgcolor="#F5F5F5">颁发机构</td>
         <td bgcolor="#F5F5F5">颁发日期</td>
</tr>
  <% for (int i=0;i<qualification_vector.size();i++){
  	qualification = (Qualification)qualification_vector.elementAt(i);
  %>
            <tr align="center">
	       <td height="30" class="info_content_01"><%=qualification.getQualName()%>&nbsp;</td>
	         <td class="info_content_01"><%=qualification.getGradeObtain()%>&nbsp;</td>
	         <td class="info_content_01"><%=qualification.getMark()%>&nbsp;</td>
	         <td class="info_content_01"><%=qualification.getQualInstitute()%>&nbsp;</td>
	         <td class="info_content_01"><%=qualification.getEndDate()%>&nbsp;</td>
            </tr>
	<%}%>
  
</table>
 <div align="left">&nbsp;&nbsp;
<span class="title1">工作经验</span></div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<tr align="center">
    <td height="30" bgcolor="#F5F5F5">公社名称</td>
	<td scope="row" bgcolor="#F5F5F5">部门名称</td>
	<td scope="row" bgcolor="#F5F5F5">职位</td>
	<td scope="row" bgcolor="#F5F5F5">离职原因</td>
	<td scope="row" bgcolor="#F5F5F5">任职期间</td>
  </tr>
  <% for (int i=0;i<experience_vector.size();i++){
  	experience = (Experience)experience_vector.elementAt(i);
  %>
  		<tr align="center">
			
          <td height="27" class="info_content_01"><%=experience.getPrevCpnyName()%>&nbsp;</td>
			<td class="info_content_01"><%=experience.getPrevDeptName()%>&nbsp;</td>
			<td class="info_content_01"><%=experience.getPrevPosition()%>&nbsp;</td>
			<td class="info_content_01"><%=experience.getLeaveReason()%>&nbsp;</td>
			<td class="info_content_01"><%=experience.getStartDate()%>～<%=experience.getEndDate()%>&nbsp;</td>
		</tr>
	<%}%>
  
</table>
</td>
</tr>
</table>
</td>
</body>
</html>
