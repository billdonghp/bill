<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" 
	import="java.sql.*,java.util.*,
			com.ait.hrm.bean.*,
			com.ait.hrm.dao.common.*,           
			com.ait.util.StringUtil" %>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean class="com.ait.hrm.bean.InfoType" id="info_type"/>
<jsp:useBean class="com.ait.hrm.bean.InfoTable" id="info_table"/>
<jsp:useBean class="com.ait.hrm.bean.InfoField" id="info_field"/>
<jsp:useBean id="type_list" class="java.util.ArrayList" />
<jsp:useBean id="table_list" class="java.util.ArrayList" />
<jsp:useBean id="field_list" class="java.util.ArrayList" />
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<title></title>
<script   type="text/javascript">  
// src="/js/hr_general_search.js"
// JavaScript Document
var rs="";
var cols=3;
var conArray = new Object();
var sqlStr = "";
var table = new Array();

function addtable(table,addstr){
	var flag = true;
	try{
		for (var i = 0; i<table.length;i++){
			if (table[i]==addstr) flag=false;
		}
		if (flag) table[table.length] = addstr;
	} catch(e){};
	return table;
}
function addcondition(){
	var oRow, oCell,form;
	if (document.searchform.column.value!=""){       
	oRow=tbody.insertRow();
	oCell=oRow.insertCell();
	oRow.align="center";	
	form = document.searchform
	if(form.relax.value=='like' || form.relax.value=='not like')
	{
		rs='%'+form.values.value+'%';              
	}else if(form.relax.value=='in'){
		var ssstr = form.values.value;
		var sssstr = new Array();
		sssstr = ssstr.split(",");
		//alert(sssstr[0]);
		//alert(sssstr[1]);
		var sstr = "";
		for(var i=0; i < sssstr.length; i++){
			sstr += "'"+sssstr[i]+"',";
			//alert(sstr);
		}
 		
	
			rs='('+sstr.substring(0,sstr.length-1)+')';
		//alert(rs);
	}
	else{
		rs=form.values.value
	} 
	for(j=1;j<cols;j++){oCell=oRow.insertCell();}//eval("oCell.innerHTML=td0_"+j+".innerHTML");
	//oRow.cells(0).innerHTML="组"+form.group.value+" <input name='conditionGroup' type='hidden' value=\""+form.group.value+"\">" ;
	oRow.cells(0).innerHTML=form.condition[form.condition.selectedIndex].text
							+" <input name='relations' type='hidden' value=\""+form.condition.value+"\">";
	if(form.relax.value=='in'){
		oRow.cells(1).innerHTML=form.column[form.column.selectedIndex].text +" "
							+form.relax[form.relax.selectedIndex].text+" "
							+rs+""    
							+" <input type=hidden name = 'whereColumn' value=\""  
							+form.column.value+"\" >"
							+" <input type=hidden name = 'expStr' value=\""+"{"
							+form.column[form.column.selectedIndex].text +"} "
							+form.relax.value+" "+
							""+rs+"\" >";
	}else{
		oRow.cells(1).innerHTML=form.column[form.column.selectedIndex].text +" "
							+form.relax[form.relax.selectedIndex].text+" '"
							+rs+"'"    
							+" <input type=hidden name = 'whereColumn' value=\""  
							+form.column.value+"\" >"
							+" <input type=hidden name = 'expStr' value=\""+"{"
							+form.column[form.column.selectedIndex].text +"} "
							+form.relax.value+" "+
							"'"+rs+"'\" >";
	}
	
		if(form.column[form.column.selectedIndex].title=="" || form.column[form.column.selectedIndex].title==null){					
			conArray[form.column[form.column.selectedIndex].text] = form.column.value;
			//alert(conArray[form.column[form.column.selectedIndex].text]+"2222222");
		}else{
				conArray[form.column[form.column.selectedIndex].text] = (form.column.value+"_CODE");
				//alert(conArray[form.column[form.column.selectedIndex].text]+"11111111");

		}
		//alert("title  "+form.column[form.column.selectedIndex].title);
		if(form.column[form.column.selectedIndex].title=="VisaTypeCode")
			conArray[form.column[form.column.selectedIndex].text] = "VISA_TYPE_CODE";
			
		if(form.column[form.column.selectedIndex].title=="StatusCode")
			conArray[form.column[form.column.selectedIndex].text] = "STATUS_CODE";
			
		if(form.column[form.column.selectedIndex].title=="SexCode")
			conArray[form.column[form.column.selectedIndex].text] = "SEXCODE";
			
		if(form.column[form.column.selectedIndex].title=="PunCode")
			conArray[form.column[form.column.selectedIndex].text] = "PUN_TYPE_ID";
			
		if(form.column[form.column.selectedIndex].title=="EVEMPPERIOD")
			conArray[form.column[form.column.selectedIndex].text] = "ev_period_id";
			
		if(form.column[form.column.selectedIndex].title=="BankNameCode")
			conArray[form.column[form.column.selectedIndex].text] = "bank_name_code";	
			
		if(form.column[form.column.selectedIndex].title=="EVEMPGRADE")
			conArray[form.column[form.column.selectedIndex].text] = "ev_grade_id";		
			
		if(form.column[form.column.selectedIndex].title=="PostGroup")
			conArray[form.column[form.column.selectedIndex].text] = "POST_GROUP_ID";
			
		if(form.column[form.column.selectedIndex].title=="MaritalStatusCode")
			conArray[form.column[form.column.selectedIndex].text] = "MARITAL_STATUS_CODE";
			
		if(form.column[form.column.selectedIndex].title=="EVTYPE")
			conArray[form.column[form.column.selectedIndex].text] = "EV_TYPE_NAME";
			
		if(form.column[form.column.selectedIndex].title=="Department" && form.column[form.column.selectedIndex].text=="评价历史部门")
			conArray[form.column[form.column.selectedIndex].text] = "EV_DEPT_ID";
			
		if(form.column[form.column.selectedIndex].title=="Department" && form.column[form.column.selectedIndex].text=="汇总部门")
			conArray[form.column[form.column.selectedIndex].text] = "DEPARTMENT";
			
		if(form.column[form.column.selectedIndex].title=="Department" && form.column[form.column.selectedIndex].text=="部门")
			conArray[form.column[form.column.selectedIndex].text] = "DEPTID";
			
		if(form.column[form.column.selectedIndex].title=="DegreeCode" && form.column[form.column.selectedIndex].text=="学历")
			conArray[form.column[form.column.selectedIndex].text] = "DEGREE_CODE";
			
		if(form.column[form.column.selectedIndex].title=="DegreeCode" && form.column[form.column.selectedIndex].text=="最终学历")
			conArray[form.column[form.column.selectedIndex].text] = "FINAL_DEGREE";	
		//alert("text "+form.column[form.column.selectedIndex].text);
	}
	redraw();
}

