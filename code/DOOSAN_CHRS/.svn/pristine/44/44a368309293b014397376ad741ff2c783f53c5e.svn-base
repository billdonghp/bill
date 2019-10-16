<%@ page contentType="text/html; charset=UTF-8" language="java"  import="com.ait.sqlmap.util.*" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.sy.sy0103.bean.*"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<SCRIPT language=JavaScript src="../js/table.js"></SCRIPT>
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.select_edit_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.select_delete_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.associated_deleting" module="sys"></ait:message>');
</script>
<script language="javascript" type="text/javascript">
<!--
   var codeNo="";
   function add(){   
      document.listForm.submit();                    
   }
  
	function getDetailCodeSub(a_codeNo){            
		HighLightTR("#E7F0EF", "black");
		codeNo = a_codeNo;                            
	}  
	
	function updateIFrame()
	{
		if(codeNo == ""){   
		 alert(msg[0]);                           
		}else{             
			url="sysCodeUpdate.jsp?codeNo=" + codeNo + "&menu_code=<%=request.getParameter("menu_code")%>";
			location.href=url;   
		}  
	}
-->
</script>
<body>
<%
  String code_id = request.getParameter("code_id")!=null?request.getParameter("code_id"):"";
  int depth = request.getParameter("depth")!=null?Integer.parseInt(request.getParameter("depth"))+1:2;
  Ent ent = new Ent();
  Biz biz = new Biz();
  Vector code_vector = new Vector();
  code_vector = biz.getLevelSecond(code_id);
  SimpleMap map=(SimpleMap)session.getAttribute("toolbarInfo");
%>
<div align="right">
<ait:image src="/images/btn/Add_little.gif"  border="0" align="absmiddle"
         	onclick="javascript:add();" style="cursor:hand" title="添加子代码" enTitle="add child code" />
<ait:image src="/images/btn/Modify_little.gif"  border="0" align="absmiddle"
        	onclick="javascript:updateIFrame();" style="cursor:hand" title="修改" enTitle="modify" />
</div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form name="listForm" method="post" action="/sy/sy_0103_a_depth1.jsp">
<input name="code_id" value="<%=code_id%>" type="hidden">
<input name="menu_code" value="sy0420" type="hidden">
<input name="depth" value="<%=depth %>" type="hidden">  
<tr align="center">      
     <td  height="30" class="info_title_01" nowrap="nowrap"><!--序号  -->
     <ait:message messageID="display.mutual.no"></ait:message>
     </td>                               
     <td  height="30" class="info_title_01" nowrap="nowrap"><!-- 基本代码 -->
       <ait:message messageID="display.sys.basic_maintenance.code_management.basic_code" module="sys"></ait:message>
     </td>   
     <td  height="30" class="info_title_01" nowrap="nowrap"><!-- 代码名称 -->
       <ait:message messageID="display.sys.basic_maintenance.code_management.code_name" module="sys"></ait:message>
     </td>     
     <td  height="30" class="info_title_01" nowrap="nowrap"><!--  英文代码名称-->
       <ait:message messageID="display.sys.basic_maintenance.code_management.code_name_english" module="sys"></ait:message>
     </td>
     <%if(depth!=1){ %>
     <td  height="30" class="info_title_01" nowrap="nowrap">法人区分</td>
     <%} %>
     <c:if test="${sessionScope.toolbarInfo.deleteMenu == 1}">
     <td  height="30" class="info_title_01" nowrap="nowrap">
     <%-- 
<!--     <a href="/Esy0420Control?flag=delete&no=<%=ent.getBasicCode()%>&menu_code=sy0103">-->
     <!-- 全部删除 -->
     --%>
     <ait:message messageID="display.sys.basic_maintenance.code_management.delete_all"  module="sys"></ait:message>
<!--     </a>-->
     </td> 
     </c:if>        
  </tr>
 
  <% for(int i=0; i<code_vector.size();i++){
         ent =(Ent)code_vector.elementAt(i);
  %>
  <tr align="center" onClick="javascript:getDetailCodeSub('<%=ent.getCodeNo() %>')">
     <td height="20" align="center" style="color: #666666;"><%=i+1%>&nbsp;</td>
     <td height="20" align="center" style="color: #666666;"><%=ent.getBasicCode()%>&nbsp;</td>
     <td height="20" align="center" style="color: #666666;"><%=ent.getBasicName()%>&nbsp;</td>
     <td height="20" align="center" style="color: #666666;"><%=ent.getCodeEnName()%>&nbsp;</td>  
     <%if(depth!=1){ %>
     <td height="20" align="center" style="color: #666666;"><%=ent.getCpnyname()%>&nbsp;</td>      
      <%} %>         
  <script language="javascript" type="text/javascript">                 
    function Delete(msg) {       
        if (confirm(msg[2])) {
            url = "/Esy0420Control?flag=delete&no=<%=ent.getBasicCode()%>&menu_code=sy0420&code_id=<%=code_id%>";
            //alert(url) ;
            location.href = url;
        } else {
            return false;  
        }
    }                        
</script>             
       <c:if test="${sessionScope.toolbarInfo.deleteMenu == 1}">
          <td height="30" align="center" style="color: #666666;">
          <%--
          <a href="/Esy0420Control?flag=delete&no=<%=ent.getBasicCode()%>&menu_code=sy0420&code_id=<%=code_id%>" >
           --%>
     <ait:image src="/images/btn/Delete_little.gif"  border="0" align="absmiddle" style="cursor:hand"  enTitle="delete" onclick="javascript:Delete(msg);"/>
     
<!--     </a>-->
     </td>
          </c:if>
  </tr>
  <%}%></form>
 </table>
 
</body>
</html>