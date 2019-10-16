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
<!-- ga_apply_voiture.jsp -->
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>派车申请</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
var time=null;


function showEmployeePartInformation(XmlHttpRequest){
    
    var dept = $('dept');
    var joinDate = $('joinDate');
    var info = XmlHttpRequest.responseText.split(",") ;
	dept.innerHTML= info[0];
    joinDate.innerHTML= info[1];
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
		td.innerHTML = "<select name='DISTINCTION"+i+"' id='DISTINCTION"+i+"' > " + document.getElementById('DISTINCTION0').innerHTML + "</select>" ;

		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text'  name='APPLY_DATE"+i+"' id='APPLY_DATE"+i+"'  class='content' readonly onClick='setday(this);' style='width:80px'>";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input name='APPLY_STARTTIME"+i+"' id='APPLY_STARTTIME"+i+"'  type='text' style='width:35px' onblur='checkTimeValue(this);'>&nbsp; (时间24小时制，如13:30)";

		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input name='APPLY_ENDTIME"+i+"' id='APPLY_ENDTIME"+i+"'  type='text' style='width:35px' onblur='checkTimeValue(this);'>&nbsp; (时间24小时制，如13:30)";		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<textarea name='CONTENT"+i+"' id='CONTENT"+i+"' style='height: 25px;width:150px' type='_moz' onfocus=this.style.height='50px'; onblur=this.style.height='20px'; ></textarea>";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='DRIVE_WAY"+i+"' id='DRIVE_WAY"+i+"'  style='width:150px' value='' />";
		
//		td = nTr.insertCell() ;
//		td.className = "info_content_01" ;
//		td.innerHTML = "<select name='visiterGiftYesOrNo"+i+"' > " + document.getElementById('visiterGiftYesOrNo0').innerHTML + "</select>" ;
	    
//	    td = nTr.insertCell() ;
//		td.className = "info_content_01" ;
//	    td.innerHTML = "<input type='text' name='GiftName"+i+"'  style='width:150pxS' value='' />";
	    
//	    td = nTr.insertCell() ;
//		td.className = "info_content_01" ;
//	    td.innerHTML = "<input type='text' name='GiftNumber"+i+"'  style='width:150px' value='' />";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<textarea name='memo"+i+"' style='height: 25px;width:150px' type='_moz' onfocus=this.style.height='50px'; onblur=this.style.height='20px'; ></textarea>";
	    
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
	
if(document.getElementById("TELL_PHONE").value == ""){
alert("联系电话不能为空");
return;
}
if(document.getElementById("LARDER").value == ""){
alert("乘用人不能为空");
return;
}
if(document.getElementById("LARDER_DEPTNAME").value == ""){
alert("所属部门不能为空");
return;
}

if(document.getElementById("LARDER_POST").value == ""){
alert("乘用人职位不能为空");
return;
}
if(document.getElementById("APPLY_USERSCOUNT").value==""||document.getElementById("APPLY_USERSCOUNT").value==null){
	 alert("乘车人数不能为空！");return;
	}

	if(document.form1.affirmor.value==""||document.form1.affirmor.value==null){
	 alert("没有设置决裁者！请设置");return;
	}
	if(CheckNumber()){
	 alert("250个汉字以内！");return;
	 
	}
	j = Number(document.form1.temp1.value);
	for(var z=0 ; z<=j ; z++){
		var startime=document.getElementById("APPLY_STARTTIME"+z).value.replace(":","");
		var endime=document.getElementById("APPLY_ENDTIME"+z).value.replace(":","");
		var startdate1=document.getElementById("APPLY_DATE"+z).value.replace("-","");
		if(document.form1('DISTINCTION'+z) != null && document.form1["DISTINCTION"+z].value == ""){
			alert("请选择区分！");
			return;
		}
		
		if(document.form1('APPLY_DATE'+z) != null && document.form1["APPLY_DATE"+z].value == ""){
			alert("请填写使用日期！");
			return;
		}		
		if(startime == null || startime ==""){
			alert("请填写出发时间！");return;

			}
		if(endime == null || endime ==""){
			alert("请填写结束时间！");return;

			}
		if(document.form1('CONTENT'+z) != null && document.form1["CONTENT"+z].value == ""){
			alert("请填写业务内容！");
			return;
		}		
		if(document.form1('DRIVE_WAY'+z) != null && document.form1["DRIVE_WAY"+z].value == ""){
			alert("请填写行车路线！");
			return;
		}		
	}
	
	

//	var endtime=document.getElementById("APPLY_ENDTIME").value.replace(":","");
//	var startdate1=document.form1.APPLY_DATE.value.replace("-","");
//	var startdate=startdate1.replace("-","");
//	var enddate1=document.form1.APPLY_ENDDATE.value.replace("-","");
//	var enddate=enddate1.replace("-","");
//	var today=document.form1.today.value;
	
	
//	if(endtime == null || endtime ==""){
//	alert("结束时间不能为空！");return;

//	}

//	if(Number(startdate)>Number(enddate)){
//	  alert("结束日期必须在开始日期之后");return;
//	}
//	if(parseInt(startdate)==parseInt(enddate)){
//	if(Number(startime)>=Number(endtime)){
//		 alert("结束时间必须在开始时间之后!");return;
//		}
//	}
	
	if (confirm("是否进行申请!")){
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=gaApply&content=addVoitureApply&flag="+1+"";
		document.form1.submit();
	}
	
	document.getElementById("applysaveid").style.display="none";//避免重复提交，隐藏按钮
	

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
<input type="hidden" name="visiter_factory_device" value="">
<input type="hidden" name="visiter_conference_language" value="">
<input type="hidden" name="pian_language" value="">
<input type="hidden" name="visiter_factory_language" value="">
<input type="hidden" name="temp" value="0">
<input type="hidden" name="temp1" value="0">
<input type="hidden" name="temp2" value="0">
<input type="hidden" name="temp3" value="0">
<input type="hidden" name="sysDate" value="${sysDate}"/>
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
				派车申请<font color="red" size="2">${declaration}</font>
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
				申请部门</td>
				<td nowrap="nowrap" class="info_content_00">
				<input type="hidden" name="APPLYER_DEPTNAME" value="${deptid }">${deptname }
				</td>
				<td nowrap="nowrap" class="info_title_01">
				联系电话</td>
				<td nowrap="nowrap" class="info_content_00" ><input type="text" id="TELL_PHONE" name="TELL_PHONE" style="width:100px"></td>
		     	 <td nowrap="nowrap" class="info_title_01" style="width:80px ">
				决裁者</td>		      
		      <td nowrap="nowrap" class="info_content_00" colspan="3">
		      
		      <% GaAffirm  ga = new GaAffirm();
		      List list =ga.getAffirmor1(admin.getAdminID(),"VehicleApply",essSysparam.isContainsOwner());%>
		      <%if(!list.isEmpty()){%>		      
				      <input type="hidden" value="<%=list.size()%>" name="affirmor">
		      <table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">

				      <%for(int i=list.size()-1;i<list.size();i++){
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
				     <!--  <img src="../images/btn/Delete_little.gif" title="删除" onclick='affirmorlist.deleteRow(this.parentNode.parentNode.rowIndex);'  align="absmiddle"><br />    --> 
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
		      
		      
		      </td>		 
				</tr>
				<tr>
				<td nowrap="nowrap" class="info_title_01">
				乘用人</td>
				<td nowrap="nowrap" class="info_content_00" ><input type="text" id="LARDER" name="LARDER" style="width:100px"></td>
		     	<td nowrap="nowrap" class="info_title_01">
				所属部门</td>
				<td nowrap="nowrap" class="info_content_00" ><input type="text" id="LARDER_DEPTNAME" name="LARDER_DEPTNAME" style="width:100px"></td>
		     	<td nowrap="nowrap" class="info_title_01">
				乘用人职位</td>
				<td nowrap="nowrap" class="info_content_00" ><input type="text" id="LARDER_POST" name="LARDER_POST" style="width:100px"></td>
		     	<td nowrap="nowrap" class="info_title_01">
				乘车人数</td>
				<td nowrap="nowrap" class="info_content_00" ><input style="width:100px" type="text" name="APPLY_USERSCOUNT" id="APPLY_USERSCOUNT" size="2" maxlength="3" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"></td>
		     	
			<!--  <td nowrap="nowrap" class="info_title_01">
				结束日期</td>	
				<td nowrap="nowrap" class="info_content_00"><input type="text" name="APPLY_ENDDATE" class="content" style="width:80px " value="<%=today %>" onClick="setday(this);" readonly></td>	      
		      <td nowrap="nowrap" class="info_title_01">
				结束时间</td>
				<td nowrap="nowrap" class="info_content_00">
				<input name="APPLY_ENDTIME" id="APPLY_ENDTIME" value="" type="text" style="width:35px" onblur="checkTimeValue(this);">&nbsp;(时间24小时制，如13:30)
				<ait:time name="APPLY_ENDTIME" spacing="30" />
				</td>-->
				
				
				 </tr>
				 <!--
				<tr>
				<td nowrap="nowrap" class="info_title_01">
				出发地</td>
				<td nowrap="nowrap" class="info_content_00" ><input type="text" id="DEPARTURES" name="DEPARTURES" value="" style="width:100px"></td>
			  <td nowrap="nowrap" class="info_title_01">
				目的地</td>
				<td nowrap="nowrap" class="info_content_00" ><input type="text" id="DESTINATIONS" name="DESTINATIONS" style="width:100px"></td>
				<td nowrap="nowrap" class="info_title_01">
				驾驶员</td>
				<td nowrap="nowrap" class="info_content_00" >
				<select name="DRIVERFLAG">
					<option value="0">需要</option>
					<option value="0">自驾</option>
				</select>
			  </td>
			  <td nowrap="nowrap" class="info_title_01">
				预计行程</td>
				<td nowrap="nowrap" class="info_content_00" ><input type="text" name="KILOMETER" style="width:35px" maxlength="4"  onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">&nbsp;公里</td>
			</tr>
			<tr>
			  <td nowrap="nowrap" class="info_title_01">
				用途/航班号</td>
				<td nowrap="nowrap" class="info_content_00" ><input type="text" name="PURPOSE" style="width:100px"></td>
			  <td nowrap="nowrap" class="info_title_01">
				目前车辆动态</td>
				<td align="center" class="info_content_00"><a href="#" onclick="view('${all.APPLY_NO }')"><font color="red"> 查看</font></a></td>
			       
		    </tr>		  
		-->
		 </table>
		
		<br>	
		<br>
		<table id = 'operateTable' width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">	
				<tr>
				<td align="left" class="title1" colspan="18">
					接送信息
				</td>
			</tr>	
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">区分</td>
				<td nowrap="nowrap" class="info_title_01">使用日期</td>
				<td nowrap="nowrap" class="info_title_01">出发时间</td>
				<td nowrap="nowrap" class="info_title_01">
				结束时间</td>
				<td nowrap="nowrap" class="info_title_01">业务内容</td>
				<td nowrap="nowrap" class="info_title_01">行车路线</td>
				<td nowrap="nowrap" class="info_title_01">备注（航班号/其他要求）</td>
				<!-- <td nowrap="nowrap" class="info_title_01">操作</td> -->
			</tr>
			
			<tr>	
				<td nowrap="nowrap" class="info_content_01">
					<select name="DISTINCTION0" id="DISTINCTION0">
					    <option value="">请选择</option>
				    		<option value="1">接</option>
				    		<option value="2">送</option>
			    	</select>
				</td>		
				<td nowrap="nowrap" class="info_content_01"><input type="text" name="APPLY_DATE0" id="APPLY_DATE0" class="content" style="width:80px "  onClick="setday(this);" readonly></td>
				<td nowrap="nowrap" class="info_content_01">
<!--				<ait:time name="APPLY_STARTTIME" spacing="30"/>-->
				<input name="APPLY_STARTTIME0" id="APPLY_STARTTIME0" value="" type="text" style="width:35px" onblur="checkTimeValue(this);">&nbsp; (时间24小时制，如13:30)</td>
				<td nowrap="nowrap" class="info_content_00">
				<input name="APPLY_ENDTIME0" id="APPLY_ENDTIME0" value="" type="text" style="width:35px" onblur="checkTimeValue(this);">&nbsp;(时间24小时制，如13:30)
				<!--<ait:time name="APPLY_ENDTIME" spacing="30" />-->
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<textarea name="CONTENT0" id="CONTENT0" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
					</td>
					
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="DRIVE_WAY0" id="DRIVE_WAY0" style="width:150px" value="" /></td>
				<!-- <td nowrap="nowrap" class="info_content_01">
			    <ait:codeClass name="visiterGiftYesOrNo0"	codeClass="YesOrNo" selected="${visiterEatryType}" all="all"/>
			    </td>		
					
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="GiftName0" style="width:150px" value="" /></td> 
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="GiftNumber0" style="width:150px" value="" /></td>-->
				<td nowrap="nowrap" class="info_content_01">
				<textarea name="memo0" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
					</td>
					
				<!-- 
				<td nowrap="nowrap" class="info_content_01">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow();" name="a1" id="a1">
				</td>
				 -->
			</tr>
			<tr>	
				<td nowrap="nowrap" class="info_content_01">
					<select name="DISTINCTION1" id="DISTINCTION1">
					    <option value="">请选择</option>
				    		<option value="1">接</option>
				    		<option value="2">送</option>
			    	</select>
				</td>		
				<td nowrap="nowrap" class="info_content_01"><input type="text" name="APPLY_DATE1" id="APPLY_DATE1" class="content" style="width:80px "  onClick="setday(this);" readonly></td>
				<td nowrap="nowrap" class="info_content_01">
<!--				<ait:time name="APPLY_STARTTIME" spacing="30"/>-->
				<input name="APPLY_STARTTIME1" id="APPLY_STARTTIME1" value="" type="text" style="width:35px" onblur="checkTimeValue(this);">&nbsp; (时间24小时制，如13:30)</td>
				<td nowrap="nowrap" class="info_content_00">
				<input name="APPLY_ENDTIME1" id="APPLY_ENDTIME1" value="" type="text" style="width:35px" onblur="checkTimeValue(this);">&nbsp;(时间24小时制，如13:30)
				<!--<ait:time name="APPLY_ENDTIME" spacing="30" />-->
				</td>
				<td nowrap="nowrap" class="info_content_01">
				<textarea name="CONTENT1" id="CONTENT1" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
					</td>
					
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="DRIVE_WAY1" id="DRIVE_WAY1" style="width:150px" value="" /></td>
				<!-- <td nowrap="nowrap" class="info_content_01">
			    <ait:codeClass name="visiterGiftYesOrNo0"	codeClass="YesOrNo" selected="${visiterEatryType}" all="all"/>
			    </td>		
					
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="GiftName0" style="width:150px" value="" /></td> 
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="GiftNumber0" style="width:150px" value="" /></td>-->
				<td nowrap="nowrap" class="info_content_01">
				<textarea name="memo1" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
					</td>
					
				<!-- 
				<td nowrap="nowrap" class="info_content_01">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow();" name="a1" id="a1">
				</td>
				 -->
			</tr>
			
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="10">		
				<c:forEach var="i" begin="1" end="6">
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
<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">   
</div>
</form>
</body>
</html>
