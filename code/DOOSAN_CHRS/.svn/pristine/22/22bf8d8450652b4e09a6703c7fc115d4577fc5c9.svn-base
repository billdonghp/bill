package com.ait.evs.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.commons.dao.CommonDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.evs.EvsCraft;
import com.ait.evs.EvsPeriod;
import com.ait.evs.Evsupcode;
import com.ait.evs.business.EvsServices;
import com.ait.evs.evs0126.bean.BizEvsCode;
import com.ait.evs.evs0126.bean.EntEvsCode;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;
import com.ibm.icu.math.BigDecimal;

public class EvsjinxingaddvaluateCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvsjinxingaddvaluateCmd.class);
			
			@Override
			public String execute(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
				MessageSource messageSource ;
				int pageSize = 0;
				int pageGroupSize = 0;
				int currentPage = 0;
				int resultCount = 0;   
				AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
			   String ev_emp_id = request.getParameter("ev_emp_id");
			   String ev_period_id = request.getParameter("ev_period_id");
			   String ev_type_id = request.getParameter("ev_type_id");
			   String craftid = request.getParameter("craftid");
			   String evstypecode = request.getParameter("evstypecode");
			   String evslinecode = request.getParameter("evslinecode");
			   String evsaircraftcode = request.getParameter("evsaircraftcode");
			   String evsprocesscode = request.getParameter("evsprocesscode");
			   String evsjcoentcode = request.getParameter("evsjcoentcode");
			   String typeionid = request.getParameter("typeionid");
			   String qualificationid = request.getParameter("qualificationid");
			   String skillscore = request.getParameter("skillscore")==null?"0":request.getParameter("skillscore");
			   String purposeid = request.getParameter("purposeid")==null?"否":request.getParameter("purposeid");
			   String proficiency = request.getParameter("proficiency")==null?"0":request.getParameter("proficiency");
			   String sheopercyq = request.getParameter("sheopercyq")==null?"否":request.getParameter("sheopercyq");
			   String operationcom = request.getParameter("operationcom");
			   String qualityofwork = request.getParameter("qualityofwork");
			   String standardaction = request.getParameter("standardaction");
			   String composite = request.getParameter("composite");
			   String skileid = request.getParameter("skileid");   
			   String sumscore = request.getParameter("sumscore");   
			   String confirmdate = request.getParameter("confirmdate");  
			   String proveby = request.getParameter("proveby");  
			   String provedate = request.getParameter("provedate");  
			   String evsperiod = request.getParameter("evsperiod");  
			   
			   String deptke = request.getParameter("deptke");  
			   String deptzhi = request.getParameter("deptzhi");  
			   String deptzu = request.getParameter("deptezu");  
			   
			   
			   String REMARK = request.getParameter("REMARK"); 
			   //String flag = request.getParameter("flag");    
			EvsServices services = EvsServices.getInstance();
			SimpleMap simpleMap = new SimpleMap(); 
			simpleMap = ObjectBindUtil.getData(request);
			 simpleMap.put("empID", ev_emp_id);
			 simpleMap.put("period", ev_period_id);
			 simpleMap.put("evTypeId", ev_type_id);
			List listcht=new ArrayList();
			Evsupcode evsUpcode = new Evsupcode();
			String countemp = "1";
			List searchCount;
			Object searchEmpList ;
			List searchPeriodList;
			List searchCraftList;
			List searchSkitypeList;
			List searchLineList;
			List searchAircraftList;
			List searchProcessList;
			List searchJobcontentList;
			List searchTypetionList;
			List searchEvsSkilelist;
			List searchEvsQualificationList;
			List searchEvsPurposeList;
			List<SimpleMap>  craftbyLine = null;
			try { 
				    
					EntEvsCode ent = BizEvsCode.selectid(evsjcoentcode);
		        	String descriptio = ent.getDescriptio();	
					if(descriptio==null)
		        		descriptio ="0";
		        	Double skillstored =0.0;
		        	
		        	BigDecimal descriptiod = new BigDecimal(descriptio.toString());  
		            BigDecimal composited = new BigDecimal(composite.toString());  
		            //BigDecimal ddd =  new BigDecimal("10"); 
		        
		        	skillstored = composited.multiply(descriptiod).doubleValue();
	        	
				
					evsUpcode.setEVS_EMPID(ev_emp_id);
				    evsUpcode.setEV_PERIOD_ID(evsperiod);
				    evsUpcode.setEV_TYPE_ID(ev_type_id);
				    evsUpcode.setCRAFT(craftid);
				    evsUpcode.setSKILLTYPE(evstypecode);
				    evsUpcode.setLINE(evslinecode);
				    evsUpcode.setAIRCRAFT(evsaircraftcode);
				    evsUpcode.setPROCESS(evsprocesscode);
				    evsUpcode.setJOBCONTENT(evsjcoentcode);
				    evsUpcode.setTYPEOPERATION(typeionid);
				    evsUpcode.setQUALIFICATION(qualificationid);
				    evsUpcode.setSKILLSCORE(String.valueOf(skillstored));
				    evsUpcode.setPURPOSE(purposeid);
				    evsUpcode.setPROFICIENCY(proficiency);
				    evsUpcode.setSHEOPERCYQ(sheopercyq);
				    evsUpcode.setOPERATIONCOM(operationcom);
				    evsUpcode.setQUALITYOFWORK(qualityofwork);
				    evsUpcode.setSTANDARDACTION(standardaction);
				    evsUpcode.setCOMPOSITE(composite);
				    evsUpcode.setSKILLLEVEL(skileid);
				    evsUpcode.setREMARK(REMARK);
				    evsUpcode.setCREATED_BY(admin.getAdminID());
				    evsUpcode.setSUMSCORE(sumscore);
				    evsUpcode.setCONFIRMDATE(confirmdate);
				    evsUpcode.setPROVEBY(proveby);
				    evsUpcode.setPROVEDATE(provedate);
				    evsUpcode.setDeptke(deptke);
				    evsUpcode.setDeptzhi(deptzhi);
				    evsUpcode.setDeptzu(deptzu);
				    
				    
				    
				    listcht.add(evsUpcode);
					services.insertJinxing(listcht);
					
					searchCount =  services.getEvsCountEmp(simpleMap);
					 searchEmpList = services.getEvsempcodeList(simpleMap);
					  searchPeriodList= services.getEvsYearIDList();//查询年度
					  searchCraftList = services.getEvsCraftList();//查询工种
					 searchSkitypeList = services.getEvsSkitypeList(); //查询技能类型
					 searchLineList = services.getEvsLineList(); //查询Line线
					 searchAircraftList = services.getEvsAircraftList(); //查询机种
					 searchProcessList = services.getEvsProcesstList(); //查询工序
					 searchJobcontentList = services.getEvsJobcontentList(); //查询作业内容
					 searchTypetionList = services.getEvsTypetionList();//查询作业类型
					 searchEvsSkilelist = services.getEvsSkilelist();//技能等级
					 searchEvsQualificationList = services.getEvsQualificationList();//职业资格
					 searchEvsPurposeList = services.getEvsPurposeList();//培训目标
					 /*for(int i=0;i<searchCount.size();i++)
					 {  
						 //先查数据的第一列
						 SimpleMap countList = (SimpleMap) searchCount.get(i);
						 String craft = (String) countList.get("CRAFT");
						 simpleMap.put("craft", craft);
						 //查询联动
						 CommonDAO commonDAO = new CommonDAO();
						 craftbyLine = commonDAO.retrievecraftByLine(simpleMap);
					 }*/
					 
				}
		     catch (Exception e) {
				logger.error(e.toString());
				request.setAttribute("update", 2);
				throw new GlRuntimeException(
						"The information Exception when running the IsertExpiredContract. ",
						e);
			}
			resultCount = listcht.size();
			request.setAttribute("craftbyLine", craftbyLine); 
			request.setAttribute("searchEmpList", searchEmpList); 
			request.setAttribute("searchPeriodList", searchPeriodList); 
			request.setAttribute("searchCraftList", searchCraftList); 
			request.setAttribute("searchSkitypeList", searchSkitypeList); 
			request.setAttribute("searchLineList", searchLineList); 
			request.setAttribute("searchAircraftList", searchAircraftList); 
			request.setAttribute("searchProcessList", searchProcessList); 
			request.setAttribute("searchJobcontentList", searchJobcontentList); 
			request.setAttribute("searchTypetionList", searchTypetionList); 
			request.setAttribute("searchEvsSkilelist", searchEvsSkilelist); 
			request.setAttribute("searchEvsQualificationList", searchEvsQualificationList); 
			request.setAttribute("searchEvsPurposeList", searchEvsPurposeList); 
			request.setAttribute("listcht", listcht); 
			request.setAttribute("searchCount", searchCount); //数据库原有的数据有几条查几条
			request.setAttribute("craftid", craftid); 
			request.setAttribute("skileid", skileid); 
			request.setAttribute("countemp", countemp);        
			request.setAttribute("resultCount", resultCount);                    
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);  
			request.setAttribute("update", 1);
			return "/evs0202_eval.jsp";
		}
}
