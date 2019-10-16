<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<SCRIPT type="text/javascript">

function Save() {
	
	var rowNum = Number($("maxRowNum").value);
	for(var i=0; i<=rowNum; i++){
		if($('presentName' + i) != null) {
			if($('presentName'+i).value == "")
			{
				alert("请选择礼品名称!");
				return false;
			}
			var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
			if(!patrn.test($('quentity'+i).value)){
				alert("数量必须为数字且不能为空 !");
				return false;
			}
		}
	}
	document.form1.action="/gaControlServlet?operation=insertFoodScheme&menu_code=${param.menu_code}";
	document.form1.fireSubmit();
}


var tableUtil = new Object();
var i=0;
tableUtil.appendRow = function(){	

		i = Number(document.form1.maxRowNum.value)+1;
		document.form1.maxRowNum.value = i;
		
		document.form1.rowCount.value = Number(document.form1.rowCount.value)+1;
		
		var trs = document.getElementById('Tables').insertRow();
		
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='presentName" + i +"'onChange='updateValue(this.value,"+i+");'>" + document.getElementById('presentName0').innerHTML + "</select>" ;
		$('presentName'+i).selectedIndex = 0;
		
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<span id='span1"+i+"'></span>&nbsp;";
		
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<span id='span2"+i+"'></span>&nbsp;" ;
		
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<span id='span3"+i+"'></span>&nbsp;" ;
		
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<span id='span4"+i+"'></span>&nbsp;" ;
		
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='hidden' name='unitPrice"+i+"' value=''>"+
					   "<input type='text' name='quentity"+i+"' size='8' onkeyup='updateTotalPrice(this.value," + i + ");'>";
					
		td = trs.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<span id='span6"+i+"'>&nbsp;</span>" ;			
					   
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

function updateValue(cell,i) 
{
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=getFoodInfoForInsertScheme&condition=" + encodeURIComponent(cell);
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onSuccess : function(XmlHttpRequest){
            				xml = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr");
							$('span1'+i).innerHTML  = xml[0].childNodes[0].firstChild.nodeValue;
							$('span2'+i).innerHTML  = xml[0].childNodes[1].firstChild.nodeValue;
							$('span3'+i).innerHTML  = xml[0].childNodes[2].firstChild.nodeValue;
							$('span4'+i).innerHTML  = xml[0].childNodes[3].firstChild.nodeValue + "&nbsp;元";
							$('unitPrice'+i).value = xml[0].childNodes[3].firstChild.nodeValue;	
							updateTotalPrice($('quentity'+i).value,i)	
            				}}
        );
}

function updateTotalPrice(context,i)
{
	var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
	if(!patrn.test($('quentity'+i).value) && $('quentity'+i).value != "")
	{
		$('span6'+i).innerHTML = "<a style='color:#FF0000'>数量错误 !</a>";
		return false;
	}
	$('span6'+i).innerHTML = round (Number($('unitPrice'+i).value) * Number(context),2)+"&nbsp;元";
}

function round(num,n) 
{
    var dd=1; 
    var temp; 
    for(i=0;i<n;i++) 
    { 
      dd*=10; 
    } 
    temp=num*dd; 
    temp=Math.round(temp); 
    return temp/dd; 
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
		<%@ include file="../inc/common_toolbar_a.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><br>
		<br>
		<table id="Tables" width="100%" border="1" cellspacing="0"
			cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr>
				<td align="left" class="title1" colspan="18">夜餐方案信息</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">名称</td>
				<td nowrap="nowrap" class="info_content_00">
					<input type="text" name="schemeName" size="15" required/>
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">备注</td>
				<td class="info_content_00">
					<textarea name="remark" rows="2" cols="25"></textarea></td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">食品名称</td>
				<td width="15%" class="info_title_01">品牌</td>
				<td width="15%" class="info_title_01">规格</td>
				<td width="15%" class="info_title_01">单位</td>
				<td width="15%" class="info_title_01">单价</td>
				<td width="15%" class="info_title_01">数量</td>
				<td width="15%" class="info_title_01">总价</td>
				<td width="15%" class="info_title_01">操作</td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_content_01">
					<ait:select dataListName="foodList" name="presentName0" all="all" onChange="updateValue(this.value,0);"/>
				</td>
				<td nowrap="nowrap" class="info_content_01"><span id="span10">&nbsp;</span></td>
				<td nowrap="nowrap" class="info_content_01"><span id="span20">&nbsp;</span></td>
				<td nowrap="nowrap" class="info_content_01"><span id="span30">&nbsp;</span></td>
				<td nowrap="nowrap" class="info_content_01"><span id="span40">&nbsp;</span></td>
				<td nowrap="nowrap" class="info_content_01">
					<input type="hidden" name="unitPrice0" value="">
					<input type="text" name="quentity0" size="8" onkeyup="updateTotalPrice(this.value,0);">
				</td>
				<td nowrap="nowrap" class="info_content_01"><span id="span60">&nbsp;</span></td>
				<td nowrap="nowrap" class="info_content_01"><img
					src="../images/btn/Add.gif" style="cursor: pointer;"
					onclick="tableUtil.appendRow();"> <img
					src="../images/btn/Delete.gif" style="cursor: pointer;"
					onclick="tableUtil.deleteRow();"></td>
			</tr>
		</table>

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
<ait:xjos/>
</html>
