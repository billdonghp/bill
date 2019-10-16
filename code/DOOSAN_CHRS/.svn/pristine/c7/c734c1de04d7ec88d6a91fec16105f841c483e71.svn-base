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

	document.ApplyForm.action="/gaControlServlet?operation=retrievePresentApplyInfo&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
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

function showMemo(val) {
    
    var memo = $('opition_'+val).innerHTML;
	var html = "<div style='background-color:#FFFFFF;height: 100%;text-align: left;'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 450
				  , height: 200
				 , shadow: true
				 , closable: true
				  , layout : 'fit'
				 , html : html
				 ,resizable : true
				 ,autoScroll:true
				 ,plain : true	
				 ,title : '决裁意见'
			});
		editDlg.setPosition(400,200);
		editDlg.show();	
} 

function Delete(applyNo){
	
	$('SEQ_NO').value = applyNo;
	
	if (confirm("确定对当前记录进行删除？")) {
		document.ApplyForm.action="/gaControlServlet?operation=deletePresentApply&menu_code=${param.menu_code}";
		document.ApplyForm.submit();
	}
}
function exportExcel(){
	 document.ApplyForm.action="/gaControlServlet?operation=PresentApplyExcel&menu_code=${param.menu_code}";		            

	 document.ApplyForm.submit(); 
}
</script>

