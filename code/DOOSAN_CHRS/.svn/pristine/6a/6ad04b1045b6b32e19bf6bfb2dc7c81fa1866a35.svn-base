<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<%@ page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date" %>
<%@ page import="com.ait.utils.FormUtil,com.ibm.icu.text.SimpleDateFormat,com.ait.sysparam.*" %>
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" /> 
<html>
<head>

<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>班车安排申请</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
var str="";
function CheckNumber(){
 var text=document.form1.APPLY_USERSCOUNT.value.replace(/[^\x00-\xff]/g,"**");
 if(text.length>500){
 return true;
 }else{
 return false;
 }
}

function Save() {

k = Number(document.form1.temp1.value);
	
	 var startime;
	 var endtime;
	 var startdate1;
     var startdate;
	 var enddate1;
	 var enddate;
	 var today;
	
	for(var z=0 ; z<=k ; z++){
	     startime=document.form1["APPLY_STARTTIME"+z].value.replace(":","");
	     endtime=document.form1["ARRIVE_TIME"+z].value.replace(":","");
		 startdate1=document.form1["APPLY_STARTDATE"+z].value.replace("-","");
		 startdate=startdate1.replace("-","");
		 enddate1=document.form1["ARRIVE_DATE"+z].value.replace("-","");
		 enddate=enddate1.replace("-","");
		 today=document.form1.today.value;
		
		if(document.form1["busAreaType"+z].value == ""){ 
		alert("请选择区域");
		return;
		}
		if(document.form1["busTypeCode"+z].value == ""){ 
		alert("请选择运营班车");
		return;
		}
		if(startime == null || startime ==""){
		alert("出发时间不能为空！");return;
		
		}
		if(endtime == null || endtime ==""){
		alert("到达时间不能为空！");return;
		
		}
		
		if(Number(startdate)>Number(enddate)){
		  alert("到达日期必须在出发日期之后");return;
		}
		if(parseInt(startdate)==parseInt(enddate)){
		if(Number(startime)>=Number(endtime)){
			 alert("到达时间必须在出发时间之后!");return;
			}
		}	
		if(document.form1["SEATNUM"+z].value == ""){ 
		alert("座位数不能为空");
		return;
		}
		if(document.form1["RIDENUM"+z].value == ""){ 
		alert("乘坐人数不能为空");
		return;
		}
		
		startime='';
	    endtime='';
		startdate1='';
		startdate='';
		enddate1='';
		enddate='';
	}

/*
var startime=document.getElementById("APPLY_STARTTIME0").value.replace(":","");
var endtime=document.getElementById("ARRIVE_TIME0").value.replace(":","");
var startdate1=document.form1.APPLY_STARTDATE0.value.replace("-","");
var startdate=startdate1.replace("-","");
var enddate1=document.form1.ARRIVE_DATE0.value.replace("-","");
var enddate=enddate1.replace("-","");
var today=document.form1.today.value;
if(document.getElementById("busAreaType0").value == ""){ 
alert("请选择区域");
return;
}
if(document.getElementById("busTypeCode0").value == ""){ 
alert("请选择运营班车");
return;
}
if(startime == null || startime ==""){
alert("出发时间不能为空！");return;

}
if(endtime == null || endtime ==""){
alert("到达时间不能为空！");return;

}

if(Number(startdate)>Number(enddate)){
  alert("到达日期必须在出发日期之后");return;
}
if(parseInt(startdate)==parseInt(enddate)){
if(Number(startime)>=Number(endtime)){
	 alert("到达时间必须在出发时间之后!");return;
	}
}	

if(document.getElementById("SEATNUM0").value == ""){
alert("座位数不能为空");
return;
}

if(document.getElementById("RIDENUM0").value == ""){
alert("乘坐人数不能为空");
return;
}

*/
    j = Number(document.form1.temp1.value);

	if (confirm("是否进行申请!")){
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=gaApply&content=addBusArrangeApply&flag="+j;
		document.form1.submit();
	}
	

}
function view(id){
	document.form1.action="gaControlServlet?menu_code=${param.menu_code}&operation=bookingVoiture&content=view&applyNo="+id;
	document.form1.submit();
}

