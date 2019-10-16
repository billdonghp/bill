<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>考勤维护&gt;访问卡维护</title>
</head>
<SCRIPT type="text/javascript">

function Add() {

	document.form1.action="/arControlServlet?operation=retrieveDataForInsertVisitCard&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {
	if(document.form1.NO.value == 0) {
	//"请选择修改项"
		alert('<ait:message  messageID="alert.att.maintenance.visit.select_item" module="ar"/>');
		return;
	}
	document.form1.action="/arControlServlet?operation=retrieveDataForUpdateVisitCard&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Search() {

	document.form1.action="/arControlServlet?operation=retrieveVisitCardList&menu_code=${param.menu_code}";
	document.form1.submit();
}

function exportExcel()
{
/*
	if(document.form1.DEPTID.value=="" && document.form1.key.value=="" && document.form1.R_TIME.value=="") {
		alert("请选择导出的Excel条件范围.");
		return;
	}
*/
    form1.action = "/arControlServlet?operation=exportVisitCardReport&menu_code=${param.menu_code}";
    form1.submit();    
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
	document.form1.NO.value=i;
} 
</SCRIPT>
<body>
<form name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../hrm/inc/hrtoolbar.jsp"%>
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
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!--查询条件-->
					<ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td colspan="9">
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       		 <tr>
					 <td width="10%" class="info_title_01"><!-- 卡号-->
					<ait:message  messageID="display.mutual.card_no"/></td>
			          <td width="15%" class="info_content_00"><input name="CARD_NO" type="text" size="15" value="${CARD_NO}"></td>
			          <td width="10%" class="info_title_01"><!-- 访问者名称-->
					<ait:message  messageID="display.att.maintenance.visit.name" module="ar"/></td>
			          <td width="15%" class="info_content_00"><ait:codeClass name="NAME" codeClass="VisitCpyName" selected="${NAME}" all="all" /></td>
					  <td width="10%" class="info_title_01"><!-- 使用时间-->
					<ait:message  messageID="display.att.maintenance.visit.use_duration" module="ar"/></td>
			          <td width="15%" class="info_content_00"><input type="text" size="10" maxlength="10" name="USE_DATE" value="${USE_DATE }" onClick="setday(this);" /></td>
					  <td width="25%" class="info_content_01">
					  <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand"/>
			          <ait:image src="/images/btn/Excel_Exp.gif"  border="0" align="absmiddle" onclick="javascript:exportExcel();" style="cursor:hand"/></td>
			    </tr>
			</table>
	      </td>
		</tr>
		</table>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_view_toolbar.jsp"%> 
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10" nowrap="nowrap"><!-- 访问卡-->
					<ait:message  messageID="display.att.maintenance.visit.visitor_card" module="ar"/></td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td width="10%" class="info_title_01" nowrap="nowrap"><!-- 卡号-->
					<ait:message  messageID="display.mutual.card_no"/></td>
		      <td width="14%" class="info_title_01" nowrap="nowrap"><!-- 访问者名称-->
					<ait:message  messageID="display.att.maintenance.visit.name" module="ar"/></td>
			   <td width="10%" class="info_title_01" nowrap="nowrap"><!-- 负责人 -->
					<ait:message  messageID="display.att.maintenance.visit.principal" module="ar"/></td>
			 <td width="10%" class="info_title_01" nowrap="nowrap"><!-- 访问人数 -->
					<ait:message  messageID="display.att.maintenance.visit.people" module="ar"/></td>
		      <td width="13%" class="info_title_01" nowrap="nowrap"><!-- 开始日期-->
					<ait:message  messageID="display.mutual.start_date"/></td>
		      <td width="13%" class="info_title_01" nowrap="nowrap"><!-- 结束日期-->
					<ait:message  messageID="display.mutual.end_date"/></td>
		      <td width="30%" class="info_title_01" nowrap="nowrap"><!-- 备注-->
					<ait:message  messageID="display.mutual.notes"/></td>
		      <td width="10%" class="info_title_01" nowrap="nowrap"><!-- 创建时间-->
					<ait:message  messageID="display.mutual.creation_date"/></td>
		      <td width="10%" class="info_title_01" nowrap="nowrap"><!-- 修改时间-->
					<ait:message  messageID="display.att.maintenance.visit.modification" module="ar"/></td>
		    </tr>
			<c:forEach items="${visitCardList}" var="oneResult" varStatus="i">
		    <tr align="center" onClick="band('#E7F0EF','black',${oneResult.NO})"> 
		      <td height="30" align="center" style="color: #666666;">${oneResult.CARD_NO}&nbsp;</td>
		      <td align="center" style="color: #666666;"><ait:content enContent="${oneResult.EN_NAME}" zhContent="${oneResult.NAME}" koContent="${oneResult.KOR_NAME}"/>&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.PRINCIPAL}&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.VISITOR_AMONT}&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.FROM_DATE}&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.TO_DATE}&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.REMARK}&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.CREATE_DATE}&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.UPDATE_DATE}&nbsp;</td>
		    </tr>
		    </c:forEach>
		    <tr align="center"> 
			 </tr>
		 </table>
		 <input type="hidden" name="NO" value="0" />
		 
	 		      <!-- Page Navigation Start-->
		        <ait:page       
		               link="/arControlServlet"
		               parameters="&operation=retrieveVisitCardList&menu_code=${param.menu_code}&CARD_NO=${CARD_NO}&NAME=${NAME}&USE_DATE=${USE_DATE}" 
		               total="${visitCardCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
		            
		            <!-- Page Navigation End -->
		
	<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(visitCardList) < 5}">
				<c:forEach var="i" begin="1" end="${5-fn:length(visitCardList)}"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
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
</form>
</body>
</html>