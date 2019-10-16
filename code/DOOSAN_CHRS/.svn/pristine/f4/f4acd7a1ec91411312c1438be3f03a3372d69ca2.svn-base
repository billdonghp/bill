<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<script src="../js/prototype.js"></script>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="jobHealthInformation" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="JOB_POSITION_INFORMATION" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="POSITION_INFORMATION" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="medicalflagg" class="java.lang.String" scope="request" />
<jsp:useBean id="empName" class="java.lang.String" scope="request" />
<jsp:useBean id="empId" class="java.lang.String" scope="request" />
<jsp:useBean id="StartDate" class="java.lang.String" scope="request" />
<jsp:useBean id="EndDate" class="java.lang.String" scope="request" />
<jsp:useBean id="POSITION" class="java.lang.String" scope="request" />
<jsp:useBean id="deptId" class="java.lang.String" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">
function Add() {
	document.ApplyForm.action="/safeControlServlet?operation=jobHealth&content=addPositionInformationView&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
function Update() {

	if(document.ApplyForm.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	document.ApplyForm.action="/safeControlServlet?operation=jobHealth&content=updatejobHealthView&menu_code=${param.menu_code}&EMPID="+document.ApplyForm.temp.value+"&tempjobId="+document.ApplyForm.tempjobId.value;
	document.ApplyForm.submit();
}
function Delete() {
	if(document.ApplyForm.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.ApplyForm.action="/safeControlServlet?operation=jobHealth&content=deletejobHealthView&menu_code=${param.menu_code}&EMPID="+document.ApplyForm.temp.value;
		document.ApplyForm.submit();
	}
}


function band(backColor,textColor,i,jobId)
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
	document.ApplyForm.temp.value=i;
	document.ApplyForm.tempjobId.value=jobId;
} 

var time=null;
function SearchContent(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchC(condition,id);
					},500);  
}

function SearchC(condition,id){

		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployeeSysAdmin&condition=" + encodeURIComponent(condition);
     	
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
    
	layer.innerHTML=XmlHttpRequest.responseText.replace('*','&');
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('empID').value=cell.childNodes[0].firstChild.nodeValue;
		$('empName').value=cell.childNodes[1].firstChild.nodeValue;
		layerClose();
}


function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.currentPage.value='0';
	document.ApplyForm.action="/safeControlServlet?operation=jobHealth&content=jobHealthSearch&flag=2&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}

function ImportExcel() {
	document.ApplyForm.action="/safeControlServlet?operation=jobHealth&content=jobHealthSearch&flag=1&menu_code=${param.menu_code}";
	document.ApplyForm.submit();	
} 
</script> 

