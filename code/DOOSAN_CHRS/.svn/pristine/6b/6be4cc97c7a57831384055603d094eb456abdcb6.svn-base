<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<jsp:useBean id="seq" class="java.lang.String" scope="request" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function search(){

	document.ApplyForm.action="/gmControlServlet?operation=ga_carManager&content=Add_carManager&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}

function Add() {
	document.ApplyForm.action="/gmControlServlet?operation=ga_carManager&content=Add_CarManager&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
function Update() {

	if(document.ApplyForm.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	document.ApplyForm.action="/gmControlServlet?operation=ga_carManager&content=update_CarManager&menu_code=${param.menu_code}&wasteId="+document.ApplyForm.temp.value;
	document.ApplyForm.submit();
}
function Delete() {
	if(document.ApplyForm.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.ApplyForm.action="/gmControlServlet?operation=waste&content=deleteWasteBasicInformation&menu_code=${param.menu_code}&wasteId=" + document.ApplyForm.temp.value;
		document.ApplyForm.submit();
	}
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
	document.ApplyForm.temp.value=i;
} 

function ImpExcel(applyno){
var url="/reportControlServlet?operation=crystal&reportName=wasteCrystalReport&waste_no="+applyno;
window.open(url ,"");
}

function ImportExcel() {
	document.ApplyForm.action="/gmControlServlet?operation=waste&content=wasteView&menu_code=${param.menu_code}&flag=2";
	document.ApplyForm.submit();	
}
</script> 
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");		
String today=timeFormatter.format(d);
String today1=timeFormatter1.format(d);
%>
<form name="ApplyForm" action="/gmControlServlet" method="post">
<input type="hidden" name="addViewJumpWasteViewFlag" value="${addViewJumpWasteViewFlag}"/>
<input type="hidden" name="seq" value="${seq}"/>
<input type="hidden" name="temp" value=""/>
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

		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>		
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%> 
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					车辆基本信息
				</td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		   <tr align="center" bgcolor="#F5F5F5">
		   	  <td nowrap="nowrap" class="info_title_01">
				编号
		      <td nowrap="nowrap" class="info_title_01">
				车辆名称</td>
		      <td nowrap="nowrap" class="info_title_01">
				型号</td>
			  <td nowrap="nowrap" class="info_title_01">
				出厂日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				车牌号码</td>			  
		      <td nowrap="nowrap" class="info_title_01">
				座位数</td>			      
			  <td nowrap="nowrap" class="info_title_01">
				里程数</td>
			  <td nowrap="nowrap" class="info_title_01">
				车辆使用状态</td>
			  <td nowrap="nowrap" class="info_title_01">
				车辆状态</td>
			  <td nowrap="nowrap" class="info_title_01">
				查看</td>			  	
		    </tr>
		    <c:forEach items="${carList}" var="test" varStatus="i">
		    <tr align="center" onclick="aa()">
		    <td nowrap="nowrap" class="info_content_01">
		    <input type="hidden" value="${test.ID }" name="ID">
		    ${i.index}</td>	
		    <td nowrap="nowrap" class="info_content_01">
		    ${test.CAR_NAME}</td>
		    <td nowrap="nowrap" class="info_content_01">${test.MODEL_NUMBER}</td>		    
		    <td nowrap="nowrap" class="info_content_01">${test.OUT_DATE}</td>		    
		    <td nowrap="nowrap" class="info_content_01">${test.CAR_NUM}</td>		    
		    <td nowrap="nowrap" class="info_content_01">${test.SIT_BUM}</td>
		    <td nowrap="nowrap" class="info_content_01">${test.KILOMETER}</td>		    
		    <td nowrap="nowrap" class="info_content_01">
		    <c:choose>
		    <c:when test="${test.CAR_USEFLAG =='0' }">未使用
		    </c:when>
		    <c:otherwise>
		    已派出
		    </c:otherwise>
		    </c:choose>
		    </td>
		    <td nowrap="nowrap" class="info_content_01">${test.CAR_FLAG_NAME}</td>
		    <td nowrap="nowrap" class="info_content_01">${test.KILOMETER}</td>
		    </tr>
		   </c:forEach>	
		 </table>
		 </form>
		 					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=wpCommand&content=wpApplyConfirmForSerch&menu_code=${param.menu_code}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
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