<form name="ApplyForm" action="/gmControlServlet" method="post">
<input type="hidden" name="SEQ_NO" value="" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		<!-- display toolbar -->
		 <%@ include file="../hrm/inc/hrtoolbar_search.jsp"%>
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
						<td width="8%" class="info_title_01">开始日期</td>
						<td width="10%" class="info_content_00"><input type="text"
							style="width:80px" name="startDate" value="${startDate}"
							onClick="setday(this);" readonly="readonly"></td>
						<td width="8%" class="info_title_01">结束日期</td>
						<td width="10%" class="info_content_00"><input type="text"
							style="width:80px" name="endDate" value="${endDate}"
							onClick="setday(this);" readonly="readonly"></td>							
						<td width="8%" class="info_title_01">状态</td>
						<td width="10%" class="info_content_00"><ait:codeClass
							name="flag" codeClass="ConfirmType" selected="${flag}"
							all="all" /></td>	
							<td  class="info_content_00">
		        		 <ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:exportExcel();" style="cursor:hand" />
		         </td>
					</tr>
					<tr>							        		        
				         <td  class="info_title_01">法人区分</td>
				         <td  class="info_content_00">
				          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="Search();"/>
				         </td> 
						<td width="8%" class="info_title_01">部门</td>
						<td width="18%" class="info_content_00">
							<ait:selDeptByCpnyId name="deptId" selected="${deptId}" all="all" cpnyId ="${cpnyId}" supervisorType="pa"/>
						</td>
						<td width="8%" class="info_title_01">人员选择</td>
						<td width="12%" colspan="2" class="info_content_00"><!-- 请输入姓名查找 --> <input
							id="empId" name="empId" size="8" value="${empId}"
							onkeyup="SearchContent(this.value,this.id);" /> <input
							type="hidden" id="personId" name="personId" size="8"
							value="${personId}" /></td>
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
				<td align="left" class="title1" colspan="10">礼品情况</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="8%" class="info_title_01" nowrap>职号</td>
				<td width="8%" class="info_title_01" nowrap>姓名</td>
				<td width="8%" class="info_title_01" nowrap>部门</td>
				<td width="10%" class="info_title_01" nowrap>申请内容</td>
				<!--  <td width="10%" class="info_title_01" nowrap>对口单位/个人</td>-->
				<td width="12%" class="info_title_01" nowrap>赠送原因</td>
				<td width="12%" class="info_title_01" nowrap>备注</td>
				<td width="8%" class="info_title_01" nowrap>使用日期</td>
				<td width="8%" class="info_title_01" nowrap>申请日期</td>
				<td width="15%" class="info_title_01" nowrap>决裁情况</td>
				<td width="12%" class="info_title_01" nowrap>确认情况</td>
				<td width="8%" class="info_title_01" nowrap>意见</td>
				<td width="8%" class="info_title_01" nowrap>操作</td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center">
					<td class="info_content_01" height="27">
					&nbsp;${oneResult.EMPID}</td>
					<td class="info_content_01">
					&nbsp;${oneResult.CHINESENAME}</td>
					<td class="info_content_01">
					&nbsp;${oneResult.DEPT_NAME}</td>
					<td class="info_content_01">
						<table>
						<c:forEach items="${oneResult.detailList}" var="detail" varStatus="j">
								<tr>
								    <td class="info_content_07">${j.index + 1} 对口单位/个人：${detail.PRESENT_OBJECT}</td>
									<td class="info_content_07"> 名称：${detail.PRESENT_NAME}</td>
									<td class="info_content_07">数量：${detail.QUENTITY}${detail.UNIT_NAME}</td>
									<td class="info_content_07">单价：${detail.UNIT_PRICE}&nbsp;元</td>
									<td class="info_content_07">价格：${detail.AMOUNT}&nbsp;元</td>
								</tr>
						</c:forEach>
						</table>
					</td>
					<!-- <td class="info_content_01">
					&nbsp;${oneResult.PRESENT_OBJECT}</td> -->
					<td class="info_content_01">
					&nbsp;${oneResult.REASON}</td>
					<td class="info_content_01">
					&nbsp;${oneResult.REMARK}</td>
					<td class="info_content_01">
					&nbsp;${oneResult.USE_DATE}</td>
					<td class="info_content_01">
					&nbsp;${oneResult.CREATE_DATE}</td>
					<td class="info_content_01">
						<table>
						<c:forEach items="${oneResult.affirmList}" var="affirm">
								<tr>
									<td class="info_content_00">${affirm.AFFIRM_LEVEL} ${affirm.AFFIRMOR_NAME} 
										<c:if test="${affirm.AFFIRM_FLAG==0}"><font color="#990099">${affirm.AFFIRM_FLAG_NAME}</font></c:if>
							          	<c:if test="${affirm.AFFIRM_FLAG==1}"><font color="#00CC00">${affirm.AFFIRM_FLAG_NAME}</font></c:if>
							          	<c:if test="${affirm.AFFIRM_FLAG==2}"><font color="#00CC00">${affirm.AFFIRM_FLAG_NAME}</font></c:if>
									</td>
								</tr>
						</c:forEach>
						</table>
					</td>
					<td class="info_content_01">
						<c:if test="${oneResult.CONFIRM_FLAG==0}"><font color="#990099">${oneResult.CONFIRM_FLAG_NAME}</font></c:if>
			          	<c:if test="${oneResult.CONFIRM_FLAG==1}"><font color="#00CC00">${oneResult.CONFIRM_FLAG_NAME}</font></c:if>
			          	<c:if test="${oneResult.CONFIRM_FLAG==2}"><font color="#00CC00">${oneResult.CONFIRM_FLAG_NAME}</font></c:if>
					</td>
					<td class="info_content_01">
						<a href="#" onClick="showMemo('${i.index}')">详情</a>
					</td>
					<td class="info_content_01">
					&nbsp;
						<c:if test="${oneResult.DELETE_OP_FLAG == 0 && oneResult.CREATED_BY eq adminID}">
							<a href="#" onClick="Delete('${oneResult.SEQ_NO}')"><font color="red">删除</font></a>
						</c:if>
					</td>
					<div id="opition_${i.index}" style="display: none;">

							<c:forEach items="${oneResult.affirmList}" var="affirm">
									   <p>${affirm.AFFIRMOR_NAME} 										
										<c:if test="${affirm.AFFIRM_FLAG==0}"><font color="#990099">${affirm.AFFIRM_FLAG_NAME}</font> ${affirm.AFFIRM_OPITION}</c:if>
							          	<c:if test="${affirm.AFFIRM_FLAG==1}"><font color="#00CC00">${affirm.AFFIRM_FLAG_NAME}</font> ${affirm.AFFIRM_OPITION}</c:if>
							          	<c:if test="${affirm.AFFIRM_FLAG==2}"><font color="#00CC00">${affirm.AFFIRM_FLAG_NAME}</font> ${affirm.AFFIRM_OPITION}</c:if>
							          	</p>
									
							</c:forEach>
							<p>
							<c:if test="${oneResult.CONFIRMOR == null}">&nbsp;&nbsp;&nbsp;担当</c:if>
							<c:if test="${oneResult.CONFIRMOR != null}">${oneResult.CONFIRMOR}</c:if>
							<c:if test="${oneResult.CONFIRM_FLAG==0}"><font color="#990099">${oneResult.CONFIRM_FLAG_NAME}</font>${oneResult.CONFIRM_OPITION}</c:if>
				          	<c:if test="${oneResult.CONFIRM_FLAG==1}"><font color="#00CC00">${oneResult.CONFIRM_FLAG_NAME}</font>${oneResult.CONFIRM_OPITION}</c:if>
				          	<c:if test="${oneResult.CONFIRM_FLAG==2}"><font color="#00CC00">${oneResult.CONFIRM_FLAG_NAME}</font>${oneResult.CONFIRM_OPITION}</c:if>
				          	</p>
						
					</div>
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
			parameters="&operation=retrievePresentApplyInfo&menu_code=${param.menu_code}&personId=${personId}&empId=${empId}&startDate=${startDate}&endDate=${endDate}&flag=${flag}&deptId=${deptId}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupsize}"
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
