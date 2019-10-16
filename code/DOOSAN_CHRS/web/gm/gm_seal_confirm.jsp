<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>公章确认</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function search(){
document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=sealManagement&content=sealConfirm";
document.form1.submit();
}

function band(backColor,textColor,i,modifyFlag)
{
	var t;
	if(typeof(preEl)!='undefined')
	{
	preEl.bgColor=orgBColor;

	try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
	}
	var el = event.srcElement;
	el = el.parentElement;
	orgBColor = el.bgColor;
	orgTColor = el.style.color;
	el.bgColor=backColor;
	try{ChangeTextColor(el,textColor);}catch(e){;}
	preEl = el;
	document.form1.temp.value=i;
	document.form1.okFlag.value = modifyFlag;
} 

function checkAll()
{
    var selected = document.form1.ck_all.value == "0" ? true : false;
    var affirmno = document.getElementsByName("affirmno") ;
  	for (var i = 0; i< affirmno.length ; i++){
		affirmno[i].checked = selected ;
	
	}
    document.form1.ck_all.value = selected ? "1" : "0";
}

function Confirm(){
	var msg1='没有要通过的记录。';
	var msg2='没有要否决的记录。';
	var msg3='请选择要通过的记录。';
	var msg4='请选择要否决的记录。';
	var msg5='你确定要通过当前已选择的记录？';
	var msg6='你确定要否决当前已选择的记录？';

	var applyno = document.getElementsByName("affirmno");
	if (applyno == null || applyno.length == null || applyno.length == 0){
	  	alert(msg1);
		return false;
	}
	var c = 0;
	for (var i=0; i<applyno.length; i++){
		if(applyno[i].checked == true){
			c++;
			break ;
		}
	}
	if (c==0){
	  	alert(msg3);
		return false;
	}                                       
    if (confirm(msg5)) {
		document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=sealManagement&content=oingConfirm";
		document.form1.submit();
	}
}

function doAffirm(DOCUMENTNO){
if(confirm('确定进行确认？')){
	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=sealManagement&content=oingConfirm&affirmno="+DOCUMENTNO;
	document.form1.submit();
	}
}

function ImportExcel() {
	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=sealManagement&content=sealConfirmExce";
	document.form1.submit();

}

 function ImpExcel(applyno){
	document.form1.action="/xlsReportServlet?operation=crystal&xlsKey=seal_affirmInfo&applyno="+applyno;
	document.form1.submit();
}
function viewFile(fileAddress,documentno){
window.open(fileAddress,"","menubar=no, toolbar=no, scrollbars=no, status=no, resizable=yes, location=no, directories=no, copyhistory=no" );
}
</SCRIPT>

<body>
	<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/gm_toolbar_confirm.jsp"%>
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
			<!-- display basic info -->
		<br>
		
		<input type="hidden"
	name="okFlag" value="0" /> <input type="hidden" name="okFlag1" value="" />
