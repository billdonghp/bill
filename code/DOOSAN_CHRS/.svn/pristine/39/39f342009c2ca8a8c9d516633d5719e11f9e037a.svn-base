<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<%@ page
	import="com.ait.utils.FormUtil,com.ibm.icu.text.SimpleDateFormat"%>
<%@ page
	import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date,com.ait.sysparam.*"%>
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList"
	scope="page" />
<jsp:useBean id="documentno" class="java.lang.String" scope="request" />
<jsp:useBean id="isLend" class="java.lang.String" scope="request" />
<jsp:useBean id="list"  class="java.util.ArrayList" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>签证申请</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function CheckNumberLength(){
 var text=document.form1.useInformation.value.replace(/[^\x00-\xff]/g,"**");
 if(text.length>500){
 return true;
 }else{
 return false;
 }
}
function CheckNumberLength1(){
 var text=document.form1.note.value.replace(/[^\x00-\xff]/g,"**");
 if(text.length>500){
 return true;
 }else{
 return false;
 }
}
function Save() {   
	   
	   if(document.form1.useInformation.value==""||document.form1.useInformation.value==null){
			alert("出差内容不能为空！");
			return;
		   };
	   if(document.form1.selectDeptid.value==""||document.form1.selectDeptid.value==null){
			   alert("前往国家不能为空！");
	   }else if(document.form1.usefDate.value==""||document.form1.usefDate.value==null){
	   alert("出差日期不能为空！");
	   }else if(document.form1.affirmor.value==""||document.form1.affirmor.value==null){
		 alert("没有设置决裁者！请设置");
	   }else if(CheckNumberLength()){
	   alert("使用内容在250个汉字以内！");
	   }else if(CheckNumberLength1()){
	   alert("备注在250个汉字以内！");
	   }
	   else{
		   //alert("1");
		   var affirmorIdArr="";
		   var affirmorDutyArr="";
		   var affirmorId = document.getElementsByName("affirmorId") ;
		   var affirmorDuty = document.getElementsByName("affirmorDuty") ;
		   	for (var i = 0; i< affirmorId.length ; i++){
		   		affirmorIdArr +=affirmorId[i].value+",";	
		   		affirmorDutyArr +=affirmorDuty[i].value+",";	
		 	}
		   $('affirmorIdArr').value=affirmorIdArr.substr(0,affirmorIdArr.length-1);
		   $('affirmorDutyArr').value=affirmorDutyArr.substr(0,affirmorDutyArr.length-1);
	    document.form1.encoding="multipart/form-data";
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=visaManger&content=addSealApply";
		document.form1.submit();
		document.getElementById("applysaveid").style.display="none";//避免重复提交，隐藏按钮
	   }
	   
	}

 function   CheckNumber(tempvalue)       {   
    var   patrn=/^[0-9]{0,3}$/;
    if  (!patrn.test(tempvalue)){         
       alert("输入1-3位数字");         
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
function seal(s){
    ///alert(s.value);
    if((s.value == 'SealType_Code05')||(s.value == 'SealType_Code06')||(s.value == 'SealType_Code09')||(s.value == 'SealType_Code10')||
    	(s.value == 'SealType_Code13')||(s.value == 'SealType_Code14')||(s.value == 'SealType_Code17')||(s.value == 'SealType_Code18')){
    	$('returnDateDiv').innerHTML = "<input type='text' name='returnDate' class='content' style='width:80px'   readonly onClick='setday(this);'>" ;
    }else{
    	$('returnDateDiv').innerHTML = "";
    }
}

function   changeSealType()       {   
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=sealManger&content=sealApplyPage";
	document.form1.submit();
  } 

function companyChange(){
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=sealManger&content=sealApplyPage";
		document.form1.submit();	
	}  
</SCRIPT>

<body>
<%
	Date d = new Date();
	SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
	String today = timeFormatter.format(d);
	String seViewDate = (String)request.getAttribute("seViewDate");
	String sealType = "";
	String useVisatype = "";
%>
<form name="form1" method="post" action="">
<input type="hidden" name="isView" value="">
<input type="hidden" name="affirmorIdArr" value="">
<input type="hidden" name="affirmorDutyArr" value="">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/toolbar_apply.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">签证申请
					<font color="red" size="2"><!--${declaration}-->
					（韩国签证普通10个工作日完成，加急2个工作日完成。其余国家签证办理时长请与管理部担当于昊平联系。因办理加急签证产生的差旅费由申请部门承担）
				</font>
				</td>
			</tr>
		</table>
		<%
			if (!errorMsg.equals("")) {
		%>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td align="center"><font color="red"><%=errorMsg%></font></td>
			</tr>
		</table>
		<%
			}
		%>

		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">

				  <td nowrap="nowrap" class="info_title_01">申请人法人 </td>
				<td nowrap="nowrap" class="info_content_00" align="left">
				<ait:codeClass codeClass="EMP_DIFF" name="companyId" selected="${companyId}" />
	      		</td>
				<td nowrap="nowrap" class="info_title_01">姓名</td>
				<td nowrap="nowrap" class="info_content_00"><%=admin.getChineseName()%></td>
				<td nowrap="nowrap" class="info_title_01">部门</td>
				<td nowrap="nowrap" class="info_content_00"><%=admin.getDepartment()%></td>
			</tr>
			<tr>
			  
			<td nowrap="nowrap" class="info_title_01">前往国家</td>
				<td nowrap="nowrap" class="info_content_00"><input
					name="selectDeptid" type="text"></td>
				<td nowrap="nowrap" class="info_title_01">签证类型</td>
				<td nowrap="nowrap" align="center" class="info_content_00">
					<select name="useVisatype" >
						<option value="0" <%if (useVisatype.equals("0")) {%> selected <%}%>>单次</option>
						<option value="1" <%if (useVisatype.equals("1")) {%> selected <%}%>>多次</option>
					</select>
				</td>
				<td colspan='2'><font color="red">*签证类型请根据各部门业务需求申请</font></td>
			</tr>
			<tr>
				
				<td nowrap="nowrap" class="info_title_01">出差内容(250个汉字以内)</td>
				<td nowrap="nowrap" class="info_content_00"><textarea
					name="useInformation" style="height: 20px; width: 150px"
					type="_moz" onfocus="this.style.height='40px'"
					onblur="this.style.height='20px';"></textarea></td>
				
				
				<td nowrap="nowrap" class="info_title_01">紧急度</td>
				<td nowrap="nowrap" align="center" class="info_content_00">
					<select name="isLend" >
						<option value="0" <%if (isLend.equals("0")) {%> selected <%}%>>普通</option>
						<option value="1" <%if (isLend.equals("1")) {%> selected <%}%>>加急</option>
					</select>
				</td>
				</tr>
				<tr>
				<!--<td colspan='6'><font  color="red">
				
			       *****韩国签证普通10个工作日完成，加急2个工作日完成。其余国家签证办理时长请与管理部担当于昊平联系。</font>
				<font  color="red">*因办理加急签证产生的差旅费由申请部门承担</font></td>-->
			</tr>
			
			<tr>
				<td nowrap="nowrap" class="info_title_01">出差日期</td>
				<td nowrap="nowrap" class="info_content_00">
				<input type="text"	name="usefDate" class="content" style="width: 80px"
					value="<%=today%>" readonly onClick="setday(this);">至
				<input type="text"	name="usetDate" class="content" style="width: 80px"
					value="<%=today%>" readonly onClick="setday(this);">
				</td>
				
				<td nowrap="nowrap" align="center" class="info_title_01">
				备注(250个汉字以内)</td>
				<td nowrap="nowrap" class="info_content_00" colspan="1"><textarea
					name="note" style="height: 20px; width: 150px" type="_moz"
					onfocus="this.style.height='40px'"
					onblur="this.style.height='20px';"></textarea></td>				
				
				<td nowrap="nowrap" class="info_title_01">决裁者</td>
				<td nowrap="nowrap" align="center" class="info_content_00">
				<%
					
				%>
				<%
					if (!list.isEmpty()) {
				%> <input type="hidden" value="<%=list.size()%>"
					name="affirmor">
				<table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">

				      <%int affirmLevel=10;
				      
				      String afid = "1234567";
				      
				      for(int i=0;i<list.size();i++){
				      gaAffirmList=(GaAffirmList)list.get(i);
				      
				      if(afid.equals(gaAffirmList.getAffirmorId())){
				    	  continue;
				      }
				      
				      afid = gaAffirmList.getAffirmorId();
				      
				      %> 
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
				<%
					} else {
				%> <input type="hidden" value="" name="affirmor">
				<table>
					<tr>
						<td><font color="red">没有决裁者</font></td>
					</tr>
				</table>
				<%
					}
				%>
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
</html>
