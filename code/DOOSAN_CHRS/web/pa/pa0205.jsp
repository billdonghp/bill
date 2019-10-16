<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.Vector,com.ait.pa.PaReport"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<%@ page import="com.ait.sy.common.PaHelper"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
    String pamonth = request.getParameter("pamonth");
	String statTypeCode = request.getParameter("statTypeCode");
	
    java.util.Calendar now = java.util.Calendar.getInstance();
	int year = now.get(now.YEAR);
	int month = now.get(now.MONTH)+1;
	if (pamonth ==null) {
		pamonth = year+("0"+month).substring(("0"+month).length()-2,("0"+month).length());
	}else{
		year = Integer.parseInt(pamonth.substring(0,4));
		month = Integer.parseInt(pamonth.substring(4,6));
	}
	
	
	String lockFlag = request.getParameter("lockFlag");
	/*
	if (lockFlag != null && Integer.parseInt(lockFlag) == PaHelper.UNLOCK_FLAG){
	    PaHelper.openProgress(pamonth);
	}
	*/
	
	PaReport report = new PaReport();  
	//Vector vlist = new Vector();
	//vlist = report.ColumnPaSelect();
	//request.setAttribute("vlist",vlist);
	List list = report.ColumnPaSelect();
	String column = "";

	Date d = new Date();
	SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
	String today=timeFormatter.format(d);
	HttpSession session2 = request.getSession(true);
	AdminBean admin2 = (AdminBean) session2.getAttribute("admin");
	request.setAttribute("cpnyDiff",admin2.getCpnyId());
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../pa/inc/pa_toolbar_balance.jsp"%>
			
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
		<%@ include file="../pa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->	   
		<br>
		<c:if test="${cpnyDiff=='78000000'||cpnyDiff=='63000000'}">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">数据传送</td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr align="center">
				<td width="*" height="30"  align="center" valign="middle" class="info_title_01">
				<!--考勤月--><ait:message  messageID="display.mutual.attendance_month" />
				    <select name="year" ><%
					for (int i=-4;i<=4;i++){
					%><option value="<%=year+i%>" <%=i==0?"selected":""%>><%=year+i%></option>
					<%}%>
				      </select>
				      <!--年-->
							<ait:message  messageID="display.mutual.year" />
				      <select name="month" ><%
					  	for (int i=1;i<=12;i++){
					%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
					<%}%>
				      </select>
				   <!-- 月 -->
					<ait:message  messageID="display.mutual.month" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="color:red; cursor:pointer;" onClick="openProcess();">向韩国传输数据</span>
				</td>
			</tr>
		</table>
		<br>
		</c:if>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--计算结果-->
					<ait:message  messageID="display.pay.maintenance.result.pay_result" module="pay"/>
				</td>
			</tr>
		 </table>
		 
	<form name="pa" method="post" action="pa_report_t_pa_result.jsp" target="_blank">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		    <td width="*" height="30"  align="center" valign="middle" class="info_title_01">
		    <!--考勤月--><ait:message  messageID="display.mutual.attendance_month" />
		    <ait:date monthName="month_result" yearName="year_result" />
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <!-- 员工区分类型 -->员工区分类型&nbsp;&nbsp;
		    <ait:empDiff name="statTypeCode" cpnyDiff="${cpnyDiff}"  selected="${param.statTypeCode}" /><!-- onChange="changeStatType()" -->
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <!--工资项目--><ait:message  messageID="display.pay.maintenance.result.list" module="pay"/>
		    ( <!--全部选中--><ait:message  messageID="display.mutual.select_2" />
		    <input type="checkbox" name="checkbox" value="checkbox" onclick="checkAll(this.checked) " class="check">)<!-- </td> -->
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <!-- 
		    <ait:image src="/images/btn/Balance.gif"  border="0" align="absmiddle" onclick="javascript:Balance();" style="cursor:hand" />
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	 -->
          	<ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel();" style="cursor:hand" />
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	工资异常数据导出
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	<ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExceptionExcel();" style="cursor:hand" title="工资等于0或者小于0的数据导出"/>
		    </td>
		 
		</tr>
		</table>
		
		<%
			for(int j=0;j<list.size();j++)
			{
				request.setAttribute("vlist"+j,(Vector)list.get(j));
			}
			int i=0;
		%>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr align="center">
		    	<td height="30" colspan="2"  align="center" class="info_content_01">
		        	<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		        	<input type="hidden" name="pamonth" value="">
				    	<tr  height="30" align="left">
					    	<c:forEach items="${vlist0}" var="vlist" >
					        	<td>
					        		<c:if test="${vlist.isView eq 1}">
						        		<input name="column" type="checkbox" value="${vlist.columnId}" class="check" checked>
						        	</c:if>
						        	<c:if test="${vlist.isView eq 0}">
						        		<input name="column" type="checkbox" value="${vlist.columnId}" class="check">
						        	</c:if>
					            </td>
						        <td width="25%">
									<ait:content enContent="${vlist.columnEnName}" zhContent="${vlist.columnname}" koContent="${vlist.columnKoName}"/>
							    </td>
								<% if((i+1)%4==0){%></tr><%} i++;%>
							</c:forEach>
		      		</table>
		    	</td>
		  	</tr>
		  	
		  	<tr align="center">
		    	<td height="30" colspan="2"  align="center" class="info_content_01">
		        	<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
				    	<tr height="30" align="left">
				    		<%i=0; %>
					    	<c:forEach items="${vlist2}" var="vlist" >
					        	<td>
						        	<c:if test="${vlist.isView eq 1}">
						        		<input name="column" type="checkbox" value="${vlist.columnId}" class="check" checked>
						        	</c:if>
						        	<c:if test="${vlist.isView eq 0}">
						        		<input name="column" type="checkbox" value="${vlist.columnId}" class="check">
						        	</c:if>
					            </td>
						        <td width="25%">
									<ait:content enContent="${vlist.columnEnName}" zhContent="${vlist.columnname}" koContent="${vlist.columnKoName}"/>
							    </td>
								<% if((i+1)%4==0){%></tr><%} i++;%>
							</c:forEach>
		      		</table>
		    	</td>
		  	</tr>
		  	
		  	<tr align="center">
		    	<td height="30" colspan="2"  align="center" class="info_content_01">
		        	<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
				    	<tr  height="30" align="left">
				    		<%i=0; %>
					    	<c:forEach items="${vlist1}" var="vlist" >
					        	<td>
					        		<c:if test="${vlist.isView eq 1}">
						        		<input name="column" type="checkbox" value="${vlist.columnId}" class="check" checked>
						        	</c:if>
						        	<c:if test="${vlist.isView eq 0}">
						        		<input name="column" type="checkbox" value="${vlist.columnId}" class="check">
						        	</c:if>
					            </td>
						        <td width="25%">
									<ait:content enContent="${vlist.columnEnName}" zhContent="${vlist.columnname}" koContent="${vlist.columnKoName}"/>
							    </td>
								<% if((i+1)%4==0){%></tr><%} i++;%>
							</c:forEach>
		      		</table>
		    	</td>
		  	</tr>
		</table>
	</form>
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
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--

