<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../inc/taglibs.jsp"%>
<script language="javascript">
  function Save(){ 
  
  var msg=new Array('<ait:message messageID="alert.sys.maintenance.dept.enter_id" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.dept.id_length" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.dept.dept_name" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.dept.dept_length" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.dept.end_start" module="sys"></ait:message>');
	   id=document.EM2Form.deptId.value;
	   name=document.EM2Form.deptName.value;
	   enname=document.EM2Form.deptEnName.value; 
<%--	   endEddate=document.EM2Form.endEddate.value;--%>
	   if(id=="")                
	   {    
	    alert(msg[0])                            
	    return ;                            
	   }    
	   if(name=="")               
	   {                          
	    alert(msg[2]);
	      return;                    
	   }  
	    if(enname=="")                    
	   {                  
	      alert(msg[2]);
	      return;                   
	   } 

    if(document.EM2Form.endEddate != null && document.EM2Form.createdDate.value != "" ){
		if( compareDate(document.EM2Form.createdDate.value,document.EM2Form.endEddate.value)!=-1 && document.EM2Form.endEddate.value != "")
		{
			alert(msg[4]);  
			return ;
		}
	} 
         document.EM2Form.submit();      
}
	
 function compareDate(a,b){
var aYear = parseInt(a.substr(0,4));
var aMonth = parseFloat(a.substr(a.indexOf('-')+1,2));
var aDay = parseFloat(a.substr(a.lastIndexOf('-')+1,2));
var bYear = parseInt(b.substr(0,4));
var bMonth = parseFloat(b.substr(b.indexOf('-')+1,2));
var bDay = parseFloat(b.substr(b.lastIndexOf('-')+1,2));
if(aYear<bYear){ return -1;}
else if(aYear==bYear){
  if(aMonth<bMonth){return -1;}
  else if(aMonth==bMonth){
    if(aDay<bDay){
    return -1;}
    else if(aDay==bDay){
    return 0;}
    else{
    return 1;}
  }
  else{return 1;}
}
else {return 1;}
}
	           
</script>   