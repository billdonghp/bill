<%@ page contentType="text/html; charset=UTF-8" import =" com.ait.sqlmap.util.SimpleMap"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.util.*"%>
<jsp:useBean id="result" class="java.util.ArrayList" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=EateryCountsExcel.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="16"><b><font size="+2">就餐人数统计表</font></b></td>
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
			    <td class="info_title_01" colspan="1" align="center" nowrap>
			       部门
			    </td>
			    <td class="info_title_01" colspan="5" align="center" nowrap>
			      员工就餐
			    </td>
			    <td class="info_title_01" colspan="5" align="center" nowrap>
			      员工申请就餐
			    </td>
			     <td class="info_title_01" colspan="5" align="center" nowrap>
			     访客申请就餐
			    </td>
	    	</tr>	
	       <tr>
			    <td class="info_title_01" align="center" nowrap>
			       &nbsp;
			    </td>
			    <%for(int i=1 ; i<=3 ; i++){ %>
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
			      总人数
			    </td>
			    <%} %>			    
	    	</tr>	     	
			<%
				SimpleMap map = null;			
				for(int z=0 ; z<result.size() ; z++){
					map = (SimpleMap)result.get(z);
					String dept = map.getString("DEPTNAME");
			%>
				<tr>
					<td class="info_title_01" colspan="1" align="center" nowrap>
							<%=dept %>
					</td>							
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("BREAK") %>
						</td>	
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("LUNCH") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("SUPPER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("DINNER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("ALLCOUNTS") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("LOCALBREAKNUM") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("LOCALLUNCHNUM") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("LOCALSUPPERNUM") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("LOCALDINNERNUM") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("ALLLOCALNUM") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("EXTERNALDATABREAKNUM") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("EXTERNALDATALUNCHNUM") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("EXTERNALDATASUPPERNUM") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("EXTERNALDATADINNERNUM") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("ALLEXTERNALNUM") %>
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