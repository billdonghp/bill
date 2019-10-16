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
	"attachment; filename=EateryMonthConsumeExcel.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="25"><b><font size="+2"> 就餐消费月别结算表</font></b></td>
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
			       日期
			    </td>
			    <td class="info_title_01" colspan="6" align="center" nowrap>
			      晚餐
			    </td>
			    <td class="info_title_01" colspan="6" align="center" nowrap>
			      夜餐
			    </td>
			     <td class="info_title_01" colspan="6" align="center" nowrap>
			     早餐
			    </td>
			    <td class="info_title_01" colspan="6" align="center" nowrap>
			     午餐
			    </td>
	    	</tr>	
	       <tr>
			    <td class="info_title_01" align="center" nowrap>
			       &nbsp;
			    </td>
			    <%for(int i=1 ; i<=4 ; i++){ %>
			    <td class="info_title_01" align="center" nowrap>
			       打卡
			    </td>
			    <td class="info_title_01"  align="center" nowrap>
			       职员申请
			    </td>
			    <td class="info_title_01"  align="center" nowrap>
			      访客申请
			    </td>
			    <td class="info_title_01"  align="center" nowrap>
			      合计
			    </td>
			    <td class="info_title_01" align="center" nowrap>
			      计划
			    </td>
			    <td class="info_title_01" align="center" nowrap>
			      结账
			    </td>
			    <%} %>			    
	    	</tr>	     	
			<%
				SimpleMap map = null;			
				for(int z=0 ; z<result.size() ; z++){
					map = (SimpleMap)result.get(z);
					String DDATE = map.getString("DDATE");
			%>
				<tr>
					<td class="info_title_01" colspan="1" align="center" nowrap>
							<%=DDATE %>
					</td>							
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("RECODESSUPPER") %>
						</td>	
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("LOCALSUPPER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("EXTERNALSUPPER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("REALSUPPER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("PLANRESULTSUPPER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("RESULTSUPPER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("RECODESDINNER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("LOCALDINNER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("EXTERNALDINNER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("REALDINNER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("PLANRESULTDINNER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("RESULTDINNER") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("RECODESBREAK") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("LOCALBREAK") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("EXTERNALBREAK") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("REALBREAK") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("PLANRESULTBREAK") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("RESULTBREAK") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("RECODESLUNCH") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("LOCALLUNCH") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("EXTERNALLUNCH") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("REALLUNCH") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("PLANRESULTLUNCH") %>
						</td>
						<td class="info_content_01"  align="center" nowrap>
								<%=map.getInt("RESULTLUNCH") %>
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