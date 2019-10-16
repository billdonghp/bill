<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*,java.lang.*"%>
<%@ page import="com.ait.utils.EntEmpTree"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/Style.css" rel="stylesheet" type="text/css">
</head>
<style  type="text/css">
img.hand{cursor:hand;}
</style>
<body topmargin="0" marginheight="0" >
<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
	<td valign="top" >
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<form name="emptree">
		<tr>
		<td class="line"><input name='上传' value="选择" onclick=" send()" class="content" type="button" style="width:50px ">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td height="5" colspan="6"></td>
			</tr><%
			String deptid="";
			EntEmpTree entEmpTree = new EntEmpTree();
			Vector vlist = new Vector();
			vlist = entEmpTree.getDeptSingleTree();
			for ( int i = 0 ; i < vlist.size() ; i++ )
			{
				entEmpTree=(EntEmpTree)vlist.elementAt(i) ;
				if (entEmpTree.getDeptLevel()==0) {
				if (!deptid.equals(entEmpTree.getDeptID())) {%>
				<tr>
					<td width="12" height="22"><img src="../images/left_menubullet_main_m.gif" width="14" height="22" class='hand'  onClick="show('<%=entEmpTree.getDeptID()%>_0')"></td>
					<td colspan="5" class="left_menu_1depth"><b>&nbsp;
					<%deptid = entEmpTree.getDeptID();
					out.print(entEmpTree.getDeptName());
					%></b></td>
				</tr>
				<%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_0" style="display:none ">
					<td width="12" height="22">&nbsp;</td>
				  <td colspan="5" class="left_menu_1depth">&nbsp;<a href="javascript:send('<%=entEmpTree.getEmpID()%>')"><%=entEmpTree.getChineseName()%></a></td>
				</tr><%}}else{
				if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_0" style="display:none ">
					<td width="12" height="22">&nbsp;</td>
				  <td colspan="5" class="left_menu_1depth">&nbsp;<a href="javascript:send('<%=entEmpTree.getEmpID()%>')"><%=entEmpTree.getChineseName()%></a></td>
				</tr><%}}}else if (entEmpTree.getDeptLevel()==1){
				if (!deptid.equals(entEmpTree.getDeptID())) {%>
				<tr>
				<td width="12" height="22">&nbsp;	</td>
				<td width="30" align="left" class="left_menu_1depth"><img src="../images/left_menubullet_sub_node_02.gif" width="14" height="22"><img src="../images/left_menubullet_main_m.gif" width="14" height="22" class='hand' onClick="show('<%=entEmpTree.getDeptID()%>_1')"></td>
				<td colspan="4" class="left_menu_1depth"><b>&nbsp;
				<%deptid = entEmpTree.getDeptID();
				out.print(entEmpTree.getDeptName());
				%>
				</b></td>
				</tr><%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_1" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td colspan="4" class="left_menu_1depth"><a href="javascript:send('<%=entEmpTree.getEmpID()%>')"><%=entEmpTree.getChineseName()%></a></td>
				</tr><%}}else{%>
				<%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_1" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td colspan="4" class="left_menu_1depth"><a href="javascript:send('<%=entEmpTree.getEmpID()%>')"><%=entEmpTree.getChineseName()%></a></td>
				</tr><%}}}else if (entEmpTree.getDeptLevel()==2){
				if (!deptid.equals(entEmpTree.getDeptID())) {%>
				<tr>
				<td width="12" height="22">&nbsp;</td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" align="left"><img src="../images/left_menubullet_sub_node_02.gif" width="14" height="22"><span class="left_menu_1depth"><b><img src="../images/left_menubullet_main_m.gif" width="14" height="22" class='hand' onClick="show('<%=entEmpTree.getDeptID()%>_2')"></b></span></td>
				<td colspan="3" class="left_menu"><span class="left_menu_1depth"><b>&nbsp;
				<%deptid = entEmpTree.getDeptID();
				out.print(entEmpTree.getDeptName());
				%>
				</b></span></td>
				</tr><%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_2" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td colspan="3" class="left_menu_1depth"><a href="javascript:send('<%=entEmpTree.getEmpID()%>')"><%=entEmpTree.getChineseName()%></a></td>
				</tr><%}}else{%>
				<%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_2" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td colspan="3" class="left_menu_1depth"><a href="javascript:send('<%=entEmpTree.getEmpID()%>')"><%=entEmpTree.getChineseName()%></a></td>
				</tr>
				<%}}}else  if (entEmpTree.getDeptLevel()==3){
				if (!deptid.equals(entEmpTree.getDeptID())) {%>
				<tr>
				<td width="12" height="22"></td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu"><img src="../images/left_menubullet_sub_node_02.gif" width="14" height="22"><img src="../images/left_menubullet_main_m.gif" width="14" height="22" class='hand' onClick="show('<%=entEmpTree.getDeptID()%>_3')"></td>
				<td colspan="2" valign="middle" class="left_menu"><span class="left_menu_1depth"><b>&nbsp;
                      <b>
                      <%deptid = entEmpTree.getDeptID();
				out.print(entEmpTree.getDeptName());
				%>
                  </b> </b></span></td>
				</tr><%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_3" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td valign="middle" class="left_menu_1depth" colspan="2" ><a href="javascript:send('<%=entEmpTree.getEmpID()%>')"><%=entEmpTree.getChineseName()%></a></td>
				</tr><%}}else{%>
				<%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_3" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td valign="middle" class="left_menu_1depth" colspan="2" ><a href="javascript:send('<%=entEmpTree.getEmpID()%>')"><%=entEmpTree.getChineseName()%></a></td>
				</tr>
				<%}}}else{
				if (!deptid.equals(entEmpTree.getDeptID())) {%>
				<tr>
				<td width="12" height="22">&nbsp;</td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td><span class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></span></td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td width="30" class="left_menu"><img src="../images/left_menubullet_sub_node_02.gif" width="14" height="22"><img src="../images/left_menubullet_main_m.gif" width="14" height="22" onClick="show('<%=entEmpTree.getDeptID()%>_4')"></td>
				<td valign="middle" class="left_menu"><span class="left_menu_1depth"><b>&nbsp;
                      <b>
                      <%deptid = entEmpTree.getDeptID();
				out.print(entEmpTree.getDeptName());
				%>
                  </b> </b></span></td>
				</tr><%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_4" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td><span class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></span></td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td class="left_menu_1depth">&nbsp;</td>
				<td valign="middle" class="left_menu_1depth"><a href="javascript:send('<%=entEmpTree.getEmpID()%>')"><%=entEmpTree.getChineseName()%></a></td>
				</tr><%}}else{%>
				<%if (!entEmpTree.getChineseName().equals("")){ %>
				<tr id="<%=entEmpTree.getDeptID()%>_4" style="display:none ">
				<td width="12" height="22">&nbsp;</td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td><span class="left_menu_1depth"><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></span></td>
				<td><img src="../images/left_menubullet_sub_node01.gif" width="14" height="22"></td>
				<td class="left_menu_1depth">&nbsp;</td>
				<td valign="middle" class="left_menu_1depth"><a href="javascript:send('<%=entEmpTree.getEmpID()%>')"><%=entEmpTree.getChineseName()%></a></td>
				</tr><%}}}}%>
			</table>
		</td>
		</tr></form>
	</table>
	<span class="line">
	<input  value="选择" onclick=" send()" class="content" type="button" style="width:50px ">
	</span></td>
</tr>
</table>

</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
function show(id)
{
	try {
		target = document.all(id);
		if (target) {
			if (target.length){
				for (i=0;i<target.length;i++){
					if (target[i].style.display=='none')
					{target[i].style.display='';}
					else  {target[i].style.display='none';}
				}
			}
			else{
				if (target.style.display=='none')
					{target.style.display='';}
					else  {target.style.display='none';}
			}
		}
	} catch (e) {alert(e);}
}

function send(empid)
{
	
	opener.document.all.empid<%=request.getParameter("no")%>.value=empid;
	self.close();
}
//-->
</SCRIPT>
