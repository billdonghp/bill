<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<script src="../js/gmMuli.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/ext-all.css" />
</head>
<body>
<script language="javascript1.5" type="text/javascript">
function dateSelectClick()
{
	document.ApplyForm.action="/gmControlServlet?operation=eatStatistic&menu_code=${param.menu_code}&content=eat_apply";
 	document.ApplyForm.submit();
}


function Search(){
	document.ApplyForm.action="/gmControlServlet?menu_code=${param.menu_code}&operation=eatStatistic&content=eat_apply";
	document.ApplyForm.submit();
}

function Save(){
	//alert(123) ;
	
	var size = document.ApplyForm.applySize.value ;
	var cnt = "";
	var falg = true;
	var eatTypeFalg = false;
	for(var i=1;i<=size;i++){
		if(document.ApplyForm("ck_" + i).checked){
			cnt += i+",";
			falg = false;
			if(document.ApplyForm("bus"+i).value=="sel"){
			alert("请选择班车");
			return;
			}
			var arr = document.ApplyForm("eatType" + i);
				for(var y = 0;y<arr.length;y++){
					if(arr[y].checked){
						eatTypeFalg = true;
					}
				}
				if(!eatTypeFalg){
					alert("请选择餐次");
					return;
				}
				eatTypeFalg = false;
		}
	}
	if(falg){
		alert("请选择申请的人员");
		return;
	}
	if(Number(document.ApplyForm.confirmFlag.value)!=0){
	 alert("管理部已经提交数据，不能进行申请!");
	 return;
	}
	
	if (confirm("是否进行申请!")){
		document.ApplyForm.action="/gmControlServlet?operation=eatStatistic&content=eatApply&menu_code=${param.menu_code}&chNo="+(cnt.substring(0,cnt.length-1));
	
		document.ApplyForm.submit();
		document.getElementById("applysaveid").style.display="none";//避免重复提交，隐藏按钮
	}
	
}

function otTypeChange(otType1,count)
{	
	var cnt = otType1.selectedIndex;
	var otType = otType1.options[cnt].value
	var size = document.ApplyForm.applySize.value ;
	if(document.ApplyForm("ck_" + count).checked){
	if(document.ApplyForm.c.checked){
		for (var i=1; i<=size; i++){
			if(document.ApplyForm("ck_" + i).checked) {
				document.ApplyForm("bus" + i).options[cnt].selected = true ;
			}
		}
		}else{
			document.ApplyForm("bus" + cnt).options[cnt].selected = true ;
		}
	}
}

function otTypeChange1(otType1,count)
{	
	var cnt = otType1.selectedIndex;
	var otType = otType1.options[cnt].value
	var size = document.ApplyForm.applySize.value ;
	if(document.ApplyForm("ck_" + count).checked){
	if(document.ApplyForm.c.checked){
		for (var i=1; i<=size; i++){
			if(document.ApplyForm("ck_" + i).checked) {
				document.ApplyForm("foodType" + i).options[cnt].selected = true ;
			}
		}
		}else{
			document.ApplyForm("foodType" + cnt).options[cnt].selected = true ;
		}
	}
}

function otTypeChange2(otType1,count)
{	
	var cnt = otType1.value;
	var size = document.ApplyForm.applySize.value ;
	if(document.ApplyForm("ck_" + count).checked){
	if(document.ApplyForm.c.checked){
		for (var i=1; i<=size; i++){
			if(document.ApplyForm("ck_" + i).checked) {
				var arr = document.ApplyForm("eatType" + count);
				for(var y = 0;y<arr.length;y++){
					if(arr[y].checked){
						var arr1 = document.ApplyForm("eatType" + i);
						arr1[y].checked = true;
					}else{
						var arr1 = document.ApplyForm("eatType" + i);
						arr1[y].checked = false;
					}
				}
			}
			}
		}else{
			if(document.ApplyForm("eatType" + count).value==cnt)
			document.ApplyForm("eatType" + count).checked = true;
		}
		}
}

function checkOne(i)
{
		document.ApplyForm("ck_" + i).checked = true ;
}

function checkAll()
{
    var selected = document.ApplyForm.ck_all.value == "0" ? true : false;
    var size = document.ApplyForm.applySize.value ;
  	for (var i=1; i<=size; i++){
		document.ApplyForm("ck_" + i).checked = selected ;
	
	}
    document.ApplyForm.ck_all.value = selected ? "1" : "0";
}

