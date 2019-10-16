<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.util.*,java.util.*" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="codemap" class="java.util.HashMap"/>
<jsp:useBean id="codeVector" class="java.util.Vector"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
	<!--
		function CheckForm(){
			if(document.EM2Form.groupName.value == ""){
			//"组名不能为空"
				alert('<ait:message  messageID="alert.att.setting.item.chinese_name" module="ar"/>');
				document.EM2Form.groupName.focus();
				return false;
			}
			return true;
		}
		function Save(){
			if(CheckForm()){
				document.EM2Form.submit();
			}
		}
		function getGroupType(){
			document.EM2Form.operation.value="ar_pagecontrol";
			document.EM2Form.page.value ="dynamicGroupAdd";
			document.EM2Form.submit();
		}
	-->
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
			<%@ include file="../inc/common_toolbar_a.jsp"%> 
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
				<td align="left" class="title1" colspan="10"><!--动态组-->
					<ait:message  messageID="display.mutual.schedule_pattern"/></td>
			</tr>
		</table>
		<table width="100%"  border="1" align="center" cellpadding="10" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		      <form  name="EM2Form" method="post" action="arControlServlet">
			  	<input type="hidden" name="operation" value="ar_add">
			  	<input type="hidden" name="page" value="">
			  	<input type="hidden" name="content" value="dynamicGroup">
			  	<input type="hidden" name="menu_code" value="<%=StringUtil.checkNull(request.getParameter("menu_code"))%>">
		        <tr>
		          <td height="15%" class="info_title_01"><!--组名-->
					<ait:message  messageID="display.att.setting.items.edit.name_chinese" module="ar"/></td>
		          <td width="75%" class="info_content_00">
		            <div align="left">
		              <input width="300" name="groupName" type="text" value="<%=StringUtil.toCN(StringUtil.checkNull(request.getParameter("groupName")))%>" >
		          </div>
		          </td>
		        </tr>
		        <tr>
		          <td height="15%" class="info_title_01"><!--英文组名-->
					<ait:message  messageID="display.att.setting.items.edit.name_english" module="ar"/></td>
		          <td width="75%" class="info_content_00">
		            <div align="left">
		              <input width="300" name="groupEnName" type="text" value="<%=StringUtil.toCN(StringUtil.checkNull(request.getParameter("groupEnName")))%>" >
		          </div>
		          </td>
		        </tr>
		        <tr>
		          <td height="15%" class="info_title_01"><!--韩文组名-->
					<ait:message  messageID="display.att.setting.items.edit.name_korean" module="ar"/></td>
		          <td width="75%" class="info_content_00">
		            <div align="left">
		              <input width="300" name="groupKoName" type="text" value="<%=StringUtil.toCN(StringUtil.checkNull(request.getParameter("groupKoName")))%>" >
		          </div>
		          </td>
		        </tr>
		        <tr>
		          <td height="30" class="info_title_01"><!--组描述-->
					<ait:message  messageID="display.mutual.description"/></td>
		          <td class="info_content_00">
		            <div align="justify">
		              <input width="300" name="description" type="text" value="<%=StringUtil.toCN(StringUtil.checkNull(request.getParameter("description")))%>" >
		          </div></td>
		        </tr>
		<!--         <tr>
		          <td height="30" class="tablecontent">组性质</td>
		          <td class="tablecontent">
		              <select name="groupProperty" onChange="javaScript:getGroupType();">
		                <option value="" selected>请选择组性质</option>
		              <%
		                try {
		                  codeVector = SysCodeParser.getCode("GroupProperty");
		                  for (int i = 0; i < codeVector.size(); i++) {
		                    codemap = (HashMap) codeVector.elementAt(i);
		              %>
		                <option value="<%=codemap.get("code")%>" <%if(StringUtil.checkNull(request.getParameter("groupProperty")).equals(codemap.get("code"))){out.print("selected");}%>><%=codemap.get("name")%></option>
		              <%
		                }
		                } catch (Exception e) {
		                }
		              %>
		              </select>		  
				  </td>
		        </tr>
		        <tr>
		          <td height="30" class="tablecontent">组限定</td>
		          <td class="tablecontent"><select name="groupQualification">
		            <option value="" selected>选择组限定</option>
		            <%
		                try {
		                  codeVector = SysCodeParser.getCode("GroupQualification");
		                  for (int i = 0; i < codeVector.size(); i++) {
		                    codemap = (HashMap) codeVector.elementAt(i);
		              %>
		            <option value="<%=codemap.get("code")%>" <%if(StringUtil.checkNull(request.getParameter("groupQualification")).equals(codemap.get("code"))){out.print("selected");}%>><%=codemap.get("name")%></option>
		            <%
		                }
		                } catch (Exception e) {
		                }
		              %>
		          </select></td>
		        </tr> -->
				<%
					if(StringUtil.checkNull(request.getParameter("groupProperty")).equals("SysGroup")){
				%>
		        <tr>
		          <td height="30" class="tablecontent">组类型</td>
		          <td class="tablecontent">
				              <%
		                try {
		                  codeVector = SysCodeParser.getCode("SysGroupType");
		                  for (int i = 0; i < codeVector.size(); i++) {
		                    codemap = (HashMap) codeVector.elementAt(i);
		              %>
					  
				  <input name="sysGroupType" type="radio" class="check" value="<%=codemap.get("code")%>"  <%if(StringUtil.checkNull(request.getParameter("sysGroupType")).equals(codemap.get("code"))){out.print("Checked");}%>>  
				  <%=codemap.get("name")%>
					  <%
		                }
		                } catch (Exception e) {
		                }
		              %></td>
		        </tr>
				<%}%>
		      </form>
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

</body>
</html>
