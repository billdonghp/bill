<%@ page language="java" 
import="java.util.*,com.ait.util.*,com.ait.hrm.business.HrmServices,com.ait.etn.EtnDepartment,com.ait.i18n.MessageSource,com.ait.sy.bean.AdminBean" 
pageEncoding="UTF-8"%>

<%
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	String code = request.getParameter("code")!=null?request.getParameter("code"):"";
	String con = "条件";
	String select ="请选择";
	if(code.equals("Department")){	
		if(!admin1.getLanguagePreference().equals("zh")){ 
			con = "condition";
			select = "select";
		}
		out.println(con+"<select name='values' style='width:150px' onchange='addcondition()'>");
        EtnDepartment etndepartment = new EtnDepartment();
        Vector dept_vector = etndepartment.getDept1();      
		for ( int i = 0 ; i < dept_vector.size() ; i++ ){           
				etndepartment = (EtnDepartment) dept_vector.get(i);           
				int deptLevel = Integer.parseInt(etndepartment.getDeptLevel());
				String deptname= "";
				if(admin1.getLanguagePreference().equals("zh")){
					deptname = etndepartment.getDeptName();
				}else if(admin1.getLanguagePreference().equals("ko")){
					deptname = etndepartment.getKorDeptName();
				}else{
					deptname = etndepartment.getEnDeptName();
				}
				for(int k=0;k<deptLevel;k++){
					deptname = "&nbsp;&nbsp;" + deptname;
				}						
        	out.println("<option value='"+etndepartment.getDeptId()+"'>"+deptname+"</option>");	
		}
		out.println("</select>");           
	}
		  
	if(code.equals("PostGrade")){
		Vector grade_vector = new Vector();
		
		if(admin1.getLanguagePreference().equals("zh")){
			grade_vector = SysCodeParser.getCode("HR_POST_GRADE",
					"POST_GRADE_ID", "POST_GRADE_NAME");
		}else if(admin1.getLanguagePreference().equals("ko")){
			grade_vector = SysCodeParser.getCode("HR_POST_GRADE",
					"POST_GRADE_ID", "POST_GRADE_KOR_NAME");
		}else{
			con = "condition";
			select = "select";
			grade_vector = SysCodeParser.getCode("HR_POST_GRADE",
					"POST_GRADE_ID", "Post_Grade_En_Name");
		}
			
		if(grade_vector.size()!=0){
			out.println(con+"<select name='values' style='width:150px' onchange='addcondition()' >");
			out.println("<option value=''>"+select+"</option>");
			for ( int i=0; i < grade_vector.size(); i++){
				HashMap codemap = (HashMap) grade_vector.elementAt(i);
				out.println("<option value='"+codemap.get("code")+"'>"+codemap.get("name")+"</option>");		
			}
			out.println("</select>");
		}else{
			out.println("<input name='values' type='text' onKeyPress=' if (event.keyCode==13) addcondition();' value='' class='topsearch'>");
		}			
	}
	
