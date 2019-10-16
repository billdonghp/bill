<%@ include file="../inc/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>ess参数设置</title>
<link rel="stylesheet" type="text/css" href="../css/default.css">
<script type="text/javascript">
	var ParamForm = function() {
		return {
			save : function() {
				document.getElementById("operation").value = "modify";
				document.getElementById("orderNo").value = document.getElementById("order_no").value;
				document.getElementById("SysparamForm").submit();
			},
			showTable : function(emTable, ifTable) {
				if (emTable != "")
					document.getElementById("emTable").value = emTable;
				if (ifTable != "")
					document.getElementById("ifTable").value = ifTable;
				document.getElementById("SysparamForm").submit();
			},
			fillFormula : function(emColumn, ifColumn) {
				var input = document.getElementById("formula_" + emColumn);
				if (input != null) {
					if (ifColumn != "")
						input.value = "COL:" + ifColumn;
					else
						input.value = "";
				}
			}
		};
	}();

	function Save() {
		ParamForm.save();
	}
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td nowrap="nowrap" ="nowrap="nowrap""></td>
		<td nowrap="nowrap" height="33" valign="TOP" align="RIGHT"><img src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>
		</td>
		<td height="33" align="LEFT" valign="TOP" nowrap="nowrap"><img src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td nowrap="nowrap" ="nowrap="nowrap""></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">

		<!-- display 3 level menu -->
		<%@ include file="../sy/inc/sy_toolbar.jsp"%>
		<!-- display content -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="15">
			<tr>
				<td class="line" nowrap="nowrap" align="center">
					<table border="0" cellpadding="0" cellspacing="1" class="dr_d">
						<tr>
							<c:choose>
								<c:when test="${empty emTable}">
									<td class="info_title_05" width="50%">选择人事表</td>
									<td class="info_content_00" width="50%">
										<select onchange="ParamForm.showTable(this.options[this.selectedIndex].value, '')">
											<option value="">请选择</option>
											<c:forEach var="table" items="${emTables}">
												<option value="${table}">${table}</option>
											</c:forEach>
										</select>
									</td>
								</c:when>
								<c:otherwise>
									<td class="info_title_05" width="50%">当前人事表</td>
									<td class="info_content_00" width="50%">${emTable}&nbsp;&nbsp;&nbsp;&nbsp;顺序号<input type="text" name="order_no" id="order_no" value="${orderNo}" style="width:30px;text-align:center;" /></td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<c:choose>
								<c:when test="${empty ifTable}">
									<td class="info_title_05" width="50%">对应接口表</td>
									<td class="info_content_00" width="50%">
										<select onchange="ParamForm.showTable('', this.options[this.selectedIndex].value)">
											<option value="">请选择</option>
											<c:forEach var="table" items="${ifTables}">
												<option value="${table}">${table}</option>
											</c:forEach>
										</select>
									</td>
								</c:when>
								<c:otherwise>
									<td class="info_title_05" width="50%">当前接口表</td>
									<td class="info_content_00" width="50%">${ifTable}</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="20">
				<td>&nbsp;</td>
			</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<form name="SysparamForm" id="SysparamForm" method="post" action="/syControlServlet">
				<input type="hidden" name="operation" id="operation" value="view" />
				<input type="hidden" name="content" id="content" value="ifparam" />
				<input type="hidden" name="menu_code" id="menu_code" value="sy0208" />
				<input type="hidden" name="emTable" id="emTable" value="${emTable}" />
				<input type="hidden" name="ifTable" id="ifTable" value="${ifTable}" />
				<input type="hidden" name="orderNo" id="orderNo" value="" />
				<c:if test="${not empty emColumns}">
					<tr>
						<td class="line" nowrap="nowrap" align="center">
							<table border="0" cellpadding="0" cellspacing="1" class="dr_d">
								<tr>
									<td class="info_title_05" style="text-align:left;">
										公式格式说明:<br />
										COL-使用映射表/视图中已有列<br />
										SQL-使用SQL从库中取数据(结果集第一条记录第一列值)<br />
										CST-使用常量(双引号表示字符串)<br />
										COD-使用代码表中的代码(COD:代码名称@代码类型)
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="line" nowrap="nowrap" align="center">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" height="15">
								<tr>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</c:if>
				<tr>
					<td class="line" nowrap="nowrap" align="center">
						<table border="0" cellpadding="0" cellspacing="1" class="dr_d">
							<c:forEach items="${emColumns}" var="emColumn">
								<tr>
									<td class="info_title_05">${emColumn[0]}<input type="hidden" name="emColumn" id="emColumn" value="${emColumn[0]}" /><c:if test="${not empty emColumn[1]}">&nbsp;&nbsp;&nbsp;&nbsp;[${emColumn[1]}]</c:if></td>
									<td class="info_content_00" style="text-align:center;">
										<input name="formula_${emColumn[0]}" id="formula_${emColumn[0]}" value="${emColumn[2]}" />
										&nbsp;&nbsp;
										<select onchange="ParamForm.fillFormula('${emColumn[0]}', this.options[this.selectedIndex].value);">
											<option>请选择关联</option>
											<c:forEach var="ifColumn" items="${ifColumns}">
												<option value="${ifColumn[0]}">${ifColumn[0]}<c:if test="${not empty ifColumn[1]}">&nbsp;&nbsp;&nbsp;&nbsp;[${ifColumn[1]}]</c:if></option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</form>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="15">
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
		<td width="11" height="5" align="LEFT" valign="TOP"><img src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>

</body>
</html>
