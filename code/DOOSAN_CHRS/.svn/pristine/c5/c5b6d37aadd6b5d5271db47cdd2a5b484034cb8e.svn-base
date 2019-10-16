<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script language="javascript">
function Save()
{
  if(isNaN(document.form1.hour.value)||document.form1.hour.value==""||document.form1.hour.value.length!=2)
  {//"请正确输入2位小时时间"
  	alert('<ait:message  messageID="alert.att.maintenance.punch.hour_correct" module="ar"/>');
	return;
  }
  
   if(isNaN(document.form1.minute.value)||document.form1.minute.value==""||document.form1.minute.value.length!=2)
  {//"请正确输入2位分钟时间"
  	alert('<ait:message  messageID="alert.att.maintenance.punch.minute_correct" module="ar"/>');
	return;
  }
  
	document.form1.action="/arControlServlet?operation=updateAttRecord&menu_code=${param.menu_code}";
	document.form1.fireSubmit(); 
}

</script>
</head>
<body>

<div id='showsearch' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
	<iframe width="370" height="200"  frameborder="0" marginwidth="0" marginheight="0" hspace="0" vspace="0" name="emp_list" ></iframe>
</div>
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
					<td align="left" class="title1" colspan="10"><!--刷卡记录-->
					<ait:message  messageID="display.att.view.punch.punch_record" module="ar"/></td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01" align="center"><!--工号-->
				<ait:message messageID="display.mutual.emp_id" /></td>
		      <td width="75%" class="info_content_00">${result.EMPID}(<ait:content enContent="${result.CHINESE_PINYIN}" zhContent="${result.CHINESENAME}" koContent="${result.KOREANNAME}"/>&nbsp;)</td>
		      <input type="hidden" name="key" value="${result.EMPID }"/>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--时间-->
					<ait:message  messageID="display.mutual.time" /></td>
		      <td class="info_content_00">
		      	<input type="text" size="10" maxlength="10" name="R_TIME" onClick="setday(this);" value="${result.R_TIME }" required/>&nbsp;
		      	<input name="hour" type="text" size="2" maxlength="2" value="${result.HOUR}" required>
	          	:
				<input name="minute" type="text"  size="2" maxlength="2" value="${result.MINUTE }" required></td>
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--类型-->
					<ait:message  messageID="display.att.view.punch.action" module="ar"/></td>
		      <td class="info_content_00"><ait:codeClass name="DOOR_TYPE" codeClass="DoorType" selected="${result.DOOR_TYPE }"/></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--备注 -->
				<ait:message messageID="display.mutual.notes" /></td>
		      <td class="info_content_00"><textarea name="REMARK" cols="30" rows="3">${result.REMARK }</textarea></td>
		    </tr>
		  </table>
		  <input type="hidden" name="RECORD_NO" value="${result.RECORD_NO}" />
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
<ait:xjos />
</html>
