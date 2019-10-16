<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">


<script language="javascript"><!-- 

function Save()
{
	if(document.form1.cpnyId.value == "" || document.form1.cpnyId.value ==null ){
	 alert("法人区分不能为空!!");
	 return;
	}
	//if (document.form1.REFERENCN_FROM_FLAG.value == "1")
	//{
	//	if(isNaN(document.form1.REFERENCN_FROM_OFFSET.value) || document.form1.REFERENCN_FROM_OFFSET.value == 0 )
	//	{
	//	 	alert('开始长度必须为数字或者不能为零!!!');
	//		return;
	//	}
	//}
	
	
	//if (document.form1.REFERENCN_TO_FLAG.value == "1")
	//{
	//	if(isNaN(document.form1.REFERENCN_TO_OFFSET.value)  || document.form1.REFERENCN_TO_OFFSET.value == 0 )
	//	{		 	
	//		alert('结束长度必须为数字或者不能为零!!!');
	//		return;
	//	}
	//}	
	
	document.form1.action="/syControlServlet?operation=essOvertimeParam_add&flag=add&menu_code=${param.menu_code}" ;
	document.form1.submit();
}
	function ChangeLeaveParam(cpnyId){
	document.form1.action="/syControlServlet?operation=essLeaveParam_add&flag=view&menu_code=${param.menu_code}&cpnyId="+cpnyId ;
	document.form1.submit();
	}
--></script>
</head>

<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>
			
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<form name="form1" method="post" >
		<input type="hidden" name="menu_code" value="${param.menu_code}">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 项目参数-->
					<ait:message  messageID="display.att.setting.parameter.parameter" module="ar"/></td>
			</tr>
		</table>
		<table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr>
		   <td class="info_title_01">法人区分</td>
		    <td class="info_content_00"><ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}"  all="all" /></td>
			<td class="info_title_01">
			条件类型
		    <td class="info_content_00">
		   
		    <ait:codeClass codeClass="ConditionTypeCode" name="CONDITION_TYPE" selected="${CONDITION_TYPE}"  all="all" />
		   
		    <%--<ait:codeClass codeClass="LeaveTypeCode" name="" selected="${APPLY_TYPE}" />--%>
		    </td>
		    <td class="info_title_01">决裁级别</td>
		    <td class="info_content_00">
		    <select name="AFFIRM_LEVEL" id="AFFIRM_LEVEL">
		     <c:forEach begin="1" end="10" var="i" step="1">
				<option value="${i}" >${i}</option>
			  </c:forEach>
		    </select>
		    </td>
		    <td class="info_title_01">加班类型</td>
		    <td class="info_content_00">
		    <ait:codeClass codeClass="OTTypeCode" name="OVERTIME_TYPE" selected="${OVERTIMETYPE}"  all="all" />
		    </td> 
		     <td class="info_title_01">是否参考</td>
		    <td class="info_content_00"><select name="REFERENCN_FLAG" id="REFERENCN_FLAG">
		      <option value="1" selected><!-- 是-->
					<ait:message  messageID="display.mutual.yes"/></option>
		      <option value="0"><!-- 否-->
					<ait:message  messageID="display.mutual.no_2"/></option>
		    </select>
		    </td>       
		  </tr>
		  <tr>
		      <td class="info_title_01">开始标志</td>
		    <td class="info_content_00"> 
		    <select name="REFERENCN_FROM_FLAG" id="REFERENCN_FROM_FLAG">
		      <option value="1" selected><!-- 是-->
					<ait:message  messageID="display.mutual.yes"/></option>
		      <option value="0"><!-- 否-->
					<ait:message  messageID="display.mutual.no_2"/></option>
		    </select>
		    </td>
		    <td class="info_title_01">开始偏移方向</td>
		    <td class="info_content_00">      
		    <select name="REFERENCN_FROM_RELATION" id="REFERENCN_FROM_RELATION">
		      <option value=">"><!-- 大于-->
					<ait:message  messageID="display.att.setting.parameter.edit.greater_than" module="ar"/></option>
		      <option value="<"><!-- 小于-->
					<ait:message  messageID="display.att.setting.parameter.edit.lesser_than" module="ar"/></option>
		      <option value="=" selected><!-- 等于-->
					<ait:message  messageID="display.att.setting.parameter.edit.equal_to" module="ar"/></option>
			  <option value=">=" >大于等于</option>
			  <option value="<=" >小于等于</option>
		    </select>
		    </td>
		    <td class="info_title_01">开始长度</td>
		    <td class="info_content_00">
		    <input name="REFERENCN_FROM_OFFSET" type="text" id="REFERENCN_FROM_OFFSET" size="5" maxlength="5" value="0">
		    </td>
		   <td class="info_title_01">部门选择</td>
		    <td class="info_content_00">
		    <ait:selDept name="serchDept" all="all" supervisorType="pa" selected="${serchDept}"/>
		    </td>
		  </tr>
		  <tr>
		    <td class="info_title_01">结束标志</td>
		    <td class="info_content_00">
		    <select name="REFERENCN_TO_FLAG" id="REFERENCN_TO_FLAG">
		      <option value="1" ><!-- 是-->
					<ait:message  messageID="display.mutual.yes"/></option>
		      <option value="0" selected><!-- 否-->
					<ait:message  messageID="display.mutual.no_2"/></option>
		    </select>
		    </td>
		    <td class="info_title_01">结束偏移方向</td>
		   <td class="info_content_00">      
		    <select name="REFERENCN_TO_RELATION" id="REFERENCN_TO_RELATION">
		      <option value=">"><!-- 大于-->
					<ait:message  messageID="display.att.setting.parameter.edit.greater_than" module="ar"/></option>
		      <option value="<"><!-- 小于-->
					<ait:message  messageID="display.att.setting.parameter.edit.lesser_than" module="ar"/></option>
		      <option value="=" selected><!-- 等于-->
					<ait:message  messageID="display.att.setting.parameter.edit.equal_to" module="ar"/></option>
			  <option value=">=" >大于等于</option>
			  <option value="<=" >小于等于</option>
		      </select>
		    </td>
		    <td class="info_title_01">结束长度</td>
		    <td class="info_content_00"><input name="REFERENCN_TO_OFFSET" type="text" id="REFERENCN_TO_OFFSET" size="5" maxlength="5" value="0"></td>
		    
		      
		   
		    
		   
		   
		    <td class="info_title_01">加班总计</td>
		    <td class="info_content_00">
		    <input name="OVERTIME_TOTAL" type="text" id="OVERTIME_TOTAL" size="5" maxlength="3" value="0">
		    </td>
		    
		   
		  </tr>
		 
		  <tr>
		</table>
		</form>

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