<input type="hidden" name="ck_all" value="0" />
<input type="hidden" name="countryTemp" value="" /> <input type="hidden"
	name="temp" value="" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">查询条件</td>
			</tr>
		</table>
		<table width="100%" height="30" border="0" cellpadding="0"
			cellspacing="1" class="dr_d">
			<tr>
				<td  class="info_title_01" nowrap="nowrap"><!-- 开始日期 -->
		    <ait:message messageID="display.mutual.start_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00" nowrap="nowrap">
		          <input type="text" name="startDate" style="width:70px" value="${startDate}" onClick="setday(this);" /></td>
		          <td  class="info_title_01"><!--  结束日期-->
		  <ait:message messageID="display.mutual.end_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00">
		          <input type="text" name="endDate" style="width:70px" value="${endDate}" onClick="setday(this);" /></td>  
				<td class="info_title_01" nowrap="nowrap">状态</td>
				<td class="info_content_00" nowrap="nowrap">
					<select name="qryType">
				         <option value="3">全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未确认</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已确认</option>
				     </select>				     
					<img src="../images/btn/Search.gif" style="cursor: pointer;"
					onclick="search();">
					<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;"
					onclick="ImportExcel();" />
				</td>
			</tr>
			<tr>
				<td  class="info_title_01">法人区分</td>
		         <td  class="info_content_00"><ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="search();"/></td> 
				<td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <ait:selDeptByCpnyId name="deptid" supervisorType="pa" all="all" cpnyId ="${cpnyId}" selected="${deptid}"/>
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}"  title="输入姓名或者职号"/></td>   
			</tr>
		</table>
	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				公章确认
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
		      <td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;"><!-- 全选 --> 
		       	<ait:message messageID="display.mutual.select_2" module="ess" /></a></td>
		      <td nowrap="nowrap" class="info_title_01">
				编号</td>
			  <td nowrap="nowrap" class="info_title_01">
				姓名</td>
			  <td nowrap="nowrap" class="info_title_01">
				部门</td>
		      <td nowrap="nowrap" class="info_title_01">
				日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				起案号</td>
			  <td nowrap="nowrap" class="info_title_01">
				使用章</td>
		      <td nowrap="nowrap" class="info_title_01">
				盖章文件的接收单位</td>
		      <td nowrap="nowrap" class="info_title_01">
				外借</td>
			  <td nowrap="nowrap" class="info_title_01">
				使用内容</td>	
		      <td nowrap="nowrap" class="info_title_01">
				份数 </td>
			  <td nowrap="nowrap" class="info_title_01">
				上传文件 </td>
			  <td nowrap="nowrap" class="info_title_01">
				归还日期 </td>							 	    
			  <td nowrap="nowrap" align="center" class="info_title_01">
				备注</td>
			  <td nowrap="nowrap" align="center" class="info_title_01">
				确认</td>	
			  <td nowrap="nowrap" class="info_title_01">
				意见</td>
			  <td nowrap="nowrap" class="info_title_01">
				导出申请书</td>		 		
		    </tr>			
		 <c:forEach items="${applyList}" var="varTest" varStatus="i">
		    <tr align="center">
		      <tr
				onClick="band('#E7F0EF','black','${varTest.DOCUMENTNO}','${varTest.ISCONFIRM}')"
				align="center">
				<td nowrap="nowrap" align="center" class="info_content_01" >
		      	&nbsp;<c:if test="${varTest.ISCONFIRM == '0'}">
		      	 <input type="checkbox" name="affirmno" value="${varTest.DOCUMENTNO}" />
		      	</c:if>&nbsp;
			  </td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.DOCUMENTNO}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.CHINESENAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.APPLYDEPTNAME}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.USEDATE}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.DRAFT_NO}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CODE_NAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.DEPTNAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.ISLEND}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.USEINFORMATION}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.USESHARES}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >
		        <c:if test="${varTest.FILEPATH!=null}">
	             <a href="#" onclick="viewFile('${varTest.FILEPATH}','${varTest.DOCUMENTNO}');"><font color="red">${varTest.FILENAME}</font></a>
	            </c:if>	 
	            <c:if test="${varTest.FILEPATH==null}">
	            &nbsp;
	            </c:if>  
	          </td>
	          <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.RETURNDATE}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.NOTE}</td>	
		      <td nowrap="nowrap" align="center" class="info_content_01" >
		      <c:if test="${varTest.ISCONFIRM==1}"><font color="#00CC00">已确认</font></c:if>
		      <c:if test="${varTest.ISCONFIRM==0}"><a href="#" onclick="doAffirm('${varTest.DOCUMENTNO}')" style="color:red;">确认</a></c:if>
		      </td> 
		      <td nowrap="nowrap"  align="center" class="info_content_00"><textarea name="affirmorIdea_${varTest.DOCUMENTNO}" style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${varTest.CONFIRMIDEA}</textarea></td>      
			 <td nowrap="nowrap" class="info_content_01">
	      		<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImpExcel('${varTest.DOCUMENTNO}');"/>
	      </td>     
			 </tr>	
			 
		</c:forEach>		
		 </table>
		 		
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=sealManagement&content=sealConfirm&menu_code=${param.menu_code}&documentno=${documentno}&startDate=${startDate}&endDate=${endDate}&serchDept=${serchDept}&qryType=${qryType}" 
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
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</form>
</body>

</html>
