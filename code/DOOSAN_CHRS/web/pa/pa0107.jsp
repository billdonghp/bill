<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="employeeListForFlag" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>个人别工资支给明细查看</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript"> 
function ImportExcel()
{
	document.form1.action = "/paControlServlet?operation=paDetailView&content=ImportExcelPerson_B&menu_code=${param.menu_code}";
    document.form1.submit(); 
}
function Search(){
	document.form1.action="/paControlServlet?operation=paDetailView&content=F_viewDetail&&menu_code=${param.menu_code}";
	document.form1.submit();
}

var time=null;
function SearchEmp(condition,id){		
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
     	var pars = "operation=paSearchEmployeeAll&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft;
		  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
    	layeri.style.height = 48;
		layer.style.height = 48; 
    }else if(size<210){
		layeri.style.height = size;
		layer.style.height = size;  
    }else{
    	layeri.style.height = 210;
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText;
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('key').value=cell.childNodes[0].firstChild.nodeValue;
		$('name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
		layerClose();
}
function PrintReport1(){
var url="/reportControlServlet?operation=crystal&reportName=pa_detail_all_person_b&AVG_THIS_ACTUAL_WAGE="+document.all("AVG_THIS_ACTUAL_WAGE").value+"&empid="+document.all("key").value+"&startmonth="+(document.all("startyear").value+document.all("startmonth").value)+"&endmonth="+(document.all("endyear").value+document.all("endmonth").value)
       +"&patype="+document.form1.patype.value;
window.open(url ,"");
}



function PrintReport(){
	var url = "" ;
	if (document.all("qufen").value == "C_12067_1330306")
	{
		url = "pa_detail_all_person_f.asp" ;
	}
	else
	{
		url = "pa_detail_all_person_b.asp" ;
	}
	
	var url="http://10.40.128.28:8099/<%= admin.getCpnyId() %>_" + url + "?empid="+document.all("key").value+"&startmonth="+(document.all("startyear").value+document.all("startmonth").value)+"&endmonth="+(document.all("endyear").value+document.all("endmonth").value)
       +"&patype="+document.form1.patype.value+"&avgthistotalsupport="+document.all("AVG_THIS_TOTAL_SUPPORT").value+"&avgthisactualwage="+document.all("AVG_THIS_ACTUAL_WAGE").value;
	window.open(url ,"Crystal_Reports"," scrollbars=yes, resizable=yes,toolbar=no,titlebar=no,location=no,dependent=yes,location=no,menubar=no") ;
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
			<%@ include file="../inc/common_toolbar_none.jsp"%>
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
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				个人别工资支给明细查看
				</td>
			</tr>  
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>		 
		<form action="" name="form1" method="post">		
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >		
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	      <tr>	        
	        <td align="center" class="info_content_01" nowrap="nowrap">
	        	<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="startyear" monthName="startmonth" yearSelected="${startyear}" monthSelected="${startmonth}" yearPlus="10" />
	        	&nbsp;--&nbsp;<ait:date yearName="endyear" monthName="endmonth" yearSelected="${endyear}" monthSelected="${endmonth}" yearPlus="10" />
	        </td>
	        <td align="center" class="info_content_01" nowrap="nowrap">
	        	类别:&nbsp;<select name="patype">
	        	<option value="">select</option>
	        	<c:forEach items="${paTypeList}" var="test" varStatus="s">
	        	<option value="${test.CODE_ID}" <c:if test="${patype==test.CODE_ID}">selected="selected"</c:if>>${test.CODE_NAME}</option>
	        	</c:forEach>	        	
	        	</select>
	        	
	        </td>	
	        <td align="center" class="info_content_01" nowrap="nowrap" >
	        	区分:&nbsp;<select name="qufen" style="width:60px" >	        	
	        	<option value="C_12067_1330306" <c:if test="${qufen=='C_12067_1330306'}">selected="selected"</c:if>>工厂</option>	        		        	
	        	<option value="C_12067_1330308" <c:if test="${qufen=='C_12067_1330308'}">selected="selected"</c:if>>支社</option>	        		            		        		        	
	        	</select>
	        	
	        </td>		    
	        <td align="center" class="info_content_01" nowrap="nowrap">
				<!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>&nbsp;:&nbsp; 
			    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
			    <span id="name"></span>
			</td>
		    <td align="center" class="info_content_01" nowrap="nowrap">
				<ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
				<ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel();" style="cursor:hand"/>
				<ait:image src="/images/btn/print.gif" align="absmiddle" onclick="javascript:PrintReport();" style="cursor:hand" />
		    </td>
	        </tr>
	      </table>	      
	      </td>
		</tr>
		</table>
		</form>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr>
		<td class="info_title_01" nowrap >部门</td>
		<td class="info_title_01" nowrap >职位</td>
		<td class="info_title_01" nowrap >号奉</td>
		<td class="info_title_01" nowrap >职号</td>
		<td class="info_title_01" nowrap >姓名</td>
		<td class="info_title_01" nowrap >入社日期</td>
		<td class="info_title_01" nowrap >退社日期</td>
		<td class="info_title_01" nowrap >支给平均</td>
		<td class="info_title_01" nowrap >实领平均</td>
		</tr>
		<c:forEach items="${basicList}" var="basic" varStatus="y">
		<td class="info_content_01" nowrap >&nbsp;${basic.DEPTNAME}</td>
		<td class="info_content_01" nowrap >&nbsp;${basic.POST_GRADE_NAME}</td>
		<td class="info_content_01" nowrap >&nbsp;${basic.POST_COEF}</td>
		<td class="info_content_01" nowrap >&nbsp;${basic.EMPID}</td>
		<td class="info_content_01" nowrap >&nbsp;${basic.CHINESENAME}</td>
		<td class="info_content_01" nowrap >&nbsp;${basic.DATE_STARTED}</td>
		<td class="info_content_01" nowrap >&nbsp;${basic.DATE_LEFT}</td>
		<!-- ?????????????????? -->
		<input type="hidden" name="AVG_THIS_TOTAL_SUPPORT" value="${basic.AVG_THIS_TOTAL_SUPPORT}">
		<input type="hidden" name="AVG_THIS_ACTUAL_WAGE" value="${basic.AVG_THIS_ACTUAL_WAGE}">
		<td class="info_content_01" nowrap >&nbsp;${basic.AVG_THIS_TOTAL_SUPPORT}</td>
		<td class="info_content_01" nowrap >&nbsp;${basic.AVG_THIS_ACTUAL_WAGE}</td>
		</c:forEach>
		</table>		
		<form action="" name="form2" method="post"> 
		<input name="ck_all" value="0" type="hidden"/>		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr>
		            <td class="info_title_00" nowrap >工资月</td>
		            <td class="info_title_00" nowrap >类别</td>
		          	<td class="info_title_00" nowrap ><!--工号--><ait:message messageID="display.mutual.emp_id" /></td>
		            <td class="info_title_00" nowrap><!--姓名--><ait:message messageID="display.mutual.name" /></td>
		            
					<td class="info_title_00" nowrap >基本工资</td>
		            <td class="info_title_00" nowrap >职务津贴</td>
				    <td class="info_title_00" nowrap >住宅补助</td>
		            <td class="info_title_00" nowrap >特殊补助</td>
		            <td class="info_title_00" nowrap >其他补偿</td>
		            <td class="info_title_00" nowrap >地区补助</td>
					<td class="info_title_00" nowrap >基本合计</td>		            
		            <td class="info_title_00" nowrap >其他支给</td>
		            <td class="info_title_00" nowrap >餐费</td>
		            <td class="info_title_00" nowrap >支给错误</td>		           
		            <td class="info_title_00" nowrap >迟早减除</td>
		            <td class="info_title_00" nowrap >未勤减除</td>
		            <td class="info_title_00" nowrap >事病减除</td>
		            <td class="info_title_00" nowrap >休假减除</td>
		            <td class="info_title_00" nowrap >休职减除</td>
		            <td class="info_title_00" nowrap >旷工减除</td>
					<td class="info_title_00" nowrap >其他减除</td>
		            <td class="info_title_00" nowrap >住宅减除</td>
		            <td class="info_title_00" nowrap >减除错误</td>
		            <td class="info_title_00" nowrap >放假减除</td>
		            <td class="info_title_00" nowrap >试用扣除</td>
		            <td class="info_title_00" nowrap >支给合计</td>
		            <td class="info_title_00" nowrap >其他计税</td>
		            <td class="info_title_00" nowrap >管理费</td>
		            <td class="info_title_00" nowrap >公司负担</td>
		            <td class="info_title_00" nowrap >个人负担</td>
		            <td class="info_title_00" nowrap >小计</td>		            
		            <td class="info_title_00" nowrap >国企合计</td>
		            <td class="info_title_00" nowrap >所得税</td>		            
		            <td class="info_title_00" nowrap >实领工资</td>
		          </tr>
		          <c:forEach items="${FviewDetailList}" var="test" varStatus="i">
		          <tr>
		            <td class="info_content_00" nowrap >${test.PA_MONTH}</td>
		            <td class="info_content_00" nowrap >${test.BONUS_TYPE}</td>
		            <td class="info_content_00" nowrap >${test.EMPID}</td>
		            <td class="info_content_00" nowrap>${test.CHINESENAME}</td>		            
					<td class="info_content_00" nowrap >${test.BASE_PAY}</td>
		            <td class="info_content_00" nowrap >${test.DUTIES_ALLOWANCE}</td>
				    <td class="info_content_00" nowrap >${test.RESIDENTIAL_GRANTS}</td>
		            <td class="info_content_00" nowrap >${test.SPECIAL_GRANTS}</td>
		            <td class="info_content_00" nowrap >${test.OTHER_COMPENSATION}</td>
		            <td class="info_content_00" nowrap >${test.REGIONAL_GRANTS}</td>
					<td class="info_content_00" nowrap >${test.TOTAL_BASIC}</td>		            
		            <td class="info_content_00" nowrap >${test.TO_THE_OTHER}</td>
		            <td class="info_content_00" nowrap >${test.MEAL_FEE}</td>		            
		            <td class="info_content_00" nowrap >${test.STICKS_TO_THE_WRONG}</td>
		            <td class="info_content_00" nowrap >${test.LATE_EARLY_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.NOT_ATTENDANCE_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.LEAVE_SICK_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.VACATION_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.LEVEL_OFF_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.ABSENTEEISM_MINUS}</td>
					<td class="info_content_00" nowrap >${test.OTHER_LESS}</td>
		            <td class="info_content_00" nowrap >${test.RESIDENTIAL_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.REDUCE_ERRORS}</td>
		            <td class="info_content_00" nowrap >${test.HOLIDAY_MINUS_ALL}</td>
		            <td class="info_content_00" nowrap >${test.TRIAL_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.THIS_TOTAL_SUPPORT}</td>	
		            <td class="info_content_00" nowrap >${test.GIFT_COST}</td>	            
		            <td class="info_content_00" nowrap >${test.HANDLING_CHARGE}</td>
		            <td class="info_content_00" nowrap >${test.PAYMENT_OF_E}</td>
		            <td class="info_content_00" nowrap >${test.PAYMENT_OF_INDIVIDUAL}</td>
		            <td class="info_content_00" nowrap >${test.XIAOJI}</td>
		            <td class="info_content_00" nowrap >${test.GUOQIHEJI}</td>		            
		            <td class="info_content_00" nowrap >${test.THIS_ACTUAL_TAX}</td>		            
		            <td class="info_content_00" nowrap >${test.THIS_ACTUAL_WAGE}</td>
		          </tr>
		          </c:forEach>
		</table>
		</form>
		
		 <!-- Page Navigation Start-->
					<ait:page       
		               link="/paControlServlet"
		               parameters="&operation=paDetailView&content=F_viewDetail&menu_code=${param.menu_code}&key=${key}&startyear=${startyear}&startmonth=${startmonth}&endyear=${endyear}&endmonth=${endmonth}&patype=${patype}" 
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
<iframe id='iemp' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:200;width:370; height:210; z-index:2;visibility: hidden;">   
</div>
</body>

</html>
