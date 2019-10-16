<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:useBean id="itemSetList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="itemtList" class="java.util.ArrayList" scope="request" />
<%@ page import="com.ait.ev.bean.EvaluateItem"%>
<%@ page import="com.ait.ev.bean.EvaluateSetItem"%>
<jsp:useBean id="evaluateSet" class="com.ait.ev.bean.EvaluateSetItem" scope="page" />
<jsp:useBean id="evaluateItem" class="com.ait.ev.bean.EvaluateItem" scope="page" />
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<!-- ev_item_set_view.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function search(){

	$('DEPTID_SEQ').value = "";
	$('POSITION_SEQ').value = "";
	$('MONTH_SEQ').value = "";
	document.ApplyForm.action="/evControlServlet?operation=evaluateSet&content=evaluateItemSetView&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}

function Add() {
    document.ApplyForm.action="/evControlServlet?operation=evaluateSet&content=evaluateItemSetAddView&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
function Update() {

	if(document.ApplyForm.DEPTID_SEQ.value=="" || document.ApplyForm.POSITION_SEQ.value=="" || document.ApplyForm.MONTH_SEQ.value=="") {
		
	    alert("请选择修改项目");
		return;
	}
	document.ApplyForm.action="/evControlServlet?operation=evaluateSet&content=evaluateItemSetModifyView&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
function Delete() {
	
	if(document.ApplyForm.DEPTID_SEQ.value=="" || document.ApplyForm.POSITION_SEQ.value=="" || document.ApplyForm.MONTH_SEQ.value=="") {
		
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.ApplyForm.action="/evControlServlet?operation=evaluateSet&content=evaluateItemSetDelete&menu_code=${param.menu_code}";
		document.ApplyForm.submit();
	}
}


function band(backColor,textColor,i,j,k)
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
	document.ApplyForm.DEPTID_SEQ.value=i;
	document.ApplyForm.POSITION_SEQ.value=j;
	document.ApplyForm.MONTH_SEQ.value=k;

} 
function para_init(){
	// 您是否进行工资月的输入项目初始化
	if (confirm('评价月'+document.ApplyForm.year.value+document.ApplyForm.month.value+'\n您是否初始化该月评价项目设置')){
		document.ApplyForm.action="/evControlServlet?operation=evaluateSet&content=evaluateItemSetInit&menu_code=${param.menu_code}";
		document.ApplyForm.submit();
	}
	return false ;
}
</script>

<form name="ApplyForm" action="" method="post">
<input type="hidden" name="DEPTID_SEQ" value="" />
<input type="hidden" name="POSITION_SEQ" value="" />
<input type="hidden" name="MONTH_SEQ" value="" />
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
		<td valign="TOP" align="CENTER"><!-- display basic info --> <br>
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
						  <td nowrap="nowrap" class="info_title_01"><!-- 评价月份-->
				          	评价月份 
				          </td>
				          <td nowrap="nowrap" class="info_content_00">
				            <ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
				          	
				          </td>
					    <td width="10%" class="info_title_01">部门</td>
						<td width="10%" class="info_content_00"><ait:selDept
							name="DeptId" selected="${DeptId}" all="all" supervisorType="pa"/></td>
						<td width="10%" class="info_title_01">职位</td>
						<td width="10%" class="info_content_00">
						<!-- 	<ait:codeClass name="positionCodeId" codeClass="PositionCode" selected="${positionCodeId}" all="all"/>
						 -->
						 <select name="selectPosition">
						    <option value="">请选择</option>
							<c:forEach items="${positionList}" var="positionList" varStatus="i">
								
								<option value="${positionList.CODE_ID}" <c:if test="${positionList.CODE_ID==selectPosition}">selected</c:if>>
									${positionList.CODE_NAME}
								</option>
							</c:forEach>
						</select>
						</td>
						<td width="15%" class="info_content_00"><ait:image
							src="/images/btn/Search.gif" align="absmiddle"
							onclick="javascript:search();" style="cursor:hand" />
						</td>
						<td width="12%" align="center" class="info_content_00" nowrap="nowrap">
					    	<a href="#" onClick="para_init() ;">评价项目初始化</a>
					    
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<!-- display 3 level menu --> <%@ include
			file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<!-- display content -->
		<%
			if (!errorMsg.equals("")) {
		%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%
			}
		%>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">评价项目设置</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
			    <td width="25%" class="info_title_01" nowrap>评价月份</td>
				<td width="25%" class="info_title_01" nowrap>部门</td>
				
				<td width="15%" class="info_title_01" nowrap>职位</td>
				<td width="20%" class="info_title_01" nowrap>评价项目/比例</td>
				
			</tr>
			<%for(int i=0;i<itemSetList.size();i++){                       
			evaluateSet = (EvaluateSetItem) itemSetList.get(i);
		    itemtList = evaluateSet.getItemList();
		    %>
				<tr align="center"
					onClick="band('#E7F0EF','black','<%=evaluateSet.getDeptId()%>','<%=evaluateSet.getPosition()%>','<%=evaluateSet.getMonth() %>');">
					<td align="center" style="color: #666666;">
					&nbsp;<%=evaluateSet.getMonth() %></td>
					<td align="center" style="color: #666666;" height="27">
					&nbsp;<%=evaluateSet.getDeptName() %>&nbsp; (<%=evaluateSet.getFourthDept() %>)</td>
	
					<td align="center" style="color: #666666;">
					&nbsp;<%=evaluateSet.getPositionName() %></td>
					  <td align="center" style="color: #666666;">
	
				    <%	for(int j=0;j<itemtList.size();j++){            
				    	evaluateItem = (EvaluateItem) itemtList.get(j);%>
				    	<font style="color: #666666;">
				    	<%= StringUtil.checkNull(evaluateItem.getItemName()) + StringUtil.getString((25 - evaluateItem.getItemName().length()) , "&nbsp;&nbsp;&nbsp;&nbsp;") %>&nbsp;&nbsp;

			            <%= StringUtil.checkNull(evaluateItem.getItemProportion())+ StringUtil.getString((25 - StringUtil.checkNull(evaluateItem.getItemProportion()).length()) , "&nbsp;&nbsp;") %>&nbsp;&nbsp;
			            
			    		
			    		
				    	</font><br>
				    
				    <%}%>
		          </td>
					
				</tr>
		 <%}%>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${resultCount < 6}">
				<c:forEach var="i" begin="1" end="${6-resultCount}" step="1">
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

		<!-- Page Navigation Start--> <ait:page link="/evControlServlet"
		    
			parameters="&operation=evaluateSet&content=evaluateItemSetView&menu_code=${param.menu_code}&DeptId=${DeptId}&selectPosition=${selectPosition}&year=${year}&month=${month}"
			total="${resultCount}" currentpage="${currentPage}"
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
</form>
</body>
</html>