function relaxlike()
{	
	if(form.relax.value=='like')
	{
		rs='%'+document.form.values.value+'%';
	}else{
		rs=doucment.form.values.value
	} 
}

function redraw()
{var nSum;

  for(i=0;i<tbody.rows.length;i++){//删除?
	tbody.rows(i).cells(cols-1).innerHTML="<input type=button name='btn"+i+"' value=- onClick=if(confirm('delete?')){tbody.deleteRow("+i+");redraw();} >";
	//tbody.rows(i).cells(0).innerHTML=(i+1)+"&nbsp;";
  }
  //document.MainForm.Sum.value=nSum;
}

function createSqlStr(){
	var form = document.searchform;
	var columns = document.searchform.columns
	if (columns&&columns.length){
	sqlStr = "select ";
	//alert(columns.length);
		for (var i = 0;i<columns.length;i++) {
		sqlStr +="\n" +columns.options[i].value +" as \""+ columns.options[i].text +"\",";
		table = addtable(table,columns.options[i].value.split(".")[0]);
		}
		sqlStr = sqlStr.substring(0,sqlStr.length-1)+"\n ";
	}
	//alert(sqlStr);
}
function createWhereStr(){
	var form = document.searchform;
	var whereStr="";
	var relation = form.relations;
	if (relation){
		if (relation.length){
			for(var i=0;i<relation.length;i++){
				whereStr +=relation[i].value+" "+ form.expStr[i].value+" \n" ;
				table = addtable(table,form.whereColumn[i].value.split(".")[0]);
			}
		whereStr =whereStr.substring(4,whereStr.length)
		}else{
		whereStr = form.expStr.value
		table = addtable(table,form.whereColumn.value.split(".")[0]);
		}
	}
	form.sql.value = whereStr;
	//alert(form.sql.value);
}
function Report(){
	var form = document.searchform;
	var tableStr="",fromSql="",whereSql="",order = ""; 
	var whereStr = replaceWhereStr(form.sql.value);   
	tableStr = table + "";                
	if (tableStr.length!=0){
		fromSql = " from "+tableStr;
		for(var i = 1; i<table.length;i++){
		whereSql +="\n"+table[0]+".EmpID = "+table[i]+".EmpID(+) and "
		}
	order  = " order by "+table[0]+".EMPID "
	}
	if (whereSql.length !=0 || whereStr.length !=0) whereSql = " where " + whereSql;
	if (whereStr.length == 0) {
		whereSql=whereSql.substr(0,whereSql.length-4);
	}else{
		whereStr =  "( " +whereStr+ " )";
		//alert(whereStr);
	}
	sqlStr = sqlStr + fromSql + whereSql + whereStr + order;
	//alert(sqlStr);
	if (sqlStr == ""){
	//"请将查询语句完整创建"	
	alert('<ait:message  messageID="alert.emp.statistics.create_full_search"  module="hrm"/>');
	return false;
	}
	else {  
	document.searchform.hole_sql.value=sqlStr;
	document.searchform.target="_blank";
	document.searchform.action="/hrm/hrmSearchResult.jsp";
	document.searchform.submit();
	}        
	 //alert(sqlStr);                          
}
function checkform(flag){
 var checkflag = false;
 if (flag) checkflag = flag
return  checkflag;
}  
function replaceWhereStr(str){
	//alert(str);
	var re = /{([\u4e00-\u9fa5\s]*)}/;
	var re1 = /{([A-Za-z\u4e00-\u9fa5]*)}/;
	var re2 = /{([a-zA-Z\s]*)}/;
	var re3 = /{([\da-zA-Z\s\.\-]*)}/;
	while (str.match(re)) {
		var reStr = str.match(re)[0];
		//alert(reStr.substr(1,reStr.length-2)+"     aaaa");
		//alert(conArray[reStr.substr(1,reStr.length-2)]+"  eee");
        str = str.replace(reStr, conArray[reStr.substr(1,reStr.length-2)]);//conArray["$1"]);
    }
    while (str.match(re1)) {
		var reStr = str.match(re1)[0];
		//alert(reStr.substr(1,reStr.length-2)+"     hhhhh");
		//alert(conArray[reStr.substr(1,reStr.length-2)]+"  kkkk");
        str = str.replace(reStr, conArray[reStr.substr(1,reStr.length-2)]);//conArray["$1"]);
    }
    while (str.match(re2)) {
		var reStr = str.match(re2)[0];
		//alert(reStr.substr(1,reStr.length-2)+"     hhhhh");
		//alert(conArray[reStr.substr(1,reStr.length-2)]+"  kkkk");
        str = str.replace(reStr, conArray[reStr.substr(1,reStr.length-2)]);//conArray["$1"]);
    }
    while (str.match(re3)) {
		var reStr = str.match(re3)[0];
		//alert(reStr.substr(1,reStr.length-2)+"     hhhhh");
		//alert(conArray[reStr.substr(1,reStr.length-2)]+"  kkkk");
        str = str.replace(reStr, conArray[reStr.substr(1,reStr.length-2)]);//conArray["$1"]);
    }
   	//alert("STR  "+str);
	return str;
}

