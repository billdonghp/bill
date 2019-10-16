<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" 
	import="java.sql.*,java.util.*,
			com.ait.hrm.bean.*,
			com.ait.hrm.dao.common.*,
			com.ait.util.StringUtil,com.ait.i18n.MessageSource,com.ait.sy.bean.AdminBean" %>
<%@ include file="/inc/taglibs.jsp"%>
<jsp:useBean class="com.ait.hrm.bean.InfoType" id="info_type"/>
<jsp:useBean class="com.ait.hrm.bean.InfoTable" id="info_table"/>
<jsp:useBean class="com.ait.hrm.bean.InfoField" id="info_field"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>


<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<body>         
<style>
.tb{table-layout:fixed}
</style>
<script language="JavaScript" type="text/JavaScript">
function changeTypeId(){
	document.searchform.tableId.options.length=0;
	document.searchform.submit();	
}
function check(i) {
	var target = parent.document.searchform.columns;
	if (document.searchform.fieldId.length){
		document.searchform.fieldName[i].checked = document.searchform.fieldId[i].checked
		var check = document.searchform.fieldId[i].checked;
		var text = document.searchform.fieldName[i].value;
		var value = document.searchform.fieldId[i].value
	}
	else {
		document.searchform.fieldName.checked = document.searchform.fieldId.checked;
		var check = document.searchform.fieldId.checked;
		var text = document.searchform.fieldName.value;
		var value = document.searchform.fieldId.value
	}
	if (check) {
	target.options[target.length] = new Option(text,value);
	}
	else{
	target.value = value;
	target.options[target.selectedIndex] = null;
	}
}
function data(id,name,code){
	this.id =id ;
	this.name =name;
	this.code = code;
	return this;
}

function onloadCheck(){
	var target = parent.document.searchform.columns;
	var columns = new Array();
	if (target.length){
		for (var i = 0;i<target.length;i++){
		columns[columns.length] = new data(target.options[i].value,target.options[i].text,target.options[i].title);		
		}
		if (document.searchform.fieldId.length){
			for (var i = 0;i<document.searchform.fieldId.length;i++){
				for(var j=0;j<columns.length;j++){
					if (document.searchform.fieldId[i].value==columns[j].id){
						document.searchform.fieldId[i].checked=true;
						document.searchform.fieldName[i].checked=true;
						columns.splice(j,1);
						break;
					}
				}
			}
		} else{
			for(var j=0;j<columns.length;j++){
				if (document.searchform.fieldId.value==columns[j].id){
					document.searchform.fieldId.checked=true;
					document.searchform.fieldName.checked=true;
					columns.splice(j,1);
					break;
				}
			}
		}
	}
}
function getColumn(){
	var target = document.searchform.fieldId;
	var allcolumns = new Array();
	if (target){
		if (target.length) {
			for( var i=0;i<target.length;i++){	
			allcolumns[allcolumns.length] = new data(document.searchform.fieldId[i].value,document.searchform.fieldName[i].value,document.searchform.fieldCode[i].value);		
			}
		}else{
			allcolumns[allcolumns.length] = new data(document.searchform.fieldId.value,document.searchform.fieldName.value,document.searchform.fieldCode.value);		
		}
	}
	return allcolumns;
}
function setColumn(columns){
	var target = parent.document.searchform.column;
	if (target.length) 	target.length = 0;
	var allcolumns = new Array();
	allcolumns = columns;
	if (columns==null)  allcolumns = getColumn();
	for (var i=0;i<allcolumns.length;i++){
		var ot = new Option(allcolumns[i].name,allcolumns[i].id);
		ot.title = allcolumns[i].code;
		target.options[target.length] = ot;
		//alert(allcolumns[i].name+"--"+allcolumns[i].id);
	}
}

