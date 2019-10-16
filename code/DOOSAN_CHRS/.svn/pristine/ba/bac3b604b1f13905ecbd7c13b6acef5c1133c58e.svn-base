<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:directive.page import="com.ait.pa.PaReport"/>
<jsp:directive.page import="com.ait.sqlmap.util.SimpleMap"/>
<jsp:directive.page import="com.ait.sqlmap.util.ObjectBindUtil"/>
<jsp:directive.page import="com.ait.util.StringUtil"/>
<jsp:directive.page import="java.util.List"/>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<script language="JavaScript" type="text/JavaScript">
function layerClose()
{
	$('emp_list').innerHTML = "" ;
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
	$('empID').value=cell.childNodes[0].firstChild.nodeValue;
	layerClose();
}

var time=null;
function SearchContent(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
     	var url = "/ajaxControlServlet" ;
     	var pars = "operation=paSearchEmployeeAll&condition=" + encodeURIComponent(condition)+"&id="+id;
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		layer = $('emp_list');
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		
		layer.style.visibility = 'visible';
		new Ajax.Updater({success: layer}, url, {method: 'post', parameters: pars, onFailure: layerClose});	
}

function showDept() {
          
          theUrl="/hrmControlServlet?menu_code=pa0104&operation=searchPaEmpByDept&empID=empID";
          var name="searchEmp";
          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
          window.open(theUrl,name,features);

}
function Search(){

	document.searchMonthPa.submit();
}
<%

	java.util.Calendar now = java.util.Calendar.getInstance();
	int year = now.get(Calendar.YEAR);

	SimpleMap sMap = new SimpleMap();
	sMap = ObjectBindUtil.getData(request);
	if (sMap.get("empID") == null)
		sMap.setString("empID","");
	request.setAttribute("sMap",sMap);
	if("".equals(StringUtil.checkNull(sMap.getString("PaYear")))){
		sMap.setString("PaYear",String.valueOf(year));
	}
