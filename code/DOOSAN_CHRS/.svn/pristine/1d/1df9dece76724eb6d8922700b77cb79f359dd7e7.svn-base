<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@page import="com.ait.sqlmap.util.SimpleMap"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Apply() {
	
	var affirmno = document.getElementsByName("affirmno") ;
	var status =0;
	for(var i=0;i<affirmno.length;i++)
	{
		if(affirmno[i].checked == true)
			status++;
		
	}
	if(status==0)
	{
		alert("请选择申请人!");
		return false;
	}
	for(var i=0;i<affirmno.length;i++)
	{	
		if(affirmno[i].checked == true)
		{	
			if($('schemeName'+i).value=="" || $('schemeName'+i).value=="all")
			{
				alert("请选择礼品方案!");
				return false;
			}
			/*
			if($('affirm'+i).value == 0)
			{
				alert("请先设置决裁者!");
				return false;
			}
			*/
		}
	}
	document.form1.action="/gaControlServlet?operation=insertApplyFestivalPresent&menu_code=${param.menu_code}";
	document.form1.submit();
}

function Search() {
	
	if($('empId').value =="")
	{
		$('personId').value = "";
	}
	document.form1.ck_all.value="";	
	document.form1.action="/gaControlServlet?operation=retrieveFestivalPresentApply&menu_code=${param.menu_code}";
	document.form1.submit();
}


var time=null;
function SearchContent(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchC(condition,id);
					},500);  
}

function SearchC(condition,id){

		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployeeSysAdmin&condition=" + encodeURIComponent(condition);

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
    
	layer.innerHTML=XmlHttpRequest.responseText.replace('*','&');
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('empId').value=cell.childNodes[0].firstChild.nodeValue;
		$('personId').value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
}

function selectAllBox()
{
    var selected = $('ck_all').value == "0" ? true : false;
    var affirmno = document.getElementsByName("affirmno") ;
 	
 	for (var i = 0; i< affirmno.length ; i++){
		affirmno[i].checked = selected ;
	}
    document.form1.ck_all.value = selected ? "1" : "0";
}

function checkAllEvent()
{
	if($('checkAll').checked == true)
	{
		$('checkboxStatus').value = "true";
	}
	else
	{
		$('checkboxStatus').value = "false";
	}
}

function selectAll(context,cut)
{
	var affirmno = document.getElementsByName("affirmno") ;
	if($('checkboxStatus').value == "false")
	{
		if(context == "all" || context == "")
		{
			$('span1'+cut).innerHTML = "";
			$('span2'+cut).innerHTML = "";
			$('schemeName'+cut).value = context;
		}
		else
		{
			updateValues(context,cut);
		}
	}
	else
	{	
		if(affirmno[cut].checked == true)
		{
			for(var j = 0; j< affirmno.length ;j++)
			{
				if(affirmno[j].checked == true)
				{
					if(context == "all" || context == "")
					{
						$('span1'+j).innerHTML = "";
						$('span2'+j).innerHTML = "";
						$('schemeName'+j).value = context;
					}
					else
					{	
						$('schemeName'+j).value = context;
						updateValues(context,j);
					}
				}
			
			}
		}
		else
		{
			if(context == "all" || context == "")
			{
				$('span1'+cut).innerHTML = "";
				$('span2'+cut).innerHTML = "";
				$('schemeName'+cut).value = context;
			}
			else
			{	
				$('schemeName'+cut).value = context;
				updateValues(context,cut);
			}
		}
	}

}

function updateValues(cell,i) 
{
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=getPresentInfoForSchemeApply&condition=" + encodeURIComponent(cell);
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onSuccess : function(XmlHttpRequest){
            					var doc = XmlHttpRequest.responseXML.getElementsByTagName("table");
								$('span1'+i).innerHTML = XmlHttpRequest.responseText;
						
								$('span2'+i).innerHTML = doc[0].getElementsByTagName("div")[0].childNodes[0].nodeValue;
								
            				}}
        );
}

