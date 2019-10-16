<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript">
  function Save(){ 
  
   var msg=new Array('<ait:message messageID="alert.sys.pay.grade_empty" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.pay.grade_name" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.pay.grade_name" module="sys"></ait:message>');
	   id=document.PostXForm.gradeid.value;
	   name=document.PostXForm.postgradename.value;             
	   enname=document.PostXForm.postgradeenname.value; 
	   if(id=="")                
	   {    
	    alert(msg[0])                     
	    return ;                            
	   }    
	   if(name=="")               
	   {                          
	    alert(msg[1]);
	      return;       
	   }  
	    if(enname=="")                    
	   {                  
	      alert(msg[2]);
	      return;                   
	   }      
         document.PostXForm.submit();      
	}  
</script>   