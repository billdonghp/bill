<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArItemBean">
</jsp:useBean>
<%
	ArrayList list=null;
	ArItem info=null;
	list=dao.getItemList();
	request.setAttribute("list",list); 
%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
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
	document.form1.itemNo.value=i;
}

function ChangeTextColor(a_obj,a_color)
{
	for (i=0;i<a_obj.cells.length;i++){
	a_obj.cells(i).style.color=a_color;
	}
}
function Add()
{
	document.form1.action="/ar/aritem_a.jsp?menu_code=<%=request.getParameter("menu_code")%>";
	document.form1.submit();
}
function Update()
{
	if(document.form1.itemNo.value==0)
	{// "请选择修改项目"
	    alert('<ait:message  messageID="alert.att.select_item_edited" module="ar"/>');
		return;
	}
	var no=document.form1.itemNo.value;
	document.form1.action="/ar/aritem_m.jsp?no="+no+"&menu_code=<%=request.getParameter("menu_code")%>";
	document.form1.submit();
}

function Delete()
{
	if(document.form1.itemNo.value==0)
	{//"请选择删除项目"
	    alert('<ait:message  messageID="alert.att.select_item_deleted" module="ar"/>');
		return;
	}//确定要删除该项目吗?
	if(!confirm('<ait:message  messageID="alert.att.delete_item" module="ar"/>'))
	{
		return;
	}
	document.form1.action="/arControlServlet?operation=aritemdel&menu_code=<%=request.getParameter("menu_code")%>";
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
		<form name="form1" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 考勤项目-->
					<ait:message  messageID="display.att.setting.items.attendance_items" module="ar"/>	</td>
			</tr>
		</table>
		<table width="100%" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr>
		    <td width="13%" class="info_title_01"><!-- 项目ID-->
					<ait:message  messageID="display.att.setting.items.id" module="ar"/>	</td>
		    <td width="15%" class="info_title_01"><!-- 项目名称-->
					<ait:message  messageID="display.att.setting.items.name" module="ar"/>	</td>
		     <td width="15%" class="info_title_01">所属法人</td>
		    <td width="10%" class="info_title_01"><!-- 简称-->
					<ait:message  messageID="display.att.setting.items.abbreviation" module="ar"/>	</td>
		    <td width="40%" class="info_title_01"><!-- 说明-->
					<ait:message  messageID="display.att.setting.items.narrative" module="ar"/>	</td>
		    <td width="12%" class="info_title_01"><!-- 考勤项目组-->
					<ait:message  messageID="display.att.setting.items.group" module="ar"/>	</td>
		    <td width="10%" class="info_title_01"><!-- 是否活跃-->
					<ait:message  messageID="display.mutual.active"/>
		    </td>
		  </tr>
		  <c:forEach items="${list}" var="oneResult">
		  <tr onClick="band('#E7F0EF','black',${oneResult.itemNo})">
		  	<td align="center" style="color: #666666;">${oneResult.itemId}&nbsp;</td>
		    <td align="center" style="color: #666666;"><ait:content enContent="${oneResult.itemEnName}" zhContent="${oneResult.itemName}" koContent="${oneResult.itemKorName}"/></td>
		    <td align="center" style="color: #666666;">${oneResult.cpnyName}&nbsp;</td>
		    <td align="center" style="color: #666666;">${oneResult.shortName}</td>
		    <td align="left" style="color: #666666;">${oneResult.description}</td>
		    <td align="center" style="color: #666666;"><ait:content enContent="${oneResult.itemGroupEnName}" zhContent="${oneResult.itemGroupName}" koContent="${oneResult.itemGroupKorName}"/></td>
		    <td align="center" style="color: #666666;"><img src="..\images\a_${oneResult.activity}.gif" /></td>
		  </tr>
		  </c:forEach>
		</table>
		<input type="hidden" name="itemNo" value="0">
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
