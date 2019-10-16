<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.util.*,com.ait.sy.business.*,com.ait.sy.bean.*,com.ait.hrm.bean.*,java.util.*" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link href="/css/default1.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript">
var rowValue = "" ;
var colValue = "" ;
function checkedAll(temp){
	if(temp.checked){
		for(i=0;i<document.hr_item.column.length;i++){
			document.hr_item.column[i].checked=true;
		}
	}else{
		for(i=0;i<document.hr_item.column.length;i++){
			document.hr_item.column[i].checked=false;
		}
	}
}
function FillItem (flag) {
	if(flag == 0){
		getValueByItem(flag); 
		if(checkIsAll && !checkIsNotAll()){
			parent.document.all.item_row.value=rowValue;
		}else{
			parent.document.all.item_row.value="all";
		}
		parent.document.all.showsearch.style.visibility='hidden';
	}else{
		getValueByItem(flag); 
		if(checkIsAll && !checkIsNotAll()){
			parent.document.all.item_col.value=colValue;
		}else{
			parent.document.all.item_col.value="all";
		}
		parent.document.all.showsearch.style.visibility='hidden';
	}
}
function checkIsAll(){
	for(i=0;i<document.hr_item.column.length;i++){
		if(!document.hr_item.column[i].checked){
			return false ;
		}
	}
	return true ;
}
function checkIsNotAll(){
	var temp = true ; 
	for(i=0;i<document.hr_item.column.length;i++){
		if(document.hr_item.column[i].checked){
			temp = false ;
		}
	}
	return temp ;
}
function getValueByItem(flag){
	for(i=0;i<document.hr_item.column.length;i++){
		if(document.hr_item.column[i].checked){
			if(flag == 0){
				if(rowValue != ""){
					rowValue += ",";
				}
				rowValue += document.hr_item.column[i].value;
			}else{
				if(colValue != ""){
					colValue += ",";
				}
				colValue += document.hr_item.column[i].value;
			}
		}
	}
}
</SCRIPT>
<%
admin = (AdminBean)session.getAttribute("admin");
List codeList = null ;
String item = request.getParameter("item");
String flag = request.getParameter("flag");//横纵向标志

if(item.equals("post_grade_id"))  
	codeList = SysCodeParser.getPostGradeCode();
else if(item.equals("post_group_id"))
	codeList = SysCodeParser.getPostGroupCode();
else if(item.equals("post_code"))
	codeList = SysCodeParser.getPostCode();    
else
	codeList = SysCodeParser.getSysCode(item);  
%>  
<body style="margin-left:0px; margin-top:0px;">
<form name="hr_item">
<!-- 全选 --><ait:message  messageID="display.mutual.select_2"/>
<input type="checkbox" name="checkbox" value="checkbox" onclick="checkedAll(this)" class="check">
<!-- 确定 -->
<input type="button" name="button" value='<ait:message  messageID="display.emp.statistics.enter"  module="hrm"/>' onclick="FillItem(<%=flag %>)"><br>
<table width="350" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#99ddcc" style="padding: 2px 2px 2px 2px;">
<tr>
<%for(int i = 0 ; i < codeList.size(); i ++){ 
	SysCodeBean codeBean = (SysCodeBean)codeList.get(i);
%>
<td>
	<input type="checkbox" name="column" value="<%=codeBean.getCodeId() %>" class=check>
	<%if(admin.getLanguagePreference().equals("zh")){%>
		<%=codeBean.getCodeName() %>
	<%}else if(admin.getLanguagePreference().equals("ko")){%>
		<%=codeBean.getKorCodeName() %>
	<%}else{%>
		<%=codeBean.getEnCodeName() %>
	<%}%>
</td>
<%if((i+1)%5==0){ %>
</tr><tr  height="30" align="left">
<%}} %>
</form>
</table>
</body>
</html>