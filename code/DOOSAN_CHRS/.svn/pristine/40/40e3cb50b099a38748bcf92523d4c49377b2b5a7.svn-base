<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<script src="../js/gmMuli.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/ext-all.css" />
</head>
<body>
<script language="javascript1.5" type="text/javascript">
function dateSelectClick()
{
	document.ApplyForm.action="/gmControlServlet?operation=eatStatistic&menu_code=${param.menu_code}&content=eat_confirm";
 	document.ApplyForm.submit();
}


function Search(){
	document.ApplyForm.action="/gmControlServlet?menu_code=${param.menu_code}&operation=eatStatistic&content=eat_confirm";
	document.ApplyForm.submit();
}

function Confirm(){
	var falg = true;
	var applyno = document.getElementsByName("applyno") ;
  	for (var i = 0; i< applyno.length ; i++){
		if(applyno[i].checked){
		falg = false;
		}
	}
	
	if(falg){
		alert("请选择要确认的人员");
		return;
	}
	document.ApplyForm.action="/gmControlServlet?operation=eatStatistic&content=eat_confirmUpdate&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
	
}

function Delete()
{	
	if(confirm("是否删除当前选择的记录!!!"))
	{
		document.ApplyForm.action="/gmControlServlet?operation=eatStatistic&content=eat_confirmDels&menu_code=${param.menu_code}" ;
		document.ApplyForm.submit();
	}
}

function del(id)
{	
	if(confirm("是否删除当前选择的记录!!!")){
		document.ApplyForm.action="/gmControlServlet?operation=eatStatistic&content=eat_confirmDel&menu_code=${param.menu_code}&chNo="+id;
		document.ApplyForm.submit();
	}
	
}

