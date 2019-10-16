<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<%@ page import="com.ait.utils.FormUtil,com.ait.ga.bean.*,com.ait.ga.cmd.visit.*"%>
<jsp:useBean id="driverBean" class="com.ait.ga.bean.DriverBean"/>
<script language="javascript">


function Save(){ 
	
	if(confirm('确认信息无误时，再保存！')){
	        document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=voitureManger&content=driverMangerSave";
	         document.form1.submit(); 	
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
					<td align="left" class="title1" colspan="10">司机信息</font></td>
				</tr>
			</table>
			<%
		String sign =(request.getParameter("sign")!=null&&!request.getParameter("sign").equals("undefined"))?request.getParameter("sign"):"";		
		if(!sign.equals("") && sign!=null){ 
		VoitureManger vm = new VoitureManger();
		driverBean=vm.getAnDriver(sign);%>
		<input name="sign" type="hidden" value="<%=sign%>">
		<%} %>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >	    
		    <tr>
		      <td class="info_title_01">姓名</td>
			 <td class="info_content_00"><input name="DRIVER_NAME" type="text" value="<%=driverBean.getDRIVER_NAME()!=null?driverBean.getDRIVER_NAME():""%>" ></td>
		     <td class="info_title_01">身份证号</td>
			 <td class="info_content_00"> <input name="DRIVER_CARD_NUM" value="<%=driverBean.getDRIVER_CARD_NUM()!=null?driverBean.getDRIVER_CARD_NUM():""%>" ></td>
			 <td class="info_title_01">联系方式</td>
			 <td class="info_content_00"><input name="DRIVER_PHONE" value="<%=driverBean.getDRIVER_PHONE()!=null?driverBean.getDRIVER_PHONE():""%>" type="text" ></td>		      
		  
		   </tr>
		  
		  </table>
			 
		</form>
				<br><br><br><br><br><br><br><br>
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
