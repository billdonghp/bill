<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.gm.bean.*,java.util.*" %>
<%@ page import="com.ait.utils.FormUtil,com.ibm.icu.text.SimpleDateFormat" %>
<%@ page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date,com.ait.sqlmap.util.SimpleMap" %>
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" /> 
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<jsp:useBean id="woodProductsList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="simpleMap" class="com.ait.sqlmap.util.SimpleMap"/>
<jsp:useBean id="applyType" class="java.lang.String" scope="request"/>
<jsp:useBean id="affirmList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="areaType" class="java.lang.String" scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>刻章申请</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function CheckNumber(tempvalue){   
    var   patrn=/^[0-9]+.{0,1}[0-9]{0,3}$/;
    if  (!patrn.test(tempvalue)){   
       alert("输入大于0的整数或者小数！");   
       return  false;   
      }   
       return true; 
}

function CheckNumber1(tempvalue){   
    var   patrn=/^[1-9][0-9]{0,3}?$/;
    if  (!patrn.test(tempvalue)){   
       alert("请输入大于0的整数！");   
       return  false;   
      }   
       return true; 
}

function Save() {
	
  var  numberArray=document.getElementsByName("numberArray");
  /**
  for(var i=0;i<numberArray.length;i++){    
	 if(document.form1["sumPrice"+numberArray[i].value].value==""||document.form1["sumPrice"+numberArray[i].value].value==null ){
	  alert("第"+Number(Number(numberArray[i].value)+1)+"项没有进行结果计算,请输入相关数值！");
	  return; 
	 }
  }
  **/
  if(numberArray.length==0){
	  alert("必须添加印章！");
	  return;
  }
  if(document.form1.today.value>document.form1.APPLY_DATE.value){
    alert("完工日期不能小于系统时间！");
    return;
  }
  if(document.form1.APPLY_DATE.value==""||document.form1.APPLY_DATE.value==null){
	 alert("完工日期不能为空！");
	 return;
  }
  if(document.form1.affirmor.value==""||document.form1.affirmor.value==null){
	 alert("没有设置决裁者！请设置");
	 return;
  }
  var contentArr="";
  var sunArr = document.getElementsByName("numberArray") ;
  	for (var i = 0; i< sunArr.length ; i++){
		contentArr +=sunArr[i].value+",";
	
	}
  $('numberArrayArr').value=contentArr.substr(0,contentArr.length-1);
  
  var affirmorIdArr="";
  var affirmorId = document.getElementsByName("affirmorId") ;
  	for (var i = 0; i< affirmorId.length ; i++){
  		affirmorIdArr +=affirmorId[i].value+",";	
	}
  $('affirmorIdArr').value=affirmorIdArr.substr(0,affirmorIdArr.length-1);
  
   //alert($('numberArrayArr').value);
   //alert($('affirmorIdArr').value);
  document.form1.encoding="multipart/form-data";
  document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=gaApply&content=addSealproductionApply";
  document.form1.submit();	
 
}

function change(temp){
var changenumber =document.form1["woodProductsNameSelect"+temp].value;
if(changenumber!="" && changenumber!=null){
var arrayStr=changenumber.split(",");	 
	 document.form1["woodProductsid"+temp].value=arrayStr[0];
	 document.form1["MEASUREMENT_UNIT_PRICE"+temp].value=arrayStr[1];
	 document.getElementById("unitPriceView"+temp).innerHTML=arrayStr[1]+" 元";
	 document.form1["woodProductsname"+temp].value=arrayStr[2];	 
}else{
 alert("印章为空");
 return;

}
}
 function Calculation(temp){
 var onePrice=document.form1["MEASUREMENT_UNIT_PRICE"+temp].value;///单价
 var num=document.form1["num"+temp].value;//数量 
 document.form1["sumPrice"+temp].value=(onePrice*num).toFixed(2);
 document.getElementById("sumPriceView"+temp).innerHTML=(onePrice*num).toFixed(2)+" 元" ;
 }
 
 
