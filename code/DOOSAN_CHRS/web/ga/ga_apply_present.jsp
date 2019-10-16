<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:useBean id="affirmList"  class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList"	scope="page" />
<%@ page
	import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date"%>
<html>
<head>
<!-- ga_apply_present.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>礼品申请1</title>
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
		td.innerHTML = "<input type='text' name=PRESENT_OBJECT_"+i+" size=30 value=''>";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='PRESENT_TYPE_" + i + "' onChange='toggle(this.value);'> " + document.getElementById('PRESENT_TYPE_0').innerHTML + "</select>" ;
		$("PRESENT_TYPE_" + i).selectedIndex = 0 ;
		
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='hidden' name='applyLimit_" + i + "' id='applyLimit_" + i + "'/><select name='PRESENT_ID3_" + i + "' onChange='getPresentInfo(" + i + ");'> " + document.getElementById('PRESENT_ID3_0').innerHTML + "</select>" ;
		$("PRESENT_ID3_" + i).selectedIndex = 0 ;
		
		
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<div id='unit_" + i + "' >&nbsp;</div>";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<div id='unitPrice_" + i + "' >&nbsp;</div>"+
						"<input type='hidden' name=priceTmp_"+i+" value=''>";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<div id='stock_" + i + "' >&nbsp;</div>" ;
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='QUENTITY_" + i + "' onblur='caclPrice(" + i + ");' />";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<input type='hidden' name='amountInput_" + i + "' value=''/>"+
	                    "<div id='amount_" + i + "' >&nbsp;</div>";
	    
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


function getPresentInfo(num)
{	   
	   $("unit_" + num).innerHTML = "&nbsp;";
	   $("unitPrice_" + num).innerHTML = "&nbsp;";
	   $("QUENTITY_" + num).value = "";
	   $("stock_" + num).innerHTML = "&nbsp;";
	   $("amount_" + num).innerHTML = "&nbsp;";
       var presentId1 = $("PRESENT_ID1_" + num);
       var presentId2 = $("PRESENT_ID2_" + num);
       var presentId3 = $("PRESENT_ID3_" + num);
	   if (presentId1.value != "") {
	       var url = "/ajaxControlServlet" ;
			new Ajax.Request(url, {
				parameters : new Hash({
					'operation' : 'getPresentInfo',
					'presentId' : presentId1.value
			
				}),
				onSuccess : function(transport) {
					
				   var hash = $H(transport.responseJSON);
				   var param = hash.get("param");
				   var stock = "库存： " + param.stock + "<br>申请中： " + param.applyQuentity;
				   $("unit_" + num).innerHTML=param.unit;
				   var price =param.unitPrice;
				   $("stock_" + num).innerHTML=stock;
				   $("applyLimit_" + num).value= Number(param.stock) - Number(param.applyQuentity);
				   $("priceTmp_" + num).value = price;
				   $("unitPrice_" + num).innerHTML = price+"&nbsp;元";
				}
			});
		}else
	   if (presentId2.value != "") {
	       var url = "/ajaxControlServlet" ;
			new Ajax.Request(url, {
				parameters : new Hash({
					'operation' : 'getPresentInfo',
					'presentId' : presentId2.value
			
				}),
				onSuccess : function(transport) {
					
				   var hash = $H(transport.responseJSON);
				   var param = hash.get("param");
				   var stock = "库存： " + param.stock + "<br>申请中： " + param.applyQuentity;
				   $("unit_" + num).innerHTML=param.unit;
				   var price =param.unitPrice;
				   $("stock_" + num).innerHTML=stock;
				   $("applyLimit_" + num).value= Number(param.stock) - Number(param.applyQuentity);
				   $("priceTmp_" + num).value = price;
				   $("unitPrice_" + num).innerHTML = price+"&nbsp;元";
				}
			});
		}else
	   if (presentId3.value != "") {
	       var url = "/ajaxControlServlet" ;
			new Ajax.Request(url, {
				parameters : new Hash({
					'operation' : 'getPresentInfo',
					'presentId' : presentId3.value
			
				}),
				onSuccess : function(transport) {
					
				   var hash = $H(transport.responseJSON);
				   var param = hash.get("param");
				   var stock = "库存： " + param.stock + "<br>申请中： " + param.applyQuentity;
				   $("unit_" + num).innerHTML=param.unit;
				   var price =param.unitPrice;
				   $("stock_" + num).innerHTML=stock;
				   $("applyLimit_" + num).value= Number(param.stock) - Number(param.applyQuentity);
				   $("priceTmp_" + num).value = price;
				   $("unitPrice_" + num).innerHTML = price+"&nbsp;元";
				}
			});
		}
	  
}
	
