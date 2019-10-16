<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ include file="../inc/taglibs.jsp"%>  
 <jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<html>
<head>

<%@ include file="../inc/meta.jsp"%>
<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<title>部门职级调整</title>
<script language=JavaScript> 
function Search(){
	document.form1.action="/paControlServlet?operation=viewdeptpostgradecmd&menu_code=${param.menu_code}&deptid=" + document.form1.DEPTID.value ;
	document.form1.submit();
}
function Save(){
	var checkFlag = 0 ;
	var selectarg=document.getElementsByName("selectC");
	for(var i=0;i<selectarg.length;i++)
	{
		if(selectarg[i].checked){
			checkFlag = 1 ;
			break ;	
		}
	}
	
	if (checkFlag == 0){
		alert("请选择部门名称");
		return;
	}else{
		document.form1.action="/paControlServlet?operation=updatedeptpostgradecmd&menu_code=${param.menu_code}&deptid=" + document.form1.DEPTID.value ;
		document.form1.submit();
	}
	
	
	
}
function checkAll()
{
	var selected = document.form1.ck_all.value == "0" ? true : false;
	var selectarg = document.getElementsByName("selectC");
	for(var i=0;i<selectarg.length;i++)
	{    
	    selectarg[i].checked = selected;     
	 
	}
	document.form1.ck_all.value = selected ? "1" : "0";
}
 

 
 
 
function postGradeYearChange(postGradeYear)
{

	
		
	if(document.form1.c.checked == true){
		var size = document.form1.selectC.length ;
		var selIndex = postGradeYear.selectedIndex;
		
	    for(var z = 0 ; z < size ; z++ )
		{
			if(document.form1.c.checked && document.form1.selectC[z].checked)
			{
				var sel = document.form1(document.form1.selectC[z].value + "_POST_GRADE_YEAR");
		    		  
	    		if(sel.type=="select-one"){
	 
	    			sel.options[selIndex].selected=true;
	    		}
	    	}
		}
	}
}

</script>             
</head>                                                                                                                                                
<body>                                     
<form name="form1" action="/paControlServlet" method="post">
<input name="ck_all" value="0" type="hidden"/>
<input type="hidden" name="deptSize" value="${fn:length(deptpostgradedata)}"/>    	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
		<%@ include file="../inc/toolbar_save_only.jsp"%>
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
			<td align="left" class="title1" ><!--查询条件--><ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td >
	      	<table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	      		<tr>
	      			<td class="info_title_01" width="12%"><!--部门-->
					<ait:message  messageID="display.mutual.department"/></td>
					 <td class="info_content_00" >
				    <ait:selDept name="DEPTID" selected="${DEPTID}" supervisorType="pa" all="all"/>
					</td>
			         <td  class="info_content_01" width="10%">
				    <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
					</td> 
					<td class="info_content_01" width="8%"><!-- 下拉框联动 --><ait:message  messageID="display.mutual.toggle_all"/>
						<input type="checkbox" name="c" value="c"  class="check" <c:if test="${c == 'c'}"> checked </c:if> />
				   </td>
				   
				 
				   
				   
				 </tr>
			</table>
	      </td>
		</tr>
		</table>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp" %>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" >部门职级</td>
			</tr>
		</table>                                      
		<table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr>
				<td class="info_title_01" width="10%"><a href="#" onclick="checkAll();" style="color:red">全选</a></td>
				<td class="info_title_01" width="70%">部门名称</td>
				<td class="info_title_01" width="20%">职级年份</td>
			</tr> 
			<c:forEach items="${deptpostgradedata}" var="VCtr" varStatus="status"> 
			<tr>
			    <td class="info_content_01"><input type="checkbox" name="selectC" value="${VCtr.DEPTID}_${VCtr.POST_GRADE_YEAR}"/></td>
			    <td class="info_content_01">${VCtr.DEPTNAME}</td>
			    <td class="info_content_01">
				    <select name="${VCtr.DEPTID}_${VCtr.POST_GRADE_YEAR}_POST_GRADE_YEAR" onchange="postGradeYearChange(this);"> 
				    	<option value="">无</option>
						<c:forEach items="${postgradeYearList}" var="oneResult">
							<option value="${oneResult.POST_GRADE_YEAR}" <c:if test="${oneResult.POST_GRADE_YEAR == VCtr.POST_GRADE_YEAR}">selected</c:if> >${oneResult.POST_GRADE_YEAR}</option>
						</c:forEach>
					</select>
			    </td>    
			</tr>      
			</c:forEach>
		</table> 
		               
		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="15">
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
</form>
</body>
</html>