function checkAll()
{
    var selected = document.ApplyForm.ck_all.value == "0" ? true : false;
    var applyno = document.getElementsByName("applyno") ;
  	for (var i = 0; i< applyno.length ; i++){
		applyno[i].checked = selected ;
	
	}
    document.ApplyForm.ck_all.value = selected ? "1" : "0";
}
function ImportExcel(){
	document.ApplyForm.action="/gmControlServlet?operation=eatStatistic&content=ImportExcel&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
function ImportExcelInterface(){
	document.ApplyForm.action="/gmControlServlet?operation=eatStatistic&content=ImportExcelInterface&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}

</script> 
<form name="ApplyForm" method="post" action="">
<input name="listSize" value="${fn:length(arrangementList)}" type="hidden">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_deleteAndOk.jsp"%>
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
			<td class="title1"><!-- 查询条件 -->
			<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>
			</td>
		</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	       <tr>
	          <td class="info_title_01" ><!-- 部门 -->
	        <ait:message messageID="display.mutual.dept" ></ait:message>   
	          </td>
	          <td class="info_content_00"  > <ait:selDept name="deptID" selected="${deptID}" all="all"  supervisorType="hr"/></td>
	          <td class="info_title_01"  ><!-- 职号/姓名 -->
	         <ait:message messageID="display.mutual.emp_no_name" module="ess" ></ait:message> 
	          </td>
	          <td class="info_content_00"  >
	          <input type="text" name="key" value="${key}" /></td>
	          <td nowrap="nowrap" class="info_title_01" ><!--  选择日期-->
	          	选择日期
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="selectdate" value="${current_date}" onClick="setday(this);" readonly style="width:70px">	
	          </td>  
	          <td nowrap="nowrap" class="info_title_01" ><!-- 确认情况 -->
	          	确认情况
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<select name="qryType">
			         <option value="3" <c:if test="${qryType==3}">selected</c:if>>请选择</option>
			         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未确认</option>   
			         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已确认</option>
			     </select>
	          </td>  
	          <td class="info_content_00"  >
	          	<ait:image src="/images/btn/Search.gif" align="absmiddle" onclick="javascript:Search();" style="cursor:hand" />&nbsp;&nbsp;&nbsp;
	          	<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="javascript:ImportExcel();"/>
	          </td>
	           <td nowrap="nowrap" class="info_title_01">
	          	刷卡机接口数据
	          </td>  
	          <td class="info_content_00"  >	          
	          	<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="javascript:ImportExcelInterface();"/>
	          </td>
	        </tr>
	        </table>

		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="9">
				
					就餐确认
				</td>
			</tr>
			<c:if test="${error!=null}">
			<tr>
				<td align="center"  colspan="10">
				<font color="red">${error}</font>
				</td>
			</tr>
			</c:if>
		</table>
		
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	  <input type="hidden" name="ck_all" value="0" />
	  <input type="hidden" name="applySize" value="${fn:length(list)}"/>  
	<tr>
	  <td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;">全选</td>
	  <td class="info_title_01" nowrap>职号</td>
	  <td class="info_title_01" nowrap>姓名</td>
	  <td class="info_title_01" nowrap>课组</td>
	  <td class="info_title_01" nowrap>部门</td>
	  <td class="info_title_01" nowrap>就餐日期</td>
	  <td class="info_title_01" nowrap>餐别</td>
	  <td class="info_title_01" nowrap>餐次</td>
	  <td class="info_title_01" nowrap>班车</td>
	  <td class="info_title_01" nowrap>申请时间</td>
	  <td class="info_title_01" nowrap>状态</td>
	  <td class="info_title_01" nowrap>总务状态</td>
	  <td class="info_title_01" nowrap>操作</td>
	</tr>
	<c:forEach items="${list}" var="list" varStatus="i">
	<tr>
	  <td class="info_content_01" nowrap>
	  <c:if test="${(list.CONFIRM == 0 && list.FALG == 0) || (list.CONFIRM == 1 && list.FALG == 0)}">
	  <input type="checkbox" name="applyno" value="${list.ID}" />
	  </c:if>
	  <c:if test="${list.CONFIRM == 1 || list.FALG == 1}">
	  &nbsp;
	  </c:if>
	  <input type="hidden" name="id${list.ID}" value="${list.ID}" />
	  </td>
	  <td class="info_content_01" nowrap>${list.EMPID }</td>
	  <td class="info_content_01" nowrap>${list.CHINESENAME }</td>
	  <td class="info_content_01" nowrap>${list.DEPTNAME }</td>
	  <td class="info_content_01" nowrap>${list.DEPTNAME1 }</td>
	  <td class="info_content_01" nowrap>
		${list.OTDATE}&nbsp;</td>
	  <td class="info_content_01" nowrap>
	  ${list.CODE_NAME }&nbsp;</td>
	  <td class="info_content_01" nowrap>
		${list.eatType}
		&nbsp;

	</td>	
	  <td class="info_content_01" nowrap>
		${list.LINE_NAME }&nbsp;
	  </td>
	  <td class="info_content_01" nowrap>
		${list.APPLYDATE}&nbsp;
	  </td>
	  <td class="info_content_01" nowrap>&nbsp;
		<c:if test="${list.CONFIRM == 0}"><font color="#990099">未确认</font></c:if> 
		<c:if test="${list.CONFIRM == 1}"><font color="#00CC00">已确认</font></c:if>
	  </td>
	  <td class="info_content_01" nowrap>&nbsp;
		<c:if test="${list.FALG == 0}"><font color="#990099">总务未确认</font></c:if> 
		<c:if test="${list.FALG == 1}"><font color="#00CC00">总务已确认</font></c:if>
	  </td>
	  <td class="info_content_01" nowrap>&nbsp;
		<c:if test="${list.FALG == 0}">
		<a href="#" onclick="del(${list.ID })">删除</a></c:if> 
	  </td>
	</tr>
	</c:forEach>
	 
	</table>
	
	<input type="hidden" name="currentPage" value="${currentPage}">
	 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gmControlServlet"
		               parameters="&menu_code=${param.menu_code}&operation=eatStatistic&content=eat_confirm&qryType=${qryType}&deptID=${deptID}&key=${key}&selectdate=${current_date }" 
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
