<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="allVisiterManagementInformation"
	class="java.util.ArrayList" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="visiterCountry" class="java.util.ArrayList"
	scope="request" />
<jsp:useBean id="visiterCountrySize" class="java.lang.String"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>参观者管理</title>
<style>
#leftnewstd .ellipsis_row{width:40px}
.ellipsis_row{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;

}
#leftnewstd .ellipsis_row2{width:60px}
.ellipsis_row2{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;
}
</style>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
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


function search() {
	document.form1.action="/puControlServlet?menu_code=${param.menu_code}&operation=visiterManagement&content=visiterManagementView&flag=1";
	document.form1.submit();
}

function Update() {
	
	if(document.form1.okFlag.value == '1'){
		alert("此条信息已确认，不能修改！")
		return;
	}
	
	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	document.form1.action="/puControlServlet?operation=visiterManagement&content=visiterManagementModifyView&menu_code=${param.menu_code}&visiterManagementId="+document.form1.temp.value;
	document.form1.submit();
}
function Delete() {
	if(document.form1.okFlag.value == '1'){
		alert("此条信息已确认，不能修改！")
		return;
	}
	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	if(confirm("确认删除吗？")){
	document.form1.action="/puControlServlet?operation=visiterManagement&content=deleteVisiterManagement&menu_code=${param.menu_code}&flag=1&visiterManagementId="+document.form1.temp.value;
	document.form1.submit();
	}
}

function ImportExcel() {

		document.form1.action="/puControlServlet?menu_code=${param.menu_code}&operation=visiterManagement&content=visiterManagementView&flag=2";
		document.form1.submit();

}

function selectCountry(){
	for(var i=0 ; i<Number(document.form1.visiterCountrySize.value) ; i++){
		if(document.form1['country_'+i].checked){
			document.form1.otherCountry.checked = false;
		}
	}
	return;
}

function selectOther(){
	if(document.form1.otherCountry.checked){
		for(var i=0 ; i<Number(document.form1.visiterCountrySize.value) ; i++){
			document.form1['country_'+i].checked = false ; 
		}
	}
	return;
}

function Confirm(){
	if(document.form1.okFlag.value == '1'){
		alert("此条信息已确认，不能再次确认！")
		return;
	}
	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择确认的项目！");
		return;
	}
	document.form1.action="/puControlServlet?menu_code=${param.menu_code}&operation=visiterManagement&content=updateModifyFlag&affirmno="+document.form1.temp.value;
	document.form1.submit();
}
function ScenePhotos(photosid){
window.open("/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&content=viewPhoto&documentno="+photosid,"","resizable=no,scrollbars,dependent,width=350,height=130,left=350,top=500");
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
function doAffirm(){
	var msg1='<ait:message messageID="alert.ess.common.nodatatopass" module="ess"></ait:message>';
	
	var msg3='<ait:message messageID="alert.ess.common.checkpass" module="ess"></ait:message>';

	var msg5='<ait:message messageID="alert.ess.common.cofirmpass" module="ess"></ait:message>';

	var affirmno = document.getElementsByName("affirmno");
	if (affirmno == null || affirmno.length == null || affirmno.length == 0){
	  	alert(msg1);
		return false;
	}
	var c = 0;
	for (var i=0; i<affirmno.length; i++){
		if(affirmno[i].checked == true){
			c++;
			break ;
		}
	}
	if (c==0){
	  	alert(msg3);
		return false;
	}                                       
    if (confirm(msg5)) {
	document.form1.action="/puControlServlet?menu_code=${param.menu_code}&operation=visiterManagement&content=updateModifyFlag";
	document.form1.submit();
	}
}

function showMemo(val) {
	var memo = document.getElementById('memo_'+val).value;
	var html = "<div style='background-color:#FFFFFF;height: 100%'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 350
				  , height: 150
				 , shadow: true
				 , closable: true
				  , layout : 'fit'
				   ,autoScroll: true
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
}
</SCRIPT>

<body>
<form name="form1" method="post"><input type="hidden"
	name="okFlag" value="0" /> <input type="hidden" name="okFlag1" value="" />
<input type="hidden" name="ck_all" value="0" />
<input type="hidden" name="countryTemp" value="" /> <input type="hidden"
	name="temp" value="" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_deleteAndModifyAndOk.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <br>
		<!-- display content -->
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
				<!-- <td class="info_title_01">国家</td>
				<td  class="info_content_00"><ait:codeClass codeClass="Visiter_Country" name="country" all="all" selected="${country}"/></td> -->
				
				<td class="info_title_01" nowrap="nowrap">状态</td>
				<td class="info_content_00" nowrap="nowrap">
					<select name="qryType">
				         <option value="3">全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未确认</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已确认</option>
				     </select>
				</td>
				<td class="info_content_00"><img
					src="../images/btn/Search.gif" style="cursor: pointer;"
					onclick="search();"></td>
				<td  class="info_content_00"></td>
			</tr>
			<tr>
				<td  class="info_title_01">法人区分</td>
		         <td  class="info_content_00"><ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="search();"/></td> 
				<td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <ait:selDeptByCpnyId name="deptid" supervisorType="pa" all="all" selected="${deptid}" cpnyId ="${cpnyId}"/>
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}"  title="输入姓名或者职号"/></td>   
				
				<td  class="info_content_00"><img
					src="../images/btn/Excel_Exp.gif" style="cursor: pointer;"
					onclick="ImportExcel();" />
					&nbsp;&nbsp;
					<!--  <select name="visiterFlag">
				         
				         <option value="1" <c:if test="${visiterFlag==0}">selected</c:if> >来访信息</option>   
				         <option value="2" <c:if test="${visiterFlag==1}">selected</c:if>>来访者信息</option>
				     </select>-->

				</td>
				<td  class="info_content_00"></td>
			</tr>
		</table>

		<!-- display 3 level menu --> <%@ include
			file="../inc/thirdToolBar.jsp"%> <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">参观者确认</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">
				<td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;"><!-- 全选 --> 
		       	<ait:message messageID="display.mutual.select_2" module="ess" /></a></td>
				<td nowrap="nowrap" class="info_title_01">编号</td>
				<!--  <td nowrap="nowrap" class="info_title_01">
				姓名</td>
			  <td nowrap="nowrap" class="info_title_01">
				职务</td>
			  <td nowrap="nowrap" class="info_title_01">
				单位</td>		      
			  <td nowrap="nowrap" class="info_title_01" style="width: 130px">
				截止时间</td>
			  -->
				<td nowrap="nowrap" class="info_title_01">来访日期</td>
				<td nowrap="nowrap" class="info_title_01">到达时间</td>
				<td nowrap="nowrap" class="info_title_01">离开时间</td>
				<!--<td nowrap="nowrap" class="info_title_01">国别</td>
							  <td nowrap="nowrap" class="info_title_01">-->
				<!--				区分</td>-->
