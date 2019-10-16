
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.evs.EvsDeptGrade"%>
<%@ page import="com.ait.evs.EvsEmpGrade"%>
<%@ page import="com.ait.evs.EvsDeptRadio"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%
//部门等级。员工等级
Vector vEvDeptGrade=null;
Vector vEvEmpGrade=null;
int vEvDeptGradeSize=0;
int vEvEmpGradeSize=0;
try{
vEvDeptGrade=SysCodeParser.getCode("EVDEPTGRADE");
vEvEmpGrade=SysCodeParser.getCode("EVEMPGRADE");
}catch(Exception e){System.out.println(e.getMessage());}
if(vEvDeptGrade!=null){
	vEvDeptGradeSize=vEvDeptGrade.size();
}
if(vEvEmpGrade!=null){
	vEvEmpGradeSize=vEvEmpGrade.size();
}
String evDeptId="";
String evPeriodId="";
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;

String flag="";
String menu_code="";
String RedirectURL="";
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evDeptId="+evDeptId+"&evPeriodId="+evPeriodId;
EvsDeptRadio evsDeptRadio;
//添加部门百分数
if(flag.equals("add")){
	try{
    	evsDeptRadio=new EvsDeptRadio();
    	
    	if(vEvDeptGrade!=null){
    	
    		List lEvDeptGrade=new Vector();
			for(int i=0;i<vEvDeptGradeSize;i++){
				EvsDeptGrade evsDeptGrade=new EvsDeptGrade();
				HashMap mEvDeptGrade=(HashMap)vEvDeptGrade.get(i);
				String evDeptGradeId=(String)mEvDeptGrade.get("code");
				evsDeptGrade.setEvDeptGradeId(evDeptGradeId);
				if(vEvEmpGrade!=null){
					List lEvEmpGrade=new Vector();
					for(int j=0;j<vEvEmpGradeSize;j++){
						HashMap mEvEmpGrade=(HashMap)vEvEmpGrade.get(j);
						String evEmpGradeId=(String)mEvEmpGrade.get("code");
						EvsEmpGrade evsEmpGrade=new EvsEmpGrade();
						
						float evGradeProp=0;
						float evWageRadio=0;
						String sEvGradeProp="";
						String sEvWageRadio="";
						sEvGradeProp=request.getParameter(evDeptGradeId+"_"+evEmpGradeId+"_"+"gradeProp")!=null?request.getParameter(evDeptGradeId+"_"+evEmpGradeId+"_"+"gradeProp"):"";
						sEvWageRadio=request.getParameter(evDeptGradeId+"_"+evEmpGradeId+"_"+"wageRadio")!=null?request.getParameter(evDeptGradeId+"_"+evEmpGradeId+"_"+"wageRadio"):"";
						
						try{
							evGradeProp=Float.parseFloat(sEvGradeProp);
						}catch(Exception e){
							evGradeProp=0;
						}
						try{
							evWageRadio=Float.parseFloat(sEvWageRadio);
						}catch(Exception e){
							evWageRadio=0;
						}
						evsEmpGrade.setEvEmpGradeId(evEmpGradeId);
						evsEmpGrade.setEvGradeProp(evGradeProp);
						evsEmpGrade.setEvWageRadio(evWageRadio);
						lEvEmpGrade.add(evsEmpGrade);
					}
					evsDeptGrade.setLEvsEmpGrade(lEvEmpGrade);
				}
				lEvDeptGrade.add(evsDeptGrade);
			}
			if(evDeptId.equals("")){
				
				evsDeptRadio.batchAddEvDeptRadioByEvDepts(lEvDeptGrade,evPeriodId);
			}else{
				
				evsDeptRadio.updateEvsDeptRadioByEvDept(evDeptId,evPeriodId,lEvDeptGrade);
			}
		}	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
//更新部门百分数
if(flag.equals("update")){
    try{
    	evsDeptRadio=new EvsDeptRadio();
    	
    	if(vEvDeptGrade!=null){
    		List lEvDeptGrade=new Vector();
			for(int i=0;i<vEvDeptGradeSize;i++){
				EvsDeptGrade evsDeptGrade=new EvsDeptGrade();
				HashMap mEvDeptGrade=(HashMap)vEvDeptGrade.get(i);
				String evDeptGradeId=(String)mEvDeptGrade.get("code");
				evsDeptGrade.setEvDeptGradeId(evDeptGradeId);
				if(vEvEmpGrade!=null){
					List lEvEmpGrade=new Vector();
					for(int j=0;j<vEvEmpGradeSize;j++){
						HashMap mEvEmpGrade=(HashMap)vEvEmpGrade.get(j);
						String evEmpGradeId=(String)mEvEmpGrade.get("code");
						EvsEmpGrade evsEmpGrade=new EvsEmpGrade();
						
						float evGradeProp=0;
						float evWageRadio=0;
						String sEvGradeProp="";
						String sEvWageRadio="";
						sEvGradeProp=request.getParameter(evDeptGradeId+"_"+evEmpGradeId+"_"+"gradeProp")!=null?request.getParameter(evDeptGradeId+"_"+evEmpGradeId+"_"+"gradeProp"):"";
						sEvWageRadio=request.getParameter(evDeptGradeId+"_"+evEmpGradeId+"_"+"wageRadio")!=null?request.getParameter(evDeptGradeId+"_"+evEmpGradeId+"_"+"wageRadio"):"";
						
						try{
							evGradeProp=Float.parseFloat(sEvGradeProp);
						}catch(Exception e){
							evGradeProp=0;
						}
						try{
							evWageRadio=Float.parseFloat(sEvWageRadio);
						}catch(Exception e){
							evWageRadio=0;
						}	evsEmpGrade.setEvEmpGradeId(evEmpGradeId);
						evsEmpGrade.setEvGradeProp(evGradeProp);
						evsEmpGrade.setEvWageRadio(evWageRadio);
						lEvEmpGrade.add(evsEmpGrade);
					}
					evsDeptGrade.setLEvsEmpGrade(lEvEmpGrade);
				}
				lEvDeptGrade.add(evsDeptGrade);
			}	
			evsDeptRadio.updateEvsDeptRadioByEvDept(evDeptId,evPeriodId,lEvDeptGrade);
		}	
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
//删除部门百分数
if(flag.equals("del")){
    String evPeriodId_DEL = (request.getParameter("ID") != null) ? request.getParameter("ID").trim(): "";
	String evDeptId_DEL=(request.getParameter("ID2") != null) ? request.getParameter("ID2").trim(): "";
	try{
		evsDeptRadio=new EvsDeptRadio();
		evsDeptRadio.delEvsDeptRadioByEvDept(evDeptId_DEL,evPeriodId_DEL);
		RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evDeptId="+evDeptId_DEL+"&evPeriodId="+evPeriodId_DEL;
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
%>
