<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../inc/taglibs.jsp"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.sy.service.SysService"%>
<%@ page import="java.util.*"%>
<jsp:directive.page import="com.ait.sqlmap.util.SimpleMap" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="../../css/default.css">
<title>添加分类表格</title>
<SCRIPT language="JavaScript" src="../../js/prototype.js"></SCRIPT>
<script type="text/javascript">
var add_item_nos ='' ;
var add_order_nos = '';
var add_item_ids = '';
var add_ref_tn = '';
var add_item_names = '';
var add_item_kor_names = '';
var add_item_en_names = '';
function fillData(){
	var flag = false;
	for(i=0;i< $('saveForm').item_no.length;i++){
		if ($('saveForm').item_no[i].checked){
			flag = true;
			if(add_item_nos == ''){
				var item_no =  $('saveForm').item_no[i].value ;
				add_item_nos = item_no ;
				add_ref_tn = $('chooseType').value ;
				add_item_ids = $('item_id'+item_no).value ;
				add_order_nos = $('order_no'+item_no).value ;
				add_item_names = $('item_name'+item_no).value ;
				add_item_kor_names = ($('item_kor_name'+item_no).value==''?'null':$('item_kor_name'+item_no).value) ;
				add_item_en_names = ($('item_en_name'+item_no).value==''?'null':$('item_en_name'+item_no).value) ;
			}else{
				var item_no = $('saveForm').item_no[i].value ;
				add_item_nos  += ',' + item_no ;
				add_ref_tn  += ',' + $('chooseType').value ;
				add_item_ids  += ',' + $('item_id'+item_no).value ;
				add_order_nos += ',' + $('order_no'+item_no).value ;
				add_item_names  += ',' + $('item_name'+item_no).value ;
				add_item_kor_names  += ',' + ($('item_kor_name'+item_no).value==''?'null':$('item_kor_name'+item_no).value) ;
				add_item_en_names  += ',' + ($('item_en_name'+item_no).value==''?'null':$('item_en_name'+item_no).value) ;
			}
		}
	}
	if(flag == false){
		alert("请选择子项目");
		return;
	}
}
			
function saveReportItem(rt_no){
	fillData();
	var url = '/ajaxControlServlet';
    var pars = 'operation=insertReportItem&menu_code=<c:out value='${toolbarInfo.menu_code}'/>&rt_no='+rt_no
    +"&add_item_nos="+add_item_nos+"&add_order_nos="+add_order_nos
    +"&add_item_ids="+add_item_ids+"&add_ref_tn="+add_ref_tn+"&add_item_names="+add_item_names
    +"&add_item_kor_names="+add_item_kor_names+"&add_item_en_names="+add_item_en_names;
   var myAjax = new Ajax.Request(
           url,
           {method: 'post', parameters: pars, onComplete: showMsg}            
           );
}
function showMsg(originalRequest){
	alert(originalRequest.responseText);
	parent.document.getElementById('coltable').style.visibility='hidden';
}
		</script>
</head>
<body
	Style="margin-left: 0px; margin-right: 0px; margin-bottom: 0px; margin-top: 0px;">
<table id="addColTable" width="370" class="dr_d" border="0"
	cellpadding="0" cellspacing="1">
	<form id="saveForm" name='saveForm' method="post" >
			<%
				String table_name = "";
				List<ReportItemReference> dataList = null;
				SimpleMap param = null ;
				if (request.getParameter("type").equals("1"))
					table_name = "AR_HISTORY";
				else
					table_name = "PA_HISTORY";
				String rt_no = request.getParameter("rt_no") ;
				param = new SimpleMap() ;
				param.set("table_name",table_name) ;
				param.set("rt_no",rt_no) ;
				dataList = SysService.getInstance().retrieveReportItemListNotInRtNo(param);
				request.setAttribute("dataList", dataList);
				if (dataList.size() > 0) {
			%>
			<td height="30" class="info_title_01" nowrap></td>
			<td class="info_title_01" nowrap>项目名称</td>
			<td class="info_title_01" nowrap>显示名称</td>
			<tr>
				<td class="info_content_01" nowrap="nowrap" colspan="3"><input
					type="button" value="保存" onclick="saveReportItem(${param.rt_no });">
					<input
					type="button" value="关闭"
					onclick="javascript:parent.document.getElementById('coltable').style.visibility='hidden';">
				</td>
			</tr>
			<!-- 
			<td class="info_title_01" nowrap>显示名称(韩)</td>
			<td class="info_title_01" nowrap>显示名称(英)</td>
			  -->
			<c:forEach items="${dataList }" var="oneResult">
				<tr>
					<td class="info_content_01"><input type="checkbox"
						id="item_no" name="item_no" value="${oneResult.item_no }">
					<input type="hidden" id="item_id${oneResult.item_no }"
						name="item_id${oneResult.item_no }" value="${oneResult.item_id}">
					<input type="hidden" id="order_no${oneResult.item_no }"
						name="order_no${oneResult.item_no }" value="${oneResult.order_no}">
					</td>
					<c:choose>
						<c:when test="${param.type == 1}">
							<input type="hidden" id="chooseType" name="chooseType" value="AR_HISTORY">
						</c:when>
						<c:otherwise>
							<input type="hidden" id="chooseType" name="chooseType" value="PA_HISTORY">
						</c:otherwise>
					</c:choose>
					<td class="info_content_01">${oneResult.item_name }</td>
					<td class="info_content_01"><input type="text"
						id="item_name${oneResult.item_no }"
						name="item_name${oneResult.item_no }"
						value="${oneResult.item_name }"></td>
					
					<input type="hidden" id="item_kor_name${oneResult.item_no }" name="item_kor_name${oneResult.item_no }">
					<input type="hidden" id="item_en_name${oneResult.item_no }" name="item_en_name${oneResult.item_no }">
					 
				</tr>
			</c:forEach>
			<%
				}
			%>
	</form>
</table>
</body>
</html>
