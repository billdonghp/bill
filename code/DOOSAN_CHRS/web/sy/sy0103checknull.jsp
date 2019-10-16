<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript">
  function Save(){ 
	   code=document.EM2Form.basicCode.value;
	   name=document.EM2Form.basicName.value;
	   enname=document.EM2Form.basicEnName.value; 
	   
	   var i=0;             
	   if(code=="")                  
	   {    
	    alert("基本代码不能为空！");i=i+1;      
	    return ;                      
	   }    
	   if(name=="")               
	   {                          
	    alert("基本代码名称不能为空！");i=i+1;
	      return;        
	   }    
	    if(enname=="")                                
	   {                   
	      alert("英文基本代码名称不能为空！");i=i+1;
	      return ;            
	   } 
	   if(i==0)             
	   {     
         document.EM2Form.submit();                  
        }    
	}  
</script>                                  