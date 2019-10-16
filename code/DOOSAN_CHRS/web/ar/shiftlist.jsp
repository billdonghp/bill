<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArShift020Bean">
</jsp:useBean>
<%
ArrayList list=dao.getShift020();
request.setAttribute("list", list);
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function Add()
{
  document.form1.action="/ar/shiftAdd.jsp?menu_code=<%=request.getParameter("menu_code")%>";
  document.form1.submit();
}
function Update()
{
  if(document.form1.shiftNo.value==0)
  {//"请选择班次"
    alert('<ait:message  messageID="alert.att.setting.parameter.select_shift" module="ar"/>');
    return;
  }
  document.form1.action="/ar/shiftEdit.jsp?shiftNo="+document.form1.shiftNo.value+"&menu_code=<%=request.getParameter("menu_code")%>";

  document.form1.submit();
}
function Delete()
{
  if(document.form1.shiftNo.value==0)
  {//"请选择班次"
    alert('<ait:message  messageID="alert.att.setting.parameter.select_shift" module="ar"/>');
    return;
  }//"确定要删除吗?"
  if(!confirm('<ait:message  messageID="alert.att.delete_item" module="ar"/>'))
  {
    return;
  }
  document.form1.action="/arControlServlet?operation=shiftdel&menu_code=<%=request.getParameter("menu_code")%>";
  document.form1.submit();
}

function band(backColor,textColor,values,id)
{
	var t;
	if(typeof(preEl)!='undefined')
	{
	preEl.bgColor=orgBColor;

	try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
	}
	var el = event.srcElement;
	el = el.parentElement;
	orgBColor = el.bgColor;
	orgTColor = el.style.color;
	el.bgColor=backColor;
	try{ChangeTextColor(el,textColor);}catch(e){;}
	preEl = el;
	document.form1.shiftNo.value=values;
  	document.form1.partition.value=0;
}
function qubie()
{
  document.form1.partition.value=1;
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
			<%@ include file="../inc/common_toolbar_no_del.jsp"%>
			
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
		<form name="form1" method="post">
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 考勤班次-->
					<ait:message  messageID="display.att.setting.shift.shift" module="ar"/></td>
			</tr>
		  </table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		
		  <tr bgcolor="#F5F5F5">
		      <td align="center" class="info_title_01"><!-- 序号-->
					<ait:message  messageID="display.mutual.no"/></td>
		
		      <td align="center" class="info_title_01"><!-- 班次类型-->
					<ait:message  messageID="display.att.setting.shift.shift_type" module="ar"/></td>
			 <td align="center" class="info_title_01">所属法人</td>
		      <td align="center" class="info_title_01"><!-- 开始日期-->
					<ait:message  messageID="display.mutual.start_date"/></td>
		      <td align="center" class="info_title_01"><!-- 开始时间-->
					<ait:message  messageID="display.mutual.start_date_2"/></td>
		      <td align="center" class="info_title_01"><!-- 结束日期-->
					<ait:message  messageID="display.mutual.end_date"/></td>
		      <td align="center" class="info_title_01"><!-- 结束时间-->
					<ait:message  messageID="display.mutual.end_date_2"/></td>
		    </tr>
		    <c:forEach items="${list}" var="oneResult" varStatus="i">
		    	<c:if test="${oneResult.rowNum == 1 }">
		    		<tr>
				      <td colspan="8" height="2" style="color: #666666;">
				      	<ait:content enContent="${oneResult.shiftEnName}" zhContent="${oneResult.shiftName}" koContent="${oneResult.shiftKoName}"/>
				      	(${oneResult.shift_no })
				      </td>
				    </tr>
			    </c:if>
			     <tr onclick="band('#E7F0EF','black','${oneResult.shift_no }')" >
			      <td align="center" style="color: #666666;">${i.count}</td>
			
			      <td align="center" style="color: #666666;">
			      	<ait:content enContent="${oneResult.itemEnName}" zhContent="${oneResult.itemName}" koContent="${oneResult.itemKoName}"/>
			      </td>
			      <td align="center" style="color: #666666;">
			      	${oneResult.cpnyName}
			      </td>
			      <td align="center" style="color: #666666;">
			      	<ait:content enContent="${oneResult.fromDayEnName}" zhContent="${oneResult.fromDayName}" koContent="${oneResult.fromDayKoName}"/>&nbsp;
			      </td>
			      <td align="center" style="color: #666666;">${oneResult.fromTime}</td>
			      <td align="center" style="color: #666666;">
			      	<ait:content enContent="${oneResult.toDayEnName}" zhContent="${oneResult.toDayName}" koContent="${oneResult.toDayKoName}"/>&nbsp;
				  </td>
			      <td align="center" style="color: #666666;">${oneResult.toTime}</td>
			    </tr>
		    </c:forEach>
		  </table>
		<input type="hidden" name="shiftNo" value="0"/>
		<input type="hidden" name="partition" value="0"/>
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