</SCRIPT>
</head>
<body>
<form name="form1" method="post"><input type="hidden"
	name="ck_all" value="0" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<ait:image src="/images/btn/Apply.gif"  border="0" align="RIGHT"
          	onclick="javascript:Apply();" style="cursor:hand" title="申请" enTitle="Apply" />
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <br>
		<table width="100%" height="30" border="0" cellspacing="1"
			cellpadding="0">
			<tr>
				<td class="title1"><!--查询条件--> <ait:message
					messageID="display.mutual.search_criteria" /></td>
			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td width="8%" class="info_title_01">职号\姓名</td>
						<td width="8%" class="info_content_00"><input type="text"
							id="empId" name="empId" size="15" value="${empId}"
							onkeyup="SearchContent(this.value,this.id)"
							onkeydown="if(window.event.keyCode==13)window.event.returnValue=false;">
							<input
							type="hidden" id="personId" name="personId" size="8"
							value="${personId}" /></td>
						<td width="8%" class="info_title_01">部门</td>
						<td width="8%" class="info_content_00"><ait:selDept
							name="DeptId" selected="${DeptId}" all="all" supervisorType="pa"/></td>
						<td width="8%" class="info_title_01">员工类型</td>
						<td width="8%" class="info_content_00">
							<ait:codeClass codeClass="EmpStatus" name="empStatus" selected="${empStatus}" remove="EmpStatus3" all="all"/>
						</td>
						<td width="8%" class="info_title_01">区分</td>
						<td width="8%" class="info_content_00">
							<ait:codeClass codeClass="FestivalPresentDiff" name="division" selected="${division}" all="all"/>
						</td>
						<td width="8%" class="info_content_01"><ait:image
							src="/images/btn/Search.gif" border="0" align="absmiddle"
							onclick="javascript:Search();" style="cursor:hand" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		<!-- display 3 level menu --> <%@ include
			file="../hrm/inc/hrm_view_toolbar.jsp"%> <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">节日礼品申请</td>
				<td width="8%" class="info_content_00">下拉框联动
				<input type="checkbox" name="checkAll" align="middle" onclick="checkAllEvent();"/></td>
				<input type="hidden" name="checkboxStatus" value="false">
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="2"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="2%" class="info_title_01"  nowrap><a href="#" style="color:red;" onclick="selectAllBox();">全选</a></td>
				<td width="3%" class="info_title_01">职号</td>
				<td width="3%" class="info_title_01">姓名</td>
				<td width="3%" class="info_title_01">课组</td>
				<td width="3%" class="info_title_01">部门</td>
				<td width="3%" class="info_title_01">员工类型</td>
				<td width="3%" class="info_title_01">方案名称</td>
				<td width="10%" class="info_title_01">方案内容</td>
				<td width="3%" class="info_title_01">总价</td>
				<%--<td width="3%" class="info_title_01">决裁人</td>--%>
			</tr>
			<%
				int i = 0;
			%>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center">
					<td align="center" style="color: #666666;" height="30">
		      		 <input type="checkbox" name="affirmno" value="${i.index}"/>
		      		 <input type="hidden" name="presentObj${i.index}" value="${oneResult.PERSON_ID}">
			 		</td>
					<td align="center" style="color: #666666;">${oneResult.EMPID}</td>
					<td align="center" style="color: #666666;">${oneResult.CHINESENAME}</td>
					<td align="center" style="color: #666666;">${oneResult.DEPTNAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.FOURTHDEPTNAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.STATUS_CODE}&nbsp;</td>
					<td align="center" style="color: #666666;">
						<ait:select dataListName="schemeNameList" name="schemeName${i.index}" all="all" onChange="selectAll(this.value,${i.index});"/>
					</td>
					<td align="center" style="color: #666666;"><span id="span1${i.index}"></span>&nbsp;</td>
					<td align="center" style="color: #666666;"><span id="span2${i.index}"></span>&nbsp;</td>
					<%--
					<td align="center" style="color: #666666;">
						<%@ page import="java.util.List" %>
						<%
								
								List affirmorList = ((List)request.getAttribute("affirmor"+i));
								request.setAttribute("affirmorList",affirmorList);
								
						%>
						<table>
								<c:if test="${fn:length(affirmorList)!=0}">
									<input type="hidden" name="affirm${i.index}" value="1">
									<c:forEach items="${affirmorList}" var="result">
										<tr>
											<td nowrap="nowrap"><font color="#990099">${result.affirmLevel}</font></td>
											<td nowrap="nowrap"><font color="#990099">${result.affirmorName}</font></td>
										 </tr>
									</c:forEach>
								</c:if>
								<c:if test="${fn:length(affirmorList)==0}">
									<input type="hidden" name="affirm${i.index}" value="0">
									<tr>
										<td nowrap="nowrap"><font color="red">未设置决裁者</font></td>
									</tr>
								</c:if>
						</table>
						<%
							i++;
						%>
					</td>--%>
				</tr>
			</c:forEach>
		</table>

		<!-- Page Navigation Start--> <ait:page link="/gaControlServlet"
			parameters="&operation=retrieveFestivalPresentApply&menu_code=${param.menu_code}&personId=${personId}&DeptId=${DeptId}&empStatus=${empStatus}&division=${division }"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupSize}"
			useJS="false" /> <!-- Page Navigation End -->
		<input type="hidden" name="pageSize" value="${pageSize}">
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(recordList) < 5}">
				<c:forEach var="i" begin="1" end="${5-fn:length(recordList)}"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
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
<iframe id='iemp'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">
</div>
</body>
<ait:xjos></ait:xjos>
</html>
