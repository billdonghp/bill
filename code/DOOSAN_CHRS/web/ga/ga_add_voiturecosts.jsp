<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<%@ page import="com.ait.utils.FormUtil,com.ait.ga.bean.*,com.ait.ga.cmd.visit.*,java.util.*"%>
<jsp:useBean id="voitureBean" class="com.ait.ga.bean.VoitureBean"/>
<jsp:useBean id="voitureResume" class="com.ait.ga.bean.VoitureBean"/>
<script language="javascript">

function Save(){ 
	if(CheckNumberlength()){	
	     alert("备注在250个汉字以内");  	  
	}else{
	  document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=voitureManger&content=AddMaintenanceCosts";
	  document.form1.fireSubmit(); 
	}
  
}


  function   CheckNumber(tempvalue)       {   
    var   patrn=/^[0-9]+.{0,1}[0-9]{0,3}$/;
    if  (!patrn.test(tempvalue)){   
       alert("输入数字或小数");   
       return  false;   
      }   
       return true; 
   }
    
function CheckNumberlength(){
 var text=document.form1.NOTE0.value.replace(/[^\x00-\xff]/g,"**");
 if(text.length>500){
 return true;
 }else{
 return false;
 }
}

	var type = null;
	var tableUtil = new Object();
	var i=0;
tableUtil.appendRow = function(){	
		i = Number(document.form1.temp1.value)+1;
		document.form1.temp1.value = i;
		
		var nTr = document.getElementById('operateTable').insertRow();
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='TIMING_DATE"+i+"' style='width:80px '  readonly onClick='setday(this);' required />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='CAUSES"+i+"' > " + document.getElementById('CAUSES0').innerHTML + "</select>" ;
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='UNIT"+i+"' > " + document.getElementById('UNIT0').innerHTML + "</select>" ;
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='AMOUNT"+i+"' onkeyup='return CheckNumber(this.value)' required />";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<textarea name='NOTE"+i+"' style='height: 25px;width:250px' type='_moz'  onfocus=this.style.height='50px'; onblur=this.style.height='20px';></textarea>";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<div><font color='red'>" + document.getElementById('name10').value + "</font> <div/>";
	    
		var td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>";
	}
	
	tableUtil.deleteRow = function(i){
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

</script>
</head>
<body>

<%VoitureManger vm = new VoitureManger();
List voitureResumelist =vm.getAllVoitureResume(); %>
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
		<input name="temp" type="hidden" value="0">
		<input name="temp1" type="hidden" value="0">
		<%
		String sign =(request.getParameter("sign")!=null&&!request.getParameter("sign").equals("undefined"))?request.getParameter("sign"):"";		
		if(!sign.equals("") && sign!=null){ 		
		voitureBean=vm.getAnVoiture(sign);%>
		<input name="sign" type="hidden" value="<%=sign%>">
		<%} %>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">车辆信息</td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">	   

		  <tr>
		     <td class="info_title_01">车种（名称）</td>
			 <td class="info_content_01"><%=voitureBean.getVoiture_Brand()!=null?voitureBean.getVoiture_Brand():"&nbsp;"%></td>
		     <td class="info_title_01">	车种（型号）</td>
			 <td class="info_content_01"><%=voitureBean.getVoiture_Model()!=null?voitureBean.getVoiture_Model():"&nbsp;"%></td>
			 <td class="info_title_01"> 车种（牌号）</td>
			 <td class="info_content_01"><%=voitureBean.getVoiture_Number()!=null?voitureBean.getVoiture_Number():"&nbsp;"%></td>		      
		    </tr>
		   <tr>
		      <td class="info_title_01">购买日期</td>
			 <td class="info_content_01"><%=voitureBean.getPurchase_Date()!=null?voitureBean.getPurchase_Date():"&nbsp;"%></td>
		     <td class="info_title_01">保修期限</td>
			 <td class="info_content_01" colspan="3"><%=voitureBean.getAS_Duration()!=null?voitureBean.getAS_Duration():"&nbsp;" %>年</td>
		  </tr>	   
		  </table>
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">增加车辆履历</td>
				</tr>
			</table>
		      <table width="100%"  border="1"cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" id = 'operateTable'>
		    <tr align="center" bgcolor="#F5F5F5">
		      <td class="info_title_01">
				发生日期</td>
		      <td class="info_title_01">
				项目</td>
			 <td class="info_title_01">
				单位</td>
			 <td class="info_title_01">
				数量</td>
		      <td width="25%" class="info_title_01">
				备注(250个汉字以内)</td>
			  <td class="info_title_01">
				输入者</td> 
				<td class="info_title_01">
				操作</td> 		
		    </tr>		  	 
		    <tr align="center" >		     
		      <td nowrap="nowrap" class="info_content_01">
		      <input type="text" name="TIMING_DATE0"  style="width:80px "  readonly onClick="setday(this);" required></td>     
		      <td nowrap="nowrap" class="info_content_01"><select name="CAUSES0">
		      <%for(int s=0;s<voitureResumelist.size();s++) {voitureResume=(VoitureBean)voitureResumelist.get(s);%>
		      <option value="<%=voitureResume.getCode_id() %>">
		      <%=voitureResume.getCode_name()%></option><%} %>
		      </select>
		      </td>
		      <td nowrap="nowrap" class="info_content_01">
		      <select name="UNIT0">
		      	<option value="0" selected="selected">元</option>
		      	<option value="1" selected="selected">公里</option>
		      	<option value="2" selected="selected">升</option>
		      </select>
		      </td>
		      <td nowrap="nowrap" class="info_content_01">
		      <input type="text" name="AMOUNT0" onkeyup="return CheckNumber(this.value)" required></td>
		      <td nowrap="nowrap" class="info_content_01" ><textarea name="NOTE0" style=" height: 25px;width:250px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea></td>
		      <td nowrap="nowrap" class="info_content_01">
		      <input type="hidden" name="name10" id="name10" value="<%=admin.getChineseName()%>">
		      <font color="red"><%=admin.getChineseName()%></font></td>	
		      <td nowrap="nowrap" class="info_content_01" >
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow();" name="a1" id="a1">
				<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow();" name="a1" id="a1">
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
<ait:xjos />
</html>
