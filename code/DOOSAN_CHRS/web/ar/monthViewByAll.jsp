<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<title>考勤查看&gt;全职员月考勤查看</title>
<SCRIPT type="text/javascript">
function Search() {

  document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=monthViewByAll&menu_code=${param.menu_code}";
  document.form1.submit();
}

function ImportExcel() {

  document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=monthlyAttReport&menu_code=${param.menu_code}";
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
		$('personId').value = "";
		
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft-80;    //文本框的定位点宽
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
		$('name').innerHTML=cell.childNodes[1].firstChild.nodeValue;
		$('personId').value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
}

</SCRIPT>
</head>
<body>

<form  name="form1" method="post" action="" > 

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_s_3.jsp"%>
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
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
				<ait:message messageID="display.mutual.search_criteria" /></td>
		</tr>
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	         <tr>
		      <td class="info_title_01" nowrap="nowrap"><!--考勤月-->
				<ait:message messageID="display.att.view.monthly.monthly" module="ar"/></td>
		      <td nowrap="nowrap" class="info_content_00">
		      	  <ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
		      	  ~
		      	  <ait:date yearName="yearEnd" monthName="monthEnd" yearSelected="${yearEnd}" monthSelected="${monthEnd}" yearPlus="10" />
		      </td>
		      <td class="info_title_01" nowrap="nowrap"><!--工号/姓名--><ait:message  messageID="display.mutual.emp_no_name"/></td>
		     <td class="info_content_00" nowrap="nowrap"><input id="key" name="key" type="text" size="10" value="${condition}" onKeyUp="SearchEmp(this.value,this.id)">
		     &nbsp;(<span id="name"></span>)<input id="personId" name="personId" type="hidden" value=""/></td>   
		      <td class="info_title_01" nowrap="nowrap" ><!--部门 -->
				<ait:message messageID="display.mutual.dept" /></td>
		      <td nowrap="nowrap" class="info_content_00"><ait:selDept name="deptID" selected="${deptID}" all="all"  supervisorType="ar"/></td>
		      <td class="info_title_01" nowrap="nowrap">区分</td>
				  <td class="info_content_00" nowrap="nowrap">
				  		<select name="empType">
				  		    <option value="">请选择</option>
				  		    <option value="office" <c:if test="${empType == 'office'}">selected</c:if> >职员</option>
				  			<option value="work" <c:if test="${empType == 'work'}">selected</c:if> >工人</option>
				  		</select>
				  </td>
		    </tr>
		    </table>
	      </td>
		</tr>
		</table>
 
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar_n.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="5">
		  <tr>
		    <td  align="center">
		    	${dataTable}
		    </td>
		  </tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${resultCount < 7}">
				<c:forEach var="i" begin="1" end="${7-resultCount}"
					step="1">
					<tr>
						<td class="info_content_01" height="30"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		   <!-- Page Navigation Start-->
	        <ait:page       
	               link="/arControlServlet"
	               parameters="&operation=ar_pagecontrol&page=monthViewByAll&menu_code=${param.menu_code}&deptID=${deptID}&empType=${empType}&year=${year}&month=${month}&yearEnd=${yearEnd}&monthEnd=${monthEnd}&key=${condition}" 
	               total="${resultCount}"
	               currentpage="${currentPage}"
	               pagesize= "${pageSize}"
	               beginlabel="paging_prv10"
	               endlabel="paging_next10"
	               prevlabel="paging_prv"
	               nextlabel="paging_next"
	               pagegroupsize="${pageGroupsize}"
	               useJS="false"/>      
	            
	            <!-- Page Navigation End -->
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
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">   
	</div>
</form>
</body>
</html>
