<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<SCRIPT type="text/javascript">


var tableUtil = new Object();
var i=0;

tableUtil.appendRow = function(){	

		i = Number(document.form1.maxRowNum.value)+1;
		document.form1.maxRowNum.value = i;
		
		document.form1.rowCount.value = Number(document.form1.rowCount.value)+1;
		
		var trs = document.getElementById('Tables').insertRow();
		
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='deptId" + i +"'>" +document.getElementById('deptId0').innerHTML + "</select>" ;
		$('deptId'+i).selectedIndex = 0;
		
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='schemeName" + i+ "'>" + document.getElementById('schemeName0').innerHTML + "</select>" ;
		
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='quentity"+i+"' size='15' require>" ;
		
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<textarea name='remark"+i+ "' rows='2' cols='25'></textarea>";
		
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>";			
	}
	
tableUtil.deleteRow = function(){
	
		document.form1.rowCount.value = Number(document.form1.rowCount.value)-1;
		
	var radioArr = document.getElementsByName('rowNum');
	var tbody = document.getElementById('Tables').tBodies[0];
	var flag = false;
	for(var i=0;i<radioArr.length;i++)
		if(radioArr[i].checked){
			tbody.removeChild(radioArr[i].parentNode.parentNode);
			flag = true;
		}
	if(flag)
		return;
	else
		alert('请先选择要删除的行');
}


function Save() {
	
	var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
	if(!patrn.test($F('quentity0'))){
		alert("人数必须为数字!");
		return false;
	}
	document.form1.action="/gaControlServlet?operation=applyFood&menu_code=${param.menu_code}";
	document.form1.fireSubmit();
}

</SCRIPT>
<body>
<form name="form1" method="post" action="">
<input type="hidden" name="maxRowNum" value="0"> 
<input type="hidden" name="rowCount" value="0"> 
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/toolbar_apply_2.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display 3 level menu -->
		<%@ include file="../hrm/inc/hrm_view_toolbar.jsp"%>
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">夜餐申请</td>
			</tr>
		</table>
		<table id="Tables" width="100%" border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">
				<td width="8%" nowrap="nowrap" class="info_title_01">申请日期</td>
				<td nowrap="nowrap" class="info_content_00">
					<input type="text" name="applyDate" size="15" readonly="readonly" onclick="setday(this);" required/> 
				</td>
			</tr>
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">部门</td>
				<td nowrap="nowrap" class="info_title_01">类型</td>
				<td nowrap="nowrap" class="info_title_01">人数</td>
				<td nowrap="nowrap" class="info_title_01">备注</td>
				<td nowrap="nowrap" class="info_title_01">操作</td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_content_01">
					<ait:selDept name="deptId0" all="all" />
				</td>
				<td nowrap="nowrap" class="info_content_01">
					<ait:select dataListName="schemeList" name="schemeName0" all="all" />
				</td>
				<td nowrap="nowrap" class="info_content_01">
					<input type="text" name="quentity0" size="15">
				</td>
				<td nowrap="nowrap" class="info_content_01">
					<textarea name="remark0" rows="2" cols="25"></textarea>
				</td>
				<td nowrap="nowrap" class="info_content_01">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow();"> 
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow();">
				</td>
			</tr>
		</table>
		<br>
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
<ait:xjos></ait:xjos>
</form>
</body>
</html>