function Checktype(tempvalue){   
    var   patrn=/^[1-9]{0,3}$/;
    if  (!patrn.test(tempvalue)){   
       alert("请输入整数！");   
       return  false;   
      }   
       return true; 
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
 

function checkTimeValue(str){
  var z=/^(([0-1][0-9])|([2-2][0-3])):([0-5][0-9])$/;
  if(!z.test($(str).value)){
   alert("请输入正确的时间格式(xx:xx)");
   $(str).value="";
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
		td.innerHTML = "<select name='busAreaType"+i+"' > " + document.getElementById('busAreaType0').innerHTML + "</select>";

				
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='APPLY_STARTDATE"+i+"'  style='width:80px' value='' onClick='setday(this);' readonly />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='APPLY_STARTTIME"+i+"' > " + document.getElementById('APPLY_STARTTIME0').innerHTML + "</select>";
		
		
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='busTypeCode"+i+"' > " + document.getElementById('busTypeCode0').innerHTML + "</select>";		
		
				
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='SEATNUM"+i+"'  style='width:35px' maxlength='4' onkeyup=if(isNaN(value)||paseValueToAmount(value))execCommand('undo') />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='RIDENUM"+i+"'  style='width:35px' maxlength='4' onkeyup=if(isNaN(value)||paseValueToAmount(value))execCommand('undo') />";
		
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='ARRIVE_DATE"+i+"'  style='width:80px' value='' onClick='setday(this);' readonly />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='ARRIVE_TIME"+i+"' > " + document.getElementById('ARRIVE_TIME0').innerHTML + "</select>";
		
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<textarea name='REMARK"+i+"' style='height: 25px;width:150px' type='_moz' onfocus=this.style.height='50px'; onblur=this.style.height='20px'; ></textarea>";
	    
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
</SCRIPT>

<body>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");		
String today=timeFormatter.format(d);
String today1=timeFormatter1.format(d);
EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
%>
<form name="form1" method="post" action="">
<input name="today" value="<%=today1%>" type="hidden">	
<input type="hidden" name="temp1" value="0">
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
				班车安排申请
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table id = 'operateTable' width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		
		    <tr align="center" bgcolor="#F5F5F5">
			  <td nowrap="nowrap" class="info_title_01">
				代请人</td>
				<td nowrap="nowrap" class="info_content_01"><input type="hidden" name="APPLYER_CHINESENAME" value="<%=admin.getChineseName() %>"> <%=admin.getChineseName() %></td>
				
			  <!-- 
				<td nowrap="nowrap" class="info_title_01">
				司机姓名</td>
				<td nowrap="nowrap" class="info_content_00" >
				<ait:codeClass codeClass="DriverCode" name="driverCode"  all="all"/>
				</td>
				 -->
				 
				  <td nowrap="nowrap" class="info_title_01" style="width:80px ">
				决裁者</td>	
				 <td nowrap="nowrap" class="info_content_01" >
		      <% GaAffirm  ga = new GaAffirm();
		      List list =ga.getAffirmor1(admin.getAdminID(),"VehicleOtApply",essSysparam.isContainsOwner());%>
		      <%if(!list.isEmpty()){%>
			      <input type="hidden" value="<%=list.size()%>" name="affirmor">
			      <table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">

				      <%for(int i=0;i<list.size();i++){
				      gaAffirmList=(GaAffirmList)list.get(i);%> 
				    <tr>
				      <td>

				      <font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font>
				      <input type="hidden" value="<%=gaAffirmList.getAffirmorId()%>" name="affirmorId"><font color="#990099"><%=gaAffirmList.getAffirmorName()%></font>
					<%
						int affirmLevel=10;
					 	if (gaAffirmList.getAffirmorDuty()!=null && gaAffirmList.getAffirmorDuty().equals("C_12005_93775")) {
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
		      <font color="red">没有决裁者</font>		      
		      <% }%>	
		      
		       <td nowrap="nowrap" class="info_content_01" colspan="6">
		       &nbsp;
			   </td>	
		</tr>	
		<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">区域</td>
				<td nowrap="nowrap" class="info_title_01">出发日期</td>
				<td nowrap="nowrap" class="info_title_01">出发时间</td>
				<td nowrap="nowrap" class="info_title_01">运营班车</td>
				<td nowrap="nowrap" class="info_title_01">座位数</td>
				<td nowrap="nowrap" class="info_title_01">乘坐人数</td>
				<td nowrap="nowrap" class="info_title_01">到达日期</td>
				<td nowrap="nowrap" class="info_title_01">到达时间</td>
				<td nowrap="nowrap" class="info_title_01">备注</td>
				<td nowrap="nowrap" class="info_title_01">操作</td>
			</tr>
		<tr align="center" bgcolor="#F5F5F5">	 
				
				<td nowrap="nowrap" class="info_content_01" >
				<ait:codeClass codeClass="BusAreaType" name="busAreaType0"  all="all"/>
				</td>
			
				
				<td nowrap="nowrap" class="info_content_01"><input type="text" name="APPLY_STARTDATE0" class="content" style="width:80px " value="<%=today %>" onClick="setday(this);" readonly></td>
				
				<td nowrap="nowrap" class="info_content_01">
				 <ait:time style="essOt" name="APPLY_STARTTIME0" spacing="30" />
				<!-- <input name="APPLY_STARTTIME0" id="APPLY_STARTTIME0" value="" type="text" style="width:35px" onblur="checkTimeValue(this);"> -->
				</td>
				
				
		         
				<td nowrap="nowrap" class="info_content_01" >
				<ait:codeClass codeClass="BusTypeCode" name="busTypeCode0" all="all"/>
				</td>
		     
			   
				<td nowrap="nowrap" class="info_content_01" ><input type="text" name="SEATNUM0" style="width:35px" maxlength="4"  onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">&nbsp;</td>
			
			  
				<td nowrap="nowrap" class="info_content_01" ><input type="text" name="RIDENUM0" style="width:35px" maxlength="4"  onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">&nbsp;</td>
				
				<td nowrap="nowrap" class="info_content_01"><input type="text" name="ARRIVE_DATE0" class="content" style="width:80px " value="<%=today %>" onClick="setday(this);" readonly></td>
			 
				<td nowrap="nowrap" class="info_content_01">
				<ait:time style="essOt" name="ARRIVE_TIME0" spacing="30" />
				<!-- <input name="ARRIVE_TIME0" id="ARRIVE_TIME0" value="" type="text" style="width:35px" onblur="checkTimeValue(this);"></td>  -->
				<td nowrap="nowrap" class="info_content_01">
					<textarea name="REMARK0" style=" height: 25px;width:150px " type="_moz"
					onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
				</td>
				
				<td nowrap="nowrap" class="info_content_01">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow();" name="a1" id="a1">
				</td>	 
				</tr>
			
			
		
		 </table>
		 <table width="100%" border="0" cellspacing="0" cellpadding="10">		
				<c:forEach var="i" begin="1" end="9">
					<tr>
						<td class="info_content_01">&nbsp;</td>					
					</tr>
				</c:forEach>			
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
</form>
</body>
<ait:xjos />
</html>
