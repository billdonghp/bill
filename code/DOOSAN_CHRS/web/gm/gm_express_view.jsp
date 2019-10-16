<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>历史记录</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript"> 
function CheckNumber(tempvalue){   
    var   patrn=/^[0-9]+.{0,1}[0-9]{0,3}$/;
    if  (!patrn.test(tempvalue)){   
       alert("输入数字！");   
       return  false;   
      }   
       return true; 
}

function ImportExcel() {
	
	document.form2.action="/gmControlServlet?menu_code=${param.menu_code}&operation=expressManger&content=expressViewExcel";
	document.form2.submit();

}

function search(){
document.form2.action="/gmControlServlet?operation=expressManger&content=expressView&menu_code=${param.menu_code}";
document.form2.submit();
}

function confirmCosts(applyno,indexV){
document.form1.action="/gmControlServlet?operation=expressManger&content=confirmCosts&menu_code=${param.menu_code}&applyno="+applyno+"&Costs="+document.form1["Costs"+indexV].value;
document.form1.submit();
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
			<%@ include file="../inc/common_toolbar_none.jsp"%>
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
	<form action="" name="form2" method="post">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		
		<tr>
			<td class="title1"><!-- 查询条件 -->
			<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>
			</td>
		</tr>
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	         <tr>
	          <td width="10%" class="info_title_01"><!--  开始日期-->
	          	开始时间 
	          </td>
	          <td width="15%" class="info_content_00">
	          	<input type="text" style="width:80px" name="StartDate" value="${StartDate}" onClick="setday(this);">
	          </td>
	          <td width="10%" class="info_title_01"><!--结束日期  -->
	             结束时间
	          </td>
	          <td width="15%" class="info_content_00">
	          	<input type="text" style="width:80px" name="EndDate" value="${EndDate}" onClick="setday(this);">
	          </td>
	          <td width="10%" class="info_title_01"><!--结束日期  -->
	            寄达城市
	          </td>
	          <td width="15%" class="info_content_00" colspan="2">
	          	<input id="citysendto" name="citysendto" type="text" size="15" value="${citysendto}">
	          </td>	         
	        </tr>
	        <tr>
		      <td width="10%" class="info_title_01">
				  快递单号
	          </td>
	          <td width="15%" class="info_content_00">
	          	<input type="text" style="width:80px" name="postNumber" value="${postNumber}">
	          </td>
	          <td width="10%" class="info_title_01"><!--结束日期  -->
	             申请部门
	          </td>
	          <td width="15%" class="info_content_00">
	          	<ait:selDept name="deptID" selected="${deptID}" all="请选择" supervisorType="pa"/>
	          </td>
	          <td width="10%" class="info_title_01"><!--结束日期  -->
	            发件人
	          </td>
	          <td width="15%" class="info_content_00">
	          	<input id="sendPerson" name="sendPerson" type="text" size="6" value="${sendPerson}">
	          </td>	         
	          <td width="15%" class="info_content_00">
          		<ait:image src="/images/btn/Search.gif" align="absmiddle"
         				onclick="javascript:search();" style="cursor:hand" />
         		<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="javascript:ImportExcel();"/>
         				
	          </td>
	        </tr>
	      </table>	      
	      </td>
		</tr>
		</table>
		</form>
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		<form action="" name="form1" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				历史记录
				</td>
			</tr> 
		</table>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
			  <td nowrap="nowrap" class="info_title_01">
				序号</td>
			  <td nowrap="nowrap" class="info_title_01">
				寄件日期</td>
			 <td nowrap="nowrap" class="info_title_01">
				申请日期</td>     
		      <td nowrap="nowrap" class="info_title_01">
				申请部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请人</td>
			  <td nowrap="nowrap" class="info_title_01">
				发件人</td>
			  <td nowrap="nowrap" class="info_title_01">
				寄达城市</td>
		      <td nowrap="nowrap" class="info_title_01">
				收件单位全称</td>
			  <td nowrap="nowrap" class="info_title_01">
				收件人</td>	
			  <td nowrap="nowrap" class="info_title_01">
				快件种类</td>	
			 <td nowrap="nowrap" class="info_title_01">
				 快递单号</td>
		      <td nowrap="nowrap" class="info_title_01">
				邮件内容 </td>
			  <td nowrap="nowrap" class="info_title_01">
				邮件费 </td>
			  <td nowrap="nowrap" class="info_title_01">
				操作</td>			  	 	     
		    </tr>
		 <c:forEach items="${expressConfirmInfoList}" var="varTest" varStatus="i">
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >${i.index+1}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.MYDATE} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.MYAPPLYDATE} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.DEPTNAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.APPLYOR}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.CHINESENAME}</td>	
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CITYARRIVE}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.POSTADDRESS}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.SENDTOPERSON} </td>	
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CODE_NAME} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.POSTNUMBER} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.POSTDESCRIPTION} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01"><input name="Costs${i.index}" type="text" value="${varTest.COSTS}" style="width:60" onkeyup="CheckNumber(this.value)">&nbsp;元</td>
		      <td nowrap="nowrap" align="center" class="info_content_01"><a href="#" style="color:red" onclick="confirmCosts('${varTest.APPLY_NO}','${i.index}')"><img src="/images/btn/p_update.gif" align="absMiddle" style="cursor:hand"></a></td>	     	     	     	     
		            
			 </tr>	
		</c:forEach>
		<input type="hidden" name="currentPage" value="${currentPage}">
		</form>		
		 </table>
		 		
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=expressManger&content=expressView&menu_code=${param.menu_code}&postNumber=${postNumber}&StartDate=${StartDate}&EndDate=${EndDate}&sendPerson=${sendPerson}&citysendto=${citysendto}&deptID=${deptID}" 
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
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;visibility: hidden;">   
	</div>
</body>

</html>
