<%@ page language="java" import="java.util.*,com.lgcn.web.*,com.lgcn.util.*,com.ait.hrm.entity.*" pageEncoding="UTF-8"%>
<jsp:useBean id="rd" class="com.lgcn.web.ReportDAO"></jsp:useBean>
<jsp:useBean id="mr" class="com.ait.hrm.entity.MarriageReport"></jsp:useBean>

    <%
    	String xlsKey = request.getParameter("xlsKey")!=null?request.getParameter("xlsKey"):"";
		String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
		String deptname = rd.getDeptName(deptid);
		Vector vector = SysCodeParser.getCode("PostCode");
		ArrayList deptlist = rd.getDept();
		int sumpro = rd.getdepts();
		
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=re_marriage.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		
     %>
     <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
     	
     	<%-------------------------- 表头  -----------------------------%>
     	<tr>
     		<td align="center" colspan="2" >区分</td>
     		<td align="center" colspan="2" >婚姻状况</td>
     		<td align="center" rowspan="2">合计</td>
     		<td align="center" rowspan="2">已婚比例</td>
     		<td align="center" rowspan="2">未婚比例</td>
     		
   		</tr>
   		<tr>
   			<td align="center">本部</td>
   			<td align="center">部门</td>
   			<td align="center">已婚</td>
   			<td align="center">未婚</td>
   		</tr>
   		<tr>
   			<td rowspan="4"  align="center">管理本部</td>
   			<%
   				mr = rd.getMarriageInfo("0001.01.01.01");
   			 %>
   			<td align="center">金融部</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   		
   			<%
   				mr = rd.getMarriageInfo("0001.01.01.02");
   			 %>
   			<td align="center">管理部</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.01.03");
   			 %>
   			<td align="center">业务部</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
     	<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.01");
   			 %>
   			<td align="center">小计</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   			<td rowspan="5"  align="center">事业本部</td>
   			<%
   				mr = rd.getMarriageInfo("0001.01.02.01");
   			 %>
   			<td align="center">E&C部</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   		
   			<%
   				mr = rd.getMarriageInfo("0001.01.02.02");
   			 %>
   			<td align="center">企划部门</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
     	<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.02.08");
   			 %>
   			<td align="center">海务部</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.02.09");
   			 %>
   			<td align="center">销售部门</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.02");
   			 %>
   			<td align="center">小计</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		
   		<tr>
   			<td rowspan="7"  align="center">生产本部</td>
   			<%
   				mr = rd.getMarriageInfo("0001.01.03.01");
   			 %>
   			<td align="center">生产部</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   		
   			<%
   				mr = rd.getMarriageInfo("0001.01.03.02");
   			 %>
   			<td align="center">动力/运营部</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.03.03");
   			 %>
   			<td align="center">技术部</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
     	<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.03.04");
   			 %>
   			<td align="center">设备部</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.03.05");
   			 %>
   			<td align="center">设计/检查部</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.03.06");
   			 %>
   			<td align="center">EHS部</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.03");
   			 %>
   			<td align="center">小计</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   			<td rowspan="4"  align="center">人力资源室</td>
   			<%
   				mr = rd.getMarriageInfo("0001.01.05.01");
   			 %>
   			<td align="center">人事科</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   		
   			<%
   				mr = rd.getMarriageInfo("0001.01.05.02");
   			 %>
   			<td align="center">教育/培训科</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.05.03");
   			 %>
   			<td align="center">总务科</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
     	<tr>
   			<%
   				mr = rd.getMarriageInfo("0001.01.05");
   			 %>
   			<td align="center">小计</td>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
   		<tr >
   			<td colspan="2" align="center">总计</td>
   			<%
   				mr = rd.getMarriageInfo("0001.01");
   			 %>
   			<td align="center"><%=mr.getY() %></td>
   			<td align="center"><%=mr.getW() %></td>
   			<td align="center"><%=mr.getCount() %></td>
   			<td align="center"><%=mr.getA() %>%</td>
   			<td align="center"><%=mr.getB() %>%</td>
   		</tr>
    </table>
