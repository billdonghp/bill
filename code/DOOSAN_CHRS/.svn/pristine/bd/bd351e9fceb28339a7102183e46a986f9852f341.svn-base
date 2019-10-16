<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:directive.page import="com.ait.sqlmap.util.SimpleMap"/>
<jsp:directive.page import="com.ait.sqlmap.util.ObjectBindUtil"/>
<jsp:directive.page import="com.ait.util.StringUtil,com.ait.util.ViewOptionUtil,com.ait.util.DateUtil"/>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
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
     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
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
		$('person_id').value=cell.childNodes[2].firstChild.nodeValue;
		$('name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
		layerClose();
}
function Search(){
	document.form1.action="/paControlServlet?operation=HousingFundRatioCmd&content=view&menu_code=${param.menu_code} ";
	document.form1.submit();
}


function ImportExcel() {
	
	document.form1.action="/paControlServlet?operation=HousingFundRatioCmd&content=housingViewExcel&menu_code=${param.menu_code} ";
	document.form1.submit();

}


function ChangeTextColor(a_obj,a_color){
        for (i=0;i<a_obj.cells.length;i++){
        a_obj.cells(i).style.color=a_color;
    }
}

function band(backColor,textColor,person_id,month,falg,seqno)
	{
	var t;
	if(typeof(preEl)!='undefined')
	{
	preEl.bgColor=orgBColor;

	try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
	}
	var el = event.srcElement;

	orgBColor = el.bgColor;
	orgTColor = el.style.color;
	el.bgColor=backColor;
	preEl = el;
		 document.form1.falg.value=falg;
		 document.form1.person_id1.value=person_id;
		 document.form1.month.value=month;
		 document.form1.seqno.value=seqno;
	}

function Add(){
	alert('请选择某个员工的,其他存入或者支出,进行修改!!!');
}

function Update(){
if(document.form1.person_id1.value == null || document.form1.person_id1.value == ''){
alert('请选择要修改的内容');
return;
}
document.form1.action="/paControlServlet?operation=HousingFundRatioCmd&content=edit&menu_code=${param.menu_code}&person_id1="+document.form1.person_id1.value+"&month="+document.form1.month.value+"&falg="+document.form1.falg.value+"&seqno="+document.form1.seqno.value;

document.form1.submit();
}

function Delete(){
if(document.form1.seqno.value == null || document.form1.seqno.value == ''){
alert('请选择要删除的内容');
return;
}
if(confirm('确定删除吗？')){
document.form1.action="/paControlServlet?operation=HousingFundRatioCmd&content=del&menu_code=${param.menu_code}&person_id1="+document.form1.person_id1.value+"&month="+document.form1.month.value+"&falg="+document.form1.falg.value+"&seqno="+document.form1.seqno.value;

document.form1.submit();
}
}
</script>
<body>
<%
SimpleMap sMap = new SimpleMap();
sMap = ObjectBindUtil.getData(request);
request.setAttribute("sMap",sMap);
String PAMonth = "";

if(!StringUtil.checkNull(sMap.getString("YEAR")).equals("") && !StringUtil.checkNull(sMap.getString("MONTH")).equals(""))
	PAMonth=sMap.getString("YEAR")+sMap.getString("MONTH");
