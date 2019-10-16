<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.AdminBean" errorPage="" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

          <select name="deptid">
            <option value="1"><!-- 请选择部门-->
				<ait:message  messageID="display.mutual.select"/></option>
            <%
            		
					String deptID = request.getParameter("deptid");
					for ( int i = 0 ; i < dept_list.size() ; i++ )
					{
						department = (Department) dept_list.get(i);
						int deptLevel = department.getDeptLevel();
						String deptname= "";
						if(admin1.getLanguagePreference().equals("zh")){
							deptname = department.getDeptName();
						}else if(admin1.getLanguagePreference().equals("ko")){
							deptname = department.getKorDept();
						}else{
							deptname = department.getDeptEnName();
						}
							
						for(int k=0;k<deptLevel;k++){
							deptname = "&nbsp;&nbsp;" + deptname;
						}						
						if(deptLevel ==1){
			%>
							
							<%}%>
            <option value="<%=department.getDeptID()%>" 
            <%if ((department.getDeptID()).equals(deptID)){%>selected<%}%> 
            	<%if(deptLevel==1){%>style="color:#FF0000"<%}%> >
            		<%=deptname%>
            </option>
            <%}%>
          </select>
          <%
			String searchcontent = request.getParameter("searchcontent");
			if(searchcontent == null)
			searchcontent = "";
			%>
			
     <input class="topsearch" name="searchcontent" type="text" id="searchcontent" lang="cs" value="<%=StringUtil.toCN(searchcontent)%>" >

