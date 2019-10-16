<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="../css/ext-all.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function Search(){
	
	if($('empId').value == "")
		{
			$('personId').value="";
		}
	document.form1.action="/gaControlServlet?operation=retrieveFestivalPresentConfirm&menu_code=${param.menu_code}";
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

function checkAll()
{
    var selected = document.form1.ck_all.value == "0" ? true : false;
    var rowNo = document.getElementsByName("rowNo") ;
  	for (var i = 0; i< rowNo.length ; i++){
		rowNo[i].checked = selected ;
	
	}
    document.form1.ck_all.value = selected ? "1" : "0";
}

function affirm(affirmFlag, index, jndex){
	
	$('applyNo').value = $('applyNo_'+index).value;
	$('AFFIRM_FLAG').value = affirmFlag;
	if($('opinion_'+index) != null){
		$('AFFIRM_OPITION').value = $('opinion_'+index).value;
	}
	$('op').value = "single";
	
	if (confirm("确定对当前记录进行决裁？")) {
		document.form1.action="/gaControlServlet?operation=confirmFestivalPresent&menu_code=${param.menu_code}";
		document.form1.submit();
	}
}


function doApproval(){
	var msg1='<ait:message messageID="alert.ess.common.nodatatopass" module="ess"></ait:message>';
	
	var msg3='<ait:message messageID="alert.ess.common.checkpass" module="ess"></ait:message>';

	var msg5='<ait:message messageID="alert.ess.common.cofirmpass" module="ess"></ait:message>';

	var affirmno = document.getElementsByName("rowNo");
	if (affirmno == null || affirmno.length == null || affirmno.length == 0){
	  	alert(msg1);
		return false;
	}
	var c = 0;
	for (var i=0; i<affirmno.length; i++){
		if(affirmno[i].checked == true){
			c++;
			break ;
		}
	}
	if (c==0){
	  	alert(msg3);
		return false;
	}                                       
    if (confirm(msg5)) {
    	$('AFFIRM_FLAG').value = 1;
    	$('op').value = "batch";
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=confirmFestivalPresent";
		document.form1.submit();
	}
}

function doReject(){
	var msg2='<ait:message messageID="alert.ess.common.nodatatoreject" module="ess"></ait:message>';
	var msg4='<ait:message messageID="alert.ess.common.checkreject" module="ess"></ait:message>';
	var msg6='<ait:message messageID="alert.ess.common.cofirmreject" module="ess"></ait:message>';
	
	var affirmno = document.getElementsByName("rowNo");
	if (affirmno == null || affirmno.length == null || affirmno.length == 0){
	  	alert(msg2);
		return false;
	}
	var c = 0;
	for (var i=0; i<affirmno.length; i++){
		if(affirmno[i].checked == true){
			c++;
			break ;
		}
	}
	if (c==0){
	  	alert(msg4);
		return false;
	} 
	if (confirm(msg6)) {
		$('AFFIRM_FLAG').value = 2;
		$('op').value = "batch";
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=confirmFestivalPresent";
		document.form1.submit();
	}
}

</script>

