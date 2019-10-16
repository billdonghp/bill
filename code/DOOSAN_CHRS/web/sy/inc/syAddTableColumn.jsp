<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../inc/taglibs.jsp"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.sy.service.SysService"%>
<%@ page import="java.util.*"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../../css/default.css">
<title>添加分类表格</title>
<SCRIPT language="JavaScript" src="../../js/prototype.js"></SCRIPT>
<script type="text/javascript">
			function fillData(row_temp){
				var flag = false;
				var temp_for_add_item = parent.document.getElementById('temp_for_add_item').value ;
				var add_item_nos = parent.document.getElementById('add_item_nos').value ;
				var add_order_nos = parent.document.getElementById('add_order_nos').value ;
				var add_item_ids = parent.document.getElementById('add_item_ids').value ;
				var add_ref_tn = parent.document.getElementById('add_ref_tn').value ;
				var add_item_names = parent.document.getElementById('add_item_names').value ;
				var add_item_kor_names = parent.document.getElementById('add_item_kor_names').value ;
				var add_item_en_names = parent.document.getElementById('add_item_en_names').value ;
				for(i=0;i< document.colForm.item_no.length;i++){
					if (document.colForm.item_no[i].checked){
						flag = true;
						if(temp_for_add_item == ''){
							temp_for_add_item = row_temp ;
							var item_no =  document.colForm.item_no[i].value ;
							add_item_nos = item_no ;
							add_ref_tn = $('chooseType').value ;
							add_item_ids = $('item_id'+item_no).value ;
							add_order_nos = $('order_no'+item_no).value ;
							add_item_names = $('item_name'+item_no).value ;
							add_item_kor_names = ($('item_kor_name'+item_no).value==''?'null':$('item_kor_name'+item_no).value) ;
							add_item_en_names = ($('item_en_name'+item_no).value==''?'null':$('item_en_name'+item_no).value) ;
						}else{
							temp_for_add_item += ',' + row_temp ;
							var item_no = document.colForm.item_no[i].value ;
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
				parent.document.getElementById('temp_for_add_item').value = temp_for_add_item;
				parent.document.getElementById('add_item_nos').value = add_item_nos ;
				parent.document.getElementById('add_item_ids').value = add_item_ids ;
				parent.document.getElementById('add_order_nos').value = add_order_nos ;
				parent.document.getElementById('add_ref_tn').value = add_ref_tn ;
				parent.document.getElementById('add_item_names').value = add_item_names ;
				parent.document.getElementById('add_item_kor_names').value = add_item_kor_names ;
				parent.document.getElementById('add_item_en_names').value = add_item_en_names ;
				
				parent.document.getElementById('coltable').style.visibility='hidden';
			}
			
		</script>
</head>
<body
	Style="margin-left: 0px; margin-right: 0px; margin-bottom: 0px; margin-top: 0px;">
<table id="addColTable" width="370" class="dr_d" border="0"
	cellpadding="0" cellspacing="1">
	<form id='colForm' name='colForm' method="post"><c:choose>
		<c:when test="${param.showModel == 1}">
			<%
				String table_name = "";
				if (request.getParameter("type").equals("1"))
					table_name = "AR_HISTORY";
				else
					table_name = "PA_HISTORY";
				List<ReportItemReference> dataList = SysService.getInstance()
						.retrieveReportItemListByTableName(table_name);
				request.setAttribute("dataList", dataList);
				if (dataList.size() > 0) {
			%>
			<td height="30" class="info_title_01" nowrap></td>
			<td class="info_title_01" nowrap>项目名称</td>
			<td class="info_title_01" nowrap>显示名称</td>
			<td class="info_title_01" nowrap>显示名称(韩)</td>
			<td class="info_title_01" nowrap>显示名称(英)</td>
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
					<td class="info_content_01"><input type="text"
						id="item_kor_name${oneResult.item_no }"
						name="item_kor_name${oneResult.item_no }"></td>
					<td class="info_content_01"><input type="text"
						id="item_en_name${oneResult.item_no }"
						name="item_en_name${oneResult.item_no }"></td>
				</tr>
			</c:forEach>
			<%
				}
			%>
		</c:when>
		<c:otherwise>
			<tr>
				<td height="30" class="info_title_01" nowrap></td>
				<td class="info_title_01" nowrap>项目名称</td>
				<td class="info_title_01" nowrap>显示名称</td>
				<td class="info_title_01" nowrap>显示名称(韩)</td>
				<td class="info_title_01" nowrap>显示名称(英)</td>
			</tr>
			<input type="hidden" id="chooseType" name="chooseType" value="TEMP">
			<tr>
				<td class="info_content_01"><input type="checkbox" id="item_no"
					name="item_no" value="1"> <input type="hidden"
					id="item_id1" name="item_id1" value="SHIFT">
					<input type="hidden" id="order_no1"
						name="order_no1" value="1">
					</td></td>
				<td class="info_content_01">工作状态</td>
				<td class="info_content_01"><input type="text" id="item_name1"
					name="item_name1" value="工作状态"></td>
				<td class="info_content_01"><input type="text"
					id="item_kor_name1" name="item_kor_name1"></td>
				<td class="info_content_01"><input type="text"
					id="item_en_name1" name="item_en_name1"></td>
			</tr>
			<tr>
				<td class="info_content_01"><input type="checkbox" id="item_no"
					name="item_no" value="2"> <input type="hidden"
					id="item_id2" name="item_id2" value="ATDETAIL">
					<input type="hidden" id="order_no2"
						name="order_no2" value="2">
					</td></td></td>
				<td class="info_content_01">考勤情况</td>
				<td class="info_content_01"><input type="text" id="item_name2"
					name="item_name2" value="考勤情况"></td>
				<td class="info_content_01"><input type="text"
					id="item_kor_name2" name="item_kor_name2"></td>
				<td class="info_content_01"><input type="text"
					id="item_en_name2" name="item_en_name2"></td>
			</tr>
			<tr>
				<td class="info_content_01"><input type="checkbox" id="item_no"
					name="item_no" value="3"> <input type="hidden"
					id="item_id3" name="item_id3" value="OVER_TIME">
					<input type="hidden" id="order_no3"
						name="order_no3" value="3">
					</td></td></td>
				<td class="info_content_01">加班</td>
				<td class="info_content_01"><input type="text" id="item_name3"
					name="item_name3" value="加班"></td>
				<td class="info_content_01"><input type="text"
					id="item_kor_name3" name="item_kor_name3"></td>
				<td class="info_content_01"><input type="text"
					id="item_en_name3" name="item_en_name3"></td>
			</tr>
		</c:otherwise>
	</c:choose>
	<tr>
		<td class="info_content_01" nowrap="nowrap" colspan="3"><input
			type="button" value="保存" onclick="fillData(${param.row_temp })"></td>
		<td class="info_content_01" nowrap colspan="2"><input
			type="button" value="关闭"
			onclick="javascript:parent.document.getElementById('coltable').style.visibility='hidden';">
		</td>
	</tr>
	</form>
</table>
</body>
</html>
