<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="employeeListForFlag" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>银行账号管理</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript"> 
function ImportExcel()
{
	document.form1.action = "/kpaControlServlet?operation=exportPayBankCode&menu_code=${param.menu_code}";
    document.form1.submit(); 
}
function Search(){
	document.form1.action="/kpaControlServlet?operation=PayBankCode&content=PayBankCodeForSearch&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update(){
	var selectC = document.getElementsByName("selectC") ;
	var size = selectC.length ;
	var selectCnt = 0 ;
	
	for(var i = 0 ; i < size ; i ++ )
	{
		if(selectC[i].checked)
		{
			selectCnt++ ;
		}
	}

	if(selectCnt == 0)
	{
		alert("请选择要修改的数据!!!") ;
		return ;	
	}
	document.form2.action="/kpaControlServlet?operation=PayBankCode&content=PayBankCodeForUpdate&menu_code=${param.menu_code}";
	document.form2.submit();
}
function checkAll()
{
  var selected = document.form2.ck_all.value == "0" ? true : false;
  var selectarg=document.getElementsByName("selectC");
  for(var i=0;i<selectarg.length;i++)
  {    
      selectarg[i].checked = selected;     
   
  }
  document.form2.ck_all.value = selected ? "1" : "0";
}
var time=null;
function SearchEmp(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft;
		  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
    	layeri.style.height = 48;
		layer.style.height = 48; 
    }else if(size<210){
		layeri.style.height = size;
		layer.style.height = size;  
    }else{
    	layeri.style.height = 210;
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText;
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('key').value=cell.childNodes[0].firstChild.nodeValue;
		$('name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
		layerClose();
}

</SCRIPT>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_Modify.jsp"%>
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
				银行账号管理
				</td>
			</tr>  
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>		 
		<form action="" name="form1" method="post">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >		
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	      <tr>
	      <input name="tableName" type="hidden" value="PA_HISTORY">
	      	<%--<td align="center" class="info_content_01" nowrap="nowrap">
	      		区分:&nbsp;
	        	<select name="tableName">
	        		<option value="PA_HISTORY" <c:if test="${tableName == 'PA_HISTORY'}">selected</c:if> >工资</option>
	        		<option value="PA_BONUS_HISTORY" <c:if test="${tableName == 'PA_BONUS_HISTORY'}">selected</c:if>>奖金</option>
	        	</select>	        
	        </td>
	        <td align="center" class="info_content_01" nowrap="nowrap">
	      		员工区分:&nbsp; <ait:codeClass codeClass="EmpDiffType" name="statTypeCode"  selected="${statTypeCode}" /> 
	        </td>
	        --%><td align="center" class="info_content_01" nowrap="nowrap">
	        	<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
	        </td>
		    <%--<td align="center" class="info_content_01" nowrap="nowrap">
			   
			   		奖金名称:&nbsp;<ait:codeClass codeClass="BonusType" name="bonusTypeCode" selected="${bonusTypeCode}" />
			   		&nbsp;&nbsp;&nbsp;
					<select name="bonusNo">
						<c:forEach var="i" begin="1" end="9" step="1">
							<option value="${i}" <c:if test="${i == bonusNo}">selected</c:if> >${i}</option>
						</c:forEach>
					</select>
			</td>
	        --%><td align="center" class="info_content_01" nowrap="nowrap">
				<!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>&nbsp;:&nbsp; 
			    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
			    <span id="name"></span>
			</td>
		    <td align="center" class="info_content_01" nowrap="nowrap">
				<ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
				<ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel();" style="cursor:hand"/>
		    </td>
	        </tr>
	      </table>	      
	      </td>
		</tr>
		</table>
		</form>
		<form action="" name="form2" method="post"> 
		<input name="ck_all" value="0" type="hidden"/>		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		<td nowrap="nowrap" class="info_title_01"><a href="#" onclick="checkAll();" style="color:red"><ait:message  messageID="alert.pay.all" module="pay"/></a></td>
		<td nowrap="nowrap" class="info_title_01"><ait:message  messageID="display.mutual.emp_id"/></td>
		<td nowrap="nowrap" class="info_title_01"><ait:message  messageID="display.mutual.name"/></td>
		<td nowrap="nowrap" class="info_title_01"><ait:message  messageID="display.mutual.dept"/></td>
		<td nowrap="nowrap" class="info_title_01"><ait:message  messageID="alert.pay.actualAmount" module="pay"/></td>
		<td nowrap="nowrap" class="info_title_01"><ait:message  messageID="alert.pay.bankAccount" module="pay"/></td>
		</tr>
		<%for(int i=0;i<employeeListForFlag.size();i++){ 
			dataMap=(SimpleMap)employeeListForFlag.get(i);%>		
		<tr align="center">
		<td nowrap="nowrap" align="center" class="info_content_01"><input type="checkbox" name="selectC" value="<%=i%>"/></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><%=dataMap.get("EMPID")%><input type="hidden" name="empid<%=i%>" value="<%=dataMap.get("EMPID")%>"></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><%=dataMap.get("CHINESENAME")%></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><%=dataMap.get("DEPARTMENT")%></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><%=dataMap.get("WAGE")%></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><input type="text" name="calcFlag<%=i%>" value="<%=dataMap.get("BANK_CARD_NO") %>"/></td>
		</tr>		
		<%} %>	   
		</table>
		</form>
		
		 <!-- Page Navigation Start-->
					<ait:page       
		               link="/paControlServlet"
		               parameters="&operation=PayBankCode&content=PayBankCodeForSearch&menu_code=${param.menu_code}&key=${key}&year=${year}&month=${month}&tableName=${tableName}&bonusTypeCode=${bonusTypeCode}&bonusNo=${bonusNo}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/> 
	  </td>     
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#D0D0FF" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
<iframe id='iemp' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:200;width:370; height:210; z-index:2;visibility: hidden;">   
</div>
</body>

</html>
