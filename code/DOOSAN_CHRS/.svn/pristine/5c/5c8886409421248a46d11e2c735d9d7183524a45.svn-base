
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsNorm"%>
<%@ page import="com.ait.evs.EvsDeptGrade"%>
<%@ page import="com.ait.evs.EvsGradeRate"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%
String evDeptGrade="";
String flag="";
String menu_code="";
String RedirectURL="";
EvsNorm evNorm;
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
evDeptGrade=request.getParameter("evDeptGrade")!=null?request.getParameter("evDeptGrade"):evDeptGrade;
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evDeptGrade="+evDeptGrade;
//添加评价基准
if(flag.equals("add")){
    Vector vEvEmpGrade=null;
	evNorm=new EvsNorm();
	int evEmpSum=-1;
	EvsDeptGrade evD=null;
    try{
    	evEmpSum=Integer.parseInt(request.getParameter("evEmpSum"));
    	vEvEmpGrade=evNorm.getVEvEmpGrade();
    	if(vEvEmpGrade!=null){
    		evD=new EvsDeptGrade();
    		evD.setEvDeptGradeId(evDeptGrade);
    		evD.setEvEmpSum(evEmpSum);
    		List lEvGradeRate=new Vector();
    		for(int i=0;i<vEvEmpGrade.size();i++){
    			HashMap h=(HashMap)vEvEmpGrade.get(i);
    			EvsGradeRate evr=new EvsGradeRate();
    			int count=0;
    			evr.setEvGrade(h.get("code").toString());
    			try{
    				count=Integer.parseInt(request.getParameter("evEmpGrade"+h.get("code").toString()));
    				
    			}catch(Exception e){}
    			evr.setEmpGradeCount(count);
    			lEvGradeRate.add(evr);
    		}
    		evD.setLEvsGradeRate(lEvGradeRate);
    		if(evEmpSum!=-1&&!evDeptGrade.equals("")&&vEvEmpGrade!=null){
    		evNorm.addEvNorm(evD);
    	}
    	}
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
//更新评价基准
if(flag.equals("update")){
	Vector vEvEmpGrade=null;
	evNorm=new EvsNorm();
	int evEmpSum=-1;
	EvsDeptGrade evD=null;
    try{
    	evEmpSum=Integer.parseInt(request.getParameter("evEmpSum"));
    	vEvEmpGrade=evNorm.getVEvEmpGrade();
    	if(vEvEmpGrade!=null){
    		evD=new EvsDeptGrade();
    		evD.setEvDeptGradeId(evDeptGrade);
    		evD.setEvEmpSum(evEmpSum);
    		List lEvGradeRate=new Vector();
    		for(int i=0;i<vEvEmpGrade.size();i++){
    			HashMap h=(HashMap)vEvEmpGrade.get(i);
    			EvsGradeRate evr=new EvsGradeRate();
    			int count=0;
    			evr.setEvGrade(h.get("code").toString());
    			try{
    				count=Integer.parseInt(request.getParameter("evEmpGrade"+h.get("code").toString()));
    			}catch(Exception e){}
    			evr.setEmpGradeCount(count);
    			lEvGradeRate.add(evr);
    		}
    		evD.setLEvsGradeRate(lEvGradeRate);
    	}
    	if(evEmpSum!=-1&&!evDeptGrade.equals("")&&vEvEmpGrade!=null){
    		evNorm.addEvNorm(evD);
    	}
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}

if(flag.equals("del")){
	
    evDeptGrade=request.getParameter("ID")!=null?request.getParameter("ID"):evDeptGrade;
    int evEmpSum=-1;
    RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evDeptGrade="+evDeptGrade;
    try{
    evEmpSum=Integer.parseInt(request.getParameter("ID2"));
    evNorm=new EvsNorm();
    evNorm.delEvNorm(evDeptGrade,evEmpSum);
    }catch(Exception e){}
    %>
		<SCRIPT LANGUAGE="JavaScript" type="">
		<!--
		alert("操作成功");
		location.href='<%=RedirectURL%>';
		//-->
		</SCRIPT>
		<%
	
}
%>
