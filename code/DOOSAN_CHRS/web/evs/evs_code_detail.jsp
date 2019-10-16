<%@ page contentType="text/html; charset=UTF-8" language="java"  import="com.ait.sqlmap.util.*" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.sy.sy0103.bean.*"%>
<%@ page import="com.ait.evs.evs0126.bean.*"%>   
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
function Delete(msg,cell) {

	   if (confirm(msg[2])) {
	  		var otNo = cell.parentNode.parentNode.childNodes[0].firstChild.value ;
	  		var codeId = cell.parentNode.parentNode.childNodes[1].firstChild.value ;
            url = "/Eevs0126Control?flag=delete&no=" + otNo + "&menu_code=evs0126&code_id="+codeId;
          
            // alert(url) ;
            
            location.href = url;
        } else {
            return false;  
        }
             
}

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
			url="evsCodeUpdate.jsp?codeNo=" + codeNo + "&menu_code=<%=request.getParameter("menu_code")%>";
			location.href=url;   
		}  
	}
-->
</script>
<body>
<%
  String code_id = request.getParameter("code_id")!=null?request.getParameter("code_id"):"";
  int depth = request.getParameter("depth")!=null?Integer.parseInt(request.getParameter("depth"))+1:2;
  EntEvsCode ent = new EntEvsCode();
  BizEvsCode biz = new BizEvsCode();
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
<form name="listForm" method="post" action="/evs/inc/evs_0103_a_depth.jsp">
<input name="code_id" value="<%=code_id%>" type="hidden">
<input name="menu_code" value="evs0126" type="hidden">
<input name="depth" value="<%=depth %>" type="hidden">  
<tr align="center">      
     <td  height="30" class="info_title_01" nowrap="nowrap"><!--序号  -->
     <ait:message messageID="display.mutual.no"></ait:message>
     </td>                               
     <td  height="30" class="info_title_01" nowrap="nowrap">岗位
     </td>   
      <td  height="30" class="info_title_01" nowrap="nowrap"><!-- 代码名称 -->
       <ait:message messageID="display.sys.basic_maintenance.code_management.code_name" module="sys"></ait:message>
     </td>     
     <td  height="30" class="info_title_01" nowrap="nowrap"><!--  英文代码名称-->
       <ait:message messageID="display.sys.basic_maintenance.code_management.code_name_english" module="sys"></ait:message>
     </td> 
     
     <td  height="30" class="info_title_01" nowrap="nowrap">创建人</td>
       <td  height="30" class="info_title_01" nowrap="nowrap">创建时间</td>
       <td  height="30" class="info_title_01" nowrap="nowrap">修改人</td>
       <td  height="30" class="info_title_01" nowrap="nowrap">修改时间</td>
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
         ent =(EntEvsCode)code_vector.elementAt(i);
  %>
  <tr align="center" onClick="javascript:getDetailCodeSub('<%=ent.getCodeNo() %>')">
     <td height="20" align="center" style="color: #666666;"><input type="hidden" name="basicCodeId" value="<%=ent.getBasicCode()%>"> <%=i+1%>&nbsp;</td>
     <td height="20" align="center" style="color: #666666;"> <input type="hidden" name="codeId" value="<%=code_id%>"> <%=ent.getBasicCode()%>&nbsp;</td>
     <td height="20" align="center" style="color: #666666;"><%=ent.getBasicName()%>&nbsp;</td>
     <td height="20" align="center" style="color: #666666;"><%=ent.getCodeEnName()%>&nbsp;</td>      
     <td height="20" align="center" style="color: #666666;"><%=ent.getCreatorID()%>&nbsp;</td>        
     <td height="20" align="center" style="color: #666666;"><%=ent.getCreateDate()%>&nbsp;</td>     
   <td height="20" align="center" style="color: #666666;"><%=ent.getModifierID()%>&nbsp;</td>        
     <td height="20" align="center" style="color: #666666;"><%=ent.getModifyDate()%>&nbsp;</td> 
  <%--         
  <script language="javascript" type="text/javascript">                 
    function Delete(msg) {       
        if (confirm(msg[2])) {
            url = "/Eevs0126Control?flag=delete&no=<%=ent.getBasicCode()%>&menu_code=evs0126&code_id=<%=code_id%>";
            //alert(url) ;
            location.href = url;
        } else {
            return false;  
        }
    }                        
</script> 
--%>              
       <c:if test="${sessionScope.toolbarInfo.deleteMenu == 1}">
          <td height="30" align="center" style="color: #666666;">
          <%--
          <a href="/Esy0420Control?flag=delete&no=<%=ent.getBasicCode()%>&menu_code=sy0420&code_id=<%=code_id%>" >
           --%>
     <ait:image src="/images/btn/Delete_little.gif"  border="0" align="absmiddle" style="cursor:hand"  enTitle="delete" onclick="javascript:Delete(msg,this);"/>
     
<!--     </a>-->
     </td>
          </c:if>
  </tr>
  <%}%></form>
 </table>
 
</body>
</html>