if(code.equals("PostGradeLevel")){		
		Vector grade_vector = new Vector();
		if(admin1.getLanguagePreference().equals("zh")){
			grade_vector = SysCodeParser.getCode("HR_POST_GRADE_LEVEL",
					"POST_GRADE_LEVEL_ID", "POST_GRADE_LEVEL_NAME"); 
		}else if(admin1.getLanguagePreference().equals("ko")){
			grade_vector = SysCodeParser.getCode("HR_POST_GRADE_LEVEL",
					"POST_GRADE_LEVEL_ID", "POST_GRADE_LEVEL_KOR_NAME"); 
		}else{
			con = "condition";
			select = "select";
			grade_vector = SysCodeParser.getCode("HR_POST_GRADE_LEVEL",
					"POST_GRADE_LEVEL_ID", "POST_GRADE_LEVEL_EN_NAME"); 
		}
			   
		if(grade_vector.size()!=0){
			out.println(con+"<select name='values' style='width:150px' onchange='addcondition()' >");
			out.println("<option value=''>"+select+"</option>");
			for ( int i=0; i < grade_vector.size(); i++){
					HashMap codemap = (HashMap) grade_vector.elementAt(i);
					out.println("<option value='"+codemap.get("code")+"'>"+codemap.get("name")+"</option>");
		
			}
			out.println("</select>");
		}else{
			out.println("<input name='values' type='text' onKeyPress=' if (event.keyCode==13) addcondition();' value='' class='topsearch'>");
		}			
	}

	if(code.equals("PostGroup")){		
		Vector group_vector = new Vector();
		if(admin1.getLanguagePreference().equals("zh")){
			group_vector = SysCodeParser.getCode("HR_POST_GROUP",
					"POST_GROUP_ID", "POST_GROUP_NAME");
		}else if(admin1.getLanguagePreference().equals("ko")){
			group_vector = SysCodeParser.getCode("HR_POST_GROUP",
					"POST_GROUP_ID", "POST_GROUP_KOR_NAME");
		}else{
			con = "condition";
			select = "select";
			group_vector = SysCodeParser.getCode("HR_POST_GROUP",
					"POST_GROUP_ID", "POST_GROUP_EN_NAME");
		}
			
		if(group_vector.size()!=0){
			out.println(con+"<select name='values' style='width:150px' onchange='addcondition()'>");
			out.println("<option value=''>"+select+"</option>");
			for ( int i=0; i < group_vector.size(); i++){
					HashMap codemap = (HashMap) group_vector.elementAt(i);
					out.println("<option value='"+codemap.get("code")+"'>"+codemap.get("name")+"</option>");		
			}
			out.println("</select>");
		}else{
			out.println("<input name='values' type='text' onKeyPress=' if (event.keyCode==13) addcondition();' value='' class='topsearch'>");
		}
	}
	
	
	if(code.equals("Post")){		
		Vector group_vector = new Vector();
		if(admin1.getLanguagePreference().equals("zh")){
			group_vector = SysCodeParser.getCode("HR_POST",
					"POST_ID", "POST_NAME");
		}else if(admin1.getLanguagePreference().equals("ko")){
			group_vector = SysCodeParser.getCode("HR_POST",
					"POST_ID", "POST_KOR_NAME");
		}else{
			con = "condition";
			select = "select";
			group_vector = SysCodeParser.getCode("HR_POST",
					"POST_ID", "POST_EN_NAME");
		}
			
		if(group_vector.size()!=0){
			out.println(con+"<select name='values' style='width:150px' onchange='addcondition()'>");
			out.println("<option value=''>"+select+"</option>");
			for ( int i=0; i < group_vector.size(); i++){
				HashMap codemap = (HashMap) group_vector.elementAt(i);
				out.println("<option value='"+codemap.get("code")+"'>"+codemap.get("name")+"</option>");		
			}
			out.println("</select>");
		}else{
			out.println("<input name='values' type='text' onKeyPress=' if (event.keyCode==13) addcondition();' value='' class='topsearch'>");
		}
	}
	
	
	if(!code.equals("PostGroup") && !code.equals("PostGrade") && !code.equals("Department")&&!code.equals("Post")){
		//全部从sy_code 取得
		Vector vc = SysCodeParser.getCode(code);
		if(!admin1.getLanguagePreference().equals("zh")){
			con = "condition";
			select = "select";
		}
		if(vc.size()!=0){
			out.println(con+"<select name='values' style='width:150px' onchange='addcondition()'>");
			out.println("<option value=''>"+select+"</option>");
			for ( int i=0; i < vc.size(); i++){
				HashMap codemap = (HashMap) vc.elementAt(i);
				if(admin1.getLanguagePreference().equals("zh")){
					out.println("<option value='"+codemap.get("code")+"'>"+codemap.get("name")+"</option>");
				}else if(admin1.getLanguagePreference().equals("ko")){
					out.println("<option value='"+codemap.get("code")+"'>"+codemap.get("korName")+"</option>");
				}else{
					out.println("<option value='"+codemap.get("code")+"'>"+codemap.get("enName")+"</option>");
				}
			}
			out.println("</select>");
		}else{
			out.println("<input name='values' type='text' onKeyPress=' if (event.keyCode==13) addcondition();' value='' class='topsearch'>");
		}
	}	
%>
