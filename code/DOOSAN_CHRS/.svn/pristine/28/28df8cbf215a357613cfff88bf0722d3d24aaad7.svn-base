<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="java.util.*,com.ait.sy.sy0103.bean.*"%>
<%@ page import="com.ait.sy.bean.AdminBean" %>
<%
	Biz biz = new Biz();
	Ent ent = null;
	AdminBean admin = (AdminBean)session.getAttribute("admin");
	String adminId = admin.getAdminID();
	int affRow = 0;//记录修改影响的行数
	
	String action = request.getParameter("action");//执行何种操作
	
	String codeNo = request.getParameter("codeNo");//主键，ID
	String codeId = request.getParameter("codeId");//代码编号
	String codeName = request.getParameter("codeName");//代码名称
	String parentCode = request.getParameter("parentCode");//上级代码编号
	
		
	if(action != null){
		if(action.equalsIgnoreCase("add") && //执行添加操作
				codeId != null && !codeId.equalsIgnoreCase("") && //代码编号得有
				codeName != null && !codeName.equalsIgnoreCase("") && //代码名字得有
				parentCode != null && !parentCode.equalsIgnoreCase("")){//上级代码得有
			ent = new Ent();
			ent.setBasicCode(codeId);
			ent.setBasicName(codeName);
			ent.setParentCode(parentCode);
			ent.setCreatorID(adminId);
			ent.setDepth(1);//添加的是代码细目，类别写0
			ent.setCreatorID(adminId);
			biz.insert(ent);
		}else if(action.equalsIgnoreCase("update") && //执行修改操作
				codeNo != null && !codeNo.equalsIgnoreCase("") && //主键得有
				codeId != null && !codeId.equalsIgnoreCase("") && //代码编号得有
				codeName != null && !codeName.equalsIgnoreCase("") && //代码名字得有
				parentCode != null && !parentCode.equalsIgnoreCase("")){//上级代码得有){
			ent = Biz.select(codeNo);
			ent.setBasicCode(codeId);
			ent.setBasicName(codeName);
			ent.setParentCode(parentCode);
			ent.setModifierID(adminId);//谁改的
			affRow = biz.update(ent);//返回影响行-1就是出错了
		}else if(action.equalsIgnoreCase("delete") && 
				codeNo != null && !codeNo.equalsIgnoreCase("")){//主键得有
			affRow = biz.delete(codeNo);//返回影响行-1就是出错了
		}
	}
	
  Vector code_vector = biz.getLevelSecond(parentCode);
%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default1.css" rel="stylesheet" type="text/css">
</head>
<!--<SCRIPT language="JavaScript" src="/js/table.js"></SCRIPT>-->
<script language="javascript" type="text/javascript">
<!--

	var codeNo="";
	function add(){
		document.listForm.submit();
	}
	function getDetailCodeSub(a_codeNo,code){
		codeNo = a_codeNo;
		parent.window.opener.document.searchform.values.value=code;
	}
	
	function updateIFrame()
	{
		if(codeNo == ""){
		}else{
			url="sysCodeUpdate.jsp?codeNo=" + codeNo;
			location.href=url;
		}
	}
-->
</script>
<body>
<form name="listForm" method="post" action="/sy/sy_0103_a_depth1.jsp">
<input type="hidden" name="parentCode" value="<%=parentCode %>" />
<script language="JavaScript">
<!--

document.write ('<div style=\"overflow:auto\; width:100%; height:350;\">')
//-->
</script>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<tr align="center">
     <td width="10%" height="30" align="center" bgcolor="#F5F5F5">序号</td>
     <td width="10%" height="30" align="center" bgcolor="#F5F5F5">基本代码</td>
     <td width="45%" height="30" align="center" bgcolor="#F5F5F5">代码名称</td>
  </tr>
<%
		for(int i=0; i<code_vector.size();i++){
         ent =(Ent)code_vector.elementAt(i);
%>
  <tr align="center"  >
     <td height="30" align="center" ><%=i+1%>&nbsp;</td>
     <td height="30" align="center"  onClick="javascript:getDetailCodeSub('<%=ent.getCodeNo() %>','<%=ent.getBasicCode()%>')" style="cursor:hand" title="代码值" onmousemove="style.bgcolor='#99CCFF'">
     <%=ent.getBasicCode()%>&nbsp;</td>
     <td height="30" align="center" onClick="javascript:getDetailCodeSub('<%=ent.getCodeNo() %>','<%=ent.getBasicName()%>')" style="cursor:hand" title="代码名" onmousemove="this.bgcolor='#99CCFF' " onmouseout="this.bgcolor='black'">
	<%=ent.getBasicName() %>
     </td>
  </tr>
  <%}%>
 </table>
</div> 
 </form>
</body>
</html>