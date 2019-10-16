<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.*,com.ait.utils.*,com.ait.sy.bean.*,com.ait.util.StringUtil" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/default.css">
</head>
<body>
<jsp:include page="/inc/hrnav.jsp"/>   
<%@ page import="com.ait.sy.sy0101.bean.*"%>
<%@ page import="com.ait.sy.sy0101.bean.Ent"%>  
 <%@ page import="java.net.URLDecoder"%>    
 
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.select_edit_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.select_delete_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.associated_deleting" module="sys"></ait:message>');
</script>   
<%@ include file="../inc/import.jsp"%>
<script language="javascript">
MENU_CODE='<%=menu_code%>';  
</script>
	<%
		request.setCharacterEncoding("UTF-8");
		boolean isSearch=false;
		String key=null;          
		String where=null;    
		if(request.getParameter("key")!=null&&request.getParameter("where")!=null)   
		{	
			             
			key = request.getParameter("key");                                               
			where=request.getParameter("where");         
			isSearch=true;                
		}               
		Ent Ent = new Ent();                    
		String x = request.getParameter("strPage");
		String y= request.getParameter("bigpage");       
		PageControl pc=new PageControl(10,10);  
		int bigpage=pc.getTmpBig(y);
		int strPage=pc.getTmpSmall(x,bigpage);

		
		if(!isSearch){
			pc.setRowCount("sy_menu order by Menu_No desc ");     
		}
		if(isSearch){   
		  String temp1="";                    
		  temp1="where "+where+" like '%"+key+"%'";
		   pc.setRowCount("sy_menu "+temp1+" order by Menu_No desc ");                          
		}
		
		pc.setintPage(strPage,bigpage);
		
		if(!isSearch)
		{
			 vlist = Biz.List(pc,"");           
		}
		if(isSearch)
		{
		String temp="where "+where+" like '%"+key+"%'";
		vlist = Biz.List(pc,temp);
		}
		   
	%>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="15"></td>
			<td width="11" height="33" valign="TOP" align="RIGHT"><img
				src="../images/tablbk01_r1_c1.gif"></td>
			<td background="../images/tablbk01_r1_c2.gif">
			 
				<!-- display toolbar -->
				<%@ include file="../sy/inc/sy_toolbar_all.jsp" %>
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
			<%@ include file="../sy/inc/sy_toolbar.jsp" %>
			
			<!-- display content -->
			<br>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10"><!-- 屏幕信息 -->
			<ait:message messageID="display.sys.basic_maintenance.screen_management.screen_info" module="sys"></ait:message>		
					</td>
				</tr>
			</table>
			<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr>
			  	<td>
				<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		        <tr>
					  <td width="4%" height="18"  class="info_title_01"><!-- 序号 -->
					<ait:message messageID="display.mutual.no"></ait:message>	  
					  </td>
					  <td width="7%" height="30"  class="info_title_01"><!-- 深度 -->
					<ait:message messageID="display.sys.basic_maintenance.screen_management.depth" module="sys"></ait:message>	  
					  </td>
					  <td width="10%" height="30" class="info_title_01"><!-- 屏幕编号 -->
					<ait:message messageID="display.sys.basic_maintenance.screen_management.screen_no" module="sys"></ait:message>	  
					  </td>
					  <td width="15%" height="30"  class="info_title_01"><!--屏幕解释  -->
					<ait:message messageID="display.sys.basic_maintenance.screen_management.screen_narrative" module="sys"></ait:message>	  
					  </td>
		
					  <td width="13%" height="30"  class="info_title_01"><!-- URL -->
					<ait:message messageID="display.sys.basic_maintenance.screen_management.url" module="sys"></ait:message>	  
					  </td>
					  <td width="7%" height="30"  class="info_title_01"><!-- 活跃 -->
					 	<ait:message messageID="display.mutual.active" ></ait:message>    
					  </td>  
				</tr>
		       <%
				int listNo = 1 ;
				for ( int i = 0 ; i < vlist.size() ; i++ )  
				  {
					Ent=(Ent)vlist.elementAt(i) ;String m_url=Ent.getMenuUrl();if(m_url.length()>20){m_url=m_url.substring(0,20);m_url+=".....";}
				%>
					<tr bgcolor="#FFFFFF" onClick="HighLightTR('#E7F0EF','black','<%=Ent.getMenuNo()%>','<%=menu_code%>','<%=Ent.getMenuCode()%>')">
		  
					  <td height="30" align="center" style="color: #666666;"><%=listNo%><% listNo = listNo + 1 ; %>&nbsp;</td>
					  <td align="center" style="color: #666666;" ><img src="/images/btn/<%=Ent.getDepth()%>.gif" width="18" height="18">&nbsp;</td>
					  <td align="center" style="color: #666666;"><%=Ent.getMenuCode()%>&nbsp;</td>
					  <%      
					   admin = (AdminBean)session.getAttribute("admin");     
					   String language=admin.getLanguagePreference();   
					   if(language.equals("zh"))
					   {   
					  %>
					  <td align="center" style="color: #666666;"><%=Ent.getMenuIntro()%>&nbsp;</td>
					  <%}else{     
						  %>   
					  <td align="center" style="color: #666666;"><%=Ent.getMenuEnIntro()%>&nbsp;</td>    
					  <%} %>	  
					  <td align="center" style="color: #666666;"><%=m_url%>&nbsp;</td>
					  <td align="center" style="color: #666666;">
					  <a href="/E<%=menu_code%>Control?flag=updata&type=updateActive&no=<%=Ent.getMenuNo()%>&menu_code=<%=menu_code%>&statsValue=<%=Ent.getActivity()%>&bigpage=<%=bigpage%>&strPage=<%= strPage%10 == 0?10:strPage%10 %>">
					  <img src="/images/<%=Ent.getActivity()%>.gif" width="20" height="25"></a></td>
				  </tr>
					<%}%>
				</table>
				</td>
			  </tr>
			</table>
			<%@ include file = "../inc/pagecontrol_1.jsp"%>
			
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
</hrml>