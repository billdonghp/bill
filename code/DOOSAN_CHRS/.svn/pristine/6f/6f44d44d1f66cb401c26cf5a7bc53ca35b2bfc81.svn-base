<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:directive.page import="com.ait.pa.PaReport"/>
<jsp:directive.page import="com.ait.sqlmap.util.SimpleMap"/>
<jsp:directive.page import="com.ait.sqlmap.util.ObjectBindUtil"/>
<jsp:directive.page import="com.ait.util.StringUtil"/>
<jsp:directive.page import="java.util.List,com.ait.util.ViewOptionUtil"/>
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
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}

function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
		layer.style.height = 48; 
    }else if(size<210){
		layer.style.height = size;  
    }else{
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText.replace("*","&");
    layer.style.visibility = 'visible';
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
		sMap.setString("empID",((AdminBean)request.getSession().getAttribute("admin")).getUsername());
	sMap.setString("cpnyId",((AdminBean)request.getSession().getAttribute("admin")).getCpnyId());
	request.setAttribute("sMap",sMap);
	if("".equals(StringUtil.checkNull(sMap.getString("paYear")))){
		sMap.setString("paYear",String.valueOf(year));
	}
%>
</script>
<body>
<%
	List list;
	SimpleMap person = new SimpleMap();
	PaReport paReport = new PaReport();
	person = (SimpleMap)paReport.RetrievePersonalSalary(sMap);
	request.setAttribute("paMap",person);
	
	String dataTable = new ViewOptionUtil().makeDataTable(request.getParameter("menu_code"), sMap);
	request.setAttribute("dataTable",dataTable);

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
		<br>
		<form  name="searchMonthPa"  method="POST"  action="">
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
		       		${paMap.POST_GRADE}&nbsp;${paMap.POST_COEF}
		        </td>
		        <td width="12%" class="info_title_01"><span class="tablecontent"><!--工资年度-->
					<ait:message  messageID="display.pay.view.annual.pay_year" module="pay"/></span></td>
		        <td width="12%" class="info_content_01" >
		       		<select name="paYear" ><%
						for (int i=-4;i<=4;i++){
						%><option value="<%=year+i%>" 
							<%if(String.valueOf(year+i).equals(sMap.getString("paYear"))){ out.print("selected");}%>><%=year+i%></option>
						<%}%>
				    </select>
		        </td>
		       
		     </tr>
		     <tr>
		        <td width="12%" height="30" class="info_title_01"><!--部门-->
					<ait:message  messageID="display.mutual.department" /></td>
		        <td width="12%" class="info_content_01">
		       		${paMap.DEPARTMENT}
		       	</td>
		        <td width="10%" height="30" class="info_title_01"><!--职位-->
					<ait:message  messageID="display.mutual.status" /></td>
			    <td width="10%" class="info_content_01">
		       		${paMap.STATUS}
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
		<!-- display 3 level menu -->
		<%@ include file="../pa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"　class="dr_d">
			${dataTable}
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
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:1;"></div>
</body>


