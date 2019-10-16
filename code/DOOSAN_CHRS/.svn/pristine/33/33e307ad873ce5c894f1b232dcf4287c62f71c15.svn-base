<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<script src="../js/prototype.js"></script>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="listMM" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="listHH" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gmType" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="cardType" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="eatLookList" class="java.util.ArrayList" scope="request" />

<jsp:useBean id="listDDS" class="java.lang.String" scope="request" />
<jsp:useBean id="listHHS" class="java.lang.String" scope="request" />
<jsp:useBean id="listMMS" class="java.lang.String" scope="request" />
<jsp:useBean id="listDDE" class="java.lang.String" scope="request" />
<jsp:useBean id="listHHE" class="java.lang.String" scope="request" />
<jsp:useBean id="listMME" class="java.lang.String" scope="request" />
<jsp:useBean id="Todaydate" class="java.lang.String" scope="request" />
<jsp:useBean id="YestedateDate" class="java.lang.String" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">
var flag = 0;

function Search() {
	flag = 1;
	if(document.ApplyForm.listDDS.value!=0 || document.ApplyForm.listDDE.value!=0){
		if(document.ApplyForm.listDDS.value==0){
			alert("请选择开始时间");
			return;
		}else if(document.ApplyForm.listDDE.value==0){
			alert("请选择结束时间");
			return;
		}else if(document.ApplyForm.listHHS.value=="" || document.ApplyForm.listMMS.value=="" || document.ApplyForm.listHHE.value=="" || document.ApplyForm.listMME.value==""){
			alert("请选择小时和分钟");
			return;
		}
	}
	if(document.ApplyForm.listDDS.value > document.ApplyForm.listDDE.value){
		alert("开始时间必须在结束时间之前！");
		return;
	}


	//重新搜索时应重置当前页数
	document.ApplyForm.currentPage.value='0';
	document.ApplyForm.action="/gmControlServlet?operation=search&menu_code=${param.menu_code}&flag=1";
	document.ApplyForm.submit();
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
		$('empID').value=cell.childNodes[0].firstChild.nodeValue;
		$('empName').value=cell.childNodes[1].firstChild.nodeValue;
		layerClose();
}

function ImportExcel() {
	document.ApplyForm.action="/gmControlServlet?operation=search&menu_code=${param.menu_code}&flag=2";
	document.ApplyForm.submit();	
} 
</script> 

