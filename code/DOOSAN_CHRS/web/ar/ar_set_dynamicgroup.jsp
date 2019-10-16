<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ar.bean.*,com.ait.util.*,java.util.*" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="dynamicGroupList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="dynamicGroup" class="com.ait.ar.bean.DynamicGroup"/>
<jsp:useBean id="selectGroup" class="com.ait.ar.bean.DynamicGroup" scope="request"/>
<jsp:useBean id="groupConditionList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="groupCondition" class="com.ait.ar.bean.GroupCondition"/>
<jsp:useBean id="conditionList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="condition" class="com.ait.ar.bean.Condition" />
<jsp:useBean id="operationStatus" class="com.ait.util.OperationStatus" scope="request" />
<%
	String groupNo = StringUtil.checkNull(request.getParameter("groupNo"),"empty");
	request.setAttribute("groupNo", groupNo);
	String	groupProperty = StringUtil.checkNull(selectGroup.getGroupProperty());	
	//out.println(groupNo);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title></title>
<script language="javascript">
	var group_no = '<%=groupNo%>';
	var groupProperty = '<%=groupProperty%>';
	// 添加一个新的动态组
	function Add(){
		location.href ='arControlServlet?operation=ar_pagecontrol&page=dynamicGroupAdd&menu_code=<%=StringUtil.checkNull(request.getParameter("menu_code"))%>';
	}
	//删除一个动态组
	function Delete(){
		//alert(group_no);
		//alert(groupProperty);
		//未选定组！请在左边组列表中选择组。
		if(group_no == "empty"){
			alert('<ait:message  messageID="alert.att.maintenance.pattern.group_left_column" module="ar"/>');
		}
		else if(groupProperty == "SysGroup"){			
			alert("System group can not be deleted!");
		}
		else{//"动态组一经删除，其所关联的所有条件将被同时删除！\n\n 确定要删除吗？"
			if(confirm('<ait:message  messageID="alert.att.maintenance.pattern.delete_pattern" module="ar"/>')){
				location.href ='arControlServlet?operation=ar_delete&content=dynamicGroup&menu_code=<%=StringUtil.checkNull(request.getParameter("menu_code"))%>&groupNo='+group_no;
			}
		}
	}
	function createRelation(code,name){
	    this.code =code;
		this.name =name;
		return this;
	}

	var allRelax = new Array()
	allRelax[allRelax.length] = new createRelation("=","=")
	allRelax[allRelax.length] = new createRelation("<","<")
	allRelax[allRelax.length] = new createRelation(">",">")
	allRelax[allRelax.length] = new createRelation("<>","<>")
	allRelax[allRelax.length] = new createRelation("in","IN")
	allRelax[allRelax.length] = new createRelation("not in","NOT IN")
	allRelax[allRelax.length] = new createRelation("between","BETWEEN")
	function getRelax(code){
	var result = "";
		for (var i = 0;i<allRelax.length;i++){
			if (allRelax[i].code == code) result = allRelax[i].name; 
		}
		return result;
	}

	function deleteCondition(conditionNo,groupNo){
		//删除分组条件
		//"您确定要删除该条件?\n删除该条件并不会将符合条件的员工真正从组中删除"
		if (confirm('<ait:message  messageID="alert.att.maintenance.pattern.delete_employee" module="ar"/>'))
		location.href='arControlServlet?operation=ar_delete&content=groupCondition&menu_code=<%=StringUtil.checkNull(request.getParameter("menu_code"))%>&groupConditionNo='+conditionNo+'&groupNo='+groupNo;
	}
	
	function modifyCondition(conditionNo,groupNo){
		//修改分组条件
		var innertext1 = document.all("field1_"+conditionNo).innerText;
		var innertext2 = document.all("field2_"+conditionNo).innerText;
		while(innertext1.indexOf(", ")>=0)
			innertext1 = innertext1.replace(", ", ",");
		while(innertext2.indexOf(", ")>=0)
			innertext2 = innertext2.replace(", ", ",");
		document.all("field1_"+conditionNo).innerHTML="<form name = \"ar\" method=\"post\" action=\"\"><input type=\"text\" name=\"field1\" value=\"\" style=\"width: 120 px;\" value=\"\"><input type=\"hidden\" name=\"field2\" value=\"\"><input type=\"hidden\" name=\"condition_no\" value=\"" + conditionNo + "\"></form>";
		document.all("field2_"+conditionNo).innerHTML="<input type=\"text\" name=\"field2i\" value=\"\" style=\"width: 120 px;\" value=\"\"><br><span onClick=\"javascript:modifyConditionSubmit();\" style=\"cursor:pointer\">OK</span>";
		document.ar.field1.value=innertext1;
		document.all.field2i.value=innertext2;
	}

	function modifyConditionSubmit(){
		document.ar.field2.value=document.all.field2i.value;
		document.ar.action='arControlServlet?operation=ar_modify&content=groupCondition&menu_code=<%=StringUtil.checkNull(request.getParameter("menu_code"))%>&groupNo=<%=request.getParameter("groupNo")%>';
		document.ar.submit();
	}
	
	function previewCondition(conditionNo,groupNo) {
		url = '/ar/dynamic_group_members.jsp?groupNo='+groupNo+'&conditionNo='+conditionNo;
		window.open(url,'emplist','width=620, height=400, top=200, left=200, scrollbars=yes,resizable=yes');
	}
	
	function groupConditions(groupNo){
		location.href='arControlServlet?operation=ar_view&content=groupCondition&menu_code=<%=StringUtil.checkNull(request.getParameter("menu_code"))%>&groupNo='+groupNo;
	}
	function addrow(){
	
		if(group_no == "empty"){
		//"未选定组!\n\n请在左边组列表中选择组！"
			alert('<ait:message  messageID="alert.att.maintenance.pattern.group_left_column" module="ar"/>');
		} else {
		  document.all.data.innerHTML = 
			"<table width=\"100%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" bordercolorlight=\"#E7E7E7\""+
			" bordercolordark=\"#FFFFFF\" style=\"padding: 2px 2px 2px 2px;\"><tr align=\"center\">\n"+
			"<td height=\"25\" colspan=\"4\"  class=\"info_title_01\"><ait:message  messageID='display.att.maintenance.pattern.add_criteria' module='ar'/>："+
			"<select name=\"add_no\" class=\"calc\" onChange=\"javascript:addrows(this.value)\">"+
			"<%for (int i=1;i<=5;i++){%><option value=\"<%=i%>\"><%=i%></option><%}%>"+ 
			"</select></td><td width=\"10%\" class=\"info_title_01\">"+
			"<img src=\"/images/btn/Save_little.gif\" style=\"cursor:hand\" onClick=\"saveOption()\">"+
			"</td></tr></table>";
			addrows(1);
		}
	}
	function getAllcondition(){
		condition = "<select name=\"condition_no\"  id=\"co_no\"  style=\"text-align:center\">\n"
		<c:forEach items="${conditionList}" var="oneResult">
			+"<option value=\"${oneResult.condition_no}\"><ait:content enContent="${oneResult.field_en_name}" zhContent="${oneResult.field_name}"/></option>\n"
		</c:forEach>

		return condition;
	}
	
	function getRelation(){	
		Relation = "<select name=\"relation\"  style=\"text-align:center; \">\n";
		for(i=0;i<allRelax.length;i++){
			Relation +="<option value=\""+allRelax[i].code+"\">"+allRelax[i].name+"</option>\n";
		 	}
		Relation+="</option>";
		 return Relation;
	}

	function addrows(num) {
	    stradd = "";
		for (j=0;j<num;j++) {
		 stradd +="<tr align=\"center\">\n"+
			 "<td width=\"154\" height=\"27\"  align=\"center\">"+getAllcondition()+"</td>\n"+
			 "<td width=\"67\" align=\"center\">"+getRelation()+"</td>\n"+
			 "<td width=\"161\" align=\"center\" valign=middle><input name=field1 type='text' style=width:80%><img src=/images/left_menubullet_sub_p.gif align=absmiddle onclick=\"getConditonDetail('field1',"+j+")\"></td>\n"+
			 "<td width=\"155\" align=\"center\" ><input name=field2 type='text' style=width:80%><img src=/images/left_menubullet_sub_p.gif align=absmiddle  onclick=\"getConditonDetail('field2',"+j+")\"></td>\n"+
			 "</tr>";
		}
		stradd = "<table width=\"100%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" bordercolorlight=\"#E7E7E7\" bordercolordark=\"#FFFFFF\">"+
		"<form name = \"ar\" method='post' >"
		 +stradd+"</form></table>";
		document.all.rows.innerHTML=stradd;
	}
	function getConditonDetail(names,hnames,index) {
		target = "";
		con_no = "";
		try {
			if  (document.all(names).length){
				con_no = document.ar.condition_no[index].value;
				target = names+"["+index+"]";
			}else{
				con_no = document.ar.condition_no.value;
				target = names;
			}
		}catch(e){
			alert(e)
		}
		window.open("/ar/ar_condition_detail.jsp?condition_no="+con_no+"&target="+target,"ar_condition_detail","width=400,height=600,left=700,top=0,scrollbars=yes,status=yes");
	}
	
	function saveOption(){
		var j = document.all.add_no.value;
		j = parseInt(j);
		flag = true;
		if (j>1){
			for (i=0;i<j;i++){
			if (document.ar.field1[i].value == ""){ flag= false;break;}
			}
			if (flag==false){
			//"请输入限定值"
				alert('<ait:message  messageID="alert.att.maintenance.pattern.enter_value" module="ar"/>');
				return false;
			}
		}else{
			if (document.ar.field1.value == ""){
			//"请输入限定值"
				alert('<ait:message  messageID="alert.att.maintenance.pattern.enter_value" module="ar"/>');
				return false;
			}
		}
		document.ar.action='arControlServlet?operation=ar_add&content=groupCondition&menu_code=<%=StringUtil.checkNull(request.getParameter("menu_code"))%>&groupNo=<%=request.getParameter("groupNo")%>';
		document.ar.submit();	
	}
	function viewGroup(groupNo){
		if(groupNo != "empty"){
			url = '/ar/dynamic_group_members.jsp?groupNo='+groupNo;
			window.open(url,'emplist','width=620, height=400, top=200, left=200, scrollbars=yes,resizable=yes');
		}
		if(groupNo == "empty")	
		//"未选定组!\n\n请在左边组列表中选择组！"
		alert('<ait:message  messageID="alert.att.maintenance.pattern.group_left_column" module="ar"/>');
	}
