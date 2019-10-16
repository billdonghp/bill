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

 if(document.form1.voiture.value==0){
  alert("请选择添加项目!");
  return;
 }
	document.form1.action="/ga/ga_add_voiturecosts.jsp?menu_code=${param.menu_code}&sign="+document.form1.voiture.value;
	document.form1.submit();
}

 function Update() { 
 
 if(document.form1.voiture.value==0){
  alert("请选择添加项目!");
  return;
 }
	document.form1.action="/ga/ga_update_voiturecosts.jsp?menu_code=${param.menu_code}&sign="+document.form1.voiture.value;
	document.form1.submit();
 
 }
 function View(id){
 document.form1.action="/ga/ga_view_voiturecosts.jsp?menu_code=${param.menu_code}&sign="+id;
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
</SCRIPT>

<body>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
String today=timeFormatter.format(d);
String year=today.substring(0,4);    
String month=StringUtil.checkNull((NumberUtil.parseInt(today.substring(5, 7))-1));  
String day=today.substring(8,10);
String backInMotnDate="";
try {
	backInMotnDate=timeFormatter.format(timeFormatter.parse(year+"-"+month+"-"+day));
} catch (Exception e) {	
	e.printStackTrace();
}
%>
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
<!--		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1">报表区</td>
		</tr>
		<tr>
			<td >&nbsp;</td>
		</tr>
	    <tr>
	      <td colspan="9">
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">	       		
			    <tr>
	              <td width="10%" class="info_title_01">区间车辆履历费用汇总表</td>
			      <td width="30%" class="info_content_00"><input type="text" name="excelStartDate" class="content" style="width:70px" value="<%=backInMotnDate%>" readonly onClick="setday(this);">&nbsp;&nbsp;至&nbsp;&nbsp;<input type="text" name="excelEndDate" class="content" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">&nbsp;&nbsp;&nbsp;&nbsp;费用</td>
			      <td width="10%" class="info_content_01"><ait:image src="../images/btn/Excel_Exp.gif" onclick="getAreaSummaryCosts()"></ait:image></td>					      
		      </tr>
			</table>
	      </td>
		</tr>		
		</table>-->
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
		      <td width="4%" class="info_title_01">
				编号</td>
		      <td width="8%" class="info_title_01">
				车种（名称）</td>
			 <td width="8%" class="info_title_01">
				车种（型号）</td>
		      <td width="10%" class="info_title_01">
				车种（牌号）</td>
			  <td width="6%" class="info_title_01">
				容纳人数</td>	
				<td width="6%" class="info_title_01">
				查看详细</td>	    
		      
		    </tr>
		    <% for(int i=0;i<allVoitureList.size();i++){ 
		      voitureBean=(VoitureBean)allVoitureList.get(i);
		      int count =allVoitureList.size();
		    %>
		    <tr onClick="band('#E7F0EF','black',<%=voitureBean.getVOITURE_ID()%>)" height="30">
		      <td align="center" style="color: #666666;"><%=i+1%></td>     
		      <td align="center" style="color: #666666;"><%=voitureBean.getVoiture_Brand() %></td>
		      <td align="center" style="color: #666666;"><%=voitureBean.getVoiture_Model() %></td>
		      <td align="center" style="color: #666666;"><%=voitureBean.getVoiture_Number()!=null?voitureBean.getVoiture_Number():"&nbsp;" %></td>
		      <td align="center" style="color: #666666;"><%=voitureBean.getSeats()!=null?voitureBean.getSeats():"&nbsp;" %></td>
		      <td align="center" style="color: #666666;"><a href="#" onclick="View(<%=voitureBean.getVOITURE_ID()%>)"><font color="red">详细查看</font> </a> </td>		    	     
		    </tr>
		   <%} %>	
		   <%for(int i=allVoitureList.size();i<10;i++){ %>
		   	<tr height="30">
		      <td align="center" style="color: #666666;">&nbsp;</td>     
		      <td align="center" style="color: #666666;">&nbsp;</td>
		      <td align="center" style="color: #666666;">&nbsp;</td>
		      <td align="center" style="color: #666666;">&nbsp;</td>
		      <td align="center" style="color: #666666;">&nbsp;</td>
		      <td align="center" style="color: #666666;">&nbsp;</td>		    	     
		    </tr>
		   <% }%>		
		    <tr align="center"> 
			 </tr>
		 </table>
		 <br />
		 <input type="hidden" name="voiture" value="0" />
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
