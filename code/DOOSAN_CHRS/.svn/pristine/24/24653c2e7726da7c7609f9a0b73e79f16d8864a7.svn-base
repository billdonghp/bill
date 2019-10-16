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
	}else if(document.form1.VOITURE_NUMBER.value==""||document.form1.VOITURE_NUMBER.value==null){
	 alert("车种（型号）不能为空！");
	}else{
///	if(document.form1.EXPENSES0.value==''){
///		alert("请输入保险证号码！");
///		return;
//	}
	if(confirm('确认信息无误时，再保存！')){
	        document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=voitureManger&content=voitureMangerSave";
	         document.form1.submit(); 	
	}	
	      
	}
  
}

	var type = null;
	var tableUtil = new Object();
	var i=0;
	var abc="";
tableUtil.appendRow = function(){	
		i = Number(document.getElementById("temp1").value);
		document.getElementById("temp1").value = i+1;
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
	    td.innerHTML = "<input type='text' name='INSURANCE_MENO"+i+"' value='' />";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<input type='text' name='TOTAL_INSURANCE_FEE"+i+"' style='width:70px' value='' onkeyup="+abc+"if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"+abc+" />&nbsp;元";
	    
		var td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>";			
	}
	
	tableUtil.deleteRow = function(){
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
		y = Number(document.getElementById("tempx").value)+1;
		document.getElementById("tempx").value = y;
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
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">车辆内容</font></td>
				</tr>
			</table>
			<%
		String sign =(request.getParameter("sign")!=null&&!request.getParameter("sign").equals("undefined"))?request.getParameter("sign"):"";		
		if(!sign.equals("") && sign!=null){ 
		VoitureManger vm = new VoitureManger();
		voitureBean=vm.getAnVoiture(sign);%>
		<input name="sign" type="hidden" value="<%=sign%>">
		<%} %>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">	    
		    <tr>
		      <td class="info_title_01">购买单位</td>
			 <td class="info_content_00"><input name="PURCHASER" type="text" value="<%=voitureBean.getPurchaser()!=null?voitureBean.getPurchaser():""%>" style="width:250px "></td>
		     <td class="info_title_01">排气量</td>
			 <td class="info_content_00"> <input name="CAPACITY" value="<%=voitureBean.getCapacity()!=null?voitureBean.getCapacity():""%>" type="text" style="width:80px "></td>
			 <td class="info_title_01">座位数</td>
			 <td class="info_content_00"><input name="SEATS" value="<%=voitureBean.getSeats()!=null?voitureBean.getSeats():""%>" type="text" style="width:80px "></td>		      
		    </tr>
		  <tr>
		      <td class="info_title_01">车辆名称</td>
			 <td class="info_content_00"><input name="VOITURE_BRAND" value="<%=voitureBean.getVoiture_Brand()!=null?voitureBean.getVoiture_Brand():""%>" type="text"  style="width:80px "></td>
		     <td class="info_title_01">	车种（型号）</td>
			 <td class="info_content_00"><input name="VOITURE_MODEL" value="<%=voitureBean.getVoiture_Model()!=null?voitureBean.getVoiture_Model():""%>" type="text"  style="width:80px " ></td>
			 <td class="info_title_01"> 车种（牌号）</td>
			 <td class="info_content_00"><input name="VOITURE_NUMBER" value="<%=voitureBean.getVoiture_Number()!=null?voitureBean.getVoiture_Number():""%>" type="text"  style="width:80px "></td>		      
		    </tr>
		   <tr>
		      <td class="info_title_01">购买日期</td>
			 <td class="info_content_00"><input type="text" value="<%=voitureBean.getPurchase_Date()!=null?voitureBean.getPurchase_Date():""%>" name="PURCHASE_DATE"  class="content" style="width:80px "  readonly onClick="setday(this);"></td>
		     <td class="info_title_01">保修期限</td>
			 <td class="info_content_00"><input name="AS_DURATION" value="<%=voitureBean.getAS_Duration()!=null?voitureBean.getAS_Duration():""%>" type="text" style="width:80px "></td>
			 <td class="info_title_01">租赁日期</td>
			 <td class="info_content_00" ><input name="RENT_DATE" value="<%=voitureBean.getRENT_DATE()!=null?voitureBean.getRENT_DATE():""%>" type="text"  style="width:80px " readonly onClick="setday(this);"></td>		      
		  </tr>	 
		  <tr>
		      <td class="info_title_01">初始里程数</td>
			 <td class="info_content_00"><input type="text" name="STAT_KILOMETER" value="<%=voitureBean.getSTAT_KILOMETER()!=null?voitureBean.getSTAT_KILOMETER():""%>" class="content" style="width:80px ">&nbsp;公里</td>
		     <td class="info_title_01">初始燃油量</td>
			 <td class="info_content_00"><input name="STAT_OIL" value="<%=voitureBean.getSTAT_OIL()!=null?voitureBean.getSTAT_OIL():""%>" type="text" style="width:80px "></td>
			 <td class="info_title_01">租赁期间</td>
			 <td class="info_content_00" ><input name="RENT_TIME" type="text" value="<%=voitureBean.getRENT_TIME()!=null?voitureBean.getRENT_TIME():""%>" style="width:80px ">年</td>		      
		  </tr>	     
		  <tr>
		  	 <td class="info_title_01">行驶证</td>
			 <td class="info_content_00"><input name="GO_CARD" value="<%=voitureBean.getGO_CARD()!=null?voitureBean.getGO_CARD():""%>" type="text" style="width:80px "></td>
			 <td class="info_title_01">行驶里程数</td>
			 <td class="info_content_00"><input name="GO_KILL" value="<%=voitureBean.getGO_KILL()!=null?voitureBean.getGO_KILL():""%>" type="text" style="width:80px "></td>		      		      
		  	 
		  </tr>	     
				<tr>
					<td align="left" class="title1" colspan="10">驾驶员信息</font></td>
				</tr>
		    <tr>
		      <td class="info_title_01">姓名</td>
			 <td class="info_content_00"><input name="DRIVER_NAME" type="text" value="<%=voitureBean.getDRIVER_NAME()!=null?voitureBean.getDRIVER_NAME():""%>" style="width:250px "></td>
		     <td class="info_title_01">员工编号</td>
			 <td class="info_content_00"> <input name="EMPID" type="text" value="<%=voitureBean.getEMPID()!=null?voitureBean.getEMPID():""%>" style="width:80px "></td>
			 <td class="info_title_01">就业合同编号</td>
			 <td class="info_content_00"><input name="JOB_CONTRACT" type="text" value="<%=voitureBean.getJOB_CONTRACT()!=null?voitureBean.getJOB_CONTRACT():""%>" style="width:80px "></td>		      
		    </tr>
		    <tr>
		      <td class="info_title_01">身份证</td>
			 <td class="info_content_00"><input name="CARD" type="text" value="<%=voitureBean.getCARD()!=null?voitureBean.getCARD():""%>" style="width:250px "></td>
		     <td class="info_title_01">驾驶证</td>
			 <td class="info_content_00" colspan="3"> <input name="DRIVER_CARD" value="<%=voitureBean.getDRIVER_CARD()!=null?voitureBean.getDRIVER_CARD():""%>" type="text" style="width:80px "></td>
		    </tr>
		    <tr>
		      <td class="info_title_01">健康证</td>
			 <td class="info_content_00"><input name="JOUN_CARD" value="<%=voitureBean.getJOUN_CARD()!=null?voitureBean.getJOUN_CARD():""%>" type="text" style="width:250px "></td>
		     <td class="info_title_01">驾照类型</td>
			 <td class="info_content_00" colspan="3"> <input name="DRIVER_CARD_TYPE" type="text" value="<%=voitureBean.getDRIVER_CARD_TYPE()!=null?voitureBean.getDRIVER_CARD_TYPE():""%>" style="width:80px "></td>
		    </tr>
			
				<tr>
					<td align="left" class="title1" colspan="10">购/租赁费用</font></td>
				</tr>
		  <tr>
		     <td class="info_title_01">购买/租赁价格</td>
			 <td class="info_content_00"><input name="PRICE" type="text" value="<%=voitureBean.getPrice()!=null?voitureBean.getPrice():""%>"  style="width:80px ">&nbsp;元</td>
		     <td class="info_title_01">增值税</td>
			 <td class="info_content_00"> <input name="ADDED_TAX" type="text" value="<%=voitureBean.getAdded_Tax()!=null?voitureBean.getAdded_Tax():""%>"  style="width:80px ">&nbsp;元</td>
			 <td class="info_title_01">进口关税</td>
			 <td class="info_content_00"><input name="CUSTOM_TAX" type="text" value="<%=voitureBean.getCustom_Tax()!=null?voitureBean.getCustom_Tax():""%>"  style="width:80px ">&nbsp;元</td>		      
		    </tr>
		   <tr>
		    <td class="info_title_01">	进口附加费</td>
			<td class="info_content_00"><input name="ADDITIONAL_TAX" type="text" value="<%=voitureBean.getAdditional_tax()!=null?voitureBean.getAdditional_tax():""%>"  style="width:80px ">&nbsp;元</td>	
		      <td class="info_title_01">挂牌费</td>
			 <td class="info_content_00"><input name="SIGN_REGISTER" type="text" value="<%=voitureBean.getSign_Register()!=null?voitureBean.getSign_Register():""%>"  style="width:80px ">&nbsp;元</td>
		     <td class="info_title_01">其他费用</td>
			<td class="info_content_00"><input name="OTHER_EXPENSES" type="text" value="<%=voitureBean.getOther_Expenses()!=null?voitureBean.getOther_Expenses():""%>"  style="width:80px ">&nbsp;元</td>		      
		  </tr>	
		  </table>
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">车辆保险</font></td>
				</tr>
			</table>
		 <table id = 'operateTable' width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		   		    <tr>
		     <td class="info_title_01">保险证号码</td>
			 <td class="info_title_01">险种</td>
			 <td class="info_title_01">保险公司</td>
			 <td class="info_title_01" >开始日期</td>		   		      
		      <td class="info_title_01">结束日期</td>
			 <td class="info_title_01">保险内容  </td>
		     <td class="info_title_01">保险费用</td>
			<td class="info_title_01"">操作</td>
		  </tr>	
		  <%
			if(!sign.equals("") && sign!=null){ 
			VoitureManger vm = new VoitureManger();
			List list=vm.getAnVoiture1(sign);
			int temp1 = list.size();
				for(int i=0;i<temp1;i++){
					voitureBean= (VoitureBean)list.get(i);
			%>
			<input type="hidden" name="temp1" id="temp1" value="<%=temp1 %>">
			   <tr>
			     <td class="info_content_01"><input type="text" name="EXPENSES<%=i %>" value="<%=voitureBean.getNOTE()!=null?voitureBean.getNOTE():""%>"> </td>
				 <td class="info_content_01"><input type="text" name="INSURANCE_TYPE<%=i %>" value="<%=voitureBean.getInsurance_Type()!=null?voitureBean.getInsurance_Type():""%>"></td>
				 <td class="info_content_01"><input type="text" name="INSURANCE_COMPANY<%=i %>" value="<%=voitureBean.getInsurance_Company()!=null?voitureBean.getInsurance_Company():""%>"></td>
				 <td class="info_content_01" ><input type="text" name="START_DATE<%=i %>" onClick="setday(this);" style="width:70px" value="<%=voitureBean.getSTART_DATE()!=null?voitureBean.getSTART_DATE():""%>"></td>		   		      
			      <td class="info_content_01"><input type="text" name="END_DATE<%=i %>" onClick="setday(this);" style="width:70px" value="<%=voitureBean.getEND_DATE()!=null?voitureBean.getEND_DATE():""%>"></td>
				 <td class="info_content_01"><input type="text" name="INSURANCE_MENO<%=i %>" value="<%=voitureBean.getINSURANCE_MENO()!=null?voitureBean.getINSURANCE_MENO():""%>"></td>
			     <td class="info_content_01"><input type="text" name="TOTAL_INSURANCE_FEE<%=i %>" style="width:70px" value="<%=voitureBean.getTotal_Insurance_Fee()!=null?voitureBean.getTotal_Insurance_Fee():""%>" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">&nbsp;元</td>
				<%if(i==0){ %>
				<td class="info_content_01" nowrap="nowrap">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow();" name="a1" id="a1">
				</td>
				<%}else{ %>
				<td class="info_content_01" nowrap="nowrap">
				<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>
				</td>
				<%} %>
			  </tr>	
			  <%} }%>
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
		   <%
			if(!sign.equals("") && sign!=null){ 
			VoitureManger vm = new VoitureManger();
			List list=vm.getAnVoiture2(sign);
			int tempx = list.size();
				for(int i=0;i<tempx;i++){
					voitureBean= (VoitureBean)list.get(i);
			%>
			<input type="hidden" name="tempx" id="tempx" value="<%=tempx %>">
		   <tr>
		     <td class="info_content_01"><input type="text" name="MAINTENACEDATE<%=i %>" value="<%=voitureBean.getNOTE()!=null?voitureBean.getNOTE():""%>"  onClick="setday(this);" style="width:70px"> </td>
			 <td class="info_content_01"><input type="text" name="NOTE<%=i %>" value="<%=voitureBean.getInsurance_Type()!=null?voitureBean.getInsurance_Type():""%>"></td>
			 <td class="info_content_01"><input type="text" name="COST<%=i %>" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')" value="<%=voitureBean.getInsurance_Company()!=null?voitureBean.getInsurance_Company():""%>">&nbsp;元</td>
			 <td class="info_content_01" ><input type="text" name="MAINTENACEDATE1<%=i %>" onClick="setday(this);" style="width:70px" value="<%=voitureBean.getSTART_DATE()!=null?voitureBean.getSTART_DATE():""%>"></td>	
			<%if(i==0){ %>
				<td class="info_content_01" nowrap="nowrap">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil1.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil1.deleteRow();" name="a1" id="a1">
				</td>
				<%}else{ %>
				<td class="info_content_01" nowrap="nowrap">
				<input type='radio' name='rowNum1' title='取消该行请选择后点击删除'/>
				</td>
				<%} %>
			  </tr>	
			  <%} }%>
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
