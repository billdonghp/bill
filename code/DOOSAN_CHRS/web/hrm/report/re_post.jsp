<%@ page language="java" import="java.util.*,com.lgcn.web.*,com.lgcn.util.*,com.ait.hrm.entity.*" pageEncoding="UTF-8"%>
<jsp:useBean id="rd" class="com.lgcn.web.ReportDAO"></jsp:useBean>
<jsp:useBean id="pr" class="com.ait.hrm.entity.PostReport"></jsp:useBean>
<jsp:useBean id="prCN" class="com.ait.hrm.entity.PostReport"></jsp:useBean>

    <%
    	String xlsKey = request.getParameter("xlsKey")!=null?request.getParameter("xlsKey"):"";
		String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
		String deptname = rd.getDeptName(deptid);
		Vector vector = SysCodeParser.getCode("PostCode");
		ArrayList deptlist = rd.getDept();
		PostReport prTotal = new PostReport() ;
		boolean flag = false ;
		int sumpro = rd.getdepts();
		
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=re_post.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		
     %>
     <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
     	
     	<%-------------------------- 表头  -----------------------------%>
     	<tr>
     		<td align="center">本部</td>
     		<td align="center">
     		<table border="1">
     		<tr>
     		<td align="center" rowspan=3 colspan=1>部门</td>
     		<td align="center" rowspan=2 colspan=14>韩方人员</td>
     		<td align="center" rowspan=1 colspan=11>中方人员</td>
     		<td align="center" rowspan=2 colspan=2></td>
     		</tr>
     		<tr>
     		<td align="center" rowspan=1 colspan=5>管理支持序列</td>
     		<td align="center" rowspan=1 colspan=3>生产序列</td>
     		<td align="center" rowspan=1 colspan=3>工程师序列</td>
     		</tr>
     		<tr>
     		<td align="center">总经理</td>
     		<td align="center">副总经理</td>
     		<td align="center">常务</td>
     		<td align="center">总监</td>
     		<td align="center">部长</td>
     		<td align="center">次长</td>
     		<td align="center">系长</td>
     		<td align="center">委员</td>
     		<td align="center">科长</td>
     		<td align="center">代理</td>
     		<td align="center">监督</td>
     		<td align="center">经理</td>
     		<td align="center">专员</td>
     		<td align="center">主任</td>
     		
     		<td align="center">副总经理</td>
     		<td align="center">经理</td>
     		<td align="center">主管</td>
     		<td align="center">专员</td>
     		<td align="center">助理</td>
     		<td align="center">主任</td>
     		<td align="center">班长</td>
     		<td align="center">技术工人</td>
     		<td align="center">高级工程师</td>
     		<td align="center">工程师</td>
     		<td align="center">助理工程师</td>
     		<td align="center" rowspan=1 colspan=1>特殊职</td>
     		<td align="center" rowspan=1 colspan=1 bgcolor="#8080ff">总计</td>
     		</tr>
     		</table>
     		</td>
   		</tr>
   		<tr>
   		<td align="center" >总经理</td>
   		<td align="center" >
   		<table border="1">
   		<td align="center" ></td>
   		<td align="center">1</td>
   		<% for(int i = 0 ; i < 25 ; i ++){ %>
   			<td align="center">0</td>
   			<%} %>
   			<td align="center">1</td>
   		</table>
		</td>
   		</tr>
   		<%if(deptid.equals("")||deptid.equals("0001.01")){ %>
   		<%List prList = rd.getPostInfoByDeptIdCN("0001.01.05");%>
   		<%List prListKR = rd.getPostInfoByDeptIdKR("0001.01.05");%>
   		<tr>
   			<td align="center">人力资源室</td>
   			<td align="center">
   			<table border="1">
   			<tr>
   			<td align="center">人力资源室长</td>
   			<td align="center">0</td>
   			<td align="center">0</td>
   			<td align="center">0</td>
   			<td align="center">1</td>
   			<% for(int i = 0 ; i < 22 ; i ++){ %>
   			<td align="center">0</td>
   			<%} %>
   			<td align="center">1</td>
   			<%
   				for(int i = 0 ; i < prListKR.size() ; i ++){
   				pr = (PostReport)prListKR.get(i) ;
   			 %>
   			 <tr>
   			 <% if(i != prListKR.size()-1){ %>
   			<td align="center"><%=pr.getDeptName() %></td>
   			<%}else{ flag = true ;%>
   			<td align="center">小计</td>
   			<%} %>
   			<td align="center"><%=pr.getCeo() %></td>
   			<td align="center"><%=pr.getFuZong() %></td>
   			<td align="center"><%=pr.getChangWu() %></td>
   			<% if(flag){ %>
   			<td align="center">1</td>
   			<%}else{ %>
   			<td align="center"><%=pr.getZongJian() %></td>
   			<%} %>
   			<td align="center"><%=pr.getBuZhang() %></td>
   			<td align="center"><%=pr.getCiZhang() %></td>
   			<td align="center"><%=pr.getXiZhang() %></td>
   			<td align="center"><%=pr.getWeiYuan() %></td>
   			<td align="center"><%=pr.getKeZhang() %></td>
   			<td align="center"><%=pr.getDaiLi() %></td>
   			<td align="center"><%=pr.getJianDu() %></td>
   			<td align="center"><%=pr.getManagerKR() %></td>
   			<td align="center"><%=pr.getZhuanYuanKR() %></td>
   			<td align="center"><%=pr.getZhuRenKR() %></td>
   			<td align="center">0</td>
   			<%prCN = (PostReport)prList.get(i) ; %>
   			<td align="center"><%=prCN.getManager() %></td>
   			<td align="center"><%=prCN.getZhuGuan() %></td>
   			<td align="center"><%=prCN.getZhuanYuan() %></td>
   			<td align="center"><%=prCN.getZhuLi() %></td>
   			<td align="center"><%=prCN.getZhuRen() %></td>
   			<td align="center"><%=prCN.getMonitor() %></td>
   			<td align="center"><%=prCN.getWorkMan() %></td>
   			<td align="center"><%=prCN.getHighEngineer() %></td>
   			<td align="center"><%=prCN.getEngineer() %></td>
   			<td align="center"><%=prCN.getAssistantEngineer() %></td>
   			<td align="center"><%=prCN.getTeShuZhi() %></td>
   			<%if(flag){ %>
   			<td align="center"><%=prCN.getSubTotal()+pr.getSubTotal()+1 %></td>
   			<%}else{ %>
   			<td align="center"><%=prCN.getSubTotal()+pr.getSubTotal() %></td>
   			<%} %>
	   		</tr>
   		
	   		<%
	   		if(flag){ 
	   			prTotal.setCeo(pr.getCeo()) ;
				prTotal.setFuZong(pr.getFuZong()) ;
				prTotal.setBuZhang(pr.getBuZhang());
				prTotal.setZongJian(pr.getZongJian()) ;
				prTotal.setChangWu(pr.getChangWu()) ;
				prTotal.setCiZhang(pr.getCiZhang()) ;
				prTotal.setXiZhang(pr.getXiZhang()) ;
				prTotal.setWeiYuan(pr.getWeiYuan()) ;
				prTotal.setDaiLi(pr.getDaiLi()) ;
				prTotal.setKeZhang(pr.getKeZhang()) ;
				prTotal.setJianDu(pr.getJianDu());
				prTotal.setManagerKR(pr.getManagerKR()) ;
				prTotal.setZhuanYuanKR(pr.getZhuanYuanKR()) ;
				prTotal.setZhuRenKR(pr.getZhuRenKR()) ;
				
				prTotal.setManager(prCN.getManager()) ;
				prTotal.setZhuGuan(prCN.getZhuGuan()) ;
				prTotal.setZhuanYuan(prCN.getZhuanYuan()) ;
				prTotal.setZhuLi(prCN.getZhuLi()) ;
				prTotal.setZhuRen(prCN.getZhuRen()) ;
				prTotal.setMonitor(prCN.getMonitor()) ;
				prTotal.setWorkMan(prCN.getWorkMan()) ;
				prTotal.setEngineer(prCN.getEngineer()) ;
				prTotal.setAssistantEngineer(prCN.getAssistantEngineer()) ;
				prTotal.setHighEngineer(prCN.getHighEngineer()) ;
				prTotal.setTeShuZhi(prCN.getTeShuZhi());
				prTotal.setSubTotal(prCN.getSubTotal()+pr.getSubTotal()) ;
				flag = false ;
	   		%>
	   		<%}} %>
     	</table>
     	</td>
     	</tr>
   		<%  prList = rd.getPostInfoByDeptIdCN("0001.01.02"); %>
   		<%  prListKR = rd.getPostInfoByDeptIdKR("0001.01.02"); %>
   		<tr>
   			<td align="center">事业本部</td>
   			<td align="center">
   			<table border="1">
   			<tr>
   			<td align="center">事业本部副总</td>
   			<td align="center">0</td>
   			<td align="center">1</td>
   			<% for(int i = 0 ; i < 24 ; i ++){ %>
   			<td align="center">0</td>
   			<%} %>
   			<td align="center">1</td>
   			</tr>
   			<%
   				for(int i = 0 ; i < prListKR.size() ; i ++){
   				pr = (PostReport)prListKR.get(i) ;
   			 %>
   			 <tr>
   			 <% if(i != prListKR.size()-1){ %>
   			<td align="center"><%=pr.getDeptName() %></td>
   			<%}else{ flag = true ;%>
   			<td align="center">小计</td>
   			<%} %>
   			<td align="center"><%=pr.getCeo() %></td>
   			<% if(flag){ %>
   			<td align="center">1</td>
   			<%}else{ %>
   			<td align="center"><%=pr.getFuZong() %></td>
   			<%} %>
   			<td align="center"><%=pr.getChangWu() %></td>
   			<td align="center"><%=pr.getZongJian() %></td>
   			<td align="center"><%=pr.getBuZhang() %></td>
   			<td align="center"><%=pr.getCiZhang() %></td>
   			<td align="center"><%=pr.getXiZhang() %></td>
   			<td align="center"><%=pr.getWeiYuan() %></td>
   			<td align="center"><%=pr.getKeZhang() %></td>
   			<td align="center"><%=pr.getDaiLi() %></td>
   			<td align="center"><%=pr.getJianDu() %></td>
   			<td align="center"><%=pr.getManagerKR() %></td>
   			<td align="center"><%=pr.getZhuanYuanKR() %></td>
   			<td align="center"><%=pr.getZhuRenKR() %></td>
   			<td align="center">0</td>
   			<%prCN = (PostReport)prList.get(i) ; %>
   			<td align="center"><%=prCN.getManager() %></td>
   			<td align="center"><%=prCN.getZhuGuan() %></td>
   			<td align="center"><%=prCN.getZhuanYuan() %></td>
   			<td align="center"><%=prCN.getZhuLi() %></td>
   			<td align="center"><%=prCN.getZhuRen() %></td>
   			<td align="center"><%=prCN.getMonitor() %></td>
   			<td align="center"><%=prCN.getWorkMan() %></td>
   			<td align="center"><%=prCN.getHighEngineer() %></td>
   			<td align="center"><%=prCN.getEngineer() %></td>
   			<td align="center"><%=prCN.getAssistantEngineer() %></td>
   			<td align="center"><%=prCN.getTeShuZhi() %></td>
   			<%if(flag){ %>
   			<td align="center"><%=prCN.getSubTotal()+pr.getSubTotal()+1 %></td>
   			<%}else{ %>
   			<td align="center"><%=prCN.getSubTotal()+pr.getSubTotal() %></td>
   			<%} %>
	   		</tr>
	   		<%
	   		if(flag){ 
	   			prTotal.setCeo(prTotal.getCeo()+pr.getCeo()) ;
				prTotal.setFuZong(prTotal.getFuZong()+pr.getFuZong()) ;
				prTotal.setBuZhang(prTotal.getBuZhang()+pr.getBuZhang());
				prTotal.setZongJian(prTotal.getZongJian()+pr.getZongJian()) ;
				prTotal.setChangWu(prTotal.getChangWu()+pr.getChangWu()) ;
				prTotal.setCiZhang(prTotal.getCiZhang()+pr.getCiZhang()) ;
				prTotal.setXiZhang(prTotal.getXiZhang()+pr.getXiZhang()) ;
				prTotal.setWeiYuan(prTotal.getWeiYuan()+pr.getWeiYuan()) ;
				prTotal.setDaiLi(prTotal.getDaiLi()+pr.getDaiLi()) ;
				prTotal.setKeZhang(prTotal.getKeZhang()+pr.getKeZhang()) ;
				prTotal.setJianDu(prTotal.getJianDu()+pr.getJianDu());
				prTotal.setManagerKR(prTotal.getManagerKR()+pr.getManagerKR()) ;
				prTotal.setZhuanYuanKR(prTotal.getZhuanYuanKR()+pr.getZhuanYuanKR()) ;
				prTotal.setZhuRenKR(prTotal.getZhuRenKR()+pr.getZhuRenKR()) ;
				
				prTotal.setManager(prTotal.getManager()+prCN.getManager()) ;
				prTotal.setZhuGuan(prTotal.getZhuGuan()+prCN.getZhuGuan()) ;
				prTotal.setZhuanYuan(prTotal.getZhuanYuan()+prCN.getZhuanYuan()) ;
				prTotal.setZhuLi(prTotal.getZhuLi()+prCN.getZhuLi()) ;
				prTotal.setZhuRen(prTotal.getZhuRen()+prCN.getZhuRen()) ;
				prTotal.setMonitor(prTotal.getMonitor()+prCN.getMonitor()) ;
				prTotal.setWorkMan(prTotal.getWorkMan()+prCN.getWorkMan()) ;
				prTotal.setEngineer(prTotal.getEngineer()+prCN.getEngineer()) ;
				prTotal.setAssistantEngineer(prTotal.getAssistantEngineer()+prCN.getAssistantEngineer()) ;
				prTotal.setHighEngineer(prTotal.getHighEngineer()+prCN.getHighEngineer()) ;
				prTotal.setTeShuZhi(prTotal.getTeShuZhi()+prCN.getTeShuZhi());
				prTotal.setSubTotal(prTotal.getSubTotal()+pr.getSubTotal()+prCN.getSubTotal()) ;
				flag = false ;
	   		%>
	   		<%}} %>
     	</table>
     	</td>
     	</tr>
   		
   		<%  prList = rd.getPostInfoByDeptIdCN("0001.01.03"); %>
   		<%  prListKR = rd.getPostInfoByDeptIdKR("0001.01.03"); %>
   		<tr>
   			<td align="center">生产本部</td>
   			<td align="center">
   			<table border="1">
   			<tr>
   			<td align="center">生产本部副总</td>
   			<td align="center">0</td>
   			<td align="center">1</td>
   			<% for(int i = 0 ; i < 24 ; i ++){ %>
   			<td align="center">0</td>
   			<%} %>
   			<td align="center">1</td>
   			<%
   				for(int i = 0 ; i < prListKR.size() ; i ++){
   				pr = (PostReport)prListKR.get(i) ;
   			 %>
   			 <tr>
   			 <% if(i != prListKR.size()-1){ %>
   			<td align="center"><%=pr.getDeptName() %></td>
   			<%}else{ flag = true ;%>
   			<td align="center">小计</td>
   			<%} %>
   			<td align="center"><%=pr.getCeo() %></td>
   			<% if(flag){ %>
   			<td align="center">1</td>
   			<%}else{ %>
   			<td align="center"><%=pr.getFuZong() %></td>
   			<%} %>
   			<td align="center"><%=pr.getChangWu() %></td>
   			<td align="center"><%=pr.getZongJian() %></td>
   			<td align="center"><%=pr.getBuZhang() %></td>
   			<td align="center"><%=pr.getCiZhang() %></td>
   			<td align="center"><%=pr.getXiZhang() %></td>
   			<td align="center"><%=pr.getWeiYuan() %></td>
   			<td align="center"><%=pr.getKeZhang() %></td>
   			<td align="center"><%=pr.getDaiLi() %></td>
   			<td align="center"><%=pr.getJianDu() %></td>
   			<td align="center"><%=pr.getManagerKR() %></td>
   			<td align="center"><%=pr.getZhuanYuanKR() %></td>
   			<td align="center"><%=pr.getZhuRenKR() %></td>
   			<td align="center">0</td>
   			<%prCN = (PostReport)prList.get(i) ; %>
   			<td align="center"><%=prCN.getManager() %></td>
   			<td align="center"><%=prCN.getZhuGuan() %></td>
   			<td align="center"><%=prCN.getZhuanYuan() %></td>
   			<td align="center"><%=prCN.getZhuLi() %></td>
   			<td align="center"><%=prCN.getZhuRen() %></td>
   			<td align="center"><%=prCN.getMonitor() %></td>
   			<td align="center"><%=prCN.getWorkMan() %></td>
   			<td align="center"><%=prCN.getHighEngineer() %></td>
   			<td align="center"><%=prCN.getEngineer() %></td>
   			<td align="center"><%=prCN.getAssistantEngineer() %></td>
   			<td align="center"><%=prCN.getTeShuZhi() %></td>
   			<%if(flag){ %>
   			<td align="center"><%=prCN.getSubTotal()+pr.getSubTotal()+1 %></td>
   			<%}else{ %>
   			<td align="center"><%=prCN.getSubTotal()+pr.getSubTotal() %></td>
   			<%} %>
	   		</tr>
	   		<%
	   		if(flag){ 
	   			prTotal.setCeo(prTotal.getCeo()+pr.getCeo()) ;
				prTotal.setFuZong(prTotal.getFuZong()+pr.getFuZong()) ;
				prTotal.setBuZhang(prTotal.getBuZhang()+pr.getBuZhang());
				prTotal.setZongJian(prTotal.getZongJian()+pr.getZongJian()) ;
				prTotal.setChangWu(prTotal.getChangWu()+pr.getChangWu()) ;
				prTotal.setCiZhang(prTotal.getCiZhang()+pr.getCiZhang()) ;
				prTotal.setXiZhang(prTotal.getXiZhang()+pr.getXiZhang()) ;
				prTotal.setWeiYuan(prTotal.getWeiYuan()+pr.getWeiYuan()) ;
				prTotal.setDaiLi(prTotal.getDaiLi()+pr.getDaiLi()) ;
				prTotal.setKeZhang(prTotal.getKeZhang()+pr.getKeZhang()) ;
				prTotal.setJianDu(prTotal.getJianDu()+pr.getJianDu());
				prTotal.setManagerKR(prTotal.getManagerKR()+pr.getManagerKR()) ;
				prTotal.setZhuanYuanKR(prTotal.getZhuanYuanKR()+pr.getZhuanYuanKR()) ;
				prTotal.setZhuRenKR(prTotal.getZhuRenKR()+pr.getZhuRenKR()) ;
				
				prTotal.setManager(prTotal.getManager()+prCN.getManager()) ;
				prTotal.setZhuGuan(prTotal.getZhuGuan()+prCN.getZhuGuan()) ;
				prTotal.setZhuanYuan(prTotal.getZhuanYuan()+prCN.getZhuanYuan()) ;
				prTotal.setZhuLi(prTotal.getZhuLi()+prCN.getZhuLi()) ;
				prTotal.setZhuRen(prTotal.getZhuRen()+prCN.getZhuRen()) ;
				prTotal.setMonitor(prTotal.getMonitor()+prCN.getMonitor()) ;
				prTotal.setWorkMan(prTotal.getWorkMan()+prCN.getWorkMan()) ;
				prTotal.setEngineer(prTotal.getEngineer()+prCN.getEngineer()) ;
				prTotal.setAssistantEngineer(prTotal.getAssistantEngineer()+prCN.getAssistantEngineer()) ;
				prTotal.setHighEngineer(prTotal.getHighEngineer()+prCN.getHighEngineer()) ;
				prTotal.setTeShuZhi(prTotal.getTeShuZhi()+prCN.getTeShuZhi());
				prTotal.setSubTotal(prTotal.getSubTotal()+pr.getSubTotal()+prCN.getSubTotal()) ;
				flag = false ;
	   		%>
	   		<%}} %>
     	</table>
     	</td>
     	</tr>
     	<%  prList = rd.getPostInfoByDeptIdCN("0001.01.01"); %>
     	<%  prListKR = rd.getPostInfoByDeptIdKR("0001.01.01"); %>
   		<tr>
   			<td align="center">管理本部</td>
   			<td align="center">
   			<table border="1">
   			<tr>
   			<td align="center">管理本部副总</td>
   			<td align="center">0</td>
   			<td align="center">1</td>
   			<% for(int i = 0 ; i < 24 ; i ++){ %>
   			<td align="center">0</td>
   			<%} %>
   			<td align="center">1</td>
   			</tr>
   			<%
   				for(int i = 0 ; i < prListKR.size() ; i ++){
   				pr = (PostReport)prListKR.get(i) ;
   			 %>
   			 <tr>
   			 <% if(i != prListKR.size()-1){ %>
   			<td align="center"><%=pr.getDeptName() %></td>
   			<%}else{ flag = true ; %>
   			<td align="center">小计</td>
   			<%} %>
   			<td align="center"><%=pr.getCeo() %></td>
   			<% if(flag){ %>
   			<td align="center">1</td>
   			<%}else{ %>
   			<td align="center"><%=pr.getFuZong() %></td>
   			<%} %>
   			<td align="center"><%=pr.getChangWu() %></td>
   			<td align="center"><%=pr.getZongJian() %></td>
   			<td align="center"><%=pr.getBuZhang() %></td>
   			<td align="center"><%=pr.getCiZhang() %></td>
   			<td align="center"><%=pr.getXiZhang() %></td>
   			<td align="center"><%=pr.getWeiYuan() %></td>
   			<td align="center"><%=pr.getKeZhang() %></td>
   			<td align="center"><%=pr.getDaiLi() %></td>
   			<td align="center"><%=pr.getJianDu() %></td>
   			<td align="center"><%=pr.getManagerKR() %></td>
   			<td align="center"><%=pr.getZhuanYuanKR() %></td>
   			<td align="center"><%=pr.getZhuRenKR() %></td>
   			<td align="center">0</td>
   			<%prCN = (PostReport)prList.get(i) ; %>
   			<td align="center"><%=prCN.getManager() %></td>
   			<td align="center"><%=prCN.getZhuGuan() %></td>
   			<td align="center"><%=prCN.getZhuanYuan() %></td>
   			<td align="center"><%=prCN.getZhuLi() %></td>
   			<td align="center"><%=prCN.getZhuRen() %></td>
   			<td align="center"><%=prCN.getMonitor() %></td>
   			<td align="center"><%=prCN.getWorkMan() %></td>
   			<td align="center"><%=prCN.getHighEngineer() %></td>
   			<td align="center"><%=prCN.getEngineer() %></td>
   			<td align="center"><%=prCN.getAssistantEngineer() %></td>
   			<td align="center"><%=prCN.getTeShuZhi() %></td>
   			<%if(flag){ %>
   			<td align="center"><%=prCN.getSubTotal()+pr.getSubTotal()+1 %></td>
   			<%}else{ %>
   			<td align="center"><%=prCN.getSubTotal()+pr.getSubTotal() %></td>
   			<%} %>
	   		</tr>
     	
	   		<%
	   		if(flag){ 
	   			prTotal.setCeo(prTotal.getCeo()+pr.getCeo()) ;
				prTotal.setFuZong(prTotal.getFuZong()+pr.getFuZong()) ;
				prTotal.setBuZhang(prTotal.getBuZhang()+pr.getBuZhang());
				prTotal.setZongJian(prTotal.getZongJian()+pr.getZongJian()) ;
				prTotal.setChangWu(prTotal.getChangWu()+pr.getChangWu()) ;
				prTotal.setCiZhang(prTotal.getCiZhang()+pr.getCiZhang()) ;
				prTotal.setXiZhang(prTotal.getXiZhang()+pr.getXiZhang()) ;
				prTotal.setWeiYuan(prTotal.getWeiYuan()+pr.getWeiYuan()) ;
				prTotal.setDaiLi(prTotal.getDaiLi()+pr.getDaiLi()) ;
				prTotal.setKeZhang(prTotal.getKeZhang()+pr.getKeZhang()) ;
				prTotal.setJianDu(prTotal.getJianDu()+pr.getJianDu());
				prTotal.setManagerKR(prTotal.getManagerKR()+pr.getManagerKR()) ;
				prTotal.setZhuanYuanKR(prTotal.getZhuanYuanKR()+pr.getZhuanYuanKR()) ;
				prTotal.setZhuRenKR(prTotal.getZhuRenKR()+pr.getZhuRenKR()) ;
				
				prTotal.setManager(prTotal.getManager()+prCN.getManager()) ;
				prTotal.setZhuGuan(prTotal.getZhuGuan()+prCN.getZhuGuan()) ;
				prTotal.setZhuanYuan(prTotal.getZhuanYuan()+prCN.getZhuanYuan()) ;
				prTotal.setZhuLi(prTotal.getZhuLi()+prCN.getZhuLi()) ;
				prTotal.setZhuRen(prTotal.getZhuRen()+prCN.getZhuRen()) ;
				prTotal.setMonitor(prTotal.getMonitor()+prCN.getMonitor()) ;
				prTotal.setWorkMan(prTotal.getWorkMan()+prCN.getWorkMan()) ;
				prTotal.setEngineer(prTotal.getEngineer()+prCN.getEngineer()) ;
				prTotal.setAssistantEngineer(prTotal.getAssistantEngineer()+prCN.getAssistantEngineer()) ;
				prTotal.setHighEngineer(prTotal.getHighEngineer()+prCN.getHighEngineer()) ;
				prTotal.setTeShuZhi(prTotal.getTeShuZhi()+prCN.getTeShuZhi());
				prTotal.setSubTotal(prTotal.getSubTotal()+pr.getSubTotal()+prCN.getSubTotal()) ;
				flag = false ;
	   		%>
	   		<%}} %>
     	</table>
     	</td>
     	</tr>
     	<tr>
   		<td align="center" >中方副总经理</td>
   		<td align="center" >
   		<table border="1">
   		<td align="center" ></td>
   		<% for(int i = 0 ; i < 14 ; i ++){ %>
   		<td align="center">0</td>
   		<%} %>
   		<td align="center">1</td>
   		<% for(int i = 0 ; i < 11 ; i ++){ %>
   			<td align="center">0</td>
   		<%} %>
   		<td align="center">1</td>
   		</table>
		</td>
   		</tr>
     	<tr>
   			<td align="center"></td>
   			<td align="center">
   			<table border="1">
   			<tr><td align="center">总计</td>
   			<td align="center">1</td>
   			<td align="center">3</td>
   			<td align="center"><%=prTotal.getChangWu() %></td>
   			<td align="center"><%=prTotal.getZongJian()+1 %></td>
   			<td align="center"><%=prTotal.getBuZhang() %></td>
   			<td align="center"><%=prTotal.getCiZhang() %></td>
   			<td align="center"><%=prTotal.getXiZhang() %></td>
   			<td align="center"><%=prTotal.getWeiYuan() %></td>
   			<td align="center"><%=prTotal.getKeZhang() %></td>
   			<td align="center"><%=prTotal.getDaiLi() %></td>
   			<td align="center"><%=prTotal.getJianDu() %></td>
   			<td align="center"><%=prTotal.getManagerKR() %></td>
   			<td align="center"><%=prTotal.getZhuanYuanKR() %></td>
   			<td align="center"><%=prTotal.getZhuRenKR() %></td>
   			
   			<td align="center">1</td>
   			<td align="center"><%=prTotal.getManager() %></td>
   			<td align="center"><%=prTotal.getZhuGuan() %></td>
   			<td align="center"><%=prTotal.getZhuanYuan() %></td>
   			<td align="center"><%=prTotal.getZhuLi() %></td>
   			<td align="center"><%=prTotal.getZhuRen() %></td>
   			<td align="center"><%=prTotal.getMonitor() %></td>
   			<td align="center"><%=prTotal.getWorkMan() %></td>
   			<td align="center"><%=prTotal.getHighEngineer() %></td>
   			<td align="center"><%=prTotal.getEngineer() %></td>
   			<td align="center"><%=prTotal.getAssistantEngineer() %></td>
   			<td align="center"><%=prTotal.getTeShuZhi() %></td>
   			<td align="center"><%=prTotal.getSubTotal()+6 %></td>
	   		</tr>
     	</table>
     	</td>
     	</tr>
 		<%}%>
</table>
