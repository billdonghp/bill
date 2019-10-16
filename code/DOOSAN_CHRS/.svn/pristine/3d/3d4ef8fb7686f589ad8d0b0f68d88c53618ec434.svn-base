package com.ait.pa.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import com.ait.pa.dao.report.PaImportPayBankDAO;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class PaImportPayBankCommand implements Command {

	private static final Logger logger = Logger.getLogger(PaImportPayBankCommand.class);

	private static final FileItemFactory factory = new DiskFileItemFactory();

	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

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
		return "/inc/alertPaImportPayBankMessage.jsp";
	}

	private void processExcel(InputStream in) throws Exception {
		Map<Integer, String> dates = new HashMap<Integer, String>();
		Map<String, String> errorMap = null ;
		
		errorList = new ArrayList<Map<String,String>>() ;
		
		HSSFWorkbook book = new HSSFWorkbook(in);
		HSSFSheet sheet = book.getSheetAt(0); //只处理第一个sheet
		HSSFRow header = sheet.getRow(0); //第一行做为表头
//		for (int i = 1; i <= 255; ++i) {
//			HSSFCell cell = header.getCell(i);
//			if (cell == null)
//			{
//				break;
//			}
//			else {
//				Date date = cell.getDateCellValue();
//				dates.put(i, formatter.format(date));
//			}
//		}
		PaImportPayBankDAO dao = PaImportPayBankDAO.getInstance();
//		ArImportShiftDAO dao = ArImportShiftDAO.getInstance();
		// 得到所有班次NO
		//List<String> shiftList = dao.getAllShiftList() ;
		
		for (int i = 1; i <= 65535; ++i) {
			HSSFRow row = sheet.getRow(i);
			if (row == null)
			{
				break;
			}
			else {
				String personId = null;
				String empId = null ;
				String payBank = null ;
				String bankCardName = null ;
				HSSFCell cell = row.getCell(0);
				
				// 得到工号empId
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					empId = StringUtils.trimToEmpty(row.getCell(0).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					empId = String.valueOf((int) row.getCell(0).getNumericCellValue());
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					payBank = StringUtils.trimToEmpty(row.getCell(1).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					payBank = String.valueOf((int) row.getCell(1).getNumericCellValue());
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					bankCardName = StringUtils.trimToEmpty(row.getCell(2).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					bankCardName = String.valueOf((int) row.getCell(2).getNumericCellValue());
				
				if (StringUtils.isNotEmpty(empId)) {
					personId = dao.getPersonIdByEmpId(empId, admin.getCpnyId()) ; 
					if (personId.equals("ERROR")) {
						
						errorMap = new HashMap<String, String>();
						errorMap.put("empId", empId) ;
						errorMap.put("payBank", payBank) ;
						errorMap.put("bankCardName", bankCardName) ;
						
						
						errorList.add(errorMap) ;
					}else{
						if (StringUtils.isNotEmpty(personId) && StringUtils.isNotEmpty(payBank)) {
							
							dao.update(payBank, personId,bankCardName);
							logger.debug(empId + " | " + payBank + " | " + admin.getCpnyId());
						}
						else
							{
								errorMap = new HashMap<String, String>();
								errorMap.put("empId", empId) ;
								errorMap.put("payBank", payBank) ;
								//errorMap.put("isSupervisor", isSupervisor ? "YES" : "NO") ;
								
								errorList.add(errorMap) ;
							}
					}
				}
				
				
				
//				if (StringUtils.isNotEmpty(empId)) {
//					personId = dao.getPersonIdByEmpId(empId, admin.getCpnyId()) ; 
//					if (personId.equals("ERROR")) {
//						
//						errorMap = new HashMap<String, String>();
//						errorMap.put("empId", empId) ;
//						errorMap.put("dateStr", "") ;
//						errorMap.put("isSupervisor", "NO") ;
//						
//						errorList.add(errorMap) ;
//					}
//					else{
//						for (int j = 1; j <= dates.size(); ++j) {
//							String dateStr = dates.get(j);
//							
//							boolean isSupervisor = dao.isArSupervisorExist(personId,admin.getAdminID()) ;
//							
//							logger.debug(personId + " | " + dateStr + " | " + isSupervisor);
//							if (isSupervisor) {
//								cell = row.getCell(j);
//								if (cell != null) {
//									int shiftNo = 0;
//									if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
//										shiftNo = (int) cell.getNumericCellValue();
//									else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
//										shiftNo = NumberUtils.toInt(cell.getRichStringCellValue().getString());
//									if (shiftNo > 0){
//										//logger.debug("Invalid Shift Number: " + cell.getRichStringCellValue().getString());
//										//throw new Exception("Invalid Shift Number");
//										
//										boolean isShiftNo = shiftList.contains(shiftNo) ; 
//										logger.debug(personId + " | " + dateStr + " | " + shiftNo + " | " + isShiftNo);
//										
//										if (isShiftNo){
//											if (dao.isExist(dateStr, personId))
//												dao.update(dateStr, personId, admin.getCpnyId());
//											else
//												dao.insert(dateStr, personId, shiftNo);
//										}
//										else{
//											errorMap = new HashMap<String, String>();
//											errorMap.put("empId", empId) ;
//											errorMap.put("dateStr", dateStr) ;
//											errorMap.put("isSupervisor", "YES") ;
//											errorMap.put("isShiftNo", isShiftNo ? "YES" : "NO") ;
//											
//											errorList.add(errorMap) ;
//										}
//									}
//								}
//							}
//							else
//							{
//								errorMap = new HashMap<String, String>();
//								errorMap.put("empId", empId) ;
//								errorMap.put("dateStr", dateStr) ;
//								errorMap.put("isSupervisor", isSupervisor ? "YES" : "NO") ;
//								
//								errorList.add(errorMap) ;
//							}
//						}
//					}
//				}
			}
		}
	}
}