<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.ar.bean.Condition" %>
<%@ page import="com.ait.ar.dao.implementation.ConditionDAOImpl" %>
<%@ page import="com.ait.sy.bean.*" %>
<%
	List optionList = null;
	int no = -1;
	ConditionDAOImpl dao = new ConditionDAOImpl();
	/*传递登陆者ID*/
	AdminBean admin = (AdminBean)session.getAttribute("admin");
	String cpny_id = admin.getCpnyId();
	dao.setLoginID(admin.getAdminID());
	String conditionNo = request.getParameter("condition_no");
	String groupNo = request.getParameter("groupNo");
	if (conditionNo!= null || !conditionNo.equals("")){
		no = Integer.parseInt(conditionNo.trim());
	}
%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
	var conditionArray = new Array();

	function addValue(check,itemValue,index){
		if(check == false){
			conditionArray[index] = null;
		}
		if(check==true){
			conditionArray[index] = itemValue;
		}
	}
	function setValue(){
		var str = "";
		for (var i=0;i<conditionArray.length;i++){
			if (conditionArray[i]!=null)
			{
				if(str != ""){
					str += ",";
				}
				str += conditionArray[i];
			}
		}
		opener.document.ar.<%=request.getParameter("target")%>.value = str;

		self.close();
	}
	
	function CheckAll(check,value)
	{
		for (var i=0;;i++)
		{
			var e =document.all(check+i);
			if(e==null) break;
			e.checked = document.all('checkAll').checked;
			if (e.checked ==true){
				conditionArray[i] =document.all(value+i).value ;
			}
			if (e.checked ==false){
				conditionArray[i] = null;
			}
		}
	}
</script>

<body style="font-size:12px ">
<table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">

<%
	if(no == 1){%>

     <tr height="5">
			<td width="300"  class="info_title_01"">
			<input type="checkbox" name="checkAll" onClick="javaScript:CheckAll('conditions','value');"></td>
          	<td width="80" height="24"  class="info_title_01" nowrap="nowrap"><!--条件(工号)-->
					<ait:message  messageID="display.mutual.emp_id"/></td>
          	<td width="100" height="24"  class="info_title_01"><!--姓名-->
					<ait:message  messageID="display.mutual.name"/></td>
          	<td width="100" height="24"  class="info_title_01"><!--部门名称-->
					<ait:message  messageID="display.mutual.department_2"/></td>
      </tr>
<%		optionList = (ArrayList)dao.getOptions(no, admin.getLanguagePreference(),cpny_id);
		StringBuffer existEmpStr = dao.getExistEmpList(groupNo, "empid");
		for(int i = 0;i<optionList.size();i++){
			StringTokenizer st = new StringTokenizer((String)optionList.get(i), "|");
			String item[] = new String []{st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()};
			if(existEmpStr.indexOf(item[0].trim()) != -1){
				continue;
			}
%>
 			<tr height="5">
				<td width="5%">
					<input type="checkbox" name="conditions<%=i%>" onClick="javaScript:addValue(this.checked,'<%=item[0]%>','<%=i%>');" />
				</td>
				<td width="25%"><%=item[0]%></td>
				<td width="20%"><%=item[1]%></td>
				<td width="50%"><%=item[2]%></td>
				<input type="hidden" name="value<%=i%>" value = "<%=item[0]%>">
			</tr>
<%	
			}
	}else{
		optionList = (ArrayList)dao.getOptions(no,cpny_id);
		for(int i = 0;i<optionList.size();i++){
			Condition condition = (Condition)optionList.get(i);
				if (condition != null) {%>
			<tr height="5">
				<td width="30">
					<input type="checkbox" name="conditions<%=i%>" onClick="javaScript:addValue(this.checked,'<%=condition.getField_id()%>','<%=i%>');">
				</td>
					<td width="258" height="24">
					<%  
						String field = null;
						if (admin.getLanguagePreference().equals("zh"))
							field = condition.getField_id();
						else 
							field = condition.getField_en_id();
					%>
					<%=field%>
					</td>
				<input type="hidden" name="value<%=i%>" value = "<%=condition.getField_id()%>"/>
			</tr>
			<%}
		}
	}
%>
</table>
<ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle"
          	onclick="javaScript:setValue();" style="cursor:hand"/>

</body>
</html>