<!--				<td nowrap="nowrap" class="info_title_01">联系电话</td>-->
				<td nowrap="nowrap" class="info_title_01">
				访问单位</td>
			  <td nowrap="nowrap" class="info_title_01">
				访问地点</td>
		      <td nowrap="nowrap" class="info_title_01">
				访问目的</td>
			  <td nowrap="nowrap" class="info_title_01">
				来访人数</td>
				<!--			  <td nowrap="nowrap" class="info_title_01" style="120px">
				&nbsp;&nbsp;&nbsp;礼品名称&nbsp;&nbsp;&nbsp;</td>
			  <td nowrap="nowrap" class="info_title_01" style="120px">
				&nbsp;&nbsp;&nbsp;礼品数量&nbsp;&nbsp;&nbsp;</td>-->
				<!-- <td nowrap="nowrap" class="info_title_01">接待部门</td> -->
				<td nowrap="nowrap" class="info_title_01">接待人员</td>
				<td nowrap="nowrap" class="info_title_01">申请人</td>
				<!-- 
				<td nowrap="nowrap" class="info_title_01">查看扫描文件</td>
				<td nowrap="nowrap" class="info_title_01">会议室编号</td>-->
				<td nowrap="nowrap" class="info_title_01" >
				查看详情</td>	
				<td nowrap="nowrap" class="info_title_01">操作</td>
				<td nowrap="nowrap" class="info_title_01">
				意见</td>
			</tr>
			<c:forEach items="${allVisiterManagementInformation}" var="all"
				varStatus="i">
					<tr
						onClick="band('#E7F0EF','black','${all.GA_VISITER_APPLY_ID}','${all.MODIFY_FLAG}')"
						align="center">
						<td nowrap="nowrap" align="center" class="info_content_01" >
				      	&nbsp;<c:if test="${all.MODIFY_FLAG == '0'}">
				      	 <input type="checkbox" name="affirmno" value="${all.GA_VISITER_APPLY_ID}" />
				      	</c:if>&nbsp;
					  </td>
						<td align="center" nowrap="nowrap">
						&nbsp;${all.GA_VISITER_APPLY_ID}</td>
						<!--				      <td align="center" nowrap="nowrap" >-->
						<!--				      	&nbsp;${all.VISITER_NAME}-->
						<!--				      </td>-->
						<!--				      <td align="center" nowrap="nowrap" >-->
						<!--				      		&nbsp;${all.VISITER_DUTY}-->
						<!--				      </td>-->
						<td align="center" nowrap="nowrap"  style="height: 40px">
						&nbsp;${all.VISITER_DATE}
						<input type = "hidden" name="VISITER_DATE_${all.GA_VISITER_APPLY_ID}" value="${all.VISITER_DATE}" />
						</td>
						<td align="center" nowrap="nowrap">
						&nbsp;${all.VISITER_COME_TIME}
						<input type = "hidden" name="VISITER_COME_TIME_${all.GA_VISITER_APPLY_ID}" value="${all.VISITER_COME_TIME}" />
						</td>
						<td align="center" nowrap="nowrap">
						&nbsp;${all.VISITER_END_TIME}
						<input type = "hidden" name="VISITER_END_TIME_${all.GA_VISITER_APPLY_ID}" value="${all.VISITER_END_TIME}" />
						</td>
						<!--
				      <td align="center" class="info_content_01">
				      		&nbsp;${all.VISITER_OUT_DATE}
				      </td>
				      
						<td align="center" nowrap="nowrap">
						&nbsp;${all.VISITER_COUNTRY}</td>-->
						<td nowrap="nowrap" >
				      		&nbsp;${all.VISITER_COMPANY}
				      </td>
				      <td nowrap="nowrap" >
				      		&nbsp;${all.VISIT_PLACE}
				      </td>
				     <td class="info_content_00" nowrap="nowrap" with="30">
		    	        <a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo('${all.GA_VISITER_APPLY_ID}');">
		    		    <span class=ellipsis_row>${all.VISITER_OBJECTIVE}</span>
	                    </a>
		    	        <input type = "hidden"
		    		    id="memo_${all.GA_VISITER_APPLY_ID}" 
		    		    value="${all.VISITER_OBJECTIVE}" />	            
            	        </td>
				      <td nowrap="nowrap" >
				      		&nbsp;${all.VISIT_COUNT}<input type="hidden" name="VISIT_COUNT_${all.GA_VISITER_APPLY_ID}" value="${all.VISIT_COUNT}">
				      </td>
						
			<!--			<td align="center">&nbsp;
						${all.VISITER_OBJECTIVE}</td>
							<td align="center" nowrap="nowrap">
						&nbsp;${all.DEPTNAME}</td>-->
						<td align="center" nowrap="nowrap">
						&nbsp;${all.PLAY_APPLYCHINESENAME}
						</td>
						
						<td align="center" nowrap="nowrap">&nbsp; ${all.CHINESENAME1}
						<input type = "hidden" name="APPLYNAME_${all.GA_VISITER_APPLY_ID}" value="${all.CHINESENAME1}" />
						<input type = "hidden" name="empid_${all.GA_VISITER_APPLY_ID}" value="${all.CHINESENAME1}" />
						
						</td>