else
	PAMonth=DateUtil.getToday("yyyy")+DateUtil.getToday("MM");
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_all.jsp"%>
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
		<form action="" name="form1" method="post">
		<input name="content" type="hidden" value="view">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >		
	    <tr>
	      <td colspan="9">
	
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	      <tr>
	      <td class="info_title_01" width="10%">
	      		查询月
	      	</td>
		    <td align="center" class="info_content_00" nowrap="nowrap">
			    <ait:date yearName="YEAR1" monthName="MONTH1" yearSelected="${YEAR1}" monthSelected="${MONTH1}" yearPlus="10" />
			    ~&nbsp; <ait:date yearName="YEAR2" monthName="MONTH2" yearSelected="${YEAR2}" monthSelected="${MONTH2}" yearPlus="10" />
			</td>
	      	<td class="info_title_01" width="10%">
	      		<!-- 部门 --><ait:message messageID="display.mutual.dept" />
	      	</td>
		    <td align="center" class="info_content_00" nowrap="nowrap">
			    <ait:selDept name="deptID" selected="${deptID}" all="all" supervisorType="pa"/>
			</td>
			<td class="info_title_01" width="10%">
				<!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>
	      	</td>
	        <td align="center" class="info_content_00" nowrap="nowrap">
				<input id="person_id" name="person_id" type="hidden" value="${person_id }"> 
			    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
			    <span id="name"></span>
			</td>
		    <td align="center" class="info_content_01" nowrap="nowrap">
				<ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
				<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="javascript:ImportExcel();"/>
		    </td>
	        </tr>
	      </table>	
	      </td>
		</tr>
		</table>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				住房公积金
				</td>
			</tr>  
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>		 
		<input type="hidden" name="person_id1" id="person_id1" value=""> 
		<input type="hidden" name="month" id="month" value=""> 
		<input type="hidden" name="falg" id="falg" value=""> 
		<input type="hidden" name="seqno" id="seqno" value=""> 
			
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<tr align="center">
		<td nowrap="nowrap" class="info_title_01" rowspan="2">职号</td>
		<td nowrap="nowrap" class="info_title_01" rowspan="2">姓名</td>
		<td nowrap="nowrap" class="info_title_01" rowspan="2">年月</td>
		<td nowrap="nowrap" class="info_title_01" rowspan="2">基数</td>
		<td nowrap="nowrap" class="info_title_01" colspan="3">工资存入</td>
		<td nowrap="nowrap" class="info_title_01" rowspan="2">其他存入</td>
		<td nowrap="nowrap" class="info_title_01" rowspan="2">支出</td>
		<td nowrap="nowrap" class="info_title_01" rowspan="2">余额</td>
		</tr>
		<tr align="center">
		<td nowrap="nowrap" class="info_title_01">个人</td>
		<td nowrap="nowrap" class="info_title_01">公司</td>
		<td nowrap="nowrap" class="info_title_01">合计</td>
		</tr>
		
		<c:forEach items="${listhouse}" var="list">
		<tr align="center">
		<td nowrap="nowrap" align="center" class="info_content_01">
		${list.EMPID }</td>
		<td nowrap="nowrap" align="center" class="info_content_01">${list.CHINESENAME }&nbsp;</td>
		<td nowrap="nowrap" align="center" class="info_content_01">${list.PA_MONTH }&nbsp;</td>
		<td nowrap="nowrap" align="center" class="info_content_01">${list.HPFS_BASE }&nbsp;</td>
		<td nowrap="nowrap" align="center" class="info_content_01">${list.GEREN }&nbsp;</td>
		<td nowrap="nowrap" align="center" class="info_content_01">${list.GONGSI }&nbsp;</td>
		<td nowrap="nowrap" align="center" class="info_content_01">${list.HEJI }&nbsp;</td>
		<td align="center" >
		<table width="100%">
		<tr onClick="band('#E7F0EF','black','${list.PERSON_ID }','${list.PA_MONTH }','1','${list.SEQ_NO }')"><td height="30"  align="center">
		<c:if test="${list.OCCUR_TYPE eq '1' }">${list.OCCUR_AMOUNT }</c:if>
		<c:if test="${list.OCCUR_TYPE ne '1' }">0</c:if>
		</td></tr>
		</table>
		</td>
		<td align="center">
		<table width="100%">
		<tr onClick="band('#E7F0EF','black','${list.PERSON_ID }','${list.PA_MONTH }','2','${list.SEQ_NO }')"><td height="30" align="center">
		<c:if test="${list.OCCUR_TYPE eq '2' }">${list.OCCUR_AMOUNT }</c:if>
		<c:if test="${list.OCCUR_TYPE ne '2' }">0</c:if>
		</td></tr>
		</table>
		</td>
		<td nowrap="nowrap" align="center" class="info_content_01">${list.ALLPRICE } &nbsp; </td>
		</tr>	
		</c:forEach>	
		</table>
		</form>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(listhouse) < 6}">
				<c:forEach var="i" begin="1" end="${6-fn:length(listhouse)}"
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
		<input type="hidden" name="currentPage" value="${resultCount}">
		 <!-- Page Navigation Start-->
					<ait:page       
		               link="/paControlServlet"
		               parameters="&operation=HousingFundRatioCmd&content=view&menu_code=${param.menu_code}&person_id=${person_id}&key=${key}&deptID=${deptID }&YEAR1=${YEAR1}&MONTH1=${MONTH1}&YEAR2=${YEAR2}&MONTH2=${MONTH2 }" 
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
<ait:xjos />
</html>