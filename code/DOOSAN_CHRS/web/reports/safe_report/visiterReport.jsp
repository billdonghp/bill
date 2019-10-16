<%@ page contentType="text/html; charset=UTF-8" import="com.ait.sqlmap.util.SimpleMap ,com.ait.sqlmap.util.ObjectBindUtil , java.util.List"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.util.*"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="jobHealthInformation" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="JOB_POSITION_INFORMATION" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="POSITION_INFORMATION" class="java.util.ArrayList" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
			response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=SpecialeateryReport--NotApply.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
	
	SimpleMap parameterObject = null;
	parameterObject = ObjectBindUtil.getData(request);
	
%>
<table border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="11"><b><font size="+2">职业健康监护档案</font></b></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	  
	  <tr align="center">
	  	<td class="info_title_01" nowrap>
	      职业健康编号
	    </td>	  
	    <td class="info_title_01" nowrap>
	       职号
	    </td>
	    <td class="info_title_01" nowrap>
	       姓名
	    </td>
	    <td class="info_title_01" nowrap>
	       部门
	    </td>
	    <td class="info_title_01" nowrap>
	       岗位
	    </td>
	    <td class="info_title_01" nowrap>
	       出生日期
	    </td>
	    <td class="info_title_01" nowrap>
	       入社日期
	    </td>
	    <td class="info_title_01" nowrap>
	       职业危害
	    </td>
	    <td class="info_title_01" nowrap>
	       从事本岗日期
	    </td>
	    <td class="info_title_01" nowrap>
	       体检日期
	    </td>
	    <td class="info_title_01" nowrap>
	       体检结果
	    </td>
	  </tr>
	  <c:forEach items="${jobHealthInformation}" var="all" varStatus="i">
		  <tr  align="center">
		    <td align="center">
		    	${all.DOCUMENTNO}&nbsp;
		    </td>
		    <td align="center">
		    	${all.EMPID}&nbsp;
		    </td>
		    <td align="center">
		    	${all.CHINESENAME}&nbsp;
		    </td>
		   <td align="center">
		    	${all.DEPTNAME}&nbsp;
		    </td>
		    <td align="center">
		    	${all.JOB_POSITION}&nbsp;
		    </td>
		    <td align="center">
		    	${all.BIRTH_YMD}&nbsp;
		    </td>
		    <td align="center">
		    	${all.DATE_STARTED}&nbsp;
		    </td>
		    <td align="center">
		    	${all.DISEASE}&nbsp;
		    </td>
		    <td align="center">
		    	${all.START_TIME}&nbsp;
		    </td>
		    <td align="center">
		    	${all.MEDICAL_YEAR}&nbsp;
		    </td>
		    <td align="center">
		    	${all.MEDICALFLAG}&nbsp;
		    </td>
		  </tr>
	  </c:forEach>
	</table>
		</td>
	</tr>
</table>
</body>
</html>