<!--						<td align="center" style="">&nbsp;${all.VISITER_GIFT_NAME}<br>
						<font color="red">原申请:${all1.VISITER_GIFT_NAME}</font></td>
						<td align="center" style="">&nbsp;${all.VISITER_GIFT_NUMBER}<br>
						<font color="red">原申请:${all1.VISITER_GIFT_NUMBER}</font></td>-->
						<!--  
						<td align="center" nowrap="nowrap"><c:forEach
							items="${filelist}" var="test" varStatus="j">
							<c:if test="${test.DOCUMENTNO==all.GA_VISITER_APPLY_ID}">
								<span style="color: red; cursor: pointer; width: 55px"
									onClick="ScenePhotos('${all.GA_VISITER_APPLY_ID}')"
									title="查看现场照片">查看扫描文件</span>
							</c:if>
						</c:forEach>&nbsp;</td>
						<td align="center" nowrap="nowrap">&nbsp;
						${all.HUIYISHI_APPLYID}</td>
						-->
						<td nowrap="nowrap" class="info_content_01">&nbsp;
				      		<a href="/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&content=viewDetail&applyno=${all.GA_VISITER_APPLY_ID}" style="color:red">查看详情</a>
				      </td>
						<td align="center" nowrap="nowrap"><c:if
							test="${all.MODIFY_FLAG == '0'}">&nbsp;
<!--							<img src="../images/btn/confirm.gif" style="cursor: pointer;"-->
<!--								onclick="OK('${all.GA_VISITER_APPLY_ID}')" />-->
								<font color="red">未确认</font>
						</c:if> <c:if test="${all.MODIFY_FLAG == '1'}">
							<font color="#00CC00">已确认</font>
						</c:if></td>
						<td nowrap="nowrap"  align="center" class="info_content_06"><textarea name="affirmorIdea_${all.GA_VISITER_APPLY_ID}" style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${all.CONFIRMIDEA}</textarea></td>
					</tr>
			</c:forEach>
			<tr align="center">
			</tr>
		</table>
		<!-- Page Navigation Start-->
		<ait:page link="/puControlServlet"
			parameters="&operation=visiterManagement&content=visiterManagementView&menu_code=${param.menu_code}&deptid=${deptid}&empName=${empName}&visitDate=${visitDate}&visitDateEND=${visitDateEND}&flag=1&startDate=${startDate }&endDate=${endDate}&country=${country }&key=${key }&qryType=${qryType }"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupsize}"
			useJS="false" />
		<!-- Page Navigation End -->
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
<input type="hidden" name="currentPage" value="${currentPage}">
</form>
</body>
</html>