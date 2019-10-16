<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>考勤维护&gt;年假维护</title>
</head>
<SCRIPT type="text/javascript">

function Add() {
	document.form1.action="/ar/arAnnualHolidy_a.jsp?menu_code=${menu_code}&year=${year}";
	document.form1.submit();
}
function Update() {
	
	for (i=1;i<=${currentListCnt};i++) {
		
		var usedYear = document.getElementById("usedYearDays_"+i).value;
		
		if(document.getElementById("annual_"+i).checked == true && 
		   document.getElementById("yearDays_"+i).value < usedYear){
			
			document.getElementById("yearDays_"+i).focus();
			alert("年假总天数不能小于已使用天数: " + usedYear);
			return;   
		}
	} 
	
	document.form1.action="/arControlServlet?operation=ar_modify&content=annual&menu_code=${menu_code}&currentPage=${currentPage}";
	document.form1.fireSubmit();
}
function Search() {
	document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=annual_v&menu_code=${menu_code}";
	document.form1.submit();
}

function CheckAll() {

	for (i=1;i<=${currentListCnt};i++) {
	
		document.getElementById("annual_"+i).checked = document.form1.checkAll.checked;
	} 
	
}

function downloadExcelWindow()
{

    form1.action = "/arControlServlet?operation=ar_pagecontrol&page=annualExcelTmp_v&menu_code=${menu_code}";
    form1.submit();    
}

function importExcelWindow()
{
	url="/arControlServlet?operation=ar_pagecontrol$page=annualExcelImport_v$menu_code=${param.menu_code}";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');
	
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
			<td class="title1">查询条件</td>
		</tr>
	    <tr>
	      <td colspan="9">
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       		 <tr>
					 <td width="10%" class="info_title_01">年假时间</td>
			          <td width="20%" class="info_content_00"><ait:date yearName="year" yearSelected="${year}" yearMinus="5" yearPlus="5"/></td>
			          <td width="10%" class="info_title_01">关键字</td>
			          <td width="20%" class="info_content_00"><input name="key" type="text" size="10" value="${key}"></td>
			          <td width="15%" class="info_content_00"><ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand"/></td>
					  <td width="25%" class="info_content_01"><ait:image src="/images/btn/Template.gif"  border="0" align="absmiddle" onclick="javascript:downloadExcelWindow();" style="cursor:hand"/>
					  										<ait:image src="/images/btn/Excel_Imp.gif"  border="0" align="absmiddle" onclick="javascript:importExcelWindow();" style="cursor:hand"/></td>
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
				<td align="left" class="title1" colspan="10">年假</td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td width="5%" class="info_title_01"><input type="checkbox" name="checkAll" onClick="CheckAll();" /></td>
		      <td width="20%" class="info_title_01">员工</td>
		      <td width="20%" class="info_title_01">部门</td>
		      <td width="15%" class="info_title_01">总年休假</td>
		      <td width="15%" class="info_title_01">已使用年假</td>
		      <td width="15%" class="info_title_01">剩余年假</td>
		      <td width="15%" class="info_title_01">年份</td>
		    </tr>
			<c:forEach items="${annualHolidyList}" var="oneResult" varStatus="i">
		    <tr align="center"> 
		      <td class="info_content_01"><input type="checkbox" value="${i.count}" name="annual_${i.count}" id="annual_${i.count}" ></td>
		      <td class="info_content_01">(${oneResult.empID}) ${oneResult.chineseName}</td>
		      <td class="info_content_01">${oneResult.department}</td>
		      <td class="info_content_01"><input name="yearDays_${i.count}" type="text" size="5" value="${oneResult.yearDays}" minvalue="${oneResult.usedYearDays}" required  onChange="javascript:if(document.form1.annual_${i.count}){document.form1.annual_${i.count}.checked=true;}">&nbsp;天</td>
		      <td class="info_content_01">${oneResult.usedYearDays}&nbsp;天</td>
		      <td class="info_content_01">${oneResult.leftYearDays}&nbsp;天</td>
		      <td class="info_content_01">${oneResult.holidyDate}</td>
		       <input type="hidden" name="holidyNo_${i.count}" value="${oneResult.holidyNo}">
		       <input type="hidden" name="updatedBy_${i.count}" value="${admin}">
		       <input type="hidden" name="usedYearDays_${i.count}" value="${oneResult.usedYearDays}">
		    </tr>
		    </c:forEach>
		    <tr align="center"> 
			 </tr>
		 </table>
		 <input type="hidden" name="currentListCnt" value="${currentListCnt}">
	 		      <!-- Page Navigation Start-->
		        <ait:page       
		               link="/arControlServlet"
		               parameters="&operation=ar_pagecontrol&page=annual_v&menu_code=${menu_code}&year=${year}&key=${key}" 
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
	<table width="100%" border="0" cellspacing="0" cellpadding="10"
			<c:if test="${fn:length(annualHolidyList) < 5}">
				<c:forEach var="i" begin="1" end="${5-fn:length(annualHolidyList)}"
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