function caclPrice(num){

	if(!isNaN($("priceTmp_" + num).value) && !isNaN($("QUENTITY_" + num).value)) {
	
		var amount = $("priceTmp_" + num).value * $("QUENTITY_" + num).value;

		$("amount_" + num).innerHTML = Math.round(amount*100)/100 +"&nbsp;元";
		
		 $("amountInput_" + num).value =  Math.round(amount*100)/100;
	}
}

function Save() {
	
	if(document.form1.affirmor.value==""||document.form1.affirmor.value==null){
		alert("请设置决裁者！");
		return;
	}
	if($("REASON").value == "") {
		alert("请输入赠送原因！");
		return;
	}
	if($("USE_DATE").value == "") {
		alert("请输入使用日期！");
		return;
	}
	if($("PRESENT_OBJECT_0").value == "") {
				alert("请输入对口单位/个人！");
				return;
			}
    if($("PRESENT_TYPE_0").value == "") {
				alert("请选择礼品类型！");
				return;
			}	
	if($("QUENTITY_0" ).value == "" || $("QUENTITY_0" ).value == 0) {
				alert("请输入礼品数量！");
				return;
			}
	if(isNaN($("QUENTITY_0" ).value)) {
				alert("礼品数量为数字！");
				return;
			}
	if(Number($("QUENTITY_0" ).value) > Number($("applyLimit_0" ).value)) {
				alert("您申请的礼品数量不足！");
				return;
			}
	if((document.form1.PRESENT_ID1_0.value == null || document.form1.PRESENT_ID1_0.value == "") && (document.form1.PRESENT_ID2_0.value == null || document.form1.PRESENT_ID2_0.value == "") && (document.form1.PRESENT_ID3_0.value == null || document.form1.PRESENT_ID3_0.value == "")){

		alert("请选择礼品名称！！！");
		return;
		}
	document.form1.action="/gaControlServlet?operation=applyPresent&menu_code=${param.menu_code}";
	document.form1.submit();
}

function ScenePhotos(cell){
var presentId = cell.parentNode.firstChild.value ;

window.open("/gaControlServlet?operation=retrievePresentPhoto&menu_code=${param.menu_code}&presentId="+presentId,"","resizable=no,scrollbars,dependent,width=350,height=100,left=350,top=500");
}
	function toggle(id){
	   $("PRESENT_ID1_0").value = "&nbsp;";
	   $("PRESENT_ID2_0").value = "&nbsp;";
	   $("PRESENT_ID3_0").value = "&nbsp;";
	   
		var div1=document.getElementById("id1");
		var div2=document.getElementById("id2");
		var div3=document.getElementById("id3");
		
		if(id=="PresentMarried"){
			div1.style.display = "block";
			div2.style.display = "none";
			div3.style.display = "none";
		}else if(id=="PresentCompany"){ 
			div1.style.display = "none";
			div2.style.display = "block";
			div3.style.display = "none";
		}
	}
