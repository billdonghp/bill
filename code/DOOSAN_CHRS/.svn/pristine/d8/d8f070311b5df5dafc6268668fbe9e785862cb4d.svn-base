<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Add() {

	document.form1.action="/gaControlServlet?operation=retrieveDataForInsertSmockRelation&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {

	if(document.form1.seqNo.value=="") {
		
	    alert("请选择修改项目");
		return;
	}
	document.form1.action="/gaControlServlet?operation=retrieveDateForUpdateSmockRelation&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Delete() {
	if(document.form1.ck_all.value=="") {
		
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.form1.action="/gaControlServlet?operation=deleteSmockProvide&menu_code=${param.menu_code}";
		document.form1.submit();
	}
}
function Search() {
	
	document.form1.ck_all.value="";	
	document.form1.action="/gaControlServlet?operation=retrieveSmockProvideList&menu_code=${param.menu_code}";
	document.form1.submit();
}



function exportExcel()
{
    form1.action = "/gaControlServlet?operation=exportSmockProvideReport&menu_code=${param.menu_code}";
    form1.submit();    
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


function getHistoryRecord(personId,smockName)
{
	document.form1.action="/gaControlServlet?operation=retrieveDataForSmockProideHistoryRecord&menu_code=${param.menu_code}&personId="+encodeURIComponent(personId)+"&smockName=" +encodeURIComponent(smockName);
	document.form1.submit();
}

function smockProvideConfirm()
{
	var boxObj = document.getElementsByName("affirmno");
	var flag = false;
	for(var i=0;i<boxObj.length;i++)
	{
		if(boxObj[i].checked)
		{
			flag = true;
			break;
		}
	}
	if(flag == false)
	{
		alert("请先选择确认项!");
		return false;
	}
	for(var i=0;i<boxObj.length;i++)
	{
		if(boxObj[i].checked)
		{	
			if(boxObj[i].value=="")
			{
				alert("请先添加员工工作服关系!");
				return false;
			}
		}
	}
	document.form1.action="/gaControlServlet?operation=insertSmockProvide&menu_code=${param.menu_code}";
	document.form1.submit();
}

function checkAll()
{
    var selected = document.form1.ck_all.value == "0" ? true : false;
    var affirmno = document.getElementsByName("affirmno") ;
  	for (var i = 0; i< affirmno.length ; i++){
		affirmno[i].checked = selected ;
	
	}
    document.form1.ck_all.value = selected ? "1" : "0";
}
</SCRIPT>
</head>
<body>
<form name="form1" method="post"><input
	type="hidden" name="ck_all" value="0"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td>
				  <table width="99%" border="0" cellpadding="0" cellspacing="0">
			        <tr>
			          <td align="left" valign="middle" style="padding:3 0 3 0 " nowrap="nowrap">
			          	<ait:history />
			          </td>
			          <td align="left" valign="middle" style="padding:3 0 3 0 " nowrap="nowrap">
			          <ait:image src="/images/btn/Delete.gif"  border="0" align="right"
			          	onclick="javascript:Delete();" style="cursor:hand" title="删除" enTitle="delete" />
			          </td>    
			        </tr>  
			      </table>
		      </td>
		  	</tr>
		   </table>
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
						<td width="8%" class="info_title_01" nowrap="nowrap">预发放开始日期</td>
						<td width="8%" class="info_content_00"><input id="startDate"
							name="startDate" type="text" size="15" readonly="readonly" value="${startDate}"
							onclick="setday(this)" /></td>
						<td width="8%" class="info_title_01">预发放结束日期</td>
						<td width="8%" class="info_content_00"><input id="endDate"
							name="endDate" type="text" size="15" readonly="readonly" value="${endDate}"
							onclick="setday(this)"></td>
						<td width="8%" class="info_title_01">名称</td>
						<td width="8%" class="info_content_00"><ait:codeClass
							codeClass="smockInfo" name="smockName"
							selected="${smockName}" all="all" /></td>
						<td width="8%" class="info_title_01">频率</td>
						<td width="8%" class="info_content_00">
							<ait:select dataListName="periodList" name="period" selected="${period}" all="all"/>
						</td>
						
					</tr>
					<tr>
						<td width="8%" class="info_title_01">职号\姓名</td>
						<td width="8%" class="info_content_00"><input type="text"
							id="empId" name="empId" size="15" value="${empId}"
							onkeyup="SearchContent(this.value,this.id)" required /> <input
							type="hidden" id="personId" name="personId" size="8"
							value="${personId}" /></td>
						<td width="8%" class="info_title_01">部门</td>
						<td width="8%" class="info_content_00"><ait:selDept
							name="DeptId" selected="${DeptId}" all="all" supervisorType="pa"/></td>
						<td width="8%" class="info_title_01">发放状态</td>
						<td width="8%" class="info_content_00">
							<ait:codeClass codeClass="provideStatus" name="provideStatus" selected="${provideStatus}" all="all"/>
						</td>
						<td width="8%" class="info_content_01"><ait:image
							src="/images/btn/Search.gif" border="0" align="absmiddle"
							onclick="javascript:Search();" style="cursor:hand" /></td>
						<td width="8%" class="info_content_01" colspan="3"><ait:image
							src="/images/btn/Excel_Exp.gif" border="0" align="right"
							onclick="javascript:exportExcel();" style="cursor:hand" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		<!-- display 3 level menu --> <%@ include
			file="../hrm/inc/hrm_view_toolbar.jsp"%> <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">个人工作服记录</td>
				<td width="8%" class="info_content_00"><ait:image
					src="/images/btn/confirm.gif" border="0" align="rigth"
					onclick="javascript:smockProvideConfirm();" style="cursor:hand" /></td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="2"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;">全选</a></td>
				<td width="8%" class="info_title_01">职号</td>
				<td width="8%" class="info_title_01">姓名</td>
				<td width="8%" class="info_title_01">性别</td>
				<td width="8%" class="info_title_01">课组</td>
				<td width="8%" class="info_title_01">部门</td>
				<td width="8%" class="info_title_01">入社时间</td>
				<td width="8%" class="info_title_01">名称</td>
				<td width="8%" class="info_title_01">型号</td>
				<td width="8%" class="info_title_01">频率</td>
				<td width="8%" class="info_title_01">预发放日期</td>
				<td width="8%" class="info_title_01">备注</td>
				<td width="8%" class="info_title_01">历史查看</td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center">
					<td nowrap="nowrap" align="center" class="info_content_01" >
		      		 <input type="checkbox" name="affirmno" value="${oneResult.SEQNO}"/>&nbsp;
		      		 <input type="hidden" name="${oneResult.SEQNO}" value="${oneResult.SMOCKNAME}"/>
			 		 </td>
					<td align="center" style="color: #666666;" height="27">${oneResult.EMPID}</td>
					<td align="center" style="color: #666666;">${oneResult.CHINESENAME}</td>
					<td align="center" style="color: #666666;">${oneResult.SEX}</td>
					<td align="center" style="color: #666666;">${oneResult.DEPTNAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.FOURTHIDEPT}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.DATESTARTED}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.SMOCKNAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.MODELNUMBER}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.PERIOD}&nbsp;</td>
					
						<c:choose>
							<c:when test="${oneResult.CREATE_DATE eq null}">
								<c:choose>
									<c:when test="${oneResult.DATE_LEFT ne null}" >
										<td align="center" style="color: #666666;">已离职</td>
									</c:when>
									<c:otherwise>
										<td align="center" style="color: #FF0000;">未发放</td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<td align="center" style="color: #666666;">${oneResult.CREATE_DATE}&nbsp;</td>
							</c:otherwise>
						</c:choose>
					<td align="center" style="color: #666666;">${oneResult.REMARK}&nbsp;</td>
					<td align="center" style="color: #666666;"><a href="#"
						onclick="getHistoryRecord('${oneResult.PERSONID}','${oneResult.SMOCK_NAME}');"><font color="red">历史查看</font></a>&nbsp;</td>
				</tr>
			</c:forEach>
		</table>

		<!-- Page Navigation Start--> <ait:page link="/gaControlServlet"
			parameters="&operation=retrieveSmockProvideList&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&smockName=${smockName}&personId=${personId}&DeptId=${DeptId}&provideStatus=${provideStatus}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupSize}"
			useJS="false" /> <!-- Page Navigation End -->

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
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">
</div>
</body>
</html>
