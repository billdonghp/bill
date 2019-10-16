<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<%@ page import="com.ait.utils.FormUtil,com.ait.ga.bean.*,com.ait.ga.cmd.visit.*"%>
<jsp:useBean id="voitureBean" class="com.ait.ga.bean.VoitureBean"/>
<script language="javascript">


function Save(){ 
	
	if(document.form1.PURCHASER.value==""||document.form1.PURCHASER.value==null){
	 alert("购买单位不能为空！");
	}else if(document.form1.VOITURE_BRAND.value==""||document.form1.VOITURE_BRAND.value==null){
	 alert("车辆名称不能为空！");
	}else if(document.form1.VOITURE_MODEL.value==""||document.form1.VOITURE_MODEL.value==null){
	 alert("车种（型号）不能为空！");
	}else if(document.form1.VOITURE_NUMBER.value==""||document.form1.VOITURE_NUMBER.value==null){
	 alert("车种（牌号）不能为空！");
	}else{
	if(document.form1.EXPENSES0.value==''){
		alert("请输入保险证号码！");
		return;
	}
	if(confirm('确认信息无误时，再保存！')){
	        document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=voitureManger&content=addVoiture";
	         document.form1.submit(); 	
	}	
	      
	}
  
}

	var type = null;
	var tableUtil = new Object();
	var i=0;
	var abc="";
