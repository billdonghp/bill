<%@ include file="../inc/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.sy.service.SysService"%>
<%@ page import="java.util.*"%>
<jsp:directive.page import="com.ait.sqlmap.util.SimpleMap" />
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../css/default.css">
	</head>
	<body>
		<% 
			String temp = request.getParameter("temp"); 
			if(temp.equals("row")){ 
				request.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html;charset=utf-8"); 
				String menu = request.getParameter("menu"); 
				String cpnyId=request.getParameter("cpnyId");
				SimpleMap sMap = new SimpleMap(); 
				sMap.set("MENU_CODE", menu); 
				sMap.set("cpnyId",cpnyId);
				List dataList = SysService.getInstance().retrieveTableByMenu(sMap); 
				request.setAttribute("dataList", dataList); 
		%>
		<c:if test="${fn:length(dataList) > 0 }">
			<table id='dataTable' width="100%" class="dr_d" border="1"
				cellpadding="0" cellspacing="0">
				<tr>
					<td class="info_title_01" nowrap="nowrap">
						中文名称
					</td>
					<td class="info_title_01" nowrap="nowrap">
						韩文名称
					</td>
					<td class="info_title_01" nowrap="nowrap">
						英文名称
					</td>
					<td class="info_title_01" nowrap="nowrap">
						显示模式
					</td>
					<td class="info_title_01" nowrap="nowrap">
						数据归属
					</td>
					<td class="info_title_01" nowrap="nowrap">
						&nbsp;
					</td>
					<td class="info_title_01" nowrap="nowrap">
						&nbsp;
					</td>
				</tr>
				<c:forEach items="${dataList }" var="oneResult">
					<input type="hidden" name="rt_no"
						value="${oneResult.rt_no }">
					<tr>
						<td class="info_content_01" nowrap="nowrap">
							<input name="name_${oneResult.rt_no }" value='${oneResult.table_name }' onchange="recordUpdate(${oneResult.rt_no })">
						</td>
						<td class="info_content_01" nowrap="nowrap">
							<input name="kor_name_${oneResult.rt_no }"
								value='${oneResult.table_kor_name }' onchange="recordUpdate(${oneResult.rt_no })">
						</td>
						<td class="info_content_01" nowrap="nowrap">
							<input name="en_name_${oneResult.rt_no }"
								value='${oneResult.table_en_name }' onchange="recordUpdate(${oneResult.rt_no })">
						</td>
						<td class="info_content_01" nowrap="nowrap">
							<input name="show_model_${oneResult.rt_no }" type="hidden" value="${oneResult.view_model }">
							<span>
							<c:choose>
								<c:when test='${oneResult.view_model == 2 }'>
										日期样式
								</c:when>
								<c:when test='${oneResult.view_model == 3 }'>
										纵列样式
								</c:when>
								<c:when test='${oneResult.view_model == 4 }'>
										横表样式
								</c:when>
								<c:otherwise>
										竖表样式
								</c:otherwise>
							</c:choose>
							</span>
						</td>
						<td class="info_content_01" nowrap="nowrap">
							<input name="report_type_${oneResult.rt_no }" type="hidden" value="${oneResult.report_type }">
							<span>
							<c:choose>
								<c:when test='${oneResult.report_type == 2 }'>
										工资信息
								</c:when>
								<c:otherwise>
										考勤信息
								</c:otherwise>
							</c:choose>
							</span>
						</td>
						<td class="info_content_01" nowrap="nowrap">
							<c:if test="${oneResult.view_model == 1 or oneResult.view_model == 3 or oneResult.view_model == 4}">
							<span id="operateColumn" onclick="operateColumn(this.id,0,'${oneResult.rt_no }','${param.cpnyId}')"
								style="cursor:hand">添加列</span>
							<span id="operateColumn" onclick="modifyColumn(${oneResult.rt_no },${oneResult.report_type })"
								style="cursor:hand">修改列</span>
							</c:if>
						</td>
						<td class="info_content_01" nowrap="nowrap">
							<span
								onclick="delDetail(this.parentNode.parentNode.rowIndex,${oneResult.rt_no })"
								style="cursor:hand">删除</span>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<%}else{ %>
		<% 
			String table_name = request.getParameter("table_name") ;
			List<ReportItemReference> dataList = SysService.getInstance().retrieveReportItemListByTableName(table_name); 
			request.setAttribute("dataList",dataList);
			if(dataList.size() > 0){
		%>
			<table id='dataTable' width="100%" class="dr_d" border="1"
				cellpadding="0" cellspacing="0">
				<td height="30" class="info_title_01" nowrap></td>
				<td class="info_title_01" nowrap>项目名称</td>
				<td class="info_title_01" nowrap>显示名称</td>
				<td class="info_title_01" nowrap>显示名称(韩)</td>
				<td class="info_title_01" nowrap>显示名称(英)</td>
				<c:forEach items="${dataList }" var="oneResult">
					<tr>
						<td class="info_content_01">
							<input type="checkbox" id="item_no" name="item_no" value="${oneResult.item_no }">
							<input type="hidden" id="item_id${oneResult.item_no }" name="item_id${oneResult.item_no }" value="${oneResult.item_id}">
						</td>
						<td class="info_content_01">
							${oneResult.item_name }
						</td>
						<td class="info_content_01">
							<input type="text" id="item_name${oneResult.item_no }" name="item_name${oneResult.item_no }" value="${oneResult.item_name }">
						</td>
						<td class="info_content_01">
							<input type="text" id="item_kor_name${oneResult.item_no }" name="item_kor_name${oneResult.item_no }" >
						</td>
						<td class="info_content_01">
							<input type="text" id="item_en_name${oneResult.item_no }" name="item_en_name${oneResult.item_no }" >
						</td>
					</tr>
				</c:forEach>
			</table>
			<%} 
		}%>
	</body>
</html>
