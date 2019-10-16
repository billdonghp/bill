<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@page import="com.ait.sqlmap.util.SimpleMap"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Delete(applyNo){
	
	$('seqNo').value = applyNo;
	
	if (confirm("确定对当前记录进行删除？")) {
		document.form1.action="/gaControlServlet?operation=deleteFestivalPresentApply&menu_code=${param.menu_code}";
		document.form1.submit();
	}
}

function Search() {
	
	document.form1.seqNo.value="";	
	document.form1.action="/gaControlServlet?operation=retrieveFestivalPresentConfrimView&menu_code=${param.menu_code}";
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
</SCRIPT>
</head>
<body>
<form name="form1" method="post"><input type="hidden"
	name="seqNo" value="0" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		
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
							<input type="hidden" id="personId" name="personId" size="8" value="${personId}" /></td>
						<td  class="info_title_01">法人区分
				          </td>
				         <td  class="info_content_00">
				          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="Search();"/>
				         </td>
						<td width="8%" class="info_title_01">部门</td>
						<td width="8%" class="info_content_00"><ait:selDeptByCpnyId
							name="DeptId" selected="${DeptId}" all="all" supervisorType="pa" cpnyId ="${cpnyId}"/></td>
						<td width="8%" class="info_title_01">员工类型</td>
						<td width="8%" class="info_content_00">
							<ait:codeClass codeClass="EmpStatus" name="empStatus" selected="${empStatus}" remove="EmpStatus3" all="all"/>
						</td>
						<td width="8%" class="info_title_01">确认类型</td>
						<td width="8%" class="info_content_00">
							<ait:codeClass codeClass="ConfirmType" name="flag" selected="${flag}" all="all"/>
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
				<td align="left" class="title1" colspan="10">节日礼品情况</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="2"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
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
			<%--<td width="3%" class="info_title_01">决裁情况</td>--%>
				<td width="3%" class="info_title_01">确认情况</td>
				<td width="3%" class="info_title_01">意见</td>
				<td width="3%" class="info_title_01">操作</td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center">
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
					<%--<%boolean flag = false; %>
					<td class="info_content_01">
						<table>
						<c:forEach items="${affirmorList}" var="affirm">
							<c:if test="${oneResult.SEQ_NO eq affirm.APPLY_NO}">
								<tr>
									<td class="info_content_00">${affirm.AFFIRM_LEVEL} ${affirm.AFFIRMOR_NAME} 
										<c:if test="${affirm.AFFIRM_FLAG==0}"><font color="#990099">未裁决</font></c:if>
							          	<c:if test="${affirm.AFFIRM_FLAG==1}"><%flag = true;%><font color="#00CC00">已通过</font></c:if>
							          	<c:if test="${affirm.AFFIRM_FLAG==2}"><%flag = true;%><font color="#00CC00">已否决</font></c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
						</table>
					</td>--%>
					<td align="center" style="color: #666666;">
						<c:if test="${oneResult.CONFIRM_FLAG eq 0}"><a style="color:#FF0000">未确认</a></c:if>
						<c:if test="${oneResult.CONFIRM_FLAG eq 1}"><a style="color:#33FF00">已确认</a></c:if>
						<c:if test="${oneResult.CONFIRM_FLAG eq 2}"><a style="color:#33FF00">已否决</a></c:if>
					</td>
					<td align="center" style="color: #666666;">
						<a href="#" onClick="showMemo('${i.index}')">详情</a>
					</td>
					<td align="center" style="color: #666666;">
						<c:if test="${oneResult.CONFIRM_FLAG eq 0}">
							<c:if test="${oneResult.APPLY_PERSON eq currentName && oneResult.CREATED_BY eq adminID}">
									<a href="#" style="color:#FF0000" onclick="Delete(${oneResult.SEQ_NO});">删除</a>
							</c:if>
						</c:if>
					&nbsp;</td>
				</tr>
				<div id="opition_${i.index}" style="display: none;">
					<!--  
						<c:forEach items="${affirmorList}" var="affirm">
							<c:if test="${oneResult.SEQ_NO eq affirm.APPLY_NO}">
								<p>
									${affirm.AFFIRMOR_NAME} 
									<c:if test="${affirm.AFFIRM_FLAG==0}"><font color="#990099">未裁决</font></c:if>
							        <c:if test="${affirm.AFFIRM_FLAG==1}"><font color="#00CC00">已通过</font>&nbsp;${affirm.AFFIRM_OPITION}</c:if>
							        <c:if test="${affirm.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font>&nbsp;${affirm.AFFIRM_OPITION}</c:if>
							   	</p>
							</c:if>
						</c:forEach>
					-->
						<c:if test="${oneResult.CONFIRMOR_NAME eq null}">
							担当
						</c:if>
						<c:if test="${oneResult.CONFIRMOR_NAME ne null}">
							${oneResult.CONFIRMOR_NAME}
						</c:if>
						<c:if test="${oneResult.CONFIRM_FLAG eq 0}"><a style="color:#FF0000">未确认</a></c:if>
						<c:if test="${oneResult.CONFIRM_FLAG eq 1}"><a style="color:#33FF00">已通过</a>&nbsp;${oneResult.CONFIRM_OPITION}</c:if>
						<c:if test="${oneResult.CONFIRM_FLAG eq 2}"><a style="color:#33FF00">已否决</a>&nbsp;${oneResult.CONFIRM_OPITION}</c:if>
				</div>
			</c:forEach>
		</table>

		<!-- Page Navigation Start--> <ait:page link="/gaControlServlet"
			parameters="&operation=retrieveFestivalPresentConfrimView&menu_code=${param.menu_code}&personId=${personId}&DeptId=${DeptId}&empStatus=${empStatus}&flag=${flag}"
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