function selMuli(name,cnt){
	
	if(document.ApplyForm.c.checked==true){
		var size = document.ApplyForm.applySize.value ;	
		var selIndex = document.ApplyForm(name + cnt).selectedIndex;
		
		if(document.ApplyForm("ck_" + cnt).checked)
	    {
		    for(var z = 1 ; z <= size ; z++ )
			{
				if(document.ApplyForm.c.checked && document.ApplyForm("ck_" + z).checked)
				{
					var sel = document.ApplyForm(name + z);
			    		  
		    		if(sel.type=="select-one"){
		    			sel.options[selIndex].selected=true;
		    		}
		    	}
			}
	    }
	}
}
</script> 
<form name="ApplyForm" method="post" action="">
<input name="listSize" value="${fn:length(arrangementList)}" type="hidden">
<input name="confirmFlag" value="${confirmFlag}" type="hidden">
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
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
			<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>
			</td>
		</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	       <tr>
	          <td class="info_title_01" ><!-- 部门 -->
	        <ait:message messageID="display.mutual.dept" ></ait:message>   
	          </td>
	          <td class="info_content_00"  > <ait:selDept name="deptID" selected="${deptID}" all="all"  supervisorType="hr"/></td>
	          <td class="info_title_01"  ><!-- 职号/姓名 -->
	         <ait:message messageID="display.mutual.emp_no_name" module="ess" ></ait:message> 
	          </td>
	          <td class="info_content_00"  >
	          <input type="text" name="key" value="${key}" /></td>
	          <td nowrap="nowrap" class="info_title_01" ><!--  选择日期-->
	          	选择日期
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="selectdate" value="${current_date}" onClick="setday(this);" readonly style="width:70px">	
	          </td>  
	          <td class="info_content_00"  >
	          	<ait:image src="/images/btn/Search.gif" align="absmiddle" onclick="javascript:Search();" style="cursor:hand" />
	          </td>
	        </tr>
	        </table>

		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="9">
				
					就餐申请
				</td>
				<td class="info_content_09" align="left"><!-- 下拉框联动 --><ait:message  messageID="display.mutual.toggle_all"/>
						<input type="checkbox" name="c" value="c" class="check" <c:if test="${c == 'c'}"> checked </c:if> />
				</td>
			</tr>
			<c:if test="${error!=null}">
			<tr>
				<td align="center"  colspan="10">
				<font color="red">${error}</font>
				</td>
			</tr>
			</c:if>
		</table>
		
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	  <input type="hidden" name="ck_all" value="0" />
	  <input type="hidden" name="applySize" value="${fn:length(list)}"/>  
	<tr>
	  <td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;">全选</td>
	  <td class="info_title_01" nowrap>职号</td>
	  <td class="info_title_01" nowrap>姓名</td>
	  <td class="info_title_01" nowrap>课组</td>
	  <td class="info_title_01" nowrap>部门</td>
	  <td class="info_title_01" nowrap>就餐日期</td>
	  <td class="info_title_01" nowrap>餐别</td>
	  <td class="info_title_01" nowrap>餐次</td>
	  <td class="info_title_01" nowrap>班车</td>
	</tr>
	<c:forEach items="${list}" var="list" varStatus="i">
	<tr>
	  <td class="info_content_01" nowrap><input type="checkbox" name="ck_${i.count}" value="${list.PERSON_ID}" />
	  <input type="hidden" name="PERSON_ID${i.count}" value="${list.PERSON_ID}" />
	  </td>
	  <td class="info_content_01" nowrap>${list.EMPID }</td>
	  <td class="info_content_01" nowrap>${list.CHINESENAME }</td>
	  <td class="info_content_01" nowrap>${list.DEPTNAME }</td>
	  <td class="info_content_01" nowrap>${list.DEPTNAME1 }</td>
	  <td class="info_content_01" nowrap>
		${list.OTDATE}</td>
	  <td class="info_content_01" nowrap><ait:codeClass codeClass="Food_Type" name="foodType${i.count}" onChange="otTypeChange1(this,${i.count});selMuli('foodType',${i.count});checkOne(${i.count});"/></td>
	  <td class="info_content_01" nowrap>
		<c:forEach items="${eatType}" var="all">
				${all.GM_TYPE}<input type="checkbox" id="eatType${i.count}"
				name="eatType${i.count}" value="${all.GM_ID}" onclick="otTypeChange2(this,${i.count});selMuli('eatType',${i.count});checkOne(${i.count});""/>
		<input type="hidden" name="${all.GM_ID}" id="${all.GM_ID}" value="${all.GM_TYPE}" />
		</c:forEach>
		无<input type="checkbox" id="eatType${i.count}" name="eatType${i.count}" value="999" onclick="otTypeChange2(this,${i.count});selMuli('eatType',${i.count});checkOne(${i.count});""/>
		&nbsp;

	</td>	
	  <td class="info_content_01" nowrap>
		<select name="bus${i.count }" onChange="selMuli('bus',${i.count});checkOne(${i.count});" >
		<option value="sel">请选择</option>
		<c:forEach items="${listBus}" var="listBus">
			<option value="${listBus.LINE_CODE }">${listBus.LINE_NAME }</option>
		</c:forEach>
		</select>
	  </td>
	</tr>
	</c:forEach>
	 
	</table>
	
	<input type="hidden" name="pageSize" value="${currentPage}">
	 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gmControlServlet"
		               parameters="&menu_code=${param.menu_code}&operation=eatStatistic&content=eat_apply&key=${key}&deptID=${deptID}&selectdate=${current_date}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
		            
		            <!-- Page Navigation End -->
		
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
</form>
</body>
</html>