</script>   
<%@ include file="hrmSearchOperation.jsp" %>
<span style="overflow-y:auto;height:expression((document.body.clientHeight-this.offsetTop>this.children[0].offsetHeight)?(this.children[0].offsetHeight) : (document.body.clientHeight-this.offsetTop)); width:expression(document.body.clientWidth);">
<table width="100%" height="400" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style=" ">
<form name="searchform" method="post" action="">
<tr align="center"> 
<td width="450" bgcolor="F7F7F7" class="info_title_01"><!-- 搜索类别选择-->
                    <ait:message  messageID="display.emp.statistics.info_search.select_type" module="hrm"/>
                    </td>
<td width="30%" height="24" class="font1"><select name="typeId" onChange="changeTypeId();">
  <option value=""><!-- 请选择-->
    <ait:message  messageID="display.emp.statistic.select" module="hrm"/></option>
  <% 
  request.setAttribute("fieldlist",field_list);
  HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session.getAttribute("admin");
  for (int i = 0 ;i<type_list.size();i++){
  info_type = (InfoType)type_list.get(i);
  %><option value="<%=info_type.getTypeId()%>" 
  <%if (info_type.getTypeId().equals(typeId)) out.print("selected");%>>
  <%if(admin1.getLanguagePreference().equals("zh")){%>
	  	<%=info_type.getTypeName()%>
	<%}else if(admin1.getLanguagePreference().equals("ko")){%>
		<%=info_type.getKorTypeName()%>
	<%} else{%>
		<%=info_type.getEnTypeName()%>
	<%}%>
  
  </option>
  <%}%>
</select></td>
<td width="600" bgcolor="F7F7F7" class="info_title_01"><!--项目选择-->
                    <ait:message  messageID="display.emp.statistics.info_search.select_item" module="hrm"/>
                    </td>
<td width="30%" class="font1"><select name="tableId" onChange="document.searchform.submit()">
  <option value=""><!-- 请选择-->
    <ait:message  messageID="display.emp.statistic.select" module="hrm"/></option>
  <% for (int i = 0 ;i<table_list.size();i++){
  info_table = (InfoTable)table_list.get(i);
  %>         
  <option value="<%=info_table.getTableId()%>" 
  <%if (info_table.getTableId().equals(tableId)) out.print("selected");%>>
    <%if(admin1.getLanguagePreference().equals("zh")){%>
	  	<%=info_table.getTableName()%>
	<%}else if(admin1.getLanguagePreference().equals("ko")){%>
		<%=info_table.getKorTableName()%>
	<%} else{%>
		<%=info_table.getEnTableName()%>
	<%}%>
  
  </option>
  <%}%>
</select></td>
</tr>
<tr align="left" valign="top">
  <td colspan="4" class="font1" style="padding:5 5 5 5 ">
 <%int i=0; %>
 	<table width="100%">
 	<tr>
	  <c:forEach items="${fieldlist}" var="field_list"> 
		  <input type="checkbox" name="fieldName" value='<ait:content enContent="${field_list.enFieldName}" zhContent="${field_list.fieldName}" koContent="${field_list.korFieldName}"/>' class="check"   style="display:none">
		  <input type="checkbox" name="fieldCode" value="${field_list.fieldCode}" class="check"   style="display:none">
		  <input type="checkbox" name="codeName" value="${field_list.codeName}" class="check"   style="display:none">
		  <td nowrap="nowrap">
			  <input type="checkbox" name="fieldId" value="${field_list.tableId}.<ait:content enContent='${field_list.enFieldId}' zhContent='${field_list.fieldId}'/>" class="check" onClick="check(<%=i %>)">
			  <ait:content enContent="${field_list.enFieldName}" zhContent="${field_list.fieldName}" koContent="${field_list.korFieldName}"/>
			  
			  <%if((i+1)%4==0){%>
				  </tr><tr>
			  <%} %>  
			  </td>	
		  
		  <%i++; %>              
	  </c:forEach>
	  </tr> 
  </table>
 </td>
  </tr>
</form>
</table>
</span>
<script language="javascript" >
if (0 !=  <%=field_list.size()%>) onloadCheck();setColumn();
</script>
</body>
</html>
