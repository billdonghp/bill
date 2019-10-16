<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<style>
body{
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #808080;
	scrollbar-highlight-color: #808080;
    scrollbar-3dlight-color: #ffffff;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #F5F5F5;
	scrollbar-arrow-color: #808080;
}
</style>
<script language="javascript">
function Save()
{
	document.form1.action="/arControlServlet?operation=ar_otplan_update&menu_code=${param.menu_code}";
	document.form1.fireSubmit(); 
}
</script>
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
		<form name="form1" method="post" action="">
		<input type="hidden" name="ot_plan_no" value="${result.OT_PLAN_NO}" />
		<input type="hidden" name="empId" value="${result.EMPID}" />
		<input type="hidden" name="planMonth" value="${result.PLAN_MONTH}" />
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">加班上限维护</td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01">姓名</td>
		      <td width="85%" class="info_content_00">${result.CHINESENAME}</td>
		    </tr>
		    <tr>
		      <td class="info_title_01">申请限制时间</td>
		      <td class="info_content_00"><input name="hour" type="text" value="${fn:substringBefore(result.APPLY_LIMTIDTIME,':')}" maxlength="2" style="width:30px" maxvalue="23" numeric  />&nbsp;&nbsp;:
		      				  &nbsp;&nbsp;<input name="minute" value="${fn:substringAfter(result.APPLY_LIMTIDTIME,':')}" style="width:30px" maxlength="2" type="text" maxvalue="59"  numeric  /> 
		      </td>
		    </tr>
		    <tr>
		    <!--  
		      <td class="info_title_01">加班月份</td>
		      <td class="info_content_00"><ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" /></td>
		      -->
		      <td class="info_title_01">开始月</td>
		      <td class="info_content_00">
		      	<ait:date yearName="syear" monthName="start_date" yearPlus="100" yearSelected="${result.startYear }" monthSelected="${result.startMonth }"/>
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01">结束月</td>
		      <td class="info_content_00">
		      	<ait:date yearName="eyear" monthName="end_date" yearPlus="100" yearSelected="${result.endYear }" monthSelected="${result.endMonth }"/>
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01">加班上限</td>
		      <td class="info_content_00"><input name="limit_ot" type="text" size="15" maxlength="10" value="${result.LIMIT_OT}"/></td>
		    </tr>
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
<ait:xjos />
</html>
