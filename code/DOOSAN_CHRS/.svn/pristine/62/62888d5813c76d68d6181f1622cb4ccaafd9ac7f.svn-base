<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<jsp:useBean id="listMM" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="listHH" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gmType" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="LookList" class="java.util.ArrayList" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function Search(){
 	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=eatStatistic&content=arrangementView&flag=view";
 	document.form1.submit();
}
function ImportExcel(){
	document.form1.action="/gmControlServlet?operation=eatStatistic&content=arrangementView&menu_code=${param.menu_code}&flag=excel";
	document.form1.submit();
}

function Delete(){
  var selectFlag=false;
   var countsArr=document.getElementsByName("arrangementDate");
   for(var i=0;i<countsArr.length;i++){
      if(countsArr[i].checked){
       selectFlag=true;
      }   
   }
   if(!selectFlag){
     alert("请选择要修改的信息！");
     return;
   }else{
	if(confirm('确定删除吗？')){	
	 	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=eatStatistic&content=arrangementDelete";
	 	document.form1.submit();
 	}
  }
}

function Add(){
	document.form1.action="/gmControlServlet?operation=eatStatistic&content=arrangementAddView&menu_code=${param.menu_code}";
	document.form1.submit();
	
}

function Update(){
   var selectFlag=false;
   var countsArr=document.getElementsByName("arrangementDate");
   for(var i=0;i<countsArr.length;i++){
      if(countsArr[i].checked){
       selectFlag=true;
      }   
   }
   if(!selectFlag){
     alert("请选择要修改的信息！");
     return;
   }else{
	document.form1.action="/gmControlServlet?operation=eatStatistic&content=arrangementUpdateView&menu_code=${param.menu_code}";
	document.form1.submit();
   }
}
function viewFile(fileAddress){
window.open(fileAddress,"","menubar=no, toolbar=no, scrollbars=no, status=no, resizable=yes, location=no, directories=no, copyhistory=no" );
}