%>
</script>
<body>
<%
	List list;
	SimpleMap person = new SimpleMap();
	PaReport paReport = new PaReport();
	person = (SimpleMap)paReport.RetrievePersonalSalary(sMap);
	list=paReport.RetrieveSalary(sMap);
	request.setAttribute("YearSalaryInfo",list);
	request.setAttribute("PaPerson",person);
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_s_2_notSaveButton.jsp"%>
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
		<form  name="searchMonthPa"  method="POST"  action="">
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--年工资-->
					<ait:message  messageID="display.pay.view.annual.annual_pay" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"　class="dr_d">
		  <tr>
		    <td>
		      <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
		        <tr>
		          
		          <td width="13%" class="info_title_01"><!--工 号-->
					<ait:message  messageID="display.mutual.emp_id_2" /></td>
		          <td width="25%" class="info_content_01">
		          		<input id="empID" name="empID" size="8" value="${sMap.empID}" onKeyUp="SearchContent(this.value,this.id)" />
		          		<ait:image src="/images/btn/Dep.gif" align="absmiddle" onclick="showDept();" style="cursor:hand" />
		          </td>
		          <td width="10%" class="info_title_01"><!--姓 名-->
					<ait:message  messageID="display.mutual.name" /></td>
		          <td width="12%" class="info_content_01">
		       		<ait:content enContent="${PaPerson.PINYINNAME}" zhContent="${PaPerson.CHINESENAME}" koContent="${PaPerson.KOREANNAME}"/>
		       	</td>
		          <td width="14%" height="30" class="info_title_01"><!--工资年度-->
					<ait:message  messageID="display.pay.view.annual.pay_year" module="pay"/></td>
		          <td width="25%" class="info_content_01">
		          	<select name="PaYear" ><%
					for (int i=-4;i<=4;i++){
					%><option value="<%=year+i%>" 
						<%if(String.valueOf(year+i).equals(sMap.getString("PaYear"))){ out.print("selected");}%>><%=year+i%></option>
					<%}%>
				      </select>
				     <!-- 年-->
					<ait:message  messageID="display.mutual.year" />
				  </td>
		          </tr>
		      </table>
		    </td>
		  </tr>
		</table>
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr>
		<td height="5">&nbsp;</td>
		</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"　class="dr_d">
		  <tr>
		    <td>
		      <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
		        <tr>
		          <td width="12%" class="info_title_01"><!--部门-->
					<ait:message  messageID="display.mutual.department" /></td>
		          <td width="18%" class="info_content_01">
		       		<ait:content enContent="${PaPerson.DEPT_EN_NAME}" zhContent="${PaPerson.DEPARTMENT}" koContent="${PaPerson.DEPT_KOR_NAME}"/>
		       	</td>
		          <td width="12%" height="30" class="info_title_01"><!--职位-->
					<ait:message  messageID="display.mutual.position" /></td>
		          <td width="12%" class="info_content_01">
		       		<ait:content enContent="${PaPerson.POSITION_EN_NAME}" zhContent="${PaPerson.POSITION}" koContent="${PaPerson.POSITION_KOR_NAME}"/>
		       </td>
		          <td width="12%" height="30" class="info_title_01" ><!--职 务-->
					<ait:message  messageID="display.mutual.post"  /></td>
		          <td width="12%" class="info_content_01">
		       		<ait:content enContent="${PaPerson.POST_EN_NAME}" zhContent="${PaPerson.POST}" koContent="${PaPerson.POST_KOR_NAME}"/>
		       	</td>
		          <td width="12%" class="info_title_01"><!--入社日期-->
					<ait:message  messageID="display.mutual.hire_date" /></td>
		          <td width="34%" class="info_content_01">${PaPerson.JOIN_DATE}</td>
		        </tr>
		      </table>
		    </td>
		  </tr>
		</table>
		</form>
		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr>
		<td height="10">&nbsp;</td>
		</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"　class="dr_d">
		  <tr>
		    <td>
			<table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
		      <tr>
		    <td class="info_title_00" nowrap><!--月份--><ait:message  messageID="display.pay.view.annual.month" module="pay"/></td>
		    <td class="info_title_00" nowrap><!--基本工资--><ait:message  messageID="display.mutual.base_pay" /></td>
			<td class="info_title_00" nowrap><!--住宅补助-->住宅补助</td>
		    <td class="info_title_00" nowrap><!--职务津贴--><ait:message  messageID="display.mutual.position_allowance" /></td>
		    <td class="info_title_00" nowrap><!--特殊补助-->特殊补助</td>
		    <td class="info_title_00" nowrap><!--地区补助-->地区补助</td>
		    <td class="info_title_00" nowrap><!--值班补助-->值班补助</td>
		    
		    <td class="info_title_00" nowrap><!--支给错误-->支给错误</td>
		    <td class="info_title_00" nowrap><!--其他支给-->其他支给</td>
		    <td class="info_title_00" nowrap><!--其他补偿-->其他补偿</td>
		    
		    <td class="info_title_00" nowrap><!--业绩工资-->业绩工资</td>
		    <td class="info_title_00" nowrap><!--应发工资--><ait:message  messageID="display.mutual.salary_payable" /></td>
		     
		    <td class="info_title_00" nowrap><!--考勤减除-->考勤减除</td> 
		    <td class="info_title_00" nowrap><!--其他减除-->其他减除</td> 
		    <td class="info_title_00" nowrap><!--减除错误-->减除错误</td> 
		    <td class="info_title_00" nowrap><!--住宅减除-->住宅减除</td> 
		    <td class="info_title_00" nowrap><!--减除小计-->减除小计</td> 
		    		    
		    <td class="info_title_00" nowrap><!--个人保险合计--><ait:message  messageID="display.pay.view.annual.total_individual_ins" module="pay"/></td> 
		    <td class="info_title_00" nowrap><!--个人住房公积金-->个人住房公积金</td>
		    
		    <td class="info_title_00" nowrap><!-- 本次支给合计--> 本次支给合计</td>
		    <td class="info_title_00" nowrap><!--本次实缴税额-->本次实缴税额</td>     
		    <td class="info_title_00" nowrap><!--本次实领工资-->本次实领工资</td>
		    <td class="info_title_00" nowrap><!--公司保险合计--><ait:message  messageID="display.pay.view.annual.toal_employer_ins" module="pay"/></td> 
		    <td class="info_title_00" nowrap><!--公司住房公积金-->公司住房公积金</td> 
		  </tr>
		   <c:forEach items="${YearSalaryInfo}" var="YSInfo" varStatus="s">
		      <tr>
		        <td class="info_content_01" nowrap>${YSInfo.MONTH}</td>
		        <td class="info_content_01" nowrap>${YSInfo.BASE_PAY}</td>
		        <td class="info_content_01" nowrap>${YSInfo.RESIDENTIAL_GRANTS}</td>
		        <td class="info_content_01" nowrap>${YSInfo.DUTIES_ALLOWANCE}</td>
		        <td class="info_content_01" nowrap>${YSInfo.SPECIAL_GRANTS}</td>
		        <td class="info_content_01" nowrap>${YSInfo.REGIONAL_GRANTS}</td>
		        <td class="info_content_01" nowrap>${YSInfo.DUTY_SUBSIDIES}</td>
		        <td class="info_content_01" nowrap>${YSInfo.STICKS_TO_THE_WRONG}</td>
		        <td class="info_content_01" nowrap>${YSInfo.TO_THE_OTHER}</td>
		        <td class="info_content_01" nowrap>${YSInfo.OTHER_COMPENSATION}</td>
		        <td class="info_content_01" nowrap>${YSInfo.PERFORMANCE_PAY2 + YSInfo.PERFORMANCE_PAY3}</td>
		        <td class="info_content_01" nowrap>${YSInfo.MEET_TOGETHER}</td>
		        <td class="info_content_01" nowrap>${YSInfo.ATTENDANCE_MINUS}</td>
		        <td class="info_content_01" nowrap>${YSInfo.OTHER_LESS}</td>
		        <td class="info_content_01" nowrap>${YSInfo.REDUCE_ERRORS}</td>
		        <td class="info_content_01" nowrap>${YSInfo.RESIDENTIAL_MINUS}</td>
		        <td class="info_content_01" nowrap>${YSInfo.MINUS_SUBTOTAL}</td>
		        <td class="info_content_01" nowrap>${YSInfo.TOTAL_PERSONAL_INSURANCE}</td>
		        <td class="info_content_01" nowrap>${YSInfo.PERSONAL_HOUSING_FUND}</td>
		        <td class="info_content_01" nowrap>${YSInfo.THIS_TOTAL_SUPPORT}</td>
		        <td class="info_content_01" nowrap>${YSInfo.THIS_ACTUAL_TAX}</td>
		        <td class="info_content_01" nowrap>${YSInfo.THIS_ACTUAL_WAGE}</td>
		        <td class="info_content_01" nowrap>${YSInfo.TOTAL_INSURANCE_COMPANIES}</td>
		        <td class="info_content_01" nowrap>${YSInfo.COMPANY_HPFS}</td>
		      </tr>
		    </c:forEach>
		      </table>
		    </td>
		  </tr>
		</table>

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
		<td bgcolor="#D0D0FF" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:1;"></div>
</body>


