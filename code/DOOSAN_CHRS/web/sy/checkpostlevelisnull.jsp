<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript">
  function Save(){ 
  
   var msg=new Array('<ait:message messageID="alert.sys.pay.salary_empty" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.pay.salary_empty_2" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.pay.range_english" module="sys"></ait:message>');
                   
	   id=document.PostXForm.postlevelid.value;
	   name=document.PostXForm.postlevelname.value;
	   enname=document.PostXForm.postlevelenname.value; 
	   
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