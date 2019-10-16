<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.utils.FormUtil,com.ait.ga.bean.*,com.ait.ga.cmd.visit.*"%>
<%@ page import="com.ait.utils.FormUtil,com.ibm.icu.text.SimpleDateFormat,com.ait.util.*" %>
<jsp:useBean id="allVoitureList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="voitureBean" class="com.ait.ga.bean.VoitureBean"/>
<jsp:useBean id="voitureManger" class="com.ait.ga.cmd.visit.VoitureManger"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>车辆履历</title>
</head>
<SCRIPT type="text/javascript">

 function Add() {
 	document.form1.action="/gaControlServlet?&operation=voitureManger&content=AddvoitureManger&menu_code=${param.menu_code}";
	document.form1.submit();
}

 function Update() { 
 
 if(document.form1.voiture.value==0){
  alert("请选择修改项目!");
  return;
 }
	document.form1.action="/gaControlServlet?&operation=voitureManger&content=UpdatevoitureManger&menu_code=${param.menu_code}&id="+document.form1.voiture.value;
	document.form1.submit();
 
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
		 document.form1.voiture.value=i;
	} 
function getAreaSummaryCosts(){
  if(dateCheck()){
  alert("开始日期必须在结束日期之后！");
 }else if(document.form2.excelStartDate.value==null||document.form2.excelEndDate.value==null||document.form2.excelEndDate.value==""||document.form2.excelStartDate.value==""){
  alert("日期不能为空！");
 }else{
  window.open("/reports/ga_report/ga_AreaSummaryCosts_Voiture.jsp?startdate="+document.form2.excelStartDate.value+"&enddate="+document.form2.excelEndDate.value,"");
  }
}

function dateCheck(){
var start=document.form2.excelStartDate.value.replace("-","");
var end = document.form2.excelEndDate.value.replace("-","");
if((start>end)){
 return true ;
}else{
return false;
}
}

function view(id){
	document.form1.action="/gaControlServlet?&operation=voitureManger&content=ViewvoitureManger&menu_code=${param.menu_code}&id="+id;
	document.form1.submit();
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
			<%@ include file="inc/gatoolbar_add.jsp"%>
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
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
	<form name="form2" method="post" action="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				<ait:message messageID="display.ga.view" module="ga"></ait:message>
				</td>
			</tr>     
		</table>
   </form>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
  <form name="form1" method="post" action="">		
		<table width="100%"  border="1"cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td class="info_title_01">
				序号</td>
		      <td class="info_title_01">
				事故发生时间</td>
			 <td class="info_title_01">
				事故情况</td>
		      <td class="info_title_01">
				发生地点</td>
			  <td class="info_title_01">
				事故原因</td>
			<td class="info_title_01">
				查看详情</td>	    
		      
		    </tr>
		    <c:forEach items="${list}" var="list" varStatus="i" end="10">
		    <tr onClick="band('#E7F0EF','black','${list.ID }')" height="30">
		      <td align="center" style="color: #666666;">${list.ID } &nbsp; </td>     
		      <td align="center" style="color: #666666;">&nbsp; ${list.STARTDATE }</td>
		      <td align="center" style="color: #666666;">&nbsp; ${list.STATE }</td>
		      <td align="center" style="color: #666666;">&nbsp; ${list.SITE }</td>
		      <td align="center" style="color: #666666;">&nbsp; ${list.CAUSE }</td>
		      <td align="center" style="color: #666666;">&nbsp; <a href="#" onclick="view('${list.ID }')"><font color="red">查看详情</font></a> </td>		    	     
		    </tr>
		    </c:forEach>
		    <c:if test="${fn:length(list) < 10}">
		    <c:forEach var="i" begin="1" end="${10-fn:length(list)}"
					step="1">
				  <tr height="30">
			      <td align="center" style="color: #666666;"> &nbsp; </td>     
			      <td align="center" style="color: #666666;">&nbsp;</td>
			      <td align="center" style="color: #666666;">&nbsp;</td>
			      <td align="center" style="color: #666666;">&nbsp;</td>
			      <td align="center" style="color: #666666;">&nbsp;</td>
			      <td align="center" style="color: #666666;">&nbsp;</td>		    	     
		    </tr>
			</c:forEach>
		    </c:if>
		    
		 </table>
		 <!-- Page Navigation Start-->
		<ait:page link="/gaControlServlet"
			parameters="&operation=visiterManagement&content=troubleManger&menu_code=${param.menu_code}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupsize}"
			useJS="false" />
		<!-- Page Navigation End -->
		 <br />
		 <input type="hidden" name="voiture" value="0" />
		</td>
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
<input type="hidden" name="currentPage" value="${currentPage}">
</form>
</form>
</body>

</html>