var type = null;
var tableUtil = new Object();	
tableUtil.appendRow = function(){ 
		var i = Number(document.form1.isVarNum.value);		
		var nTr = document.getElementById('operateTable').insertRow();
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='woodProductsNameSelect"+i+"'  onchange='change("+i+")'><option value=''>select</option> "+
		             "<%for(int j=0;j<woodProductsList.size();j++){ simpleMap=(SimpleMap)woodProductsList.get(j); %><option value='<%=simpleMap.get("WOODPRODUCTS_ID")%>,<%=simpleMap.get("MEASUREMENT_UNIT_PRICE")%>,<%=simpleMap.get("PRODUCTS_NAME")%>,<%=simpleMap.get("NOTES")%>'><%=simpleMap.get("PRODUCTS_NAME")%></option><%}%> "+
		              "</select>";
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input name='num"+i+"' type='text' maxlength='5' size='2' value='0' onkeyup='CheckNumber1(this.value)' onchange='Calculation("+i+")'>个</td>&nbsp; ";	

		/**
		td = nTr.insertCell() ;
		td.className = "info_content_00" ;
		td.innerHTML = "<input type='hidden' name='MEASUREMENT_UNIT_PRICE"+i+"' value='0'><span id='unitPriceView"+i+"'></span>&nbsp;";	     

		td = nTr.insertCell() ;
		td.className = "info_content_00" ;
	    td.innerHTML = "<input name='sumPrice"+i+"' value='' type='hidden'><span id='sumPriceView"+i+"'></span>&nbsp;";
	    **/     
		      
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='sealType"+i+"'><option value='newSeal'>新章</option><option value='oldSeal'>旧章</option></select>";	     
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML ="<input name='woodProductsid"+i+"' type='hidden' value=''> "+
		      "<input name='woodProductsname"+i+"' type='hidden' value=''>"+
		      " <input type='file' name='jpgUpload"+i+"' value=''  style='width:200px' title='上传'>";   	
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "&nbsp;<input type='hidden' name='numberArray'  value='"+i+"'/> <a href='#' onClick='tableUtil.deleteRow("+i+");'><img src='/images/left_menubullet_sub_m.gif' alt='删除该行' align='absmiddle'></a>";
		document.form1.isVarNum.value = Number(document.form1.isVarNum.value) + 1;		
	}
	
	tableUtil.deleteRow = function(temp){		
		var tbody = document.getElementById('operateTable').tBodies[0];	
		var deleteFlag=document.form1["woodProductsNameSelect"+temp];	
	    tbody.removeChild(deleteFlag.parentNode.parentNode);
	}
 
	function changeArea(){
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=gaApply&content=sealproductionpply";
		document.form1.submit();	
	}
</SCRIPT>

