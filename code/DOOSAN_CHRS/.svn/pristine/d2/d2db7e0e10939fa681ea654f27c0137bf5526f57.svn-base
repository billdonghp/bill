<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<script language="javascript">

function excel(){
	var from=document.form1.from_date.value;
	var to=document.form1.to_date.value;
	var deptid=document.form1.deptid.value;
	if(!from==''&&!to==''){
		window.open("/reports/ar_report/ar_shift_reports.jsp?from="+from+"&to="+to+"&deptid="+deptid);
	}else{
	//'请选择开始结束日期！'
		alert('<ait:message  messageID="alert.att.select_start_end" module="ar"/>');
	}	
}	

function disPlay(id)
{
  if(id=="empid")
  display.location.href="empshift_a.jsp";
  if(id=="groupid")
  display.location.href="groupshift_a.jsp";
  if(id=="dept")
  display.location.href="deptshift_a.jsp";
  if(id == 'emp_batch')
  display.location.href="emp_batchshift_a.jsp";
  if(id == 'import')
  display.location.href="import_shift_a.jsp";
}
</script>

</head>
<body>


<table width="100%" height="540" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_all.jsp"%>
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
		<%@ include file="../inc/common_toolbar.jsp"%> 
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--员工排班-->
					<ait:message  messageID="display.att.maintenance.shift.shift_arrangement" module="ar"/></td>
			</tr>
		</table>
		<table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<form name="form1" method="post" action=""> 
		  <tr align="center">
		    <td width="80%" height="40" align="center">
		    导出员工排班报表: &nbsp;&nbsp;&nbsp;&nbsp;
		    <!-- 部门--><ait:message  messageID="display.mutual.dept"/>
		    <ait:selDept dataListName="dept_list" name="deptid" separator="&nbsp;&nbsp;"  supervisorType="ar"/> &nbsp;
      
      		<!--开始时间--><ait:message  messageID="display.mutual.start_date_2"/>
     <input name="from_date" type="text" size="10" readonly onclick="setday(this);">
			<!--结束时间--><ait:message  messageID="display.mutual.end_time"/>
    <input name="to_date" type="text" size="10" readonly onclick="setday(this);">
         <ait:image src="/images/btn/Excel.gif"  border="0" align="absmiddle" onclick="javascript:excel();" style="cursor:hand" /> <font color=red>(班次报表导出查看)</font></td>
		  </tr>
		  <tr align="center">
		  	
		    <td width="80%" height="40" align="center">
		    进行员工排班:&nbsp;&nbsp;&nbsp;&nbsp; 
		    <input type="radio" name="type" value="empid" onClick="disPlay(this.value)">
		     <!-- 按个人排班-->
					<ait:message  messageID="display.att.maintenance.shift.by_individual" module="ar"/>
			<input type="radio" name="type" value="emp_batch" onClick="disPlay(this.value)">
			<!-- 按批量排班-->
					<ait:message  messageID="display.att.maintenance.shift.batch" module="ar"/>
		       <input type="radio" name="type" value="groupid" onClick="disPlay(this.value)">
		      <!--按动态组排班-->
					<ait:message  messageID="display.att.maintenance.shift.by_schedule_pattern" module="ar"/>
		      <input type="radio" name="type" value="dept" onClick="disPlay(this.value)"><!--按部门排班-->
					<ait:message  messageID="display.att.maintenance.shift.by_department" module="ar"/>
			  <input type="radio" name="type" value="import" onClick="disPlay(this.value)">
					导入排班</td>
			
			</td>
		  </tr>
		<tr align="center" >
		    <td colspan="5"  align="center" valign="top"  style="padding:5 5 5 5 ">
		    <iframe width="100%" height="100%" marginwidth="0" marginheight="0" frameborder="0" id="display"></iframe></td>
		</tr>
		<input type="hidden" name="where" value="0">
		</form>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:forEach var="i" begin="1" end="14"
				step="1">
				<tr>
					<td class="info_content_01" height="30"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
				</tr>
			</c:forEach>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="10">
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
<script language="javascript">
display.location.href="empshift_a.jsp";
</script>
</html>
