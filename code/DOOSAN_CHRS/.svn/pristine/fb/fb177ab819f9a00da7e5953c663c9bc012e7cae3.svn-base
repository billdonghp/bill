<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="com.ait.web.CommonXlsSqlHelper,com.ait.hrm.business.*" %>
<%@ page import="java.util.*,com.ait.sy.business.SyCodeServices,com.ait.util.*" %>
<jsp:useBean id="department" class="com.ait.hrm.bean.Department"/> <!-- data type contained in the dept_list -->
 <%@ include file="../inc/meta.jsp"%>
 <%@ include file="../inc/taglibs.jsp"%>
<html>   
	<head>
		<title>人事报表</title>  
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="JavaScript">              
   function doXlsSubmit(){            
   
      var xlsKeyObjs = document.getElementsByName("xlsKey");
      var xlsKey = "";
      var from = document.getElementById("from").value;
      var to = document.getElementById("to").value;            
      var beginYear=document.getElementById("beginYear").value;        
      var endYear=document.getElementById("endYear").value;
      var beginMonth=document.getElementById("beginMonth").value;
      var endMonth=document.getElementById("endMonth").value; 
      var joinTypeCode=document.getElementById("joinTypeCode1").value;
       var url="" ;       
      for (i = 0; i < xlsKeyObjs.length; i++)
         if (xlsKeyObjs[i].checked){
            xlsKey = xlsKeyObjs[i].value;             
            break;
         }
      if ("" == xlsKey){
        //"请选择要打印的报表！"
         alert('<ait:message  messageID="alert.emp.statistics.report_print"  module="hrm"/>');
         return;
      }else
      {
        url="/ReportServlet?xlsKey="+xlsKey ; 
      }
      
      if(xlsKey == "dimission"){
      	if(from == "" || to == ""){
      		//"请选择员工离职率统计报表的开始结束日期"
      		alert('<ait:message  messageID="alert.emp.statistics.start_date"  module="hrm"/>') ;
      		return ;
      	}
      	   url=url+"&from="+from+"&to="+to;
      }
      
       if(xlsKey == "distributionreport"){               
      	if(beginYear>endYear){
      		//"开始日期不能大于结束日期"
      		alert('<ait:message  messageID="alert.emp.statistics.end_date_before"  module="hrm"/>') ;
      		return ;
      	}if(beginYear==endYear){  
      	   if(beginMonth>endMonth)  
      		 //"开始日期不能大于结束日期"
      		 {
      		alert('<ait:message  messageID="alert.emp.statistics.start_date_after"  module="hrm"/>') ;
      		return ;             
      		}
      	}
      	url=url+"&beginYear="+beginYear+"&endYear="+endYear+"&beginMonth="+beginMonth+"&endMonth="+endMonth;             
      } 
      
       if(xlsKey == "postgroupreport")
       {
        url=url+"&joinTypeCode="+joinTypeCode
       }   
        if(xlsKey == "sex")
       {
       joinTypeCode=document.getElementById("joinTypeCode2").value;
         url=url+"&joinTypeCode="+joinTypeCode
       }           
        if(xlsKey == "empstatus")
       {
       joinTypeCode=document.getElementById("joinTypeCode3").value;
         url=url+"&joinTypeCode="+joinTypeCode
       }           
        if(xlsKey == "empContract")
       {
       joinTypeCode=document.getElementById("joinTypeCode4").value;
        url=url+"&joinTypeCode="+joinTypeCode
       }                           
      
      location.href= url;
              
   }
</script>       
	</head>                    
	             
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<c:import url="./inc/hrmreporttoolbar.jsp" />    
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
<%@ include file="../inc/common_toolbar_n.jsp"%>
<%
	GregorianCalendar currentDay = new GregorianCalendar();
	int month=currentDay.get(Calendar.MONTH) + 1;
	int year= currentDay.get(Calendar.YEAR);
%>
<form name="rptForm" action="ReportServlet" method="post">	
<input type="hidden" value="" name="deptid">		
 <table width="80%" border="1" cellpadding="2" cellspacing="1" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >
<tr>
    <td height="40"  align="left" valign="middle"  colspan="2"><!-- 人员现况月对比报表 -->
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
        <input name="xlsKey" type="radio" class="check" value="empNowContrast">            
    <ait:message  messageID="display.emp.statistics.statistic_report.headcount_monthly_comparison" module="hrm"/>     
    </td>
     <td height="30" colspan="2"  align="center" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </td>      
  </tr>        