<form name="ApplyForm" action="/safeControlServlet" method="post">
<input type="hidden" name="temp" value=""/>
<input type="hidden" name="tempjobId" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
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
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" align="center">
		<tr>
			<td class="title1"><!-- 查询条件 -->
			<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>
			</td>
		</tr>
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d" align="center">
	       <tr align="center">
	        <td width="10%" class="info_title_01">
		      		姓名/职号&nbsp;
		      	</td>
	      		  <td width="15%" class="info_content_00"><!-- 请输入姓名查找 -->
		          	<input id="empName" name="empName" size="8" value="${empName}"  
	          		onkeyup="SearchContent(this.value,this.id);"  
	          		title="<ait:message messageID='alert.emp.staff_info.basic_info.search_name' module='hrm'/>" /> 
          				
       				<input type="hidden" id="empID" name="empID" size="8" value="${empId}"  
       				onkeyup="SearchContent(this.value,this.id);"  
       				title="<ait:message messageID='alert.emp.staff_info.basic_info.search_emp_id' module='hrm'/>"  
       				required/> 
          				 
		        </td>
	         <td width="10%" class="info_title_01">
		          		岗位 
		          </td>
		          <td width="15%" class="info_content_00" align="center">
			          <select name="POSITION">
				          	<option value="">
			          			请选择
			          		</option>
							<c:forEach items="${POSITION_INFORMATION}" var="all" varStatus="i">
								<c:choose>
								<c:when test="${all.RELATIONS_ID == POSITION}">
									<option value="${all.RELATIONS_ID}" selected>${all.CODE_NAME}</option>
								</c:when>
								<c:otherwise>
									<option value="${all.RELATIONS_ID}">
										${all.CODE_NAME}
									</option>
								</c:otherwise>
								</c:choose>
							</c:forEach>
					  </select>
		          </td>
		          <td width="10%" class="info_title_01">
		        		<ait:message messageID="display.mutual.dept" ></ait:message>   
		          </td>
		          <td width="20%" class="info_content_00" align="center">
		          		<ait:selDept name="deptID" selected="${deptId}" all="请选择部门" supervisorType="hr"/>
		          </td>
		          <td class="info_content_00" align="center"></td>
	        </tr>
	      	<tr>
		      	
		         
		          <td width="10%" class="info_title_01">
		          		开始日期
		          </td>
		          <td width="15%" class="info_content_00" align="center">
		          		<input type="text" name="StartDate" style="width: 85px" value="${StartDate}" onClick="setday(this);" readonly="readonly">
		          </td>
		          <td width="10%" class="info_title_01">
		            	 结束日期
		          </td>
		          <td width="15%" class="info_content_00" align="center">
		          		<input type="text" name="EndDate" style="width: 85px" value="${EndDate}" onClick="setday(this);" readonly="readonly">
		          </td>
		          
		          <td width="10%" class="info_title_01">
		           		最近体检结果
		          </td>
		           <td width="20%" align="center" class="info_content_00">&nbsp;
		           		<select name="medicalflag">
		           		<option value="">
		          			请选择
		          		</option>
			          	<option value="正常" <c:if test="${medicalflagg == '正常'}">selected</c:if>>
		          			正常
		          		</option>
		          		<option value="异常" <c:if test="${medicalflagg == '异常'}">selected</c:if>>
		          			异常
		          		</option>
			          </select>
		          </td>
		          <td class="info_content_00" align="center">
		          <ait:image src="/images/btn/Search.gif" align="absmiddle"
         				onclick="javascript:Search();" style="cursor:hand" />
         		<img src="/images/btn/Excel_Exp.gif" style="cursor:hand" onclick="ImportExcel()"/>
		          </td>
	        </tr>
	       
	      </table>
			<ait:xjos />
	      </td>
		</tr>
		</table>
			<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
			</iframe>
			<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">   
			</div>
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					职业健康监护档案
				</td>
			</tr>
		</table>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	  
	  <tr>
	   <td class="info_title_01" nowrap>
	      职业健康编号
	    </td>	  
	    <td class="info_title_01" nowrap>
	       职号
	    </td>
	    <td class="info_title_01" nowrap>
	       姓名
	    </td>
	    <td class="info_title_01" nowrap>
	       部门
	    </td>
	    <td class="info_title_01" nowrap>
	       岗位
	    </td>
	    <td class="info_title_01" nowrap>
	       出生日期
	    </td>
	    <td class="info_title_01" nowrap>
	       入社日期
	    </td>
	    <td class="info_title_01" nowrap>
	       职业危害
	    </td>
	    <td class="info_title_01" nowrap>
	       从事本岗日期
	    </td>
	    <td class="info_title_01" nowrap>
	       最近体检日期
	    </td>
	    <td class="info_title_01" nowrap>
	       最近体检结果
	    </td>
	    <td class="info_title_01" nowrap>
	       历次体检
	    </td>
	  </tr>
	  <c:forEach items="${jobHealthInformation}" var="all" varStatus="i">
		  <tr onClick="band('#E7F0EF','black','${all.PERSON_ID}','${all.JOB_POSITION_ID}')" align="center" style="height: 30px">
		  	<td align="center" nowrap>
		    	${all.DOCUMENTNO}&nbsp;
		    </td>
		    <td align="center" nowrap>&nbsp;
		    	${all.EMPID}&nbsp;
		    </td>		    
		    <td align="center" nowrap>&nbsp;
		    	${all.CHINESENAME}&nbsp;
		    </td>
		    <td align="center" nowrap>&nbsp;
		    	${all.DEPTNAME}&nbsp;
		    </td>
		   <td align="center" nowrap>&nbsp;
		    	${all.JOB_POSITION}&nbsp;
		    </td>
		    <td align="center" nowrap>&nbsp;
		    	${all.BIRTH_YMD}&nbsp;
		    </td>
		    <td align="center" nowrap>&nbsp;
		    	${all.DATE_STARTED}&nbsp;
		    </td>
		    <td align="center" nowrap>&nbsp;
		    	${all.DISEASE}&nbsp;
		    </td>
		    <td align="center" nowrap>&nbsp;
		    	${all.START_TIME}&nbsp;
		    </td>
		    <td align="center" nowrap>
		    	${all.MEDICAL_YEAR}&nbsp;
		    </td>
		    <td align="center" style="color: #666666;">&nbsp;
		    	<c:if test="${all.MEDICALFLAG == '异常'}"><font color="red">${all.MEDICALFLAG}</font></c:if>
		    	<c:if test="${all.MEDICALFLAG == '正常'}"><font color="black">${all.MEDICALFLAG}</font></c:if>
		    </td>
		    <td align="center">
		    	<a href="/safeControlServlet?operation=jobHealth&content=searchDetail&person_id=${all.PERSON_ID}&jobpositionid=${all.JOB_POSITION_ID}&menu_code=${param.menu_code}">查看详情</a>
		    </td>
		  </tr>
	  </c:forEach>
	</table>
	
	 <!-- Page Navigation Start-->
					<ait:page       
		               link="/safeControlServlet"
		               parameters="&operation=jobHealth&content=jobHealthSearch&menu_code=${param.menu_code}&POSITION=${POSITION }&deptID=${deptID }&StartDate=${StartDate }&EndDate=${EndDate }&medicalflag=${medicalflag }" 
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
<input type="hidden" name="currentPage" value="${currentPage}" />
</form>
</body>
</html>
