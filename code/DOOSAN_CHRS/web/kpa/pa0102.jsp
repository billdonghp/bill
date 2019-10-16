<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:directive.page import="com.ait.sy.common.PaHelper"/>
<jsp:directive.page import="com.ait.pa.PaReport"/>
<jsp:directive.page import="com.ait.sqlmap.util.SimpleMap"/>
<jsp:directive.page import="com.ait.sqlmap.util.ObjectBindUtil"/>
<jsp:directive.page import="com.ait.util.StringUtil"/>

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
          
          theUrl="/hrmControlServlet?menu_code=pa0102&operation=searchPaEmpByDept&empID=empID";
          var name="searchEmp";
          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
          window.open(theUrl,name,features);

}
function Search(){

	document.searchPaInfo.submit();
}
</script>
<%
SimpleMap sMap = new SimpleMap();
sMap = ObjectBindUtil.getData(request);
request.setAttribute("sMap",sMap);

String PAMonth=sMap.getString("YEAR")+sMap.getString("MONTH");

Integer lockFlag = PaHelper.getProcessLockFlag(PAMonth);
%>

</head>
<body>
<%
	SimpleMap paMap=new SimpleMap();
	PaReport paReport = new PaReport();
	SimpleMap parameterObject = new SimpleMap();
	parameterObject.setString("PaMonth", PAMonth);
	parameterObject.setString("empID", StringUtil.checkNull(sMap.getString("empID")));
	
	paMap=(SimpleMap)paReport.RetrieveBasicPaInfo(parameterObject);
	request.setAttribute("paMap",paMap);
	
%>


   


