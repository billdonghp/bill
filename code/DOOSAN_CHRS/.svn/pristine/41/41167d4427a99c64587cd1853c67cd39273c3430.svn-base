<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>考勤维护&gt;休假维护</title>
</head>
<SCRIPT type="text/javascript">

function Add() {
	document.form1.action="/ar/ar_add_vacation.jsp?menu_code=${menu_code}";
	document.form1.submit();
}
function Update() {
	if(document.form1.NO.value == 0) {
		// "请选择修改项目"
	    alert('<ait:message  messageID="alert.att.select_item_edited" module="ar"/>');
		return;
	}
	document.form1.action="/arControlServlet?operation=retrieveDataForUpdateVaction&menu_code=${menu_code}&currentPage=${currentPage}&vac_no=" + document.form1.NO.value;
	document.form1.fireSubmit();
}
function Delete() {
	var flag = false ;
	for (i=0;i<document.form1.vacations.length;i++){
		if(document.form1.vacations[i].checked){
			flag = true;
			break ;
		}
	}
	if (!flag)
	{//"请选择删除项目"
	    alert('<ait:message  messageID="alert.att.select_item_deleted" module="ar"/>');
		return ;
	}
	
	//确认删除选中项?
	if(confirm('<ait:message  messageID="alert.att.delete_item" module="ar"/>')) {
		document.form1.action="/arControlServlet?operation=ar_vacation_del&page=vacation_del&vac_no=" + document.form1.NO.value + "&menu_code=${menu_code}&vac_tp=" 
		+ document.getElementById("vac_tp").value + "&strt_month=" + document.getElementById("strt_month").value ;
		
		document.form1.submit();
	}
}
function Search() {
	document.form1.action="/arControlServlet?operation=ar_vacation&page=vacation_v&menu_code=${menu_code}&vac_tp=" 
		+ document.getElementById("vac_tp").value + "&strt_month=" + document.getElementById("strt_month").value ;
	document.form1.submit();
}


function CheckAll() {
	if (document.form1.vacations) {
		if (document.form1.vacations[0]) {
			for (i=0;i<document.form1.vacations.length;i++)
				document.form1.vacations[i].checked = document.form1.checkAll.checked;
		} else {
			document.form1.vacations.checked = document.form1.checkAll.checked;
		}
	}
}

function downloadExcelWindow()
{

    form1.action = "/arControlServlet?operation=ar_vacation&page=vacationExcelTmp_v&menu_code=${menu_code}";
    form1.submit();    
}

function importExcelWindow()
{
	url="/arControlServlet?operation=ar_vacation$page=vacationExcelImport_v$menu_code=${param.menu_code}";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');
	
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
					 <td width="10%" class="info_title_01"><!--休假类型-->
					<ait:message  messageID="display.att.setting.dayoff.type" module="ar"/></td>
			          <td width="20%" class="info_content_00"><ait:codeClass name="vac_tp" codeClass="VacationType" selected="${vac_tp}" all="ALL" remove="VacType20"/></td>
			          <td width="10%" class="info_title_01"><!--开始月-->
					<ait:message  messageID="display.att.setting.dayoff.start_month" module="ar"/></td>
			          <td width="20%" class="info_content_00">
				          <select name="strt_month">
				          	<option value="">
								<ait:message  messageID="display.mutual.select"/></option>
					          <c:forEach items="${vacationStrt_monthList}" var="vacationStrt_monthList">
					          <c:choose>
					          	<c:when test="${strt_month==vacationStrt_monthList.NAME}">
					          		<option selected="selected" value="${vacationStrt_monthList.ID }">${vacationStrt_monthList.NAME }</option>
					          	</c:when>
					          	<c:otherwise></c:otherwise>
					          </c:choose>
					          	<option  value="${vacationStrt_monthList.ID }">${vacationStrt_monthList.NAME }</option>
					          </c:forEach>
				          </select>
			          <!-- <ait:select name="strt_month" selected="${strt_month}" dataListName="vacationStrt_monthList" all="ALL"/>-->
			          </td>
			          <td width="15%" class="info_content_00"><ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand"/></td>
					  <!--  <td><ait:image src="/images/btn/Excel_Imp.gif"  border="0" align="absmiddle" onclick="javascript:importExcelWindow();" style="cursor:hand"/></td> -->
				 </tr>
			</table>
	      </td>
		</tr>
		</table>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<form name="form1" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--个人休假-->
					<ait:message  messageID="display.att.setting.dayoff.personal" module="ar"/></td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td width="5%" class="info_title_01"><input type="checkbox" name="checkAll" onClick="CheckAll();" /></td>
		      <td width="20%" class="info_title_01"><!--休假类型-->
					<ait:message  messageID="display.att.setting.dayoff.type" module="ar"/></td>
		      <td width="20%" class="info_title_01"><!--开始月-->
					<ait:message  messageID="display.att.setting.dayoff.start_month" module="ar"/></td>
		      <td width="15%" class="info_title_01"><!--结束月-->
					<ait:message  messageID="display.att.setting.dayoff.end_month" module="ar"/></td>
		      <td width="15%" class="info_title_01"><!--休假时间-->
					<ait:message  messageID="display.att.setting.dayoff.time" module="ar"/></td>
		      <td width="15%" class="info_title_01"><!--创建时间-->
					<ait:message  messageID="display.mutual.creation_date"/></td>
		      <td width="15%" class="info_title_01"><!--修改时间-->
					<ait:message  messageID="display.att.setting.dayoff.edit_time" module="ar"/></td>
		    </tr>
			<c:forEach items="${vacationList}" var="oneResult" varStatus="i">
		    <tr align="center" onClick="band('#E7F0EF','black',${oneResult.VAC_NO})" > 
		      <td height="30" align="center" style="color: #666666;"><input type="checkbox" value="${oneResult.VAC_NO}" name="vacations" id="vacations" ></td>
		      <td align="center" style="color: #666666;"><ait:content enContent="${oneResult.VAC_TP_EN}" zhContent="${oneResult.VAC_TP_CN}" koContent="${oneResult.VAC_TP_KOR}" enCutLength="20"/>&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.STRT_MONTH}&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.END_MONTH}&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.VAC_DAY_CNT}&nbsp;
		      <!--天--> <ait:message  messageID="display.mutual.day"/></td>
		      <td align="center" style="color: #666666;">${oneResult.CREATE_DATE}&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.UPDATE_DATE}&nbsp;</td>	      
		    </tr>
		    </c:forEach>
		    <input type="hidden" name="NO" value="0" />
		    <tr align="center"> 
			 </tr>
		 </table>
		 <input type="hidden" name="currentListCnt" value="${currentListCnt}">
	 		      <!-- Page Navigation Start-->
		        <ait:page       
		               link="/arControlServlet"
		               parameters="&operation=ar_vacation&page=vacation_v&menu_code=${menu_code}&strt_month=${strt_month}&vac_tp=${vac_tp}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
		            
		            <!-- Page Navigation End -->
		</form>
	<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(vacationList) < 5}">
				<c:forEach var="i" begin="1" end="${5-fn:length(vacationList)}" step="1">
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
<script src="../js/xjos.js" language="JavaScript"></script>
</html>