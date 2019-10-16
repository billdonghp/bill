<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.NumberUtil" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="departmentListForFlag" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>生产性分析部门标识</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript"> 
function Search(){
 document.form1.action="/arControlServlet?operation=MESFlag&content=search&menu_code=${param.menu_code}";
 document.form1.submit();
}
function Save(){
 document.form2.action="/arControlServlet?operation=MESFlag&content=update&menu_code=${param.menu_code}&deptID="+document.form1.deptID.value;
 document.form2.submit();
}
function checkAll()
{
  var selected = document.form2.ck_all.value == "0" ? true : false;
  var selectarg=document.getElementsByName("selectC");
  for(var i=0;i<selectarg.length;i++)
  {    
      selectarg[i].checked = selected;     
   
  }
  document.form2.ck_all.value = selected ? "1" : "0";
}
function selectFlag(num){

	var selectCheckbox=document.getElementsByName("selectC");
	for(var i=0;i<selectCheckbox.length;i++){
	if(selectCheckbox[i].value==num){
	selectCheckbox[i].checked=true
	}	
	}

}

</SCRIPT>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/toolbar_save_only.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				生产性标识
				</td>
			</tr>  
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>		 
		<form action="" name="form1" method="post">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >		
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	      <tr>
		    <td align="center" class="info_content_01" nowrap="nowrap">
			    <!-- 部门 --><ait:message messageID="display.mutual.dept" />&nbsp;:&nbsp; 
			    <ait:selDept name="deptID" selected="${deptID}" supervisorType="ar"/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>	        
		    <td align="center" class="info_content_01" nowrap="nowrap">
				<ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
		    </td>
	        </tr>
	      </table>	      
	      </td>
		</tr>
		</table>
		</form>
		<form action="" name="form2" method="post"> 
		<input name="ck_all" value="0" type="hidden"/>		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		<td nowrap="nowrap" class="info_title_01"><a href="#" onclick="checkAll();" style="color:red">全选</a></td>
		<td nowrap="nowrap" class="info_title_01">部门代码</td>
		<td nowrap="nowrap" class="info_title_01">部门名称</td>
		<td nowrap="nowrap" class="info_title_01">所属</td>
		<td nowrap="nowrap" class="info_title_01">MES供给标识</td>
		<td nowrap="nowrap" class="info_title_01">部门状态标识</td>			
		</tr>
		<%for(int i=0;i<departmentListForFlag.size();i++){ 
			dataMap=(SimpleMap)departmentListForFlag.get(i);%>		
		<tr align="center">
		<td nowrap="nowrap" align="center" class="info_content_01"><input type="checkbox" name="selectC" value="<%=i%>"/></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><%=dataMap.get("DEPTID")%><input type="hidden" name="deptid<%=i%>" value="<%=dataMap.get("DEPTID")%>"></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><%=dataMap.get("DEPTNAME")%></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><%=dataMap.get("OFFICE")%></td>
		<td nowrap="nowrap" align="center" class="info_content_01"><select name="MESFlag<%=i%>" onchange="selectFlag(<%=i%>)"><option value="1" <%if(dataMap.get("MES_FLAG").equals("1")){ %> selected="selected"<%} %>>提供</option><option value="0"<%if(dataMap.get("MES_FLAG").equals("0")){ %> selected="selected"<%} %> >不提供</option></select></td>
		<td nowrap="nowrap" align="center" class="info_content_01">
		<select name="activeFlag<%=i%>" onchange="selectFlag(<%=i%>)">
		<option value="1" <%if(NumberUtil.parseInt(dataMap.get("ACTIVEFLAG"))==1){ %> selected="selected"<%} %>>正常</option>
		<option value="0" <%if(NumberUtil.parseInt(dataMap.get("ACTIVEFLAG"))==0){ %> selected="selected"<%} %>>失效</option>
		</select>
		</td>
		</tr>		
		<%} %>	   
		</table>
		</form>
		
		 <!-- Page Navigation Start-->
					<ait:page       
		               link="/arControlServlet"
		               parameters="&operation=MESFlag&content=search&menu_code=${param.menu_code}&deptID=${deptID}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/> 
	  </td>     
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</body>

</html>
