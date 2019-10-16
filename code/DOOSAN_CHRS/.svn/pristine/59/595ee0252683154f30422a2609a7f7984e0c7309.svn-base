<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ait.commons.dao.ViewOptionDAO,com.ait.sy.bean.*" %>
<%@ page import="com.ait.util.*,java.util.*" %>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../../css/default.css">
		<title>修改报表页面项目</title>
		<SCRIPT language="JavaScript" src="../../js/prototype.js"></SCRIPT>
		<script type="text/javascript">
var add_ri_nos ;
var add_item_ids ;
var add_item_names ;
var add_item_kor_names ; 
var add_item_en_names ;
var add_order_nos ;
function fillData(param){
	for(i=1;i<= param;i++){
		if(add_ri_nos == ''){
			add_ri_nos =  $('ri_no_'+i).value ;
			add_item_ids = $('item_id_'+i).value ;
			add_item_names = $('item_name_'+i).value ;
			add_item_kor_names = ($('item_kor_name_'+i).value==''?'null':$('item_kor_name_'+i).value) ;
			add_item_en_names = ($('item_en_name_'+i).value==''?'null':$('item_en_name_'+i).value) ;
			add_order_nos = ($('order_no_'+i).value==''?'null':$('order_no_'+i).value) ;
		}else{
			add_ri_nos += ',' + $('ri_no_'+i).value ;
			add_item_ids += ',' + $('item_id_'+i).value ;
			add_item_names += ',' + $('item_name_'+i).value ;
			add_item_kor_names += ',' + ($('item_kor_name_'+i).value==''?'null':$('item_kor_name_'+i).value) ;
			add_item_en_names += ',' + ($('item_en_name_'+i).value==''?'null':$('item_en_name_'+i).value) ;
			add_order_nos += ',' + ($('order_no_'+i).value==  '' ? 'null':$('order_no_'+i).value) ;
			
		}
	}
}
function deleteItem(index,ri_no){
	var tempTable = $('colTable');
	if(confirm('是否确定要删除?')){
		deleteReportItem(ri_no) ;
		tempTable.deleteRow(index);
	}
}
function deleteReportItem(ri_no){
	var url = '/ajaxControlServlet';
    var pars = 'operation=deleteReportItem&menu_code=<c:out value='${toolbarInfo.menu_code}'/>&ri_no='+ri_no;
   var myAjax = new Ajax.Request(
           url,
           {method: 'post', parameters: pars, onComplete: showMsg2}            
           );
    window.location.reload();
}
function updateReportItem(){
	var url = '/ajaxControlServlet';
    var pars = 'operation=updateReportItem&menu_code=<c:out value='${toolbarInfo.menu_code}'/>&add_ri_nos='
    +add_ri_nos+'&add_item_names='+add_item_names+'&add_item_kor_names='+add_item_kor_names
    +'&add_item_en_names='+add_item_en_names+'&add_order_nos='+add_order_nos+"&add_item_ids="+add_item_ids;
   var myAjax = new Ajax.Request(
           url,
           {method: 'post', parameters: pars, onComplete: showMsg}            
           );
}
function showMsg2(originalRequest){
	alert(originalRequest.responseText);
}
function showMsg(originalRequest){
	alert(originalRequest.responseText);
	window.close();
}
function updateItem(param){

	fillData(param);
	updateReportItem();
}
		</script>
	</head>
	<body>
		<table id="colTable" width="370" class="dr_d" border="0" cellpadding="0" cellspacing="1">
		<form id='colForm' name='colForm' method="post">
			<%
				int rt_no = NumberUtil.parseInt(request.getParameter("rt_no")) ;
				ViewOptionDAO viewDAO = new ViewOptionDAO();
				ReportTable reportTable= new ReportTable() ;
				reportTable.setRt_no(rt_no);
				List<ReportItem> dataList = viewDAO.retrieveReportItemList(reportTable);
				request.setAttribute("dataList", dataList);
				if (dataList.size() > 0) {
			%>
			<td class="info_title_01" nowrap>序号</td>
			<td height="30" class="info_title_01" nowrap>公式</td>
			<td class="info_title_01" nowrap>显示名称</td>
			<td class="info_title_01" nowrap>显示名称(韩)</td>
			<td class="info_title_01" nowrap>显示名称(英)</td>
			<td class="info_title_01" nowrap>操作</td>
			<c:forEach items="${dataList }" var="oneResult"  varStatus="i">
				<tr>
					<input type="hidden" id="ri_no_${i.count}" name="ri_no_${i.count}" value="${oneResult.ri_no }">
					<td class="info_content_01"><input type="hidden"
						id="order_no_${i.count}"
						name="order_no_${i.count}"
						value="${oneResult.order_no}">${oneResult.order_no}</td>
					<td class="info_content_01" >
						<textarea id="item_id_${i.count}" name="item_id_${i.count}" style=" height: 25px;width:250px "  
						onfocus="this.style.height='40px'" onblur="this.style.height='25px'">${oneResult.ref_item_id}</textarea>
					</td>
					<td class="info_content_01"><input type="text"
						id="item_name_${i.count}"
						name="item_name_${i.count}"
						value="${oneResult.item_name }"></td>
					<td class="info_content_01"><input type="text"
						id="item_kor_name_${i.count}"
						name="item_kor_name_${i.count}"
						value="${oneResult.item_kor_name }"></td>
					<td class="info_content_01"><input type="text"
						id="item_en_name_${i.count}"
						name="item_en_name_${i.count}"
						value="${oneResult.item_en_name }"></td>
					<td class="info_content_01">
						<input type="button" value="删除" onclick="deleteItem(this.parentNode.parentNode.rowIndex,${oneResult.ri_no })">
					</td>
					
				</tr>
				<c:set var="count" value="${i.count}"></c:set>
			</c:forEach>
			<%
				}
			%>
			<tr>
				<td class="info_content_01" nowrap="nowrap" colspan="6">
					<input type="button" value="保存" onclick="updateItem(<c:out value="${count }"/>);">
				&nbsp;
					<input type="button" value="关闭"
						onclick="javascript:window.close();">
				</td>
			</tr>
			</form>
		</table>
	</body>
</html>
