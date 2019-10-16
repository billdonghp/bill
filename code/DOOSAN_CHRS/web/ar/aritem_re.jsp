<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.hr.bean.*,com.ait.ar.bean.*,java.util.*,com.ait.util.*"%>
<%@ page import="com.ait.ar.bean.ArItemBasic"%>
<jsp:useBean id="daob" scope="page" class="com.ait.ar.dao.ArItemBasicBean">
</jsp:useBean>
<%
ArrayList groupList=null;
groupList=daob.getGROUP();
ArItemBasic infog=null;
%>
<html>
<head>
<script language="javascript">
var a;
var grouplist=new Array();
<%if(groupList!=null){
   	 for(int i=0;i<groupList.size();i++){
	
      	infog=(ArItemBasic)groupList.get(i);
%>
a =new Object();
a["text"]="<%=infog.getGroupName()%>";
a["value"]="<%=infog.getGroupNo()%>";
grouplist[grouplist.length]=a;
<%}
}
%>
parent.opener.reloadgroupNo(grouplist);
self.close();
</script>
<title></title>
</head>

<body>

</body>
</html>
