<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<SCRIPT language="JavaScript" src="/pa/js/patable.js"></SCRIPT>
<script type="text/javascript">

var time=null;
function SearchParamConfigure(condition,id){		
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
		
		$('PARAM_ID').value = "" ;
		
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=paSearchParamConfigure&key=" + encodeURIComponent(condition);
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
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[3].firstChild.nodeValue;
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
		$('PARAM_ID').value=cell.childNodes[0].firstChild.nodeValue;
		$('paramName').innerHTML= cell.childNodes[0].firstChild.nodeValue + "  /  " + cell.childNodes[1].firstChild.nodeValue ;
		layerClose();
}


</script>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../pa/inc/common_toolbar_all.jsp"%>
			
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
		<form name="form1" method="post">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!--查询条件-->
					<ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td colspan="9">
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       		 <tr>
					 <td width="10%" class="info_title_01"><!-- 搜索条件 -->搜索条件</td>
			         <td width="20%" class="info_content_00">
			          <input id="key" name="key" type="text" size="20" value="${key}" onKeyUp="SearchParamConfigure(this.value,this.id)">
			          <input type="hidden" id="PARAM_ID" name="PARAM_ID" value="${PARAM_ID}" />
			         </td> 
			         <td width="10%" class="info_title_01"><!-- 项目ID -->项目ID/项目名称</td>
			         <td width="15%" class="info_content_00" id="paramName">
			          &nbsp;
			         </td>
			         <td width="10%" class="info_title_01"><!--考勤月-->
					<ait:message  messageID="display.att.maintenance.lock.timing" module="ar"/></td>
			         <td width="15%" class="info_content_00" ><ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
			         </td>
					 <td class="info_content_00">
					 <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand"/></td>
			    </tr>
			</table>
	      </td>
		</tr>
		</table>
		</form>
		<!-- display 3 level menu -->
		<%@ include file="../pa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--输入项目参数列表-->
					输入项目参数列表</td>
			</tr>
		 </table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center"> 
			<td width="8%" nowrap class="info_title_01"><!--编号-->
					<ait:message  messageID="display.mutual.no"/></td>
			<td nowrap class="info_title_01">项目ID</td>
			<td nowrap class="info_title_01">所属法人</td>
			<td nowrap class="info_title_01">员工区分名称</td>
			<td nowrap class="info_title_01">考勤月</td>
			<td nowrap class="info_title_01">区分项目1</td>
			<td nowrap class="info_title_01">区分项目2</td>
			<td nowrap class="info_title_01">数据类型</td>
			<td nowrap class="info_title_01">参数类型</td>
			<td nowrap class="info_title_01">默认值</td>
		  </tr> 
		<c:forEach items="${paParamItimConfigureList}" var="vlist">
		  <tr align="center" onclick="HighLightTR('#E7F0EF','black','${vlist.CONFIGURE_NO}')" > 
			<td height="22" align="center" style="color: #666666;" >${vlist.CONFIGURE_NO}&nbsp;</td>
			<td align="center" style="color: #666666;" nowrap="nowrap">${vlist.PARAM_NAME}&nbsp;</td>
			<td align="center" style="color: #666666;" nowrap="nowrap">${vlist.CPNY_NAME}&nbsp;</td>
			<td align="center" style="color: #666666;" nowrap="nowrap">${vlist.STAT_TYPE}&nbsp;</td>
			<td align="center" style="color: #666666;" nowrap="nowrap">${vlist.PA_MONTH_STR}&nbsp;</td>
			<td align="center" style="color: #666666;" nowrap="nowrap">${vlist.FIELD_NAME}&nbsp;</td>
			<td align="center" style="color: #666666;" nowrap="nowrap">${vlist.FIELD_NAME2}&nbsp;</td>
			<td align="center" style="color: #666666;" nowrap="nowrap">${vlist.DATA_TYPE_NAME}&nbsp;</td>
			<td align="center" style="color: #666666;" nowrap="nowrap">${vlist.GENERATE_TYPE}&nbsp;</td>
			<td align="center" style="color: #666666;" nowrap="nowrap">${vlist.DEFAULT_VAL}&nbsp;</td>
		  </tr>
		</c:forEach>
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(paParamItimConfigureList) < 7}">
				<c:forEach var="i" begin="1" end="${7-fn:length(paParamItimConfigureList)}"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
		<!-- Page Navigation Start-->
		        <ait:page
		               link="/paControlServlet"
		               parameters="&operation=paParamItemConfigureList&menu_code=${param.menu_code}&PARAM_ID=${PARAM_ID}&year=${year}&month=${month}" 
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
<script language="JavaScript" type="text/JavaScript">
var ID;
ID='';
function Add(){
	
	if ($("PARAM_ID").value != ""){
		document.form1.action="/paControlServlet?operation=paParamItemConfigureList&method=addView&menu_code=${param.menu_code}";
		document.form1.submit();
	}
	else{
	//"请选择参数"
		alert('<ait:message  messageID="alert.pay.select_parameter" module="pay"/>');
		$("key").focus() ;
	}
	
	
}
function Update(){
	if (ID!=''){
		location.href="/paControlServlet?operation=paParamItemConfigureList&method=updateView&menu_code=${param.menu_code}&CONFIGURE_NO="+ID;
	}
	else{
	//"请选择参数"
		alert('<ait:message  messageID="alert.pay.select_parameter" module="pay"/>');
	}
}
function Delete(){
	
	if (ID!=""){
	//"删除后,相关信息也将被清空!\n\n      确定要删除吗?"
		if (confirm('<ait:message  messageID="alert.pay.associated_deleting" module="pay"/>') )	{
			document.form1.action = "/paControlServlet?operation=paParamItemConfigureList&method=delete&menu_code=${param.menu_code}&CONFIGURE_NO=" + ID;
			//alert(document.form1.action);
			document.form1.submit();
		}
	}
	else{
	//"请选择参数"
		alert('<ait:message  messageID="alert.pay.select_parameter" module="pay"/>');
	}
	
}
function Search(){
	document.form1.action="/paControlServlet?operation=paParamItemConfigureList&menu_code=${param.menu_code}";
	document.form1.submit();
}
</script>
</body>
	<iframe id='iemp' style="position:absolute; top:100; width:500; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:500; height:210; z-index:2; visibility: hidden;">   
	</div>
</html>