<form name="ApplyForm" action="/gmControlServlet" method="post">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_search.jsp"%>
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
			<td align="right"><!-- 查询条件 -->
				<img src="/images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImportExcel()"/>
			</td>
		</tr>
		</table>
			<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
			</iframe>
			<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">   
			</div>
		<!-- display 3 level menu -->
		 <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	      <tr>
	          <td nowrap="nowrap" class="info_title_01"><!--  开始日期-->
	          	开始时间 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="listDDS" value="${listDDS}" onClick="setday(this);" readonly  style="width:70px">
	          <input type="hidden" name="hhS" value="${listHHS}"/>
	          <input type="hidden" name="mmS" value="${listMMS}"/>
	          <select name="listHHS" style="width:50px">
	          <option value="">请选择时</option>
	          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
						<c:choose>
							<c:when test="${lhh == listHHS}">
								<option value="${listHHS}" selected>${listHHS}</option>
							</c:when>
							<c:otherwise>
								<option value="${lhh}">${lhh}</option>
							</c:otherwise>
						</c:choose>
	          	</c:forEach>
	          </select>
	          :
	          <select name="listMMS" style="width:50px">
	          <option value="">选择分钟</option>
	          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
	          		<c:choose>
						<c:when test="${lmm == listMMS}">
							<option value="${listMMS}" selected>${listMMS}</option>
						</c:when>
						<c:otherwise>
							<option value="${lmm}">${lmm}</option>
						</c:otherwise>
					</c:choose>
	          	</c:forEach>
	          </select>
	          </td>
	          <td nowrap="nowrap" class="info_title_01"><!--结束日期  -->
	             结束时间
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="listDDE" value="${listDDE}" onClick="setday(this);" readonly  style="width:70px">
	          <input type="hidden" name="hhE" value="${listHHE}"/>
	          <input type="hidden" name="mmE" value="${listMME}"/>
	          	<select name="listHHE" style="width:50px">
	          	<option value="">请选择时</option>
	          		<c:forEach items="${listHH}" var="lhhe" varStatus="i"> 
	          			<c:choose>
							<c:when test="${lhhe == listHHE}">
								<option value="${listHHE}" selected>${listHHE}</option>
							</c:when>
							<c:otherwise>
								<option value="${lhhe}">${lhhe}</option>
							</c:otherwise>
						</c:choose>
	          		</c:forEach>
	          	</select>
	          	:
	          	<select name="listMME" style="width:50px">
	          	<option value="">选择分钟</option>
	          		<c:forEach items="${listMM}" var="lmme" varStatus="i">
	          			<c:choose>
							<c:when test="${lmme == listMME}">
								<option value="${listMME}" selected>${listMME}</option>
							</c:when>
							<c:otherwise>
								<option value="${lmme}">${lmme}</option>
							</c:otherwise>
						</c:choose>
	          		</c:forEach>
	          	</select>
	          </td>
	          <td nowrap="nowrap" class="info_title_01"><!-- 部门 -->
	        		<ait:message messageID="display.mutual.dept" ></ait:message>   
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          		<ait:selDept name="deptID" selected="${deptID}" all="请选择" supervisorType="hr"/>
	          </td>
	          
	        </tr>
	        <tr>
	        <td nowrap="nowrap" class="info_title_01"><!-- 就餐卡号 -->
	       		  职号/姓名
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
			          <input id="empName" name="empName" size="8" value="${empName}"  
			          		onkeyup="SearchContent(this.value,this.id);"  
			          		title="请输入姓名查找" /> 
          				
       				<input type="hidden" id="empID" name="empID" size="8" value="${empID}"  
	       				onkeyup="SearchContent(this.value,this.id);"  
	       				title="请输入工号查找"  
	       				required/> 
	          
	          
	          </td>
	          <td nowrap="nowrap" class="info_title_01"><!-- 就餐类型-->
	          	就餐类型 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	         	 <select name="gm_type">
	         	 <option value="">请选择</option>
		         	 <c:forEach items="${gmType}" var="oneResult" varStatus="i">
		         	 	
		          		<option value="${oneResult.GM_ID}" <c:if test="${oneResult.GM_ID==gm_type}">selected="selected" </c:if>>${oneResult.GM_TYPE}</option>
	          		</c:forEach>
	          	</select>
	          </td>

	          <td nowrap="nowrap" class="info_title_01"><!--(就餐)卡类别 -->
	        	就餐卡类别 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">	        
	          	<select name="card_type">
	          	<option value="">请选择就餐卡类别</option>
		         	 <c:forEach items="${cardType}" var="oneResult" varStatus="i">
		         	 	
		          		<option value="${oneResult.TYPE_NO}" <c:if test="${oneResult.TYPE_NO==card_type}">selected="selected" </c:if>>${oneResult.TYPE_NAME}</option>
	          		</c:forEach>
	          	</select>
			  </td>
	          
	        </tr>
	        </table>
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 加班申请 -->
				
					就餐查看
				</td>
			</tr>
		</table>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	  
	  <tr>
	  
	    <td class="info_title_01" nowrap><!-- 职号 -->
	       职号
	    </td>
	    <td class="info_title_01" nowrap><!--  姓名-->
	       姓名
	    </td>
	    <td class="info_title_01" nowrap><!-- 部门 -->
	      部门
	    </td>
	    <td class="info_title_01" nowrap><!--(就餐)卡类别  -->
	     就餐卡类别
	    </td>
	    <td class="info_title_01" nowrap><!-- 就餐时间 -->
	     就餐时间
	    </td>
	    <td class="info_title_01" nowrap><!-- 就餐类型 -->
	     就餐类型
	    </td>
	    <td class="info_title_01" nowrap><!--班次  -->
	     班次
	    </td>  
	    <td class="info_title_01" nowrap><!--  员工类别-->
		员工类别
	    </td>
	  </tr>
	  <c:forEach items="${eatLookList}" var="all" varStatus="i">
		  <tr align="center">
		    <td class="info_content_01">
		    	${all.EMPID}&nbsp;
		    </td>
		    <td class="info_content_01">
		    	${all.CHINESENAME}&nbsp;
		    </td>
		    <td class="info_content_01">
		    	${all.DEPTNAME}&nbsp;
		    </td>
		   <td class="info_content_01">
		    	${all.TYPE_NAME}&nbsp;
		    </td>
		    <td class="info_content_01">
		    	${all.RTIME}&nbsp;
		    </td>
		    <td class="info_content_01">
		    	${all.EATERYTYPE}&nbsp;
		    </td>
		   <td class="info_content_01">
		    	${all.SHIFTNAME}&nbsp;
		    </td>
		    <td class="info_content_01">
		    	${all.CODE_NAME}&nbsp;
		    </td>
		  </tr>
	  </c:forEach>
	</table>
	
	 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=search&menu_code=${param.menu_code}&listDDS=${listDDS}&listHHS=${listHHS}&listMMS=${listMMS}&listDDE=${listDDE}&listHHE=${listHHE}&listMME=${listMME}&empName=${empName}&empID=${empID}&gm_type=${gm_type}&card_type=${card_type}&deptID=${deptID}" 
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
