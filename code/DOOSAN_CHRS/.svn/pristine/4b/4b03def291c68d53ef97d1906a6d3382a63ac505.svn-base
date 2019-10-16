<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="employeeListForFlag" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<jsp:useBean id="postgradeYearList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="dataMap1" class="com.ait.sqlmap.util.SimpleMap"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>工资供给标识</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript"> 
function Search(){
 document.form1.action="/paControlServlet?operation=calculationFlag&content=PayCalculationSignsForSearch&menu_code=${param.menu_code}";
 document.form1.submit();
}
function Save(){
 if (confirm('是否确认保存') ){	
 document.form1.action="/paControlServlet?operation=calculationFlag&content=PayCalculationSignsForUpdate&menu_code=${param.menu_code}&deptID="+document.form1.deptID.value+"&key="+document.form1.key.value+"&person_id="+document.form1.person_id.value;
 document.form1.submit();
 }
}
function checkAll()
{
  var selected = document.form1.ck_all.value == "0" ? true : false;
  var selectarg=document.getElementsByName("selectC");
  for(var i=0;i<selectarg.length;i++)
  {    
      selectarg[i].checked = selected;     
   
  }
  document.form1.ck_all.value = selected ? "1" : "0";
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
     	var pars = "operation=paSearchEmployeeAll&condition=" + encodeURIComponent(condition);
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
    
	layer.innerHTML=XmlHttpRequest.responseText.replace("*","&");
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
		$('person_id').value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
}

