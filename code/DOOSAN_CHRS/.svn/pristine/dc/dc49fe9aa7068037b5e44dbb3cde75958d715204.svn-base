package com.ait.evs.command;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import com.ait.commons.dao.CommonDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.evs.Evsupcode;
import com.ait.evs.business.EvsServices;
import com.ait.evs.dao.EvsCommonDAO;
import com.ait.evs.evs0126.bean.BizEvsCode;
import com.ait.evs.evs0126.bean.EntEvsCode;
import com.ait.pa.dao.report.PaImportPayBankDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;
import com.ibm.icu.math.BigDecimal;

public class ImportsetupcodeCommand implements Command {

	private static final Logger logger = Logger.getLogger(ImportsetupcodeCommand.class);

	private static final FileItemFactory factory = new DiskFileItemFactory();

	private AdminBean admin = null ;
	
	private List<Map<String,String>> errorList = null ;
	
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		try {
			
			admin = (AdminBean) request.getSession().getAttribute("admin");
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField()) {
					this.processExcel(item.getInputStream());
					message = "导入成功";
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			message = "导入失败,请检查文件";
		}
		request.setAttribute("errorList", errorList);
		request.setAttribute("alert", message);
		request.setAttribute("reload", "parent.opener.location.reload();parent.close()");
		return "/inc/alertPaImportSetupMessage.jsp";
	}

	private void processExcel(InputStream in) throws Exception {
		Map<Integer, String> dates = new HashMap<Integer, String>();
		Map<String, String> errorMap = null ;
		
		errorList = new ArrayList<Map<String,String>>() ;
		
		HSSFWorkbook book = new HSSFWorkbook(in);
		HSSFSheet sheet = book.getSheetAt(0); //只处理第一个sheet
		PaImportPayBankDAO dao = PaImportPayBankDAO.getInstance();
		EvsCommonDAO evsdao = new EvsCommonDAO();
		EvsServices services = EvsServices.getInstance();
		Evsupcode evsUpcode = new Evsupcode();
		
		List searchCount;
		SimpleMap simpleMap = new SimpleMap(); 
		
		
		String personId = null;
		String empId = null ;
		String chinesename = null ;
		String craftid = null ;//工种
		String evslinecode = null ;//岗位
		String evsjcoentcode = null ;//工序
		
		double proficiency = 0.0 ;//熟练度分数
		double operationcom = 0.0 ;//作业标准分数
		double qualityofwork = 0.0 ;//作业品质分数
		double standardaction = 0.0 ;//标准动作分数
		
		   
		   String ev_period_id = ""; //评价年度
		   String ev_type_id = "";		  
		   String evstypecode = "";
		   String evsaircraftcode = "";
		   String evsprocesscode = "";
		   String typeionid = "";
		   String qualificationid = "";
		   String purpose = "";
		   String sheopercyq = "";		 
		   String skileid = "";   
		   String sumscore = "0.0";   
		   String confirmdate = "";  
		   String proveby = "";  
		   String provedate = "";  
		   String REMARK ="";
		   String status ="";
		   
		   String deptke = "";  
		   String deptzhi ="";
		   String deptzu ="";
		   
		   double sumtores =0.0;
		for (int i = 1; i <= 65535; ++i) {
			List listcht=new ArrayList();
			
			HSSFRow row = sheet.getRow(i);
			if (row == null)
			{
				break;
			}
			else {
				
							
				HSSFCell cell = row.getCell(0);

				
				// 得到工号empId
				if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING)
					empId = StringUtils.trimToEmpty(row.getCell(0).getRichStringCellValue().getString());
				else if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					empId = String.valueOf((int) row.getCell(0).getNumericCellValue());

				if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING)
					chinesename = StringUtils.trimToEmpty(row.getCell(1).getRichStringCellValue().getString());
				else if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					chinesename = String.valueOf((int) row.getCell(1).getNumericCellValue());
				
				if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING)
					ev_period_id = StringUtils.trimToEmpty(row.getCell(2).getRichStringCellValue().getString());
				else if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					ev_period_id = String.valueOf((int) row.getCell(2).getNumericCellValue());
				
				//deptke = row.getCell(3).toString();
				//deptzhi = row.getCell(4).toString();
				//deptzu = row.getCell(5).toString();
				
				provedate = row.getCell(3).toString();
				
				if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING)
					craftid = StringUtils.trimToEmpty(row.getCell(4).getRichStringCellValue().getString());			

				if (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_STRING)
					evslinecode = StringUtils.trimToEmpty(row.getCell(5).getRichStringCellValue().getString());
			
				if (row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_STRING)
					evsjcoentcode = StringUtils.trimToEmpty(row.getCell(6).getRichStringCellValue().getString());
				

				
				if (row.getCell(7).getCellType() == HSSFCell.CELL_TYPE_STRING)
					proficiency = Double.parseDouble(row.getCell(7).getRichStringCellValue().getString());
				else if (row.getCell(7).getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					proficiency = (double) row.getCell(7).getNumericCellValue();
				
				if (row.getCell(8).getCellType() == HSSFCell.CELL_TYPE_STRING)
					standardaction = Double.parseDouble(row.getCell(8).getRichStringCellValue().getString());
				else if (row.getCell(8).getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					standardaction = (double) row.getCell(8).getNumericCellValue();
				
				if (row.getCell(9).getCellType() == HSSFCell.CELL_TYPE_STRING)
					operationcom = Double.parseDouble(row.getCell(9).getRichStringCellValue().getString());
				else if (row.getCell(9).getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					operationcom = (double) row.getCell(9).getNumericCellValue();
				
				if (row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_STRING)
					qualityofwork = Double.parseDouble(row.getCell(10).getRichStringCellValue().getString());
				else if (row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					qualityofwork = (double) row.getCell(10).getNumericCellValue();
						
				//if (row.getCell(11).getCellType() == HSSFCell.CELL_TYPE_STRING)
			//	REMARK = StringUtils.trimToEmpty(row.getCell(11).getRichStringCellValue().getString());
				if(row.getCell(11)!=null)
				REMARK = row.getCell(11).toString();
				
				if (row.getCell(12).getCellType() == HSSFCell.CELL_TYPE_STRING)
					proveby = StringUtils.trimToEmpty(row.getCell(12).getRichStringCellValue().getString());			
				
					confirmdate = row.getCell(13).toString();
			    if(confirmdate!=null&&(confirmdate.equals("是")||confirmdate.equals("yes")||confirmdate.equals("YES")))
			    	confirmdate="yes";
			    else
			    	confirmdate="no";
			    	
				if (row.getCell(14).getCellType() == HSSFCell.CELL_TYPE_STRING)
					sheopercyq = StringUtils.trimToEmpty(row.getCell(14).getRichStringCellValue().getString());
				
				if(sheopercyq!=null&&(sheopercyq.equals("是")||sheopercyq.equals("yes")||sheopercyq.equals("YES")))
					sheopercyq ="yes";
				else
					sheopercyq ="no";
				
				if (row.getCell(15).getCellType() == HSSFCell.CELL_TYPE_STRING)
					purpose = StringUtils.trimToEmpty(row.getCell(15).getRichStringCellValue().getString());			

				if(purpose!=null&&(purpose.equals("是")||purpose.equals("yes")||sheopercyq.equals("YES")))
					purpose ="yes";
				else
					purpose ="no";
				
				
				
				if (StringUtils.isNotEmpty(empId)) {
					personId = dao.getPersonIdByEmpId(empId, "78000000") ; 
					if (personId.equals("ERROR")) {
						
						errorMap = new HashMap<String, String>();
						errorMap.put("empId", empId) ;
						errorMap.put("giveName", chinesename) ;
						errorMap.put("remark", "工号不存在") ;
						
						
						errorList.add(errorMap) ;
						
						continue;
					}else{
						
						if (StringUtils.isNotEmpty(personId) && StringUtils.isNotEmpty(ev_period_id) 
								&& StringUtils.isNotEmpty(evsjcoentcode)) {
							
							
							try { 
								
								
								EntEvsCode ent = BizEvsCode.selectid(evsjcoentcode);
								if(ent==null){
									errorMap = new HashMap<String, String>();
									errorMap.put("empId", empId) ;
									errorMap.put("giveName", chinesename) ;
									errorMap.put("remark", evsjcoentcode+"  工序不存在") ;
									
									
									errorList.add(errorMap) ;
									continue;
								}
								
								deptke = evsdao.getDeptkeByEmpId(personId) ; 
								deptzhi = evsdao.getDeptzhiByEmpId(personId) ; 
								deptzu = evsdao.getDeptzuByEmpId(personId) ; 
								
					        	String descriptio2 = ent.getDescriptio();
					        	SimpleMap parameterObject = new SimpleMap();
					        	CommonDAO commonDAO = new CommonDAO();
					        	
					        
					        	
					        	if(descriptio2==null)
					        		descriptio2 ="0";
					        	double skillstored =0.0;
					        	double composited =0.0;
					        	
					        	
					        	
					        	Double descriptiod = new Double(descriptio2.toString());  
					            
					            //BigDecimal ddd =  new BigDecimal("10"); 
					        
					            composited = proficiency + standardaction + operationcom + qualityofwork;
					            
					        	
					        	if(composited>10){
					        		errorMap = new HashMap<String, String>();
									errorMap.put("empId", empId) ;
									errorMap.put("giveName", chinesename) ;;
									errorMap.put("remark", "积分大于10") ;
									
									errorList.add(errorMap) ;
									continue;
					        	}
					        	
					        	if(composited<=8){
					        		skillstored = 0.0;
					        	}else					        	
					        	skillstored = composited*descriptiod/10;
					        	
					        	
					        	parameterObject.put("pro",String.valueOf(composited));
					        	
					        	List<SimpleMap> lPosition = commonDAO.retrieveLineByPro(parameterObject);
					        	
					        	if(lPosition.size()>0){
									for (SimpleMap p : lPosition) {
										
										skileid = p.getString("DENGJI");
										
									}
					        	}
					        	//sumtores = sumtores+skillstored;
					        	
								 simpleMap.put("empID", personId);
								 simpleMap.put("periodid", "");
								 simpleMap.put("jobcontent", evsjcoentcode);
								 simpleMap.put("purpose", purpose);
								
								searchCount =  services.getEvsCountEmp(simpleMap);
								////判断导入数据是否存在
								if(searchCount.size()>0){
									
									 Map searchCountList = (Map)searchCount.get(0);
									
									status = (String)searchCountList.get("STATUS");
									if(status!=null&&status.equals("1")){							        	
										
										evsUpcode.setSETUPCODENO((String)searchCountList.get("SETUPCODENO"));
										evsUpcode.setEVS_EMPID(personId);
									    evsUpcode.setEV_PERIOD_ID(ev_period_id);
									    evsUpcode.setEV_TYPE_ID(ev_type_id);
									    evsUpcode.setCRAFT(craftid);
									    evsUpcode.setSKILLTYPE(evstypecode);
									    evsUpcode.setLINE(evslinecode);
									    evsUpcode.setAIRCRAFT(evsaircraftcode);
									    evsUpcode.setPROCESS(evsprocesscode);
									    evsUpcode.setJOBCONTENT(evsjcoentcode);
									    evsUpcode.setTYPEOPERATION(typeionid);
									    evsUpcode.setQUALIFICATION(qualificationid);
									    evsUpcode.setSKILLSCORE(String.format("%.1f", skillstored));
									    evsUpcode.setPURPOSE(purpose);
									    evsUpcode.setPROFICIENCY(String.valueOf(proficiency));
									    evsUpcode.setSHEOPERCYQ(sheopercyq);
									    evsUpcode.setOPERATIONCOM(String.valueOf(operationcom));
									    evsUpcode.setQUALITYOFWORK(String.valueOf(qualityofwork));
									    evsUpcode.setSTANDARDACTION(String.valueOf(standardaction));
									    evsUpcode.setCOMPOSITE(String.format("%.1f", composited));
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
										services.updateJinxing(listcht);
									}
									
								}else{
									 evsUpcode.setEVS_EMPID(personId);
									    evsUpcode.setEV_PERIOD_ID(ev_period_id);
									    evsUpcode.setEV_TYPE_ID(ev_type_id);
									    evsUpcode.setCRAFT(craftid);
									    evsUpcode.setSKILLTYPE(evstypecode);
									    evsUpcode.setLINE(evslinecode);
									    evsUpcode.setAIRCRAFT(evsaircraftcode);
									    evsUpcode.setPROCESS(evsprocesscode);
									    evsUpcode.setJOBCONTENT(evsjcoentcode);
									    evsUpcode.setTYPEOPERATION(typeionid);
									    evsUpcode.setQUALIFICATION(qualificationid);
									    evsUpcode.setSKILLSCORE(String.format("%.1f", skillstored));
									    evsUpcode.setPURPOSE(purpose);
									    evsUpcode.setPROFICIENCY(String.valueOf(proficiency));
									    evsUpcode.setSHEOPERCYQ(sheopercyq);
									    evsUpcode.setOPERATIONCOM(String.valueOf(operationcom));
									    evsUpcode.setQUALITYOFWORK(String.valueOf(qualityofwork));
									    evsUpcode.setSTANDARDACTION(String.valueOf(standardaction));
									    evsUpcode.setCOMPOSITE(String.format("%.1f", composited));
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
										
										
								}
								////////////////							   
							
							}
						     catch (Exception e) {
								logger.error(e.toString());
								System.out.print(e.toString());
								throw new GlRuntimeException(
										"The information Exception when running the IsertExpiredContract. ",
										e);
							}
						//	dao.insertOther(paMonth, personId,giveName,Double.toString(PayAmount),Double.toString(PerWelfareSum),Double.toString(IncomeTax),Double.toString(NetAmount));
						
							logger.debug(empId + " | " + chinesename + " | " + admin.getCpnyId());
						}else
							{
								errorMap = new HashMap<String, String>();
								errorMap.put("empId", empId) ;
								errorMap.put("payBank", chinesename) ;
								errorMap.put("remark", "年度为空") ;
								errorList.add(errorMap) ;
								continue;
							}
					}
				}else{
					errorMap = new HashMap<String, String>();
					errorMap.put("empId", empId) ;
					errorMap.put("payBank", chinesename) ;
					errorMap.put("remark", "职号为空") ;
					errorList.add(errorMap) ;
					continue;
				}
			}
		}////循环结束
	}
}