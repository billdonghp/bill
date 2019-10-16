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

function hrefSearchsubmit(){
 	document.form1.action="/gmControlServlet?operation=hrefSearchsubmit&menu_code=${param.menu_code}";
 	document.form1.submit();
}

function hrefDeletesubmit(exceptionId){
	if(confirm('确定删除吗？')){	
	 	document.form1.action="/gmControlServlet?operation=hrefDeletesubmit&exceptionId="+exceptionId+"&menu_code=${param.menu_code}";
	 	document.form1.submit();
 	}
}

function Add(){
	document.form1.action="gmControlServlet?menu_code=ga0705&operation=eateryAction&content=addView";
	document.form1.submit();
	
}
</script> 

<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");		
String today=timeFormatter.format(d);
String today1=timeFormatter1.format(d);
%>
<form name="form1" method="post" action="">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_add.jsp"%>
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
	          <input type="text" name="listDDS" value="${listDDS}" onClick="setday(this);" readonly style="width:70px">
	          </td>
	          <td nowrap="nowrap" class="info_title_01"><!--  开始日期-->
	          	结束时间
	          </td>
	          
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="listDDE" value="${listDDE}" onClick="setday(this);" readonly style="width:70px">
	          </td>

			 <td nowrap="nowrap" class="info_title_01">
				<img src="../images/btn/Search.gif" onClick="hrefSearchsubmit()">
			</td>
	          
	        </tr>
	        </table>
         <BR>
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 加班申请 -->
				
					就餐异常查看
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
	    <td class="info_title_01" nowrap><!--  姓名-->
	       异常日期
	    </td>
	    <td class="info_title_01" nowrap><!-- 部门 -->
	      餐别
	    </td>
	    <td class="info_title_01" nowrap><!--(就餐)卡类别  -->
	     备注
	    </td>
	    <td class="info_title_01" nowrap><!-- (就餐)卡号 -->
	     创建时间
	    </td>
	    <td class="info_title_01" nowrap><!-- 就餐时间 -->
	     创建者
	    </td>
	    <td class="info_title_01" nowrap><!-- 就餐时间 -->
	     操作
	    </td>
	  </tr>
	  <c:forEach items="${LookList}" var="all" varStatus="i">
		  <tr align="center">
		    <td align="center" style="color: #666666;">
		    	${all.GM_EXCEPTION_DATE}&nbsp;
		    </td>
		    <td align="center" style="color: #666666;">
		    	${all.GM_EXCEPTION_EATERY}&nbsp;
		    </td>
		    <td width='200' style='word-break:break-all' align="left">
		    	${all.GM_EXCEPTION_REMARK}&nbsp;
		    </td>
		    <td align="center" style="color: #666666;">
		    	${all.GM_EXCEPTION_CREATEDATE}&nbsp;
		    </td>
		    <td align="center" style="color: #666666;">
		    	${all.CHINESENAME}&nbsp;
		    </td>
		    <td align="center" style="color: #666666;">
		    	<img src="../images/btn/Delete.gif" onClick="hrefDeletesubmit(${all.GM_EXCEPTION_ID})">
		    </td>
		  </tr>
	  </c:forEach>
	</table>
	
	 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=searh&menu_code=${param.menu_code}" 
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