<body>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
String today=timeFormatter.format(d);
%>
<form name="form1" method="post" action="" >
<input type="hidden" name="isCalculation" value="0">
<input type="hidden" name="isVarNum" value="0">	
<input type="hidden" name="listsize" value="<%=woodProductsList.size()%>">
<input type="hidden" name="today" value="<%=today%>">
<input type="hidden" name="numberArrayArr" value="">
<input type="hidden" name="affirmorIdArr" value="">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/toolbar_apply.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->
	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				刻章申请<font color="red" size="2">${declaration}</font>
				</td>
			</tr>     
		</table>
		 <%if (!errorMsg.equals("")){%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		      <tr align="center" bgcolor="#F5F5F5">		      
		     <td nowrap="nowrap" class="info_title_01">职号</td>
			 <td nowrap="nowrap" class="info_title_01">部门</td>
			 <td nowrap="nowrap" class="info_title_01">姓名</td>
			 <td nowrap="nowrap" class="info_title_01">地区区分</td>
			 <td nowrap="nowrap" class="info_title_01">希望完工日期</td>
			 <td nowrap="nowrap" class="info_title_01">决裁者</td>
			 <td nowrap="15%" class="info_title_01">用途(250个汉字以内)</td>
			 <td nowrap="nowrap" class="info_title_01">添加印章</td>			    
		    </tr>		    
		    <tr>
		      <td nowrap="nowrap" class="info_content_01" ><input type="hidden" name="APPLYER_ID" value="<%=admin.getAdminID() %>"><%=admin.getUsername()%></td>
		      <td nowrap="nowrap" class="info_content_01"><input type="hidden" name="APPLYER_DEPTNAME" value="<%=admin.getDepartment() %>"><input type="hidden" name="APPLYER_DEPTID" value="<%=admin.getDeptID()%>"><%=admin.getDepartment() %></td>
		      <td nowrap="nowrap" class="info_content_01"><input type="hidden" name="APPLYER_CHINESENAME" value="<%=admin.getChineseName() %>"> <%=admin.getChineseName() %></td>
		      <td nowrap="nowrap" calss="info_content_01" align="center">
			      <select name="areaType" onchange="changeArea();" >
			      	<option value="yantai" <%if (areaType.equalsIgnoreCase("yantai")) {%> selected <%}%>>烟台</option>
			      	<option value="beijing" <%if (areaType.equalsIgnoreCase("beijing")) {%> selected <%}%>>北京</option>
			      </select>
		      </td>
		      <td nowrap="nowrap" class="info_content_01"><input type="text" name="APPLY_DATE" class="content" style="width:100px "  value="<%=today%>" onClick="setday(this);" readonly></td>
		      <td align="right" class="info_content_01" > 	     
		      <%if(!affirmList.isEmpty()){%>
		      <input type="hidden" value="<%=affirmList.size()%>" name="affirmor">
		      <table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">
		      	<%int affirmLevel=10;
		      		for(int i=0;i<affirmList.size();i++){
				      gaAffirmList=(GaAffirmList)affirmList.get(i);%> 
			      <tr>
				      <td>				      
				      
				      <%
				      	if ("yantai".equalsIgnoreCase(areaType)){
				      %>
				      	<font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font>
				      	<input type="hidden" value="<%=gaAffirmList.getAffirmorId()%>" name="affirmorId">		      
				      	<input type="hidden" value="<%=gaAffirmList.getAffirmorDuty()%>" name="affirmorDuty">
				      	<font color="#990099"><%=gaAffirmList.getAffirmorName()%></font>	
				      <%}else{
				    	  if ("7509910".equals(gaAffirmList.getAffirmorId())){
				      		%>		
				      		<font color="#990099"><%=i+1%></font>
				      		<input type="hidden" value="430903" name="affirmorId">		      
				      		<input type="hidden" value="C_12005_93775" name="affirmorDuty">
				      		<font color="#990099">李卉</font>	
				      	
						      <%
						      	continue;
				    	  		}
						      %>
				      	
				     	<font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font>
				      	<input type="hidden" value="<%=gaAffirmList.getAffirmorId()%>" name="affirmorId">		      
				      	<input type="hidden" value="<%=gaAffirmList.getAffirmorDuty()%>" name="affirmorDuty">
				      	<font color="#990099"><%=gaAffirmList.getAffirmorName()%></font>	
				      <%} %>
					<%
					 	if (gaAffirmList.getAffirmorDuty()!=null && (gaAffirmList.getAffirmorDuty().equals("C_12005_93775") || 
					 			gaAffirmList.getAffirmorDuty().equals("C_12005_43") || gaAffirmList.getAffirmorDuty().equals("C_12005_1330060"))) {
					 		affirmLevel=gaAffirmList.getAffirmLevel();
					 	}
					
						if (gaAffirmList.getAffirmLevel()<affirmLevel){
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
		      <table align="center">
		      <tr><td><font color="red">没有决裁者</font></td></tr>		      
		      </table>
		      <% }%>		      
		      </td>	 
		      <td width="15%" class="info_content_00"><textarea name="APPLY_PURPOSE" style=" height: 20px;width:250px " type="_moz"  onfocus="this.style.height='40px'" onblur="this.style.height='20px';"></textarea></td>
		      <td nowrap="nowrap" class="info_content_01"><ait:image src="/images/left_menubullet_main_p.gif"  border="0" align="absmiddle" 	onclick="tableUtil.appendRow();" style="cursor:hand" title="添加" enTitle="add" /> 		       
		    </tr>		  
		
		  	  
     </table>
     <table id="operateTable"width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
     	<tr align="center" bgcolor="#F5F5F5">
		      <td width="10%" class="info_title_01">制品名称</td>
		      <td width="11%" class="info_title_01">数量</td>
			 <!-- 
			 <td width="16%" class="info_title_01">制品单价</td>		   
			 <td width="19%" class="info_title_01">计算结果</td>
			 -->
			
		     <td width="8%" class="info_title_01">类型</td>
			   <td width="10%" class="info_title_01">上传文件</td>
			 <td width="5%" class="info_title_01">&nbsp;</td>
		  </tr>	
     </table>

		       <span id="shuruquyu" > 
  
		       <font color="red"></font>
             </span > 

    
  
		

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
<ait:xjos />
</html>