function selectFlag(num){

	var selectCheckbox=document.getElementsByName("selectC");
	for(var i=0;i<selectCheckbox.length;i++){
	if(selectCheckbox[i].value==num){
	selectCheckbox[i].checked=true
	}
	
	}

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
			<%@ include file="../inc/toolbar_save_only.jsp"%>
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
		<br>	
		<form action="" name="form1" method="post">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >		
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	      <tr>
	      	<td class="info_title_01" width="10%">
	      		<!-- 部门 --><ait:message messageID="display.mutual.dept" />
	      	</td>
		    <td align="center" class="info_content_00" nowrap="nowrap">
			    <ait:selDept name="deptID" selected="${deptID}" all="all" supervisorType="pa"/>
			</td>
			<td class="info_title_01" width="10%">
				<!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>
			</td>
	        <td align="center" class="info_content_00" nowrap="nowrap">
				<input id="person_id" name="person_id" type="hidden" value="${person_id }"> 
			    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
			    <span id="name"></span>
			</td>
			<td class="info_title_01">区分</td>
			<c:if test="${cpnyId ne '59000000' }">
		        <td class="info_content_00" nowrap="nowrap">
		      		<ait:empDiff  name="statTypeCode"  cpnyDiff="<%= admin.getCpnyId() %>" selected="${statTypeCode}"/> 
		        </td>
		    </c:if>
		    <c:if test="${cpnyId eq '59000000' }">
		        <td class="info_content_00" nowrap="nowrap">
		      		<select name="joinType">
		      			<option value="">全部</option>
		      			<option value="C_12009_1330064" <c:if test="${joinType == 'C_12009_1330064' }">selected</c:if>>驻在员</option>
		      		</select>
		        </td>
		    </c:if>
			</tr>
			<tr>
			<td class="info_title_01" width="10%">
	      		计算标识
	      	</td>
		    <td align="center" class="info_content_00" nowrap="nowrap">
			    <select name="flag">
			    	<option value="Y" <c:if test="${flag == 'Y'}">selected</c:if> >Y</option>
			    	<option value="N" <c:if test="${flag == 'N'}">selected</c:if> >N</option>
			    </select>
			</td>
			<td class="info_title_01" width="10%">
	      		号俸年份 
	      	</td>
		    <td align="center" class="info_content_00" nowrap="nowrap">
			    <select name="year">
			    	<option value="">全部</option>
			    	<c:forEach items="${postgradeYearList}" var="item" varStatus="i">
			    		<c:if test="${year == item.POST_GRADE_YEAR}">
			    			<option value="${item.POST_GRADE_YEAR}" selected>${item.POST_GRADE_YEAR}</option>
			    		</c:if>
			    		<c:if test="${year != item.POST_GRADE_YEAR}">
			    			<option value="${item.POST_GRADE_YEAR}">${item.POST_GRADE_YEAR}</option>
			    		</c:if>
			    	</c:forEach>
			    </select>
			</td>
		    <td align="center" class="info_content_01" nowrap="nowrap" colspan="2">
				<ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
		    </td>
	        </tr>
	      </table>	      
	      </td>
		</tr>
		</table>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				工资计算标识
				</td>
			</tr>  
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>		 
		<input name="ck_all" value="0" type="hidden"/>		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		<td nowrap="nowrap" class="info_title_01"><a href="#" onclick="checkAll();" style="color:red">全选</a></td>
		<td nowrap="nowrap" class="info_title_01">职号</td>
		<td nowrap="nowrap" class="info_title_01">姓名</td>
		<td nowrap="nowrap" class="info_title_01">部门</td>
		<c:if test="${cpnyId eq '59000000'}">
		<td nowrap="nowrap" class="info_title_01">所属法人</td>
		</c:if>
		<td nowrap="nowrap" class="info_title_01">标识</td>
		<c:if test="${cpnyId eq '59000000' }">
			<td nowrap="nowrap" class="info_title_01">驻在员薪酬标识</td>
		</c:if>
		<td nowrap="nowrap" class="info_title_01">职级年份</td>
		</tr>
		<%for(int i=0;i<employeeListForFlag.size();i++){ 
			dataMap=(SimpleMap)employeeListForFlag.get(i);%>		
		<tr align="center">
		<td nowrap="nowrap" align="center" class="info_content_01">
		<input type="hidden" id="person_id<%=i%>" name="person_id<%=i%>" value="<%=dataMap.get("PERSON_ID")%>">
		<input type="checkbox" name="selectC" value="<%=i%>"/></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><%=dataMap.get("EMPID")%><input type="hidden" name="empid<%=i%>" value="<%=dataMap.get("EMPID")%>"></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><%=dataMap.get("CHINESENAME")%></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><%=dataMap.get("DEPTNAME")%></td>
		<c:if test="${cpnyId eq '59000000'}">
		<td nowrap="nowrap" align="center" class="info_content_01"><%=dataMap.get("CPNYNAME")%></td>
		</c:if>
		<td nowrap="nowrap" align="center" class="info_content_01">
			<select name="calcFlag<%=i%>" onchange="selectFlag(<%=i%>)">
				<option value="Y" <%if(dataMap.get("CALC_FLAG").equals("Y")){ %> selected="selected"<%} %>>Y</option>
				<option value="N"<%if(dataMap.get("CALC_FLAG").equals("N")){ %> selected="selected"<%} %> >N</option>
			</select>
		</td>
		<c:if test="${cpnyId eq '59000000'}">
			<td nowrap="nowrap" align="center" class="info_content_01">
			<select name="koCalcFlag<%=i%>" onchange="selectFlag(<%=i%>)">
				<option value="Y" <%if(dataMap.get("KO_CALC_FLAG").equals("Y")){ %> selected="selected"<%} %>>Y</option>
				<option value="N"<%if(dataMap.get("KO_CALC_FLAG").equals("N")){ %> selected="selected"<%} %> >N</option>
			</select>
		</td>
		</c:if>
		<td nowrap="nowrap" align="center" class="info_content_01">
		<select name="POST_GRADE_YEAR<%=i%>" onchange="selectFlag(<%=i%>)"> 		    	
				    	<%for(int s=0;s<postgradeYearList.size();s++){
				    		dataMap1=(SimpleMap)postgradeYearList.get(s);%>						
							<option value="<%=dataMap1.get("POST_GRADE_YEAR")%>" <%if(dataMap1.get("POST_GRADE_YEAR").equals(dataMap.get("POST_GRADE_YEAR"))){ %> selected<%} %> ><%=dataMap1.get("POST_GRADE_YEAR")%></option>
						<% }%>
		</select>
		</td>
		</tr>		
		<%} %>	   
		</table>
		</form>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(employeeListForFlag) < 6}">
				<c:forEach var="i" begin="1" end="${6-fn:length(employeeListForFlag)}"
					step="1">
					<tr>
						<td class="info_content_01" height="30"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
		 <!-- Page Navigation Start-->
					<ait:page       
		               link="/paControlServlet"
		               parameters="&operation=calculationFlag&content=PayCalculationSignsForSearch&menu_code=${param.menu_code}&deptID=${deptID}&key=${key}&flag=${flag}&year=${year}&statTypeCode=${statTypeCode}&joinType=${joinType }" 
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
<iframe id='iemp' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:200;width:370; height:210; z-index:2;visibility: hidden;">   
</div>
</body>

</html>