var http_request = false;
		function markRequest(url){
		//alert("ddddddd");
			http_request = false;
			if(window.XMLHttpRequest){ //Mozilla, Safari,...
				http_request = new XMLHttpRequest();
				if(http_request.overrideMimeType){
					http_request.overrideMimeType("text/html");
				}
			}else if(window.ActiveXObject){ //IE
				try{
					http_request = new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e){
					try{
						http_request = new ActiveXObject("MicroSoft.XMLHTTP");
					}catch(e){}
				}
			}
			
			if(!http_request){
				alert("Giving up :( Cannot create an XMLHTTP instance");
				return false;
			}
			http_request.onreadystatechange = alertContents;
			http_request.open("GET",url,true);
			http_request.send(null);
		}
		
		function alertContents(){
			if(http_request.readyState == 4){
				if(http_request.status == 200){
					//alert(http_request.responseText);
					document.getElementById("cc").innerHTML = http_request.responseText;
					//alert(http_request.responseText);
				}else{
				//"无效的Reaquest."
					alert('<ait:message  messageID="display.mutual.request.invalid"/>');
				}
			}  
		}
		  
		function conditionitem(obj){
			//alert("obj"+obj);			
			//正在读取.....
			document.getElementById("cc").innerText = '<ait:message  messageID="display.mutual.processing"/>';
			markRequest("Condition.jsp?code="+obj);  
		}

</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
	 <c:import url="./inc/hrmgeneralsearchtoolbar.jsp" /> </td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>      
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<br>   
     <%@ include file="../inc/common_toolbar_n.jsp"%>        
       <br>   