<form name="form1" action="/gmControlServlet" method="post">
<input type="hidden" name="SEQ_NO" value="" />
<input type="hidden" name="applyNo" value="" />
<input type="hidden" name="AFFIRM_FLAG" value="" />
<input type="hidden" name="AFFIRM_OPITION" value="" />
<input type="hidden" name="ck_all" value="0" />
<input type="hidden" name="op" value="" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		<!-- display toolbar -->
		 <%@ include file="../inc/common_toolbar_affirm.jsp"%>
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
				<td class="title1"><!-- 查询条件 --> <ait:message
					messageID="display.mutual.search_criteria" module="ess"></ait:message>
				</td>
			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td width="8%" class="info_title_01">人员选择</td>
						<td width="10%" class="info_content_00"><!-- 请输入姓名查找 --> <input
							id="empId" name="empId" size="8" value="${empId}"
							onkeyup="SearchContent(this.value,this.id);" 
							onkeydown="javascript:if(window.event.keyCode==13)window.event.returnValue=false;"> 
							<input type="hidden" id="personId" name="personId" size="8"
							value="${personId}" /></td>
						<td  class="info_title_01">法人区分
				          </td>
				         <td  class="info_content_00">
				          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="Search();"/>
				         </td>
						<td width="8%" class="info_title_01">部门</td>
						<td width="18%" class="info_content_00">
							<ait:selDeptByCpnyId name="DeptId" selected="${DeptId}" supervisorType="pa" all="all" cpnyId ="${cpnyId}"/>
						</td>
						<td width="8%" class="info_title_01">状态</td>
						<td width="8%" class="info_content_00"><ait:codeClass
							name="flag" codeClass="ConfirmType" selected="${flag}" all="all"/></td>
						 <td width="6%" class="info_content_00">
		         			<ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:Search();" style="cursor:hand" />
		         		 </td>   
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<!-- display 3 level menu --> <%@ include
			file="../hrm/inc/hrm_view_toolbar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">节日礼品确认</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="2"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="3%" class="info_title_01" nowrap><a href="#" onclick="checkAll();" style="color:red;">全选</a></td>
				<td width="3%" class="info_title_01">职号</td>
				<td width="3%" class="info_title_01">姓名</td>
				<td width="3%" class="info_title_01">课组</td>
				<td width="3%" class="info_title_01">部门</td>
				<td width="3%" class="info_title_01">员工类型</td>
				<td width="3%" class="info_title_01">方案名称</td>
				<td width="10%" class="info_title_01">方案内容</td>
				<td width="3%" class="info_title_01">总价</td>
				<td width="3%" class="info_title_01">申请人</td>
				<td width="3%" class="info_title_01">申请日期</td>
				<!--<td width="3%" class="info_title_01">决裁情况</td>-->
				<td width="3%" class="info_title_01">确认情况</td>
				<td width="3%" class="info_title_01">意见</td>
				<td width="3%" class="info_title_01">操作</td>
			</tr>
			<c:forEach items="${recordApplyList}" var="oneResult" varStatus="i">
				<tr align="center">
					<input type="hidden" name="applyNo_${i.index}" value="${oneResult.SEQ_NO}"/>
					<td class="info_content_01" height="27">&nbsp;
						<c:if test="${oneResult.CONFIRM_FLAG == 0}">
							<input type="checkbox" name="rowNo" value="${i.index}" />
						</c:if>
					</td>
					<td align="center" style="color: #666666;" height="30">${oneResult.EMPID}</td>
					<td align="center" style="color: #666666;">${oneResult.CHINESENAME}</td>
					<td align="center" style="color: #666666;">${oneResult.DEPTNAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.FOURTHDEPTNAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.STATUS_NAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.SCHEME_NAME}&nbsp;</td>
					<td align="center" style="color: #666666;">
						<table>
							<c:forEach items="${recordDetaiList}" var="result">
								<c:if test="${oneResult.SCHEME_NO eq result.SCHEME_NO}">
									<tr>
										<td width="10%">&nbsp;&nbsp;礼品名称:&nbsp;<span style="color: #FF0000">${result.PRESENT_NAME}</span></td>
										<td width="10%">&nbsp;&nbsp;品牌:&nbsp;<span style="color: #FF0000">${result.BRAND}</span></td>
										<td width="10%">&nbsp;&nbsp;规格:&nbsp;<span style="color: #FF0000">${result.SPECIFIC}</span></td>
										<td width="10%">&nbsp;&nbsp;单价:&nbsp;<span style="color: #FF0000">${result.UNIT_PRICE}</span>&nbsp;元</td>
										<td width="10%">&nbsp;&nbsp;数量:&nbsp;<span style="color: #FF0000">${result.QUENTITY}</span>${result.UNIT}</td>
										<td width="10%">&nbsp;&nbsp;价格:&nbsp;<span style="color: #FF0000">${result.TOTAL_PRICE}</span>&nbsp;元</td>
									</tr>
								</c:if>
							</c:forEach>
						 </table>
					</td>
					<c:forEach items="${SchemeTotalPrice}" var="result">
						<c:if test="${oneResult.SCHEME_NO eq result.SCHEME_NO}">
							<td align="center" style="color: #666666;">${result.SCHEME_TOTAL}&nbsp;元</td>
						</c:if>
					</c:forEach>
					<td align="center" style="color: #666666;">${oneResult.APPLY_PERSON}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.APPLY_DATE}&nbsp;</td>
					<!--  
					<td class="info_content_01">
						<table>
						<c:forEach items="${affirmorList}" var="affirm" varStatus="j">
							<c:if test="${oneResult.SEQ_NO eq affirm.APPLY_NO}">
								<tr>
									<td class="info_content_00">${affirm.AFFIRM_LEVEL} ${affirm.AFFIRMOR_NAME}
									<input type="hidden" name="affirmNo_${j.index}" value="${affirm.AFFIRM_NO}"/> 
										<c:if test="${affirm.AFFIRM_FLAG==0}"><font color="#990099">未裁决</font></c:if>
							          	<c:if test="${affirm.AFFIRM_FLAG==1}"><font color="#00CC00">已通过</font></c:if>
							          	<c:if test="${affirm.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
						</table>
					</td>
					-->
					<td align="center" style="color: #666666;">
						<c:if test="${oneResult.CONFIRM_FLAG eq 0}">
							<a style="color:#FF0000">未确认</a>
						</c:if>
						<c:if test="${oneResult.CONFIRM_FLAG eq 1}">
							<a style="color:#33FF00">已通过</a>
						</c:if>
						<c:if test="${oneResult.CONFIRM_FLAG eq 2}">
							<a style="color:#33FF00">已否决</a>
						</c:if>
					</td>
					<td class="info_content_01">
						<c:if test="${oneResult.CONFIRM_FLAG == 0}">
							<textarea name="opinion_${i.index}" style=" height: 25px;width:150px " type="_moz"
							onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
						</c:if>
						<c:if test="${oneResult.CONFIRM_FLAG != 0}">
							<table>
								${oneResult.CONFIRM_OPITION}
							</table>
						</c:if>
					</td>
					<td class="info_content_01">&nbsp;
						<c:if test="${oneResult.CONFIRM_FLAG eq 0}">
							<a href="#" onClick="affirm(1,'${i.index}','${j.index}')"><font color="red">通过</font></a>
							<a href="#" onClick="affirm(2,'${i.index}','${j.index}')"><font color="red">否决</font></a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${recordCount < 3}">
				<c:forEach var="i" begin="1" end="${6-recordCount}" step="1">
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

		<!-- Page Navigation Start--> <ait:page link="/gaControlServlet"
			parameters="&operation=retrieveFestivalPresentConfirm&menu_code=${param.menu_code}&personId=${personId}&empId=${empId}&flag=${flag}&deptId=${deptId}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupSize}"
			useJS="false" /> <!-- Page Navigation End -->

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
<iframe id='iemp'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">
</div>
</form>
</body>
</html>
