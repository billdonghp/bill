<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.sy.dao.*,com.ait.sy.bean.*,com.ait.util.*"%>
<% 
	String menu_code = request.getParameter("menu_code")!=null?request.getParameter("menu_code"):"";
	String affirmtypeId = request.getParameter("affirmtypeId")!=null?request.getParameter("affirmtypeId"):"";
	String deptcodeId = request.getParameter("deptcodeId")!=null?request.getParameter("deptcodeId"):"";
	String affirmid = request.getParameter("affirmid")!=null?request.getParameter("affirmid"):"";
	String empId = StringUtil.checkNull(request.getParameter("empId"));	
	String levelflag = request.getParameter("levelflag")!=null?request.getParameter("levelflag"):"";
	String affirmRelationNo = request.getParameter("affirmRelationNo")!=null?request.getParameter("affirmRelationNo"):"";
	String deleteallbydept=request.getParameter("deleteallbydept")!=null?request.getParameter("deleteallbydept"):"";
	String x = request.getParameter("x")!=null?request.getParameter("x"):"1";
	String y= request.getParameter("y")!=null?request.getParameter("y"):"1";  
	

    AffirmDAO affirmdao = new AffirmDAO();

	if(levelflag.equals("up")){
	   affirmdao.affirmLevelUp(affirmRelationNo);
    }
	if(levelflag.equals("down")){
	   affirmdao.affirmLevelDown(affirmRelationNo);
    }
	if(levelflag.equals("delete")){
	   affirmdao.affirmLevelDelete(affirmRelationNo);
    }

	if(levelflag.equals("deleteandback")){
		
		affirmdao.affirmLevelDelete(affirmRelationNo);
		response.sendRedirect("/sy/sy0130.jsp?menu_code="+menu_code+"&strPage="+x+"&bigpage="+y);	

	}else if(levelflag.equals("batch")){
		String newaffirmid = request.getParameter("newaffirmid")!=null?request.getParameter("newaffirmid"):"";		
		String affirmTypeId = request.getParameter("affirmTypeId") ;
		affirmdao.udpateAffirm(affirmid,newaffirmid,affirmRelationNo,affirmTypeId);
		
		response.sendRedirect("/sy/sy0130.jsp?menu_code="+menu_code+"&strPage="+x+"&bigpage="+y);	
	}else{
    	response.sendRedirect("/sy/sy0130_a.jsp?menu_code="+menu_code+"&affirmtypeId="+affirmtypeId+"&deptcodeId="+deptcodeId+"&affirmid="+affirmid+"&empId="+empId+"&x="+x+"&y="+y);
	}
%>
