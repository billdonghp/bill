<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.*" errorPage="" %>
<%@ include file="/inc/taglibs.jsp"%>
<jsp:include page="/inc/hrnav.jsp"/>
<%@ include file="/inc/importa.jsp"%>
<SCRIPT LANGUAGE="JavaScript" src="../js/sy0103.js"></SCRIPT>
<script language="javascript1.2">
<!--hidden
  function addSyCode(){
  
     var msg=new Array('<ait:message messageID="alert.sys.maintenance.code.basic_empty" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.code.basic_empty_2" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.code.basic_english" module="sys"></ait:message>');
      code=document.EM2Form.basicCode.value;
	   name=document.EM2Form.basicName.value;
	   enname=document.EM2Form.basicEnName.value; 
	   
	   var i=0;             
	   if(code=="")                             
	   {    
	    alert(msg[0]);i=i+1;      
	    return ;                      
	   }    
	   if(name=="")               
	   {                          
	    alert(msg[1]);i=i+1;
	      return;        
	   }                                                                          
	    if(enname=="")                                
	   {                   
	      alert(msg[2]);i=i+1;
	      return ;            
	   } 
	   if(i==0)             
	   {     
         document.EM2Form.submit();                  
        }    
  }
  
--></script>
<% 
String code_id = request.getParameter("code_id")!=null?request.getParameter("code_id"):"";
menu_code = request.getParameter("menu_code")!=null?request.getParameter("menu_code"):"";
String depth = request.getParameter("depth")!=null?request.getParameter("depth"):"2";                 
%>
<div align="right">
<ait:image src="/images/btn/Save_little.gif"  border="0" align="absmiddle" onclick="javascript:addSyCode();" style="cursor:hand"/>
<a href="javascript:history.back();" ><ait:image src="/images/btn/Back.gif"  border="0" align="absmiddle" style="cursor:hand"/></a>
</div><br>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form  name="EM2Form" method="post" action="/E<%=menu_code%>Control?flag=insert&menu_code=<%=menu_code%>" onSubmit="return CheckForm()" >
 <input name="parentCode" type="hidden" id="BasicName2" value="<%=code_id%>">
 <input name="depth" type="hidden" value="<%=depth %>">
    <tr>
      <td height="30" class="info_title_01" width="15%" nowrap="nowrap"><div align="left">
      <!-- 基本代码 -->
      <ait:message messageID="display.sys.basic_maintenance.code_management.basic_code" module="sys"></ait:message></div></td>
      <td class="info_content_01">
        <div align="justify">
          <input name="basicCode" type="text" id="BasicCode">
        </div>
      </td>
    </tr>
    <tr>
      <td height="14" class="info_title_01" width="15%" nowrap="nowrap"><div align="left">
     <ait:message messageID="display.sys.basic_maintenance.code_management.code_name" module="sys"></ait:message>
      </div></td>
      <td class="info_content_01">
        <div align="justify">
          <input name="basicName" type="text" id="BasicName">
       </div>
     </td>
    </tr>
     <tr>  
      <td height="14" class="info_title_01" width="15%" nowrap="nowrap"><div align="left">
       <ait:message messageID="display.sys.basic_maintenance.code_management.code_name_english" module="sys"></ait:message>  
      </div></td>
      <td class="info_content_01">                           
        <div align="justify">
          <input name="basicEnName" type="text" id="basicEnName"/>
       </div>
     </td>
    </tr> 
    <%if(!depth.equals("0") && !depth.equals("1")){ %> 
    <tr>  
      <td height="14" class="info_title_01" width="15%" nowrap="nowrap"><div align="left">
       法人区分  
      </div></td>
      <td class="info_content_01">                           
        <div align="justify">
          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" all="all"/>
       </div>
     </td>
    </tr>
   <%} %>                
  </form>
</table>

