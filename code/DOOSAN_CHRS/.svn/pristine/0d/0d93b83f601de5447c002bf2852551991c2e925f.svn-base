<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.ArItem"%>
<%@ page import="com.ait.ar.bean.*,java.util.*,com.ait.util.*"%>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArItemBean">
</jsp:useBean>
<%
ArrayList list=dao.getItemList();
ArItem info=null;
HttpSession session1 = request.getSession(true);
AdminBean admin1 = (AdminBean) session.getAttribute("admin");
request.setAttribute("list",list);
%>
<html>
<head>

<title>班次添加</title>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">

function addrow()
{
var count=parseInt(document.form1.count.value);
count++;
document.form1.count.value=count;
var htm="";
	for(var i=0;i<count;i++)
	{
		htm+="<table width='100%'  border='1'cellspacing='0' cellpadding='10' bordercolorlight='#E7E7E7' bordercolordark='#FFFFFF' style='padding: 2px 2px 2px 2px;'>";
		htm+="<tr>";
		htm+="<td width='15%' class='info_content_01'><ait:message messageID='display.mutual.start_date_2'/></td>";
		htm+="<td width='15%'><input name='fh"+i+"' type='text' size='2' maxlength='2'>:<input name='fm"+i+"' type='text' size='2' maxlength='2'></td>";
		htm+="<td width='15%' class='info_content_01'><ait:message  messageID='display.mutual.end_date_2'/></td>";
		htm+="<td width='15%'><input name='th"+i+"' type='text' size='2' maxlength='2'>:<input name='tm"+i+"' type='text' size='2' maxlength='2'></td>";
		htm+="<td width='15%'><select name='itemNo"+i+"'>";
		htm+="<c:forEach items='${list}' var='list'>";
		htm+="<option value='${list.itemNo}'>";
		htm+="<ait:content enContent='${list.itemEnName}' zhContent='${list.itemName}' koContent='${list.itemKorName}'/>";
		htm+="</option>";
		htm+="</c:forEach>";
		htm+="</select></td>";
		htm+="<td width='25%'>";
		htm+="<table width='100%'  border='0' cellspacing='0' cellpadding='0'>";
		htm+="<tr>";
		htm+="<td class='info_content_00' nowrap><ait:message messageID='display.att.setting.shift.edit.start' module='ar'/></td>";
		htm+="<td class='info_content_00' style='width:200px'><input type='radio' name='fday"+i+"' value='0' checked><ait:message messageID='display.att.setting.shift.add.same_day' module='ar'/>&nbsp;&nbsp;<input type='radio' name='fday"+i+"' value='1'><ait:message messageID='display.att.setting.shift.add.2nd_day' module='ar'/>";
		htm+="<input type='radio' name='fday"+i+"' value='2'><ait:message messageID='display.att.setting.shift.add.3rd_day' module='ar'/></td>";
		htm+="</tr>";
		htm+="<tr>";
		htm+="<td class='info_content_00' nowrap><ait:message messageID='display.att.setting.shift.edit.end' module='ar'/></td>";
		htm+="<td class='info_content_00' style='width:200px'><input type='radio' name='tday"+i+"' value='0' checked><ait:message messageID='display.att.setting.shift.add.same_day' module='ar'/>&nbsp;&nbsp;<input type='radio' name='tday"+i+"' value='1'><ait:message messageID='display.att.setting.shift.add.2nd_day' module='ar'/>";
		htm+="<input type='radio' name='tday"+i+"' value='2'><ait:message messageID='display.att.setting.shift.add.3rd_day' module='ar'/></td>";
		htm+="</tr>";
		htm+="</table>";
		htm+="</tr>";
		htm+="</table>";
	}
	document.all.createTable.innerHTML=htm;
}
function Save()
{
  if(document.form1.shiftId.value=="")
  {//"请填写班次ID"
  	alert('<ait:message  messageID="alert.att.setting.shift.add.id" module="ar"/>');
	return;
  }
  if(document.form1.shiftName.value=="")
  {//"请填写班次名称"
  	alert('<ait:message  messageID="alert.att.setting.item.chinese_name" module="ar"/>');
	return;
  }
  
  if(document.form1.itemNo0 == null)
  {//"请添加班次的时间信息"
    alert('<ait:message  messageID="alert.att.setting.shift.add.time_value" module="ar"/>');
	return;
  }
  for(var i=0;i<document.form1.elements.length;i++)
  {
  	if(document.form1.elements[i].name.indexOf("fh")!=-1)
	{
		if(isNaN(form1.elements[i].value)||form1.elements[i].value=="")
		{//"请正确输入开始时间"
			alert('<ait:message  messageID="alert.att.correct_start_time" module="ar"/>');
			return;
		}
	}
	if(document.form1.elements[i].name.indexOf("fm")!=-1)
	{
	   if(isNaN(form1.elements[i].value)||form1.elements[i].value=="")
		{//"请正确输入开始时间"
			alert('<ait:message  messageID="alert.att.correct_start_time" module="ar"/>');
			return;
		}
	}
	if(document.form1.elements[i].name.indexOf("th")!=-1)
	{
		if(isNaN(form1.elements[i].value)||form1.elements[i].value=="")
		{//"请正确输入结束时间"
			alert('<ait:message  messageID="alert.att.correct_end_time" module="ar"/>');
			return;
		}
	}
	if(document.form1.elements[i].name.indexOf("tm")!=-1)
	{
		if(isNaN(form1.elements[i].value)||form1.elements[i].value=="")
		{//"请正确输入结束时间"
			alert('<ait:message  messageID="alert.att.correct_end_time" module="ar"/>');
			return;
		}
	}

  }
  document.form1.action="/arControlServlet?operation=shiftadd&menu_code=<%=request.getParameter("menu_code")%>";
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
			<%@ include file="/inc/common_toolbar_a.jsp"%>
			
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11" >&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<form name="form1" method="post" action="">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10"><!-- 考勤班次-->
					<ait:message  messageID="display.att.setting.shift.shift" module="ar"/></td>
				</tr>
			</table>
		  <table width="100%"  border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="13%" class="info_title_01"><!-- 班次ID-->
					<ait:message  messageID="display.att.setting.shift.edit.id" module="ar"/></td>
		      <td width="20%" class="info_content_00"><input name="shiftId" type="text" id="shiftId" size="15"></td>
		      <td width="13%" class="info_title_01"><!--班次名称-->
					<ait:message  messageID="display.att.setting.items.edit.name_chinese" module="ar"/></td>
		      <td width="20%" class="info_content_00"><input name="shiftName" type="text" id="shiftName" size="15"></td> 
		    </tr>
		     <tr>
		      <td width="13%" class="info_title_01">法人区分</td>
		      <td width="21%" class="info_content_00">
		      <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" all="all"/>
		      </td>		     
		      <td width="13%" class="info_title_01"><!-- 班次性质-->
					<ait:message  messageID="display.att.setting.shift.edit.time_division" module="ar"/></td>
		      <td width="20%" class="info_content_00">
		      <select name="dataType">
		        <option value="1"><ait:message  messageID="display.mutual.workday"/><!-- 平日 --></option>
		        <option value="2"><ait:message  messageID="display.mutual.weekend"/><!-- 周末 --></option>
		      </select>
		      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      <ait:image src="../images/btn/Add_little.gif"  border="0" align="absmiddle" onclick="javascript:addrow();" style="cursor:hand" title="添加班次参数"/>
		      </td>		     
		      <!-- 英文名称-->
		      <!--  
		      <td width="13%" class="info_title_01">
					<ait:message  messageID="display.att.setting.items.edit.name_english" module="ar"/></td>
		      <td width="20%" class="info_content_00"><input name="shiftEnName" type="text" id="shiftEnName" size="15"></td>
		      -->
		      <!-- 韩文名称-->
		      <!--  
		      <td width="13%" class="info_title_01">
					<ait:message  messageID="display.att.setting.items.edit.name_korean" module="ar"/></td>
		      <td width="21%" class="info_content_00"><input name="shiftKoName" type="text" id="shiftKoName" size="15"></td>
		      -->
		    </tr>
		  </table>
		  <div id="createTable"></div>
		 <input type="hidden" name="count" value="0">
		
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

