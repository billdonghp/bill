<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.ArStaItem"%>
<%@ page import="com.ait.ar.bean.*,java.util.*,com.ait.util.*"%>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArStaItemBean">
</jsp:useBean>

<%
ArrayList list=dao.getStaItemList();
request.setAttribute("list",list);
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
var itemName = '';
function up(id,order)
{

	document.form1.action="/arControlServlet?operation=staitemorder&itemNo="+id+"&order="+order+"&type=up&menu_code=<%=request.getParameter("menu_code")%>";
        document.form1.submit();
}
function down(id,order)
{
	document.form1.action="/arControlServlet?operation=staitemorder&itemNo="+id+"&order="+order+"&type=down&menu_code=<%=request.getParameter("menu_code")%>";
       document.form1.submit();
}
function band(backColor,textColor,i,a_itemName)
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
	itemName=a_itemName;
}
function Add()
{
  document.form1.action="/ar/arstaitem_a.jsp?menu_code=<%=request.getParameter("menu_code")%>";
  document.form1.submit();
}
function Update()
{
  if(document.form1.itemNo.value==0)
  {// "请选择修改项目"
	    alert('<ait:message  messageID="alert.att.select_item_edited" module="ar"/>');
    return;
  }
  document.form1.action="/ar/arstaitem_m.jsp?itemNo="+document.form1.itemNo.value+"&menu_code=<%=request.getParameter("menu_code")%>";
  document.form1.submit();
}
function Delete()
{
  if(document.form1.itemNo.value==0)
  {//"请选择删除项目"
	    alert('<ait:message  messageID="alert.att.select_item_deleted" module="ar"/>');
    return;
  }//"确定要删除吗?"
  if(!confirm('<ait:message  messageID="alert.att.delete_item" module="ar"/>'))
  {
    return;
  }
  document.form1.action="/arControlServlet?operation=staitemdel&itemNo="+document.form1.itemNo.value+"&menu_code=<%=request.getParameter("menu_code")%>&itemName="+itemName;
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
		<form name="form1" method="POST">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--汇总项目-->
					<ait:message  messageID="display.att.setting.setting.complete_items" module="ar"/></td>
			</tr>
		</table>		
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center">
			<td height="24" class="info_title_01" nowrap="nowrap"><!-- 序号-->
					<ait:message  messageID="display.mutual.no"/></td>
			<td height="24" class="info_title_01" nowrap="nowrap"><!-- 汇总项目ID-->
					<ait:message  messageID="display.att.setting.setting.item_id" module="ar"/></td>
			<td height="24" class="info_title_01" nowrap="nowrap"><!-- 汇总项目名称-->
					<ait:message  messageID="display.att.setting.setting.item_name" module="ar"/></td>
			<td height="24" class="info_title_01" nowrap="nowrap">所属法人</td>
			<td height="24" class="info_title_01" nowrap="nowrap"><!-- 单位-->
					<ait:message  messageID="display.att.setting.setting.unit" module="ar"/></td>
			<td height="24" class="info_title_01" nowrap="nowrap"><!-- 最小单位-->
					<ait:message  messageID="display.att.setting.setting.minimum_unit" module="ar"/></td>
			<td class="info_title_01" nowrap="nowrap"><!-- 开始日期-->
					<ait:message  messageID="display.mutual.start_date"/></td>
	    	<td class="info_title_01" nowrap="nowrap"><!-- 结束日期-->
					<ait:message  messageID="display.mutual.end_date"/></td>
	    	<td class="info_title_01" nowrap="nowrap"><!-- 排序-->
					<ait:message  messageID="display.att.setting.setting.sequence" module="ar"/></td>
		  </tr>

		   <c:forEach items="${list}" var="oneResult" varStatus="i">
			   <tr align="center" onclick="band('#E7F0EF','black','${oneResult.itemNo }')">
			    	<td height="26" align="center" style="color: #666666;">${i.count }</td>
			    	<td align="center" style="color: #666666;">${oneResult.staItemId}&nbsp;</td>
					<td align="center" style="color: #666666;">
						<ait:content enContent="${oneResult.itemEnName}" zhContent="${oneResult.itemName}" koContent="${oneResult.itemKoName}"/>&nbsp;
					</td>
					<td align="center" style="color: #666666;">
						${oneResult.cpnyName}&nbsp;
					</td>
					<td halign="center" style="color: #666666;">
						<ait:content enContent="${oneResult.unitEnName}" zhContent="${oneResult.unitName}" koContent="${oneResult.unitKoName}"/>&nbsp;
					</td>
					<td align="center" style="color: #666666;">${oneResult.minUnit}</td>
				    <td align="center" style="color: #666666;">${oneResult.fromDate}</td>
			        <td align="center" style="color: #666666;">${oneResult.toDate}</td>
			        <td align="center" style="color: #666666;">
				        <table width="100%"  border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td align="center">
				            <c:if test="${i.count > 1}">
				            	<img src="../images/btn/up.gif" width="12" height="15" align="middle" style="cursor:hand" onClick="up('${oneResult.itemNo }','${oneResult.calOrder }')">
				            </c:if>
				            </td>
				            <td align="center">
				            <c:if test="${i.count < fn:length(list)}">
				            	<img src="../images/btn/down.gif" width="12" height="15" align="middle" style="cursor:hand" onClick="down('${oneResult.itemNo }','${oneResult.calOrder }')">
				            </c:if>
				            </td>
				          </tr>
				        </table>
			        </td>
			  </tr>
		   </c:forEach>
		</table>
		<input type="hidden" name="itemNo" value="0"/>
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