tableUtil.appendRow = function(){	
		i = Number(document.form1.temp1.value)+1;
		document.form1.temp1.value = i;
		
		var nTr = document.getElementById('operateTable').insertRow();
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='EXPENSES"+i+"' value='' />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='INSURANCE_TYPE"+i+"' value='' />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='INSURANCE_COMPANY"+i+"' value='' />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='START_DATE"+i+"'  onClick='setday(this);' style='width:70px' value='' />"  ;
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='END_DATE"+i+"'  onClick='setday(this);' style='width:70px' value='' />";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<input type='text' name='INSURANCE_MENO"+i+"' value=''/>";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<input type='text' name='TOTAL_INSURANCE_FEE"+i+"' style='width:70px' value=''  onkeyup="+abc+"if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"+abc+" />&nbsp;元";
	    
		var td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>";			
	}
	
	tableUtil.deleteRow = function(){
		document.form1.temp1.value = Number(document.form1.temp1.value)- 1;
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
	
	var tableUtil1 = new Object();
	var y=0;
tableUtil1.appendRow = function(){	
		y = Number(document.form1.tempx.value)+1;
		document.form1.tempx.value = y;
		
		var nTr = document.getElementById('operateTable1').insertRow();
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='MAINTENACEDATE"+y+"'   onClick='setday(this);' style='width:70px' value=''/>";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='NOTE"+y+"' value='' />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='COST"+y+"' value='' onkeyup="+abc+"if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"+abc+" />&nbsp;元";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='MAINTENACEDATE1"+y+"'  onClick='setday(this);' style='width:70px' value='' />"  ;
		
		var td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum1' title='取消该行请选择后点击删除'/>";			
	}
	
	tableUtil1.deleteRow = function(){
		document.form1.tempx.value = Number(document.form1.tempx.value)- 1;
		var radioArr = document.getElementsByName('rowNum1');
		var tbody = document.getElementById('operateTable1').tBodies[0];
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
	
function paseValueToAmount(value){   
     if(value!=null&&value!=''){   
         var decimalIndex=value.indexOf('.');   
         if(decimalIndex=='-1'){   
             return false;   
         }else{   
             var decimalPart=value.substring(decimalIndex+1,value.length);   
             if(decimalPart.length>2){   
                 return true;   
             }else{   
                 return false;   
             }   
         }   
     }   
     return false;   
 } 
	
</script>
</head>
<body>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>
			
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr> 
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display content -->
		<br>
		<form name="form1" method="post" action="">
		<input type="hidden" name="temp" value="0">
<input type="hidden" name="temp1" value="0">
<input type="hidden" name="tempx" value="0">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">车辆内容<font color="red">(带*号的为必填项)</font></td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">	    
		    <tr>
		      <td class="info_title_01">*购买单位</td>
			 <td class="info_content_00"><input name="PURCHASER" type="text" style="width:250px "></td>
		     <td class="info_title_01">排气量</td>
			 <td class="info_content_00"> <input name="CAPACITY" type="text" style="width:80px "></td>
			 <td class="info_title_01">座位数</td>
			 <td class="info_content_00"><input name="SEATS" type="text" style="width:80px "></td>		      
		    </tr>
		  <tr>
		      <td class="info_title_01">*车辆名称</td>
			 <td class="info_content_00"><input name="VOITURE_BRAND" type="text"  style="width:80px "></td>
		     <td class="info_title_01">	*车种（型号）</td>
			 <td class="info_content_00"><input name="VOITURE_MODEL" type="text"  style="width:80px " ></td>
			 <td class="info_title_01"> *车种（牌号）</td>
			 <td class="info_content_00"><input name="VOITURE_NUMBER" type="text"  style="width:80px "></td>		      
		    </tr>
		   <tr>
		      <td class="info_title_01">购买日期</td>
			 <td class="info_content_00"><input type="text" name="PURCHASE_DATE"  class="content" style="width:80px "  readonly onClick="setday(this);"></td>
		     <td class="info_title_01">保修期限</td>
			 <td class="info_content_00"><input name="AS_DURATION" type="text" style="width:80px "></td>
			 <td class="info_title_01">租赁日期</td>
			 <td class="info_content_00" ><input name="RENT_DATE" type="text"  style="width:80px " readonly onClick="setday(this);"></td>		      
		  </tr>	 
		  <tr>
		      <td class="info_title_01">初始里程数</td>
			 <td class="info_content_00"><input type="text" name="STAT_KILOMETER"  class="content" style="width:80px ">&nbsp;公里</td>
		     <td class="info_title_01">初始燃油量</td>
			 <td class="info_content_00"><input name="STAT_OIL" type="text" style="width:80px "></td>
			 <td class="info_title_01">租赁期间</td>
			 <td class="info_content_00" ><input name="RENT_TIME" type="text" style="width:80px ">年</td>	
			 </tr>	
			<td class="info_title_01">行驶证</td>
			 <td class="info_content_00"><input name="GO_CARD" type="text" style="width:80px "></td>
			 <td class="info_title_01">行驶里程数</td>
			 <td class="info_content_00" colspan="3"><input name="GO_KILL" type="text" style="width:80px " onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">&nbsp;公里 </td>
			      
			<tr>		      
		  </tr>	     
				<tr>
					<td align="left" class="title1" colspan="10">驾驶员信息</font></td>
				</tr>
		    <tr>
		      <td class="info_title_01">姓名</td>
			 <td class="info_content_00"><input name="DRIVER_NAME" type="text" style="width:250px "></td>
		     <td class="info_title_01">员工编号</td>
			 <td class="info_content_00"> <input name="EMPID" type="text" style="width:80px "></td>
			 <td class="info_title_01">就业合同编号</td>
			 <td class="info_content_00"><input name="JOB_CONTRACT" type="text" style="width:80px "></td>		      
		    </tr>
		    <tr>
		      <td class="info_title_01">身份证</td>
			 <td class="info_content_00"><input name="CARD" type="text" style="width:250px "></td>
		     <td class="info_title_01">驾驶证</td>
			 <td class="info_content_00" colspan="3"> <input name="DRIVER_CARD" type="text" style="width:80px "></td>
			 	      
		    </tr>
		    <tr>
		      <td class="info_title_01">健康证</td>
			 <td class="info_content_00"><input name="JOUN_CARD" type="text" style="width:250px "></td>
		     <td class="info_title_01">驾照类型</td>
			 <td class="info_content_00" colspan="3"> <input name="DRIVER_CARD_TYPE" type="text" style="width:80px "></td>
		    </tr>
				<tr>
					<td align="left" class="title1" colspan="10">车辆费用</font></td>
				</tr>
		  <tr>
		     <td class="info_title_01">购买/租赁价格</td>
			 <td class="info_content_00"><input name="PRICE" type="text"  style="width:80px ">&nbsp;元</td>
		     <td class="info_title_01">增值税</td>
			 <td class="info_content_00"> <input name="ADDED_TAX" type="text" style="width:80px ">&nbsp;元</td>
			 <td class="info_title_01">进口关税</td>
			 <td class="info_content_00"><input name="CUSTOM_TAX" type="text"  style="width:80px ">&nbsp;元</td>		      
		    </tr>
		   <tr>
		    <td class="info_title_01">	进口附加费</td>
			<td class="info_content_00"><input name="ADDITIONAL_TAX" type="text" style="width:80px ">&nbsp;元</td>	
		      <td class="info_title_01">车辆挂号费</td>
			 <td class="info_content_00"><input name="SIGN_REGISTER" type="text" style="width:80px ">&nbsp;元</td>
		     <td class="info_title_01">其他费用</td>
			<td class="info_content_00"><input name="OTHER_EXPENSES" type="text" style="width:80px ">&nbsp;元</td>		      
		  </tr>	
		  </table>
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">车辆保险</font></td>
				</tr>
			</table>
		 <table id = 'operateTable' width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		   		    <tr>
		     <td class="info_title_01">*保险证号码</td>
			 <td class="info_title_01">险种</td>
			 <td class="info_title_01">保险公司</td>
			 <td class="info_title_01" >开始日期</td>		   		      
		      <td class="info_title_01">结束日期</td>
			 <td class="info_title_01">保险内容  </td>
		     <td class="info_title_01">保险费用</td>
			<td class="info_title_01"">操作</td>
		  </tr>	
		   <tr>
		     <td class="info_content_01"><input type="text" name="EXPENSES0"> </td>
			 <td class="info_content_01"><input type="text" name="INSURANCE_TYPE0"></td>
			 <td class="info_content_01"><input type="text" name="INSURANCE_COMPANY0" ></td>
			 <td class="info_content_01" ><input type="text" name="START_DATE0" onClick="setday(this);" style="width:70px"></td>		   		      
		      <td class="info_content_01"><input type="text" name="END_DATE0" onClick="setday(this);" style="width:70px"></td>
			 <td class="info_content_01"><input type="text" name="INSURANCE_MENO0"></td>
		     <td class="info_content_01"><input type="text" name="TOTAL_INSURANCE_FEE0" style="width:70px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">&nbsp;元</td>
			<td class="info_content_01" nowrap="nowrap">
				<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow();" name="a1" id="a1">
				<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow();" name="a1" id="a1">
			</td>
		  </tr>	
		  </table>
		   <table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">车辆保养</font></td>
				</tr>
			</table>
			 <table id = 'operateTable1' width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		   		    <tr>
		     <td class="info_title_01">保养日期</td>
			 <td class="info_title_01">保养内容</td>
			 <td class="info_title_01">保养花费</td>
			 <td class="info_title_01" >预计保养日期</td>		   		      
			<td class="info_title_01"">操作</td>
		  </tr>	
		   <tr>
		     <td class="info_content_01"><input type="text" name="MAINTENACEDATE0"  onClick="setday(this);" style="width:70px"> </td>
			 <td class="info_content_01"><input type="text" name="NOTE0"></td>
			 <td class="info_content_01"><input type="text" name="COST0" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">&nbsp;元</td>
			 <td class="info_content_01" ><input type="text" name="MAINTENACEDATE10" onClick="setday(this);" style="width:70px"></td>	
			<td class="info_content_01" nowrap="nowrap">
				<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil1.appendRow();" name="a11" id="a11">
				<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil1.deleteRow();" name="a11" id="a11">
			</td>
		  </tr>	
		  </table>
		</form>

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

</html>