<form action="" method="POST" name="searchPaInfo"> 


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
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--员工基本信息-->
					<ait:message  messageID="display.pay.view.base.basic_info" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"　class="dr_d">
		  <tr>
		    <td>
		      <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
		      <tr>
		      	<td width="12%" height="30" class="info_title_01"><!--工 号-->
					<ait:message  messageID="display.mutual.emp_id_2" /></td>
				<td width="20%" class="info_content_01">
					<input id="empID" name="empID" size="8" onKeyUp="SearchContent(this.value,this.id)"  value="${sMap.empID}"/>
					<ait:image src="/images/btn/Dep.gif" align="absmiddle" onclick="showDept();" style="cursor:hand" />
				</td>
		       	<td width="10%" class="info_title_01"><!--姓 名-->
					<ait:message  messageID="display.mutual.name" /></td>
		       	<td width="10%" class="info_content_01">
		       		${paMap.CHINESENAME}
		       	</td>
		       	<td width="10%" class="info_title_01"><span class="tablecontent"><!--级号-->级号</span></td>
		        <td width="10%" class="info_content_01" >
		       		${paMap.POST_GRADE_CODE}&nbsp;${paMap.POST_COEF}
		        </td>
		        <td width="12%" class="info_title_01"><span class="tablecontent"><!--工资发放月-->
					<ait:message  messageID="display.mutual.pay_month" /></span></td>
		        <td width="12%" class="info_content_01" >
		       		<ait:date yearName="YEAR" monthName="MONTH" yearSelected="${sMap.YEAR}" monthSelected="${sMap.MONTH}" yearPlus="10" />
		        </td>
		       
		     </tr>
		     <tr>
		        <td width="12%" height="30" class="info_title_01"><!--部门-->
					<ait:message  messageID="display.mutual.department" /></td>
		        <td width="12%" class="info_content_01">
		       		${paMap.DEPARTMENT}
		       	</td>
		        <td width="10%" height="30" class="info_title_01"><!--职位-->
					<ait:message  messageID="display.mutual.position" /></td>
			    <td width="10%" class="info_content_01">
		       		${paMap.POSITION}
		       	</td>
			    <td width="10%" height="30" class="info_title_01"><!--职 务-->
					<ait:message  messageID="display.mutual.post"  /></td>
			    <td width="10%" class="info_content_01">
		       		${paMap.POST}
		       	</td>
		        <td width="12%" class="info_title_01"><!--入社日期-->
					<ait:message  messageID="display.mutual.hire_date" /></td>
		        <td width="12%" class="info_content_01">${paMap.JOIN_DATE}</td>
		      </table>
		    </td>
		  </tr>
		</table>
		</form>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"　class="dr_d">
		  <tr>
		  <!--  
		    <td width="231" height="30"><span class="title1">人事基础</span></td>
		    
		    <td width="8"></td>
		    -->
		    <td width="232"><span class="title1"><!--工资基础-->
					<ait:message  messageID="display.pay.view.base.pay_basis" module="pay"/></span></td>
		    <td width="8"></td>
		    <td width="232"><span class="title1"><!--考勤基础-->
					<ait:message  messageID="display.pay.view.base.attendance" module="pay"/></span></td>
		    <td width="8"></td>
		    <td width="232"><span class="title1"><!--加班基础-->
					<ait:message  messageID="display.pay.view.base.ot_basis" module="pay"/></span></td>
		  </tr>
		  <tr>
		  
		    <td rowspan="2" valign="top" width="31%">
			    
			    <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
			         <tr>
			           <td height="30" colspan="2" class="info_title_01" nowrap="nowrap"><!--级号工资-->
							级号工资</td>
			           <td width="58%" class="info_content_01">${paMap.PAY_CLASS_WAGES + 0}</td>
			         </tr>
			         <tr>
			           <td height="30" colspan="2" class="info_title_01" nowrap="nowrap"><!--年薪工资-->
							年薪工资</td>
			           <td width="58%" class="info_content_01">${paMap.ANNUAL_PAY + 0}</td>
			         </tr>
			         <tr>
			           <td width="14%" rowspan="4" class="info_title_01" nowrap="nowrap"><!--津贴-->
					<ait:message  messageID="display.mutual.allowance" /></td>
			           <td height="30" class="info_title_01"><!--职务津贴-->
					<ait:message  messageID="display.mutual.position_allowance" /></td>
			           <td class="info_content_01">${paMap.POST_ALLOWANCE + 0}</td>
			         </tr>
			         <tr>
			           <td height="30" class="info_title_01" nowrap="nowrap"><!--住宅补助-->住宅补助</td>
			           <td class="info_content_01">${paMap.RESIDENTIAL_GRANTS + 0}</td>
			         </tr>
			         <tr>
			           <td height="30" class="info_title_01" nowrap="nowrap"><!--特殊补助-->特殊补助</td>
			           <td class="info_content_01">${paMap.SPECIAL_GRANTS + 0}</td>
			         </tr>
			         <tr>
			           <td height="30" class="info_title_01" nowrap="nowrap"><!--地区补助-->
					<ait:message  messageID="display.mutual.allowance_subsidy" /></td>
			           <td class="info_content_01">${paMap.REGIONAL_GRANTS + 0}</td>
			         </tr>
			        
			         <tr>
			         <td width="14%" rowspan="5" class="info_title_01" nowrap="nowrap"><!--加 减 项-->
						<ait:message  messageID="display.pay.view.monthly.variance" module="pay"/></td>
			           <td height="30"  class="info_title_01" nowrap="nowrap"><!--支给错误-->支给错误</td>
			           <td width="58%" class="info_content_01">${paMap.STICKS_TO_THE_WRONG + 0}</td>
			         </tr>
					  <tr>
			           <td height="30"  class="info_title_01" nowrap="nowrap"><!--其他支给-->其他支给</td>
			           <td width="58%" class="info_content_01">${paMap.TO_THE_OTHER + 0}</td>
			         </tr>
			          <tr>
			           <td height="30" class="info_title_01" nowrap="nowrap"><!--住宅减除-->住宅减除</td>
			           <td width="58%" class="info_content_01">${paMap.RESIDENTIAL_MINUS + 0}</td>
			         </tr>
			         <tr>
			           <td height="30" class="info_title_01" nowrap="nowrap"><!--减除错误-->减除错误</td>
			           <td width="58%" class="info_content_01">${paMap.REDUCE_ERRORS + 0}</td>
			         </tr>
			         <tr>
			           <td height="30" class="info_title_01" nowrap="nowrap"><!--其他减除-->其他减除</td>
			           <td width="58%" class="info_content_01">${paMap.OTHER_LESS + 0}</td>
			         </tr>
		    	</table>	
		    </td>
		    <td rowspan="2" width="1%"></td>
		    <td height="160" valign="top" width="31%">
		
			    <table width="100%"  border="0" cellpadding="4" cellspacing="1" class="dr_d">
			         <tr>
			           <td width="40%" height="29" class="info_title_01" nowrap="nowrap"><!--病假-->
							<ait:message  messageID="display.mutual.sick_leave" /></td>
			           <td width="60%" class="info_content_01">${paMap.SICK_LEAVE + 0}</td>
			         </tr>
			          <tr>
			           <td width="40%" height="29" class="info_title_01" nowrap="nowrap"><!--三月内病休-->三月内病休</td>
			           <td width="60%" class="info_content_01">${paMap.SICK_LEAVE_THREE_MONTH + 0}</td>
			         </tr>
			          <tr>
			           <td width="40%" height="29" class="info_title_01" nowrap="nowrap"><!--三月外病休-->三月外病休</td>
			           <td width="60%" class="info_content_01">${paMap.SICK_LEAVE_SIX_MONTH + paMap.SICK_LEAVE_NINE_MONTH + paMap.SICK_LEAVE_ONE_YEARS + paMap.SICK_LEAVE_EIGHTEEN_MONTH + paMap.SICK_LEAVE_TWO_YEARS}</td>
			         </tr>
			         <tr>
			           <td height="29" class="info_title_01" nowrap="nowrap"><!--事假-->
					<ait:message  messageID="display.mutual.casual_leave" /></td>
			           <td class="info_content_01">${paMap.CASUAL_LEAVE + 0}</td>
			         </tr>
			         <tr>
			           <td height="29" class="info_title_01" nowrap="nowrap"><!--旷工-->
					<ait:message  messageID="display.mutual.absenteeism" /></td>
			           <td class="info_content_01">${paMap.ABSENTEEISM + 0}</td>
			         </tr>
			         <tr>
			           <td height="29" class="info_title_01" nowrap="nowrap"><!--迟到-->
					<ait:message  messageID="display.mutual.tardiness" /></td>
			           <td class="info_content_01">${paMap.CONVERTED_TARDINESS + 0}</td>
			         </tr>
			         <tr>
			           <td height="29" class="info_title_01" nowrap="nowrap"><!--早退-->
					<ait:message  messageID="display.mutual.truancy" /></td>
			           <td class="info_content_01">${paMap.CONVERTED_TRUANCY + 0}</td>
			         </tr>
			         <tr>
			           <td height="29" class="info_title_01" nowrap="nowrap"><!--休职-->
					<ait:message  messageID="display.mutual.unpaid_leave" /></td>
			           <td class="info_content_01">${paMap.UNPAID_LEAVE + 0}</td>
			         </tr>
			         <tr>
			           <td height="29" class="info_title_01" nowrap="nowrap"><!--婚假-->
						<ait:message  messageID="display.mutual.marriage_leave" /></td>
			           <td class="info_content_01">${paMap.MARRIAGE_LEAVE + 0}</td>
			         </tr>
			         <tr>
			           <td height="29" class="info_title_01" nowrap="nowrap"><!--丧假-->
						<ait:message  messageID="display.mutual.bereavement_leave" /></td>
			           <td class="info_content_01">${paMap.BEREFT_LEAVE + 0}</td>
			         </tr>
			         <tr>
			           <td height="29" class="info_title_01" nowrap="nowrap"><!--产假-->
						产假</td>
			           <td class="info_content_01">${paMap.MATERNITY_LEAVE + 0}</td>
			         </tr>
			         <tr>
			           <td height="29" class="info_title_01" nowrap="nowrap"><!--放假-->
						放假</td>
			           <td class="info_content_01">${paMap.VACATION + 0}</td>
			         </tr>
			         <tr>
			           <td height="29" class="info_title_01" nowrap="nowrap"><!--未勤-->
						未勤</td>
			           <td class="info_content_01">${paMap.INSUFFICIENT_ATTENDANCE + 0}</td>
			         </tr>
			     </table>
				 <table width="100%"  border="0" cellpadding="0" cellspacing="0">
			           <tr>
			            <td height="5" class="info_content_01"></td>
			           </tr>
		         </table>
			</td>
		    <td rowspan="2" width="1%"></td>
		    <td rowspan="2" valign="top" width="31%">
			    <table width="100%"  border="0" cellpadding="4" cellspacing="1" class="dr_d">
			         <tr>
			           <td width="25%" rowspan="4" class="info_title_01" nowrap="nowrap"><!--加班时间-->
					<ait:message  messageID="display.pay.view.base.total_ot" module="pay"/></td>
			           <td width="30%" height="30" class="info_title_01" nowrap="nowrap"><!--合 计-->
					<ait:message  messageID="display.pay.view.base.total"  module="pay"/></td>
			           <td width="45%" class="info_content_01">${paMap.TOTAL_WEEKDAY_OT + paMap.WEEKEND_OT + paMap.HOLIDAY_OT}</td>
			         </tr>
			         <tr>
			           <td height="30" class="info_title_01" nowrap="nowrap"><!--平 日-->
					<ait:message  messageID="display.pay.view.base.weekday" module="pay" /></td>
			           <td class="info_content_01">${paMap.TOTAL_WEEKDAY_OT + 0}</td>
			         </tr>
			         <tr>
			           <td height="30" class="info_title_01" nowrap="nowrap"><!--公休日-->
					<ait:message  messageID="display.pay.view.base.weekend" module="pay" /></td>
			           <td class="info_content_01">${paMap.WEEKEND_OT + 0}</td>
			         </tr>
			         <tr>
			           <td height="30" class="info_title_01" nowrap="nowrap"><!--节假日-->
					<ait:message  messageID="display.pay.view.base.holiday" module="pay" /></td>
			           <td class="info_content_01">${paMap.HOLIDAY_OT + 0}</td>
			         </tr>
			         
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
</html>