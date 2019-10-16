<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<%@ page import="java.util.Vector,com.ait.pa.PaReport"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<%@ page import="com.ait.sy.common.PaHelper,com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.utils.FormUtil,java.util.*,com.ait.util.NumberUtil"%>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap" scope="request"></jsp:useBean>
<jsp:useBean id="planYear" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="thisyear" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="startMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="endMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="yesThisYearRoseMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="yesNextYearRoseMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="noThisYearRoseMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="noNextYearRoseMonth" class="java.lang.String" scope="request"></jsp:useBean>
<ait:processBarJs />
<html>
<head>
<title>基本工资推算</title>
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
	month =pamonth.substring(4,6);
	}
	int year_t=NumberUtil.parseInt(year);
	int month_t=NumberUtil.parseInt(month);

%>

<SCRIPT LANGUAGE="JavaScript">
function Search(){
	if($('versionNote').value==""||$('versionNote').value==null){
	 alert("版本信息不能为空！");
	 return;
	}
	if($('datumStartMonth').value>$('datumEndMonth').value){
	 alert("工资基准结束月必须大于或者等于开始月！");
	 return;
	}
	if(($('yesThisYearRate').value==""||$('yesThisYearRate').value==null) && $('yesThisYearRoseMonth').value>$('datumEndMonth').value){
	 alert("年薪制本年度上涨比率不能为空！");
	 return;
	}
	if(($('noThisYearRate').value==""||$('noThisYearRate').value==null) && $('noThisYearRoseMonth').value>$('datumEndMonth').value){
	 alert("非年薪制本年度上涨比率不能为空！");
	 return;
	}	
	if($('sysYear').value!=$('planYear').value && ($('yesNextYearRate').value==""||$('yesNextYearRate').value==null)){
	 alert("年薪制下年度上涨比率不能为空！");
	 return;
	}
	if($('sysYear').value!=$('planYear').value && ($('noNextYearRate').value==""||$('noNextYearRate').value==null)){
	 alert("非年薪制下年度上涨比率不能为空！");
	 return;
	}
	    document.form1.action="/paControlServlet?operation=paPlan&content=payPlan01Serche&menu_code=pa0401";
		beforeSubmit();
		document.form1.fireSubmit();
		afterSubmit();
}

function Save(){ 	
 if($('search').value==""||$('search').value==null){
 alert("请先进行搜索数据！");
 return;
 }
		 document.form1.action="/paControlServlet?operation=paPlan&content=payPlan01Save&menu_code=pa0401";
		 document.form1.submit();
	
}

function paseValueToAmount(value){   
                if(value!=null&&value!=''){   
                    var decimalIndex=value.indexOf('.');   
                    if(decimalIndex=='-1'){   
                        return false;   
                    }else{   
                        var decimalPart=value.substring(0,decimalIndex+1);   
                        if(Number(decimalPart)<1){   
                            return true;   
                        }else{   
                            return false;   
                        }   
                    }   
                }   
                return false;   
 }
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<form name="form1" action="" method="post">
<input type="hidden" name="sysYear" value="<%=year%>">
<input type="hidden" name="search" value="${search}">
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
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		<td align="center" class="info_title_01">计划年度</td>
		<td align="center" class="info_content_00">
		    <select name="planYear" class="pamonth"><%
			for (int i=0;i<2;i++){
			%><option value="<%=year_t+i%>" <%=(NumberUtil.parseInt(planYear))==(year_t+1)?"selected":""%>><%=year_t+i%></option>
			<%}%>			
		    </select><ait:message  messageID="display.mutual.year" /> <!--年-->
        </td>
        <td align="center" class="info_title_01">基准年月</td>
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
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=(NumberUtil.parseInt(endMonth))==(i)?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select>月
        </td>        
         <td align="center" class="info_title_01">版本信息</td>
         <td align="center" class="info_content_00" colspan="3"><textarea name="versionNote" style=" height: 30px;width:300px ">${versionNote}</textarea></td>
		</tr>
		
		<tr>
		<td  align="center" class="info_title_01">年薪制</td>
		<td align="center" class="info_content_00">
		      本年度<select name="yesThisYearRoseMonth" class="pamonth"><%
			  	for (int i=1;i<=12;i++){
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=(NumberUtil.parseInt(yesThisYearRoseMonth))==(i)?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select>月&nbsp;&nbsp;&nbsp;
		     上涨比率<input name="yesThisYearRate" value="${yesThisYearRate}" type="text" style="width:40px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">%
		 </td>
		 
		 <td align="center" class="info_title_01">年薪制</td>
		 <Td align="center" class="info_content_00"> 		 
		      下年度<select name="yesNextYearRoseMonth" class="pamonth"><%
			  	for (int i=1;i<=12;i++){
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=(NumberUtil.parseInt(yesNextYearRoseMonth))==(i)?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select>月&nbsp;&nbsp;&nbsp;
		     上涨比率<input name="yesNextYearRate" value="${yesNextYearRate}" type="text" style="width:40px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">%
		 </td>
		<td  align="center" class="info_title_01">非年薪制</td>
		<td align="center" class="info_content_00">
		      本年度<select name="noThisYearRoseMonth" class="pamonth"><%
			  	for (int i=1;i<=12;i++){
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=(NumberUtil.parseInt(noThisYearRoseMonth))==(i)?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select>月&nbsp;&nbsp;&nbsp;
		     上涨比率<input name="noThisYearRate" value="${noThisYearRate}" type="text" style="width:40px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">%
		 </td>
		 <td align="center" class="info_title_01">非年薪制</td>
		 <Td align="center" class="info_content_00"> 		 
		      下年度<select name="noNextYearRoseMonth" class="pamonth"><%
			  	for (int i=1;i<=12;i++){
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=(NumberUtil.parseInt(noNextYearRoseMonth))==(i)?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select>月&nbsp;&nbsp;&nbsp;
		     上涨比率<input name="noNextYearRate" value="${noNextYearRate}" type="text" style="width:40px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">%
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
					基本工资推算</td>
			</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="center"><ait:processBar/>&nbsp;<br></td>			
			</tr>
	</table>
	
	<br>	
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5" style='position: relative; top: expression(this.offsetParent.scrollTop);'>
		      
		      <td nowrap="nowrap" class="info_title_01">
				编号</td>
		      <td nowrap="nowrap" class="info_title_01">
				部门</td>
			 <td nowrap="nowrap" class="info_title_01">
				工资类型</td>		      
		      <td nowrap="nowrap" class="info_title_01">
				职群</td>	
			  <td nowrap="nowrap" class="info_title_01">
				职级</td>			   
			  <td nowrap="nowrap" class="info_title_01">
				工资项目</td>		
			  <c:forEach begin="1" end="12" varStatus="i">			
			  <td nowrap="nowrap" class="info_title_01">
			  ${i.index}月</td>		  
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
			 <td nowrap="nowrap" class="info_content_00">${test.PAY1}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PAY2}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.PAY3}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.PAY4}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.PAY5}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.PAY6}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.PAY7}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.PAY8}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.PAY9}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.PAY10}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PAY11}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PAY12}</td>				 				
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