</script>
</head>

<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_all.jsp"%>
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
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--动态组-->
					<ait:message  messageID="display.mutual.schedule_pattern"/></td>
			</tr>
		</table>
		<%
			if (operationStatus.isSuccess()){
		%>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center">
		    <td width="100" height="30"  class="info_title_01"><!--人员分组-->
					<ait:message  messageID="display.att.maintenance.pattern.pattern_division" module="ar"/></td>
		    <td width="800" colspan="1" class="info_title_01"><!--组定义-->
					<ait:message  messageID="display.att.maintenance.pattern.definition" module="ar"/></td>
		    <td  width="50" class="info_title_01">
		    <ait:image src="/images/btn/Member.gif"  border="0" align="absmiddle"
          	onclick="javascript:viewGroup('${groupNo}');" style="cursor:hand"/>
		    <td width="50" class="info_title_01">
		     <ait:image src="/images/btn/Add_little.gif"  border="0" align="absmiddle"
          	onclick="addrow()" style="cursor:hand" title="添加" enTitle="add"/></td>
		  </tr>
		  <tr align="center" >
		    <td width="100" align="center" valign="top">
		        <select name="dynamicGroup" id="dynamicGroup" size="17" style="width:200px " onChange="groupConditions(this.value)">
		        	<c:forEach items="${dynamicGroupList }" var="oneResult">
		        		<option value="${oneResult.groupNo}" <c:if test="${oneResult.groupNo==groupNo}">selected</c:if> ><ait:content enContent="${oneResult.groupEnName}" zhContent="${oneResult.groupName}" koContent="${oneResult.groupKoName}"/>&nbsp;</option>
		        	</c:forEach>
		        </select>
		    </td>
		    <td width="1000" colspan="3"  align="center" valign="top"> 
				<table width="100%" height="54" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
				  <tr align="center" height="25">
				    <td width="100"  class="info_title_01"><!--条件-->
							<ait:message  messageID="display.mutual.condition"/></td>
				    <td width="50" class="info_title_01"><!--关系-->
							<ait:message  messageID="display.mutual.relation"/></td>
				    <td width="700" class="info_title_01"><!--值1-->
							<ait:message  messageID="display.att.maintenance.pattern.value1" module="ar"/></td>
				    <td width="100" class="info_title_01"><!--值2-->
							<ait:message  messageID="display.att.maintenance.pattern.value2" module="ar"/></td>
				    <td width="50" class="info_title_01"><!--操作-->
							<ait:message  messageID="display.mutual.operate"/></td>
				  </tr>
				  <c:forEach items="${groupConditionList }" var="oneResult">
					   <tr align="center">
					    <td width="100" align="center" class="info_content_01"><ait:content enContent="${oneResult.field_en_name}" zhContent="${oneResult.field_name}"/>&nbsp;</td>
					    <td width="50" align="center" class="info_content_01"><script>document.write(getRelax('${oneResult.relation}'));</script>&nbsp;</td>
					    <td class="ar_set_dynamicgroup" id="field1_${oneResult.group_condition_no}">${oneResult.value1}&nbsp;</td>
					    <td width="100" align="center" class="info_content_01" id="field2_${oneResult.group_condition_no}">${oneResult.value2}&nbsp;</td>
					    <td width="50" align="center" class="info_content_01" valign="middle" >[<span style="cursor:pointer" onClick="previewCondition(${oneResult.group_condition_no},${oneResult.group_no})" title="<ait:message  messageID="display.mutual.description" />">V<!-- 预览 --></span>]&nbsp;[<span style="cursor:pointer" onClick="modifyCondition(${oneResult.group_condition_no},${oneResult.group_no })" title="<ait:message  messageID="display.mutual.edit" />">M<!-- 修改 --></span>]&nbsp;[<span style="cursor:pointer" onClick="deleteCondition(${oneResult.group_condition_no },${oneResult.group_no })" title="<ait:message  messageID="display.mutual.delete" />">D<!-- 删除 --></span>]</td>
					  </tr>
				  </c:forEach>
					
				  <%
				  	if(groupConditionList.size()<=0){//未设定组条件！%>
				  	<tr>
				  		<td height="27" colspan="5" align="center">
				  			<ait:message  messageID="display.att.maintenance.pattern.set_criteria" module="ar"/>
				  		</td>
				  	</tr>
				 <%}%> 
				 </table>
				<div id="data"></div>
				<div id="rows"></div>
			</td>
		  </tr>
		</table>
		<%}%>
		<%		if(!operationStatus.isSuccess()){
					out.print("<table width=\"754\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" bordercolorlight=\"#E7E7E7\" bordercolordark=\"#FFFFFF\" style=\"padding: 2px 2px 2px 2px;\"><tr><td><font color=\"#FF0000\" size=\"+1\">"+operationStatus.getMessage()+"</font></td></tr>");
				}
		%>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
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
</table>


</body>
</html>