<tr align="center">
    <td height="40" align="left" valign="middle" colspan="2">
       &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
	<input name="xlsKey" type="radio" class="check" value="dimission">
	<!-- 员工离职率统计报表  -->
     <ait:message  messageID="display.emp.statistics.statistic_report.leaver_rat" module="hrm"/>
	</td>
	<td height="30" colspan="2" align="center" valign="middle" colspan="2"> <!-- 开始日期  -->  
       &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
         <ait:message  messageID="display.mutual.start_date" />
                    
	<input type="text" name="from"  value="" class="content" style="width:90px " onClick="setday(this);" >&nbsp;&nbsp;&nbsp;
	<!-- 结束日期 -->
       <ait:message  messageID="display.mutual.end_date" />
	<input type="text" name="to"  value="" class="content" style="width:90px " onClick="setday(this);" >
	</td>
</tr>
<tr align="center">
<td height="40" colspan="2" align="left" valign="middle">
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
    <!--  人员配置分布情况报表 -->
      <input name="xlsKey" type="radio" class="check" value="distributionreport">
      <ait:message  messageID="display.emp.statistics.statistic_report.departmental_structure" module="hrm"/>          
</td>
	<td height="30"  align="center" valign="middle" colspan="2"> <!-- 起始年月  -->
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  
                    <ait:message  messageID="display.emp.statistics.statistic_report.from" module="hrm"/>                   
	<select name="beginYear">
	<%for (int i=-3;i<=20;i++){%>
		<option value="<%=year+i%>" <%=i==0?"selected":""%>><%=year+i%></option>
	<%}%>
	</select>
	<select name="beginMonth">
		<%for (int i=1;i<=12;i++){%>
    	<option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
	<%}%>
      </select>
      <!-- 结束年月 -->
                    <ait:message  messageID="display.emp.statistics.statistic_report.through" module="hrm"/>
                    
	<select name="endYear">
	<%for (int i=-3;i<=20;i++){%>
		<option value="<%=year+i%>" <%=i==0?"selected":""%>><%=year+i%></option>
	<%}%>
	</select>
	<select name="endMonth">
		<%for (int i=1;i<=12;i++){%>
    	<option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
	<%}%>
      </select>
  
    </td>
</tr>  

  <tr align="center">
    <td height="40" align="left" valign="middle" colspan="2"><!-- 员工职群分布报表 -->   
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;              
      <input name="xlsKey" type="radio" class="check" value="postgroupreport">
     <ait:message  messageID="display.emp.statistics.statistic_report.post_group_representation" module="hrm"/>
    </td>
     <td height="30" colspan="2" align="center" valign="middle">&nbsp;
     <ait:codeClass codeClass="EmpDivision" name="joinTypeCode1" selected="${joinTypeCode}" /></td>
  </tr>            
  <tr align="center">
    <td height="40"  align="left" valign="middle"  colspan="2"><!-- 员工性别统计分布报表 -->
    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
     <input name="xlsKey" type="radio" class="check" value="sex">
    <ait:message  messageID="display.emp.statistics.statistic_report.gender_statistic" module="hrm"/>              
     
    </td>
     <td height="30" colspan="2" align="center" valign="middle">&nbsp;
     <ait:codeClass codeClass="EmpDivision" name="joinTypeCode2" selected="${joinTypeCode}" />
      </td>      
  </tr>       
  
   <tr align="center">
    <td height="40" align="left" valign="middle"  colspan="2"><!-- 员工状态统计分布报表 -->
       &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
      <input name="xlsKey" type="radio" class="check" value="empstatus">
     <ait:message  messageID="display.emp.statistics.statistic_report.staff_status" module="hrm"/>
    
    </td>
     <td height="30" colspan="2" align="center" valign="middle">&nbsp;
     <ait:codeClass codeClass="EmpDivision" name="joinTypeCode3" selected="${joinTypeCode}" />
  </tr>
                         
   <tr align="center">
    <td height="40"  align="left" valign="middle" colspan="2" ><!-- 员工合同现况统计表 -->
     &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
       <input name="xlsKey" type="radio" class="check" value="empContract">
        <ait:message  messageID="display.emp.statistics.contract_statistic" module="hrm"/>
    
    </td>
     <td height="30" colspan="2" align="center" valign="middle">&nbsp;
     <ait:codeClass codeClass="EmpDivision" name="joinTypeCode4" selected="${joinTypeCode}" />
      </td>      
  </tr>      
                           
</table> 


  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="30">
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


