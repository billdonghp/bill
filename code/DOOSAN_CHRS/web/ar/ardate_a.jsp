<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function Save()
{
  if(document.form1.describe.value=="")
  {//请填写区间说明
  	alert('<ait:message  messageID="alert.att.setting.time.fill_period_description" module="ar"/>');
	return;
  }
  if(isNaN(document.form1.fYear.value)||document.form1.fYear.value==""||document.form1.fYear.value.length!=4)
  {//"请正确输入4位数年份"
  	alert('<ait:message  messageID="alert.att.setting.time.year_value" module="ar"/>');
	return;
  }
  if(isNaN(document.form1.fMonth.value)||document.form1.fMonth.value==""||document.form1.fMonth.value.length!=2)
  {//"请正确输入2位数月份"
  	alert('<ait:message  messageID="alert.att.setting.time.month_value" module="ar"/>');
	return;
  }
  if(isNaN(document.form1.fDay.value)||document.form1.fDay.value==""||document.form1.fDay.value.length!=2)
  {//"请正确输入2位数天数"
  	alert('<ait:message  messageID="alert.att.setting.time.day_value" module="ar"/>');
	return;
  }
  if(isNaN(document.form1.tYear.value)||document.form1.tYear.value==""||document.form1.tYear.value.length!=4)
  {//"请正确输入4位数年份"
  	alert('<ait:message  messageID="alert.att.setting.time.year_value" module="ar"/>');
	return;
  }
  if(isNaN(document.form1.tMonth.value)||document.form1.tMonth.value==""||document.form1.tMonth.value.length!=2)
  {//"请正确输入2位数月份"
  	alert('<ait:message  messageID="alert.att.setting.time.month_value" module="ar"/>');
	return;
  }
  if(isNaN(document.form1.tDay.value)||document.form1.tDay.value==""||document.form1.tDay.value.length!=2)
  {//"请正确输入2位数天数"
  	alert('<ait:message  messageID="alert.att.setting.time.day_value" module="ar"/>');
	return;
  }

  if(isNaN(document.form1.starDay.value)||document.form1.starDay.value==""||document.form1.starDay.value.length>2)
  {//"请正确输入开始天数"
  	alert('<ait:message  messageID="alert.att.input_start_time" module="ar"/>');
	return;
  }
  if(isNaN(document.form1.endDay.value)||document.form1.endDay.value==""||document.form1.endDay.value.length>2)
  {//"请正确输入结束天数"
  	alert('<ait:message  messageID="alert.att.input_end_time" module="ar"/>');
	return;
  }
  document.form1.action="/arControlServlet?operation=ardateadd&menu_code=<%=request.getParameter("menu_code")%>";
  document.form1.submit();
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
		   <table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10"><!-- 员工区分 -->
					<ait:message  messageID="display.att.setting.cycle.cycle_attendance" module="ar"/></td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01"><!-- 区间类型 -->法人区分</td>
		      <td width="75%" class="info_content_00"><%=admin.getCpnyName()%><input name="cpnyId" type="hidden" value="<%=admin.getCpnyId()%>"/></td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01"><!-- 区间类型 -->员工类型</td>
		      <td width="75%" class="info_content_00"><ait:empDiff name="empDiffTypeCode" cpnyDiff="<%=admin.getCpnyId()%>"/></td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01"><!-- 区间说明 -->
					<ait:message  messageID="display.att.setting.cycle.description" module="ar"/></td>
		      <td width="75%" class="info_content_00"><input name=describe type="text" size="20" ></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 开始日期 -->
					<ait:message  messageID="display.att.setting.cycle.actual_start" module="ar"/></td>
		      <td class="info_content_00"><input name="fYear" type="text"  size="4" maxlength="4">
		        <!-- 年-->
					<ait:message  messageID="display.mutual.z_yy"/>
		        <input name="fMonth" type="text"  size="2" maxlength="2">
		        <!--月-->
					<ait:message  messageID="display.mutual.z_mm"/>
		        <input name="fDay" type="text"  size="2" maxlength="2">
		        <!--日-->
					<ait:message  messageID="display.mutual.z_dd"/>
		        </td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 结束日期 -->
					<ait:message  messageID="display.att.setting.cycle.actual_end" module="ar"/></td>
		      <td class="info_content_00"><input name="tYear" type="text" id="tYear" size="4" maxlength="4">
		       <!--  年-->
					<ait:message  messageID="display.mutual.z_yy"/>
				  <input name="tMonth" type="text" id="tMonth" size="2" maxlength="2">
			   <!--月-->
					<ait:message  messageID="display.mutual.z_mm"/>
				  <input name="tDay" type="text" id="tDay" size="2" maxlength="2">
			  <!-- 日-->
					<ait:message  messageID="display.mutual.z_dd"/></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 开始日 -->
					<ait:message  messageID="display.mutual.start_date_3"/></td>
		      <td class="info_content_00"><input name="starDay" type="text" id="starDay" size="2" maxlength="2"></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 结束日 -->
					<ait:message  messageID="display.mutual.end_date_3"/></td>
		      <td class="info_content_00"><input name="endDay" type="text" id="endDay" size="2" maxlength="2"></td>
		    </tr>	    
		  </table>
		</form>

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
</html>
