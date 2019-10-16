<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="com.ait.utils.*" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@page import="java.util.*"%>
<SCRIPT LANGUAGE="JavaScript" src="../evs/js/evtable.js"></SCRIPT>
<%
String menu_code ="";
String rodeLevel ="";
ArrayList tool_menu=null;
menu_code = request.getParameter("menu_code");
 admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
ToolMenu toolmenu = new ToolMenu();
MenuBiz menubiz = new MenuBiz();
tool_menu=menubiz.toolMenuList(menu_code,rodeLevel);
String imgname = menu_code.substring(0,2);
EvsMaster evsMasters=new EvsMaster();
List lEvsEmp=null;
String daochu = "0";
String fasong = "0";
try{
  
	lEvsEmp=evsMasters.getEvsPersonDaochu("RS");//大的 

	if(admin.getAdminID()!=null&&(admin.getAdminID().equals("1464498")||admin.getAdminID().equals("1464086")||admin.getAdminID().equals("9999901")))
		fasong="1";
	if(lEvsEmp!=null ){
		int lEvsEmp2Size=lEvsEmp.size();
		for(int i=0;i<lEvsEmp2Size;i++){
			EvsMaster evsMEmp=(EvsMaster)lEvsEmp.get(i);
			
			if(admin.getAdminID()!=null&&admin.getAdminID().equals(evsMEmp.getEvMaster()))
				daochu="1";
		}
	}
}catch(Exception e){
	e.printStackTrace();
}
int insertMenu=0;
int updateMenu=0;
int deleteMenu=0;
for(int i=0;i<tool_menu.size();i++){
	toolmenu=(ToolMenu)tool_menu.get(i);
	if(toolmenu.getInsertr()==1){
		insertMenu=1;
	
	}
	if(toolmenu.getUpdate()==1){
		updateMenu=1;
	
	}
	if(toolmenu.getDelect()==1){
		deleteMenu=1;
	
	}
}
%>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" valign="middle">
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			<tr>
			 <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
			 <td align="right" valign="middle" style="padding:3 0 3 0 ">         	  
		          <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand" />	         
             
				 <% if("1".equals(daochu)){%> 			         						 
					<ait:image src="/images/btn/Excel_Exp.gif"  border="0" align="absmiddle" onclick="javascript:exportEXL();" style="cursor:hand" />			 
				   <%} %>
				   <% if("1".equals(fasong)){%> 			         						 
	  
				  <ait:image src="/images/btn/SendMail2.gif"  border="0" align="absmiddle" onclick="sendMailPart();" style="cursor:hand" />          	
				   <%} %>
				    </td> 
			</tr>
		</table>
		</td>
	</tr>

</table>