function Search(){

	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=retrieveDataForApplyPresent";
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
				<td align="left" class="title1" colspan="10">礼品申请<font color="red" size="2">${declaration}</font></td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">
				<td  class="info_title_01">礼品所属法人</td>
				         <td  class="info_content_00">
				          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="Search();"/>
				         </td> 
				<td width="8%" nowrap="nowrap" class="info_title_01">职号</td>
				<td width="16%" nowrap="nowrap" class="info_content_00">${empId}</td>
				<td width="8%" nowrap="nowrap" class="info_title_01">姓名</td>
				<td width="16%" nowrap="nowrap" " class="info_content_00">${name}</td>
				<td width="8%" nowrap="nowrap" class="info_title_01">部门</td>
				<td width="16%" nowrap="nowrap" class="info_content_00">${dept}</td>
				<!--  <td width="8%" nowrap="nowrap" class="info_title_01">对口单位/个人</td>
				<td width="20%" nowrap="nowrap" class="info_content_00">
					<input type="text" name="PRESENT_OBJECT" size=30>
				</td>
				-->	
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_title_01">*赠送原因</td>
				<td nowrap="nowrap" class="info_content_00">
					<textarea name="REASON" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
				</td>
				<td nowrap="nowrap" class="info_title_01">*使用日期</td>
				<td nowrap="nowrap" class="info_content_00">
					<input type="text" name="USE_DATE" value="" class="content" readonly onClick="setday(this);" style="width:70px">
				</td>
				<td nowrap="nowrap" class="info_title_01">备注</td>
				<td nowrap="nowrap" class="info_content_00">
					<textarea name="REMARK" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
				</td>
				<td nowrap="nowrap" class="info_title_01">决裁者</td>
				<td nowrap="nowrap" class="info_content_00">
					<%if(!affirmList.isEmpty()){%>
							      <input type="hidden" value="<%=affirmList.size()%>" name="affirmor">
							      <table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">

							      <%int affirmLevel=10;
							      for(int i=0;i<affirmList.size();i++){
							      gaAffirmList=(GaAffirmList)affirmList.get(i);%> 
							    <tr>
							      <td>
			
							      <font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font>
							      <input type="hidden" value="<%=gaAffirmList.getAffirmorId()%>" name="affirmorId">		      
				      			  <input type="hidden" value="<%=gaAffirmList.getAffirmorDuty()%>" name="affirmorDuty">
							      <font color="#990099"><%=gaAffirmList.getAffirmorName()%></font>
								<%									
								 	if (gaAffirmList.getAffirmorDuty()!=null && (gaAffirmList.getAffirmorDuty().equals("C_12005_93775") || 
								 			gaAffirmList.getAffirmorDuty().equals("C_12005_43") || gaAffirmList.getAffirmorDuty().equals("C_12005_1330060"))) {
								 		affirmLevel=gaAffirmList.getAffirmLevel();
								 	}
								
									if ("朴赞赫".equals(gaAffirmList.getAffirmorName())||"高在均".equals(gaAffirmList.getAffirmorName())){
								 %>
							      <img src="../images/btn/Delete_little.gif" title="删除" onclick='affirmorlist.deleteRow(this.parentNode.parentNode.rowIndex);'  align="absmiddle"><br />    
								<%
									}
								%>	
							      </td>
						      </tr>
						      <%
							      }
								%>	
					  			    </table> 
							      <%}else{ %>
							      <input type="hidden" value="" name="affirmor">
							      <table>
							      <tr><td nowrap="nowrap"><font color="red">没有决裁者</font></td></tr>		      
							      </table>
							      <% }%>
				</td>
			</tr>
		</table>
		<br>
		<br>
		<table id='operateTable' width="100%" border="1" cellspacing="0"
			cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr>
				<td align="left" class="title1" colspan="18">礼品信息</td>
			</tr>
			<tr align="center" bgcolor="#F5F5F5">
			    <td nowrap="nowrap" class="info_title_01">对口单位/个人</td>
				<td nowrap="nowrap" class="info_title_01">礼品类型</td>
				<td nowrap="nowrap" class="info_title_01">礼品名称</td>
				<td nowrap="nowrap" class="info_title_01">单位</td>
				<td nowrap="nowrap" class="info_title_01">单价</td>
				<td nowrap="nowrap" class="info_title_01">库存量</td>
				<td nowrap="nowrap" class="info_title_01">数量</td>
				<td nowrap="nowrap" class="info_title_01">价格</td>
				<td nowrap="nowrap" class="info_title_01">备注</td>
			</tr>
			<tr>    
				<input type="hidden" name="applyLimit_0" id="applyLimit_0"/>
				<td nowrap="nowrap" class="info_content_01">
					<input type="text" name="PRESENT_OBJECT_0" size=30 value=''>
				</td>
				<td nowrap="nowrap" class="info_content_01">
					<ait:codeClass name="PRESENT_TYPE_0" codeClass="PresentType" cpnyId="${cpnyId}" onChange="toggle(this.value);" all="all"/>
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<div id="id1" style="display:none">
					<ait:codeClass name="PRESENT_ID1_0" codeClass="PresentMarried" cpnyId="${cpnyId}" onChange="getPresentInfo(0);" all="all"/>
					<span style="color:red; cursor:pointer;" onClick="ScenePhotos(this)" title="查看礼品照片">查看照片</span>
		         </div>   
		         <div id="id2" style="display:none">
					<ait:codeClass name="PRESENT_ID2_0" codeClass="PresentName" cpnyId="${cpnyId}" onChange="getPresentInfo(0);" all="all"/>
					<span style="color:red; cursor:pointer;" onClick="ScenePhotos(this)" title="查看礼品照片">查看照片</span>
		         </div>   
		         <div id="id3">
					<ait:codeClass name="PRESENT_ID3_0" codeClass="PresentName" cpnyId="${cpnyId}" onChange="getPresentInfo(0);" all="all"/>
					<span style="color:red; cursor:pointer;" onClick="ScenePhotos(this)" title="查看礼品照片">查看照片</span>
		         </div>  
				</td>
				<td nowrap="nowrap" class="info_content_01"><div id="unit_0">&nbsp;</div></td>
				<td nowrap="nowrap" class="info_content_01"><div id="unitPrice_0">&nbsp;</div></td>
				<input type="hidden" name="priceTmp_0" value="">
				<td nowrap="nowrap" class="info_content_01"><div id="stock_0">&nbsp;</div></td>
				<td nowrap="nowrap" class="info_content_01">
					<input type="text" name="QUENTITY_0" onblur="caclPrice(0);"/>
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<input type="hidden" name="amountInput_0" value=''/>
				<div id="amount_0">&nbsp;</div></td>
				<td nowrap="nowrap" class="info_content_01">
					<textarea name="REMARK_0" style=" height: 25px;width:150px " type="_moz"
					onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
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
