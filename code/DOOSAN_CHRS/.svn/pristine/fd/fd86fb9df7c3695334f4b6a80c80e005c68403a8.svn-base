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
<title>司机加班申请</title>
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
var startime=document.getElementById("APPLY_STARTTIME").value.replace(":","");
var endtime=document.getElementById("APPLY_ENDTIME").value.replace(":","");
var startdate1=document.form1.APPLY_STARTDATE.value.replace("-","");
var startdate=startdate1.replace("-","");
var enddate1=document.form1.APPLY_ENDDATE.value.replace("-","");
var enddate=enddate1.replace("-","");
var today=document.form1.today.value;
if(document.getElementById("driverCode").value == ""){ 
alert("请选择司机姓名");
return;
}
if(document.getElementById("driverOtType").value == ""){ 
alert("请选择工作类型");
return;
}
if(document.getElementById("OTTypeCode").value == ""){ 
alert("请选择加班类型");
return;
}
if(startime == null || startime ==""){
alert("开始时间不能为空！");return;

}
if(endtime == null || endtime ==""){
alert("结束时间不能为空！");return;

}

if(Number(startdate)>Number(enddate)){
  alert("结束日期必须在开始日期之后");return;
}
if(parseInt(startdate)==parseInt(enddate)){
if(Number(startime)>=Number(endtime)){
	 alert("结束时间必须在开始时间之后!");return;
	}
}	

///if(document.getElementById("DEPARTURES").value == ""){
///alert("出发地不能为空");
///return;
///}

///if(document.getElementById("DESTINATIONS").value == ""){
///alert("目的地不能为空");
///return;
///}
///if(document.getElementById("PURPOSE").value == ""){
///alert("用途/航班号不能为空");
///return;
///}


///	if(document.getElementById("APPLY_USERSCOUNT").value==""||document.getElementById("APPLY_USERSCOUNT").value==null){
///	 alert("乘车人数不能为空！");return;
///	}
///	if(document.form1.affirmor.value==""||document.form1.affirmor.value==null){
///	 alert("没有设置决裁者！请设置");return;
///	}
///	if(CheckNumber()){
///	 alert("250个汉字以内！");return;
	 
///	}

	if (confirm("是否进行申请!")){
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=gaApply&content=addDriverOtApply";
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
				司机加班申请
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		
		    <tr align="center" bgcolor="#F5F5F5">
			  <td nowrap="nowrap" class="info_title_01">
				申请人</td>
				<td nowrap="nowrap" class="info_content_00"><input type="hidden" name="APPLYER_CHINESENAME" value="<%=admin.getChineseName() %>"> <%=admin.getChineseName() %></td>
				
			  
				<td nowrap="nowrap" class="info_title_01">
				司机姓名</td>
				<td nowrap="nowrap" class="info_content_00" >
				<ait:codeClass codeClass="DriverCode" name="driverCode"  all="all"/>
				</td>
				<td nowrap="nowrap" class="info_title_01">
				工作类型</td>
				<td nowrap="nowrap" class="info_content_00" >
				<ait:codeClass codeClass="DriverOtType" name="driverOtType" all="all"/>
				</td>
		     	<td nowrap="nowrap" class="info_title_01">
				加班类型</td>
				<td nowrap="nowrap" class="info_content_00" >
				<input type="hidden" name="APPLY_DATE" value="<%=today %>"> 
				<ait:codeClass codeClass="OTTypeCode" name="OTTypeCode" all="all"/>
			
				</td>
				
				</tr>
				<tr>
				 <td nowrap="nowrap" class="info_title_01">
				开始日期</td>
				
				<td nowrap="nowrap" class="info_content_00"><input type="text" name="APPLY_STARTDATE" class="content" style="width:80px " value="<%=today %>" onClick="setday(this);" readonly></td>
				  <td nowrap="nowrap" class="info_title_01">
				开始时间</td>
				<!--
				
		      -->
				<td nowrap="nowrap" class="info_content_00">
			<ait:time name="APPLY_STARTTIME" spacing="30"/>
			<!--	<input name="APPLY_STARTTIME" id="APPLY_STARTTIME" value="" type="text" style="width:35px" onblur="checkTimeValue(this);">&nbsp; (时间24小时制，如13:30)-->
			  </td>
			  <td nowrap="nowrap" class="info_title_01">
				结束日期</td>	
				<td nowrap="nowrap" class="info_content_00"><input type="text" name="APPLY_ENDDATE" class="content" style="width:80px " value="<%=today %>" onClick="setday(this);" readonly></td>	      
		      <td nowrap="nowrap" class="info_title_01">
				结束时间</td>
				<td nowrap="nowrap" class="info_content_00">
			<!--	<input name="APPLY_ENDTIME" id="APPLY_ENDTIME" value="" type="text" style="width:35px" onblur="checkTimeValue(this);">&nbsp;(时间24小时制，如13:30)-->
				<ait:time name="APPLY_ENDTIME" spacing="30" />
				</td>
				 </tr>
				<tr>
				 <td nowrap="nowrap" class="info_title_01">
				加班长度</td>
				<td nowrap="nowrap" class="info_content_00">
		           <select name="OT_LENGTH" style="width: 50px " >
		           	<c:forEach items="${deductList}" var="oneDeduct">
				         		<option value="${oneDeduct}" >${oneDeduct}</option>
		           	</c:forEach>
		           </select>
		           (小时)
		         </td>
				 <td nowrap="nowrap" class="info_title_01">
				加班事由</td>
				<td nowrap="nowrap" class="info_content_00">
					<textarea name="REASON" style=" height: 25px;width:150px " type="_moz"
					onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
				</td>
				 <td nowrap="nowrap" class="info_title_01">
				备注</td>
				<td nowrap="nowrap" class="info_content_00">
					<textarea name="REMARK" style=" height: 25px;width:150px " type="_moz"
					onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
				</td>
				 <td nowrap="nowrap" class="info_title_01" style="width:80px ">
				决裁者</td>	
				 <td nowrap="nowrap" class="info_content_00" colspan="3">
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
		      <font color="red">没有决裁者</font>		      
		      <% }%>		      
				
			
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