</script> 
<form name="form1" method="post" action="">
<input name="listSize" value="${fn:length(arrangementList)}" type="hidden">
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
	         <td nowrap="nowrap" class="info_title_01" ><!--  开始日期-->
	          	开始时间 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="startDate" value="${startDate}" onClick="setday(this);" readonly style="width:70px">
	          </td>
	          <td nowrap="nowrap" class="info_title_01"><!--  开始日期-->
	          	结束时间
	          </td>
	          
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="endDate" value="${endDate}" onClick="setday(this);" readonly style="width:70px">
	          </td>

			 <td nowrap="nowrap" class="info_title_01">
				<img src="../images/btn/Search.gif" onClick="Search()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="javascript:ImportExcel();"/>
			</td>
		
	        </tr>
	        </table>

		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 加班申请 -->
				
					就餐安排
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
	  
	  <tr>
	    <td class="info_title_01" nowrap rowspan="3">
	      选择
	    </td>
	    <td class="info_title_01" nowrap rowspan="3">
	      日期
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	      早餐
	    </td>
	    <td class="info_title_01" nowrap colspan="4">
	     午餐
	    </td>
	    <td class="info_title_01" nowrap colspan="4">
	     晚餐
	    </td>
	    <td class="info_title_01" nowrap  colspan="2">
	     夜餐
	    </td>
	    <td class="info_title_01" nowrap colspan="40">
	     班车
	    </td>
	    <td class="info_title_01" nowrap rowspan="3">
	     菜谱
	    </td>
	    <td class="info_title_01" nowrap rowspan="3">
	     备注
	    </td>
	  </tr>
	  <tr>	   
	    <td class="info_title_01" nowrap colspan="2">
	      &nbsp;
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     中餐
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     韩餐
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     中餐
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     韩餐
	    </td>
	   <td class="info_title_01" nowrap colspan="2">
	     &nbsp;
	    </td>
	    
	     <td class="info_title_01" nowrap colspan="2">
	     牟平17点
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     牟平20点
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     牟平21点
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     牟平夜班
	    </td>
	     <td class="info_title_01" nowrap colspan="2">
	    开发区17点
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	    开发区20点
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	    开发区21点
	    </td>
	     <td class="info_title_01" nowrap colspan="2">
	    开发区夜班
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     福山17点
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     福山20点
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	      福山21点
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     福山夜班
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     莱山17点
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     莱山20点
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	     莱山21点
	    </td>
	     <td class="info_title_01" nowrap colspan="2">
	     莱山夜班
	    </td>
	    <td class="info_title_01" nowrap colspan="2">
	    芝罘17点
	    </td>
	     <td class="info_title_01" nowrap colspan="2">
	    芝罘20点
	    </td>	
	      <td class="info_title_01" nowrap colspan="2">
	    芝罘21点
	    </td>	
	      <td class="info_title_01" nowrap colspan="2">
	    芝罘夜班
	    </td>		   
	  </tr>	 
	  	<tr>	   
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	   	<td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	     <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	     <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	     <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	     <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	     <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	    <td class="info_title_01" nowrap>
	      人数
	    </td>
	    <td class="info_title_01" nowrap>
	     状态
	    </td>
	  
	  </tr>	 
	  <c:forEach items="${arrangementList}" var="test" varStatus="i">
	  <tr>
	    <td class="info_content_01" nowrap>
	    <input type="checkbox" name="arrangementDate" value="${test.ARRANGEMENT_DATE}"/>		     
	    </td>	   
	    <td class="info_content_01" nowrap>
	      ${test.ARRANGEMENT_DATE}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	      ${test.BREAKCHINESENUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>	    
	      ${test.BREAKCHINESEFLAG}&nbsp;
	    </td>
	   	 <td class="info_content_01" nowrap>
	     ${test.LUNCHCHINESENUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LUNCHCHINESEFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LUNCHKOREANNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	    ${test.LUNCHKOREANFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	      ${test.SUPPERINESENUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.SUPPERCHINESEFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	      ${test.SUPPERKOREANNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.SUPPERKOREANFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	      ${test.DINNERCHINESENUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.DINNERCHINESEFLAG}&nbsp;
	    </td>
	     <td class="info_content_01" nowrap>
	     ${test.LINE_SEVENNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_SEVENFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_ONENUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_ONEFLAG}&nbsp;
	    </td>
	     <td class="info_content_01" nowrap>
	     ${test.LINE_SEVENTEENNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_SEVENTEENFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_SIXNUM}&nbsp;
	    </td>
	    
	    <td class="info_content_01" nowrap>
	     ${test.LINE_SIXFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	      ${test.LINE_TWONUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_TWOFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	      ${test.LINE_EIGHTNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_EIGHTFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	      ${test.LINE_SIXTEENNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_SIXTEENFLAG}&nbsp;
	    </td>
	     <td class="info_content_01" nowrap>
	      ${test.LINE_TWELVENUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_TWELVEFLAG}&nbsp;
	    </td>
	     <td class="info_content_01" nowrap>
	      ${test.LINE_THREENUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_THREEFLAG}&nbsp;
	    </td>
	     <td class="info_content_01" nowrap>
	      ${test.LINE_NINENUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_NINEFLAG}&nbsp;
	    </td>
	     <td class="info_content_01" nowrap>
	      ${test.LINE_EIGHTEENNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_EIGHTEENFLAG}&nbsp;
	    </td>
	     <td class="info_content_01" nowrap>
	      ${test.LINE_THIRTEENNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_THIRTEENFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	      ${test.LINE_FOURNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_FOURFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	      ${test.LINE_TENNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_TENFLAG}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	      ${test.LINE_TWENTYNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_TWENTYFLAG}&nbsp;
	    </td>
	     <td class="info_content_01" nowrap>
	      ${test.LINE_FOURTEENNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_FOURTEENFLAG}&nbsp;
	    </td>
	     <td class="info_content_01" nowrap>
	       ${test.LINE_FIVENUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	      ${test.LINE_FIVEFLAG}&nbsp;
	    </td>	
	    <td class="info_content_01" nowrap>
	     ${test.LINE_ELEVENNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_ELEVENFLAG}&nbsp;
	    </td>
	     <td class="info_content_01" nowrap>
	     ${test.LINE_NINETEENNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_NINETEENFLAG}&nbsp;
	    </td>
	     <td class="info_content_01" nowrap>
	     ${test.LINE_FIFTEENNUM}&nbsp;
	    </td>
	    <td class="info_content_01" nowrap>
	     ${test.LINE_FIFTEENFLAG}&nbsp;
	    </td>
	    
	    
	   
	    
	   
	    <td class="info_content_01" nowrap>
	    <c:if test="${test.FILE_ADDRESS!=null}">
	     <a href="#" onclick="viewFile('${test.FILE_ADDRESS}');"><font color="red">${test.RECIPES_NAME}</font></a>
	    </c:if>	 
	    <c:if test="${test.FILE_ADDRESS==null}">
	     无
	    </c:if>    
	    </td>	  
	    <td class="info_textarea_content">
	    ${test.REMARK}&nbsp;
	    </td>	
	  </tr>	 
	  
	  </c:forEach>
	</table>
	
	
	 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gmControlServlet"
		               parameters="&menu_code=${param.menu_code}&operation=eatStatistic&content=arrangementView&startDate=${startDate}&endDate=${endDate}" 
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
