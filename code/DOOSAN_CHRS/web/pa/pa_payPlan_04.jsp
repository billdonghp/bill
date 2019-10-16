<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<%@ page import="com.ait.utils.FormUtil,com.ait.sqlmap.util.*,com.ait.util.NumberUtil,java.util.*"%>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap" scope="page"></jsp:useBean>
<jsp:useBean id="thisyear" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="planYear" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="startMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="endMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="payPlan04Serche" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="paVersion" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="peVersion" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="paVersionStr" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="peVersionStr" class="java.lang.String" scope="request"></jsp:useBean>
<html>
<head>
<ait:processBarJs />
<title>加班费推算</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
    String pamonth = request.getParameter("pamonth");
    java.util.Calendar now = java.util.Calendar.getInstance();
	String year = String.valueOf(now.get(now.YEAR));
	String month = String.valueOf(now.get(now.MONTH)+1);
	if (pamonth == null || pamonth.length() == 0) {
		pamonth = year + month ;
	}else{
	year = pamonth.substring(0,4);
	month = pamonth.substring(4,6);
	}
	int year_t=NumberUtil.parseInt(year);
	int month_t=NumberUtil.parseInt(month);

%>

<SCRIPT LANGUAGE="JavaScript">
function Search(){
	if($('paVersion').value=="" || $('paVersion').value==null ){
	alert("请选择已经推算过的基本工资！")
	return;
	}
	if($('peVersion').value=="" || $('peVersion').value==null ){
	alert("请选择已经推算过的补贴！")
	return;
	}
	if($('datumStartMonth').value>$('datumEndMonth').value){
	 alert("工资基准结束月必须大于或者等于开始月！");
	 return;
	}
	if($('versionNote').value=="" ||$('versionNote').value==null){
	alert("请输入版本信息！");
	return;
	}		
	 document.form1.action="/paControlServlet?operation=paPlan&content=payPlan04Serche&menu_code=pa0404";
	 beforeSubmit();
	 document.form1.fireSubmit();
	 afterSubmit();
}

function Save(){
 if($('search').value==""||$('search').value==null){
 alert("请先进行搜索数据！");
 return;
 }
 document.form1.action="/paControlServlet?operation=paPlan&content=payPlan04Save&menu_code=pa0404";
 document.form1.submit();

}

</SCRIPT>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
String today=timeFormatter.format(d);

%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_pa_plan.jsp"%>
			
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="left">
		<!-- display basic info -->
		<br>
		<form name="form1" action="" method="post">
		<input type="hidden" name="search" value="${search}">
	<table width="60%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		<td align="center" class="info_title_01">
		  计划年度
		<Td>
		<td align="center" class="info_content_00">
		 <select name="planYear" class="pamonth"><%
			for (int i=0;i<2;i++){
			%><option value="<%=year_t+i%>" <%=(NumberUtil.parseInt(planYear))==(year_t+1)?"selected":""%>><%=year_t+i%></option>
			<%}%>
		      </select>
		      <!--年-->
			<ait:message  messageID="display.mutual.year" />
		</td>		   
		<td height="15"  align="center" class="info_title_01">
			基准年月
	    <td>
	    <td align="center" class="info_content_00">
	   <select name="datumYear" class="pamonth"><%
			for (int i=0;i<1;i++){
			%><option value="<%=year_t+i%>" <%=year_t==(year_t+i)?"selected":""%>><%=year_t+i%></option>
			<%}%>			
		      </select>
					<ait:message  messageID="display.mutual.year" />
		      <select name="datumStartMonth" class="pamonth"><%
			  	for (int i=1;i<=12;i++){
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=(NumberUtil.parseInt(startMonth))==(i)?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select>月&nbsp;&nbsp;～&nbsp;&nbsp; 
		      <select name="datumEndMonth" class="pamonth"><%
			  	for (int i=1;i<=12;i++){
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>"  <%=(NumberUtil.parseInt(endMonth))==(i)?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select>月
		 </td>		
		<td align="center" class="info_title_01">
		  基本工资
		  </td>
		<td align="center" class="info_content_00">
		  <select name="paVersion">
		  <option value="">请选择</option>
		  <c:forEach items="${paVersion}" var="test" varStatus="i">
		  <option value="${test.seqid}" <c:if test="${test.seqid==paVersionStr}">selected</c:if>>${test.versionnote}</option>
		  </c:forEach>
		  </select>
		  </td>
		  <td height="15"  align="center" class="info_title_01">
		  补贴
		  </td>
		<td align="center" class="info_content_00">
		  <select name="peVersion">
		  <option value="">请选择</option>
		  <c:forEach items="${peVersion}" var="test" varStatus="i">
		  <option value="${test.seqid}" <c:if test="${test.seqid==peVersionStr}">selected</c:if>>${test.versionnote}</option>
		  </c:forEach>
		  </select>
		  </td>	
	   <td align="center" class="info_title_01">		  
		   版本信息
		 <Td>
		 <td align="center" class="info_content_00">
		 <textarea name="versionNote" style=" height: 40px;width:250px " >${versionNote}</textarea>
		 </td>	  
		</tr>
		</table>

		<!-- display 3 level menu -->
		<%@ include file="../pa/inc/common_toolbar.jsp"%>		
		
		<!-- display content -->	
	<br>		
	<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--计算结果-->
					加班费推算</td>
			</tr>
    </table>
     <table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="center"><ait:processBar/>&nbsp;<br></td>			
	</tr>
	</table>
    <br>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      
		      <td nowrap="nowrap" class="info_title_01" rowspan="2">
				编号</td>
		      <td nowrap="nowrap" class="info_title_01" rowspan="2">
				部门</td>
			 <td nowrap="nowrap" class="info_title_01" rowspan="2">
				工资类型</td>		      
		      <td nowrap="nowrap" class="info_title_01" rowspan="2">
				职群</td>	
			  <td nowrap="nowrap" class="info_title_01" rowspan="2">
				职级</td>			   
			  <td nowrap="nowrap" class="info_title_01" rowspan="2">
				工资项目</td>		
			  <c:forEach begin="1" end="12" varStatus="i">			
			  <td nowrap="nowrap" class="info_title_01" colspan="3">
			  ${i.index}月</td>		  
		      </c:forEach>	     
			 </tr>
		     <tr align="center" bgcolor="#F5F5F5">
			 <c:forEach begin="1" end="12" varStatus="i">	
			  <td nowrap="nowrap" class="info_title_01">基数</td>
			  <td nowrap="nowrap" class="info_title_01">加班时间</td>
			  <td nowrap="nowrap" class="info_title_01">加班费</td>			
		    </c:forEach>	 
		     </tr>
			 <c:forEach items="${list}" var="test" varStatus="i">	
			 <tr>
			 <td nowrap="nowrap" class="info_content_00">${i.index+1}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.BG_NM}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.WAGESTYPE}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.GROUPTYPE}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.POSTYPE}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PAYTYPE}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.BASE1}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE1}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA1}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.BASE2}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE2}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA2}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.BASE3}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE3}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA3}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.BASE4}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE4}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA4}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.BASE5}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE5}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA5}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.BASE6}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE6}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA6}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.BASE7}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE7}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA7}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.BASE8}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE8}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA8}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.BASE9}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE9}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA9}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.BASE10}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE10}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA10}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.BASE11}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE11}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA11}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.BASE12}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE12}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA12}</td>				 				
			 </tr>
			</c:forEach>
		</table>

	</form>	
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
<ait:xjos/> 
</body>
</html>
