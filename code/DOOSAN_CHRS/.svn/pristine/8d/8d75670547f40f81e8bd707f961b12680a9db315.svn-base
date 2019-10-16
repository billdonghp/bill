<%@ page contentType="text/html; charset=UTF-8" import =" com.ait.sqlmap.util.SimpleMap, com.ait.gm.dao.GMDAO"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.util.*"%>
<jsp:useBean id="result" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="resultTemporary" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="allEateryType" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="resultTotal" class="java.util.ArrayList" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=YearEateryPeopleReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="66"><b><font size="+2">年间就餐统计表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
			    <td class="info_title_01"colspan="1" align="center" nowrap>
			       部门
			    </td>
			    <%for(int i=1 ; i<=12 ; i++){ %>
			    <td class="info_title_01" colspan="5" align="center" nowrap>
			       <%=i %>月
			    </td>
			    <%} %>
			    <td class="info_title_01" colspan="5" align="center" nowrap>
			       合计
			    </td>
	    	</tr>	
	       <tr>
			    <td class="info_title_01" align="center" nowrap>
			       &nbsp;
			    </td>
			    <%for(int i=1 ; i<=13 ; i++){ %>
			    <td class="info_title_01" align="center" nowrap>
			       早餐
			    </td>
			    <td class="info_title_01"  align="center" nowrap>
			       午餐
			    </td>
			    <td class="info_title_01"  align="center" nowrap>
			      晚餐
			    </td>
			    <td class="info_title_01"  align="center" nowrap>
			      夜餐
			    </td>
			    <td class="info_title_01" align="center" nowrap>
			      合计
			    </td>
			    <%} %>			    
	    	</tr>	     	
			<%
				SimpleMap map = null;
				SimpleMap map1 = null;
				String months[] = {"01","02","03","04","05","06","07","08","09","10","11","12"};
				String eateryType[] = {"break","lunch","supper","dinner"};
				for(int z=0 ; z<result.size() ; z++){
					map = (SimpleMap)result.get(z);
					String dept = map.getString("DEPTNAME")!=null?map.getString("DEPTNAME"):"临时卡";
			%>
				<tr>
					<td class="info_title_01" colspan="1" align="center" nowrap>
							<%=dept %>
					</td>
					<%
						String month = "";
						for(int i=0 ; i<months.length ; i++){
					%>
								<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt(months[i]+"break") %>
								</td>
								<td class="info_content_01" align="center" nowrap>
								<%=map.getInt(months[i]+"lunch") %>
								</td>
								<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt(months[i]+"supper") %>
								</td>
								<td class="info_content_01" align="center" nowrap>
								<%=map.getInt(months[i]+"dinner") %>
								</td>
								<td class="info_content_01" align="center" nowrap>
								<%=map.getInt(months[i]+"allcount") %>
								</td>
					<%
							}
							
					%>				
							<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("ALLBREAK") %>
							</td>
						   <td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("ALLLUNCH") %>
						   </td>
						   <td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("ALLSUPPER") %>
						   </td>
						   <td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("ALLDINNER") %>
						   </td>
						 <td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("ALLCOUNT") %>
						 </td>						
					
				</tr>
				<%
					}
				%>		
				
				
		</table>
		</td>
	</tr>
</table>
</body>
</html>