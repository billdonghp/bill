<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.sy.sy0103.bean.*"%>
<%@ page import="com.ait.evs.evs0126.bean.*"%>   
<SCRIPT language=JavaScript>
	function save(){
		
	 var msg=new Array('<ait:message messageID="alert.sys.maintenance.code.basic_empty" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.code.basic_empty_2" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.code.basic_english" module="sys"></ait:message>');
	    code=document.evsCodeUpdateForm.codeId.value;
	   name=document.evsCodeUpdateForm.codeName.value;
	   enname=document.evsCodeUpdateForm.basicEnName.value; 
	   
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
       document.evsCodeUpdateForm.submit();                
        }               
        	
	}            
</SCRIPT>
<%
    String menu_code = request.getParameter("menu_code");
	String codeNo = request.getParameter("codeNo");
	EntEvsCode ent = BizEvsCode.select(codeNo);
	System.out.println(menu_code);
%>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<form  name="evsCodeUpdateForm" method="post" action="/E<%=menu_code%>Control?flag=updateSubCode&menu_code=<%=menu_code%>" onSubmit="return CheckForm()" >
  <input type="hidden" name="parentCode" value="<%=ent.getParentCode() %>" />
  <input type="hidden" name="descrIptioy" value="<%=ent.getDescriptio() %>" />
  <input type="hidden" name="codeNo" value="<%=codeNo %>" />
  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr>
      <td height="30" class="info_title_01" width="15%" nowrap="nowrap"><div align="left"><!-- 基本代码 -->
      <ait:message messageID="display.sys.basic_maintenance.code_management.basic_code" module="sys"></ait:message> 
      </div></td>
      <td class="info_content_01" width="85%"><div align="left">
          <input name="codeId" type="hidden" value="<%=ent.getBasicCode()%>"><%=ent.getBasicCode()%>
        </div></td>
    </tr>
    <tr>
      <td height="30" class="info_title_01" nowrap="nowrap"><div align="left"><!--基本代码名称  -->
         <ait:message messageID="display.sys.basic_maintenance.code_management.code_name" module="sys"></ait:message>
      </div></td>
      <td class="info_content_01" nowrap="nowrap"><div align="left">
          <input name="codeName" type="text" value="<%=ent.getBasicName()%>" />  
        </div></td>                                                                               
    </tr>
    <tr>
      <td height="30" class="info_title_01" nowrap="nowrap"><div align="left"><!-- 英文代码名称 -->
     <ait:message messageID="display.sys.basic_maintenance.code_management.code_name_english" module="sys"></ait:message>  
      </div></td>
      <td class="info_content_01"><div align="left">
          <input name="basicEnName" type="text" value="<%=ent.getCodeEnName()%>" />  
        </div></td>
    </tr>  
    <tr>  
      <td height="14" class="info_title_01" width="15%" nowrap="nowrap"><div align="left">工序难度系数
      </div></td>
      <td class="info_content_01">                           
        <div align="justify">
          <input name="descrIptio" type="text" value="<%=ent.getDescriptio()%>"/>
       </div>
     </td>
    </tr> 
    <%if(ent.getDepth()!=0 && ent.getDepth()!=1){ %> 
    <tr>  
      <td height="14" class="info_title_01" width="15%" nowrap="nowrap"><div align="left">
       法人区分  
      </div></td>
      <td class="info_content_01">                           
        <div align="justify">
          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" all="all" selected="<%=ent.getCpnyId()%>"/>
       </div>
     </td>
    </tr> 
    <%} %>         
    <tr>
          <td><a href="javascript:save();" ><ait:image src="/images/btn/Save_little.gif"  border="0" align="absmiddle" style="cursor:hand"/></a> </td>
      <td><a href="javascript:history.back();" ><ait:image src="/images/btn/Back.gif"  border="0" align="absmiddle" style="cursor:hand"/></a> </td>
    </tr>
  </table>
</form>
