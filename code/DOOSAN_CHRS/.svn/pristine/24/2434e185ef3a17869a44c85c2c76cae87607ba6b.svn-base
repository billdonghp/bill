<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<!-- ga_add_present.jsp -->
<script language="javascript">

function Save()
{	
	var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
	if(!patrn.test(document.form1.UNIT_PRICE.value) && $("DATA_TYPE").value == "StockType001"){
      alert("单价必须输入数字或者小数！");
      return  false;
    }
    if(!patrn.test(document.form1.QUENTITY.value)){
      alert("数量必须输入数字！");
      return  false;
    }
    if($('DATA_TYPE').value=="StockType002")
    {
    	if(Number($("QUENTITY").value) > Number($("applyLimit").value))
    	{
    		alert("库存的礼品数量不足!");
    		return false;
    	}
    }
    document.form1.action="/gaControlServlet?operation=insertPresentYtgl&menu_code=${param.menu_code}";
	document.form1.submit();
}

function changeContent(type) {

	if (type == "StockType002") {
		var nTr = document.getElementById('tableObj').insertRow(6);
		
		td = nTr.insertCell() ;
		td.className = "info_title_01" ;
		td.innerHTML = "使用部门";
		
		td = nTr.insertCell() ;
		td.className = "info_content_00" ;
		td.innerHTML = $("divObj").innerHTML ;
		
		var nTr = document.getElementById('tableObj').insertRow(5);
		
		td = nTr.insertCell() ;
		td.className = "info_title_01" ;
		td.innerHTML = "库存";
		
		td = nTr.insertCell() ;
		td.className = "info_content_00" ;
		td.innerHTML = "<div id='stock'>&nbsp;</div>";
	} else {
		document.getElementById('tableObj').deleteRow(6);
		document.getElementById('tableObj').deleteRow(4);
	}
	getPresentInfo();
		
}

function getPresentInfo()
{	   
       var presentId1 = $("PRESENT_ID1");
       var presentId2 = $("PRESENT_ID2");
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
				   var stock = "库存： " + (Number(param.stock) - Number(param.applyQuentity)) + param.unit;
				   $("stock").innerHTML=stock;
				   $("applyLimit").value= (Number(param.stock) - Number(param.applyQuentity));
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
				   var stock = "库存： " + (Number(param.stock) - Number(param.applyQuentity)) + param.unit;
				   $("stock").innerHTML=stock;
				   $("applyLimit").value= (Number(param.stock) - Number(param.applyQuentity));
				}
			});
		}
	  
}

	function toggle(id){
	   $("PRESENT_ID1").value = "&nbsp;";
	   $("PRESENT_ID2").value = "&nbsp;";
	   $("PRESENT_ID3").value = "&nbsp;";
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
	function search(){
		document.form1.action="/ga/ga_add_present.jsp?menu_code=${param.menu_code}$cpnyId="+document.form1.cpnyId.value;
		document.form1.submit();
	}
</script>
</head>
<body>

							      
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_a.jsp"%>
<%String cpnyId=request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->

		<!-- display content --> <br>
		<form name="form1" method="post" action=""><input type="hidden"
			name="wasteTypeId" value="" />
		<input type="hidden" name="applyLimit" value=""/>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">礼品基本信息</td>
			</tr>
		</table>
		<table id="tableObj" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<input type="hidden" name="gmIds" value="" />
			<tr>
			<td nowrap="nowrap" class="info_title_01">所属法人</td>
				<td nowrap="nowrap" class="info_content_00">									
 					 <input type="hidden" name="cpnyId" value="78888888" ></input>
 					 DICC
				</td>			
			</tr>
			<tr>
				<td width="10%" class="info_title_01">礼品类型</td>
				<td class="info_content_00">
					<ait:codeClass name="PRESENT_TYPE" codeClass="PresentType" cpnyId="78888888" onChange="toggle(this.value);" all="all"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">礼品名称</td>
				<td class="info_content_00">
				<div id="id1" style="display:none">
					<ait:codeClass name="PRESENT_ID1" codeClass="PresentMarried" cpnyId="78888888" onChange="getPresentInfo();" all="all"/>
		         </div>   
		         <div id="id2" style="display:none">
					<ait:codeClass name="PRESENT_ID2" codeClass="PresentName" cpnyId="78888888" onChange="getPresentInfo();" all="all"/>
		         </div>    
		         <div id="id3">
					<ait:codeClass name="PRESENT_ID3" codeClass="PresentName" cpnyId="78888888" onChange="getPresentInfo();" all="all"/>
		         </div>  
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">单位</td>
				<td class="info_content_00">
					<ait:codeClass name="UNIT" codeClass="PresentUnit"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">单价</td>
				<td class="info_content_00">
					<input type="text" name="UNIT_PRICE" size="10">&nbsp;元
				</td>
			</tr>
				<td width="10%" class="info_title_01">类型</td>
				<td class="info_content_00">
					<ait:codeClass name="DATA_TYPE" codeClass="StockType" onChange="changeContent(this.value)"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">数量</td>
				<td class="info_content_00">
					<input name="QUENTITY" type="text" size="10"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">备注</td>
				<td class="info_content_00">
					<textarea name="REMARK" rows="3" cols="20"></textarea>
				</td>
			</tr>
		</table>
		
		<div id="divObj" style="display: none;">
			<ait:selDept name="APPLY_DEPT"/>
		</div>
					
		</form>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
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
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</body>
<ait:xjos />
</html>