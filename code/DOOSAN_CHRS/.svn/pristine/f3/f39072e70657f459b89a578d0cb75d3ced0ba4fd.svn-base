<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.*" errorPage="" %>
<jsp:include page="/inc/hrnav.jsp"/>
<%@ include file="../inc/import.jsp"%>
<%@ include file="../inc/taglibs.jsp"%>
<SCRIPT LANGUAGE="JavaScript" src="../js/sy0103.js"></SCRIPT>
<%@ page import="com.ait.sy.sy0103.bean.*"%>
<%
	String depth = request.getParameter("depth")!=null?request.getParameter("depth"):"0";

 %>
 <script type="text/javascript">   
var msg=new Array( '<ait:message messageID="alert.sys.maintenance.code.enter_code" module="sys"></ait:message>',				//请输入基本代码。
                   '<ait:message messageID="alert.sys.maintenance.code.code_length" module="sys"></ait:message>',				//您输入基本代码太长，请在20个字符以内。
                   '<ait:message messageID="alert.sys.maintenance.code.enter_codename" module="sys"></ait:message>',		//请输入基本代码名称。
				   '<ait:message messageID="alert.sys.maintenance.code.code_length" module="sys"></ait:message>',				//您输入基本代码名称太长，请在50个字符以内。
				   '<ait:message messageID="alert.sys.maintenance.code.basic_empty" module="sys"></ait:message>',				//基本代码不能为空。
				   '<ait:message messageID="alert.sys.maintenance.code.basic_empty_2" module="sys"></ait:message>',				//基本代码名称不能为空。
				   '<ait:message messageID="alert.sys.maintenance.code.basic_english" module="sys"></ait:message>'				//英文基本代码名称不能为空。
				);
</script>   
 
 
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
				<td align="left" class="title1" colspan="10"> 添加代码 
<!--		 <ait:message messageID="display.sys.basic_maintenance.code_management.code_description" module="sys"></ait:message>           		-->
				</td>
			</tr>
		</table>
		<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
		  </tr>
		  <tr>
		    <td class="line"><table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		           <form  name="EM2Form" method="post" action="/E<%=menu_code%>Control?flag=insert&menu_code=<%=menu_code%>" onSubmit="return CheckForm()" >
					  <input name="parentCode" type="hidden" id="BasicName2" value="0">
					  <input name="depth" type="hidden" value="<%=depth %>">
		
		        <tr>
		          <td height="30" class="info_title_01" width="15%"><div align="left"><!-- 基本代码 -->
		      <ait:message messageID="display.sys.basic_maintenance.code_management.basic_code" module="sys"></ait:message>                
		          </div></td>
		          <td class="tablecontent" width="85%">
		            <div align="justify">
		              <input name="basicCode" type="text" id="BasicCode">
		            </div>
		          </td>
		        </tr>
		        <tr>
		          <td height="14" class="info_title_01"><div align="left"><!--基本代码名称  -->
		        <ait:message messageID="display.sys.basic_maintenance.code_management.code_name" module="sys"></ait:message>              
		          </div></td>
		          <td class="tablecontent">
		            <div align="justify">
		              <input name="basicName" type="text" id="BasicName">
		           </div>
		         </td>
		        </tr>
		         <tr>
		          <td height="14" class="info_title_01"><div align="left"><!-- 英文基本代码名称 -->
		   <ait:message messageID="display.sys.basic_maintenance.code_management.code_name_english" module="sys"></ait:message>                   
		          </div></td>
		          <td class="tablecontent">
		            <div align="justify">
		              <input name="basicEnName" type="text" id="basicEnName">
		           </div>
		         </td>
		        </tr>
        <%if(!depth.equals("0")&& !depth.equals("1")){ %>
			 <tr>  
		      <td height="14" class="info_title_01" width="15%" nowrap="nowrap"><div align="left">
		       法人区分  
		      </div></td>
		      <td class="info_content_01">                           
		        <div align="justify">
		          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" />
		       </div>
		     </td>
		    </tr>  
       <%}%> 
		      </form>
		    </table></td>
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

