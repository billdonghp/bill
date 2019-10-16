<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<!-- ga_view_checkaccount.jsp -->
<%@ include file="../inc/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function Add() {
	document.ApplyForm.action="/gaControlServlet?operation=CheckManager&content=searchCheckBank&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
function Delete() {
	if(document.ApplyForm.SEQ_NO.value=="") {
		
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.ApplyForm.action="/gaControlServlet?operation=CheckManager&content=deleteCheckAccount&menu_code=${param.menu_code}";
		document.ApplyForm.submit();
	}
}

function Update() {

	if(document.ApplyForm.SEQ_NO.value=="") {
		
	    alert("请选择修改项目");
		return;
	}
	document.ApplyForm.action="/gaControlServlet?operation=CheckManager&content=updateCheckAccount&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
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
	document.ApplyForm.SEQ_NO.value=i;
} 

function importExcel()
{
	url="/paControlServlet?operation=importCheckAccountReport";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
}

function search(){

	document.ApplyForm.action="/gaControlServlet?operation=CheckManager&content=searchCheckAccount&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
 
 function ExportExcel() {
	document.ApplyForm.action="/gaControlServlet?menu_code=${param.menu_code}&operation=view&content=excelCheckAccountview";
	document.ApplyForm.submit();
}
 
 function Balance() {
	 if(document.ApplyForm.startDate.value==""||document.ApplyForm.startDate.value==null){
			 alert("请选择开始日期！");	
			 return;	
		}
	 if(document.ApplyForm.endDate.value==""||document.ApplyForm.endDate.value==null){
			 alert("请选择结束日期！");	
			 return;	
		}
	document.ApplyForm.action="/gaControlServlet?menu_code=${param.menu_code}&operation=view&content=balanceCheckAccountview";
	document.ApplyForm.submit();
}
</script>

<form name="ApplyForm" action="/gaControlServlet" method="post">
<input type="hidden" name="SEQ_NO" value="" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_all.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info -->
		<table width="100%" height="30" border="0" cellspacing="1"
			cellpadding="0">
			<tr>
				<td class="title1"><!-- 查询条件 --> <ait:message
					messageID="display.mutual.search_criteria" module="ess"></ait:message>
				</td>
			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td width="10%" class="info_title_01">开始日期</td>
						<td width="10%" class="info_content_00"><input type="text"
							style="width:80px" name="startDate" value="${startDate}"
							onClick="setday(this);" readonly="readonly"></td>
						<td width="10%" class="info_title_01">结束日期</td>
						<td width="10%" class="info_content_00"><input type="text"
							style="width:80px" name="endDate" value="${endDate}"
							onClick="setday(this);" readonly="readonly"></td>
						<td nowrap="nowrap" class="info_title_01">所属法人</td>
						<td nowrap="nowrap" class="info_content_00">									
   					       <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="search();"/>
						</td>
						<td width="10%" class="info_title_01">银行名称</td>
						<td width="10%" class="info_content_00">
						
						
			
						<ait:codeClass name="CHECKBANK_TYPE" codeClass="CheckBankInfor" all="all" cpnyId ="${cpnyId}" selected="${CHECKBANK_TYPE}"/></td>
						
						
						<!-- 新添加功能 -->
						
						<td width="10%" class="info_title_01">状态</td>
						 
						<td width="10%" class="info_content_00">
				     <select name="cheType">
				     <option value="5" <c:if test="${cheType==5}">selected</c:if>>全部
				     </option>
				     <option value="0" <c:if test="${cheType==0}">selected</c:if>>未使用
				     </option>
				        <option value="1"  <c:if test="${cheType=='1'}">selected</c:if>>已使用  
				         </option>   
				         <option value="2" <c:if test="${cheType=='2'}">selected</c:if>>作废      
				         </option>
				           
				         <option value="3" <c:if test="${cheType==3}">selected</c:if>>使用中
				         </option>                
				     </select>
						</td>
						<td width="10%" class="info_content_00">
						<ait:image src="/images/btn/Search.gif" align="absmiddle" onclick="javascript:search();" style="cursor:hand" /></td>
					    <td width="5%" class="info_content_00">
		                <img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ExportExcel();" /> 
		                <td width="10%" class="info_content_00">
<a target="_blank" href="/reports/template/check_account.xls"><ait:image src="/images/btn/Template.gif"  border="0" align="absmiddle" /></a></td>
						<td width="10%" class="info_content_00">
<ait:image src="/images/btn/Excel_Imp.gif"  border="0" align="absmiddle" onclick="javascript:importExcel();" style="cursor:hand"/></td>
						<td width="5%" class="info_content_00">
<a href="#" onClick="Balance();" style="color: red;font-weight:bold;">&nbsp;*&nbsp;盘点</a></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<!-- display 3 level menu --> <%@ include
			file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">基本信息</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="15%" class="info_title_01" nowrap>银行名称</td>
				<td width="15%" class="info_title_01" nowrap>账号</td>
				<td width="15%" class="info_title_01" nowrap>支票类型</td>
				<td width="15%" class="info_title_01" nowrap>支票号</td>
				<td width="8%" class="info_title_01" nowrap>创建者</td>
				<td width="8%" class="info_title_01" nowrap>录入日期</td>
				<td width="8%" class="info_title_01" nowrap>备注</td>
				<td width="8%" class="info_title_01" nowrap>状态</td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center"
					onClick="band('#E7F0EF','black',${oneResult.SEQ_NO});">
					<td align="center" style="color: #666666;" height="27">
					&nbsp;${oneResult.BANKNAME}</td>
					<td align="center" style="color: #666666;" height="27">
					&nbsp;${oneResult.ACCOUNT}</td>
					<td align="center" style="color: #666666;" height="27">
					&nbsp;${oneResult.CHECK_TYPE}</td>
					<td align="center" style="color: #666666;" height="27">
					&nbsp;${oneResult.CHECK_ACCOUNT}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.CREATE_BY}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.ENTRY_DATE}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.NOTE}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.ACTIVITY}</td>
				</tr>
			</c:forEach>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${recordCount < 6}">
				<c:forEach var="i" begin="1" end="${6-recordCount}" step="1">
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
<%--  源代码
		<!-- Page Navigation Start--> <ait:page link="/gaControlServlet"
			parameters="&operation=CheckManager&content=CheckAccount&menu_code=${param.menu_code}&personId=${personId}&empId=${empId}&startDate=${startDate}&endDate=${endDate}&cheType=${cheType}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupsize}"
			useJS="false" /> <!-- Page Navigation End -->
			
			 --%>
			 
		<!-- Page Navigation Start   自己修改转入到查询语句--> <ait:page link="/gaControlServlet"
			parameters="&operation=CheckManager&content=searchCheckAccount&menu_code=${param.menu_code}&personId=${personId}&empId=${empId}&startDate=${startDate}&endDate=${endDate}&cheType=${cheType}&CHECKBANK_TYPE=${CHECKBANK_TYPE}&cpnyId=${cpnyId}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupsize}"
			useJS="false" /> <!-- Page Navigation End -->

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
<iframe id='iemp'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">
</div>
</form>
</body>
</html>
