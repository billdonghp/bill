<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../inc/taglibs.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="../inc/meta.jsp"%>
<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<!--<link href="../css/default.css" rel="stylesheet" type="text/css">-->
<title>ess加班参数</title>
<script type="text/javascript">
var msg = '' ;   
function Add() {
	document.form1.action="/syControlServlet?operation=essOvertimeParam_add&flag=view&menu_code=${param.menu_code}" ;
	document.form1.submit();
}

function Update() {
	if(document.form1.PARAM_NO.value == 0) {
		// "请选择修改项目"
	    alert('<ait:message  messageID="alert.att.select_item_edited" module="ar"/>');
		return;
	}
	
	document.form1.action="/syControlServlet?operation=retrieveDataForUpdateEssOvertimeParam&menu_code=${param.menu_code}&currentPage=${currentPage}" ;
	document.form1.submit();
}

function Delete() {
	if(document.form1.PARAM_NO.value == 0) {
		// "请选择修改项目"
	    alert('<ait:message  messageID="alert.att.select_item_edited" module="ar"/>');
		return;
	}
	
	if (confirm("你确定要删除当前已选择的记录？")) {  
		document.form1.action= "/syControlServlet?&operation=essOvertimeParam_del&menu_code=${param.menu_code}" ;
		document.form1.submit();
	}
}

function Search() {
	document.form1.action = "/syControlServlet?&operation=essOvertimeParam&menu_code=${param.menu_code}&CONDITION_TYPE=" + document.form1.CONDITION_TYPE.value ;
	document.form1.PARAM_NO.value="" ;
	document.form1.submit();
}

function band(backColor,textColor,i,j)
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
	document.form1.PARAM_NO.value=i;
	document.form1.cpnyId.value=j
} 
</script>                                                                                                                         
<body>                                     
<form name="form1" method="post">
<input type="hidden" name="PARAM_NO" value="0" />
<input type="hidden" name="cpnyId" value="0" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
		<%@ include file="../inc/sy_common_toolbar.jsp" %> 
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
			<td class="title1" align="left"><!--查询条件--><ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td colspan="9">
	      	<table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	      		<tr>
					 <td class="info_title_01">条件类型</td>
			         <td class="info_content_00"><ait:codeClass codeClass="ConditionTypeCode" name="CONDITION_TYPE" selected="${CONDITION_TYPE}" /></td>
					 <td class="info_content_01"><ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand"/></td>
			    </tr>
			</table>
	      </td>
		</tr>
		</table>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp" %>
		
		<!-- display content -->
		<br>
		<table width="98%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">ess休假参数</td>
			</tr>
		</table>                                              
		<br>
		 <table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr>
		      <td nowrap="nowrap" class="info_title_01">条件类型</td>
		      <td nowrap="nowrap" class="info_title_01">法人区分</td>
		      <td nowrap="nowrap" class="info_title_01">部门</td>
			  <td nowrap="nowrap" class="info_title_01">加班类型</td>
			  <td nowrap="nowrap" class="info_title_01">决裁级别</td>
		      <td nowrap="nowrap" class="info_title_01">是否参考</td>
			  <td nowrap="nowrap" class="info_title_01">开始标志</td>
		      <td nowrap="nowrap" class="info_title_01">开始偏移方向</td>
		      <td nowrap="nowrap" class="info_title_01">开始长度</td>
			  <td nowrap="nowrap" class="info_title_01">结束标志</td>
			  <td nowrap="nowrap" class="info_title_01">结束偏移方向</td>
			  <td nowrap="nowrap" class="info_title_01">结束长度</td>    
		      <td nowrap="nowrap" class="info_title_01">加班总计</td>
			  
			</tr>              
			<c:forEach items="${essOvertimeParmList}" var="essOvertime" varStatus="status"> 
			<tr bgcolor="#FFFFFF" onClick="band('#E7F0EF','black',${essOvertime.PARAM_NO},${essOvertime.CPNY_ID})" height="30px">
				<td align="center" style="color: #666666;" nowrap="nowrap">${essOvertime.CONDITIONNAME}&nbsp;</td> 
				<td align="center" style="color: #666666;" nowrap="nowrap">${essOvertime.CPNYNAME}&nbsp;</td>   
				<td align="center" style="color: #666666;" nowrap="nowrap">${essOvertime.DEPTNAME}&nbsp;</td>      
				<td align="center" style="color: #666666;" nowrap="nowrap">${essOvertime.OVERTIMENAME}&nbsp;</td>
				<td align="center" style="color: #666666;" nowrap="nowrap">${essOvertime.AFFIRM_LEVEL}&nbsp;</td>
				<td align="center" style="color: #666666;" nowrap="nowrap">
					<c:if test="${essOvertime.REFERENCN_FLAG == '0'}">否</c:if><c:if test="${essOvertime.REFERENCN_FLAG == '1'}">是</c:if>&nbsp;</td>
				<td align="center" style="color: #666666;" nowrap="nowrap">
					<c:if test="${essOvertime.REFERENCN_FROM_FLAG == '0'}">否</c:if><c:if test="${essOvertime.REFERENCN_FROM_FLAG == '1'}">是</c:if>&nbsp;</td>               
				<td align="center" style="color: #666666;" nowrap="nowrap">${essOvertime.REFERENCN_FROM_RELATION}&nbsp;</td>
				<td align="center" style="color: #666666;" nowrap="nowrap">${essOvertime.REFERENCN_FROM_OFFSET}&nbsp;</td>
				<td align="center" style="color: #666666;" nowrap="nowrap">
					<c:if test="${essOvertime.REFERENCN_TO_FLAG == '0'}">否</c:if><c:if test="${essOvertime.REFERENCN_TO_FLAG == '1'}">是</c:if>&nbsp;</td>              
				<td align="center" style="color: #666666;" nowrap="nowrap">${essOvertime.REFERENCN_TO_RELATION}&nbsp;</td>
				<td align="center" style="color: #666666;" nowrap="nowrap">${essOvertime.REFERENCN_TO_OFFSET}&nbsp;</td>
				<td align="center" style="color: #666666;" nowrap="nowrap">${essOvertime.OVERTIME_TOTAL}&nbsp;</td>
				
			</tr>      
			</c:forEach> 
			<c:if test="${fn:length(essOvertimeParmList) < 9}">
				<c:forEach var="i" begin="1" end="${9-fn:length(essOvertimeParmList)}"
					step="1">
					<tr>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						<td class="info_content_01">&nbsp;</td>
						
					</tr>
				</c:forEach>
			</c:if>  
		</table>
		<!-- Page Navigation Start-->
		        <ait:page       
		               link="/syControlServlet"
		               parameters="&operation=essOvertionParam&menu_code=${param.menu_code}&CONDITION_TYPE=${CONDITION_TYPE}" 
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
	            
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
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