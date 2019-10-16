<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>洗衣申请</title>
</head>

<SCRIPT type="text/javascript">


var tableUtil = new Object();
var i=0;

tableUtil.appendRow = function(){	

		i = Number(document.form1.maxRowNum.value)+1;
		document.form1.maxRowNum.value = i;
		
		document.form1.rowCount.value = Number(document.form1.rowCount.value)+1;
		
		var nTr = document.getElementById('operateTable').insertRow();
		
		
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='WASH_NO_" + i + "' onChange='getWashInfo(" + i + ");'> " + document.getElementById('WASH_NO_0').innerHTML + "</select>" ;
		$("WASH_NO_" + i).selectedIndex = 0 ;
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<div id='unit_" + i + "' >&nbsp;</div>";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<div id='unitPrice_" + i + "' >&nbsp;</div>"+
						"<input type='hidden' name='priceTmp_"+i+"' value='' > ";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='QUENTITY_" + i + "' onblur='caclPrice(" + i + ");' />";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<div id='amount_" + i + "' >&nbsp;</div>";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<textarea name='REMARK_" + i + "' style=' height: 25px;width:150px' type='_moz' onfocus=this.style.height='50px'; onblur=this.style.height='20px';></textarea>";
	      
		var td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>";			
	}
	
	tableUtil.deleteRow = function(){
	
		document.form1.rowCount.value = Number(document.form1.rowCount.value)-1;
		
		var radioArr = document.getElementsByName('rowNum');
		var tbody = document.getElementById('operateTable').tBodies[0];
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


function getWashInfo(num)
{	   
	   $("unit_" + num).innerHTML = "&nbsp;";
	   $("unitPrice_" + num).innerHTML = "&nbsp;";
	   $("QUENTITY_" + num).value = "";
	   $("amount_" + num).innerHTML = "&nbsp;";
	   
       var washId = $("WASH_NO_" + num);
	   if (washId.value != "") {
	       var url = "/ajaxControlServlet" ;
			new Ajax.Request(url, {
				parameters : new Hash({
					'operation' : 'getWashInfo',
					'SEQ_NO' : washId.value
			
				}),
				onSuccess : function(transport) {
					
				   var hash = $H(transport.responseJSON);
				   var param = hash.get("param");
				   $("unit_" + num).innerHTML=param.unit;
				   $("unitPrice_" + num).innerHTML=param.unitPrice+"&nbsp;元";
				   $("priceTmp_"+num).value = param.unitPrice;
				}
			});
		}
	  
}
	
function caclPrice(num){

	if(!isNaN($("priceTmp_" + num).value) && !isNaN($("QUENTITY_" + num).value)) {
	
		var amount = $("priceTmp_" + num).value * $("QUENTITY_" + num).value;

		$("amount_" + num).innerHTML = amount+"&nbsp;元";
	}
}

function Save() {

	var rowNum = Number($("maxRowNum").value);
	for(var i=0; i<=rowNum; i++){
		if($("WASH_NO_" + i) != null) {
			
			if($("WASH_NO_" + i).value == "") {
				alert("请选择衣物！");
				return;
			}
			if($("QUENTITY_" + i).value == "" || $("QUENTITY_" + i).value == 0) {
				alert("请输入衣物数量！");
				return;
			}
			if(isNaN($("QUENTITY_" + i).value)) {
				alert("衣物数量为数字！");
				return;
			}
		}
	}
	
	document.form1.action="/gaControlServlet?operation=applyWash&menu_code=${param.menu_code}";
	document.form1.submit();
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
				<td align="left" class="title1" colspan="10">洗衣申请<font color="red" size="2">${declaration}</font></td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">
				<td width="8%" nowrap="nowrap" class="info_title_01">职号</td>
				<td width="16%" nowrap="nowrap" class="info_content_00">${empId}</td>
				<td width="8%" nowrap="nowrap" class="info_title_01">姓名</td>
				<td width="16%" nowrap="nowrap" " class="info_content_00">${name}</td>
				<td width="8%" nowrap="nowrap" class="info_title_01">部门</td>
				<td width="16%" nowrap="nowrap" class="info_content_00">${dept}</td>
				<td width="8%" nowrap="nowrap" class="info_title_01">备注</td>
				<td width="20%" nowrap="nowrap" class="info_content_00">
					<textarea name="REMARK" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
				</td>
			</tr>
		</table>
		<br>
		<br>
		<table id='operateTable' width="100%" border="1" cellspacing="0"
			cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr>
				<td align="left" class="title1" colspan="18">衣物信息</td>
			</tr>
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">衣物名称</td>
				<td nowrap="nowrap" class="info_title_01">单位</td>
				<td nowrap="nowrap" class="info_title_01">单价</td>
				<td nowrap="nowrap" class="info_title_01">数量</td>
				<td nowrap="nowrap" class="info_title_01">价格</td>
				<td nowrap="nowrap" class="info_title_01">洗衣人员明细</td>
				<td nowrap="nowrap" class="info_title_01">操作</td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_content_01">
					<ait:select name="WASH_NO_0" dataListName="clothingList" onChange="getWashInfo(0);" all="all"/>
				</td>
				<td nowrap="nowrap" class="info_content_01"><div id="unit_0">&nbsp;</div></td>
				<td nowrap="nowrap" class="info_content_01"><div id="unitPrice_0">&nbsp;</div></td>
				<input type="hidden" name="priceTmp_0" value="" >
				<td nowrap="nowrap" class="info_content_01">
					<input type="text" name="QUENTITY_0" onblur="caclPrice(0);"/>
				</td>
				<td nowrap="nowrap" class="info_content_01"><div id="amount_0">&nbsp;</div></td>
				<td nowrap="nowrap" class="info_content_01">
					<textarea name="REMARK_0" style=" height: 25px;width:150px " type="_moz"
					onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
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
</form>
</body>
</html>