function checkAll(para){
// alert(para);
	if(para){
		for(i=0;i<document.pa.column.length;i++){
			document.pa.column[i].checked=true;
		}
	}else{
		for(i=0;i<document.pa.column.length;i++){
			document.pa.column[i].checked=false;
		}
	}
}

function changeStatType(){
	location.href = "pa0205.jsp?menu_code=<%=menu_code%>" + "&statTypeCode="+document.all("statTypeCode").value ;
	}

function ImportExcel(){
	var year = document.all("year_result").value ;
    var month = document.all("month_result").value;
    var pamonth = year + month;

	var a = false;
	var s ="";
	
	for(i=0;i<document.pa.column.length;i++){
		if (document.pa.column[i].checked){
			a = true;
			break ;
			//s+=document.pa.column[i].value+" as \""+document.pa.column1[i].value+"\",";
		}
	}
	if (a==true){
		//document.pa.sql.value ="select " + s.substring(0,s.length-1) +" from T_PA_RESULT";
		// alert(document.pa.sql.value);
		document.pa.pamonth.value = pamonth ;
		document.pa.action="pa_report_t_pa_result.jsp";
		document.pa.submit();
	}
	else{
	//"请选择所需项目"
	alert('<ait:message  messageID="alert.pay.select_items" module="pay"/>');
	}
}

function ImportExceptionExcel(){
	var year = document.all("year_result").value ;
    var month = document.all("month_result").value;
    var pamonth = year + month;
    
    var a = false;
	var s ="";
	
	for(i=0;i<document.pa.column.length;i++){
		if (document.pa.column[i].checked){
			a = true;
			break ;
			
		}
	}
	if (a==true){
	    document.pa.pamonth.value = pamonth ;
		document.pa.action="pa_report_t_pa_result_exception.jsp";
		document.pa.submit();
	}
	else{
	//"请选择所需项目"
	alert('<ait:message  messageID="alert.pay.select_items" module="pay"/>');
	}


}
function Balance(){
location.href = "<%=menu_code%>_t.jsp?menu_code=<%=menu_code%>&flag=update&adminid=<%=admin.getAdminID()%>";
}

function openProcess(){
  var year = document.all("year").value ;
  var month = document.all("month").value;
  var pamonth = year + month;
  
  if (!confirm ('向韩国传送 ' + pamonth + ' 的工资数据!!!' ))
  {
  		return ;
  }
  
  var url = "/paControlServlet?operation=pa_OpenProgress&TABLE_NAME=PA_HISTORY&menu_code=${param.menu_code}&lockFlag=<%=PaHelper.UNLOCK_FLAG%>&PA_MONTH=" 
  			+ pamonth + "&year=" + year + "&month=" + month ;
  location.href = url;
  
}

function paymentPay(){
  var pamonth = document.all("AnnualPaymentsYear").value;
  var payear=document.all("OnPayMonth").value;
  var padate=document.all("PaymentDates").value;    
  var url = "/paControlServlet?operation=paInterface&content="+document.all("Project").value+"&AnnualPaymentsYear="+pamonth+"&OnPayMonth="+payear+"&PaymentDates="+padate+"&InputBase="+document.all("InputBase").value;
  location.href = url;  
}

 function   CheckNumber(tempvalue) {   
    var   patrn=/^[0-9]+.{0,1}[0-9]{0,4}$/;
    if  (!patrn.test(tempvalue)){         
       alert("请输入数字");          
       return  false;      
      }   
       return true; 
  } 
//-->
</SCRIPT>