<%@ page language="java" import="java.util.*,com.lgcn.web.*,com.lgcn.util.*,com.ait.hrm.entity.*" pageEncoding="UTF-8"%>
<jsp:useBean id="rd" class="com.lgcn.web.ReportDAO"></jsp:useBean>
<jsp:useBean id="ecr" class="com.ait.hrm.entity.EndContractReport"></jsp:useBean>

    <%
    	String xlsKey = request.getParameter("xlsKey")!=null?request.getParameter("xlsKey"):"";
		String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
		String deptname = rd.getDeptName(deptid);
		ArrayList deptlist = rd.getDept();
		PostReport prTotal = new PostReport() ;
		boolean flag = false ;
		int sumpro = rd.getdepts();
		int year = NumberUtil.parseInt(new java.text.SimpleDateFormat("yyyy").format(new Date().getTime()));
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=re_endContract.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		
     %>
     <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
     	
     	<%-------------------------- 表头  -----------------------------%>
     	<tr>
     		<td align="center">本部</td>
     		<td align="center">部门</td>
     		<td align="center"><%=year %>年第1季度</td>
     		<td align="center"><%=year %>年第2季度</td>
     		<td align="center"><%=year %>年第3季度</td>
     		<td align="center"><%=year %>年第4季度</td>
     		<td align="center"><%=year+1 %>年第1季度</td>
     		<td align="center"><%=year+1 %>年第2季度</td>
     		<td align="center"><%=year+1 %>年第3季度</td>
     		<td align="center"><%=year+1 %>年第4季度</td>
     		<td align="center"><%=year+2 %>年及以后</td>
     		<td align="center">总计</td>
   		</tr>
   		
   		<tr>
   		<td align="center" rowspan=4>人力资源室</td>
   		<%ecr = rd.getEndContractInfo("0001.01.05.01");%>
   			<td align="center"><%=ecr.getDeptName() %></td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.05.02");%>
   			<td align="center"><%=ecr.getDeptName() %></td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.05.03");%>
   			<td align="center"><%=ecr.getDeptName() %></td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.05");%>
   			<td align="center">小计</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
   		<tr>
   		<td align="center" rowspan=5>事业本部</td>
   		<%ecr = rd.getEndContractInfo("0001.01.02.01");%>
   			<td align="center">E&C部</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.02.02");%>
   			<td align="center">企划部门</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.02.08");%>
   			<td align="center">海务部</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.02.09");%>
   			<td align="center">销售部门</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.02");%>
   			<td align="center">小计</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
   		<tr>
   		<td align="center" rowspan=7>生产本部</td>
   		<%ecr = rd.getEndContractInfo("0001.01.03.01");%>
   			<td align="center">生产部</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.03.02");%>
   			<td align="center">动力/运营部</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.03.03");%>
   			<td align="center">技术部</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.03.04");%>
   			<td align="center">设备部</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.03.05");%>
   			<td align="center">设计/检查部</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.03.06");%>
   			<td align="center">EHS部</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.03");%>
   			<td align="center">小计</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
   		<tr>
   		<td align="center" rowspan=4>管理本部</td>
   		<%ecr = rd.getEndContractInfo("0001.01.01.01");%>
   			<td align="center">金融部</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.01.02");%>
   			<td align="center">管理部</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.01.03");%>
   			<td align="center">业务部</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01.01");%>
   			<td align="center">小计</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
     	<tr>
     	<%ecr = rd.getEndContractInfo("0001.01");%>
     		<td align="center">----</td>
   			<td align="center">总计</td>
     		<td align="center"><%=ecr.getFst_season_now() %></td>
     		<td align="center"><%=ecr.getScd_season_now() %></td>
     		<td align="center"><%=ecr.getThd_season_now() %></td>
     		<td align="center"><%=ecr.getFth_season_now() %></td>
     		<td align="center"><%=ecr.getFst_season_next() %></td>
     		<td align="center"><%=ecr.getScd_season_next() %></td>
     		<td align="center"><%=ecr.getThd_season_next() %></td>
     		<td align="center"><%=ecr.getFth_season_next() %></td>
     		<td align="center"><%=ecr.getAfter_next_year() %></td>
     		<td align="center"><%=ecr.getTotal() %></td>
     	</tr>
</table>
