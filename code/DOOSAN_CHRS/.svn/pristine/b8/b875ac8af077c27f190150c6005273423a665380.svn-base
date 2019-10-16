<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.*" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.sy.sy0103.bean.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
</head>
<body>
<jsp:include page="/inc/hrnav.jsp"/>
<%@ include file="../inc/import.jsp"%>
<SCRIPT LANGUAGE="JavaScript" src="../js/sy0103.js"></SCRIPT>
	<%
		Ent Ent = new Ent();
		vlist = Biz.Detail(no);
        Ent=(Ent)vlist.elementAt(0) ;
	%>
<%@ include file="../sy/sy0103checknull.jsp" %>             

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../sy/inc/sy_toolbar_a.jsp"%>  
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
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 代码修改 -->
		  <ait:message messageID="display.sys.basic_maintenance.code_management.code_description" module="sys"></ait:message>   		
				</td>
			</tr>
		</table>
		<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="1"></td>
		  </tr>
		  <tr>
		    <td class="line">
			    <table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			      <form  name="EM2Form" method="post" action="/E<%=menu_code%>Control?flag=updata&menu_code=<%=menu_code%>&no=<%=no%>" onSubmit="return CheckForm()" >
				    <input name="parentCode" type="hidden" id="parentCode" value="0">
			        <tr>
			          <td height="30" class="info_title_01"><div align="left"><!--基本代码  -->
		  <ait:message messageID="display.sys.basic_maintenance.code_management.basic_code" module="sys"></ait:message>   	          
			          </div></td>
			          <td class="tablecontent">
			            <div align="justify">
			              <input name="basicCode" type="hidden" value="<%=Ent.getBasicCode()%>"><%=Ent.getBasicCode()%>
			            </div>
			          </td>   
			        </tr>
			        <tr>
			          <td height="14" class="info_title_01"><div align="left"><!-- 基本代码名称 -->
		  <ait:message messageID="display.sys.basic_maintenance.code_management.code_name" module="sys"></ait:message>   	          
			          </div></td>
			          <td class="tablecontent">
			            <div align="justify">
			              <input name="basicName" type="text" value="<%=Ent.getBasicName()%>">
			           </div>
			         </td>
			        </tr>
			         <tr>
		          <td height="14" class="info_title_01"><div align="left"><!-- 英文基本代码名称 -->
		    <ait:message messageID="display.sys.basic_maintenance.code_management.code_name_english" module="sys"></ait:message>           
		          </div></td>
		          <td class="tablecontent">
		            <div align="justify">
		              <input name="basicEnName" type="text" value="<%=Ent.getCodeEnName()%>">
		           </div>
		         </td>
		        </tr>
		     <%if(Ent.getDepth()!=0 && Ent.getDepth()!=1){%>
		    <tr>  
		    
		      <td height="14" class="info_title_01" width="15%" nowrap="nowrap"><div align="left">
		       法人区分  
		      </div></td>
		      <td class="info_content_01">                           
		        <div align="justify">
		          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="<%=Ent.getCpnyId()%>"/>
		       </div>
		     </td>
          </tr>   
            <%}%>
			      </form>
			    </table>
		    </td>
		  </tr>
		</table>

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

