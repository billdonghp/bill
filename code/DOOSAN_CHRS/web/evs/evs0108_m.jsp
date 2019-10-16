<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsNorm"%>
<%@ page import="com.ait.evs.EvsDeptGrade"%>
<%@ page import="com.ait.evs.EvsGradeRate"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<jsp:useBean id="codemap_grade" class="java.util.HashMap" scope="page"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 评价基准设置</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.sql.*,com.ait.utils.*" errorPage=""%>
<%@page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<%
String menu_code ="";
String rodeLevel ="";
ArrayList tool_menu=null;
menu_code = request.getParameter("menu_code");
admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
ToolMenu toolmenu = new ToolMenu();
MenuBiz menubiz = new MenuBiz();
tool_menu=menubiz.toolMenuList(menu_code,rodeLevel);
String imgname = menu_code.substring(0,2);

int insertMenu=0;
int updateMenu=0;
int deleteMenu=0;
for(int i=0;i<tool_menu.size();i++){
	toolmenu=(ToolMenu)tool_menu.get(i);
	if(toolmenu.getInsertr()==1){
		insertMenu=1;
	
	}
	if(toolmenu.getUpdate()==1){
		updateMenu=1;
	
	}
	if(toolmenu.getDelect()==1){
		deleteMenu=1;
	
	}
}
%>
<script language="JavaScript" type="" src="">
    function ModifySave()
    {

            document.LGFORM.action='/evs/<%=menu_code%>_t.jsp';
            document.LGFORM.submit();
    }
    </script>
<form name="LGFORM" method="POST" action="" >
<input type="hidden" name="flag" value="update">
<input type="hidden" name="menu_code" value="<%=menu_code%>" >
<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" valign="middle">
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" align="right" valign="middle"
					style="padding:3 0 3 0 "><a href="javascript:ModifySave();"><img
					src="/images/btn/save1.gif" width="67" height="24" border="0"
					align="absmiddle"> </a> <a href="javascript:history.back();"> <img
					src="/images/btn/return1.gif" width="67" height="24"
					align="absmiddle"> </a></td>
			</tr>
		</table>
		</td>
	</tr>

</table>
<%
EvsNorm evNorm=new EvsNorm();
String evDeptGradeId="";
int evEmpSum=-1;
evDeptGradeId=request.getParameter("ID")!=null?request.getParameter("ID"):"";
EvsDeptGrade evDeptGrade=null;
try{
	evEmpSum=Integer.parseInt(request.getParameter("ID2"));
	evDeptGrade=evNorm.getEvDeptGradeById(evDeptGradeId,evEmpSum);
}catch(Exception e){}	

Vector vEvDeptGrade=null;
EvsDeptGrade evD=null;
try{
	evD=evNorm.getEvDeptGradeById(evDeptGradeId,evEmpSum);
	vEvDeptGrade=SysCodeParser.getCode("EVDEPTGRADE");
}catch(Exception e){}
%>
<input type="hidden" value="<%=evDeptGradeId%>" name="evDeptGrade">
<input type="hidden" value="<%=evEmpSum%>" name="evEmpSum">
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">

	<tr>
		<td height="2"></td>
	</tr>
	<tr>
		<td class="line">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" >
				<td width="60" bgcolor="#F5F5F5">
				<div align="center">总人数</div>
				</td>
				<%
				Vector vEvEmpGrade=evNorm.getVEvEmpGrade();
				if(vEvEmpGrade!=null){
					int vEvEmpGradeSize=vEvEmpGrade.size();
					for(int i=0;i<vEvEmpGradeSize;i++){
						HashMap h=(HashMap)vEvEmpGrade.get(i);
						%>
						<td width="100/<%=vEvEmpGradeSize%>" bgcolor="#F5F5F5">
							<div align="center"><%=StringUtil.checkNull(h.get("name").toString())%>级人数</div>
						</td>
						<%
					}
				}
				%>
			</tr>
			<tr align="center" >
				<td width="60" >
				<div align="center"><%=evEmpSum%></div>
				</td>
				<%
				
				List vEvGradeRate=evD.getLEvsGradeRate();
				if(vEvGradeRate!=null){
					int vEvGradeRateSize=vEvGradeRate.size();
					for(int i=0;i<vEvGradeRateSize;i++){
						EvsGradeRate evr=(EvsGradeRate)vEvGradeRate.get(i);
						
						%>
						<td width="100/<%=vEvGradeRateSize%>" >
							<div align="center">
									<input type='text' name='evEmpGrade<%=evr.getEvGrade()%>' value='<%=evr.getEmpGradeCount()%>' size='10'>
							</div>
						</td>
						<%
					}
				}
				%>
			</tr>
		</table>
		</td>
	</tr>
</table>

</body>
</html>
<script language="JavaScript" type="" src="">
	
	
	function ModifySave()
    {
		if(checkSum()){
            document.LGFORM.action='/evs/<%=menu_code%>_t.jsp';
            document.LGFORM.submit();
		}	
    }
	function checkSum(){
		var sum=0;
		if(isNaN(document.LGFORM.evEmpSum.value)){
			alert("人数必须为数字！");
			document.LGFORM.evEmpSum.value="";
			document.LGFORM.evEmpSum.focus();
			return false;
		}else{
		<%
		 	if(vEvEmpGrade!=null){
				int vEvEmpGradeSize=vEvEmpGrade.size();
				for(int i=0;i<vEvEmpGradeSize;i++){
						HashMap h2=(HashMap)vEvEmpGrade.get(i);
		%>
			if(isNaN(document.LGFORM.evEmpGrade<%=h2.get("code").toString()%>.value)){
				alert("人数必须为数字！");
				document.LGFORM.evEmpGrade<%=h2.get("code").toString()%>.value="";
			    document.LGFORM.evEmpGrade<%=h2.get("code").toString()%>.focus();
				return false;
			}else{
				if(document.LGFORM.evEmpGrade<%=h2.get("code").toString()%>.value==''){
					document.LGFORM.evEmpGrade<%=h2.get("code").toString()%>.value=0;
				}
				count=document.LGFORM.evEmpGrade<%=h2.get("code").toString()%>.value;
				try{
					count=parseInt(count);
				}catch(e){
					count=0;	
				}
	
				sum=sum+count;
			}		
		<%
			}
		}
		%>
			if(sum!=document.LGFORM.evEmpSum.value){
				alert("各等级人数和与总人数不等！");
				return false;
			}
		}
		return true;
	}
</script>