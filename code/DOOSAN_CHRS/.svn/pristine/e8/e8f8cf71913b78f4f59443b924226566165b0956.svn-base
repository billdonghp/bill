<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ait.ar.bean.ArDate"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArDateBean">
</jsp:useBean>
<%@ include file="../inc/taglibs.jsp"%>
<%
	ArrayList list = dao.getArDate();
	ArDate info = null;
	request.setAttribute("list", list);
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/calendarcode.js"></script>
<script language="javascript">
	function Add()
	{
	  document.form1.action="/ar/ardate_a.jsp?menu_code=<%=request.getParameter("menu_code")%>";
	  document.form1.submit();
	}
	function Update()
	{
	  if(document.form1.statNo.value==0)
	  {// "请选择修改项目"
	    alert('<ait:message  messageID="alert.att.select_item_edited" module="ar"/>');
	    return;
	  }
	  document.form1.action="/ar/ardate_m.jsp?statNo="+(document.form1.statNo.value)+"&menu_code=<%=request.getParameter("menu_code")%>";
	  document.form1.submit();
	}
	function Delete()
	{
	  if(document.form1.statNo.value==0)
	  {//"请选择删除项目"
	    alert('<ait:message  messageID="alert.att.select_item_deleted" module="ar"/>');
	    return;
	  }//"确定要删除吗?"
	  if(!confirm('<ait:message  messageID="alert.att.delete_item" module="ar"/>'))
	  {
	    return;
	  }
	  document.form1.action="/arControlServlet?operation=ardatedelete&statNo="+(document.form1.statNo.value)+"&menu_code=<%=request.getParameter("menu_code")%>";
	  document.form1.submit();
	}
	function band(backColor,textColor,i)
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
		document.form1.statNo.value=i;
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
		<form name="form1" method="post" action="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 考勤时间 -->
					<ait:message  messageID="display.att.setting.cycle.cycle_attendance" module="ar"/>	
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="10"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr bgcolor="#F5F5F5">
				<td class="info_title_01"><!-- 序号 -->
					<ait:message  messageID="display.mutual.no"/></td>
			    <td class="info_title_01">所属法人</td>
				<td class="info_title_01">员工类型</td>	
				<td class="info_title_01"><!--考勤说明 -->
					<ait:message  messageID="display.att.setting.cycle.description" module="ar"/>	</td>
				<td class="info_title_01"><!-- 开始日期 -->
					<ait:message  messageID="display.att.setting.cycle.actual_start" module="ar"/></td>
				<td class="info_title_01"><!-- 结束日期 -->
					<ait:message  messageID="display.att.setting.cycle.actual_end" module="ar"/></td>
				<td class="info_title_01"><!-- 开始日 -->
					<ait:message  messageID="display.mutual.start_date_3"/></td>
				<td class="info_title_01"><!-- 结束日 -->
					<ait:message  messageID="display.mutual.end_date_3"/></td>
			</tr>
			<%for (int i = 0; i < list.size(); i++) {
						info = (ArDate)list.get(i);
		
			%>
			<tr onClick="band('#E7F0EF','black',<%= info.getStatNo() %>)">
				<td align="center" style="color: #666666;"><%= i + 1 %></td>
				<td align="center" style="color: #666666;"><%=info.getCpnyName() %></td>
				<td align="center" style="color: #666666;"><%=info.getCodeName() %></td>
				<td align="center" style="color: #666666;"><%=info.getDescribe() %></td>
				<td align="center" style="color: #666666;"><%=info.getFromDate() %></td>
				<td align="center" style="color: #666666;"><%=info.getToDate() %></td>
				<td align="center" style="color: #666666;"><%=info.getStarDate() %></td>
				<td align="center" style="color: #666666;"><%=info.getEndDate() %></td>
			</tr>
			<%}%>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(list) < 9}">
				<c:forEach var="i" begin="1" end="${8-fn:length(list)}"
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
			</c:if>
		</table>
		<input type="hidden" name="statNo" value="0" /></form>

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

<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
