<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" import="com.ait.util.StringUtil"%>
<%@ include file="/inc/taglibs.jsp"%> 
         
<%

	MenuEnt menuent = new MenuEnt();
	ArrayList menu_vector = menubiz.thirdmenulist(menu_code, rodeLevel);
	String search = request.getParameter("search");
	if (search == null) {
		search = "";                          
	}
	
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="8">&nbsp;</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="4" height="26"><img src="/images/lietianbiao3.jpg"
					width="4" height="26"></td>
				<td background="/images/lietianbiao4.jpg" align="LEFT"
					valign="MIDDLE">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<%
								for (int i = 0; i < menu_vector.size(); i++) {
								menuent = (MenuEnt) menu_vector.get(i);
						%>
						<%
						if (menu_code.equals(menuent.getMenuCode())) {
						%>
						<td class="letianfont3"><a href="<%=menuent.getMenuUrl()%>">
						<ait:content enContent='<%=StringUtil.checkNull(menuent.getMenuEnIntro())  %>'  
						koContent='<%=StringUtil.checkNull(menuent.getMenuKorIntro())%>'      
						zhContent='<%=StringUtil.checkNull(menuent.getMenuIntro())%>'></ait:content>
						</a></td>
						<td><span class="STYLE1">|</span></td>
						<%                                     
						} else {             
						%>
						<td class="letianfont4"><a href="<%=menuent.getMenuUrl()%>">                
						<ait:content enContent='<%=StringUtil.checkNull(menuent.getMenuEnIntro()) %>'  
						koContent='<%=StringUtil.checkNull(menuent.getMenuKorIntro())%>'      
						zhContent='<%=StringUtil.checkNull(menuent.getMenuIntro())%>'></ait:content>
						</a></td>                                  
						<td><span class="STYLE1">|</span></td>
						<%
							}
							}                    
						%>
					</tr>
				</table>
				</td>
				<td width="8" height="26"><img src="/images/lietianbiao5.jpg"
					width="8" height="26"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="2"></td>
	</tr>
</table>
