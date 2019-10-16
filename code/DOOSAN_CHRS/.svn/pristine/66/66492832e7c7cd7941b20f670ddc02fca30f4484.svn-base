<%@ page language="java" import="java.util.*,com.ait.util.*,com.ait.sy.bean.*,com.ait.i18n.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/taglibs.jsp"%>
<jsp:useBean id="dao" class="com.ait.hrm.dao.report.Hr_Report_DAO" />
<%@page import="java.net.URLDecoder,java.net.URLEncoder,com.ait.i18n.MessageSource,com.ait.sy.bean.AdminBean"%>
<html> 
  <head>
    <title>人事报表</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>  
                                                   
  <body>              
    <%    
		HttpSession session1 = request.getSession(true);
		AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
		MessageSource messageSource1 = new MessageSource(admin1.getLocale(), "UTF-8");
		MessageSource messageSource2 = new MessageSource("hrm",admin1.getLocale(), "UTF-8");
    	String rowvalue = request.getParameter("rowvalue")!=null?request.getParameter("rowvalue"):"";
    	String rowtitle = request.getParameter("rowtitle")!=null?request.getParameter("rowtitle"):"";
    	String colvalue = request.getParameter("colvalue")!=null?request.getParameter("colvalue"):"";
    	String coltitle = request.getParameter("coltitle")!=null?request.getParameter("coltitle"):"";
     	String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
     	String rowtext = request.getParameter("rowtext")!=null?URLDecoder.decode(request.getParameter("rowtext"),"UTF-8"):"";
     	String coltext = request.getParameter("coltext")!=null?URLDecoder.decode(request.getParameter("coltext"),"UTF-8"):"";
     	String rowItem = request.getParameter("rowItem");
     	String colItem = request.getParameter("colItem");                  
     	Vector rowlist = new Vector();    
     	Vector collist = new Vector();
     	
     	if(!rowtitle.equals("")){ 
     		if(rowvalue.equals("post_group_id")){
     			if(admin1.getLanguagePreference().equals("zh")){
     				rowlist = SysCodeParser.getCode("hr_post_group",rowvalue,"post_group_name",rowItem);
     			}else if(admin1.getLanguagePreference().equals("ko")){
     				rowlist = SysCodeParser.getCode("hr_post_group",rowvalue,"POST_GROUP_KOR_NAME",rowItem);
     			}else{
     				rowlist = SysCodeParser.getCode("hr_post_group",rowvalue,"POST_GROUP_EN_NAME",rowItem);
     			}     			
     		}else if(rowvalue.equals("post_grade_id")){    
     			if(admin1.getLanguagePreference().equals("zh")){
     				rowlist = SysCodeParser.getCode("hr_post_grade","post_grade_id","post_grade_name",rowItem);
     			}else if(admin1.getLanguagePreference().equals("ko")){
     				rowlist = SysCodeParser.getCode("hr_post_grade","post_grade_id","POST_GRADE_KOR_NAME",rowItem);
     			}else{
     				rowlist = SysCodeParser.getCode("hr_post_grade","post_grade_id","POST_GRADE_EN_NAME",rowItem);
     			}
     		}else if(rowvalue.equals("post_code")){
     			if(admin1.getLanguagePreference().equals("zh")){
     				rowlist = SysCodeParser.getCode("hr_post","post_id","post_name",rowItem);
     			}else if(admin1.getLanguagePreference().equals("ko")){
     				rowlist = SysCodeParser.getCode("hr_post","post_id","POST_KOR_NAME",rowItem);
     			}else{
     				rowlist = SysCodeParser.getCode("hr_post","post_id","POST_EN_NAME",rowItem);
     			}
     		}else{
     			rowlist = SysCodeParser.getSysCodeV(rowtitle,rowItem,admin1.getLanguagePreference());
     		}
		}
     	
     	if(!coltitle.equals("")){     		            
     		if(colvalue.equals("post_group_id")){
     			if(admin1.getLanguagePreference().equals("zh")){
     				collist = SysCodeParser.getCode("hr_post_group",colvalue,"post_group_name",colItem);
     			}else if(admin1.getLanguagePreference().equals("ko")){
     				collist = SysCodeParser.getCode("hr_post_group",colvalue,"POST_GROUP_KOR_NAME",colItem);
     			}else{
     				collist = SysCodeParser.getCode("hr_post_group",colvalue,"POST_GROUP_EN_NAME",colItem);
     			}
     		}else if(colvalue.equals("post_grade_id")){
     			if(admin1.getLanguagePreference().equals("zh")){
         			collist = SysCodeParser.getCode("hr_post_grade","post_grade_id","post_grade_name",colItem);     				
     			}else if(admin1.getLanguagePreference().equals("ko")){
     				collist = SysCodeParser.getCode("hr_post_grade","post_grade_id","POST_GRADE_KOR_NAME",colItem);
     			}else{                      
         			collist = SysCodeParser.getCode("hr_post_grade","post_grade_id","POST_GRADE_EN_NAME",colItem);       				
     			}
     		}else if(colvalue.equals("post_code")){
     			if(admin1.getLanguagePreference().equals("zh")){
     				collist = SysCodeParser.getCode("hr_post","post_id","post_name",colItem);
     			}else if(admin1.getLanguagePreference().equals("ko")){
     				collist = SysCodeParser.getCode("hr_post","post_id","POST_KOR_NAME",colItem);
     			}else{
     				collist = SysCodeParser.getCode("hr_post","post_id","POST_EN_NAME",colItem);
     			}     				
     		}else{
     			collist = SysCodeParser.getSysCodeV(coltitle,colItem,admin1.getLanguagePreference());     					
     		}      
     	}
     	response.setHeader("Content-Type", "application/vnd.ms-excel; charset=gbk");
		response.setHeader("Content-Disposition", "attachment; filename=hr_report.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
     	if(rowlist.size()>0 && collist.size()>0 ){
     %> 
     <p align="center"><font size="5" color="red"><strong><%=rowtext+"/"+coltext%><!-- 报表 -->
        	<ait:message  messageID="report.hrm.organize.structure.report" module="report"/></strong></font></p>
     <table	align="center" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    	<tr>
    		<td align="center"  bgcolor="#ff6317" rowspan="1" colspan="1"><%=rowtext+"/"+coltext%></td>
    		<%
    			for(int i=0;i<collist.size();i++){
    				HashMap map = (HashMap)collist.get(i); 
    		%>		
				<td align="center"  bgcolor="#BFCAE6" rowspan="1" colspan="1"><%=URLDecoder.decode(map.get("name").toString(),"UTF-8")%></td>
			<%
				}
			 %>
    	</tr>
    	<%
    		for(int j=0;j<rowlist.size();j++){
    			HashMap rowmap = (HashMap)rowlist.get(j); 
    	 %>                 
    	<tr>
    		<td align="center"  bgcolor="#FEF889" rowspan="1" colspan="1"><%=URLDecoder.decode(rowmap.get("name").toString(),"UTF-8")%></td>
    		<%
    			for(int i=0;i<collist.size();i++){
    				HashMap colmap = (HashMap)collist.get(i); 
    		%>		
				<td align="center"  bgcolor="#CAFF70" rowspan="1" colspan="1">
				<%=dao.getHr_Counnt(rowvalue,colvalue,rowmap.get("code")+"",colmap.get("code")+"",deptid) %></td>
			<%
				}
			 %>
    	</tr>
    	<%
    	 	}
    	 %>  
    	 <tr>
    		<td align="center"  bgcolor="#ff6317" rowspan="1" colspan="1"><!-- 合计 -->
        	<ait:message  messageID="report.hrm.organize.structure.sum" module="report"/></td>
    		<%
    			for(int i=0;i<collist.size();i++){
    				HashMap map = (HashMap)collist.get(i); 
    		%>		
				<td align="center"  bgcolor="#ff6317" rowspan="1" colspan="1">
				<%=dao.getHr_Cot(colvalue,map.get("code")+"",deptid,rowvalue,rowItem)%>
				</td>
			<%
				}
			 %>
    	</tr>
    </table>                   
     <%
     	}else{
     		AdminBean admin = (AdminBean) session.getAttribute("admin");
     		MessageSource messageSource = new MessageSource("report",admin.getLocale(), "UTF-8");
     		String message = messageSource.getMessage("report.hrm.common.parameter");
     		
     		out.print(message);                   
     	}
      %>
  </body>
</html>
