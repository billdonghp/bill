<%@ page contentType="text/html; charset=UTF-8" language="java" 
	import="com.ait.util.*,
			com.ait.utils.ActivityReport,
			java.util.Vector" %>
<%@ include file="../inc/taglibs.jsp"%>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
	function showAll(){
		document.sf.flag.value='all';
		document.sf.action = "";
		document.sf.submit(); 
	}    
</script>          
	<%
		ActivityReport report = new ActivityReport();
		Vector vlist = new Vector();
		Vector data = new Vector();
		String sql = StringUtil.toCN(StringUtil.checkNull(request.getParameter("hole_sql")));
		String flag = StringUtil.checkNull(request.getParameter("flag"));
		if (!sql.equals("")) vlist = report.DataSelect(sql);
		int num = 11;
		if (flag.equals("all")) num = vlist.size();
		String tabledata = "";
		String tr="";  
	%>
<div style="padding:10 0 0 10;align:left " >
 <table width="97%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img  
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
		<div align="right">		
			<ait:image src="/images/btn/Excel_Exp.gif"  onclick="document.sf.submit();" 
			align="absmiddle" style="cursor:hand;padding:10 0 0 15 "></ait:image>   	
		<td width="10" height="33" align="LEFT" valign="TOP">
			<img src="../images/tablbk01_r1_c26.gif">  </td>  
		<td width="11"></td>
	</tr>               
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
			<table>
				<tr>
					<td><h5>
						<!--查询成功，供检索到 条记录
					    	<a href="javascript:showAll()">  </a>-->
					    	<ait:message messageID="display.emp.statistics.info_search.records"  module="hrm"></ait:message>	
					    		<font color="red">
					    			<%=vlist.size()-1%>
					    		 </font>
					    	
					    	 <br></h5>
					    	 <% if (vlist.size() > 1){%>
							<h5><!--前条记录如下：-->
					    	<ait:message  messageID="display.emp.statistics.info_search.shown"  module="hrm"></ait:message>	
									<%//num-1%>
							</h5> 
					</td>
				</tr>
				<tr>
					<td>
						<table border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;" >
							<tr>
								<td>
									<%if(vlist.size()!=0){ %>
									<%	for ( int i = 0 ; i < vlist.size() && i<=num-1 ; i++ ){ 		  
											report=(ActivityReport)vlist.elementAt(i);
											if (i==0){%>
												<tr align="center" style="font-size:10pt; font-weight:bold;height:26px"  bgcolor="F7F7F7"> 
											<%}else{%>
												<tr align="center" style="font-size:9pt"> 
											<%}
									 		data = report.getData();
									 		tr="";
									 		if (data.size()!=0) {
									 			for (int j=0;j<data.size();j++) {
													tabledata=StringUtil.editNbsp((String)data.elementAt(j));
													tr +="<td>"+tabledata+"</td>";
												}%><%=tr%>
											<%}%>
											</tr>
										<%}}%>
								</td>								
							</tr>						
						</table>	
					</td>
				</tr><%}%>
			</table>	    
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>  
	<c:forEach begin="1" end="5" >
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>   
	</c:forEach>   
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
</div>
<form method="post"  action="hrmSearchExcel.jsp" style="display:none " name="sf">
	<textarea name="hole_sql"><%=sql%></textarea>
	<textarea name="flag"><%=flag%></textarea>
</form>
