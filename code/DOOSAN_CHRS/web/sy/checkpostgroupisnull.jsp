<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../inc/taglibs.jsp"%>
<script language="javascript">
  function Save(){ 
  
  var msg=new Array('<ait:message messageID="alert.sys.pay.job_id" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.pay.job_name" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.pay.job_english_name" module="sys"></ait:message>');

	   id=document.PostXForm.groupid.value;
	   name=document.PostXForm.postgroupname.value;
	   enname=document.PostXForm.postgroupenname.value; 
	   
	   var i=0;                 
	   if(id=="")                    
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
         document.PostXForm.submit();         
        }    
	}  
</script>                                  