<!--<div style=" width:754; text-align:right; height:26; vertical-align:middle; line-height:26">-->
<!-- <img src="/images/btn/search1.gif" width="56" height="18" vspace="4" align="absmiddle"  onClick="javascript:createSqlStr();Report();location=location">
 -->

<table height="100%" border="0" cellspacing="0" cellpadding="0" width="100%">         
  <form name="searchform" method="post" onSubmit="return checkform();">
    <tr>
     <td width="15%"  align="center"  class="info_title_01"><!-- 已选中项目-->
		<ait:message  messageID="display.emp.statistics.info_search.selected_items" module="hrm"/>
	<select name="columns" size="10" style="width:99%; height:95% ">
      </select></td>                     
	  <td width="100%" height="150">
	  <iframe src="hrmSearchSelect.jsp" scrolling="no" frameborder="0" height="100%" width="100%"></iframe>
	  </td>
      
    </tr>
    <tr valign="top">   
      <td colspan="2"><table width="100%" height="180"  border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="66%"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
            <tr align="center">
              <td width="43" height="24" align="center" class="info_title_01" nowrap="nowrap"> <!--关系-->
						<ait:message  messageID="display.mutual.relation"/>
						</td> 
              <td height="24" align="left" class="info_title_01">
                <select name="condition">
                  <option value="and" selected><!-- 与-->
						<ait:message  messageID="display.mutual.and"/></option>
                  <option value="or"><!-- 或-->
						<ait:message  messageID="display.mutual.or"/></option>
                </select><!-- 项目 -->
                    <ait:message  messageID="display.emp.statistics.info_search.item" module="hrm"/>
                    <select name="column" onchange="conditionitem(options[column.selectedIndex].title)">
      </select>
     <select name="relax"><!-- 等于 不等于 小于 大于 类似  不类似 在其中-->                         
        <option value="=" selected="selected" >
			<ait:message  messageID="display.mutual.equal_to"/>
		</option>
        <option value="<>">
			<ait:message  messageID="display.mutual.unequal_to"/>
		</option>
        <option value='<' >
			<ait:message  messageID="display.mutual.lesser_than"/>
		</option>
        <option value=">" >
			<ait:message  messageID="display.mutual.greater_than"/>
		</option>
        <option value="like" >
			<ait:message  messageID="display.mutual.like"/>
		</option>               
        <option value="not like" >
			<ait:message  messageID="display.mutual.unlike"/>
		</option>
        <option value="in" >
			<ait:message  messageID="display.mutual.in"/>
		</option>
      </select>
    
      <span id="cc">
      	<input name="values" type="text" onkeypress=" if (event.keycode==13) addcondition();" value="" class="topsearch">
      </span>        
       <input name="button"  type="Button"  align="right"  value='<ait:message  messageID="display.emp.statistics.enter" module="hrm"/>'  onclick="addcondition();"/></td>
            </tr>
            <tr valign="top">
              <td colspan="2" class="font1">
                <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
                  <tr align="center" bgcolor="F7F7F7">
                    <td width="10%" height="20" class="info_title_01"  align="left" nowrap="nowrap"><!--关系-->
						<ait:message  messageID="display.mutual.relation"/>            
					</td>
                    <td width="78%" height="20"  class="info_title_01"><!--条件-->
                    	<ait:message  messageID="display.emp.statistics.info_search.search_criteria" module="hrm"/>
                    </td>
                    <td class="info_title_01" class="info_title_01"><!--操作-->
                    	<ait:message  messageID="display.emp.statistics.info_search.operate_delete" module="hrm"/>
                    </td>
                  </tr>
                  <tbody id="tbody" class="info_title_01">                               
                  
                  </tbody>                 
                  <tr>
	                  <td width="10%" >&nbsp;</td>
	                  <td width="10%">&nbsp;</td>
	                  <td width="10%"> 
	                   <!--  <a href="javascript:createWhereStr();">生成条件 --> 
	                    <input type="button" onclick="javascript:createWhereStr();" value='<ait:message  messageID="display.emp.statistics.info_search.generate_criteria" module="hrm"/>' >
	                    <!-- </a>   -->  
	                   </td>
                  </tr>
              </table>
              </td>
            </tr>
          </table></td>
          <td width="30%" class="info_title_01"><textarea name="sql" style="width:100%;height:100%"></textarea></td>
        </tr>
      </table></td>
    </tr>
	<input type="hidden" name="hole_sql">
  </form>
</table>

	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="30">
			<tr>
				<td>&nbsp;</td>
			</tr>
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
</body>

</html>
